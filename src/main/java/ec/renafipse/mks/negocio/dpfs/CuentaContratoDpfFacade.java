/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.dpfs.CuentaContratoDpf;
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
public class CuentaContratoDpfFacade extends AbstractFacade<CuentaContratoDpf> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaContratoDpfFacade() {
        super(CuentaContratoDpf.class);
    }
    /**
     * Retorna las cuentas de los DPFS
     * @param codigoContrato Codigo de Contrato
     * @param codigoIfip Codigo de la Ifip
     * @return Lista de Cuentas de Contratos de Dpfs
     */
    public List<CuentaContratoDpf> itemsCodigoContratoCodigoIfip(Long codigoContrato, long codigoIfip) {
        Query query = this.em.createNamedQuery(CuentaContratoDpf.findByCodigoContratoCodigoIfip);
        query.setParameter("codigoContrato", codigoContrato);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
}
