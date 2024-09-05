/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos.lineacredito;

import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitudInfTec;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andy
 */
@Stateless
public class LineaCreditoSolicitudInfTecFacade extends AbstractFacade<LineaCreditoSolicitudInfTec> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public LineaCreditoSolicitudInfTecFacade() {
        super(LineaCreditoSolicitudInfTec.class);
    }   
    
}