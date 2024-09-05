/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfi;
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
public class PeriodicidadProMonIfiFacade extends AbstractFacade<PeriodicidadProMonIfi> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodicidadProMonIfiFacade() {
        super(PeriodicidadProMonIfi.class);
    }
    public PeriodicidadProMonIfi getPeriodicidadProMonIfi(Long codigoProducto, Long codigoMoneda, Long codigoIfip) {
        Query query = this.em.createNamedQuery(PeriodicidadProMonIfi.findByPeriodicidadProMonIfiPK);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        try {
            PeriodicidadProMonIfi tasaInteresProCreMonIfi = (PeriodicidadProMonIfi)query.getSingleResult();
            return tasaInteresProCreMonIfi == null ? null : tasaInteresProCreMonIfi;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List <Periodicidad> getPeriodicidadProMonIfiElim(Long codigoProducto, Long codigoMoneda, Long codigoIfip, char eliminado, char esParaCredito) {
        Query query = this.em.createNamedQuery(PeriodicidadProMonIfi.findByProMonIfi);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        query.setParameter("esParaCredito", esParaCredito);
        return query.getResultList();
    }
    
    public PeriodicidadProMonIfi getPeriodicidadProMonIfiCuoMax(Long codigoProducto, Long codigoMoneda, Long codigoIfip, char eliminado, Long codigoPeriodicidad) {
        Query query = this.em.createNamedQuery(PeriodicidadProMonIfi.findByCuotasMaximasPeriodicidad);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoPeriodicidad", codigoPeriodicidad);
        try {
            PeriodicidadProMonIfi tasaInteresProCreMonIfi = (PeriodicidadProMonIfi)query.getSingleResult();
            return tasaInteresProCreMonIfi == null ? null : tasaInteresProCreMonIfi;
        } catch (NoResultException e) {
            return null;
        }
    }
}
