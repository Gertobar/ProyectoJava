/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.Menu;
import ec.renafipse.mks.modelo.seguridades.OpcionOperacion;
import ec.renafipse.mks.modelo.seguridades.Rol;
import ec.renafipse.mks.modelo.seguridades.RolOpcionOperacion;
import ec.renafipse.mks.modelo.seguridades.TipoOperacion;
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
public class OpcionOperacionFacade extends AbstractFacade<OpcionOperacion> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpcionOperacionFacade() {
        super(OpcionOperacion.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * DEVUELVE LA OPCION OPERACION DE UN MENU Y TIPO OPERACION
     *
     * @param codigoMenu
     * @param codigoTipoOperacion
     * @return Devuelve la lista de Opiones Operaciones.
     */
    public List<OpcionOperacion> getItemsOpcionOperacionTipoOperacionMenu(Menu codigoMenu, TipoOperacion codigoTipoOperacion) {
        Query query = em.createNamedQuery(OpcionOperacion.findByOpcionOperacionTipoOperacionMenu);
        query.setParameter("codigoMenu", codigoMenu);
        query.setParameter("codigoTipoOperacion", codigoTipoOperacion);

        return query.getResultList();
    }

    /**
     *
     * @param codigoMenu
     * @param codigoRol
     * @param codigoIfip
     * @param eliminado
     * @return
     */
    public List<OpcionOperacion> getItemsOpcionOperacionRol(Long codigoMenu, String codigoRol, Long codigoIfip, char eliminado) {
        Query query = em.createNamedQuery(OpcionOperacion.findByOpcionOperacionExistentesRol);
        query.setParameter("codigoMenu", codigoMenu);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    /**
     *
     * @param codigoMenu
     * @param codigoRol
     * @param eliminado
     * @return
     */
    public List<OpcionOperacion> getItemsOpcionOperacionRolPorDef(Long codigoMenu, String codigoRol, char eliminado) {
        Query query = em.createNamedQuery(OpcionOperacion.findByOpcionOperaExistentesRolPorDef);
        query.setParameter("codigoMenu", codigoMenu);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

 /**
  * 
  * @param codigoMenu
  * @param eliminado
  * @return 
  */
    public List<OpcionOperacion> getItemsOpcionOperacion(Long codigoMenu, char eliminado) {
        Query query = em.createNamedQuery(OpcionOperacion.findByOpcionOperacionEliminado);
        query.setParameter("codigoMenu", codigoMenu);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
