/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TIPO_COMPROBANTE_IFIP_AGENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoComprobanteIfipAgencia.findAll", query = "SELECT t FROM TipoComprobanteIfipAgencia t"),
    @NamedQuery(name = "TipoComprobanteIfipAgencia.findByCodigoTipoComprobante", query = "SELECT t FROM TipoComprobanteIfipAgencia t WHERE t.tipoComprobanteIfipAgenciaPK.codigoTipoComprobante = :codigoTipoComprobante"),
    @NamedQuery(name = "TipoComprobanteIfipAgencia.findByCodigoIfip", query = "SELECT t FROM TipoComprobanteIfipAgencia t WHERE t.tipoComprobanteIfipAgenciaPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "TipoComprobanteIfipAgencia.findByCodigoPeriodo", query = "SELECT t FROM TipoComprobanteIfipAgencia t WHERE t.tipoComprobanteIfipAgenciaPK.codigoPeriodo = :codigoPeriodo"),
    @NamedQuery(name = "TipoComprobanteIfipAgencia.findBySecuencia", query = "SELECT t FROM TipoComprobanteIfipAgencia t WHERE t.secuencia = :secuencia"),
    @NamedQuery(name = "TipoComprobanteIfipAgencia.findByNumero", query = "SELECT t FROM TipoComprobanteIfipAgencia t WHERE t.numero = :numero")})
public class TipoComprobanteIfipAgencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipoComprobanteIfipAgenciaPK tipoComprobanteIfipAgenciaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECUENCIA")
    private long secuencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO")
    private String numero;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipAgencia ifipAgencia;
    @JoinColumn(name = "CODIGO_TIPO_COMPROBANTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoComprobante tipoComprobante;

    public TipoComprobanteIfipAgencia() {
    }

    public TipoComprobanteIfipAgencia(TipoComprobanteIfipAgenciaPK tipoComprobanteIfipAgenciaPK) {
        this.tipoComprobanteIfipAgenciaPK = tipoComprobanteIfipAgenciaPK;
    }

    public TipoComprobanteIfipAgencia(TipoComprobanteIfipAgenciaPK tipoComprobanteIfipAgenciaPK, long secuencia, String numero, long codigoIfipAgencia) {
        this.tipoComprobanteIfipAgenciaPK = tipoComprobanteIfipAgenciaPK;
        this.secuencia = secuencia;
        this.numero = numero;
    }

    public TipoComprobanteIfipAgencia(long codigoTipoComprobante, long codigoIfip, String codigoPeriodo) {
        this.tipoComprobanteIfipAgenciaPK = new TipoComprobanteIfipAgenciaPK(codigoTipoComprobante, codigoIfip, codigoPeriodo);
    }

    public TipoComprobanteIfipAgenciaPK getTipoComprobanteIfipAgenciaPK() {
        return tipoComprobanteIfipAgenciaPK;
    }

    public void setTipoComprobanteIfipAgenciaPK(TipoComprobanteIfipAgenciaPK tipoComprobanteIfipAgenciaPK) {
        this.tipoComprobanteIfipAgenciaPK = tipoComprobanteIfipAgenciaPK;
    }

    public long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(long secuencia) {
        this.secuencia = secuencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoComprobanteIfipAgenciaPK != null ? tipoComprobanteIfipAgenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoComprobanteIfipAgencia)) {
            return false;
        }
        TipoComprobanteIfipAgencia other = (TipoComprobanteIfipAgencia) object;
        if ((this.tipoComprobanteIfipAgenciaPK == null && other.tipoComprobanteIfipAgenciaPK != null) || (this.tipoComprobanteIfipAgenciaPK != null && !this.tipoComprobanteIfipAgenciaPK.equals(other.tipoComprobanteIfipAgenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.TipoComprobanteIfipAgencia[ tipoComprobanteIfipAgenciaPK=" + tipoComprobanteIfipAgenciaPK + " ]";
    }

    /**
     * @return the ifipAgencia
     */
    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    /**
     * @param ifipAgencia the ifipAgencia to set
     */
    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }

    /**
     * @return the codigoIfip
     */
    public long getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    /**
     * @return the tipoComprobante
     */
    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * @param tipoComprobante the tipoComprobante to set
     */
    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }
    
}
