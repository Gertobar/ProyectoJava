/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;


/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.PROVEEDOR_CUENTA_ENT_FIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveedorCuentaEntFin.findAll", query = "SELECT p FROM ProveedorCuentaEntFin p"),
    @NamedQuery(name = "ProveedorCuentaEntFin.findByCodigo", query = "SELECT p FROM ProveedorCuentaEntFin p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ProveedorCuentaEntFin.findByCodigoTipCue", query = "SELECT p FROM ProveedorCuentaEntFin p WHERE p.codigoTipCue = :codigoTipCue"),
    @NamedQuery(name = "ProveedorCuentaEntFin.findByCodigoEntidadFinanciera", query = "SELECT p FROM ProveedorCuentaEntFin p WHERE p.codigoEntidadFinanciera = :codigoEntidadFinanciera"),
    @NamedQuery(name = "ProveedorCuentaEntFin.findByNumeroCuenta", query = "SELECT p FROM ProveedorCuentaEntFin p WHERE p.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "ProveedorCuentaEntFin.findByNombreCuenta", query = "SELECT p FROM ProveedorCuentaEntFin p WHERE p.nombreCuenta = :nombreCuenta"),
    @NamedQuery(name = "ProveedorCuentaEntFin.findByObservaciones", query = "SELECT p FROM ProveedorCuentaEntFin p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "ProveedorCuentaEntFin.findByCodigoProveedor", query = "SELECT p FROM ProveedorCuentaEntFin p WHERE  p.codigoProveedor.codigo = :codigoProveedor"),
    @NamedQuery(name = "ProveedorCuentaEntFin.findByEliminado", query = "SELECT p FROM ProveedorCuentaEntFin p WHERE p.eliminado = :eliminado"),
    @NamedQuery(name = "ProveedorCuentaEntFin.findByCodProveedorEliminado", query = "SELECT p.entidadFinanciera FROM ProveedorCuentaEntFin p WHERE p.eliminado = :eliminado AND p.codigoProveedor.codigo = :codigoProveedor"),
    @NamedQuery(name = "ProveedorCuentaEntFin.findByCodProvEliminaCodEntidad", query = "SELECT p.numeroCuenta FROM ProveedorCuentaEntFin p   WHERE p.eliminado = :eliminado AND p.codigoProveedor.codigo = :codigoProveedor AND p.codigoEntidadFinanciera = :codigoEntidad")})

public class ProveedorCuentaEntFin implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoProveedor = "ProveedorCuentaEntFin.findByCodigoProveedor";
    public static final String findByCodProveedorEliminado = "ProveedorCuentaEntFin.findByCodProveedorEliminado";
    public static final String findByCodProvEliminaCodEntidad = "ProveedorCuentaEntFin.findByCodProvEliminaCodEntidad";
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PROVEEDOR_CUENTA_ENT_FIN")
    @SequenceGenerator(name = "GSQ_PROVEEDOR_CUENTA_ENT_FIN", schema = "MKS_ADQUISICIONES", allocationSize = 0, sequenceName = "SEQ_PROVEEDOR_CUENTA_ENT_FIN")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIP_CUE")
    private long codigoTipCue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENTIDAD_FINANCIERA")
    private long codigoEntidadFinanciera;
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
    @JoinColumn(name = "CODIGO_PROVEEDOR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Proveedor codigoProveedor;
    
    @JoinColumn(name = "CODIGO_TIP_CUE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoCuentaEntidadFinanciera tipCueEntFinanciera;
        
    @JoinColumn(name = "CODIGO_ENTIDAD_FINANCIERA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EntidadFinanciera entidadFinanciera;
    
 
    public ProveedorCuentaEntFin() {
    }

    public ProveedorCuentaEntFin(Long codigo) {
        this.codigo = codigo;
    }

    public ProveedorCuentaEntFin(Long codigo, long codigoTipCue, long codigoEntidadFinanciera, String numeroCuenta, String nombreCuenta, String observaciones, char eliminado) {
        this.codigo = codigo;
        this.codigoTipCue = codigoTipCue;
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
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

    public long getCodigoEntidadFinanciera() {
        return codigoEntidadFinanciera;
    }

    public void setCodigoEntidadFinanciera(long codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
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

    public Proveedor getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(Proveedor codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
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
        if (!(object instanceof ProveedorCuentaEntFin)) {
            return false;
        }
        ProveedorCuentaEntFin other = (ProveedorCuentaEntFin) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.ProveedorCuentaEntFin[ codigo=" + codigo + " ]";
    }

    /**
     * @return the tipCueEntFinanciera
     */
    public TipoCuentaEntidadFinanciera getTipCueEntFinanciera() {
        return tipCueEntFinanciera;
    }

    /**
     * @param tipCueEntFinanciera the tipCueEntFinanciera to set
     */
    public void setTipCueEntFinanciera(TipoCuentaEntidadFinanciera tipCueEntFinanciera) {
        this.tipCueEntFinanciera = tipCueEntFinanciera;
    }

    /**
     * @return the entidadFinanciera
     */
    public EntidadFinanciera getEntidadFinanciera() {
        return entidadFinanciera;
    }

    /**
     * @param entidadFinanciera the entidadFinanciera to set
     */
    public void setEntidadFinanciera(EntidadFinanciera entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }
    
}
