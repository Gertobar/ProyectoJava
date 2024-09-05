/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.UsuarioEfeProChe;
import ec.renafipse.mks.modelo.seguridades.UsuarioGuiaDeposito;
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
public class UsuarioEfeProCheFacade extends AbstractFacade<UsuarioEfeProChe> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioEfeProCheFacade() {
        super(UsuarioEfeProChe.class);
    }

    /**
     *
     * @param codigoUsuario
     * @param codigoIfipAgencia
     * @param eliminado
     * @return
     */
    public List<UsuarioEfeProChe> getItemsUsuarioResponsableEfectizarEliminado(Long codigoUsuario, Long codigoIfipAgencia, char eliminado) {
        Query query = em.createNamedQuery(UsuarioEfeProChe.findByUsuarioResponsableEfectivizacionEliminado);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    /**
     * 
     * @param codigoIfipAgencia
     * @param eliminado
     * @return 
     */
    public List<UsuarioEfeProChe> getItemsUsuarioResponsableEfectizar(Long codigoIfipAgencia, char eliminado) {
        Query query = em.createNamedQuery(UsuarioEfeProChe.findByUsuarioResponsableEfectivizacion);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

}
