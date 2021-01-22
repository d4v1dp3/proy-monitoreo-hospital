/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.pacient;

import ipn.cic.sistmhospital.exception.MedicoException;
import ipn.cic.sistmhospital.modelo.EntMedico;
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

@Named(value = "listadoPacientesMB")
@ViewScoped
public class ListadoPacientesMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ListadoPacientesMB.class.getName());

    @EJB
    private PacienteSBLocal pacienteSB;
    @EJB
    private MedicoSBLocal medicoSB;
    @EJB
    private UtilWebSBLocal utilWebSB;

    private EntMedico medico;
    private List<EntPaciente> pacientesComp;
    private EntPaciente pacienteMostrar;

    @PostConstruct
    public void cargaPacientes() {
        FacesMessage msg=null;
        
        //Recuperar Entidad de Medico        
        try {
            logger.log(Level.INFO,"Entra a cargar medico.");
            EntUsuario usrMedico = utilWebSB.getUsrAutenticado(); 
            logger.log(Level.INFO, "Usuario encontrado: {0}", usrMedico.getIdPersona());

            medico = medicoSB.getMedico(usrMedico.getIdPersona());
            logger.log(Level.INFO, "Medico encontrado: {0}", medico.getEmail());
            pacientesComp = medicoSB.getListaPaciente(medico);

        } catch (MedicoException ex) {
            logger.log(Level.SEVERE,"Error al cargar medico.");
        }
        
        
        if(msg==null){
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Éxito:",
                            "Pacientes cargados correctamente",
                            FacesMessage.SEVERITY_INFO);
        }
        utilWebSB.addMsg("frGestPacientes:msgsGP", msg);
        PrimeFaces.current().ajax().update("frGestPacientes:msgsGP");
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
        valNombre.add(pacienteMostrar.getIdPersona().getNombre());
        
        List<String> valPrimerAp = new ArrayList<>();
        valPrimerAp.add(pacienteMostrar.getIdPersona().getPrimerApellido());
        
        List<String> valSegundoAp = new ArrayList<>();
        valSegundoAp.add(pacienteMostrar.getIdPersona().getSegundoApellido());
            
        List<String> valId = new ArrayList<>();
        valId.add(pacienteMostrar.getIdPaciente().toString());
            
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
                
        PrimeFaces.current().dialog().openDynamic("pacientes/dialDashboardPaciente", options, parametros);
    }

    public void retornoMuestraDashboard(SelectEvent event) {
        FacesMessage msg = null;
        if (event.getObject() != null) {
            msg = (FacesMessage) event.getObject();
        } else {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Diálogo ",
                            "Diálogo cerrado sin aplicar cambios.",
                            FacesMessage.SEVERITY_INFO);
        }
        utilWebSB.addMsg("frGestPacientes:msgsGP", msg);
    }
    
    public void mostrarAntecedentes() {
        logger.log(Level.INFO,"Abre antecedentes del paciente.");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 340);
        options.put("height", 560);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        
        //Envio de Parametros
        Map<String, List<String>> parametros = new HashMap<>();
        
        List<String> valNombre = new ArrayList<>();
        valNombre.add(pacienteMostrar.getIdPersona().getNombre());
        
        List<String> valPrimerAp = new ArrayList<>();
        valPrimerAp.add(pacienteMostrar.getIdPersona().getPrimerApellido());
        
        List<String> valSegundoAp = new ArrayList<>();
        valSegundoAp.add(pacienteMostrar.getIdPersona().getSegundoApellido());
            

        List<String> diabetes = new ArrayList<>();
        if(pacienteMostrar.getEntAntecedentes().getDiabetes())
            diabetes.add("true");
        else
            diabetes.add("false");
        
        List<String> cancer = new ArrayList<>();
        if(pacienteMostrar.getEntAntecedentes().getCancer())
            cancer.add("true");
        else
            cancer.add("false");
        
        List<String> asma = new ArrayList<>();
        if(pacienteMostrar.getEntAntecedentes().getAsma())
            asma.add("true");
        else
            asma.add("false");
        
        List<String> vih = new ArrayList<>();
        if(pacienteMostrar.getEntAntecedentes().getVih())
            vih.add("true");
        else
            vih.add("false");
            
        List<String> has = new ArrayList<>();
        if(pacienteMostrar.getEntAntecedentes().getHas())
            has.add("true");
        else
            has.add("false");
        
        List<String> epoc = new ArrayList<>();
        if(pacienteMostrar.getEntAntecedentes().getEpoc())
            epoc.add("true");
        else
            epoc.add("false");
        
        List<String> embarazo = new ArrayList<>();
        if(pacienteMostrar.getEntAntecedentes().getEmbarazo())
            embarazo.add("true");
        else
            embarazo.add("false");
        
        List<String> artritis = new ArrayList<>();
        if(pacienteMostrar.getEntAntecedentes().getArtritis())
            artritis.add("true");
        else
            artritis.add("false");
        
        List<String> enfau = new ArrayList<>();
        if(pacienteMostrar.getEntAntecedentes().getEnfautoinmune())
            enfau.add("true");
        else
            enfau.add("false");
                
        logger.log(Level.INFO,"PrimerAp: {0}", valPrimerAp.get(0));
        logger.log(Level.INFO,"SegundoAp: {0}", valSegundoAp.get(0));
        
        parametros.put("pacNombre", valNombre);
        parametros.put("pacPrimerAp", valPrimerAp);
        parametros.put("pacSegundoAp", valSegundoAp);
        parametros.put("diab", diabetes);
        parametros.put("canc", cancer);
        parametros.put("asma", asma);
        parametros.put("vih", vih);
        parametros.put("has", has);
        parametros.put("epoc", epoc);
        parametros.put("emba", embarazo);
        parametros.put("artr", artritis);
        parametros.put("enfa", enfau);
                
        PrimeFaces.current().dialog().openDynamic("pacientes/dialAntecedentesPaciente", options, parametros);
    }
    
    public void retornoMostrarAntecedentes(SelectEvent event) {
        FacesMessage msg = null;
        if (event.getObject() != null) {
            msg = (FacesMessage) event.getObject();
        } else {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Diálogo ",
                            "Diálogo cerrado sin aplicar cambios.",
                            FacesMessage.SEVERITY_INFO);
        }
        utilWebSB.addMsg("frGestPacientes:msgsGP", msg);
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
     * @return the pacienteMostrar
     */
    public EntPaciente getPacienteMostrar() {
        return pacienteMostrar;
    }

    /**
     * @param pacienteMostrar the pacienteMostrar to set
     */
    public void setPacienteMostrar(EntPaciente pacienteMostrar) {
        this.pacienteMostrar = pacienteMostrar;
    }

}
