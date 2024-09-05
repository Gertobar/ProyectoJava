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
@Table(name = "MKS_AHORROS.ESTADO_CUENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCuenta.findAll", query = "SELECT e FROM EstadoCuenta e"),
    @NamedQuery(name = "EstadoCuenta.findByCodigo", query = "SELECT e FROM EstadoCuenta e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EstadoCuenta.findByNombre", query = "SELECT e FROM EstadoCuenta e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoCuenta.findByIndicaActiva", query = "SELECT e FROM EstadoCuenta e WHERE e.indicaActiva = :indicaActiva"),
    @NamedQuery(name = "EstadoCuenta.findByPuedeReactivar", query = "SELECT e FROM EstadoCuenta e WHERE e.puedeReactivar = :puedeReactivar"),
    @NamedQuery(name = "EstadoCuenta.findByEliminado", query = "SELECT e FROM EstadoCuenta e WHERE e.eliminado = :eliminado")})
public class EstadoCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_ESTADO_CUENTA")
    @SequenceGenerator(name = "GSQ_ESTADO_CUENTA", schema = "MKS_AHORROS", allocationSize = 0, sequenceName = "SEQ_ESTADO_CUENTA")
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
    @Column(name = "INDICA_ACTIVA")
    private char indicaActiva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUEDE_REACTIVAR")
    private char puedeReactivar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEstado", fetch = FetchType.LAZY)
    private Collection<Cuenta> cuentaCollection;

    public EstadoCuenta() {
    }

    public EstadoCuenta(Long codigo) {
        this.codigo = codigo;
    }

    public EstadoCuenta(Long codigo, String nombre, char indicaActiva, char puedeReactivar, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.indicaActiva = indicaActiva;
        this.puedeReactivar = puedeReactivar;
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

    public char getIndicaActiva() {
        return indicaActiva;
    }

    public void setIndicaActiva(char indicaActiva) {
        this.indicaActiva = indicaActiva;
    }

    public char getPuedeReactivar() {
        return puedeReactivar;
    }

    public void setPuedeReactivar(char puedeReactivar) {
        this.puedeReactivar = puedeReactivar;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Cuenta> getCuentaCollection() {
        return cuentaCollection;
    }

    public void setCuentaCollection(Collection<Cuenta> cuentaCollection) {
        this.cuentaCollection = cuentaCollection;
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
        if (!(object instanceof EstadoCuenta)) {
            return false;
        }
        EstadoCuenta other = (EstadoCuenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.EstadoCuenta[ codigo=" + codigo + " ]";
    }
    
}
