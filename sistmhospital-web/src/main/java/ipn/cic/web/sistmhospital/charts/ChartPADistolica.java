/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.charts;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author J.PEREZ
 */
@ManagedBean(name="GraficaPADistolica")
public class ChartPADistolica implements Serializable{
    private PieChartModel model;
    
    public ChartPADistolica() {
        model = new PieChartModel();
        model.set("Normal [70 - 90]", 100);
        model.set("Hipotension [Menos de 60 mmHg]", 0);
        model.set("Hipertension [Mas de 90 mmHg]", 0);
        
        
        model.setTitle("80 mmHg");
        model.setLegendPosition("s");
        model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        
        model.setShadow(false);
        model.setSeriesColors("60d319,ffc900,ff5500");
        model.setFill(true);
        model.setShowDataLabels(false);
        model.setDiameter(50);
        
        model.setExtender("customExtender");
    }
    
    public PieChartModel getModel() {return model;}
}
