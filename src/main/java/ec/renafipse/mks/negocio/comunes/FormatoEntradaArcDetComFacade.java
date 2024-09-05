/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.comunes;

import ec.renafipse.mks.modelo.comunes.FormatoEntradaArcDetCom;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andres
 */
@Stateless
public class FormatoEntradaArcDetComFacade extends AbstractFacade<FormatoEntradaArcDetCom> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormatoEntradaArcDetComFacade() {
        super(FormatoEntradaArcDetCom.class);
    }
    
    public List<FormatoEntradaArcDetCom> getListaFormatoEntradaArchivoDetalleComprimidoPorFormato(Long codigoFormato) {
        List<FormatoEntradaArcDetCom> lista = null;
        Query query = em.createNamedQuery("FormatoEntradaArcDetCom.findByCodigoFormato", FormatoEntradaArcDetCom.class);
        query.setParameter("codigoFormato", codigoFormato);
        if (!query.getResultList().isEmpty()) {
            List<FormatoEntradaArcDetCom> resultado = query.getResultList();
            if (resultado.size() > 0) {
                lista = resultado;
            }
        }
        return lista;
    }

}
