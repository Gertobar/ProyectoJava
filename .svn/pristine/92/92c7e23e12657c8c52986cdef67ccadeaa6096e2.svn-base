/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.negocio.AbstractFacade;
import ec.renafipse.mks.modelo.creditos.DiasEnvioNotificacionIfip;
import ec.renafipse.mks.modelo.creditos.TipoNotificacionIfip;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author santiago
 */
@Stateless
public class DiasEnvioNotificacionIfipFacade extends AbstractFacade<DiasEnvioNotificacionIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiasEnvioNotificacionIfipFacade() {
        super(DiasEnvioNotificacionIfip.class);
    }

    public List<TipoNotificacionIfip> getItemsDiasTipoNotificacionIfip(long diasMora, Long codigoIfip, char eliminado, long numeroCredito, long cuota, char anulada) {
        Query query = this.em.createNamedQuery(DiasEnvioNotificacionIfip.findByDiasTipoNotificacionIfip);
        query.setParameter("diasMora", diasMora);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("cuota", cuota);
        query.setParameter("anulada", anulada);
        return query.getResultList();
    }

}
