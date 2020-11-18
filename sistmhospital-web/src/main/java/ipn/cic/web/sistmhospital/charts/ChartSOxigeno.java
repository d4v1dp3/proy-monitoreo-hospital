/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.charts;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author J.PEREZ
 */

@Named(value="parametrosSOxigeno")
@ViewScoped
public class ChartSOxigeno implements Serializable{
    private PieChartModel model = new PieChartModel();
    
    public ChartSOxigeno() {
        model.set("Normal [95% - 100%]", 0);
        model.set("Hipoxia Leve [91% - 94%]", 0);
        model.set("Hipoxia Moderada [86% - 90%]", 0);
        model.set("Hipoxia Grave [Menos de 85%]", 0);
        
        model.setLegendPosition("se");
        model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        model.setShadow(false);
        model.setSeriesColors("60d319,fcf330,fcac30,ff5500");
        
        model.setExtender("customExtender");
    }
    
    public PieChartModel getModel() {return model;}

}