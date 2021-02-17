/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.PacienteMedicoException;
import ipn.cic.sistmhospital.modelo.EntPacienteMedico;
import javax.ejb.Local;

/**
 *
 * @author J.Perez
 */
@Local
public interface PacienteMedicoSBLocal {
    EntPacienteMedico guardaEntPacienteMedico(EntPacienteMedico pacmed) throws PacienteMedicoException;    
}
