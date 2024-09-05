/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.ifips.Ifip;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "MKS_CREDITOS.PROVISION_CARTERA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProvisionCartera.findAll", query = "SELECT p FROM ProvisionCartera p"),
    @NamedQuery(name = "ProvisionCartera.findByCodigo", query = "SELECT p FROM ProvisionCartera p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ProvisionCartera.findByCodigoIfip", query = "SELECT p FROM ProvisionCartera p WHERE p.codigoIfip.codigo = :codigoIfip"),
    @NamedQuery(name = "ProvisionCartera.findByFecha", query = "SELECT p FROM ProvisionCartera p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "ProvisionCartera.findByCodigoIfipFecha", query = "SELECT p FROM ProvisionCartera p WHERE p.codigoIfip.codigo = :codigoIfip AND p.fecha = :fecha"),
    @NamedQuery(name = "ProvisionCartera.findByNumfindByCodigoIfiperoRegistros", query = "SELECT p FROM ProvisionCartera p WHERE p.numeroRegistros = :numeroRegistros"),
    @NamedQuery(name = "ProvisionCartera.findByTotal", query = "SELECT p FROM ProvisionCartera p WHERE p.total = :total")})
public class ProvisionCartera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_REGISTROS")
    private long numeroRegistros;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;

    public ProvisionCartera() {
    }

    public ProvisionCartera(Long codigo) {
        this.codigo = codigo;
    }

    public ProvisionCartera(Long codigo, Ifip codigoIfip, Date fecha, long numeroRegistros, BigDecimal total) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.fecha = fecha;
        this.numeroRegistros = numeroRegistros;
        this.total = total;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getNumeroRegistros() {
        return numeroRegistros;
    }

    public void setNumeroRegistros(long numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
        if (!(object instanceof ProvisionCartera)) {
            return false;
        }
        ProvisionCartera other = (ProvisionCartera) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ProvisionCartera[ codigo=" + codigo + " ]";
    }
    
}
