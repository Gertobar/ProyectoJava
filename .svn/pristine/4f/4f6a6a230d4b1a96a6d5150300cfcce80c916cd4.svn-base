/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.seguridades.negocio.dao;

import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistema;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
public class UsuarioDAO extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    public List<Usuario> getUsuario(String username) {
        List<Usuario> listUsuario  = null;        
        /*Query query = em.createNamedQuery(Usuario.findByUsernameEliminado);
        query.setParameter("username", username);
        query.setParameter("eliminado", 'N');
        listUsuario = query.getResultList();*/
        return listUsuario;        
        
    }
    
    public List<UsuarioSistema> getUsuarioSistema(Usuario usuario, String contrasena) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
       /* Query query = em.createNamedQuery(UsuarioSistema.findByUsuarioSistema);
        query.setParameter("codigoUsuario", usuario.getCodigo());
        query.setParameter("codigoSistema", Long.parseLong("2"));
        query.setParameter("contrasena", Sesion.MD5(contrasena));*/
        return null;        
        
    }
    
}
