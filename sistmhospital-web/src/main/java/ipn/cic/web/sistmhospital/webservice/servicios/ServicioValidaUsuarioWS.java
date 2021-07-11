/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.webservice.servicios;

import com.google.gson.JsonSyntaxException;
import ipn.cic.sistmhospital.exception.UsuarioException;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.sistmhospital.sesion.UsuarioSBLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import ipn.cic.web.sistmhospital.delegate.ValidaUsuarioBDLocal;

/**
 *
 * @author marcos
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

@Path("validausuario")
public class ServicioValidaUsuarioWS {

    @EJB
    UsuarioSBLocal usuarioSB;
    
    @EJB 
    ValidaUsuarioBDLocal validaBD;

    @POST
    public JsonObject recibeIdUsuario(JsonObject datos) {
        /*Estructura JSON Recibida
        {
            "idusuario": "********",
            "cifra": "********"
        }*/
        
        JsonObject respuesta;
        respuesta = validaBD.validaUsuario(datos);
        return respuesta;
    }

    @GET
    public JsonObject validaUsuario() {

        JsonObject respuesta = Json.createObjectBuilder()
        .add("Mensaje", "Directiva disponible.")
        .build();
        return respuesta;

    }
}
