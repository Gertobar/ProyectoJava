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
import javax.persistence.NamedNativeQuery;
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
@Table(name = "MKS_IFIPS.CANAL_SERVICIO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CanalServicioIfip.findByCodigo", query = "SELECT c FROM CanalServicioIfip c WHERE c.codigo = :codigo")
})
@NamedNativeQuery(
        name = "CanalServicioIfip.findByEnvioIfipEliminado", query = "SELECT c.codigo, c.canal, c.descripcion FROM mks_ifips.servicio_ifip s, mks_ifips.envio_canal_servicio_ifip e, mks_ifips.canal_servicio_ifip c WHERE s.codigo_ifip = :codigoIfip AND s.eliminado = :eliminado AND s.codigo = e.codigo_servicio_ifip AND e.eliminado = :eliminado AND e.codigo_canal_servicio_ifip = c.codigo", resultClass=CanalServicioIfip.class
)
public class CanalServicioIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CANAL")
    private String canal;
    @Size(max = 800)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCanalServicioIfip")
    private Collection<EnvioCanalServicioIfip> envioCanalServicioIfipCollection;

    public CanalServicioIfip() {
    }

    public CanalServicioIfip(String codigo) {
        this.codigo = codigo;
    }

    public CanalServicioIfip(String codigo, String canal) {
        this.codigo = codigo;
        this.canal = canal;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<EnvioCanalServicioIfip> getEnvioCanalServicioIfipCollection() {
        return envioCanalServicioIfipCollection;
    }

    public void setEnvioCanalServicioIfipCollection(Collection<EnvioCanalServicioIfip> envioCanalServicioIfipCollection) {
        this.envioCanalServicioIfipCollection = envioCanalServicioIfipCollection;
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
        if (!(object instanceof CanalServicioIfip)) {
            return false;
        }
        CanalServicioIfip other = (CanalServicioIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.CanalServicioIfip[ codigo=" + codigo + " ]";
    }
    
}
