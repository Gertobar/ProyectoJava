/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.ClasePersona;
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
public class ClasePersonaFacade extends AbstractFacade<ClasePersona> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClasePersonaFacade() {
        super(ClasePersona.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS    
    /**
     * Obtiene los tipos de identificacion vigentes (No eliminados)
     *
     * @return Lista de Tipos de Identificacion
     */
    public List<ClasePersona> getItemsTipoPersonaVigentes() {
        Query query = em.createNamedQuery(ClasePersona.findByEliminado);
        query.setParameter("eliminado", 'N');
        return query.getResultList();
    }

    /**
     * Obtiene el listado de tipos de persona de acuerdo al codigo
     *
     * @param codigo
     * @return Listao de TipoPersonas
     */
    public List<ClasePersona> getItemsCodigo(Long codigo) {
        Query query = em.createNamedQuery(ClasePersona.findByCodigo);
        query.setParameter("codigo", 'N');
        return query.getResultList();
    }

    /**
     * Obtiene el listado de tipos de persona de acuerdo al codigo
     *
     * @param codigo
     * @return Listao de TipoPersonas
     */
    public List<ClasePersona> getItemsEsParaInstitucionEliminado(char esParaInstitucion, char eliminado) {
        Query query = em.createNamedQuery(ClasePersona.findByEsParaInstitucionEliminado);
        query.setParameter("esParaInstitucion", esParaInstitucion);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
     /**
     * Obtiene el listado de tipos de persona de acuerdo al codigo
     *
     * @param codigo
     * @return Listao de TipoPersonas
     */
    public List<ClasePersona> getItemsEstadoEliminado(char eliminado) {
        Query query = em.createNamedQuery(ClasePersona.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
    /**
     * Obtiene el listado de tipos de persona de acuerdo al codigo
     *
     * @param codigo
     * @return Listao de TipoPersonas
     */
    public List<ClasePersona> getItemsTipoPersonabyCodigo(Long codigo) {
        Query query = em.createNamedQuery(ClasePersona.findByCodigo);
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }

}
