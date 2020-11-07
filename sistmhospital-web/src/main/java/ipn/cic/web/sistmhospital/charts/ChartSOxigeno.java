/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.charts;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.OhlcChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author J.PEREZ
 */
@ManagedBean(name="GraficaOxigeno")
public class ChartSOxigeno implements Serializable{
    private PieChartModel model;
    
    public ChartSOxigeno() {
        model = new PieChartModel();
        model.set("Normal [95% - 100%]", 100);
        model.set("Hipoxia Leve [91% - 94%]", 0);
        model.set("Hipoxia Moderada [86% - 90%]", 0);
        model.set("Hipoxia Grave [Menos de 85%]", 0);
        //model.set("", 2);
        model.setTitle("98%");
        model.setLegendPosition("s");
        model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        model.setShadow(false);
        model.setSeriesColors("60d319,fcf330,fcac30,ff5500");
        model.setFill(true);
        model.setShowDataLabels(false);
        model.setDiameter(50);
        //model.setSliceMargin(10);
        
        model.setExtender("customExtender");
    }
    
    public PieChartModel getModel() {return model;}

}