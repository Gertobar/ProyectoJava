/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.INGRESO_EGRESO_MONEDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngresoEgresoMoneda.findAll", query = "SELECT i FROM IngresoEgresoMoneda i"),
    @NamedQuery(name = "IngresoEgresoMoneda.findByCodigoIngresoEgreso", query = "SELECT i FROM IngresoEgresoMoneda i WHERE i.ingresoEgresoMonedaPK.codigoIngresoEgreso = :codigoIngresoEgreso"),
    @NamedQuery(name = "IngresoEgresoMoneda.findByCodigoMoneda", query = "SELECT i FROM IngresoEgresoMoneda i WHERE i.ingresoEgresoMonedaPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "IngresoEgresoMoneda.findByMostrar", query = "SELECT i FROM IngresoEgresoMoneda i WHERE i.mostrar = :mostrar"),
    @NamedQuery(name = "IngresoEgresoMoneda.findByRegistradoPor", query = "SELECT i FROM IngresoEgresoMoneda i WHERE i.registradoPor = :registradoPor"),
    @NamedQuery(name = "IngresoEgresoMoneda.findByFechaRegistro", query = "SELECT i FROM IngresoEgresoMoneda i WHERE i.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "IngresoEgresoMoneda.findByEliminado", query = "SELECT i.ingresoEgreso FROM IngresoEgresoMoneda i WHERE i.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "IngresoEgresoMoneda.findByIngresoEgresoMonedaEliminadoMostrar", query = "SELECT i.ingresoEgreso FROM IngresoEgresoMoneda i JOIN i.ingresoEgreso e WHERE i.ingresoEgresoMonedaPK.codigoMoneda = :codigoMoneda AND i.eliminado = :eliminado AND i.mostrar = :mostrar AND e.esIngreso =:esIngreso AND e.eliminado = :eliminado"),
    @NamedQuery(name = "IngresoEgresoMoneda.findByEsIngresoEliminado", query = "SELECT i FROM IngresoEgresoMoneda i JOIN i.ingresoEgreso ie WHERE ie.esIngreso = :esIngreso AND i.eliminado = :eliminado AND ie.eliminado = :eliminado AND i.mostrar = :mostrar"),
//personalizadas
@NamedQuery(name = "IngresoEgresoMoneda.findbyIfipMoneda",
        query = "SELECT ie FROM IngresoEgresoMoneda i JOIN i.ingresoEgreso ie  WHERE  i.ingresoEgresoMonedaPK.codigoMoneda=:codigoMoneda and ie.eliminado = :eliminado and ie.esIngreso= :tipo and ie.contabiliza=:contabilizaSN")
})
public class IngresoEgresoMoneda implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByIngresoEgresoMonedaEliminadoMostrar = "IngresoEgresoMoneda.findByIngresoEgresoMonedaEliminadoMostrar";
    public static final String findByEsIngresoEliminado = "IngresoEgresoMoneda.findByEsIngresoEliminado";
    public static final String findbyIfipMoneda = "IngresoEgresoMoneda.findbyIfipMoneda";
    @EmbeddedId
    protected IngresoEgresoMonedaPK ingresoEgresoMonedaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOSTRAR")
    private char mostrar;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingresoEgresoMoneda", fetch = FetchType.LAZY)
    private Collection<IngresoEgresoCaja> ingresoEgresoCajaCollection;
    @JoinColumn(name = "CODIGO_INGRESO_EGRESO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IngresoEgreso ingresoEgreso;

    public IngresoEgresoMoneda() {
    }

    public IngresoEgresoMoneda(IngresoEgresoMonedaPK ingresoEgresoMonedaPK) {
        this.ingresoEgresoMonedaPK = ingresoEgresoMonedaPK;
    }

    public IngresoEgresoMoneda(IngresoEgresoMonedaPK ingresoEgresoMonedaPK, char mostrar, long registradoPor, Date fechaRegistro, char eliminado) {
        this.ingresoEgresoMonedaPK = ingresoEgresoMonedaPK;
        this.mostrar = mostrar;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public IngresoEgresoMoneda(long codigoIngresoEgreso, long codigoMoneda) {
        this.ingresoEgresoMonedaPK = new IngresoEgresoMonedaPK(codigoIngresoEgreso, codigoMoneda);
    }

    public IngresoEgresoMonedaPK getIngresoEgresoMonedaPK() {
        return ingresoEgresoMonedaPK;
    }

    public void setIngresoEgresoMonedaPK(IngresoEgresoMonedaPK ingresoEgresoMonedaPK) {
        this.ingresoEgresoMonedaPK = ingresoEgresoMonedaPK;
    }

    public char getMostrar() {
        return mostrar;
    }

    public void setMostrar(char mostrar) {
        this.mostrar = mostrar;
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

    @XmlTransient
    public Collection<IngresoEgresoCaja> getIngresoEgresoCajaCollection() {
        return ingresoEgresoCajaCollection;
    }

    public void setIngresoEgresoCajaCollection(Collection<IngresoEgresoCaja> ingresoEgresoCajaCollection) {
        this.ingresoEgresoCajaCollection = ingresoEgresoCajaCollection;
    }

    public IngresoEgreso getIngresoEgreso() {
        return ingresoEgreso;
    }

    public void setIngresoEgreso(IngresoEgreso ingresoEgreso) {
        this.ingresoEgreso = ingresoEgreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingresoEgresoMonedaPK != null ? ingresoEgresoMonedaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngresoEgresoMoneda)) {
            return false;
        }
        IngresoEgresoMoneda other = (IngresoEgresoMoneda) object;
        if ((this.ingresoEgresoMonedaPK == null && other.ingresoEgresoMonedaPK != null) || (this.ingresoEgresoMonedaPK != null && !this.ingresoEgresoMonedaPK.equals(other.ingresoEgresoMonedaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.IngresoEgresoMoneda[ ingresoEgresoMonedaPK=" + ingresoEgresoMonedaPK + " ]";
    }
    
}
