/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.DocumentoRetencion;
import ec.renafipse.mks.modelo.contables.DocumentoRetencionDpf;
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
public class DocumentoRetencionDpfFacade extends AbstractFacade<DocumentoRetencionDpf> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentoRetencionDpfFacade() {
        super(DocumentoRetencionDpf.class);
    }
    
    /**
     * Codigo de Contrato
     * @param codigoContrato
     * @return 
     */
    public List<DocumentoRetencion> getItemsDocumentoRetencionCompra(Long codigoContrato)
    {
        Query query = this.em.createNamedQuery(DocumentoRetencionDpf.findByContratoDpf);
        query.setParameter("codigoContrato", codigoContrato);
        return query.getResultList();
    }
            
   
}
