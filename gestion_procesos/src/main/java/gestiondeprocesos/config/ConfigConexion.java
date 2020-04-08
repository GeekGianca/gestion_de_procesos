/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeprocesos.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public abstract class ConfigConexion {

    protected String params[];
    protected Connection conn;

    abstract Connection open();

    public ResultSet query(String query) {
        Statement estatuto;
        ResultSet rs = null;
        try {
            estatuto = conn.createStatement();
            rs = estatuto.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConfigConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean execute(String query) {
        Statement estatuto;
        boolean guardar = true;
        try {
            estatuto = conn.createStatement();
            estatuto.executeUpdate(query);
        } catch (SQLException ex) {
            guardar = false;
            Logger.getLogger(ConfigConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardar;
    }

    public boolean close() {
        boolean ok = true;
        try {
            conn.close();
        } catch (SQLException ex) {
            ok = false;
            Logger.getLogger(ConfigConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
}
