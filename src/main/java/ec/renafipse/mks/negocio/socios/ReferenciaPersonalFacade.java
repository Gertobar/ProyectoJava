/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.ReferenciaPersonal;
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
public class ReferenciaPersonalFacade extends AbstractFacade<ReferenciaPersonal> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReferenciaPersonalFacade() {
        super(ReferenciaPersonal.class);
    }

    public List<ReferenciaPersonal> getItemsRefPerPer(Persona codigoPersona) {

        return em.createNamedQuery(ReferenciaPersonal.findByPersona).setParameter("codigoPersona", codigoPersona).getResultList();

    }
    //---------------------------------------------------------------------------------------

    /**
     * Actualiza las Referecias Personales de la Persona
     * @param direccion direccion
     * @param eliminado S o N eliminado
     * @param nombres Nombre de la persona
     * @param telefono telefono 
     * @param codigo Codigo secuencial de la referencia personal
     */
    @Transactional
    public void actualiza(String direccion, char eliminado, String nombres,  String telefono, long codigo) {
        Query query = em.createQuery("UPDATE ReferenciaPersonal r "
                + "SET r.direccion = :direccion, \n"
                + "r.eliminado = :eliminado, \n"
                + "r.nombres = :nombres, \n"
                + "r.telefono = :telefono   \n"
                + "WHERE r.codigo = :codigo");
        query.setParameter("direccion", direccion);
        query.setParameter("eliminado", eliminado);
        query.setParameter("telefono", telefono);
        query.setParameter("nombres", nombres);
        query.setParameter("codigo", codigo);
        query.executeUpdate();

    }

}

