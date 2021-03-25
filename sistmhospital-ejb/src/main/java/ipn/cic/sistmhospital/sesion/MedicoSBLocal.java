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
import ipn.cic.sistmhospital.modelo.EntUsuario;
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
    EntPersona getPersonaDeMedico(EntMedico entMedico) throws MedicoException;
    
    public List<EntPaciente> getListaPaciente(EntMedico entMedico) throws MedicoException;
    
    public List<EntMedico> getMedicos() throws MedicoException;
    
    public EntMedico getMedicoDePaciente(EntPaciente entPaciente) throws MedicoException;
    
    public EntMedico getMedico(Integer idMedico) throws MedicoException;
    
    public EntMedico getMedico(String email);
    
}
