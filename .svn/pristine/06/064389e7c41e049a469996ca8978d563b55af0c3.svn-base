/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.ProcesoContableIngEgrMon;
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
public class ProcesoContableIngEgrMonFacade extends AbstractFacade<ProcesoContableIngEgrMon> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoContableIngEgrMonFacade() {
        super(ProcesoContableIngEgrMon.class);
    }

    public List<ProcesoContableIngEgrMon> getItemsProcesosContablesExistentesIngresoegresoMoneda(Long codigoMoneda, Long codigoIngresoegreso, char eliminado) {
        Query query = em.createNamedQuery(ProcesoContableIngEgrMon.findByMonedaIngreso);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIngresoegreso", codigoIngresoegreso);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    public List<ProcesoContableIngEgrMon> getItemsProcesosContablesExistentesIngresoegresoMonedaTodos(Long codigoMoneda, Long codigoIngresoegreso) {
        Query query = em.createNamedQuery(ProcesoContableIngEgrMon.findByMonedaIngresoTodos);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIngresoegreso", codigoIngresoegreso);
        return query.getResultList();
    }

}
