/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class SolicitudDetalleFacade extends AbstractFacade<SolicitudDetalle> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudDetalleFacade() {
        super(SolicitudDetalle.class);
    }

    public List<Solicitud> getItemsCodigoSocioCodigoIfipMora(Long codigoSocio, Long codigoIfip, BigDecimal mora) {
        Query query = this.em.createNamedQuery(SolicitudDetalle.findByCodigoSocioCodigoIfipMora);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("mora", mora);
        return query.getResultList();
    }
    
    public List<SolicitudDetalle> getItemsDetalleCodigoSocioCodigoIfipMora(Long codigoSocio, Long codigoIfip, BigDecimal mora) {
        Query query = this.em.createNamedQuery(SolicitudDetalle.findDetByCodigoSocioCodigoIfipMora);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("mora", mora);
        return query.getResultList();
    }

    public List<Solicitud> getItemsSolicitudNombreSocioCodigoIfipMora(String nombreCompleto, Long codigoIfip, BigDecimal mora) {
        Query query = this.em.createNamedQuery(SolicitudDetalle.findByNombreSocioCodigoIfipMora);
        query.setParameter("nombreCompleto", nombreCompleto);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("mora", mora);
        return query.getResultList();
    }
    
    public List<Solicitud> getItemsSolicitudCodSocioCodIfipDiasMora(long codigoSocio, Long codigoIfip, long mora) {
        Query query = this.em.createNamedQuery(SolicitudDetalle.findByCodSocioCodIfipDiasMora);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("mora", mora);
        return query.getResultList();
    }

    public List<SolicitudDetalle> getItemSolicitudCreditoNumeroIfipSaldoCapital(Long numeroCredito, Long codigoIfip, BigDecimal saldoCapital) {
        Query query = this.em.createNamedQuery(SolicitudDetalle.findByNumeroSolicitudIfipSaldoCapital);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("saldoCapital", saldoCapital);
        return query.getResultList();
    }
    
    public List<SolicitudDetalle> getItemSolicitudCreditoNumeroIfip(Long numeroCredito, Long codigoIfip) {
        Query query = this.em.createNamedQuery(SolicitudDetalle.findByNumeroSolicitudIfip);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
    
    public List<SolicitudDetalle> getItemSolicitudCreditoNumeroIfipMora(Long numeroCredito, Long codigoIfip, BigDecimal mora) {
        Query query = this.em.createNamedQuery(SolicitudDetalle.findByNumeroSolicitudIfipMora);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("mora", mora);
        return query.getResultList();
    }

}
