/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.delegate;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author iliaco
 */
@Stateless
@PermitAll
@SecurityDomain("other")
public class MedidasBD implements MedidasBDLocal {

    /// implementar el metodo para persistir las medidas.
    // usar los sb de paciente , careta  para recuperar la entidad paciente, l
    //la entidad careta, crear una entidad Medida asociar las entidades paciente y careta a medidad
    //persistir la medida.
    // devolver un json con el código éxito.
    
}
