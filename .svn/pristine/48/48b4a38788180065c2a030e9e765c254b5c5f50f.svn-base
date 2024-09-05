/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.reportes;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_REPORTES.PARAMETRO_ENTRADA_REPORTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroEntradaReporte.findAll", query = "SELECT p FROM ParametroEntradaReporte p"),
    @NamedQuery(name = "ParametroEntradaReporte.findByCodigo", query = "SELECT p FROM ParametroEntradaReporte p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ParametroEntradaReporte.findByOrden", query = "SELECT p FROM ParametroEntradaReporte p WHERE p.orden = :orden"),
    //PERSONALIZADOS
    @NamedQuery(name = "ParametroEntradaReporte.findByCodigoReporte", query = "SELECT p FROM ParametroEntradaReporte p WHERE p.codigoReporte.codigo = :codigoReporte ORDER BY p.orden")
})
public class ParametroEntradaReporte implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoReporte = "ParametroEntradaReporte.findByCodigoReporte";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN")
    private int orden;
    @JoinColumn(name = "CODIGO_REPORTE", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ReporteDetalle codigoReporte;
    @JoinColumn(name = "CODIGO_PARAMETRO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ParametroEntrada codigoParametro;

    public ParametroEntradaReporte() {
    }

    public ParametroEntradaReporte(Long codigo) {
        this.codigo = codigo;
    }

    public ParametroEntradaReporte(Long codigo, int orden) {
        this.codigo = codigo;
        this.orden = orden;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public ReporteDetalle getCodigoReporte() {
        return codigoReporte;
    }

    public void setCodigoReporte(ReporteDetalle codigoReporte) {
        this.codigoReporte = codigoReporte;
    }

    public ParametroEntrada getCodigoParametro() {
        return codigoParametro;
    }

    public void setCodigoParametro(ParametroEntrada codigoParametro) {
        this.codigoParametro = codigoParametro;
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
        if (!(object instanceof ParametroEntradaReporte)) {
            return false;
        }
        ParametroEntradaReporte other = (ParametroEntradaReporte) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.reportes.ParametroEntradaReporte[ codigo=" + codigo + " ]";
    }
    
}
