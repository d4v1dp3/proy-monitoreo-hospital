/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.webservice.recursos;

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
import java.util.Calendar;


/**
 *
 * @author J.PEREZ
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

@Path("serviciosMovil")
public class ServiciosMovilWS {
    
    @POST
    public JsonObject recibeMedidas(JsonObject datos){
       /*
        long idPaciente = Long.parseLong(datos.getString("idpaciente"));
        long idCareta = Long.parseLong(datos.getString("idcareta"));
        String fecha = datos.getString("fechamedicion");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaMedicion = null;
        try {
            fechaMedicion = formato.parse(fecha);
        } 
        catch (ParseException ex) {  System.out.println(ex); }
           
        float saturacionOxigeno = Float.parseFloat(datos.getString("saturacionoxigeno"));
        float temperatura = Float.parseFloat(datos.getString("temperatura"));
        short frecCardiaca = Short.parseShort(datos.getString("freccardiaca"));
        short frecRespiratoria = Short.parseShort(datos.getString("frecrespiratoria"));
        boolean alerta = Boolean.parseBoolean(datos.getString("alerta"));
        int preArtSistolica = Integer.parseInt(datos.getString("preartsistolica"));
        int preArtDiastolic = Integer.parseInt(datos.getString("preartdiastolica"));      
*/
        Gson gson= new Gson();
        MedidasVO med = gson.fromJson(datos.toString(), MedidasVO.class);
        med.setFechaMedicion(Calendar.getInstance().getTime());
        //MedidasVO medidas = new MedidasVO(fechaMedicion, saturacionOxigeno, temperatura,frecCardiaca,frecRespiratoria,alerta,preArtSistolica, preArtDiastolic );
        
        
        
        
       /* JsonObject respuesta = Json.createObjectBuilder()
        .add("IDPaciente", idPaciente)
        .add("IDCareta", idCareta)   
        .add("Fecha", fecha)
        .add("Oxigeno", saturacionOxigeno)   
        .add("temperatura", temperatura)  
        .add("FCardiaca", frecCardiaca)  
        .add("FRespiratoria", frecRespiratoria)  
        .add("Alerta", alerta)  
        .add("preArtSistolica", preArtSistolica)  
        .add("preArtDiastolic", preArtDiastolic) 
        .build();*/
        
        JsonObject respuesta = Json.createObjectBuilder()
        .add("IDPaciente", med.getIdPaciente())
        .add("Fecha", med.getFechaMedicion().toString())
        .add("Oxigeno", med.getSaturacionOxigeno())   
        .add("temperatura", med.getTemperatura())  
        .add("FCardiaca", med.getFrecCardiaca())  
        .add("FRespiratoria", med.getFrecRespiratoria())  
        .add("Alerta", med.isAlerta())  
        .add("preArtSistolica", med.getPreArtSistolica())  
        .add("preArtDiastolic", med.getPreArtDiastolica()) 
        .build();
        
        /*Estructura JSON Recibida
        {
            "idPaciente": 20178,
            "fechaMedicion": "21/10/2020",
            "saturacionOxigeno": 98.0,
            "temperatura": 26.0,
            "frecCardiaca": 62, 
            "frecRespiratoria": 19,
            "alerta": 0, 
            "preArtSistolica":  110,
            "preArtDiastolica": 78
        }*/
        
        /*
        JsonObject respuesta = Json.createObjectBuilder()
        .add("Mensaje", "Datos recibidos correctamente!")
        .add("Oxigeno", saturacionOxigeno)
        .build();*/
        return respuesta;
    }
    
    
}
