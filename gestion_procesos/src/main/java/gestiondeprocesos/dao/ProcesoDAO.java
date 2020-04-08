/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeprocesos.dao;

import gestiondeprocesos.Proceso;
import java.util.List;

/**
 *
 * @author 
 */
public interface ProcesoDAO {
    boolean create(Proceso p);
    boolean delete(int pid);
    List<Proceso> selects();
}
