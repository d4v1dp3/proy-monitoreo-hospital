/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.admon;

import ipn.cic.web.sistmhospital.bean.vo.MedicoVO;
import ipn.cic.web.sistmhospital.bean.vo.PersonaVO;
import ipn.cic.web.sistmhospital.bean.vo.UsuarioVO;
import ipn.cic.web.sistmhospital.util.Mensaje;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *Managebean para la gestión en sistema de médicos asociados a un hospital.
 * 
 * @author Iliac Huerta Trujillo
 * 
 */
@Named(value ="gestionMedicoMB")
@ViewScoped
public class GestionMedicoMB implements Serializable{
    private static final Logger logger = Logger.getLogger(GestionMedicoMB.class.getName());
    private UsuarioVO datUsuario;
    private MedicoVO datMedico;
    private PersonaVO datPersona;
    
    @PostConstruct
    public void iniciaVO(){
        datUsuario = new UsuarioVO();
        datMedico = new MedicoVO();
        datPersona = new PersonaVO();
    }
    
    public void guardarMedico(){
        
    }
    
    
    public void cerrarDialogo(){
        FacesMessage mensaje = Mensaje.getInstance()
                                      .getMensaje("CERRANDO_DIALOGO", "CERRANDO_CORRECTAMENTE",
                                                   FacesMessage.SEVERITY_INFO);
        PrimeFaces.current().dialog().closeDynamic(mensaje);
    }
    
    
    /**
     * @return the datUsuario
     */
    public UsuarioVO getDatUsuario() {
        return datUsuario;
    }

    /**
     * @param datUsuario the datUsuario to set
     */
    public void setDatUsuario(UsuarioVO datUsuario) {
        this.datUsuario = datUsuario;
    }

    /**
     * @return the datMedico
     */
    public MedicoVO getDatMedico() {
        return datMedico;
    }

    /**
     * @param datMedico the datMedico to set
     */
    public void setDatMedico(MedicoVO datMedico) {
        this.datMedico = datMedico;
    }

    /**
     * @return the datPersona
     */
    public PersonaVO getDatPersona() {
        return datPersona;
    }

    /**
     * @param datPersona the datPersona to set
     */
    public void setDatPersona(PersonaVO datPersona) {
        this.datPersona = datPersona;
    }
}
