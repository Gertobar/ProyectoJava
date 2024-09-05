/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.ClienteIfip;
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
public class ClienteIfipFacade extends AbstractFacade<ClienteIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteIfipFacade() {
        super(ClienteIfip.class);
    }

    /**
     * Retorna los clientes de la IFIP
     *
     * @param codigoIfip codigo de la Ifip
     * @return Lista de Clientes para la IFIP
     */
    public List<ClienteIfip> getItemsCodigoIfip(long codigoIfip) {
        return this.em.createNamedQuery(ClienteIfip.findByCodigoIfip).setParameter("codigoIfip", codigoIfip).getResultList();
    }
    
    /**
     * Retorna los clientes de la ifip que tengan la identificacion de la persona
     ** @param codigoIfip codigo de la Ifip
     * @param identificacion Identificacion del cliente
     * @return Lista de Clientes para la IFIP
     */
    public List<ClienteIfip> getItemsIdentificacionPersonaCodigoIfip(long codigoIfip, String identificacion) {
        Query query = this.em.createNamedQuery(ClienteIfip.findByIdentificacionPersonaCodigoIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("identificacion", identificacion);
        return query.getResultList();
    }
    
      /**
     * Retorna los clientes de la ifip que tengan la identificacion de la persona
     ** @param codigoIfip codigo de la Ifip
     * @param nombreCompleto Nombre de la persona porque buscars
     * @return Lista de Clientes para la IFIP
     */
    public List<ClienteIfip> getItemsNombrePersonaCodigoIfip(long codigoIfip, String nombreCompleto) {
        Query query = this.em.createNamedQuery(ClienteIfip.findByNombrePersonaCodigoIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("nombreCompleto", nombreCompleto.toUpperCase());
        return query.getResultList();
    }

}
