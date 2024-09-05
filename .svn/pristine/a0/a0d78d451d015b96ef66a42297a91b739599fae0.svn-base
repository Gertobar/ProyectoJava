/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.EstadoCuenta;
import ec.renafipse.mks.modelo.ahorros.TalonarioLibretaAhorroDet;
import ec.renafipse.mks.modelo.ahorros.TipoFirma;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vicastoc
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE LAS CUENTAS DEL SOCIO
     *
     *
     * @param codigoSocio Codigo del Socio
     * @param codigoIfip Codigo de la IFIP
     * @return Lista de Cuentas
     */
    public List<Cuenta> getItemsCuentasCodigoSocioCodigoIfip(Long codigoSocio, Long codigoIfip) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findByCodigoSocioCodigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }

    /**
     * OBTIENE EL LISTADO DE CUENTAS MEDIANTE EL CODIGO DE SOCIO, IFIP, TIPO DE
     * PRODUCTO Y ESTADO
     *
     * @param codigoSocio Codigo del Socio
     * @param codigoIfip Codigo de la Ifip
     * @param codigoMoneda Codigo de la Moneda
     * @param codigoTipoProducto Codigo del Tipo de Produto; Ahorros,
     * Certificados, etc.
     * @param codigoEstado Codigo del Estado de la Cuenta.
     * @return Listado de Cuentas
     */
    public List<Cuenta> getItemsCuentasSocioIfipTipoProductoEstado(Long codigoSocio, Long codigoIfip, Long codigoMoneda, Long codigoTipoProducto, Long codigoEstado) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findBySocioIfipMonedaTipoProductoEstado);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoEstado", codigoEstado);
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }

    /**
     * OBTIENE EL LISTADO DE CUENTAS PARA LOS MOVIMIENTOS
     *
     * @param codigoIfip Codigo de la Ifip
     * @param codigoMoneda Codigo de la Moneda
     * @param codigoTipoProducto Codigo del Tipo de Produto; Ahorros,
     * Certificados, etc.
     * @param nombreCompleto Nombre del Socio
     * @param indicaActiva Si la cuenta (s) a buscar esten activas S=Si N=No
     * @return Listado de Cuentas
     */
    public List<Cuenta> getItemsMovimientoCuenta(Long codigoIfip, Long codigoMoneda, Long codigoTipoProducto, String nombreCompleto, char indicaActiva) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findByMovimientoCuenta);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("nombreCompleto", nombreCompleto.toUpperCase());
        query.setParameter("indicaActiva", indicaActiva);
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }

    /**
     * OBTIENE EL LISTADO DE CUENTAS PARA LOS ESTADOS DE CUENTAS
     *
     * @param codigoIfip Codigo de la Ifip
     * @param codigoMoneda Codigo de la Moneda
     * @param codigoTipoProducto Codigo del Tipo de Produto; Ahorros,
     * Certificados, etc.
     * @param nombreCompleto Nombre del Socio
     * @return Listado de Cuentas
     */
    public List<Cuenta> getItemsEstadoCuenta(Long codigoIfip, Long codigoMoneda, Long codigoTipoProducto, String nombreCompleto) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findByEstadoCuenta);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("nombreCompleto", nombreCompleto.toUpperCase());
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }

    /**
     * OBTIENE EL LISTADO DE CUENTAS PARA LOS MOVIMIENTOS
     *
     * @param codigoIfip Codigo de la Ifip
     * @param codigoMoneda Codigo de la Moneda
     * @param codigoTipoProducto Codigo del Tipo de Produto; Ahorros,
     * Certificados, etc.
     * @param numero Numero de la cuenta
     * @return Listado de Cuentas
     */
    public List<Cuenta> getItemsNumero(Long codigoIfip, Long codigoMoneda, Long codigoTipoProducto, String numero, Long codigoEstado) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findByNumero);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("numero", numero.toUpperCase());
        query.setParameter("codigoEstado", codigoEstado);
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }
    
        public List<Cuenta> getItemsCuentas(Long codigoIfip, Long codigoMoneda, Long codigoTipoProducto, String numero) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findByNumeroAll);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("numero", numero.toUpperCase());
        //query.setParameter("codigoEstado", codigoEstado);
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }

    /**
     *
     * @param codigoSocio
     * @param codigoIfip
     * @param puedeReactivar
     * @return
     */
    public List<Cuenta> getItemsPuedeReactivar(Long codigoSocio, Long codigoIfip, char puedeReactivar) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findByPuedeReactivar);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("puedeReactivar", puedeReactivar);
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }

    /**
     *
     * @param codigoSocio
     * @param codigoIfip
     * @param codigoTipoProducto
     * @param codigoMoneda
     * @param puedeReactivar
     * @return
     */
    public List<Cuenta> getItemsProductoMonedaPuedeReactivar(Long codigoSocio, Long codigoIfip, Long codigoTipoProducto, Long codigoMoneda, char puedeReactivar) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findByProductoMonedaPuedeReactivar);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("puedeReactivar", puedeReactivar);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }

    /**
     *
     * @param codigoSocio
     * @param codigoIfip
     * @param puedeReactivar
     * @param codigoProducto
     * @param saldoTotal
     * @return
     */
    public List<Cuenta> getItemsCertAprPuedeReactivar(Long codigoSocio, Long codigoIfip, char puedeReactivar, Long codigoProducto, BigDecimal saldoTotal) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findByCertAportPuedeReactivar);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("puedeReactivar", puedeReactivar);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("saldoTotal", saldoTotal);
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }

    /**
     *
     * @param codigoSocio
     * @param codigoIfip
     * @param puedeReactivar
     * @param codigoProducto
     * @return
     */
    public List<Cuenta> getItemsPuedeReactivarAhorros(Long codigoSocio, Long codigoIfip, char puedeReactivar, Long codigoProducto) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findByPuedeReactivarAhorros);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("puedeReactivar", puedeReactivar);
        query.setParameter("codigoProducto", codigoProducto);
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }

    /**
     * Obtiene Cuentas de acuerdo al producto, moneda, ifip y estado
     *
     * @param codigoIfip Codigo de la Ifip
     * @param codigoMoneda Codigo de la Moneda
     * @param codigoTipoProducto Codigo del Producto
     * @param codigoEstado Codigo de Estado
     * @return Lista de Cuentas
     */
    public List<Cuenta> getItemsMonedaProductoIfipEstado(Long codigoIfip, Long codigoMoneda, Long codigoTipoProducto, long codigoEstado) {

        Query query = this.em.createNamedQuery(Cuenta.findByMonedaProductoIfipEstado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoEstado", codigoEstado);

        return query.getResultList();
    }

    /**
     * Obtiene las cuentas de ahorros del socio de acuerdo a su estado
     *
     * @param codigoSocio Codigo del Socio
     * @param codigoIfip Codigo de la Ifip
     * @param codigoEstado Codigo de Estado
     * @return
     */
    public List<Cuenta> getItemsCodigoSocioCodigoIfipCodigoEstado(Long codigoSocio, Long codigoIfip, long codigoEstado) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findByCodigoSocioCodigoIfipCodigoEstado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoEstado", codigoEstado);
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }

    public List<Cuenta> getItemsfindByNumCuentaIfipSocioBenef(Long codigoSocio, Long codigoTipoProducto, Long codigoEstado, Long codigoMoneda, String numero, Long codigoIfip, char esSocioBeneficiario) {
        List<Cuenta> listCuentasSocios;

        Query query = this.em.createNamedQuery(Cuenta.findByNumCuentaIfipSocioBenef);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoEstado", codigoEstado);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("numero", numero);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("esSocioBeneficiario", esSocioBeneficiario);
        listCuentasSocios = query.getResultList();

        return listCuentasSocios;
    }

    // -------------------------------------------------------------     
    @Transactional
    public void actualizaNumeroLibreta(Cuenta cuenta, String numeroLibreta) {

        //System.out.println("Actualiza la Cuenta");
        Query query = em.createQuery("UPDATE Cuenta c SET c.numeroLibreta = :numeroLibreta WHERE codigo = :codigo ");
        query.setParameter("numeroLibreta", numeroLibreta);
        query.setParameter("codigo", cuenta.getCodigo());
        query.executeUpdate();
        //System.out.println("Fin de Actualizar");
    }

    @Transactional
    public void actualizaLibretaTalonario(TalonarioLibretaAhorroDet talonarioLibretaAhorroDet, char estado) {

        //System.out.println("Actualiza el talonario");
        Query query = em.createQuery("UPDATE TalonarioLibretaAhorroDet t SET t.estado = :estado WHERE t.numeroLibreta = :numeroLibreta ");
        query.setParameter("numeroLibreta", talonarioLibretaAhorroDet.getNumeroLibreta());
        query.setParameter("estado", estado);
        query.executeUpdate();
        //System.out.println("Fin de actualizar el talonario");
    }

    /**
     * Actualiza el estado de la cuenta
     *
     * @param codigoEstado Codigo de Estado
     * @param codigo Codigo de la Cuenta
     */
    @Transactional
    public void actualizaEstado(EstadoCuenta codigoEstado, long codigo) {

        //System.out.println("Actualiza el talonario");
        Query query = em.createQuery("UPDATE Cuenta c SET c.codigoEstado = :codigoEstado WHERE c.codigo = :codigo");
        query.setParameter("codigoEstado", codigoEstado);
        query.setParameter("codigo", codigo);
        query.executeUpdate();
        //System.out.println("Fin de actualizar el talonario");
    }

    /**
     * Actualiza el benefiaciario del socio
     *
     * @param esSocioBeneficiario Si es socio beneficiario
     * @param personaBeneficiario Codigo de Persona del Benefiaciario     
     * @param codigo codigo de la cuenta
     */
    @Transactional
    public void actualizaBeneficiario(char esSocioBeneficiario, Persona personaBeneficiario, long codigo) {

        Query query;
        if (personaBeneficiario != null) {
            query = em.createQuery("UPDATE Cuenta c SET c.esSocioBeneficiario =:esSocioBeneficiario, c.codigoPersonaBeneficiario = :codigoPersonaBeneficiario  WHERE c.codigo = :codigo");
            query.setParameter("esSocioBeneficiario", esSocioBeneficiario);
            query.setParameter("codigoPersonaBeneficiario", personaBeneficiario.getCodigo());
            query.setParameter("codigo", codigo);
        } else {
            query = em.createQuery("UPDATE Cuenta c SET c.esSocioBeneficiario =:esSocioBeneficiario, c.codigoPersonaBeneficiario = null  WHERE c.codigo = :codigo");
            query.setParameter("esSocioBeneficiario", esSocioBeneficiario);
            query.setParameter("codigo", codigo);
        }
        query.executeUpdate();
    }

    /**
     * Actualiza el tipo de firmante de la cuenta
     *
     * @param tipoPirma Tipo de Firma
     * @param codigo Codigo de la cuenta
     */
    public void actualizaTipoFirma(TipoFirma tipoPirma, long codigo) {

        //System.out.println("Actualiza el talonario");
        Query query = em.createQuery("UPDATE Cuenta c SET c.codigoTipoFirma =:tipoPirma  WHERE c.codigo = :codigo");
        query.setParameter("tipoPirma", tipoPirma);
        query.setParameter("codigo", codigo);
        query.executeUpdate();
    }
    
    /**
     * 
     * @param codigoPersona
     * @param codigoIfip
     * @param codigoEstado
     * @return 
     */
    public List<Cuenta> getItemsCodigoPersonaCodigoIfipCodigoEstado(Long codigoPersona, Long codigoIfip, Long codigoEstado) {
        List<Cuenta> listCuentasPersonas;
        Query query = this.em.createNamedQuery("Cuenta.findByCodigoPersonaCodigoIfipCodigoEstado", Cuenta.class);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoEstado", codigoEstado);
        listCuentasPersonas = query.getResultList();
        return listCuentasPersonas;
    }
}
