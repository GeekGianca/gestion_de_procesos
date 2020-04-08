/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeprocesos.config;

/**
 *
 * @author 
 */
public class FactoriaMysql {
    public static final int MYSQL = 1;
    public static String confiMYSQL[] = {
        "gestiondeprocesos", "root", "admin23"
    };
    
    public static ConfigConexion getInstanceOpenConexion(int tipoBd){
        switch (tipoBd) {
            case FactoriaMysql.MYSQL:
                return new ConexionDb(confiMYSQL);
            default:
                System.out.println("Conexion abierta");
        }
        return null;
    }
}
