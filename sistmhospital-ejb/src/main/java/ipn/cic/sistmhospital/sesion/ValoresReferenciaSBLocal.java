/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.NoExisteValoresRefException;
import ipn.cic.sistmhospital.exception.UpdateEntityException;
import ipn.cic.sistmhospital.exception.ValoresReferenciaException;
import ipn.cic.sistmhospital.modelo.EntValoresReferencia;
import javax.ejb.Local;

/**
 *
 * @author J.PEREZ
 */
@Local
public interface ValoresReferenciaSBLocal {
    Boolean existenVref() throws ValoresReferenciaException;
    EntValoresReferencia guardaValoresReferencia(EntValoresReferencia vref) throws ValoresReferenciaException;
    EntValoresReferencia getValoresReferencia() throws ValoresReferenciaException;
    EntValoresReferencia updateValoresReferencia(EntValoresReferencia vref) throws UpdateEntityException;
    EntValoresReferencia getValoresReferenciaId(Short idValRef) throws NoExisteValoresRefException;
}
