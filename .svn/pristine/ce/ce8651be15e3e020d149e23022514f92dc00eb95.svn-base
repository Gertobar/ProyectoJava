/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.PeriodoEfeCheEntFin;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
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
public class PeriodoEfeCheEntFinFacade extends AbstractFacade<PeriodoEfeCheEntFin> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodoEfeCheEntFinFacade() {
        super(PeriodoEfeCheEntFin.class);
    }
    // ------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Obtiene las entidades Financieras que Tienen un periodo de
     * EFECTIVIZACION.
     *
     * @param codigoIfip Codigo de la IFIP
     * @param eliminado Eliminado S=Si N=No
     * @return Lista de Entidades Financieras
     */
    public List<EntidadFinanciera> getItemsEntidadFinancieraIfipEliminado(Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(PeriodoEfeCheEntFin.findByEntidadFinancieraIfipEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    /**
     *
     * @param codigoEntidadFinanciera
     * @param codigoIfip
     * @param eliminado
     * @return
     */
    public List<Periodicidad> getItemsPeriodicidadEntFinIfipEliminado(Long codigoEntidadFinanciera, Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(PeriodoEfeCheEntFin.findByPeriodicidadEntFinIfipEliminado);
        query.setParameter("codigoEntidadFinanciera", codigoEntidadFinanciera);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

}
