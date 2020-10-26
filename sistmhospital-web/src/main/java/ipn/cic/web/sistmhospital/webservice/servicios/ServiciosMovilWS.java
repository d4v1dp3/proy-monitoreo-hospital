/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.webservice.servicios;

import ipn.cic.web.sistmhospital.bean.vo.MedidasVO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import ipn.cic.sistmhospital.exception.MedidasException;
import ipn.cic.web.sistmhospital.delegate.MedidasBDLocal;
import java.util.Calendar;
import javax.ejb.EJB;

/**
 *
 * @author J.PEREZ
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

@Path("persistemedicion")
public class ServiciosMovilWS {
    
    @EJB
    private MedidasBDLocal medidasDB;
    
    @POST
    public JsonObject recibeMedidas(JsonObject datos) throws MedidasException{
        /*Estructura JSON Recibida
        {
            "idPaciente": 20178,
            "saturacionOxigeno": 98.0,
            "temperatura": 26.0,
            "frecCardiaca": 62, 
            "frecRespiratoria": 19,
            "alerta": 0, 
            "preArtSistolica":  110,
            "preArtDiastolica": 78
        }*/

        Gson gson= new Gson();
        MedidasVO med = gson.fromJson(datos.toString(), MedidasVO.class);
        med.setFechaMedicion(Calendar.getInstance().getTime());

        JsonObject respuesta;
        respuesta = medidasDB.guardarMedidas(med);
        
        return respuesta;
    }
    
    @GET
    public JsonObject confirmaConexion(){
     
        JsonObject respuesta = Json.createObjectBuilder()
        .add("Mensaje", "Directiva disponible.")
        .build();
        return respuesta;
    }
    
    
}

        /*RESPUESTA EN JSON
        JsonObject respuesta = Json.createObjectBuilder()
        .add("IDPaciente", med.getIdPaciente())
        //.add("Fecha", med.getFechaMedicion().toString())
        .add("Oxigeno", med.getSaturacionOxigeno())   
        .add("temperatura", med.getTemperatura())  
        .add("FCardiaca", med.getFrecCardiaca())  
        .add("FRespiratoria", med.getFrecRespiratoria())  
        .add("Alerta", med.isAlerta())  
        .add("preArtSistolica", med.getPreArtSistolica())  
        .add("preArtDiastolic", med.getPreArtDiastolica()) 
        .build();*/ 
