/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion.dashboard;

import ipn.cic.sistmhospital.exception.NoExisteMedicionesException;
import ipn.cic.sistmhospital.exception.NoExistePacienteDashException;
import ipn.cic.sistmhospital.modelo.EntMedidas;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.sesion.BaseSB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author marcos
 */
@Stateless
public class DashboardBD extends BaseSB implements DashboardBDLocal {
    private static final Logger logger = Logger.getLogger(DashboardBD.class.getName());
    
    @Override
    public EntPaciente getPaciente(Long idPaciente)throws NoExistePacienteDashException{
        query = em.createQuery("SELECT e FROM EntPaciente e WHERE e.idPaciente = :idPaciente");
        query.setParameter("idPaciente", idPaciente);
        try{
            EntPaciente paciente = (EntPaciente)query.getSingleResult();
            return paciente;
        }catch(NoResultException ex){
            logger.log(Level.SEVERE,"La consulta no obtuvo resultados",ex);
            throw new NoExistePacienteDashException("No se encontro al paciente");
        }
        
    }

    @Override
    public List<EntMedidas> getListaMedidas(EntPaciente entPaciente) throws NoExisteMedicionesException{
        query = em.createQuery("SELECT e FROM EntMedidas e WHERE e.entPaciente = :entPaciente");
        query.setParameter("entPaciente", entPaciente);
        try{
            return query.getResultList();
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error al obtener lista de mediciones: {0}",e.getMessage());
            throw new NoExisteMedicionesException("No es posible obtener lista de mediciones del paciente");
        }
    }
    
    
}