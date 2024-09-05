/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.TipoVinculado;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Miguel Saca
 */
@Stateless
public class TipoVinculadoFacade extends AbstractFacade<TipoVinculado> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    public TipoVinculadoFacade() {
        super(TipoVinculado.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
