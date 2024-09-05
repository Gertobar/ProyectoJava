/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import ec.renafipse.mks.modelo.ifips.Ifip;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "MKS_SOCIOS.TIPO_PERSONA_OBSERVABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPersonaObservable.findAll", query = "SELECT t FROM TipoPersonaObservable t"),
    @NamedQuery(name = "TipoPersonaObservable.findByCodigo", query = "SELECT t FROM TipoPersonaObservable t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoPersonaObservable.findByCodigoIfip", query = "SELECT t FROM TipoPersonaObservable t WHERE t.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "TipoPersonaObservable.findByNombre", query = "SELECT t FROM TipoPersonaObservable t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoPersonaObservable.findByNombretabla", query = "SELECT t FROM TipoPersonaObservable t WHERE t.nombretabla = :nombretabla"),
    @NamedQuery(name = "TipoPersonaObservable.findByElinimado", query = "SELECT t FROM TipoPersonaObservable t WHERE t.elinimado = :elinimado")})
public class TipoPersonaObservable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRETABLA")
    private String nombretabla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELINIMADO")
    private char elinimado;

    public TipoPersonaObservable() {
    }

    public TipoPersonaObservable(Long codigo) {
        this.codigo = codigo;
    }

    public TipoPersonaObservable(Long codigo, Ifip codigoIfip, String nombre, String nombretabla, char elinimado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.nombre = nombre;
        this.nombretabla = nombretabla;
        this.elinimado = elinimado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombretabla() {
        return nombretabla;
    }

    public void setNombretabla(String nombretabla) {
        this.nombretabla = nombretabla;
    }

    public char getElinimado() {
        return elinimado;
    }

    public void setElinimado(char elinimado) {
        this.elinimado = elinimado;
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
        if (!(object instanceof TipoPersonaObservable)) {
            return false;
        }
        TipoPersonaObservable other = (TipoPersonaObservable) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.TipoPersonaObservable[ codigo=" + codigo + " ]";
    }
    
}
