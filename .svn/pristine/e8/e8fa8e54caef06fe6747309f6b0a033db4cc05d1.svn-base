/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.ReferenciaEntidadFinanciera;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vicastoc
 */
@Stateless
public class ReferenciaEntidadFinancieraFacade extends AbstractFacade<ReferenciaEntidadFinanciera> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReferenciaEntidadFinancieraFacade() {
        super(ReferenciaEntidadFinanciera.class);
    }

    public List<ReferenciaEntidadFinanciera> getItemsRefEntFinPer(Persona codigoPersona) {
        return this.em.createNamedQuery(ReferenciaEntidadFinanciera.findByPersona).setParameter("codigoPersona", codigoPersona).getResultList();
    }

    //---------------------------------------------------------------------------------------
    /**
     * Actualiza la referencia de entidad financiera
     * @param codigoEntidadFinanciera codigo de la Entidad Financiera
     * @param codigoTipoCuenta Codigo del Tipo de Cuenta
     * @param eliminado S o N Eliminado
     * @param numeroCuenta Numero de Cuenta
     * @param observaciones Observaciones
     * @param codigo Codigo de la Referencia
     */
    @Transactional
    public void actualiza(EntidadFinanciera codigoEntidadFinanciera, TipoCuentaEntidadFinanciera codigoTipoCuenta, char eliminado, String numeroCuenta, String observaciones, long codigo) {
        Query query = em.createQuery("UPDATE ReferenciaEntidadFinanciera r \n"
                + "SET r.codigoEntidadFinanciera = :codigoEntidadFinanciera, \n"
                + "r.codigoTipoCuenta = :codigoTipoCuenta, \n"
                + "r.eliminado = :eliminado, \n"
                + "r.numeroCuenta = :numeroCuenta, \n"
                + "r.observaciones = :observaciones \n"
                + "WHERE r.codigo = :codigo");
        query.setParameter("codigoEntidadFinanciera", codigoEntidadFinanciera);
        query.setParameter("codigoTipoCuenta", codigoTipoCuenta);
        query.setParameter("eliminado", eliminado);
        query.setParameter("numeroCuenta", numeroCuenta);
        query.setParameter("observaciones", observaciones);
        query.setParameter("codigo", codigo);
        query.executeUpdate();

    }

}
