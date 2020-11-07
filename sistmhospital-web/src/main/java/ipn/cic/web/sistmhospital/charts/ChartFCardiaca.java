/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.charts;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author J.PEREZ
 */

@ManagedBean(name="GraficaFCardiaca")
public class ChartFCardiaca implements Serializable{
     private PieChartModel model;
    
    public ChartFCardiaca() {
        model = new PieChartModel();
        model.set("Normal [60 - 100]", 100);
        model.set("Bradicardia [Menos de 60]", 0);
        model.set("Taquiardia [Mas de 100]", 0);
        
        model.setTitle("68 LPM");
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