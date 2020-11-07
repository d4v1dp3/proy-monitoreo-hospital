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
@ManagedBean(name="HistoricoFRespiratoria")
public class HistoricoFRespiratoria implements Serializable{
    
    private LineChartModel model;
    
    public HistoricoFRespiratoria() {
        model = new LineChartModel();
        ChartSeries p1 = new ChartSeries();
        p1.setLabel("Normal [12 - 20]");
        p1.set("00:00", 15);
        p1.set("01:00", 12);
        p1.set("02:00", 14);
        p1.set("03:00", 18);
        p1.set("04:00", 12);
        p1.set("05:00", 18);
        p1.set("06:00", 12);
        p1.set("07:00", 16);
        p1.set("08:00", 16);
        p1.set("09:00", 16);
        p1.set("10:00", 15);
        p1.set("11:00", 18);
        p1.set("12:00", 12);
        p1.set("13:00", 18);
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
        p2.setLabel("Bradipnea [Menos de 12]");
        
        ChartSeries p3 = new ChartSeries();
        p3.setLabel("Taquipnea [Mas de 20]");
        
        model.addSeries(p1);
        model.addSeries(p2);
        model.addSeries(p3);
        
        model.setSeriesColors("60d319,ffc900,ff5500");
        
        model.setTitle("Frecuencia Respiratoria");
        model.setLegendPosition("ne");
        model.setShowPointLabels(true);
        
        model.getAxes().put(AxisType.X, new CategoryAxis("Tiempo (Horas)"));
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Frecuencia (RPM)");
        yAxis.setMin(0);
        yAxis.setMax(120);
    }
    
    public LineChartModel getModel() {return model;}
    
}
