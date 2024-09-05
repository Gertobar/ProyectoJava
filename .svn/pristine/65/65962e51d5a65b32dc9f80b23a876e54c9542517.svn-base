/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.SaldosCaja;
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
public class SaldosCajaFacade extends AbstractFacade<SaldosCaja> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SaldosCajaFacade() {
        super(SaldosCaja.class);
    }
    
    // ------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    
    /**
     * OBTIENE LOS SALDOS DE LA CAJA MEDIANTE EL CODIGO DE LA APERTURA
     * @param codigoApertura Codigo de la Apertura
     * @return Lista de Saldos de Caja
     */
    public List<SaldosCaja> getItemsCodigoApertura(Long codigoApertura)
    {
        Query query = this.em.createNamedQuery(SaldosCaja.findByCodigoApertura);
        query.setParameter("codigoApertura", codigoApertura);
        
        return query.getResultList();
    }
     
    /**
     * Obtiene los saldos de la Ifip de Agencia de Acuerdo el Estaod de la CAJA
     * @param codigoIfipAgencia Codigo de Ifip de Agencia
     * @param estado Estado de la Apertura de la Caja
     * @return Lista de Saldos de Caja
     */
     public List<SaldosCaja> getItemsIfipAgenciaEstado(Long codigoIfipAgencia, char estado)
    {
        Query query = this.em.createNamedQuery(SaldosCaja.findByIfipAgenciaEstado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("estado", estado);
        
        return query.getResultList();
    }
}
