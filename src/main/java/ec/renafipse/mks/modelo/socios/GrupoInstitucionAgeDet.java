/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_SOCIOS.GRUPO_INSTITUCION_AGE_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoInstitucionAgeDet.findAll", query = "SELECT g FROM GrupoInstitucionAgeDet g"),
    @NamedQuery(name = "GrupoInstitucionAgeDet.findByCodigoGrupo", query = "SELECT g FROM GrupoInstitucionAgeDet g WHERE g.grupoInstitucionAgeDetPK.codigoGrupo = :codigoGrupo"),
    @NamedQuery(name = "GrupoInstitucionAgeDet.findByCodigoSocio", query = "SELECT g FROM GrupoInstitucionAgeDet g WHERE g.grupoInstitucionAgeDetPK.codigoSocio = :codigoSocio"),
    @NamedQuery(name = "GrupoInstitucionAgeDet.findByCodigoIfip", query = "SELECT g FROM GrupoInstitucionAgeDet g WHERE g.grupoInstitucionAgeDetPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "GrupoInstitucionAgeDet.findByValorAporte", query = "SELECT g FROM GrupoInstitucionAgeDet g WHERE g.valorAporte = :valorAporte"),
    @NamedQuery(name = "GrupoInstitucionAgeDet.findByRegistradoPor", query = "SELECT g FROM GrupoInstitucionAgeDet g WHERE g.registradoPor = :registradoPor"),
    @NamedQuery(name = "GrupoInstitucionAgeDet.findByFechaRegistro", query = "SELECT g FROM GrupoInstitucionAgeDet g WHERE g.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "GrupoInstitucionAgeDet.findByEliminado", query = "SELECT g FROM GrupoInstitucionAgeDet g WHERE g.eliminado = :eliminado"),
    // Personalizados
    @NamedQuery(name = "GrupoInstitucionAgeDet.findByCodigoSocioCodigoIfip", query = "SELECT g FROM GrupoInstitucionAgeDet g WHERE g.grupoInstitucionAgeDetPK.codigoSocio = :codigoSocio AND g.grupoInstitucionAgeDetPK.codigoIfip = :codigoIfip ORDER BY g.grupoInstitucionAgeDetPK.codigoGrupo")
})
public class GrupoInstitucionAgeDet implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoSocioCodigoIfip= "GrupoInstitucionAgeDet.findByCodigoSocioCodigoIfip";
    @EmbeddedId
    protected GrupoInstitucionAgeDetPK grupoInstitucionAgeDetPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_APORTE")
    private BigDecimal valorAporte;
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
     @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_SOCIO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Socio socio;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_GRUPO", referencedColumnName = "CODIGO_GRUPO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO_IFIP_AGENCIA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private GrupoInstitucionIfipAgencia grupoInstitucionIfipAgencia;

    public GrupoInstitucionAgeDet() {
    }

    public GrupoInstitucionAgeDet(GrupoInstitucionAgeDetPK grupoInstitucionAgeDetPK) {
        this.grupoInstitucionAgeDetPK = grupoInstitucionAgeDetPK;
    }

    public GrupoInstitucionAgeDet(GrupoInstitucionAgeDetPK grupoInstitucionAgeDetPK, BigDecimal valorAporte, long registradoPor, Date fechaRegistro, char eliminado) {
        this.grupoInstitucionAgeDetPK = grupoInstitucionAgeDetPK;
        this.valorAporte = valorAporte;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public GrupoInstitucionAgeDet(long codigoGrupo, long codigoSocio, long codigoIfip) {
        this.grupoInstitucionAgeDetPK = new GrupoInstitucionAgeDetPK(codigoGrupo, codigoSocio, codigoIfip);
    }

    public GrupoInstitucionAgeDetPK getGrupoInstitucionAgeDetPK() {
        return grupoInstitucionAgeDetPK;
    }

    public void setGrupoInstitucionAgeDetPK(GrupoInstitucionAgeDetPK grupoInstitucionAgeDetPK) {
        this.grupoInstitucionAgeDetPK = grupoInstitucionAgeDetPK;
    }

    public BigDecimal getValorAporte() {
        return valorAporte;
    }

    public void setValorAporte(BigDecimal valorAporte) {
        this.valorAporte = valorAporte;
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

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public GrupoInstitucionIfipAgencia getGrupoInstitucionIfipAgencia() {
        return grupoInstitucionIfipAgencia;
    }

    public void setGrupoInstitucionIfipAgencia(GrupoInstitucionIfipAgencia grupoInstitucionIfipAgencia) {
        this.grupoInstitucionIfipAgencia = grupoInstitucionIfipAgencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupoInstitucionAgeDetPK != null ? grupoInstitucionAgeDetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoInstitucionAgeDet)) {
            return false;
        }
        GrupoInstitucionAgeDet other = (GrupoInstitucionAgeDet) object;
        if ((this.grupoInstitucionAgeDetPK == null && other.grupoInstitucionAgeDetPK != null) || (this.grupoInstitucionAgeDetPK != null && !this.grupoInstitucionAgeDetPK.equals(other.grupoInstitucionAgeDetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDet[ grupoInstitucionAgeDetPK=" + grupoInstitucionAgeDetPK + " ]";
    }

    /**
     * @return the codigoIfipAgencia
     */
    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    /**
     * @param codigoIfipAgencia the codigoIfipAgencia to set
     */
    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }
    
}
