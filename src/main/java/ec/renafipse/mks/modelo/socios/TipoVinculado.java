/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author mvks
 */
@Entity
@Table(name = "TIPO_VINCULADO", catalog = "", schema = "MKS_SOCIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVinculado.findAll", query = "SELECT t FROM TipoVinculado t")
    , @NamedQuery(name = "TipoVinculado.findByCodigo", query = "SELECT t FROM TipoVinculado t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoVinculado.findByRol", query = "SELECT t FROM TipoVinculado t WHERE t.rol = :rol")
    , @NamedQuery(name = "TipoVinculado.findByGrado", query = "SELECT t FROM TipoVinculado t WHERE t.grado = :grado")
    , @NamedQuery(name = "TipoVinculado.findByTipo", query = "SELECT t FROM TipoVinculado t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "TipoVinculado.findByRegistradoPor", query = "SELECT t FROM TipoVinculado t WHERE t.registradoPor = :registradoPor")
    , @NamedQuery(name = "TipoVinculado.findByFechaRegistro", query = "SELECT t FROM TipoVinculado t WHERE t.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "TipoVinculado.findByEliminado", query = "SELECT t FROM TipoVinculado t WHERE t.eliminado = :eliminado")})
public class TipoVinculado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO", nullable = false)
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ROL", nullable = false, length = 32)
    private String rol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRADO", nullable = false)
    private Short grado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO", nullable = false)
    private Character tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR", nullable = false)
    private Long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO", nullable = false)
    private Character eliminado;
    @Column(name = "RECIPROCO_FEMENINO")
    private Long codigoFemenino;
    @Column(name = "RECIPROCO_MASCULINO")
    private Long codigoMasculino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoVinculado", fetch = FetchType.LAZY)
    private List<PersonaVinculado> personaVinculadoList;
    @OneToMany(mappedBy = "reciprocoFemenino", fetch = FetchType.LAZY)
    private List<TipoVinculado> tipoVinculadoList;
    @JoinColumn(name = "RECIPROCO_FEMENINO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoVinculado reciprocoFemenino;
    @OneToMany(mappedBy = "reciprocoMasculino", fetch = FetchType.LAZY)
    private List<TipoVinculado> tipoVinculadoList1;
    @JoinColumn(name = "RECIPROCO_MASCULINO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoVinculado reciprocoMasculino;

    public TipoVinculado() {
    }

    public TipoVinculado(Long codigo) {
        this.codigo = codigo;
    }

    public TipoVinculado(Long codigo, String rol) {
        this.codigo = codigo;
        this.rol = rol;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public Long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(Long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Character getEliminado() {
        return eliminado;
    }

    public void setEliminado(Character eliminado) {
        this.eliminado = eliminado;
    }

    public Long getCodigoFemenino() {
        return codigoFemenino;
    }

    public void setCodigoFemenino(Long codigoFemenino) {
        this.codigoFemenino = codigoFemenino;
    }

    public Long getCodigoMasculino() {
        return codigoMasculino;
    }

    public void setCodigoMasculino(Long codigoMasculino) {
        this.codigoMasculino = codigoMasculino;
    }

    @XmlTransient
    public List<PersonaVinculado> getPersonaVinculadoList() {
        return personaVinculadoList;
    }

    public void setPersonaVinculadoList(List<PersonaVinculado> personaVinculadoList) {
        this.personaVinculadoList = personaVinculadoList;
    }

    @XmlTransient
    public List<TipoVinculado> getTipoVinculadoList() {
        return tipoVinculadoList;
    }

    public void setTipoVinculadoList(List<TipoVinculado> tipoVinculadoList) {
        this.tipoVinculadoList = tipoVinculadoList;
    }

    public TipoVinculado getReciprocoFemenino() {
        return reciprocoFemenino;
    }

    public void setReciprocoFemenino(TipoVinculado reciprocoFemenino) {
        this.reciprocoFemenino = reciprocoFemenino;
    }

    @XmlTransient
    public List<TipoVinculado> getTipoVinculadoList1() {
        return tipoVinculadoList1;
    }

    public void setTipoVinculadoList1(List<TipoVinculado> tipoVinculadoList1) {
        this.tipoVinculadoList1 = tipoVinculadoList1;
    }

    public TipoVinculado getReciprocoMasculino() {
        return reciprocoMasculino;
    }

    public void setReciprocoMasculino(TipoVinculado reciprocoMasculino) {
        this.reciprocoMasculino = reciprocoMasculino;
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
        if (!(object instanceof TipoVinculado)) {
            return false;
        }
        TipoVinculado other = (TipoVinculado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.TipoVinculado[ codigo=" + codigo + " ]";
    }

}
