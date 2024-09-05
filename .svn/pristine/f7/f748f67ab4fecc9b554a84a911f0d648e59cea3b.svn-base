/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "MKS_HISTORICOS.UAF_PERSONA_POLITICAMENTE_EXP")
@XmlRootElement
public class UafPersonaPoliticamenteExp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "DOCUMENTO")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "APELLIDOS_NOMBRES")
    private String apellidosNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DENOMINACION_PUESTO")
    private String denominacionPuesto;
    @Size(max = 200)
    @Column(name = "ENTIDAD")
    private String entidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_UAF_PERSONA_POLITICAMENTE_EXP")
    @SequenceGenerator(name = "GSQ_UAF_PERSONA_POLITICAMENTE_EXP", schema = "MKS_HISTORICOS",  allocationSize = 0, sequenceName = "SEQ_UAF_PERSONA_POLITICAMENTE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @JoinColumn(name = "CODIGO_UAF_CARGA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private UafCarga codigoUafCarga;

    public UafPersonaPoliticamenteExp() {
    }

    public UafPersonaPoliticamenteExp(Long codigo) {
        this.codigo = codigo;
    }

    public UafPersonaPoliticamenteExp(Long codigo, String documento, String apellidosNombres, String denominacionPuesto, String estado, Date fecha) {
        this.codigo = codigo;
        this.documento = documento;
        this.apellidosNombres = apellidosNombres;
        this.denominacionPuesto = denominacionPuesto;
        this.estado = estado;
        this.fecha = fecha;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getApellidosNombres() {
        return apellidosNombres;
    }

    public void setApellidosNombres(String apellidosNombres) {
        this.apellidosNombres = apellidosNombres;
    }

    public String getDenominacionPuesto() {
        return denominacionPuesto;
    }

    public void setDenominacionPuesto(String denominacionPuesto) {
        this.denominacionPuesto = denominacionPuesto;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public UafCarga getCodigoUafCarga() {
        return codigoUafCarga;
    }

    public void setCodigoUafCarga(UafCarga codigoUafCarga) {
        this.codigoUafCarga = codigoUafCarga;
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
        if (!(object instanceof UafPersonaPoliticamenteExp)) {
            return false;
        }
        UafPersonaPoliticamenteExp other = (UafPersonaPoliticamenteExp) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.UafPersonaPoliticamenteExp[ codigo=" + codigo + " ]";
    }
    
}
