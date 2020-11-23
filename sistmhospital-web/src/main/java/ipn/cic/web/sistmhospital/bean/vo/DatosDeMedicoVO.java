/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.vo;

import ipn.cic.sistmhospital.modelo.EntHospital;
import ipn.cic.sistmhospital.modelo.EntPacienteMedico;
import ipn.cic.sistmhospital.modelo.EntPersona;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author J.PEREZ
 */
public class DatosDeMedicoVO implements Serializable{

    //private Integer idMedico;
    private String cedulaProf;
    private String email;
    private String celular;
    //private List<EntHospital> entHospitalList;
    private EntPersona idPersona;
    //private List<EntPacienteMedico> entPacienteMedicoList;
    
       public DatosDeMedicoVO(String cedulaProf, String celular, EntPersona idPersona) {
        this.cedulaProf = cedulaProf;
        this.celular = celular;
        this.idPersona = idPersona;
    }

//    public Integer getIdMedico() {
//        return idMedico;
//    }
//
//    public void setIdMedico(Integer idMedico) {
//        this.idMedico = idMedico;
//    }

    public String getCedulaProf() {
        return cedulaProf;
    }

    public void setCedulaProf(String cedulaProf) {
        this.cedulaProf = cedulaProf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

//    public List<EntHospital> getEntHospitalList() {
//        return entHospitalList;
//    }
//
//    public void setEntHospitalList(List<EntHospital> entHospitalList) {
//        this.entHospitalList = entHospitalList;
//    }

    public EntPersona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(EntPersona idPersona) {
        this.idPersona = idPersona;
    }

//    public List<EntPacienteMedico> getEntPacienteMedicoList() {
//        return entPacienteMedicoList;
//    }
//
//    public void setEntPacienteMedicoList(List<EntPacienteMedico> entPacienteMedicoList) {
//        this.entPacienteMedicoList = entPacienteMedicoList;
//    }
    
}
