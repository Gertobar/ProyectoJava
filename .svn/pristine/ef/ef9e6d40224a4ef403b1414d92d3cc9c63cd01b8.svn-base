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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.MOVIMIENTO_CUENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimientoCuenta.findAll", query = "SELECT m FROM MovimientoCuenta m"),
    @NamedQuery(name = "MovimientoCuenta.findByCodigo", query = "SELECT m FROM MovimientoCuenta m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MovimientoCuenta.findByFechaMovimiento", query = "SELECT m FROM MovimientoCuenta m WHERE m.fechaMovimiento = :fechaMovimiento"),
    @NamedQuery(name = "MovimientoCuenta.findByTipo", query = "SELECT m FROM MovimientoCuenta m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "MovimientoCuenta.findByTotalEfectivo", query = "SELECT m FROM MovimientoCuenta m WHERE m.totalEfectivo = :totalEfectivo"),
    @NamedQuery(name = "MovimientoCuenta.findByTotalCheques", query = "SELECT m FROM MovimientoCuenta m WHERE m.totalCheques = :totalCheques"),
    @NamedQuery(name = "MovimientoCuenta.findByTotalMovimiento", query = "SELECT m FROM MovimientoCuenta m WHERE m.totalMovimiento = :totalMovimiento"),
    @NamedQuery(name = "MovimientoCuenta.findBySaldoCuenta", query = "SELECT m FROM MovimientoCuenta m WHERE m.saldoCuenta = :saldoCuenta"),
    //Personalizados
    @NamedQuery(name = "MovimientoCuenta.findByEstadoDeCuenta", query = "SELECT m FROM MovimientoCuenta m WHERE m.codigoCuenta.codigo = :codigoCuenta AND  m.fechaMovimiento >= :fechaInicio AND m.fechaMovimiento <= :fechaFin ORDER BY m.codigo"),
    @NamedQuery(name = "MovimientoCuenta.getByCodigoAnteriorDelPrimeroEstadoCuentas", query = "SELECT MAX(m.codigo) FROM MovimientoCuenta m WHERE m.codigoCuenta.codigo = :codigoCuenta AND m.codigo < :codigoMovimiento"),
    //@NamedQuery(name = "MovimientoCuenta.findByEstadoDeCuentaImpreso", query = "SELECT m FROM MovimientoCuenta m JOIN m.movimientoCuentaAdicional ma WHERE m.codigoCuenta.codigo = :codigoCuenta AND ma.impreso = :impreso ORDER BY m.codigo"),
    
    //@NamedQuery(name = "MovimientoCuenta.findByMovimientoAdicionalNoImpresa", query = "SELECT ma FROM MovimientoCuenta m JOIN m.movimientoCuentaAdicional ma WHERE m.codigoCuenta.codigo = :codigoCuenta AND ma.impreso = :impreso ORDER BY m.codigo")
})
public class MovimientoCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByEstadoDeCuenta = "MovimientoCuenta.findByEstadoDeCuenta";
    public static final String getByCodigoAnteriorDelPrimeroEstadoCuentas = "MovimientoCuenta.getByCodigoAnteriorDelPrimeroEstadoCuentas";
    public static final String findByCodigo = "MovimientoCuenta.findByCodigo";
    //public static final String findByEstadoDeCuentaImpreso = "MovimientoCuenta.findByEstadoDeCuentaImpreso";
    //public static final String findByMovimientoAdicionalNoImpresa = "MovimientoCuenta.findByMovimientoAdicionalNoImpresa";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_MOVIMIENTO_CUENTA")
    @SequenceGenerator(name = "GSQ_MOVIMIENTO_CUENTA", schema = "MKS_AHORROS", allocationSize = 0, sequenceName = "SEQ_MOVIMIENTO_CUENTA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MOVIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;
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
    @Column(name = "TOTAL_MOVIMIENTO")
    private BigDecimal totalMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_TOTAL_CUENTA")
    private BigDecimal saldoCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_BLOQUEADO_CUENTA")
    private BigDecimal saldoBloqueadoCuenta;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "movimientoCuenta", fetch = FetchType.LAZY)
    private MovimientoCuentaAdicional movimientoCuentaAdicional;
    
    @JoinColumn(name = "CODIGO_CUENTA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Cuenta codigoCuenta;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO_TIPO_PRODUCTO"),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA"),
        @JoinColumn(name = "CODIGO_CONCEPTO", referencedColumnName = "CODIGO_CONCEPTO")})
    @ManyToOne(optional = false)
    private ConceptoTransaccionPro conceptoTransaccionPro;

    public MovimientoCuenta() {
    }

    public MovimientoCuenta(Long codigo) {
        this.codigo = codigo;
    }

    public MovimientoCuenta(Long codigo, Date fechaMovimiento, char tipo, BigDecimal totalEfectivo, BigDecimal totalCheques, BigDecimal totalMovimiento, BigDecimal saldoCuenta) {
        this.codigo = codigo;
        this.fechaMovimiento = fechaMovimiento;
        this.tipo = tipo;
        this.totalEfectivo = totalEfectivo;
        this.totalCheques = totalCheques;
        this.totalMovimiento = totalMovimiento;
        this.saldoCuenta = saldoCuenta;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
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

    public BigDecimal getTotalMovimiento() {
        return totalMovimiento;
    }

    public void setTotalMovimiento(BigDecimal totalMovimiento) {
        this.totalMovimiento = totalMovimiento;
    }

    public BigDecimal getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(BigDecimal saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public MovimientoCuentaAdicional getMovimientoCuentaAdicional() {
        return movimientoCuentaAdicional;
    }

    public void setMovimientoCuentaAdicional(MovimientoCuentaAdicional movimientoCuentaAdicional) {
        this.movimientoCuentaAdicional = movimientoCuentaAdicional;
    }


    public Cuenta getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(Cuenta codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public ConceptoTransaccionPro getConceptoTransaccionPro() {
        return conceptoTransaccionPro;
    }

    public void setConceptoTransaccionPro(ConceptoTransaccionPro conceptoTransaccionPro) {
        this.conceptoTransaccionPro = conceptoTransaccionPro;
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
        if (!(object instanceof MovimientoCuenta)) {
            return false;
        }
        MovimientoCuenta other = (MovimientoCuenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.MovimientoCuenta[ codigo=" + codigo + " ]";
    }

    /**
     * @return the saldoBloqueadoCuenta
     */
    public BigDecimal getSaldoBloqueadoCuenta() {
        return saldoBloqueadoCuenta;
    }

    /**
     * @param saldoBloqueadoCuenta the saldoBloqueadoCuenta to set
     */
    public void setSaldoBloqueadoCuenta(BigDecimal saldoBloqueadoCuenta) {
        this.saldoBloqueadoCuenta = saldoBloqueadoCuenta;
    }

}
