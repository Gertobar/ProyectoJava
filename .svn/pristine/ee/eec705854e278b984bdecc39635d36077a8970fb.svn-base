/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.LicitudFonOrgDest;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosFormularioAdi;
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
public class LicitudFondosFormularioAdiFacade extends AbstractFacade<LicitudFondosFormularioAdi> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicitudFondosFormularioAdiFacade() {
        super(LicitudFondosFormularioAdi.class);
    }

    /**
     * Obtiene los formularios asigandos con el numero en la ifip
     *
     * @param numeroFormulario Numero de Formulario
     * @param codigoIfip Codigo de la Ifip
     * @return Lista de Formularios
     */
    public List<LicitudFondosFormularioAdi> getItemsNumeroFormularioCodigoIfio(String numeroFormulario, Long codigoIfip) {
        Query query = em.createNamedQuery(LicitudFondosFormularioAdi.findByCodigoIfipNumeroFormulario);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("numeroFormulario", numeroFormulario);
        return query.getResultList();
    }

    /**
     * Actualiza el estado impreso del formulario de licitud de fondos
     *
     *
     * @param codigoFormulario codigo del Formulario
     * @param codigoOrigen Origen de los FOndos
     * @param codigoDestino Destino de los Fondos
     * @param codigoPersonaFirma Codigod de la persona que firma el formulario
     * @param fechaActualizacion Fecha de Actualizacion del Estado
     * @param numeroFormulario NUmero del Formulario
     * @param actualizadoPor Usuario quien actualizo el estado
     * @param codigoRelacion Codigo de la Relacion que tiene la persona que
     * firmara la licitud con el socio
     * @param realizaPersona Si realiza la persona es el socio o no
     */
    public void actualizaLicitudFondos(Long codigoFormulario, LicitudFonOrgDest codigoOrigen, LicitudFonOrgDest codigoDestino, Long codigoPersonaFirma, String numeroFormulario, Date fechaActualizacion, Long actualizadoPor, Long codigoRelacion, char realizaPersona) {
        Query query = em.createQuery("UPDATE LicitudFondosFormularioAdi l SET l.codigoOrigen = :codigoOrigen, l.codigoDestino = :codigoDestino, l.codigoPersonaFirma=:codigoPersonaFirma, l.numeroFormulario= :numeroFormulario,  l.actualizadoPor = :actualizadoPor, l.fechaActualizacion=:fechaActualizacion, l.codigoRelacion=:codigoRelacion, l.realizaPersona = :realizaPersona  WHERE l.codigoFormulario = :codigoFormulario");
        query.setParameter("codigoFormulario", codigoFormulario);
        query.setParameter("codigoOrigen", codigoOrigen);
        query.setParameter("codigoDestino", codigoDestino);
        query.setParameter("codigoPersonaFirma", codigoPersonaFirma);
        query.setParameter("numeroFormulario", numeroFormulario);
        query.setParameter("fechaActualizacion", fechaActualizacion, TemporalType.TIMESTAMP);
        query.setParameter("actualizadoPor", actualizadoPor);
        query.setParameter("codigoRelacion", codigoRelacion);
        query.setParameter("realizaPersona", realizaPersona);
        query.executeUpdate();
    }

    /**
     * Obtiene el listado de licitud de fondos adicionales mediante el codigo del formulario
     * @param codigoFormulario Codigo del Formulario
     * @return  Lista de Licitud de Fondos
     */
    public LicitudFondosFormularioAdi getLicitudFondosFormularioAdi(Long codigoFormulario) {
        List<LicitudFondosFormularioAdi> listaLicitudFondosFormularioAdi = this.em.createNamedQuery(LicitudFondosFormularioAdi.findByCodigoFormulario).setParameter("codigoFormulario", codigoFormulario).getResultList();        
        System.err.println("listaLicitudFondosFormularioAdi "+listaLicitudFondosFormularioAdi);
        if (listaLicitudFondosFormularioAdi.isEmpty()) {
            return null;
        } else if (listaLicitudFondosFormularioAdi.size() >1 ) { 
            return null;
        }else{
            return listaLicitudFondosFormularioAdi.get(0);
        }
    }

}
