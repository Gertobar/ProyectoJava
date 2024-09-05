/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.TIPO_BLOQUEO_CUENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoBloqueoCuenta.findAll", query = "SELECT t FROM TipoBloqueoCuenta t"),
    @NamedQuery(name = "TipoBloqueoCuenta.findByCodigo", query = "SELECT t FROM TipoBloqueoCuenta t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoBloqueoCuenta.findByNombre", query = "SELECT t FROM TipoBloqueoCuenta t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoBloqueoCuenta.findByEliminado", query = "SELECT t FROM TipoBloqueoCuenta t WHERE t.eliminado = :eliminado"),
    @NamedQuery(name = "TipoBloqueoCuenta.findByMostrarEliminado", query = "SELECT t FROM TipoBloqueoCuenta t WHERE t.mostrar = :mostrar AND t.eliminado = :eliminado ORDER BY t.nombre")
})
public class TipoBloqueoCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByMostrarEliminado = "TipoBloqueoCuenta.findByMostrarEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_BLOQUEO_CUENTA")
    @SequenceGenerator(name = "GSQ_TIPO_BLOQUEO_CUENTA", schema = "MKS_AHORROS", allocationSize = 0, sequenceName = "SEQ_TIPO_BLOQUEO_CUENTA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOSTRAR")
    private char mostrar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoBloqueoCuenta", fetch = FetchType.LAZY)
    private Collection<BloqueoCuenta> bloqueoCuentaCollection;

    public TipoBloqueoCuenta() {
    }

    public TipoBloqueoCuenta(Long codigo) {
        this.codigo = codigo;
    }

    public TipoBloqueoCuenta(Long codigo, String nombre, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<BloqueoCuenta> getBloqueoCuentaCollection() {
        return bloqueoCuentaCollection;
    }

    public void setBloqueoCuentaCollection(Collection<BloqueoCuenta> bloqueoCuentaCollection) {
        this.bloqueoCuentaCollection = bloqueoCuentaCollection;
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
        if (!(object instanceof TipoBloqueoCuenta)) {
            return false;
        }
        TipoBloqueoCuenta other = (TipoBloqueoCuenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.TipoBloqueoCuenta[ codigo=" + codigo + " ]";
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
    
}
