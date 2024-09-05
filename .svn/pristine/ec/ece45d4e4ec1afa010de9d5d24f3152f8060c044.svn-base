/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.GRUPO_CUENTA_CONTABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoCuentaContable.findAll", query = "SELECT g FROM GrupoCuentaContable g"),
    @NamedQuery(name = "GrupoCuentaContable.findByCodigo", query = "SELECT g FROM GrupoCuentaContable g WHERE g.codigo = :codigo"),
    @NamedQuery(name = "GrupoCuentaContable.findByNombre", query = "SELECT g FROM GrupoCuentaContable g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GrupoCuentaContable.findByMostrarEnBg", query = "SELECT g FROM GrupoCuentaContable g WHERE g.mostrarEnBg = :mostrarEnBg"),
    @NamedQuery(name = "GrupoCuentaContable.findByMostrarEnEr", query = "SELECT g FROM GrupoCuentaContable g WHERE g.mostrarEnEr = :mostrarEnEr"),
    @NamedQuery(name = "GrupoCuentaContable.findByMostrarEnBc", query = "SELECT g FROM GrupoCuentaContable g WHERE g.mostrarEnBc = :mostrarEnBc"),
    @NamedQuery(name = "GrupoCuentaContable.findByEliminado", query = "SELECT g FROM GrupoCuentaContable g WHERE g.eliminado = :eliminado")})
public class GrupoCuentaContable implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado="GrupoCuentaContable.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_GRUPO_CUENTA_CONTABLE")
    @SequenceGenerator(name = "GSQ_GRUPO_CUENTA_CONTABLE", schema = "MKS_CONTABLES",  allocationSize = 0, sequenceName = "SEQ_GRUPO_CUENTA_CONTABLE")        
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
    @Column(name = "MOSTRAR_EN_BG")
    private char mostrarEnBg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOSTRAR_EN_ER")
    private char mostrarEnEr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOSTRAR_EN_BC")
    private char mostrarEnBc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoGrupo", fetch = FetchType.LAZY)
    private Collection<PlanDeCuenta> planDeCuentaCollection;

    public GrupoCuentaContable() {
    }

    public GrupoCuentaContable(Long codigo) {
        this.codigo = codigo;
    }

    public GrupoCuentaContable(Long codigo, String nombre, char mostrarEnBg, char mostrarEnEr, char mostrarEnBc, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.mostrarEnBg = mostrarEnBg;
        this.mostrarEnEr = mostrarEnEr;
        this.mostrarEnBc = mostrarEnBc;
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

    public char getMostrarEnBg() {
        return mostrarEnBg;
    }

    public void setMostrarEnBg(char mostrarEnBg) {
        this.mostrarEnBg = mostrarEnBg;
    }

    public char getMostrarEnEr() {
        return mostrarEnEr;
    }

    public void setMostrarEnEr(char mostrarEnEr) {
        this.mostrarEnEr = mostrarEnEr;
    }

    public char getMostrarEnBc() {
        return mostrarEnBc;
    }

    public void setMostrarEnBc(char mostrarEnBc) {
        this.mostrarEnBc = mostrarEnBc;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<PlanDeCuenta> getPlanDeCuentaCollection() {
        return planDeCuentaCollection;
    }

    public void setPlanDeCuentaCollection(Collection<PlanDeCuenta> planDeCuentaCollection) {
        this.planDeCuentaCollection = planDeCuentaCollection;
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
        if (!(object instanceof GrupoCuentaContable)) {
            return false;
        }
        GrupoCuentaContable other = (GrupoCuentaContable) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.GrupoCuentaContable[ codigo=" + codigo + " ]";
    }
    
}
