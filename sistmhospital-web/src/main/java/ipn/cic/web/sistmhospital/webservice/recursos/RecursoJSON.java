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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author J.PEREZ
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

@Path("recurso")
public class RecursoJSON {
    
    @GET
    public JsonObject getUsuario(){        
        //Recuperado de: https://www.programcreek.com/java-api-examples/?class=javax.ws.rs.core.MediaType&method=APPLICATION_JSON
        
        JsonObject result = Json.createObjectBuilder()
        .add("Mensaje", "JSON.")
        .build();
        return result;
    }    
    
    @PUT
    public JsonObject recibeMedidas(JsonObject datos){
       
        
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

        
        MedidasVO medidas = new MedidasVO(fechaMedicion, saturacionOxigeno, temperatura,frecCardiaca,frecRespiratoria,alerta,preArtSistolica, preArtDiastolic );

        /*
        JsonObject respuesta = Json.createObjectBuilder()
        .add("Fecha", fecha)
        .add("Oxigeno", saturacionOxigeno)   
        .add("temperatura", temperatura)  
        .add("FCardiaca", frecCardiaca)  
        .add("FRespiratoria", frecRespiratoria)  
        .add("Alerta", alerta)  
        .add("preArtSistolica", preArtSistolica)  
        .add("preArtDiastolic", preArtDiastolic) 
        .build();*/
         /*Estructura JSON Recibida
        {
            "fechamedicion": "21/10/2020",
            "horamedicion": "16:00",
            "saturacionoxigeno": "98.0",
            "temperatura": "26.0",
            "freccardiaca": "62", 
            "frecrespiratoria": "19",
            "alerta": "0", 
            "preartsistolica":  "110",
            "preartdiastolica": "78"
        }        
        */
        
        
        JsonObject respuesta = Json.createObjectBuilder()
        .add("Mensaje", "Datos recibidos correctamente!")
        .add("Oxigeno", saturacionOxigeno)
        .build();
        return respuesta;
    }
    
    
}
