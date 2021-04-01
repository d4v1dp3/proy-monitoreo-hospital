/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Iliac Huerta Trujillo <ihuertat@ipn.mx>
 */
@Entity
@Table(name = "MH_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntUsuario.findAll", query = "SELECT e FROM EntUsuario e"),
    @NamedQuery(name = "EntUsuario.findByIdUsuario", query = "SELECT e FROM EntUsuario e WHERE e.idUsuario = :idUsuario"),
    @NamedQuery(name = "EntUsuario.findByContrasenia", query = "SELECT e FROM EntUsuario e WHERE e.contrasenia = :contrasenia"),
    @NamedQuery(name = "EntUsuario.findByEmail", query = "SELECT e FROM EntUsuario e WHERE e.email = :email"),
    @NamedQuery(name = "EntUsuario.findByActivo", query = "SELECT e FROM EntUsuario e WHERE e.activo = :activo")})
public class EntUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    @Size(max = 50)
    @Column(name = "CONTRASENIA")
    private String contrasenia;
    @Column(name = "ACTIVO")
    private Boolean activo;
    @JoinTable(name = "MH_USUARIO_ROL", joinColumns = {
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")},
         inverseJoinColumns = {
        @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<EntRol> entRolList;
    @OneToMany(mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<EntBitacora> entBitacoraList;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne(fetch = FetchType.LAZY)
    private EntPersona idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;

    public EntUsuario() {
        entRolList = new ArrayList<>();
    }

    public EntUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
        entRolList = new ArrayList<>();
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

    @XmlTransient
    public List<EntRol> getEntRolList() {
        return entRolList;
    }

    public void setEntRolList(List<EntRol> entRolList) {
        this.entRolList = entRolList;
    }

    @XmlTransient
    public List<EntBitacora> getEntBitacoraList() {
        return entBitacoraList;
    }

    public void setEntBitacoraList(List<EntBitacora> entBitacoraList) {
        this.entBitacoraList = entBitacoraList;
    }

    public EntPersona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(EntPersona idPersona) {
        this.idPersona = idPersona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntUsuario)) {
            return false;
        }
        EntUsuario other = (EntUsuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipn.cic.sistmhospital.modelo.EntUsuario[ idUsuario=" + idUsuario + " ]";
    }

}
