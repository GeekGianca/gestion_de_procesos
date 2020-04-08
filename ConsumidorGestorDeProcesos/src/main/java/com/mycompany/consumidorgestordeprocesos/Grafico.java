/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consumidorgestordeprocesos;

import com.mycompany.consumidorgestordeprocesos.modelo.ProcesoGestionado;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Geek-Programmer
 */
public class Grafico extends JFrame{
    JPanel panel;
    private LinkedList<ProcesoGestionado> cola;
    public Grafico(LinkedList<ProcesoGestionado> cola){
        setTitle("Procesos Vs TurnAround");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        this.cola = cola;
        init();
    }
 
    private void init() {
        panel = new JPanel();
        getContentPane().add(panel);
        // Fuente de Datos
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        cola.forEach(pg -> 
            line_chart_dataset.addValue(Double.parseDouble(pg.getTurnaRound()+""), "Proceso", pg.getNombre())
        );   
 
        // Creando el Grafico
        JFreeChart chart=ChartFactory.createLineChart("Procesos Vs TurnAround",
                "Proceso","TurnAround",line_chart_dataset,PlotOrientation.VERTICAL,
                true,true,false);  
        
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
    }
    
}
