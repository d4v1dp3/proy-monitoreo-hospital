/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.webservice.recursos;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.shaded.json.JSONObject;

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
        .add("user", "Jose")
        .add("pass", 12345)
        .build();
        return result;
   }
    
    
    
    @PUT
    public JsonObject recibeUsuario(JsonObject datos){
        
        
        
        JsonObject respuesta = Json.createObjectBuilder()
        .add("Usuario:", datos.getJsonString("Nombre"))
        .add("Apellido:", datos.getJsonString("Apellido"))   
        .build();
        return respuesta;
    }
    
    
}
