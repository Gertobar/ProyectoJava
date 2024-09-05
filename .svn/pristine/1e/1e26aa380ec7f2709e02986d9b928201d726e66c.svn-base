/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.Compra;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author vastudillo
 */
@Stateless
public class CompraFacade extends AbstractFacade<Compra> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }

    //-----------------------------------------------------------
    //PERSONALIDO
    public List<Compra> getItemsCompraFecha(Long codigoIfip, Date fechaInicio, Date fechaFin) {
        Query query = em.createNamedQuery(Compra.findByCodigoIfipFechaIngreso);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
        query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
        return query.getResultList();
    }
    
      public List<Compra> getItemsCompraFecha(Long codigoIfip, Date fechaInicio, Date fechaFin,char estadoUno, char estadoDos) {
        Query query = em.createNamedQuery(Compra.findByCodigoIfipFechaIngresoIngresadaRetenida);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
        query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
        query.setParameter("estadoUno", estadoUno);
        query.setParameter("estadoDos", estadoDos);
        return query.getResultList();
    }

    public List<Compra> getItemsCompraCodigoIfipIdentificacionProveedor(Long codigoIfip, String identificacion) {
        Query query = em.createNamedQuery(Compra.findByCodigoIfipIdentificacionProveedor);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("identificacion", identificacion);
        return query.getResultList();
    }
    //personalidad sirve para listar todas las compras por el codigo del proveedor
    public List<Compra> getItemsCompraCodigoProveedor(Long codigoIfip, Long identificacion) {
        Query query = em.createNamedQuery(Compra.findByCodigoProveedor);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoprovedor", identificacion);
        return query.getResultList();
    }
    
      public List<Compra> getItemsComprasCodigoProveedorIngresadaRetenida(Long codigoIfip, Long identificacion, char estadoUno, char estadoDos) {
        Query query = em.createNamedQuery(Compra.findByComprasCodigoProveedorIngresadaRetenida);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoprovedor", identificacion);
        query.setParameter("estadoUno", estadoUno);
        query.setParameter("estadoDos", estadoDos);
        return query.getResultList();
    }
    
     public List<Compra> getItemsAllCompraCodigoIfip(Long codigoIfip) {
        Query query = em.createNamedQuery(Compra.findByCodigoIfipAllCompras);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
     
     public List<Compra> getItemsComprasCodiProrTipoComproNumFact(Long codigoIfip, Long codigoProveedor, Long codigoTipoComprobante,String numeroComprobante ) {
        Query query = em.createNamedQuery(Compra.findByCodigoProveedorTipoComprobanteNumeroFactura);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoProvedor", codigoProveedor);
        query.setParameter("codigoTipoComprobante", codigoTipoComprobante);
        query.setParameter("numeroComprobante", numeroComprobante);
        return query.getResultList();
    }
     
    public List<Compra> getItemsfindByComprasCodProvEstado(Long codigoIfip, Long codigoProveedor, char estadoCompra) {
        Query query = em.createNamedQuery(Compra.findByComprasCodProvEstado);
        //System.out.println("datos que llegab  "+codigoProveedor);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoprovedor", codigoProveedor);
        query.setParameter("estadoCompra", estadoCompra);
        return query.getResultList();
    }
     
    public List<Compra> getItemsCompraByFechaIfipEsta(Long codigoIfip, Date fechaInicio, Date fechaFin, char estadoCompra) {
        Query query = em.createNamedQuery(Compra.findByCodigoIfipFechaIngresoEstado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
        query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
        query.setParameter("estadoCompra", estadoCompra);
        
        return query.getResultList();
    }
    
    /**
     * 
     * @param codigoTipoComprobante
     * @param codigoIfip
     * @param fechaFin
     * @param numeroComprobante
     * @return 
     */
    public List<Compra> getItemsComprobanteTipoIfipNumero(Long codigoTipoComprobante, long codigoIfip, String numeroComprobante) {
        Query query = em.createNamedQuery(Compra.findByComprobanteTipoIfipNumero);
        query.setParameter("codigoTipoComprobante", codigoTipoComprobante);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("numeroComprobante", numeroComprobante);
        
        return query.getResultList();
    }
    
    public List<Compra> getItemsComprobanteTipoIfipNumeroProve(Long codigoTipoComprobante, long codigoIfip, String numeroComprobante,long codigoProveedor) {
        Query query = em.createNamedQuery(Compra.findByComprobanteTipoIfipNumeroProv);
        query.setParameter("codigoTipoComprobante", codigoTipoComprobante);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("numeroComprobante", numeroComprobante);
        query.setParameter("codigoProveedor", codigoProveedor);
        
        return query.getResultList();
    }
}
