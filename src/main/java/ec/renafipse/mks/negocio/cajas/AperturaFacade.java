/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.cajas.FondeoCaja;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author vicastoc
 */
@Stateless
public class AperturaFacade extends AbstractFacade<Apertura> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AperturaFacade() {
        super(Apertura.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Obtiene las aperturas de la caja del usuario por el estado y en la
     * Agencia
     *
     * @param codigoUsuario Codigo del usuario
     * @param codigoIfipAgencia Codigo de la Agencia
     * @param estado Estado de la Apertura
     * @return Lista de Fondeos
     */
    public List<Apertura> getItemsAperturaUsuarioIfipAgencia(Long codigoUsuario, Long codigoIfipAgencia, char estado) {
        Query query = this.em.createNamedQuery(Apertura.findByAperturaUsuarioIfipAgencia);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("estado", estado);

        return query.getResultList();
    }

    /**
     * OBTIENE LAS APERTURAS DEL USUARIO DE ACUERDO EL ESTADO
     *
     * @param codigoUsuario Codigo de Usuario
     * @param estado Estado A=Aperturada C=Cerrada
     * @return Listado de Aperturas
     */
    public List<Apertura> getItemsUsuarioEstado(Long codigoUsuario, char estado) {
        Query query = this.em.createNamedQuery(Apertura.findByUsuarioEstado);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("estado", estado);

        return query.getResultList();
    }

    /**
     * OBTIENE LAS APERTURAS DE LA AGENCIA DE ACUERDO A LA APERTURA
     *
     * @param codigoIfipAgencia Codigo de la Ifip de la Agencia
     * @param estado Estado A=Aperturada C=Cerrada
     * @return Listado de Aperturas
     */
    public List<Apertura> getItemsIfipAgenciaEstado(Long codigoIfipAgencia, char estado) {
        Query query = this.em.createNamedQuery(Apertura.findByIfipAgenciaEstado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("estado", estado);

        return query.getResultList();
    }

    /**
     * OBTIENE LAS APERTURAS DE ACUERDO A LA FECHA LA AGENCIA Y EL USUARIO
     *
     * @param codigoUsuario Codigo del Usuario
     * @param codigoIfipAgencia Codigo de la Agencia
     * @param fechaApertura Fecha de la Apertura
     * @return Listado de Aperturas
     */
    public List<Apertura> getItemsUsuarioIfipAgenciaFechaApertura(Long codigoUsuario, Long codigoIfipAgencia, Date fechaApertura) {
        Query query = this.em.createNamedQuery(Apertura.findByUsuarioIfipAgenciaFechaApertura);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("codigoUsuario", codigoUsuario);
        query.setParameter("fechaApertura", fechaApertura, TemporalType.DATE);

        return query.getResultList();
    }

}
