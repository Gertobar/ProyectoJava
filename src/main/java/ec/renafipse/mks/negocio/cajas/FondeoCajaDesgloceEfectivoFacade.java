/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.FondeoCaja;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDesgloceEfectivo;
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
public class FondeoCajaDesgloceEfectivoFacade extends AbstractFacade<FondeoCajaDesgloceEfectivo> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FondeoCajaDesgloceEfectivoFacade() {
        super(FondeoCajaDesgloceEfectivo.class);
    }
    
     // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    
    /**
     * Obtiene el desglocel del efectivo del fondeo de la moneda
     * @param codigoFondeo Codigo del Fondeo
     * @param codigoMoneda Codigo de la Moneda
     * @return Lista de Desgloce de Efectivo del Fondeo
     */
    public List<FondeoCajaDesgloceEfectivo> getItemsFondeoCajaDesgloceEfectivo(Long codigoFondeo, Long codigoMoneda )
    {        
        Query query = this.em.createNamedQuery(FondeoCajaDesgloceEfectivo.findByCodigoFondeoCodigoMoneda);
        query.setParameter("codigoFondeo", codigoFondeo);
        query.setParameter("codigoMoneda", codigoMoneda);
        
        return query.getResultList();
    }
    
}
