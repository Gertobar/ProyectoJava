/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author willan
 */
@Entity
@Table(name = "MKS_SOCIOS.CLASE_PERSONA", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasePersona.findAll", query = "SELECT c FROM ClasePersona c")
    , @NamedQuery(name = "ClasePersona.findByCodigo", query = "SELECT c FROM ClasePersona c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "ClasePersona.findByNombre", query = "SELECT c FROM ClasePersona c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "ClasePersona.findByEsParaInstitucion", query = "SELECT c FROM ClasePersona c WHERE c.esParaInstitucion = :esParaInstitucion")
    , @NamedQuery(name = "ClasePersona.findByEliminado", query = "SELECT c FROM ClasePersona c WHERE c.eliminado = :eliminado")
    , @NamedQuery(name = "ClasePersona.findByCodigoOc", query = "SELECT c FROM ClasePersona c WHERE c.codigoOc = :codigoOc")})
public class ClasePersona implements Serializable {
    public static final String findByEliminado = "ClasePersona.findByEliminado";
    public static final String findAll = "ClasePersona.findAll";
    public static final String findByCodigo = "ClasePersona.findByCodigo";
    public static final String findByEsParaInstitucionEliminado = "ClasePersona.findByEsParaInstitucion";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_CLASE_PERSONA")
    @SequenceGenerator(name = "GSQ_CLASE_PERSONA", schema = "MKS_SOCIOS", allocationSize = 0, sequenceName = "SEQ_CLASE_PERSONA")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "ES_PARA_INSTITUCION", nullable = false)
    private Character esParaInstitucion;
    @Basic(optional = false)
    @Column(name = "ELIMINADO", nullable = false)
    private Character eliminado;
    @Basic(optional = false)
    @Column(name = "CODIGO_OC", nullable = false, length = 1)
    private String codigoOc;
    @OneToMany(mappedBy = "codigoClasePersona")
    private List<RelacionPersona> relacionPersonaList;

    public ClasePersona() {
    }

    public ClasePersona(Long codigo) {
        this.codigo = codigo;
    }

    public ClasePersona(Long codigo, String nombre, Character esParaInstitucion, Character eliminado, String codigoOc) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.esParaInstitucion = esParaInstitucion;
        this.eliminado = eliminado;
        this.codigoOc = codigoOc;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getEsParaInstitucion() {
        return esParaInstitucion;
    }

    public void setEsParaInstitucion(Character esParaInstitucion) {
        this.esParaInstitucion = esParaInstitucion;
    }

    public Character getEliminado() {
        return eliminado;
    }

    public void setEliminado(Character eliminado) {
        this.eliminado = eliminado;
    }

    public String getCodigoOc() {
        return codigoOc;
    }

    public void setCodigoOc(String codigoOc) {
        this.codigoOc = codigoOc;
    }

    @XmlTransient
    public List<RelacionPersona> getRelacionPersonaList() {
        return relacionPersonaList;
    }

    public void setRelacionPersonaList(List<RelacionPersona> relacionPersonaList) {
        this.relacionPersonaList = relacionPersonaList;
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
        if (!(object instanceof ClasePersona)) {
            return false;
        }
        ClasePersona other = (ClasePersona) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication3.ClasePersona[ codigo=" + codigo + " ]";
    }

}
