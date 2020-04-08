/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consumidorgestordeprocesos.hilos;

import com.mycompany.consumidorgestordeprocesos.Archivo;
import com.mycompany.consumidorgestordeprocesos.Utilidad;
import com.mycompany.consumidorgestordeprocesos.modelo.ProcesoGestionado;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Geek-Programmer
 */
public class HiloProcesos implements Runnable {

    /**
     * Componentes de la vista
     */
    private ProcesoGestionado procesoActual;
    private LinkedList<ProcesoGestionado> lista;
    private LinkedList<ProcesoGestionado> terminados;
    private JTable procesosTerminados;
    private JTable procesosActuales;
    private JProgressBar progreso;
    private JLabel noProceso;
    private JLabel procentajeProgreso;
    /**
     * Variables de control por procesos
     */
    private int contador;//Contador del total de procesos que se van ingresando
    private int NProceso;//Carga el número de procesos ejecutándose
    private int Rafaga = 0;//Carga la ráfaga en ejecución
    private int Quantum = 0;//Carga el quantum en ejecución
    private int ResiduoRafaga = 0;//Carga el residuo en ejecución
    private int TiempoProceso = 0;//Carga el tiempo que se dura procesando
    private int ValorBarra;//Carga el progreso de la Barra
    private int CantidadProcesos;//Número de procesos terminados
    private int estado = 1;
    private int prioridad;
    private int posicionEnVista;
    private boolean suspendido;
    private boolean termino;

    /**
     * Constructor
     *
     * @param lista
     * @param procesosTerminados
     * @param procesosActuales
     * @param progreso
     * @param noProceso
     * @param procentajeProgreso
     */
    public HiloProcesos(LinkedList<ProcesoGestionado> lista, LinkedList<ProcesoGestionado> terminados, JTable procesosTerminados, JTable procesosActuales, JProgressBar progreso, JLabel noProceso, JLabel procentajeProgreso, boolean termino) {
        this.procesosTerminados = procesosTerminados;
        this.terminados = terminados;
        this.procesosActuales = procesosActuales;
        this.progreso = progreso;
        this.noProceso = noProceso;
        this.procentajeProgreso = procentajeProgreso;
        this.lista = lista;
        this.termino = termino;
        contador = lista.size();
        procesoActual = null;
    }

    public boolean isTermino() {
        return termino;
    }

    @Override
    public void run() {
        int aumentador = 0;//Controla que los procesos no se pasen de la rafaga 
        while (estado != 0) {//Mientras el estado sea diferente de 0 se ejecuta
            cargarProceso();//Se carga el primer proceso de la lista
            while (!lista.isEmpty()) {//Mientras la lista no este vacia se ejecuta el while
                if (ResiduoRafaga > 0) {//Si la rafaga es mayor que 0 entonces el proceso se ejecuta
                    if (ResiduoRafaga > Quantum && prioridad == 0) {//Si la prioridad es 0, se ejecuta hasta terminar
                        for (int i = 0; i < Rafaga; i++) {
                            procesosActuales.setValueAt("Procesando", posicionEnVista, 4);
                            copiarCaracter(i);//Realiza el copiado del caracter
                            ResiduoRafaga--;//Disminuye su rafaga
                            procesoActual.setResiduoRafa(ResiduoRafaga);//se le asigna al proceso actual
                            cargarBarra(Rafaga, ResiduoRafaga);//se muestra la barra
                            cargar();//se carga en la vista
                            procesosActuales.setValueAt(String.valueOf(ResiduoRafaga), posicionEnVista, 3);//Se muestra el residuo en la tabla
                            TiempoProceso++;//Aumenta el tiempo del proceso
                            detener();//Se detiene dependiendo el tiempo del hilo
                        }
                        limpiaTabla();
                        procesoFinalizado();
                        cargarProceso();
                    } else {
                        boolean detener = true;
                        while (detener) {
                            procesosActuales.setValueAt("Procesando", posicionEnVista, 4);
                            copiarCaracter(aumentador);
                            ResiduoRafaga--;
                            procesoActual.setResiduoRafa(ResiduoRafaga);
                            cargarBarra(Rafaga, ResiduoRafaga);
                            cargar();
                            procesosActuales.setValueAt(String.valueOf(ResiduoRafaga), posicionEnVista, 3);
                            TiempoProceso++;
                            detener();
                            aumentador++;
                            if (ResiduoRafaga == 0) {
                                detener = false;
                                aumentador = 0;
                            }

                            if (aumentador == Quantum) {
                                detener = false;
                                aumentador = 0;
                            }
                        }
                        if (ResiduoRafaga == 0) {
                            limpiaTabla();
                            procesoFinalizado();
                            cargarProceso();
                        } else {
                            //Queda en espera
                            procesosActuales.setValueAt("Listo", posicionEnVista, 4);
                            lista.addLast(procesoActual);
                            lista.removeFirst();
                            cargarProceso();
                        }
                    }
                } else {
                    limpiaTabla();
                    procesoFinalizado();
                    cargarProceso();
                }
                synchronized (this) {
                    while (suspendido) {
                        try {
                            wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(HiloProcesos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            noProceso.setText("");
            procentajeProgreso.setText("0%");
            progreso.setValue(0);
            progreso.repaint();
            estado = 0;
            termino = true;
        }
    }

    public void suspendido() {
        suspendido = true;
    }

    public synchronized void resumir() {
        suspendido = false;
        notify();
    }

    private void cargarProceso() {
        if (!lista.isEmpty()) {
            procesoActual = lista.getFirst();
            procesoActual.setNoEjecuciones(procesoActual.getNoEjecuciones() + 1);
            posicionEnVista = procesoActual.getPosicion();
            prioridad = procesoActual.getPrioridad();
            NProceso = procesoActual.getPid();
            Rafaga = procesoActual.getRafaga();
            Quantum = Utilidad.quantum;
            ResiduoRafaga = procesoActual.getResiduoRafa();
            noProceso.setText(String.valueOf(NProceso));
        }
    }

    private void cargarBarra(int rafaga, int residuoRafaga) {
        int valor = 100 / rafaga;
        int porcentaje = 100 - (valor * residuoRafaga);
        ValorBarra = porcentaje;
        procentajeProgreso.setText(String.valueOf(ValorBarra + "%"));
    }

    private void cargar() {
        progreso.setValue(ValorBarra);
        progreso.repaint();
    }

    private void detener() {
        try {
            Thread.sleep(Utilidad.tiempoHilo); //Dormir sistema
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloProcesos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void procesoFinalizado() {
        DefaultTableModel modelo = (DefaultTableModel) procesosTerminados.getModel();
        int tiempoLlegada = posicionEnVista + 1;
        Object[] miTabla = new Object[7];
        miTabla[0] = procesoActual.getPid();
        miTabla[1] = procesoActual.getNombre();
        miTabla[2] = tiempoLlegada * 1000;
        miTabla[3] = procesoActual.getRafaga();
        miTabla[4] = procesoActual.getPrioridad();
        miTabla[5] = (TiempoProceso - tiempoLlegada) * 1000;
        miTabla[6] = (Quantum * procesoActual.getNoEjecuciones() * 1000) + " Milisegundos";
        modelo.addRow(miTabla);
        procesosTerminados.setModel(modelo);
        CantidadProcesos++;
        ProcesoGestionado pgt = lista.removeFirst();
        pgt.setTurnaRound((TiempoProceso - tiempoLlegada) * 1000);
        terminados.addLast(pgt);
        procesoActual = null;
    }

    private void limpiaTabla() {
        procesosActuales.setValueAt("Terminado", posicionEnVista, 4);
    }

    private void copiarCaracter(int posicion) {
        if (posicion < procesoActual.getCaracteres().length()) {
            char cartcr = procesoActual.getCaracteres().charAt(posicion);
            guardarEnArchivo(procesoActual.getNombre());
            guardarEnArchivo(String.valueOf(cartcr));
            procesoActual.settRafaga(procesoActual.gettRafaga() + 1);
        }
    }

    private void guardarEnArchivo(String toString) {
        Archivo a = new Archivo();
        try {
            a.guardar(toString);
        } catch (IOException ex) {
            Logger.getLogger(HiloProcesos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
