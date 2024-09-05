/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.SeguimientoEstadoComprobante;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vastudillo
 */
@Stateless
public class SeguimientoEstadoComprobanteFacade extends AbstractFacade<SeguimientoEstadoComprobante> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeguimientoEstadoComprobanteFacade() {
        super(SeguimientoEstadoComprobante.class);
    }
    
}
