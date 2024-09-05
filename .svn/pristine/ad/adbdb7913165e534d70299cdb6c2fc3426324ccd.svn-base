/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.TipoComprobante;
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
public class TipoComprobanteFacade extends AbstractFacade<TipoComprobante> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoComprobanteFacade() {
        super(TipoComprobante.class);
    }
    
    public List<TipoComprobante> getItemsTipoComprobanteEliminado( char eliminado, char esParaComprobante) {
        Query query = em.createNamedQuery(TipoComprobante.findByEliminadoParaComprobante);
        query.setParameter("eliminado", eliminado);
        query.setParameter("esParaComprobante", esParaComprobante);
        return query.getResultList();
    }
    
   public List<TipoComprobante> getItemsTipoComprobanteEliminado(Long codigo, char eliminado) {
        Query query = em.createNamedQuery(TipoComprobante.findByCodigoEstadoEliminado);
        query.setParameter("codigo", codigo);        
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
}
