/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.ExpiracionContrasena;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistema;
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
public class ExpiracionContrasenaFacade extends AbstractFacade<ExpiracionContrasena> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExpiracionContrasenaFacade() {
        super(ExpiracionContrasena.class);
    }

    /**
     * 
     * @param usuarioSistema
     * @param vigente
     * @return 
     */
    public List<ExpiracionContrasena> getIemsExpiracionConUsuSisVig(UsuarioSistema usuarioSistema, char vigente){
        Query query = this.em.createNamedQuery(ExpiracionContrasena.findByExpiracionConUsuSisVig);
        query.setParameter("usuarioSistema", usuarioSistema);
        query.setParameter("vigente", vigente);
        return query.getResultList();
    }
    
    

}
