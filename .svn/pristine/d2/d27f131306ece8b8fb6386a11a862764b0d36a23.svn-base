/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.comunes;

import ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivo;
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
public class FormatoEntradaArchivoFacade extends AbstractFacade<FormatoEntradaArchivo> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormatoEntradaArchivoFacade() {
        super(FormatoEntradaArchivo.class);
    }

    public FormatoEntradaArchivo getFormatoEntradaArchivoPorNombre(String nombre) {
        List<FormatoEntradaArchivo> lista = null;
        FormatoEntradaArchivo formatoEntradaArchivo = null;
        Query query = em.createNamedQuery("FormatoEntradaArchivo.findByNombre", FormatoEntradaArchivo.class);
        query.setParameter("nombre", nombre);
        if (!query.getResultList().isEmpty()) {
            List<FormatoEntradaArchivo> resultado = query.getResultList();
            if (resultado.size() > 0) {
                lista = resultado;
            }
        }
        if (lista != null) {
            formatoEntradaArchivo = (FormatoEntradaArchivo) lista.get(0);
        }
        return formatoEntradaArchivo;
    }

}
