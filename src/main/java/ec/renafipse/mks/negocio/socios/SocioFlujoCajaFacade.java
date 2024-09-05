/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.SocioFlujoCaja;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
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
public class SocioFlujoCajaFacade extends AbstractFacade<SocioFlujoCaja> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocioFlujoCajaFacade() {
        super(SocioFlujoCaja.class);
    }
    
    public SocioFlujoCaja getSocioFlujoCajaCodigoSocioIfip(Long codigoSocio, Long codigoIfip) {
        List<SocioFlujoCaja> lista = null;
        SocioFlujoCaja socioFlujoCaja = null;
        Query query = em.createNamedQuery("SocioFlujoCaja.findByCodigoSocioIfip", SocioFlujoCaja.class);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        if (!query.getResultList().isEmpty()) {
          List<SocioFlujoCaja> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        if (lista != null) {
          socioFlujoCaja = (SocioFlujoCaja)lista.get(0);
        }
        return socioFlujoCaja;
  }
    
    public SocioFlujoCaja getSocioFlujoCajaCodigoPersonaIfip(Long codigoPersona, Long codigoIfip){
         List<SocioFlujoCaja> lista = null;
        SocioFlujoCaja socioFlujoCaja = null;
        Query query = em.createNamedQuery("SocioFlujoCaja.findByCodigoPersonaIfip", SocioFlujoCaja.class);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoIfip", codigoIfip);
        if (!query.getResultList().isEmpty()) {
          List<SocioFlujoCaja> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        if (lista != null) {
          socioFlujoCaja = (SocioFlujoCaja)lista.get(0);
        }
        return socioFlujoCaja;
    }
    
   /**
     * 
     * @param socioFlujoCaja 
     */
    @Transactional
    public void actualiza(SocioFlujoCaja socioFlujoCaja) {
        Query query = em.createQuery("UPDATE SocioFlujoCaja c "
                                    + "SET totalIngresosSocio = :totalIngresosSocio, "
                                    + "totalIngresosConyuge = :totalIngresosConyuge, "
                                    + "totalGastosSocio = :totalGastosSocio, "
                                    + "saldo = :saldo, "
                                    + "cobertura = :cobertura, "
                                    + "fechaActualizacion = :fechaActualizacion, "
                                    + "codigoUsurioActualizo = :codigoUsurioActualizo "
                                    + "WHERE c.socioFlujoCajaPK = :socioFlujoCajaPK");
        query.setParameter("totalIngresosSocio", socioFlujoCaja.getTotalIngresosSocio());
        query.setParameter("totalIngresosConyuge", socioFlujoCaja.getTotalIngresosConyuge());
        query.setParameter("totalGastosSocio", socioFlujoCaja.getTotalGastosSocio());        
        query.setParameter("saldo", socioFlujoCaja.getSaldo());
        query.setParameter("cobertura", socioFlujoCaja.getCobertura());
        query.setParameter("fechaActualizacion", socioFlujoCaja.getFechaActualizacion());
        query.setParameter("codigoUsurioActualizo", socioFlujoCaja.getCodigoUsuarioActualizo());
        query.setParameter("codigoPersona", socioFlujoCaja.getCodigoPersona());
        query.executeUpdate();
    }      
}