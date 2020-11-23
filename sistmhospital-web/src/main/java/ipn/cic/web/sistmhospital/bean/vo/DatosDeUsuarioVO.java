/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.vo;

import ipn.cic.sistmhospital.modelo.EntBitacora;
import ipn.cic.sistmhospital.modelo.EntPersona;
import ipn.cic.sistmhospital.modelo.EntRol;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author J.PEREZ
 */
public class DatosDeUsuarioVO implements Serializable {

    private String idUsuario;
    private String contrasenia;
    private Boolean activo;
    private List<EntRol> entRolList;
    private List<EntBitacora> entBitacoraList;
    private EntPersona idPersona;

    public DatosDeUsuarioVO() {
    }

    public DatosDeUsuarioVO(String idUsuario, String contrasenia, Boolean activo, List<EntRol> entRolList, List<EntBitacora> entBitacoraList, EntPersona idPersona) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
        this.activo = activo;
        this.entRolList = entRolList;
        this.entBitacoraList = entBitacoraList;
        this.idPersona = idPersona;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public EntPersona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(EntPersona idPersona) {
        this.idPersona = idPersona;
    }

    public List<EntRol> getEntRolList() {
        return entRolList;
    }

    public void setEntRolList(List<EntRol> entRolList) {
        this.entRolList = entRolList;
    }

    public List<EntBitacora> getEntBitacoraList() {
        return entBitacoraList;
    }

    public void setEntBitacoraList(List<EntBitacora> entBitacoraList) {
        this.entBitacoraList = entBitacoraList;
    }

}
