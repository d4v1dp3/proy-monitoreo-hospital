/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipn.cic.web.sistmhospital.bean.comun;

import ipn.cic.sistmhospital.modelo.EntMedico;
import ipn.cic.sistmhospital.modelo.EntUsuario;
import ipn.cic.sistmhospital.sesion.MedicoSBLocal;
import ipn.cic.sistmhospital.sesion.UsuarioSBLocal;
import ipn.cic.sistmhospital.util.correo.CorreoSBLocal;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.mail.Session;
import org.primefaces.PrimeFaces;

/**
 *
 * @author marcos
 */
@Named(value="recoverPasswordMB")
@ViewScoped
public class RecoverPasswordMB implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(RecoverPasswordMB.class.getName());
    
    private String email;
    
    @EJB
    private MedicoSBLocal medicoSB;
    
    @EJB
    private CorreoSBLocal correoSB;
    
    @EJB
    private UsuarioSBLocal usuarioSB;
         
    @Resource(name = "java:jboss/mail/sisMHospitalMail")
    private Session mailSesion;
    
    private EntMedico medico;
    private EntUsuario usuario;
    
    @PostConstruct
    public void RecoverPasswordMB(){
    }
    
    public void recoverPassword(){
        medico =  medicoSB.getMedico(email);
        if(medico!=null){
            usuario = usuarioSB.getUsuario(medico);
            if(usuario!=null){
                logger.log(Level.INFO,"Correo encontrado");
                correoSB.enviarCorreo(mailSesion,medico.getEmail(),"Recuperación contraseña",usuario.getContrasenia());
                enviado();
            }else{
                logger.log(Level.SEVERE,"Correo no encontrado");
                error();
            } 
        }else{
            logger.log(Level.SEVERE,"Correo no encontrado");
            error();
        }
                
    }

    public void enviado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Correo enviado con la contraseña."));
    }
    
    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encontró el correo."));
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
    
}
