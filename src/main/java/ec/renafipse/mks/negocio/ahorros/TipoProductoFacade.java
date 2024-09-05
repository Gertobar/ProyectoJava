/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.TipoProducto;
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
public class TipoProductoFacade extends AbstractFacade<TipoProducto> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoProductoFacade() {
        super(TipoProducto.class);
    }
     // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE LAS MONEDAS DE ACUERDO AL ESTADO ELIMINADO
     *
     * @param eliminado Eliminado S=Si N=No
     * @return Lista de Monedas
     */
    public List<TipoProducto> getItemsTipoProducto(char eliminado) {
        List<TipoProducto> listItemsTipoProducto;
        Query query = this.em.createNamedQuery(TipoProducto.findByEliminado);
        query.setParameter("eliminado", eliminado);
        listItemsTipoProducto = query.getResultList();
        return listItemsTipoProducto;
    }
    public List<TipoProducto> getItemsTipoProductoMoneda(Long codigoMoneda, char eliminado) {
        Query query = this.em.createNamedQuery(TipoProducto.findByMoneda);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    public TipoProducto getTipoProducto(Long codigo) {
        Query query = this.em.createNamedQuery(TipoProducto.findByCodigo);
        query.setParameter("codigo", codigo);
        return query.getSingleResult()==null? null:(TipoProducto) query.getSingleResult();
    }
    public List<TipoProducto> getItemsTipoProductoConceptoTransaccion() {
        List<TipoProducto> listItemsTipoProducto;
        Query query = this.em.createNamedQuery(TipoProducto.findByConceptoTransaccion);
        listItemsTipoProducto = query.getResultList();
        return listItemsTipoProducto;
    }
    public List<TipoProducto> getItemsTipoProductoMonedaIfip(Long codigoIfip, Long codigoMoneda) {
        List<TipoProducto> listItemsTipoProducto;
        Query query = this.em.createNamedQuery(TipoProducto.findByMonedaIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoMoneda", codigoMoneda);
        listItemsTipoProducto = query.getResultList();
        return listItemsTipoProducto;
    }
    public List<TipoProducto> getItemsTipoProductoMonedaIfip(Long codigoIfip, char eliminado) {
        List<TipoProducto> listItemsTipoProducto;
        Query query = this.em.createNamedQuery(TipoProducto.findBytipoProductoIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        listItemsTipoProducto = query.getResultList();
        return listItemsTipoProducto;
    }
}
