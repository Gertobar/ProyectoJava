/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import ec.renafipse.mks.modelo.socios.PersonaResidencia;
import ec.renafipse.mks.modelo.socios.Sector;
import ec.renafipse.mks.modelo.socios.TipoVivienda;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
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
public class PersonaResidenciaFacade extends AbstractFacade<PersonaResidencia> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaResidenciaFacade() {
        super(PersonaResidencia.class);
    }

    //-------------------------------------------------------------------------------------
    /**
     * Actualiza datos de la residencia de la persona
     * @param codigoUbiGeoRes Codigo de Ubicacion Geografica de Residencia
     * @param codigoTipoVivienda Tipo de Vivienda
     * @param codigoPeriodicidad Periodicidad
     * @param codigoSector Codigo de Sector
     * @param barrio Barrio
     * @param direccion Direccion de domicilio
     * @param referencia Referencia de donde reside
     * @param tiempo Tiempo de Residencia
     * @param codigoPersona  Codigo de la Persona
     */
    @Transactional
    public void actualiza(UbicacionGeografica codigoUbiGeoRes, TipoVivienda codigoTipoVivienda, Periodicidad codigoPeriodicidad, Sector codigoSector, String barrio,  String direccion, String referencia, int tiempo, long codigoPersona) {
        Query query = em.createQuery("UPDATE PersonaResidencia c "
                + "SET codigoUbiGeoRes = :codigoUbiGeoRes, \n"
                + "codigoTipoVivienda = :codigoTipoVivienda, \n"
                + "codigoPeriodicidad = :codigoPeriodicidad, \n"
                + "codigoSector = :codigoSector, \n"
                + "barrio = :barrio, \n"
                + "direccion = :direccion, \n"
                + "referencia = :referencia, \n"
                + "tiempo = :tiempo \n"
                + "WHERE codigoPersona = :codigoPersona");
        query.setParameter("codigoUbiGeoRes", codigoUbiGeoRes);
        query.setParameter("codigoTipoVivienda", codigoTipoVivienda);
        query.setParameter("codigoPeriodicidad", codigoPeriodicidad);
        query.setParameter("codigoSector", codigoSector);
        query.setParameter("barrio", barrio);
        query.setParameter("direccion", direccion);
        query.setParameter("referencia", referencia);
        query.setParameter("tiempo", tiempo);
        query.setParameter("codigoPersona", codigoPersona);
        query.executeUpdate();

    }

}
