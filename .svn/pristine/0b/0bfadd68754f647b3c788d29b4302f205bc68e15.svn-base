/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.dpfs.MotivoPrecancelacion;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author FliaAstudillo
 */
@Stateless
public class MotivoPrecancelacionFacade extends AbstractFacade<MotivoPrecancelacion> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MotivoPrecancelacionFacade() {
        super(MotivoPrecancelacion.class);
    }

    public List<MotivoPrecancelacion> getItemsEliminado(char eliminado) {
        return this.em.createNamedQuery(MotivoPrecancelacion.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }

}
