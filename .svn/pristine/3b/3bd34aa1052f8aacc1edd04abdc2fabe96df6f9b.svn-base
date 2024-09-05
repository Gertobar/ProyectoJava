/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@Entity
@Table(name = "MKS_CREDITOS.INFORME_TECNICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformeTecnico.findAll", query = "SELECT i FROM InformeTecnico i"),
    @NamedQuery(name = "InformeTecnico.findByNumeroSolicitud", query = "SELECT i FROM InformeTecnico i WHERE i.informeTecnicoPK.numeroSolicitud = :numeroSolicitud"),
    @NamedQuery(name = "InformeTecnico.findByCodigoIfip", query = "SELECT i FROM InformeTecnico i WHERE i.informeTecnicoPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "InformeTecnico.findByFechaRegistro", query = "SELECT i FROM InformeTecnico i WHERE i.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "InformeTecnico.findByRegistradoPor", query = "SELECT i FROM InformeTecnico i WHERE i.registradoPor = :registradoPor"),
    @NamedQuery(name = "InformeTecnico.findByFactible", query = "SELECT i FROM InformeTecnico i WHERE i.factible = :factible"),
    @NamedQuery(name = "InformeTecnico.findByDetalle", query = "SELECT i FROM InformeTecnico i WHERE i.detalle = :detalle"),
    @NamedQuery(name = "InformeTecnico.findByTotalIngresos", query = "SELECT i FROM InformeTecnico i WHERE i.totalIngresos = :totalIngresos"),
    @NamedQuery(name = "InformeTecnico.findByTotalEgresos", query = "SELECT i FROM InformeTecnico i WHERE i.totalEgresos = :totalEgresos"),
    @NamedQuery(name = "InformeTecnico.findByTotalFlujoCaja", query = "SELECT i FROM InformeTecnico i WHERE i.totalFlujoCaja = :totalFlujoCaja"),
    @NamedQuery(name = "InformeTecnico.findByCobertura", query = "SELECT i FROM InformeTecnico i WHERE i.cobertura = :cobertura"),
    @NamedQuery(name = "InformeTecnico.findByTotalActivos", query = "SELECT i FROM InformeTecnico i WHERE i.totalActivos = :totalActivos"),
    @NamedQuery(name = "InformeTecnico.findByTotalPasivos", query = "SELECT i FROM InformeTecnico i WHERE i.totalPasivos = :totalPasivos"),
    @NamedQuery(name = "InformeTecnico.findByTotalPatrimonio", query = "SELECT i FROM InformeTecnico i WHERE i.totalPatrimonio = :totalPatrimonio")})
public class InformeTecnico implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InformeTecnicoPK informeTecnicoPK;
    @Basic(optional = false)
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @Column(name = "FACTIBLE")
    private char factible;
    @Basic(optional = false)
    @Column(name = "DETALLE")
    private String detalle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "TOTAL_INGRESOS")
    private BigDecimal totalIngresos;
    @Basic(optional = false)
    @Column(name = "TOTAL_EGRESOS")
    private BigDecimal totalEgresos;
    @Basic(optional = false)
    @Column(name = "TOTAL_FLUJO_CAJA")
    private BigDecimal totalFlujoCaja;
    @Basic(optional = false)
    @Column(name = "COBERTURA")
    private BigDecimal cobertura;
    @Basic(optional = false)
    @Column(name = "TOTAL_ACTIVOS")
    private BigDecimal totalActivos;
    @Basic(optional = false)
    @Column(name = "TOTAL_PASIVOS")
    private BigDecimal totalPasivos;
    @Basic(optional = false)
    @Column(name = "TOTAL_PATRIMONIO")
    private BigDecimal totalPatrimonio;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_SOLICITUD", referencedColumnName = "NUMERO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Solicitud solicitud;

    public InformeTecnico() {
    }

    public InformeTecnico(InformeTecnicoPK informeTecnicoPK) {
        this.informeTecnicoPK = informeTecnicoPK;
    }

    public InformeTecnico(InformeTecnicoPK informeTecnicoPK, Date fechaRegistro, long registradoPor, char factible, String detalle, BigDecimal totalIngresos, BigDecimal totalEgresos, BigDecimal totalFlujoCaja, BigDecimal cobertura, BigDecimal totalActivos, BigDecimal totalPasivos, BigDecimal totalPatrimonio) {
        this.informeTecnicoPK = informeTecnicoPK;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
        this.factible = factible;
        this.detalle = detalle;
        this.totalIngresos = totalIngresos;
        this.totalEgresos = totalEgresos;
        this.totalFlujoCaja = totalFlujoCaja;
        this.cobertura = cobertura;
        this.totalActivos = totalActivos;
        this.totalPasivos = totalPasivos;
        this.totalPatrimonio = totalPatrimonio;
    }

    public InformeTecnico(long numeroSolicitud, long codigoIfip) {
        this.informeTecnicoPK = new InformeTecnicoPK(numeroSolicitud, codigoIfip);
    }

    public InformeTecnicoPK getInformeTecnicoPK() {
        return informeTecnicoPK;
    }

    public void setInformeTecnicoPK(InformeTecnicoPK informeTecnicoPK) {
        this.informeTecnicoPK = informeTecnicoPK;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public char getFactible() {
        return factible;
    }

    public void setFactible(char factible) {
        this.factible = factible;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
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

    public BigDecimal getCobertura() {
        return cobertura;
    }

    public void setCobertura(BigDecimal cobertura) {
        this.cobertura = cobertura;
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

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (informeTecnicoPK != null ? informeTecnicoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformeTecnico)) {
            return false;
        }
        InformeTecnico other = (InformeTecnico) object;
        if ((this.informeTecnicoPK == null && other.informeTecnicoPK != null) || (this.informeTecnicoPK != null && !this.informeTecnicoPK.equals(other.informeTecnicoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject2.InformeTecnico[ informeTecnicoPK=" + informeTecnicoPK + " ]";
    }
    
}
