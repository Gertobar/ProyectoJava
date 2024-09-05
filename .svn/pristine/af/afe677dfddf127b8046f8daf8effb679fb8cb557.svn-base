/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetDet;
import ec.renafipse.mks.negocio.AbstractFacade;
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
public class TalonarioDocumentoRetDetFacade extends AbstractFacade<TalonarioDocumentoRetDet> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TalonarioDocumentoRetDetFacade() {
        super(TalonarioDocumentoRetDet.class);
    }
    
    public List<TalonarioDocumentoRetDet> getItemsSerieTalonarioSerieDocumento(String serie, long serieDocumento)
    {
        Query query = this.em.createNamedQuery(TalonarioDocumentoRetDet.findBySerieTalonarioSerieDocumento);
        query.setParameter("serie", serie);
        query.setParameter("serieDocumento", serieDocumento);
        return query.getResultList();
    }
}
