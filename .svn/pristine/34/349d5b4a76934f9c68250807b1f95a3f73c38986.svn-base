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
@Table(name = "MKS_CONTABLES.PERIODO_CONTABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoContable.findAll", query = "SELECT p FROM PeriodoContable p ORDER BY p.codigo DESC"),
    @NamedQuery(name = "PeriodoContable.findByCodigo", query = "SELECT p FROM PeriodoContable p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "PeriodoContable.findByFechaInicio", query = "SELECT p FROM PeriodoContable p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "PeriodoContable.findByFechaFin", query = "SELECT p FROM PeriodoContable p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "PeriodoContable.findByActual", query = "SELECT p FROM PeriodoContable p WHERE p.actual = :actual"),
    @NamedQuery(name = "PeriodoContable.findByPeriodosContables", query = "SELECT p FROM PeriodoContable p ORDER BY p.codigo DESC")
})
public class PeriodoContable implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByActual = "PeriodoContable.findByActual";
    public static final String findByPeriodosContables = "PeriodoContable.findByPeriodosContables";
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTUAL")
    private char actual;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPeriodo", fetch = FetchType.LAZY)
    private Collection<Comprobante> comprobanteCollection;

    public PeriodoContable() {
    }

    public PeriodoContable(String codigo) {
        this.codigo = codigo;
    }

    public PeriodoContable(String codigo, Date fechaInicio, Date fechaFin, char actual) {
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.actual = actual;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public char getActual() {
        return actual;
    }

    public void setActual(char actual) {
        this.actual = actual;
    }
   

    @XmlTransient
    public Collection<Comprobante> getComprobanteCollection() {
        return comprobanteCollection;
    }

    public void setComprobanteCollection(Collection<Comprobante> comprobanteCollection) {
        this.comprobanteCollection = comprobanteCollection;
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
        if (!(object instanceof PeriodoContable)) {
            return false;
        }
        PeriodoContable other = (PeriodoContable) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.PeriodoContable[ codigo=" + codigo + " ]";
    }
    
}
