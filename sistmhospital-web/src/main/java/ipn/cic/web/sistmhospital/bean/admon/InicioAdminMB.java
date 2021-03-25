/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.admon;

import ipn.cic.sistmhospital.exception.CaretaHospitalException;
import ipn.cic.sistmhospital.exception.CatalogoException;
import ipn.cic.sistmhospital.exception.MedicoException;
import ipn.cic.sistmhospital.exception.NoExisteHospitalException;
import ipn.cic.sistmhospital.exception.NoExistePersonaException;
import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntCaretaHospital;
import ipn.cic.sistmhospital.modelo.EntHospital;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.modelo.EntPersona;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.sistmhospital.sesion.CaretaHospitalSBLocal;
import ipn.cic.sistmhospital.sesion.CatalogoSBLocal;
import ipn.cic.sistmhospital.sesion.HospitalSBLocal;
import ipn.cic.sistmhospital.sesion.MedicoSBLocal;
import ipn.cic.sistmhospital.sesion.PersonaSBLocal;
import ipn.cic.sistmhospital.util.Constantes;
import ipn.cic.web.sistmhospital.util.Mensaje;
import ipn.cic.web.sistmhospital.util.UtilWebSBLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author J.Perez
 */
@Named(value = "inicioAdminMB")
@ViewScoped
public class InicioAdminMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(InicioAdminMB.class.getName());

    @EJB
    private MedicoSBLocal medicoSB;
    @EJB
    private PersonaSBLocal personaSB;
    @EJB
    private UtilWebSBLocal utilWebSB;
    @EJB
    private HospitalSBLocal hospitalSB;
    @EJB 
    private CatalogoSBLocal catalogoSB;
    @EJB
    private CaretaHospitalSBLocal caretahospitalSB;

    private EntHospital hospital;
    private List<EntMedico> medicos;
    private List<EntPaciente> pacientes;
    private List<EntCareta> caretas;
    private List<EntCaretaHospital> caretasSA;
    
    String nomSistema = ""; 
    String nomAdmin = "";


    @PostConstruct
    public void cargarDatos() {
        FacesMessage msg = null;

        logger.log(Level.INFO, "Entra a cargar datos."); 
        nomSistema = Constantes.getInstance().getString("NOMBRE_SISTEMA");
        
        medicos = new ArrayList();
        pacientes = new ArrayList();
        caretas = new ArrayList();
       
        try {
            EntUsuario usrAdmin = utilWebSB.getUsrAutenticado();
            EntPersona persona = personaSB.getEntPersona(usrAdmin.getIdPersona());
            nomAdmin = persona.getNombre();
            
            medicos = medicoSB.getMedicos();
            pacientes = catalogoSB.getCatalogo("EntPaciente");
            caretas = catalogoSB.getCatalogo("EntCareta");
            caretasSA = caretahospitalSB.getCaretasNoAsignadas();
            hospital = hospitalSB.getPrimerHospital();

        } catch (MedicoException ex) {
            logger.log(Level.SEVERE, "Error al cargar medicos.");
        } catch (NoExisteHospitalException ex) {
            logger.log(Level.SEVERE, "Error al cargar hospital.");
        } catch (CatalogoException ex) {
            logger.log(Level.SEVERE, "Error al recuperar pacientes/caretas.");
        } catch (CaretaHospitalException ex) {
            logger.log(Level.SEVERE, "Error al recuperar dispositivos no asignados.");
        } catch (NoExistePersonaException ex) {
            logger.log(Level.SEVERE, "Error al recuperar datos del administrador.");
        }

        if (msg == null) {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Éxito:",
                            "Pacientes cargados correctamente",
                            FacesMessage.SEVERITY_INFO);
        }
//        utilWebSB.addMsg("frGestPacientes:msgsIP", msg);
//        PrimeFaces.current().ajax().update("frIPacientes:msgsIP");
    }

    public String getNomSistema() {
        return nomSistema;
    }

    public void setNomSistema(String nomSistema) {
        this.nomSistema = nomSistema;
    }   
    
    public EntHospital getHospital() {
        return hospital;
    }

    public void setHospital(EntHospital hospital) {
        this.hospital = hospital;
    }

    public List<EntMedico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<EntMedico> medicos) {
        this.medicos = medicos;
    }

    public List<EntPaciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<EntPaciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<EntCareta> getCaretas() {
        return caretas;
    }

    public void setCaretas(List<EntCareta> caretas) {
        this.caretas = caretas;
    }

    public List<EntCaretaHospital> getCaretasSA() {
        return caretasSA;
    }

    public void setCaretasSA(List<EntCaretaHospital> caretasSA) {
        this.caretasSA = caretasSA;
    }

    public String getNomAdmin() {
        return nomAdmin;
    }

    public void setNomAdmin(String nomAdmin) {
        this.nomAdmin = nomAdmin;
    }

    

}
