/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.cajas;

import ec.renafipse.mks.capas.utilitario.ConvierteDato;
import ec.renafipse.mks.modelo.ifips.Boveda;
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
@Table(name = "MKS_CAJAS.FONDEO_CAJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FondeoCaja.findAll", query = "SELECT f FROM FondeoCaja f"),
    @NamedQuery(name = "FondeoCaja.findByCodigo", query = "SELECT f FROM FondeoCaja f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "FondeoCaja.findByCodigoBoveda", query = "SELECT f FROM FondeoCaja f WHERE f.codigoBoveda = :codigoBoveda"),
    @NamedQuery(name = "FondeoCaja.findByCodigoIfipAgencia", query = "SELECT f FROM FondeoCaja f WHERE f.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "FondeoCaja.findByCodigoUsuarioFondeo", query = "SELECT f FROM FondeoCaja f WHERE f.codigoUsuarioFondeo = :codigoUsuarioFondeo"),
    @NamedQuery(name = "FondeoCaja.findByCodigoComputadorFondeo", query = "SELECT f FROM FondeoCaja f WHERE f.codigoComputadorFondeo = :codigoComputadorFondeo"),
    @NamedQuery(name = "FondeoCaja.findByCodigoUsuarioCaja", query = "SELECT f FROM FondeoCaja f WHERE f.codigoUsuarioCaja = :codigoUsuarioCaja"),
    @NamedQuery(name = "FondeoCaja.findByCodigoComputadorCaja", query = "SELECT f FROM FondeoCaja f WHERE f.codigoComputadorCaja = :codigoComputadorCaja"),
    @NamedQuery(name = "FondeoCaja.findByFechaFondeo", query = "SELECT f FROM FondeoCaja f WHERE f.fechaFondeo = :fechaFondeo"),
    @NamedQuery(name = "FondeoCaja.findByEstado", query = "SELECT f FROM FondeoCaja f JOIN f.ifipAgencia a WHERE f.estado = :estado AND a.codigo = :codigoIfipAgencia"),
    //Personalizados
    @NamedQuery(name = "FondeoCaja.findByFondeoComputadorVigente", query = "SELECT f FROM FondeoCaja f WHERE f.codigoComputadorCaja = :codigoComputadorCaja AND f.estado NOT IN ('C','N')"),
    @NamedQuery(name = "FondeoCaja.findByFondeoCajeroVigente", query = "SELECT f FROM FondeoCaja f WHERE f.codigoUsuarioCaja =  :codigoUsuarioCaja AND f.estado NOT IN ('C','N')"),
    @NamedQuery(name = "FondeoCaja.findByFechasFondeo", query = "SELECT f FROM FondeoCaja f JOIN f.ifipAgencia a WHERE  f.fechaFondeo >= :fechaInicioFondeo AND f.fechaFondeo <= :fechaFinFondeo AND a.codigo = :codigoIfipAgencia"),
    @NamedQuery(name = "FondeoCaja.findByFechasFondeoEstado", query = "SELECT f FROM FondeoCaja f JOIN f.ifipAgencia a WHERE f.fechaFondeo >= :fechaInicioFondeo AND f.fechaFondeo <= :fechaFinFondeo AND f.estado = :estado AND a.codigo = :codigoIfipAgencia"),
    @NamedQuery(name = "FondeoCaja.findByFondeoAperturaCaja", query = "SELECT f FROM FondeoCaja f JOIN f.ifipAgencia a WHERE  f.codigoComputadorCaja = :codigoComputadorCaja AND f.codigoUsuarioCaja = :codigoUsuarioCaja AND f.estado = :estado AND a.codigo = :codigoIfipAgencia")
})
public class FondeoCaja implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByFechaFondeoComputador = "FondeoCaja.findByFechaFondeoComputador";
    public static final String findByFechaFondeo = "FondeoCaja.findByFechaFondeo";
    public static final String findByFondeoComputadorVigente = "FondeoCaja.findByFondeoComputadorVigente";
    public static final String findByFondeoCajeroVigente = "FondeoCaja.findByFondeoCajeroVigente";
    public static final String findByFechasFondeo = "FondeoCaja.findByFechasFondeo";
    public static final String findByFechasFondeoEstado = "FondeoCaja.findByFechasFondeoEstado";
    public static final String findByEstado = "FondeoCaja.findByEstado";
    public static final String findByFondeoAperturaCaja = "FondeoCaja.findByFondeoAperturaCaja";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_FONDEO_CAJA")
    @SequenceGenerator(name = "GSQ_FONDEO_CAJA", schema = "MKS_CAJAS", allocationSize = 0, sequenceName = "SEQ_FONDEO_CAJA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_BOVEDA")
    private long codigoBoveda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_FONDEO")
    private long codigoUsuarioFondeo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPUTADOR_FONDEO")
    private long codigoComputadorFondeo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_CAJA")
    private long codigoUsuarioCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPUTADOR_CAJA")
    private long codigoComputadorCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FONDEO")
    @Temporal(TemporalType.DATE)
    private Date fechaFondeo;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema; 
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "codigoFondeo", fetch = FetchType.LAZY)
    private AperturaCajaFondeo aperturaCajaFondeo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fondeoCaja", fetch = FetchType.EAGER) 
    private Collection<FondeoCajaDetalle> fondeoCajaDetalleCollection;
    @JoinColumn(name = "CODIGO_BOVEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Boveda boveda;
    @JoinColumn(name = "CODIGO_COMPUTADOR_CAJA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Computador computadorCaja;
    @JoinColumn(name = "CODIGO_COMPUTADOR_FONDEO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Computador computadorFondeo;
    @JoinColumn(name = "CODIGO_USUARIO_CAJA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioCaja;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipAgencia  ifipAgencia;
    @JoinColumn(name = "CODIGO_USUARIO_FONDEO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioFondeo;
    public FondeoCaja() {
    }

    public FondeoCaja(Long codigo) {
        this.codigo = codigo;
    }

    public FondeoCaja(Long codigo, long codigoBoveda, long codigoIfipAgencia, long codigoUsuarioFondeo, long codigoComputadorFondeo, long codigoUsuarioCaja, long codigoComputadorCaja, Date fechaFondeo, char estado) {
        this.codigo = codigo;
        this.codigoBoveda = codigoBoveda;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoUsuarioFondeo = codigoUsuarioFondeo;
        this.codigoComputadorFondeo = codigoComputadorFondeo;
        this.codigoUsuarioCaja = codigoUsuarioCaja;
        this.codigoComputadorCaja = codigoComputadorCaja;
        this.fechaFondeo = fechaFondeo;
        this.estado = estado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoBoveda() {
        return codigoBoveda;
    }

    public void setCodigoBoveda(long codigoBoveda) {
        this.codigoBoveda = codigoBoveda;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoUsuarioFondeo() {
        return codigoUsuarioFondeo;
    }

    public void setCodigoUsuarioFondeo(long codigoUsuarioFondeo) {
        this.codigoUsuarioFondeo = codigoUsuarioFondeo;
    }

    public long getCodigoComputadorFondeo() {
        return codigoComputadorFondeo;
    }

    public void setCodigoComputadorFondeo(long codigoComputadorFondeo) {
        this.codigoComputadorFondeo = codigoComputadorFondeo;
    }

    public long getCodigoUsuarioCaja() {
        return codigoUsuarioCaja;
    }

    public void setCodigoUsuarioCaja(long codigoUsuarioCaja) {
        this.codigoUsuarioCaja = codigoUsuarioCaja;
    }

    public long getCodigoComputadorCaja() {
        return codigoComputadorCaja;
    }

    public void setCodigoComputadorCaja(long codigoComputadorCaja) {
        this.codigoComputadorCaja = codigoComputadorCaja;
    }

    public Date getFechaFondeo() {
        
        return ConvierteDato.getFechaMedium(fechaFondeo);
    }

    public void setFechaFondeo(Date fechaFondeo) {
        this.fechaFondeo = fechaFondeo;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public AperturaCajaFondeo getAperturaCajaFondeo() {
        return aperturaCajaFondeo;
    }

    public void setAperturaCajaFondeo(AperturaCajaFondeo aperturaCajaFondeo) {
        this.aperturaCajaFondeo = aperturaCajaFondeo;
    }

    @XmlTransient
    public Collection<FondeoCajaDetalle> getFondeoCajaDetalleCollection() {
        return fondeoCajaDetalleCollection;
    }

    public void setFondeoCajaDetalleCollection(Collection<FondeoCajaDetalle> fondeoCajaDetalleCollection) {
        this.fondeoCajaDetalleCollection = fondeoCajaDetalleCollection;
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
        if (!(object instanceof FondeoCaja)) {
            return false;
        }
        FondeoCaja other = (FondeoCaja) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.FondeoCaja[ codigo=" + codigo + " ]";
    }

    /**
     * @return the boveda
     */
    public Boveda getBoveda() {
        return boveda;
    }

    /**
     * @param boveda the boveda to set
     */
    public void setBoveda(Boveda boveda) {
        this.boveda = boveda;
    }

    /**
     * @return the computadorCaja
     */
    public Computador getComputadorCaja() {
        return computadorCaja;
    }

    /**
     * @param computadorCaja the computadorCaja to set
     */
    public void setComputadorCaja(Computador computadorCaja) {
        this.computadorCaja = computadorCaja;
    }

    /**
     * @return the computadorFondeo
     */
    public Computador getComputadorFondeo() {
        return computadorFondeo;
    }

    /**
     * @param computadorFondeo the computadorFondeo to set
     */
    public void setComputadorFondeo(Computador computadorFondeo) {
        this.computadorFondeo = computadorFondeo;
    }

    /**
     * @return the usuarioCaja
     */
    public Usuario getUsuarioCaja() {
        return usuarioCaja;
    }

    /**
     * @param usuarioCaja the usuarioCaja to set
     */
    public void setUsuarioCaja(Usuario usuarioCaja) {
        this.usuarioCaja = usuarioCaja;
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

    /**
     * @return the fechaSistema
     */
    public Date getFechaSistema() {
        return fechaSistema;
    }

    /**
     * @param fechaSistema the fechaSistema to set
     */
    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    /**
     * @return the usuarioFondeo
     */
    public Usuario getUsuarioFondeo() {
        return usuarioFondeo;
    }

    /**
     * @param usuarioFondeo the usuarioFondeo to set
     */
    public void setUsuarioFondeo(Usuario usuarioFondeo) {
        this.usuarioFondeo = usuarioFondeo;
    }

}
