/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.SOCIO_MENOR_EDAD_REP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SocioMenorEdadRep.findAll", query = "SELECT s FROM SocioMenorEdadRep s"),
    @NamedQuery(name = "SocioMenorEdadRep.findByCodigoSocio", query = "SELECT s FROM SocioMenorEdadRep s WHERE s.socioMenorEdadRepPK.codigoSocio = :codigoSocio"),
    @NamedQuery(name = "SocioMenorEdadRep.findByCodigoIfip", query = "SELECT s FROM SocioMenorEdadRep s WHERE s.socioMenorEdadRepPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "SocioMenorEdadRep.findByFechaIngreso", query = "SELECT s FROM SocioMenorEdadRep s WHERE s.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "SocioMenorEdadRep.findByFechaActualizacion", query = "SELECT s FROM SocioMenorEdadRep s WHERE s.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "SocioMenorEdadRep.findByEliminado", query = "SELECT s FROM SocioMenorEdadRep s WHERE s.eliminado = :eliminado")})
public class SocioMenorEdadRep implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SocioMenorEdadRepPK socioMenorEdadRepPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TIPO_RELACION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoRelacion codigoTipoRelacion;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_SOCIO_REP", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Socio socio;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_SOCIO", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Socio socio1;
    @JoinColumn(name = "CODIGO_PERSONA_REP", referencedColumnName = "CODIGO_PERSONA")
    @ManyToOne(optional = false)
    private PersonaNatural codigoPersonaRep;

    public SocioMenorEdadRep() {
    }

    public SocioMenorEdadRep(SocioMenorEdadRepPK socioMenorEdadRepPK) {
        this.socioMenorEdadRepPK = socioMenorEdadRepPK;
    }

    public SocioMenorEdadRep(SocioMenorEdadRepPK socioMenorEdadRepPK, Date fechaIngreso, Date fechaActualizacion, char eliminado) {
        this.socioMenorEdadRepPK = socioMenorEdadRepPK;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
        this.eliminado = eliminado;
    }

    public SocioMenorEdadRep(long codigoSocio, long codigoIfip) {
        this.socioMenorEdadRepPK = new SocioMenorEdadRepPK(codigoSocio, codigoIfip);
    }

    public SocioMenorEdadRepPK getSocioMenorEdadRepPK() {
        return socioMenorEdadRepPK;
    }

    public void setSocioMenorEdadRepPK(SocioMenorEdadRepPK socioMenorEdadRepPK) {
        this.socioMenorEdadRepPK = socioMenorEdadRepPK;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public TipoRelacion getCodigoTipoRelacion() {
        return codigoTipoRelacion;
    }

    public void setCodigoTipoRelacion(TipoRelacion codigoTipoRelacion) {
        this.codigoTipoRelacion = codigoTipoRelacion;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Socio getSocio1() {
        return socio1;
    }

    public void setSocio1(Socio socio1) {
        this.socio1 = socio1;
    }

    public PersonaNatural getCodigoPersonaRep() {
        return codigoPersonaRep;
    }

    public void setCodigoPersonaRep(PersonaNatural codigoPersonaRep) {
        this.codigoPersonaRep = codigoPersonaRep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (socioMenorEdadRepPK != null ? socioMenorEdadRepPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioMenorEdadRep)) {
            return false;
        }
        SocioMenorEdadRep other = (SocioMenorEdadRep) object;
        if ((this.socioMenorEdadRepPK == null && other.socioMenorEdadRepPK != null) || (this.socioMenorEdadRepPK != null && !this.socioMenorEdadRepPK.equals(other.socioMenorEdadRepPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SocioMenorEdadRep[ socioMenorEdadRepPK=" + socioMenorEdadRepPK + " ]";
    }
    
}
