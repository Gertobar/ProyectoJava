/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.dpfs.TasaInteresDpfIfip;
import ec.renafipse.mks.modelo.dpfs.TasaInteresProDpfMon;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
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
public class TasaInteresProDpfMonFacade extends AbstractFacade<TasaInteresProDpfMon> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TasaInteresProDpfMonFacade() {
        super(TasaInteresProDpfMon.class);
    }
    
    //----------------------------------------------------------------------------
    //PERSONALIZADOS
    
    /**
     * Obtiene las tasas de intereses para los dias del DPF, por el monto y moneda
     * de acuerdo a la ifip y el estado eliminado
     * @param codigoIfip codigo de la ifip
     * @param rango rango en días que se tendrá captado el dpf
     * @param codigoMoneda codigo de la moneda
     * @param monto capital que va invertir el socio/persona
     * @param eliminado S=SI N=NO
     * @return  Lista de Tasas de Interes
     */
    public List<TasaInteresProDpfMon> getItemsContratoDpf(Long codigoIfip, Long rango, Long codigoMoneda, BigDecimal monto, char eliminado)
    {
       // WHERE s.codigoIfip = :codigoIfip  AND s.eliminado =:eliminado  AND  :rango BETWEEN r.rangoInicial AND r.rangoFinal AND r.eliminado = :eliminado AND :monto BETWEEN t.montoInicial AND t.montoFinal AND t.codigoMoneda =:codigoMoneda AND t.eliminado = :eliminado
        Query query = this.em.createNamedQuery(TasaInteresProDpfMon.findByContratoDpf);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        query.setParameter("rango", rango);
        query.setParameter("monto", monto);
        query.setParameter("codigoMoneda", codigoMoneda);
        
        
        String consulta = query.unwrap(org.hibernate.Query.class).getQueryString();
        
        return query.getResultList();
    }
    
    //----------------------------------------------------------------------------
    //PERSONALIZADOS
    
    /**
     * Obtiene las tasas de intereses para los dias del DPF, por el monto y moneda
     * de acuerdo a la ifip y el estado eliminado
     * @param codigoIfip codigo de la ifip
     * @param rango rango en días que se tendrá captado el dpf
     * @param codigoMoneda codigo de la moneda
     * @param monto capital que va invertir el socio/persona
     * @param eliminado S=SI N=NO
     * @return  Lista de Tasas de Interes
     */
    public List<TasaInteresProDpfMon> getItemsContratoDpfMontoMinimo(Long codigoIfip, Long rango, Long codigoMoneda, BigDecimal monto, char eliminado)
    {
       // WHERE s.codigoIfip = :codigoIfip  AND s.eliminado =:eliminado  AND  :rango BETWEEN r.rangoInicial AND r.rangoFinal AND r.eliminado = :eliminado AND :monto BETWEEN t.montoInicial AND t.montoFinal AND t.codigoMoneda =:codigoMoneda AND t.eliminado = :eliminado
        Query query = this.em.createNamedQuery(TasaInteresProDpfMon.findByContratoDpfMontoMinimo);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        query.setParameter("rango", rango);
        query.setParameter("monto", monto);
        query.setParameter("codigoMoneda", codigoMoneda);
        
        return query.getResultList();
    }    
}
