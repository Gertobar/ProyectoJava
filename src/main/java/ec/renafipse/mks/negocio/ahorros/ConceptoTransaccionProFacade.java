/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro;
import ec.renafipse.mks.modelo.ahorros.Transaccion;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class ConceptoTransaccionProFacade extends AbstractFacade<ConceptoTransaccionPro> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConceptoTransaccionProFacade() {
        super(ConceptoTransaccionPro.class);
    }


    /**
     *
     * @param codigoMoneda
     * @param codigoTipoProducto
     * @param eliminado
     * @return
     */
    public List<Transaccion> getItemsTransaccionesPermisosRol(Long codigoTipoProducto, Long codigoMoneda, char eliminado) {
        Query query = this.em.createNamedQuery(ConceptoTransaccionPro.findByTransaccionesPermisosRol);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    public ConceptoTransaccionPro getIConceptoTransaccionPro(Long codigoTipoProducto, Long codigoMoneda, Long codigoConcepto) {
        Query query = this.em.createNamedQuery(ConceptoTransaccionPro.findByPK);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoConcepto", codigoConcepto);
        try {
            ConceptoTransaccionPro conceptoTransaccionPro =(ConceptoTransaccionPro) query.getSingleResult();
            return conceptoTransaccionPro == null ? null : conceptoTransaccionPro;
        } catch (NoResultException e) {
            return null;
        }
    }
    
     public List<ConceptoTransaccionPro> getItemsContabilizaExistentesConceptoTransaccionProIfi(Long codigoTipoProducto, Long codigoMoneda, Long codigoTransaccion, char eliminado, char contabilizaSN) {
        Query query = this.em.createNamedQuery(ConceptoTransaccionPro.findByTransaccionesContabilizada);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoTransaccion", codigoTransaccion);
        query.setParameter("eliminado", eliminado);
        query.setParameter("contabilizaSN", contabilizaSN);
        return query.getResultList();
    }
     
     public List<ConceptoTransaccionPro> getItemsPermisosExistentesConceptoTransaccionProIfi(Long codigoTipoProducto, Long codigoMoneda, Long codigoTransaccion, char eliminado) {
        Query query = this.em.createNamedQuery(ConceptoTransaccionPro.findByConceptosPermisosRol);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoTransaccion", codigoTransaccion);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
     
      public List<ConceptoTransaccionPro> getItemsCodigoTipoProductoMoneda(Long codigoTipoProducto, Long codigoMoneda) {
        Query query = this.em.createNamedQuery(ConceptoTransaccionPro.findByCodigoTipoProductoMoneda);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        return query.getResultList();
    }
}
