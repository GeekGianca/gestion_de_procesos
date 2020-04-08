/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consumidorgestordeprocesos.modelo;

/**
 *
 * @author Geek-Programmer
 */
public class ProcesoGestionado {
    private Integer pid;
    private String nombre;
    private int prioridad;
    private String usuario;
    private int rafaga;
    private int residuoRafa;
    private String caracteres;
    private String caracter;
    private int tiempoLlegada;
    private int turnaRound;
    private int tiempoFinalizacion;
    private int posicion;
    private int tRafaga;
    private int noEjecuciones;

    public ProcesoGestionado() {
        this.posicion = -1;
    }

    public ProcesoGestionado(Integer pid, String nombre, int prioridad, int rafaga, int residuoRafa, String caracteres, String caracter, int tiempoLlegada, int turnaRound, int tiempoFinalizacion) {
        this.pid = pid;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.rafaga = rafaga;
        this.residuoRafa = residuoRafa;
        this.caracteres = caracteres;
        this.caracter = caracter;
        this.tiempoLlegada = tiempoLlegada;
        this.turnaRound = turnaRound;
        this.tiempoFinalizacion = tiempoFinalizacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getNoEjecuciones() {
        return noEjecuciones;
    }

    public void setNoEjecuciones(int noEjecuciones) {
        this.noEjecuciones = noEjecuciones;
    }

    public int gettRafaga() {
        return tRafaga;
    }

    public void settRafaga(int tRafaga) {
        this.tRafaga = tRafaga;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getRafaga() {
        return rafaga;
    }

    public void setRafaga(int rafaga) {
        this.rafaga = rafaga;
    }

    public int getResiduoRafa() {
        return residuoRafa;
    }

    public void setResiduoRafa(int residuoRafa) {
        this.residuoRafa = residuoRafa;
    }

    public String getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(String caracteres) {
        this.caracteres = caracteres;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public int getTurnaRound() {
        return turnaRound;
    }

    public void setTurnaRound(int turnaRound) {
        this.turnaRound = turnaRound;
    }

    public int getTiempoFinalizacion() {
        return tiempoFinalizacion;
    }

    public void setTiempoFinalizacion(int tiempoFinalizacion) {
        this.tiempoFinalizacion = tiempoFinalizacion;
    }
    
    public void capturarProcesosRest(Proceso proceso){
        this.setPid(proceso.getPid());
        this.setNombre(proceso.getNombre());
        this.setCaracter(proceso.getCaracter());
        this.setCaracteres(proceso.getCaracteres());
        this.setPrioridad(proceso.getPrioridad());
    }
}
