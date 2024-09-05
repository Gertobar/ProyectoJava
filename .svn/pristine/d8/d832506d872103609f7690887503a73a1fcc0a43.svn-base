/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.AperturaDetalle;
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
public class AperturaDetalleFacade extends AbstractFacade<AperturaDetalle> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AperturaDetalleFacade() {
        super(AperturaDetalle.class);
    }
    
    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    
    /**
     * Obtiene el Detalle de las Aperturas por el Codigo de la Apertura
     * @param codigoApertura  Codigo de la Apertura
     * @return Lista de Fondeos
     */
    public List<AperturaDetalle> getItemsCodigoApertura(Long codigoApertura )
    {        
        Query query = this.em.createNamedQuery(AperturaDetalle.findByCodigoApertura);
        query.setParameter("codigoApertura", codigoApertura);
        
        return query.getResultList();
    }
    
}
