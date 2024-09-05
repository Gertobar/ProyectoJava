/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.auditorias;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.modelo.auditorias.LineaCreditoAuditoria;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCredito;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andres
 */
@Stateless
public class LineaCreditoAuditoriaFacade extends AbstractFacade<LineaCreditoAuditoria> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaCreditoAuditoriaFacade() {
        super(LineaCreditoAuditoria.class);
    }
    
    /**
     * Guarda la auditoria de la tabla LineaCredito
     * @param lineaCredito
     * @param accion 
     */
    public void guardaLineaCreditoAuditoria(LineaCredito lineaCredito, char accion){
        LineaCreditoAuditoria lineaCreditoAuditoria = new LineaCreditoAuditoria();
        lineaCreditoAuditoria.setCodigoLineaCredito(lineaCredito.getCodigo());
        lineaCreditoAuditoria.setCodigoProducto(lineaCredito.getCodigoProducto().getCodigo());
        lineaCreditoAuditoria.setCodigoMoneda(lineaCredito.getCodigoMoneda().getCodigo());
        lineaCreditoAuditoria.setCodigoPeriodicidad(lineaCredito.getCodigoPeriodicidad().getCodigo());
        lineaCreditoAuditoria.setCodigoOrigenRecursos(lineaCredito.getCodigoOrigenRecursos().getCodigo());
        lineaCreditoAuditoria.setNombre(lineaCredito.getNombre());
        lineaCreditoAuditoria.setDescripcion(lineaCredito.getDescripcion());
        lineaCreditoAuditoria.setMontoMinimo(lineaCredito.getMontoMinimo());
        lineaCreditoAuditoria.setMontoMaximo(lineaCredito.getMontoMaximo());
        lineaCreditoAuditoria.setDiaGeneraCredito(lineaCredito.getDiaGeneraCredito());
        lineaCreditoAuditoria.setPorcentajePagoMinimo(lineaCredito.getPorcentajePagoMinimo());
        lineaCreditoAuditoria.setDiaParaBloqueo(lineaCredito.getDiaParaBloqueo());
        lineaCreditoAuditoria.setTipoTabla(lineaCredito.getTipoTabla());
        lineaCreditoAuditoria.setPlazoAutomatico(lineaCredito.getPlazoAutomatico());
        lineaCreditoAuditoria.setVigente(lineaCredito.getVigente());
        lineaCreditoAuditoria.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
        lineaCreditoAuditoria.setCodigoUsuario(ActivacionUsuario.getUsuario().getCodigo());
        lineaCreditoAuditoria.setFechaRegistro(new Date());
        lineaCreditoAuditoria.setAccion(accion);
        create(lineaCreditoAuditoria);
    }
    
}
