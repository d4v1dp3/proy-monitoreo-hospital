/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.delegate;

import com.google.gson.Gson;
import ipn.cic.sistmhospital.exception.MedidasException;
import ipn.cic.sistmhospital.exception.NoExisteCaretaException;
import ipn.cic.sistmhospital.exception.NoExistePacienteException;
import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntMedidas;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.sistmhospital.sesion.CaretaSBLocal;
import ipn.cic.sistmhospital.sesion.MedidasSBLocal;
import ipn.cic.sistmhospital.sesion.PacienteSBLocal;
import ipn.cic.web.sistmhospital.bean.vo.MedidasVO;
import java.util.Calendar;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author iliaco
 */
@Stateless
@PermitAll
@SecurityDomain("other")
public class MedidasBD implements MedidasBDLocal {
    private static final Logger logger = Logger.getLogger(MedidasBDLocal.class.getName());
    
    @EJB
    private PacienteSBLocal pacienteSB;
    
    @EJB
    private MedidasSBLocal medidasSB;

    
    private EntPaciente cargarPaciente(long idPaciente) throws NoExistePacienteException{
        try{
            EntPaciente paciente = pacienteSB.getPaciente(idPaciente);
            logger.log(Level.INFO, "\tPaciente {0} recuperado.", idPaciente);  
            return paciente;
        }catch(NoExistePacienteException ex){            
            logger.log(Level.SEVERE,"Error al recuperar datos del paciente: {0}",ex.getMessage());
            return null;
        }
    }
    
    @Override
    public JsonObject guardarMedidas(MedidasVO med) throws MedidasException, NoExistePacienteException{ 
        
        EntPaciente paciente;
        EntMedidas medidas = new EntMedidas();
        JsonObject respuesta; 
        
        try {   
            
            paciente = cargarPaciente(med.getIdPaciente());
            
            medidas.setEntPaciente(paciente);
            medidas.setEntCareta(paciente.getIdCareta());  
            medidas.setFechaMedicion(med.getFechaMedicion());
            medidas.setSaturacionOxigeno(med.getSaturacionOxigeno());
            medidas.setTemperatura(med.getTemperatura());
            medidas.setFrecCardiaca(med.getFrecCardiaca());
            medidas.setFrecRespiratoria(med.getFrecRespiratoria());
            medidas.setAlerta(med.getAlerta());
            medidas.setPreArtSistolica(med.getPreArtSistolica());
            medidas.setPreArtDiastolica(med.getPreArtDiastolica());
                      
            medidas = medidasSB.guardaMedidas(medidas);
            logger.log(Level.INFO,"Medidas guardadas.");
            
            respuesta = Json.createObjectBuilder()
            .add("Respuesta", "0")
            .add("Exito", "Medidas almacenadas correctamente.")
            .build();
        } catch (NoExistePacienteException  ex) {
            logger.log(Level.SEVERE,"Error, paciente no econtrado : {0}",ex.getMessage());

            respuesta = Json.createObjectBuilder()
            .add("Respuesta", "1")
            .add("Error", "No existe paciente.")
            .build();
        }catch (MedidasException  ex) {
            logger.log(Level.SEVERE,"Error al guardar las medidas : {0}",ex.getMessage());

            respuesta = Json.createObjectBuilder()
            .add("Respuesta", "3")
            .add("Error", "Itente mas tarde.")
            .build();
        }
        return respuesta;
    }
        

}
