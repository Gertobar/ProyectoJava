/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.Boveda;
import ec.renafipse.mks.modelo.ifips.BovedaDinero;
import ec.renafipse.mks.modelo.seguridades.UsuarioIfipAgencia;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Viajes2
 */
@Stateless
public class BovedaDineroFacade extends AbstractFacade<BovedaDinero> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BovedaDineroFacade() {
        super(BovedaDinero.class);
    }
    
     // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Metodo que obtiene las bovedas de la agencia, y que tiene responsable al usuario
     * para la moneda y para el monto superior.
     * @param codigoIfipAgencia Codigo de la Ifip de la Agencia
     * @param responsable COdigo del Usuario Responsable de la Boveda
     * @param codigoMoneda Codigo de la Moneda
     * @param totalDinero Total del Dinero que debe superar la boveda
     * @param eliminado
     * @return  Lista de Bovedas
     */
    public List<Boveda> getItemsBovedaMonedaFondeoCaja(Long codigoIfipAgencia, Long responsable, Long codigoMoneda, BigDecimal totalDinero, char eliminado) {
        Query query = this.em.createNamedQuery(BovedaDinero.findByBovedaMonedaFondeoCaja);        
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("responsable", responsable);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoMoneda", codigoMoneda);        
        query.setParameter("totalDinero", totalDinero);      
        return query.getResultList();
    }
    
    
    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Metodo que obtiene las bovedas de la agencia y de la moneda, y que tiene responsable al usuario
     * @param codigoIfipAgencia Codigo de la Ifip de la Agencia
     * @param responsable COdigo del Usuario Responsable de la Boveda
     * @param codigoMoneda Codigo de la Moneda
     * @param eliminado
     * @return  Lista de Bovedas
     */
    public List<Boveda> getItemsBovedaMonedaMovimientoBoveda(Long codigoIfipAgencia, Long responsable, Long codigoMoneda, char eliminado) {
        Query query = this.em.createNamedQuery(BovedaDinero.findByBovedaMonedaMovimientoBoveda);        
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("responsable", responsable);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoMoneda", codigoMoneda);   
        return query.getResultList();
    }
}
