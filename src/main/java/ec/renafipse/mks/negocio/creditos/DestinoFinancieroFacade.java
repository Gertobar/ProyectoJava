/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.DestinoFinanciero;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class DestinoFinancieroFacade extends AbstractFacade<DestinoFinanciero> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DestinoFinancieroFacade() {
        super(DestinoFinanciero.class);
    }

    public List<DestinoFinanciero> getItemsEliminado(char eliminado) {
        return this.em.createNamedQuery(DestinoFinanciero.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
}
