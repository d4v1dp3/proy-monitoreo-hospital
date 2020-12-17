/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion.dashboard;

import ipn.cic.sistmhospital.modelo.EntMedidas;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.sesion.BaseSB;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author marcos
 */
@Stateless
public class DashboardBD extends BaseSB implements DashboardBDLocal {
    
    @Override
    public EntPaciente getPaciente(Long idPaciente){
        query = em.createQuery("SELECT e FROM EntPaciente e WHERE e.idPaciente = :idPaciente");
        query.setParameter("idPaciente", idPaciente);
        EntPaciente paciente = (EntPaciente)query.getSingleResult();
        return paciente;
    }

    @Override
    public List<EntMedidas> getListaMedidas(EntPaciente entPaciente){
        query = em.createQuery("SELECT e FROM EntMedidas e WHERE e.entPaciente = :entPaciente");
        query.setParameter("entPaciente", entPaciente);
        return query.getResultList();
    }
    
    
}
