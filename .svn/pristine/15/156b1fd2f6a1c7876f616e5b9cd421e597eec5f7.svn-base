/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.ActividadEconomica;
import ec.renafipse.mks.modelo.socios.SubsectorActividadEconomica;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class SubsectorActividadEconomicaFacade extends AbstractFacade<SubsectorActividadEconomica> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubsectorActividadEconomicaFacade() {
        super(SubsectorActividadEconomica.class);
    }
    
     // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los subsectores de acuerdo a al sector
     *
     * @param codigoSector Codigo del Sector de la Actividad Economica     
     * @param eliminado S=SI N=NO
     * @return Lista de actividades Economicas
     */
    public List<SubsectorActividadEconomica> getItemsCodigoSectorElminado(long codigoSector, char eliminado) {
        Query query = this.em.createNamedQuery(SubsectorActividadEconomica.findByCodigoSectorElminado);
        query.setParameter("codigoSector", codigoSector);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
}
