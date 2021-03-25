/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.IDUsuarioException;
import ipn.cic.sistmhospital.exception.SaveEntityException;
import ipn.cic.sistmhospital.exception.UpdateEntityException;
import ipn.cic.sistmhospital.exception.UsuarioException;
import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.sistmhospital.modelo.EntPersona;
import ipn.cic.sistmhospital.modelo.EntRol;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Iliac Huerta Trujillo <ihuertat@ipn.mx>
 */
@Stateless
public class UsuarioSB extends BaseSB implements UsuarioSBLocal {
    private static final Logger logger = Logger.getLogger(UsuarioSB.class.getName());

    /**
     * Obtiene al usuario que tiene el nombre de usuario especificado como
     * parametro (debe ser unico)
     *
     * @param nomUsuario Nombre de usuario unico
     * @return Entidad EntUsuario
     * @throws UsuarioException
     */
    @Override
    public EntUsuario getUsuario(String nomUsuario) throws UsuarioException {
        EntUsuario entUsuario = null;
        logger.log(Level.INFO,"Nombre del usuario buscado :{0} ", nomUsuario);
        try {
            query = em.createNamedQuery("EntUsuario.findByIdUsuario");
            query.setParameter("idUsuario", nomUsuario);
            entUsuario = (EntUsuario) query.getSingleResult();
            
            entUsuario.getIdPersona();
            entUsuario.getIdPersona().getNombre();
            entUsuario.getIdPersona().getPrimerApellido();
            entUsuario.getIdPersona().getSegundoApellido();
            entUsuario.getIdPersona().getCurp();
            entUsuario.getIdPersona().getEdad();
            entUsuario.getIdPersona().getIdGenero();
                    
            return entUsuario;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new UsuarioException(e.getMessage(), e);
        }
    }

    /**
     * Obtiene todos los roles del usuario identificado por el parametro
     * idUsuario en orden ascendente por el campo descripcion del rol
     *
     * @param idUsuario Identificador unico del usuario
     * @return Lista de entidades EntRol
     * @throws UsuarioException
     */
    @Override
    public List<EntRol> getRoles(String idUsuario) throws UsuarioException {
        try {
            query = em.createQuery("SELECT DISTINCT r FROM EntRol r "
                    + "JOIN r.entUsuarioList u "
                    + "WHERE u.idUsuario=:id_Usuario "
                    + "ORDER BY r.descripcion ASC");
            query.setParameter("id_Usuario", idUsuario);
            return query.getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new UsuarioException(e.getMessage(), e);
        }
    }

    @Override
    public List<EntUsuario> getUsuarios() throws UsuarioException {
        try{
            query = em.createQuery("SELECT usr From EntUsuario usr LEFT JOIN FETCH  usr.idPersona p "
                    + "ORDER BY p.primerApellido, p.segundoApellido, p.nombre");
            return query.getResultList();
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error al obtener la lista de usuarios : {0}",e.getMessage());
            throw new UsuarioException("No esposible obtener la lista de usuarios",e);
        }
                    
    }

    @Override
    public EntUsuario saveUsuario(EntUsuario eu) throws SaveEntityException, IDUsuarioException{
        if(existeIdUsiario(eu.getIdUsuario())){
            throw new IDUsuarioException();
        }        
        eu = (EntUsuario) this.saveEntity(eu);
        return eu;
    }

    @Override
    public boolean existeIdUsiario(String idUsuario){
        try{
            query = em.createNamedQuery("EntUsuario.findByIdUsuario");
            query.setParameter("idUsuario",idUsuario.trim());
            EntUsuario usuario = (EntUsuario) query.getSingleResult();
            
            return usuario!=null;            
        }catch(NoResultException e){
            return false;
        }
    }
    
    @Override
    public EntUsuario updateUsuario(EntUsuario usuario) throws UpdateEntityException {
        return (EntUsuario)this.updateEntity(usuario);   
    }
    
    @Override
    public EntPersona getPersonaDeUsuario(EntUsuario usuario) throws UsuarioException {
        query = em.createQuery("SELECT usr.idPersona From EntUsuario usr WHERE usr.idUsuario=:idUsuario");
        query.setParameter("idUsuario", usuario.getIdUsuario());
        EntPersona persona = (EntPersona) query.getSingleResult();
        persona.getCurp();
        persona.getNombre();
        persona.getPrimerApellido();
        persona.getSegundoApellido();
        persona.getEdad();
        persona.getIdGenero();
        return persona;
    }

    @Override
    public EntUsuario getUsuario(EntMedico medico) {
        try{
            query = em.createQuery("SELECT e FROM EntUsuario e WHERE e.idPersona=:idPersona");
            query.setParameter("idPersona",medico.getIdPersona());
            EntUsuario usuario = (EntUsuario) query.getSingleResult();        
            return usuario;
        }catch(Exception e){
            
        }
        return null;
    }
        
}
