/*
 * Instituto Politécnico Nacional
 * Centro de Investigación en Computación (CIC-IPN)
 * Laboratorio de Robótica y Mecatrónica
 * Todos los derechos reservados
 */
package ipn.cic.sistmhospital.sesion;

import ipn.cic.sistmhospital.exception.AntecedentesException;
import ipn.cic.sistmhospital.exception.SaveEntityException;
import ipn.cic.sistmhospital.modelo.EntAntecedentes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author J.PEREZ
 */
@Stateless
public class AntecedentesSB extends BaseSB implements AntecedentesSBLocal {
    private static final Logger logger = Logger.getLogger(AntecedentesSB.class.getName());
    
    @Override
    public EntAntecedentes guardaAntecedentes(EntAntecedentes antecedentes) throws AntecedentesException{
        try{
            return (EntAntecedentes)saveEntity(antecedentes);
        }catch(SaveEntityException ex){
            logger.log(Level.SEVERE,"Error al intentar salvar entidad : {0}", ex.getMessage());
            throw new AntecedentesException("Error al salvar entidad en AntecedentesSB",ex);
        }
    }

}
