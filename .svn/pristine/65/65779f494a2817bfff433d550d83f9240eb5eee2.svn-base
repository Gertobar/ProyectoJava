/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.AperturaCajaFondeo;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class AperturaCajaFondeoFacade extends AbstractFacade<AperturaCajaFondeo> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AperturaCajaFondeoFacade() {
        super(AperturaCajaFondeo.class);
    }
    
    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    
    /**
     * Obtiene los fondeos de una apertura de caja
     * @param codigoApertura Codigo de la Apertura
     * @return Lista de Fondeos
     */
    public List<AperturaCajaFondeo> getItemsCodigoApertura(Long codigoApertura)
    {        
        Query query = this.em.createNamedQuery(AperturaCajaFondeo.findByCodigoApertura);
        query.setParameter("codigoApertura", codigoApertura);
        
        return query.getResultList();
    }
}
