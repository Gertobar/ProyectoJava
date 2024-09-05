/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.comunes;

import ec.renafipse.mks.modelo.comunes.Empresa;
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
public class EmpresaFacade extends AbstractFacade<Empresa> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }
    
    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items del Cargo acuerdo al estado eliminado
     * @param eliminado S=SI N=NO
     * @return 
     */
    public List<Empresa> getItemsEmpresaEliminado(char eliminado)
    {
        return this.em.createNamedQuery(Empresa.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    //------------------------------------------------------------------------
    
}
