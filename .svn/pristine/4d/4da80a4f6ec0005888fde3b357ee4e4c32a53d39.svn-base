/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import ec.renafipse.mks.modelo.comunes.Moneda;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_IFIPS.IFIP_MONEDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfipMoneda.findAll", query = "SELECT i FROM IfipMoneda i"),
    @NamedQuery(name = "IfipMoneda.findByCodigoMoneda", query = "SELECT i FROM IfipMoneda i WHERE i.ifipMonedaPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "IfipMoneda.findByCodigoIfip", query = "SELECT i FROM IfipMoneda i WHERE i.ifipMonedaPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "IfipMoneda.findByRegistradoPor", query = "SELECT i FROM IfipMoneda i WHERE i.registradoPor = :registradoPor"),
    @NamedQuery(name = "IfipMoneda.findByFechaRegistro", query = "SELECT i FROM IfipMoneda i WHERE i.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "IfipMoneda.findByEliminado", query = "SELECT i FROM IfipMoneda i WHERE i.eliminado = :eliminado"),
    @NamedQuery(name = "IfipMoneda.findByCodigoIfipEliminado", query = "SELECT m FROM IfipMoneda i JOIN i.moneda m WHERE i.ifipMonedaPK.codigoIfip = :codigoIfip AND i.eliminado = :eliminado AND m.eliminado = :eliminado"),
})
public class IfipMoneda implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoIfipEliminado = "IfipMoneda.findByCodigoIfipEliminado";
    @EmbeddedId
    protected IfipMonedaPK ifipMonedaPK;
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
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip ifip;
    @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda;

    public IfipMoneda() {
    }

    public IfipMoneda(IfipMonedaPK ifipMonedaPK) {
        this.ifipMonedaPK = ifipMonedaPK;
    }

    public IfipMoneda(IfipMonedaPK ifipMonedaPK, long registradoPor, Date fechaRegistro, char eliminado) {
        this.ifipMonedaPK = ifipMonedaPK;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public IfipMoneda(long codigoMoneda, long codigoIfip) {
        this.ifipMonedaPK = new IfipMonedaPK(codigoMoneda, codigoIfip);
    }

    public IfipMonedaPK getIfipMonedaPK() {
        return ifipMonedaPK;
    }

    public void setIfipMonedaPK(IfipMonedaPK ifipMonedaPK) {
        this.ifipMonedaPK = ifipMonedaPK;
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

    public Ifip getIfip() {
        return ifip;
    }

    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ifipMonedaPK != null ? ifipMonedaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfipMoneda)) {
            return false;
        }
        IfipMoneda other = (IfipMoneda) object;
        if ((this.ifipMonedaPK == null && other.ifipMonedaPK != null) || (this.ifipMonedaPK != null && !this.ifipMonedaPK.equals(other.ifipMonedaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.IfipMoneda[ ifipMonedaPK=" + ifipMonedaPK + " ]";
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
    
}
