/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.CuentasCuadreFinanciero;
import ec.renafipse.mks.modelo.contables.PlanDeCuenta;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author renafipse1
 */
@Stateless
public class CuentasCuadreFinancieroFacade extends AbstractFacade<CuentasCuadreFinanciero> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentasCuadreFinancieroFacade() {
        super(CuentasCuadreFinanciero.class);
    }
    
    public List<CuentasCuadreFinanciero> getItemsPlanCuentaTipoCuadre(long codigoTipoCuadre) {
        Query query = em.createNamedQuery(CuentasCuadreFinanciero.findByCodigoTipoCuadre);
        query.setParameter("codigoTipoCuadre", codigoTipoCuadre);
        return query.getResultList();
    }
}
