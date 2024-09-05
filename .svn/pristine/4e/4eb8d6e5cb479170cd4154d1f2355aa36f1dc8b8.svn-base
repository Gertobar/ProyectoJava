/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.Comprobante;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.ArrayList;
import java.util.Date;
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
public class ComprobanteFacade extends AbstractFacade<Comprobante> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComprobanteFacade() {
        super(Comprobante.class);
    }

    public List<Comprobante> getItemsComprobante(char buscarPor, Date fecha, String numeroComprobante, Long codigoIfip, Long codigoIfipAgencia) {
        List<Comprobante> listComprobante = null;
        if (buscarPor == 'F') {
            Query query = em.createNamedQuery(Comprobante.findByFechaAgenciaIFIP);
            query.setParameter("fecha", fecha);
            query.setParameter("codigoIfip", codigoIfip);
            query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
            listComprobante = query.getResultList();
        }
        if (buscarPor == 'N') {
            Query query = em.createNamedQuery(Comprobante.findByNumeroComprobanteAgenciaIFIP);
            query.setParameter("numeroComprobante", numeroComprobante);
            query.setParameter("codigoIfip", codigoIfip);
            query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
            listComprobante = query.getResultList();
        }
        return listComprobante;
    }

    public List<String> getItemsPeriodo() {
        List<String> listPeriodos = new ArrayList<String>();
        List<Comprobante> listComprobantes = null;
        Query query = em.createNamedQuery(Comprobante.findPeriodo);
        listComprobantes = query.getResultList();
        for (Comprobante comprobante : listComprobantes) {
            if (!listPeriodos.contains(comprobante.getCodigoPeriodo().getCodigo())) {
                listPeriodos.add(comprobante.getCodigoPeriodo().getCodigo());
            } 
        }
        return listPeriodos;
    }
    
    public List<Comprobante> getItemsComprobanteDetalleFechaIfip(Long codigoIfip, Date fechaInicio,Date fechaFin){
        Query query = em.createNamedQuery(Comprobante.findByIfipEntreFecha);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.getResultList();
    }

}
