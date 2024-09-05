/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.GARANTE_NOTIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GaranteNotificacion.findAll", query = "SELECT g FROM GaranteNotificacion g"),
    @NamedQuery(name = "GaranteNotificacion.findByCodigoNotificacion", query = "SELECT g FROM GaranteNotificacion g WHERE g.garanteNotificacionPK.codigoNotificacion = :codigoNotificacion"),
    @NamedQuery(name = "GaranteNotificacion.findByCodigoGarante", query = "SELECT g FROM GaranteNotificacion g WHERE g.garanteNotificacionPK.codigoGarante = :codigoGarante")})
public class GaranteNotificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GaranteNotificacionPK garanteNotificacionPK;
    @JoinColumn(name = "CODIGO_NOTIFICACION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private NotificacionCredito notificacionCredito;
    @JoinColumn(name = "CODIGO_GARANTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GaranteCredito garanteCredito;

    public GaranteNotificacion() {
    }

    public GaranteNotificacion(GaranteNotificacionPK garanteNotificacionPK) {
        this.garanteNotificacionPK = garanteNotificacionPK;
    }

    public GaranteNotificacion(long codigoNotificacion, long codigoGarante) {
        this.garanteNotificacionPK = new GaranteNotificacionPK(codigoNotificacion, codigoGarante);
    }

    public GaranteNotificacionPK getGaranteNotificacionPK() {
        return garanteNotificacionPK;
    }

    public void setGaranteNotificacionPK(GaranteNotificacionPK garanteNotificacionPK) {
        this.garanteNotificacionPK = garanteNotificacionPK;
    }

    public NotificacionCredito getNotificacionCredito() {
        return notificacionCredito;
    }

    public void setNotificacionCredito(NotificacionCredito notificacionCredito) {
        this.notificacionCredito = notificacionCredito;
    }

    public GaranteCredito getGaranteCredito() {
        return garanteCredito;
    }

    public void setGaranteCredito(GaranteCredito garanteCredito) {
        this.garanteCredito = garanteCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (garanteNotificacionPK != null ? garanteNotificacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GaranteNotificacion)) {
            return false;
        }
        GaranteNotificacion other = (GaranteNotificacion) object;
        if ((this.garanteNotificacionPK == null && other.garanteNotificacionPK != null) || (this.garanteNotificacionPK != null && !this.garanteNotificacionPK.equals(other.garanteNotificacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.GaranteNotificacion[ garanteNotificacionPK=" + garanteNotificacionPK + " ]";
    }
    
}
