/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.FirmanteCuenta;
import ec.renafipse.mks.modelo.ahorros.FirmanteCuentaPK;
import ec.renafipse.mks.modelo.ahorros.TipoFirma;
import ec.renafipse.mks.negocio.AbstractFacade;
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
public class FirmanteCuentaFacade extends AbstractFacade<FirmanteCuenta> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FirmanteCuentaFacade() {
        super(FirmanteCuenta.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE LOS FIRMANTES DE UNA CUENTA
     *
     * @param codigoCuenta Codigo de la Cuenta
     * @return Lista de Firmas
     */
    public List<FirmanteCuenta> getItemsFirmanteCuenta(Long codigoCuenta) {

        System.out.println("codigoCuenta  en busqueda de firmantes "+codigoCuenta);
        Query query = this.em.createNamedQuery(FirmanteCuenta.findByCodigoCuenta);
        query.setParameter("codigoCuenta", codigoCuenta);
        return query.getResultList();
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE LOS FIRMANTES DE UNA CUENTA DE ACUERDO AL ESTADO ELIMINADO
     *
     * @param codigoCuenta Codigo de la Cuenta
     * @param eliminado S=Si N=No
     * @return Lista de Firmas
     */
    public List<FirmanteCuenta> getItemsCodigoCuentaEliminado(Long codigoCuenta, char eliminado) {
        List<FirmanteCuenta> listFirmanteCuentas;
        Query query = this.em.createNamedQuery(FirmanteCuenta.findByCodigoCuentaEliminado);
        query.setParameter("codigoCuenta", codigoCuenta);
        query.setParameter("eliminado", eliminado);
        listFirmanteCuentas = query.getResultList();

        return listFirmanteCuentas;
    }

    @Transactional
    public void actualizaFirmante(FirmanteCuentaPK codigo, Long codigoTipoRel, char eliminado) {
        FirmanteCuenta firmante = super.find(codigo);
        firmante.setCodigoTipRel(codigoTipoRel);
        firmante.setEliminado(eliminado);
    }
}
