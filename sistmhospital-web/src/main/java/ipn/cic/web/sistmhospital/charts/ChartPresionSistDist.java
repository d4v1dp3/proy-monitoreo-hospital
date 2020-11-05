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
@ManagedBean(name="HistoricoPresionSistDist")
public class ChartPresionSistDist implements Serializable{
    
    private LineChartModel model;
      
    
    public ChartPresionSistDist() {
        model = new LineChartModel();
        
        ChartSeries oxigeno = new ChartSeries();
        oxigeno.setLabel("Presion Arterial Sistolica");
        oxigeno.set("09:00", 110);
        oxigeno.set("09:30", 112);
        oxigeno.set("10:00", 124);
        oxigeno.set("10:30", 113);
        oxigeno.set("11:00", 134);
        oxigeno.set("11:30", 130);
        oxigeno.set("12:00", 112);
        oxigeno.set("12:30", 140);
        oxigeno.set("13:00", 138);
        oxigeno.set("13:30", 130);
        
        ChartSeries temperatura = new ChartSeries();
        temperatura.setLabel("Presion Arterial Distolica");
        temperatura.set("09:00", 70);
        temperatura.set("09:30", 71);
        temperatura.set("10:00", 80);
        temperatura.set("10:30",74);
        temperatura.set("11:00", 82);
        temperatura.set("11:30", 90);
        temperatura.set("12:00", 80);
        temperatura.set("12:30", 85);
        temperatura.set("13:00", 70);
        temperatura.set("13:30", 73);
        
        model.addSeries(oxigeno);
        model.addSeries(temperatura);
        //model.setTitle("Category Chart");
        model.setLegendPosition("se");
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Tiempo(Horas)"));
        
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("mmHg");
        yAxis.setMin(0);yAxis.setMax(200);
    }
    
    public LineChartModel getModel() {
        return model;
    }
    
}
