/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaRelacionIfip;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
public class PersonaRelacionIfipFacade extends AbstractFacade<PersonaRelacionIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    public PersonaRelacionIfipFacade() {
        super(PersonaRelacionIfip.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaRelacionIfip getItemByCodigoPersona(long codigoPersona) {
        Query query = this.em.createNamedQuery(PersonaRelacionIfip.findByCodigoPersona);
        query.setParameter("codigoPersona", codigoPersona);
        try {
            return (PersonaRelacionIfip) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    public BigDecimal getItemByCodigoPersonaEliminado(long codigoPersona, char eliminado) {
        String sql = "SELECT sum(v1) val from (\n"
                + "SELECT count(*) v1\n"
                + "FROM mks_socios.PERSONA_RELACION_IFIP PRI WHERE PRI.CODIGO_PERSONA IN ( \n"
                + "SELECT PV.CODIGO_PERSONA FROM mks_socios.PERSONA_VINCULADO PV WHERE PV.CODIGO_PERSONA_VINCULADO = :codigoPersona AND PV.ELIMINADO = :eliminado UNION \n"
                + "SELECT PV.CODIGO_PERSONA_VINCULADO FROM mks_socios.PERSONA_VINCULADO PV WHERE PV.CODIGO_PERSONA = :codigoPersona AND PV.ELIMINADO = :eliminado ) AND PRI.ELIMINADO = :eliminado UNION \n"
                + "SELECT count(*) v1\n"
                + "FROM mks_socios.PERSONA_RELACION_IFIP WHERE CODIGO_PERSONA = :codigoPersona AND ELIMINADO = :eliminado)";
        Query query = em.createNativeQuery(sql);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("eliminado", eliminado);
        try {
            BigDecimal regs = (BigDecimal) query.getSingleResult();
            return regs;
        } catch (NoResultException | NonUniqueResultException ex) {
            return BigDecimal.ZERO;
        }
    }

    public Persona getItemByCodigoPersonaP(long codigoPersona) {
        Query query = this.em.createNamedQuery(PersonaRelacionIfip.findByCodigoPersonaP);
        query.setParameter("codigoPersona", codigoPersona);
        try {
            return (Persona) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    @Transactional
    public void actualiza(Long codigo, Long codigoCargo, Long codigoIfipAgencia, Date fechaRegistro, char eliminado) {
        Query query = em.createQuery("UPDATE PersonaRelacionIfip p "
                + "SET p.eliminado = :eliminado, \n"
                + "p.codigoCargo.codigo = :codigoCargo, \n"
                + "p.codigoIfipAgencia.codigo = :codigoIfipAgencia, \n"
                + "p.fechaRegistro = :fechaRegistro \n"
                + "WHERE p.codigo = :codigo");
        query.setParameter("codigo", codigo);
        query.setParameter("codigoCargo", codigoCargo);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("fechaRegistro", fechaRegistro);
        query.setParameter("eliminado", eliminado);
        query.executeUpdate();
    }
}
