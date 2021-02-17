/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.exception;

/**
 *
 * @author marcos
 */
public class PacienteMedicoException extends Exception{
    public PacienteMedicoException(String msg) {
        super(msg);
    }
    
    public PacienteMedicoException(String msg, Throwable anid) {
        super(msg,anid);
    }
}
