/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.seguros;

import ec.renafipse.mks.modelo.seguros.ContratoSeguro;
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
public class ContratoSeguroFacade extends AbstractFacade<ContratoSeguro> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoSeguroFacade() {
        super(ContratoSeguro.class);
    }
    
    public List<ContratoSeguro> getItemsSeguro(String criterio, String buscarPor, char indicaActivo, Long codigoIfip) {
        List<ContratoSeguro> listSocios = null;
        if (buscarPor.equals("N")) {
            Query query = this.em.createNamedQuery(ContratoSeguro.findByNombreSocioActivoIfip);
            query.setParameter("nombreCompleto", criterio.toUpperCase());
            query.setParameter("codigoIfip", codigoIfip);
            query.setParameter("indicaActivo", indicaActivo);
            listSocios = query.getResultList();
        }

        if (buscarPor.equals("I")) {
            Query query = this.em.createNamedQuery(ContratoSeguro.findByIdentificacionSocioActivoIfip);
            query.setParameter("identificacion", criterio);
            query.setParameter("codigoIfip", codigoIfip);
            query.setParameter("indicaActivo", indicaActivo);
            listSocios = query.getResultList();
        }
        return listSocios;
    }
    
    public List<ContratoSeguro> getItemsEliminado(char eliminado) {
        Query query = this.em.createNamedQuery(ContratoSeguro.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public List<ContratoSeguro> getItemsSocioNoEstado(long codigoSocio, char estado, char eliminado, Long codigoIfip) {
        Query query = this.em.createNamedQuery(ContratoSeguro.findBySocioNoEstadoEliminado);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("estado", estado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    
}
