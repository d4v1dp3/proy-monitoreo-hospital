/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.EstadoPacienteException;
import ipn.cic.sistmhospital.modelo.EntEstadopaciente;
import javax.ejb.Local;

/**
 *
 * @author J.Perez
 */
@Local
public interface EstadoPacienteSBLocal {
    EntEstadopaciente getEstadoPaciente(Integer idestado) throws EstadoPacienteException;    
}
