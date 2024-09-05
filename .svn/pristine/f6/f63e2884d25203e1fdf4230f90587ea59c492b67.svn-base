/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.DestinoFinProCreMonIfip;
import ec.renafipse.mks.modelo.creditos.DestinoFinanciero;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.ArrayList;
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
public class DestinoFinProCreMonIfipFacade extends AbstractFacade<DestinoFinProCreMonIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DestinoFinProCreMonIfipFacade() {
        super(DestinoFinProCreMonIfip.class);
    }

    // Obtener las Actividades Economicas atadas al producto de credito por credito
    public List<DestinoFinanciero> getItemsDestinoFinancieroPorProducto(long codigoProducto, long codigoMoneda, long codigoIfip)
    {
        Query query = em.createNamedQuery(DestinoFinProCreMonIfip.findByProductoCredito);
        List<DestinoFinanciero> listadoAct = new ArrayList<DestinoFinanciero>();
        query.setParameter("codigoProducto",codigoProducto);
        query.setParameter("codigoMoneda",codigoMoneda);
        query.setParameter("codigoIfip",codigoIfip);
        List<DestinoFinProCreMonIfip> listado = query.getResultList();
        for( DestinoFinProCreMonIfip item : listado ){
            listadoAct.add(item.getDestinoFinanciero());
        }
        return listadoAct;
    }
}

