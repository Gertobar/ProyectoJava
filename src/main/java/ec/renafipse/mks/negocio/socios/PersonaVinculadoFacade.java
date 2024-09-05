/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.PersonaVinculado;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Saca
 */
@Stateless
public class PersonaVinculadoFacade extends AbstractFacade<PersonaVinculado> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    public PersonaVinculadoFacade() {
        super(PersonaVinculado.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<PersonaVinculado> getItemsfindByVinculadoYpersona(long codigoPersona) {
        Query query = this.em.createNamedQuery(PersonaVinculado.findByVinculadoYpersona);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }
    
    public List<PersonaVinculado> getItemsfindByVinculadoNoEliminado(long codigoPersona) {
        Query query = this.em.createNamedQuery(PersonaVinculado.findByVinculadoNoEliminado);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("eliminado", 'N');
        return query.getResultList();
    }
    
    public String getRolReciprocoByVinculadoYpersona(long codigoPersona, long codigoMasculino, long codigoFemenino) {
        System.out.println(codigoPersona+","+codigoMasculino+","+codigoFemenino);
        try {
            Query query = em.createNativeQuery(
                    "SELECT \n"
                    + "	CASE PN.SEXO WHEN 'F' \n"
                    + "		THEN (SELECT TV.ROL FROM MKS_SOCIOS.TIPO_VINCULADO TV WHERE TV.CODIGO = :codigoFemenino)\n"
                    + "		ELSE (SELECT TV.ROL FROM MKS_SOCIOS.TIPO_VINCULADO TV WHERE TV.CODIGO = :codigoMasculino)\n"
                    + "	END AS ROL\n"
                    + "FROM MKS_SOCIOS.PERSONA_NATURAL PN \n"
                    + "WHERE PN.CODIGO_PERSONA = :codigoPersona"
            );
            query.setParameter("codigoPersona", codigoPersona);
            query.setParameter("codigoMasculino", codigoMasculino);
            query.setParameter("codigoFemenino", codigoFemenino);
            return query.getSingleResult().toString();
        } catch (NoResultException ex) {
            return "";
        } catch (NonUniqueResultException ex) {
            return "";
        }
    }

    @Transactional
    public int actualiza(long codigoPersona, long codigoPersonaVinculado, long codigoTipoVinculado, char eliminado, Date fechaRegistro, long registradoPor) {
        Query query = em.createQuery("UPDATE PersonaVinculado p "
                + "SET p.eliminado = :eliminado, \n"
                + "p.codigoTipoVinculado.codigo = :codigoTipoVinculado, \n"
                + "p.fechaRegistro = :fechaRegistro, \n"
                + "p.registradoPor = :registradoPor \n"
                + "WHERE p.personaVinculadoPK.codigoPersonaVinculado = :codigoPersonaVinculado \n"
                + "AND p.personaVinculadoPK.codigoPersona = :codigoPersona");
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoPersonaVinculado", codigoPersonaVinculado);
        query.setParameter("codigoTipoVinculado", codigoTipoVinculado);
        query.setParameter("eliminado", eliminado);
        query.setParameter("fechaRegistro", fechaRegistro);
        query.setParameter("registradoPor", registradoPor);
        return query.executeUpdate();
    }
}
