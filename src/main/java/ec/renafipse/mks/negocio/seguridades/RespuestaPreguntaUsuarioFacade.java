/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.RespuestaPreguntaUsuario;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vicastoc
 */
@Stateless
public class RespuestaPreguntaUsuarioFacade extends AbstractFacade<RespuestaPreguntaUsuario> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RespuestaPreguntaUsuarioFacade() {
        super(RespuestaPreguntaUsuario.class);
    }
    
}
