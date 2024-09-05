/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos.lineacredito;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_CREDITOS.LINEA_CREDITO_SOL_INF_TEC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaCreditoSolicitudInfTec.findAll", query = "SELECT l FROM LineaCreditoSolicitudInfTec l"),
    @NamedQuery(name = "LineaCreditoSolicitudInfTec.findByCodigoLineaCreditoSol", query = "SELECT l FROM LineaCreditoSolicitudInfTec l WHERE l.codigoLineaCreditoSol = :codigoLineaCreditoSol"),
    @NamedQuery(name = "LineaCreditoSolicitudInfTec.findByFactible", query = "SELECT l FROM LineaCreditoSolicitudInfTec l WHERE l.factible = :factible"),
    @NamedQuery(name = "LineaCreditoSolicitudInfTec.findByRecomendacion", query = "SELECT l FROM LineaCreditoSolicitudInfTec l WHERE l.recomendacion = :recomendacion"),
    @NamedQuery(name = "LineaCreditoSolicitudInfTec.findByTotalIngresos", query = "SELECT l FROM LineaCreditoSolicitudInfTec l WHERE l.totalIngresos = :totalIngresos"),
    @NamedQuery(name = "LineaCreditoSolicitudInfTec.findByTotalEgresos", query = "SELECT l FROM LineaCreditoSolicitudInfTec l WHERE l.totalEgresos = :totalEgresos"),
    @NamedQuery(name = "LineaCreditoSolicitudInfTec.findByTotalFlujoCaja", query = "SELECT l FROM LineaCreditoSolicitudInfTec l WHERE l.totalFlujoCaja = :totalFlujoCaja"),
    @NamedQuery(name = "LineaCreditoSolicitudInfTec.findByTotalActivos", query = "SELECT l FROM LineaCreditoSolicitudInfTec l WHERE l.totalActivos = :totalActivos"),
    @NamedQuery(name = "LineaCreditoSolicitudInfTec.findByTotalPasivos", query = "SELECT l FROM LineaCreditoSolicitudInfTec l WHERE l.totalPasivos = :totalPasivos"),
    @NamedQuery(name = "LineaCreditoSolicitudInfTec.findByTotalPatrimonio", query = "SELECT l FROM LineaCreditoSolicitudInfTec l WHERE l.totalPatrimonio = :totalPatrimonio"),
    @NamedQuery(name = "LineaCreditoSolicitudInfTec.findByCobertura", query = "SELECT l FROM LineaCreditoSolicitudInfTec l WHERE l.cobertura = :cobertura")})
public class LineaCreditoSolicitudInfTec implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_LINEA_CREDITO_SOL")
    private Long codigoLineaCreditoSol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACTIBLE")
    private char factible;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "RECOMENDACION")
    private String recomendacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_INGRESOS")
    private BigDecimal totalIngresos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_EGRESOS")
    private BigDecimal totalEgresos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_FLUJO_CAJA")
    private BigDecimal totalFlujoCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_ACTIVOS")
    private BigDecimal totalActivos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PASIVOS")
    private BigDecimal totalPasivos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PATRIMONIO")
    private BigDecimal totalPatrimonio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COBERTURA")
    private BigDecimal cobertura;
    @JoinColumn(name = "CODIGO_LINEA_CREDITO_SOL", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private LineaCreditoSolicitud lineaCreditoSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;

    public LineaCreditoSolicitudInfTec() {
    }

    public LineaCreditoSolicitudInfTec(Long codigoLineaCreditoSol) {
        this.codigoLineaCreditoSol = codigoLineaCreditoSol;
    }

    public LineaCreditoSolicitudInfTec(Long codigoLineaCreditoSol, char factible, String recomendacion, BigDecimal totalIngresos, BigDecimal totalEgresos, BigDecimal totalFlujoCaja, BigDecimal totalActivos, BigDecimal totalPasivos, BigDecimal totalPatrimonio, BigDecimal cobertura, Date fechaRegistro, Long codigoUsuario) {
        this.codigoLineaCreditoSol = codigoLineaCreditoSol;
        this.factible = factible;
        this.recomendacion = recomendacion;
        this.totalIngresos = totalIngresos;
        this.totalEgresos = totalEgresos;
        this.totalFlujoCaja = totalFlujoCaja;
        this.totalActivos = totalActivos;
        this.totalPasivos = totalPasivos;
        this.totalPatrimonio = totalPatrimonio;
        this.cobertura = cobertura;
        this.fechaRegistro = fechaRegistro;
        this.codigoUsuario = codigoUsuario;
    }

    public Long getCodigoLineaCreditoSol() {
        return codigoLineaCreditoSol;
    }

    public void setCodigoLineaCreditoSol(Long codigoLineaCreditoSol) {
        this.codigoLineaCreditoSol = codigoLineaCreditoSol;
    }

    public char getFactible() {
        return factible;
    }

    public void setFactible(char factible) {
        this.factible = factible;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public BigDecimal getTotalEgresos() {
        return totalEgresos;
    }

    public void setTotalEgresos(BigDecimal totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

    public BigDecimal getTotalFlujoCaja() {
        return totalFlujoCaja;
    }

    public void setTotalFlujoCaja(BigDecimal totalFlujoCaja) {
        this.totalFlujoCaja = totalFlujoCaja;
    }

    public BigDecimal getTotalActivos() {
        return totalActivos;
    }

    public void setTotalActivos(BigDecimal totalActivos) {
        this.totalActivos = totalActivos;
    }

    public BigDecimal getTotalPasivos() {
        return totalPasivos;
    }

    public void setTotalPasivos(BigDecimal totalPasivos) {
        this.totalPasivos = totalPasivos;
    }

    public BigDecimal getTotalPatrimonio() {
        return totalPatrimonio;
    }

    public void setTotalPatrimonio(BigDecimal totalPatrimonio) {
        this.totalPatrimonio = totalPatrimonio;
    }

    public BigDecimal getCobertura() {
        return cobertura;
    }

    public void setCobertura(BigDecimal cobertura) {
        this.cobertura = cobertura;
    }

    public LineaCreditoSolicitud getLineaCreditoSolicitud() {
        return lineaCreditoSolicitud;
    }

    public void setLineaCreditoSolicitud(LineaCreditoSolicitud lineaCreditoSolicitud) {
        this.lineaCreditoSolicitud = lineaCreditoSolicitud;
    }
    
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
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
        if (!(object instanceof LineaCreditoSolicitudInfTec)) {
            return false;
        }
        LineaCreditoSolicitudInfTec other = (LineaCreditoSolicitudInfTec) object;
        if ((this.codigoLineaCreditoSol == null && other.codigoLineaCreditoSol != null) || (this.codigoLineaCreditoSol != null && !this.codigoLineaCreditoSol.equals(other.codigoLineaCreditoSol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitudInfTec[ codigoLineaCreditoSol=" + codigoLineaCreditoSol + " ]";
    }
    
}
