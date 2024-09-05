/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import ec.renafipse.mks.modelo.comunes.Periodicidad;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.PERIODO_EFECTIVIZACION_CHEQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoEfectivizacionCheque.findAll", query = "SELECT p FROM PeriodoEfectivizacionCheque p"),
    @NamedQuery(name = "PeriodoEfectivizacionCheque.findByCodigo", query = "SELECT p FROM PeriodoEfectivizacionCheque p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "PeriodoEfectivizacionCheque.findByCodigoPeriodicidad", query = "SELECT p FROM PeriodoEfectivizacionCheque p WHERE p.codigoPeriodicidad = :codigoPeriodicidad"),
    @NamedQuery(name = "PeriodoEfectivizacionCheque.findByNombre", query = "SELECT p FROM PeriodoEfectivizacionCheque p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PeriodoEfectivizacionCheque.findByValor", query = "SELECT p FROM PeriodoEfectivizacionCheque p WHERE p.valor = :valor"),
    @NamedQuery(name = "PeriodoEfectivizacionCheque.findByEliminado", query = "SELECT p FROM PeriodoEfectivizacionCheque p WHERE p.eliminado = :eliminado")})
public class PeriodoEfectivizacionCheque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PERIODO_EFECTIVIZACION_CHE")
    @SequenceGenerator(name = "GSQ_PERIODO_EFECTIVIZACION_CHE", schema = "MKS_CAJAS",  allocationSize = 0, sequenceName = "SEQ_PERIODO_EFECTIVIZACION_CHE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERIODICIDAD")
    private long codigoPeriodicidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private long valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_PERIODICIDAD", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Periodicidad periodicidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPeriodo", fetch = FetchType.LAZY)
    
    private Collection<PeriodoEfeCheEntFin> periodoEfeCheEntFinCollection;

    public PeriodoEfectivizacionCheque() {
    }

    public PeriodoEfectivizacionCheque(Long codigo) {
        this.codigo = codigo;
    }

    public PeriodoEfectivizacionCheque(Long codigo, long codigoPeriodicidad, String nombre, long valor, char eliminado) {
        this.codigo = codigo;
        this.codigoPeriodicidad = codigoPeriodicidad;
        this.nombre = nombre;
        this.valor = valor;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoPeriodicidad() {
        return codigoPeriodicidad;
    }

    public void setCodigoPeriodicidad(long codigoPeriodicidad) {
        this.codigoPeriodicidad = codigoPeriodicidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<PeriodoEfeCheEntFin> getPeriodoEfeCheEntFinCollection() {
        return periodoEfeCheEntFinCollection;
    }

    public void setPeriodoEfeCheEntFinCollection(Collection<PeriodoEfeCheEntFin> periodoEfeCheEntFinCollection) {
        this.periodoEfeCheEntFinCollection = periodoEfeCheEntFinCollection;
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
        if (!(object instanceof PeriodoEfectivizacionCheque)) {
            return false;
        }
        PeriodoEfectivizacionCheque other = (PeriodoEfectivizacionCheque) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.PeriodoEfectivizacionCheque[ codigo=" + codigo + " ]";
    }

    /**
     * @return the periodicidad
     */
    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    /**
     * @param periodicidad the periodicidad to set
     */
    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }
    
}
