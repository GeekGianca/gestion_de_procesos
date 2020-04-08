/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeprocesos.dao;

import gestiondeprocesos.Proceso;
import gestiondeprocesos.config.ConfigConexion;
import gestiondeprocesos.config.FactoriaMysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class ProcesoDAOImpl implements ProcesoDAO{
    
    ConfigConexion conf;
    
    @Override
    public boolean create(Proceso p) {
        System.out.println("Guardando...");
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean guardar = false;
        try{
            String sql = String.format("INSERT INTO `proceso`(`pid`, `nombre`, `usuario`, `descripcion`, `prioridad`) VALUES (%s, '%s', '%s', '%s', %s)", p.getPid(), p.getNombre(), p.getUsuario(), p.getDescripcion(), p.getPrioridad());
            System.out.println("Obteniendo...");
            guardar = this.conf.execute(sql);
        }catch(Exception ex){
            delete(p.getPid());
            create(p);
            Logger.getLogger(ProcesoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            this.conf.close();
        }
        return guardar;
    }

    @Override
    public boolean delete(int pid) {
         boolean eliminar = false;
         this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        try{
            String sql = String.format("DELETE FROM `proceso` WHERE pid = %s", pid);
            eliminar = this.conf.execute(sql);
        }catch(Exception e){
            Logger.getLogger(ProcesoDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.conf.close();
        }
        return eliminar;
    }

    @Override
    public List<Proceso> selects() {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM `proceso`;");
        List<Proceso> listar = null;
        try {
            listar = new ArrayList<>();
            ResultSet rs = this.conf.query(sql.toString());
            while (rs.next()) {
                Proceso proceso = new Proceso();
                proceso.setPid(rs.getInt("pid"));
                proceso.setNombre(rs.getString("nombre"));
                proceso.setUsuario(rs.getString("usuario"));
                proceso.setDescripcion(rs.getString("descripcion"));
                proceso.setPrioridad(rs.getInt("prioridad"));
                listar.add(proceso);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProcesoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return listar;
    }
    
}
