/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.PROCESO_CONTABLE_ING_EGR_MON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoContableIngEgrMon.findAll", query = "SELECT p FROM ProcesoContableIngEgrMon p"),
    @NamedQuery(name = "ProcesoContableIngEgrMon.findByCodigoProceso", query = "SELECT p FROM ProcesoContableIngEgrMon p WHERE p.procesoContableIngEgrMonPK.codigoProceso = :codigoProceso"),
    @NamedQuery(name = "ProcesoContableIngEgrMon.findByCodigoIngresoEgreso", query = "SELECT p FROM ProcesoContableIngEgrMon p WHERE p.procesoContableIngEgrMonPK.codigoIngresoEgreso = :codigoIngresoEgreso"),
    @NamedQuery(name = "ProcesoContableIngEgrMon.findByCodigoMoneda", query = "SELECT p FROM ProcesoContableIngEgrMon p WHERE p.procesoContableIngEgrMonPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "ProcesoContableIngEgrMon.findByRegistradoPor", query = "SELECT p FROM ProcesoContableIngEgrMon p WHERE p.registradoPor = :registradoPor"),
    @NamedQuery(name = "ProcesoContableIngEgrMon.findByFechaRegistro", query = "SELECT p FROM ProcesoContableIngEgrMon p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ProcesoContableIngEgrMon.findByEliminado", query = "SELECT p FROM ProcesoContableIngEgrMon p WHERE p.eliminado = :eliminado"),
    //personalizadas
    @NamedQuery(name = "ProcesoContableIngEgrMon.findByMonedaIngreso", query = "SELECT p FROM ProcesoContableIngEgrMon p WHERE p.procesoContableIngEgrMonPK.codigoMoneda=:codigoMoneda and p.procesoContableIngEgrMonPK.codigoIngresoEgreso= :codigoIngresoegreso  and p.eliminado = :eliminado"),
    @NamedQuery(name = "ProcesoContableIngEgrMon.findByMonedaIngresoTodos", query = "SELECT p FROM ProcesoContableIngEgrMon p WHERE p.procesoContableIngEgrMonPK.codigoMoneda=:codigoMoneda and p.procesoContableIngEgrMonPK.codigoIngresoEgreso= :codigoIngresoegreso "),
    
})
public class ProcesoContableIngEgrMon implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByMonedaIngreso="ProcesoContableIngEgrMon.findByMonedaIngreso";
    public static final String findByMonedaIngresoTodos="ProcesoContableIngEgrMon.findByMonedaIngresoTodos";

    @EmbeddedId
    protected ProcesoContableIngEgrMonPK procesoContableIngEgrMonPK;
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
    @JoinColumn(name = "CODIGO_PROCESO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProcesoContable procesoContable;

    public ProcesoContableIngEgrMon() {
    }

    public ProcesoContableIngEgrMon(ProcesoContableIngEgrMonPK procesoContableIngEgrMonPK) {
        this.procesoContableIngEgrMonPK = procesoContableIngEgrMonPK;
    }

    public ProcesoContableIngEgrMon(ProcesoContableIngEgrMonPK procesoContableIngEgrMonPK, long registradoPor, Date fechaRegistro, char eliminado) {
        this.procesoContableIngEgrMonPK = procesoContableIngEgrMonPK;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public ProcesoContableIngEgrMon(long codigoProceso, long codigoIngresoEgreso, long codigoMoneda) {
        this.procesoContableIngEgrMonPK = new ProcesoContableIngEgrMonPK(codigoProceso, codigoIngresoEgreso, codigoMoneda);
    }

    public ProcesoContableIngEgrMonPK getProcesoContableIngEgrMonPK() {
        return procesoContableIngEgrMonPK;
    }

    public void setProcesoContableIngEgrMonPK(ProcesoContableIngEgrMonPK procesoContableIngEgrMonPK) {
        this.procesoContableIngEgrMonPK = procesoContableIngEgrMonPK;
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

    public ProcesoContable getProcesoContable() {
        return procesoContable;
    }

    public void setProcesoContable(ProcesoContable procesoContable) {
        this.procesoContable = procesoContable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (procesoContableIngEgrMonPK != null ? procesoContableIngEgrMonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoContableIngEgrMon)) {
            return false;
        }
        ProcesoContableIngEgrMon other = (ProcesoContableIngEgrMon) object;
        if ((this.procesoContableIngEgrMonPK == null && other.procesoContableIngEgrMonPK != null) || (this.procesoContableIngEgrMonPK != null && !this.procesoContableIngEgrMonPK.equals(other.procesoContableIngEgrMonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ProcesoContableIngEgrMon[ procesoContableIngEgrMonPK=" + procesoContableIngEgrMonPK + " ]";
    }
    
}
