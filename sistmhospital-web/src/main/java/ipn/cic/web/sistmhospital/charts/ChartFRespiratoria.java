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

@Named(value="parametrosFRespiratoria")
@ViewScoped
public class ChartFRespiratoria implements Serializable{
    private PieChartModel model = new PieChartModel();
    
    public ChartFRespiratoria() {
        model.set("Normal [12 - 20]", 0);
        model.set("Bradipnea [Menos de 12]", 0);
        model.set("Taquipnea [Mas de 20]", 0);
        
        model.setLegendPosition("se");
        model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        
        model.setShadow(false);
        model.setSeriesColors("60d319,ffc900,ff5500");
        
        model.setExtender("customExtender");
    }
    
    public PieChartModel getModel() {return model;}
}
