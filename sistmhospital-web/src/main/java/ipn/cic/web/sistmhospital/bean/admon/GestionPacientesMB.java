/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.admon;

import ipn.cic.sistmhospital.exception.MedicoException;
import ipn.cic.sistmhospital.exception.PacienteException;
import ipn.cic.sistmhospital.exception.UsuarioException;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.sistmhospital.sesion.MedicoSBLocal;
import ipn.cic.sistmhospital.sesion.PacienteSBLocal;
import ipn.cic.sistmhospital.sesion.UsuarioSBLocal;
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

@Named(value = "gestionPacientesMB")
@ViewScoped
public class GestionPacientesMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GestionPacientesMB.class.getName());

    @EJB
    private PacienteSBLocal pacienteSB;
    @EJB
    private MedicoSBLocal medicoSB;
    @EJB
    private UtilWebSBLocal utilWebSB;

    private EntMedico medico;
    private List<EntPaciente> pacientesComp;
    private EntUsuario usuarioEditar;

    @PostConstruct
    public void cargaPacientes() {
        FacesMessage msg=null;
        
        //Cargar Entidad de Medico        
        try {
            logger.log(Level.INFO,"Entra a cargar medico.");
            medico = medicoSB.getMedico(1);
                    
                    //Recuperar Pacientes
        } catch (MedicoException ex) {
            logger.log(Level.SEVERE,"Error al cargar medico.");
        }
        
        //Recuperar Pacientes
        try {
            logger.log(Level.INFO,"Entra a cargar pacientes.");
            pacientesComp = pacienteSB.getPacientes(medico);           
            
        } catch (PacienteException ex) {
            logger.log(Level.SEVERE, "Error en MB intentar recuperar los pacientes del medico: {0}", ex.getMessage());
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error pacientes:",
                            "Error al intentar recuperar los pacientes del medico, intente más tarde.",
                            FacesMessage.SEVERITY_ERROR);
        }
        
        if(msg==null){
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Éxito:",
                            "Pacientes cargados correctamente",
                            FacesMessage.SEVERITY_INFO);
        }
//        utilWebSB.addMsg("frGestUsuarios:msgsGU", msg);
        //PrimeFaces.current().ajax().update("frGestUsuarios:msgsGU");
    }

    
    public void editarUsuario() {
        logger.log(Level.INFO,"Abre dashboard de un paciente.");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 890);
        options.put("height", 640);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");

        PrimeFaces.current().dialog().openDynamic("pacientes/dialDashboardPaciente", options, null);
    }

    public void retornoEditaUsuario(SelectEvent event) {
        FacesMessage msg = null;

        if (event.getObject() != null) {
            msg = (FacesMessage) event.getObject();

        } else {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Diálogo ",
                            "Diálogo cerrado sin aplicar cambios",
                            FacesMessage.SEVERITY_INFO);
        }
//        utilWebSB.addMsg("frGestUsuarios:msgsGU", msg);
    }

    /**
     * @return the pacientesComp
     */
    public List<EntPaciente> getPacientesComp() {
        return pacientesComp;
    }

    /**
     * @param usuariosComp the pacientesComp to set
     */
    public void setPacientesComp(List<EntPaciente> usuariosComp) {
        this.pacientesComp = usuariosComp;
    }

    /**
     * @return the usuarioEditar
     */
    public EntUsuario getUsuarioEditar() {
        return usuarioEditar;
    }

    /**
     * @param usuarioEditar the usuarioEditar to set
     */
    public void setUsuarioEditar(EntUsuario usuarioEditar) {
        this.usuarioEditar = usuarioEditar;
    }

}
