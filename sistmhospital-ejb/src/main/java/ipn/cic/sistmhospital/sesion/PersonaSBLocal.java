/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.NoExistePersonaException;
import ipn.cic.sistmhospital.exception.SaveEntityException;
import ipn.cic.sistmhospital.exception.UpdateEntityException;
import ipn.cic.sistmhospital.modelo.EntPersona;
import javax.ejb.Local;

/**
 *
 * @author Iliac Huerta Trujillo <ihuertat@ipn.mx>
 */
@Local
public interface PersonaSBLocal {
    EntPersona savePersona(EntPersona persona)throws SaveEntityException; 
    EntPersona updatePersona(EntPersona persona) throws UpdateEntityException;
    EntPersona getPersonaDePaciente(Long idPaciente);
    EntPersona getEntPersona(EntPersona Persona) throws NoExistePersonaException;
}
