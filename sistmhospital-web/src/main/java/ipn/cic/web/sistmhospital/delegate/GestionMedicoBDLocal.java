/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.delegate;

import ipn.cic.sistmhospital.exception.MedicoException;
import ipn.cic.sistmhospital.modelo.EntMedico;
import javax.ejb.Local;

/**
 *
 * @author iliaco
 */
@Local
public interface GestionMedicoBDLocal {
    public EntMedico guardarMedicoNuevo() throws MedicoException;
}
