/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.PersonaActEcoPri;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
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
public class PersonaActEcoPriFacade extends AbstractFacade<PersonaActEcoPri> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaActEcoPriFacade() {
        super(PersonaActEcoPri.class);
    }

    //-------------------------------------------------------------------------------------
    /**
     * Actualiza la Actividad Principal de la Pesrona
     * @param codigoActividadEconomica Codigo de Actividad Economica
     * @param fechaActualizacion Fecha de Actualizacion de la Actividad Economica
     * @param codigoPersona  Codigo de la Persona
     */
    @Transactional
    public void actualiza(long codigoActividadEconomica, Date fechaActualizacion, long codigoPersona) {
        Query query = em.createQuery("UPDATE PersonaActEcoPri c "
                + "SET codigoActividadEconomica = :codigoActividadEconomica, \n"
                + "fechaActualizacion = :fechaActualizacion \n"
                + "WHERE codigoPersona = :codigoPersona");
        query.setParameter("codigoActividadEconomica", codigoActividadEconomica);
        query.setParameter("fechaActualizacion", fechaActualizacion, TemporalType.TIMESTAMP);
        query.setParameter("codigoPersona", codigoPersona);
        query.executeUpdate();
    }

}
