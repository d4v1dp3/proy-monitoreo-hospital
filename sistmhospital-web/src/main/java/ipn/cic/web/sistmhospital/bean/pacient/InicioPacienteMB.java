/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.pacient;

import ipn.cic.sistmhospital.exception.MedicoException;
import ipn.cic.sistmhospital.exception.NoExistePacienteException;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.modelo.EntMedidas;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.sistmhospital.sesion.MedicoSBLocal;
import ipn.cic.sistmhospital.sesion.PacienteSBLocal;
import ipn.cic.web.sistmhospital.util.Mensaje;
import ipn.cic.web.sistmhospital.util.UtilWebSBLocal;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

@Named(value = "inicioPacienteMB")
@ViewScoped
public class InicioPacienteMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(InicioPacienteMB.class.getName());

    @EJB
    private PacienteSBLocal pacienteSB;
    @EJB
    private MedicoSBLocal medicoSB;
    @EJB
    private UtilWebSBLocal utilWebSB;

    private EntPaciente paciente;
    
    private List<EntMedidas> medidas;
    private EntMedico medicoPac;
    

    @PostConstruct
    public void cargaPaciente() {
        FacesMessage msg=null;
        
        //Recuperar Entidad de Paciente        
        try {
            logger.log(Level.INFO,"Entra a cargar usuario.");
            EntUsuario usrPaciente = utilWebSB.getUsrAutenticado(); 
            logger.log(Level.INFO, "Usuario encontrado: {0}", usrPaciente.getIdPersona());

            paciente = pacienteSB.getPaciente(usrPaciente.getIdPersona());
            logger.log(Level.INFO, "Paciente recuperado: {0}", paciente.getIdPaciente());

            //Recuperar medico del paciente
            medicoPac = medicoSB.getMedicoDePaciente(paciente);
            logger.log(Level.INFO, "Medico recuperado en Inicio: {0}", medicoPac.getCedulaProf());
            
            
        } catch (NoExistePacienteException ex) {
            logger.log(Level.SEVERE,"Error al cargar paciente.");
        } catch (MedicoException ex) {
            logger.log(Level.SEVERE,"Error al cargar medico del paciente.");
        }
        
        
        if(msg==null){
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Éxito:",
                            "Pacientes cargados correctamente",
                            FacesMessage.SEVERITY_INFO);
        }
//        utilWebSB.addMsg("frGestPacientes:msgsIP", msg);
//        PrimeFaces.current().ajax().update("frIPacientes:msgsIP");
    }

    
    
    public void mostrarDashboard() {
        logger.log(Level.INFO,"Abre dashboard de un paciente.");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 890);
        options.put("height", 640);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        
        //Envio de Parametros
        Map<String, List<String>> parametros = new HashMap<>();
        
        List<String> valNombre = new ArrayList<>();
        valNombre.add(paciente.getIdPersona().getNombre());
        
        List<String> valPrimerAp = new ArrayList<>();
        valPrimerAp.add(paciente.getIdPersona().getPrimerApellido());
        
        List<String> valSegundoAp = new ArrayList<>();
        valSegundoAp.add(paciente.getIdPersona().getSegundoApellido());
            
        List<String> valId = new ArrayList<>();
        valId.add(paciente.getIdPaciente().toString());
            
        DateTimeFormatter formatoFecha =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<String> valFechaHist = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();
        valFechaHist.add(fechaActual.format(formatoFecha));
                
        logger.log(Level.INFO,"PrimerAp: {0}", valPrimerAp.get(0));
        logger.log(Level.INFO,"SegundoAp: {0}", valSegundoAp.get(0));
        logger.log(Level.INFO,"Fecha Actual: {0}",valFechaHist.get(0));
        
        parametros.put("pacNombre", valNombre);
        parametros.put("pacPrimerAp", valPrimerAp);
        parametros.put("pacSegundoAp", valSegundoAp);
        parametros.put("pacId", valId);
        parametros.put("pacfechaHist",valFechaHist);
                
        PrimeFaces.current().dialog().openDynamic("dialDashboardPaciente", options, parametros);
    }

    public void retornoMostrarDashboard(SelectEvent event) {

    }
    
    
    public void mostrarDatosMedico(){
        logger.log(Level.INFO,"Abre datos de medico.");   
        
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 440);
        options.put("height", 550);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        
        //Envio de Parametros del Medico
        Map<String, List<String>> parametros = new HashMap<>();
        
        List<String> valNombre = new ArrayList<>();
        valNombre.add(medicoPac.getIdPersona().getNombre());
        
         List<String> valPrimerAp = new ArrayList<>();
        valPrimerAp.add(medicoPac.getIdPersona().getPrimerApellido());
        
         List<String> valSegundoAp = new ArrayList<>();
        valSegundoAp.add(medicoPac.getIdPersona().getSegundoApellido());
        
        List<String> valCorreo = new ArrayList<>();
        valCorreo.add(medicoPac.getEmail());
        
        List<String> valTel = new ArrayList<>();
        valTel.add(medicoPac.getCelular());
        
        List<String> valCedula = new ArrayList<>();
        valTel.add(medicoPac.getCedulaProf());
            
        parametros.put("medNombre", valNombre);
        parametros.put("medPrimerAp", valPrimerAp);
        parametros.put("medSegundoAp", valSegundoAp);
        parametros.put("medEmail", valCorreo);
        parametros.put("medTel", valTel);
        parametros.put("medCedula", valCedula);

        PrimeFaces.current().dialog().openDynamic("dialDatosMedicoPaciente", options, parametros);
    }
    
    public void retornoMostrarDatosMedico(){ 
        FacesMessage msg = null;

    }

    public void cerrarDialogo(){
        logger.log(Level.INFO,"Invocando cerrar dialogo.");
        FacesMessage mensaje = Mensaje.getInstance()
                                      .getMensaje("CERRANDO_DIALOGO", "CERRANDO_CORRECTAMENTE",
                                                   FacesMessage.SEVERITY_INFO);
        cerrarDialogo(mensaje);
    }
    private void cerrarDialogo(FacesMessage mensaje){
        PrimeFaces.current().dialog().closeDynamic(mensaje);
    }
    

}