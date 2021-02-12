/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.HospitalException;
import ipn.cic.sistmhospital.exception.NoExisteHospitalException;
import ipn.cic.sistmhospital.exception.SaveEntityException;
import ipn.cic.sistmhospital.exception.UpdateEntityException;
import ipn.cic.sistmhospital.modelo.EntHospital;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Iliac Huerta Trujillo <ihuertat@ipn.mx>
 */
@Stateless
public class HospitalSB extends BaseSB implements HospitalSBLocal {
    private static final Logger logger = Logger.getLogger(HospitalSB.class.getName());
    
    @Override
    public Boolean existeHospital() throws HospitalException {
        Query qry = em.createQuery("SELECT count(h) FROM EntHospital h");
        Long res = (Long)qry.getSingleResult();
        logger.log(Level.INFO,"Entero resultado = {0}",res);
        
        return res!=0;     
        
    }

    @Override
    public EntHospital guardaHospital(EntHospital hosp) throws HospitalException {
        try {
            return (EntHospital)saveEntity(hosp);
        } catch (SaveEntityException ex) {
            logger.log(Level.SEVERE,"Error al intentar salvar entidad : {0}", ex.getMessage());
            throw new HospitalException("Error al salvar entidad en HospitalSB",ex);
        }
    }

    @Override
   public EntHospital getPrimerHospital() throws NoExisteHospitalException {//*       
        Query qry = em.createQuery("SELECT h FROM EntHospital h ORDER BY h.idHospital");
        List<EntHospital> res = qry.getResultList();
        
        if(res.size()==0){
            throw new NoExisteHospitalException("No existen hospitales!");
        }
        return res.get(0);
    }

    @Override
    public EntHospital updateHospital(EntHospital eh) throws UpdateEntityException {//*
        return (EntHospital)this.updateEntity(eh);   
    }
   
    @Override
    public EntHospital getHospital(Integer idHospital) throws NoExisteHospitalException {          
        Query qry = em.createQuery("SELECT e FROM EntHospital e WHERE e.idHospital = :idHospital");
        qry.setParameter("idHospital", idHospital);
        try{
            EntHospital res = (EntHospital)qry.getSingleResult();
            logger.log(Level.SEVERE, "Hospital {0} encontrado.", res.getIdHospital());
            return res;
        }catch(NoResultException ex){
            logger.log(Level.SEVERE, "La consulta no obtuvo resultados");
            throw new NoExisteHospitalException("No se encontro el hospital SB.");
        }
    }
    
}
