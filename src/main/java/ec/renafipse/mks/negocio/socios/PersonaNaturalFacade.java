/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import ec.renafipse.mks.modelo.socios.EstadoCivil;
import ec.renafipse.mks.modelo.socios.FuenteIngresos;
import ec.renafipse.mks.modelo.socios.Instruccion;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.modelo.socios.Profesion;
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
public class PersonaNaturalFacade extends AbstractFacade<PersonaNatural> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaNaturalFacade() {
        super(PersonaNatural.class);
    }

    public List<PersonaNatural> getItemsLikeNombre(String nombreCompleto) {
        Query query = this.em.createNamedQuery(PersonaNatural.findByNombreLike);
        query.setParameter("nombreCompleto", nombreCompleto.toUpperCase());
        return query.getResultList();

    }

    public List<PersonaNatural> getItemsIdentificacion(String identificacion) {
        Query query = this.em.createNamedQuery(PersonaNatural.findByidentificacion);
        query.setParameter("identificacion", identificacion);
        return query.getResultList();

    }

    public PersonaNatural getIdentificacion(String identificacion) {
        Query query = this.em.createNamedQuery(PersonaNatural.findByidentificacion);
        query.setMaxResults(1);
        query.setParameter("identificacion", identificacion);
        List results = query.getResultList();
        if (!results.isEmpty()) {
            return (PersonaNatural) results.get(0);
        } else {
            return null;
        }
    }
    
    /**
     * Busca una persona Natural por su código
     * @param codigoPersona
     * @return 
     */
    public PersonaNatural getPorCodigoPersona(Long codigoPersona) {
        Query query = this.em.createNamedQuery("PersonaNatural.findByCodigoPersona", PersonaNatural.class);
        query.setParameter("codigoPersona", codigoPersona);
        List results = query.getResultList();
        if (!results.isEmpty()) {
            return (PersonaNatural) results.get(0);
        } else {
            return null;
        }
    }
    //-------------------------------------------------------------------------------------
    /**
     * Actualiza la persona Natural
     * @param codigoUbiGeoNac Codigo de la Ubicacion Geografica de Nacimiento de la Persona
     * @param codigoUbiGeoNaci
     * @param codigoProfesion Codigo de Profersion
     * @param codigoInstruccion Codigo de la Instrucción
     * @param codigoFuenteIngresos
     * @param codigoEstadoCivil
     * @param nombres
     * @param primerApellido
     * @param segundoApellido
     * @param ingresos
     * @param sexo
     * @param fechaNacimiento
     * @param exoneradoSri
     * @param cargasFamiliares
     * @param fechaActualizacion
     * @param codigoPersona 
     */
    @Transactional
    public void actualiza(UbicacionGeografica codigoUbiGeoNac, UbicacionGeografica codigoUbiGeoNaci,Profesion codigoProfesion, Instruccion codigoInstruccion, FuenteIngresos codigoFuenteIngresos, EstadoCivil codigoEstadoCivil, String nombres, String primerApellido, String segundoApellido, BigDecimal ingresos, char sexo, Date fechaNacimiento, char exoneradoSri, int cargasFamiliares, Date fechaActualizacion, Persona codigoPersona) {        
        Query query = em.createQuery("UPDATE PersonaNatural c "
                + "SET codigoUbiGeoNac = :codigoUbiGeoNac, \n"
                + "codigoUbiGeoNaci = :codigoUbiGeoNaci, \n"
                + "codigoProfesion = :codigoProfesion, \n"
                + "codigoInstruccion = :codigoInstruccion, \n"
                + "codigoFuenteIngresos = :codigoFuenteIngresos, \n"
                + "codigoEstadoCivil = :codigoEstadoCivil, \n"
                + "nombres = :nombres, \n"
                + "primerApellido = :primerApellido, \n"
                + "segundoApellido = :segundoApellido, \n"
                + "ingresos = :ingresos, \n"
                + "sexo = :sexo, \n"
                + "fechaNacimiento = :fechaNacimiento, \n"
                + "exoneradoSri = :exoneradoSri, \n"
                + "cargasFamiliares = :cargasFamiliares, \n"
                + "fechaActualizacion = :fechaActualizacion \n"
                + "WHERE codigoPersona = :codigoPersona");
        query.setParameter("codigoUbiGeoNac", codigoUbiGeoNac);
        query.setParameter("codigoUbiGeoNaci", codigoUbiGeoNaci);
        query.setParameter("codigoProfesion", codigoProfesion);
        query.setParameter("codigoInstruccion", codigoInstruccion);
        query.setParameter("codigoFuenteIngresos", codigoFuenteIngresos);
        query.setParameter("codigoEstadoCivil", codigoEstadoCivil );
        query.setParameter("nombres",nombres );
        query.setParameter("primerApellido", primerApellido);
        query.setParameter("segundoApellido", segundoApellido);
        query.setParameter("ingresos", ingresos);
        query.setParameter("sexo", sexo);
        query.setParameter("fechaNacimiento", fechaNacimiento, TemporalType.DATE);
        query.setParameter("exoneradoSri", exoneradoSri);
        query.setParameter("cargasFamiliares", cargasFamiliares);
        query.setParameter("fechaActualizacion",fechaActualizacion, TemporalType.TIMESTAMP );
        query.setParameter("codigoPersona", codigoPersona.getCodigo());
        query.executeUpdate();
    }

}
