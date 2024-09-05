/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.TipoNotificacionIfip;
import ec.renafipse.mks.modelo.creditos.TipoRubro;
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
public class TipoNotificacionIfipFacade extends AbstractFacade<TipoNotificacionIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoNotificacionIfipFacade() {
        super(TipoNotificacionIfip.class);
    }

    public List<TipoNotificacionIfip> getItemsTipoNotificacionIfipEliminado(Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(TipoNotificacionIfip.findByTipoEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    public TipoNotificacionIfip getTipoNotificacionCodigo(Long codigoTipoNotificacion) {
        Query query = this.em.createNamedQuery(TipoNotificacionIfip.findByCodigoTipoNotificacion);
        query.setParameter("codigoTipoNotificacion", codigoTipoNotificacion);
        return query.getSingleResult() == null ? null : (TipoNotificacionIfip) query.getSingleResult();
    }

}
