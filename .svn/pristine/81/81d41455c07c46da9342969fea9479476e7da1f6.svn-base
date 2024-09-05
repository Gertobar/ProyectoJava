/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.FuenteIngresos;
import ec.renafipse.mks.modelo.socios.Profesion;
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
public class FuenteIngresosFacade extends AbstractFacade<FuenteIngresos> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuenteIngresosFacade() {
        super(FuenteIngresos.class);
    }
    
     // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de Fuente de Ingresos acuerdo al estado eliminado
     * @param eliminado S=SI N=NO
     * @return 
     */
    public List<FuenteIngresos> getItemsFuenteIngresosEliminado(char eliminado)
    {
        return this.em.createNamedQuery(FuenteIngresos.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
    
}
