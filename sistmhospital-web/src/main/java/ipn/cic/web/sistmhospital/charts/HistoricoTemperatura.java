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
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author J.PEREZ
 */
@ManagedBean(name="HistoricoTemperatura")
public class HistoricoTemperatura implements Serializable{
    
    private BarChartModel model;
    
    public HistoricoTemperatura() {
        model = new BarChartModel();
                
        ChartSeries saturacion1 = new ChartSeries();
        saturacion1.setLabel("Normal [36.2° - 37.2°]");
        saturacion1.set("00:00", 36.2);
        saturacion1.set("01:00", 36.3);
        saturacion1.set("02:00", 36.4);
        saturacion1.set("03:00", 36);
        saturacion1.set("04:00", 36);
        saturacion1.set("05:00", 36);
        saturacion1.set("06:00", 36);
        saturacion1.set("07:00", 36);
        saturacion1.set("08:00", 36);
        saturacion1.set("09:00", 36);
        saturacion1.set("10:00", 36);
        saturacion1.set("11:00", 36);
        saturacion1.set("12:00", 36);
        saturacion1.set("13:00", 36);
        saturacion1.set("14:00", 0);
        saturacion1.set("15:00", 0);
        saturacion1.set("16:00", 0);
        saturacion1.set("17:00", 0);
        saturacion1.set("18:00", 0);
        saturacion1.set("19:00", 0);
        saturacion1.set("20:00", 0);
        saturacion1.set("21:00", 0);
        saturacion1.set("22:00", 0);
        saturacion1.set("23:00", 0);   

        ChartSeries saturacion2 = new ChartSeries();
        saturacion2.setLabel("Hipotermia [Menos de 35°]");
        
        ChartSeries saturacion3 = new ChartSeries();
        saturacion3.setLabel("Fiebre [Mas de 37.5°]");
        
        ChartSeries saturacion4 = new ChartSeries();
        saturacion4.setLabel("Hipertermia [Mas de 40°]");

        model.addSeries(saturacion1);
        model.addSeries(saturacion2);
        model.addSeries(saturacion3);
        model.addSeries(saturacion4);
        
        model.setTitle("Temperatura");
        model.setLegendPosition("se");
        model.setShowPointLabels(true);
        model.setStacked(true);
        //model.setBarPadding(5);
        model.setBarWidth(8);
        
         model.setSeriesColors("60d319,1984d3,ffc900,ff5500");
        
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Tiempo (Horas)");
        
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Grados (°C)");
        yAxis.setMin(0);
        yAxis.setMax(50);
    }
    
    public BarChartModel getModel() { return model; }
    
    
    
//    private BarChartModel model;
//    
//    public HistoricoTemperatura() {
//        model = new BarChartModel();
//        
//        ChartSeries d1 = new ChartSeries();
//        d1.setLabel("Normal [36.2° - 37.2°]");
//        d1.set("00:00", 36.4);
//        d1.set("01:00", 36.1);
//        d1.set("02:00", 36.0);
//        d1.set("03:00", 36.3);
//        d1.set("04:00", 36.1);
//        d1.set("05:00", 36.0);
//        d1.set("06:00", 36.5);
//        d1.set("07:00", 36.4);
//        d1.set("08:00", 36.4);
//        d1.set("09:00", 36.3);
//        d1.set("10:00", 36.3);
//        d1.set("11:00", 36.1);
//        d1.set("12:00", 36.0);
//        d1.set("13:00", 36.3);
//        d1.set("14:00", 36.0);
//        d1.set("15:00", 36.3);
//        d1.set("16:00", 36.4);
//        d1.set("17:00", 36.7);
//        d1.set("18:00", 36.5);
//        d1.set("19:00", 36.3);
//        d1.set("20:00", 36.2);
//        d1.set("21:00", 36.6);
//        d1.set("22:00", 36.7);
//        d1.set("23:00", 36.5);
//        
//        ChartSeries d2 = new ChartSeries();
//        d2.setLabel("Hipotermia [Menos de 35°]");
//        
//        ChartSeries d3 = new ChartSeries();
//        d3.setLabel("Fiebre [Mas de 37.5°]");
//        
//        ChartSeries d4 = new ChartSeries();
//        d4.setLabel("Hipertermia [Mas de 40°]");
//
//        model.addSeries(d2);
//        model.addSeries(d1);        
//        model.addSeries(d3);
//        model.addSeries(d4);
//        
//        model.setTitle("Temperatura");
//        model.setShowPointLabels(true);
//        model.setLegendPosition("se");
//        
//        model.setSeriesColors("00ecff,60d319,ffc900,ff5500");
//        
//        Axis xAxis = model.getAxis(AxisType.X);
//        xAxis.setLabel("Tiempo (Horas)");
//        
//        Axis yAxis = model.getAxis(AxisType.Y);
//        yAxis.setLabel("Grados Centigrados (°C)");
//        yAxis.setMin(0);
//        yAxis.setMax(45);
//    }
//    
//    public BarChartModel getModel() { return model; }
    
}
