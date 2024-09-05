/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos.lineacredito;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_CREDITOS.LINEA_CREDITO_SOL_ENV_COM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaCreditoSolEnvCom.findAll", query = "SELECT l FROM LineaCreditoSolEnvCom l"),
    @NamedQuery(name = "LineaCreditoSolEnvCom.findByCodigoLineaCreditoSol", query = "SELECT l FROM LineaCreditoSolEnvCom l WHERE l.codigoLineaCreditoSol = :codigoLineaCreditoSol"),
    @NamedQuery(name = "LineaCreditoSolEnvCom.findByObservaciones", query = "SELECT l FROM LineaCreditoSolEnvCom l WHERE l.observaciones = :observaciones")})
public class LineaCreditoSolEnvCom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_LINEA_CREDITO_SOL")
    private Long codigoLineaCreditoSol;
    @Size(max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "CODIGO_LINEA_CREDITO_SOL", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private LineaCreditoSolicitud lineaCreditoSolicitud;

    public LineaCreditoSolEnvCom() {
    }

    public LineaCreditoSolEnvCom(Long codigoLineaCreditoSol) {
        this.codigoLineaCreditoSol = codigoLineaCreditoSol;
    }
    
    public LineaCreditoSolEnvCom(Long codigoLineaCreditoSol, String observaciones) {
        this.codigoLineaCreditoSol = codigoLineaCreditoSol;
        this.observaciones = observaciones;
    }
    
    public Long getCodigoLineaCreditoSol() {
        return codigoLineaCreditoSol;
    }

    public void setCodigoLineaCreditoSol(Long codigoLineaCreditoSol) {
        this.codigoLineaCreditoSol = codigoLineaCreditoSol;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LineaCreditoSolicitud getLineaCreditoSolicitud() {
        return lineaCreditoSolicitud;
    }

    public void setLineaCreditoSolicitud(LineaCreditoSolicitud lineaCreditoSolicitud) {
        this.lineaCreditoSolicitud = lineaCreditoSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoLineaCreditoSol != null ? codigoLineaCreditoSol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineaCreditoSolEnvCom)) {
            return false;
        }
        LineaCreditoSolEnvCom other = (LineaCreditoSolEnvCom) object;
        if ((this.codigoLineaCreditoSol == null && other.codigoLineaCreditoSol != null) || (this.codigoLineaCreditoSol != null && !this.codigoLineaCreditoSol.equals(other.codigoLineaCreditoSol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolEnvCom[ codigoLineaCreditoSol=" + codigoLineaCreditoSol + " ]";
    }
    
}
