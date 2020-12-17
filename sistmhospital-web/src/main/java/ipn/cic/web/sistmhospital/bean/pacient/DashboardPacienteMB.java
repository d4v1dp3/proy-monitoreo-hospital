/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.pacient;

import ipn.cic.sistmhospital.modelo.EntMedidas;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.sesion.dashboard.DashboardBDLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author J.PEREZ
 */

@Named(value="dashboardPacienteMB")
@ViewScoped
public class DashboardPacienteMB implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DashboardPacienteMB.class.getName());
    
    @EJB
    private DashboardBDLocal dashboardBD;

    private EntPaciente paciente;
    private List<EntMedidas> medidasComp;
     
    private String pacienteNombre;
    private String pacientePrimerAp;
    private String pacienteSegundoAp;
    private String pacienteId;
    private float pacSaturacionOxg;
    private float pacTemperatura;
    private short pacFrecRespiratoria;
    private short pacFrecCardiaca;
    private int   pacPreArtSistolica;
    private int   pacPreArtDiastolica;  
    
    
    private PieChartModel graficoOxigeno = new PieChartModel();
     private PieChartModel graficoTemperatura = new PieChartModel();
     private PieChartModel graficoFR = new PieChartModel();
     private PieChartModel graficoFC = new PieChartModel();
     private PieChartModel graficoPA = new PieChartModel();
     
     private BarChartModel historicoSOxigeno = new BarChartModel();

     private BarChartModel historicoTemperatura = new BarChartModel();
     private LineChartModel historicoFrecCardiaca= new LineChartModel();
     private LineChartModel historicoFrecRespiratoria = new LineChartModel();
     private LineChartModel historicoPreArterial = new LineChartModel();

    public void cargaInicial(){
        paciente = dashboardBD.getPaciente(Long.parseLong(pacienteId));
       
        logger.log(Level.INFO,"idPaciente dashboard: {0}",paciente.getIdPaciente());
        medidasComp = dashboardBD.getListaMedidas(paciente);
        logger.log(Level.INFO,"Mediciones recuperadas: {0}",medidasComp.size());
                        
        pacSaturacionOxg = medidasComp.get(medidasComp.size()-1).getSaturacionOxigeno();
        pacTemperatura = medidasComp.get(medidasComp.size()-1).getTemperatura();
        pacFrecRespiratoria = medidasComp.get(medidasComp.size()-1).getFrecRespiratoria();
        pacFrecCardiaca = medidasComp.get(medidasComp.size()-1).getFrecCardiaca();
        pacPreArtSistolica = medidasComp.get(medidasComp.size()-1).getPreArtSistolica();
        pacPreArtDiastolica = medidasComp.get(medidasComp.size()-1).getPreArtDiastolica();
    }  
     
    @PostConstruct
    public void DashboardPacienteMB() {         
        cargaGraficoOxigeno();
        cargaGraficoTemperatura();
        cargaGraficoFRespiratoria();
        cargaGarficoFCardiaca();
        cargaGarficoPArterial();
        
        cargaHistoricoSOxigeno();
        cargaHistoricoTemperatura();
        cargaHistoricoFCardiaca();
        cargaHistoricoFRespiratoria();
        cargaHistoricoPArterial();
    }   

    
    public void cargaGraficoOxigeno() {
        graficoOxigeno.set("Normal [95% - 100%]", 0);
        graficoOxigeno.set("Hipoxia Leve [91% - 94%]", 0);
        graficoOxigeno.set("Hipoxia Moderada [86% - 90%]", 0);
        graficoOxigeno.set("Hipoxia Grave [Menos de 85%]", 0);
        
        graficoOxigeno.setLegendPosition("se");
        graficoOxigeno.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        graficoOxigeno.setShadow(false);
        graficoOxigeno.setSeriesColors("60d319,fcf330,fcac30,ff5500");
        
        graficoOxigeno.setExtender("customExtender");
    }
    
    public void cargaGraficoTemperatura() {
        graficoTemperatura.set("Normal [36.2° - 37.2°]", 0);
        graficoTemperatura.set("Hipotermia [Menos de 35°]", 0);
        graficoTemperatura.set("Fiebre [Mas de 37.5°]", 0);
        graficoTemperatura.set("Hipertermia [Mas de 40°]", 0);
        
        graficoTemperatura.setLegendPosition("se");
        graficoTemperatura.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        graficoTemperatura.setShadow(false);
        graficoTemperatura.setSeriesColors("60d319,1984d3,ffc900,ff5500,ffffff");
        
        graficoTemperatura.setExtender("customExtender");
    }
    
    public void cargaGraficoFRespiratoria() {
        graficoFR.set("Normal [12 - 20]", 0);
        graficoFR.set("Bradipnea [Menos de 12]", 0);
        graficoFR.set("Taquipnea [Mas de 20]", 0);
        
        graficoFR.setLegendPosition("se");
        graficoFR.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        
        graficoFR.setShadow(false);
        graficoFR.setSeriesColors("60d319,ffc900,ff5500");
        
        graficoFR.setExtender("customExtender");
    }
    
    public void cargaGarficoFCardiaca() {
        graficoFC.set("Normal [60 - 100]", 0);
        graficoFC.set("Bradicardia [Menos de 60]", 0);
        graficoFC.set("Taquiardia [Mas de 100]", 0);
        
        graficoFC.setLegendPosition("se");
        graficoFC.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        
        graficoFC.setShadow(false);
        graficoFC.setSeriesColors("60d319,ffc900,ff5500");
        
        graficoFC.setExtender("customExtender");
    }
    
    public void cargaGarficoPArterial() {
        graficoPA.set("Normal [110/70 - 140/90] mmHg", 0);
        graficoPA.set("Hipotension [Menos de 100/60 mmHg]", 0);
        graficoPA.set("Hipertension [Mas de 140/90 mmHg]", 0);
        
        graficoPA.setLegendPosition("se");
        graficoPA.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        
        graficoPA.setShadow(false);
        graficoPA.setSeriesColors("60d319,ffc900,ff5500");
        
        graficoPA.setExtender("customExtender");
    }
    
    public void cargaHistoricoSOxigeno() {
        ChartSeries saturacion1 = new ChartSeries();
        saturacion1.setLabel("Normal [95% - 100%]");
        saturacion1.set("00:00", 85);
        saturacion1.set("01:00", 86);
        saturacion1.set("02:00", 80);
        saturacion1.set("03:00", 88);
        saturacion1.set("04:00", 80);
        saturacion1.set("05:00", 85);
        saturacion1.set("06:00", 90);
        saturacion1.set("07:00", 96);
        saturacion1.set("08:00", 85);
        saturacion1.set("09:00", 80);
        saturacion1.set("10:00", 90);
        saturacion1.set("11:00", 92);
        saturacion1.set("12:00", 94);
        saturacion1.set("13:00", 94);
//        saturacion1.set("14:00", 0);
//        saturacion1.set("15:00", 0);
//        saturacion1.set("16:00", 0);
//        saturacion1.set("17:00", 0);
//        saturacion1.set("18:00", 0);
//        saturacion1.set("19:00", 0);
//        saturacion1.set("20:00", 0);
//        saturacion1.set("21:00", 0);
//        saturacion1.set("22:00", 0);
//        saturacion1.set("23:00", 0);   

        ChartSeries saturacion2 = new ChartSeries();
        saturacion2.setLabel("Hipoxia Leve [91% - 94%]");
        
        ChartSeries saturacion3 = new ChartSeries();
        saturacion3.setLabel("Hipoxia Moderada [86% - 90%]");
        
        ChartSeries saturacion4 = new ChartSeries();
        saturacion4.setLabel("Hipoxia Grave [Menos de 85%]");

        historicoSOxigeno.addSeries(saturacion1);
        historicoSOxigeno.addSeries(saturacion2);
        historicoSOxigeno.addSeries(saturacion3);
        historicoSOxigeno.addSeries(saturacion4);
        
        historicoSOxigeno.setTitle("Saturación de Oxígeno");
        historicoSOxigeno.setLegendPosition("se");
        historicoSOxigeno.setShowPointLabels(true);
        historicoSOxigeno.setStacked(true);
        //model.setBarPadding(5);
        historicoSOxigeno.setBarWidth(8);
        
        historicoSOxigeno.setSeriesColors("60d319,fcf330,fcac30,ff5500");
        
        Axis xAxis = historicoSOxigeno.getAxis(AxisType.X);
        xAxis.setLabel("Tiempo (Horas)");
        
        Axis yAxis = historicoSOxigeno.getAxis(AxisType.Y);
        yAxis.setLabel("Porcentaje (%)");
        yAxis.setMin(0);
        yAxis.setMax(120);
    }
    
    public void cargaHistoricoTemperatura() {
        ChartSeries saturacion1 = new ChartSeries();
        saturacion1.setLabel("Normal [36.2° - 37.2°]");
        saturacion1.set("00:00", 36.2);
        saturacion1.set("01:00", 36.3);
        saturacion1.set("02:00", 36.4);
        saturacion1.set("03:00", 36);
        saturacion1.set("04:00", 36);
        saturacion1.set("05:00", 36);
        saturacion1.set("06:00", 36);
        saturacion1.set("07:00", 36);
        saturacion1.set("08:00", 36);
        saturacion1.set("09:00", 36);
        saturacion1.set("10:00", 36);
        saturacion1.set("11:00", 36);
        saturacion1.set("12:00", 36);
        saturacion1.set("13:00", 36);
//        saturacion1.set("14:00", 0);
//        saturacion1.set("15:00", 0);
//        saturacion1.set("16:00", 0);
//        saturacion1.set("17:00", 0);
//        saturacion1.set("18:00", 0);
//        saturacion1.set("19:00", 0);
//        saturacion1.set("20:00", 0);
//        saturacion1.set("21:00", 0);
//        saturacion1.set("22:00", 0);
//        saturacion1.set("23:00", 0);   

        ChartSeries saturacion2 = new ChartSeries();
        saturacion2.setLabel("Hipotermia [Menos de 35°]");
        
        ChartSeries saturacion3 = new ChartSeries();
        saturacion3.setLabel("Fiebre [Mas de 37.5°]");
        
        ChartSeries saturacion4 = new ChartSeries();
        saturacion4.setLabel("Hipertermia [Mas de 40°]");

        historicoTemperatura.addSeries(saturacion1);
        historicoTemperatura.addSeries(saturacion2);
        historicoTemperatura.addSeries(saturacion3);
        historicoTemperatura.addSeries(saturacion4);
        
        historicoTemperatura.setTitle("Temperatura");
        historicoTemperatura.setLegendPosition("se");
        historicoTemperatura.setShowPointLabels(true);
        historicoTemperatura.setStacked(true);
        //model.setBarPadding(5);
        historicoTemperatura.setBarWidth(8);
        
         historicoTemperatura.setSeriesColors("60d319,1984d3,ffc900,ff5500");
        
        Axis xAxis = historicoTemperatura.getAxis(AxisType.X);
        xAxis.setLabel("Tiempo (Horas)");
        
        Axis yAxis = historicoTemperatura.getAxis(AxisType.Y);
        yAxis.setLabel("Grados (°C)");
        yAxis.setMin(0);
        yAxis.setMax(50);
    }

    public void cargaHistoricoFCardiaca() {
        ChartSeries p1 = new ChartSeries();
        p1.setLabel("Normal [60 - 100]");
        p1.set("00:00", 85);
        p1.set("01:00", 86);
        p1.set("02:00", 80);
        p1.set("03:00", 88);
        p1.set("04:00", 80);
        p1.set("05:00", 85);
        p1.set("06:00", 90);
        p1.set("07:00", 96);
        p1.set("08:00", 85);
        p1.set("09:00", 80);
        p1.set("10:00", 90);
        p1.set("11:00", 92);
        p1.set("12:00", 94);
        p1.set("13:00", 94);
//        p1.set("14:00", 0);
//        p1.set("15:00", 0);
//        p1.set("16:00", 0);
//        p1.set("17:00", 0);
//        p1.set("18:00", 0);
//        p1.set("19:00", 0);
//        p1.set("20:00", 0);
//        p1.set("21:00", 0);
//        p1.set("22:00", 0);
//        p1.set("23:00", 0);   

        ChartSeries p2 = new ChartSeries();
        p2.setLabel("Bradicardia [Menos de 60]");
        
        ChartSeries p3 = new ChartSeries();
        p3.setLabel("Taquiardia [Mas de 100]");
        
        historicoFrecCardiaca.addSeries(p1);
        historicoFrecCardiaca.addSeries(p2);
        historicoFrecCardiaca.addSeries(p3);
        
        historicoFrecCardiaca.setSeriesColors("60d319,ffc900,ff5500");
        
        historicoFrecCardiaca.setTitle("Frecuencia Cardíaca");
        historicoFrecCardiaca.setLegendPosition("se");
        historicoFrecCardiaca.setShowPointLabels(true);
        
        historicoFrecCardiaca.getAxes().put(AxisType.X, new CategoryAxis("Tiempo (Horas)"));
        Axis yAxis = historicoFrecCardiaca.getAxis(AxisType.Y);
        yAxis.setLabel("Frecuencia (LPM)");
        yAxis.setMin(0);
        yAxis.setMax(120);
    }
    
    public void cargaHistoricoFRespiratoria() {
        ChartSeries p1 = new ChartSeries();
        p1.setLabel("Normal [12 - 20]");
        p1.set("00:00", 15);
        p1.set("01:00", 12);
        p1.set("02:00", 14);
        p1.set("03:00", 18);
        p1.set("04:00", 12);
        p1.set("05:00", 18);
        p1.set("06:00", 12);
        p1.set("07:00", 16);
        p1.set("08:00", 16);
        p1.set("09:00", 16);
        p1.set("10:00", 15);
        p1.set("11:00", 18);
        p1.set("12:00", 12);
        p1.set("13:00", 18);
//        p1.set("14:00", 0);
//        p1.set("15:00", 0);
//        p1.set("16:00", 0);
//        p1.set("17:00", 0);
//        p1.set("18:00", 0);
//        p1.set("19:00", 0);
//        p1.set("20:00", 0);
//        p1.set("21:00", 0);
//        p1.set("22:00", 0);
//        p1.set("23:00", 0);   

        ChartSeries p2 = new ChartSeries();
        p2.setLabel("Bradipnea [Menos de 12]");
        
        ChartSeries p3 = new ChartSeries();
        p3.setLabel("Taquipnea [Mas de 20]");
        
        historicoFrecRespiratoria.addSeries(p1);
        historicoFrecRespiratoria.addSeries(p2);
        historicoFrecRespiratoria.addSeries(p3);
        
        historicoFrecRespiratoria.setSeriesColors("60d319,ffc900,ff5500");
        
        historicoFrecRespiratoria.setTitle("Frecuencia Respiratoria");
        historicoFrecRespiratoria.setLegendPosition("ne");
        historicoFrecRespiratoria.setShowPointLabels(true);
        
        historicoFrecRespiratoria.getAxes().put(AxisType.X, new CategoryAxis("Tiempo (Horas)"));
        Axis yAxis = historicoFrecRespiratoria.getAxis(AxisType.Y);
        yAxis.setLabel("Frecuencia (RPM)");
        yAxis.setMin(0);
        yAxis.setMax(120);
    }
    
    public void cargaHistoricoPArterial() {
        ChartSeries sistolica = new ChartSeries();
        sistolica.setLabel("Presión Arterial Sistólica");
        sistolica.set("00:00", 110);
        sistolica.set("01:00", 112);
        sistolica.set("02:00", 123);
        sistolica.set("03:00", 120);
        sistolica.set("04:00", 130);
        sistolica.set("05:00", 120);
        sistolica.set("06:00", 130);
        sistolica.set("07:00", 112);
        sistolica.set("08:00", 126);
        sistolica.set("09:00", 140);
        sistolica.set("10:00", 130);
        sistolica.set("11:00", 134);
        sistolica.set("12:00", 120);
        sistolica.set("13:00", 120);
//        sistolica.set("14:00", 0);
//        sistolica.set("15:00", 0);
//        sistolica.set("16:00", 0);
//        sistolica.set("17:00", 0);
//        sistolica.set("18:00", 0);
//        sistolica.set("19:00", 0);
//        sistolica.set("20:00", 0);
//        sistolica.set("21:00", 0);
//        sistolica.set("22:00", 0);
//        sistolica.set("23:00", 0);  

        ChartSeries distolica = new ChartSeries();
        distolica.setLabel("Presión Arterial Distólica");
        distolica.set("00:00", 70);
        distolica.set("01:00", 71);
        distolica.set("02:00", 80);
        distolica.set("03:00", 82);
        distolica.set("04:00", 78);
        distolica.set("05:00", 80);
        distolica.set("06:00", 88);
        distolica.set("07:00", 80);
        distolica.set("08:00", 78);
        distolica.set("09:00", 76);
        distolica.set("10:00", 86);
        distolica.set("11:00", 84);
        distolica.set("12:00", 76);
        distolica.set("13:00", 80);
//        distolica.set("14:00", 78);
//        distolica.set("15:00", 85);
//        distolica.set("16:00", 84);
//        distolica.set("17:00", 80);
//        distolica.set("18:00", 70);
//        distolica.set("19:00", 76);
//        distolica.set("20:00", 85);
//        distolica.set("21:00", 70);
//        distolica.set("22:00", 78);
//        distolica.set("23:00", 80);
        
        historicoPreArterial.addSeries(sistolica);
        historicoPreArterial.addSeries(distolica);
        
        historicoPreArterial.setTitle("Presión Arterial Sistólica y Distólica");
        historicoPreArterial.setLegendPosition("se");
        historicoPreArterial.setShowPointLabels(true);
        historicoPreArterial.getAxes().put(AxisType.X, new CategoryAxis("Tiempo(Horas)"));
        
        Axis yAxis = historicoPreArterial.getAxis(AxisType.Y);
        yAxis.setLabel("mmHg");
        yAxis.setMin(0);yAxis.setMax(200);
    }
    
    /**
     * @return the pacienteNombre
     */
    public String getPacienteNombre() {
        return pacienteNombre;
    }

    /**
     * @param pacienteNombre the pacienteNombre to set
     */
    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public String getPacientePrimerAp() {
        return pacientePrimerAp;
    }

    public void setPacientePrimerAp(String pacientePrimerAp) {
        this.pacientePrimerAp = pacientePrimerAp;
    }

    public String getPacienteSegundoAp() {
        return pacienteSegundoAp;
    }

    public void setPacienteSegundoAp(String pacienteSegundoAp) {
        this.pacienteSegundoAp = pacienteSegundoAp;
    }
    
    public String getPacienteId(){
        return pacienteId;
    }
    
    public void setPacienteId(String pacienteId){
        this.pacienteId = pacienteId;
    }
    
    public float getPacSaturacionOxg() {
        return pacSaturacionOxg;
    }

    public void setPacSaturacionOxg(float pacSaturacionOxg) {
        this.pacSaturacionOxg = pacSaturacionOxg;
    }

    public float getPacTemperatura() {
        return pacTemperatura;
    }

    public void setPacTemperatura(float pacTemperatura) {
        this.pacTemperatura = pacTemperatura;
    }

    public short getPacFrecRespiratoria() {
        return pacFrecRespiratoria;
    }

    public void setPacFrecRespiratoria(short pacFrecRespiratoria) {
        this.pacFrecRespiratoria = pacFrecRespiratoria;
    }

    public short getPacFrecCardiaca() {
        return pacFrecCardiaca;
    }

    public void setPacFrecCardiaca(short pacFrecCardiaca) {
        this.pacFrecCardiaca = pacFrecCardiaca;
    }

    public int getPacPreArtSistolica() {
        return pacPreArtSistolica;
    }

    public void setPacPreArtSistolica(int pacPreArtSistolica) {
        this.pacPreArtSistolica = pacPreArtSistolica;
    }

    public int getPacPreArtDiastolica() {
        return pacPreArtDiastolica;
    }

    public void setPacPreArtDiastolica(int pacPreArtDiastolica) {
        this.pacPreArtDiastolica = pacPreArtDiastolica;
    }
    
    public PieChartModel getGraficoOxigeno() {
        return graficoOxigeno;
    }

    public void setGraficoOxigeno(PieChartModel graficoOxigeno) {
        this.graficoOxigeno = graficoOxigeno;
    }

    public PieChartModel getGraficoTemperatura() {
        return graficoTemperatura;
    }

    public void setGraficoTemperatura(PieChartModel graficoTemperatura) {
        this.graficoTemperatura = graficoTemperatura;
    }

    public PieChartModel getGraficoFR() {
        return graficoFR;
    }

    public void setGraficoFR(PieChartModel graficoFR) {
        this.graficoFR = graficoFR;
    }

    public PieChartModel getGraficoFC() {
        return graficoFC;
    }

    public void setGraficoFC(PieChartModel graficoFC) {
        this.graficoFC = graficoFC;
    }

    public PieChartModel getGraficoPA() {
        return graficoPA;
    }

    public void setGraficoPA(PieChartModel graficoPA) {
        this.graficoPA = graficoPA;
    }
    
    public BarChartModel getHistoricoSOxigeno() {
        return historicoSOxigeno;
    }

    public void setHistoricoSOxigeno(BarChartModel historicoSOxigeno) {
        this.historicoSOxigeno = historicoSOxigeno;
    }

    public BarChartModel getHistoricoTemperatura() {
        return historicoTemperatura;
    }

    public void setHistoricoTemperatura(BarChartModel historicoTemperatura) {
        this.historicoTemperatura = historicoTemperatura;
    }

    public LineChartModel getHistoricoFrecCardiaca() {
        return historicoFrecCardiaca;
    }

    public void setHistoricoFrecCardiaca(LineChartModel historicoFrecCardiaca) {
        this.historicoFrecCardiaca = historicoFrecCardiaca;
    }

    public LineChartModel getHistoricoFrecRespiratoria() {
        return historicoFrecRespiratoria;
    }

    public void setHistoricoFrecRespiratoria(LineChartModel historicoFrecRespiratoria) {
        this.historicoFrecRespiratoria = historicoFrecRespiratoria;
    }

    public LineChartModel getHistoricoPreArterial() {
        return historicoPreArterial;
    }

    public void setHistoricoPreArterial(LineChartModel historicoPreArterial) {
        this.historicoPreArterial = historicoPreArterial;
    }
    
}