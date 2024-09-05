/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccion;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro;
import ec.renafipse.mks.modelo.ahorros.Transaccion;
import ec.renafipse.mks.modelo.seguridades.RolConceptoTransaccionIfip;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Viajes2
 */
@Stateless
public class RolConceptoTransaccionIfipFacade extends AbstractFacade<RolConceptoTransaccionIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolConceptoTransaccionIfipFacade() {
        super(RolConceptoTransaccionIfip.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Obtiene las transacciones, de acuerdo a permisos del ROL
     *
     * @param codigoTipoProducto Codigo del Tipo de Producto
     * @param codigoMoneda Codigo de la Moneda
     * @param codigoIfip Codigo de la IFIP
     * @param codigoRol Codigo del Rol
     * @param eliminado Elminado S=Si N=No
     * @param mostrar Mostrar S=SI N=NO
     * @return Lista de Transacciones
     */
    public List<Transaccion> getItemsTransaccionesMovimentoCaja(Long codigoTipoProducto, Long codigoMoneda,
            Long codigoIfip, String codigoRol,
            char eliminado, char mostrar) {
        Query query = em.createNamedQuery(RolConceptoTransaccionIfip.findByTransaccionesMovimentoCaja);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("eliminado", eliminado);
        query.setParameter("mostrar", mostrar);

        return query.getResultList();
    }

    /**
     * Obtiene los conceptos de la transaccion, de acuerdo a permisos del ROL
     *
     * @param codigoTipoProducto Codigo del Tipo de Producto
     * @param codigoMoneda Codigo de la Moneda
     * @param codigoIfip Codigo de la IFIP
     * @param codigoTransaccion Codigo de la Transaccion
     * @param codigoRol Codigo del Rol
     * @param eliminado Elminado S=Si N=No
     * @param mostrar Mostrar S=SI N=NO
     * @return Lista de Concepos
     */
    public List<ConceptoTransaccion> getItemsConceptosMovimentoCaja(Long codigoTipoProducto, Long codigoMoneda,
            Long codigoIfip, Long codigoTransaccion,
            String codigoRol, char eliminado, char mostrar) {
        Query query = em.createNamedQuery(RolConceptoTransaccionIfip.findByConceptosMovimentoCaja);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTransaccion", codigoTransaccion);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("eliminado", eliminado);
        query.setParameter("mostrar", mostrar);

        return query.getResultList();
    }

    /**
     *
     * @param codigoTipoProducto
     * @param codigoMoneda
     * @param codigoIfip
     * @param codigoRol
     * @param codigoTransaccion
     * @param eliminado
     * @param mostrar
     * @return
     */
    public List<ConceptoTransaccionPro> getItemsPermisosExistentesRolConTranIfip(Long codigoTipoProducto, Long codigoMoneda, Long codigoIfip, String codigoRol, Long codigoTransaccion, char eliminado, char mostrar) {
        Query query = this.em.createNamedQuery(RolConceptoTransaccionIfip.findByConceptosPermisosRol);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("codigoTransaccion", codigoTransaccion);
        query.setParameter("eliminado", eliminado);
        query.setParameter("mostrar", mostrar);
        return query.getResultList();
    }

    /**
     * 
     * @param codigoRol
     * @param codigoTransaccion
     * @param codigoIfip
     * @param codigoProducto
     * @param codigoMoneda
     * @return 
     */
    public List<RolConceptoTransaccionIfip> getItemsRolConceptoTransaccionIfip(String codigoRol, Long codigoTransaccion, Long codigoIfip, Long codigoProducto, Long codigoMoneda) {
        Query query = em.createNamedQuery(RolConceptoTransaccionIfip.findByRolConceptoTransaccionIfip);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTransaccion", codigoTransaccion);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        return query.getResultList();
    }

}
