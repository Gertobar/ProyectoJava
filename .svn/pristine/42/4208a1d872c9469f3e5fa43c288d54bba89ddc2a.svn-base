/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.reportes;

import ec.renafipse.mks.modelo.reportes.ParametroEntradaReporte;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class ParametroEntradaReporteFacade extends AbstractFacade<ParametroEntradaReporte> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroEntradaReporteFacade() {
        super(ParametroEntradaReporte.class);
    }

    public List<ParametroEntradaReporte> getItemsCodigoReporte(long codigoReporte) {
        return this.em.createNamedQuery(ParametroEntradaReporte.findByCodigoReporte).setParameter("codigoReporte", codigoReporte).getResultList();
    }

}
