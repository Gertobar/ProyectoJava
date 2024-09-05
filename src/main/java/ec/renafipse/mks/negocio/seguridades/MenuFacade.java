/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.Menu;
import ec.renafipse.mks.modelo.seguridades.OpcionOperacion;
import ec.renafipse.mks.modelo.seguridades.RolOpcionOperacion;
import ec.renafipse.mks.modelo.seguridades.Sistema;
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
public class MenuFacade extends AbstractFacade<Menu> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }
    
    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    
    /**
     * Devuelve el los sistemas a los que puede acceder la IFIP
     * @param sistema Entidad Sistema
     * @return  Devuelve una lista del Menu del Sistema seleccionado en donde estan los modulos, menus y opciones.
     */
    public List<Menu> getItemsMenu(Sistema sistema)
    {
        Query query =  em.createNamedQuery(Menu.findBySistema);
        query.setParameter("sistema", sistema);
        List<Menu> listMenu = query.getResultList();        
        return  listMenu;
    }
    
    /**
     * Devuelve los Modulos que tiene permisos la IFIP
     * @param codigoIfip Codigo de la Ifip 
     * @param codigoRol Codigo del Rol del Usuario
     * @return Devuelve los modulos que tiene asignado una IFIP en el sistema Minka Software
     */
     public List<Menu> getItemsModulos(Long  codigoIfip, String codigoRol) {
        Query query = em.createQuery("SELECT i.menu FROM IfipMenu i JOIN i.menu m WHERE m.tipo = 'D' AND i.eliminado = 'N' AND m.eliminado='N' AND i.ifipMenuPK.codigoIfip = :codigoIfip AND m.codigoSistema = 2  ORDER BY m.orden", Menu.class); 
        query.setParameter("codigoIfip", codigoIfip);
        //query.setParameter("codigoRol", codigoRol);
        return query.getResultList();
    }
     
    /**
     * Devuelve las opciones del Menu que tiene permisos la IFIP
     * @param codigoRol Codigo del Rol que tiene el Usuiario en la Agencia
     * @param codigoMenu Codigo del Menu del cual se desea obtener las opciones/Items
     * @param codigoIfip Codigo de la Ifip a la que pertenece el susuario
     * @return Devuelve una Lista de Menu que contiene las opciones del 
     */
     public List<Menu> getItemsOpcionesMenu(String codigoRol, Long codigoMenu, Long codigoIfip) {
        Query query = em.createQuery("SELECT DISTINCT m FROM RolOpcionOperacion r,  OpcionOperacion p, Menu m  WHERE r.eliminado = 'N' AND r.rolOpcionOperacionPK.codigoIfip = :codigoIfip AND  r.rol.codigo = :codigoRol AND r.rol.eliminado = 'N' AND r.opcionOperacion.eliminado = 'N' AND r.opcionOperacion.codigoMenu.eliminado = 'N' AND r.opcionOperacion.codigoMenu.tipo = 'O' AND r.opcionOperacion.codigoMenu.codigoMenuPadre.codigo = :codigoMenu  AND  r.opcionOperacion.codigo = p.codigo AND p.codigoMenu.codigo = m.codigo ORDER BY m.orden", Menu.class); 
        query.setParameter("codigoIfip", codigoIfip);      
        query.setParameter("codigoRol", codigoRol);            
        query.setParameter("codigoMenu", codigoMenu);        
        return query.getResultList();
    }
     
     /**
     * Devuelve los Modulos que tiene permisos la IFIP
     * @param modulo Entidad modulo seleccionado
     * @return Lista de Menu que contiene los menus (sub-menus) del modulo seleccionado
     */
     public List<Menu> getItemsMenu(Menu modulo) {
        Query query = em.createNamedQuery(Menu.findByModulo);
        query.setParameter("tipo", 'M');            
        query.setParameter("eliminado", 'N');
        query.setParameter("codigoMenuPadre", modulo);        
        return query.getResultList();
    }    
    
    /**
     * Devuelve las operaciones  que tiene el rol sobre la opcion seleccionada
     * @param codigoRol Codigo del Rol del Usuario
     * @param codigoIfip Codigo de la Ifip a la cual esta asiganada el usuario
     * @param codigoOpcion Codigo de la Opcion del Menu
     * @return Listado de OpcionOperacion del Rol
     */
    public List<OpcionOperacion> getOpcionOperacion(String codigoRol, Long codigoIfip, Long codigoOpcion)
    {
        Query query = em.createNamedQuery(RolOpcionOperacion.findByOpcionOperacion);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", 'N');
        query.setParameter("codigoMenu", codigoOpcion);
        
        return query.getResultList();
    }
    
    /**
     * Metodo que Devuelve los menus padres a los que puede ser asigando el menu
     * @param  tipo Tipo del menu 
     * @return Lista de Menus de acuerdo el tipo y el sistema
     */
    public List<Menu> getItemsMenuPorTipo(char tipo)
    {
        Query query = em.createNamedQuery(Menu.findByTipo);
        query.setParameter("tipo", tipo);        
        query.setParameter("codigoSistema", Long.parseLong("2"));        
        return query.getResultList();
    }
    
        /**
     *
     * @param codigoSistema
     * @return
     */
    public List<Menu> getItemsModulo(Long codigoSistema) {
        Query query = em.createNamedQuery(Menu.findByOpcionPorModulo);
        query.setParameter("codigoSistema", codigoSistema);
        query.setParameter("tipo", 'D');
        query.setParameter("eliminado", 'N');
        return query.getResultList();
    }

    /**
     * 
     * @param codigoMenu
     * @return 
     */
    public List<Menu> getItemsHijosOpcionOperacion(Menu codigoMenu) {
        Query query = em.createNamedQuery(Menu.findByCodigoPadre);
        query.setParameter("codigoMenuPadre", codigoMenu);
        query.setParameter("eliminado", 'N');
        return query.getResultList();
    }
    
   
    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
    
     
} // FIN DE LA CLASE
