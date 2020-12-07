/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.RolException;
import ipn.cic.sistmhospital.modelo.EntRol;
import javax.ejb.Local;

/**
 *
 * @author iliac
 */
@Local
public interface RolSBLocal {
    EntRol getRolId(Short idRol) throws RolException;
}
