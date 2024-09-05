/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import ec.renafipse.mks.modelo.contables.Comprobante;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.COMPRABANTE_RETENCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComprabanteRetencion.findAll", query = "SELECT c FROM ComprabanteRetencion c"),
    @NamedQuery(name = "ComprabanteRetencion.findByCodigoComprobante", query = "SELECT c FROM ComprabanteRetencion c WHERE c.codigoComprobante = :codigoComprobante")})
public class ComprabanteRetencion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPROBANTE")
    private Long codigoComprobante;
    @JoinColumn(name = "CODIGO_RETENCION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Retencion codigoRetencion;
    @JoinColumn(name = "CODIGO_COMPROBANTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Comprobante comprobante;

    public ComprabanteRetencion() {
    }

    public ComprabanteRetencion(Long codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public Long getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(Long codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public Retencion getCodigoRetencion() {
        return codigoRetencion;
    }

    public void setCodigoRetencion(Retencion codigoRetencion) {
        this.codigoRetencion = codigoRetencion;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoComprobante != null ? codigoComprobante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComprabanteRetencion)) {
            return false;
        }
        ComprabanteRetencion other = (ComprabanteRetencion) object;
        if ((this.codigoComprobante == null && other.codigoComprobante != null) || (this.codigoComprobante != null && !this.codigoComprobante.equals(other.codigoComprobante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.contables.ComprabanteRetencion[ codigoComprobante=" + codigoComprobante + " ]";
    }
    
}
