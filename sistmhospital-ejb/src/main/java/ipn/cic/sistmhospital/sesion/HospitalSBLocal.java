/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.HospitalException;
import ipn.cic.sistmhospital.exception.NoExisteHospitalException;
import ipn.cic.sistmhospital.exception.UpdateEntityException;
import ipn.cic.sistmhospital.modelo.EntHospital;
import javax.ejb.Local;

/**
 *
 * @author Iliac Huerta Trujillo <ihuertat@ipn.mx>
 */
@Local
public interface HospitalSBLocal {
    Boolean existeHospital()throws HospitalException;
    EntHospital guardaHospital(EntHospital hosp) throws HospitalException;
    EntHospital getPrimerHospital() throws NoExisteHospitalException;
    
    
    EntHospital updateHospital(EntHospital hosp) throws UpdateEntityException;//*
       
    
    
}
