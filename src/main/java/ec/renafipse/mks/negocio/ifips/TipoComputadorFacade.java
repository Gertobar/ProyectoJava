/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.TipoComputador;
import ec.renafipse.mks.modelo.ifips.TipoSistemaOperativo;
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
public class TipoComputadorFacade extends AbstractFacade<TipoComputador> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoComputadorFacade() {
        super(TipoComputador.class);
    }
    
        //METODO PERSONALIZADO SC
    public List<TipoComputador> getItemsSistemaOperativoEliminado (char eliminado)
    {
        Query query=em.createNamedQuery(TipoComputador.findByEliminado);
        query.setParameter("eliminado",eliminado);
        return query.getResultList();
    }
    
}
