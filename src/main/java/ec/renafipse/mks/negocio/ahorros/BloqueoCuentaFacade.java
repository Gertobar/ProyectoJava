/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.BloqueoCuenta;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
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
public class BloqueoCuentaFacade extends AbstractFacade<BloqueoCuenta> {
   @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BloqueoCuentaFacade() {
        super(BloqueoCuenta.class);
    }
    
    public List<BloqueoCuenta> getItemsCuentaVigente(Cuenta cuenta, char vigente)
    {
        Query query = this.em.createNamedQuery(BloqueoCuenta.findByCuentaVigente);
        query.setParameter("cuenta", cuenta);
        query.setParameter("vigente", vigente);
        return query.getResultList();
    }
    
    /**
     * 
     * @param cuenta
     * @param vigente
     * @param valor
     * @return 
     */
    public List<BloqueoCuenta> getItemsCuentaVigenteMayorAUnValor(Cuenta cuenta, char vigente, BigDecimal valor) {
        Query query = this.em.createNamedQuery("BloqueoCuenta.findByCuentaVigenteMayorAUnValor", BloqueoCuenta.class);
        query.setParameter("cuenta", cuenta);
        query.setParameter("vigente", vigente);
        query.setParameter("valor", valor);
        return query.getResultList();
    }
}
