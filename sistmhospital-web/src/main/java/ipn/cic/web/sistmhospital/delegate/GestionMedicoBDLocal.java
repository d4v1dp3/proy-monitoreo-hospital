/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.delegate;

import ipn.cic.sistmhospital.exception.MedicoException;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.web.sistmhospital.bean.vo.MedicoVO;
import ipn.cic.web.sistmhospital.bean.vo.PersonaVO;
import ipn.cic.web.sistmhospital.bean.vo.UsuarioVO;
import javax.ejb.Local;

/**
 *
 * @author iliaco
 */
@Local
public interface GestionMedicoBDLocal {
    public EntMedico guardarMedicoNuevo(MedicoVO medico, PersonaVO persona, 
                                        UsuarioVO usuario) throws MedicoException;
}
