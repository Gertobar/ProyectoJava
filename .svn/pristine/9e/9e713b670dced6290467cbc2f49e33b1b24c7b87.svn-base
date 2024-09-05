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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mascoop
 */
@Entity
@Table(name = "OFICIAL_CREDITO", catalog = "", schema = "MKS_CREDITOS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO_PERSONA", "CODIGO_IFIP_AGENCIA"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OficialCredito.findByCodigoPersonaAndCodigoIfipAgencia", query = "SELECT o FROM OficialCredito o WHERE o.codigoPersona = :codigoPersona AND o.codigoIfipAgencia = :codigoIfipAgencia") })
public class OficialCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO", nullable = false)
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA", nullable = false)
    private long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA", nullable = false)
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    public OficialCredito() {
    }

    public OficialCredito(Long codigo) {
        this.codigo = codigo;
    }

    public OficialCredito(Long codigo, long codigoPersona, long codigoIfipAgencia, Date fechaIngreso, Date fechaRegistro) {
        this.codigo = codigo;
        this.codigoPersona = codigoPersona;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.fechaIngreso = fechaIngreso;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OficialCredito)) {
            return false;
        }
        OficialCredito other = (OficialCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.OficialCredito[ codigo=" + codigo + " ]";
    }
    
}
