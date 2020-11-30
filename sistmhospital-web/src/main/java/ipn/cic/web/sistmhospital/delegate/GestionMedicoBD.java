/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.delegate;

import ipn.cic.sistmhospital.exception.MedicoException;
import ipn.cic.sistmhospital.modelo.EntMedico;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author iliaco
 */
@Stateless
@PermitAll
@SecurityDomain("other")
public class GestionMedicoBD implements GestionMedicoBDLocal {

    @Override
    public EntMedico guardarMedicoNuevo() throws MedicoException {
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
