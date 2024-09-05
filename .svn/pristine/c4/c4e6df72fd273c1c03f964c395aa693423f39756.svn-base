/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos.lineacredito;

import ec.renafipse.mks.modelo.creditos.lineacredito.AvanceLineaCredito;
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
public class AvanceLineaCreditoFacade extends AbstractFacade<AvanceLineaCredito> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvanceLineaCreditoFacade() {
        super(AvanceLineaCredito.class);
    }
    
    
    /**
     * 
     * @param codigoLineaCreditoSolicitud
     * @return 
     */
    public List<AvanceLineaCredito> getListaLineaCreditoPorCodigoSolicitudPendientePago(Long codigoLineaCreditoSolicitud) {
        List<AvanceLineaCredito> lista = null;
        Query query = em.createNamedQuery("AvanceLineaCredito.findByCodigoSolicitudPendientePago", AvanceLineaCredito.class);
        query.setParameter("codigoLineaCreditoSolicitud", codigoLineaCreditoSolicitud);
        if (!query.getResultList().isEmpty()) {
          List<AvanceLineaCredito> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    /**
     * Metodo para obtener un avance por codigo
     * @param codigo
     * @return 
     */
    public AvanceLineaCredito getAvanceByCodigo(Long codigo) {
        AvanceLineaCredito avance = null;
        Query query = em.createNamedQuery("AvanceLineaCredito.findByCodigo", AvanceLineaCredito.class);
        query.setParameter("codigo", codigo);
        if (!query.getResultList().isEmpty()) {
          List<AvanceLineaCredito> resultado = query.getResultList();
          if (resultado.size() > 0)
            avance = resultado.get(0);
        }
        return avance;
    }
}
