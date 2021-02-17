/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.admon;

import ipn.cic.sistmhospital.exception.CatalogoException;
import ipn.cic.sistmhospital.exception.IDUsuarioException;
import ipn.cic.sistmhospital.exception.MedicoException;
import ipn.cic.sistmhospital.modelo.EntGenero;
import ipn.cic.sistmhospital.modelo.EntHospital;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.sesion.CatalogoSBLocal;
import ipn.cic.web.sistmhospital.bean.vo.MedicoVO;
import ipn.cic.web.sistmhospital.bean.vo.PersonaVO;
import ipn.cic.web.sistmhospital.bean.vo.UsuarioVO;
import ipn.cic.web.sistmhospital.delegate.GestionMedicoBDLocal;
import ipn.cic.web.sistmhospital.util.Mensaje;
import ipn.cic.web.sistmhospital.util.UtilWebSBLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    private EntMedico medGuardado;
    private List<EntGenero> catGenero;
    
    private List<EntHospital> listHospital;
    
    @EJB
    GestionMedicoBDLocal gstMed;
    @EJB
    UtilWebSBLocal utilWebSB;
    @EJB
    CatalogoSBLocal catalogoSB;  
    
    @PostConstruct
    public void iniciaVO(){
        datUsuario = new UsuarioVO();
        datMedico = new MedicoVO();
        datPersona = new PersonaVO();
        medGuardado = null;
        try {
            setCatGenero((List<EntGenero>) catalogoSB.getCatalogo("EntGenero"));
        } catch (CatalogoException ex) {
            FacesMessage msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "No es posible recuperar catálogo de género :"+ex.getMessage(), 
                                                FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frmAltaMedico:msgAltaMed", msg);
            return;
        }
        
        listHospital = new ArrayList();
        
        try {
            setListHospital((List<EntHospital>) catalogoSB.getCatalogo("EntHospital"));
        } catch (CatalogoException ex) {
            FacesMessage msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "No es posible recuperar catálogo de hospital :"+ex.getMessage(), 
                                                FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frmAltaMedico:msgAltaMed", msg);
            return;
        }
    }
    
    public void guardarMedico(){
        logger.log(Level.INFO, "Inicia proceso de guardar médico");
        medGuardado = null;
        try{
            logger.log(Level.INFO, "Guardando entidad médico");
            medGuardado = gstMed.guardarMedicoNuevo(datMedico, datPersona, datUsuario);
            
        } catch (MedicoException ex) {
            logger.log(Level.INFO, "Error al guardar médico: {0}",ex.getMessage());
            FacesMessage msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "Error al intentar guardar médico :"+ex.getMessage(), 
                                                FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frmAltaMedico:msgAltaMed", msg);
            return;
        } catch (IDUsuarioException ex) {
            logger.log(Level.INFO, "Error al guardar médico: {0}",ex.getMessage());
            FacesMessage msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "El ID de USUARIO ya existe, CAMBIARLO", 
                                                FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frmAltaMedico:idUsrMess", msg);
            return;
        }
        
        FacesMessage msg=null;
        if (medGuardado == null){
            msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "Imposible guardar datos de Médico, intente más tarde", 
                                                FacesMessage.SEVERITY_ERROR);
            cerrarDialogo(msg);
        }else{
            msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Exíto",
                                                "El registro de médico se realizó correctamente : id="+this.medGuardado.getIdMedico(), 
                                                FacesMessage.SEVERITY_INFO);
        }
        
        cerrarDialogo(msg);
    }
    
    
    public void cerrarDialogo(){
        logger.log(Level.INFO,"Invocando cerrar dialogo.");
        //FacesMessage mensaje = Mensaje.getInstance()
          //                            .getMensaje("CERRANDO_DIALOGO", "CERRANDO_CORRECTAMENTE",
            //                                       FacesMessage.SEVERITY_INFO);
        cerrarDialogo(null);
    }
    
    private void cerrarDialogo(FacesMessage mensaje){
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

    /**
     * @return the medGuardado
     */
    public EntMedico getMedGuardado() {
        return medGuardado;
    }

    /**
     * @param medGuardado the medGuardado to set
     */
    public void setMedGuardado(EntMedico medGuardado) {
        this.medGuardado = medGuardado;
    }

    /**
     * @return the catGenero
     */
    public List<EntGenero> getCatGenero() {
        return catGenero;
    }

    /**
     * @param catGenero the catGenero to set
     */
    public void setCatGenero(List<EntGenero> catGenero) {
        this.catGenero = catGenero;
    }

    public List<EntHospital> getListHospital() {
        return listHospital;
    }

    public void setListHospital(List<EntHospital> listHospital) {
        this.listHospital = listHospital;
    }
    
    
    
}
