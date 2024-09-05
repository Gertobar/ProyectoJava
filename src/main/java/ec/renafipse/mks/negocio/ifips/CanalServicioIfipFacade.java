/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.CanalServicioIfip;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andres
 */
@Stateless
public class CanalServicioIfipFacade extends AbstractFacade<CanalServicioIfip> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CanalServicioIfipFacade() {
        super(CanalServicioIfip.class);
    }
    
    /**
     * 
     * @param codigoIfip
     * @param eliminado
     * @return 
     */
    public List<CanalServicioIfip> getListaEnvioCanalServicoIfipEliminado(Long codigoIfip, char eliminado) {
        List<CanalServicioIfip> lista = null;
        Query query = em.createNamedQuery("CanalServicioIfip.findByEnvioIfipEliminado", CanalServicioIfip.class);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        if (!query.getResultList().isEmpty()) {
          List<CanalServicioIfip> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
}
