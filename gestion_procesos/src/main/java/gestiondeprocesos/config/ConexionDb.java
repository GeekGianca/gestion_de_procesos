/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeprocesos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class ConexionDb extends ConfigConexion{

    public ConexionDb(String params[]) {
        this.params = params;
        this.open();
    }

    @Override
    Connection open() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //jdbc:mysql://localhost:3306/gprocesos?zeroDateTimeBehavior=convertToNull
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+this.params[0]+"?autoReconnect=true&useSSL=false", this.params[1], this.params[2]);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConexionDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.conn;
    }
    
}
