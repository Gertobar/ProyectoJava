/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.DESTINO_FINANCIERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DestinoFinanciero.findAll", query = "SELECT d FROM DestinoFinanciero d"),
    @NamedQuery(name = "DestinoFinanciero.findByCodigo", query = "SELECT d FROM DestinoFinanciero d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "DestinoFinanciero.findByCodigoOc", query = "SELECT d FROM DestinoFinanciero d WHERE d.codigoOc = :codigoOc"),
    @NamedQuery(name = "DestinoFinanciero.findByNombre", query = "SELECT d FROM DestinoFinanciero d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DestinoFinanciero.findByDescripcion", query = "SELECT d FROM DestinoFinanciero d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DestinoFinanciero.findByEliminado", query = "SELECT d FROM DestinoFinanciero d WHERE d.eliminado = :eliminado")})
public class DestinoFinanciero implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "DestinoFinanciero.findByEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CODIGO_OC")
    private String codigoOc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    public DestinoFinanciero() {
    }

    public DestinoFinanciero(Long codigo) {
        this.codigo = codigo;
    }

    public DestinoFinanciero(Long codigo, String codigoOc, String nombre, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.codigoOc = codigoOc;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCodigoOc() {
        return codigoOc;
    }

    public void setCodigoOc(String codigoOc) {
        this.codigoOc = codigoOc;
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
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
        if (!(object instanceof DestinoFinanciero)) {
            return false;
        }
        DestinoFinanciero other = (DestinoFinanciero) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.DestinoFinanciero[ codigo=" + codigo + " ]";
    }
    
}
