/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.reportes.ReporteDetalle;
import ec.renafipse.mks.modelo.reportes.ReporteGrupo;
import ec.renafipse.mks.modelo.seguridades.Rol;
import ec.renafipse.mks.modelo.seguridades.RolReporteDetalle;
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
public class RolReporteDetalleFacade extends AbstractFacade<RolReporteDetalle> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolReporteDetalleFacade() {
        super(RolReporteDetalle.class);
    }

    /**
     * Devuelve los Detalles de los reportes de acuerdo al rol y grupo
     * @param codigoRol Codigo de Rol
     * @param eliminado Eliminado S o N
     * @return Lista de Reporte de Detalle
     */
    public List<ReporteGrupo> getItemsReporteRolGrupoEliminado(String codigoRol, char eliminado) {
        Query query = em.createNamedQuery(RolReporteDetalle.findByReporteRolGrupoEliminado);
        query.setParameter("codigoRol", codigoRol);        
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    /**
     * Devuelve los Detalles de los reportes de acuerdo al rol y grupo
     * @param codigoRol Codigo de Rol
     * @param codigoGrupo Codigo de Grupo
     * @param eliminado Eliminado S o N
     * @return Lista de Reporte de Detalle
     */
    public List<ReporteDetalle> getItemsReporteDetalleRolGrupoEliminado(String codigoRol, long codigoGrupo, char eliminado) {
        Query query = em.createNamedQuery(RolReporteDetalle.findByReporteDetalleRolGrupoEliminado);
        query.setParameter("codigoRol", codigoRol);
        query.setParameter("codigoGrupo", codigoGrupo);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

}
