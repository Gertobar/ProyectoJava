/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.comunes;

import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.comunes.Moneda;
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
public class MonedaFacade extends AbstractFacade<Moneda> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MonedaFacade() {
        super(Moneda.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE LAS MONEDAS DE ACUERDO AL ESTADO ELIMINADO
     *
     * @param eliminado Eliminado S=Si N=No
     * @return Lista de Monedas
     */
    public List<Moneda> getItemsMonedas(char eliminado) {
        List<Moneda> listMonedas;
        Query query = this.em.createNamedQuery(Moneda.findByEliminado);
        query.setParameter("eliminado", eliminado);
        listMonedas = query.getResultList();
        return listMonedas;
    }
    public Moneda getMoneda(Long codigo) {
        Query query = this.em.createNamedQuery(Moneda.findByCodigo);
        query.setParameter("codigo", codigo);
        return query.getSingleResult()==null ? null: (Moneda) query.getSingleResult();
    }
    public List<Moneda> getItemsMonedasProductoConceptoTransaccionPro(Long codigo) {
        List<Moneda> listMonedas;
        Query query = this.em.createNamedQuery(Moneda.findByTipoProducto);
        query.setParameter("codigo", codigo);
        listMonedas = query.getResultList();
        return listMonedas;
    }
    public List<Moneda> getItemsMonedasProductoCredito(Long codigoProducto, Long codigoIfip, char eliminado) {
        List<Moneda> listMonedas;
        Query query = this.em.createNamedQuery(Moneda.findByProductoCreditoMoneda);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        listMonedas = query.getResultList();
        return listMonedas;
    }
    public List<Moneda> getItemsMonedasProductoCredito(Long codigoProducto, Long codigoIfip) {
        List<Moneda> listMonedas;
        Query query = this.em.createNamedQuery(Moneda.findByProductoCreditoM);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoIfip", codigoIfip);
        listMonedas = query.getResultList();
        return listMonedas;
    }
    public List<Moneda> getItemsMonedasProductoIfip(Long codigoTipoProducto, Long codigoIfip) {
        List<Moneda> listMonedas;
        Query query = this.em.createNamedQuery(Moneda.findByMonedaIfip);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoIfip", codigoIfip);
        listMonedas = query.getResultList();
        return listMonedas;
    }
    public List<Moneda> getItemsIfipMonedas(Long codigoIfip) {
        Query query = this.em.createNamedQuery(Moneda.findByIfipMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
    public List<Moneda> getItemsTasaMonedas(Long codigoProducto, Long codigoIfip, char eliminado) {
        List<Moneda> listMonedas;
        Query query = this.em.createNamedQuery(Moneda.findByTasaIntProCreMonIfi);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        listMonedas = query.getResultList();
        return listMonedas;
    }
}
