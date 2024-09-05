/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.TALONARIO_LIBRETA_AHORRO_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TalonarioLibretaAhorroDet.findAll", query = "SELECT t FROM TalonarioLibretaAhorroDet t"),
    @NamedQuery(name = "TalonarioLibretaAhorroDet.findByNumeroLibreta", query = "SELECT t FROM TalonarioLibretaAhorroDet t WHERE t.numeroLibreta = :numeroLibreta"),
    @NamedQuery(name = "TalonarioLibretaAhorroDet.findBySerie", query = "SELECT t FROM TalonarioLibretaAhorroDet t WHERE t.serie = :serie"),
    @NamedQuery(name = "TalonarioLibretaAhorroDet.findByEstado", query = "SELECT t FROM TalonarioLibretaAhorroDet t WHERE t.estado = :estado"),
    // Personalizados
    @NamedQuery(name = "TalonarioLibretaAhorroDet.findByNumeroLibretaEstado", query = "SELECT t FROM TalonarioLibretaAhorroDet t WHERE t.numeroLibreta = :numeroLibreta AND t.estado = :estado"),
    @NamedQuery(name = "TalonarioLibretaAhorroDet.findByNumeroLibretaProducto", query = "SELECT t FROM TalonarioLibretaAhorroDet t JOIN t.codigoTalLibAhoCab c  WHERE  c.codigoTipoProducto = :codigoTipoProducto AND c.codigoMoneda = :codigoMoneda AND c.codigoIfip = :codigoIfip AND  t.numeroLibreta = :numeroLibreta"),
    @NamedQuery(name = "TalonarioLibretaAhorroDet.findByLibretaIfipProductoMonedaEstado", query = "SELECT t FROM TalonarioLibretaAhorroDet t JOIN t.codigoTalLibAhoCab c  WHERE c.codigoTipoProducto = :codigoTipoProducto AND c.codigoMoneda = :codigoMoneda AND c.codigoIfip = :codigoIfip AND  t.estado = :estado"),
    @NamedQuery(name = "TalonarioLibretaAhorroDet.findByLibretaIfipProductoMonedaSerie", query = "SELECT t FROM TalonarioLibretaAhorroDet t JOIN t.codigoTalLibAhoCab c  WHERE c.codigoTipoProducto = :codigoTipoProducto AND c.codigoMoneda = :codigoMoneda AND c.codigoIfip = :codigoIfip AND  t.serie = :serie"),
    @NamedQuery(name = "TalonarioLibretaAhorroDet.findByLibretaIfipProductoMonedaSerieEliminado", query = "SELECT t FROM TalonarioLibretaAhorroDet t JOIN t.codigoTalLibAhoCab c  WHERE c.codigoTipoProducto = :codigoTipoProducto AND c.codigoMoneda = :codigoMoneda AND c.codigoIfip = :codigoIfip AND  t.serie = :serie AND c.eliminado = 'N' AND t.estado != 'E'")
})
public class TalonarioLibretaAhorroDet implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByNumeroLibretaEstado = "TalonarioLibretaAhorroDet.findByNumeroLibretaEstado";
    public static final String findByNumeroLibreta = "TalonarioLibretaAhorroDet.findByNumeroLibreta";
    public static final String findByNumeroLibretaProducto = "TalonarioLibretaAhorroDet.findByNumeroLibretaProducto";
    public static final String findByLibretaIfipProductoMonedaEstado = "TalonarioLibretaAhorroDet.findByLibretaIfipProductoMonedaEstado";
    public static final String findByLibretaIfipProductoMonedaSerie = "TalonarioLibretaAhorroDet.findByLibretaIfipProductoMonedaSerie";
    public static final String findByLibretaIfipProductoMonedaSerieEliminado = "TalonarioLibretaAhorroDet.findByLibretaIfipProductoMonedaSerieEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NUMERO_LIBRETA")
    private String numeroLibreta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERIE")
    private long serie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroLibreta", fetch = FetchType.LAZY)
    private Collection<Cuenta> cuentaCollection;
    @JoinColumn(name = "CODIGO_TAL_LIB_AHO_CAB", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TalonarioLibretaAhorroCab codigoTalLibAhoCab;
    /*@OneToOne(cascade = CascadeType.ALL, mappedBy = "talonarioLibretaAhorroDet", fetch = FetchType.LAZY)
    private LibretaAsignadaCuenta libretaAsignadaCuenta;*/

    public TalonarioLibretaAhorroDet() {
    }

    public TalonarioLibretaAhorroDet(String numeroLibreta) {
        this.numeroLibreta = numeroLibreta;
    }

    public TalonarioLibretaAhorroDet(String numeroLibreta, long serie, char estado) {
        this.numeroLibreta = numeroLibreta;
        this.serie = serie;
        this.estado = estado;
    }

    public String getNumeroLibreta() {
        return numeroLibreta;
    }

    public void setNumeroLibreta(String numeroLibreta) {
        this.numeroLibreta = numeroLibreta;
    }

    public long getSerie() {
        return serie;
    }

    public void setSerie(long serie) {
        this.serie = serie;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Cuenta> getCuentaCollection() {
        return cuentaCollection;
    }

    public void setCuentaCollection(Collection<Cuenta> cuentaCollection) {
        this.cuentaCollection = cuentaCollection;
    }

    public TalonarioLibretaAhorroCab getCodigoTalLibAhoCab() {
        return codigoTalLibAhoCab;
    }

    public void setCodigoTalLibAhoCab(TalonarioLibretaAhorroCab codigoTalLibAhoCab) {
        this.codigoTalLibAhoCab = codigoTalLibAhoCab;
    }

    /*public LibretaAsignadaCuenta getLibretaAsignadaCuenta() {
        return libretaAsignadaCuenta;
    }

    public void setLibretaAsignadaCuenta(LibretaAsignadaCuenta libretaAsignadaCuenta) {
        this.libretaAsignadaCuenta = libretaAsignadaCuenta;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroLibreta != null ? numeroLibreta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TalonarioLibretaAhorroDet)) {
            return false;
        }
        TalonarioLibretaAhorroDet other = (TalonarioLibretaAhorroDet) object;
        if ((this.numeroLibreta == null && other.numeroLibreta != null) || (this.numeroLibreta != null && !this.numeroLibreta.equals(other.numeroLibreta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.TalonarioLibretaAhorroDet[ numeroLibreta=" + numeroLibreta + " ]";
    }
    
}
