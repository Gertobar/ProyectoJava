/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.comunes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_COMUNES.TIPO_TELEFONO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTelefono.findAll", query = "SELECT t FROM TipoTelefono t"),
    @NamedQuery(name = "TipoTelefono.findByCodigo", query = "SELECT t FROM TipoTelefono t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoTelefono.findByNombre", query = "SELECT t FROM TipoTelefono t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoTelefono.findBySiglas", query = "SELECT t FROM TipoTelefono t WHERE t.siglas = :siglas"),
    @NamedQuery(name = "TipoTelefono.findByEliminado", query = "SELECT t FROM TipoTelefono t WHERE t.eliminado = :eliminado")})
public class TipoTelefono implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "TipoTelefono.findByEliminado";
    @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_TELEFONO")
    @SequenceGenerator(name = "GSQ_TIPO_TELEFONO", schema = "MKS_COMUNES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_TIPO_TELEFONO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    public TipoTelefono() {
    }

    public TipoTelefono(Long codigo) {
        this.codigo = codigo;
    }

    public TipoTelefono(Long codigo, String nombre, String siglas, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
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

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
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
        if (!(object instanceof TipoTelefono)) {
            return false;
        }
        TipoTelefono other = (TipoTelefono) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.comunes.TipoTelefono[ codigo=" + codigo + " ]";
    }
    
}
