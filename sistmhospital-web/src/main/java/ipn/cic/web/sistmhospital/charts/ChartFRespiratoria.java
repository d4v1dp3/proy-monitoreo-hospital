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
/**
 *
 * @author J.PEREZ
 */

@ManagedBean(name="GraficaFRespiratoria")
public class ChartFRespiratoria implements Serializable{
    
    private DonutChartModel donutModel;

    @PostConstruct
    public void init() {
        createDonutModel();
    }

    private void createDonutModel() {
       donutModel = new DonutChartModel();
        ChartData data = new ChartData();
        
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(26);
        values.add(50);
        dataSet.setData(values);
        
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(0, 255, 235)");//Azul
        bgColors.add("rgb(255, 255, 255)");//Blanco
        dataSet.setBackgroundColor(bgColors);
        
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Normal");
        //labels.add("White");
        data.setLabels(labels);
        
        donutModel.setData(data);
    }
    
    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }
    
}
