/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.DestinoEspecifico;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andres
 */
@Stateless
public class DestinoEspecificoFacade extends AbstractFacade<DestinoEspecifico> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DestinoEspecificoFacade() {
        super(DestinoEspecifico.class);
    }
    
    /**
     * 
     * @param eliminado
     * @return 
     */
    public List<DestinoEspecifico> getListaEliminado(String eliminado) {
        Query query = this.em.createNamedQuery("DestinoEspecifico.findByEliminado", DestinoEspecifico.class);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
