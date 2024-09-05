/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FliaAstudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.PARAMETRO_GENERAL_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroGeneralCredito.findAll", query = "SELECT p FROM ParametroGeneralCredito p"),
    @NamedQuery(name = "ParametroGeneralCredito.findByCodigo", query = "SELECT p FROM ParametroGeneralCredito p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ParametroGeneralCredito.findByNombre", query = "SELECT p FROM ParametroGeneralCredito p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ParametroGeneralCredito.findByDescripcion", query = "SELECT p FROM ParametroGeneralCredito p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ParametroGeneralCredito.findByEliminado", query = "SELECT p FROM ParametroGeneralCredito p WHERE p.eliminado = :eliminado")})
public class ParametroGeneralCredito implements Serializable {
    private static final long serialVersionUID = 1L;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametroGeneralCredito")
    private Collection<ParametroGeneralCreditoIfip> parametroGeneralCreditoIfipCollection;

    public ParametroGeneralCredito() {
    }

    public ParametroGeneralCredito(Long codigo) {
        this.codigo = codigo;
    }

    public ParametroGeneralCredito(Long codigo, String nombre, String descripcion, char eliminado) {
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

    @XmlTransient
    public Collection<ParametroGeneralCreditoIfip> getParametroGeneralCreditoIfipCollection() {
        return parametroGeneralCreditoIfipCollection;
    }

    public void setParametroGeneralCreditoIfipCollection(Collection<ParametroGeneralCreditoIfip> parametroGeneralCreditoIfipCollection) {
        this.parametroGeneralCreditoIfipCollection = parametroGeneralCreditoIfipCollection;
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
        if (!(object instanceof ParametroGeneralCredito)) {
            return false;
        }
        ParametroGeneralCredito other = (ParametroGeneralCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ParametroGeneralCredito[ codigo=" + codigo + " ]";
    }
    
}
