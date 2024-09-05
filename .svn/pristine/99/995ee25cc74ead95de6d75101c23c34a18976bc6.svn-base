/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.auditorias;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.modelo.auditorias.LineaCreditoPlazoMaximoAud;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoPlazoMaximo;
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
public class LineaCreditoPlazoMaximoAudFacade extends AbstractFacade<LineaCreditoPlazoMaximoAud> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaCreditoPlazoMaximoAudFacade() {
        super(LineaCreditoPlazoMaximoAud.class);
    }
    
     /**
     * Guarda la auditoria de la tabla LineaCreditoPlazoMaximo
     * @param listaLineaCreditoPlazoMaximo
     * @param accion 
     */
    public void guardaLineaCreditoPlazoMaximoAuditoria(List<LineaCreditoPlazoMaximo> listaLineaCreditoPlazoMaximo,char accion){
        LineaCreditoPlazoMaximoAud lineaCreditoPlazoMaximoAud;
        for(LineaCreditoPlazoMaximo item : listaLineaCreditoPlazoMaximo){
            lineaCreditoPlazoMaximoAud = new LineaCreditoPlazoMaximoAud();
            lineaCreditoPlazoMaximoAud.setCodigoLineaCreditoPlaMax(item.getCodigo());
            lineaCreditoPlazoMaximoAud.setCodigoLineaCredito(item.getCodigoLineaCredito().getCodigo());
            lineaCreditoPlazoMaximoAud.setMontoInicioPlazo(item.getMontoInicioPlazo());
            lineaCreditoPlazoMaximoAud.setMontoFinPlazo(item.getMontoFinPlazo());
            lineaCreditoPlazoMaximoAud.setNumeroCuotasInicio(item.getNumeroCuotasInicio());
            lineaCreditoPlazoMaximoAud.setNumeroCuotasFin(item.getNumeroCuotasFin());
            lineaCreditoPlazoMaximoAud.setEliminado(item.getEliminado());
            lineaCreditoPlazoMaximoAud.setCodigoUsuario(ActivacionUsuario.getUsuario().getCodigo());
            lineaCreditoPlazoMaximoAud.setFechaRegistro(new Date());
            lineaCreditoPlazoMaximoAud.setAccion(accion);
            create(lineaCreditoPlazoMaximoAud);
        }
    }
}
