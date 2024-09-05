/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.ProveedorCuentaEntFin;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
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
 * @author renafipse1
 */
@Stateless
public class ProveedorCuentaEntFinFacade extends AbstractFacade<ProveedorCuentaEntFin> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorCuentaEntFinFacade() {
        super(ProveedorCuentaEntFin.class);
    }
    
   public List<ProveedorCuentaEntFin> getItemsCuentaEntfinbyCodigoProveedor(Long codigoProveedor) {
        Query query = em.createNamedQuery(ProveedorCuentaEntFin.findByCodigoProveedor);
        query.setParameter("codigoProveedor", codigoProveedor);
        return query.getResultList();
   }
   
    public List<EntidadFinanciera> getItemsCuentaEntfinbyCodigoProveedorEstado(Long codigoProveedor, char eliminado) {
        Query query = em.createNamedQuery(ProveedorCuentaEntFin.findByCodProveedorEliminado);
        query.setParameter("codigoProveedor", codigoProveedor);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
        public List<String> getItemsCuentaEntfinfindByCodProvEliminaCodEntida(Long codigoProveedor, Long codigoEntidad, char eliminado) {
        Query query = em.createNamedQuery(ProveedorCuentaEntFin.findByCodProvEliminaCodEntidad);
        query.setParameter("codigoProveedor", codigoProveedor);
        query.setParameter("codigoEntidad", codigoEntidad);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
