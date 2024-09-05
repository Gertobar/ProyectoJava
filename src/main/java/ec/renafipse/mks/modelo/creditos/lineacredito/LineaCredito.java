/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos.lineacredito;

import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.creditos.OrigenRecursos;
import ec.renafipse.mks.modelo.creditos.ProductoCredito;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaIfip;
import ec.renafipse.mks.modelo.ifips.Ifip;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_CREDITOS.LINEA_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaCredito.findAll", query = "SELECT l FROM LineaCredito l"),
    @NamedQuery(name = "LineaCredito.findByCodigo", query = "SELECT l FROM LineaCredito l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LineaCredito.findByNombre", query = "SELECT l FROM LineaCredito l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "LineaCredito.findByCodigoIfipVigente", query = "SELECT l FROM LineaCredito l WHERE l.codigoIfip.codigo = :codigoIfip AND l.vigente = :vigente")})
public class LineaCredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_LINEA_CREDITO")
    @SequenceGenerator(name = "GSQ_LINEA_CREDITO", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_LINEA_CREDITO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_MINIMO")
    private BigDecimal montoMinimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_MAXIMO")
    private BigDecimal montoMaximo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_GENERA_CREDITO")
    private short diaGeneraCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJE_PAGO_MINIMO")
    private BigDecimal porcentajePagoMinimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_PARA_BLOQUEO")
    private int diaParaBloqueo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_TABLA")
    private String tipoTabla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PLAZO_AUTOMATICO")
    private String plazoAutomatico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;
    @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ProductoCredito codigoProducto;
    @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Moneda codigoMoneda;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoCreditoMonedaIfip productoCreditoMonedaIfip;
    @JoinColumn(name = "CODIGO_PERIODICIDAD", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Periodicidad codigoPeriodicidad;
    @JoinColumn(name = "CODIGO_ORIGEN_RECURSOS", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private OrigenRecursos codigoOrigenRecursos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoLineaCredito")
    private Collection<LineaCreditoPlazo> lineaCreditoPlazoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoLineaCredito")
    private Collection<LineaCreditoPlazoMaximo> lineaCreditoPlazoMaximoCollection;
    
    public LineaCredito() {
    }

    public LineaCredito(Long codigo) {
        this.codigo = codigo;
    }

    public LineaCredito(Long codigo, String nombre, String descripcion, BigDecimal montoMinimo, BigDecimal montoMaximo, short diaGeneraCredito, BigDecimal porcentajePagoMinimo, int diaParaBloqueo, String tipoTabla, String plazoAutomatico, Long codigoUsuario, Date fechaRegistro, String vigente, ProductoCredito codigoProducto, Moneda codigoMoneda, Ifip codigoIfip, Periodicidad codigoPeriodicidad, OrigenRecursos codigoOrigenRecursos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.montoMinimo = montoMinimo;
        this.montoMaximo = montoMaximo;
        this.diaGeneraCredito = diaGeneraCredito;
        this.porcentajePagoMinimo = porcentajePagoMinimo;
        this.diaParaBloqueo = diaParaBloqueo;
        this.tipoTabla = tipoTabla;
        this.plazoAutomatico = plazoAutomatico;
        this.codigoUsuario = codigoUsuario;
        this.fechaRegistro = fechaRegistro;
        this.vigente = vigente;
        this.codigoProducto = codigoProducto;
        this.codigoMoneda = codigoMoneda;
        this.codigoIfip = codigoIfip;
        this.codigoPeriodicidad = codigoPeriodicidad;
        this.codigoOrigenRecursos = codigoOrigenRecursos;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(BigDecimal montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public BigDecimal getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(BigDecimal montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public short getDiaGeneraCredito() {
        return diaGeneraCredito;
    }

    public void setDiaGeneraCredito(short diaGeneraCredito) {
        this.diaGeneraCredito = diaGeneraCredito;
    }

    public BigDecimal getPorcentajePagoMinimo() {
        return porcentajePagoMinimo;
    }

    public void setPorcentajePagoMinimo(BigDecimal porcentajePagoMinimo) {
        this.porcentajePagoMinimo = porcentajePagoMinimo;
    }

    public int getDiaParaBloqueo() {
        return diaParaBloqueo;
    }

    public void setDiaParaBloqueo(int diaParaBloqueo) {
        this.diaParaBloqueo = diaParaBloqueo;
    }

    public String getTipoTabla() {
        return tipoTabla;
    }

    public void setTipoTabla(String tipoTabla) {
        this.tipoTabla = tipoTabla;
    }

    public String getPlazoAutomatico() {
        return plazoAutomatico;
    }

    public void setPlazoAutomatico(String plazoAutomatico) {
        this.plazoAutomatico = plazoAutomatico;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public ProductoCredito getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(ProductoCredito codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Moneda getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(Moneda codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }
    
    public ProductoCreditoMonedaIfip getProductoCreditoMonedaIfip() {
        return productoCreditoMonedaIfip;
    }

    public void setProductoCreditoMonedaIfip(ProductoCreditoMonedaIfip productoCreditoMonedaIfip) {
        this.productoCreditoMonedaIfip = productoCreditoMonedaIfip;
    }

    public Periodicidad getCodigoPeriodicidad() {
        return codigoPeriodicidad;
    }

    public void setCodigoPeriodicidad(Periodicidad codigoPeriodicidad) {
        this.codigoPeriodicidad = codigoPeriodicidad;
    }

    public OrigenRecursos getCodigoOrigenRecursos() {
        return codigoOrigenRecursos;
    }

    public void setCodigoOrigenRecursos(OrigenRecursos codigoOrigenRecursos) {
        this.codigoOrigenRecursos = codigoOrigenRecursos;
    }

    @XmlTransient
    public Collection<LineaCreditoPlazo> getLineaCreditoPlazoCollection() {
        return lineaCreditoPlazoCollection;
    }

    public void setLineaCreditoPlazoCollection(Collection<LineaCreditoPlazo> lineaCreditoPlazoCollection) {
        this.lineaCreditoPlazoCollection = lineaCreditoPlazoCollection;
    }
    
    @XmlTransient
    public Collection<LineaCreditoPlazoMaximo> getLineaCreditoPlazoMaximoCollection() {
        return lineaCreditoPlazoMaximoCollection;
    }

    public void setLineaCreditoPlazoMaximoCollection(Collection<LineaCreditoPlazoMaximo> lineaCreditoPlazoMaximoCollection) {
        this.lineaCreditoPlazoMaximoCollection = lineaCreditoPlazoMaximoCollection;
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
        if (!(object instanceof LineaCredito)) {
            return false;
        }
        LineaCredito other = (LineaCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.LineaCredito[ codigo=" + codigo + " ]";
    }
    
}
