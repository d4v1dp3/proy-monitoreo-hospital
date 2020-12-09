/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipn.cic.web.sistmhospital.delegate;

import ipn.cic.sistmhospital.exception.GeneroException;
import ipn.cic.sistmhospital.exception.NoExisteHospitalException;
import ipn.cic.sistmhospital.exception.PacienteException;
import ipn.cic.sistmhospital.exception.RolException;
import ipn.cic.sistmhospital.exception.SaveEntityException;
import ipn.cic.sistmhospital.modelo.EntGenero;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.modelo.EntPersona;
import ipn.cic.sistmhospital.modelo.EntRol;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.sistmhospital.sesion.GeneroSBLocal;
import ipn.cic.sistmhospital.sesion.HospitalSBLocal;
import ipn.cic.sistmhospital.sesion.PacienteSBLocal;
import ipn.cic.sistmhospital.sesion.PersonaSBLocal;
import ipn.cic.sistmhospital.sesion.RolSBLocal;
import ipn.cic.sistmhospital.sesion.UsuarioSBLocal;
import ipn.cic.sistmhospital.util.Constantes;
import ipn.cic.web.sistmhospital.bean.vo.PacienteVO;
import ipn.cic.web.sistmhospital.bean.vo.PersonaVO;
import ipn.cic.web.sistmhospital.bean.vo.UsuarioVO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author J.PEREZ
 */

@Stateless
@PermitAll
@SecurityDomain("other")
public class GestionPacienteBD implements GestionPacienteBDLocal {

     private static final Logger logger = Logger.getLogger(GestionMedicoBD.class.getName());

    @EJB
    private PersonaSBLocal personaSB;
    @EJB
    private UsuarioSBLocal usuarioSB;
    @EJB
    private PacienteSBLocal pacienteSB;
    @EJB
    private GeneroSBLocal generoSB;
    @EJB
    private RolSBLocal  rolSB;
    @EJB
    private HospitalSBLocal hospitalSB;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public EntPaciente guardarPacienteNuevo(PacienteVO paciente, PersonaVO persona, UsuarioVO usuario) throws PacienteException {
        
        EntPersona entPersona = new EntPersona();
        
        entPersona.setNombre(persona.getNombre().toUpperCase());
        entPersona.setPrimerApellido(persona.getPrimerApellido().toUpperCase());
        entPersona.setSegundoApellido(persona.getSegundoApellido().toUpperCase());
        entPersona.setCurp(persona.getCurp().toUpperCase());
        
        try {
            EntGenero genero = generoSB.getGeneroID(persona.getIdGenero().shortValue());
            entPersona.setIdGenero(genero);
            entPersona = personaSB.savePersona(entPersona);
        } catch (GeneroException ex) {
            logger.log(Level.SEVERE, "Error al obtener EntGenero con id : {0}", persona.getIdGenero());
            logger.log(Level.SEVERE, "Error al obtener EntGenero: {0}", ex.getMessage());
            throw new PacienteException("Imposible asignar genero ", ex);
        } catch (SaveEntityException ex) {
            logger.log(Level.SEVERE,"Error al persistir EntPersona : {0}",ex.getMessage());
            throw new PacienteException("Imposible salvar Persona para Paciente ", ex);
        }
        
         // Creando la Entidad USUARIO
        EntUsuario entUsuario = new EntUsuario();
        entUsuario.setIdUsuario(usuario.getIdUsuario());
        entUsuario.setContrasenia(usuario.getContrasenia());
        entUsuario.setIdPersona(entPersona);
        entUsuario.setActivo(usuario.getActivo());
        Short pacRol = new Integer(Constantes.getInstance().getInt("ROL_PACIENTE")).shortValue();
        
        try {
            EntRol rolMedico = rolSB.getRolId(pacRol);
            entUsuario.getEntRolList().add(rolMedico);
            entUsuario = usuarioSB.saveUsuario(entUsuario);
        } catch (SaveEntityException | RolException ex) {
            logger.log(Level.SEVERE,"Error al persistir usuario : {0}",ex.getMessage());
            throw new PacienteException("Error ", ex);
        }
        
        EntPaciente entPac = new EntPaciente();
        
        logger.log(Level.INFO,"Creando Entidad Paciente[1]");
        entPac.setIdPersona(entPersona);
        entPac.setDirCalle(paciente.getDirCalle());
        entPac.setDirNumero(paciente.getDirNumero());
        entPac.setDirInterior(paciente.getDirInterior());
        entPac.setTelFijo(paciente.getTelFijo());
        entPac.setTelCel(paciente.getTelCel());
        //entPac.setIdCareta(paciente.getIdCareta());
        //entPac.setIdEstadopaciente(paciente.getIdEstadopaciente());
        
        try {            
            entPac.setIdHospital(hospitalSB.getPrimerHospital());
        } catch (NoExisteHospitalException ex) {
            logger.log(Level.SEVERE,"Error al ObtenerDatos de hospital : {0}",ex.getMessage());
            throw new PacienteException("Error al consultar datos de hospital ", ex);
        }

        
        entPac = pacienteSB.guardaPaciente(entPac);
        
        return entPac;
    }
    
    
    
}
