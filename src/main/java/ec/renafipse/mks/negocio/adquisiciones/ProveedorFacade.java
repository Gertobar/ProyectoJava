/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.Proveedor;
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
public class ProveedorFacade extends AbstractFacade<Proveedor> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorFacade() {
        super(Proveedor.class);
    }

    public List<Proveedor> getItemsProveedor() {
        //System.out.println("En getItemsProveedor");
        Query query = this.em.createNamedQuery(Proveedor.findAll);
//        query.setParameter("codigo", codigoIfip);
        return query.getResultList();
    }

//    public List<Proveedor> getItemsProveedorPrametrizado(String criterio, String buscarPor, Long codigoIfip) {
    public List<Proveedor> getItemsProveedorPrametrizado(String criterio, String buscarPor) {
        List<Proveedor> listProveedores = null;

//        if (buscarPor.equals("N")) {
//            Query query = this.em.createNamedQuery(Socio.findByNombres);
//            query.setParameter("nombreCompleto", criterio.toUpperCase());
//            query.setParameter("codigoIfip", codigoIfip);
//            listProveedores = query.getResultList();
//        }
//
        //System.out.println("En GetItemsProveedorP");
        if (buscarPor.equals("I")) {
            //System.out.println("En If de getitemspp");
            Query query = this.em.createNamedQuery(Proveedor.findByIdentificacion);
            query.setParameter("identificacion", criterio);
//            query.setParameter("codigoIfip", codigoIfip);
            listProveedores = query.getResultList();
        }

        return listProveedores;
    }

    public List<Proveedor> getItemsIdenProveedor(Long codigoPersona) {
        Query query = this.em.createNamedQuery(Proveedor.findByCodigoPersona);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }

    public List<Proveedor> getItemsNombreProveedor(String nombreCompleto, char eliminado) {
        Query query = this.em.createNamedQuery(Proveedor.findByNombrePersonaProveedor);
        query.setParameter("nombreCompleto", nombreCompleto);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    public List<Proveedor> getItemsProveedorByCodigo(Long codigo) {
        Query query = this.em.createNamedQuery(Proveedor.findByCodigo);
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }

    public List<Proveedor> getItemsProveedorByIdentificacion(String identificacion) {
        //System.out.println("CEDULA A BUSCAR "+identificacion);
        Query query = this.em.createNamedQuery(Proveedor.findByIdentificacion);
        query.setParameter("identificacion", identificacion);
        return query.getResultList();
    }
 

    public List<Proveedor> getItemsfindByProveedorCodPersona(Long codigoPersona) {
        Query query = this.em.createNamedQuery(Proveedor.findByCodigoPersona);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }

    public List<Proveedor> getActualIdProveedor() {
        Query query = this.em.createNamedQuery(Proveedor.findAllCodigo);
        return query.getResultList();
    }
    
    public Long getSecuenciaCodigoProveedor() {
        Query query = em.createNamedQuery(Proveedor.getCodigoProveedor);
        
        List<Object> listCodigoProveedor = query.getResultList();

        if (listCodigoProveedor == null) {
            return Long.parseLong("1");
        }

        int codigoProveedor = Integer.parseInt((listCodigoProveedor.get(0) == null) ? "0" : listCodigoProveedor.get(0).toString());
        codigoProveedor++;
        return Long.parseLong(String.valueOf(codigoProveedor));

    }
}
