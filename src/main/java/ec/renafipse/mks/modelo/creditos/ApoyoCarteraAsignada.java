/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mascoop
 */
@Entity
@Table(name = "APOYO_CARTERA_ASIGNADA", catalog = "", schema = "MKS_CREDITOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApoyoCarteraAsignada.findAll", query = "SELECT a FROM ApoyoCarteraAsignada a")
    , @NamedQuery(name = "ApoyoCarteraAsignada.findByCodigoPersonaAndAgencia", query = "SELECT a FROM ApoyoCarteraAsignada a WHERE a.apoyoCarteraAsignadaPK.codigoPersona = :codigoPersona AND a.apoyoCarteraAsignadaPK.codigoIfipAgencia = :codigoIfipAgencia")
    , @NamedQuery(name = "ApoyoCarteraAsignada.findByCodigoPersona", query = "SELECT a FROM ApoyoCarteraAsignada a WHERE a.apoyoCarteraAsignadaPK.codigoPersona = :codigoPersona")
    })
public class ApoyoCarteraAsignada implements Serializable {

    private static final long serialVersionUID = 1L;
    public static String findByCodigoPersonaAndAgencia = "ApoyoCarteraAsignada.findByCodigoPersonaAndAgencia";
    @EmbeddedId
    protected ApoyoCarteraAsignadaPK apoyoCarteraAsignadaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(nullable = false, length = 1)
    private String vigente;

    public ApoyoCarteraAsignada() {
    }

    public ApoyoCarteraAsignada(ApoyoCarteraAsignadaPK apoyoCarteraAsignadaPK) {
        this.apoyoCarteraAsignadaPK = apoyoCarteraAsignadaPK;
    }

    public ApoyoCarteraAsignada(ApoyoCarteraAsignadaPK apoyoCarteraAsignadaPK, String vigente) {
        this.apoyoCarteraAsignadaPK = apoyoCarteraAsignadaPK;
        this.vigente = vigente;
    }

    public ApoyoCarteraAsignada(long codigoPersona, long codigoCalificacionAsignacion, long codigoIfipAgencia) {
        this.apoyoCarteraAsignadaPK = new ApoyoCarteraAsignadaPK(codigoPersona, codigoCalificacionAsignacion, codigoIfipAgencia);
    }

    public ApoyoCarteraAsignadaPK getApoyoCarteraAsignadaPK() {
        return apoyoCarteraAsignadaPK;
    }

    public void setApoyoCarteraAsignadaPK(ApoyoCarteraAsignadaPK apoyoCarteraAsignadaPK) {
        this.apoyoCarteraAsignadaPK = apoyoCarteraAsignadaPK;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apoyoCarteraAsignadaPK != null ? apoyoCarteraAsignadaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApoyoCarteraAsignada)) {
            return false;
        }
        ApoyoCarteraAsignada other = (ApoyoCarteraAsignada) object;
        if ((this.apoyoCarteraAsignadaPK == null && other.apoyoCarteraAsignadaPK != null) || (this.apoyoCarteraAsignadaPK != null && !this.apoyoCarteraAsignadaPK.equals(other.apoyoCarteraAsignadaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ApoyoCarteraAsignada[ apoyoCarteraAsignadaPK=" + apoyoCarteraAsignadaPK + " ]";
    }
    
}
