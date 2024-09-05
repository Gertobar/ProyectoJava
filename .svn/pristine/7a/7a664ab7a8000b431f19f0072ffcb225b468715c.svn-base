/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.ComprobanteDetalle;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
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
public class ComprobanteDetalleFacade extends AbstractFacade<ComprobanteDetalle> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComprobanteDetalleFacade() {
        super(ComprobanteDetalle.class);
    }
    
    public List<ComprobanteDetalle> getItemsComprobanteDetalle(Long codigoComprobante){
        Query query = em.createNamedQuery(ComprobanteDetalle.findByCodigoComprobante);
        query.setParameter("codigoComprobante", codigoComprobante);
        return query.getResultList();
    }
    
    public List<ComprobanteDetalle> getItemsComprobanteDetalleFechaIfipCuenta(Long codigoIfip, Date fechaInicio,Date fechaFin, String cuentaContable, long codigoTipoPlan){
        Query query = em.createNamedQuery(ComprobanteDetalle.findByIfipEntreFechaCuenta);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("cuentaContable", cuentaContable);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("codigoTipoPlan", codigoTipoPlan);
        
        return query.getResultList();
    }
}
