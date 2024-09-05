/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.LicitudFonOrgDest;
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
public class LicitudFonOrgDestFacade extends AbstractFacade<LicitudFonOrgDest> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicitudFonOrgDestFacade() {
        super(LicitudFonOrgDest.class);
    }

    /**
     * Obtiene Los Origenes o Destinos para licitud de acuerdo el origen o eliminado
     * @param esOrigen S=SI N=NO
     * @param eliminado S=SI N=NO
     * @return  Lista de Origenes y Destinos
     */
    public List<LicitudFonOrgDest> getItemsEsOrigenEliminado(char esOrigen, char eliminado) {
        Query query = this.em.createNamedQuery(LicitudFonOrgDest.findByEsOrigenEliminado);
        query.setParameter("esOrigen", esOrigen);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();

    }
}
