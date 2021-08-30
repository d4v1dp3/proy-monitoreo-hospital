/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.delegate;

import ipn.cic.sistmhospital.exception.IDUsuarioException;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.web.sistmhospital.bean.vo.PersonaVO;
import ipn.cic.web.sistmhospital.bean.vo.UsuarioVO;
import javax.ejb.Local;

/**
 *
 * @author marcos
 */
@Local
public interface GestionAdministradorBDLocal {
    public EntUsuario guardarAdministradorNuevo(PersonaVO persona, UsuarioVO usuario) throws IDUsuarioException;
}
