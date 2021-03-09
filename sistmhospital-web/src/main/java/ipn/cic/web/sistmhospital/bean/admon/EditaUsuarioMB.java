/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.admon;

import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntCaretaHospital;
import ipn.cic.sistmhospital.modelo.EntHospital;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.modelo.EntPersona;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.sistmhospital.sesion.CaretaHospitalSBLocal;
import ipn.cic.sistmhospital.sesion.CaretaSBLocal;
import ipn.cic.sistmhospital.sesion.CatalogoSBLocal;
import ipn.cic.sistmhospital.sesion.HospitalSBLocal;
import ipn.cic.web.sistmhospital.util.Mensaje;
import ipn.cic.web.sistmhospital.util.UtilWebSBLocal;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *ManageBean usado para la edición de usuarios, solo se permite la actualización
 * de datos generales de usuario y datos generales de persona.
 * 
 * @author Iliac Huerta Trujillo <ihuertat@ipn.mx>
 */

@Named(value="editaUsuarioMB")
@ViewScoped
public class EditaUsuarioMB implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(EditaUsuarioMB.class.getName());
    
    @EJB
    private CaretaSBLocal caretaSB;
    @EJB
    private CaretaHospitalSBLocal caretahospitalSB;
    @EJB
    private HospitalSBLocal hospitalSB;
    @EJB
    private UtilWebSBLocal utilWebSB;
    @EJB
    CatalogoSBLocal catalogoSB;
    
    private EntPersona persona;
    private EntUsuario usuario;
    private EntMedico medico;
    private EntHospital hospital;
    private EntCareta careta;

    private List<EntHospital> listHospital;
    private List<EntCaretaHospital> caretashospitalNA;//Caretas no Asignadas
    
    
    
    private long idUsuario = 0;
    private long idPersona = 0;
    
    
    @PostConstruct
    public void cargarDatosUsuario() {
        
        logger.log(Level.INFO, "Entra a cargar datos de usuario id={0}", idUsuario);

//        caretaHospital = new EntCaretaHospital();
//
//        try {
//            //Cargar Lista de Hospitales
//            setListHospital((List<EntHospital>) catalogoSB.getCatalogo("EntHospital"));
//        } catch (CatalogoException ex) {
//            Logger.getLogger(GestionDispositivosMB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        //Cargar lista de caretas Asignadas a pacientes
//        FacesMessage msg = null;
//        try {
//            logger.log(Level.INFO, "Entra a cargar dispositivos.");
//            caretashospital = caretahospitalSB.getCaretasAsignadas();
//            caretashospitalNA = caretahospitalSB.getCaretasNoAsignadas();
//        } catch (CaretaHospitalException ex) {
//            logger.log(Level.SEVERE, "Error en MB al cargar caretashopital : {0}", ex.getMessage());
//            msg = Mensaje.getInstance()
//                    .getMensajeAdaptado("Error dispositivos:",
//                            "Error al intentar recuperar caretas intente más tarde.",
//                            FacesMessage.SEVERITY_ERROR);
//        }
//
//        if (msg == null) {
//            msg = Mensaje.getInstance()
//                    .getMensajeAdaptado("Éxito:",
//                            "Dispositivos cargados correctamente",
//                            FacesMessage.SEVERITY_INFO);
//        }
        //utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
//        PrimeFaces.current().ajax().update("frGestDispositivos:msgsGD");
    }
    
    
    public void guardarCambiosUsuario(){
    
    }
    
    public void cerrarDialogo(){
        FacesMessage mensaje = Mensaje.getInstance()
                                      .getMensaje("CERRANDO_DIALOGO", "CERRANDO_CORRECTAMENTE",
                                                   FacesMessage.SEVERITY_INFO);
        PrimeFaces.current().dialog().closeDynamic(mensaje);
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
}
