/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.Cliente;
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
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    /**
     * Obtiene los clientes que tienen esa identificacion - esto deberia ser
     * unico
     *
     * @param identificacion Identificacion del Cliente
     * @return Lista de Clientes
     */
    public List<Cliente> getItemsIdentificacionPersona(String identificacion) {
        return this.em.createNamedQuery(Cliente.findByIdentificacionPersona).setParameter("identificacion", identificacion).getResultList();
    }

}
