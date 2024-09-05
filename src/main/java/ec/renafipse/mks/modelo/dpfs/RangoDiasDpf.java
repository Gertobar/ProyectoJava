/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_DPFS.RANGO_DIAS_DPF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RangoDiasDpf.findAll", query = "SELECT r FROM RangoDiasDpf r"),
    @NamedQuery(name = "RangoDiasDpf.findByCodigo", query = "SELECT r FROM RangoDiasDpf r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "RangoDiasDpf.findByRangoInicial", query = "SELECT r FROM RangoDiasDpf r WHERE r.rangoInicial = :rangoInicial"),
    @NamedQuery(name = "RangoDiasDpf.findByRangoFinal", query = "SELECT r FROM RangoDiasDpf r WHERE r.rangoFinal = :rangoFinal"),
    @NamedQuery(name = "RangoDiasDpf.findByEliminado", query = "SELECT r FROM RangoDiasDpf r WHERE r.eliminado = :eliminado")})
public class RangoDiasDpf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_RANGO_DIAS_DPF")
    @SequenceGenerator(name = "GSQ_RANGO_DIAS_DPF", schema = "MKS_DPFS",  allocationSize = 0, sequenceName = "SEQ_RANGO_DIAS_DPF")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RANGO_INICIAL")
    private long rangoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RANGO_FINAL")
    private long rangoFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoRango", fetch = FetchType.LAZY)
    private Collection<TasaInteresProDpfMon> tasaInteresProDpfMonCollection;

    public RangoDiasDpf() {
    }

    public RangoDiasDpf(Long codigo) {
        this.codigo = codigo;
    }

    public RangoDiasDpf(Long codigo, long rangoInicial, long rangoFinal, char eliminado) {
        this.codigo = codigo;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(long rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public long getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(long rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<TasaInteresProDpfMon> getTasaInteresProDpfMonCollection() {
        return tasaInteresProDpfMonCollection;
    }

    public void setTasaInteresProDpfMonCollection(Collection<TasaInteresProDpfMon> tasaInteresProDpfMonCollection) {
        this.tasaInteresProDpfMonCollection = tasaInteresProDpfMonCollection;
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
        if (!(object instanceof RangoDiasDpf)) {
            return false;
        }
        RangoDiasDpf other = (RangoDiasDpf) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.RangoDiasDpf[ codigo=" + codigo + " ]";
    }
    
}
