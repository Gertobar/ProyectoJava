/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.ApoyoCarteraAsignada;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Santiago Araujo
 */
@Stateless
public class ApoyoCarteraAsignadaFacade extends AbstractFacade<ApoyoCarteraAsignada> {
    
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
        protected EntityManager getEntityManager() {
        return em;
    }

    public ApoyoCarteraAsignadaFacade() {
        super(ApoyoCarteraAsignada.class);
    }
    /***
     * 
     * @param codigoPersona Codigo de la persona que se verficia si es apoyo
     * @param codigoIfipAgencia Codigo de la agencia para ver si la apoya
     * @return boolean
     */
    public boolean esApoyoEnGestion(Long codigoPersona, Long codigoIfipAgencia){
        Query query = em.createNamedQuery("ApoyoCarteraAsignada.findByCodigoPersonaAndAgencia",ApoyoCarteraAsignada.class);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList().size() > 0;
    }
}
