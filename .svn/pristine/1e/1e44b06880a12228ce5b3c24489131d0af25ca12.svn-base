/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.TipoFirma;
import ec.renafipse.mks.modelo.comunes.Moneda;
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
public class TipoFirmaFacade extends AbstractFacade<TipoFirma> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoFirmaFacade() {
        super(TipoFirma.class);
    }

     // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE LOS TIPOS DE FIRMAS DE ACUERDO AL ESTADO ELIMINADO
     *
     * @param eliminado Eliminado S=Si N=No
     * @return Lista de Firmas
     */
    public List<TipoFirma> getItemsTipoFirma(char eliminado) {
        List<TipoFirma> listTipoFirmas;
        Query query = this.em.createNamedQuery(TipoFirma.findByEliminado);
        query.setParameter("eliminado", eliminado);
        listTipoFirmas = query.getResultList();

        return listTipoFirmas;
    }
}
