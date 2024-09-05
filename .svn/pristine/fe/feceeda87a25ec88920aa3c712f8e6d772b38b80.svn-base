/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.GrupoCuentaContable;
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
public class GrupoCuentaContableFacade extends AbstractFacade<GrupoCuentaContable> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoCuentaContableFacade() {
        super(GrupoCuentaContable.class);
    }

    public List<GrupoCuentaContable> getItemsGrupoPlanCuentaEliminado(char eliminado) {
        Query query = em.createNamedQuery(GrupoCuentaContable.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

}
