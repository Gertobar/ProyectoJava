/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.TIPO_PLAN_DE_CUENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPlanDeCuenta.findAll", query = "SELECT t FROM TipoPlanDeCuenta t"),
    @NamedQuery(name = "TipoPlanDeCuenta.findByCodigo", query = "SELECT t FROM TipoPlanDeCuenta t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoPlanDeCuenta.findByNombre", query = "SELECT t FROM TipoPlanDeCuenta t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoPlanDeCuenta.findBySiglas", query = "SELECT t FROM TipoPlanDeCuenta t WHERE t.siglas = :siglas"),
    @NamedQuery(name = "TipoPlanDeCuenta.findByVigente", query = "SELECT t FROM TipoPlanDeCuenta t WHERE t.vigente = :vigente"),
    @NamedQuery(name = "TipoPlanDeCuenta.findByEliminado", query = "SELECT t FROM TipoPlanDeCuenta t WHERE t.eliminado = :eliminado"),
    //Personalizadas
    @NamedQuery(name = "TipoPlanDeCuenta.findByEliminadoVigente", query = "SELECT t FROM TipoPlanDeCuenta t WHERE t.eliminado = :eliminado AND t.vigente = :vigente"),
    @NamedQuery(name = "TipoPlanDeCuenta.findByFechaVigencia", query = "SELECT t FROM TipoPlanDeCuenta t WHERE :fechaInicio BETWEEN t.fechaInicioVigencia AND t.fechaFinVigencia AND :fechaFin BETWEEN t.fechaInicioVigencia AND t.fechaFinVigencia")

})
public class TipoPlanDeCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminadoVigente="TipoPlanDeCuenta.findByEliminadoVigente";
    public static final String findByVigente="TipoPlanDeCuenta.findByVigente";
    public static final String findByFechaVigencia="TipoPlanDeCuenta.findByFechaVigencia";
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
    @Size(min = 1, max = 5)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENTE")
    private char vigente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinVigencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoPlanDeCuenta", fetch = FetchType.LAZY)
    private Collection<PlanDeCuenta> planDeCuentaCollection;

    public TipoPlanDeCuenta() {
    }

    public TipoPlanDeCuenta(Long codigo) {
        this.codigo = codigo;
    }

    public TipoPlanDeCuenta(Long codigo, String nombre, String siglas, char vigente, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
        this.vigente = vigente;
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

    public char getVigente() {
        return vigente;
    }

    public void setVigente(char vigente) {
        this.vigente = vigente;
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
        if (!(object instanceof TipoPlanDeCuenta)) {
            return false;
        }
        TipoPlanDeCuenta other = (TipoPlanDeCuenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.TipoPlanDeCuenta[ codigo=" + codigo + " ]";
    }

    /**
     * @return the fechaInicioVigencia
     */
    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    /**
     * @param fechaInicioVigencia the fechaInicioVigencia to set
     */
    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    /**
     * @return the fechaFinVigencia
     */
    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    /**
     * @param fechaFinVigencia the fechaFinVigencia to set
     */
    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }
    
}
