/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.ifips.IfipCuentaEntidadFinanciera;
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
public class IfipCuentaEntidadFinancieraFacade extends AbstractFacade<IfipCuentaEntidadFinanciera> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IfipCuentaEntidadFinancieraFacade() {
        super(IfipCuentaEntidadFinanciera.class);
    }

    /**
     * Obtiene las Cuentas de la Entidad Financiera y que Exista en la Agencia
     *
     * @param codigoIfipAgencia Codigo de la Agencia
     * @param codigoIfip Codigo de la Ifip
     * @param eliminado Eliminado
     * @return Lista de Cuentas de la Entidad Financiera en la IFIP
     */
    public List<IfipCuentaEntidadFinanciera> getItemsCuentaFinancieraIfipAgencia(Long codigoIfipAgencia, Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(IfipCuentaEntidadFinanciera.findByCuentaFinancieraIfipAgencia);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    public List<EntidadFinanciera> getItemsIfipEntidadAgenciaCuentaEntFinEliminado(Long codigoIfip, Long codigoIfipAgencia,  char eliminado) {
        Query query = this.em.createNamedQuery(IfipCuentaEntidadFinanciera.findEntidadByIfipAgenciaIifpEliminado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public List<IfipCuentaEntidadFinanciera> getItemsIfipEntidadAgenciaCuentaEntFinEliminado(Long codigoIfip, Long codigoIfipAgencia, char eliminado, Long codEntidad) {
        Query query = em.createNamedQuery(IfipCuentaEntidadFinanciera.findCodCueByIfipAgenciaIifpEliminadoEntFin);
        query.setParameter("codigoEntidadFinanciera", codEntidad);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
 /*
    public List<IfipCuentaEntidadFinanciera> getItemsIfipCodCuentaByIfipAgenciaCuentaEntFinElicodPCD(Long codigoIfip, Long codigoIfipAgencia, char eliminado, Long codEntidad, String numeroCuenta) {
        Query query = em.createNamedQuery(IfipCuentaEntidadFinanciera.findByEntFin);
        query.setParameter("codEntidad", codEntidad);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("numeroCuenta", numeroCuenta);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    */
    
    
}
