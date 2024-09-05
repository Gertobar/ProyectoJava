/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.IfipAgencia;

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
public class IfipAgenciaFacade extends AbstractFacade<IfipAgencia> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IfipAgenciaFacade() {
        super(IfipAgencia.class);
    }

 /**
  * 
  * @param codigoIfip
  * @param eliminado
  * @return 
  */
    public List<IfipAgencia> getItemsIfipAgencia(Long codigoIfip, char eliminado) {
        Query query = em.createNamedQuery(IfipAgencia.findByIfipAgenPorIfipEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
      public List<IfipAgencia> getItemsIfipAgenciaPorIfip(Long codigoIfip)
    {
        Query query = em.createNamedQuery(IfipAgencia.findByIfipAgenPorIfip);
        query.setParameter("codigoIfip", codigoIfip);
        
        return query.getResultList();
    }
      
      /***
     * Metodo para obtener una agencia
     * @param codigo
     * @return 
     */
    public IfipAgencia getAgenciaPorCodigo(Long codigo)
    {
        Query query = em.createNamedQuery(IfipAgencia.findByCodigo);
        query.setParameter("codigo", codigo);
        return (IfipAgencia)query.getResultList().get(0);
    }
}
