/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos.asignacionCartera;

import ec.renafipse.mks.modelo.creditos.Solicitud;
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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author willan
 */
@Entity
@Table(name = "MKS_CREDITOS.CARTERA_ASIGNADA")
@NamedQueries({
    @NamedQuery(name = "CarteraAsignada.findAll", query = "SELECT c FROM CarteraAsignada c")
    , @NamedQuery(name = "CarteraAsignada.findByCodigo", query = "SELECT c FROM CarteraAsignada c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CarteraAsignada.findByCodigoUsuario", query = "SELECT c FROM CarteraAsignada c WHERE c.codigoUsuario = :codigoUsuario")
    , @NamedQuery(name = "CarteraAsignada.findByFechaRegistro", query = "SELECT c FROM CarteraAsignada c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "CarteraAsignada.findByCapitalPendiente", query = "SELECT c FROM CarteraAsignada c WHERE c.capitalPendiente = :capitalPendiente")
    , @NamedQuery(name = "CarteraAsignada.findByInteresPendiente", query = "SELECT c FROM CarteraAsignada c WHERE c.interesPendiente = :interesPendiente")
    , @NamedQuery(name = "CarteraAsignada.findByMoraPendiente", query = "SELECT c FROM CarteraAsignada c WHERE c.moraPendiente = :moraPendiente")
    , @NamedQuery(name = "CarteraAsignada.findByRubroPendiente", query = "SELECT c FROM CarteraAsignada c WHERE c.rubroPendiente = :rubroPendiente")
    , @NamedQuery(name = "CarteraAsignada.findByValorNotificacion", query = "SELECT c FROM CarteraAsignada c WHERE c.valorNotificacion = :valorNotificacion")
    , @NamedQuery(name = "CarteraAsignada.findByValorJudicial", query = "SELECT c FROM CarteraAsignada c WHERE c.valorJudicial = :valorJudicial")
    , @NamedQuery(name = "CarteraAsignada.findByDiasMora", query = "SELECT c FROM CarteraAsignada c WHERE c.diasMora = :diasMora")
    , @NamedQuery(name = "CarteraAsignada.findByVigente", query = "SELECT c FROM CarteraAsignada c WHERE c.vigente = :vigente")
    , @NamedQuery(name = "CarteraAsignada.findByCodigoPersonaVigente", query = "SELECT c FROM CarteraAsignada c WHERE c.codigoRecuperador.codigoPersona = :codigoPersona AND c.vigente = 'S' AND c.solicitud.codigoEstado = 6")})
@NamedNativeQueries({
    @NamedNativeQuery(name="CarteraAsignada.findByAgenciaAndResponsable", 
            query= "SELECT ca.*\n" +
                    "FROM   mks_creditos.cartera_asignada ca,\n" +
                    "       mks_creditos.solicitud so,\n" +
                    "       mks_creditos.apoyo_cartera_asignada aca\n" +
                    "WHERE  ca.numero_credito = so.numero\n" +
                    "AND    ca.codigo_ifip = so.codigo_ifip\n" +
                    "AND    so.codigo_ifip_agencia = aca.codigo_ifip_agencia\n" +
                    "AND    aca.codigo_persona = :codigoPersona\n" +
                    "AND    aca.codigo_ifip_agencia = :codigoIfipAgencia\n" +
                    "AND    ca.vigente = 'S'\n" +
                    "AND    so.codigo_estado = 6\n" +
                    "UNION\n" +
                    "SELECT ca.*\n" +
                    "FROM   mks_creditos.cartera_asignada ca,\n" +
                    "       mks_creditos.solicitud so\n" +
                    "WHERE  ca.numero_credito = so.numero\n" +
                    "AND    ca.codigo_ifip = so.codigo_ifip\n" +
                    "AND    ca.codigo_recuperador = :codigoPersona\n" +
                    "AND    ca.vigente = 'S'\n" +
                    "AND    so.codigo_estado = 6\n" +
                    "ORDER BY 1" , resultClass=CarteraAsignada.class),
    @NamedNativeQuery(name="CarteraAsignada.findByResponsable", 
            query= "SELECT ca.codigo,\n" +
                   "       ca.codigo_recuperador,\n" +
                   "       ca.numero_credito,\n" +
                   "       ca.codigo_ifip,\n" +
                   "       ca.codigo_usuario,\n" +
                   "       ca.fecha_registro,\n" +
                   "       ca.capital_pendiente,\n" +
                   "       ca.interes_pendiente,\n" +
                   "       ca.mora_pendiente,\n" +
                   "       ca.rubro_pendiente,\n" +
                   "       ca.valor_notificacion,\n" +
                   "       ca.valor_judicial,\n" +
                   "       ca.dias_mora,\n" +
                   "       ca.vigente\n" +
                    "FROM   mks_creditos.cartera_asignada ca,\n" +
                    "       mks_creditos.solicitud so,\n" +
                    "       mks_creditos.apoyo_cartera_asignada aca\n" +
                    "WHERE  ca.numero_credito = so.numero\n" +
                    "AND    ca.codigo_ifip = so.codigo_ifip\n" +
                    "AND    so.codigo_ifip_agencia = aca.codigo_ifip_agencia\n" +
                    "AND    aca.codigo_persona = :codigoPersona\n" +
                    "AND    ca.vigente = 'S'\n" +
                    "AND    so.codigo_estado = 6\n" +
                    "UNION\n" +
                    "SELECT ca.*\n" +
                    "FROM   mks_creditos.cartera_asignada ca,\n" +
                    "       mks_creditos.solicitud so\n" +
                    "WHERE  ca.numero_credito = so.numero\n" +
                    "AND    ca.codigo_ifip = so.codigo_ifip\n" +
                    "AND    ca.codigo_recuperador = :codigoPersona\n" +
                    "AND    ca.vigente = 'S'\n" +
                    "AND    so.codigo_estado = 6\n" +
                    "ORDER BY 1" , resultClass=CarteraAsignada.class) })
public class CarteraAsignada implements Serializable {

    private static final long serialVersionUID = 1L;
    public static String findByCodigoPersonaVigente = "CarteraAsignada.findByCodigoPersonaVigente";
    public static String findByAgenciaAndResponsable = "CarteraAsignada.findByAgenciaAndResponsable";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_CARTERA_ASIGNADA")
    @SequenceGenerator(name = "GSQ_CARTERA_ASIGNADA", schema = "MKS_CREDITOS", allocationSize = 0, sequenceName = "SEQ_CARTERA_ASIGNADA")
    @Basic(optional = false)
    @Column(name = "CODIGO")    
    private Long codigo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPITAL_PENDIENTE")
    private BigDecimal capitalPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES_PENDIENTE")
    private BigDecimal interesPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MORA_PENDIENTE")
    private BigDecimal moraPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUBRO_PENDIENTE")
    private BigDecimal rubroPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_NOTIFICACION")
    private BigDecimal valorNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_JUDICIAL")
    private BigDecimal valorJudicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_MORA")
    private long diasMora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;
    @JoinColumn(name = "CODIGO_RECUPERADOR", referencedColumnName = "CODIGO_PERSONA")
    @ManyToOne(optional = false)
    private Recuperador codigoRecuperador;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO")
        , @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP")})
    @ManyToOne(optional = false)
    private Solicitud solicitud;

    public CarteraAsignada() {
    }

    public CarteraAsignada(Long codigo) {
        this.codigo = codigo;
    }

    public CarteraAsignada(Long codigo, long codigoUsuario, Date fechaRegistro, BigDecimal capitalPendiente, BigDecimal interesPendiente, BigDecimal moraPendiente, BigDecimal rubroPendiente, BigDecimal valorNotificacion, BigDecimal valorJudicial, long diasMora, String vigente) {
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.fechaRegistro = fechaRegistro;
        this.capitalPendiente = capitalPendiente;
        this.interesPendiente = interesPendiente;
        this.moraPendiente = moraPendiente;
        this.rubroPendiente = rubroPendiente;
        this.valorNotificacion = valorNotificacion;
        this.valorJudicial = valorJudicial;
        this.diasMora = diasMora;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigDecimal getCapitalPendiente() {
        return capitalPendiente;
    }

    public void setCapitalPendiente(BigDecimal capitalPendiente) {
        this.capitalPendiente = capitalPendiente;
    }

    public BigDecimal getInteresPendiente() {
        return interesPendiente;
    }

    public void setInteresPendiente(BigDecimal interesPendiente) {
        this.interesPendiente = interesPendiente;
    }

    public BigDecimal getMoraPendiente() {
        return moraPendiente;
    }

    public void setMoraPendiente(BigDecimal moraPendiente) {
        this.moraPendiente = moraPendiente;
    }

    public BigDecimal getRubroPendiente() {
        return rubroPendiente;
    }

    public void setRubroPendiente(BigDecimal rubroPendiente) {
        this.rubroPendiente = rubroPendiente;
    }

    public BigDecimal getValorNotificacion() {
        return valorNotificacion;
    }

    public void setValorNotificacion(BigDecimal valorNotificacion) {
        this.valorNotificacion = valorNotificacion;
    }

    public BigDecimal getValorJudicial() {
        return valorJudicial;
    }

    public void setValorJudicial(BigDecimal valorJudicial) {
        this.valorJudicial = valorJudicial;
    }

    public long getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(long diasMora) {
        this.diasMora = diasMora;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public Recuperador getCodigoRecuperador() {
        return codigoRecuperador;
    }

    public void setCodigoRecuperador(Recuperador codigoRecuperador) {
        this.codigoRecuperador = codigoRecuperador;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarteraAsignada)) {
            return false;
        }
        CarteraAsignada other = (CarteraAsignada) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.asignacionCartera.CarteraAsignada[ codigo=" + codigo + " ]";
    }
    
}
