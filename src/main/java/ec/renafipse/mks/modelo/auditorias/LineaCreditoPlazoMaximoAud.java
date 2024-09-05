/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.auditorias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MKS_AUDITORIAS.LINEA_CREDITO_PLAZO_MAXIMO_AUD")
@XmlRootElement
public class LineaCreditoPlazoMaximoAud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_LINEA_CREDITO_AUDITORIA_PLA_MAX")
    @SequenceGenerator(name = "GSQ_LINEA_CREDITO_AUDITORIA_PLA_MAX", schema = "MKS_AUDITORIAS",  allocationSize = 0, sequenceName = "SEQ_LINEA_CREDITO_PLA_MAX_AUD")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_LINEA_CREDITO_PLA_MAX")
    private Long codigoLineaCreditoPlaMax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_LINEA_CREDITO")
    private Long codigoLineaCredito;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_INICIO_PLAZO")
    private BigDecimal montoInicioPlazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_FIN_PLAZO")
    private BigDecimal montoFinPlazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CUOTAS_INICIO")
    private Long numeroCuotasInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CUOTAS_FIN")
    private Long numeroCuotasFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCION")
    private char accion;

    public LineaCreditoPlazoMaximoAud() {
    }

    public LineaCreditoPlazoMaximoAud(Long codigo) {
        this.codigo = codigo;
    }

    public LineaCreditoPlazoMaximoAud(Long codigo, Long codigoLineaCreditoPlaMax, Long codigoLineaCredito, BigDecimal montoInicioPlazo, BigDecimal montoFinPlazo, Long numeroCuotasInicio, Long numeroCuotasFin, String eliminado, Long codigoUsuario, Date fechaRegistro, char accion) {
        this.codigo = codigo;
        this.codigoLineaCreditoPlaMax = codigoLineaCreditoPlaMax;
        this.codigoLineaCredito = codigoLineaCredito;
        this.montoInicioPlazo = montoInicioPlazo;
        this.montoFinPlazo = montoFinPlazo;
        this.numeroCuotasInicio = numeroCuotasInicio;
        this.numeroCuotasFin = numeroCuotasFin;
        this.eliminado = eliminado;
        this.codigoUsuario = codigoUsuario;
        this.fechaRegistro = fechaRegistro;
        this.accion = accion;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoLineaCreditoPlaMax() {
        return codigoLineaCreditoPlaMax;
    }

    public void setCodigoLineaCreditoPlaMax(Long codigoLineaCreditoPlaMax) {
        this.codigoLineaCreditoPlaMax = codigoLineaCreditoPlaMax;
    }

    public Long getCodigoLineaCredito() {
        return codigoLineaCredito;
    }

    public void setCodigoLineaCredito(Long codigoLineaCredito) {
        this.codigoLineaCredito = codigoLineaCredito;
    }

    public BigDecimal getMontoInicioPlazo() {
        return montoInicioPlazo;
    }

    public void setMontoInicioPlazo(BigDecimal montoInicioPlazo) {
        this.montoInicioPlazo = montoInicioPlazo;
    }

    public BigDecimal getMontoFinPlazo() {
        return montoFinPlazo;
    }

    public void setMontoFinPlazo(BigDecimal montoFinPlazo) {
        this.montoFinPlazo = montoFinPlazo;
    }

    public Long getNumeroCuotasInicio() {
        return numeroCuotasInicio;
    }

    public void setNumeroCuotasInicio(Long numeroCuotasInicio) {
        this.numeroCuotasInicio = numeroCuotasInicio;
    }

    public Long getNumeroCuotasFin() {
        return numeroCuotasFin;
    }

    public void setNumeroCuotasFin(Long numeroCuotasFin) {
        this.numeroCuotasFin = numeroCuotasFin;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public char getAccion() {
        return accion;
    }

    public void setAccion(char accion) {
        this.accion = accion;
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
        if (!(object instanceof LineaCreditoPlazoMaximoAud)) {
            return false;
        }
        LineaCreditoPlazoMaximoAud other = (LineaCreditoPlazoMaximoAud) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.auditorias.LineaCreditoPlazoMaximoAud[ codigo=" + codigo + " ]";
    }
    
}
