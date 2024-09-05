/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author v.astudillo
 */
@Embeddable
public class DocumentoRetencionDpfPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_RETENCION")
    private long codigoRetencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_DOCUMENTO")
    private long codigoDocumento;

    public DocumentoRetencionDpfPK() {
    }

    public DocumentoRetencionDpfPK(long codigoRetencion, long codigoDocumento) {
        this.codigoRetencion = codigoRetencion;
        this.codigoDocumento = codigoDocumento;
    }

    public long getCodigoRetencion() {
        return codigoRetencion;
    }

    public void setCodigoRetencion(long codigoRetencion) {
        this.codigoRetencion = codigoRetencion;
    }

    public long getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(long codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoRetencion;
        hash += (int) codigoDocumento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoRetencionDpfPK)) {
            return false;
        }
        DocumentoRetencionDpfPK other = (DocumentoRetencionDpfPK) object;
        if (this.codigoRetencion != other.codigoRetencion) {
            return false;
        }
        if (this.codigoDocumento != other.codigoDocumento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.DocumentoRetencionDpfPK[ codigoRetencion=" + codigoRetencion + ", codigoDocumento=" + codigoDocumento + " ]";
    }
    
}
