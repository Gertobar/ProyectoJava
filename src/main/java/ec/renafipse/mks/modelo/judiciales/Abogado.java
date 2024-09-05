/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.judiciales;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_JUDICIALES.ABOGADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Abogado.findAll", query = "SELECT a FROM Abogado a"),
    @NamedQuery(name = "Abogado.findByCodigo", query = "SELECT a FROM Abogado a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Abogado.findByCodigoProveedor", query = "SELECT a FROM Abogado a WHERE a.codigoProveedor = :codigoProveedor"),
    @NamedQuery(name = "Abogado.findByCodigoIfip", query = "SELECT a FROM Abogado a WHERE a.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Abogado.findByFechaRegistro", query = "SELECT a FROM Abogado a WHERE a.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Abogado.findByRegistradoPor", query = "SELECT a FROM Abogado a WHERE a.registradoPor = :registradoPor"),
    @NamedQuery(name = "Abogado.findByEliminado", query = "SELECT a FROM Abogado a WHERE a.eliminado = :eliminado")})
public class Abogado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_ABOGADO")
    @SequenceGenerator(name = "GSQ_ABOGADO", schema = "MKS_JUDICIALES", allocationSize = 0, sequenceName = "SEQ_ABOGADO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROVEEDOR")
    private long codigoProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoAbogado")
    private Collection<PagareAbogado> pagareAbogadoCollection;

    public Abogado() {
    }

    public Abogado(Long codigo) {
        this.codigo = codigo;
    }

    public Abogado(Long codigo, long codigoProveedor, long codigoIfip, Date fechaRegistro, long registradoPor, String eliminado) {
        this.codigo = codigo;
        this.codigoProveedor = codigoProveedor;
        this.codigoIfip = codigoIfip;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(long codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<PagareAbogado> getPagareAbogadoCollection() {
        return pagareAbogadoCollection;
    }

    public void setPagareAbogadoCollection(Collection<PagareAbogado> pagareAbogadoCollection) {
        this.pagareAbogadoCollection = pagareAbogadoCollection;
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
        if (!(object instanceof Abogado)) {
            return false;
        }
        Abogado other = (Abogado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.judiciales.Abogado[ codigo=" + codigo + " ]";
    }

}
