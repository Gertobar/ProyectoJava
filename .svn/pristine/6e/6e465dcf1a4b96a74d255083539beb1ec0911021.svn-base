/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.LicitudFonOrgDest;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosFormulario;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class LicitudFondosFormularioFacade extends AbstractFacade<LicitudFondosFormulario> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicitudFondosFormularioFacade() {
        super(LicitudFondosFormulario.class);
    }

    /**
     * Obtiene la licitud de fondos de la persona de acuerdo a su estado
     * @param identificacion Numero de Identificacion
     * @param codigoIfip Codigo de la Ifip
     * @param estado Estado P=Pendiente R=Registrado I=Impresos A=Anulado
     * @param codigoIfipAgencia Codigo de la Agencia
     * @return Lista de Formularios de Control 
     */
    public List<LicitudFondosFormulario> getItemsIdentificacionPersonaEstado(String identificacion, Long codigoIfip, char estado, Long codigoIfipAgencia) {
        Query query = this.em.createNamedQuery(LicitudFondosFormulario.findByIdentificacionPersonaEstado);
        query.setParameter("identificacion", identificacion);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estado", estado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();
    }
    /**
     * Metodo para obtener los fomularios de licitud de fondos de las personas por identificacion estado y por la agencia donde genero o donde es socio
     * @param identificacion
     * @param codigoIfip
     * @param estado
     * @param codigoIfipAgencia
     * @return 
     */
    public List<LicitudFondosFormulario> getItemsIdentificacionPersonaEstadoAgencia(String identificacion, Long codigoIfip, char estado, Long codigoIfipAgencia) {
        Query query = this.em.createNamedQuery(LicitudFondosFormulario.findByIdentificacionPersonaEstadoAgencia);
        query.setParameter("identificacion", identificacion);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estado", estado);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();
    }
    
    /**
     * Obtiene la licitud de fondos de  acuerdo a su estado
     * @param codigoIfip Codigo de la Ifip
     * @param estado Estado P=Pendiente R=Registrado I=Impresos A=Anulado
     * @param codigoIfipAgencia Codigo de la Agencia
     * @return Lista de Formularios de Control 
     */
    public List<LicitudFondosFormulario> getItemsEstado(char estado, Long codigoIfip, Long codigoIfipAgencia) {
        Query query = this.em.createNamedQuery(LicitudFondosFormulario.findByEstado);
        query.setParameter("estado", estado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();

    }
    /***
     * Metodo para obtener los formularios de licitud de fondos por estado y agencia donde se genero y donde es socio
     * @param estado
     * @param codigoIfip
     * @param codigoIfipAgencia
     * @return 
     */
    public List<LicitudFondosFormulario> getItemsEstadoAgencia(char estado, Long codigoIfip, Long codigoIfipAgencia) {
        Query query = this.em.createNamedQuery(LicitudFondosFormulario.findByEstadoAgencia);
        query.setParameter("estado", estado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();

    }
    
     /**
     * Actualiza el estado impreso del formulario de licitud de fondos
     *
     * @param codigo codigo del formulario
     * @param estado P=Pendiente R=Registrado I=Impresos A=Anulado
     * @param fechaActualizacion Fecha de Actualizacion del Estado
     * @param actualizadoPor Usuario quien actualizo el estado
     */
    public void actualizaEstadoLicitudFondos(Long codigo, char estado, Date fechaActualizacion, Long actualizadoPor) {
        Query query = em.createQuery("UPDATE LicitudFondosFormulario l SET l.estado = :estado,    l.actualizadoPor = :actualizadoPor, l.fechaActualizacion=:fechaActualizacion  WHERE l.codigo = :codigo");
        query.setParameter("codigo", codigo);
        query.setParameter("estado", estado);
        query.setParameter("fechaActualizacion", fechaActualizacion, TemporalType.TIMESTAMP);
        query.setParameter("actualizadoPor", actualizadoPor);
        query.executeUpdate();
    }
    
    

}
