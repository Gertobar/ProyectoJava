/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.dpfs.DocumentoContratoDpf;
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
public class DocumentoContratoDpfFacade extends AbstractFacade<DocumentoContratoDpf> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentoContratoDpfFacade() {
        super(DocumentoContratoDpf.class);
    }

    /**
     * Obtiene los documentos del contrato DPF
     * @param codigoIfip Codigo de la IFIP
     * @param codigoContrato Codigo del Contrato
     * @param estado Estado
     * @return  Lista de Documentos
     */
    public List<DocumentoContratoDpf> getItemsCodigoContratoEstado(Long codigoIfip, Long codigoContrato, char estado) 
    {
        Query query = this.em.createNamedQuery(DocumentoContratoDpf.findByCodigoContratoEstado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoContrato", codigoContrato);
        query.setParameter("estado", estado);
        
        return query.getResultList();
        
    }


}
