/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonial;
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
public class SocioSituacionPatrimonialFacade extends AbstractFacade<SocioSituacionPatrimonial> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocioSituacionPatrimonialFacade() {
        super(SocioSituacionPatrimonial.class);
    }
    
    public SocioSituacionPatrimonial getSocioSocioSituacionPatrimonialCodigoSocioIfip(Long codigoSocio, Long codigoIfip) {
        List<SocioSituacionPatrimonial> lista = null;
        SocioSituacionPatrimonial socioSituacionPatrimonial = null;
        Query query = em.createNamedQuery("SocioSituacionPatrimonial.findByCodigoSocioIfip", SocioSituacionPatrimonial.class);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        if (!query.getResultList().isEmpty()) {
          List<SocioSituacionPatrimonial> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        if (lista != null) {
          socioSituacionPatrimonial = (SocioSituacionPatrimonial)lista.get(0);
        }
        return socioSituacionPatrimonial;
  }
    
  public SocioSituacionPatrimonial getPersonaSocioSituacionPatrimonial(Long codigoPersona) {
        List<SocioSituacionPatrimonial> lista = null;
        SocioSituacionPatrimonial socioSituacionPatrimonial = null;
        Query query = em.createNamedQuery("SocioSituacionPatrimonial.findByCodigoPersona", SocioSituacionPatrimonial.class);
        query.setParameter("codigoPersona", codigoPersona);
        if (!query.getResultList().isEmpty()) {
          List<SocioSituacionPatrimonial> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        if (lista != null) {
          socioSituacionPatrimonial = (SocioSituacionPatrimonial)lista.get(0);
        }
        return socioSituacionPatrimonial;
  }
/**
     *
     * @param socioFlujoCaja
     */
    @Transactional
    public void actualiza(SocioSituacionPatrimonial socioSituacionPatrimonial) {
        Query query = em.createQuery("UPDATE SocioSituacionPatrimonial c "
                + "SET totalActivos = :totalActivos, "
                + "totalPasivos = :totalPasivos, "
                + "totalPatrimonio = :totalPatrimonio, "
                + "fechaActualizacion = :fechaActualizacion, "
                + "codigoUsuarioActualizo = :codigoUsuarioActualizo "
                + "WHERE c.codigoPersona = :codigoPersona");
        query.setParameter("totalActivos", socioSituacionPatrimonial.getTotalActivos());
        query.setParameter("totalPasivos", socioSituacionPatrimonial.getTotalPasivos());
        query.setParameter("totalPatrimonio", socioSituacionPatrimonial.getTotalPatrimonio());
        query.setParameter("fechaActualizacion", socioSituacionPatrimonial.getFechaActualizacion());
        query.setParameter("codigoUsuarioActualizo", socioSituacionPatrimonial.getCodigoUsuarioActualizo());
        query.setParameter("codigoPersona", socioSituacionPatrimonial.getCodigoPersona());
        query.executeUpdate();
    }
    
}