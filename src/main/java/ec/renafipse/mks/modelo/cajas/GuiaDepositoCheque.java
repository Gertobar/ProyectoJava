/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.cajas;

import ec.renafipse.mks.capas.utilitario.ConvierteDato;
import ec.renafipse.mks.modelo.ifips.IfipAgenciaCuentaEntFin;
import ec.renafipse.mks.modelo.ifips.IfipCuentaEntidadFinanciera;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.GUIA_DEPOSITO_CHEQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GuiaDepositoCheque.findAll", query = "SELECT g FROM GuiaDepositoCheque g"),
    @NamedQuery(name = "GuiaDepositoCheque.findByCodigo", query = "SELECT g FROM GuiaDepositoCheque g WHERE g.codigo = :codigo"),
    @NamedQuery(name = "GuiaDepositoCheque.findByCodigoIfipAgencia", query = "SELECT g FROM GuiaDepositoCheque g WHERE g.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "GuiaDepositoCheque.findByCodigoUsuario", query = "SELECT g FROM GuiaDepositoCheque g WHERE g.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "GuiaDepositoCheque.findByCodigoComputador", query = "SELECT g FROM GuiaDepositoCheque g WHERE g.codigoComputador = :codigoComputador"),
    @NamedQuery(name = "GuiaDepositoCheque.findByCodigoCuentaEntFin", query = "SELECT g FROM GuiaDepositoCheque g WHERE g.codigoCuentaEntFin = :codigoCuentaEntFin"),
    @NamedQuery(name = "GuiaDepositoCheque.findByFecha", query = "SELECT g FROM GuiaDepositoCheque g WHERE g.fecha = :fecha"),
    @NamedQuery(name = "GuiaDepositoCheque.findByFechaSistema", query = "SELECT g FROM GuiaDepositoCheque g WHERE g.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "GuiaDepositoCheque.findByEstado", query = "SELECT g FROM GuiaDepositoCheque g WHERE g.estado = :estado"),
    @NamedQuery(name = "GuiaDepositoCheque.findByFechaDeposito", query = "SELECT g FROM GuiaDepositoCheque g WHERE g.fechaDeposito = :fechaDeposito"),
    @NamedQuery(name = "GuiaDepositoCheque.findByObservacionesDeposito", query = "SELECT g FROM GuiaDepositoCheque g WHERE g.observacionesDeposito = :observacionesDeposito")
})
public class GuiaDepositoCheque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_GUIA_DEPOSITO_CHEQUE")
    @SequenceGenerator(name = "GSQ_GUIA_DEPOSITO_CHEQUE", schema = "MKS_CAJAS", allocationSize = 0, sequenceName = "SEQ_GUIA_DEPOSITO_CHEQUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPUTADOR")
    private long codigoComputador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_ENT_FIN")
    private long codigoCuentaEntFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Column(name = "FECHA_DEPOSITO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeposito;
    @Size(max = 100)
    @Column(name = "OBSERVACIONES_DEPOSITO")
    private String observacionesDeposito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guiaDepositoCheque", fetch = FetchType.LAZY)
    private Collection<GuiaDepositoChequeDet> guiaDepositoChequeDetCollection;
    @JoinColumn(name = "CODIGO_CUENTA_ENT_FIN", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipCuentaEntidadFinanciera ifipCuentaEntidadFinanciera;

    public GuiaDepositoCheque() {
    }

    public GuiaDepositoCheque(Long codigo) {
        this.codigo = codigo;
    }

    public GuiaDepositoCheque(Long codigo, long codigoIfipAgencia, long codigoUsuario, long codigoComputador, long codigoCuentaEntFin, Date fecha, Date fechaSistema, char estado) {
        this.codigo = codigo;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoUsuario = codigoUsuario;
        this.codigoComputador = codigoComputador;
        this.codigoCuentaEntFin = codigoCuentaEntFin;
        this.fecha = fecha;
        this.fechaSistema = fechaSistema;
        this.estado = estado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public long getCodigoComputador() {
        return codigoComputador;
    }

    public void setCodigoComputador(long codigoComputador) {
        this.codigoComputador = codigoComputador;
    }

    public long getCodigoCuentaEntFin() {
        return codigoCuentaEntFin;
    }

    public void setCodigoCuentaEntFin(long codigoCuentaEntFin) {
        this.codigoCuentaEntFin = codigoCuentaEntFin;
    }

    public Date getFecha() {
        return ConvierteDato.getFechaMedium(fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Date getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(Date fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }

    public String getObservacionesDeposito() {
        return observacionesDeposito;
    }

    public void setObservacionesDeposito(String observacionesDeposito) {
        this.observacionesDeposito = observacionesDeposito;
    }

    @XmlTransient
    public Collection<GuiaDepositoChequeDet> getGuiaDepositoChequeDetCollection() {
        return guiaDepositoChequeDetCollection;
    }

    public void setGuiaDepositoChequeDetCollection(Collection<GuiaDepositoChequeDet> guiaDepositoChequeDetCollection) {
        this.guiaDepositoChequeDetCollection = guiaDepositoChequeDetCollection;
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
        if (!(object instanceof GuiaDepositoCheque)) {
            return false;
        }
        GuiaDepositoCheque other = (GuiaDepositoCheque) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.GuiaDepositoCheque[ codigo=" + codigo + " ]";
    }

    /**
     * @return the ifipCuentaEntidadFinanciera
     */
    public IfipCuentaEntidadFinanciera getIfipCuentaEntidadFinanciera() {
        return ifipCuentaEntidadFinanciera;
    }

    /**
     * @param ifipCuentaEntidadFinanciera the ifipCuentaEntidadFinanciera to set
     */
    public void setIfipCuentaEntidadFinanciera(IfipCuentaEntidadFinanciera ifipCuentaEntidadFinanciera) {
        this.ifipCuentaEntidadFinanciera = ifipCuentaEntidadFinanciera;
    }

    

}
