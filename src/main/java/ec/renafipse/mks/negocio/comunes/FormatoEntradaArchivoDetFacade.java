/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.comunes;

import ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivoDet;
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
public class FormatoEntradaArchivoDetFacade extends AbstractFacade<FormatoEntradaArchivoDet> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormatoEntradaArchivoDetFacade() {
        super(FormatoEntradaArchivoDet.class);
    }

    public List<FormatoEntradaArchivoDet> getListaFormatoEntradaArchivoDetallePorFormato(Long codigoFormato) {
        List<FormatoEntradaArchivoDet> lista = null;
        Query query = em.createNamedQuery("FormatoEntradaArchivoDet.findByCodigoFormato", FormatoEntradaArchivoDet.class);
        query.setParameter("codigoFormato", codigoFormato);
        if (!query.getResultList().isEmpty()) {
            List<FormatoEntradaArchivoDet> resultado = query.getResultList();
            if (resultado.size() > 0) {
                lista = resultado;
            }
        }
        return lista;
    }
    
    public List<FormatoEntradaArchivoDet> getListaFormatoEntradaArchivoDetallePorNombreFormato(String nombreFormato) {
        List<FormatoEntradaArchivoDet> lista = null;
        Query query = em.createNamedQuery("FormatoEntradaArchivoDet.findByNombreFormato", FormatoEntradaArchivoDet.class);
        query.setParameter("nombreFormato", nombreFormato);
        if (!query.getResultList().isEmpty()) {
            List<FormatoEntradaArchivoDet> resultado = query.getResultList();
            if (resultado.size() > 0) {
                lista = resultado;
            }
        }
        return lista;
    }
}
