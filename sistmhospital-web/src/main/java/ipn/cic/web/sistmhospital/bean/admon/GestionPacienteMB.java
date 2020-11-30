/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.admon;

import ipn.cic.web.sistmhospital.util.Mensaje;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Iliac Huerta Trujillo
 * 
 */
@Named(value ="gestionPacienteMB")
@ViewScoped
public class GestionPacienteMB implements Serializable{
    private static final Logger logger = Logger.getLogger(GestionPacienteMB.class.getName());
    
    
    public void cerrarDialogo(){
        FacesMessage mensaje = Mensaje.getInstance()
                                      .getMensaje("CERRANDO_DIALOGO", "CERRANDO_CORRECTAMENTE",
                                                   FacesMessage.SEVERITY_INFO);
        PrimeFaces.current().dialog().closeDynamic(mensaje);
    }
}
