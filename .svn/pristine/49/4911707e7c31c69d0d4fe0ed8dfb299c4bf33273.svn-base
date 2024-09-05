/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos.lineacredito;

import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolTipGar;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andres
 */
@Stateless
public class LineaCreditoSolTipGarFacade extends AbstractFacade<LineaCreditoSolTipGar> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaCreditoSolTipGarFacade() {
        super(LineaCreditoSolTipGar.class);
    }
    
    /**
     * 
     * @param codigoLineaCreditoSolicitud
     * @param codigoTipoGarantia
     * @return 
     */
    public LineaCreditoSolTipGar getLineaCreditoSolTipGarPorLineaCreditoSolicitudCodigoTipoGarantia (Long codigoLineaCreditoSolicitud, Long codigoTipoGarantia){
        List<LineaCreditoSolTipGar> lista = null;
        LineaCreditoSolTipGar lineaCreditoSolTipGar = null;
        Query query = em.createNamedQuery("LineaCreditoSolTipGar.findByCodigoLineaCreditoSolicitudCodigoTipoGarantia", LineaCreditoSolTipGar.class);
        query.setParameter("codigoLineaCreditoSolicitud", codigoLineaCreditoSolicitud);
        query.setParameter("codigoTipoGarantia", codigoTipoGarantia);
        if (!query.getResultList().isEmpty()) {
            List<LineaCreditoSolTipGar> resultado = query.getResultList();
            if (resultado.size() > 0) {
                lista = resultado;
            }
        }
        if (lista != null) {
            lineaCreditoSolTipGar = (LineaCreditoSolTipGar) lista.get(0);
        }
        return lineaCreditoSolTipGar;
    }
}
