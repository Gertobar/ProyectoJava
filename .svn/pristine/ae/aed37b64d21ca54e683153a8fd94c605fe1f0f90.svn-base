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
@Table(name = "MKS_AHORROS.TIPO_FIRMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFirma.findAll", query = "SELECT t FROM TipoFirma t"),
    @NamedQuery(name = "TipoFirma.findByCodigo", query = "SELECT t FROM TipoFirma t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoFirma.findByNombre", query = "SELECT t FROM TipoFirma t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoFirma.findByTieneFirmas", query = "SELECT t FROM TipoFirma t WHERE t.tieneFirmas = :tieneFirmas"),
    @NamedQuery(name = "TipoFirma.findByQuienFirma", query = "SELECT t FROM TipoFirma t WHERE t.quienFirma = :quienFirma"),
    @NamedQuery(name = "TipoFirma.findByEliminado", query = "SELECT t FROM TipoFirma t WHERE t.eliminado = :eliminado")})
public class TipoFirma implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "TipoFirma.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_FIRMA")
    @SequenceGenerator(name = "GSQ_TIPO_FIRMA", schema = "MKS_AHORROS", allocationSize = 0, sequenceName = "SEQ_TIPO_FIRMA")
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
    @Column(name = "TIENE_FIRMAS")
    private char tieneFirmas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUIEN_FIRMA")
    private char quienFirma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoFirma")
    private Collection<Cuenta> cuentaCollection;

    public TipoFirma() {
    }

    public TipoFirma(Long codigo) {
        this.codigo = codigo;
    }

    public TipoFirma(Long codigo, String nombre, char tieneFirmas, char quienFirma, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tieneFirmas = tieneFirmas;
        this.quienFirma = quienFirma;
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

    public char getTieneFirmas() {
        return tieneFirmas;
    }

    public void setTieneFirmas(char tieneFirmas) {
        this.tieneFirmas = tieneFirmas;
    }

    public char getQuienFirma() {
        return quienFirma;
    }

    public void setQuienFirma(char quienFirma) {
        this.quienFirma = quienFirma;
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
        if (!(object instanceof TipoFirma)) {
            return false;
        }
        TipoFirma other = (TipoFirma) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.TipoFirma[ codigo=" + codigo + " ]";
    }
    
}
