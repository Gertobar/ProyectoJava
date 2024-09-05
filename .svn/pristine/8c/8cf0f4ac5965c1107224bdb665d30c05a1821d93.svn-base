/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgencia;
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
public class GrupoInstitucionIfipAgenciaFacade extends AbstractFacade<GrupoInstitucionIfipAgencia> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoInstitucionIfipAgenciaFacade() {
        super(GrupoInstitucionIfipAgencia.class);
    }
    
    //---------------------------------------------------------------------------
    //-- METODOS PERSONALIZADOS
    
    /**
     * Obtiene los grupos asignados a la Agencia
     * @param codigoIfipAgencia Codigo de la Agencia de la IFIP
     * @param eliminado eliminado S=SI N=NO
     * @return Lista de Grupos
     */
    public List<GrupoInstitucionIfipAgencia> getItemsGrupoIfipAgenciaEliminado(long codigoIfipAgencia, char eliminado)
    {
        Query query = this.em.createNamedQuery(GrupoInstitucionIfipAgencia.findByGrupoIfipAgenciaEliminado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    //-- FIN DE PERSONALIZADOS
    //---------------------------------------------------------------------------
    
}
