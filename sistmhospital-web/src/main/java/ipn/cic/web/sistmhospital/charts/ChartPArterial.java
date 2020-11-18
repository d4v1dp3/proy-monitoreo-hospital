/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.charts;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author J.PEREZ
 */

@Named(value="parametrosPArterial")
@ViewScoped
public class ChartPArterial implements Serializable{
    private PieChartModel model = new PieChartModel();
    
    public ChartPArterial() {
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
