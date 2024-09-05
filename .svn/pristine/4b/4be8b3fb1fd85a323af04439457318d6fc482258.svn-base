/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.auditorias;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.modelo.auditorias.LineaCreditoPlazoAuditoria;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoPlazo;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andres
 */
@Stateless
public class LineaCreditoPlazoAuditoriaFacade extends AbstractFacade<LineaCreditoPlazoAuditoria> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaCreditoPlazoAuditoriaFacade() {
        super(LineaCreditoPlazoAuditoria.class);
    }
    
    /**
     * Guarda la auditoria de la tabla LineaCreditoPlazo
     * @param listaLineaCreditoPlazo
     * @param accion 
     */
    public void guardaLineaCreditoPlazoAuditoria(List<LineaCreditoPlazo> listaLineaCreditoPlazo,char accion){
        LineaCreditoPlazoAuditoria lineaCreditoPlazoAuditoria;
        for (LineaCreditoPlazo item : listaLineaCreditoPlazo){
            lineaCreditoPlazoAuditoria = new LineaCreditoPlazoAuditoria();
            lineaCreditoPlazoAuditoria.setCodigoLineaCreditoPlazo(item.getCodigo());
            lineaCreditoPlazoAuditoria.setCodigoLineaCredito(item.getCodigoLineaCredito().getCodigo());
            lineaCreditoPlazoAuditoria.setMontoInicioPlazo(item.getMontoInicioPlazo());
            lineaCreditoPlazoAuditoria.setMontoFinPlazo(item.getMontoFinPlazo());
            lineaCreditoPlazoAuditoria.setNumeroCuotas(item.getNumeroCuotas());
            lineaCreditoPlazoAuditoria.setEliminado(item.getEliminado());
            lineaCreditoPlazoAuditoria.setCodigoUsuario(ActivacionUsuario.getUsuario().getCodigo());
            lineaCreditoPlazoAuditoria.setFechaRegistro(new Date());
            lineaCreditoPlazoAuditoria.setAccion(accion);
            create(lineaCreditoPlazoAuditoria);
        }
    }
}
