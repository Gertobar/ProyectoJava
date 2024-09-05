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
@Table(name = "MKS_CREDITOS.TIPO_GARANTIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoGarantia.findAll", query = "SELECT t FROM TipoGarantia t"),
    @NamedQuery(name = "TipoGarantia.findByCodigo", query = "SELECT t FROM TipoGarantia t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoGarantia.findByNombre", query = "SELECT t FROM TipoGarantia t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoGarantia.findByEliminado", query = "SELECT t FROM TipoGarantia t WHERE t.eliminado = :eliminado")})
public class TipoGarantia implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "TipoGarantia.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_GARANTIA")
    @SequenceGenerator(name = "GSQ_TIPO_GARANTIA", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_TIPO_GARANTIA")
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
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoGarantia", fetch = FetchType.LAZY)
    private Collection<TipoGarantiaProCreMonIfi> tipoGarantiaProCreMonIfiCollection;

    public TipoGarantia() {
    }

    public TipoGarantia(Long codigo) {
        this.codigo = codigo;
    }

    public TipoGarantia(Long codigo, String nombre, char eliminado) {
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
    public Collection<TipoGarantiaProCreMonIfi> getTipoGarantiaProCreMonIfiCollection() {
        return tipoGarantiaProCreMonIfiCollection;
    }

    public void setTipoGarantiaProCreMonIfiCollection(Collection<TipoGarantiaProCreMonIfi> tipoGarantiaProCreMonIfiCollection) {
        this.tipoGarantiaProCreMonIfiCollection = tipoGarantiaProCreMonIfiCollection;
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
        if (!(object instanceof TipoGarantia)) {
            return false;
        }
        TipoGarantia other = (TipoGarantia) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoGarantia[ codigo=" + codigo + " ]";
    }
    
}
