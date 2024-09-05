/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.ActividadEcoProCreMonIfip;
import ec.renafipse.mks.modelo.socios.ActividadEconomica;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.ArrayList;
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
public class ActividadEcoProMonIifipFacade extends AbstractFacade<ActividadEcoProCreMonIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActividadEcoProMonIifipFacade() {
        super(ActividadEcoProCreMonIfip.class);
    }

    // Obtener las Actividades Economicas atadas al producto de credito por credito
    public List<ActividadEconomica> getItemsActEcoNivelPadreEliminado(long codigoProducto, long codigoMoneda, long codigoIfip, long nivel, long codigoPadre,char catalogo,char eliminado)
    {
        Query query = em.createNamedQuery(ActividadEcoProCreMonIfip.findByNivelPadreEliminado);        
        List<ActividadEconomica> listadoAct = new ArrayList<ActividadEconomica>();
        query.setParameter("codigoProducto",codigoProducto);
        query.setParameter("codigoMoneda",codigoMoneda);
        query.setParameter("codigoIfip",codigoIfip);
        query.setParameter("eliminado", eliminado);        
        query.setParameter("nivel", nivel);      
        query.setParameter("catalogo", catalogo);          
        query.setParameter("codigoPadre", codigoPadre); 
        query.setParameter("eliminado", eliminado);        
        List<ActividadEcoProCreMonIfip> listado = query.getResultList();
        for( ActividadEcoProCreMonIfip item : listado ){
            listadoAct.add(item.getActividadEconomica());
        }
        return listadoAct;
    }
    
    // Obtener las Actividades Economicas atadas al producto de credito 
    public List<ActividadEconomica> getItemsActEcoNivelEliminado(long codigoProducto, long codigoMoneda, long codigoIfip, long nivel, char catalogo,char eliminado)
    {
        Query query = em.createNamedQuery(ActividadEcoProCreMonIfip.findByNivelEliminado);        
        List<ActividadEconomica> listadoAct = new ArrayList<ActividadEconomica>();
        query.setParameter("codigoProducto",codigoProducto);
        query.setParameter("codigoMoneda",codigoMoneda);
        query.setParameter("codigoIfip",codigoIfip);
        query.setParameter("nivel", nivel);      
        query.setParameter("catalogo", catalogo);          
        query.setParameter("eliminado", eliminado); 
        List<ActividadEcoProCreMonIfip> listado = query.getResultList();
        for( ActividadEcoProCreMonIfip item : listado ){
            listadoAct.add(item.getActividadEconomica());
        }
        return listadoAct;
    }

    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
}
