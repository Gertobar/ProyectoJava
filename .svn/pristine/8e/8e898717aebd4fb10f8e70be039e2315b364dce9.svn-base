/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author willan
 */
@Entity
@Table(name = "MKS_SOCIOS.RELACION_PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelacionPersona.findAll", query = "SELECT r FROM RelacionPersona r")
    , @NamedQuery(name = "RelacionPersona.findByCodigo", query = "SELECT r FROM RelacionPersona r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RelacionPersona.findByDescripcion", query = "SELECT r FROM RelacionPersona r WHERE r.descripcion = :descripcion")})
public class RelacionPersona implements Serializable {

    public static final String findByEliminado = "RelacionPersona.findByEliminado";
    public static final String findAll = "RelacionPersona.findAll";
    public static final String findByCodigo = "RelacionPersona .findByCodigo";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_RELACION_PERSONA")
    @SequenceGenerator(name = "GSQ_RELACION_PERSONA", schema = "MKS_SOCIOS", allocationSize = 0, sequenceName = "SEQ_RELACION_PERSONA")
    @Column(name = "CODIGO")
    private Long codigo;
    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;
    @JoinColumn(name = "CODIGO_CLASE_PERSONA", referencedColumnName = "CODIGO")
    @ManyToOne
    private ClasePersona codigoClasePersona;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO")
    @ManyToOne
    private Persona codigoPersona;
    
    @JoinColumn(name = "CODIGO_PERSONA_RELACION", referencedColumnName = "CODIGO")
    @ManyToOne
    private Persona codigoPersonaRelacion;

    public RelacionPersona() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ClasePersona getCodigoClasePersona() {
        return codigoClasePersona;
    }

    public void setCodigoClasePersona(ClasePersona codigoClasePersona) {
        this.codigoClasePersona = codigoClasePersona;
    }

    public Persona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Persona codigoPersona) {
        this.codigoPersona = codigoPersona;
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
        if (!(object instanceof RelacionPersona)) {
            return false;
        }
        RelacionPersona other = (RelacionPersona) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication3.RelacionPersona[ codigo=" + codigo + " ]";
    }

    public Persona getCodigoPersonaRelacion() {
        return codigoPersonaRelacion;
    }

    public void setCodigoPersonaRelacion(Persona codigoPersonaRelacion) {
        this.codigoPersonaRelacion = codigoPersonaRelacion;
    }

}
