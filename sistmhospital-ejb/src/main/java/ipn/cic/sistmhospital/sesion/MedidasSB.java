/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.MedidasException;
import ipn.cic.sistmhospital.exception.SaveEntityException;
import ipn.cic.sistmhospital.modelo.EntMedidas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author J.PEREZ
 */
@Stateless
@LocalBean
public class MedidasSB extends BaseSB implements MedidasSBLocal{
    private static final Logger logger = Logger.getLogger(HospitalSB.class.getName());

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public EntMedidas guardaMedidas(EntMedidas med) throws MedidasException {
        try {
            return (EntMedidas)saveEntity(med);
        } catch (SaveEntityException ex) {
            logger.log(Level.SEVERE,"Error al intentar salvar entidad medidas : {0}", ex.getMessage());
            throw new MedidasException("Error al salvar las medidas.",ex);
        }
    }
}
