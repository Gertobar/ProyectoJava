/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.LicitudFondosPerExcFor;
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
 * @author v.astudillo
 */
@Stateless
public class LicitudFondosPerExcForFacade extends AbstractFacade<LicitudFondosPerExcFor> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicitudFondosPerExcForFacade() {
        super(LicitudFondosPerExcFor.class);
    }
    
    /**
     * Obtiene el listado de personas excentas de acuerdo al estado
     * @param codigoIfip codigo de la Ifip
     * @param estado Estado de excepcion
     * @return Listado de Personas excentas de formulario
     */
    public List<LicitudFondosPerExcFor> getItemsCodigoIfipEstado(Long codigoIfip, char estado)
    {
        Query query = this.em.createNamedQuery(LicitudFondosPerExcFor.findByCodigoIfipEstado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    
    /**
     * Obtiene el listado de personas excentas de acuerdo a un rango de fechas de registro
     * @param codigoIfip codigo de la Ifip
     * @param fechaInicio Fecha de Inicio
     * @param fechaFin Fecha de Fin
     * @return Listado de Personas excentas de formulario
     */
    public List<LicitudFondosPerExcFor> getItemsCodigoIfipRangoFechaRegistro(Long codigoIfip, Date fechaInicio, Date fechaFin)
    {
        Query query = this.em.createNamedQuery(LicitudFondosPerExcFor.findByCodigoIfipRangoFechaRegistro);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
        query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
        return query.getResultList();
    }
    
    
     /**
     * Obtiene el listado de personas excentas de acuerdo a un rango de fechas de registro y estado
     * @param codigoIfip codigo de la Ifip
     * @param fechaInicio Fecha de Inicio
     * @param fechaFin Fecha de Fin
     * @param estado  Estado de excepcion
     * @return Listado de Personas excentas de formulario
     */
    public List<LicitudFondosPerExcFor> getItemsCodigoIfipRangoFechaRegistroEstado(Long codigoIfip, Date fechaInicio, Date fechaFin, char estado)
    {
        Query query = this.em.createNamedQuery(LicitudFondosPerExcFor.findByCodigoIfipRangoFechaRegistroEstado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
        query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    
    /**
     * Actualiza el estado de la excepcion
     * @param codigo Codigod de la Excepcion
     * @param estado Estado de la Excepcion
     * @param fechaEstado Fecha del estado
     * @param estadoCambiadoPor Codigo de Usuario quien cambio el estado
     * @param observacionesEstado  Observaciones del Estado
     */
    public void actualizaEstado(Long codigo, char estado, Date fechaEstado, Long estadoCambiadoPor, String observacionesEstado)
    {
        Query query = this.em.createQuery("UPDATE LicitudFondosPerExcFor l SET l.estado = :estado, l.estadoCambiadoPor = :estadoCambiadoPor, l.fechaEstado = :fechaEstado, l.observacionesEstado = :observacionesEstado WHERE l.codigo =:codigo");
        query.setParameter("estado", estado);
        query.setParameter("estadoCambiadoPor", estadoCambiadoPor);
        query.setParameter("fechaEstado", fechaEstado, TemporalType.TIMESTAMP);
        query.setParameter("observacionesEstado", observacionesEstado);
        query.setParameter("codigo", codigo);        
        query.executeUpdate();
    }
}
