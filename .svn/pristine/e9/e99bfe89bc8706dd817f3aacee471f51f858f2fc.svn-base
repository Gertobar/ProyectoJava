/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.PlanDeCuenta;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author renafipse1
 */
@Stateless
public class PlanDeCuentaFacade extends AbstractFacade<PlanDeCuenta> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanDeCuentaFacade() {
        super(PlanDeCuenta.class);
    }
    
    public List<PlanDeCuenta> getItemsPlanCuentaGrupoTipo(Long codigoTipoPlan, Long codigoGrupo, char eliminado) {
        Query query = em.createNamedQuery(PlanDeCuenta.findByCodigoTipoPlanCodigoGrupo);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoTipoPlan", codigoTipoPlan);
        query.setParameter("codigoGrupo", codigoGrupo);
        return query.getResultList();
    }
    
    public List<PlanDeCuenta> getItemsPlanCuentaEliminado(char eliminado) {
        Query query = em.createNamedQuery(PlanDeCuenta.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public List<PlanDeCuenta> getItemsPlanCuentaGrupoNivel(long codigoGrupo, short nivel) {
        Query query = em.createNamedQuery(PlanDeCuenta.findBycodigoGrupoNivel);
        query.setParameter("codigoGrupo", codigoGrupo);
        query.setParameter("nivel", nivel);
        return query.getResultList();
    }
    
    public List<PlanDeCuenta> getItemsPlanCuenta() {
        Query query = em.createNamedQuery(PlanDeCuenta.findAll);
        return query.getResultList();
    }
    
    
}
