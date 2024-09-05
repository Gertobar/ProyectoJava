/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.MovimientoCuenta;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
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
 * @author vicastoc
 */
@Stateless
public class MovimientoCuentaFacade extends AbstractFacade<MovimientoCuenta> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoCuentaFacade() {
        super(MovimientoCuenta.class);
    }

    // -------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * METODO QUE DEVULEVE EL ESTADO DE CUENTAS DEL SOCIO
     *
     * @param codigoCuenta Codigo de la Cuenta
     * @param fechaInicio Fecha de Inicio
     * @param fechaFin Fecha de Fin
     * @return Lista de Movimientos
     */
    public List<MovimientoCuenta> getItemsEstadoDeCuenta(Long codigoCuenta, Date fechaInicio, Date fechaFin) {
        Query query = this.em.createNamedQuery(MovimientoCuenta.findByEstadoDeCuenta);
        query.setParameter("codigoCuenta", codigoCuenta);
        query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
        query.setParameter("fechaFin", fechaFin, TemporalType.DATE);

        return query.getResultList();
    }

    // -------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * METODO QUE DEVUELVE EL CODIGO DE MOVIMIENTO ANTERIOR AL PRIMERO DEL
     * ESTADO DE CUENTAS, CON EL FIN DE OBTENER EL SALDO INICIAL DEL ESTADO DE
     * CUENTAS
     *
     * @param codigoMovimiento Primer Codigo de Movimiento del Resultado del
     * Estado de Cuentas.
     * @param codigoCuenta Codigo de la Cuenta
     * @return Codigo de Movimiento Tipo LONG
     */
    public Long getCodigoAnteriorDelPrimeroEstadoCuentas(Long codigoMovimiento, Long codigoCuenta) {
        Long codigoMovimientoAnterior;
        Query query = this.em.createNamedQuery(MovimientoCuenta.getByCodigoAnteriorDelPrimeroEstadoCuentas);
        query.setParameter("codigoMovimiento", codigoMovimiento);
        query.setParameter("codigoCuenta", codigoCuenta);
        List<Object> listaObjetos = query.getResultList();        
        codigoMovimientoAnterior = (listaObjetos == null) ? null : ((listaObjetos.get(0) != null) ? Long.parseLong(listaObjetos.get(0).toString()) : null);

        return codigoMovimientoAnterior;
    }

    /**
     * Estado del cuenta del socio que no este impreso
     *
     * @param codigoCuenta
     * @param impreso
     * @return
     */
    /*public List<MovimientoCuenta> getItemsEstadoDeCuentaImpreso(Long codigoCuenta, char impreso) {
        Query query = this.em.createNamedQuery(MovimientoCuenta.findByEstadoDeCuentaImpreso);
        query.setParameter("codigoCuenta", codigoCuenta);
        query.setParameter("impreso", impreso);
        return query.getResultList();
    }*/

    /*public List<MovimientoCuentaAdicional> getItemsMovimientosLibretaImpreso(Long codigoCuenta, char impreso) {
        Query query = this.em.createNamedQuery(MovimientoCuenta.findByMovimientoAdicionalNoImpresa);
        query.setParameter("codigoCuenta", codigoCuenta);
        query.setParameter("impreso", impreso);
        return query.getResultList();
    }*/

}
