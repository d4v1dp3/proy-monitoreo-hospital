/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.CaretaException;
import ipn.cic.sistmhospital.exception.NoExisteCaretaException;
import ipn.cic.sistmhospital.exception.RemoveEntityException;
import ipn.cic.sistmhospital.exception.UpdateEntityException;
import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntCaretaHospital;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author J.PEREZ
 */

@Local
public interface CaretaSBLocal {
    EntCareta guardaCareta(EntCareta careta) throws CaretaException;
    EntCareta getCareta(long idCareta) throws NoExisteCaretaException;
    List<EntCareta> getCaretas() throws NoExisteCaretaException;
    boolean borrarCareta(EntCareta careta) throws RemoveEntityException;
    EntCareta updateCareta(EntCareta careta) throws UpdateEntityException;
}
