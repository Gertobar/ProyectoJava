/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ifips;

import ec.renafipse.mks.modelo.seguridades.Usuario;
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Viajes2
 */
@Entity
@Table(name = "MKS_IFIPS.BOVEDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boveda.findAll", query = "SELECT b FROM Boveda b"),
    @NamedQuery(name = "Boveda.findByCodigo", query = "SELECT b FROM Boveda b WHERE b.codigo = :codigo"),
    @NamedQuery(name = "Boveda.findByNombre", query = "SELECT b FROM Boveda b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "Boveda.findByDescripcion", query = "SELECT b FROM Boveda b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "Boveda.findByAncho", query = "SELECT b FROM Boveda b WHERE b.ancho = :ancho"),
    @NamedQuery(name = "Boveda.findByLargo", query = "SELECT b FROM Boveda b WHERE b.largo = :largo"),
    @NamedQuery(name = "Boveda.findByAltura", query = "SELECT b FROM Boveda b WHERE b.altura = :altura"),
    @NamedQuery(name = "Boveda.findByTieneCamaras", query = "SELECT b FROM Boveda b WHERE b.tieneCamaras = :tieneCamaras"),
    @NamedQuery(name = "Boveda.findByEliminado", query = "SELECT b FROM Boveda b WHERE b.eliminado = :eliminado"),
    @NamedQuery(name = "Boveda.findByResponsable", query = "SELECT b FROM Boveda b WHERE b.responsable = :responsable"),
    //Personalizados
    @NamedQuery(name = "Boveda.findByResponsableIfipAgenciaEliminado", query = "SELECT b FROM Boveda b WHERE b.codigoIfipAgencia.codigo = :codigoIfipAgencia AND b.responsable = :responsable AND b.eliminado = :eliminado")

})
public class Boveda implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByResponsableIfipAgenciaEliminado = "Boveda.findByResponsableIfipAgenciaEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_BOVEDA")
    @SequenceGenerator(name = "GSQ_BOVEDA", schema = "MKS_IFIPS", allocationSize = 0, sequenceName = "SEQ_BOVEDA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANCHO")
    private BigDecimal ancho;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LARGO")
    private BigDecimal largo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALTURA")
    private BigDecimal altura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIENE_CAMARAS")
    private char tieneCamaras;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESPONSABLE")
    private long responsable;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long ifipAgenciaBoveda;
    
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipAgencia codigoIfipAgencia;
    @JoinColumn(name = "RESPONSABLE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario responsableBoveda;

//    
//      @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoBoveda", fetch = FetchType.LAZY)
//    private Collection<MovimientoBoveda> movimientoBovedaCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boveda", fetch = FetchType.LAZY)
//    private Collection<BovedaDinero> bovedaDineroCollection;
    public Boveda() {
    }

    public Boveda(Long codigo) {
        this.codigo = codigo;
    }

    public Boveda(Long codigo, String nombre, String descripcion, BigDecimal ancho, BigDecimal largo, BigDecimal altura, char tieneCamaras, char eliminado, long responsable) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ancho = ancho;
        this.largo = largo;
        this.altura = altura;
        this.tieneCamaras = tieneCamaras;
        this.eliminado = eliminado;
        this.responsable = responsable;
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

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public BigDecimal getLargo() {
        return largo;
    }

    public void setLargo(BigDecimal largo) {
        this.largo = largo;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public char getTieneCamaras() {
        return tieneCamaras;
    }

    public void setTieneCamaras(char tieneCamaras) {
        this.tieneCamaras = tieneCamaras;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public long getResponsable() {
        return responsable;
    }

    public void setResponsable(long responsable) {
        this.responsable = responsable;
    }

//    @XmlTransient
//    public Collection<MovimientoBoveda> getMovimientoBovedaCollection() {
//        return movimientoBovedaCollection;
//    }
//
//    public void setMovimientoBovedaCollection(Collection<MovimientoBoveda> movimientoBovedaCollection) {
//        this.movimientoBovedaCollection = movimientoBovedaCollection;
//    }
    public IfipAgencia getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(IfipAgencia codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

//    @XmlTransient
//    public Collection<BovedaDinero> getBovedaDineroCollection() {
//        return bovedaDineroCollection;
//    }
//
//    public void setBovedaDineroCollection(Collection<BovedaDinero> bovedaDineroCollection) {
//        this.bovedaDineroCollection = bovedaDineroCollection;
//    }
    public Usuario getResponsableBoveda() {
        return responsableBoveda;
    }

    public void setResponsableBoveda(Usuario responsableBoveda) {
        this.responsableBoveda = responsableBoveda;
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
        if (!(object instanceof Boveda)) {
            return false;
        }
        Boveda other = (Boveda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.Boveda[ codigo=" + codigo + " ]";
    }

//    /**
//     * @return the ifipAgenciaBoveda
//     */
//    public long getIfipAgenciaBoveda() {
//        return ifipAgenciaBoveda;
//    }
//
//    /**
//     * @param ifipAgenciaBoveda the ifipAgenciaBoveda to set
//     */
//    public void setIfipAgenciaBoveda(long ifipAgenciaBoveda) {
//        this.ifipAgenciaBoveda = ifipAgenciaBoveda;
//    }
    /**
     * @return the ifipAgenciaBoveda
     */
    public long getIfipAgenciaBoveda() {
        return ifipAgenciaBoveda;
    }

    /**
     * @param ifipAgenciaBoveda the ifipAgenciaBoveda to set
     */
    public void setIfipAgenciaBoveda(long ifipAgenciaBoveda) {
        this.ifipAgenciaBoveda = ifipAgenciaBoveda;
    }

}
