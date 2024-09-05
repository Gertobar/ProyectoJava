/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author renafipse1
 */
@Entity
@Table(name = "MKS_CREDITOS.DIAS_ENVIO_NOTIFICACION_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiasEnvioNotificacionIfip.findAll", query = "SELECT d FROM DiasEnvioNotificacionIfip d"),
    @NamedQuery(name = "DiasEnvioNotificacionIfip.findByCodigoTipoNotificacion", query = "SELECT d FROM DiasEnvioNotificacionIfip d WHERE d.diasEnvioNotificacionIfipPK.codigoTipoNotificacion = :codigoTipoNotificacion"),
    @NamedQuery(name = "DiasEnvioNotificacionIfip.findByCodigoIfip", query = "SELECT d FROM DiasEnvioNotificacionIfip d WHERE d.diasEnvioNotificacionIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "DiasEnvioNotificacionIfip.findByDiaInicial", query = "SELECT d FROM DiasEnvioNotificacionIfip d WHERE d.diaInicial = :diaInicial"),
    @NamedQuery(name = "DiasEnvioNotificacionIfip.findByDiaFinal", query = "SELECT d FROM DiasEnvioNotificacionIfip d WHERE d.diaFinal = :diaFinal"),
    @NamedQuery(name = "DiasEnvioNotificacionIfip.findByRegistradoPor", query = "SELECT d FROM DiasEnvioNotificacionIfip d WHERE d.registradoPor = :registradoPor"),
    @NamedQuery(name = "DiasEnvioNotificacionIfip.findByFechaRegistro", query = "SELECT d FROM DiasEnvioNotificacionIfip d WHERE d.fechaRegistro = :fechaRegistro"),
    //Personalizadas
    @NamedQuery(name = "DiasEnvioNotificacionIfip.findByDiasTipoNotificacionIfip", query = "SELECT ti FROM DiasEnvioNotificacionIfip d join d.tipoNotificacionIfip ti join ti.tipoNotificacion t where  :diasMora >= d.diaInicial and  d.diasEnvioNotificacionIfipPK.codigoIfip = :codigoIfip and ti.eliminado = :eliminado and t.eliminado = :eliminado AND t.codigo NOT IN (SELECT nc.codigoTipoNotificacion FROM NotificacionCredito nc WHERE nc.numeroCredito  = :numeroCredito AND  nc.codigoIfip = :codigoIfip AND nc.cuota = :cuota AND nc.estado <> :anulada ) ORDER BY t.codigo")
})
public class DiasEnvioNotificacionIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByDiasTipoNotificacionIfip= "DiasEnvioNotificacionIfip.findByDiasTipoNotificacionIfip";
    @EmbeddedId
    protected DiasEnvioNotificacionIfipPK diasEnvioNotificacionIfipPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_INICIAL")
    private long diaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_FINAL")
    private long diaFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
     @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_NOTIFICACION", referencedColumnName = "CODIGO_TIPO_NOTIFICACION", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TipoNotificacionIfip tipoNotificacionIfip;
    

    public DiasEnvioNotificacionIfip() {
    }

    public DiasEnvioNotificacionIfip(DiasEnvioNotificacionIfipPK diasEnvioNotificacionIfipPK) {
        this.diasEnvioNotificacionIfipPK = diasEnvioNotificacionIfipPK;
    }

    public DiasEnvioNotificacionIfip(DiasEnvioNotificacionIfipPK diasEnvioNotificacionIfipPK, long diaInicial, long diaFinal, long registradoPor, Date fechaRegistro) {
        this.diasEnvioNotificacionIfipPK = diasEnvioNotificacionIfipPK;
        this.diaInicial = diaInicial;
        this.diaFinal = diaFinal;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
    }

    public DiasEnvioNotificacionIfip(long codigoTipoNotificacion, long codigoIfip) {
        this.diasEnvioNotificacionIfipPK = new DiasEnvioNotificacionIfipPK(codigoTipoNotificacion, codigoIfip);
    }

    public DiasEnvioNotificacionIfipPK getDiasEnvioNotificacionIfipPK() {
        return diasEnvioNotificacionIfipPK;
    }

    public void setDiasEnvioNotificacionIfipPK(DiasEnvioNotificacionIfipPK diasEnvioNotificacionIfipPK) {
        this.diasEnvioNotificacionIfipPK = diasEnvioNotificacionIfipPK;
    }

    public long getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(long diaInicial) {
        this.diaInicial = diaInicial;
    }

    public long getDiaFinal() {
        return diaFinal;
    }

    public void setDiaFinal(long diaFinal) {
        this.diaFinal = diaFinal;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diasEnvioNotificacionIfipPK != null ? diasEnvioNotificacionIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiasEnvioNotificacionIfip)) {
            return false;
        }
        DiasEnvioNotificacionIfip other = (DiasEnvioNotificacionIfip) object;
        if ((this.diasEnvioNotificacionIfipPK == null && other.diasEnvioNotificacionIfipPK != null) || (this.diasEnvioNotificacionIfipPK != null && !this.diasEnvioNotificacionIfipPK.equals(other.diasEnvioNotificacionIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.DiasEnvioNotificacionIfip[ diasEnvioNotificacionIfipPK=" + diasEnvioNotificacionIfipPK + " ]";
    }

    /**
     * @return the tipoNotificacionIfip
     */
    public TipoNotificacionIfip getTipoNotificacionIfip() {
        return tipoNotificacionIfip;
    }

    /**
     * @param tipoNotificacionIfip the tipoNotificacionIfip to set
     */
    public void setTipoNotificacionIfip(TipoNotificacionIfip tipoNotificacionIfip) {
        this.tipoNotificacionIfip = tipoNotificacionIfip;
    }
    
}
