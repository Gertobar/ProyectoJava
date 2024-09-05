/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.modelo.ifips.TipoMovimientoBoveda;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Viajes2
 */
@Stateless
public class TipoMovimientoBovedaFacade extends AbstractFacade<TipoMovimientoBoveda> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoMovimientoBovedaFacade() {
        super(TipoMovimientoBoveda.class);
    }
     // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los tipos de movimientos de la boveda de acuerdo al tipo y el estado eliminado
     * @param tipo I=Ingreso E=Egreso
     * @param eliminado S=SI N=NO
     * @return  Lista de TIpo de Movimiento.
     */
    public List<TipoMovimientoBoveda> getItemsTipoEliminado(char tipo, char mostrar, char eliminado)
    {
        Query query = em.createNamedQuery(TipoMovimientoBoveda.findByTipoMostrarEliminado);        
        query.setParameter("tipo", tipo);
        query.setParameter("mostrar", mostrar);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
