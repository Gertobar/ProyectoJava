/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.comunes;

import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
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
public class UbicacionGeograficaFacade extends AbstractFacade<UbicacionGeografica> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UbicacionGeograficaFacade() {
        super(UbicacionGeografica.class);
    }
    
    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS    
    
   /**
    * Obtiene las ubicaciones geograficas vigentes (no eliminado) de una jerarquia especifica
    * @param codigoJerarquia
    * @return 
    */
    public List<UbicacionGeografica> getItemsUbicacionGeograficaJerarquiaVigentes(Long codigoJerarquia)
    {
        Query query = em.createNamedQuery(UbicacionGeografica.findByJerarquiaEliminado);        
        query.setParameter("codigoJerarquia", codigoJerarquia);      
        query.setParameter("eliminado", 'N');   
        //System.out.println(query);
        return query.getResultList();
    }
  
    /**
     * Obtiene las ubicaciones geograficas hijas de acuerdo el codigo del padre
     * @param codigoUbiGeoPadre
     * @return 
     */
    public List<UbicacionGeografica> getItemsUbicacionGeograficaHijosVigentes(UbicacionGeografica codigoUbiGeoPadre)
    {
        Query query = em.createNamedQuery(UbicacionGeografica.findByPadreEliminado);        
        query.setParameter("codigoUbiGeoPadre", codigoUbiGeoPadre);      
        query.setParameter("eliminado", 'N');        
        return query.getResultList();
    }
    
    /**
     * Obtiene la ubicacion geografica por medio del codigo
     * @param codigo
     * @return 
     */
    public UbicacionGeografica getUbicacionGeografica(Long codigo)
    {
        Query query = em.createNamedQuery(UbicacionGeografica.findByCodigo);        
        query.setParameter("codigo", codigo);
        return (UbicacionGeografica)query.getResultList().get(0);
    }
    
   
    
    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
}
