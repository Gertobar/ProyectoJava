/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.modelo.cajas.FondeoCaja;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDesgloceEfectivo;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDesgloceEfectivoPK;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDetalle;
import ec.renafipse.mks.modelo.cajas.FraccionMoneda;
import ec.renafipse.mks.modelo.seguridades.UsuarioIfipAgencia;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class FraccionMonedaFacade extends AbstractFacade<FraccionMoneda> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FraccionMonedaFacade() {
        super(FraccionMoneda.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Metodo que obtiene las Fracciones de las Monedas
     *
     * @param codigoMoneda Codigo de la Moneda
     * @param eliminado Eliminado S=SI N=No
     * @return Lista de Fracciones de Monedas
     */
    public List<FraccionMoneda> getItemsEliminadoTipoFranccionMoneda(Long codigoMoneda, char eliminado) {
        Query query = this.em.createNamedQuery(FraccionMoneda.findByEliminadoTipoFranccionMoneda);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

   
}
