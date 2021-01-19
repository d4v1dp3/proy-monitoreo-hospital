/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion.dashboard;

import ipn.cic.sistmhospital.exception.NoExisteEstadoPacException;
import ipn.cic.sistmhospital.exception.NoExisteMedicionesException;
import ipn.cic.sistmhospital.exception.NoExistePacienteDashException;
import ipn.cic.sistmhospital.exception.NoExisteValoresRefException;
import ipn.cic.sistmhospital.modelo.EntEstadopaciente;
import ipn.cic.sistmhospital.modelo.EntMedidas;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.modelo.EntValoresReferencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcos
 */
@Local 
public interface DashboardBDLocal {
    public EntPaciente getPaciente(Long idPaciente) throws NoExistePacienteDashException;
    public List<EntMedidas> getListaMedidas(EntPaciente entPaciente) throws NoExisteMedicionesException;
    public EntValoresReferencia getValoresRef(Short idValRef) throws NoExisteValoresRefException;
    public EntEstadopaciente getEstadoPac(Long idPaciente) throws NoExisteEstadoPacException;
}