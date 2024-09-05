/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.SocioFlujoCajaEgreso;
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
public class SocioFlujoCajaEgresoFacade extends AbstractFacade<SocioFlujoCajaEgreso> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocioFlujoCajaEgresoFacade() {
        super(SocioFlujoCajaEgreso.class);
    }
    
     // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de Flujo de Caja de Egresos del Socio en la IFIP
     * @param codigoSocio Codigo del Socio
     * @param codigoIfip Codigo de la Ifip
     * @return 
     */
    public List<SocioFlujoCajaEgreso> getItemsFlujoCajaEgresoSocio(Long codigoSocio, Long codigoIfip)
    {
        Query query = em.createNamedQuery(SocioFlujoCajaEgreso.findByCodigoSocioCodigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
    
    /**
     * Obtiene los items de flujo de caja de egresos de una persona
     * @param codigoPersona
     * @return 
     */
    public List<SocioFlujoCajaEgreso> getItemsFlujoCajaEgresoPersona(Long codigoPersona) {
        Query query = em.createNamedQuery("SocioFlujoCajaEgreso.findByCodigoPersona", SocioFlujoCajaEgreso.class);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }
    
    /**
     * 
     * @param socioFlujoCajaEgreso
     */
    @Transactional
    public void actualiza(SocioFlujoCajaEgreso socioFlujoCajaEgreso) {
        Query query = em.createQuery("UPDATE SocioFlujoCajaEgreso c "
                                    + "SET totalSocio = :totalSocio "
                                    + "WHERE c.socioFlujoCajaEgresoPK = :socioFlujoCajaEgresoPK"
        );
        query.setParameter("totalSocio", socioFlujoCajaEgreso.getTotalSocio());
        query.setParameter("socioFlujoCajaEgresoPK", socioFlujoCajaEgreso.getSocioFlujoCajaEgresoPK());
        query.executeUpdate();
    }
    
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
    
}