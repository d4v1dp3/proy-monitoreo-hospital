/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.MedicoException;
import ipn.cic.sistmhospital.exception.SaveEntityException;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.modelo.EntPersona;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 * SessionBean para manejo de entidades EntMedico, encargada de la persistencia
 * actualización y eliminación de registros.
 * @author Iliac Huerta Trujillo
 */
@Stateless
public class MedicoSB extends BaseSB implements MedicoSBLocal {
    private static final Logger logger = Logger.getLogger(MedicoSB.class.getName());
    
    @Override
    public EntMedico saveMedico(EntMedico med) throws MedicoException {
        try {
            med = (EntMedico) saveEntity(med);
        } catch (SaveEntityException ex) {
            logger.log(Level.SEVERE,"Error al persistir entidadMedico : {0}",ex.getMessage());
            throw new MedicoException("Imposible persistir Entidad Médico",ex);
        }
        return med;
    }
    
    
    @Override
    public EntMedico getMedico(EntPersona entPersona) throws MedicoException {
        logger.log(Level.INFO, "MedicoSB: Entra a recuperar medico.");
        
        query = em.createQuery("SELECT e From EntMedico e WHERE e.idPersona = :idPersona")
                .setParameter("idPersona", entPersona);
//        
//        logger.log(Level.INFO, "PacienteSB: consulta ejecutada.");
        EntMedico res = new EntMedico();
        res = (EntMedico)query.getSingleResult();

        logger.log(Level.INFO, "MedicoSB: Medico recuperado. {0}", res.getCedulaProf());
        return res;
    }
    
}
