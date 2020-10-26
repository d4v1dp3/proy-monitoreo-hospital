/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.web.sistmhospital.delegate;

import ipn.cic.sistmhospital.exception.MedidasException;
import ipn.cic.sistmhospital.exception.PacienteException;
import ipn.cic.sistmhospital.modelo.EntCareta;
import ipn.cic.sistmhospital.modelo.EntPaciente;
import ipn.cic.web.sistmhospital.bean.vo.MedidasVO;
import javax.ejb.Local;
import javax.json.JsonObject;

/**
 *
 * @author iliaco
 */
@Local
public interface MedidasBDLocal {
 
    //definir un metodo que reciba el VO y lo persista lanzar una excepcion si no se puede hacer
    EntPaciente cargarPaciente(long idPaciente);
    JsonObject guardarMedidas(MedidasVO med) throws MedidasException;
    EntCareta cargarCareta(long idCareta);
    
}
