/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.exception;

/**
 *
 * @author J.PEREZ
 */
public class ValoresReferenciaException extends Exception {

    /**
     * Creates a new instance of <code>ValoresReferenciaException</code> without
     * detail message.
     */
    public ValoresReferenciaException() {
    }

    /**
     * Constructs an instance of <code>ValoresReferenciaException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ValoresReferenciaException(String msg) {
        super(msg);
    }
    
    public ValoresReferenciaException(String msg, Throwable anid) {
        super(msg, anid);
    }
}
