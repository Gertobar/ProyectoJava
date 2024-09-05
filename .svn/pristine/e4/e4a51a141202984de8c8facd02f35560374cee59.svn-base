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
 * @author FliaAstudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.ORIGEN_RECURSOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrigenRecursos.findAll", query = "SELECT o FROM OrigenRecursos o"),
    @NamedQuery(name = "OrigenRecursos.findByCodigo", query = "SELECT o FROM OrigenRecursos o WHERE o.codigo = :codigo"),
    @NamedQuery(name = "OrigenRecursos.findByCodigoOc", query = "SELECT o FROM OrigenRecursos o WHERE o.codigoOc = :codigoOc"),
    @NamedQuery(name = "OrigenRecursos.findByNombre", query = "SELECT o FROM OrigenRecursos o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "OrigenRecursos.findByEliminado", query = "SELECT o FROM OrigenRecursos o WHERE o.eliminado = :eliminado ORDER BY o.nombre")})
public class OrigenRecursos implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "OrigenRecursos.findByEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CODIGO_OC")
    private String codigoOc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    public OrigenRecursos() {
    }

    public OrigenRecursos(Long codigo) {
        this.codigo = codigo;
    }

    public OrigenRecursos(Long codigo, String codigoOc, String nombre, char eliminado) {
        this.codigo = codigo;
        this.codigoOc = codigoOc;
        this.nombre = nombre;
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
        if (!(object instanceof OrigenRecursos)) {
            return false;
        }
        OrigenRecursos other = (OrigenRecursos) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.OrigenRecursos[ codigo=" + codigo + " ]";
    }
    
}
