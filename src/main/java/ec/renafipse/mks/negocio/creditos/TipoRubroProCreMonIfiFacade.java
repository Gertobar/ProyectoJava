/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.TipoRubroProCreMonIfi;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class TipoRubroProCreMonIfiFacade extends AbstractFacade<TipoRubroProCreMonIfi> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoRubroProCreMonIfiFacade() {
        super(TipoRubroProCreMonIfi.class);
    }
    public List<TipoRubroProCreMonIfi> getItemsTipoRubroProCreMonIfi(Long codigoTipoRubro,Long codigoProducto, Long codigoMoneda, Long codigoIfip) {
        Query query = this.em.createNamedQuery(TipoRubroProCreMonIfi.findByTipoRubroProCreMonIfi);
        query.setParameter("codigoTipoRubro", codigoTipoRubro);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        try {
            List<TipoRubroProCreMonIfi> tasaInteresProCreMonIfi = query.getResultList();
            return tasaInteresProCreMonIfi == null ? null : tasaInteresProCreMonIfi;
        } catch (NoResultException e) {
            return null;
        }
    }
    public TipoRubroProCreMonIfi getTipoRubroProCreMonIfi(Long codigoTipoRubro,Long codigoProducto, Long codigoMoneda, Long codigoIfip) {
        Query query = this.em.createNamedQuery(TipoRubroProCreMonIfi.findByTipoRubroProCreMonIfi);
        query.setParameter("codigoTipoRubro", codigoTipoRubro);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        try {
            TipoRubroProCreMonIfi tasaInteresProCreMonIfi =(TipoRubroProCreMonIfi) query.getSingleResult();
            return tasaInteresProCreMonIfi == null ? null : tasaInteresProCreMonIfi;
        } catch (NoResultException e) {
            return null;
        }
    }
    public TipoRubroProCreMonIfi getItemsTipoRubroProCreMonIfiOrden(Long codigoTipoRubro,Long codigoProducto, Long codigoMoneda, Long codigoIfip, short ordenCobro) {
        Query query = this.em.createNamedQuery(TipoRubroProCreMonIfi.findByTipoRubroProCreMonIfiOrden);
        query.setParameter("codigoTipoRubro", codigoTipoRubro);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("ordenCobro", ordenCobro);
        try {
            TipoRubroProCreMonIfi tasaInteresProCreMonIfi = (TipoRubroProCreMonIfi)query.getSingleResult();
            return tasaInteresProCreMonIfi == null ? null : tasaInteresProCreMonIfi;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<TipoRubroProCreMonIfi> getItemsProCreMonIfi(Long codigoProducto, Long codigoMoneda, Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(TipoRubroProCreMonIfi.findByProCreMonIfi);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
        
    }
}
