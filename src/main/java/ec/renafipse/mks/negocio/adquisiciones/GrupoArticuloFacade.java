/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.GrupoArticulo;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class GrupoArticuloFacade extends AbstractFacade<GrupoArticulo> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoArticuloFacade() {
        super(GrupoArticulo.class);
    }
    
    public List<GrupoArticulo> getItemsAllGrupoArticulo() {
        Query query = em.createNamedQuery(GrupoArticulo.findByAllGrupoArticulo);
         
        return query.getResultList();
    }
    
     public List<GrupoArticulo> getItemsElimiando(char eliminado) {
        return  em.createNamedQuery(GrupoArticulo.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
}
