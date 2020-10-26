/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.MedidasException;
import ipn.cic.sistmhospital.modelo.EntMedidas;

/**
 *
 * @author J.PEREZ
 */
public interface MedidasSBLocal {
    EntMedidas guardaMedidas(EntMedidas med) throws MedidasException;
}
