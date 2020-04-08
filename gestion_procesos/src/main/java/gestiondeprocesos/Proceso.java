/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeprocesos;

/**
 *
 * @author 
 */
public class Proceso {
    private int pid;
    private String nombre;
    private String usuario;
    private String descripcion;
    private int prioridad;

    public Proceso() {
    }

    public Proceso(int pid, String nombre, String usuario, String descripcion, int prioridad) {
        this.pid = pid;
        this.nombre = nombre;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
    public String lineaProceso(){
        return getPid()+"\t"+getNombre()+"\t"+getUsuario()+"\t"+getDescripcion()+"\t"+getPrioridad();
    }
}
