/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.charts;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author J.PEREZ
 */

@Named(value="parametrosFCardiaca")
@ViewScoped
public class ChartFCardiaca implements Serializable{

    
    private PieChartModel model = new PieChartModel();
    
    @PostConstruct    
    public void ChartFCardiaca() {
        model.set("Normal [60 - 100]", 0);
        model.set("Bradicardia [Menos de 60]", 0);
        model.set("Taquiardia [Mas de 100]", 0);
        
        model.setLegendPosition("se");
        model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        
        model.setShadow(false);
        model.setSeriesColors("60d319,ffc900,ff5500");
        
        model.setExtender("customExtender");
    }
    
    public PieChartModel getModel() {
        return model;
    }
    
    public void setModel(PieChartModel model) {
        this.model = model;
    }
}