/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.cajas;

import ec.renafipse.mks.modelo.ahorros.MovimientoCuenta;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.CHEQUE_DEPOSITADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChequeDepositado.findAll", query = "SELECT c FROM ChequeDepositado c"),
    @NamedQuery(name = "ChequeDepositado.findByCodigo", query = "SELECT c FROM ChequeDepositado c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "ChequeDepositado.findByCodigoMovimiento", query = "SELECT c FROM ChequeDepositado c WHERE c.codigoMovimiento = :codigoMovimiento"),
    @NamedQuery(name = "ChequeDepositado.findByCodigoEntidadFinanciera", query = "SELECT c FROM ChequeDepositado c WHERE c.codigoEntidadFinanciera = :codigoEntidadFinanciera"),
    @NamedQuery(name = "ChequeDepositado.findByCodigoUsuarioMovimiento", query = "SELECT c FROM ChequeDepositado c WHERE c.codigoUsuarioMovimiento = :codigoUsuarioMovimiento"),
    @NamedQuery(name = "ChequeDepositado.findByCodigoUsuarioPosee", query = "SELECT c FROM ChequeDepositado c WHERE c.codigoUsuarioPosee = :codigoUsuarioPosee"),
    @NamedQuery(name = "ChequeDepositado.findByNumeroCuenta", query = "SELECT c FROM ChequeDepositado c WHERE c.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "ChequeDepositado.findByNumeroCheque", query = "SELECT c FROM ChequeDepositado c WHERE c.numeroCheque = :numeroCheque"),
    @NamedQuery(name = "ChequeDepositado.findByValor", query = "SELECT c FROM ChequeDepositado c WHERE c.valor = :valor"),
    //Personalizados
    @NamedQuery(name = "ChequeDepositado.findByEntFinumeroChequeNumeroCuenta", query = "SELECT c FROM ChequeDepositado c WHERE c.codigoEntidadFinanciera = :codigoEntidadFinanciera AND  c.numeroCheque = :numeroCheque AND c.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "ChequeDepositado.findByChequeDepositadoATransferir", query = "SELECT c FROM ChequeDepositado c JOIN c.movimientoCuenta m WHERE m.conceptoTransaccionPro.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda AND c.codigoEstado = :codigoEstado AND c.codigoUsuarioMovimiento = :codigoUsuarioMovimiento"),
    @NamedQuery(name = "ChequeDepositado.findByChequesTransferidos", query = "SELECT c FROM ChequeDepositado c JOIN c.movimientoCuenta m WHERE m.conceptoTransaccionPro.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda AND c.codigoEstado.codigo = :codigoEstado AND c.codigoUsuarioPosee = :codigoUsuarioPosee"),
    @NamedQuery(name = "ChequeDepositado.findByChequesEnGuiEntFin", query = "SELECT c FROM ChequeDepositado c JOIN c.movimientoCuenta m JOIN c.entidadFinanciera e WHERE m.movimientoCuentaAdicional.ifipAgencia.codigo = :codigoIfipAgencia AND e.codigo = :codigoEntFin AND m.conceptoTransaccionPro.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda AND c.codigoEstado.codigo = :codigoEstado AND c.transferenciaChequeDesgloce.guiaDepositoChequeDes.guiaDepositoChequeDet.guiaDepositoCheque.fecha <= :fechaGuia"),
    @NamedQuery(name = "ChequeDepositado.findBySocioIfipEntFinCheque", query = "SELECT c FROM ChequeDepositado c JOIN c.movimientoCuenta m JOIN m.codigoCuenta cu JOIN cu.socio s WHERE s.socioPK.codigoIfip = :codigoIfip AND m.movimientoCuentaAdicional.ifipAgencia.codigo = :codigoIfipAgencia AND c.codigo = :codigoCheque"),
    @NamedQuery(name = "ChequeDepositado.findByEntFinChequeUsuarioProtesto", query = "SELECT DISTINCT e FROM ChequeDepositado c JOIN c.entidadFinanciera e JOIN c.movimientoCuenta m JOIN m.movimientoCuentaAdicional ma WHERE ma.codigoIfipAgencia = :codigoIfipAgencia AND c.codigoEstado.codigo = :estadoCheque"),
    @NamedQuery(name = "ChequeDepositado.findByChequesTransferidosGuia", query = "SELECT c FROM ChequeDepositado c JOIN c.movimientoCuentaAdicional m WHERE m.movimientoCuenta.conceptoTransaccionPro.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda AND c.codigoEstado.codigo = :codigoEstado AND m.codigoIfipAgencia = :codigoIfipAgencia AND m.codigoIfip = :codigoIfip ORDER BY c.codigo"),

})
public class ChequeDepositado implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByEntFinumeroChequeNumeroCuenta = "ChequeDepositado.findByEntFinumeroChequeNumeroCuenta";
    public static final String findByChequeDepositadoATransferir = "ChequeDepositado.findByChequeDepositadoATransferir";
    public static final String findByChequesTransferidos = "ChequeDepositado.findByChequesTransferidos";
    public static final String findByChequesEnGuiEntFin = "ChequeDepositado.findByChequesEnGuiEntFin";
    public static final String findBySocioIfipEntFinCheque = "ChequeDepositado.findBySocioIfipEntFinCheque";
    public static final String findByEntFinChequeUsuarioProtesto = "ChequeDepositado.findByEntFinChequeUsuarioProtesto";
    public static final String findByCodigo = "ChequeDepositado.findByCodigo";
    public static final String findByChequesTransferidosGuia = "ChequeDepositado.findByChequesTransferidosGuia";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_CHEQUE_DEPOSITADO")
    @SequenceGenerator(name = "GSQ_CHEQUE_DEPOSITADO", schema = "MKS_CAJAS", allocationSize = 0, sequenceName = "SEQ_CHEQUE_DEPOSITADO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MOVIMIENTO")
    private long codigoMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENTIDAD_FINANCIERA")
    private long codigoEntidadFinanciera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_MOVIMIENTO")
    private long codigoUsuarioMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_POSEE")
    private long codigoUsuarioPosee;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO_CHEQUE")
    private String numeroCheque;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @ManyToMany(mappedBy = "chequeDepositadoCollection")
    private Collection<AperturaDetalle> aperturaDetalleCollection;
    @JoinTable(name = "CIERRE_DESGLOCE_CHEQUE", joinColumns = {
        @JoinColumn(name = "CODIGO_CHEQUE", referencedColumnName = "CODIGO", insertable = false, updatable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "CODIGO_APERTURA", referencedColumnName = "CODIGO_APERTURA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)})
    @ManyToMany
    private Collection<CierreDetalle> cierreDetalleCollection;
    @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private EstadoChequeDepositado codigoEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCheque", fetch = FetchType.LAZY)
    private Collection<SeguimientoChequeDep> seguimientoChequeDepCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "chequeDepositado", fetch = FetchType.LAZY)
    private TransferenciaChequeDesgloce transferenciaChequeDesgloce;
    @JoinColumn(name = "CODIGO_ENTIDAD_FINANCIERA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EntidadFinanciera entidadFinanciera;
    @JoinColumn(name = "CODIGO_MOVIMIENTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MovimientoCuenta movimientoCuenta;
    @JoinColumn(name = "CODIGO_MOVIMIENTO", referencedColumnName = "CODIGO_MOVIMIENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MovimientoCuentaAdicional movimientoCuentaAdicional;

    public ChequeDepositado() {
    }

    public ChequeDepositado(Long codigo) {
        this.codigo = codigo;
    }

    public ChequeDepositado(Long codigo, long codigoMovimiento, long codigoEntidadFinanciera, long codigoUsuarioMovimiento, long codigoUsuarioPosee, String numeroCuenta, String numeroCheque, BigDecimal valor) {
        this.codigo = codigo;
        this.codigoMovimiento = codigoMovimiento;
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
        this.codigoUsuarioMovimiento = codigoUsuarioMovimiento;
        this.codigoUsuarioPosee = codigoUsuarioPosee;
        this.numeroCuenta = numeroCuenta;
        this.numeroCheque = numeroCheque;
        this.valor = valor;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public long getCodigoEntidadFinanciera() {
        return codigoEntidadFinanciera;
    }

    public void setCodigoEntidadFinanciera(long codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
    }

    public long getCodigoUsuarioMovimiento() {
        return codigoUsuarioMovimiento;
    }

    public void setCodigoUsuarioMovimiento(long codigoUsuarioMovimiento) {
        this.codigoUsuarioMovimiento = codigoUsuarioMovimiento;
    }

    public long getCodigoUsuarioPosee() {
        return codigoUsuarioPosee;
    }

    public void setCodigoUsuarioPosee(long codigoUsuarioPosee) {
        this.codigoUsuarioPosee = codigoUsuarioPosee;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @XmlTransient
    public Collection<AperturaDetalle> getAperturaDetalleCollection() {
        return aperturaDetalleCollection;
    }

    public void setAperturaDetalleCollection(Collection<AperturaDetalle> aperturaDetalleCollection) {
        this.aperturaDetalleCollection = aperturaDetalleCollection;
    }

    @XmlTransient
    public Collection<CierreDetalle> getCierreDetalleCollection() {
        return cierreDetalleCollection;
    }

    public void setCierreDetalleCollection(Collection<CierreDetalle> cierreDetalleCollection) {
        this.cierreDetalleCollection = cierreDetalleCollection;
    }

    public EstadoChequeDepositado getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(EstadoChequeDepositado codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    @XmlTransient
    public Collection<SeguimientoChequeDep> getSeguimientoChequeDepCollection() {
        return seguimientoChequeDepCollection;
    }

    public void setSeguimientoChequeDepCollection(Collection<SeguimientoChequeDep> seguimientoChequeDepCollection) {
        this.seguimientoChequeDepCollection = seguimientoChequeDepCollection;
    }

    public TransferenciaChequeDesgloce getTransferenciaChequeDesgloce() {
        return transferenciaChequeDesgloce;
    }

    public void setTransferenciaChequeDesgloce(TransferenciaChequeDesgloce transferenciaChequeDesgloce) {
        this.transferenciaChequeDesgloce = transferenciaChequeDesgloce;
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
        if (!(object instanceof ChequeDepositado)) {
            return false;
        }
        ChequeDepositado other = (ChequeDepositado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.ChequeDepositado[ codigo=" + codigo + " ]";
    }

    /**
     * @return the entidadFinanciera
     */
    public EntidadFinanciera getEntidadFinanciera() {
        return entidadFinanciera;
    }

    /**
     * @param entidadFinanciera the entidadFinanciera to set
     */
    public void setEntidadFinanciera(EntidadFinanciera entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    /**
     * @return the movimientoCuenta
     */
    public MovimientoCuenta getMovimientoCuenta() {
        return movimientoCuenta;
    }

    /**
     * @param movimientoCuenta the movimientoCuenta to set
     */
    public void setMovimientoCuenta(MovimientoCuenta movimientoCuenta) {
        this.movimientoCuenta = movimientoCuenta;
    }

    /**
     * @return the movimientoCuentaAdicional
     */
    public MovimientoCuentaAdicional getMovimientoCuentaAdicional() {
        return movimientoCuentaAdicional;
    }

    /**
     * @param movimientoCuentaAdicional the movimientoCuentaAdicional to set
     */
    public void setMovimientoCuentaAdicional(MovimientoCuentaAdicional movimientoCuentaAdicional) {
        this.movimientoCuentaAdicional = movimientoCuentaAdicional;
    }

}
