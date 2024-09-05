/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.MotivoCambioContrasena;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vicastoc
 */
@Stateless
public class MotivoCambioContrasenaFacade extends AbstractFacade<MotivoCambioContrasena> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MotivoCambioContrasenaFacade() {
        super(MotivoCambioContrasena.class);
    }

    public List<MotivoCambioContrasena> getItemsMotCamContrasena(Long codigo){
        return em.createNamedQuery(MotivoCambioContrasena.findByCodigo).setParameter("codigo", codigo).getResultList();
    }
    
}
