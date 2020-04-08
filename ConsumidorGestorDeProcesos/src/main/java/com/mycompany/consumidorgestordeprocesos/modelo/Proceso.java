/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consumidorgestordeprocesos.modelo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Geek-Programmer
 */
@XmlRootElement
public class Proceso implements Serializable {

    private Integer pid;
    private int quantum;
    private String nombre;
    private int prioridad;
    private String descripcion;
    private String usuario;
    private String caracter;

    public Proceso() {
    }

    public Proceso(Integer pid, int quantum, String nombre, int prioridad, String descripcion, String usuario, String caracter) {
        this.pid = pid;
        this.quantum = quantum;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.caracter = caracter;
    }

    public Proceso(Integer pid, int quantum, String nombre, int prioridad, String caracteres, String caracter) {
        this.pid = pid;
        this.quantum = quantum;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.descripcion = caracteres;
        this.caracter = caracter;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
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

    public String getCaracteres() {
        return descripcion;
    }

    public void setCaracteres(String caracteres) {
        this.descripcion = caracteres;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    @Override
    public String toString() {
        return "Proceso{" + "pid=" + pid + ", quantum=" + quantum + ", nombre=" + nombre + ", prioridad=" + prioridad + ", caracteres=" + descripcion + ", caracter=" + caracter + '}';
    }

}
