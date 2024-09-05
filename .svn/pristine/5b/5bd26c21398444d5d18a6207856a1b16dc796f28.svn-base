/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.ProcesoContable;
import ec.renafipse.mks.modelo.contables.ProcesoContableConTraPro;
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
public class ProcesoContableConTraProFacade extends AbstractFacade<ProcesoContableConTraPro> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoContableConTraProFacade() {
        super(ProcesoContableConTraPro.class);
    }
    
   
     public List<ProcesoContableConTraPro> getItemsProcesosContablesExistentesMonedaTipoProductoTransaccionConcepto(Long codigoMoneda,Long codigoTipoProducto,
             Long codigoTransaccion,Long codigoConcepto, char eliminado ) {
        Query query = em.createNamedQuery(ProcesoContableConTraPro.findByProcesoContableMonedaTipoProductoTransaccionConcepto);       
        query.setParameter("codigoMoneda", codigoMoneda);  
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoTransaccion", codigoTransaccion);
        query.setParameter("codigoConcepto", codigoConcepto);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
     public List<ProcesoContableConTraPro> getItemsProcesosContablesExistentesMonedaTipoProductoTransaccionConceptoTodos(Long codigoMoneda,Long codigoTipoProducto,
             Long codigoTransaccion,Long codigoConcepto) {
        Query query = em.createNamedQuery(ProcesoContableConTraPro.findByProcesoContableMonedaTipoProductoTransaccionConceptotodos);       
        query.setParameter("codigoMoneda", codigoMoneda);  
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoTransaccion", codigoTransaccion);
        query.setParameter("codigoConcepto", codigoConcepto);        
        return query.getResultList();
    }
}
