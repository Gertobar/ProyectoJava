/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.RetiroCheque;
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
public class RetiroChequeFacade extends AbstractFacade<RetiroCheque> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RetiroChequeFacade() {
        super(RetiroCheque.class);
    }

    /**
     * Obtiene los Chques Girados de acuerdo as su estado, ifip y agencias
     * @param codigoIfip Codigo de la Ifip
     * @param codigoIfipAgencia Codigo de la Gencia
     * @param estado Estado del Cheque Girado
     * @return  Lista de Cheques de Retiro de Dinero
     */
    public List<RetiroCheque> getItemsCodigiIfipCodigoAgenciaEstado(long codigoIfip, long codigoIfipAgencia, char estado) {
        Query query = this.em.createNamedQuery(RetiroCheque.findByCodigiIfipCodigoAgenciaEstado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("estado", estado);

        return query.getResultList();

    }
    
    /**
     * Obtiene los Chques Girados de acuerdo as dos estados, ifip y agencias
     * @param codigoIfip Codigo de la Ifip
     * @param codigoIfipAgencia Codigo de la Gencia
     * @param estadoUno Estado Uno del Cheque Girado
     * @param estadoDos Estado Dos del Cheque Girado
     * @return  Lista de Cheques de Retiro de Dinero
     */
    public List<RetiroCheque> getItemsCodigiIfipCodigoAgenciaEstadoUnoEstadoDos(long codigoIfip, long codigoIfipAgencia, char estadoUno, char estadoDos) {
        Query query = this.em.createNamedQuery(RetiroCheque.findByCodigiIfipCodigoAgenciaEstadoUnoEstadoDos);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("estadoUno", estadoUno);
        query.setParameter("estadoDos", estadoDos);

        return query.getResultList();

    }
}
