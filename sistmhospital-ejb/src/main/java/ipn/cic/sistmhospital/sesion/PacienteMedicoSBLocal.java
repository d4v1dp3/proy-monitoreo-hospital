/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.NoExistePacienteMedicoException;
import ipn.cic.sistmhospital.exception.PacienteMedicoException;
import ipn.cic.sistmhospital.exception.UpdateEntityException;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.modelo.EntPacienteMedico;
import javax.ejb.Local;

/**
 *
 * @author J.Perez
 */
@Local
public interface PacienteMedicoSBLocal {
    EntPacienteMedico guardaEntPacienteMedico(EntPacienteMedico pacmed) throws PacienteMedicoException;  
    public EntPacienteMedico updateEntPacienteMedico(EntPacienteMedico pacMed) throws UpdateEntityException;
    public EntPacienteMedico getEntPacienteMedico(EntPaciente idPaciente) throws NoExistePacienteMedicoException;
}
