/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.ParametroGeneralCreditoIfip;
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
public class ParametroGeneralCreditoIfipFacade extends AbstractFacade<ParametroGeneralCreditoIfip> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroGeneralCreditoIfipFacade() {
        super(ParametroGeneralCreditoIfip.class);
    }
    
    public ParametroGeneralCreditoIfip getItemCodigoParametroIfipEliminado(Long codigoParametro,Long codigoIfip, char eliminado)
    {
        Query query = this.em.createNamedQuery(ParametroGeneralCreditoIfip.findByParametroIfipEliminado);
        query.setParameter("codigoParametro", codigoParametro);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        try {
            return (ParametroGeneralCreditoIfip) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }       
    }
}
