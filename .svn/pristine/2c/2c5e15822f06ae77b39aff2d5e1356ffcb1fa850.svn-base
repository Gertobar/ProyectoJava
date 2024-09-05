/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.ahorros.Cuenta;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.SOLICITUD_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudDetalle.findAll", query = "SELECT s FROM SolicitudDetalle s"),
    @NamedQuery(name = "SolicitudDetalle.findByNumeroCredito", query = "SELECT s FROM SolicitudDetalle s WHERE s.solicitudDetallePK.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "SolicitudDetalle.findByCodigoIfip", query = "SELECT s FROM SolicitudDetalle s WHERE s.solicitudDetallePK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "SolicitudDetalle.findByCodigoCuentaAcreditada", query = "SELECT s FROM SolicitudDetalle s WHERE s.codigoCuentaAcreditada = :codigoCuentaAcreditada"),
    @NamedQuery(name = "SolicitudDetalle.findByCodigoCuentaDebito", query = "SELECT s FROM SolicitudDetalle s WHERE s.codigoCuentaDebito = :codigoCuentaDebito"),
    @NamedQuery(name = "SolicitudDetalle.findByTuvoRubros", query = "SELECT s FROM SolicitudDetalle s WHERE s.tuvoRubros = :tuvoRubros"),
    @NamedQuery(name = "SolicitudDetalle.findBySaldoCapital", query = "SELECT s FROM SolicitudDetalle s WHERE s.saldoCapital = :saldoCapital"),
    @NamedQuery(name = "SolicitudDetalle.findByCuotaPendiente", query = "SELECT s FROM SolicitudDetalle s WHERE s.cuotaPendiente = :cuotaPendiente"),
    @NamedQuery(name = "SolicitudDetalle.findByDiasMora", query = "SELECT s FROM SolicitudDetalle s WHERE s.diasMora = :diasMora"),
    @NamedQuery(name = "SolicitudDetalle.findByTotalMora", query = "SELECT s FROM SolicitudDetalle s WHERE s.totalMora = :totalMora"),
    @NamedQuery(name = "SolicitudDetalle.findByNumeroNotificaciones", query = "SELECT s FROM SolicitudDetalle s WHERE s.numeroNotificaciones = :numeroNotificaciones"),
    @NamedQuery(name = "SolicitudDetalle.findByTotalNotificaciones", query = "SELECT s FROM SolicitudDetalle s WHERE s.totalNotificaciones = :totalNotificaciones"),
    @NamedQuery(name = "SolicitudDetalle.findByDiasJudicial", query = "SELECT s FROM SolicitudDetalle s WHERE s.diasJudicial = :diasJudicial"),
    @NamedQuery(name = "SolicitudDetalle.findByTotalCostoJudicial", query = "SELECT s FROM SolicitudDetalle s WHERE s.totalCostoJudicial = :totalCostoJudicial"),
    @NamedQuery(name = "SolicitudDetalle.findByTotalRubrosCobrados", query = "SELECT s FROM SolicitudDetalle s WHERE s.totalRubrosCobrados = :totalRubrosCobrados"),
//Personalizadas
    @NamedQuery(name = "SolicitudDetalle.findByCodigoSocioCodigoIfipMora", query = "SELECT so FROM SolicitudDetalle s JOIN s.solicitud so WHERE so.socio.socioPK.codigo = :codigoSocio AND so.solicitudPK.codigoIfip = :codigoIfip AND s.totalMora > :mora"),
    @NamedQuery(name = "SolicitudDetalle.findDetByCodigoSocioCodigoIfipMora", query = "SELECT s FROM SolicitudDetalle s JOIN s.solicitud so WHERE so.socio.socioPK.codigo = :codigoSocio AND so.solicitudPK.codigoIfip = :codigoIfip AND s.totalMora > :mora"),
    @NamedQuery(name = "SolicitudDetalle.findByNombreSocioCodigoIfipMora", query = "SELECT so FROM SolicitudDetalle s JOIN s.solicitud so WHERE so.socio.codigoPersona.nombreCompleto LIKE :nombreCompleto AND so.socio.socioPK.codigoIfip = :codigoIfip AND s.totalMora > :mora"),
    @NamedQuery(name = "SolicitudDetalle.findByCodSocioCodIfipDiasMora", query = "SELECT so FROM SolicitudDetalle s JOIN s.solicitud so WHERE so.socio.socioPK.codigo = :codigoSocio AND so.socio.socioPK.codigoIfip = :codigoIfip AND s.diasMora > :mora"),
    @NamedQuery(name = "SolicitudDetalle.findByNumeroSolicitudIfipSaldoCapital", query = "SELECT s FROM SolicitudDetalle s WHERE s.solicitudDetallePK.numeroCredito = :numeroCredito AND s.solicitudDetallePK.codigoIfip = :codigoIfip AND s.saldoCapital > :saldoCapital"),
    @NamedQuery(name = "SolicitudDetalle.findByNumeroSolicitudIfipMora", query = "SELECT s FROM SolicitudDetalle s WHERE s.solicitudDetallePK.numeroCredito = :numeroCredito AND s.solicitudDetallePK.codigoIfip = :codigoIfip AND s.totalMora > :mora"),
    @NamedQuery(name = "SolicitudDetalle.findByNumeroSolicitudIfip", query = "SELECT s FROM SolicitudDetalle s WHERE s.solicitudDetallePK.numeroCredito = :numeroCredito AND s.solicitudDetallePK.codigoIfip = :codigoIfip")           

})
public class SolicitudDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByCodigoSocioCodigoIfipMora = "SolicitudDetalle.findByCodigoSocioCodigoIfipMora";
    public static final String findDetByCodigoSocioCodigoIfipMora = "SolicitudDetalle.findDetByCodigoSocioCodigoIfipMora";
    public static final String findByNombreSocioCodigoIfipMora = "SolicitudDetalle.findByNombreSocioCodigoIfipMora";
    public static final String findByNumeroSolicitudIfipSaldoCapital = "SolicitudDetalle.findByNumeroSolicitudIfipSaldoCapital";
    public static final String findByNumeroSolicitudIfip = "SolicitudDetalle.findByNumeroSolicitudIfip";
    public static final String findByNumeroSolicitudIfipMora = "SolicitudDetalle.findByNumeroSolicitudIfipMora";
    public static final String findByCodSocioCodIfipDiasMora = "SolicitudDetalle.findByCodSocioCodIfipDiasMora";
    @EmbeddedId
    protected SolicitudDetallePK solicitudDetallePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_ACREDITADA")
    private long codigoCuentaAcreditada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_DEBITO")
    private long codigoCuentaDebito;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TUVO_RUBROS")
    private char tuvoRubros;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_CAPITAL")
    private BigDecimal saldoCapital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUOTA_PENDIENTE")
    private int cuotaPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_MORA")
    private long diasMora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_MORA")
    private BigDecimal totalMora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_NOTIFICACIONES")
    private long numeroNotificaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_NOTIFICACIONES")
    private BigDecimal totalNotificaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_JUDICIAL")
    private long diasJudicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_COSTO_JUDICIAL")
    private BigDecimal totalCostoJudicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_RUBROS_COBRADOS")
    private BigDecimal totalRubrosCobrados;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Solicitud solicitud;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CUENTA_ACREDITADA", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Cuenta cuentaAcreditada;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CUENTA_DEBITO", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Cuenta cuentaDebito;

    public SolicitudDetalle() {
    }

    public SolicitudDetalle(SolicitudDetallePK solicitudDetallePK) {
        this.solicitudDetallePK = solicitudDetallePK;
    }

    public SolicitudDetalle(SolicitudDetallePK solicitudDetallePK, long codigoCuentaAcreditada, long codigoCuentaDebito, char tuvoRubros, BigDecimal saldoCapital, int cuotaPendiente, long diasMora, BigDecimal totalMora, long numeroNotificaciones, BigDecimal totalNotificaciones, long diasJudicial, BigDecimal totalCostoJudicial, BigDecimal totalRubrosCobrados) {
        this.solicitudDetallePK = solicitudDetallePK;
        this.codigoCuentaAcreditada = codigoCuentaAcreditada;
        this.codigoCuentaDebito = codigoCuentaDebito;
        this.tuvoRubros = tuvoRubros;
        this.saldoCapital = saldoCapital;
        this.cuotaPendiente = cuotaPendiente;
        this.diasMora = diasMora;
        this.totalMora = totalMora;
        this.numeroNotificaciones = numeroNotificaciones;
        this.totalNotificaciones = totalNotificaciones;
        this.diasJudicial = diasJudicial;
        this.totalCostoJudicial = totalCostoJudicial;
        this.totalRubrosCobrados = totalRubrosCobrados;
    }

    public SolicitudDetalle(long numeroCredito, long codigoIfip) {
        this.solicitudDetallePK = new SolicitudDetallePK(numeroCredito, codigoIfip);
    }

    public SolicitudDetallePK getSolicitudDetallePK() {
        return solicitudDetallePK;
    }

    public void setSolicitudDetallePK(SolicitudDetallePK solicitudDetallePK) {
        this.solicitudDetallePK = solicitudDetallePK;
    }

    public long getCodigoCuentaAcreditada() {
        return codigoCuentaAcreditada;
    }

    public void setCodigoCuentaAcreditada(long codigoCuentaAcreditada) {
        this.codigoCuentaAcreditada = codigoCuentaAcreditada;
    }

    public long getCodigoCuentaDebito() {
        return codigoCuentaDebito;
    }

    public void setCodigoCuentaDebito(long codigoCuentaDebito) {
        this.codigoCuentaDebito = codigoCuentaDebito;
    }

    public char getTuvoRubros() {
        return tuvoRubros;
    }

    public void setTuvoRubros(char tuvoRubros) {
        this.tuvoRubros = tuvoRubros;
    }

    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    public int getCuotaPendiente() {
        return cuotaPendiente;
    }

    public void setCuotaPendiente(int cuotaPendiente) {
        this.cuotaPendiente = cuotaPendiente;
    }

    public long getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(long diasMora) {
        this.diasMora = diasMora;
    }

    public BigDecimal getTotalMora() {
        return totalMora;
    }

    public void setTotalMora(BigDecimal totalMora) {
        this.totalMora = totalMora;
    }

    public long getNumeroNotificaciones() {
        return numeroNotificaciones;
    }

    public void setNumeroNotificaciones(long numeroNotificaciones) {
        this.numeroNotificaciones = numeroNotificaciones;
    }

    public BigDecimal getTotalNotificaciones() {
        return totalNotificaciones;
    }

    public void setTotalNotificaciones(BigDecimal totalNotificaciones) {
        this.totalNotificaciones = totalNotificaciones;
    }

    public long getDiasJudicial() {
        return diasJudicial;
    }

    public void setDiasJudicial(long diasJudicial) {
        this.diasJudicial = diasJudicial;
    }

    public BigDecimal getTotalCostoJudicial() {
        return totalCostoJudicial;
    }

    public void setTotalCostoJudicial(BigDecimal totalCostoJudicial) {
        this.totalCostoJudicial = totalCostoJudicial;
    }

    public BigDecimal getTotalRubrosCobrados() {
        return totalRubrosCobrados;
    }

    public void setTotalRubrosCobrados(BigDecimal totalRubrosCobrados) {
        this.totalRubrosCobrados = totalRubrosCobrados;
    }

    /*public Solicitud getSolicitud() {
     return solicitud;
     }

     public void setSolicitud(Solicitud solicitud) {
     this.solicitud = solicitud;
     }*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solicitudDetallePK != null ? solicitudDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudDetalle)) {
            return false;
        }
        SolicitudDetalle other = (SolicitudDetalle) object;
        if ((this.solicitudDetallePK == null && other.solicitudDetallePK != null) || (this.solicitudDetallePK != null && !this.solicitudDetallePK.equals(other.solicitudDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.SolicitudDetalle[ solicitudDetallePK=" + solicitudDetallePK + " ]";
    }

    /**
     * @return the cuentaAcreditada
     */
    public Cuenta getCuentaAcreditada() {
        return cuentaAcreditada;
    }

    /**
     * @param cuentaAcreditada the cuentaAcreditada to set
     */
    public void setCuentaAcreditada(Cuenta cuentaAcreditada) {
        this.cuentaAcreditada = cuentaAcreditada;
    }

    /**
     * @return the cuentaDebito
     */
    public Cuenta getCuentaDebito() {
        return cuentaDebito;
    }

    /**
     * @param cuentaDebito the cuentaDebito to set
     */
    public void setCuentaDebito(Cuenta cuentaDebito) {
        this.cuentaDebito = cuentaDebito;
    }

    /**
     * @return the solicitud
     */
    public Solicitud getSolicitud() {
        return solicitud;
    }

    /**
     * @param solicitud the solicitud to set
     */
    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

}
