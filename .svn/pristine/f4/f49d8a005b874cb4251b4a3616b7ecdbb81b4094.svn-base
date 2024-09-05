/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.CierreDesgloceEfectivo;
import ec.renafipse.mks.modelo.cajas.ParametroGeneralCaja;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vicastoc
 */
@Stateless
public class ParametroGeneralCajaFacade extends AbstractFacade<ParametroGeneralCaja> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroGeneralCajaFacade() {
        super(ParametroGeneralCaja.class);
    }

    /**
     * Obtiene el parametro General de la Caja de acuerdo al Codigo
     * @param codigo Codigo del Parametro
     * @return Parametro General
     */
    public ParametroGeneralCaja getItemCodigo(Long codigo) {
        return (ParametroGeneralCaja) this.em.createNamedQuery(ParametroGeneralCaja.findByCodigo).setParameter("codigo", codigo).getSingleResult();
    }
}
