/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.TalonarioChequeDetalle;
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
public class TalonarioChequeDetalleFacade extends AbstractFacade<TalonarioChequeDetalle> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TalonarioChequeDetalleFacade() {
        super(TalonarioChequeDetalle.class);
    }

    public List<TalonarioChequeDetalle> itemsFindByNumChequEntFin(Long codigoCuentaEntFin,Long numeroCheque) {
        Query query = em.createNamedQuery(TalonarioChequeDetalle.findByNumChequEntFin);
        query.setParameter("codigoCuentaEntFin", codigoCuentaEntFin);
        query.setParameter("numeroCheque", numeroCheque);
        return query.getResultList();
    }
        public List<TalonarioChequeDetalle> itemsFindByNumChequEntFinaIfipAgencia(Long codigoCuentaEntFin,Long numeroCheque, Long codigoAgencia) {
        Query query = em.createNamedQuery(TalonarioChequeDetalle.findByNumChequEntFinaIfipAgencia);
        query.setParameter("codigoCuentaEntFin", codigoCuentaEntFin);
        query.setParameter("numeroCheque", numeroCheque);
        query.setParameter("codigoAgencia", codigoAgencia);
        return query.getResultList();
    }
    
}
