/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.EstadoSocio;
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
public class EstadoSocioFacade extends AbstractFacade<EstadoSocio> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoSocioFacade() {
        super(EstadoSocio.class);
    }
    
    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de los estados de acuerdo al estado eliminado
     * @param eliminado S=SI N=NO
     * @return 
     */
    public List<EstadoSocio> getItemsEstadoSocioEliminado(char eliminado)
    {
        return this.em.createNamedQuery(EstadoSocio.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
}
