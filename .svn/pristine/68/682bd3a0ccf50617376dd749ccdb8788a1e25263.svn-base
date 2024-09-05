/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;


import ec.renafipse.mks.modelo.socios.TipoPersona;
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
public class TipoPersonaFacade extends AbstractFacade<TipoPersona> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPersonaFacade() {
        super(TipoPersona.class);
    }
    
    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS    
    
     /**
     * Obtiene los tipos de identificacion vigentes (No eliminados)
     * @return Lista de Tipos de Identificacion
     */
    public List<TipoPersona> getItemsTipoPersonaVigentes()
    {
        Query query = em.createNamedQuery(TipoPersona.findByEliminado);        
        query.setParameter("eliminado", 'N');        
        return query.getResultList();
    }
    
    /**
     * Obtiene el listado de tipos de persona de acuerdo al codigo
     * @param codigo
     * @return  Listao de TipoPersonas
     */
    public List<TipoPersona> getItemsCodigo(Long codigo)
    {
        Query query = em.createNamedQuery(TipoPersona.findByCodigo);        
        query.setParameter("codigo", 'N');        
        return query.getResultList();
    }
    
    
      /**
     * Obtiene el listado de tipos de persona de acuerdo al codigo
     * @param codigo
     * @return  Listao de TipoPersonas
     */
    public List<TipoPersona> getItemsEsParaInstitucionEliminado(char esParaInstitucion, char eliminado)
    {
        Query query = em.createNamedQuery(TipoPersona.findByEsParaInstitucionEliminado);        
        query.setParameter("esParaInstitucion", esParaInstitucion);        
        query.setParameter("eliminado", eliminado);        
        return query.getResultList();
    }
    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
        /**
     * Obtiene el listado de tipos de persona de acuerdo al codigo
     * @param codigo
     * @return  Listao de TipoPersonas
     */
    public List<TipoPersona> getItemsTipoPersonabyCodigo(Long codigo)
    {
        Query query = em.createNamedQuery(TipoPersona.findByCodigo);        
        query.setParameter("codigo", codigo);        
        return query.getResultList();
    }
    
}
