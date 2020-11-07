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
@ManagedBean(name="HistoricoPresionArterial")
public class HistoricoPArterial implements Serializable{
    
    private LineChartModel model;
      
    
    public HistoricoPArterial() {
        model = new LineChartModel();
        
        ChartSeries sistolica = new ChartSeries();
        sistolica.setLabel("Presion Arterial Sistolica");
        sistolica.set("00:00", 110);
        sistolica.set("01:00", 112);
        sistolica.set("02:00", 123);
        sistolica.set("03:00", 120);
        sistolica.set("04:00", 130);
        sistolica.set("05:00", 120);
        sistolica.set("06:00", 130);
        sistolica.set("07:00", 112);
        sistolica.set("08:00", 126);
        sistolica.set("09:00", 140);
        sistolica.set("10:00", 130);
        sistolica.set("11:00", 134);
        sistolica.set("12:00", 120);
        sistolica.set("13:00", 120);
//        sistolica.set("14:00", 0);
//        sistolica.set("15:00", 0);
//        sistolica.set("16:00", 0);
//        sistolica.set("17:00", 0);
//        sistolica.set("18:00", 0);
//        sistolica.set("19:00", 0);
//        sistolica.set("20:00", 0);
//        sistolica.set("21:00", 0);
//        sistolica.set("22:00", 0);
//        sistolica.set("23:00", 0);  

        ChartSeries distolica = new ChartSeries();
        distolica.setLabel("Presion Arterial Distolica");
        distolica.set("00:00", 70);
        distolica.set("01:00", 71);
        distolica.set("02:00", 80);
        distolica.set("03:00", 82);
        distolica.set("04:00", 78);
        distolica.set("05:00", 80);
        distolica.set("06:00", 88);
        distolica.set("07:00", 80);
        distolica.set("08:00", 78);
        distolica.set("09:00", 76);
        distolica.set("10:00", 86);
        distolica.set("11:00", 84);
        distolica.set("12:00", 76);
        distolica.set("13:00", 80);
//        distolica.set("14:00", 78);
//        distolica.set("15:00", 85);
//        distolica.set("16:00", 84);
//        distolica.set("17:00", 80);
//        distolica.set("18:00", 70);
//        distolica.set("19:00", 76);
//        distolica.set("20:00", 85);
//        distolica.set("21:00", 70);
//        distolica.set("22:00", 78);
//        distolica.set("23:00", 80);
        
        model.addSeries(sistolica);
        model.addSeries(distolica);
        
        model.setTitle("Presion Arterial Sinstolica y Distolica");
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
