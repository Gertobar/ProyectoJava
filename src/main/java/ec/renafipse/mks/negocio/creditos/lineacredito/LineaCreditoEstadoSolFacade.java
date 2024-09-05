/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos.lineacredito;

import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoEstadoSol;
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
public class LineaCreditoEstadoSolFacade extends AbstractFacade<LineaCreditoEstadoSol> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaCreditoEstadoSolFacade() {
        super(LineaCreditoEstadoSol.class);
    }

    /**
     * Devuelve el listado de estados de solicitud de credito por estado
     * eliminado
     *
     * @param codigo
     * @param eliminado
     * @return
     */
    public List<LineaCreditoEstadoSol> getItemsPorCodigoYEliminado(Long codigo, char eliminado) {
        List<LineaCreditoEstadoSol> lista = null;
        Query query = em.createNamedQuery("LineaCreditoEstadoSol.findCambioEstado", LineaCreditoEstadoSol.class);
        if (!query.getResultList().isEmpty()) {
            List<LineaCreditoEstadoSol> resultado = query.getResultList();
            if (resultado.size() > 0) {
                lista = resultado;
            }
        }
        return lista;
    }

    /**
     * 
     * @param codigo
     * @return 
     */
    public LineaCreditoEstadoSol getLineaCreditoEstadoSolPorCodigo(Long codigo) {
        List<LineaCreditoEstadoSol> lista = null;
        LineaCreditoEstadoSol lineaCreditoEstadoSol = null;
        Query query = em.createNamedQuery("LineaCreditoEstadoSol.findByCodigo", LineaCreditoEstadoSol.class);
        query.setParameter("codigo", codigo);
        if (!query.getResultList().isEmpty()) {
            List<LineaCreditoEstadoSol> resultado = query.getResultList();
            if (resultado.size() > 0) {
                lista = resultado;
            }
        }
        if (lista != null) {
            lineaCreditoEstadoSol = (LineaCreditoEstadoSol) lista.get(0);
        }
        return lineaCreditoEstadoSol;
    }

}
