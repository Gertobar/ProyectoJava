/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.PersonaInstitucion;
import ec.renafipse.mks.modelo.socios.TipoInstitucion;
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
public class PersonaInstitucionFacade extends AbstractFacade<PersonaInstitucion> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaInstitucionFacade() {
        super(PersonaInstitucion.class);
    }

    //-------------------------------------------------------------------------------------
    /**
     * Actualiza datos de la Institucion 
     * @param codigoTipoInstitucion Tipo de Institucion
     * @param razonSocial Razon Social
     * @param nombreComercial Nombre comercial
     * @param objetoSocial Objeto Social
     * @param fechaConstitucion Fecha de Constitucion
     * @param esSujetoSri S o N sujeto del SRI
     * @param observaciones Observaciones
     * @param fechaActualizacion Fecha de Actualizacion
     * @param controladaOc Controla Organismos de Control
     * @param codigoPersona  Codigo de la Persona
     */
    @Transactional    
    public void actualiza(TipoInstitucion codigoTipoInstitucion, String razonSocial,  String nombreComercial, String objetoSocial, Date fechaConstitucion,  char esSujetoSri, String observaciones,  Date fechaActualizacion, char controladaOc, Long codigoPersona) {
        Query query = em.createQuery("UPDATE PersonaInstitucion c "
                + "SET  codigoTipoInstitucion = :codigoTipoInstitucion, \n"
                + "razonSocial = :razonSocial, \n"
                + "nombreComercial = :nombreComercial, \n"
                + "objetoSocial = :objetoSocial, \n"
                + "fechaConstitucion = :fechaConstitucion, \n"
                + "esSujetoSri = :esSujetoSri, \n"
                + "observaciones = :observaciones, \n"
                + "fechaActualizacion = :fechaActualizacion, \n"
                + "controladaOc = :controladaOc \n"
                + "WHERE codigoPersona = :codigoPersona");
        query.setParameter("codigoTipoInstitucion", codigoTipoInstitucion);
        query.setParameter("razonSocial", razonSocial);
        query.setParameter("nombreComercial", nombreComercial);
        query.setParameter("objetoSocial", objetoSocial);
        query.setParameter("fechaConstitucion", fechaConstitucion, TemporalType.DATE);
        query.setParameter("esSujetoSri", esSujetoSri);        
        query.setParameter("fechaActualizacion", fechaActualizacion, TemporalType.TIMESTAMP);
        query.setParameter("controladaOc", controladaOc);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("observaciones", observaciones);
        query.executeUpdate();
    }

}
