/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ifips;

import ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalleCheque;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_IFIPS.IFIP_AGENCIA_CUENTA_ENT_FIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfipAgenciaCuentaEntFin.findAll", query = "SELECT i FROM IfipAgenciaCuentaEntFin i"),
    @NamedQuery(name = "IfipAgenciaCuentaEntFin.findByCodigo", query = "SELECT i FROM IfipAgenciaCuentaEntFin i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "IfipAgenciaCuentaEntFin.findByCodigoTipCue", query = "SELECT i FROM IfipAgenciaCuentaEntFin i WHERE i.codigoTipCue = :codigoTipCue"),
    @NamedQuery(name = "IfipAgenciaCuentaEntFin.findByNumeroCuenta", query = "SELECT i FROM IfipAgenciaCuentaEntFin i WHERE i.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "IfipAgenciaCuentaEntFin.findByNombreCuenta", query = "SELECT i FROM IfipAgenciaCuentaEntFin i WHERE i.nombreCuenta = :nombreCuenta"),
    @NamedQuery(name = "IfipAgenciaCuentaEntFin.findByObservaciones", query = "SELECT i FROM IfipAgenciaCuentaEntFin i WHERE i.observaciones = :observaciones"),
    @NamedQuery(name = "IfipAgenciaCuentaEntFin.findByEliminado", query = "SELECT i FROM IfipAgenciaCuentaEntFin i WHERE i.eliminado = :eliminado"),
    //Personalizadas
    @NamedQuery(name = "IfipAgenciaCuentaEntFin.findByIfipAgenciaCuentaEntFinEliminado", query = "SELECT i FROM IfipAgenciaCuentaEntFin i JOIN i.entidadFinancieraIfiAge e JOIN e.entidadFinanciera f JOIN e.ifipAgencia a JOIN f.codigoTipoEntidad te WHERE e.entidadFinancieraIfiAgePK.codigoIfipAgencia = :codigoIfipAgencia AND i.eliminado = :eliminado AND e.eliminado = :eliminado AND a.eliminado = :eliminado AND te.eliminado = :eliminado"),
    @NamedQuery(name = "IfipAgenciaCuentaEntFin.findEntidadByIfipAgenciaCuentaEntFinEliminado", query = "SELECT distinct(e.entidadFinanciera) FROM IfipAgenciaCuentaEntFin i JOIN i.entidadFinancieraIfiAge e JOIN e.entidadFinanciera f JOIN e.ifipAgencia a JOIN f.codigoTipoEntidad te WHERE e.entidadFinancieraIfiAgePK.codigoIfipAgencia = :codigoIfipAgencia AND i.eliminado = :eliminado AND e.eliminado = :eliminado AND a.eliminado = :eliminado AND te.eliminado = :eliminado"),
    @NamedQuery(name = "IfipAgenciaCuentaEntFin.findEntidadByIfipAgenciaCuentaEntFinElicodPCD", query = "SELECT i.numeroCuenta FROM IfipAgenciaCuentaEntFin i JOIN i.entidadFinancieraIfiAge e JOIN e.entidadFinanciera f JOIN e.ifipAgencia a JOIN f.codigoTipoEntidad te WHERE e.entidadFinancieraIfiAgePK.codigoIfipAgencia = :codigoIfipAgencia AND i.eliminado = :eliminado AND e.eliminado = :eliminado AND a.eliminado = :eliminado AND te.eliminado = :eliminado AND f.codigo =:codEntidad "),
    @NamedQuery(name = "IfipAgenciaCuentaEntFin.findCodCuentaByIfipAgenciaCuentaEntFinElicodPCD", query = "SELECT i FROM IfipAgenciaCuentaEntFin i JOIN i.entidadFinancieraIfiAge e JOIN e.entidadFinanciera f JOIN e.ifipAgencia a JOIN f.codigoTipoEntidad te WHERE e.entidadFinancieraIfiAgePK.codigoIfipAgencia = :codigoIfipAgencia AND i.eliminado = :eliminado AND e.eliminado = :eliminado AND a.eliminado = :eliminado AND te.eliminado = :eliminado AND f.codigo =:codEntidad AND i.numeroCuenta =:numeroCuenta")

})
public class IfipAgenciaCuentaEntFin implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByIfipAgenciaCuentaEntFinEliminado = "IfipAgenciaCuentaEntFin.findByIfipAgenciaCuentaEntFinEliminado";
    public static final String findEntidadByIfipAgenciaCuentaEntFinEliminado = "IfipAgenciaCuentaEntFin.findEntidadByIfipAgenciaCuentaEntFinEliminado";
    public static final String findEntidadByIfipAgenciaCuentaEntFinElicodPCD = "IfipAgenciaCuentaEntFin.findEntidadByIfipAgenciaCuentaEntFinElicodPCD";
    public static final String findCodCuentaByIfipAgenciaCuentaEntFinElicodPCD = "IfipAgenciaCuentaEntFin.findCodCuentaByIfipAgenciaCuentaEntFinElicodPCD";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIP_CUE")
    private long codigoTipCue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_CUENTA")
    private String nombreCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO_IFIP_AGENCIA"),
        @JoinColumn(name = "CODIGO_ENTIDAD_FINANCIERA", referencedColumnName = "CODIGO_ENTIDAD_FINANCIERA")})
    @ManyToOne(optional = false)
    private EntidadFinancieraIfiAge entidadFinancieraIfiAge;

 
    public IfipAgenciaCuentaEntFin() {
    }

    public IfipAgenciaCuentaEntFin(Long codigo) {
        this.codigo = codigo;
    }

    public IfipAgenciaCuentaEntFin(Long codigo, long codigoTipCue, String numeroCuenta, String nombreCuenta, String observaciones, char eliminado) {
        this.codigo = codigo;
        this.codigoTipCue = codigoTipCue;
        this.numeroCuenta = numeroCuenta;
        this.nombreCuenta = nombreCuenta;
        this.observaciones = observaciones;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoTipCue() {
        return codigoTipCue;
    }

    public void setCodigoTipCue(long codigoTipCue) {
        this.codigoTipCue = codigoTipCue;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public EntidadFinancieraIfiAge getEntidadFinancieraIfiAge() {
        return entidadFinancieraIfiAge;
    }

    public void setEntidadFinancieraIfiAge(EntidadFinancieraIfiAge entidadFinancieraIfiAge) {
        this.entidadFinancieraIfiAge = entidadFinancieraIfiAge;
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
        if (!(object instanceof IfipAgenciaCuentaEntFin)) {
            return false;
        }
        IfipAgenciaCuentaEntFin other = (IfipAgenciaCuentaEntFin) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.IfipAgenciaCuentaEntFin[ codigo=" + codigo + " ]";
    }
 
}
