/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TIPO_RUBRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRubro.findAll", query = "SELECT t FROM TipoRubro t"),
    @NamedQuery(name = "TipoRubro.findByCodio", query = "SELECT t FROM TipoRubro t WHERE t.codio = :codio"),
    @NamedQuery(name = "TipoRubro.findByNombre", query = "SELECT t FROM TipoRubro t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoRubro.findBySiglas", query = "SELECT t FROM TipoRubro t WHERE t.siglas = :siglas"),
    @NamedQuery(name = "TipoRubro.findByDescripcion", query = "SELECT t FROM TipoRubro t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoRubro.findByEliminado", query = "SELECT t FROM TipoRubro t WHERE t.eliminado = :eliminado")})
public class TipoRubro implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "TipoRubro.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_RUBRO")
    @SequenceGenerator(name = "GSQ_TIPO_RUBRO", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_TIPO_RUBRO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoRubro", fetch = FetchType.LAZY)
    private Collection<TipoRubroProCreMonIfi> tipoRubroProCreMonIfiCollection;

    public TipoRubro() {
    }

    public TipoRubro(Long codio) {
        this.codio = codio;
    }

    public TipoRubro(Long codio, String nombre, String siglas, String descripcion, char eliminado) {
        this.codio = codio;
        this.nombre = nombre;
        this.siglas = siglas;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    public Long getCodio() {
        return codio;
    }

    public void setCodio(Long codio) {
        this.codio = codio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
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
    public Collection<TipoRubroProCreMonIfi> getTipoRubroProCreMonIfiCollection() {
        return tipoRubroProCreMonIfiCollection;
    }

    public void setTipoRubroProCreMonIfiCollection(Collection<TipoRubroProCreMonIfi> tipoRubroProCreMonIfiCollection) {
        this.tipoRubroProCreMonIfiCollection = tipoRubroProCreMonIfiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codio != null ? codio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRubro)) {
            return false;
        }
        TipoRubro other = (TipoRubro) object;
        if ((this.codio == null && other.codio != null) || (this.codio != null && !this.codio.equals(other.codio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoRubro[ codio=" + codio + " ]";
    }
    
}
