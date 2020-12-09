/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipn.cic.web.sistmhospital.delegate;

import ipn.cic.sistmhospital.exception.PacienteException;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.web.sistmhospital.bean.vo.PacienteVO;
import ipn.cic.web.sistmhospital.bean.vo.PersonaVO;
import ipn.cic.web.sistmhospital.bean.vo.UsuarioVO;
import javax.ejb.Local;

/**
 *
 * @author J.PEREZ
 */
@Local
public interface GestionPacienteBDLocal {
     public EntPaciente guardarPacienteNuevo(PacienteVO paciente, PersonaVO persona, 
                                        UsuarioVO usuario) throws PacienteException;
}
