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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_COMUNES.TIPO_CUENTA_ENTIDAD_FINANCIERA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCuentaEntidadFinanciera.findAll", query = "SELECT t FROM TipoCuentaEntidadFinanciera t"),
    @NamedQuery(name = "TipoCuentaEntidadFinanciera.findByCodigo", query = "SELECT t FROM TipoCuentaEntidadFinanciera t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoCuentaEntidadFinanciera.findByNombre", query = "SELECT t FROM TipoCuentaEntidadFinanciera t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoCuentaEntidadFinanciera.findBySiglas", query = "SELECT t FROM TipoCuentaEntidadFinanciera t WHERE t.siglas = :siglas"),
    @NamedQuery(name = "TipoCuentaEntidadFinanciera.findByEliminado", query = "SELECT t FROM TipoCuentaEntidadFinanciera t WHERE t.eliminado = :eliminado")})
public class TipoCuentaEntidadFinanciera implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "TipoCuentaEntidadFinanciera.findByEliminado";
    public static final String findByCodigo = "TipoCuentaEntidadFinanciera.findByCodigo";
    @Id
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
    @Column(name = "SIGLAS")
    private char siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    public TipoCuentaEntidadFinanciera() {
    }

    public TipoCuentaEntidadFinanciera(Long codigo) {
        this.codigo = codigo;
    }

    public TipoCuentaEntidadFinanciera(Long codigo, String nombre, char siglas, char eliminado) {
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

    public char getSiglas() {
        return siglas;
    }

    public void setSiglas(char siglas) {
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
        if (!(object instanceof TipoCuentaEntidadFinanciera)) {
            return false;
        }
        TipoCuentaEntidadFinanciera other = (TipoCuentaEntidadFinanciera) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera[ codigo=" + codigo + " ]";
    }
    
}
