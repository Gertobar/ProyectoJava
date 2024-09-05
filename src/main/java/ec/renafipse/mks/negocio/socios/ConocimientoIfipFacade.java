/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.ConocimientoIfip;
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
public class ConocimientoIfipFacade extends AbstractFacade<ConocimientoIfip> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConocimientoIfipFacade() {
        super(ConocimientoIfip.class);
    }
    
     // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de Conocimiento IFIP de acuerdo al estado eliminado
     * @param eliminado S=SI N=NO
     * @return 
     */
    public List<ConocimientoIfip> getItemsConocimientoIfipEliminado(char eliminado)
    {
        return this.em.createNamedQuery(ConocimientoIfip.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
    
}
