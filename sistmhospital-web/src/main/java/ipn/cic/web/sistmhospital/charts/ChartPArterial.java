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
@ManagedBean(name="ParametrosPArterial")
public class ChartPArterial implements Serializable{
    private PieChartModel model;
    
    public ChartPArterial() {
        model = new PieChartModel();
        model.set("Normal [110/70 - 140/90] mmHg", 0);
        model.set("Hipotension [Menos de 100/60 mmHg]", 0);
        model.set("Hipertension [Mas de 140/90 mmHg]", 0);
        
        model.setLegendPosition("se");
        model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        
        model.setShadow(false);
        model.setSeriesColors("60d319,ffc900,ff5500");
        
        model.setExtender("customExtender");
    }
    
    public PieChartModel getModel() {return model;}
}
