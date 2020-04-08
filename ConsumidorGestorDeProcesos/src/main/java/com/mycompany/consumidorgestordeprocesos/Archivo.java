/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consumidorgestordeprocesos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Gianc
 */
public class Archivo {

    private File archivoF;
    private String archivo;

    public Archivo() {
        this.archivo = "procesos.txt";
    }

    public void guardar(String info) throws IOException {
        archivoF = new File(archivo);
        BufferedWriter escribir;
        if (archivoF.exists()) {
            escribir = new BufferedWriter(new FileWriter(archivoF, true));
            escribir.write(info);
            escribir.newLine();
        } else {
            escribir = new BufferedWriter(new FileWriter(archivoF));
            escribir.write(info);
            escribir.newLine();
        }
        escribir.close();
    }
}
