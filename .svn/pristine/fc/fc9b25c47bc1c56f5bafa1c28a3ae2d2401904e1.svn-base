/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ifips;

import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.seguridades.Usuario;
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
import javax.persistence.JoinColumns;
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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_IFIPS.ENTIDAD_FINANCIERA_IFI_AGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntidadFinancieraIfiAge.findAll", query = "SELECT e FROM EntidadFinancieraIfiAge e"),
    @NamedQuery(name = "EntidadFinancieraIfiAge.findByCodigoEntidadFinanciera", query = "SELECT e FROM EntidadFinancieraIfiAge e WHERE e.entidadFinancieraIfiAgePK.codigoEntidadFinanciera = :codigoEntidadFinanciera"),
    @NamedQuery(name = "EntidadFinancieraIfiAge.findByCodigoIfipAgencia", query = "SELECT e FROM EntidadFinancieraIfiAge e WHERE e.entidadFinancieraIfiAgePK.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "EntidadFinancieraIfiAge.findByIngresadoPor", query = "SELECT e FROM EntidadFinancieraIfiAge e WHERE e.ingresadoPor = :ingresadoPor"),
    @NamedQuery(name = "EntidadFinancieraIfiAge.findByFechaIngreso", query = "SELECT e FROM EntidadFinancieraIfiAge e WHERE e.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "EntidadFinancieraIfiAge.findByEliminado", query = "SELECT e FROM EntidadFinancieraIfiAge e WHERE e.eliminado = :eliminado"),
//Personalizados
    @NamedQuery(name = "EntidadFinancieraIfiAge.findByEntFinIfiAgenEliminado", query = "SELECT ef FROM EntidadFinancieraIfiAge e JOIN e.ifipAgencia i JOIN e.entidadFinanciera ef JOIN ef.codigoTipoEntidad tef WHERE i.codigo = :codigoIfiAge AND e.eliminado = :eliminado AND i.eliminado = :eliminado AND ef.eliminado = :eliminado AND tef.eliminado = :eliminado"),
    @NamedQuery(name = "EntidadFinancieraIfiAge.findByEntidadFinanIfip", query = "SELECT e FROM EntidadFinancieraIfiAge e join e.ifipAgencia i WHERE i.codigoIfip.codigo = :codigoIfip"),})

public class EntidadFinancieraIfiAge implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByEntFinIfiAgenEliminado = "EntidadFinancieraIfiAge.findByEntFinIfiAgenEliminado";
    public static final String findByEntidadFinanIfip = "EntidadFinancieraIfiAge.findByEntidadFinanIfip";


    @EmbeddedId
    protected EntidadFinancieraIfiAgePK entidadFinancieraIfiAgePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INGRESADO_POR")
    private long ingresadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipAgencia ifipAgencia;
    @JoinColumn(name = "CODIGO_ENTIDAD_FINANCIERA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EntidadFinanciera entidadFinanciera;
    @JoinColumns({
        @JoinColumn(name = "INGRESADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Usuario usuarioIngresadoPor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidadFinancieraIfiAge", fetch = FetchType.LAZY)
    private Collection<IfipAgenciaCuentaEntFin> ifipAgenciaCuentaEntFinCollection;

    public EntidadFinancieraIfiAge() {
    }

    public EntidadFinancieraIfiAge(EntidadFinancieraIfiAgePK entidadFinancieraIfiAgePK) {
        this.entidadFinancieraIfiAgePK = entidadFinancieraIfiAgePK;
    }

    public EntidadFinancieraIfiAge(EntidadFinancieraIfiAgePK entidadFinancieraIfiAgePK, long ingresadoPor, Date fechaIngreso, char eliminado) {
        this.entidadFinancieraIfiAgePK = entidadFinancieraIfiAgePK;
        this.ingresadoPor = ingresadoPor;
        this.fechaIngreso = fechaIngreso;
        this.eliminado = eliminado;
    }

    public EntidadFinancieraIfiAge(long codigoEntidadFinanciera, long codigoIfipAgencia) {
        this.entidadFinancieraIfiAgePK = new EntidadFinancieraIfiAgePK(codigoEntidadFinanciera, codigoIfipAgencia);
    }

    public EntidadFinancieraIfiAgePK getEntidadFinancieraIfiAgePK() {
        return entidadFinancieraIfiAgePK;
    }

    public void setEntidadFinancieraIfiAgePK(EntidadFinancieraIfiAgePK entidadFinancieraIfiAgePK) {
        this.entidadFinancieraIfiAgePK = entidadFinancieraIfiAgePK;
    }

    public long getIngresadoPor() {
        return ingresadoPor;
    }

    public void setIngresadoPor(long ingresadoPor) {
        this.ingresadoPor = ingresadoPor;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }

    @XmlTransient
    public Collection<IfipAgenciaCuentaEntFin> getIfipAgenciaCuentaEntFinCollection() {
        return ifipAgenciaCuentaEntFinCollection;
    }

    public void setIfipAgenciaCuentaEntFinCollection(Collection<IfipAgenciaCuentaEntFin> ifipAgenciaCuentaEntFinCollection) {
        this.ifipAgenciaCuentaEntFinCollection = ifipAgenciaCuentaEntFinCollection;
    }

    public Usuario getUsuarioIngresadoPor() {
        return usuarioIngresadoPor;
    }

    public void setUsuarioIngresadoPor(Usuario usuarioIngresadoPor) {
        this.usuarioIngresadoPor = usuarioIngresadoPor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entidadFinancieraIfiAgePK != null ? entidadFinancieraIfiAgePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntidadFinancieraIfiAge)) {
            return false;
        }
        EntidadFinancieraIfiAge other = (EntidadFinancieraIfiAge) object;
        if ((this.entidadFinancieraIfiAgePK == null && other.entidadFinancieraIfiAgePK != null) || (this.entidadFinancieraIfiAgePK != null && !this.entidadFinancieraIfiAgePK.equals(other.entidadFinancieraIfiAgePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.EntidadFinancieraIfiAge[ entidadFinancieraIfiAgePK=" + entidadFinancieraIfiAgePK + " ]";
    }

    /**
     * @return the entidadFinanciera
     */
    public EntidadFinanciera getEntidadFinanciera() {
        return entidadFinanciera;
    }

    /**
     * @param entidadFinanciera the entidadFinanciera to set
     */
    public void setEntidadFinanciera(EntidadFinanciera entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

}
