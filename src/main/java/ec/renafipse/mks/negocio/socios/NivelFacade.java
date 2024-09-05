/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.Nivel;
import ec.renafipse.mks.modelo.socios.TipoInstitucion;
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
public class NivelFacade extends AbstractFacade<Nivel> {
    private List<Nivel> itemsNivel;
    
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NivelFacade() {
        super(Nivel.class);
    }
    
    
    
    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    
    public List<Nivel> getItemsNivel()
    {
        return this.em.createNamedQuery(Nivel.findAll).getResultList();
    }
    
    
    /**
     * Obtiene los items del Tipo de Institucion de acuerdo al estado eliminado
     * @param eliminado S=SI N=NO
     * @return 
     */
    public List<Nivel> getItemsNivelEliminado(char eliminado)
    {
        return this.em.createNamedQuery(Nivel.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
}
