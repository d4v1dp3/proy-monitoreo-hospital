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
@ManagedBean(name="HistoricoFrecCardResp")
public class ChartFrecuenciaCardResp implements Serializable{
    
    private LineChartModel model;
      
    
    public ChartFrecuenciaCardResp() {
        model = new LineChartModel();
        
        ChartSeries oxigeno = new ChartSeries();
        oxigeno.setLabel("Frecuencia Cardiaca (PPM)");
        oxigeno.set("09:00", 60);
        oxigeno.set("09:30", 84);
        oxigeno.set("10:00", 80);
        oxigeno.set("10:30", 68);
        oxigeno.set("11:00", 90);
        oxigeno.set("11:30", 84);
        oxigeno.set("12:00", 64);
        oxigeno.set("12:30", 80);
        oxigeno.set("13:00", 68);
        oxigeno.set("13:30", 88);
        
        ChartSeries temperatura = new ChartSeries();
        temperatura.setLabel("Fecuencia Respiratoria (RPM)");
        temperatura.set("09:00", 150.9);
        temperatura.set("09:30", 153.2);
        temperatura.set("10:00", 154.1);
        temperatura.set("10:30",154.3);
        temperatura.set("11:00", 150);
        temperatura.set("11:30", 153.4);
        temperatura.set("12:00", 153);
        temperatura.set("12:30", 154.3);
        temperatura.set("13:00", 154.2);
        temperatura.set("13:30", 154.3);
        
        model.addSeries(oxigeno);
        model.addSeries(temperatura);
        //model.setTitle("Category Chart");
        model.setLegendPosition("se");
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Tiempo(Horas)"));
        
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Frecuencia");
        yAxis.setMin(0);yAxis.setMax(200);
    }
    
    public LineChartModel getModel() {
        return model;
    }
    
}
