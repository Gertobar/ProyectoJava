/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.FuenteIngresos;
import ec.renafipse.mks.modelo.socios.TipoVivienda;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vicastoc
 */
@Stateless
public class TipoViviendaFacade extends AbstractFacade<TipoVivienda> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoViviendaFacade() {
        super(TipoVivienda.class);
    }
    
    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items del Tipo de Vivienda acuerdo al estado eliminado
     * @param eliminado S=SI N=NO
     * @return 
     */
    public List<TipoVivienda> getItemsTipoViviendaEliminado(char eliminado)
    {
        return this.em.createNamedQuery(TipoVivienda.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
    
}
