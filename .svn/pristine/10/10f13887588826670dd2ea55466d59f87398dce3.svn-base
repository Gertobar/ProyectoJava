/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.TipoIdentificacion;
import ec.renafipse.mks.modelo.socios.TipoPerTipoIde;
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
public class TipoPerTipoIdeFacade extends AbstractFacade<TipoPerTipoIde> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPerTipoIdeFacade() {
        super(TipoPerTipoIde.class);
    }
    
    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
   
     /**
     * Obtiene los tipos de identificacion vigentes (No Eliminados) a partir del
     * tipo de persona
     *
     * @param codigoTipoPersona
     * @return Lista de Tipos de Identificacion
     */
    public List<TipoIdentificacion> getItemsTipoIdentificacionVigente(Long codigoTipoPersona) {
        Query query = em.createNamedQuery(TipoPerTipoIde.findByTipoPersona);
        query.setParameter("codigoTipoPersona", codigoTipoPersona);
        query.setParameter("eliminado", 'N');
        query.setParameter("eliminadoTipoIde", 'N');
        query.setParameter("eliminadoTipoPer", 'N');

        return query.getResultList();
    }
    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
}
