/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

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
@Table(name = "MKS_AHORROS.TIPO_MOTIVO_CAMBIO_CHEQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMotivoCambioCheque.findAll", query = "SELECT t FROM TipoMotivoCambioCheque t"),
    @NamedQuery(name = "TipoMotivoCambioCheque.findByCodigo", query = "SELECT t FROM TipoMotivoCambioCheque t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoMotivoCambioCheque.findByNombre", query = "SELECT t FROM TipoMotivoCambioCheque t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoMotivoCambioCheque.findByDescripcion", query = "SELECT t FROM TipoMotivoCambioCheque t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoMotivoCambioCheque.findByEliminado", query = "SELECT t FROM TipoMotivoCambioCheque t WHERE t.eliminado = :eliminado ORDER BY t.nombre")})
public class TipoMotivoCambioCheque implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "TipoMotivoCambioCheque.findByEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    
    public TipoMotivoCambioCheque() {
    }

    public TipoMotivoCambioCheque(Long codigo) {
        this.codigo = codigo;
    }

    public TipoMotivoCambioCheque(Long codigo, String nombre, String descripcion, char eliminado) {
        this.codigo = codigo;
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
        if (!(object instanceof TipoMotivoCambioCheque)) {
            return false;
        }
        TipoMotivoCambioCheque other = (TipoMotivoCambioCheque) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.ahorros.TipoMotivoCambioCheque[ codigo=" + codigo + " ]";
    }
    
}
