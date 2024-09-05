/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_IFIPS.IFIP_AGENCIA_SECUENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfipAgenciaSecuencia.findAll", query = "SELECT i FROM IfipAgenciaSecuencia i"),
    @NamedQuery(name = "IfipAgenciaSecuencia.findByCodigoIfipAgencia", query = "SELECT i FROM IfipAgenciaSecuencia i WHERE i.ifipAgenciaSecuenciaPK.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "IfipAgenciaSecuencia.findByCodigoSecuencia", query = "SELECT i FROM IfipAgenciaSecuencia i WHERE i.ifipAgenciaSecuenciaPK.codigoSecuencia = :codigoSecuencia"),
    @NamedQuery(name = "IfipAgenciaSecuencia.findByValor", query = "SELECT i FROM IfipAgenciaSecuencia i WHERE i.valor = :valor")})
public class IfipAgenciaSecuencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IfipAgenciaSecuenciaPK ifipAgenciaSecuenciaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private long valor;
    @JoinColumn(name = "CODIGO_SECUENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Secuencia secuencia;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipAgencia ifipAgencia;

    public IfipAgenciaSecuencia() {
    }

    public IfipAgenciaSecuencia(IfipAgenciaSecuenciaPK ifipAgenciaSecuenciaPK) {
        this.ifipAgenciaSecuenciaPK = ifipAgenciaSecuenciaPK;
    }

    public IfipAgenciaSecuencia(IfipAgenciaSecuenciaPK ifipAgenciaSecuenciaPK, long valor) {
        this.ifipAgenciaSecuenciaPK = ifipAgenciaSecuenciaPK;
        this.valor = valor;
    }

    public IfipAgenciaSecuencia(long codigoIfipAgencia, long codigoSecuencia) {
        this.ifipAgenciaSecuenciaPK = new IfipAgenciaSecuenciaPK(codigoIfipAgencia, codigoSecuencia);
    }

    public IfipAgenciaSecuenciaPK getIfipAgenciaSecuenciaPK() {
        return ifipAgenciaSecuenciaPK;
    }

    public void setIfipAgenciaSecuenciaPK(IfipAgenciaSecuenciaPK ifipAgenciaSecuenciaPK) {
        this.ifipAgenciaSecuenciaPK = ifipAgenciaSecuenciaPK;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public Secuencia getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Secuencia secuencia) {
        this.secuencia = secuencia;
    }

    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ifipAgenciaSecuenciaPK != null ? ifipAgenciaSecuenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfipAgenciaSecuencia)) {
            return false;
        }
        IfipAgenciaSecuencia other = (IfipAgenciaSecuencia) object;
        if ((this.ifipAgenciaSecuenciaPK == null && other.ifipAgenciaSecuenciaPK != null) || (this.ifipAgenciaSecuenciaPK != null && !this.ifipAgenciaSecuenciaPK.equals(other.ifipAgenciaSecuenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.IfipAgenciaSecuencia[ ifipAgenciaSecuenciaPK=" + ifipAgenciaSecuenciaPK + " ]";
    }
    
}
