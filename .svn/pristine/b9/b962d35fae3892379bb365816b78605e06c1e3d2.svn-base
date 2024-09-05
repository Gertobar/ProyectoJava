/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_DPFS.TASA_INTERES_PRO_DPF_MON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TasaInteresProDpfMon.findAll", query = "SELECT t FROM TasaInteresProDpfMon t")
    ,
    @NamedQuery(name = "TasaInteresProDpfMon.findByCodigo", query = "SELECT t FROM TasaInteresProDpfMon t WHERE t.codigo = :codigo")
    ,
    @NamedQuery(name = "TasaInteresProDpfMon.findByCodigoMoneda", query = "SELECT t FROM TasaInteresProDpfMon t WHERE t.codigoMoneda = :codigoMoneda")
    ,
    @NamedQuery(name = "TasaInteresProDpfMon.findByCodigoIfip", query = "SELECT t FROM TasaInteresProDpfMon t WHERE t.codigoIfip = :codigoIfip")
    ,
    @NamedQuery(name = "TasaInteresProDpfMon.findByMontoInicial", query = "SELECT t FROM TasaInteresProDpfMon t WHERE t.montoInicial = :montoInicial")
    ,
    @NamedQuery(name = "TasaInteresProDpfMon.findByMontoFinal", query = "SELECT t FROM TasaInteresProDpfMon t WHERE t.montoFinal = :montoFinal")
    ,
    @NamedQuery(name = "TasaInteresProDpfMon.findByRegistradoPor", query = "SELECT t FROM TasaInteresProDpfMon t WHERE t.registradoPor = :registradoPor")
    ,
    @NamedQuery(name = "TasaInteresProDpfMon.findByFechaRegistro", query = "SELECT t FROM TasaInteresProDpfMon t WHERE t.fechaRegistro = :fechaRegistro")
    ,
    @NamedQuery(name = "TasaInteresProDpfMon.findByEliminado", query = "SELECT t FROM TasaInteresProDpfMon t WHERE t.eliminado = :eliminado")
    ,
    //PERSONALIZADOS
    @NamedQuery(name = "TasaInteresProDpfMon.findByContratoDpf", query = ""
            + "SELECT t FROM TasaInteresProDpfMon t "
            + "JOIN t.tasa s  JOIN t.rango r "
            + "WHERE s.codigoIfip = :codigoIfip  AND s.eliminado =:eliminado  "
            + "AND  :rango BETWEEN r.rangoInicial "
            + "AND r.rangoFinal "
            + "AND r.eliminado = :eliminado "
            + "AND :monto BETWEEN t.montoInicial AND t.montoFinal "
            + "AND t.codigoMoneda =:codigoMoneda AND t.eliminado = :eliminado "
            + "AND COALESCE(t.montoMinimoNuevaTasa,0) >0 "
            + "")
    ,
    @NamedQuery(name = "TasaInteresProDpfMon.findByContratoDpfMontoMinimo", query = "SELECT t FROM TasaInteresProDpfMon t JOIN t.tasa s  JOIN t.rango r "
            + "WHERE s.codigoIfip = :codigoIfip  "
            + " AND s.eliminado =:eliminado  "
            + " AND :rango BETWEEN r.rangoInicial AND r.rangoFinal "
            + " AND r.eliminado = :eliminado "
            + " AND :monto >= COALESCE(t.montoMinimoNuevaTasa,0) "
            + " AND t.codigoMoneda =:codigoMoneda "
            + " AND t.eliminado = :eliminado")
})
public class TasaInteresProDpfMon implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByContratoDpf = "TasaInteresProDpfMon.findByContratoDpf";
    public static final String findByContratoDpfMontoMinimo = "TasaInteresProDpfMon.findByContratoDpfMontoMinimo";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TASA_INTERES_PRO_DPF_MON")
    @SequenceGenerator(name = "GSQ_TASA_INTERES_PRO_DPF_MON", schema = "MKS_DPFS", allocationSize = 0, sequenceName = "SEQ_TASA_INTERES_PRO_DPF_MON")
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
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_RANGO")
    private long codigoRango;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TASA")
    private long codigoTasa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_INICIAL")
    private BigDecimal montoInicial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_FINAL")
    private BigDecimal montoFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TASA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TasaInteresDpfIfip tasa;
    @JoinColumn(name = "CODIGO_RANGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RangoDiasDpf rango;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTasa", fetch = FetchType.LAZY)
    private Collection<RenovacionContratoDpf> renovacionContratoDpfCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTasa", fetch = FetchType.LAZY)
    private Collection<ContratoDpf> contratoDpfCollection;
    @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda;
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioRealizadoPor;

    @Basic(optional = true)    
    @Column(name = "MONTO_MINIMO_NUEVA_TASA",nullable = true)
    private BigDecimal montoMinimoNuevaTasa = BigDecimal.ZERO;

    public TasaInteresProDpfMon() {
    }

    public TasaInteresProDpfMon(Long codigo) {
        this.codigo = codigo;
    }

    public TasaInteresProDpfMon(Long codigo, long codigoMoneda, long codigoIfip, BigDecimal montoInicial, BigDecimal montoFinal, long registradoPor, Date fechaRegistro, char eliminado, BigDecimal montoMinimoNuevaTasa) {
        this.montoMinimoNuevaTasa = montoMinimoNuevaTasa;
        this.codigo = codigo;
        this.codigoMoneda = codigoMoneda;
        this.codigoIfip = codigoIfip;
        this.montoInicial = montoInicial;
        this.montoFinal = montoFinal;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
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

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }

    public BigDecimal getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(BigDecimal montoFinal) {
        this.montoFinal = montoFinal;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<RenovacionContratoDpf> getRenovacionContratoDpfCollection() {
        return renovacionContratoDpfCollection;
    }

    public void setRenovacionContratoDpfCollection(Collection<RenovacionContratoDpf> renovacionContratoDpfCollection) {
        this.renovacionContratoDpfCollection = renovacionContratoDpfCollection;
    }

    @XmlTransient
    public Collection<ContratoDpf> getContratoDpfCollection() {
        return contratoDpfCollection;
    }

    public void setContratoDpfCollection(Collection<ContratoDpf> contratoDpfCollection) {
        this.contratoDpfCollection = contratoDpfCollection;
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
        if (!(object instanceof TasaInteresProDpfMon)) {
            return false;
        }
        TasaInteresProDpfMon other = (TasaInteresProDpfMon) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.TasaInteresProDpfMon[ codigo=" + codigo + " ]";
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the usuarioRealizadoPor
     */
    public Usuario getUsuarioRealizadoPor() {
        return usuarioRealizadoPor;
    }

    /**
     * @param usuarioRealizadoPor the usuarioRealizadoPor to set
     */
    public void setUsuarioRealizadoPor(Usuario usuarioRealizadoPor) {
        this.usuarioRealizadoPor = usuarioRealizadoPor;
    }

    /**
     * @return the codigoRango
     */
    public long getCodigoRango() {
        return codigoRango;
    }

    /**
     * @param codigoRango the codigoRango to set
     */
    public void setCodigoRango(long codigoRango) {
        this.codigoRango = codigoRango;
    }

    /**
     * @return the codigoTasa
     */
    public long getCodigoTasa() {
        return codigoTasa;
    }

    /**
     * @param codigoTasa the codigoTasa to set
     */
    public void setCodigoTasa(long codigoTasa) {
        this.codigoTasa = codigoTasa;
    }

    /**
     * @return the tasa
     */
    public TasaInteresDpfIfip getTasa() {
        return tasa;
    }

    /**
     * @param tasa the tasa to set
     */
    public void setTasa(TasaInteresDpfIfip tasa) {
        this.tasa = tasa;
    }

    /**
     * @return the rango
     */
    public RangoDiasDpf getRango() {
        return rango;
    }

    /**
     * @param rango the rango to set
     */
    public void setRango(RangoDiasDpf rango) {
        this.rango = rango;
    }

    public BigDecimal getMontoMinimoNuevaTasa() {
        return montoMinimoNuevaTasa;
    }

    public void setMontoMinimoNuevaTasa(BigDecimal montoMinimoNuevaTasa) {
        this.montoMinimoNuevaTasa = montoMinimoNuevaTasa;
    }

}
