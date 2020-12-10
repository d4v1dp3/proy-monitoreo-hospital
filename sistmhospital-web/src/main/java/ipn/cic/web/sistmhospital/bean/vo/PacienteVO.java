/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.vo;

import ipn.cic.sistmhospital.modelo.EntAntecedentes;
import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntEstadopaciente;
import ipn.cic.sistmhospital.modelo.EntHospital;
import ipn.cic.sistmhospital.modelo.EntMedidas;
import ipn.cic.sistmhospital.modelo.EntPacienteMedico;
import ipn.cic.sistmhospital.modelo.EntPersona;
import ipn.cic.sistmhospital.modelo.EntSintomas;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author J.PEREZ
 */
public class PacienteVO implements Serializable{
    
    private Integer idPaciente;
    private String dirCalle;
    private Integer dirNumero;
    private String dirInterior;
    private String telFijo;
    private String telCel;
//    private List<EntSintomas> entSintomasList;
//    private List<EntMedidas> entMedidasList;
//    private EntAntecedentes entAntecedentes;
    private Integer idCareta;
    private Integer idEstadopaciente;
    private Integer idHospital;    
//    private List<EntPacienteMedico> entPacienteMedicoList;
    
    
    public PacienteVO(){
        idPaciente = 0;
        dirCalle = "";
        dirNumero = 0;
        dirInterior = "";
        telFijo = "";
        telCel = "";
        idCareta = 0;
        idEstadopaciente = 0;
        idHospital = 0;   
    }
    
    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDirCalle() {
        return dirCalle;
    }

    public void setDirCalle(String dirCalle) {
        this.dirCalle = dirCalle;
    }

    public Integer getDirNumero() {
        return dirNumero;
    }

    public void setDirNumero(Integer dirNumero) {
        this.dirNumero = dirNumero;
    }

    public String getDirInterior() {
        return dirInterior;
    }

    public void setDirInterior(String dirInterior) {
        this.dirInterior = dirInterior;
    }

    public String getTelFijo() {
        return telFijo;
    }

    public void setTelFijo(String telFijo) {
        this.telFijo = telFijo;
    }

    public String getTelCel() {
        return telCel;
    }

    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }

    public Integer getIdCareta() {
        return idCareta;
    }

    public void setIdCareta(Integer idCareta) {
        this.idCareta = idCareta;
    }

    public Integer getIdEstadopaciente() {
        return idEstadopaciente;
    }

    public void setIdEstadopaciente(Integer idEstadopaciente) {
        this.idEstadopaciente = idEstadopaciente;
    }

    public Integer getIdHospital() {
        return idHospital;
    }

    //private Integer idPersona;
    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
    }
    
    
   

}
