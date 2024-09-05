/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.TipoMotivoCambioCheque;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class TipoMotivoCambioChequeFacade extends AbstractFacade<TipoMotivoCambioCheque> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoMotivoCambioChequeFacade() {
        super(TipoMotivoCambioCheque.class);
    }

    /**
     * Obtiene los tipos de motivos de cambio de cheque de acuerdo al estado eliminado
     * @param eliminado S=SI N=NO
     * @return Lista de Tipos de Motivo de Cambio
     */
    public List<TipoMotivoCambioCheque> getItemsEliminado(char eliminado) {
        return this.em.createNamedQuery(TipoMotivoCambioCheque.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
}
