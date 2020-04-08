/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeprocesos;

import gestiondeprocesos.dao.ProcesoDAOImpl;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author
 */
@XmlRootElement
public class ListaProcesos {

    private List<Proceso> lista;

    public ListaProcesos() {
        ProcesoDAOImpl dao = new ProcesoDAOImpl();
        lista = dao.selects();
    }

    @XmlElement(name = "proceso")
    public List<Proceso> getLista() {
        return lista;
    }

    public void setLista(List<Proceso> lista) {
        this.lista = lista;
    }
}
