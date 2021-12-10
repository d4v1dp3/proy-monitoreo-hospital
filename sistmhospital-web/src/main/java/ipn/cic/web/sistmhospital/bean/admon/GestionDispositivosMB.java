/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.bean.admon;

import ipn.cic.sistmhospital.exception.CaretaException;
import ipn.cic.sistmhospital.exception.CaretaHospitalException;
import ipn.cic.sistmhospital.exception.CatalogoException;
import ipn.cic.sistmhospital.exception.NoExisteCaretaException;
import ipn.cic.sistmhospital.exception.NoExisteHospitalException;
import ipn.cic.sistmhospital.exception.RemoveEntityException;
import ipn.cic.sistmhospital.exception.UpdateEntityException;
import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntCaretaHospital;
import ipn.cic.sistmhospital.modelo.EntCaretaHospitalPK;
import ipn.cic.sistmhospital.modelo.EntEstadocareta;
import ipn.cic.sistmhospital.modelo.EntHospital;
import ipn.cic.sistmhospital.sesion.CaretaHospitalSBLocal;
import ipn.cic.sistmhospital.sesion.CaretaSBLocal;
import ipn.cic.sistmhospital.sesion.CatalogoSBLocal;
import ipn.cic.sistmhospital.sesion.HospitalSBLocal;
import ipn.cic.sistmhospital.util.Constantes;
import ipn.cic.web.sistmhospital.util.Mensaje;
import ipn.cic.web.sistmhospital.util.UtilWebSBLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 * ManageBean que se utiliza para carga de usuarios en el sistema
 *
 * @author Iliac Huerta Trujillo <ihuertat@ipn.mx>
 */
@Named(value = "gestionDispositivosMB")
@ViewScoped
public class GestionDispositivosMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GestionDispositivosMB.class.getName());

    @EJB
    private CaretaSBLocal caretaSB;
    @EJB
    private CaretaHospitalSBLocal caretahospitalSB;
    @EJB
    private HospitalSBLocal hospitalSB;
    @EJB
    private UtilWebSBLocal utilWebSB;
    @EJB
    CatalogoSBLocal catalogoSB;

    private List<EntCaretaHospital> caretashospitalAsignadas;
    private List<EntCaretaHospital> caretashospitalAveriadas;
    private List<EntCaretaHospital> caretashospitalDisponibles;

    private EntCaretaHospital caretaHospitalEditar;
    private EntCaretaHospital caretaHospitalEliminar;

    private EntCareta caretaEditar;
    private EntCareta caretaEliminar;
    private EntCareta caretaGuard;
    private List<EntHospital> listHospital;
    private List<EntEstadocareta> listaEstadoscareta;

    private EntCaretaHospital caretaHospital;
    private EntHospital entHospital;
    private EntEstadocareta estadocareta;

    private String fechaManufactura = "";
    private long idCareta = 0;
    private long noSerie = 0;
    private Integer idHosp = 0;
    private Integer idestadocareta=0;

    @PostConstruct
    public void cargarDispositivos() {
        FacesMessage msg = null;
        

        logger.log(Level.INFO, "Entra a gestion dispositivos.");
        
        caretaGuard = new EntCareta();
        caretaEditar = new EntCareta();
        caretaEliminar = new EntCareta();
        listHospital = new ArrayList();
        caretaHospital = new EntCaretaHospital();

        try {
            //Cargar Lista de Hospitales
            setListHospital((List<EntHospital>) catalogoSB.getCatalogo("EntHospital"));
        } catch (CatalogoException ex) {
            logger.log(Level.SEVERE, "Error al recuperar datos de hopital.");
            
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error Dispositivos",
                            "Error al intentar recuperar catálogo de dispositivos, intentelo más tarde.",
                            FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
            PrimeFaces.current().ajax().update("frGestDispositivos:msgsGD");
        }
        
        try {
            caretashospitalAsignadas = caretahospitalSB.getCaretasAsignadas();
        } catch (CaretaHospitalException ex) {
            logger.log(Level.SEVERE, "Error al recuperar caretas asignadas.");
            caretashospitalAsignadas = new ArrayList();
            
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error Dispositivos",
                            "Error al intentar recuperar dispositivos, intentelo mas tarde.",
                            FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
            PrimeFaces.current().ajax().update("frGestDispositivos:msgsGD");
        }
        
        try {
            caretashospitalDisponibles = caretahospitalSB.getCaretasDisponibles();
        } catch (CaretaHospitalException ex) {
            logger.log(Level.SEVERE, "Error al recuperar caretas sin asignar.: {0}", ex.getMessage());
            caretashospitalDisponibles = new ArrayList();
            
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error Dispositivos:",
                            "Error al intentar recuperar dispositivos no asignados, intentelo mas tarde.",
                            FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
            PrimeFaces.current().ajax().update("frGestDispositivos:msgsGD");
            
        }
        
        try {
            caretashospitalAveriadas = caretahospitalSB.getCaretasAveriadas();
        } catch (CaretaHospitalException ex) {
            logger.log(Level.SEVERE, "Error al recuperar caretas averiadas.: {0}", ex.getMessage());
            caretashospitalAveriadas = new ArrayList();
            
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error Dispositivos:",
                            "Error al intentar recuperar dispositivos en desuso, intentelo mas tarde.",
                            FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
            PrimeFaces.current().ajax().update("frGestDispositivos:msgsGD");
            
        }
        
         try {
            listaEstadoscareta = catalogoSB.getCatalogo("EntEstadocareta");
            
            //Remover el estado ASIGNADO de las opciones
            listaEstadoscareta.remove(1);
            
        } catch (CatalogoException ex) {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error",
                            "Error al recuperar estados de dispositivos",
                            FacesMessage.SEVERITY_INFO);
            logger.log(Level.SEVERE, "Error al recuperar estados de dispositivo.");
        }

        if (msg == null) {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Mensaje:",
                            "Dispositivos cargados correctamente",
                            FacesMessage.SEVERITY_INFO);
            utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
//            PrimeFaces.current().ajax().update("frGestDispositivos:msgsGD");
        }
        
        logger.log(Level.INFO, "\tDatos cargados correctamente.");
    }

    public void guardarDispositivo() {
        FacesMessage msg = null;

        logger.log(Level.INFO, "Entrando a Guardar Dispositivo.");

        caretaGuard.setFechaManufactura(fechaManufactura);
        caretaGuard.setNoSerie(noSerie);
        
        //Asignar estado DISPONIBLE
        short idestado =(short)Constantes.getInstance().getInt("EDO_CARETA_DISPONIBLE");
        EntEstadocareta estado = new EntEstadocareta(idestado, "DISPONIBLE");
        caretaGuard.setIdEstadoCareta(estado);

        //Obtener fecha:
        Calendar fecha = new GregorianCalendar();

        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        //Guradar Careta en BD
        try {
            logger.log(Level.INFO, "Guardando careta...");
            caretaGuard = caretaSB.guardaCareta(caretaGuard);
            logger.log(Level.INFO, "Asignando hospital a careta...");
            caretaHospital.setEntCareta(caretaGuard);
            entHospital = hospitalSB.getHospital(idHosp);
            caretaHospital.setEntHospital(entHospital);
            caretaHospital.setFechaAsignacion(anio + "-" + mes + "-" + dia);

            EntCaretaHospitalPK ech = new EntCaretaHospitalPK();
            ech.setIdCareta(caretaGuard.getIdCareta());
            ech.setIdHospital(entHospital.getIdHospital());

            caretaHospital.setEntCaretaHospitalPK(ech);

            logger.log(Level.INFO, "Fecha de asignacion: {0}", caretaHospital.getFechaAsignacion());
            logger.log(Level.INFO, "Guardando relacion careta hospital...{0}", caretaHospital.getFechaAsignacion());
            caretaHospital = caretahospitalSB.guardaCaretaHospital(caretaHospital);

            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Dispositivo Registrado ",
                            "Dispositivo registrado correctamente. No Serie: "+caretaHospital.getEntCareta().getNoSerie(),
                            FacesMessage.SEVERITY_INFO);

            cerrarDialogo(msg);

        } catch (CaretaException ex) {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error",
                            "Error al intentar guardar dispositivo: " + ex.getMessage(),
                            FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
            cerrarDialogo(msg);
        } catch (CaretaHospitalException ex) {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error",
                            "Error al intentar guardar caretahospital :" + ex.getMessage(),
                            FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
            cerrarDialogo(msg);
        } catch (NoExisteHospitalException ex) {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error",
                            "Error al intentar recuperar datos de hospital :" + ex.getMessage(),
                            FacesMessage.SEVERITY_ERROR);
            //utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
            utilWebSB.addMsg("frmRegDispositivo:msgRegDisp", msg);
            cerrarDialogo(msg);
        }

    }

    public void guardarCambiosDispositivo() {
        logger.log(Level.INFO, "Entrando a actualizar dispositivo.");
        caretaGuard.setFechaManufactura(fechaManufactura);
        try {
            caretaGuard = caretaSB.guardaCareta(caretaGuard);
        } catch (CaretaException ex) {
            FacesMessage msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error",
                            "Error al intentar guardar dispositivo:" + ex.getMessage(),
                            FacesMessage.SEVERITY_ERROR);
            utilWebSB.addMsg("frmRegDispositivo:msgRegDisp", msg);
            return;
        }
        FacesMessage msg = null;
        if (caretaGuard == null) {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Error",
                            "Imposible guardar datos de dispositivo, intentelo más tarde",
                            FacesMessage.SEVERITY_ERROR);
            cerrarDialogo(msg);
        } else {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Dispositivo Registrado",
                            "El registro de dispositivo se realizó correctamente: id=" + this.caretaGuard.getIdCareta(),
                            FacesMessage.SEVERITY_INFO);
        }
        utilWebSB.addMsg("frmRegDispositivo:msgRegDisp", msg);
        cerrarDialogo(msg);
    }

    public void cerrarDialogo() {
        cerrarDialogo(null);
    }

    public void cerrarDialogo(FacesMessage mensaje) {
        PrimeFaces.current().dialog().closeDynamic(mensaje);
    }

    public void registrarDispositivo() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 650);
        options.put("height", 580);
        options.put("dynamic", true);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("dispositivos/dialRegistrarDispositivo", options, null);
    }

    public void retornoRegistrarDispositivo(SelectEvent event) {
        FacesMessage msg = null;

        if (event.getObject() != null) {
            
            msg = (FacesMessage) event.getObject();
            cargarDispositivos();
        } else {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Diálogo",
                            "Dialogo cerrado sin aplicar cambios.",
                            FacesMessage.SEVERITY_INFO);
        }
        utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
    }

    public void editarDispositivo() {
       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        idCareta = caretaHospitalEditar.getEntCareta().getIdCareta();
        noSerie = caretaHospitalEditar.getEntCareta().getNoSerie();
        fechaManufactura = sdf.format(caretaHospitalEditar.getEntCareta().getFechaManufactura());
        
        try {
            estadocareta = caretaSB.getEstadoCareta(caretaHospitalEditar.getEntCareta());
        } catch (NoExisteCaretaException ex) {
            logger.log(Level.SEVERE, "Error al recuperar estado del dispositivo.");
        }

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 650);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");

        //Envio de Parametros
        Map<String, List<String>> parametros = new HashMap<>();

        List<String> idcareta = new ArrayList<>();
        idcareta.add(idCareta + "");

        List<String> nSerie = new ArrayList<>();
        nSerie.add(noSerie + "");

        List<String> fManufactura = new ArrayList<>();
        fManufactura.add(fechaManufactura);
        
        List<String> idestadocar = new ArrayList<>();
        idestadocar.add(idestadocareta+"");

        List<String> idHospit = new ArrayList<>();
        idHospit.add(caretaHospitalEditar.getEntHospital().getIdHospital().toString());

        parametros.put("idCareta", idcareta);
        parametros.put("noSerie", nSerie);
        parametros.put("fecManufac", fManufactura);
        parametros.put("idestadocareta", idestadocar);
        parametros.put("idHosp", idHospit);

        logger.log(Level.INFO, "ID Dispositivo Seleccionado: {0}", idCareta);
        PrimeFaces.current().dialog().openDynamic("dispositivos/dialEditarDispositivo", options, parametros);
    }

    public void retornoEditarDispositivo(SelectEvent event) {
        FacesMessage msg = null;

        if (event.getObject() != null) {
            msg = (FacesMessage) event.getObject();
            cargarDispositivos();
        } else {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Diálogo ",
                            "Diálogo cerrado sin aplicar cambios",
                            FacesMessage.SEVERITY_INFO);
        }

        utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
    }

    public void actualizarDispositivo() {
        FacesMessage msg = null;
        try {
            
            if(idestadocareta==1){
                caretaEditar.setIdEstadoCareta(listaEstadoscareta.get(0));
            }else if(idestadocareta==3){
                caretaEditar.setIdEstadoCareta(listaEstadoscareta.get(1));
            } 

            //Actualizar datos de careta
            caretaEditar.setIdCareta(idCareta);
            caretaEditar.setNoSerie(noSerie);
            logger.log(Level.INFO,"Fecha a modificar : {0}",fechaManufactura);
            caretaEditar.setFechaManufactura(fechaManufactura);

            caretaSB.updateCareta(caretaEditar);

            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Diálogo ",
                            "Datos de dispositivo actualizados correctamente.",
                            FacesMessage.SEVERITY_INFO);

            cerrarDialogo(msg);
        } catch (UpdateEntityException ex) {
            Logger.getLogger(GestionDispositivosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void confirmarBaja() {

        idCareta = caretaHospitalEliminar.getEntCareta().getIdCareta();
        noSerie = caretaHospitalEliminar.getEntCareta().getNoSerie();
        logger.log(Level.INFO, "ID Dispositivo Seleccionado: {0}", idCareta);

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 350);
        options.put("height", 150);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");

        //Envio de Parametros
        Map<String, List<String>> parametros = new HashMap<>();

        List<String> idcareta = new ArrayList<>();
        idcareta.add(idCareta + "");

        List<String> nSerie = new ArrayList<>();
        nSerie.add(noSerie + "");

        List<String> fManufactura = new ArrayList<>();
        fManufactura.add(caretaHospitalEliminar.getEntCareta().getFechaManufactura().toString());

        parametros.put("idCareta", idcareta);
        parametros.put("noSerie", nSerie);
        parametros.put("fecManufac", fManufactura);

        PrimeFaces.current().dialog().openDynamic("dispositivos/dialConfirmacion", options, parametros);
    }

    public void eliminarDispositivo() {
        FacesMessage msg = null;

        caretaEliminar.setIdCareta(idCareta);
        caretaEliminar.setNoSerie(noSerie);
        caretaEliminar.setFechaManufactura(fechaManufactura);

        try {
            caretaHospitalEliminar = caretahospitalSB.getCaretaHospital(caretaEliminar);
        } catch (CaretaHospitalException ex) {
            Logger.getLogger(GestionDispositivosMB.class.getName()).log(Level.SEVERE, null, ex);
        }

        logger.log(Level.INFO, "Dispositivo Seleccionado: ID[{0}]",
                caretaEliminar.getIdCareta());
        logger.log(Level.INFO, "Relacion Seleccionada: FA[{0}]",
                caretaHospitalEliminar.getFechaAsignacion());

        try {
            caretahospitalSB.borrarCaretaHospital(caretaHospitalEliminar);
            boolean borrado = caretaSB.borrarCareta(caretaEliminar);
            if (borrado) {
                logger.log(Level.INFO, "Dispositivo Eliminado.");
                msg = Mensaje.getInstance()
                        .getMensajeAdaptado("Dispositivo Eliminado ",
                                "Dispositivo eliminado correctamente.",
                                FacesMessage.SEVERITY_INFO);
            }

            cerrarDialogo(msg);

        } catch (RemoveEntityException ex) {
            Logger.getLogger(GestionDispositivosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void retornoEliminarDispositivo(SelectEvent event) {
        FacesMessage msg = null;

        if (event.getObject() != null) {
            msg = (FacesMessage) event.getObject();
            cargarDispositivos();
        } else {
            msg = Mensaje.getInstance()
                    .getMensajeAdaptado("Diálogo ",
                            "Dialogo cerrado sin aplicar cambios.",
                            FacesMessage.SEVERITY_INFO);
        }
        utilWebSB.addMsg("frGestDispositivos:msgsGD", msg);
    }

    public CaretaSBLocal getCaretaSB() {
        return caretaSB;
    }

    public void setCaretaSB(CaretaSBLocal caretaSB) {
        this.caretaSB = caretaSB;
    }

    public EntCareta getCaretaEditar() {
        return caretaEditar;
    }

    public void setCaretaEditar(EntCareta caretaEditar) {
        this.caretaEditar = caretaEditar;
    }

    public EntCareta getCaretaGuard() {
        return caretaGuard;
    }

    public void setCaretaGuard(EntCareta caretaGuard) {
        this.caretaGuard = caretaGuard;
    }

    public String getFechaManufactura() {
        return fechaManufactura;
    }

    public void setFechaManufactura(String fechaManufantura) {
        this.fechaManufactura = fechaManufantura;
    }

    public EntCareta getCaretaEliminar() {
        return caretaEliminar;
    }

    public void setCaretaEliminar(EntCareta caretaEliminar) {
        this.caretaEliminar = caretaEliminar;
    }

    public long getIdCareta() {
        return idCareta;
    }

    public void setIdCareta(long idCareta) {
        this.idCareta = idCareta;
    }

    public long getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(long noSerie) {
        this.noSerie = noSerie;
    }

    public EntHospital getEntHospital() {
        return entHospital;
    }

    public void setEntHospital(EntHospital entHospital) {
        this.entHospital = entHospital;
    }

    public List<EntHospital> getListHospital() {
        return listHospital;
    }

    public void setListHospital(List<EntHospital> listHospital) {
        this.listHospital = listHospital;
    }

    public Integer getIdHosp() {
        return idHosp;
    }

    public void setIdHosp(Integer idHosp) {
        this.idHosp = idHosp;
    }

    public EntCaretaHospital getCaretaHospital() {
        return caretaHospital;
    }

    public void setCaretaHospital(EntCaretaHospital caretaHospital) {
        this.caretaHospital = caretaHospital;
    }

    public List<EntCaretaHospital> getCaretashospitalAsignadas() {
        return caretashospitalAsignadas;
    }

    public void setCaretashospitalAsignadas(List<EntCaretaHospital> caretashospitalAsignadas) {
        this.caretashospitalAsignadas = caretashospitalAsignadas;
    }

    public EntCaretaHospital getCaretaHospitalEditar() {
        return caretaHospitalEditar;
    }

    public void setCaretaHospitalEditar(EntCaretaHospital caretaHospitalEditar) {
        this.caretaHospitalEditar = caretaHospitalEditar;
    }

    public List<EntCaretaHospital> getCaretashospitalDisponibles() {
        return caretashospitalDisponibles;
    }

    public void setCaretashospitalDisponibles(List<EntCaretaHospital> caretashospitalDisponibles) {
        this.caretashospitalDisponibles = caretashospitalDisponibles;
    }

    public EntCaretaHospital getCaretaHospitalEliminar() {
        return caretaHospitalEliminar;
    }

    public void setCaretaHospitalEliminar(EntCaretaHospital caretaHospitalEliminar) {
        this.caretaHospitalEliminar = caretaHospitalEliminar;
    }

    public List<EntCaretaHospital> getCaretashospitalAveriadas() {
        return caretashospitalAveriadas;
    }

    public void setCaretashospitalAveriadas(List<EntCaretaHospital> caretashospitalAveriadas) {
        this.caretashospitalAveriadas = caretashospitalAveriadas;
    }

    public Integer getIdestadocareta() {
        return idestadocareta;
    }

    public void setIdestadocareta(Integer idestadocareta) {
        this.idestadocareta = idestadocareta;
    }

    public List<EntEstadocareta> getListaEstadoscareta() {
        return listaEstadoscareta;
    }

    public void setListaEstadoscareta(List<EntEstadocareta> listaEstadoscareta) {
        this.listaEstadoscareta = listaEstadoscareta;
    }

    public EntEstadocareta getEstadocareta() {
        return estadocareta;
    }

    public void setEstadocareta(EntEstadocareta estadocareta) {
        this.estadocareta = estadocareta;
    }
    
    

}
