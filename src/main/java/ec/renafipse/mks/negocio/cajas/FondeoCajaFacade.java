/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.modelo.cajas.FondeoCaja;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDesgloceEfectivo;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDesgloceEfectivoPK;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDetalle;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.UserTransaction;

/**
 *
 * @author vicastoc
 */
@Stateless
public class FondeoCajaFacade extends AbstractFacade<FondeoCaja> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FondeoCajaFacade() {
        super(FondeoCaja.class);
    }
    
    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    
    /**
     * Obtiene los fondeos de una fecha asignados a un computador (CAJA)
     * @param fechaFondeo Fecha de Fondeo
     * @param codigoComputadorCaja Codigo del Computador de la Caja fondeada
     * @return Lista de Fondeos
     */
    public List<FondeoCaja> getItemsFechaFondeoComputador(Date fechaFondeo, Long codigoComputadorCaja )
    {        
        Query query = this.em.createNamedQuery(FondeoCaja.findByFechaFondeoComputador);
        query.setParameter("fechaFondeo", fechaFondeo, TemporalType.DATE);
        query.setParameter("codigoComputadorCaja", codigoComputadorCaja);
        
        return query.getResultList();
    }
    
    /**
     * Obtiene los fondeos de una fecha 
     * @param fechaFondeo Fecha de Fondeo
     * @return Lista de Fondeos
     */
    public List<FondeoCaja> getItemsFechaFondeo(Date fechaFondeo)
    {        
        Query query = this.em.createNamedQuery(FondeoCaja.findByFechaFondeo);
        query.setParameter("fechaFondeo", fechaFondeo, TemporalType.DATE);                
        return query.getResultList();
    }
    
    /**
     * Obtiene los fondeos vigentes de un Cajero
     * @param codigoUsuarioCaja
     * @return Lista de Fondeos
     */
    public List<FondeoCaja> getItemsFondeoCajeroVigente(Long codigoUsuarioCaja )
    {        
        Query query = this.em.createNamedQuery(FondeoCaja.findByFondeoCajeroVigente);
        query.setParameter("codigoUsuarioCaja", codigoUsuarioCaja);        
        return query.getResultList();
    }
    
    /**
     * Obtiene los fondeos vigentes de una caja.
     * @param codigoComputadorCaja Codigo del Computador.
     * @return Lista de Fondeos
     */
    public List<FondeoCaja> getItemsFondeoComputadorVigente(Long codigoComputadorCaja )
    {        
        Query query = this.em.createNamedQuery(FondeoCaja.findByFondeoComputadorVigente);
        query.setParameter("codigoComputadorCaja", codigoComputadorCaja);        
        return query.getResultList();
    }
    
     /**
     * Obtiene los fondeos de un rango de fechas
     * @param fechaInicioFondeo Fecha de Inicio de Fondeo
     * @param fechaFinFondeo Fecha Fin de Fondeo
     * @param codigoIfipAgencia Codigo de la Ifip de la Agencia
     * @return Lista de Fondeos
     */
    public List<FondeoCaja> getItemsFechasFondeo(Date fechaInicioFondeo, Date fechaFinFondeo, Long codigoIfipAgencia)
    {        
        Query query = this.em.createNamedQuery(FondeoCaja.findByFechasFondeo);
        query.setParameter("fechaInicioFondeo", fechaInicioFondeo, TemporalType.DATE);                
        query.setParameter("fechaFinFondeo", fechaFinFondeo, TemporalType.DATE);                
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();
    }
    
    
       /**
     * Obtiene los fondeos de un rango de fechas de acuerdo al estado
     * @param fechaInicioFondeo Fecha de Inicio de Fondeo
     * @param fechaFinFondeo Fecha Fin de Fondeo
     * @param estado Estado del Fondeo
     * @param codigoIfipAgencia Codigo de la Ifip de la Agencia
     * @return Lista de Fondeos
     */
    public List<FondeoCaja> getItemsFechasFondeoEstado(Date fechaInicioFondeo, Date fechaFinFondeo, char estado, Long codigoIfipAgencia)
    {        
        Query query = this.em.createNamedQuery(FondeoCaja.findByFechasFondeoEstado);
        query.setParameter("fechaInicioFondeo", fechaInicioFondeo, TemporalType.DATE);                
        query.setParameter("fechaFinFondeo", fechaFinFondeo, TemporalType.DATE);                
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    
      /**
     * Obtiene los fondeos de acuerdo al estado
     * @param estado Estado del Fondeo
     * @param codigoIfipAgencia Codigo de la Ifip de la Agencia
     * @return Lista de Fondeos
     */
    public List<FondeoCaja> getItemsEstado(char estado, Long codigoIfipAgencia)
    {        
        Query query = this.em.createNamedQuery(FondeoCaja.findByEstado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("estado", estado);
        return query.getResultList();
    }

     /**
     * Obtiene los fondeo para la apertura de caja
     * @param fechaFondeo Fecha de Fondeo
     * @param codigoComputadorCaja Computador de Caja
     * @param codigoUsuarioCaja Usuario de Caja
     * @param estado Estado del Fondeo
     * @param codigoIfipAgencia Codigo de la Ifip de la Agencia
     * @return Lista de Fondeos.
     */
    public List<FondeoCaja> getItemsFondeoAperturaCaja(Date fechaFondeo, Long codigoComputadorCaja, Long codigoUsuarioCaja, char estado, Long codigoIfipAgencia)
    {        
        Query query = this.em.createNamedQuery(FondeoCaja.findByFondeoAperturaCaja);
       // query.setParameter("fechaFondeo", fechaFondeo, TemporalType.DATE);
        query.setParameter("codigoComputadorCaja", codigoComputadorCaja);
        query.setParameter("codigoUsuarioCaja", codigoUsuarioCaja);
        query.setParameter("estado", estado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();
    }
  

}
