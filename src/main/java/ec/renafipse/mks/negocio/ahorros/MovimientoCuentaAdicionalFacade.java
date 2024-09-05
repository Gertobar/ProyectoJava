/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
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
public class MovimientoCuentaAdicionalFacade extends AbstractFacade<MovimientoCuentaAdicional> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoCuentaAdicionalFacade() {
        super(MovimientoCuentaAdicional.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE LOS MOVIMIENTOS QUE SE HAN HECHO CON EL COMPROBANTE
     *
     * @param codigoIfip Codigo de la IFIP
     * @param numeroComprobante
     * @return Lista de Cuentas
     */
    public List<MovimientoCuentaAdicional> getItemsNumeroComprobanteIfip(Long codigoIfip, String numeroComprobante) {
        Query query = this.em.createNamedQuery(MovimientoCuentaAdicional.findByNumeroComprobanteIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("numeroComprobante", numeroComprobante);
        return query.getResultList();
    }

    /**
     * Obtiene los movimientos que no han sido impresos
     *
     *
     * @param codigoCuenta Codigo de la Cuenta
     * @param impreso Estado N
     * @return Lista de Movimientos Adicionales
     */
    public List<MovimientoCuentaAdicional> getItemsMovimientoAdicionalNoImpreso(Long codigoCuenta, char impreso) {
        Query query = this.em.createNamedQuery(MovimientoCuentaAdicional.findByMovimientoAdicionalNoImpreso);
        query.setParameter("codigoCuenta", codigoCuenta);
        query.setParameter("impreso", impreso);
        return query.getResultList();
    }

    /**
     * Obtiene los movimientos que han sido impresos
     *
     *
     * @param codigoCuenta Codigo de la Cuenta
     * @param numeroLibreta Numero de Libreta
     * @param impreso Estado S     * 
     * @return Lista de Movimientos Adicionales
     */
    public List<MovimientoCuentaAdicional> getItemsMovimientoAdicionalImpreso(Long codigoCuenta, String numeroLibreta, char impreso) {
        Query query = this.em.createNamedQuery(MovimientoCuentaAdicional.findByMovimientoAdicionalImpreso);
        query.setParameter("codigoCuenta", codigoCuenta);
        query.setParameter("numeroLibreta", numeroLibreta);
        query.setParameter("impreso", impreso);
        return query.getResultList();
    }
}
