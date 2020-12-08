/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.charts;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author J.PEREZ
 */

@Named(value="historicoSOxigeno")
@ViewScoped
public class HistoricoSOxigeno implements Serializable{

   
    private BarChartModel model = new BarChartModel();
    
    public HistoricoSOxigeno(){}
    
    @PostConstruct   
    public void HistoricoSOxigeno() {
        ChartSeries saturacion1 = new ChartSeries();
        saturacion1.setLabel("Normal [95% - 100%]");
        saturacion1.set("00:00", 85);
        saturacion1.set("01:00", 86);
        saturacion1.set("02:00", 80);
        saturacion1.set("03:00", 88);
        saturacion1.set("04:00", 80);
        saturacion1.set("05:00", 85);
        saturacion1.set("06:00", 90);
        saturacion1.set("07:00", 96);
        saturacion1.set("08:00", 85);
        saturacion1.set("09:00", 80);
        saturacion1.set("10:00", 90);
        saturacion1.set("11:00", 92);
        saturacion1.set("12:00", 94);
        saturacion1.set("13:00", 94);
//        saturacion1.set("14:00", 0);
//        saturacion1.set("15:00", 0);
//        saturacion1.set("16:00", 0);
//        saturacion1.set("17:00", 0);
//        saturacion1.set("18:00", 0);
//        saturacion1.set("19:00", 0);
//        saturacion1.set("20:00", 0);
//        saturacion1.set("21:00", 0);
//        saturacion1.set("22:00", 0);
//        saturacion1.set("23:00", 0);   

        ChartSeries saturacion2 = new ChartSeries();
        saturacion2.setLabel("Hipoxia Leve [91% - 94%]");
        
        ChartSeries saturacion3 = new ChartSeries();
        saturacion3.setLabel("Hipoxia Moderada [86% - 90%]");
        
        ChartSeries saturacion4 = new ChartSeries();
        saturacion4.setLabel("Hipoxia Grave [Menos de 85%]");

        model.addSeries(saturacion1);
        model.addSeries(saturacion2);
        model.addSeries(saturacion3);
        model.addSeries(saturacion4);
        
        model.setTitle("Saturacion de Oxigeno");
        model.setLegendPosition("se");
        model.setShowPointLabels(true);
        model.setStacked(true);
        //model.setBarPadding(5);
        model.setBarWidth(8);
        
        model.setSeriesColors("60d319,fcf330,fcac30,ff5500");
        
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Tiempo (Horas)");
        
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Porcentaje (%)");
        yAxis.setMin(0);
        yAxis.setMax(120);
    }
    
    public BarChartModel getModel() { 
        return model; 
    }
    
     public void setModel(BarChartModel model) {
        this.model = model;
    }
}
