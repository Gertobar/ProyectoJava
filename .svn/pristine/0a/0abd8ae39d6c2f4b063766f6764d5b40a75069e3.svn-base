/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.RolOpcionOperacion;
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
public class RolOpcionOperacionFacade extends AbstractFacade<RolOpcionOperacion> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolOpcionOperacionFacade() {
        super(RolOpcionOperacion.class);
    }

    /**
     *
     * @param codigoRol
     * @param codigoOpcionOperacion
     * @param codigoIfip
     * @return
     */
    public List<RolOpcionOperacion> getItemsOpcionOperacionRolMenu(String codigoRol, Long codigoOpcionOperacion, Long codigoIfip) {
        Query query = em.createNamedQuery(RolOpcionOperacion.findByOpcionOperacionRolMenu);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoOpcionOperacion", codigoOpcionOperacion);
        return query.getResultList();
    }
    
    /**
     * Obtiene los permisos del Menu
     * @param codigoRol Codigo del Rol
     * @param codigoMenu Codigo del Menu
     * @param eliminado Eliminado
     * @param codigoIfip Codigo de la Ifip
     * @return  Lista de Opciones
     */
   public List<RolOpcionOperacion> getItemsOperacionPermisosMenu(String codigoRol, Long codigoMenu, Long codigoIfip, char eliminado) {
        Query query = em.createNamedQuery(RolOpcionOperacion.findByOpcionOperacionPermisosMenu);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoMenu", codigoMenu);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

}
