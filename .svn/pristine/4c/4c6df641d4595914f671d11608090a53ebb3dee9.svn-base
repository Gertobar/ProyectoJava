/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "MKS_AHORROS.LIBRETA_ASIGNADA_CUENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LibretaAsignadaCuenta.findAll", query = "SELECT l FROM LibretaAsignadaCuenta l"),
    @NamedQuery(name = "LibretaAsignadaCuenta.findByNumeroLibreta", query = "SELECT l FROM LibretaAsignadaCuenta l WHERE l.numeroLibreta = :numeroLibreta"),
    @NamedQuery(name = "LibretaAsignadaCuenta.findByAsignadoPor", query = "SELECT l FROM LibretaAsignadaCuenta l WHERE l.asignadoPor = :asignadoPor"),
    @NamedQuery(name = "LibretaAsignadaCuenta.findByFechaAsignacion", query = "SELECT l FROM LibretaAsignadaCuenta l WHERE l.fechaAsignacion = :fechaAsignacion")})
public class LibretaAsignadaCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NUMERO_LIBRETA")
    private String numeroLibreta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASIGNADO_POR")
    private Long codigoUsuarioAsignadoPor;
    @JoinColumn(name = "ASIGNADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario asignadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ASIGNACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    /*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroLibreta", fetch = FetchType.LAZY)
    private Collection<ImpresionLibretaCuenta> impresionLibretaCuentaCollection;
    @JoinColumn(name = "NUMERO_LIBRETA", referencedColumnName = "NUMERO_LIBRETA", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TalonarioLibretaAhorroDet talonarioLibretaAhorroDet;*/
    @JoinColumn(name = "CODIGO_CUENTA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Cuenta codigoCuenta;

    public LibretaAsignadaCuenta() {
    }

    public LibretaAsignadaCuenta(String numeroLibreta) {
        this.numeroLibreta = numeroLibreta;
    }

    public LibretaAsignadaCuenta(String numeroLibreta, Usuario asignadoPor, Date fechaAsignacion) {
        this.numeroLibreta = numeroLibreta;
        this.asignadoPor = asignadoPor;
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getNumeroLibreta() {
        return numeroLibreta;
    }

    public void setNumeroLibreta(String numeroLibreta) {
        this.numeroLibreta = numeroLibreta;
    }

    public Usuario getAsignadoPor() {
        return asignadoPor;
    }

    public void setAsignadoPor(Usuario asignadoPor) {
        this.asignadoPor = asignadoPor;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    /*@XmlTransient
    public Collection<ImpresionLibretaCuenta> getImpresionLibretaCuentaCollection() {
        return impresionLibretaCuentaCollection;
    }

    public void setImpresionLibretaCuentaCollection(Collection<ImpresionLibretaCuenta> impresionLibretaCuentaCollection) {
        this.impresionLibretaCuentaCollection = impresionLibretaCuentaCollection;
    }

    public TalonarioLibretaAhorroDet getTalonarioLibretaAhorroDet() {
        return talonarioLibretaAhorroDet;
    }

    public void setTalonarioLibretaAhorroDet(TalonarioLibretaAhorroDet talonarioLibretaAhorroDet) {
        this.talonarioLibretaAhorroDet = talonarioLibretaAhorroDet;
    }*/

    public Cuenta getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(Cuenta codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroLibreta != null ? numeroLibreta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LibretaAsignadaCuenta)) {
            return false;
        }
        LibretaAsignadaCuenta other = (LibretaAsignadaCuenta) object;
        if ((this.numeroLibreta == null && other.numeroLibreta != null) || (this.numeroLibreta != null && !this.numeroLibreta.equals(other.numeroLibreta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.LibretaAsignadaCuenta[ numeroLibreta=" + numeroLibreta + " ]";
    }

    /**
     * @return the codigoUsuarioAsignadoPor
     */
    public Long getCodigoUsuarioAsignadoPor() {
        return codigoUsuarioAsignadoPor;
    }

    /**
     * @param codigoUsuarioAsignadoPor the codigoUsuarioAsignadoPor to set
     */
    public void setCodigoUsuarioAsignadoPor(Long codigoUsuarioAsignadoPor) {
        this.codigoUsuarioAsignadoPor = codigoUsuarioAsignadoPor;
    }
    
}
