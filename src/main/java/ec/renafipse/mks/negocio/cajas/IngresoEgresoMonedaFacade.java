/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.IngresoEgreso;
import ec.renafipse.mks.modelo.cajas.IngresoEgresoMoneda;
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
public class IngresoEgresoMonedaFacade extends AbstractFacade<IngresoEgresoMoneda> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngresoEgresoMonedaFacade() {
        super(IngresoEgresoMoneda.class);
    }

    // -------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * OBTIENE LOS INGRESOS Y EGRESOS DE ACUERDO A LA MONEDA Y A LOS ESTADOS
     * ELIMINADO Y MOSTRAR
     *
     * @param codigoMoneda Codigo de la Moneda
     * @param esIngreso Si es Ingreso o Egreso
     * @param eliminado S=Si N=NO
     * @param mostrar S=Si N=NO
     * @return Lista de Ingresos y Egresos
     */
    public List<IngresoEgreso> getIngresoEgresoMonedaEliminadoMostrar(Long codigoMoneda, char esIngreso, char eliminado, char mostrar) {
        Query query = em.createNamedQuery(IngresoEgresoMoneda.findByIngresoEgresoMonedaEliminadoMostrar);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("eliminado", eliminado);
        query.setParameter("mostrar", mostrar);
        query.setParameter("esIngreso", esIngreso);

        return query.getResultList();
    }

  /**
   * 
   * @param esIngreso
   * @param eliminado
   * @param mostrar
   * @return 
   */
    public List<IngresoEgresoMoneda> getItemsIngresoEgresoPermisosFaltantes(char esIngreso, char eliminado, char mostrar) {
        Query query = em.createNamedQuery(IngresoEgresoMoneda.findByEsIngresoEliminado);
        query.setParameter("esIngreso", esIngreso);
        query.setParameter("eliminado", eliminado);
        query.setParameter("mostrar", mostrar);
        return query.getResultList();
    }
    
    /**
     * 
     * @param codigoMoneda
     * @param eliminado
     * @param tipo
     * @param contabilizaSN
     * @return 
     */
    public List<IngresoEgreso> getItemsIngresoEgreso(Long codigoMoneda, char eliminado,char tipo,char contabilizaSN) {
        List<IngresoEgreso> listIngresoEgreso;
        
        Query query = this.em.createNamedQuery(IngresoEgresoMoneda.findbyIfipMoneda);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("eliminado", eliminado);
        query.setParameter("tipo", tipo);
        query.setParameter("contabilizaSN", contabilizaSN);
        listIngresoEgreso = query.getResultList();
        return listIngresoEgreso;
    }
}
