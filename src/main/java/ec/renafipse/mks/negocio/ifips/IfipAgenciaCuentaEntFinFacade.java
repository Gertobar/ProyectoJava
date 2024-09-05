/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.ifips.IfipAgenciaCuentaEntFin;
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
public class IfipAgenciaCuentaEntFinFacade extends AbstractFacade<IfipAgenciaCuentaEntFin> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IfipAgenciaCuentaEntFinFacade() {
        super(IfipAgenciaCuentaEntFin.class);
    }

    /**
     *
     * @param codigoIfipAgencia
     * @param eliminado
     * @return
     */
    public List<IfipAgenciaCuentaEntFin> getItemsIfipAgenciaCuentaEntFinEliminado(long codigoIfipAgencia, char eliminado) {
        Query query = em.createNamedQuery(IfipAgenciaCuentaEntFin.findByIfipAgenciaCuentaEntFinEliminado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    /**
     * lista todas las entidades financieras con las culaes esta ifip esta
     * relacionadad
     *
     * @param codigoIfipAgencia
     * @param eliminado
     * @return
     */
    public List<EntidadFinanciera> getItemsIfipEntidadAgenciaCuentaEntFinEliminado(long codigoIfipAgencia, char eliminado) {
        Query query = em.createNamedQuery(IfipAgenciaCuentaEntFin.findEntidadByIfipAgenciaCuentaEntFinEliminado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    
    /**
     * lista todas las cuentas de la Agencia que tienen con una respectiva entidad financiera
     * @param codigoIfipAgencia
     * @param eliminado
     * @param codEntidad
     * @return 
     */
    public List<Long> getItemsIfipEntidadAgenciaCuentaEntFinEliminado(Long codigoIfipAgencia, char eliminado, Long codEntidad) {
        Query query = em.createNamedQuery(IfipAgenciaCuentaEntFin.findEntidadByIfipAgenciaCuentaEntFinElicodPCD);
        query.setParameter("codEntidad", codEntidad);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public List<IfipAgenciaCuentaEntFin> getItemsIfipCodCuentaByIfipAgenciaCuentaEntFinElicodPCD(Long codigoIfipAgencia, char eliminado, Long codEntidad, String numeroCuenta) {
        Query query = em.createNamedQuery(IfipAgenciaCuentaEntFin.findCodCuentaByIfipAgenciaCuentaEntFinElicodPCD);
        query.setParameter("codEntidad", codEntidad);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("numeroCuenta", numeroCuenta);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
