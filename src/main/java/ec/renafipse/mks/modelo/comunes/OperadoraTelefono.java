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
@Table(name = "MKS_COMUNES.OPERADORA_TELEFONO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OperadoraTelefono.findAll", query = "SELECT o FROM OperadoraTelefono o"),
    @NamedQuery(name = "OperadoraTelefono.findByCodigo", query = "SELECT o FROM OperadoraTelefono o WHERE o.codigo = :codigo"),
    @NamedQuery(name = "OperadoraTelefono.findByNombre", query = "SELECT o FROM OperadoraTelefono o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "OperadoraTelefono.findBySiglas", query = "SELECT o FROM OperadoraTelefono o WHERE o.siglas = :siglas"),
    @NamedQuery(name = "OperadoraTelefono.findByEliminado", query = "SELECT o FROM OperadoraTelefono o WHERE o.eliminado = :eliminado ORDER BY o.nombre")})
public class OperadoraTelefono implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "OperadoraTelefono.findByEliminado";
    @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_OPERADORA_TELEFONO")
    @SequenceGenerator(name = "GSQ_OPERADORA_TELEFONO", schema = "MKS_COMUNES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_OPERADORA_TELEFONO")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    public OperadoraTelefono() {
    }

    public OperadoraTelefono(Long codigo) {
        this.codigo = codigo;
    }

    public OperadoraTelefono(Long codigo, String nombre, String siglas, char eliminado) {
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
        if (!(object instanceof OperadoraTelefono)) {
            return false;
        }
        OperadoraTelefono other = (OperadoraTelefono) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.comunes.OperadoraTelefono[ codigo=" + codigo + " ]";
    }
    
}
