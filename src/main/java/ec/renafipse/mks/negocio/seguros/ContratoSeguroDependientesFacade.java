/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguros;

import ec.renafipse.mks.modelo.seguros.ContratoSeguroDependientes;
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
public class ContratoSeguroDependientesFacade extends AbstractFacade<ContratoSeguroDependientes> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoSeguroDependientesFacade() {
        super(ContratoSeguroDependientes.class);
    }

    /**
     * 
     * @param codigoSeguro
     * @return 
     */
    public List<ContratoSeguroDependientes> getItemsCodContrato(Long codigoSeguro,char eliminado) {
        Query query = this.em.createNamedQuery(ContratoSeguroDependientes.findByCodigoSeguroEliminado);
        query.setParameter("codigoSeguro", codigoSeguro);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    
}
