/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.adquisiciones;
 
import ec.renafipse.mks.modelo.adquisiciones.ProveedorIfip;
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
public class ProveedorIfipFacade extends AbstractFacade<ProveedorIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorIfipFacade() {
        super(ProveedorIfip.class);
    }

    public List<ProveedorIfip> getItemsProveedorIfip(Long codigoProveedor) {
        Query query = this.em.createNamedQuery(ProveedorIfip.findByCodigoProveedor);
        query.setParameter("codigoProveedor", codigoProveedor);
        return query.getResultList();
    }

    public List<ProveedorIfip> getItemsCodigoIfipNombreProveedor(Long codigoIfip, String nombreCompleto) {
        Query query = this.em.createNamedQuery(ProveedorIfip.findByCodigoIfipNombreProveedor);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("nombreCompleto", nombreCompleto);
        return query.getResultList();
    }

    public List<ProveedorIfip> getItemsProveedorIdentificacion(String identificacion) {
        Query query = this.em.createNamedQuery(ProveedorIfip.findByIdentificacion);
        query.setParameter("identificacion", identificacion);
        return query.getResultList();
    }

    public List<ProveedorIfip> getItemsCodigoProveedorIfipEliminado(Long codigoProveedor, Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(ProveedorIfip.findByCodigoProveedorIfipEliminado);
        query.setParameter("codigoProveedor", codigoProveedor);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    
    public List<ProveedorIfip> getItemsIdProveedorIfipEliminado(String identificacion, Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(ProveedorIfip.findByIDProveedorIfipEliminado);
        query.setParameter("identificacion", identificacion);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }    
    
    public List<ProveedorIfip> getItemsNombresProveedorIfipEliminado(String nombreCompleto, Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(ProveedorIfip.findByNombreProveedorIfipEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("nombreCompleto", nombreCompleto);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
