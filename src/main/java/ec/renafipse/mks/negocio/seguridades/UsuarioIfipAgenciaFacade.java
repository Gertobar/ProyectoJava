/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioIfipAgencia;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 * 
 */
@Stateless
public class UsuarioIfipAgenciaFacade extends AbstractFacade<UsuarioIfipAgencia> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioIfipAgenciaFacade() {
        super(UsuarioIfipAgencia.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Metodo que obtiene las Agencias que puede acceder el Usuario
     * @param codigoUsuario Codigo de Usuario
     * @return  Lista de UsuarioIfipAgencia a las que puede acceder el usuario
     */
    public List<UsuarioIfipAgencia> getItemsPermisosIfiAgeUsu(Long codigoUsuario, Long codigoIfip) {
        Query query = this.em.createNamedQuery(UsuarioIfipAgencia.findByAsiIfiAgeUsu);        
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", 'N');
        List<UsuarioIfipAgencia> listUsuarioIfipAgencia = query.getResultList();
        return listUsuarioIfipAgencia;
    }
    
    /**
     * Obtiene los usuarios CAJEROS asignados a la Agencia para fondeo de Caja
     * @param codigoUsuario Codigo de Usuario Responsable de Fondear las Cajas para la Agencia
     * @param puedeAbrirCaja Si el rol puede abrir la caja o no
     * @param codigoIfipAgencia Codigo de la Agencia de la IFIP
     * @param codigoIfip Codigo de la Ifip
     * @param eliminado ELiminado S=SI N=No
     * @return  Lista de Usuarios
     */
    public List<Usuario> getItemsUsuariosFondeoCaja(Long codigoUsuario, char puedeAbrirCaja, Long codigoIfipAgencia, Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(UsuarioIfipAgencia.findByUsuariosFondeoCaja);        
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("puedeAbrirCaja", puedeAbrirCaja);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);       
        return query.getResultList();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
}
