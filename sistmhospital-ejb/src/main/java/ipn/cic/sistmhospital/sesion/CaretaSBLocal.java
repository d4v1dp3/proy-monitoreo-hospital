/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.NoExisteCaretaException;
import ipn.cic.sistmhospital.modelo.EntCareta;
import javax.ejb.Local;

/**
 *
 * @author J.PEREZ
 */

@Local
public interface CaretaSBLocal {
    EntCareta getCareta(long idCareta) throws NoExisteCaretaException;
}
