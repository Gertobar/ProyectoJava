/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.Computador;


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
public class ComputadorFacade extends AbstractFacade<Computador> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComputadorFacade() {
        super(Computador.class);
    }
    
    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los computadores de una ifip y agencia, destinados a un fin y que no esten eliminados
     * @param codigoIfip Codigo de la IFIP
     * @param codigoIfipAgencia Codigode la Agencia de la IFIP
     * @param eliminado S=SI N=NO
     * @param codigoDestino COdigo del Destino del computador
     * @return  Lista de Computadores.
     */
    public List<Computador> getItemsIfipAgenciaDestinoEliminado(Long codigoIfip, Long codigoIfipAgencia, Long codigoDestino, char eliminado)
    {
        Query query = em.createNamedQuery(Computador.findByIfipAgenciaDestinoEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("codigoDestino", codigoDestino);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    /**
     * Obtiene los computadores por medio la Direccion IP, codigo de la Ifip Agencia y eliminado    
     * @param codigoIfip Codigo de la Ifip
     * @param direccionIp Direccion Ip
     * @param eliminado Eliminado S=Si N=No
     * @return Lista de Computadores
     */
    public List<Computador> getItemsDireccionIpIfipAgenciaEliminado(Long codigoIfip, String direccionIp, char eliminado)
    {
        Query query = em.createNamedQuery(Computador.findByDireccionIpIfipEliminado);        
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("direccionIp", direccionIp);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    public List<Computador> getItemsIfipAgenciaEliminado(Long codigoIfip, Long codigoIfipAgencia, char eliminado)
    {
        Query query = em.createNamedQuery(Computador.findByIfipAgenciaEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
     public List<Computador> getItemIfipComputador(Long codigoIfip)
    {
        Query query = em.createNamedQuery(Computador.findByIfipComputador);
        query.setParameter("codigoIfip", codigoIfip);
        
        return query.getResultList();
    }
     
     public List<Computador> getItemsCodigoComputadorIfiAgenPerEli(Long codigoComputador, Long codigoIfip, Long codigoIfipAgenciaPertenece, char eliminado)
    {
        Query query = em.createNamedQuery(Computador.findByCodigoComputadorIfiAgenPerEli);
        query.setParameter("codigoComputador", codigoComputador);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgenciaPertenece", codigoIfipAgenciaPertenece);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
     
       
    
     

    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
        
}
