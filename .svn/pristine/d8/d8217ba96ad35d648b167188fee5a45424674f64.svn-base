/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.EstadoCivil;
import ec.renafipse.mks.modelo.socios.FuenteIngresos;
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
public class EstadoCivilFacade extends AbstractFacade<EstadoCivil> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoCivilFacade() {
        super(EstadoCivil.class);
    }
    
    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de Estado Civil acuerdo al estado eliminado
     * @param eliminado S=SI N=NO
     * @return 
     */
    public List<EstadoCivil> getItemsEstadoCivilEliminado(char eliminado)
    {
        return this.em.createNamedQuery(EstadoCivil.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
    
}
