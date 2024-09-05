/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_SOCIOS.GRUPO_INSTITUCION_IFIP_AGENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoInstitucionIfipAgencia.findAll", query = "SELECT g FROM GrupoInstitucionIfipAgencia g"),
    @NamedQuery(name = "GrupoInstitucionIfipAgencia.findByCodigoGrupo", query = "SELECT g FROM GrupoInstitucionIfipAgencia g WHERE g.grupoInstitucionIfipAgenciaPK.codigoGrupo = :codigoGrupo"),
    @NamedQuery(name = "GrupoInstitucionIfipAgencia.findByCodigoIfipAgencia", query = "SELECT g FROM GrupoInstitucionIfipAgencia g WHERE g.grupoInstitucionIfipAgenciaPK.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "GrupoInstitucionIfipAgencia.findByGeneraDescuentoRol", query = "SELECT g FROM GrupoInstitucionIfipAgencia g WHERE g.generaDescuentoRol = :generaDescuentoRol"),
    @NamedQuery(name = "GrupoInstitucionIfipAgencia.findByAcreditaSueldos", query = "SELECT g FROM GrupoInstitucionIfipAgencia g WHERE g.acreditaSueldos = :acreditaSueldos"),
    @NamedQuery(name = "GrupoInstitucionIfipAgencia.findByCreditosGrupales", query = "SELECT g FROM GrupoInstitucionIfipAgencia g WHERE g.creditosGrupales = :creditosGrupales"),
    @NamedQuery(name = "GrupoInstitucionIfipAgencia.findByRegistradoPor", query = "SELECT g FROM GrupoInstitucionIfipAgencia g WHERE g.registradoPor = :registradoPor"),
    @NamedQuery(name = "GrupoInstitucionIfipAgencia.findByFechaRegistro", query = "SELECT g FROM GrupoInstitucionIfipAgencia g WHERE g.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "GrupoInstitucionIfipAgencia.findByEliminado", query = "SELECT g FROM GrupoInstitucionIfipAgencia g WHERE g.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "GrupoInstitucionIfipAgencia.findByGrupoIfipAgenciaEliminado", query = "SELECT g FROM GrupoInstitucionIfipAgencia g JOIN g.grupoInstitucion r WHERE g.grupoInstitucionIfipAgenciaPK.codigoIfipAgencia = :codigoIfipAgencia AND g.eliminado = :eliminado AND r.eliminado = :eliminado")
})
public class GrupoInstitucionIfipAgencia implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByGrupoIfipAgenciaEliminado = "GrupoInstitucionIfipAgencia.findByGrupoIfipAgenciaEliminado";
    @EmbeddedId
    protected GrupoInstitucionIfipAgenciaPK grupoInstitucionIfipAgenciaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GENERA_DESCUENTO_ROL")
    private char generaDescuentoRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACREDITA_SUELDOS")
    private char acreditaSueldos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREDITOS_GRUPALES")
    private char creditosGrupales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_GRUPO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GrupoInstitucion grupoInstitucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoInstitucionIfipAgencia", fetch = FetchType.LAZY)
    private Collection<GrupoInstitucionAgeDet> grupoInstitucionAgeDetCollection;

    public GrupoInstitucionIfipAgencia() {
    }

    public GrupoInstitucionIfipAgencia(GrupoInstitucionIfipAgenciaPK grupoInstitucionIfipAgenciaPK) {
        this.grupoInstitucionIfipAgenciaPK = grupoInstitucionIfipAgenciaPK;
    }

    public GrupoInstitucionIfipAgencia(GrupoInstitucionIfipAgenciaPK grupoInstitucionIfipAgenciaPK, char generaDescuentoRol, char acreditaSueldos, char creditosGrupales, long registradoPor, Date fechaRegistro, char eliminado) {
        this.grupoInstitucionIfipAgenciaPK = grupoInstitucionIfipAgenciaPK;
        this.generaDescuentoRol = generaDescuentoRol;
        this.acreditaSueldos = acreditaSueldos;
        this.creditosGrupales = creditosGrupales;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public GrupoInstitucionIfipAgencia(long codigoGrupo, long codigoIfipAgencia) {
        this.grupoInstitucionIfipAgenciaPK = new GrupoInstitucionIfipAgenciaPK(codigoGrupo, codigoIfipAgencia);
    }

    public GrupoInstitucionIfipAgenciaPK getGrupoInstitucionIfipAgenciaPK() {
        return grupoInstitucionIfipAgenciaPK;
    }

    public void setGrupoInstitucionIfipAgenciaPK(GrupoInstitucionIfipAgenciaPK grupoInstitucionIfipAgenciaPK) {
        this.grupoInstitucionIfipAgenciaPK = grupoInstitucionIfipAgenciaPK;
    }

    public char getGeneraDescuentoRol() {
        return generaDescuentoRol;
    }

    public void setGeneraDescuentoRol(char generaDescuentoRol) {
        this.generaDescuentoRol = generaDescuentoRol;
    }

    public char getAcreditaSueldos() {
        return acreditaSueldos;
    }

    public void setAcreditaSueldos(char acreditaSueldos) {
        this.acreditaSueldos = acreditaSueldos;
    }

    public char getCreditosGrupales() {
        return creditosGrupales;
    }

    public void setCreditosGrupales(char creditosGrupales) {
        this.creditosGrupales = creditosGrupales;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public GrupoInstitucion getGrupoInstitucion() {
        return grupoInstitucion;
    }

    public void setGrupoInstitucion(GrupoInstitucion grupoInstitucion) {
        this.grupoInstitucion = grupoInstitucion;
    }

    @XmlTransient
    public Collection<GrupoInstitucionAgeDet> getGrupoInstitucionAgeDetCollection() {
        return grupoInstitucionAgeDetCollection;
    }

    public void setGrupoInstitucionAgeDetCollection(Collection<GrupoInstitucionAgeDet> grupoInstitucionAgeDetCollection) {
        this.grupoInstitucionAgeDetCollection = grupoInstitucionAgeDetCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupoInstitucionIfipAgenciaPK != null ? grupoInstitucionIfipAgenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoInstitucionIfipAgencia)) {
            return false;
        }
        GrupoInstitucionIfipAgencia other = (GrupoInstitucionIfipAgencia) object;
        if ((this.grupoInstitucionIfipAgenciaPK == null && other.grupoInstitucionIfipAgenciaPK != null) || (this.grupoInstitucionIfipAgenciaPK != null && !this.grupoInstitucionIfipAgenciaPK.equals(other.grupoInstitucionIfipAgenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgencia[ grupoInstitucionIfipAgenciaPK=" + grupoInstitucionIfipAgenciaPK + " ]";
    }
    
}
