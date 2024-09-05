/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.APERTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apertura.findAll", query = "SELECT a FROM Apertura a"),
    @NamedQuery(name = "Apertura.findByCodigo", query = "SELECT a FROM Apertura a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Apertura.findByCodigoUsuario", query = "SELECT a FROM Apertura a WHERE a.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "Apertura.findByCodigoIfipAgencia", query = "SELECT a FROM Apertura a WHERE a.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "Apertura.findByCodigoComputador", query = "SELECT a FROM Apertura a WHERE a.codigoComputador = :codigoComputador"),
    @NamedQuery(name = "Apertura.findByTipo", query = "SELECT a FROM Apertura a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "Apertura.findByFechaApertura", query = "SELECT a FROM Apertura a WHERE a.fechaApertura = :fechaApertura"),
    @NamedQuery(name = "Apertura.findByFechaSistema", query = "SELECT a FROM Apertura a WHERE a.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "Apertura.findByEstado", query = "SELECT a FROM Apertura a WHERE a.estado = :estado"),
    //Personalizados
    @NamedQuery(name = "Apertura.findByAperturaUsuarioIfipAgencia", query = "SELECT a FROM Apertura a WHERE a.codigoIfipAgencia = :codigoIfipAgencia AND  a.codigoUsuario = :codigoUsuario AND a.estado = :estado"),
    @NamedQuery(name = "Apertura.findByUsuarioEstado", query = "SELECT a FROM Apertura a WHERE  a.codigoUsuario = :codigoUsuario AND a.estado = :estado"),
    @NamedQuery(name = "Apertura.findByIfipAgenciaEstado", query = "SELECT a FROM Apertura a WHERE  a.codigoIfipAgencia = :codigoIfipAgencia AND a.estado = :estado"),
    @NamedQuery(name = "Apertura.findByUsuarioIfipAgenciaFechaApertura", query = "SELECT a FROM Apertura a WHERE a.codigoIfipAgencia=:codigoIfipAgencia AND a.codigoUsuario = :codigoUsuario AND a.fechaApertura = :fechaApertura")
})
public class Apertura implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByAperturaUsuarioIfipAgencia = "Apertura.findByAperturaUsuarioIfipAgencia";
    public static final String findByUsuarioEstado = "Apertura.findByUsuarioEstado";
    public static final String findByIfipAgenciaEstado = "Apertura.findByIfipAgenciaEstado";
    public static final String findByUsuarioIfipAgenciaFechaApertura = "Apertura.findByUsuarioIfipAgenciaFechaApertura";
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_APERTURA")
    @SequenceGenerator(name = "GSQ_APERTURA", schema = "MKS_CAJAS",  allocationSize = 0, sequenceName = "SEQ_APERTURA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
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
    @Column(name = "FECHA_APERTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaApertura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apertura", fetch = FetchType.LAZY)
    private Collection<AperturaDetalle> aperturaDetalleCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "apertura", fetch = FetchType.LAZY)
    private AperturaCajaFondeo aperturaCajaFondeo;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "apertura", fetch =  FetchType.LAZY)
    private Cierre cierre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoApertura", fetch = FetchType.LAZY)
    private Collection<AperturaCajaMovimientoCta> aperturaCajaMovimientoCtaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoApertura", fetch = FetchType.LAZY)
    private Collection<IngresoEgresoCaja> ingresoEgresoCajaCollection;
    @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "CODIGO_COMPUTADOR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Computador computador;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipAgencia ifipAgencia;

    public Apertura() {
    }

    public Apertura(Long codigo) {
        this.codigo = codigo;
    }

    public Apertura(Long codigo, long codigoUsuario, long codigoIfipAgencia, long codigoComputador, char tipo, Date fechaApertura, Date fechaSistema, char estado) {
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoComputador = codigoComputador;
        this.tipo = tipo;
        this.fechaApertura = fechaApertura;
        this.fechaSistema = fechaSistema;
        this.estado = estado;
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

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoComputador() {
        return codigoComputador;
    }

    public void setCodigoComputador(long codigoComputador) {
        this.codigoComputador = codigoComputador;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<AperturaDetalle> getAperturaDetalleCollection() {
        return aperturaDetalleCollection;
    }

    public void setAperturaDetalleCollection(Collection<AperturaDetalle> aperturaDetalleCollection) {
        this.aperturaDetalleCollection = aperturaDetalleCollection;
    }

    public AperturaCajaFondeo getAperturaCajaFondeo() {
        return aperturaCajaFondeo;
    }

    public void setAperturaCajaFondeo(AperturaCajaFondeo aperturaCajaFondeo) {
        this.aperturaCajaFondeo = aperturaCajaFondeo;
    }

    public Cierre getCierre() {
        return cierre;
    }

    public void setCierre(Cierre cierre) {
        this.cierre = cierre;
    }

    @XmlTransient
    public Collection<AperturaCajaMovimientoCta> getAperturaCajaMovimientoCtaCollection() {
        return aperturaCajaMovimientoCtaCollection;
    }

    public void setAperturaCajaMovimientoCtaCollection(Collection<AperturaCajaMovimientoCta> aperturaCajaMovimientoCtaCollection) {
        this.aperturaCajaMovimientoCtaCollection = aperturaCajaMovimientoCtaCollection;
    }

    @XmlTransient
    public Collection<IngresoEgresoCaja> getIngresoEgresoCajaCollection() {
        return ingresoEgresoCajaCollection;
    }

    public void setIngresoEgresoCajaCollection(Collection<IngresoEgresoCaja> ingresoEgresoCajaCollection) {
        this.ingresoEgresoCajaCollection = ingresoEgresoCajaCollection;
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
        if (!(object instanceof Apertura)) {
            return false;
        }
        Apertura other = (Apertura) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.Apertura[ codigo=" + codigo + " ]";
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the computador
     */
    public Computador getComputador() {
        return computador;
    }

    /**
     * @param computador the computador to set
     */
    public void setComputador(Computador computador) {
        this.computador = computador;
    }

    /**
     * @return the ifipAgencia
     */
    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    /**
     * @param ifipAgencia the ifipAgencia to set
     */
    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }
    
}
