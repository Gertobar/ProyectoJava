/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.dpfs;


import ec.renafipse.mks.modelo.dpfs.PagoDpf;
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
public class PagoDpfFacade extends AbstractFacade<PagoDpf> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoDpfFacade() {
        super(PagoDpf.class);
    }

   /**
    * Obtiene los pagos de intereses del credito
    * @param codigoContrato Codigo del Contrato del DPF
    * @param codigoIfip Codigo del a IFIP
     * @param retencionImpresa Retencion Impresa
    * @return Lista de Pagos del DPF
    */
    public List<PagoDpf> getItemsPagoDpfContratoIfip(long codigoContrato, long codigoIfip, char retencionImpresa) {
        Query query = this.em.createNamedQuery(PagoDpf.findPagoDpfContratoIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoContrato", codigoContrato);
        query.setParameter("retencionImpresa", retencionImpresa);
        return query.getResultList();
    }
    
    public List<PagoDpf> getItemsPagoDpfContratoIfip(long codigoContrato, long codigoIfip) {
        Query query = this.em.createNamedQuery(PagoDpf.findPagoDpfConIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoContrato", codigoContrato);
        return query.getResultList();
    }

}
