/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.entidad.ahorro.programado;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author andres
 */
public class TablaAhorroProgramado {
    
    private Long codigo;
    private Long cuota;
    private Date fechaPago;
    private Date fechaMaximaPago;
    private BigDecimal valor;
    private String estado;
    private String esPorIncumplimiento;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTablaAhorroProgramado")
    private Set<PagoContratoAhorroPro> pagoContratoAhorroProSet;*/    
    //@JoinColumn(name = "CODIGO_CONTRATO_AHORRO_PRO", referencedColumnName = "CODIGO")
    //@ManyToOne(optional = false)
    //private ContratoAhorroProgramado contratoAhorroProgramado;

    public TablaAhorroProgramado() {
    }

    public TablaAhorroProgramado(Long codigo) {
        this.codigo = codigo;
    }

    public TablaAhorroProgramado(Long codigo, Long cuota, Date fechaPago, Date fechaMaximaPago, BigDecimal valor, String estado, String esPorIncumplimiento, ContratoAhorroProgramado contratoAhorroProgramado) {
        this.codigo = codigo;
        this.cuota = cuota;
        this.fechaPago = fechaPago;
        this.fechaMaximaPago = fechaMaximaPago;
        this.valor = valor;
        this.estado = estado;
        this.esPorIncumplimiento = esPorIncumplimiento;
        //this.contratoAhorroProgramado = contratoAhorroProgramado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCuota() {
        return cuota;
    }

    public void setCuota(Long cuota) {
        this.cuota = cuota;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaMaximaPago() {
        return fechaMaximaPago;
    }

    public void setFechaMaximaPago(Date fechaMaximaPago) {
        this.fechaMaximaPago = fechaMaximaPago;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEsPorIncumplimiento() {
        return esPorIncumplimiento;
    }

    public void setEsPorIncumplimiento(String esPorIncumplimiento) {
        this.esPorIncumplimiento = esPorIncumplimiento;
    }

    //@XmlTransient
   /* public Set<PagoContratoAhorroPro> getPagoContratoAhorroProSet() {
        return pagoContratoAhorroProSet;
    }

    public void setPagoContratoAhorroProSet(Set<PagoContratoAhorroPro> pagoContratoAhorroProSet) {
        this.pagoContratoAhorroProSet = pagoContratoAhorroProSet;
    }*/

    /*public ContratoAhorroProgramado getCodigoContratoAhorroPro() {
        return contratoAhorroProgramado;
    }

    public void setCodigoContratoAhorroPro(ContratoAhorroProgramado contratoAhorroProgramado) {
        this.contratoAhorroProgramado = contratoAhorroProgramado;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablaAhorroProgramado)) {
            return false;
        }
        TablaAhorroProgramado other = (TablaAhorroProgramado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TablaAhorroProgramado{" + "codigo=" + codigo + ", cuota=" + cuota + ", fechaPago=" + fechaPago + ", fechaMaximaPago=" + fechaMaximaPago + ", valor=" + valor + ", estado=" + estado + ", esPorIncumplimiento=" + esPorIncumplimiento + '}';
    }
    
}
