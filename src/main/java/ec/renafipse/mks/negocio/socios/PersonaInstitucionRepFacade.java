/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.Cargo;
import ec.renafipse.mks.modelo.socios.Nivel;
import ec.renafipse.mks.modelo.socios.PersonaInstitucionRep;
import ec.renafipse.mks.negocio.AbstractFacade;
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
public class PersonaInstitucionRepFacade extends AbstractFacade<PersonaInstitucionRep> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaInstitucionRepFacade() {
        super(PersonaInstitucionRep.class);
    }

    //-------------------------------------------------------------------------------------
    /**
     * Actualiza el representante de una Institucion 
     * @param codigoPersonaRep Codigo de persona institucion
     * @param codigoNivel codigo de Nivel
     * @param codigoCargo Codigo de cargo
     * @param eliminado S o N Eliminado
     * @param codigoPersona Codigo de Persona
     */
    @Transactional
    public void actualiza(Nivel codigoNivel, Cargo codigoCargo, char eliminado, long codigoPersona, long codigoPersonaRep) {
        Query query = em.createQuery("UPDATE PersonaInstitucionRep c "
                + "SET codigoNivel = :codigoNivel, \n"
                + "codigoCargo = :codigoCargo, \n"
                + "eliminado = :eliminado "
                + "WHERE c.personaInstitucionRepPK.codigoPersona = :codigoPersona \n"
                + "AND c.personaInstitucionRepPK.codigoPersonaRep = :codigoPersonaRep");
        query.setParameter("codigoPersonaRep", codigoPersonaRep);
        query.setParameter("codigoNivel", codigoNivel);
        query.setParameter("codigoCargo", codigoCargo);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoPersona", codigoPersona);
        query.executeUpdate();

    }
}
