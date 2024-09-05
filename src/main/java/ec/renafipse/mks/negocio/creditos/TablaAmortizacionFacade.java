/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacion;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacionPK;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
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
public class TablaAmortizacionFacade extends AbstractFacade<TablaAmortizacion> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TablaAmortizacionFacade() {
        super(TablaAmortizacion.class);
    }

    /**
     *
     * @param numeroCredito
     * @param codigoIfip
     * @param estado
     * @return
     */
    public List<TablaAmortizacion> getItemsCreditoCuotaPendiente(Long numeroCredito, Long codigoIfip, char estado) {
        Query query = this.em.createNamedQuery(TablaAmortizacion.findByCreditoCuotaEstadoPendiente);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    /**
     * Obtiene la tabla de amortizacion de un credito
     *
     * @param numeroCredito NUmero de Credito
     * @param codigoIfip Codigo de la Ifip
     * @return
     */
    public List<TablaAmortizacion> getItemsNumeroCreditoCodigoIfip(Long numeroCredito, Long codigoIfip) {
        Query query = this.em.createNamedQuery(TablaAmortizacion.findByNumeroCreditoCodigoIfip);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }

    /**
     * Obtiene las cutoas que no han sido pagadas y que por en estan en mora de
     * acuerdo a la fecha en la vence
     *
     * @param numeroCredito Numero de Credito
     * @param codigoIfip Codigo de la Ifip
     * @param fechaVence Fecha de Mencimiento
     * @param estado P = Pendiente
     * @return Lista de Tabla de Amortización con la Cuota
     */

    public List<TablaAmortizacion> getItemsCuotasVencidasCreditoIfip(Long numeroCredito, Long codigoIfip, Date fechaVence, char estado) {
        Query query = this.em.createNamedQuery(TablaAmortizacion.findByCuotasVencidasCreditoIfip);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("fechaVence", fechaVence);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    
    public int getPlazoCreditoIfip(Long numeroCredito, Long codigoIfip){
        int plazo = 0;
        List<TablaAmortizacion> tablaAmortizacion = getItemsNumeroCreditoCodigoIfip(numeroCredito, codigoIfip);
        if(tablaAmortizacion == null)
            return 0;
        if(tablaAmortizacion.isEmpty())
            return 0;
        Date fechaInicio = null;
        Date fechaFin = null;
        for( int a=0; a<tablaAmortizacion.size(); a++ ){
            if(tablaAmortizacion.get(a).getTablaAmortizacionPK().getCuota() == 1)
                fechaInicio = tablaAmortizacion.get(a).getFechaInicio();
            fechaFin = tablaAmortizacion.get(a).getFechaPago();
        }
        plazo = (int) ((fechaFin.getTime() - fechaInicio.getTime())/86400000);
        return plazo;
    }

}
