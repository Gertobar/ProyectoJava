/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.entidad.ahorro.programado;

/**
 *
 * @author andres
 */
public class AhorroProgramado {
    
    private Long codigo;
    private Long codigoIfip;
    private Long codigoTipoProducto;  
    private Long codigoConceptoTraTra;
    private String nombre;
    private String descripcion;
    private String vigente;

    public AhorroProgramado() {
    }

    public AhorroProgramado(Long codigo) {
        this.codigo = codigo;
    }

    public AhorroProgramado(Long codigo, Long codigoIfip, Long codigoTipoProducto, Long codigoConceptoTraTra, String nombre, String descripcion, String vigente) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.codigoTipoProducto = codigoTipoProducto;
        this.codigoConceptoTraTra = codigoConceptoTraTra;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public Long getCodigoTipoProducto() {
        return codigoTipoProducto;
    }

    public void setCodigoTipoProducto(Long codigoTipoProducto) {
        this.codigoTipoProducto = codigoTipoProducto;
    }

    public Long getCodigoConceptoTraTra() {
        return codigoConceptoTraTra;
    }

    public void setCodigoConceptoTraTra(Long codigoConceptoTraTra) {
        this.codigoConceptoTraTra = codigoConceptoTraTra;
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

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
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
        if (!(object instanceof AhorroProgramado)) {
            return false;
        }
        AhorroProgramado other = (AhorroProgramado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.control.ahorros.programados.AhorroProgramado[ codigo=" + codigo + " ]";
    }
   
}
