/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_DPFS.CUENTA_CONTRATO_DPF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaContratoDpf.findAll", query = "SELECT c FROM CuentaContratoDpf c"),
    @NamedQuery(name = "CuentaContratoDpf.findByCodigoContrato", query = "SELECT c FROM CuentaContratoDpf c WHERE c.cuentaContratoDpfPK.codigoContrato = :codigoContrato"),
    @NamedQuery(name = "CuentaContratoDpf.findByCodigoIfip", query = "SELECT c FROM CuentaContratoDpf c WHERE c.cuentaContratoDpfPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "CuentaContratoDpf.findByCodigoCuentaCredito", query = "SELECT c FROM CuentaContratoDpf c WHERE c.codigoCuentaCredito = :codigoCuentaCredito"),
    @NamedQuery(name = "CuentaContratoDpf.findByCodigoCuentaDebito", query = "SELECT c FROM CuentaContratoDpf c WHERE c.codigoCuentaDebito = :codigoCuentaDebito"),
    @NamedQuery(name = "CuentaContratoDpf.findByRegistradoPor", query = "SELECT c FROM CuentaContratoDpf c WHERE c.registradoPor = :registradoPor"),
    @NamedQuery(name = "CuentaContratoDpf.findByFechaRegistro", query = "SELECT c FROM CuentaContratoDpf c WHERE c.fechaRegistro = :fechaRegistro"),
    //Personalizado
    @NamedQuery(name = "CuentaContratoDpf.findByCodigoContratoCodigoIfip", query = "SELECT c FROM CuentaContratoDpf c WHERE c.cuentaContratoDpfPK.codigoContrato = :codigoContrato AND c.cuentaContratoDpfPK.codigoIfip = :codigoIfip"),    
    @NamedQuery(name = "CuentaContratoDpf.findByEliminado", query = "SELECT c FROM CuentaContratoDpf c WHERE c.eliminado = :eliminado"),    
})


public class CuentaContratoDpf implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoContratoCodigoIfip = "CuentaContratoDpf.findByCodigoContratoCodigoIfip";
    @EmbeddedId
    protected CuentaContratoDpfPK cuentaContratoDpfPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_CREDITO")
    private long codigoCuentaCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_DEBITO")
    private long codigoCuentaDebito;
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
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CONTRATO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ContratoDpf contratoDpf;
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO",insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioRealizadoPor;
    @JoinColumn(name = "CODIGO_CUENTA_CREDITO", referencedColumnName = "CODIGO",insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta  cuentaCredito;
    @JoinColumn(name = "CODIGO_CUENTA_DEBITO", referencedColumnName = "CODIGO",insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta  cuentaDebito;
    
    public CuentaContratoDpf() {
    }

    public CuentaContratoDpf(CuentaContratoDpfPK cuentaContratoDpfPK) {
        this.cuentaContratoDpfPK = cuentaContratoDpfPK;
    }

    public CuentaContratoDpf(CuentaContratoDpfPK cuentaContratoDpfPK, long codigoCuentaCredito, long codigoCuentaDebito, long registradoPor, Date fechaRegistro, char eliminado) {
        this.cuentaContratoDpfPK = cuentaContratoDpfPK;
        this.codigoCuentaCredito = codigoCuentaCredito;
        this.codigoCuentaDebito = codigoCuentaDebito;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public CuentaContratoDpf(long codigoContrato, long codigoIfip) {
        this.cuentaContratoDpfPK = new CuentaContratoDpfPK(codigoContrato, codigoIfip);
    }

    public CuentaContratoDpfPK getCuentaContratoDpfPK() {
        return cuentaContratoDpfPK;
    }

    public void setCuentaContratoDpfPK(CuentaContratoDpfPK cuentaContratoDpfPK) {
        this.cuentaContratoDpfPK = cuentaContratoDpfPK;
    }

    public long getCodigoCuentaCredito() {
        return codigoCuentaCredito;
    }

    public void setCodigoCuentaCredito(long codigoCuentaCredito) {
        this.codigoCuentaCredito = codigoCuentaCredito;
    }

    public long getCodigoCuentaDebito() {
        return codigoCuentaDebito;
    }

    public void setCodigoCuentaDebito(long codigoCuentaDebito) {
        this.codigoCuentaDebito = codigoCuentaDebito;
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

    public ContratoDpf getContratoDpf() {
        return contratoDpf;
    }

    public void setContratoDpf(ContratoDpf contratoDpf) {
        this.contratoDpf = contratoDpf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaContratoDpfPK != null ? cuentaContratoDpfPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaContratoDpf)) {
            return false;
        }
        CuentaContratoDpf other = (CuentaContratoDpf) object;
        if ((this.cuentaContratoDpfPK == null && other.cuentaContratoDpfPK != null) || (this.cuentaContratoDpfPK != null && !this.cuentaContratoDpfPK.equals(other.cuentaContratoDpfPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.CuentaContratoDpf[ cuentaContratoDpfPK=" + cuentaContratoDpfPK + " ]";
    }

    /**
     * @return the usuarioRealizadoPor
     */
    public Usuario getUsuarioRealizadoPor() {
        return usuarioRealizadoPor;
    }

    /**
     * @param usuarioRealizadoPor the usuarioRealizadoPor to set
     */
    public void setUsuarioRealizadoPor(Usuario usuarioRealizadoPor) {
        this.usuarioRealizadoPor = usuarioRealizadoPor;
    }

    /**
     * @return the cuentaCredito
     */
    public Cuenta getCuentaCredito() {
        return cuentaCredito;
    }

    /**
     * @param cuentaCredito the cuentaCredito to set
     */
    public void setCuentaCredito(Cuenta cuentaCredito) {
        this.cuentaCredito = cuentaCredito;
    }

    /**
     * @return the cuentaDebito
     */
    public Cuenta getCuentaDebito() {
        return cuentaDebito;
    }

    /**
     * @param cuentaDebito the cuentaDebito to set
     */
    public void setCuentaDebito(Cuenta cuentaDebito) {
        this.cuentaDebito = cuentaDebito;
    }
    
}
