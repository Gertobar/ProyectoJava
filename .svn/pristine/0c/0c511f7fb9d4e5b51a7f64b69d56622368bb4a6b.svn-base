/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.TipoPlanDeCuenta;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author vastudillo
 */
@Stateless
public class TipoPlanDeCuentaFacade extends AbstractFacade<TipoPlanDeCuenta> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPlanDeCuentaFacade() {
        super(TipoPlanDeCuenta.class);
    }
    
    public List<TipoPlanDeCuenta> getItemsTipoPlanCuentaVigenteEliminado(char vigente, char eliminado) {
        Query query = em.createNamedQuery(TipoPlanDeCuenta.findByEliminadoVigente);
        query.setParameter("vigente", vigente);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public List<TipoPlanDeCuenta> getItemsTipoPlanCuentaVigente(char vigente) {
        Query query = em.createNamedQuery(TipoPlanDeCuenta.findByVigente);
        query.setParameter("vigente", vigente);
        return query.getResultList();
    }
    
    public List<TipoPlanDeCuenta> getItemsFechaVigente(Date fechaInicio, Date fechaFin) {
        Query query = em.createNamedQuery(TipoPlanDeCuenta.findByFechaVigencia);
        query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
        query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
        return query.getResultList();
    }
}
