/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.NoExistePacienteException;
import ipn.cic.sistmhospital.exception.PacienteException;
import ipn.cic.sistmhospital.exception.UpdateEntityException;
import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.modelo.EntPersona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Iliac Huerta Trujillo <ihuertat@ipn.mx>
 */
@Local
public interface PacienteSBLocal {
    
    EntPaciente updatePaciente(EntPaciente pac) throws UpdateEntityException;
    EntPaciente guardaPaciente(EntPaciente paciente) throws PacienteException;
    EntPaciente getPaciente(long idPaciente) throws NoExistePacienteException;
    public EntPaciente getPaciente(EntPersona Persona) throws NoExistePacienteException;
    public List<EntPaciente> getPacientes(EntMedico entMedico) throws PacienteException;
    EntCareta getCaretaDePaciente(EntPaciente Paciente) throws NoExistePacienteException;
    public List<EntPaciente> getPacientes() throws PacienteException;
    
}
