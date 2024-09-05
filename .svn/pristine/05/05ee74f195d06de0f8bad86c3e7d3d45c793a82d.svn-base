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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author Viajes2
 */
@Entity
@Table(name = "MKS_IFIPS.MOVIMIENTO_BOVEDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimientoBoveda.findAll", query = "SELECT m FROM MovimientoBoveda m"),
    @NamedQuery(name = "MovimientoBoveda.findByCodigo", query = "SELECT m FROM MovimientoBoveda m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MovimientoBoveda.findByCodigoMoneda", query = "SELECT m FROM MovimientoBoveda m WHERE m.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "MovimientoBoveda.findByCodigoUsuario", query = "SELECT m FROM MovimientoBoveda m WHERE m.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "MovimientoBoveda.findByTipo", query = "SELECT m FROM MovimientoBoveda m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "MovimientoBoveda.findByFechaSistema", query = "SELECT m FROM MovimientoBoveda m WHERE m.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "MovimientoBoveda.findByFechaMovimiento", query = "SELECT m FROM MovimientoBoveda m WHERE m.fechaMovimiento = :fechaMovimiento"),
    @NamedQuery(name = "MovimientoBoveda.findByComprobante", query = "SELECT m FROM MovimientoBoveda m WHERE m.comprobante = :comprobante"),
    @NamedQuery(name = "MovimientoBoveda.findByValorEfectivo", query = "SELECT m FROM MovimientoBoveda m WHERE m.valorEfectivo = :valorEfectivo"),
    @NamedQuery(name = "MovimientoBoveda.findByTotalMovimiento", query = "SELECT m FROM MovimientoBoveda m WHERE m.totalMovimiento = :totalMovimiento"),
    //Personalizados
    @NamedQuery(name = "MovimientoBoveda.findByBodegaTipoMovimientoComprobante", query = "SELECT m FROM MovimientoBoveda m WHERE m.codigoBoveda.codigo = :codigoBoveda AND m.codigoTipoMovimiento.codigo = :codigoTipoMovimiento AND m.comprobante = :comprobante")
})
public class MovimientoBoveda implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByBodegaTipoMovimientoComprobante = "MovimientoBoveda.findByBodegaTipoMovimientoComprobante";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_MOVIMIENTO_BOVEDA")
    @SequenceGenerator(name = "GSQ_MOVIMIENTO_BOVEDA", schema = "MKS_IFIPS",  allocationSize = 0, sequenceName = "SEQ_MOVIMIENTO_BOVEDA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPUTADOR")
    private long codigoComputador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MOVIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 30)
    @Column(name = "COMPROBANTE")
    private String comprobante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_EFECTIVO")
    private BigDecimal valorEfectivo;
    @Column(name = "TOTAL_MOVIMIENTO")
    private BigDecimal totalMovimiento;
    @JoinColumn(name = "CODIGO_TIPO_MOVIMIENTO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoMovimientoBoveda codigoTipoMovimiento;
    @JoinColumn(name = "CODIGO_BOVEDA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Boveda codigoBoveda;
    

    public MovimientoBoveda() {
    }

    public MovimientoBoveda(Long codigo) {
        this.codigo = codigo;
    }

    public MovimientoBoveda(Long codigo, long codigoMoneda, long codigoUsuario, char tipo, Date fechaSistema, Date fechaMovimiento, String comprobante) {
        this.codigo = codigo;
        this.codigoMoneda = codigoMoneda;
        this.codigoUsuario = codigoUsuario;
        this.tipo = tipo;
        this.fechaSistema = fechaSistema;
        this.fechaMovimiento = fechaMovimiento;
        this.comprobante = comprobante;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public BigDecimal getValorEfectivo() {
        return valorEfectivo;
    }

    public void setValorEfectivo(BigDecimal valorEfectivo) {
        this.valorEfectivo = valorEfectivo;
    }

    public BigDecimal getTotalMovimiento() {
        return totalMovimiento;
    }

    public void setTotalMovimiento(BigDecimal totalMovimiento) {
        this.totalMovimiento = totalMovimiento;
    }

    public TipoMovimientoBoveda getCodigoTipoMovimiento() {
        return codigoTipoMovimiento;
    }

    public void setCodigoTipoMovimiento(TipoMovimientoBoveda codigoTipoMovimiento) {
        this.codigoTipoMovimiento = codigoTipoMovimiento;
    }

    public Boveda getCodigoBoveda() {
        return codigoBoveda;
    }

    public void setCodigoBoveda(Boveda codigoBoveda) {
        this.codigoBoveda = codigoBoveda;
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
        if (!(object instanceof MovimientoBoveda)) {
            return false;
        }
        MovimientoBoveda other = (MovimientoBoveda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.MovimientoBoveda[ codigo=" + codigo + " ]";
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

    /**
     * @return the codigoComputador
     */
    public long getCodigoComputador() {
        return codigoComputador;
    }

    /**
     * @param codigoComputador the codigoComputador to set
     */
    public void setCodigoComputador(long codigoComputador) {
        this.codigoComputador = codigoComputador;
    }
    
}
