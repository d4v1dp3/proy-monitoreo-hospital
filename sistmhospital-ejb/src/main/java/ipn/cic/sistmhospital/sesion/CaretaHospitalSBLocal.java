/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.CaretaHospitalException;
import ipn.cic.sistmhospital.exception.RemoveEntityException;
import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntCaretaHospital;
import ipn.cic.sistmhospital.modelo.EntHospital;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author J.Perez
 */
@Local
public interface CaretaHospitalSBLocal {
    EntCaretaHospital guardaCaretaHospital(EntCaretaHospital caretahospital) throws CaretaHospitalException;
    EntCaretaHospital getCaretaHospital(EntCareta careta) throws CaretaHospitalException;
    List<EntCaretaHospital> getCaretasAsignadas() throws CaretaHospitalException;
    List<EntCaretaHospital> getCaretasNoAsignadas() throws CaretaHospitalException;
    List<EntCaretaHospital> getCaretasNoAsignadas(EntHospital entHospital) throws CaretaHospitalException;
    boolean borrarCaretaHospital(EntCaretaHospital caretahospital) throws RemoveEntityException;
}
