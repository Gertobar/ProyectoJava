/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.BloqueoCuentaUsuario;
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
public class BloqueoCuentaUsuarioFacade extends AbstractFacade<BloqueoCuentaUsuario> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BloqueoCuentaUsuarioFacade() {
        super(BloqueoCuentaUsuario.class);
    }
    
        /**
     * 
     * @param usuarioSistema
     * @param vigente
     * @return 
     */
    public List<BloqueoCuentaUsuario> getIemsBloCuenUsuVig(UsuarioSistema usuarioSistema, char vigente){
        Query query = this.em.createNamedQuery(BloqueoCuentaUsuario.findByBloqueoCuenUsuVig);
        query.setParameter("usuarioSistema", usuarioSistema);
        query.setParameter("vigente", vigente);
        return query.getResultList();
    }
    
    
}
