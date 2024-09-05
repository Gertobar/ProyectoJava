/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.dpfs;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.Persona;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_DPFS.TASA_INTERES_PRO_DPF_MOD")
@XmlRootElement

public class TasaIntenresProductoDPFMontoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TASA_INTERES_PRO_DPF_MOD")
    @SequenceGenerator(name = "GSQ_TASA_INTERES_PRO_DPF_MOD", schema = "MKS_DPFS", allocationSize = 0, sequenceName = "SEQ_TASA_INTERES_PRO_DPF_MOD")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_INICIAL")
    private BigDecimal montoInicial;

    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_FINAL")
    private BigDecimal montoFinal;

    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_CAPITAL")
    private BigDecimal montoCapital;

    @Basic(optional = false)
    @NotNull
    @Column(name = "TASA_NOMAL")
    private BigDecimal tasaNormal;

    @Basic(optional = false)
    @NotNull
    @Column(name = "TASA_ACUMULADA")
    private BigDecimal tasaAcumulada;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PLAZO_DIAS")
    private long plasoDias;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJE_ACUMULADA_MAX")
    private BigDecimal porcentajeAcumulacionMaximo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "APROBADO")
    private char esAprobado;

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegistro;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @JoinColumn(name = "CODIGO_TASA_ROL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TasaInteresRolAprobacion rolAprueba;

    @JoinColumn(name = "CODIGO_TASA_INTERES_PDM", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TasaInteresProDpfMon rasaInteresProDpfMon;

    @JoinColumn(name = "CODIGO_PERSONA_BENEFICIARIO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Persona persona;

    @JoinColumn(name = "CODIGO_USUARIO_APRUEBA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario usuario;

    @JoinColumns({
        @JoinColumn(name = "CODIGO_DPF", referencedColumnName = "CODIGO", nullable = true)
        ,
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", nullable = true)})
    @ManyToOne(optional = true)
    private ContratoDpf contratoDpf;

    public TasaIntenresProductoDPFMontoDetalle() {
    }

    public TasaIntenresProductoDPFMontoDetalle(BigDecimal montoInicial, BigDecimal montoFinal, BigDecimal montoCapital, BigDecimal tasaNormal, BigDecimal tasaAcumulada, long plasoDias, BigDecimal porcentajeAcumulacionMaximo, char esAprobado, Date fechaRegistro, String descripcion, TasaInteresRolAprobacion rolAprueba, TasaInteresProDpfMon rasaInteresProDpfMon, Persona persona, Usuario usuario) {
        this.montoInicial = montoInicial;
        this.montoFinal = montoFinal;
        this.montoCapital = montoCapital;
        this.tasaNormal = tasaNormal;
        this.tasaAcumulada = tasaAcumulada;
        this.plasoDias = plasoDias;
        this.porcentajeAcumulacionMaximo = porcentajeAcumulacionMaximo;
        this.esAprobado = esAprobado;
        this.fechaRegistro = fechaRegistro;
        this.descripcion = descripcion;
        this.rolAprueba = rolAprueba;
        this.rasaInteresProDpfMon = rasaInteresProDpfMon;
        this.persona = persona;
        this.usuario = usuario;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }

    public BigDecimal getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(BigDecimal montoFinal) {
        this.montoFinal = montoFinal;
    }

    public BigDecimal getMontoCapital() {
        return montoCapital;
    }

    public void setMontoCapital(BigDecimal montoCapital) {
        this.montoCapital = montoCapital;
    }

    public TasaInteresRolAprobacion getRolAprueba() {
        return rolAprueba;
    }

    public void setRolAprueba(TasaInteresRolAprobacion rolAprueba) {
        this.rolAprueba = rolAprueba;
    }

    public TasaInteresProDpfMon getRasaInteresProDpfMon() {
        return rasaInteresProDpfMon;
    }

    public void setRasaInteresProDpfMon(TasaInteresProDpfMon rasaInteresProDpfMon) {
        this.rasaInteresProDpfMon = rasaInteresProDpfMon;
    }

    public char getEsAprobado() {
        return esAprobado;
    }

    public void setEsAprobado(char esAprobado) {
        this.esAprobado = esAprobado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public BigDecimal getTasaNormal() {
        return tasaNormal;
    }

    public void setTasaNormal(BigDecimal tasaNormal) {
        this.tasaNormal = tasaNormal;
    }

    public BigDecimal getTasaAcumulada() {
        return tasaAcumulada;
    }

    public void setTasaAcumulada(BigDecimal tasaAcumulada) {
        this.tasaAcumulada = tasaAcumulada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof TasaIntenresProductoDPFMontoDetalle)) {
            return false;
        }
        TasaIntenresProductoDPFMontoDetalle other = (TasaIntenresProductoDPFMontoDetalle) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs[ codigo=" + codigo + " ]";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getPlasoDias() {
        return plasoDias;
    }

    public void setPlasoDias(long plasoDias) {
        this.plasoDias = plasoDias;
    }

    public BigDecimal getPorcentajeAcumulacionMaximo() {
        return porcentajeAcumulacionMaximo;
    }

    public void setPorcentajeAcumulacionMaximo(BigDecimal porcentajeAcumulacionMaximo) {
        this.porcentajeAcumulacionMaximo = porcentajeAcumulacionMaximo;
    }

    public ContratoDpf getContratoDpf() {
        return contratoDpf;
    }

    public void setContratoDpf(ContratoDpf contratoDpf) {
        this.contratoDpf = contratoDpf;
    }
    
    

}
