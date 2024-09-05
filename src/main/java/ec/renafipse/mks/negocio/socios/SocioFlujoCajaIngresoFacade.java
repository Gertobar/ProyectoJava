/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.SocioFlujoCajaIngreso;
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
public class SocioFlujoCajaIngresoFacade extends AbstractFacade<SocioFlujoCajaIngreso> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocioFlujoCajaIngresoFacade() {
        super(SocioFlujoCajaIngreso.class);
    }
    
    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de Flujo de Caja de Ingresos del Socio en la IFIP
     * @param codigoSocio Codigo del Socio
     * @param codigoIfip Codigo de la Ifip
     * @return 
     */
    public List<SocioFlujoCajaIngreso> getItemsFlujoCajaIngresoSocio(Long codigoSocio, Long codigoIfip) {
        Query query = em.createNamedQuery(SocioFlujoCajaIngreso.findByCodigoSocioCodigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
    
    /**
     * Obtiene los items de flujo de caja de ingresos de una persona
     * @param codigoPersona
     * @return 
     */
    public List<SocioFlujoCajaIngreso> getItemsFlujoCajaIngresoPersona(Long codigoPersona) {
        Query query = em.createNamedQuery("SocioFlujoCajaIngreso.findByCodigoPersona", SocioFlujoCajaIngreso.class);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }
    
    /**
     * 
     * @param socioFlujoCajaIngreso
     */
    @Transactional
    public void actualiza(SocioFlujoCajaIngreso socioFlujoCajaIngreso) {
        Query query = em.createQuery("UPDATE SocioFlujoCajaIngreso c "
                                    + "SET totalSocio = :totalSocio, "
                                    + "totalConyuge = :totalConyuge "
                                    + "WHERE c.socioFlujoCajaIngresoPK = :socioFlujoCajaIngresoPK"
        );
        query.setParameter("totalSocio", socioFlujoCajaIngreso.getTotalSocio());
        query.setParameter("totalConyuge", socioFlujoCajaIngreso.getTotalConyuge());
        query.setParameter("socioFlujoCajaIngresoPK", socioFlujoCajaIngreso.getSocioFlujoCajaIngresoPK());
        query.executeUpdate();
    }
    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
}