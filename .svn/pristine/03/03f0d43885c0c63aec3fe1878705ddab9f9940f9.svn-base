/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

import ec.renafipse.mks.modelo.comunes.Moneda;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_DPFS.TALONARIO_DOCUMENTO_DPF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TalonarioDocumentoDpf.findAll", query = "SELECT t FROM TalonarioDocumentoDpf t"),
    @NamedQuery(name = "TalonarioDocumentoDpf.findByCodigo", query = "SELECT t FROM TalonarioDocumentoDpf t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TalonarioDocumentoDpf.findByCodigoIfip", query = "SELECT t FROM TalonarioDocumentoDpf t WHERE t.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "TalonarioDocumentoDpf.findByCodigoIfipAgencia", query = "SELECT t FROM TalonarioDocumentoDpf t WHERE t.codigoIfipAgencia = :codigoIfipAgencia ORDER BY t.inicioSerie"),
    @NamedQuery(name = "TalonarioDocumentoDpf.findByInicioSerie", query = "SELECT t FROM TalonarioDocumentoDpf t WHERE t.inicioSerie = :inicioSerie"),
    @NamedQuery(name = "TalonarioDocumentoDpf.findByFinSerie", query = "SELECT t FROM TalonarioDocumentoDpf t WHERE t.finSerie = :finSerie"),
    @NamedQuery(name = "TalonarioDocumentoDpf.findByDigitos", query = "SELECT t FROM TalonarioDocumentoDpf t WHERE t.digitos = :digitos"),
    @NamedQuery(name = "TalonarioDocumentoDpf.findByRegistradoPor", query = "SELECT t FROM TalonarioDocumentoDpf t WHERE t.registradoPor = :registradoPor"),
    @NamedQuery(name = "TalonarioDocumentoDpf.findByFechaRegistro", query = "SELECT t FROM TalonarioDocumentoDpf t WHERE t.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "TalonarioDocumentoDpf.findByEliminado", query = "SELECT t FROM TalonarioDocumentoDpf t WHERE t.eliminado = :eliminado"),
    //PERSONALIZADAS
    @NamedQuery(name = "TalonarioDocumentoDpf.findByUnique", query = "SELECT t FROM TalonarioDocumentoDpf t WHERE t.codigoIfip = :codigoIfip AND t.codigoMoneda = :codigoMoneda AND t.inicioSerie = :inicioSerie"),
    @NamedQuery(name = "TalonarioDocumentoDpf.findByRangoSerie", query = "SELECT t FROM TalonarioDocumentoDpf t WHERE t.codigoIfip = :codigoIfip AND t.codigoMoneda = :codigoMoneda AND :inicioSerie BETWEEN t.inicioSerie AND t.finSerie")})
public class TalonarioDocumentoDpf implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByUnique = "TalonarioDocumentoDpf.findByUnique";
    public static final String findByCodigoIfipAgencia = "TalonarioDocumentoDpf.findByCodigoIfipAgencia";
    public static final String findByRangoSerie = "TalonarioDocumentoDpf.findByRangoSerie";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_RENOVACION_CONTRATO_DPF")
    @SequenceGenerator(name = "GSQ_RENOVACION_CONTRATO_DPF", schema = "MKS_DPFS",  allocationSize = 0, sequenceName = "SEQ_RENOVACION_CONTRATO_DPF")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Min(value = 1)
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INICIO_SERIE")
    private long inicioSerie;
    @Basic(optional = false)
    @NotNull
    @Min(value = 1)
    @Column(name = "FIN_SERIE")
    private long finSerie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIGITOS")
    private long digitos;   
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPUTADOR")
    private long codigoComputador;
   
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioRegistradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipAgencia ifipAgencia;
    @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda;
    @JoinColumn(name = "CODIGO_COMPUTADOR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Computador computador;
    
    public TalonarioDocumentoDpf() {
    }

    public TalonarioDocumentoDpf(Long codigo) {
        this.codigo = codigo;
    }

    public TalonarioDocumentoDpf(Long codigo, long codigoIfip, long codigoIfipAgencia, long inicioSerie, long finSerie, long digitos, long registradoPor, Date fechaRegistro, char eliminado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.inicioSerie = inicioSerie;
        this.finSerie = finSerie;
        this.digitos = digitos;
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

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getInicioSerie() {
        return inicioSerie;
    }

    public void setInicioSerie(long inicioSerie) {
        this.inicioSerie = inicioSerie;
    }

    public long getFinSerie() {
        return finSerie;
    }

    public void setFinSerie(long finSerie) {
        this.finSerie = finSerie;
    }

    public long getDigitos() {
        return digitos;
    }

    public void setDigitos(long digitos) {
        this.digitos = digitos;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TalonarioDocumentoDpf)) {
            return false;
        }
        TalonarioDocumentoDpf other = (TalonarioDocumentoDpf) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.TalonarioDocumentoDpf[ codigo=" + codigo + " ]";
    }
    /**
     * @return the usuarioRegistradoPor
     */
    public Usuario getUsuarioRegistradoPor() {
        return usuarioRegistradoPor;
    }

    /**
     * @param usuarioRegistradoPor the usuarioRegistradoPor to set
     */
    public void setUsuarioRegistradoPor(Usuario usuarioRegistradoPor) {
        this.usuarioRegistradoPor = usuarioRegistradoPor;
    }

    /**
     * @return the codigoMoneda
     */
    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    /**
     * @param codigoMoneda the codigoMoneda to set
     */
    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
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

   

   
    
}
