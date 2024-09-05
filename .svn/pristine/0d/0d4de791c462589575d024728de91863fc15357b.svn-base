/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.SocioSituacionPatPas;
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
public class SocioSituacionPatPasFacade extends AbstractFacade<SocioSituacionPatPas> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocioSituacionPatPasFacade() {
        super(SocioSituacionPatPas.class);
    }
    
      // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de la Situacion Patrimonial de los Pasivos del Socio en la IFIP
     * @param codigoSocio Codigo del Socio
     * @param codigoIfip Codigo de la Ifip
     * @return 
     */
    public List<SocioSituacionPatPas> getItemsSocioSituacionPatPas(Long codigoSocio, Long codigoIfip)
    {
        Query query = em.createNamedQuery(SocioSituacionPatPas.findByCodigoSocioCodigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
    
    /**
     * Obtiene los items de la Situacion Patrimonial de los Pasivos de una persona
     * @param codigoPersona
     * @return 
     */
    public List<SocioSituacionPatPas> getItemsPersonaSituacionPatPas(Long codigoPersona) {
        Query query = em.createNamedQuery("SocioSituacionPatPas.findByCodigoPersona", SocioSituacionPatPas.class);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }
    
    /**
     * 
     * @param socioSituacionPatAct
     */
    @Transactional
    public void actualiza(SocioSituacionPatPas socioSituacionPatPas) {
        Query query = em.createQuery("UPDATE SocioSituacionPatPas c "
                                    + "SET total = :total "
                                    + "WHERE c.socioSituacionPatPasPK = :socioSituacionPatPasPK"
        );
        query.setParameter("total", socioSituacionPatPas.getTotal());
        query.setParameter("socioSituacionPatPasPK", socioSituacionPatPas.getSocioSituacionPatPasPK());
        query.executeUpdate();
    }
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
    
}