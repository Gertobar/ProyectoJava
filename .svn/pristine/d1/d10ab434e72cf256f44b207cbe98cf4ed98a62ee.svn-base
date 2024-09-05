/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.TALONARIO_LIBRETA_AHORRO_CAB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findAll", query = "SELECT t FROM TalonarioLibretaAhorroCab t"),
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByCodigo", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByInicioSerie", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.inicioSerie = :inicioSerie"),
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByFinSerie", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.finSerie = :finSerie"),
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByDigitosLibreta", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.digitosLibreta = :digitosLibreta"),
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByRegistradoPor", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.registradoPor = :registradoPor"),
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByFechaRegistro", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByEliminado", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByCodigoIfip", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.codigoIfip = :codigoIfip ORDER BY t.productoIfip.producto.moneda.nombre, t.productoIfip.producto.tipoProducto.nombre, t.inicioSerie"),
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByCodigoIfipSerie", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.codigoIfip = :codigoIfip AND :serieInicio BETWEEN t.inicioSerie AND t.finSerie AND :serieFin BETWEEN t.inicioSerie AND t.finSerie AND t.eliminado = :eliminado"),
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByCodigoIfipSerieTipoProducto", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.codigoTipoProducto = :codigoTipoProducto AND t.codigoIfip = :codigoIfip AND :serieInicio BETWEEN t.inicioSerie AND t.finSerie AND :serieFin BETWEEN t.inicioSerie AND t.finSerie AND t.eliminado = :eliminado"),
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByCodigoIfipInicioSerieEntreInicioFinSerie", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.codigoIfip = :codigoIfip AND :inicioSerie >= t.inicioSerie AND  :inicioSerie <= t.finSerie"),
    @NamedQuery(name = "TalonarioLibretaAhorroCab.findByCodigoIfipInicioSerieEntreInicioFinSerieTipoProducto", query = "SELECT t FROM TalonarioLibretaAhorroCab t WHERE t.codigoTipoProducto = :codigoTipoProducto AND t.codigoIfip = :codigoIfip AND :inicioSerie >= t.inicioSerie AND  :inicioSerie <= t.finSerie")
})
public class TalonarioLibretaAhorroCab implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoIfip = "TalonarioLibretaAhorroCab.findByCodigoIfip";
    public static final String findByCodigoIfipInicioSerieEntreInicioFinSerie = "TalonarioLibretaAhorroCab.findByCodigoIfipInicioSerieEntreInicioFinSerie";
    public static final String findByCodigoIfipInicioSerieEntreInicioFinSerieTipoProducto = "TalonarioLibretaAhorroCab.findByCodigoIfipInicioSerieEntreInicioFinSerieTipoProducto";
    public static final String findByCodigoIfipSerie = "TalonarioLibretaAhorroCab.findByCodigoIfipSerie";
    public static final String findByCodigoIfipSerieTipoProducto = "TalonarioLibretaAhorroCab.findByCodigoIfipSerieTipoProducto";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TALONARIO_LIBRETA_AHO_CAB")
    @SequenceGenerator(name = "GSQ_TALONARIO_LIBRETA_AHO_CAB", schema = "MKS_AHORROS", allocationSize = 0, sequenceName = "SEQ_TALONARIO_LIBRETA_AHO_CAB")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_PRODUCTO")
    private long codigoTipoProducto;
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
    @Column(name = "INICIO_SERIE")
    private long inicioSerie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FIN_SERIE")    
    private long finSerie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIGITOS_LIBRETA")
    private short digitosLibreta;
    /*@Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")*/
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTalLibAhoCab", fetch = FetchType.LAZY )
    private Collection<TalonarioLibretaAhorroDet> talonarioLibretaAhorroDetCollection;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoIfip productoIfip;

    public TalonarioLibretaAhorroCab() {
    }

    public TalonarioLibretaAhorroCab(Long codigo) {
        this.codigo = codigo;
    }

    public TalonarioLibretaAhorroCab(Long codigo, long inicioSerie, long finSerie, short digitosLibreta, Usuario registradoPor, Date fechaRegistro, char eliminado) {
        this.codigo = codigo;
        this.inicioSerie = inicioSerie;
        this.finSerie = finSerie;
        this.digitosLibreta = digitosLibreta;
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

    public short getDigitosLibreta() {
        return digitosLibreta;
    }

    public void setDigitosLibreta(short digitosLibreta) {
        this.digitosLibreta = digitosLibreta;
    }

    public Usuario getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(Usuario registradoPor) {
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
    public Collection<TalonarioLibretaAhorroDet> getTalonarioLibretaAhorroDetCollection() {
        return talonarioLibretaAhorroDetCollection;
    }

    public void setTalonarioLibretaAhorroDetCollection(Collection<TalonarioLibretaAhorroDet> talonarioLibretaAhorroDetCollection) {
        this.talonarioLibretaAhorroDetCollection = talonarioLibretaAhorroDetCollection;
    }

    public ProductoIfip getProductoIfip() {
        return productoIfip;
    }

    public void setProductoIfip(ProductoIfip productoIfip) {
        this.productoIfip = productoIfip;
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
        if (!(object instanceof TalonarioLibretaAhorroCab)) {
            return false;
        }
        TalonarioLibretaAhorroCab other = (TalonarioLibretaAhorroCab) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.TalonarioLibretaAhorroCab[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoTipoProducto
     */
    public long getCodigoTipoProducto() {
        return codigoTipoProducto;
    }

    /**
     * @param codigoTipoProducto the codigoTipoProducto to set
     */
    public void setCodigoTipoProducto(long codigoTipoProducto) {
        this.codigoTipoProducto = codigoTipoProducto;
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
     * @return the codigoIfip
     */
    public long getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }
    
}
