/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeprocesos;

import gestiondeprocesos.dao.ProcesoDAOImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author
 */
public class ControladorProcesos extends HttpServlet {

    private ProcesoDAOImpl dao = new ProcesoDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Init service");
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/") + 1);
        switch (accion) {
            case "index.do":
                response.sendRedirect("index.jsp");
                break;
            case "registrarproceso.do":
                registrar(request, response);
                break;
            case "procesos.do":
                cargar(request, response);
                break;
            default:
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void registrar(HttpServletRequest request, HttpServletResponse response) {
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        List<Proceso> procesos = new ArrayList<>();
        try {
            String str_proceso;
            String admin
                    = System.getenv("windir") + "\\system32\\" + "tasklist.exe /v";
            Process proceso = Runtime.getRuntime().exec(admin);
            try (BufferedReader input = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {
                int ronds = 0;
                while ((str_proceso = input.readLine()) != null) {
                    if (ronds > 2) {
                        String[] spl = str_proceso.split("  ");
                        List<String> listalimpia = new ArrayList<>();
                        for (String spl1 : spl) {
                            if (!spl1.isEmpty()) {
                                listalimpia.add(spl1);
                            }
                        }
                        //System.out.println(Arrays.toString(listalimpia.toArray()));
                        String[] pid = listalimpia.get(1).split(" ");
                        List<String> pidSpl = new ArrayList<>();
                        for (String pid1 : pid) {
                            if (!pid1.isEmpty()) {
                                pidSpl.add(pid1);
                            }
                        }
                        List<String> mem = new ArrayList<>();
                        String[] memSpl = listalimpia.get(3).split(" ");
                        for (String memSpl1 : memSpl) {
                            if (!memSpl1.isEmpty()) {
                                mem.add(memSpl1);
                            }
                        }
                        Proceso procesoMod = new Proceso();
                        procesoMod.setPid(Integer.parseInt(pidSpl.get(0).replace(" ", "")));
                        procesoMod.setNombre(listalimpia.get(0));
                        procesoMod.setUsuario(listalimpia.get(4));
                        procesoMod.setDescripcion(pidSpl.get(0) + "-Mem usage " + mem.get(0) + "k, status: " + mem.get(2)+", Cpu time usage: "+listalimpia.get(5));
                        procesoMod.setPrioridad((listalimpia.get(4).contains("N/A") ? 0 : 1));
                        procesos.add(procesoMod);
                        /*System.out.println("Pid: " + procesoMod.getPid());
                        System.out.println("Nombre: " + procesoMod.getNombre());
                        System.out.println("Usuario: " + procesoMod.getUsuario());
                        System.out.println("Descripcion: " + procesoMod.getDescripcion());
                        System.out.println("Prioridad: " + procesoMod.getPrioridad());*/
                    }
                    ronds++;
                }
            }
            for (int i = 0; i < cantidad; i++) {
                int seleccionRandom = (int)(Math.random()*procesos.size());
                Proceso pro = procesos.get(seleccionRandom);
                System.out.println(pro.lineaProceso());
                dao.create(pro);
            }
            cargar(request, response);
        } catch (IOException | NumberFormatException e) {
            System.err.println(e);
            Logger.getLogger(ControladorProcesos.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void cargar(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Proceso> procesos;
            procesos = dao.selects();
            request.getSession().setAttribute("procesos", procesos);
            response.sendRedirect("lista.jsp");
        } catch (IOException ex) {
            System.err.println(ex);
            Logger.getLogger(ControladorProcesos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
