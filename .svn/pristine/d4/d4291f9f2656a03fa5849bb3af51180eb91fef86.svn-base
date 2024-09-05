/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccion;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionLote;
import ec.renafipse.mks.modelo.ahorros.Transaccion;
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
public class ConceptoTransaccionLoteFacade extends AbstractFacade<ConceptoTransaccionLote> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConceptoTransaccionLoteFacade() {
        super(ConceptoTransaccionLote.class);
    }

    //--------------------------------------------------------------------------
    //-- METODOS PERSONALIZADOS
    /**
     * Obtiene los Conceptos de las Transacciones que pueden ser ejectadas en lote
     * @param codigoTipoProducto Codigo del Tipo de Producto
     * @param codigoMoneda  Codigo de la Moneda
     * @param codigoTransaccion Codigo de la Transaccion
     * @param codigoIfip Codigo de la Ifip
     * @param mostrar Si se muestra la transaccion S=SI N=NO
     * @param eliminado eliminado S=SI N=NO
     * @return Lista de Concepto de Transacciones
     */
    public List<ConceptoTransaccion> getItemsConceptoTransaccionEjecucion(long codigoTipoProducto, long codigoMoneda, long codigoTransaccion, long codigoIfip,  char eliminado) {
        Query query =  this.em.createNamedQuery(ConceptoTransaccionLote.findByConceptoTransaccionEjecucion);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoTransaccion", codigoTransaccion);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
        
    }
    
    /**
     * Obtiene las transacciones perimitar en la ejecion por lote
     * @param codigoTipoProducto Codigo del Tipo de Producto
     * @param codigoMoneda Codigo de la Moneda
     * @param codigoIfip Codigo de la Ifip
     * @param mostrar Mostrar S=SI N=NO
     * @param eliminado Elimiando S=SI N=NO
     * @return  Lista de Transacciones
     */
    public List<Transaccion> getItemsTransaccionEjecucion(long codigoTipoProducto, long codigoMoneda, long codigoIfip,  char eliminado) {
        Query query =  this.em.createNamedQuery(ConceptoTransaccionLote.findByTransaccionEjecucion);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
        
    }
    //------------------------------------------------------------------------

}
