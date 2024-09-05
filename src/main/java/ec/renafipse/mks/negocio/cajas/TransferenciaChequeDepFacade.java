/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.TransferenciaChequeDep;
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
public class TransferenciaChequeDepFacade extends AbstractFacade<TransferenciaChequeDep> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransferenciaChequeDepFacade() {
        super(TransferenciaChequeDep.class);
    }
    
   /**
    * 
    * @param codigoUsuario
    * @param codigoIfipAgencia
    * @return 
    */
    public List<TransferenciaChequeDep> getItemsTransferChequeDep(Long codigoUsuario, Long codigoIfipAgencia) {
        Query query = em.createNamedQuery(TransferenciaChequeDep.findByUsuarioIfipAgencia);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();
    }

}
