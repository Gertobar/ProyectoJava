/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.DocumentoRetencion;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class DocumentoRetencionFacade extends AbstractFacade<DocumentoRetencion> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentoRetencionFacade() {
        super(DocumentoRetencion.class);
    }
    
    /**
     * 
     * @param codigo
     * @param codigoIfip
     * @param codigoIfipAgencia
     * @param estado
     * @return 
     */
    public List<DocumentoRetencion> getItemsDocumentoRetencionCompra(Long codigo, long codigoIfip, long codigoIfipAgencia, char estado)
    {
        Query query = this.em.createNamedQuery(DocumentoRetencion.findByDocumentoRetencionCompra);
        query.setParameter("codigo", codigo);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
            
    
    public List<DocumentoRetencion> getItemsDocumentoRetencionEstado(Long codigoRetencion,  char estado)
    {
        Query query = this.em.createNamedQuery(DocumentoRetencion.findByDocumentoRetencionEstado);
        query.setParameter("codigoRetencion", codigoRetencion);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
}
