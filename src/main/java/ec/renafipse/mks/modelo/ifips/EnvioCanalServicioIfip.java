/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_IFIPS.ENVIO_CANAL_SERVICIO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnvioCanalServicioIfip.findByCodigo", query = "SELECT e FROM EnvioCanalServicioIfip e WHERE e.codigo = :codigo")
    ,@NamedQuery(name = "EnvioCanalServicioIfip.findByCodigoServicioIfipCodigoCanalServicioIfip", query = "SELECT e FROM EnvioCanalServicioIfip e WHERE e.codigoServicioIfip.codigo = :codigoServicioIfip AND e.codigoCanalServicioIfip.codigo = :codigoCanalServicioIfip")
})
public class EnvioCanalServicioIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_SERVICIO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ServicioIfip codigoServicioIfip;
    @JoinColumn(name = "CODIGO_CANAL_SERVICIO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private CanalServicioIfip codigoCanalServicioIfip;

    public EnvioCanalServicioIfip() {
    }

    public EnvioCanalServicioIfip(Long codigo) {
        this.codigo = codigo;
    }

    public EnvioCanalServicioIfip(Long codigo, String observaciones, char eliminado) {
        this.codigo = codigo;
        this.observaciones = observaciones;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public ServicioIfip getCodigoServicioIfip() {
        return codigoServicioIfip;
    }

    public void setCodigoServicioIfip(ServicioIfip codigoServicioIfip) {
        this.codigoServicioIfip = codigoServicioIfip;
    }

    public CanalServicioIfip getCodigoCanalServicioIfip() {
        return codigoCanalServicioIfip;
    }

    public void setCodigoCanalServicioIfip(CanalServicioIfip codigoCanalServicioIfip) {
        this.codigoCanalServicioIfip = codigoCanalServicioIfip;
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
        if (!(object instanceof EnvioCanalServicioIfip)) {
            return false;
        }
        EnvioCanalServicioIfip other = (EnvioCanalServicioIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.EnvioCanalServicioIfip[ codigo=" + codigo + " ]";
    }
    
}
