/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.INGRESO_EGRESO_CAJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngresoEgresoCaja.findAll", query = "SELECT i FROM IngresoEgresoCaja i"),
    @NamedQuery(name = "IngresoEgresoCaja.findByCodigo", query = "SELECT i FROM IngresoEgresoCaja i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "IngresoEgresoCaja.findByFechaIngresoEgreso", query = "SELECT i FROM IngresoEgresoCaja i WHERE i.fechaIngresoEgreso = :fechaIngresoEgreso"),
    @NamedQuery(name = "IngresoEgresoCaja.findByTipo", query = "SELECT i FROM IngresoEgresoCaja i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "IngresoEgresoCaja.findByTotalEfectivo", query = "SELECT i FROM IngresoEgresoCaja i WHERE i.totalEfectivo = :totalEfectivo"),
    @NamedQuery(name = "IngresoEgresoCaja.findByTotalCheques", query = "SELECT i FROM IngresoEgresoCaja i WHERE i.totalCheques = :totalCheques"),
    @NamedQuery(name = "IngresoEgresoCaja.findByTotalIngresoEgreso", query = "SELECT i FROM IngresoEgresoCaja i WHERE i.totalIngresoEgreso = :totalIngresoEgreso")})
public class IngresoEgresoCaja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_INGRESO_EGRESO_CAJA")
    @SequenceGenerator(name = "GSQ_INGRESO_EGRESO_CAJA", schema = "MKS_CAJAS",  allocationSize = 0, sequenceName = "SEQ_INGRESO_EGRESO_CAJA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO_EGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoEgreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_EFECTIVO")
    private BigDecimal totalEfectivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CHEQUES")
    private BigDecimal totalCheques;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_INGRESO_EGRESO")
    private BigDecimal totalIngresoEgreso;
    @Basic(optional = false)
    @NotNull    
    @Size(min = 5, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;    
    @JoinColumns({
        @JoinColumn(name = "CODIGO_INGRESO_EGRESO", referencedColumnName = "CODIGO_INGRESO_EGRESO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private IngresoEgresoMoneda ingresoEgresoMoneda;
    @JoinColumn(name = "CODIGO_APERTURA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Apertura codigoApertura;

    public IngresoEgresoCaja() {
    }

    public IngresoEgresoCaja(Long codigo) {
        this.codigo = codigo;
    }

    public IngresoEgresoCaja(Long codigo, Date fechaIngresoEgreso, char tipo, BigDecimal totalEfectivo, BigDecimal totalCheques, BigDecimal totalIngresoEgreso) {
        this.codigo = codigo;
        this.fechaIngresoEgreso = fechaIngresoEgreso;
        this.tipo = tipo;
        this.totalEfectivo = totalEfectivo;
        this.totalCheques = totalCheques;
        this.totalIngresoEgreso = totalIngresoEgreso;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaIngresoEgreso() {
        return fechaIngresoEgreso;
    }

    public void setFechaIngresoEgreso(Date fechaIngresoEgreso) {
        this.fechaIngresoEgreso = fechaIngresoEgreso;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getTotalEfectivo() {
        return totalEfectivo;
    }

    public void setTotalEfectivo(BigDecimal totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }

    public BigDecimal getTotalCheques() {
        return totalCheques;
    }

    public void setTotalCheques(BigDecimal totalCheques) {
        this.totalCheques = totalCheques;
    }

    public BigDecimal getTotalIngresoEgreso() {
        return totalIngresoEgreso;
    }

    public void setTotalIngresoEgreso(BigDecimal totalIngresoEgreso) {
        this.totalIngresoEgreso = totalIngresoEgreso;
    }

    public IngresoEgresoMoneda getIngresoEgresoMoneda() {
        return ingresoEgresoMoneda;
    }

    public void setIngresoEgresoMoneda(IngresoEgresoMoneda ingresoEgresoMoneda) {
        this.ingresoEgresoMoneda = ingresoEgresoMoneda;
    }

    public Apertura getCodigoApertura() {
        return codigoApertura;
    }

    public void setCodigoApertura(Apertura codigoApertura) {
        this.codigoApertura = codigoApertura;
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
        if (!(object instanceof IngresoEgresoCaja)) {
            return false;
        }
        IngresoEgresoCaja other = (IngresoEgresoCaja) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.IngresoEgresoCaja[ codigo=" + codigo + " ]";
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}
