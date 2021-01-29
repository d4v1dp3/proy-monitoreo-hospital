/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.admon;

import ipn.cic.sistmhospital.exception.CaretaException;
import ipn.cic.sistmhospital.exception.NoExisteCaretaException;
import ipn.cic.sistmhospital.exception.PacienteException;
import ipn.cic.sistmhospital.exception.RemoveEntityException;
import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.sistmhospital.sesion.CaretaSBLocal;
import ipn.cic.web.sistmhospital.bean.vo.CaretaVO;
import ipn.cic.web.sistmhospital.util.Mensaje;
import ipn.cic.web.sistmhospital.util.UtilWebSBLocal;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 * ManageBean que se utiliza para carga de usuarios en el sistema
 *
 * @author Iliac Huerta Trujillo <ihuertat@ipn.mx>
 */
@Named(value = "gestionDispositivosMB")
@ViewScoped
public class GestionDispositivosMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GestionUsuariosMB.class.getName());

    @EJB
    private CaretaSBLocal caretaSB;
    @EJB
    private UtilWebSBLocal utilWebSB;

    private List<EntCareta> caretas;//Caretas Asginadas
    private List<EntCareta> caretasNA;//Caretas no Asignadas
    
    private EntCareta caretaEditar;
    private EntCareta caretaEliminar;
    
    private EntCareta caretaGuard;
    
    private EntUsuario usuarioEditar;
    
    private String fechaManufactura = "";    
    private long idCareta = 0;
    

    @PostConstruct
    public void cargarDispositivos() {
        caretaGuard = new EntCareta();
        
        FacesMessage msg=null;
        try {
            logger.log(Level.INFO,"Entra a cargar dispositivos.");
            caretas = caretaSB.getCaretas();
        } catch (NoExisteCaretaException ex) {
            logger.log(Level.SEVERE, "Error en MB al cargar caretas : {0}", ex.getMessage());
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error dispositivos:",
                            "Error al intentar recuperar caretas intente más tarde.",
                            FacesMessage.SEVERITY_ERROR);
        }
        
        if(msg==null){
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Éxito:",
                            "Dispositivos cargados correctamente",
                            FacesMessage.SEVERITY_INFO);
        }
        utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
//        PrimeFaces.current().ajax().update("frGestDispositivos:msgsGD");
    }
    
    public void guardarDispositivo(){
        logger.log(Level.INFO,"Entrando a Guardar Dispositivo.");
        
        caretaGuard.setFechaManufactura(fechaManufactura);
        //caretaGuard.setIdCareta(idCareta);
        
        try{            
            caretaGuard = caretaSB.guardaCareta(caretaGuard);
        } catch (CaretaException ex) {
            FacesMessage msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "Error al intentar guardar careta :"+ex.getMessage(), 
                                                FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frmRegDispositivo:msgRegDisp", msg);
            return;
        }
        FacesMessage msg=null;
        if (caretaGuard == null){
            msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "Imposible guardar datos de dispositivo, intente más tarde", 
                                                FacesMessage.SEVERITY_ERROR);
            cerrarDialogo(msg);
        }else{
            msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Exíto",
                                                "El registro de careta se realizó correctamente : id="+this.caretaGuard.getIdCareta(), 
                                                FacesMessage.SEVERITY_INFO);
        }
        utilWebSB.addMsg("frmRegDispositivo:msgRegDisp", msg);        
        cerrarDialogo(msg);
    }
    
    public void guardarCambiosDispositivo(){
        logger.log(Level.INFO,"Entrando a actualizar dispositivo.");        
        caretaGuard.setFechaManufactura(fechaManufactura);
        try{            
            caretaGuard = caretaSB.guardaCareta(caretaGuard);
        } catch (CaretaException ex) {
            FacesMessage msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "Error al intentar guardar careta :"+ex.getMessage(), 
                                                FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frmRegDispositivo:msgRegDisp", msg);
            return;
        }
        FacesMessage msg=null;
        if (caretaGuard == null){
            msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Error",
                                                "Imposible guardar datos de dispositivo, intente más tarde", 
                                                FacesMessage.SEVERITY_ERROR);
            cerrarDialogo(msg);
        }else{
            msg = Mensaje.getInstance()
                                     .getMensajeAdaptado("Exíto",
                                                "El registro de careta se realizó correctamente : id="+this.caretaGuard.getIdCareta(), 
                                                FacesMessage.SEVERITY_INFO);
        }
        utilWebSB.addMsg("frmRegDispositivo:msgRegDisp", msg);        
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
    
    public void registrarDispositivo(){        
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 580);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("dispositivos/dialRegistrarDispositivo", options, null);
    }
    
    public void retornoRegistrarDispositivo(){
        cargarDispositivos();
    }
    
    
    
    public void editarDispositivo() {
    
        idCareta = caretaEditar.getIdCareta();
        fechaManufactura = caretaEditar.getFechaManufactura().toString();
        
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 580);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        logger.log(Level.INFO, "Dispositivo Seleccionado: {0}", caretaEditar.getFechaManufactura());
        PrimeFaces.current().dialog().openDynamic("dispositivos/dialEditarDispositivo", options, null);
    }

    public void retornoEditarDispositivo(SelectEvent event) {
        FacesMessage msg = null;

        if (event.getObject() != null) {
            msg = (FacesMessage) event.getObject();

        } else {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Diálogo ",
                            "Diálogo cerrado sin aplicar cambios",
                            FacesMessage.SEVERITY_INFO);
        }
        cargarDispositivos();
        utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
    }
    
    public void actualizarDispositivo(){
        FacesMessage msg = null;
               
        
        msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Diálogo ",
                            "Datos de dispositivo actualizados correctamente.",
                            FacesMessage.SEVERITY_INFO);
        
        
        cerrarDialogo(msg);
    }
    
    public void eliminarDispositivo(){
        FacesMessage msg = null;
        logger.log(Level.INFO, "Dispositivo Seleccionado: ID[{0}]",
                caretaEliminar.getIdCareta());
        
        try {
            boolean borrado = caretaSB.borrarCareta(caretaEliminar);
            if(borrado){
                logger.log(Level.INFO, "Dispositivo eliminado.");
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Diálogo ",
                            "Dispositivo eliminado correctamente.",
                            FacesMessage.SEVERITY_INFO);
            }
            
            utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
            cargarDispositivos();
        } catch (RemoveEntityException ex) {
            Logger.getLogger(GestionDispositivosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void retornoEliminarDispositivo(){
    }

    public CaretaSBLocal getCaretaSB() {
        return caretaSB;
    }

    public void setCaretaSB(CaretaSBLocal caretaSB) {
        this.caretaSB = caretaSB;
    }

    public List<EntCareta> getCaretas() {
        return caretas;
    }

    public void setCaretas(List<EntCareta> caretas) {
        this.caretas = caretas;
    }

    public EntCareta getCaretaEditar() {
        return caretaEditar;
    }

    public void setCaretaEditar(EntCareta caretaEditar) {
        this.caretaEditar = caretaEditar;
    }

    public EntUsuario getUsuarioEditar() {
        return usuarioEditar;
    }

    public void setUsuarioEditar(EntUsuario usuarioEditar) {
        this.usuarioEditar = usuarioEditar;
    }

    public EntCareta getCaretaGuard() {
        return caretaGuard;
    }

    public void setCaretaGuard(EntCareta caretaGuard) {
        this.caretaGuard = caretaGuard;
    }

    public String getFechaManufactura() {
        return fechaManufactura;
    }

    public void setFechaManufactura(String fechaManufantura) {
        this.fechaManufactura = fechaManufantura;
    }

    public EntCareta getCaretaEliminar() {
        return caretaEliminar;
    }

    public void setCaretaEliminar(EntCareta caretaEliminar) {
        this.caretaEliminar = caretaEliminar;
    }

    public long getIdCareta() {
        return idCareta;
    }

    public void setIdCareta(long idCareta) {
        this.idCareta = idCareta;
    }

    public List<EntCareta> getCaretasNA() {
        return caretasNA;
    }

    public void setCaretasNA(List<EntCareta> caretasNA) {
        this.caretasNA = caretasNA;
    }
    
    

}
