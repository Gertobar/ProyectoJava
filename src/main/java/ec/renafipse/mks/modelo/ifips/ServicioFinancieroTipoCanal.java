/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_IFIPS.SERVICIO_FINANCIERO_TIPO_CANAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioFinancieroTipoCanal.findByCodigoIfip", query = "SELECT s FROM ServicioFinancieroTipoCanal s JOIN s.codigoServicioFinanciero sf WHERE sf.codigoIfip.codigo = :codigoIfip")
})
public class ServicioFinancieroTipoCanal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CODIGO_TIPO_CANAL")
    private String codigoTipoCanal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TARIFA")
    private BigDecimal tarifa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "IVA")
    private String iva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;
    @JoinColumn(name = "CODIGO_SERVICIO_FINANCIERO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ServicioFinanciero codigoServicioFinanciero;

    public ServicioFinancieroTipoCanal() {
    }

    public ServicioFinancieroTipoCanal(Long codigo) {
        this.codigo = codigo;
    }

    public ServicioFinancieroTipoCanal(Long codigo, String codigoTipoCanal, long codigoUsuario, BigDecimal tarifa, String iva, Date fechaSistema, String vigente) {
        this.codigo = codigo;
        this.codigoTipoCanal = codigoTipoCanal;
        this.codigoUsuario = codigoUsuario;
        this.tarifa = tarifa;
        this.iva = iva;
        this.fechaSistema = fechaSistema;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCodigoTipoCanal() {
        return codigoTipoCanal;
    }

    public void setCodigoTipoCanal(String codigoTipoCanal) {
        this.codigoTipoCanal = codigoTipoCanal;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public ServicioFinanciero getCodigoServicioFinanciero() {
        return codigoServicioFinanciero;
    }

    public void setCodigoServicioFinanciero(ServicioFinanciero codigoServicioFinanciero) {
        this.codigoServicioFinanciero = codigoServicioFinanciero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioFinancieroTipoCanal)) {
            return false;
        }
        ServicioFinancieroTipoCanal other = (ServicioFinancieroTipoCanal) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.ServicioFinancieroTipoCanal[ codigo=" + codigo + " ]";
    }
    
}
