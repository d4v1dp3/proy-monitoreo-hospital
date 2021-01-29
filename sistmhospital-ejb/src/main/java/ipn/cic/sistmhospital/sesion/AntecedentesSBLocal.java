/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.AntecedentesException;
import ipn.cic.sistmhospital.modelo.EntAntecedentes;
import javax.ejb.Local;

/**
 *
 * @author J.PEREZ
 */
@Local
public interface AntecedentesSBLocal {
    EntAntecedentes guardaAntecedentes(EntAntecedentes antecedentes) throws AntecedentesException;
}
