/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.comunes.ConsultasCriterioConstructor;
import ec.renafipse.mks.modelo.socios.ClasePersona;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.RelacionPersona;
import ec.renafipse.mks.modelo.socios.TipoPersona;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class RelacionPersonaFacade extends AbstractFacade<RelacionPersona> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelacionPersonaFacade() {
        super(RelacionPersona.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS    
    /**
     * Obtiene los tipos de identificacion vigentes (No eliminados)
     *
     * @return Lista de Tipos de Identificacion
     */
    public List<TipoPersona> getItemsTipoPersonaVigentes() {
        Query query = em.createNamedQuery(RelacionPersona.findByEliminado);
        query.setParameter("eliminado", 'N');
        return query.getResultList();
    }

    /**
     * Obtiene el listado de tipos de persona de acuerdo al codigo
     *
     * @param codigo
     * @return Listao de TipoPersonas
     */
    public List<TipoPersona> getItemsCodigo(Long codigo) {
        Query query = em.createNamedQuery(RelacionPersona.findByCodigo);
        query.setParameter("codigo", 'N');
        return query.getResultList();
    }

    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
    /**
     * Obtiene el listado de tipos de persona de acuerdo al codigo
     *
     * @param codigo
     * @return Listao de TipoPersonas
     */
    public List<TipoPersona> getItemsTipoPersonabyCodigo(Long codigo) {
        Query query = em.createNamedQuery(RelacionPersona.findByCodigo);
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }

    /**
     * Metodo que permite actualizar un registro
     *
     * @param rp relacion de persona a actualizar
     * @return devuelve la entidad actualizada
     */
    public RelacionPersona actualizar(Persona persona, ClasePersona clasePersona, Persona personaRelacion, String descripcion) {
        RelacionPersona relacionPersona = null;
        relacionPersona = new ConsultasCriterioConstructor<RelacionPersona>(RelacionPersona.class, em).consultaPrimerDato("codigoPersona", persona);
        ConsultasCriterioConstructor ccc = new ConsultasCriterioConstructor<RelacionPersona>(RelacionPersona.class, em);
        if (relacionPersona == null) {
            relacionPersona = new RelacionPersona();
        }
        relacionPersona.setCodigoPersona(persona);
        relacionPersona.setCodigoClasePersona(clasePersona);
        relacionPersona.setDescripcion(descripcion);
        relacionPersona.setCodigoPersonaRelacion(personaRelacion);
        relacionPersona = (RelacionPersona) ccc.guardarOActualizar(relacionPersona);
        return relacionPersona;
    }

    /**
     * Metodo que permite actualizar un registro
     *
     * @param persona parametro de entrada
     * @return devuelve la entidad encontrada
     */
    public RelacionPersona buscarRelacionPersona(Persona persona) {
        RelacionPersona relacionPersona = null;
        try {
            relacionPersona = new ConsultasCriterioConstructor<RelacionPersona>(RelacionPersona.class, em).consultaPrimerDato("codigoPersona", persona);
        } catch (Exception e) {
        }
        return relacionPersona;
    }
}
