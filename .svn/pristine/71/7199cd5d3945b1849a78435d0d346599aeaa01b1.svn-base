/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.dpfs.RenovacionContratoDpf;
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
public class RenovacionContratoDpfFacade extends AbstractFacade<RenovacionContratoDpf> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RenovacionContratoDpfFacade() {
        super(RenovacionContratoDpf.class);
    }

    /**
     * Obtiene las renovacione de un DPF de la IFIP
     * @param codigoPersona Codigo de la Persona
     * @param codigoIfip Codigo Ifip
     * @param estado Estado P=Pendiente A=Anulado R=Renovado
     * @return Lista de Renovaciones
     */
    public List<RenovacionContratoDpf> getItemsCodigoPersonaCodigoIfipEstado(Long codigoPersona, Long codigoIfip, char estado) {
        Query query = this.em.createNamedQuery(RenovacionContratoDpf.findByCodigoPersonaCodigoIfipEstado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("estado", estado);
        return query.getResultList();
        
    }

}
