/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.ifips.IfipMoneda;
import ec.renafipse.mks.modelo.ifips.TipoMovimientoBoveda;
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
public class IfipMonedaFacade extends AbstractFacade<IfipMoneda> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IfipMonedaFacade() {
        super(IfipMoneda.class);
    }
    
     // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene las Monedas que tiene Asignado la IFIP y de acuerdo al estado
     * @param codigoIfip Codigo de la IFip
     * @param eliminado S=SI N=NO
     * @return  Lista de Monedas
     */
    public List<Moneda> getItemsCodigoIfipEliminado(Long codigoIfip, char eliminado)
    {
        Query query = em.createNamedQuery(IfipMoneda.findByCodigoIfipEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
}
