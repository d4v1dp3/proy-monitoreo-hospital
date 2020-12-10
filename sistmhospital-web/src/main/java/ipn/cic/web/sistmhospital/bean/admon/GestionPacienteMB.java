/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.admon;

import ipn.cic.sistmhospital.exception.CatalogoException;
import ipn.cic.sistmhospital.exception.PacienteException;
import ipn.cic.sistmhospital.modelo.EntEstadopaciente;
import ipn.cic.sistmhospital.modelo.EntGenero;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.sesion.CatalogoSBLocal;
import ipn.cic.web.sistmhospital.bean.vo.PacienteVO;
import ipn.cic.web.sistmhospital.bean.vo.PersonaVO;
import ipn.cic.web.sistmhospital.bean.vo.UsuarioVO;
import ipn.cic.web.sistmhospital.delegate.GestionPacienteBDLocal;
import ipn.cic.web.sistmhospital.util.Mensaje;
import ipn.cic.web.sistmhospital.util.UtilWebSBLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Iliac Huerta Trujillo
 * 
 */
@Named(value ="gestionPacienteMB")
@ViewScoped
public class GestionPacienteMB implements Serializable{
    private static final Logger logger = Logger.getLogger(GestionPacienteMB.class.getName());
    
    private PersonaVO datPersona;
    private UsuarioVO datUsuario;
    private PacienteVO datPaciente;    
    private EntPaciente pacGuardado;
    
    private List<EntGenero> catGenero;
    private List<EntEstadopaciente> catEstado;
    
    @EJB
    GestionPacienteBDLocal gstPac;
    @EJB
    UtilWebSBLocal utilWebSB;
    @EJB
    CatalogoSBLocal catalogoSB;
    
    @PostConstruct
    public void iniciaVO(){
        datUsuario = new UsuarioVO();
        datPaciente = new PacienteVO();
        datPersona = new PersonaVO();
        pacGuardado = null;
       
        try {
            setCatGenero((List<EntGenero>) catalogoSB.getCatalogo("EntGenero"));
        } catch (CatalogoException ex) {
            FacesMessage msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "No es posible recuperar catálogo de género :"+ex.getMessage(), 
                                                FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frmAltaPaciente:msgAltaPas", msg);
        }
        
        try {
            setCatEstado((List<EntEstadopaciente>) catalogoSB.getCatalogo("EntEstadopaciente"));
        } catch (CatalogoException ex) {
            FacesMessage msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "No es posible recuperar catálogo de estadoPaciente :"+ex.getMessage(), 
                                                FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frmAltaPaciente:msgAltaPas", msg);
        }
        logger.log(Level.INFO,"Categorias en Form AltaPaciente[Fin]");
        return;
    }
    
    public void guardarPaciente(){
        logger.log(Level.INFO,"Entrando a Guardar Paciente[1]");
        try{            
            pacGuardado = gstPac.guardarPacienteNuevo(datPaciente, datPersona, datUsuario);
        } catch (PacienteException ex) {
            FacesMessage msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "Error al intentar guardar médico :"+ex.getMessage(), 
                                                FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frmAltaPaciente:msgAltaPas", msg);
            return;
        }
        FacesMessage msg=null;
        if (pacGuardado == null){
            msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "Imposible guardar datos de Paciente, intente más tarde", 
                                                FacesMessage.SEVERITY_ERROR);
            cerrarDialogo(msg);
        }else{
            msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Exíto",
                                                "El registro de paciente se realizó correctamente : id="+this.pacGuardado.getIdPaciente(), 
                                                FacesMessage.SEVERITY_INFO);
        }
        
        cerrarDialogo(msg);
    }
    
    
    
     public void cerrarDialogo(){
        FacesMessage mensaje = Mensaje.getInstance()
                                      .getMensaje("CERRANDO_DIALOGO", "CERRANDO_CORRECTAMENTE",
                                                   FacesMessage.SEVERITY_INFO);
        cerrarDialogo(mensaje);
    }
    
    public void cerrarDialogo(FacesMessage mensaje){
        PrimeFaces.current().dialog().closeDynamic(mensaje);
    }
    
    
    public List<EntEstadopaciente> getCatEstado() {
        return catEstado;
    }

    public void setCatEstado(List<EntEstadopaciente> catEstado) {
        this.catEstado = catEstado;
    }
    
    public void setDatUsuario(UsuarioVO datUsuario) {
        this.datUsuario = datUsuario;
    }
    

    public PersonaVO getDatPersona() {
        return datPersona;
    }

    public void setDatPersona(PersonaVO datPersona) {
        this.datPersona = datPersona;
    }

     public PacienteVO getDatPaciente() {
        return datPaciente;
    }

    public void setDatPaciente(PacienteVO datPaciente) {
        this.datPaciente = datPaciente;
    }

    public EntPaciente getPacGuardado() {
        return pacGuardado;
    }

    public void setPacGuardado(EntPaciente pacGuardado) {
        this.pacGuardado = pacGuardado;
    }

    public GestionPacienteBDLocal getGstPac() {
        return gstPac;
    }

    public void setGstPac(GestionPacienteBDLocal gstPac) {
        this.gstPac = gstPac;
    }


    public List<EntGenero> getCatGenero() {
        return catGenero;
    }

    public void setCatGenero(List<EntGenero> catGenero) {
        this.catGenero = catGenero;
    }

    public GestionPacienteBDLocal getGstMed() {
        return gstPac;
    }

    public void setGstMed(GestionPacienteBDLocal gstMed) {
        this.gstPac = gstPac;
    }

    public UtilWebSBLocal getUtilWebSB() {
        return utilWebSB;
    }

    public void setUtilWebSB(UtilWebSBLocal utilWebSB) {
        this.utilWebSB = utilWebSB;
    }

    public CatalogoSBLocal getCatalogoSB() {
        return catalogoSB;
    }

    public void setCatalogoSB(CatalogoSBLocal catalogoSB) {
        this.catalogoSB = catalogoSB;
    }
    
}
