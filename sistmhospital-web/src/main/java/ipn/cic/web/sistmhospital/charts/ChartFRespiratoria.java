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

@ManagedBean(name="ParametrosFRespiratoria")
public class ChartFRespiratoria implements Serializable{
    
    private PieChartModel model;
    
    public ChartFRespiratoria() {
        model = new PieChartModel();
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
