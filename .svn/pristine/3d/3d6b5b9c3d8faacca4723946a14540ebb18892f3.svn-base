/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.cajas.IngresoEgreso;
import ec.renafipse.mks.modelo.cajas.IngresoEgresoMoneda;
import ec.renafipse.mks.modelo.seguridades.RolIngresoEgresoIfip;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class RolIngresoEgresoIfipFacade extends AbstractFacade<RolIngresoEgresoIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolIngresoEgresoIfipFacade() {
        super(RolIngresoEgresoIfip.class);
    }

    // -------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE LOS INGRESOS Y EGRESOS PARA MOSTRAR EN LA VENTA DE
     * INGRESO/EGRESOS DE CAJA DE ACUERDO A LOS PERMISOS PERTINENTES
     *
     * @param codigoMoneda Codigo de la Moneda
     * @param codigoIfip Codigo de la IFIP
     * @param codigoRol Codigo del ROl
     * @param esIngreso Si es Ingreso o Egreso
     * @param eliminado S=Si N=NO
     * @param mostrar S=Si N=NO
     * @return Lista de Ingresos y Egresos
     */
    public List<IngresoEgreso> getIngresoEgresoCaja(Long codigoMoneda, Long codigoIfip, String codigoRol, char esIngreso, char eliminado, char mostrar) {
        Query query = em.createNamedQuery(RolIngresoEgresoIfip.findByIngresoEgresoCaja);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("mostrar", mostrar);
        query.setParameter("esIngreso", esIngreso);

        return query.getResultList();
    }

    /**
     *
     * @param codigoMoneda
     * @param codigoIfip
     * @param codigoRol
     * @param esIngreso
     * @param mostrar
     * @return
     */
    public List<IngresoEgresoMoneda> getItemsIngresoEgresoPermisosExistentes(Long codigoMoneda, Long codigoIfip, String codigoRol, char esIngreso, char mostrar) {
        Query query = em.createNamedQuery(RolIngresoEgresoIfip.findByIngresoEgresoPermisos);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("esIngreso", esIngreso);
        query.setParameter("mostrar", mostrar);
        return query.getResultList();
    }

    /**
     * Buscar todos los roles de ingresos o egresos
     *
     * @param codigoMoneda
     * @param codigoRol
     * @param codigoIfip
     * @param codigoIngresoEgreso
     * @return
     */
    public List<RolIngresoEgresoIfip> getItemsRolIngresoEgresoIfip(Long codigoMoneda, String codigoRol, Long codigoIfip, Long codigoIngresoEgreso) {
        Query query = em.createNamedQuery(RolIngresoEgresoIfip.findByRolIngresoEgresoIfip);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIngresoEgreso", codigoIngresoEgreso);
        return query.getResultList();
    }
}
