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
@ManagedBean(name="ParametrosTemperatura")
public class ChartTemperatura implements Serializable{
    
    
    private PieChartModel model;
    
    public ChartTemperatura() {
        model = new PieChartModel();
        
        model.set("Normal [36.2° - 37.2°]", 0);
        model.set("Hipotermia [Menos de 35°]", 0);
        model.set("Fiebre [Mas de 37.5°]", 0);
        model.set("Hipertermia [Mas de 40°]", 0);
        
        model.setLegendPosition("se");
        model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        model.setShadow(false);
        model.setSeriesColors("60d319,1984d3,ffc900,ff5500,ffffff");
        
        model.setExtender("customExtender");
    }
    
    public PieChartModel getModel() {return model;}

}