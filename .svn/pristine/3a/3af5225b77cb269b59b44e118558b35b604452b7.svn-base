/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.ParametroGeneralSegIfi;
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
public class ParametroGeneralSegIfiFacade extends AbstractFacade<ParametroGeneralSegIfi> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroGeneralSegIfiFacade() {
        super(ParametroGeneralSegIfi.class);
    }
    
    /**
     * 
     * @param codigoIfip
     * @return 
     */
    public List<ParametroGeneralSegIfi> getItemsParGenSegIfi(Long codigoIfip)
    {
        return this.em.createNamedQuery(ParametroGeneralSegIfi.findByCodigoIfip).setParameter("codigoIfip", codigoIfip).getResultList();
    }
    
      /**
     * Obtiene el parametro de seguridad de una IFIP
     * @param codigoIfip
     * @param codigoParametro
     * @return  Lista de Parametros de Seguridad IFIP
     */
    public List<ParametroGeneralSegIfi> getItemsCodigoIfipCodigoParametro(Long codigoIfip, Long codigoParametro)
    {
        Query query = em.createNamedQuery(ParametroGeneralSegIfi.findByCodigoIfipCodigoParametro);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoParametro", codigoParametro);
        return query.getResultList();
    }
    
}
