/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TIPO_GARANTIA_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoGarantiaCredito.findAll", query = "SELECT t FROM TipoGarantiaCredito t"),
    @NamedQuery(name = "TipoGarantiaCredito.findByNumeroCredito", query = "SELECT t FROM TipoGarantiaCredito t WHERE t.tipoGarantiaCreditoPK.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "TipoGarantiaCredito.findByCodigoIfip", query = "SELECT t FROM TipoGarantiaCredito t WHERE t.tipoGarantiaCreditoPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "TipoGarantiaCredito.findByCodigoTipoGarantia", query = "SELECT t FROM TipoGarantiaCredito t WHERE t.tipoGarantiaCreditoPK.codigoTipoGarantia = :codigoTipoGarantia"),
    @NamedQuery(name = "TipoGarantiaCredito.findByRegistradoPor", query = "SELECT t FROM TipoGarantiaCredito t WHERE t.registradoPor = :registradoPor"),
    @NamedQuery(name = "TipoGarantiaCredito.findByFechaRegistro", query = "SELECT t FROM TipoGarantiaCredito t WHERE t.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "TipoGarantiaCredito.findByEliminado", query = "SELECT t FROM TipoGarantiaCredito t WHERE t.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "TipoGarantiaCredito.findByNumCreditoIfipEliminado", query = "SELECT t FROM TipoGarantiaCredito t WHERE t.tipoGarantiaCreditoPK.numeroCredito = :numeroCredito AND t.tipoGarantiaCreditoPK.codigoIfip = :codigoIfip AND t.eliminado = :eliminado")
})
public class TipoGarantiaCredito implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByNumCreditoIfipEliminado = "TipoGarantiaCredito.findByNumCreditoIfipEliminado";
    @EmbeddedId
    protected TipoGarantiaCreditoPK tipoGarantiaCreditoPK;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoGarantiaCredito", fetch = FetchType.LAZY)
    private Collection<GaranteCredito> garanteCreditoCollection;
    @JoinColumn(name = "CODIGO_TIPO_GARANTIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoGarantiaProCreMonIfi tipoGarantiaProCreMonIfi;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Solicitud solicitud;
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioRegistradoPor;
    
    public TipoGarantiaCredito() {
    }

    public TipoGarantiaCredito(TipoGarantiaCreditoPK tipoGarantiaCreditoPK) {
        this.tipoGarantiaCreditoPK = tipoGarantiaCreditoPK;
    }

    public TipoGarantiaCredito(TipoGarantiaCreditoPK tipoGarantiaCreditoPK, long registradoPor, Date fechaRegistro, char eliminado) {
        this.tipoGarantiaCreditoPK = tipoGarantiaCreditoPK;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public TipoGarantiaCredito(long numeroCredito, long codigoIfip, long codigoTipoGarantia) {
        this.tipoGarantiaCreditoPK = new TipoGarantiaCreditoPK(numeroCredito, codigoIfip, codigoTipoGarantia);
    }

    public TipoGarantiaCreditoPK getTipoGarantiaCreditoPK() {
        return tipoGarantiaCreditoPK;
    }

    public void setTipoGarantiaCreditoPK(TipoGarantiaCreditoPK tipoGarantiaCreditoPK) {
        this.tipoGarantiaCreditoPK = tipoGarantiaCreditoPK;
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

    @XmlTransient
    public Collection<GaranteCredito> getGaranteCreditoCollection() {
        return garanteCreditoCollection;
    }

    public void setGaranteCreditoCollection(Collection<GaranteCredito> garanteCreditoCollection) {
        this.garanteCreditoCollection = garanteCreditoCollection;
    }

    public TipoGarantiaProCreMonIfi getTipoGarantiaProCreMonIfi() {
        return tipoGarantiaProCreMonIfi;
    }

    public void setTipoGarantiaProCreMonIfi(TipoGarantiaProCreMonIfi tipoGarantiaProCreMonIfi) {
        this.tipoGarantiaProCreMonIfi = tipoGarantiaProCreMonIfi;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoGarantiaCreditoPK != null ? tipoGarantiaCreditoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoGarantiaCredito)) {
            return false;
        }
        TipoGarantiaCredito other = (TipoGarantiaCredito) object;
        if ((this.tipoGarantiaCreditoPK == null && other.tipoGarantiaCreditoPK != null) || (this.tipoGarantiaCreditoPK != null && !this.tipoGarantiaCreditoPK.equals(other.tipoGarantiaCreditoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoGarantiaCredito[ tipoGarantiaCreditoPK=" + tipoGarantiaCreditoPK + " ]";
    }

    /**
     * @return the usuarioRegistradoPor
     */
    public Usuario getUsuarioRegistradoPor() {
        return usuarioRegistradoPor;
    }

    /**
     * @param usuarioRegistradoPor the usuarioRegistradoPor to set
     */
    public void setUsuarioRegistradoPor(Usuario usuarioRegistradoPor) {
        this.usuarioRegistradoPor = usuarioRegistradoPor;
    }
    
}
