/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.delegate;

import ipn.cic.sistmhospital.exception.GeneroException;
import ipn.cic.sistmhospital.exception.IDUsuarioException;
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
 * @author iliaco
 */
@Stateless
@PermitAll
@SecurityDomain("other")
@TransactionManagement(value=TransactionManagementType.CONTAINER)
@TransactionAttribute(value=TransactionAttributeType.REQUIRED)
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
    private RolSBLocal rolSB;
    @EJB
    private HospitalSBLocal hospitalSB;
    @Resource
    private EJBContext ejbContext;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public EntMedico guardarMedicoNuevo(MedicoVO medico, PersonaVO persona,
            UsuarioVO usuario) throws MedicoException, IDUsuarioException {
        // Aquí hay que preparar los datos para almacenar la información
        // primero los datos de persona para persistirlos
        try {
            logger.log(Level.INFO, "Inicia Delegate guardar medico nuevo ");
            EntPersona entPersona = new EntPersona();
            entPersona.setNombre(persona.getNombre().toUpperCase());
            entPersona.setPrimerApellido(persona.getPrimerApellido().toUpperCase());
            entPersona.setSegundoApellido(persona.getSegundoApellido().toUpperCase());
            entPersona.setCurp(persona.getCurp().toUpperCase());
            entPersona.setEdad(persona.getEdad());

            EntGenero genero = generoSB.getGeneroID(persona.getIdGenero().shortValue());
            entPersona.setIdGenero(genero);
            entPersona = personaSB.savePersona(entPersona);

            // Creando la Entidad USUARIO
            EntUsuario entUsuario = new EntUsuario();
            entUsuario.setIdUsuario(usuario.getIdUsuario());
            entUsuario.setContrasenia(usuario.getContrasenia());
            entUsuario.setIdPersona(entPersona);
            entUsuario.setActivo(usuario.getActivo());
            entUsuario.setEmail(usuario.getEmail());
            Short medRol = new Integer(Constantes.getInstance().getInt("ROL_MEDICO")).shortValue();

            EntRol rolMedico = rolSB.getRolId(medRol);
            entUsuario.getEntRolList().add(rolMedico);
            entUsuario = usuarioSB.saveUsuario(entUsuario);

            EntMedico entMed = new EntMedico();
            entMed.setCedulaProf(medico.getCedulaProf());
            entMed.setCelular(medico.getCelular());
            entMed.setIdPersona(entPersona);

            entMed.getEntHospitalList().add(hospitalSB.getPrimerHospital());

            entMed = medicoSB.saveMedico(entMed);

            return entMed;
        } catch (GeneroException ex) {
            logger.log(Level.SEVERE, "Error al obtener EntGenero con id : {0}", persona.getIdGenero());
            logger.log(Level.SEVERE, "Error al obtener EntGenero: {0}", ex.getMessage());
            ejbContext.setRollbackOnly();
            throw new MedicoException("Imposible asignar genero ", ex);
        } catch (SaveEntityException ex) {
            logger.log(Level.SEVERE, "Error al persistir EnPersona : {0}", ex.getMessage());
            ejbContext.setRollbackOnly();
            throw new MedicoException("Imposible salvar Persona para Médico ", ex);
            
        } catch (RolException ex) {
            logger.log(Level.SEVERE, "Error al persistir usuario : {0}", ex.getMessage());
            ejbContext.setRollbackOnly();
            throw new MedicoException("Error ", ex);
        } catch (NoExisteHospitalException ex) {
            logger.log(Level.SEVERE, "Error al ObtenerDatos de hospital : {0}", ex.getMessage());
            ejbContext.setRollbackOnly();
            throw new MedicoException("Error al consultar datos de hospital ", ex);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error Desconocido : {0}", ex.getMessage());
            ejbContext.setRollbackOnly();
            throw new MedicoException("Imposible salvar Persona para Médico ", ex);
        }
    }
}
