/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.ChequeDepositado;
import ec.renafipse.mks.modelo.cajas.GuiaDepositoChequeDes;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
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
public class GuiaDepositoChequeDesFacade extends AbstractFacade<GuiaDepositoChequeDes> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GuiaDepositoChequeDesFacade() {
        super(GuiaDepositoChequeDes.class);
    }

    /**
     *
     * @param codigoMoneda
     * @param codigoEstado
     * @param fechaEfectivizacion
     * @return
     */
    public List<ChequeDepositado> getItemsChequesEmitidosGuia(Long codigoMoneda, Long codigoEstado, Date fechaEfectivizacion, Long codigoIfipAgencia) {
        Query query = this.em.createNamedQuery(GuiaDepositoChequeDes.findByChequesEmitidosGuia);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoEstado", codigoEstado);
        query.setParameter("fechaEfectivizacion", fechaEfectivizacion);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();
    }

    /**
     *
     * @param codigoIfipAgencia
     * @param codigoMoneda
     * @param codigoEntFin
     * @param codigoEstado
     * @param codigoUsuarioPosee
     * @param fechaGuia
     * @return
     */
    public List<ChequeDepositado> getItemsChequesGuiaProtesto(Long codigoIfipAgencia, Long codigoMoneda, Long codigoEntFin, Long codigoEstado, Long codigoUsuarioPosee, Date fechaGuia) {
        Query query = this.em.createNamedQuery(GuiaDepositoChequeDes.findByChequesGuiaProtesto);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("codigoEntFin", codigoMoneda);
        query.setParameter("codigoMoneda", codigoEntFin);
        query.setParameter("codigoEstado", codigoEstado);
        query.setParameter("codigoUsuarioPosee", codigoUsuarioPosee);
        query.setParameter("fechaGuia", fechaGuia);
        return query.getResultList();
    }
}
