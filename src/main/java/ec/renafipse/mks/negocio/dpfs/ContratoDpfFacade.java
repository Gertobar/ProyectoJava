/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.comunes.ConsultasCriterioConstructor;
import ec.renafipse.mks.modelo.dpfs.ContratoDpf;
import ec.renafipse.mks.modelo.dpfs.ContratoDpfPK;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author vastudillo
 */
@Stateless
public class ContratoDpfFacade extends AbstractFacade<ContratoDpf> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoDpfFacade() {
        super(ContratoDpf.class);
    }

    //------------------------------------------------------------------------
    //PERSONALIZADOS
    /**
     * Obtiene los contrados de los DPFS de la persona en la IFIP
     * @param codigoPersona
     * @param codigoIfip
     * @return Contratos de DDF
     */
    public List<ContratoDpf> getItemsCodigoIfipCodigoPersona(Long codigoPersona, Long codigoIfip) {
        Query query = this.em.createNamedQuery(ContratoDpf.findByCodigoIfipCodigoPersona);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }
    
    /**
     * Obtiene los contratos de los DPF que se pueden renovar
     * @param codigoIfip Codigo de la Ifip
     * @param codigoPersona Codigo de la Persona
     * @param estado Etado del Contrato V=Vigente C=Cancelar A=Anulado
     * @param estadoRenovacion Estado de la Renovacion P=Pendiente A=Anulado R=Renovado
     * @return  Lista de Contratos de DPF
     */
    public List<ContratoDpf> getItemsRenovacionContratoDpf(Long codigoIfip, Long codigoPersona, char estado, char estadoRenovacion, char renovacionAutomatica) {
        Query query = this.em.createNamedQuery(ContratoDpf.findByRenovacionContratoDpf);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("estado", estado);
        query.setParameter("estadoRenovacion", estadoRenovacion);
        query.setParameter("renovacionAutomatica", renovacionAutomatica);
        
        return query.getResultList();
    }

    /**
     * Obtiene los contratos de los DPF que se pueden anular
     * @param codigoIfip Codigo de la Ifip
     * @param codigoIfipAgencia Codigo de la Agencia donde se realizo el Contrato
     * @param estado Etado del Contrato V=Vigente C=Cancelar A=Anulado
     * @param fechaContrato Fecha en la que se realizo el contrato
     * @return  Lista de Contratos de DPF
     */
    public List<ContratoDpf> getItemsAnularContrato(Long codigoIfip, Long codigoIfipAgencia, char estado, Date fechaContrato) {
        Query query = this.em.createNamedQuery(ContratoDpf.findByAnularContrato);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("estado", estado);
        query.setParameter("fechaContrato", fechaContrato, TemporalType.DATE);
        return query.getResultList();
    }
    
    /**
     * Obtiene los contratos de los DPFS de acuerdo a la ifip, persona y estado
     * @param codigoIfip Codigo de la IFIP
     * @param codigoPersona Codigo de la Persona
     * @param estado Estado 
     * @return Lista de Contratos de DPF
     */
     public List<ContratoDpf> getItemsIfipPersonaEstado(Long codigoIfip, Long codigoPersona, char estado) {
        Query query = this.em.createNamedQuery(ContratoDpf.findByIfipPersonaEstado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estado", estado);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }
     
     /**
     * Obtiene los contratos de los DPFS de acuerdo a la ifip, persona y si acredita mensualmente
     * @param codigoIfip Codigo de la IFIP
     * @param codigoPersona Codigo de la Persona 
     * @param acreditaMensual 
     * @return Lista de Contratos de DPF
     */
      public List<ContratoDpf> getItemsIfipPersonaAcreditaMensual(Long codigoIfip, Long codigoPersona, char acreditaMensual) {
        Query query = this.em.createNamedQuery(ContratoDpf.findByIfipPersonaAcreditaMensual);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("acreditaMensual", acreditaMensual);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }
      
      /**
     * Obtiene los contrados de los DPFS de la persona en la IFIP
     * @param codigoPersona
     * @param codigoIfip
     * @param retencion
     * @return Contratos de DDF
     */
    public List<ContratoDpf> getItemsCodigoIfipCodigoPersonaConRetencion(Long codigoPersona, Long codigoIfip, BigDecimal retencion) {
        Query query = this.em.createNamedQuery(ContratoDpf.findByCodigoIfipCodigoPersonaConRetencion);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("retencion", retencion);
        return query.getResultList();
    }
    
    /**
     * Metodo que busca por clave primaria
     *
     * @param contratoDpfPK
     * @return devuelve el contrato DPF buscado por ContratoDpfPK
     */
    public ContratoDpf buscarPorCampo(ContratoDpfPK contratoDpfPK){
        ConsultasCriterioConstructor criterioConstructor = new ConsultasCriterioConstructor<ContratoDpf>(ContratoDpf.class, em);
        ContratoDpf contratoDpfEncontrado=null;
        contratoDpfEncontrado=(ContratoDpf)criterioConstructor.consultaPrimerDato("contratoDpfPK", contratoDpfPK);
        return contratoDpfEncontrado;
    }
}
