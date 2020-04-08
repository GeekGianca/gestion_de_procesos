/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consumidorgestordeprocesos.rest;

import com.mycompany.consumidorgestordeprocesos.modelo.ListaProcesos;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Geek-Programmer
 */
public class ProcesosRest {
    private WebResource webResource;
    private Client client;
    
    private final String BASE_URL = "http://localhost:8080/gestion_procesos/";

    public ProcesosRest() {
        client = Client.create(new DefaultClientConfig());
        webResource = client.resource(BASE_URL).path("webapi/");
    }
    
    public ListaProcesos findAll() throws Exception{
        WebResource wr = webResource;
        return wr.path("myresource").accept(MediaType.APPLICATION_XML)
                .get(ListaProcesos.class);
    }
}
