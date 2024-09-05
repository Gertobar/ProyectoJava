/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.GaranteCredito;
import ec.renafipse.mks.modelo.creditos.PagoCredito;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class GaranteCreditoFacade extends AbstractFacade<GaranteCredito> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GaranteCreditoFacade() {
        super(GaranteCredito.class);
    }

    public List<GaranteCredito> getItemsGaranteCreditoIfipVigenteEliminado(long numeroCredito, long codigoIfip, char vigente, char eliminado) {
        Query query = this.em.createNamedQuery(GaranteCredito.findByGaranteCreditoIfipVigenteEliminado);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("vigente", vigente);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public int getNumeroGarantiasPersona(Long codigoPersona, Long codigoEstado, char vigente) {
        Query query = this.em.createNamedQuery(GaranteCredito.findByCodigoPersonaVigenteEstadoCred);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoEstado", codigoEstado);
        query.setParameter("vigente", vigente);
        return query.getResultList().size();
    }
    
    public List<Solicitud> getItemPersonaIfipVigente(Long codigoPersona, Long codigoIfip, char vigente) {
        Query query = this.em.createNamedQuery(GaranteCredito.findByCodigoPersonaIfipVigente);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("vigente", vigente);
        return query.getResultList();
    }

}
