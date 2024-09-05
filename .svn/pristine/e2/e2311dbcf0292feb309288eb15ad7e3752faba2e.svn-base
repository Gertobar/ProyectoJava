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
@Table(name = "MKS_AUDITORIAS.LINEA_CREDITO_AUDITORIA")
@XmlRootElement
public class LineaCreditoAuditoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_LINEA_CREDITO_AUDITORIA")
    @SequenceGenerator(name = "GSQ_LINEA_CREDITO_AUDITORIA", schema = "MKS_AUDITORIAS",  allocationSize = 0, sequenceName = "SEQ_LINEA_CREDITO_AUDITORIA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_LINEA_CREDITO")
    private Long codigoLineaCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PRODUCTO")
    private Long codigoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private Long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private Long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERIODICIDAD")
    private Long codigoPeriodicidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ORIGEN_RECURSOS")
    private Long codigoOrigenRecursos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_MINIMO")
    private BigDecimal montoMinimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_MAXIMO")
    private BigDecimal montoMaximo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_GENERA_CREDITO")
    private short diaGeneraCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJE_PAGO_MINIMO")
    private BigDecimal porcentajePagoMinimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_PARA_BLOQUEO")
    private int diaParaBloqueo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_TABLA")
    private String tipoTabla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PLAZO_AUTOMATICO")
    private String plazoAutomatico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;
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

    public LineaCreditoAuditoria() {
    }

    public LineaCreditoAuditoria(Long codigo) {
        this.codigo = codigo;
    }

    public LineaCreditoAuditoria(Long codigo, Long codigoLineaCredito, Long codigoProducto, Long codigoMoneda, Long codigoIfip, Long codigoPeriodicidad, Long codigoOrigenRecursos, String nombre, String descripcion, BigDecimal montoMinimo, BigDecimal montoMaximo, short diaGeneraCredito, BigDecimal porcentajePagoMinimo, int diaParaBloqueo, String tipoTabla, String plazoAutomatico, String vigente, Long codigoUsuario, Date fechaRegistro, char accion) {
        this.codigo = codigo;
        this.codigoLineaCredito = codigoLineaCredito;
        this.codigoProducto = codigoProducto;
        this.codigoMoneda = codigoMoneda;
        this.codigoIfip = codigoIfip;
        this.codigoPeriodicidad = codigoPeriodicidad;
        this.codigoOrigenRecursos = codigoOrigenRecursos;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.montoMinimo = montoMinimo;
        this.montoMaximo = montoMaximo;
        this.diaGeneraCredito = diaGeneraCredito;
        this.porcentajePagoMinimo = porcentajePagoMinimo;
        this.diaParaBloqueo = diaParaBloqueo;
        this.tipoTabla = tipoTabla;
        this.plazoAutomatico = plazoAutomatico;
        this.vigente = vigente;
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

    public Long getCodigoLineaCredito() {
        return codigoLineaCredito;
    }

    public void setCodigoLineaCredito(Long codigoLineaCredito) {
        this.codigoLineaCredito = codigoLineaCredito;
    }

    public Long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(Long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public Long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public Long getCodigoPeriodicidad() {
        return codigoPeriodicidad;
    }

    public void setCodigoPeriodicidad(Long codigoPeriodicidad) {
        this.codigoPeriodicidad = codigoPeriodicidad;
    }

    public Long getCodigoOrigenRecursos() {
        return codigoOrigenRecursos;
    }

    public void setCodigoOrigenRecursos(Long codigoOrigenRecursos) {
        this.codigoOrigenRecursos = codigoOrigenRecursos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(BigDecimal montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public BigDecimal getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(BigDecimal montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public short getDiaGeneraCredito() {
        return diaGeneraCredito;
    }

    public void setDiaGeneraCredito(short diaGeneraCredito) {
        this.diaGeneraCredito = diaGeneraCredito;
    }

    public BigDecimal getPorcentajePagoMinimo() {
        return porcentajePagoMinimo;
    }

    public void setPorcentajePagoMinimo(BigDecimal porcentajePagoMinimo) {
        this.porcentajePagoMinimo = porcentajePagoMinimo;
    }

    public int getDiaParaBloqueo() {
        return diaParaBloqueo;
    }

    public void setDiaParaBloqueo(int diaParaBloqueo) {
        this.diaParaBloqueo = diaParaBloqueo;
    }

    public String getTipoTabla() {
        return tipoTabla;
    }

    public void setTipoTabla(String tipoTabla) {
        this.tipoTabla = tipoTabla;
    }

    public String getPlazoAutomatico() {
        return plazoAutomatico;
    }

    public void setPlazoAutomatico(String plazoAutomatico) {
        this.plazoAutomatico = plazoAutomatico;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
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
        if (!(object instanceof LineaCreditoAuditoria)) {
            return false;
        }
        LineaCreditoAuditoria other = (LineaCreditoAuditoria) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.auditorias.LineaCreditoAuditoria[ codigo=" + codigo + " ]";
    }
    
}
