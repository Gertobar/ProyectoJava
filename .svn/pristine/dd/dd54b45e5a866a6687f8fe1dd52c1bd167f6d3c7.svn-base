/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.LicitudFondosTipoMotivoExc;
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
public class LicitudFondosTipoMotivoExcFacade extends AbstractFacade<LicitudFondosTipoMotivoExc> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicitudFondosTipoMotivoExcFacade() {
        super(LicitudFondosTipoMotivoExc.class);
    }

    /**
     * Obtiene el tipo de motivo de aprobacion
     *
     * @param eliminado S=SI N=NO
     * @return Listado de Tipos de Movitivos
     */
    public List<LicitudFondosTipoMotivoExc> getItemsEliminado(char eliminado) {
        return this.em.createNamedQuery(LicitudFondosTipoMotivoExc.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }

}
