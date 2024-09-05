/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.SolicitudMotivoDevolucion;
import ec.renafipse.mks.modelo.creditos.SolicitudPreAprobadaNegada;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Santiago Araujo
 */
@Stateless
public class SolicitudMotivoDevolucionFacade extends AbstractFacade<SolicitudMotivoDevolucion> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
        protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudMotivoDevolucionFacade() {
        super(SolicitudMotivoDevolucion.class);
    }
    /***
     * Metodo para obtener los motivos de devolucion vigentes para una ifip
     * @param codigoIfip
     * @param vigente
     * @return 
     */
    public List<SolicitudMotivoDevolucion> getMotivosPorVigente(Long codigoIfip, String vigente){
        Query query = this.em.createNamedQuery(SolicitudMotivoDevolucion.findByVigente);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("vigente", vigente);
        List<SolicitudMotivoDevolucion> lista = query.getResultList();
        List<SolicitudMotivoDevolucion> motivos = new ArrayList();
        //Crear en la lista Seleccione
        SolicitudMotivoDevolucion solicitudMotivo = new SolicitudMotivoDevolucion(0L,codigoIfip,"Seleccione en caso de devolucion...","S");
        motivos.add(solicitudMotivo);
        for(int a=0;a<lista.size();a++)
            motivos.add(lista.get(a));
        return motivos;
    }
    
    /***
     * Metodo para obtener el motivo de devolucion a traves del codigo
     * @param codigo
     * @return 
     */
    public SolicitudMotivoDevolucion getMotivoPorCodigo(Long codigo){
        Query query = this.em.createNamedQuery(SolicitudMotivoDevolucion.findByCodigo);
        query.setParameter("codigo", codigo);
        SolicitudMotivoDevolucion motivo = (SolicitudMotivoDevolucion)query.getResultList().get(0);
        return motivo;
    }
}
