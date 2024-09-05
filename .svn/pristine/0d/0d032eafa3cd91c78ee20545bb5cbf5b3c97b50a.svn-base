/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalleCheque;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author renafipse1
 */
@Stateless
public class PagoCompraDetalleChequeFacade extends AbstractFacade<PagoCompraDetalleCheque> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoCompraDetalleChequeFacade() {
        super(PagoCompraDetalleCheque.class);
    }
        public List<PagoCompraDetalleCheque> findByCodEnTtFinNumCheNumCue(Long codigoEntidadFinanciera, Long numeroCheque) {
        Query query = em.createNamedQuery(PagoCompraDetalleCheque.findByCodEnTtFinNumChe);
        query.setParameter("codigoEntidadFinanciera", codigoEntidadFinanciera);
        query.setParameter("numeroCheque", numeroCheque);

        return query.getResultList();
    }

    public List<PagoCompraDetalleCheque> findByCodigoPagoCompraDetalle(Long codigoPagComDet) {
        Query query = em.createNamedQuery(PagoCompraDetalleCheque.findByCodPagCompDet);
        query.setParameter("codigoPagComDet", codigoPagComDet);

        return query.getResultList();
    }

    public Object findByCodEnTtFinNumCheNumCue(Long codigo, String numeroCuenta, Long numeroCheque) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
