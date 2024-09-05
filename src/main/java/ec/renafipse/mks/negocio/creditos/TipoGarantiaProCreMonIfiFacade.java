/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.TipoGarantiaProCreMonIfi;
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
public class TipoGarantiaProCreMonIfiFacade extends AbstractFacade<TipoGarantiaProCreMonIfi> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoGarantiaProCreMonIfiFacade() {
        super(TipoGarantiaProCreMonIfi.class);
    }
    public TipoGarantiaProCreMonIfi getTipoGarantiaProCreMonIfi(Long codigoTasa, Long codigoTipoGarantia) {
        Query query = this.em.createNamedQuery(TipoGarantiaProCreMonIfi.findByTasaTipoGarantia);
        query.setParameter("codigoTasa", codigoTasa);
        query.setParameter("codigoTipoGarantia", codigoTipoGarantia);
        try {
            TipoGarantiaProCreMonIfi tasaInteresProCreMonIfi = (TipoGarantiaProCreMonIfi)query.getSingleResult();
            
            return tasaInteresProCreMonIfi == null ? null : tasaInteresProCreMonIfi;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public TipoGarantiaProCreMonIfi getTipoGarantiaProCreMonIfiElim(Long codigoTasa, Long codigoTipoGarantia, char eliminado) {
        Query query = this.em.createNamedQuery(TipoGarantiaProCreMonIfi.findByTasaTipoGarantiaElim);
        query.setParameter("codigoTasa", codigoTasa);
        query.setParameter("codigoTipoGarantia", codigoTipoGarantia);
        query.setParameter("eliminado", eliminado);
        try {
            TipoGarantiaProCreMonIfi tasaInteresProCreMonIfi = (TipoGarantiaProCreMonIfi)query.getSingleResult();
            
            return tasaInteresProCreMonIfi == null ? null : tasaInteresProCreMonIfi;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<TipoGarantiaProCreMonIfi> getTipoGarantiaProCreMonIfiTasaElim(Long codigoTasa, char eliminado) {
        Query query = this.em.createNamedQuery(TipoGarantiaProCreMonIfi.findByTasaElim);
        query.setParameter("codigoTasa", codigoTasa);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public List<TipoGarantiaProCreMonIfi> getTipoGarantiaProCreMonIfiTasaObliElim(Long codigoTasa,char obligatorio, char eliminado) {
        Query query = this.em.createNamedQuery(TipoGarantiaProCreMonIfi.findByTasaObliElim);
        query.setParameter("codigoTasa", codigoTasa);
        query.setParameter("obligatorio", obligatorio);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
