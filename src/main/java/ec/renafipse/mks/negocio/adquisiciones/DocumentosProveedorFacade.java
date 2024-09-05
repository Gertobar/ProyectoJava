/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.DocumentosProveedor;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
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
public class DocumentosProveedorFacade extends AbstractFacade<DocumentosProveedor> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentosProveedorFacade() {
        super(DocumentosProveedor.class);
    }

    public List<DocumentosProveedor> getItemsDocumentosProveedor(Long codigoProveedor) {
//        //System.out.println("CodigoProveedor:  "  +  codigoProveedor);
//        Query query = this.em.createNamedQuery(DocumentosProveedor.findByCodigoProveedor);
//        query.setParameter("codigoProveedor", codigoProveedor);
//        return query.getResultList();

        //System.out.println("------------------------------------------------CODIGO IFIP : " + codigoProveedor);
        List<DocumentosProveedor> listDocumentosProveedor;
        Query query = this.em.createNamedQuery(DocumentosProveedor.findByCodigoProveedor);
        query.setParameter("codigoProveedor", codigoProveedor);
        listDocumentosProveedor = query.getResultList();
        //System.out.println(" tamaño: " + listDocumentosProveedor.size());
        return listDocumentosProveedor;
    }

    public List<DocumentosProveedor> getItemsDatosProveedorComprobante(Long codigoProveedor, Long codigoComprobante, String serie, Long numeroSerie) {
        Query query = em.createNamedQuery(DocumentosProveedor.findByCodigoProveedorCodigoComprobanteNumeroSerie);
        query.setParameter("codigoProveedor", codigoProveedor);
        query.setParameter("codigoTipoComprobante", codigoComprobante); //
        query.setParameter("serie", serie);
        query.setParameter("numeroSerie", numeroSerie);
        return query.getResultList();
    }

    public List<DocumentosProveedor> getItemsDatosProveedorComprobanteSerie(Long codigoProveedor, Long codigoComprobante, String serie, Long inicioSerie, Long finSerie) {
        Query query = em.createNamedQuery(DocumentosProveedor.findByCodigoProveedorTipoComprobanteSerieIF);
        query.setParameter("codigoProveedor", codigoProveedor);
        query.setParameter("codigoTipoComprobante", codigoComprobante); //
        query.setParameter("serie", serie);
        query.setParameter("inicioSerie", inicioSerie);
        query.setParameter("finSerie", finSerie);
        return query.getResultList();
    }

    //
    public List<DocumentosProveedor> getItemsDatosfindByCodigoProveedorTipoComprobanteAut(Long codigoProveedor, Long codigoComprobante, String numeroAutorizacion) {
        Query query = em.createNamedQuery(DocumentosProveedor.findByCodigoProveedorTipoComprobanteAut);
        query.setParameter("codigoProveedor", codigoProveedor);
        query.setParameter("codigoTipoComprobante", codigoComprobante); //
        query.setParameter("numeroAutorizacion", numeroAutorizacion);
        return query.getResultList();
    }

    //////- VALIDACION DE CODIGO_PROVEEDOR, CODIGO_TIPO_COMPROBANTE, FECHA_EMISION
    public List<DocumentosProveedor> getItemsDatosfindByCodProvTipoComprFechaEmision(Long codigoProveedor, Long codigoComprobante, Date fechaEmision) {
        Query query = em.createNamedQuery(DocumentosProveedor.findByCodProvTipoComprFechaEmision);
        query.setParameter("codigoProveedor", codigoProveedor);
        query.setParameter("codigoTipoComprobante", codigoComprobante); //
        query.setParameter("fechaEmision", fechaEmision);
        return query.getResultList();
    }

    public List<DocumentosProveedor> getItemsDocumentosProveedorfindByCodigoProveedor(Long codigoProveedor) {
        Query query = em.createNamedQuery(DocumentosProveedor.findByCodigoProveedor);
        query.setParameter("codigoProveedor", codigoProveedor);
        return query.getResultList();
    }

}
