/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion.dashboard;

import ipn.cic.sistmhospital.modelo.EntMedidas;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcos
 */
@Local 
public interface DashboardBDLocal {
    EntPaciente getPaciente(Long idPaciente);
    public List<EntMedidas> getListaMedidas(EntPaciente entPaciente);
}
