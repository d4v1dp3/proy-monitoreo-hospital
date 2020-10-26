/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.MedidasException;
import ipn.cic.sistmhospital.modelo.EntMedidas;
import javax.ejb.Local;

/**
 *
 * @author J.PEREZ
 */

@Local
public interface MedidasSBLocal {
    EntMedidas guardaMedidas(EntMedidas med) throws MedidasException;
}
