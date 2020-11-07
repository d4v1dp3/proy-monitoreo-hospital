/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.charts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.OhlcChartSeries;
import org.primefaces.model.chart.PieChartModel;
/**
 *
 * @author J.PEREZ
 */

@ManagedBean(name="HistoricoFCardiaca")
public class HistoricoFCardiaca implements Serializable{
    
    private LineChartModel model;
    
    public HistoricoFCardiaca() {
        model = new LineChartModel();
        ChartSeries p1 = new ChartSeries();
        p1.setLabel("Normal [60 - 100]");
        p1.set("00:00", 85);
        p1.set("01:00", 86);
        p1.set("02:00", 80);
        p1.set("03:00", 88);
        p1.set("04:00", 80);
        p1.set("05:00", 85);
        p1.set("06:00", 90);
        p1.set("07:00", 96);
        p1.set("08:00", 85);
        p1.set("09:00", 80);
        p1.set("10:00", 90);
        p1.set("11:00", 92);
        p1.set("12:00", 94);
        p1.set("13:00", 94);
//        p1.set("14:00", 0);
//        p1.set("15:00", 0);
//        p1.set("16:00", 0);
//        p1.set("17:00", 0);
//        p1.set("18:00", 0);
//        p1.set("19:00", 0);
//        p1.set("20:00", 0);
//        p1.set("21:00", 0);
//        p1.set("22:00", 0);
//        p1.set("23:00", 0);   

        ChartSeries p2 = new ChartSeries();
        p2.setLabel("Bradicardia [Menos de 60]");
        
        ChartSeries p3 = new ChartSeries();
        p3.setLabel("Taquiardia [Mas de 100]");
        
        model.addSeries(p1);
        model.addSeries(p2);
        model.addSeries(p3);
        
        model.setSeriesColors("60d319,ffc900,ff5500");
        
        model.setTitle("Frecuencia Cardiaca");
        model.setLegendPosition("se");
        model.setShowPointLabels(true);
        
        model.getAxes().put(AxisType.X, new CategoryAxis("Tiempo (Horas)"));
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Frecuencia (LPM)");
        yAxis.setMin(0);
        yAxis.setMax(120);
    }
    
    public LineChartModel getModel() {return model;}
    
//    private LineChartModel model;
//
//    public HistoricoFCardiaca() {
//        model = new LineChartModel();
//        
//        ChartSeries d1 = new ChartSeries();
//        d1.setLabel("Normal [60 - 100]");
//        d1.set("16:00", 70);
//
//        ChartSeries d2 = new ChartSeries();
//        d2.setLabel("Bradicardia [Menos de 60]");
//        
//        ChartSeries d3 = new ChartSeries();
//        d3.setLabel("Taquiardia [Mas de 100]");
//
//        model.addSeries(d2);
//        model.addSeries(d1);        
//        model.addSeries(d3);
//        
//        model.setTitle("Frecuencia Cardiaca");
//        model.setLegendPosition("se");
//        
//        model.setSeriesColors("00ecff,ffc900,ff5500");
//       // model.setExtender("chartExtenderFCard");
//        
//        Axis xAxis = model.getAxis(AxisType.X);
//        xAxis.setLabel("Tiempo (Horas)");
//        
//        Axis yAxis = model.getAxis(AxisType.Y);
//        yAxis.setLabel("Latidos por Minuto (LPM)");
//        yAxis.setMin(0);
//        yAxis.setMax(45);
//    }
//    
//    public LineChartModel getModel() { return model; }
//    
}
