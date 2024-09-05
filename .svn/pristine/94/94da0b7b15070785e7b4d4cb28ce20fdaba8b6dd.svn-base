/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.TmpProvisionCarteraDetalle;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andres
 */
@Stateless
public class TmpProvisionCarteraDetalleFacade extends AbstractFacade<TmpProvisionCarteraDetalle> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TmpProvisionCarteraDetalleFacade() {
        super(TmpProvisionCarteraDetalle.class);
    }
    
    public List<TmpProvisionCarteraDetalle> getListaTmpProvisionCarteraDetalleOrdenada(Long codigoIfip, java.sql.Timestamp fecha){
        List<TmpProvisionCarteraDetalle> lista = null;
        Query query = this.em.createNamedQuery("TmpProvisionCarteraDetalle.findAllByFechaIfipOrder", TmpProvisionCarteraDetalle.class);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("fecha", fecha);
        if (!(query.getResultList().isEmpty())){
            List<TmpProvisionCarteraDetalle> resultado = query.getResultList();
            if (resultado.size() > 0)
                lista = resultado;
        }
        return lista;
    }
    
}
