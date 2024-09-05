/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.dpfs.TalonarioDocumentoDpf;
import ec.renafipse.mks.modelo.ifips.IfipMoneda;
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
public class TalonarioDocumentoDpfFacade extends AbstractFacade<TalonarioDocumentoDpf> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TalonarioDocumentoDpfFacade() {
        super(TalonarioDocumentoDpf.class);
    }

    public List<TalonarioDocumentoDpf> getItemsUnique(Long codigoIfip, Long codigoMoneda, Long inicioSerie) {
        Query query = em.createNamedQuery(TalonarioDocumentoDpf.findByUnique);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("inicioSerie", inicioSerie);
        return query.getResultList();

    }
    
    public List<TalonarioDocumentoDpf> getItemsCodigoIfipAgencia(Long codigoIfipAgencia) {
        Query query = em.createNamedQuery(TalonarioDocumentoDpf.findByCodigoIfipAgencia);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();

    }
    
    public List<TalonarioDocumentoDpf> getItemsRangoSerie(Long codigoIfip, Long codigoMoneda, Long inicioSerie) {
        Query query = em.createNamedQuery(TalonarioDocumentoDpf.findByRangoSerie);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("inicioSerie", inicioSerie);
        return query.getResultList();

    }
}
