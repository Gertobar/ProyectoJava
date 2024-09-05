/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos.lineacredito;

import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCredito;
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
public class LineaCreditoFacade extends AbstractFacade<LineaCredito> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaCreditoFacade() {
        super(LineaCredito.class);
    }
    
    /**
     * Obtiene el listado de lineas de credito por ifip eliminado SI NO
     * @param codigoIfip
     * @param vigente
     * @return 
     */
    public List<LineaCredito> getListaLineaCreditoPorIfipVigente(Long codigoIfip, String vigente){
        List<LineaCredito> lista = null;
        Query query = em.createNamedQuery("LineaCredito.findByCodigoIfipVigente", LineaCredito.class);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("vigente", vigente);
        if (!query.getResultList().isEmpty()) {
          List<LineaCredito> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    /***
     * Metodo para obtener la linea de credito a partir del codigo
     * @param codigo
     * @return 
     */
    public LineaCredito getLineaCreditoByCodigo(Long codigo){
        List<LineaCredito> lista = null;
        Query query = em.createNamedQuery("LineaCredito.findByCodigo", LineaCredito.class);
        query.setParameter("codigo", codigo);
        if (!query.getResultList().isEmpty()) {
          List<LineaCredito> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }else
            return null;
        return lista.get(0);
    }
}
