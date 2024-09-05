/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.judiciales;

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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_JUDICIALES.DOCUMENTO_CREDITO_ENTREGADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentoCreditoEntregado.findAll", query = "SELECT d FROM DocumentoCreditoEntregado d"),
    @NamedQuery(name = "DocumentoCreditoEntregado.findByCodigo", query = "SELECT d FROM DocumentoCreditoEntregado d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "DocumentoCreditoEntregado.findByObservacion", query = "SELECT d FROM DocumentoCreditoEntregado d WHERE d.observacion = :observacion"),
    @NamedQuery(name = "DocumentoCreditoEntregado.findByEliminado", query = "SELECT d FROM DocumentoCreditoEntregado d WHERE d.eliminado = :eliminado")})
public class DocumentoCreditoEntregado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Size(max = 500)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @JoinColumn(name = "CODIGO_TIPO_DOCUMENTO_CREDITO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoDocumentoCredito codigoTipoDocumentoCredito;
    @JoinColumn(name = "CODIGO_PAGARE_ABOGADO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private PagareAbogado codigoPagareAbogado;

    public DocumentoCreditoEntregado() {
    }

    public DocumentoCreditoEntregado(Long codigo) {
        this.codigo = codigo;
    }

    public DocumentoCreditoEntregado(Long codigo, String eliminado) {
        this.codigo = codigo;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public TipoDocumentoCredito getCodigoTipoDocumentoCredito() {
        return codigoTipoDocumentoCredito;
    }

    public void setCodigoTipoDocumentoCredito(TipoDocumentoCredito codigoTipoDocumentoCredito) {
        this.codigoTipoDocumentoCredito = codigoTipoDocumentoCredito;
    }

    public PagareAbogado getCodigoPagareAbogado() {
        return codigoPagareAbogado;
    }

    public void setCodigoPagareAbogado(PagareAbogado codigoPagareAbogado) {
        this.codigoPagareAbogado = codigoPagareAbogado;
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
        if (!(object instanceof DocumentoCreditoEntregado)) {
            return false;
        }
        DocumentoCreditoEntregado other = (DocumentoCreditoEntregado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.judiciales.DocumentoCreditoEntregado[ codigo=" + codigo + " ]";
    }
    
}
