/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.dpfs.TalonarioDocumentoDpfDet;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class TalonarioDocumentoDpfDetFacade extends AbstractFacade<TalonarioDocumentoDpfDet> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TalonarioDocumentoDpfDetFacade() {
        super(TalonarioDocumentoDpfDet.class);
    }

    //--------------------------------------------------------------------------
    //PERSONALIZADOS
    public TalonarioDocumentoDpfDet getItemDocumentoContratoDpf(Long codigoIfip, Long codigoComputador, Long codigoMoneda, Long codigoIfipAgencia, char estado) {
        Query query = this.em.createNamedQuery(TalonarioDocumentoDpfDet.findBySerieContratoDpf);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoComputador", codigoComputador);
        query.setParameter("estado", estado);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        
        //System.out.println("getItemSerieContratoDpf " + query.getSingleResult());

        Long serie = (Long) query.getSingleResult();

        //System.out.println("serie " + serie);
        if (serie != null) {
            query = em.createNamedQuery(TalonarioDocumentoDpfDet.findByCodigoIfipSerie);
            query.setParameter("codigoIfip", codigoIfip);
            query.setParameter("serie", serie);
            //System.out.println("getItemSerieContratoDpf " + query.getResultList());
            List<TalonarioDocumentoDpfDet> listaTalonarioDocumentoDpfDet = query.getResultList();
            if (!listaTalonarioDocumentoDpfDet.isEmpty()) {
                return listaTalonarioDocumentoDpfDet.get(0);
            } else {
                return null;
            }

        }

        return null;
    }

    /**
     * Obtiene los documentos de los DPFS para la anulacion
     * @param codigoIfip Codigo de la Ifip
     * @param codigoComputador Codigo del Computador
     * @param codigoMoneda Codigo de la Moneda
     * @param estado Estado P=Pendiente A=Asignado N=Anulado
     * @param codigoIfipAgencia Codigo de Agencia
     * @param inicioSerie Inicio de la serie para la busqueda
     * @param finSerie Fin de la serie para la busqueda
     * @return 
     */
    public List<TalonarioDocumentoDpfDet> getItemsAnulaDocumento(Long codigoIfip, Long codigoComputador,
            Long codigoMoneda, char estado, Long codigoIfipAgencia,
            Long inicioSerie, Long finSerie) {
        Query query = this.em.createNamedQuery(TalonarioDocumentoDpfDet.findByAnulaDocumento);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoComputador", codigoComputador);
        query.setParameter("estado", estado);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("inicioSerie", inicioSerie);
        query.setParameter("finSerie", finSerie);

        return query.getResultList();

    }
}
