/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.charts;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author J.PEREZ
 */

@ManagedBean(name="GraficosPaciente")
public class GraficosPaciente implements Serializable{

    private BarChartModel HistoricoSOxigeno = new BarChartModel();
    private BarChartModel HistoricoTemperatura = new BarChartModel();
    private BarChartModel HistoricoFCardiaca = new BarChartModel();
    private BarChartModel HistoricoFRespiratoria = new BarChartModel();
    private BarChartModel HistoricoPASistolica = new BarChartModel();
    private BarChartModel HistoricoPADistolica = new BarChartModel();

    public GraficosPaciente() {
        HistoricoSOxigeno();
        HistoricoTemperatura();
        HistoricoFCardiaca();
    }
    
    private void HistoricoSOxigeno() {        
        ChartSeries saturacion1 = new ChartSeries();
        saturacion1.setLabel("Normal [95% - 100%]");
        saturacion1.set("16:00", 94);

        ChartSeries saturacion2 = new ChartSeries();
        saturacion2.setLabel("Hipoxia Leve [91% - 94%]");
        
        ChartSeries saturacion3 = new ChartSeries();
        saturacion3.setLabel("Hipoxia Moderada [86% - 90%]");
        
        ChartSeries saturacion4 = new ChartSeries();
        saturacion4.setLabel("Hipoxia Grave [Menos de 85%]");

        HistoricoSOxigeno.addSeries(saturacion1);
        HistoricoSOxigeno.addSeries(saturacion2);
        HistoricoSOxigeno.addSeries(saturacion3);
        HistoricoSOxigeno.addSeries(saturacion4);
        
        HistoricoSOxigeno.setTitle("Saturacion de Oxigeno");
        HistoricoSOxigeno.setLegendPosition("se");
        
        //HistoricoSOxigeno.setSeriesColors("00ecff,60d319,ffc900,ff5500");
        //HistoricoSOxigeno.setExtender("chartExtenderOxig");
        
        Axis xAxis = HistoricoSOxigeno.getAxis(AxisType.X);
        xAxis.setLabel("Tiempo (Horas)");
        
        Axis yAxis = HistoricoSOxigeno.getAxis(AxisType.Y);
        yAxis.setLabel("Porcentaje (%)");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }
    
    private void  HistoricoTemperatura() {
        ChartSeries d1 = new ChartSeries();
        d1.setLabel("Normal [36.2° - 37.2°]");
        d1.set("16:00", 36.4);

        ChartSeries d2 = new ChartSeries();
        d2.setLabel("Hipotermia [Menos de 35°]");
        
        ChartSeries d3 = new ChartSeries();
        d3.setLabel("Fiebre [Mas de 37.5°]");
        
        ChartSeries d4 = new ChartSeries();
        d4.setLabel("Hipertermia [Mas de 40°]");

        HistoricoTemperatura.addSeries(d2);
        HistoricoTemperatura.addSeries(d1);        
        HistoricoTemperatura.addSeries(d3);
        HistoricoTemperatura.addSeries(d4);
        
        HistoricoTemperatura.setTitle("Temperatura");
        HistoricoTemperatura.setLegendPosition("se");
        
        //HistoricoTemperatura.setSeriesColors("00ecff,60d319,ffc900,ff5500");
        //model.setExtender("chartExtenderTemp");
        
        Axis xAxis = HistoricoTemperatura.getAxis(AxisType.X);
        xAxis.setLabel("Tiempo (Horas)");
        
        Axis yAxis = HistoricoTemperatura.getAxis(AxisType.Y);
        yAxis.setLabel("Grados Centigrados (°C)");
        yAxis.setMin(0);
        yAxis.setMax(45);//Un maximo de temperatura de 45 de un paciente VIVO
    }
    
    private void HistoricoFCardiaca() {
        ChartSeries d1 = new ChartSeries();
        d1.setLabel("Normal [60 - 100]");
        d1.set("16:00", 70);

        ChartSeries d2 = new ChartSeries();
        d2.setLabel("Bradicardia [Menos de 60]");
        
        ChartSeries d3 = new ChartSeries();
        d3.setLabel("Taquiardia [Mas de 100]");

        HistoricoFCardiaca.addSeries(d2);
        HistoricoFCardiaca.addSeries(d1);        
        HistoricoFCardiaca.addSeries(d3);
        
        HistoricoFCardiaca.setTitle("Frecuencia Cardiaca");
        HistoricoFCardiaca.setLegendPosition("se");
        
        //HistoricoFCardiaca.setSeriesColors("00ecff,ffc900,ff5500");
        //HistoricoFCardiaca.setExtender("chartExtenderFCard");
        
        Axis xAxis = HistoricoFCardiaca.getAxis(AxisType.X);
        xAxis.setLabel("Tiempo (Horas)");
        
        Axis yAxis = HistoricoFCardiaca.getAxis(AxisType.Y);
        yAxis.setLabel("Latidos por Minuto (LPM)");
        yAxis.setMin(0);
        yAxis.setMax(45);
    }
    
     public BarChartModel getHistoricoSOxigeno() {
        return HistoricoSOxigeno;
    }

    public void setHistoricoSOxigeno(BarChartModel HistoricoSOxigeno) {
        this.HistoricoSOxigeno = HistoricoSOxigeno;
    }

    public BarChartModel getHistoricoTemperatura() {
        return HistoricoTemperatura;
    }

    public void setHistoricoTemperatura(BarChartModel HistoricoTemperatura) {
        this.HistoricoTemperatura = HistoricoTemperatura;
    }

    public BarChartModel getHistoricoFCardiaca() {
        return HistoricoFCardiaca;
    }

    public void setHistoricoFCardiaca(BarChartModel HistoricoFCardiaca) {
        this.HistoricoFCardiaca = HistoricoFCardiaca;
    }

    public BarChartModel getHistoricoFRespiratoria() {
        return HistoricoFRespiratoria;
    }

    public void setHistoricoFRespiratoria(BarChartModel HistoricoFRespiratoria) {
        this.HistoricoFRespiratoria = HistoricoFRespiratoria;
    }

    public BarChartModel getHistoricoPASistolica() {
        return HistoricoPASistolica;
    }

    public void setHistoricoPASistolica(BarChartModel HistoricoPASistolica) {
        this.HistoricoPASistolica = HistoricoPASistolica;
    }

    public BarChartModel getHistoricoPADistolica() {
        return HistoricoPADistolica;
    }

    public void setHistoricoPADistolica(BarChartModel HistoricoPADistolica) {
        this.HistoricoPADistolica = HistoricoPADistolica;
    }
        
    
}
