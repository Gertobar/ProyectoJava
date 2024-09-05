/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos.lineacredito;

import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoPlazo;
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
public class LineaCreditoPlazoFacade extends AbstractFacade<LineaCreditoPlazo> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaCreditoPlazoFacade() {
        super(LineaCreditoPlazo.class);
    }
    
    /**
     * Obtiene el listado de lineas de credito plazo de una linea de credito
     * @param codigoLineaCredito
     * @return 
     */
    public List<LineaCreditoPlazo> getListaLineaCreditoPlazoPorLinea(Long codigoLineaCredito){
        List<LineaCreditoPlazo> lista = null;
        Query query = em.createNamedQuery("LineaCreditoPlazo.findByCodigoLineaCredito", LineaCreditoPlazo.class);
        query.setParameter("codigoLineaCredito", codigoLineaCredito);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoPlazo> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    
}
