/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.NoExistePacienteException;
import ipn.cic.sistmhospital.exception.PacienteException;
import ipn.cic.sistmhospital.exception.SaveEntityException;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Iliac Huerta Trujillo <ihuertat@ipn.mx>
 */
@Stateless
public class PacienteSB extends BaseSB implements PacienteSBLocal {
    private static final Logger logger = Logger.getLogger(PacienteSB.class.getName());
    
    @Override
    public EntPaciente guardaPaciente(EntPaciente paciente) throws PacienteException {
        try {
            return (EntPaciente)saveEntity(paciente);
        } catch (SaveEntityException ex) {
            logger.log(Level.SEVERE,"Error al intentar salvar entidad : {0}", ex.getMessage());
            throw new PacienteException("Error al salvar entidad en PacienteSB",ex);
        }
    }
    
    @Override
    public EntPaciente getPaciente(long idPaciente) throws NoExistePacienteException {          
        Query qry = em.createQuery("SELECT e FROM EntPaciente e WHERE e.idPaciente = :idPaciente");
        qry.setParameter("idPaciente", idPaciente);
        EntPaciente res = (EntPaciente)qry.getSingleResult();//DUDA
        
        if(res == null){
            throw new NoExistePacienteException("No se encontro el paciente.");
        }
        res.getIdCareta();
        return res;
    }
}
