/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author willan
 */
@Entity
@Table(name = "MKS_SEGURIDADES.OFICIAL_CUMPLIMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OficialCumplimiento.findAll", query = "SELECT o FROM OficialCumplimiento o")
    , @NamedQuery(name = "OficialCumplimiento.findByCodigo", query = "SELECT o FROM OficialCumplimiento o WHERE o.codigo = :codigo")
    , @NamedQuery(name = "OficialCumplimiento.findByCodigoPersona", query = "SELECT o FROM OficialCumplimiento o WHERE o.codigoPersona = :codigoPersona")
    , @NamedQuery(name = "OficialCumplimiento.findByCorreoElectronico", query = "SELECT o FROM OficialCumplimiento o WHERE o.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "OficialCumplimiento.findByVigente", query = "SELECT o FROM OficialCumplimiento o WHERE o.vigente = :vigente")
    , @NamedQuery(name = "OficialCumplimiento.findByFechaSalida", query = "SELECT o FROM OficialCumplimiento o WHERE o.fechaSalida = :fechaSalida")})
public class OficialCumplimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    @Basic(optional = false)
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;
    @Basic(optional = false)
    @Column(name = "VIGENTE")
    private String vigente;
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;

    public OficialCumplimiento() {
    }

    public OficialCumplimiento(Long codigo) {
        this.codigo = codigo;
    }

    public OficialCumplimiento(Long codigo, long codigoPersona, String correoElectronico, String vigente) {
        this.codigo = codigo;
        this.codigoPersona = codigoPersona;
        this.correoElectronico = correoElectronico;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
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
        if (!(object instanceof OficialCumplimiento)) {
            return false;
        }
        OficialCumplimiento other = (OficialCumplimiento) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication3.OficialCumplimiento[ codigo=" + codigo + " ]";
    }
    
}
