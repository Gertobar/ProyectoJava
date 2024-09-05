/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.EstadoCredito;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vastudillo
 */
@Stateless
public class EstadoCreditoFacade extends AbstractFacade<EstadoCredito> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoCreditoFacade() {
        super(EstadoCredito.class);
    }

    /**
     * BUSCA LOS ESTADOS DE LOS CREDITOS DE ACUERDO AL ESTADO ELIMINADO
     * @param eliminado Eliminado S=Si N=No
     * @return Lista de Estado de Creditos
     */
    public List<EstadoCredito> getItemsEliminado(char eliminado) {
        return this.em.createNamedQuery(EstadoCredito.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }

}
