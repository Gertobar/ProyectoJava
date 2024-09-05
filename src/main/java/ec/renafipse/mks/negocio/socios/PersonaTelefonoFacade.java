/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.comunes.OperadoraTelefono;
import ec.renafipse.mks.modelo.comunes.TipoTelefono;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaTelefono;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vicastoc
 */
@Stateless
public class PersonaTelefonoFacade extends AbstractFacade<PersonaTelefono> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaTelefonoFacade() {
        super(PersonaTelefono.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     *
     * @param codigoPersona
     * @return
     */
    public List<PersonaTelefono> getItemsPorPersona(Persona codigoPersona) {

        return this.em.createNamedQuery(PersonaTelefono.findByPersona).setParameter("codigoPersona", codigoPersona).getResultList();
    }

     //-------------------------------------------------------------------------------------
    /**
     * Actualiza el Telefono de la Persona
     * @param codigoPersona Persona
     * @param codigoTipoTelefono Tipo de Telefono
     * @param codigoOperadoraTelefono Operadora de Telefono
     * @param numero NUmero de Telefono
     * @param eliminado Eliminado
     * @param codigo  Codigo Secuencial del Telefono
     */
    @Transactional
    public void actualiza(Persona codigoPersona, TipoTelefono codigoTipoTelefono, OperadoraTelefono codigoOperadoraTelefono, String numero, char eliminado, String recibeNotificacion, long codigo) {
        Query query = em.createQuery("UPDATE PersonaTelefono c "
                + "SET codigoPersona = :codigoPersona, \n"
                + "codigoTipoTelefono = :codigoTipoTelefono, \n"
                + "codigoOperadoraTelefono = :codigoOperadoraTelefono, \n"
                + "numero = :numero, \n"
                + "eliminado = :eliminado, \n"
                + "recibeNotificacion = :recibeNotificacion \n"
                + "WHERE codigo = :codigo");
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoTipoTelefono", codigoTipoTelefono);
        query.setParameter("codigoOperadoraTelefono", codigoOperadoraTelefono);
        query.setParameter("numero", numero);
        query.setParameter("eliminado", eliminado);
        query.setParameter("recibeNotificacion", recibeNotificacion);
        query.setParameter("codigo", codigo);
        query.executeUpdate();

    }

    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
}
