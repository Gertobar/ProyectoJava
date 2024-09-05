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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MKS_COMUNES.ENTIDAD_FINANCIERA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntidadFinanciera.findAll", query = "SELECT e FROM EntidadFinanciera e"),
    @NamedQuery(name = "EntidadFinanciera.findByCodigo", query = "SELECT e FROM EntidadFinanciera e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EntidadFinanciera.findByNombre", query = "SELECT e FROM EntidadFinanciera e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EntidadFinanciera.findByEliminado", query = "SELECT e FROM EntidadFinanciera e WHERE e.eliminado = :eliminado ORDER BY e.nombre")})
public class EntidadFinanciera implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "EntidadFinanciera.findByEliminado";
    public static final String findByCodigo = "EntidadFinanciera.findByCodigo";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_ENTIDAD_FINANCIERA")
    @SequenceGenerator(name = "GSQ_ENTIDAD_FINANCIERA", schema = "MKS_COMUNES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_ENTIDAD_FINANCIERA")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TIPO_ENTIDAD", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoEntidadFinanciera codigoTipoEntidad;
    
    public EntidadFinanciera() {
    }

    public EntidadFinanciera(Long codigo) {
        this.codigo = codigo;
    }

    public EntidadFinanciera(Long codigo, String nombre, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public TipoEntidadFinanciera getCodigoTipoEntidad() {
        return codigoTipoEntidad;
    }

    public void setCodigoTipoEntidad(TipoEntidadFinanciera codigoTipoEntidad) {
        this.codigoTipoEntidad = codigoTipoEntidad;
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
        if (!(object instanceof EntidadFinanciera)) {
            return false;
        }
        EntidadFinanciera other = (EntidadFinanciera) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.comunes.EntidadFinanciera[ codigo=" + codigo + " ]";
    }

}
