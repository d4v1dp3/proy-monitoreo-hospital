/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.NoExistePacienteException;
import ipn.cic.sistmhospital.exception.PacienteException;
import ipn.cic.sistmhospital.exception.UsuarioException;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Iliac Huerta Trujillo <ihuertat@ipn.mx>
 */
@Local
public interface PacienteSBLocal {
    
    EntPaciente guardaPaciente(EntPaciente paciente) throws PacienteException;
    EntPaciente getPaciente(long idPaciente) throws NoExistePacienteException;
    public List<EntPaciente> getPacientes(EntMedico entMedico) throws PacienteException;
    
}
