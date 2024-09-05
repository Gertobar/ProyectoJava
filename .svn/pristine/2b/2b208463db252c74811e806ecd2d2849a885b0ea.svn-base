/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.etapa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_ETAPA.DESCUENTO_SOCIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescuentoSocio.findAll", query = "SELECT d FROM DescuentoSocio d"),    
    @NamedQuery(name = "DescuentoSocio.findByFechaCorte", query = "SELECT d FROM DescuentoSocio d WHERE d.fechaGeneracion = :fechaCorte")})
public class DescuentoSocio implements Serializable {
    public static final String findByFechaCorte = "DescuentoSocio.findByFechaCorte";
   private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CEDULA")
    private String cedula;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AHORRO")
    private BigDecimal ahorro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CERTIFICADOS")
    private BigDecimal certificados;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FONDO_MORTUORIO")
    private BigDecimal fondoMortuorio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUMA_CREDITOS")
    private BigDecimal sumaCreditos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_DESCUENTO")
    private BigDecimal totalDescuento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "COOFEC")
    private String coofec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COODE1")
    private BigDecimal coode1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COOCES")
    private BigDecimal cooces;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDINARIO")
    private BigDecimal ordinario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMERGENTE")
    private BigDecimal emergente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "URGENTE")
    private BigDecimal urgente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCTIVO")
    private BigDecimal productivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTUDIO")
    private BigDecimal estudio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HIPOTECARIO")
    private BigDecimal hipotecario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDICO")
    private BigDecimal medico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_GENERACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GENERADO_POR")
    private long generadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_GRUPO")
    private long codigoGrupo;
    @Size(max = 30)
    @Column(name = "CUENTA_CERTIFICADOS")
    private String cuentaCertificados;
    @Size(max = 30)
    @Column(name = "CUENTA_VISTA")
    private String cuentaVista;
    @Size(max = 30)
    @Column(name = "CUENTA_RESTRINGIDOS")
    private String cuentaRestringidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SOCIO")
    private long codigoSocio;

    public DescuentoSocio() {
    }

    public DescuentoSocio(Long codigo) {
        this.codigo = codigo;
    }

    public DescuentoSocio(Long codigo, String cedula, BigDecimal ahorro, BigDecimal certificados, BigDecimal fondoMortuorio, BigDecimal sumaCreditos, BigDecimal totalDescuento, String nombres, String coofec, BigDecimal coode1, BigDecimal cooces, BigDecimal ordinario, BigDecimal emergente, BigDecimal urgente, BigDecimal productivo, BigDecimal estudio, BigDecimal hipotecario, BigDecimal medico, Date fechaGeneracion, long generadoPor, long codigoGrupo, Date fechaInicio, Date fechaFin, long codigoSocio) {
        this.codigo = codigo;
        this.cedula = cedula;
        this.ahorro = ahorro;
        this.certificados = certificados;
        this.fondoMortuorio = fondoMortuorio;
        this.sumaCreditos = sumaCreditos;
        this.totalDescuento = totalDescuento;
        this.nombres = nombres;
        this.coofec = coofec;
        this.coode1 = coode1;
        this.cooces = cooces;
        this.ordinario = ordinario;
        this.emergente = emergente;
        this.urgente = urgente;
        this.productivo = productivo;
        this.estudio = estudio;
        this.hipotecario = hipotecario;
        this.medico = medico;
        this.fechaGeneracion = fechaGeneracion;
        this.generadoPor = generadoPor;
        this.codigoGrupo = codigoGrupo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.codigoSocio = codigoSocio;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public BigDecimal getAhorro() {
        return ahorro;
    }

    public void setAhorro(BigDecimal ahorro) {
        this.ahorro = ahorro;
    }

    public BigDecimal getCertificados() {
        return certificados;
    }

    public void setCertificados(BigDecimal certificados) {
        this.certificados = certificados;
    }

    public BigDecimal getFondoMortuorio() {
        return fondoMortuorio;
    }

    public void setFondoMortuorio(BigDecimal fondoMortuorio) {
        this.fondoMortuorio = fondoMortuorio;
    }

    public BigDecimal getSumaCreditos() {
        return sumaCreditos;
    }

    public void setSumaCreditos(BigDecimal sumaCreditos) {
        this.sumaCreditos = sumaCreditos;
    }

    public BigDecimal getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(BigDecimal totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCoofec() {
        return coofec;
    }

    public void setCoofec(String coofec) {
        this.coofec = coofec;
    }

    public BigDecimal getCoode1() {
        return coode1;
    }

    public void setCoode1(BigDecimal coode1) {
        this.coode1 = coode1;
    }

    public BigDecimal getCooces() {
        return cooces;
    }

    public void setCooces(BigDecimal cooces) {
        this.cooces = cooces;
    }

    public BigDecimal getOrdinario() {
        return ordinario;
    }

    public void setOrdinario(BigDecimal ordinario) {
        this.ordinario = ordinario;
    }

    public BigDecimal getEmergente() {
        return emergente;
    }

    public void setEmergente(BigDecimal emergente) {
        this.emergente = emergente;
    }

    public BigDecimal getUrgente() {
        return urgente;
    }

    public void setUrgente(BigDecimal urgente) {
        this.urgente = urgente;
    }

    public BigDecimal getProductivo() {
        return productivo;
    }

    public void setProductivo(BigDecimal productivo) {
        this.productivo = productivo;
    }

    public BigDecimal getEstudio() {
        return estudio;
    }

    public void setEstudio(BigDecimal estudio) {
        this.estudio = estudio;
    }

    public BigDecimal getHipotecario() {
        return hipotecario;
    }

    public void setHipotecario(BigDecimal hipotecario) {
        this.hipotecario = hipotecario;
    }

    public BigDecimal getMedico() {
        return medico;
    }

    public void setMedico(BigDecimal medico) {
        this.medico = medico;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public long getGeneradoPor() {
        return generadoPor;
    }

    public void setGeneradoPor(long generadoPor) {
        this.generadoPor = generadoPor;
    }

    public long getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(long codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getCuentaCertificados() {
        return cuentaCertificados;
    }

    public void setCuentaCertificados(String cuentaCertificados) {
        this.cuentaCertificados = cuentaCertificados;
    }

    public String getCuentaVista() {
        return cuentaVista;
    }

    public void setCuentaVista(String cuentaVista) {
        this.cuentaVista = cuentaVista;
    }

    public String getCuentaRestringidos() {
        return cuentaRestringidos;
    }

    public void setCuentaRestringidos(String cuentaRestringidos) {
        this.cuentaRestringidos = cuentaRestringidos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public long getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(long codigoSocio) {
        this.codigoSocio = codigoSocio;
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
        if (!(object instanceof DescuentoSocio)) {
            return false;
        }
        DescuentoSocio other = (DescuentoSocio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.etapa.DescuentoSocio[ codigo=" + codigo + " ]";
    }

   
    
}
