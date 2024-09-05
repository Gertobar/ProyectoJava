/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.CuentaContableEntFin;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class CuentaContableEntFinFacade extends AbstractFacade<CuentaContableEntFin> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaContableEntFinFacade() {
        super(CuentaContableEntFin.class);
    }

    public List<CuentaContableEntFin> findByCodigoEliminado(Long codigoEntidadFinanciera, char estado) {
        Query query = em.createNamedQuery(CuentaContableEntFin.findByCodigoEliminado);
        query.setParameter("codigoEntidadFinanciera", codigoEntidadFinanciera);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
}
