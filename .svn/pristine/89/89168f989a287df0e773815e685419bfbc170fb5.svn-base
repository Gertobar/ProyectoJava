/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

import ec.renafipse.mks.modelo.ifips.Ifip;
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
@Table(name = "MKS_SEGURIDADES.IFIP_SISTEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfipSistema.findAll", query = "SELECT i FROM IfipSistema i"),
    @NamedQuery(name = "IfipSistema.findByCodigoIfip", query = "SELECT i FROM IfipSistema i WHERE i.ifipSistemaPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "IfipSistema.findByCodigoSistema", query = "SELECT i FROM IfipSistema i WHERE i.ifipSistemaPK.codigoSistema = :codigoSistema"),
    @NamedQuery(name = "IfipSistema.findByFechaRegistro", query = "SELECT i FROM IfipSistema i WHERE i.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "IfipSistema.findByEliminado", query = "SELECT i FROM IfipSistema i WHERE i.eliminado = :eliminado")})
public class IfipSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IfipSistemaPK ifipSistemaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario registradoPor;
    @JoinColumn(name = "CODIGO_SISTEMA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sistema sistema;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip ifip;

    public IfipSistema() {
    }

    public IfipSistema(IfipSistemaPK ifipSistemaPK) {
        this.ifipSistemaPK = ifipSistemaPK;
    }

    public IfipSistema(IfipSistemaPK ifipSistemaPK, Date fechaRegistro, char eliminado) {
        this.ifipSistemaPK = ifipSistemaPK;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public IfipSistema(long codigoIfip, long codigoSistema) {
        this.ifipSistemaPK = new IfipSistemaPK(codigoIfip, codigoSistema);
    }

    public IfipSistemaPK getIfipSistemaPK() {
        return ifipSistemaPK;
    }

    public void setIfipSistemaPK(IfipSistemaPK ifipSistemaPK) {
        this.ifipSistemaPK = ifipSistemaPK;
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

    public Usuario getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(Usuario registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ifipSistemaPK != null ? ifipSistemaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfipSistema)) {
            return false;
        }
        IfipSistema other = (IfipSistema) object;
        if ((this.ifipSistemaPK == null && other.ifipSistemaPK != null) || (this.ifipSistemaPK != null && !this.ifipSistemaPK.equals(other.ifipSistemaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.IfipSistema[ ifipSistemaPK=" + ifipSistemaPK + " ]";
    }

    /**
     * @return the ifip
     */
    public Ifip getIfip() {
        return ifip;
    }

    /**
     * @param ifip the ifip to set
     */
    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }


}
