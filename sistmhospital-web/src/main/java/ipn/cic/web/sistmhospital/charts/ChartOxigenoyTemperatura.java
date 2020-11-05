/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.charts;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author J.PEREZ
 */
@ManagedBean(name="HistoricoOxigenoyTemperatura")
public class ChartOxigenoyTemperatura implements Serializable{
    
    private LineChartModel model;
      
    
    public ChartOxigenoyTemperatura() {
        model = new LineChartModel();
        
        ChartSeries oxigeno = new ChartSeries();
        oxigeno.setLabel("Saturacion de Oxigeno");
        oxigeno.set("09:00", 90);
        oxigeno.set("09:30", 92);
        oxigeno.set("10:00", 89);
        oxigeno.set("10:30", 88);
        oxigeno.set("11:00", 85);
        oxigeno.set("11:30", 89);
        oxigeno.set("12:00", 90);
        oxigeno.set("12:30", 85);
        oxigeno.set("13:00", 96);
        oxigeno.set("13:30", 98);
        
        ChartSeries temperatura = new ChartSeries();
        temperatura.setLabel("Temperatura");
        temperatura.set("09:00", 36);
        temperatura.set("09:30", 36.2);
        temperatura.set("10:00", 35.8);
        temperatura.set("10:30", 32);
        temperatura.set("11:00", 33);
        temperatura.set("11:30", 32);
        temperatura.set("12:00", 32);
        temperatura.set("12:30", 32.6);
        temperatura.set("13:00", 32.1);
        temperatura.set("13:30", 32);
        
        model.addSeries(oxigeno);
        model.addSeries(temperatura);
        //model.setTitle("Category Chart");
        model.setLegendPosition("se");
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Tiempo(Horas)"));
        
        Axis yAxis = model.getAxis(AxisType.Y);
        //yAxis.setLabel("Grados C");
        yAxis.setMin(0);yAxis.setMax(120);
    }
    
    public LineChartModel getModel() {
        return model;
    }
    
}
