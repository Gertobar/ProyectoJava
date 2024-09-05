/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguros;

import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.socios.Socio;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
 * @author scordero
 */
@Entity
@Table(name = "MKS_SEGUROS.CONTRATO_SEGURO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoSeguro.findAll", query = "SELECT c FROM ContratoSeguro c"),
    @NamedQuery(name = "ContratoSeguro.findByCodigo", query = "SELECT c FROM ContratoSeguro c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "ContratoSeguro.findByCodigoSocio", query = "SELECT c FROM ContratoSeguro c WHERE c.codigoSocio = :codigoSocio"),
    @NamedQuery(name = "ContratoSeguro.findByFechaContrato", query = "SELECT c FROM ContratoSeguro c WHERE c.fechaContrato = :fechaContrato"),
    @NamedQuery(name = "ContratoSeguro.findByFechaVencimiento", query = "SELECT c FROM ContratoSeguro c WHERE c.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "ContratoSeguro.findByFechaActualizacion", query = "SELECT c FROM ContratoSeguro c WHERE c.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "ContratoSeguro.findByTipo", query = "SELECT c FROM ContratoSeguro c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "ContratoSeguro.findByNumeroCredito", query = "SELECT c FROM ContratoSeguro c WHERE c.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "ContratoSeguro.findByCodigoCuenta", query = "SELECT c FROM ContratoSeguro c WHERE c.codigoCuenta = :codigoCuenta"),
    @NamedQuery(name = "ContratoSeguro.findByCodigoIfip", query = "SELECT c FROM ContratoSeguro c WHERE c.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ContratoSeguro.findByCodigoUsuarioCrea", query = "SELECT c FROM ContratoSeguro c WHERE c.codigoUsuarioCrea = :codigoUsuarioCrea"),
    @NamedQuery(name = "ContratoSeguro.findByCodigoUsuarioActualiza", query = "SELECT c FROM ContratoSeguro c WHERE c.codigoUsuarioActualiza = :codigoUsuarioActualiza"),
    @NamedQuery(name = "ContratoSeguro.findByEliminado", query = "SELECT c FROM ContratoSeguro c WHERE c.eliminado = :eliminado"),
///PERSONALIZADOS
    @NamedQuery(name = "ContratoSeguro.findByNombreSocioActivoIfip", query = "SELECT c FROM ContratoSeguro c WHERE c.socio.codigoPersona.nombreCompleto LIKE :nombreCompleto AND c.socio.codigoEstadoSocio.indicaActivo = :indicaActivo AND c.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ContratoSeguro.findByIdentificacionSocioActivoIfip", query = "SELECT c FROM ContratoSeguro c WHERE c.socio.codigoPersona.identificacion = :identificacion AND c.socio.codigoEstadoSocio.indicaActivo = :indicaActivo AND c.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ContratoSeguro.findBySocioNoEstadoEliminado", query = "SELECT c FROM ContratoSeguro c WHERE c.socio.socioPK.codigo = :codigoSocio AND c.estado <> :estado AND c.eliminado = :eliminado AND c.codigoIfip = :codigoIfip")
})
public class ContratoSeguro implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByNombreSocioActivoIfip = "ContratoSeguro.findByNombreSocioActivoIfip";
    public static final String findByEliminado = "ContratoSeguro.findByEliminado";
    public static final String findByIdentificacionSocioActivoIfip = "ContratoSeguro.findByIdentificacionSocioActivoIfip";
    public static final String findBySocioNoEstadoEliminado = "ContratoSeguro.findBySocioNoEstadoEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SOCIO")
    private long codigoSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONTRATO")
    @Temporal(TemporalType.DATE)
    private Date fechaContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Column(name = "NUMERO_CREDITO")
    private Long numeroCredito;
    @Column(name = "CODIGO_CUENTA")
    private Long codigoCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_CREA")
    private long codigoUsuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_ACTUALIZA")
    private long codigoUsuarioActualiza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_VALOR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ValorSeguro codigoValor;    
    @JoinColumns({
         @JoinColumn(name = "CODIGO_SOCIO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
         @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Socio socio;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CUENTA", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = true)
    private Cuenta cuenta;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "RENOVACION_AUTOMATICA")    
    private char renovacionAutomatica;

    public ContratoSeguro() {
    }

    public ContratoSeguro(Long codigo) {
        this.codigo = codigo;
    }

    public ContratoSeguro(Long codigo, long codigoSocio, Date fechaContrato, Date fechaVencimiento, Date fechaActualizacion, char tipo, long codigoIfip, long codigoUsuarioCrea, long codigoUsuarioActualiza, char eliminado) {
        this.codigo = codigo;
        this.codigoSocio = codigoSocio;
        this.fechaContrato = fechaContrato;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaActualizacion = fechaActualizacion;
        this.tipo = tipo;
        this.codigoIfip = codigoIfip;
        this.codigoUsuarioCrea = codigoUsuarioCrea;
        this.codigoUsuarioActualiza = codigoUsuarioActualiza;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(long codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Long getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(Long numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public Long getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(Long codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoUsuarioCrea() {
        return codigoUsuarioCrea;
    }

    public void setCodigoUsuarioCrea(long codigoUsuarioCrea) {
        this.codigoUsuarioCrea = codigoUsuarioCrea;
    }

    public long getCodigoUsuarioActualiza() {
        return codigoUsuarioActualiza;
    }

    public void setCodigoUsuarioActualiza(long codigoUsuarioActualiza) {
        this.codigoUsuarioActualiza = codigoUsuarioActualiza;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public ValorSeguro getCodigoValor() {
        return codigoValor;
    }

    public void setCodigoValor(ValorSeguro codigoValor) {
        this.codigoValor = codigoValor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoSeguro)) {
            return false;
        }
        ContratoSeguro other = (ContratoSeguro) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguros.ContratoSeguro[ codigo=" + codigo + " ]";
    }

    /**
     * @return the socio
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    /**
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the renovacionAutomatica
     */
    public char getRenovacionAutomatica() {
        return renovacionAutomatica;
    }

    /**
     * @param renovacionAutomatica the renovacionAutomatica to set
     */
    public void setRenovacionAutomatica(char renovacionAutomatica) {
        this.renovacionAutomatica = renovacionAutomatica;
    }

    /**
     * @return the estado
     */
    public char getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(char estado) {
        this.estado = estado;
    }
    
}
