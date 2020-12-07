/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.GeneroException;
import ipn.cic.sistmhospital.modelo.EntGenero;
import javax.ejb.Local;

/**
 *
 * @author iliac
 */
@Local
public interface GeneroSBLocal {
    EntGenero getGeneroID (Short idGenero) throws GeneroException;
}
