/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.SeguimientoEstadoSolicitud;
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
public class SeguimientoEstadoSolicitudFacade extends AbstractFacade<SeguimientoEstadoSolicitud> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeguimientoEstadoSolicitudFacade() {
        super(SeguimientoEstadoSolicitud.class);
    }

    public List<SeguimientoEstadoSolicitud> getItemsSeguimientoSolicitudIfipEstado(Long numeroSolicitud, Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(SeguimientoEstadoSolicitud.findBySolicitudIfipEstado);
        query.setParameter("numeroSolicitud", numeroSolicitud);
        query.setParameter("codigoIfip", codigoIfip);
         query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

}
