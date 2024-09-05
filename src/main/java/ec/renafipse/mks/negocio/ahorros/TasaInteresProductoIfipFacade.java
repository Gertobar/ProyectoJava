/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.TasaInteresProductoIfip;
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
public class TasaInteresProductoIfipFacade extends AbstractFacade<TasaInteresProductoIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TasaInteresProductoIfipFacade() {
        super(TasaInteresProductoIfip.class);
    }

    public List<TasaInteresProductoIfip> getItemsTasaInteresProductoIfipI(long tipoProducto,long codigo_tasa,long rangoFinal,long rangoInicial, char eliminado) {
        Query query = this.em.createNamedQuery(TasaInteresProductoIfip.findByRangos);
        query.setParameter("codigoTipoProducto", tipoProducto);
        query.setParameter("codigoTasaInteres",codigo_tasa);
        query.setParameter("rFinal", rangoFinal);
        query.setParameter("eliminado", eliminado);
        query.setParameter("rInicial", rangoInicial);

        return query.getResultList();

    }
      public List<TasaInteresProductoIfip> getItemsTasaInteresProductoIfip(long tipoProducto,long codigoIfip,long codigoMoneda,long rangoInicial) {
        Query query = this.em.createNamedQuery(TasaInteresProductoIfip.findByUniqui);
        query.setParameter("codigoTipoProducto", tipoProducto);
        query.setParameter("codigoIfip",codigoIfip);
        query.setParameter("codigoMoneda",codigoMoneda);
        query.setParameter("rangoInicial", rangoInicial);

        return query.getResultList();

    }



}
