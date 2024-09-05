/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.Retencion;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class RetencionFacade extends AbstractFacade<Retencion> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RetencionFacade() {
        super(Retencion.class);
    }

    //sirve para verificar si existe sila 
    public List<Retencion> getItemsRetencionesfindByCodigoCompra(Long codigoCompra) {
        Query query = em.createNamedQuery(Retencion.findByCodigoCompra);
        query.setParameter("codigoCompra", codigoCompra);
        return query.getResultList();
    }

    public List<Retencion> getItemsRetencionesfindByEstado(char estado) {
        Query query = em.createNamedQuery(Retencion.findByEstado);
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    public List<Retencion> getItemsRetencionesfindByEstadoCodigoCompra(char estado,Long codigoCompra) {
        Query query = em.createNamedQuery(Retencion.findByEstadoCodigoCompra);
        query.setParameter("estado", estado);
        query.setParameter("codigoCompra", codigoCompra);
        return query.getResultList();
    }
    //SELECT r FROM Retencion r WHERE r.codigoIfip = :codigoIfip AND r.codigoIfipAgencia = 
    //:codigoIfipAgencia AND  r.fechaRetencion >= :fechaInicio AND r.fechaRetencion <= :fechaFin"   
    ///findByIfipIAgenciaFechaIngreso
        public List<Retencion> getItemsRetencionesfindByIfipIAgenciaFechaIngreso(Long codigoIfip, Long codigoIfipAgencia, Date fechaInicio, Date fechaFin ) {
        Query query = em.createNamedQuery(Retencion.findByIfipIAgenciaFechaIngreso);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
          
        return query.getResultList();
    }
        //
        
      public List<Retencion> getItemsRetencionesfindByIfipIAgenciaPro(Long codigoIfip, Long codigoIfipAgencia, Long codigoProveedor) {
        Query query = em.createNamedQuery(Retencion.findByIfipIAgenciaPro);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("codigoProveedor", codigoProveedor);

          
        return query.getResultList();
    }
}
