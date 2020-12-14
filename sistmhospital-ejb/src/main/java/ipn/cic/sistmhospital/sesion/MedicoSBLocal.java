/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.MedicoException;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.modelo.EntPersona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author iliac
 */
@Local
public interface MedicoSBLocal {
    public EntMedico saveMedico(EntMedico med) throws MedicoException;
    
    public EntMedico getMedico(EntPersona entPersona) throws MedicoException;
    
    public List<EntPaciente> getListaPaciente(EntMedico entMedico) throws MedicoException;
    
}
