/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.comunes;

import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.socios.TipoVivienda;
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
public class EntidadFinancieraFacade extends AbstractFacade<EntidadFinanciera> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntidadFinancieraFacade() {
        super(EntidadFinanciera.class);
    }
    
    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de la Entidad Financiera de acuerdo al estado eliminado
     * @param eliminado S=SI N=NO
     * @return 
     */
    public List<EntidadFinanciera> getItemsEntidadFinancieraEliminado(char eliminado)
    {
        return this.em.createNamedQuery(EntidadFinanciera.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    public List<EntidadFinanciera> getItemsEntFin(Long codigo) {
        Query query = em.createNamedQuery(EntidadFinanciera.findByCodigo);
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }
        
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
//    public List<EntidadFinanciera>getItemsEntidadaFinancieraIfipEliminado(char eliminado,Long Ifip)
//    {
//        this.em.createNamedQuery(EntidadFinanciera.findByEliminado)
//        return null;
//    }
}
