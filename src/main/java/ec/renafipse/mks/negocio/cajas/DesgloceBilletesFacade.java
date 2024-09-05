/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.ahorros.MovimientoCuenta;
import ec.renafipse.mks.modelo.cajas.DesgloceBilletes;
import ec.renafipse.mks.modelo.comunes.ConsultasCriterioConstructor;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author willan
 */
@Stateless
public class DesgloceBilletesFacade extends AbstractFacade<DesgloceBilletes> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    public DesgloceBilletesFacade() {        
        super(DesgloceBilletes.class);
    }
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void guardar(){
        em.getTransaction().commit();
    }         
    
    public MovimientoCuenta obtenerMovimiento(Long codigo){
        try {
            Query consulta=em.createNamedQuery(MovimientoCuenta.findByCodigo);
            consulta.setParameter("codigo", codigo);
            MovimientoCuenta movimientoCuenta=(MovimientoCuenta) consulta.getSingleResult();
            
            //MovimientoCuenta movimientoCuenta=new ConsultasCriterioConstructor<MovimientoCuenta>(MovimientoCuenta.class,em).consultaPrimerDato("codigo", cogigo);
            return movimientoCuenta;
        } catch (Exception e) {
            return null;
        }     
    }
}
