/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.SustentoTributario;
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
public class SustentoTributarioFacade extends AbstractFacade<SustentoTributario> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SustentoTributarioFacade() {
        super(SustentoTributario.class);
    }
    public List<SustentoTributario> getItemsSustentoTribEliminado(char eliminado) {
        Query query = em.createNamedQuery(SustentoTributario.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
}
