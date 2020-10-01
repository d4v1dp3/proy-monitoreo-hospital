/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.UsuarioException;
import ipn.cic.sistmhospital.modelo.EntRol;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

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
}
