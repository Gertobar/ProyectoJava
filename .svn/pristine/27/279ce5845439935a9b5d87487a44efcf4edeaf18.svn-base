/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TIPO_NOTIFICACION_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNotificacionIfip.findAll", query = "SELECT t FROM TipoNotificacionIfip t"),
    @NamedQuery(name = "TipoNotificacionIfip.findByCodigoTipoNotificacion", query = "SELECT t FROM TipoNotificacionIfip t WHERE t.tipoNotificacionIfipPK.codigoTipoNotificacion = :codigoTipoNotificacion"),
    @NamedQuery(name = "TipoNotificacionIfip.findByCodigoIfip", query = "SELECT t FROM TipoNotificacionIfip t WHERE t.tipoNotificacionIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "TipoNotificacionIfip.findByOrdenCobro", query = "SELECT t FROM TipoNotificacionIfip t WHERE t.ordenCobro = :ordenCobro"),
    @NamedQuery(name = "TipoNotificacionIfip.findByValor", query = "SELECT t FROM TipoNotificacionIfip t WHERE t.valor = :valor"),
    @NamedQuery(name = "TipoNotificacionIfip.findByTexto", query = "SELECT t FROM TipoNotificacionIfip t WHERE t.texto = :texto"),
    @NamedQuery(name = "TipoNotificacionIfip.findByRegistradoPor", query = "SELECT t FROM TipoNotificacionIfip t WHERE t.usuarioRegistradoPor = :usuarioRegistradoPor"),
    @NamedQuery(name = "TipoNotificacionIfip.findByFechaRegistro", query = "SELECT t FROM TipoNotificacionIfip t WHERE t.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "TipoNotificacionIfip.findByEliminado", query = "SELECT t FROM TipoNotificacionIfip t WHERE t.eliminado = :eliminado"),
//Personalizadas
    @NamedQuery(name = "TipoNotificacionIfip.findByTipoEliminado", query = "SELECT t FROM TipoNotificacionIfip t JOIN t.tipoNotificacion n WHERE t.tipoNotificacionIfipPK.codigoIfip = :codigoIfip AND t.eliminado = :eliminado AND n.eliminado = :eliminado ORDER BY t.ordenCobro ASC") 
        
})
public class TipoNotificacionIfip implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByTipoEliminado = "TipoNotificacionIfip.findByTipoEliminado";
    public static final String findByCodigoTipoNotificacion="TipoNotificacionIfip.findByCodigoTipoNotificacion";
    @EmbeddedId
    protected TipoNotificacionIfipPK tipoNotificacionIfipPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN_COBRO")
    private short ordenCobro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "TEXTO")
    private String texto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoNotificacionIfip", fetch = FetchType.LAZY)
    private Collection<NotificacionCredito> notificacionCreditoCollection;
    @JoinColumn(name = "CODIGO_TIPO_NOTIFICACION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoNotificacion tipoNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR", insertable = false, updatable = false)
    private Long usuarioRegistradoPor;

    public TipoNotificacionIfip() {
    }

    public TipoNotificacionIfip(TipoNotificacionIfipPK tipoNotificacionIfipPK) {
        this.tipoNotificacionIfipPK = tipoNotificacionIfipPK;
    }

    public TipoNotificacionIfip(TipoNotificacionIfipPK tipoNotificacionIfipPK, short ordenCobro, BigDecimal valor, String texto, long usuarioRegistradoPor, Date fechaRegistro, char eliminado) {
        this.tipoNotificacionIfipPK = tipoNotificacionIfipPK;
        this.ordenCobro = ordenCobro;
        this.valor = valor;
        this.texto = texto;
        this.usuarioRegistradoPor = usuarioRegistradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public TipoNotificacionIfip(long codigoTipoNotificacion, long codigoIfip) {
        this.tipoNotificacionIfipPK = new TipoNotificacionIfipPK(codigoTipoNotificacion, codigoIfip);
    }

    public TipoNotificacionIfipPK getTipoNotificacionIfipPK() {
        return tipoNotificacionIfipPK;
    }

    public void setTipoNotificacionIfipPK(TipoNotificacionIfipPK tipoNotificacionIfipPK) {
        this.tipoNotificacionIfipPK = tipoNotificacionIfipPK;
    }

    public short getOrdenCobro() {
        return ordenCobro;
    }

    public void setOrdenCobro(short ordenCobro) {
        this.ordenCobro = ordenCobro;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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
    public Collection<NotificacionCredito> getNotificacionCreditoCollection() {
        return notificacionCreditoCollection;
    }

    public void setNotificacionCreditoCollection(Collection<NotificacionCredito> notificacionCreditoCollection) {
        this.notificacionCreditoCollection = notificacionCreditoCollection;
    }

    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoNotificacionIfipPK != null ? tipoNotificacionIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNotificacionIfip)) {
            return false;
        }
        TipoNotificacionIfip other = (TipoNotificacionIfip) object;
        if ((this.tipoNotificacionIfipPK == null && other.tipoNotificacionIfipPK != null) || (this.tipoNotificacionIfipPK != null && !this.tipoNotificacionIfipPK.equals(other.tipoNotificacionIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoNotificacionIfip[ tipoNotificacionIfipPK=" + tipoNotificacionIfipPK + " ]";
    }

    /**
     * @return the usuarioRegistradoPor
     */
    public Long getUsuarioRegistradoPor() {
        return usuarioRegistradoPor;
    }

    /**
     * @param usuarioRegistradoPor the usuarioRegistradoPor to set
     */
    public void setUsuarioRegistradoPor(Long usuarioRegistradoPor) {
        this.usuarioRegistradoPor = usuarioRegistradoPor;
    }
}