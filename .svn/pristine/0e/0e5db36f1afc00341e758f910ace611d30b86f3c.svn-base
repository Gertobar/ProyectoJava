/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.auditorias;

import ec.renafipse.mks.modelo.auditorias.LineaCreditoSolicitudEstado;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andres
 */
@Stateless
public class LineaCreditoSolicitudEstadoFacade extends AbstractFacade<LineaCreditoSolicitudEstado> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaCreditoSolicitudEstadoFacade() {
        super(LineaCreditoSolicitudEstado.class);
    }
    
}
