/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioAccesoSistema;
import ec.renafipse.mks.modelo.seguridades.UsuarioEstadoCredito;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistema;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Metodo que busca los datos del usuario de acuerdo al username
     *
     * @param username Nombre del Usuario ingresado en la ventana de Login
     * @return Devuelve una Lista con los usuarios que tienen el username
     * ingresado
     */
    public List<Usuario> getUsuario(String username) {
        List<Usuario> listUsuario;
        Query query = em.createNamedQuery(Usuario.findByUsernameEliminado);
        query.setParameter("username", username);
        query.setParameter("eliminado", 'N');
        listUsuario = query.getResultList();
        return listUsuario;

    }

    /**
     *
     * @param usuario
     * @param codigoSistema
     * @param eliminado
     * @return
     */
    public List<UsuarioSistema> getUsuarioSistemaUsernameEstado(Usuario usuario, Long codigoSistema, char eliminado) {
        Query query = em.createNamedQuery(UsuarioSistema.findByUsuSistemaUsernameEstado);
        query.setParameter("username", usuario.getUsername());
        query.setParameter("codigoSistema", codigoSistema);
        query.setParameter("estado", eliminado);
        return query.getResultList();
    }

    /**
     *
     * @param codigoPersona
     * @return
     */
    public List<Usuario> getUsuarioPersona(Long codigoPersona) {
        Query query = em.createNamedQuery(Usuario.findByUsuarioPersona);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }

    /**
     * Metodo que obtiene los sistemas que tiene un usuario, aqui se valida con
     * la contraseña del usuario
     *
     * @param usuario Codigo del Usuario
     * @param contrasena Contraseña ingresada por la ventana de login
     * @return Devuelve el lista de Usuarios Sistema que contien los sistemas
     * que el usuario puede ingresar
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public List<UsuarioSistema> getUsuarioSistema(Usuario usuario, String contrasena) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        Query query = em.createNamedQuery(UsuarioSistema.findByUsuarioSistema);
        query.setParameter("codigoUsuario", usuario.getCodigo());
        query.setParameter("codigoSistema", Long.parseLong("2"));
        query.setParameter("contrasena", Sesion.MD5(contrasena));
        List<UsuarioSistema> listUsuarioSistema = query.getResultList();
        return listUsuarioSistema;
    }

    /**
     *
     * @param codigoPersona
     * @param codigoIfip Codigo de la IFIP a descriminar (diferente)
     * @param eliminado
     * @return
     */
    public List<Usuario> getItemsUsuarioDiferenteIfipEliminado(Long codigoPersona, Ifip codigoIfip, char eliminado) {
        Query query = em.createNamedQuery(Usuario.findByUsuarioDiferenteIfipEliminado);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    /**
     *
     * @param codigoIfip
     * @param codigoPersona
     * @return
     */
    public List<Usuario> getItemsPersonaIfip(Long codigoIfip, Long codigoPersona) {
        Query query = em.createNamedQuery(Usuario.findByPersonaIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }

    /**
     *
     * @param codigoIfip
     * @param codigoUsuario
     * @param eliminado
     * @return
     */
    public List<Usuario> getItemsUsuarioIfipDifUsuCon(Long codigoIfip, Long codigoUsuario, char eliminado) {
        Query query = em.createNamedQuery(Usuario.findByUsuarioIfipDifUsuCon);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    /**
     *
     * @param codigoIfip
     * @param eliminado
     * @return
     */
    public List<Usuario> getUsuarioEli(Long codigoIfip, char eliminado) {
        Query query = em.createNamedQuery(Usuario.findByIfipEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    /**
     *
     * @param codigoIfip
     * @param eliminado
     * @return
     */
    public List<Usuario> getItemsUsuarioIfipEliminado(Long codigoIfip, char eliminado) {
        Query query = em.createNamedQuery(Usuario.findByUsuarioIfipEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    /**
     *
     * @param username
     * @return
     */
    public List<Usuario> getUsername(String username) {
        Query query = em.createNamedQuery(Usuario.findByUsernameDistinct);
        query.setParameter("username", username);
        return query.getResultList();

    }

 
    public List<Usuario> getUsuariobyCodigo(Long codigo) {
        Query query = em.createNamedQuery(Usuario.findByCodigo);
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }
    
    
    /**
     * 
     * @param codigoEstadoCredito
     * @param codigoUsuario
     * @param eliminado
     * @return 
     */
    public List<UsuarioEstadoCredito> getUsuarioEstadoCredito(Long codigoEstadoCredito, Long codigoUsuario, char eliminado) {
        Query query = em.createNamedQuery(UsuarioEstadoCredito.findByUsuarioEstadoCredito);
        query.setParameter("codigoEstadoCredito", codigoEstadoCredito);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();

    }

    /**
     * Metodo: Inserta el acceso al sistema del usuario
     *
     * @param usuarioAccesoSistema Entidad UsuarioAccesoSistema con los datos
     * del acceso
     */
    public void persistAccesoSistema(UsuarioAccesoSistema usuarioAccesoSistema) {
        try {
            //Hibernate.initialize(usuarioAccesoSistema);
            this.em.persist(usuarioAccesoSistema);
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaNegocio();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeNegocio,
                    new Object[]{"usuarioFacade", "init", CapturaError.getErrorException(ex)});
        }
    }
    
    public Usuario getUsuarioPorUsername (String username) {
        List<Usuario> lista = null;
        Usuario usuario = null;
        Query query = em.createNamedQuery(Usuario.findByUsernameEliminado);
        query.setParameter("username", username);
        query.setParameter("eliminado", 'N');
        if (!query.getResultList().isEmpty()) {
          List<Usuario> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        if (lista != null) {
          usuario = (Usuario)lista.get(0);
        }
        return usuario;        
    }
        
    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
}
