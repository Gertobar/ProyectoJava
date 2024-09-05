/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.TipoBloqueoCuenta;
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
public class TipoBloqueoCuentaFacade extends AbstractFacade<TipoBloqueoCuenta> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoBloqueoCuentaFacade() {
        super(TipoBloqueoCuenta.class);
    }
    
    /**
     * Obtiene los Tipos de Bloqueo de Cuenta  de acuerdo a los estados mostrar y eliminado
     * @param mostrar Mostrar S o N
     * @param eliminado Elmiminado S o N
     * @return Lista de Tipos de Bloqueo
     */
    public List<TipoBloqueoCuenta> getItemsMostrarElimiando(char mostrar, char eliminado)
    {
        Query query = this.em.createNamedQuery(TipoBloqueoCuenta.findByMostrarEliminado);
        query.setParameter("mostrar", mostrar);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
}
