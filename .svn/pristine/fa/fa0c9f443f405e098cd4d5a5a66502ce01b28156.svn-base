/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;


import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionTransf;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FliaAstudillo
 */
@Stateless
public class ConceptoTransaccionTransfFacade extends AbstractFacade<ConceptoTransaccionTransf> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConceptoTransaccionTransfFacade() {
        super(ConceptoTransaccionTransf.class);
    }
    
    /**
     * Obtiene los tipos de transferencias de acuerdo a su estado eliminado
     * @param eliminado N o S
     * @return Lsita de Conceptos de Transferencias
     */
    public List<ConceptoTransaccionTransf> getItemsEliminado(char eliminado)
    {
        return this.em.createNamedQuery(ConceptoTransaccionTransf.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    
    /**
     * Obtiene los tipos de transferencias de acuerdo al producto y su estado eliminado
     * @param codigoMoneda Codigo de Moneda
     * @param codigoProductoOrigen COdigo de Producto Origen de donde Se va realizar la transferencia
     * @param codigoIfip Codigo de la IFip
     * @param eliminado N o S
     * @return Lsita de Conceptos de Transferencias
     */
    public List<ConceptoTransaccionTransf> getItemsDescripcionTransferencia(long codigoMoneda, long codigoProductoOrigen, long codigoIfip, char eliminado)
    {
        Query query = this.em.createNamedQuery(ConceptoTransaccionTransf.findByDescripcionTransferencia);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoProductoOrigen", codigoProductoOrigen);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
