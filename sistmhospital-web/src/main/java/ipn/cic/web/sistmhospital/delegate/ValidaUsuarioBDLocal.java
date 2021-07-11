/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.delegate;

import javax.ejb.Local;
import javax.json.JsonObject;

/**
 *
 * @author marcos
 */
@Local
public interface ValidaUsuarioBDLocal {
    JsonObject validaUsuario(JsonObject datos);
}
