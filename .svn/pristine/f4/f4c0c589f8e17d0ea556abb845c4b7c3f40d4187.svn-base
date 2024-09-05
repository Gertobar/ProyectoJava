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
@Table(name = "MKS_IFIPS.TIPO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoIfip.findAll", query = "SELECT t FROM TipoIfip t"),
    @NamedQuery(name = "TipoIfip.findByCodigo", query = "SELECT t FROM TipoIfip t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoIfip.findByNombre", query = "SELECT t FROM TipoIfip t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoIfip.findBySiglas", query = "SELECT t FROM TipoIfip t WHERE t.siglas = :siglas"),
    @NamedQuery(name = "TipoIfip.findByAgencias", query = "SELECT t FROM TipoIfip t WHERE t.agencias = :agencias"),
    @NamedQuery(name = "TipoIfip.findByVentanillas", query = "SELECT t FROM TipoIfip t WHERE t.ventanillas = :ventanillas"),
    @NamedQuery(name = "TipoIfip.findByEliminado", query = "SELECT t FROM TipoIfip t WHERE t.eliminado = :eliminado")})
public class TipoIfip implements Serializable {
    private static final long serialVersionUID = 1L;
     @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_IFIP")
    @SequenceGenerator(name = "GSQ_TIPO_IFIP", schema = "MKS_IFIPS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_TIPO_IFIP")
    @Column(name = "CODIGO")
    private Long codigo;
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
    @Column(name = "AGENCIAS")
    private char agencias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VENTANILLAS")
    private char ventanillas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoIfip")
    private Collection<Ifip> ifipCollection;

    public TipoIfip() {
    }

    public TipoIfip(Long codigo) {
        this.codigo = codigo;
    }

    public TipoIfip(Long codigo, String nombre, String siglas, char agencias, char ventanillas, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
        this.agencias = agencias;
        this.ventanillas = ventanillas;
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

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public char getAgencias() {
        return agencias;
    }

    public void setAgencias(char agencias) {
        this.agencias = agencias;
    }

    public char getVentanillas() {
        return ventanillas;
    }

    public void setVentanillas(char ventanillas) {
        this.ventanillas = ventanillas;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Ifip> getIfipCollection() {
        return ifipCollection;
    }

    public void setIfipCollection(Collection<Ifip> ifipCollection) {
        this.ifipCollection = ifipCollection;
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
        if (!(object instanceof TipoIfip)) {
            return false;
        }
        TipoIfip other = (TipoIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.TipoIfip[ codigo=" + codigo + " ]";
    }
    
}
