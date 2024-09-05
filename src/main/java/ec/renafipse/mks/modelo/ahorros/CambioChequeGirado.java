/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.CAMBIO_CHEQUE_GIRADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CambioChequeGirado.findAll", query = "SELECT c FROM CambioChequeGirado c"),
    @NamedQuery(name = "CambioChequeGirado.findByCodigo", query = "SELECT c FROM CambioChequeGirado c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CambioChequeGirado.findByCodigoRetiro", query = "SELECT c FROM CambioChequeGirado c WHERE c.codigoRetiro = :codigoRetiro"),
    @NamedQuery(name = "CambioChequeGirado.findByObservaciones", query = "SELECT c FROM CambioChequeGirado c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "CambioChequeGirado.findByFechaCambio", query = "SELECT c FROM CambioChequeGirado c WHERE c.fechaCambio = :fechaCambio"),
    @NamedQuery(name = "CambioChequeGirado.findByCambiadoPor", query = "SELECT c FROM CambioChequeGirado c WHERE c.cambiadoPor = :cambiadoPor")})
public class CambioChequeGirado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_RETIRO")
    private long codigoRetiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CAMBIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAMBIADO_POR")
    private char cambiadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CHEQUE_NUEVO")
    private long numeroChequeNuevo;
    @JoinColumn(name = "CODIGO_TIPO_MOTIVO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoMotivoCambioCheque codigoTipoMotivo;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CUENTA_ENT_FIN", referencedColumnName = "CODIGO_CUENTA_ENT_FIN", insertable = false, updatable =  false),
        @JoinColumn(name = "NUMERO_CHEQUE_ANTERIOR", referencedColumnName = "NUMERO_CHEQUE",insertable = false, updatable =  false)})
    @OneToOne(optional = false)
    private TalonarioChequeDetalle talonarioChequeDetalle;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CUENTA_ENT_FIN", referencedColumnName = "CODIGO_CUENTA_ENT_FIN", insertable = false, updatable =  false),
        @JoinColumn(name = "NUMERO_CHEQUE_NUEVO", referencedColumnName = "NUMERO_CHEQUE", insertable = false, updatable =  false)})
    @OneToOne(optional = false)
    private TalonarioChequeDetalle talonarioChequeDetalle1;

    public CambioChequeGirado() {
    }

    public CambioChequeGirado(Long codigo) {
        this.codigo = codigo;
    }

    public CambioChequeGirado(Long codigo, long codigoRetiro, String observaciones, Date fechaCambio, char cambiadoPor) {
        this.codigo = codigo;
        this.codigoRetiro = codigoRetiro;
        this.observaciones = observaciones;
        this.fechaCambio = fechaCambio;
        this.cambiadoPor = cambiadoPor;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoRetiro() {
        return codigoRetiro;
    }

    public void setCodigoRetiro(long codigoRetiro) {
        this.codigoRetiro = codigoRetiro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public char getCambiadoPor() {
        return cambiadoPor;
    }

    public void setCambiadoPor(char cambiadoPor) {
        this.cambiadoPor = cambiadoPor;
    }

    public TipoMotivoCambioCheque getCodigoTipoMotivo() {
        return codigoTipoMotivo;
    }

    public void setCodigoTipoMotivo(TipoMotivoCambioCheque codigoTipoMotivo) {
        this.codigoTipoMotivo = codigoTipoMotivo;
    }

    public TalonarioChequeDetalle getTalonarioChequeDetalle() {
        return talonarioChequeDetalle;
    }

    public void setTalonarioChequeDetalle(TalonarioChequeDetalle talonarioChequeDetalle) {
        this.talonarioChequeDetalle = talonarioChequeDetalle;
    }

    public TalonarioChequeDetalle getTalonarioChequeDetalle1() {
        return talonarioChequeDetalle1;
    }

    public void setTalonarioChequeDetalle1(TalonarioChequeDetalle talonarioChequeDetalle1) {
        this.talonarioChequeDetalle1 = talonarioChequeDetalle1;
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
        if (!(object instanceof CambioChequeGirado)) {
            return false;
        }
        CambioChequeGirado other = (CambioChequeGirado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.ahorros.CambioChequeGirado[ codigo=" + codigo + " ]";
    }

    /**
     * @return the numeroChequeNuevo
     */
    public long getNumeroChequeNuevo() {
        return numeroChequeNuevo;
    }

    /**
     * @param numeroChequeNuevo the numeroChequeNuevo to set
     */
    public void setNumeroChequeNuevo(long numeroChequeNuevo) {
        this.numeroChequeNuevo = numeroChequeNuevo;
    }
    
}
