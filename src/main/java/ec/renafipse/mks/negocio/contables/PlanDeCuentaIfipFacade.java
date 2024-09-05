/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.PlanDeCuenta;
import ec.renafipse.mks.modelo.contables.PlanDeCuentaIfip;
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
public class PlanDeCuentaIfipFacade extends AbstractFacade<PlanDeCuentaIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanDeCuentaIfipFacade() {
        super(PlanDeCuentaIfip.class);
    }

    /**
     *
     * @param codigoIfip
     * @param esDeMovimiento
     * @param vigente
     * @param eliminado
     * @return
     */
    public List<PlanDeCuenta> getItemsPlanCuentaVigenteEliminado(long codigoIfip, char esDeMovimiento, char vigente, char eliminado) {
        Query query = em.createNamedQuery(PlanDeCuentaIfip.findByPlanCuentaVigente);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("esDeMovimiento", esDeMovimiento);
        query.setParameter("vigente", vigente);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public List<PlanDeCuentaIfip> getItemsPlanCuentaIfipGrupoEliminadoNivel(long codigoIfip, long codigoGrupo, char eliminado, short nivel) {
        Query query = em.createNamedQuery(PlanDeCuentaIfip.findByCodigoIfipGrupoEliminadoNivel);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        query.setParameter("nivel", nivel);
        query.setParameter("codigoGrupo", codigoGrupo);
        return query.getResultList();
    }
    
    public List<PlanDeCuenta> getItemsPlanCuentaIfipEliminado(long codigoIfip, char eliminado) {
        Query query = em.createNamedQuery(PlanDeCuentaIfip.findByCodigoIfipEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    /**
     * Obtiene las cuentas contables de la Ifip que esten en el plan de cuentas vigente que sean de movimiento y no esten eliminadas
     * @param codigoIfip Codigo de la Ifip
     * @param esDeMovimiento Si es de Movimiento S o N
     * @param vigente Si es del plan de vigente S
     * @param eliminado Si estan eliminadas S o N
     * @return Plan de Cuentas de la Ifip
     */
    public List<PlanDeCuentaIfip> getItemsPlanCuentaIfipVigenteEliminado(long codigoIfip, char esDeMovimiento, char vigente, char eliminado) {
        Query query = em.createNamedQuery(PlanDeCuentaIfip.findByPlanCuentaIfipVigente);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("esDeMovimiento", esDeMovimiento);
        query.setParameter("vigente", vigente);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public List<PlanDeCuenta> getItemsCodigoIfipCodigoTipoPlan(long codigoIfip, long codigoTipoPlan) {
        Query query = em.createNamedQuery(PlanDeCuentaIfip.findByCodigoIfipCodigoTipoPlan);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTipoPlan", codigoTipoPlan);
        return query.getResultList();
    }

}
