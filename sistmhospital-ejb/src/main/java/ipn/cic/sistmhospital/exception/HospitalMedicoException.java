/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.exception;

/**
 *
 * @author J.Perez
 */
public class HospitalMedicoException extends Exception {

    /**
     * Constructs an instance of <code>CatalogosException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public HospitalMedicoException(String msg) {
        super(msg);
    }
    
    public HospitalMedicoException(String msg, Throwable anid) {
        super(msg, anid);
    }
}
