/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.capas.utilitario.ConvierteDato;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TABLA_AMORTIZACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TablaAmortizacion.findByNumeroCreditoAndCodigoIfipAndCuota", query = "SELECT t FROM TablaAmortizacion t WHERE t.tablaAmortizacionPK.numeroCredito = :numeroCredito AND t.tablaAmortizacionPK.codigoIfip = :codigoIfip AND t.tablaAmortizacionPK.cuota = :cuota"),
    @NamedQuery(name = "TablaAmortizacion.findAll", query = "SELECT t FROM TablaAmortizacion t"),
    @NamedQuery(name = "TablaAmortizacion.findByNumeroCredito", query = "SELECT t FROM TablaAmortizacion t WHERE t.tablaAmortizacionPK.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "TablaAmortizacion.findByCodigoIfip", query = "SELECT t FROM TablaAmortizacion t WHERE t.tablaAmortizacionPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "TablaAmortizacion.findByCuota", query = "SELECT t FROM TablaAmortizacion t WHERE t.tablaAmortizacionPK.cuota = :cuota"),
    @NamedQuery(name = "TablaAmortizacion.findByFechaInicio", query = "SELECT t FROM TablaAmortizacion t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "TablaAmortizacion.findByFechaPago", query = "SELECT t FROM TablaAmortizacion t WHERE t.fechaPago = :fechaPago"),
    @NamedQuery(name = "TablaAmortizacion.findBySaldoCapital", query = "SELECT t FROM TablaAmortizacion t WHERE t.saldoCapital = :saldoCapital"),
    @NamedQuery(name = "TablaAmortizacion.findByCapital", query = "SELECT t FROM TablaAmortizacion t WHERE t.capital = :capital"),
    @NamedQuery(name = "TablaAmortizacion.findByInteres", query = "SELECT t FROM TablaAmortizacion t WHERE t.interes = :interes"),
    @NamedQuery(name = "TablaAmortizacion.findByRubros", query = "SELECT t FROM TablaAmortizacion t WHERE t.rubros = :rubros"),
    @NamedQuery(name = "TablaAmortizacion.findByTotal", query = "SELECT t FROM TablaAmortizacion t WHERE t.total = :total"),
    @NamedQuery(name = "TablaAmortizacion.findByEsDeGracia", query = "SELECT t FROM TablaAmortizacion t WHERE t.esDeGracia = :esDeGracia"),
    @NamedQuery(name = "TablaAmortizacion.findByEstado", query = "SELECT t FROM TablaAmortizacion t WHERE t.estado = :estado"),
//Personalizadas
    @NamedQuery(name = "TablaAmortizacion.findByCreditoCuotaEstadoPendiente", query = "SELECT t FROM TablaAmortizacion t WHERE t.tablaAmortizacionPK.numeroCredito = :numeroCredito AND t.tablaAmortizacionPK.codigoIfip = :codigoIfip AND t.estado = :estado ORDER BY t.tablaAmortizacionPK.cuota"),
    @NamedQuery(name = "TablaAmortizacion.findByNumeroCreditoCodigoIfip", query = "SELECT t FROM TablaAmortizacion t WHERE t.tablaAmortizacionPK.numeroCredito = :numeroCredito AND t.tablaAmortizacionPK.codigoIfip = :codigoIfip ORDER BY t.tablaAmortizacionPK.cuota"),
    @NamedQuery(name = "TablaAmortizacion.findByCuotasVencidasCreditoIfip", query = "SELECT t FROM TablaAmortizacion t WHERE t.tablaAmortizacionPK.numeroCredito = :numeroCredito AND t.tablaAmortizacionPK.codigoIfip = :codigoIfip AND t.fechaPago <:fechaVence  AND t.estado = :estado ORDER BY t.tablaAmortizacionPK.cuota")
})
public class TablaAmortizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByCreditoCuotaEstadoPendiente = "TablaAmortizacion.findByCreditoCuotaEstadoPendiente";
    public static final String findByNumeroCreditoCodigoIfip = "TablaAmortizacion.findByNumeroCreditoCodigoIfip";
    public static final String findByCuotasVencidasCreditoIfip = "TablaAmortizacion.findByCuotasVencidasCreditoIfip";
    @EmbeddedId
    protected TablaAmortizacionPK tablaAmortizacionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_CAPITAL")
    private BigDecimal saldoCapital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPITAL")
    private BigDecimal capital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES")
    private BigDecimal interes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUBROS")
    private BigDecimal rubros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_DE_GRACIA")
    private char esDeGracia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABONO")
    private BigDecimal abono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_MADURACION")
    private String codigoTipoMaduracion;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Solicitud solicitud;

    @JoinColumn(name = "CODIGO_TIPO_MADURACION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoMaduracion tipoMaduracion;

    public TablaAmortizacion() {
    }

    public TablaAmortizacion(TablaAmortizacionPK tablaAmortizacionPK) {
        this.tablaAmortizacionPK = tablaAmortizacionPK;
    }

    public TablaAmortizacion(TablaAmortizacionPK tablaAmortizacionPK, Date fechaInicio, Date fechaPago, BigDecimal saldoCapital, BigDecimal capital, BigDecimal interes, BigDecimal rubros, BigDecimal total, char esDeGracia, char estado) {
        this.tablaAmortizacionPK = tablaAmortizacionPK;
        this.fechaInicio = fechaInicio;
        this.fechaPago = fechaPago;
        this.saldoCapital = saldoCapital;
        this.capital = capital;
        this.interes = interes;
        this.rubros = rubros;
        this.total = total;
        this.esDeGracia = esDeGracia;
        this.estado = estado;
    }

    public TablaAmortizacion(long numeroCredito, long codigoIfip, long cuota) {
        this.tablaAmortizacionPK = new TablaAmortizacionPK(numeroCredito, codigoIfip, cuota);
    }

    public TablaAmortizacionPK getTablaAmortizacionPK() {
        return tablaAmortizacionPK;
    }

    public void setTablaAmortizacionPK(TablaAmortizacionPK tablaAmortizacionPK) {
        this.tablaAmortizacionPK = tablaAmortizacionPK;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getRubros() {
        return rubros;
    }

    public void setRubros(BigDecimal rubros) {
        this.rubros = rubros;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public char getEsDeGracia() {
        return esDeGracia;
    }

    public void setEsDeGracia(char esDeGracia) {
        this.esDeGracia = esDeGracia;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tablaAmortizacionPK != null ? tablaAmortizacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablaAmortizacion)) {
            return false;
        }
        TablaAmortizacion other = (TablaAmortizacion) object;
        if ((this.tablaAmortizacionPK == null && other.tablaAmortizacionPK != null) || (this.tablaAmortizacionPK != null && !this.tablaAmortizacionPK.equals(other.tablaAmortizacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TablaAmortizacion[ tablaAmortizacionPK=" + tablaAmortizacionPK + " ]";
    }

    /**
     * @return the tipoMaduracion
     */
    public TipoMaduracion getTipoMaduracion() {
        return tipoMaduracion;
    }

    /**
     * @param tipoMaduracion the tipoMaduracion to set
     */
    public void setTipoMaduracion(TipoMaduracion tipoMaduracion) {
        this.tipoMaduracion = tipoMaduracion;
    }

    /**
     * @return the codigoTipoMaduracion
     */
    public String getCodigoTipoMaduracion() {
        return codigoTipoMaduracion;
    }

    /**
     * @param codigoTipoMaduracion the codigoTipoMaduracion to set
     */
    public void setCodigoTipoMaduracion(String codigoTipoMaduracion) {
        this.codigoTipoMaduracion = codigoTipoMaduracion;
    }

    /**
     * @return the abono
     */
    public BigDecimal getAbono() {
        return abono;
    }

    /**
     * @param abono the abono to set
     */
    public void setAbono(BigDecimal abono) {
        this.abono = abono;
    }
    
    
    public String getFechaPagoFormato()
    {
        return new SimpleDateFormat("dd/MM/yyyy").format(fechaPago);
    }

}
