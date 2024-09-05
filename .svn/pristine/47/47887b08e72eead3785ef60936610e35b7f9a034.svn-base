/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.comunes;

import ec.renafipse.mks.modelo.comunes.OperadoraTelefono;
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
public class OperadoraTelefonoFacade extends AbstractFacade<OperadoraTelefono> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperadoraTelefonoFacade() {
        super(OperadoraTelefono.class);
    }
    
    
    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de la Operadora del Telefono acuerdo al estado eliminado
     * @param eliminado S=SI N=NO
     * @return 
     */
    public List<OperadoraTelefono> getItemsOperadoraTelefonoEliminado(char eliminado)
    {
        return this.em.createNamedQuery(OperadoraTelefono.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
}
