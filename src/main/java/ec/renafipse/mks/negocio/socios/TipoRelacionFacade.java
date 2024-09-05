/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.TipoRelacion;
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
public class TipoRelacionFacade extends AbstractFacade<TipoRelacion> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoRelacionFacade() {
        super(TipoRelacion.class);
    }
    
     // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
   
    /**
     *Obtiene los tipos de relacion para la solicitud de socios.
     * @param esParaSolicitudSocio S=SI N=NO
     * @param eliminado S=SI N=NO
     * @return Listado de Periodicidades.
     */
    public List<TipoRelacion> getItemsEsParaSolicitudSocioEliminado(char esParaSolicitudSocio, char eliminado) {
        Query query = this.em.createNamedQuery(TipoRelacion.findByEsParaSolicitudSocioEliminado);
        query.setParameter("esParaSolicitudSocio", esParaSolicitudSocio);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
   /**
     *Obtiene los tipos de relacion para firmantes de cuentas
     * @param esParaFirmantes S=SI N=NO
     * @param eliminado S=SI N=NO
     * @return Listado de Periodicidades.
     */
    public List<TipoRelacion> getItemsEsParaFirmantesEliminado(char esParaFirmantes, char eliminado) {
        Query query = this.em.createNamedQuery(TipoRelacion.findByEsParaFirmantesEliminado);
        query.setParameter("esParaFirmantes", esParaFirmantes);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    
    public List<TipoRelacion> getItemsEsParaSegurosEliminado(char esParaSeguros, char eliminado) {
        Query query = this.em.createNamedQuery(TipoRelacion.findByEsParaSegurosEliminado);
        query.setParameter("esParaSeguros", esParaSeguros);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
}
