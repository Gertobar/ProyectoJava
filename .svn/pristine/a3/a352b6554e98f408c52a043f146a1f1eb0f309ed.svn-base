/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.LICITUD_FONDOS_PER_EXC_FOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicitudFondosPerExcFor.findAll", query = "SELECT l FROM LicitudFondosPerExcFor l"),    
    @NamedQuery(name = "LicitudFondosPerExcFor.findByCodigoIfipRangoFechaRegistro", query = "SELECT l FROM LicitudFondosPerExcFor l WHERE l.codigoIfip = :codigoIfip AND l.fechaRegistro >= :fechaInicio AND l.fechaRegistro <= :fechaFin ORDER BY l.codigo"),
    @NamedQuery(name = "LicitudFondosPerExcFor.findByCodigoIfipRangoFechaRegistroEstado", query = "SELECT l FROM LicitudFondosPerExcFor l WHERE  l.estado = :estado AND   l.codigoIfip = :codigoIfip AND l.fechaRegistro >= :fechaInicio AND l.fechaRegistro <= :fechaFin ORDER BY l.codigo"),
    @NamedQuery(name = "LicitudFondosPerExcFor.findByCodigoIfipEstado", query = "SELECT l FROM LicitudFondosPerExcFor l WHERE  l.estado = :estado AND   l.codigoIfip = :codigoIfip ORDER BY l.codigo")
})
public class LicitudFondosPerExcFor implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoIfipRangoFechaRegistro="LicitudFondosPerExcFor.findByCodigoIfipRangoFechaRegistro";
    public static final String findByCodigoIfipRangoFechaRegistroEstado="LicitudFondosPerExcFor.findByCodigoIfipRangoFechaRegistroEstado";
    public static final String findByCodigoIfipEstado="LicitudFondosPerExcFor.findByCodigoIfipEstado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_FORMULARIOS")
    private long numeroFormularios;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_FORMULARIOS")
    private BigDecimal totalFormularios;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_CAMBIADO_POR")
    private long estadoCambiadoPor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "OBSERVACIONES_ESTADO")
    private String observacionesEstado;
    @JoinColumn(name = "CODIGO_MODULO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private LicitudFondosModulo codigoModulo;
    @JoinColumn(name = "CODIGO_FORMULARIO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private LicitudFondosFormulario codigoFormulario;
    

    public LicitudFondosPerExcFor() {
    }

    public LicitudFondosPerExcFor(Long codigo) {
        this.codigo = codigo;
    }

    public LicitudFondosPerExcFor(Long codigo, long codigoPersona, long codigoIfip, long codigoIfipAgencia, long numeroFormularios, BigDecimal totalFormularios, char estado, Date fechaRegistro, long registradoPor, Date fechaEstado, long estadoCambiadoPor, String observacionesEstado) {
        this.codigo = codigo;
        this.codigoPersona = codigoPersona;
        this.codigoIfip = codigoIfip;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.numeroFormularios = numeroFormularios;
        this.totalFormularios = totalFormularios;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
        this.fechaEstado = fechaEstado;
        this.estadoCambiadoPor = estadoCambiadoPor;
        this.observacionesEstado = observacionesEstado;
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

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getNumeroFormularios() {
        return numeroFormularios;
    }

    public void setNumeroFormularios(long numeroFormularios) {
        this.numeroFormularios = numeroFormularios;
    }

    public BigDecimal getTotalFormularios() {
        return totalFormularios;
    }

    public void setTotalFormularios(BigDecimal totalFormularios) {
        this.totalFormularios = totalFormularios;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public long getEstadoCambiadoPor() {
        return estadoCambiadoPor;
    }

    public void setEstadoCambiadoPor(long estadoCambiadoPor) {
        this.estadoCambiadoPor = estadoCambiadoPor;
    }

    public String getObservacionesEstado() {
        return observacionesEstado;
    }

    public void setObservacionesEstado(String observacionesEstado) {
        this.observacionesEstado = observacionesEstado;
    }

    public LicitudFondosModulo getCodigoModulo() {
        return codigoModulo;
    }

    public void setCodigoModulo(LicitudFondosModulo codigoModulo) {
        this.codigoModulo = codigoModulo;
    }

    public LicitudFondosFormulario getCodigoFormulario() {
        return codigoFormulario;
    }

    public void setCodigoFormulario(LicitudFondosFormulario codigoFormulario) {
        this.codigoFormulario = codigoFormulario;
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
        if (!(object instanceof LicitudFondosPerExcFor)) {
            return false;
        }
        LicitudFondosPerExcFor other = (LicitudFondosPerExcFor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorrosl.LicitudFondosPerExcFor[ codigo=" + codigo + " ]";
    }
    
    
    /**
     * Devuelve en cadena el valor del estado
     * @return 
     */
    public String getEstadoToString() {

        if (this.estado == 'P') {
            return "PENDIENTE";
        } else if (this.estado == 'A') {
            return "APROBADO";
        } else if (this.estado == 'N') {
            return "ANULADO";
        } 
        return "DESCONOCIDO";
    }
    
}
