/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.Boveda;

import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Viajes2
 */
@Stateless
public class BovedaFacade extends AbstractFacade<Boveda> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BovedaFacade() {
        super(Boveda.class);
    }

    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene las bovedas a las cuales es responsable el usuario en la Agencia
     *
     * @param codigoIfipAgencia Codigode la Agencia de la IFIP
     * @param responsable Codigo de Usuario Responsable
     * @param eliminado S=SI N=NO
     * @return Lista de Bovedas.
     */
    public List<Boveda> getItemsResponsableIfipAgenciaEliminado(Long codigoIfipAgencia, Long responsable, char eliminado) {
        Query query = em.createNamedQuery(Boveda.findByResponsableIfipAgenciaEliminado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("responsable", responsable);

        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
//    public List<Boveda> getItemsResponsableIfipEliminado(String codigoRol, Long codigoIfip, char eliminado) {
//        Query query = em.createNamedQuery(Boveda.findByResponsableIfipEliminado);
//        query.setParameter("codigoRol", codigoRol);
//        query.setParameter("codigoIfip", codigoIfip);
//        query.setParameter("eliminado", eliminado);
//        return query.getResultList();
//    }

}
