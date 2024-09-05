/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.FormaPago;
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
public class FormaPagoFacade extends AbstractFacade<FormaPago> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormaPagoFacade() {
        super(FormaPago.class);
    }
    
    public List<FormaPago> getItemsComprobantesComprafindByCodigoCompra(Long codigoCompra, char eliminado) {
        Query query = em.createNamedQuery(FormaPago.findByCodigoEliminado);
        query.setParameter("codigo", codigoCompra);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
}
