/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.RolOpcionOpePorDef;
import ec.renafipse.mks.modelo.seguridades.RolOpcionOperacion;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class RolOpcionOpePorDefFacade extends AbstractFacade<RolOpcionOpePorDef> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolOpcionOpePorDefFacade() {
        super(RolOpcionOpePorDef.class);
    }

    /**
     * 
     * @param codigoRol
     * @param codigoOpcionOpeacion
     * @return 
     */
      public List<RolOpcionOpePorDef> getItemsOpcionOperacionRolMenu(String codigoRol, Long codigoOpcionOpeacion) {
        Query query = em.createNamedQuery(RolOpcionOpePorDef.findByOpcionOpeRolPorDefMenu);
        query.setParameter("codigoRol", codigoRol);       
        query.setParameter("codigoOpcionOperacion", codigoOpcionOpeacion);
        return query.getResultList();
    }
    
}
