/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.ItemSituacionPatrimonial;
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
public class ItemSituacionPatrimonialFacade extends AbstractFacade<ItemSituacionPatrimonial> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemSituacionPatrimonialFacade() {
        super(ItemSituacionPatrimonial.class);
    }
    
    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de Situacion Patrimonial acuerdo al estado eliminado
     * @param tipo Tipo de Item I=Ingreso E=Egreso
     * @param eliminado S=SI N=NO
     * @return 
     */
    public List<ItemSituacionPatrimonial> getItemsSituacionPatrimonial(char tipo, char eliminado)
    {
        Query query = em.createNamedQuery(ItemSituacionPatrimonial.findByTipoEliminado);
        query.setParameter("tipo", tipo);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
    
}
