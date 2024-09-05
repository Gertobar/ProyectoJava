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
@Table(name = "MKS_IFIPS.DESTINO_COMPUTADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DestinoComputador.findAll", query = "SELECT d FROM DestinoComputador d"),
    @NamedQuery(name = "DestinoComputador.findByCodigo", query = "SELECT d FROM DestinoComputador d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "DestinoComputador.findByNombre", query = "SELECT d FROM DestinoComputador d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DestinoComputador.findByEliminado", query = "SELECT d FROM DestinoComputador d WHERE d.eliminado = :eliminado")})
public class DestinoComputador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_DESTINO_COMPUTADOR")
    @SequenceGenerator(name = "GSQ_DESTINO_COMPUTADOR", schema = "MKS_IFIPS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_DESTINO_COMPUTADOR")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoDestinoComputador")
    private Collection<Computador> computadorCollection;

    public DestinoComputador() {
    }

    public DestinoComputador(Long codigo) {
        this.codigo = codigo;
    }

    public DestinoComputador(Long codigo, String nombre, char eliminado) {
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
    public Collection<Computador> getComputadorCollection() {
        return computadorCollection;
    }

    public void setComputadorCollection(Collection<Computador> computadorCollection) {
        this.computadorCollection = computadorCollection;
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
        if (!(object instanceof DestinoComputador)) {
            return false;
        }
        DestinoComputador other = (DestinoComputador) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.DestinoComputador[ codigo=" + codigo + " ]";
    }

}
