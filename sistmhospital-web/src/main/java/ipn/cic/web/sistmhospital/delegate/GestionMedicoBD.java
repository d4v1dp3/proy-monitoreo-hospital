/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.delegate;

import ipn.cic.sistmhospital.exception.GeneroException;
import ipn.cic.sistmhospital.exception.MedicoException;
import ipn.cic.sistmhospital.exception.NoExisteHospitalException;
import ipn.cic.sistmhospital.exception.RolException;
import ipn.cic.sistmhospital.exception.SaveEntityException;
import ipn.cic.sistmhospital.modelo.EntGenero;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.modelo.EntPersona;
import ipn.cic.sistmhospital.modelo.EntRol;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.sistmhospital.sesion.GeneroSBLocal;
import ipn.cic.sistmhospital.sesion.HospitalSBLocal;
import ipn.cic.sistmhospital.sesion.MedicoSBLocal;
import ipn.cic.sistmhospital.sesion.PersonaSBLocal;
import ipn.cic.sistmhospital.sesion.RolSBLocal;
import ipn.cic.sistmhospital.sesion.UsuarioSBLocal;
import ipn.cic.sistmhospital.util.Constantes;
import ipn.cic.web.sistmhospital.bean.vo.MedicoVO;
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
 * @author iliaco
 */
@Stateless
@PermitAll
@SecurityDomain("other")
public class GestionMedicoBD implements GestionMedicoBDLocal {

    private static final Logger logger = Logger.getLogger(GestionMedicoBD.class.getName());

    @EJB
    private PersonaSBLocal personaSB;
    @EJB
    private UsuarioSBLocal usuarioSB;
    @EJB
    private MedicoSBLocal medicoSB;
    @EJB
    private GeneroSBLocal generoSB;
    @EJB
    private RolSBLocal  rolSB;
    @EJB
    private HospitalSBLocal hospitalSB;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public EntMedico guardarMedicoNuevo(MedicoVO medico, PersonaVO persona,
                                        UsuarioVO usuario) throws MedicoException {
        // Aquí hay que preparar los datos para almacenar la información
        // primero los datos de persona para persistirlos
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
            throw new MedicoException("Imposible asignar genero ", ex);
        } catch (SaveEntityException ex) {
            logger.log(Level.SEVERE,"Error al persistir EnPersona : {0}",ex.getMessage());
            throw new MedicoException("Imposible salvar Persona para Médico ", ex);
        }
        
        // Creando la Entidad USUARIO
        EntUsuario entUsuario = new EntUsuario();
        entUsuario.setIdUsuario(usuario.getIdUsuario());
        entUsuario.setContrasenia(usuario.getContrasenia());
        entUsuario.setIdPersona(entPersona);
        entUsuario.setActivo(usuario.getActivo());
        Short medRol = new Integer(Constantes.getInstance().getInt("ROL_MEDICO")).shortValue();
        
        
        try {
            EntRol rolMedico = rolSB.getRolId(medRol);
            entUsuario.getEntRolList().add(rolMedico);
            entUsuario = usuarioSB.saveUsuario(entUsuario);
        } catch (SaveEntityException | RolException ex) {
            logger.log(Level.SEVERE,"Error al persistir usuario : {0}",ex.getMessage());
            throw new MedicoException("Error ", ex);
        }
        
        EntMedico entMed = new EntMedico();
        entMed.setCedulaProf(medico.getCedulaProf());
        entMed.setCelular(medico.getCelular());
        entMed.setEmail(medico.getEmail());
        entMed.setIdPersona(entPersona);
        
        try {
            entMed.getEntHospitalList().add(hospitalSB.getPrimerHospital());
        } catch (NoExisteHospitalException ex) {
            logger.log(Level.SEVERE,"Error al ObtenerDatos de hospital : {0}",ex.getMessage());
            throw new MedicoException("Error al consultar datos de hospital ", ex);
        }
        
        entMed = medicoSB.saveMedico(entMed);
        
        return entMed;
        
    }

}
