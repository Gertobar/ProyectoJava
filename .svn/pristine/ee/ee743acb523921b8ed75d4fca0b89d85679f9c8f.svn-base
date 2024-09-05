/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vastudillo
 */
@Embeddable
public class DocumentoContratoDpfPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CONTRATO")
    private long codigoContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_DOCUMENTO")
    private long codigoDocumento;

    public DocumentoContratoDpfPK() {
    }

    public DocumentoContratoDpfPK(long codigoContrato, long codigoIfip, long codigoDocumento) {
        this.codigoContrato = codigoContrato;
        this.codigoIfip = codigoIfip;
        this.codigoDocumento = codigoDocumento;
    }

    public long getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(long codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
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
        hash += (int) codigoContrato;
        hash += (int) codigoIfip;
        hash += (int) codigoDocumento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoContratoDpfPK)) {
            return false;
        }
        DocumentoContratoDpfPK other = (DocumentoContratoDpfPK) object;
        if (this.codigoContrato != other.codigoContrato) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        if (this.codigoDocumento != other.codigoDocumento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.DocumentoContratoDpfPK[ codigoContrato=" + codigoContrato + ", codigoIfip=" + codigoIfip + ", codigoDocumento=" + codigoDocumento + " ]";
    }
    
}
