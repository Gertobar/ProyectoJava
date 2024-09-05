/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

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
public class UsuarioGuiaDepositoFacade extends AbstractFacade<UsuarioGuiaDeposito> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioGuiaDepositoFacade() {
        super(UsuarioGuiaDeposito.class);
    }

    /**
     *
     * @param codigoIfipAgencia
     * @param eliminado
     * @return
     */
    public List<UsuarioGuiaDeposito> getItemsUsuarioGuiaDepositoIfipAgenciaEliminado(Long codigoIfipAgencia, char eliminado) {
        Query query = em.createNamedQuery(UsuarioGuiaDeposito.findByUsuarioIfipAgenciaEliminado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

/**
 * 
 * @param codigoUsuario
 * @param codigoIfipAgencia
 * @param eliminado
 * @return 
 */
    public List<UsuarioGuiaDeposito> getItemsUsuarioResponsableGuiaEliminado(Long codigoUsuario, Long codigoIfipAgencia, char eliminado) {
        Query query = em.createNamedQuery(UsuarioGuiaDeposito.findByUsuarioResponsableGuiaEliminado);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
