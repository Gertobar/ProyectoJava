/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
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
public class CalculoCuotaPagarFacade extends AbstractFacade<CalculoCuotaPagar> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalculoCuotaPagarFacade() {
        super(CalculoCuotaPagar.class);
    }

    /**
     *
     * @param numeroCredito
     * @param codigoIfip
     * @param cuota
     * @return
     */
    public List<CalculoCuotaPagar> getItemsCalculoCuotaNumeroIfip(Long numeroCredito, Long codigoIfip, Long cuota) {
        Query query = this.em.createNamedQuery(CalculoCuotaPagar.findByCalculoCuotaNumeroIfip);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("cuota", cuota);
        return query.getResultList();
    }
    
    /**
     *
     * @param numeroCredito
     * @param codigoIfip
     * @param estado
     * @return
     */
    public List<CalculoCuotaPagar> getItemsCalculoCuotaPendientes(Long numeroCredito, Long codigoIfip, char estado) {
        Query query = this.em.createNamedQuery(CalculoCuotaPagar.findByCalculoCuotaPendientes);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    
    /**
     *
     * @param numeroCredito
     * @param codigoIfip
     * @return
     */
    public List<CalculoCuotaPagar> getItemsCalculoNumeroIfip(Long numeroCredito, Long codigoIfip) {
        Query query = this.em.createNamedQuery(CalculoCuotaPagar.findByCalculoNumeroIfip);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }

    /**
     *
     * 30/10/2017
     * Modificado por Willan Jara
     * La corrección realizada es para cuando las listas devuelven nulos
     * se agreaga un try catch     
     * 
     * @param numeroCredito 
     * @param codigoIfip 
     * @param estado 
     * @return devuelve el numero de dias mora
     */
    public long getDiasMoraNumeroIfip(Long numeroCredito, Long codigoIfip, char estado) {                        
        Query query = this.em.createNamedQuery(CalculoCuotaPagar.findDiasMoraNimeroIfip);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estado", estado);
        if (query.getResultList().isEmpty()) {
            return 0;
        } else {
            try {   //Correccion realizada             
                return Long.parseLong(query.getResultList().get(0).toString());
            } catch (Exception e) {
                return 0;
            }
        }

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
    public List<CalculoCuotaPagar> getItemsCuotasVencidasCreditoIfip(Long numeroCredito, Long codigoIfip, Date fechaVence, char estado) {
        Query query = this.em.createNamedQuery(CalculoCuotaPagar.findByCuotasVencidasCreditoIfip);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("fechaVence", fechaVence);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    /***
     * Metodo para obtene rel valor vencido de un credito
     * @param numeroCredito numero de credito
     * @param codigoIfip codigo de la ifip
     * @param fechaVence fecha de corte
     * @param estado estado de las cuotas
     * @return 
     */
    public BigDecimal getValorVencido(Long numeroCredito, Long codigoIfip, Date fechaVence, char estado){
        List<CalculoCuotaPagar> cuotas = this.getItemsCuotasVencidasCreditoIfip(numeroCredito, codigoIfip, fechaVence, 'P');
        BigDecimal valorVencido = BigDecimal.ZERO;
        for(int a=0; a<cuotas.size();a++){
            valorVencido = valorVencido.add(cuotas.get(a).getTotalPago());
        }
        return valorVencido;
    }

}
