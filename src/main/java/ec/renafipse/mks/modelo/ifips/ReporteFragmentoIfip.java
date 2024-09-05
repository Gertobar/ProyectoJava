/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author santiago
 */
@Entity
@Table(name = "MKS_IFIPS.REPORTE_FRAGMENTO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteFragmentoIfip.findAll", query = "SELECT r FROM ReporteFragmentoIfip r"),
    @NamedQuery(name = "ReporteFragmentoIfip.findByCodigoReporte", query = "SELECT r FROM ReporteFragmentoIfip r WHERE r.reporteFragmentoIfipPK.codigoReporte = :codigoReporte"),
    @NamedQuery(name = "ReporteFragmentoIfip.findByCodigoFragmento", query = "SELECT r FROM ReporteFragmentoIfip r WHERE r.reporteFragmentoIfipPK.codigoFragmento = :codigoFragmento"),
    @NamedQuery(name = "ReporteFragmentoIfip.findByCodigoIfip", query = "SELECT r FROM ReporteFragmentoIfip r WHERE r.reporteFragmentoIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ReporteFragmentoIfip.findByTexto", query = "SELECT r FROM ReporteFragmentoIfip r WHERE r.texto = :texto"),
    @NamedQuery(name = "ReporteFragmentoIfip.findByIfipRepFragmento",query = "SELECT r FROM ReporteFragmentoIfip r WHERE r.reporteFragmentoIfipPK.codigoReporte = :codigoReporte AND r.reporteFragmentoIfipPK.codigoFragmento = :codigoFragmento AND r.reporteFragmentoIfipPK.codigoIfip = :codigoIfip")
})

//PERSONALIZADO
    
public class ReporteFragmentoIfip implements Serializable {
      public static final String findByCodIfipRepFrag="ReporteFragmentoIfip.findByIfipRepFragmento";
      public static final String findByCodigoFragmento="ReporteFragmentoIfip.findByCodigoFragmento";
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReporteFragmentoIfipPK reporteFragmentoIfipPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "TEXTO")
    private String texto;
    @JoinColumn(name = "CODIGO_REPORTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reporte reporte;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip ifip;
    @JoinColumn(name = "CODIGO_FRAGMENTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FragmentoReporte fragmentoReporte;

    public ReporteFragmentoIfip() {
    }

    public ReporteFragmentoIfip(ReporteFragmentoIfipPK reporteFragmentoIfipPK) {
        this.reporteFragmentoIfipPK = reporteFragmentoIfipPK;
    }

    public ReporteFragmentoIfip(ReporteFragmentoIfipPK reporteFragmentoIfipPK, String texto) {
        this.reporteFragmentoIfipPK = reporteFragmentoIfipPK;
        this.texto = texto;
    }

    public ReporteFragmentoIfip(long codigoReporte, long codigoFragmento, long codigoIfip) {
        this.reporteFragmentoIfipPK = new ReporteFragmentoIfipPK(codigoReporte, codigoFragmento, codigoIfip);
    }

    public ReporteFragmentoIfipPK getReporteFragmentoIfipPK() {
        return reporteFragmentoIfipPK;
    }

    public void setReporteFragmentoIfipPK(ReporteFragmentoIfipPK reporteFragmentoIfipPK) {
        this.reporteFragmentoIfipPK = reporteFragmentoIfipPK;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public Ifip getIfip() {
        return ifip;
    }

    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

    public FragmentoReporte getFragmentoReporte() {
        return fragmentoReporte;
    }

    public void setFragmentoReporte(FragmentoReporte fragmentoReporte) {
        this.fragmentoReporte = fragmentoReporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reporteFragmentoIfipPK != null ? reporteFragmentoIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteFragmentoIfip)) {
            return false;
        }
        ReporteFragmentoIfip other = (ReporteFragmentoIfip) object;
        if ((this.reporteFragmentoIfipPK == null && other.reporteFragmentoIfipPK != null) || (this.reporteFragmentoIfipPK != null && !this.reporteFragmentoIfipPK.equals(other.reporteFragmentoIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.ReporteFragmentoIfip[ reporteFragmentoIfipPK=" + reporteFragmentoIfipPK + " ]";
    }
    
}
