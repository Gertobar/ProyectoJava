/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.LicitudFonCptoTran;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class LicitudFonCptoTranFacade extends AbstractFacade<LicitudFonCptoTran> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicitudFonCptoTranFacade() {
        super(LicitudFonCptoTran.class);
    }
    
    /**
     * Obtiene la transaccion de acuerdo al tipo
     * @param codigoConcepto Codigo de Concepto
     * @param codigoMoneda Codigo de Moneda
     * @param codigoTipoProducto Codigo del Tipo de Producto
     * @param codigoIfip Codigo de la Ifip
     * @param eliminado Eliminado S=SI N=NO
     * @return  Lista de Transacciones de licitud de Fondos.
     */
    public List<LicitudFonCptoTran> getByTipo(Long codigoConcepto, Long codigoMoneda, Long codigoTipoProducto, Long codigoIfip, char eliminado)
    {
        Query query = this.em.createNamedQuery(LicitudFonCptoTran.findByTipo);
        query.setParameter("codigoConcepto", codigoConcepto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        
        return query.getResultList();
    }
}
