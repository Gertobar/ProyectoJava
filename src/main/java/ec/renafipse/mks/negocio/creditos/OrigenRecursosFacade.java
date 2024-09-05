/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.OrigenRecursos;
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
public class OrigenRecursosFacade extends AbstractFacade<OrigenRecursos> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrigenRecursosFacade() {
        super(OrigenRecursos.class);
    }
    
    /**
     * 
     * @param eliminado
     * @return 
     */
    public List<OrigenRecursos> getItemsEliminado(char eliminado)
    {
       return this.em.createNamedQuery(OrigenRecursos.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
}
