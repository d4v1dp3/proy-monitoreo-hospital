/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.CaretaHospitalException;
import ipn.cic.sistmhospital.exception.SaveEntityException;
import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntCaretaHospital;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author leoj_
 */
@Stateless
public class CaretaHospitalSB extends BaseSB implements CaretaHospitalSBLocal {
    private static final Logger logger = Logger.getLogger(HospitalSB.class.getName());

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public EntCaretaHospital guardaCaretaHospital(EntCaretaHospital caretahospital) throws CaretaHospitalException {
        try {
            return (EntCaretaHospital)saveEntity(caretahospital);
        } catch (SaveEntityException ex) {
            logger.log(Level.SEVERE,"Error al intentar salvar entidad : {0}", ex.getMessage());
            throw new CaretaHospitalException("Error al salvar entidad en CaretaHospitalSB",ex);
        }
    }
    
    @Override
    public EntCaretaHospital getCaretaHospital(EntCareta careta) throws CaretaHospitalException {          
        Query qry = em.createQuery("SELECT e FROM EntCaretaHospital e WHERE e.entCareta = :entCareta");
        qry.setParameter("entCareta", careta);
        try{
            EntCaretaHospital res = (EntCaretaHospital)qry.getSingleResult();
            return res;
        }catch(NoResultException ex){
            logger.log(Level.SEVERE, "La consulta no obtuvo resultados");
            throw new CaretaHospitalException("No se encontro caretahospital SB.");
        }
    }
    
    @Override
    public List<EntCaretaHospital> getCaretasAsignadas() throws CaretaHospitalException {        
        Query qry = em.createQuery("SELECT e FROM EntCaretaHospital e");
        List<EntCaretaHospital> res = qry.getResultList();  
        List<EntCaretaHospital> resp = new ArrayList();  
        for(EntCaretaHospital ent: res){
            ent.getEntCareta().getIdCareta();
            ent.getEntCareta().getNoSerie();
            ent.getEntCareta().getFechaManufactura();
            ent.getEntHospital().getIdHospital();
            ent.getEntHospital().getNombre();
            ent.getFechaAsignacion();
            resp.add(ent);
        }
        if(resp.isEmpty()){
            throw new CaretaHospitalException("Relacion no encontrada.");
        }
        return resp;
    }
}
