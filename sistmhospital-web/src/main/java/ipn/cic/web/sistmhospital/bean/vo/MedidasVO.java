/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.vo;
import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntMedidasPK;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author J.PEREZ
 */
public class MedidasVO implements Serializable{
    
    //protected EntMedidasPK entMedidasPK;
    private Date fechaMedicion;
    private float saturacionOxigeno;
    private float temperatura;
    private short frecCardiaca;
    private short frecRespiratoria;
    private boolean alerta;
    private int preArtSistolica; 
    private int preArtDiastolica;
    //private EntCareta entCareta;
    
    public MedidasVO(){}
        
    public MedidasVO(Date fechaMedicion, float saturacionOxigeno, float temperatura, short frecCardiaca, short frecRespiratoria, boolean alerta, int preArtSistolica, int preArtDiastolica) {
        this.fechaMedicion = fechaMedicion;
        this.saturacionOxigeno = saturacionOxigeno;
        this.temperatura = temperatura;
        this.frecCardiaca = frecCardiaca;
        this.frecRespiratoria = frecRespiratoria;
        this.alerta = alerta;
        this.preArtSistolica = preArtSistolica;
        this.preArtDiastolica = preArtDiastolica;
    }


    public Date getFechaMedicion() {
        return fechaMedicion;
    }

    public void setFechaMedicion(Date fechaMedicion) {
        this.fechaMedicion = fechaMedicion;
    }

    public float getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(float saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public short getFrecCardiaca() {
        return frecCardiaca;
    }

    public void setFrecCardiaca(short frecCardiaca) {
        this.frecCardiaca = frecCardiaca;
    }

    public short getFrecRespiratoria() {
        return frecRespiratoria;
    }

    public void setFrecRespiratoria(short frecRespiratoria) {
        this.frecRespiratoria = frecRespiratoria;
    }

    public boolean isAlerta() {
        return alerta;
    }

    public void setAlerta(boolean alerta) {
        this.alerta = alerta;
    }

    public int getPreArtSistolica() {
        return preArtSistolica;
    }

    public void setPreArtSistolica(int preArtSistolica) {
        this.preArtSistolica = preArtSistolica;
    }

    public int getPreArtDiastolica() {
        return preArtDiastolica;
    }

    public void setPreArtDiastolica(int preArtDiastolica) {
        this.preArtDiastolica = preArtDiastolica;
    }

}
