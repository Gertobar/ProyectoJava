/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera;
import ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDet;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class GrupoInstitucionAgeDetFacade extends AbstractFacade<GrupoInstitucionAgeDet> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoInstitucionAgeDetFacade() {
        super(GrupoInstitucionAgeDet.class);
    }

    //------------------------------------------------------------------------------
    //-- METODOS PERSONALIZADOS
    /**
     * Obtiene los grupos a los que pertenece el socio.
     *
     * @param codigoSocio Codigo de Socio
     * @param codigoIfip Codigod de la Ifip
     * @return Lista de Grupos
     */
    public List<GrupoInstitucionAgeDet> getItemsCodigoSocioCodigoIfip(long codigoSocio, long codigoIfip) {
        Query query = this.em.createNamedQuery(GrupoInstitucionAgeDet.findByCodigoSocioCodigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();

    }

    //---------------------------------------------------------------------------------------
    /**
     * Actualiza el estado eliminado     
     * @param eliminado S o N Eliminado
     * @param codigoGrupo Codigo del Grupo
     * @param codigoIfip Codigo de la Ifip
     * @param codigoSocio  Codigo del Socio
     */
    @Transactional
    public void actualizaElimiado(char eliminado, long codigoGrupo, long codigoIfip, long codigoSocio) {
        Query query = em.createQuery("UPDATE GrupoInstitucionAgeDet g "
                + "SET g.eliminado = :eliminado "
                + "WHERE g.grupoInstitucionAgeDetPK.codigoGrupo = :codigoGrupo "
                + "AND g.grupoInstitucionAgeDetPK.codigoIfip = :codigoIfip "
                + "AND g.grupoInstitucionAgeDetPK.codigoSocio = :codigoSocio"
        );        
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoGrupo", codigoGrupo);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.executeUpdate();

    }
    
     //---------------------------------------------------------------------------------------
    /**
     * Actualiza el grupo que el socio tiene asignado
     * @param codigoIfipAgencia Codigo de la Agencia
     * @param eliminado S o N Eliminado
     * @param valorAporte Valor de Aporte Enteros y Decimales (2)
     * @param codigoGrupo Codigo del Grupo
     * @param codigoIfip Codigo de la Ifip
     * @param codigoSocio  Codigo del Socio
     */
    @Transactional
    public void actualiza(long codigoIfipAgencia, char eliminado, BigDecimal valorAporte, long codigoGrupo, long codigoIfip, long codigoSocio) {
        Query query = em.createQuery("UPDATE GrupoInstitucionAgeDet g "
                + "SET g.codigoIfipAgencia = :codigoIfipAgencia, "
                + "g.eliminado = :eliminado, "
                + "g.valorAporte =:valorAporte "
                + "WHERE g.grupoInstitucionAgeDetPK.codigoGrupo = :codigoGrupo "
                + "AND g.grupoInstitucionAgeDetPK.codigoIfip = :codigoIfip "
                + "AND g.grupoInstitucionAgeDetPK.codigoSocio = :codigoSocio"
        );
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("eliminado", eliminado);
        query.setParameter("valorAporte", valorAporte);
        query.setParameter("codigoGrupo", codigoGrupo);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.executeUpdate();

    }

}
