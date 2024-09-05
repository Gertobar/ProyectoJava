/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.IMPRESION_LIBRETA_CUENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImpresionLibretaCuenta.findAll", query = "SELECT i FROM ImpresionLibretaCuenta i"),
    @NamedQuery(name = "ImpresionLibretaCuenta.findByCodigoMovimiento", query = "SELECT i FROM ImpresionLibretaCuenta i WHERE i.impresionLibretaCuentaPK.codigoMovimiento = :codigoMovimiento"),
    @NamedQuery(name = "ImpresionLibretaCuenta.findByNumeroLinea", query = "SELECT i FROM ImpresionLibretaCuenta i WHERE i.numeroLinea = :numeroLinea"),
    //Personalizadas
    @NamedQuery(name = "ImpresionLibretaCuenta.findByNumeroLibretaSocio", query = "SELECT i FROM ImpresionLibretaCuenta i WHERE i.impresionLibretaCuentaPK.numeroLibreta = :numeroLibreta"),
    @NamedQuery(name = "ImpresionLibretaCuenta.findUltimaLineaImpresaLibreta", query = "SELECT max(i.numeroLinea) FROM ImpresionLibretaCuenta i WHERE i.impresionLibretaCuentaPK.numeroLibreta = :numeroLibreta"),
    
})
public class ImpresionLibretaCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByNumeroLibretaSocio= "ImpresionLibretaCuenta.findByNumeroLibretaSocio";
    public static final String findUltimaLineaImpresaLibreta= "ImpresionLibretaCuenta.findUltimaLineaImpresaLibreta";
    
    @EmbeddedId
    protected ImpresionLibretaCuentaPK impresionLibretaCuentaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_LINEA")
    private short numeroLinea;
    @Column(name = "ESTADO")
    private Character estado;
    @JoinColumn(name = "CODIGO_MOVIMIENTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MovimientoCuenta movimientoCuenta;
    @JoinColumn(name = "NUMERO_LIBRETA", referencedColumnName = "NUMERO_LIBRETA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LibretaAsignadaCuenta libretaAsignadaCuenta;
    @JoinColumn(name = "CODIGO_MOVIMIENTO", referencedColumnName = "CODIGO_MOVIMIENTO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MovimientoCuentaAdicional movimientoCuentaAdicional;


     public ImpresionLibretaCuenta() {
    }

    public ImpresionLibretaCuenta(ImpresionLibretaCuentaPK impresionLibretaCuentaPK) {
        this.impresionLibretaCuentaPK = impresionLibretaCuentaPK;
    }

    public ImpresionLibretaCuenta(ImpresionLibretaCuentaPK impresionLibretaCuentaPK, short numeroLinea) {
        this.impresionLibretaCuentaPK = impresionLibretaCuentaPK;
        this.numeroLinea = numeroLinea;
    }

    public ImpresionLibretaCuenta(long codigoMovimiento, String numeroLibreta) {
        this.impresionLibretaCuentaPK = new ImpresionLibretaCuentaPK(codigoMovimiento, numeroLibreta);
    }

    public ImpresionLibretaCuentaPK getImpresionLibretaCuentaPK() {
        return impresionLibretaCuentaPK;
    }

    public void setImpresionLibretaCuentaPK(ImpresionLibretaCuentaPK impresionLibretaCuentaPK) {
        this.impresionLibretaCuentaPK = impresionLibretaCuentaPK;
    }

    public short getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(short numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public MovimientoCuenta getMovimientoCuenta() {
        return movimientoCuenta;
    }

    public void setMovimientoCuenta(MovimientoCuenta movimientoCuenta) {
        this.movimientoCuenta = movimientoCuenta;
    }

    /*public LibretaAsignadaCuenta getNumeroLibreta() {
        return numeroLibreta;
    }

    public void setNumeroLibreta(LibretaAsignadaCuenta numeroLibreta) {
        this.numeroLibreta = numeroLibreta;
    }    */

    /**
     * @return the movimientoCuentaAdicional
     */
    public MovimientoCuentaAdicional getMovimientoCuentaAdicional() {
        return movimientoCuentaAdicional;
    }

    /**
     * @param movimientoCuentaAdicional the movimientoCuentaAdicional to set
     */
    public void setMovimientoCuentaAdicional(MovimientoCuentaAdicional movimientoCuentaAdicional) {
        this.movimientoCuentaAdicional = movimientoCuentaAdicional;
    }

 

     @Override
    public int hashCode() {
        int hash = 0;
        hash += (impresionLibretaCuentaPK != null ? impresionLibretaCuentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImpresionLibretaCuenta)) {
            return false;
        }
        ImpresionLibretaCuenta other = (ImpresionLibretaCuenta) object;
        if ((this.impresionLibretaCuentaPK == null && other.impresionLibretaCuentaPK != null) || (this.impresionLibretaCuentaPK != null && !this.impresionLibretaCuentaPK.equals(other.impresionLibretaCuentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.ImpresionLibretaCuenta[ impresionLibretaCuentaPK=" + impresionLibretaCuentaPK + " ]";
    }

    /**
     * @return the estado
     */
    public Character getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Character estado) {
        this.estado = estado;
    }

    /**
     * @return the libretaAsignadaCuenta
     */
    public LibretaAsignadaCuenta getLibretaAsignadaCuenta() {
        return libretaAsignadaCuenta;
    }

    /**
     * @param libretaAsignadaCuenta the libretaAsignadaCuenta to set
     */
    public void setLibretaAsignadaCuenta(LibretaAsignadaCuenta libretaAsignadaCuenta) {
        this.libretaAsignadaCuenta = libretaAsignadaCuenta;
    }

    
}
