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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_IFIPS.SERVICIO_FINANCIERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioFinanciero.findAll", query = "SELECT s FROM ServicioFinanciero s")})
public class ServicioFinanciero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CODIGO_TIPO_SERVICIO")
    private String codigoTipoServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CODIGO_SERVICIO")
    private String codigoServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SERVICIO")
    private String servicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION_IDENTIFICADOR")
    private String descripcionIdentificador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoServicioFinanciero")
    private Collection<ServicioFinancieroTipoCanal> servicioFinancieroTipoCanalCollection;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Ifip codigoIfip;

    public ServicioFinanciero() {
    }

    public ServicioFinanciero(Long codigo) {
        this.codigo = codigo;
    }

    public ServicioFinanciero(Long codigo, String codigoTipoServicio, String codigoServicio, String servicio, String descripcion, String descripcionIdentificador) {
        this.codigo = codigo;
        this.codigoTipoServicio = codigoTipoServicio;
        this.codigoServicio = codigoServicio;
        this.servicio = servicio;
        this.descripcion = descripcion;
        this.descripcionIdentificador = descripcionIdentificador;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCodigoTipoServicio() {
        return codigoTipoServicio;
    }

    public void setCodigoTipoServicio(String codigoTipoServicio) {
        this.codigoTipoServicio = codigoTipoServicio;
    }

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionIdentificador() {
        return descripcionIdentificador;
    }

    public void setDescripcionIdentificador(String descripcionIdentificador) {
        this.descripcionIdentificador = descripcionIdentificador;
    }

    @XmlTransient
    public Collection<ServicioFinancieroTipoCanal> getServicioFinancieroTipoCanalCollection() {
        return servicioFinancieroTipoCanalCollection;
    }

    public void setServicioFinancieroTipoCanalCollection(Collection<ServicioFinancieroTipoCanal> servicioFinancieroTipoCanalCollection) {
        this.servicioFinancieroTipoCanalCollection = servicioFinancieroTipoCanalCollection;
    }

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
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
        if (!(object instanceof ServicioFinanciero)) {
            return false;
        }
        ServicioFinanciero other = (ServicioFinanciero) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.ServicioFinanciero[ codigo=" + codigo + " ]";
    }
    
}
