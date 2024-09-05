/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.SEGUIMIENTO_CHEQUE_DEP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeguimientoChequeDep.findAll", query = "SELECT s FROM SeguimientoChequeDep s"),
    @NamedQuery(name = "SeguimientoChequeDep.findByCodigo", query = "SELECT s FROM SeguimientoChequeDep s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "SeguimientoChequeDep.findByCodigoEstado", query = "SELECT s FROM SeguimientoChequeDep s WHERE s.codigoEstado = :codigoEstado"),
    @NamedQuery(name = "SeguimientoChequeDep.findByFechaEstado", query = "SELECT s FROM SeguimientoChequeDep s WHERE s.fechaEstado = :fechaEstado"),
    @NamedQuery(name = "SeguimientoChequeDep.findByCodigoUsuarioEstado", query = "SELECT s FROM SeguimientoChequeDep s WHERE s.codigoUsuarioEstado = :codigoUsuarioEstado")})
public class SeguimientoChequeDep implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_SEGUIMIENTO_CHEQUE_DEP")
    @SequenceGenerator(name = "GSQ_SEGUIMIENTO_CHEQUE_DEP", schema = "MKS_CAJAS",  allocationSize = 0, sequenceName = "SEQ_SEGUIMIENTO_CHEQUE_DEP")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESTADO")
    private BigInteger codigoEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    private long fechaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_ESTADO")
    private long codigoUsuarioEstado;
    @JoinColumn(name = "CODIGO_USUARIO_POSEE", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private EstadoChequeDepositado codigoUsuarioPosee;
    @JoinColumn(name = "CODIGO_CHEQUE", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ChequeDepositado codigoCheque;

    public SeguimientoChequeDep() {
    }

    public SeguimientoChequeDep(Long codigo) {
        this.codigo = codigo;
    }

    public SeguimientoChequeDep(Long codigo, BigInteger codigoEstado, long fechaEstado, long codigoUsuarioEstado) {
        this.codigo = codigo;
        this.codigoEstado = codigoEstado;
        this.fechaEstado = fechaEstado;
        this.codigoUsuarioEstado = codigoUsuarioEstado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigInteger getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(BigInteger codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public long getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(long fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public long getCodigoUsuarioEstado() {
        return codigoUsuarioEstado;
    }

    public void setCodigoUsuarioEstado(long codigoUsuarioEstado) {
        this.codigoUsuarioEstado = codigoUsuarioEstado;
    }

    public EstadoChequeDepositado getCodigoUsuarioPosee() {
        return codigoUsuarioPosee;
    }

    public void setCodigoUsuarioPosee(EstadoChequeDepositado codigoUsuarioPosee) {
        this.codigoUsuarioPosee = codigoUsuarioPosee;
    }

    public ChequeDepositado getCodigoCheque() {
        return codigoCheque;
    }

    public void setCodigoCheque(ChequeDepositado codigoCheque) {
        this.codigoCheque = codigoCheque;
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
        if (!(object instanceof SeguimientoChequeDep)) {
            return false;
        }
        SeguimientoChequeDep other = (SeguimientoChequeDep) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.SeguimientoChequeDep[ codigo=" + codigo + " ]";
    }
    
}
