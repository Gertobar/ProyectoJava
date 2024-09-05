/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "MKS_HISTORICOS.UAF_CARGA")
@XmlRootElement
public class UafCarga implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_UAF_CARGA")
    @SequenceGenerator(name = "GSQ_UAF_CARGA", schema = "MKS_HISTORICOS",  allocationSize = 0, sequenceName = "SEQ_UAF_CARGA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
    @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario codigoUsuario;
    @JoinColumn(name = "CODIGO_TIPO_PERSONA_OBS", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoPersonaObservable codigoTipoPersonaObs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_REGISTROS")
    private long totalRegistros;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CORTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCorte;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoUafCarga")
    private List<UafHomonimo> uafHomonimoList;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoUafCarga")
    private List<UafPersonaPoliticamenteExp> uafPersonaPoliticamenteExpList;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoUafCarga")
    private List<UafSentenciado> uafSentenciadoList;

    public UafCarga() {
    }

    public UafCarga(Long codigo) {
        this.codigo = codigo;
    }

    public UafCarga(Long codigo, Ifip codigoIfip, Usuario codigoUsuario, TipoPersonaObservable codigoTipoPersonaObs, long totalRegistros, String observaciones, Date fechaCorte) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.codigoUsuario = codigoUsuario;
        this.codigoTipoPersonaObs = codigoTipoPersonaObs;
        this.totalRegistros = totalRegistros;
        this.observaciones = observaciones;
        this.fechaCorte = fechaCorte;
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

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public TipoPersonaObservable getCodigoTipoPersonaObs() {
        return codigoTipoPersonaObs;
    }

    public void setCodigoTipoPersonaObs(TipoPersonaObservable codigoTipoPersonaObs) {
        this.codigoTipoPersonaObs = codigoTipoPersonaObs;
    }

    public long getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(long totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    @XmlTransient
    public List<UafHomonimo> getUafHomonimoList() {
        return uafHomonimoList;
    }

    public void setUafHomonimoList(List<UafHomonimo> uafHomonimoList) {
        this.uafHomonimoList = uafHomonimoList;
    }

    @XmlTransient
    public List<UafPersonaPoliticamenteExp> getUafPersonaPoliticamenteExpList() {
        return uafPersonaPoliticamenteExpList;
    }

    public void setUafPersonaPoliticamenteExpList(List<UafPersonaPoliticamenteExp> uafPersonaPoliticamenteExpList) {
        this.uafPersonaPoliticamenteExpList = uafPersonaPoliticamenteExpList;
    }

    @XmlTransient
    public List<UafSentenciado> getUafSentenciadoList() {
        return uafSentenciadoList;
    }

    public void setUafSentenciadoList(List<UafSentenciado> uafSentenciadoList) {
        this.uafSentenciadoList = uafSentenciadoList;
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
        if (!(object instanceof UafCarga)) {
            return false;
        }
        UafCarga other = (UafCarga) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.UafCarga[ codigo=" + codigo + " ]";
    }
    
}