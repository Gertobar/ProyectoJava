/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.comunes;

import ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera;
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
public class TipoCuentaEntidadFinancieraFacade extends AbstractFacade<TipoCuentaEntidadFinanciera> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCuentaEntidadFinancieraFacade() {
        super(TipoCuentaEntidadFinanciera.class);
    }
    
     // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items del Tipo de Cuenta de Entidades Financieras de acuerdo al estado eliminado
     * @param eliminado S=SI N=NO
     * @return 
     */
    public List<TipoCuentaEntidadFinanciera> getItemsTipoCuentaEntFinEliminado(char eliminado)
    {
        return this.em.createNamedQuery(TipoCuentaEntidadFinanciera.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    public List<TipoCuentaEntidadFinanciera> getItemsTipCueEntFin(Long codigo) {
        Query query = em.createNamedQuery(TipoCuentaEntidadFinanciera.findByCodigo);
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
    
}
