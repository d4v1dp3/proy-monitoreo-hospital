/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipn.cic.web.sistmhospital.delegate;

import ipn.cic.sistmhospital.exception.GeneroException;
import ipn.cic.sistmhospital.exception.IDUsuarioException;
import ipn.cic.sistmhospital.exception.RolException;
import ipn.cic.sistmhospital.exception.SaveEntityException;
import ipn.cic.sistmhospital.modelo.EntGenero;
import ipn.cic.sistmhospital.modelo.EntPersona;
import ipn.cic.sistmhospital.modelo.EntRol;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.sistmhospital.sesion.GeneroSBLocal;
import ipn.cic.sistmhospital.sesion.PersonaSBLocal;
import ipn.cic.sistmhospital.sesion.RolSBLocal;
import ipn.cic.sistmhospital.sesion.UsuarioSBLocal;
import ipn.cic.sistmhospital.util.Constantes;
import ipn.cic.web.sistmhospital.bean.vo.PersonaVO;
import ipn.cic.web.sistmhospital.bean.vo.UsuarioVO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author marcos
 */
@Stateless
@PermitAll
@SecurityDomain("other")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class GestionAdministradorBD implements GestionAdministradorBDLocal {

    private static final Logger logger = Logger.getLogger(GestionMedicoBD.class.getName());

    @EJB
    private PersonaSBLocal personaSB;
    @EJB
    private UsuarioSBLocal usuarioSB;
    @EJB
    private GeneroSBLocal generoSB;
    @EJB
    private RolSBLocal rolSB;
    @Resource
    private EJBContext ejbContext;
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public EntUsuario guardarAdministradorNuevo(PersonaVO persona, UsuarioVO usuario) throws IDUsuarioException {

        try {
            logger.log(Level.INFO, "Inicia Delegate guardar Administrador nuevo");
            logger.log(Level.INFO, "Persona para administrador ... ");
            EntPersona entPersona = new EntPersona();
            entPersona.setNombre(persona.getNombre().toUpperCase());
            entPersona.setPrimerApellido(persona.getPrimerApellido().toUpperCase());
            entPersona.setSegundoApellido(persona.getSegundoApellido().toUpperCase());
            entPersona.setCurp(persona.getCurp().toUpperCase());
            entPersona.setEdad(persona.getEdad());

            EntGenero genero = generoSB.getGeneroID(persona.getIdGenero().shortValue());
            entPersona.setIdGenero(genero);
            entPersona = personaSB.savePersona(entPersona);
            logger.log(Level.INFO, "idPersona{0}", entPersona.getIdPersona());

            // Creando la Entidad USUARIO
            logger.log(Level.INFO, "Usuario administrador... ");
            EntUsuario entUsuario = new EntUsuario();
            entUsuario.setIdUsuario(usuario.getIdUsuario());
            entUsuario.setContrasenia(usuario.getContrasenia());
            entUsuario.setIdPersona(entPersona);
            entUsuario.setActivo(usuario.getActivo());
            entUsuario.setEmail(usuario.getEmail());

            //Asignando rol
            Short admRol = new Integer(Constantes.getInstance().getInt("ROL_ADMINISTRADOR")).shortValue();
            EntRol rolAdministrador = rolSB.getRolId(admRol);
            entUsuario.getEntRolList().add(rolAdministrador);
            entUsuario = usuarioSB.saveUsuario(entUsuario);
            logger.log(Level.INFO, "idUsuario{0}", entUsuario.getIdUsuario());
            logger.log(Level.INFO, "Rol: {0}", entUsuario.getEntRolList().get(0).getDescripcion());

            return entUsuario;
        } catch (GeneroException ex) {
            logger.log(Level.SEVERE, "Error al obtener EntGenero con id : {0}", persona.getIdGenero());
            logger.log(Level.SEVERE, "Error al obtener EntGenero: {0}", ex.getMessage());
            ejbContext.setRollbackOnly();
        } catch (SaveEntityException ex) {
            logger.log(Level.SEVERE, "Error al persistir EnPersona : {0}", ex.getMessage());
            ejbContext.setRollbackOnly();
        } catch (RolException ex) {
             logger.log(Level.SEVERE, "Error al persistir usuario : {0}", ex.getMessage());
            ejbContext.setRollbackOnly();
        } 
        return null;
    }
}
