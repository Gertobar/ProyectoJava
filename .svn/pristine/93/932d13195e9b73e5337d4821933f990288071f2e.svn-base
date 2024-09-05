/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.TalonarioCheque;
import ec.renafipse.mks.modelo.ifips.IfipCuentaEntidadFinanciera;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class TalonarioChequeFacade extends AbstractFacade<TalonarioCheque> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TalonarioChequeFacade() {
        super(TalonarioCheque.class);
    }
    
    /**
     * Obtiene las Cuenatas de Las Entidaddes Financieras que tienen cheques
     * @param codigoIfip Codigo de la Ifip
     * @param codigoIfipAgencia Codigo de la Agencia
     * @param terminada Si el Talonario esta terminado S=SI N=NO
     * @return Lista de IfipCuentaEntidadFinanciera 
     */
    public List<IfipCuentaEntidadFinanciera> getIfipCuentaEntidadFinancieraCheque(long codigoIfip, long codigoIfipAgencia, char terminada)
    {
        Query query = this.em.createNamedQuery(TalonarioCheque.findByIfipCuentaEntidadFinancieraCheque);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("terminada", terminada);
        return query.getResultList();
        
    }
    
    /**
     * Obtiene los talonarios de los cheques de las entidades financieras de la agencia
     * @param codigoIfip Codigo de la Ifip
     * @param codigoIfipAgencia Codigo de la Agencia
     * @return Lista de  Talonarios de Cheques
     */
    public List<TalonarioCheque> getItemsCodigoIfipCodigoIfipAgencia(long codigoIfip, long codigoIfipAgencia)
    {
        Query query = this.em.createNamedQuery(TalonarioCheque.findByCodigoIfipCodigoIfipAgencia);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();
        
    }
}
