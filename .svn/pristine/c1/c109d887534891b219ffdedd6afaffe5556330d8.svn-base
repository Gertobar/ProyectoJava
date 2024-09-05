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
@Table(name = "MKS_HISTORICOS.UAF_SENTENCIADO")
@XmlRootElement
public class UafSentenciado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 15)
    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO_IDENTIFICACION")
    private String numeroIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 30)
    @Column(name = "NACIONALIDAD")
    private String nacionalidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_UAF_SENTENCIADO")
    @SequenceGenerator(name = "GSQ_UAF_SENTENCIADO", schema = "MKS_HISTORICOS",  allocationSize = 0, sequenceName = "SEQ_UAF_SENTENCIADO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @JoinColumn(name = "CODIGO_UAF_CARGA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private UafCarga codigoUafCarga;

    public UafSentenciado() {
    }

    public UafSentenciado(Long codigo) {
        this.codigo = codigo;
    }

    public UafSentenciado(Long codigo, String numeroIdentificacion, String apellidos, String nombres, Date fecha) {
        this.codigo = codigo;
        this.numeroIdentificacion = numeroIdentificacion;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fecha = fecha;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
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
        if (!(object instanceof UafSentenciado)) {
            return false;
        }
        UafSentenciado other = (UafSentenciado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.UafSentenciado[ codigo=" + codigo + " ]";
    }
    
}
