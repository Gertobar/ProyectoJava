/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.Usuario;
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
public class UsuarioSistemaFacade extends AbstractFacade<UsuarioSistema> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioSistemaFacade() {
        super(UsuarioSistema.class);
    }

    /**
     *
     * @param codigoUsuario
     * @param codigoSistema
     * @param contrasena
     * @return
     */
    public List<UsuarioSistema> getItemsUsuarioSistema(Long codigoUsuario, Long codigoSistema, String contrasena) {
        Query query = em.createNamedQuery(UsuarioSistema.findByUsuarioSistema);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("codigoSistema", codigoSistema);        
        return query.getResultList();
    }

    /**
     *
     * @param nombreCompleto
     * @param codigoIfip
     * @param codigoSistema
     * @return
     */
    public List<UsuarioSistema> getItemsUsuarios(String nombreCompleto, Long codigoIfip, Long codigoSistema) {
        Query query = this.em.createNamedQuery(UsuarioSistema.findByUsuarioSistemaNomIfi);
        query.setParameter("nombreCompleto", nombreCompleto.toUpperCase());
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoSistema", codigoSistema);
        return query.getResultList();
    }

    /**
     *
     * @param nombreCompleto
     * @param codigoIfip
     * @param codigoSistema
     * @param codigoUsuario
     * @return
     */
    public List<UsuarioSistema> getItemsUsuariosExpCon(String nombreCompleto, Long codigoIfip, Long codigoSistema, Long codigoUsuario) {
        Query query = this.em.createNamedQuery(UsuarioSistema.findByUsuarioSisNomIfiExpCon);
        query.setParameter("nombreCompleto", nombreCompleto.toUpperCase());
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoSistema", codigoSistema);
        query.setParameter("codigoUsuario", codigoUsuario);
        return query.getResultList();
    }

    /**
     * 
     * @param codigoUsuario
     * @param estado
     * @return 
     */
    public List<UsuarioSistema> getItemsUsuarioSistemaSel(Long codigoUsuario,  char estado) {
        Query query = this.em.createNamedQuery(UsuarioSistema.findByUsuSisEliminado);
        query.setParameter("codigoUsuario", codigoUsuario);      
        query.setParameter("estado", estado);  
        return query.getResultList();
    }
    
    /**
     * 
     * @param codigoSistema
     * @param codigoIfip
     * @param codigoUsuario
     * @param eliminado
     * @return  Listado de Usuarios
     */
    public List<Usuario> getItemsUsuSisBoveda(Long codigoSistema, Long codigoIfip, Long codigoUsuario,  char eliminado) {
        Query query = this.em.createNamedQuery(UsuarioSistema.findByUsuSisBoveda);
        query.setParameter("codigoSistema", codigoSistema);      
        query.setParameter("codigoIfip", codigoIfip);  
        query.setParameter("codigoUsuario", codigoUsuario);  
        query.setParameter("eliminado", eliminado);  
        return query.getResultList();
    }
    
    /**
     * 
     * @param codigoUsuario
     * @param codigoSistema
     * @return 
     */
    public UsuarioSistema getUsuarioSistemaPorCodigoUsuarioCodigoSistema(Long codigoUsuario, Long codigoSistema){
        List<UsuarioSistema> lista = null;
        UsuarioSistema usuarioSistema = null;
        Query query = em.createNamedQuery("UsuarioSistema.findByCodigoUsuarioCodigoSistema", UsuarioSistema.class);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("codigoSistema", codigoSistema);
        if (!query.getResultList().isEmpty()) {
          List<UsuarioSistema> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        if (lista != null) {
          usuarioSistema = (UsuarioSistema)lista.get(0);
        }
        return usuarioSistema;
    }
}
