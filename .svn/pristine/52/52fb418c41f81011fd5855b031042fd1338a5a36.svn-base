/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.ChequeDepositado;
import ec.renafipse.mks.modelo.cajas.EstadoChequeDepositado;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
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
public class ChequeDepositadoFacade extends AbstractFacade<ChequeDepositado> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChequeDepositadoFacade() {
        super(ChequeDepositado.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE LOS CHEQUES DE LA ENTIDAD FINANCIERA CON ESE NUMERO Y CON LA
     * CUENTA
     *
     * @param codigoEntidadFinanciera Codigo de la Entidad Financiera
     * @param numeroCheque Numero del Cheque
     * @param numeroCuenta Numero de la Cuenta del Cheque
     * @return Lista de Cheques Depositados
     */
    public List<ChequeDepositado> getItemsEntFinumeroChequeNumeroCuenta(Long codigoEntidadFinanciera, String numeroCheque, String numeroCuenta) {
        Query query = this.em.createNamedQuery(ChequeDepositado.findByEntFinumeroChequeNumeroCuenta);
        query.setParameter("codigoEntidadFinanciera", codigoEntidadFinanciera);
        query.setParameter("numeroCheque", numeroCheque);
        query.setParameter("numeroCuenta", numeroCuenta);
        return query.getResultList();
    }

    /**
     *
     * @param codigoMoneda
     * @param codigoEstado
     * @param codigoUsuarioMovimiento
     * @return
     */
    public List<ChequeDepositado> getItemsChequeDepositadoATransferir(Long codigoMoneda, EstadoChequeDepositado codigoEstado, Long codigoUsuarioMovimiento) {
        Query query = this.em.createNamedQuery(ChequeDepositado.findByChequeDepositadoATransferir);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoEstado", codigoEstado);
        query.setParameter("codigoUsuarioMovimiento", codigoUsuarioMovimiento);
        return query.getResultList();
    }

    /**
     *
     * @param codigoMoneda
     * @param codigoEstado
     * @param codigoUsuarioPosee
     * @return
     */
    public List<ChequeDepositado> getItemsChequesTransferidos(Long codigoMoneda, Long codigoEstado, Long codigoUsuarioPosee) {
        Query query = this.em.createNamedQuery(ChequeDepositado.findByChequesTransferidos);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoEstado", codigoEstado);
        query.setParameter("codigoUsuarioPosee", codigoUsuarioPosee);
        return query.getResultList();
    }

    /**
     *
     * @param codigoIfipAgencia
     * @param codigoMoneda
     * @param codigoEntFin
     * @param codigoEstado
     * @param fechaGuia
     * @return
     */
    public List<ChequeDepositado> getItemsChequesAGuiaEntFin(Long codigoIfipAgencia, Long codigoMoneda, Long codigoEntFin, Long codigoEstado, Date fechaGuia) {
        Query query = this.em.createNamedQuery(ChequeDepositado.findByChequesEnGuiEntFin);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoEntFin", codigoEntFin);
        query.setParameter("codigoEstado", codigoEstado);
        query.setParameter("fechaGuia", fechaGuia);
        return query.getResultList();
    }

    /**
     *
     * @param codigoIfipAgencia
     * @param codigoIfip
     * @param codigoCheque
     * @return
     */
    public List<ChequeDepositado> getItemsSocioCuenta(Long codigoIfipAgencia, Long codigoIfip, Long codigoCheque) {
        Query query = this.em.createNamedQuery(ChequeDepositado.findBySocioIfipEntFinCheque);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoCheque", codigoCheque);
        return query.getResultList();
    }

    /**
     *
     * @param codigoIfipAgencia
     * @param estadoCheque
     * @return
     */
    public List<EntidadFinanciera> getItemsEntFinChequeProtesto(Long codigoIfipAgencia, Long estadoCheque) {
        Query query = em.createNamedQuery(ChequeDepositado.findByEntFinChequeUsuarioProtesto);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("estadoCheque", estadoCheque);
        return query.getResultList();
    }

    /**
     * 
     * @param codigoCheque
     * @return 
     */
    public ChequeDepositado getValorCheque(Long codigoCheque) {
        Query query = this.em.createNamedQuery(ChequeDepositado.findByCodigo);
        query.setParameter("codigo", codigoCheque);
        return query.getSingleResult() == null ? null : (ChequeDepositado) query.getSingleResult();
    }
    
    /**
     * Obtiene los cheques transferidos
     * @param codigoMoneda Coldigo de Moneda
     * @param codigoEstado Codigo de Estado
     * @param codigoIfipAgencia
     * @param codigoIfip
     * @return Lista de Cheques
     */
    public List<ChequeDepositado> getItemsChequesTransferidosGuia(Long codigoMoneda, Long codigoEstado, Long codigoIfipAgencia, Long codigoIfip) {
        Query query = this.em.createNamedQuery(ChequeDepositado.findByChequesTransferidosGuia);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoEstado", codigoEstado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
}
