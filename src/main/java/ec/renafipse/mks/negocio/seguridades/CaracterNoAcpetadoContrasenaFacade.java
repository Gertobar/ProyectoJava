/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.CaracterNoAcpetadoContrasena;
import ec.renafipse.mks.modelo.seguridades.Rol;
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
public class CaracterNoAcpetadoContrasenaFacade extends AbstractFacade<CaracterNoAcpetadoContrasena> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaracterNoAcpetadoContrasenaFacade() {
        super(CaracterNoAcpetadoContrasena.class);
    }

    /**
     *
     * @return
     */
    public List<CaracterNoAcpetadoContrasena> getItemsEliminado(char eliminado) {
        Query query = em.createNamedQuery(CaracterNoAcpetadoContrasena.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

}
