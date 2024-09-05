/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ifips;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "MKS_IFIPS.TIPO_MOVIMIENTO_BOVEDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMovimientoBoveda.findAll", query = "SELECT t FROM TipoMovimientoBoveda t"),
    @NamedQuery(name = "TipoMovimientoBoveda.findByCodigo", query = "SELECT t FROM TipoMovimientoBoveda t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoMovimientoBoveda.findByNombre", query = "SELECT t FROM TipoMovimientoBoveda t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoMovimientoBoveda.findByDescripcion", query = "SELECT t FROM TipoMovimientoBoveda t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoMovimientoBoveda.findByEliminado", query = "SELECT t FROM TipoMovimientoBoveda t WHERE t.eliminado = :eliminado"),
    @NamedQuery(name = "TipoMovimientoBoveda.findByTipoMostrarEliminado", query = "SELECT t FROM TipoMovimientoBoveda t WHERE t.tipo = :tipo AND t.mostrar =  :mostrar AND t.eliminado = :eliminado")
})
public class TipoMovimientoBoveda implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "TipoMovimientoBoveda.findByEliminado";
    public static final String findByTipoMostrarEliminado = "TipoMovimientoBoveda.findByTipoMostrarEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_MOVIMIENTO_BOVEDA")
    @SequenceGenerator(name = "GSQ_TIPO_MOVIMIENTO_BOVEDA", schema = "MKS_IFIPS", allocationSize = 0, sequenceName = "SEQ_TIPO_MOVIMIENTO_BOVEDA")
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOSTRAR")
    private char mostrar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONTABILIZA")
    private char contabiliza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERVIENE")
    private char interviene;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoMovimiento", fetch = FetchType.LAZY)
    private Collection<MovimientoBoveda> movimientoBovedaCollection;

    public TipoMovimientoBoveda() {
    }

    public TipoMovimientoBoveda(Long codigo) {
        this.codigo = codigo;
    }

    public TipoMovimientoBoveda(Long codigo, String nombre, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<MovimientoBoveda> getMovimientoBovedaCollection() {
        return movimientoBovedaCollection;
    }

    public void setMovimientoBovedaCollection(Collection<MovimientoBoveda> movimientoBovedaCollection) {
        this.movimientoBovedaCollection = movimientoBovedaCollection;
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
        if (!(object instanceof TipoMovimientoBoveda)) {
            return false;
        }
        TipoMovimientoBoveda other = (TipoMovimientoBoveda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.TipoMovimientoBoveda[ codigo=" + codigo + " ]";
    }

    /**
     * @return the tipo
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the mostrar
     */
    public char getMostrar() {
        return mostrar;
    }

    /**
     * @param mostrar the mostrar to set
     */
    public void setMostrar(char mostrar) {
        this.mostrar = mostrar;
    }

    /**
     * @return the contabiliza
     */
    public char getContabiliza() {
        return contabiliza;
    }

    /**
     * @param contabiliza the contabiliza to set
     */
    public void setContabiliza(char contabiliza) {
        this.contabiliza = contabiliza;
    }

    
    /**
     * @return the interviene
     */
    public char getInterviene() {
        return interviene;
    }

    /**
     * @param interviene the interviene to set
     */
    public void setInterviene(char interviene) {
        this.interviene = interviene;
    }

}
