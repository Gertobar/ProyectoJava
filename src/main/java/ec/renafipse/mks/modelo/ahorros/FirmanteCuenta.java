/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.TipoRelacion;
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
@Table(name = "MKS_AHORROS.FIRMANTE_CUENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FirmanteCuenta.findAll", query = "SELECT f FROM FirmanteCuenta f"),
    @NamedQuery(name = "FirmanteCuenta.findByCodigoCuenta", query = "SELECT f FROM FirmanteCuenta f WHERE f.firmanteCuentaPK.codigoCuenta = :codigoCuenta"),
    @NamedQuery(name = "FirmanteCuenta.findByCodigoPersona", query = "SELECT f FROM FirmanteCuenta f WHERE f.firmanteCuentaPK.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "FirmanteCuenta.findByCodigoTipRel", query = "SELECT f FROM FirmanteCuenta f WHERE f.codigoTipRel = :codigoTipRel"),
    @NamedQuery(name = "FirmanteCuenta.findByRegistradoPor", query = "SELECT f FROM FirmanteCuenta f WHERE f.registradoPor = :registradoPor"),
    @NamedQuery(name = "FirmanteCuenta.findByFechaRegistro", query = "SELECT f FROM FirmanteCuenta f WHERE f.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "FirmanteCuenta.findByEliminado", query = "SELECT f FROM FirmanteCuenta f WHERE f.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "FirmanteCuenta.findByCodigoCuentaEliminado", query = "SELECT f FROM FirmanteCuenta f WHERE f.firmanteCuentaPK.codigoCuenta = :codigoCuenta AND f.eliminado = :eliminado")
})
public class FirmanteCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoCuenta = "FirmanteCuenta.findByCodigoCuenta";
    public static final String findByCodigoCuentaEliminado = "FirmanteCuenta.findByCodigoCuentaEliminado";
    
    @EmbeddedId
    protected FirmanteCuentaPK firmanteCuentaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIP_REL")    
    private Long codigoTipRel;
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
    /*@JoinColumn(name = "CODIGO_CUENTA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta;    */
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "CODIGO_TIP_REL", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoRelacion tipoRelacion;
     
    public FirmanteCuenta() {
    }

    public FirmanteCuenta(FirmanteCuentaPK firmanteCuentaPK) {
        this.firmanteCuentaPK = firmanteCuentaPK;
    }

    public FirmanteCuenta(FirmanteCuentaPK firmanteCuentaPK, Long codigoTipRel, long registradoPor, Date fechaRegistro, char eliminado) {
        this.firmanteCuentaPK = firmanteCuentaPK;
        this.codigoTipRel = codigoTipRel;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public FirmanteCuenta(long codigoCuenta, long codigoPersona) {
        this.firmanteCuentaPK = new FirmanteCuentaPK(codigoCuenta, codigoPersona);
    }

    public FirmanteCuentaPK getFirmanteCuentaPK() {
        return firmanteCuentaPK;
    }

    public void setFirmanteCuentaPK(FirmanteCuentaPK firmanteCuentaPK) {
        this.firmanteCuentaPK = firmanteCuentaPK;
    }

    public Long getCodigoTipRel() {
        return codigoTipRel;
    }

    public void setCodigoTipRel(Long codigoTipRel) {
        this.codigoTipRel = codigoTipRel;
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

   /* public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (firmanteCuentaPK != null ? firmanteCuentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FirmanteCuenta)) {
            return false;
        }
        FirmanteCuenta other = (FirmanteCuenta) object;
        if ((this.firmanteCuentaPK == null && other.firmanteCuentaPK != null) || (this.firmanteCuentaPK != null && !this.firmanteCuentaPK.equals(other.firmanteCuentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.FirmanteCuenta[ firmanteCuentaPK=" + firmanteCuentaPK + " ]";
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the tipoRelacion
     */
    public TipoRelacion getTipoRelacion() {
        return tipoRelacion;
    }

    /**
     * @param tipoRelacion the tipoRelacion to set
     */
    public void setTipoRelacion(TipoRelacion tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }
    
}
