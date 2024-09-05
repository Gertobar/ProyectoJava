/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.ImpresionLibretaCuenta;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class ImpresionLibretaCuentaFacade extends AbstractFacade<ImpresionLibretaCuenta> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImpresionLibretaCuentaFacade() {
        super(ImpresionLibretaCuenta.class);
    }

    public List<ImpresionLibretaCuenta> getItemsImpresionLibretaCuentaNumero(String numeroLibreta) {
        Query query = this.em.createNamedQuery(ImpresionLibretaCuenta.findByNumeroLibretaSocio);
        query.setParameter("numeroLibreta", numeroLibreta);
        return query.getResultList();

    }
    
    public int getUltimaLineaImpresaLibreta(String numeroLibreta) {
        Query query = this.em.createNamedQuery(ImpresionLibretaCuenta.findUltimaLineaImpresaLibreta);
        query.setParameter("numeroLibreta", numeroLibreta);
        List resultado=query.getResultList();
        System.out.println("Resultado "+resultado);
        if (resultado==null)
            return 0;
        else if (resultado.isEmpty()) 
            return 0; 
        else {
            if (resultado.get(0)==null)
                return 0;
            else 
                return Integer.parseInt(resultado.get(0).toString());        
        }

    }

}
