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
import org.primefaces.model.chart.LegendPlacement;
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
@ManagedBean(name="GraficaTemperatura")
public class ChartTemperatura implements Serializable{
    
    
    private PieChartModel model;
    
    public ChartTemperatura() {
        model = new PieChartModel();
        
        model.set("Normal [36.2° - 37.2°]", 100);
        model.set("Hipotermia [Menos de 35°]", 0);
        model.set("Fiebre [Mas de 37.5°]", 0);
        model.set("Hipertermia [Mas de 40°]", 0);
        
        model.setTitle("36.4%");
        model.setLegendPosition("s");
        model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        model.setShadow(false);
        model.setSeriesColors("60d319,1984d3,ffc900,ff5500,ffffff");
        model.setFill(true);
        model.setShowDataLabels(false);
        model.setDiameter(50);
        
        model.setExtender("customExtender");
    }
    
    public PieChartModel getModel() {return model;}

//    private PieChartModel model;
//    
//    public ChartTemperatura() {
//        model = new PieChartModel();
//        
//        
//        Map<String, Number> temp = new LinkedHashMap<String, Number>();
//        temp.put("Normal [36.2° - 37.2°]", 100);  
//        temp.put("Hipotermia [Menos de 35°]", 1);  
//        temp.put("Fiebre [Mas de 37.5°]", 1);  
//        temp.put("Hipertermia [Mas de 40°]", 1); 
//        temp.put("", 0);
//        model.addCircle(temp);
//        
//        Map<String, Number> blank = new LinkedHashMap<String, Number>();
//        blank.put("Normal [36.2° - 37.2°]", 0);  
//        blank.put("Hipotermia [Menos de 35°]", 0);  
//        blank.put("Fiebre [Mas de 37.5°]", 0);  
//        blank.put("Hipertermia [Mas de 40°]", 0); 
//        blank.put("", 1000);
//        model.addCircle(blank);
//        model.addCircle(blank);
//        
//        
//        
////        Map<String, Number> hipotermia = new LinkedHashMap<String, Number>();
////        hipotermia.put("Hipotermia [Menos de 35°]", 1);        
////        model.addCircle(hipotermia);        
////        
////        Map<String, Number> fiebre = new LinkedHashMap<String, Number>();
////        fiebre.put("Fiebre [Mas de 37.5°]", 1);        
////        model.addCircle(fiebre);
////        
////        Map<String, Number> hipertermia = new LinkedHashMap<String, Number>();
////        hipertermia.put("Hipertermia [Mas de 40°]", 1);        
////        model.addCircle(hipertermia);
//        
//        model.setSeriesColors("00ecff,60d319,ffc900,ff5500, ffffff");
//        model.setExtender("customExtender");
//
//        //model.setLegendPosition("");
//        model.setShowDataLabels(false);
//        model.setDataFormat("value");
//        model.setShadow(false);
//        model.setTitle("36.4 °C");
//        model.setLegendPosition("s");
//        model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
//    }
//    
//    public DonutChartModel getModel() { return model; }
}