/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.FondeoCaja;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDetalle;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author vicastoc
 */
@Stateless
public class FondeoCajaDetalleFacade extends AbstractFacade<FondeoCajaDetalle> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FondeoCajaDetalleFacade() {
        super(FondeoCajaDetalle.class);
    }
    
     // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    
    /**
     * Obtiene los detalle de fondeos de acuerdo al codigo del fondeo.
     * @param codigoFondeo Codigo del Fondeo
     * @return Lista de Detalle de Fondeos
     */
    public List<FondeoCajaDetalle> getItemsCodigoFondeo(Long codigoFondeo )
    {        
        Query query = this.em.createNamedQuery(FondeoCajaDetalle.findByCodigoFondeo);
        query.setParameter("codigoFondeo", codigoFondeo);
        
        return query.getResultList();
    }
}
