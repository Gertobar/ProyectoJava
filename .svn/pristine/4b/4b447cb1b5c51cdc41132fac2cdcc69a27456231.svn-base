/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.TipoCartera;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class TipoCarteraFacade extends AbstractFacade<TipoCartera> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCarteraFacade() {
        super(TipoCartera.class);
    }
    
    public List<TipoCartera> getItemsTipoCarteraEliminado(char eliminado)
    {
        return this.em.createNamedQuery(TipoCartera.findByElminado).setParameter("eliminado", eliminado).getResultList();
    }
    
    /**
     * Metodo para obtener los Tipo de Creditos de los productos que tiene la Ifip
     */
    public List<TipoCartera> getItemsTipoCarteraProductoCredito(Long codigoIfip, Long codigoMoneda){
        Query query = em.createNamedQuery(TipoCartera.findTipoCarteraByProductoVigente);
        List<TipoCartera> listadoTipoCartera = new ArrayList<TipoCartera>();
        query.setParameter("codigoIfip",codigoIfip);
        query.setParameter("codigoMoneda",codigoMoneda);
        listadoTipoCartera = query.getResultList();
        return listadoTipoCartera;
    }
}
