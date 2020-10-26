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
    private CaretaSBLocal caretaSB;
    
    @EJB
    private MedidasSBLocal medidasSB;
    
    EntPaciente paciente;
    EntCareta careta;
    EntMedidas medidas;

    /// implementar el metodo para persistir las medidas.
    // usar los sb de paciente , careta  para recuperar la entidad paciente, l
    //la entidad careta, crear una entidad Medida asociar las entidades paciente y careta a medidad
    //persistir la medida.
    // devolver un json con el código éxito.
    
    @Override
    public EntPaciente cargarPaciente(long idPaciente){
        try{
            paciente = pacienteSB.getPaciente(idPaciente);
            logger.log(Level.INFO, "\tPaciente {0} recuperado.", idPaciente);  
            return paciente;
        }catch(NoExistePacienteException ex){            
            logger.log(Level.SEVERE,"Error al recuperar datos del paciente: {0}",ex.getMessage());
            return null;
        }
    }
    
    public EntCareta cargarCareta(long idCareta){
        try{
            careta = caretaSB.getCareta(idCareta);
            logger.log(Level.INFO, "\tCareta {0} recuperada.", idCareta);  
            return careta;
        }catch(NoExisteCaretaException ex){            
            logger.log(Level.SEVERE,"Error al recuperar datos de careta: {0}",ex.getMessage());
            return null;
        }
    }

    
    @Override
    public JsonObject guardarMedidas(MedidasVO med) throws MedidasException{        
        JsonObject respuesta; 
        try {            
            medidas.setEntPaciente(cargarPaciente(med.getIdPaciente()));
            //medidas.setEntCareta(cargarCareta(med.getIdCareta()));            
            medidas = medidasSB.guardaMedidas(medidas);
            logger.log(Level.INFO,"Medidas guardadas.");
            
            respuesta = Json.createObjectBuilder()
            .add("Mensaje", "Exito al guardar medidas recibidas.")
            .build();
        } catch (MedidasException ex) {
            logger.log(Level.SEVERE,"Error en MB al guardar medidas : {0}",ex.getMessage());
            respuesta = Json.createObjectBuilder()
            .add("Error", "No se guardaron las medidas.")
            .build();
        }
        return respuesta;
    }
    
    
    

    

}
