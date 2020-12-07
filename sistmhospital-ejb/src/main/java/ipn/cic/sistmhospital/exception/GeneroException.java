/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.exception;

/**
 *
 * @author Iliac Huerta Trujillo
 */
public class GeneroException extends Exception {

    /**
     * Constructs an instance of <code>GeneroException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public GeneroException(String msg) {
        super(msg);
    }
    
    public GeneroException(String msg, Throwable anid) {
        super(msg, anid);
    }
}
