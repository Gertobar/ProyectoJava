/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDet;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class TransferenciaChequeDepDetFacade extends AbstractFacade<TransferenciaChequeDepDet> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransferenciaChequeDepDetFacade() {
        super(TransferenciaChequeDepDet.class);
    }
    
/**
 * 
 * @param codigoTransferencia
 * @return 
 */
    public List<TransferenciaChequeDepDet> getItemsTransferChequeDetDet(Long codigoTransferencia) {
        Query query = em.createNamedQuery(TransferenciaChequeDepDet.findByCodigoTransferencia);
        query.setParameter("codigoTransferencia", codigoTransferencia);
        return query.getResultList();
    }
}
