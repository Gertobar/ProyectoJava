/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos.lineacredito;

import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolEnvCom;
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
public class LineaCreditoSolEnvComFacade extends AbstractFacade<LineaCreditoSolEnvCom> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaCreditoSolEnvComFacade() {
        super(LineaCreditoSolEnvCom.class);
    }
    /***
     * Metodo para obtener las observaciones de una solicitud de linea de credito
     * @param codigoLineaCreditoSolicitud
     * @return 
     */
    public String getObservacionByCodigoLineaCreditoSolicitud(long codigoLineaCreditoSolicitud){
        List<LineaCreditoSolEnvCom> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolEnvCom.findByCodigoLineaCreditoSol", LineaCreditoSolEnvCom.class);
        query.setParameter("codigoLineaCreditoSol", codigoLineaCreditoSolicitud);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolEnvCom> resultado = query.getResultList();
          if (resultado.size() == 1)
              return resultado.get(0).getObservaciones();
          else
              return null;
        }
        return null;
    }
}
