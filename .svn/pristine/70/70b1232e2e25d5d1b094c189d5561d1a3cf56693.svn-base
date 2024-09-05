/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.SocioSituacionPatAct;
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
public class SocioSituacionPatActFacade extends AbstractFacade<SocioSituacionPatAct> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocioSituacionPatActFacade() {
        super(SocioSituacionPatAct.class);
    }
    
      // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de la Situacion Patrimonial de los Activos del Socio en la IFIP
     * @param codigoSocio Codigo del Socio
     * @param codigoIfip Codigo de la Ifip
     * @return 
     */
    public List<SocioSituacionPatAct> getItemsSocioSituacionPatAct(Long codigoSocio, Long codigoIfip)
    {
        Query query = em.createNamedQuery(SocioSituacionPatAct.findByCodigoSocioCodigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
    
    /**
     * Obtiene los items de la Situacion Patrimonial de los Activos de una persona
     * @param codigoPersona
     * @return 
     */
    public List<SocioSituacionPatAct> getItemsPersonaSituacionPatAct(Long codigoPersona) {
        Query query = em.createNamedQuery("SocioSituacionPatAct.findByCodigoPersona", SocioSituacionPatAct.class);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }
    
    /**
     * 
     * @param socioSituacionPatAct
     */
    @Transactional
    public void actualiza(SocioSituacionPatAct socioSituacionPatAct) {
        Query query = em.createQuery("UPDATE SocioSituacionPatAct c "
                                    + "SET total = :total "
                                    + "WHERE c.socioSituacionPatActPK = :socioSituacionPatActPK"
        );
        query.setParameter("total", socioSituacionPatAct.getTotal());
        query.setParameter("socioSituacionPatActPK", socioSituacionPatAct.getSocioSituacionPatActPK());
        query.executeUpdate();
    }
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
    
}