/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.ifips.IfipCuentaEntidadFinanciera;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.TALONARIO_CHEQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TalonarioCheque.findAll", query = "SELECT t FROM TalonarioCheque t"),
    @NamedQuery(name = "TalonarioCheque.findByCodigo", query = "SELECT t FROM TalonarioCheque t WHERE t.talonarioChequePK.codigo = :codigo"),
    @NamedQuery(name = "TalonarioCheque.findByCodigoCuentaEntFin", query = "SELECT t FROM TalonarioCheque t WHERE t.talonarioChequePK.codigoCuentaEntFin = :codigoCuentaEntFin"),
    @NamedQuery(name = "TalonarioCheque.findByNumeroInicio", query = "SELECT t FROM TalonarioCheque t WHERE t.numeroInicio = :numeroInicio"),
    @NamedQuery(name = "TalonarioCheque.findByNumeroFin", query = "SELECT t FROM TalonarioCheque t WHERE t.numeroFin = :numeroFin"),
    @NamedQuery(name = "TalonarioCheque.findByCodigoIfipAgencia", query = "SELECT t FROM TalonarioCheque t WHERE t.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "TalonarioCheque.findByRegistradoPor", query = "SELECT t FROM TalonarioCheque t WHERE t.registradoPor = :registradoPor"),
    @NamedQuery(name = "TalonarioCheque.findByFechaRegistro", query = "SELECT t FROM TalonarioCheque t WHERE t.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "TalonarioCheque.findByTerminada", query = "SELECT t FROM TalonarioCheque t WHERE t.terminada = :terminada"),
    //Personalizados
    @NamedQuery(name = "TalonarioCheque.findByIfipCuentaEntidadFinancieraCheque", query = "SELECT DISTINCT i FROM TalonarioCheque t JOIN t.ifipCuentaEntidadFinanciera i WHERE i.codigoIfip.codigo = :codigoIfip AND  t.codigoIfipAgencia = :codigoIfipAgencia AND t.terminada = :terminada ORDER BY i.codigoEntidadFinanciera, i.numeroCuenta"),
    @NamedQuery(name = "TalonarioCheque.findByCodigoIfipCodigoIfipAgencia", query = "SELECT t FROM TalonarioCheque t JOIN t.ifipCuentaEntidadFinanciera i WHERE i.codigoIfip.codigo = :codigoIfip AND  t.codigoIfipAgencia = :codigoIfipAgencia ORDER BY i.codigoEntidadFinanciera, i.numeroCuenta, t.numeroInicio")

})
public class TalonarioCheque implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByIfipCuentaEntidadFinancieraCheque = "TalonarioCheque.findByIfipCuentaEntidadFinancieraCheque";
    public static final String findByCodigoIfipCodigoIfipAgencia = "TalonarioCheque.findByCodigoIfipCodigoIfipAgencia";
    @EmbeddedId
    protected TalonarioChequePK talonarioChequePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_INICIO")
    private long numeroInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_FIN")
    private long numeroFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
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
    @Column(name = "TERMINADA")
    private char terminada;
    
    @JoinColumn(name = "CODIGO_CUENTA_ENT_FIN", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private IfipCuentaEntidadFinanciera ifipCuentaEntidadFinanciera;
   

    public TalonarioCheque() {
    }

    public TalonarioCheque(TalonarioChequePK talonarioChequePK) {
        this.talonarioChequePK = talonarioChequePK;
    }

    public TalonarioCheque(TalonarioChequePK talonarioChequePK, long numeroInicio, long numeroFin, long codigoIfipAgencia, long registradoPor, Date fechaRegistro, char terminada) {
        this.talonarioChequePK = talonarioChequePK;
        this.numeroInicio = numeroInicio;
        this.numeroFin = numeroFin;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.terminada = terminada;
    }

    public TalonarioCheque(long codigo, long codigoCuentaEntFin) {
        this.talonarioChequePK = new TalonarioChequePK(codigo, codigoCuentaEntFin);
    }

    public TalonarioChequePK getTalonarioChequePK() {
        return talonarioChequePK;
    }

    public void setTalonarioChequePK(TalonarioChequePK talonarioChequePK) {
        this.talonarioChequePK = talonarioChequePK;
    }

    public long getNumeroInicio() {
        return numeroInicio;
    }

    public void setNumeroInicio(long numeroInicio) {
        this.numeroInicio = numeroInicio;
    }

    public long getNumeroFin() {
        return numeroFin;
    }

    public void setNumeroFin(long numeroFin) {
        this.numeroFin = numeroFin;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
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

    public char getTerminada() {
        return terminada;
    }

    public void setTerminada(char terminada) {
        this.terminada = terminada;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (talonarioChequePK != null ? talonarioChequePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TalonarioCheque)) {
            return false;
        }
        TalonarioCheque other = (TalonarioCheque) object;
        if ((this.talonarioChequePK == null && other.talonarioChequePK != null) || (this.talonarioChequePK != null && !this.talonarioChequePK.equals(other.talonarioChequePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.ahorros.TalonarioCheque[ talonarioChequePK=" + talonarioChequePK + " ]";
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
