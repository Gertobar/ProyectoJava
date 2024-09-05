/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import ec.renafipse.mks.modelo.creditos.TipoCarteraMaduracion;
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
@Table(name = "MKS_CONTABLES.PROCESO_CONTABLE_MAD_CAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoContableMadCar.findAll", query = "SELECT p FROM ProcesoContableMadCar p"),
    @NamedQuery(name = "ProcesoContableMadCar.findByCodigoProceso", query = "SELECT p FROM ProcesoContableMadCar p WHERE p.procesoContableMadCarPK.codigoProceso = :codigoProceso"),
    @NamedQuery(name = "ProcesoContableMadCar.findByCodigoMaduracion", query = "SELECT p FROM ProcesoContableMadCar p WHERE p.procesoContableMadCarPK.codigoMaduracion = :codigoMaduracion"),
    @NamedQuery(name = "ProcesoContableMadCar.findByRegistradoPor", query = "SELECT p FROM ProcesoContableMadCar p WHERE p.registradoPor = :registradoPor"),
    @NamedQuery(name = "ProcesoContableMadCar.findByFechaRegistro", query = "SELECT p FROM ProcesoContableMadCar p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ProcesoContableMadCar.findByEliminado", query = "SELECT p FROM ProcesoContableMadCar p WHERE p.eliminado = :eliminado")})
public class ProcesoContableMadCar implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProcesoContableMadCarPK procesoContableMadCarPK;
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
    @JoinColumn(name = "CODIGO_PROCESO", referencedColumnName = "CODIGO",insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProcesoContable procesoContable;
    @JoinColumn(name = "CODIGO_MADURACION", referencedColumnName = "CODIGO",insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoCarteraMaduracion tipoCarteraMaduracion;

    public ProcesoContableMadCar() {
    }

    public ProcesoContableMadCar(ProcesoContableMadCarPK procesoContableMadCarPK) {
        this.procesoContableMadCarPK = procesoContableMadCarPK;
    }

    public ProcesoContableMadCar(ProcesoContableMadCarPK procesoContableMadCarPK, long registradoPor, Date fechaRegistro, char eliminado) {
        this.procesoContableMadCarPK = procesoContableMadCarPK;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public ProcesoContableMadCar(long codigoProceso, long codigoMaduracion) {
        this.procesoContableMadCarPK = new ProcesoContableMadCarPK(codigoProceso, codigoMaduracion);
    }

    public ProcesoContableMadCarPK getProcesoContableMadCarPK() {
        return procesoContableMadCarPK;
    }

    public void setProcesoContableMadCarPK(ProcesoContableMadCarPK procesoContableMadCarPK) {
        this.procesoContableMadCarPK = procesoContableMadCarPK;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (procesoContableMadCarPK != null ? procesoContableMadCarPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoContableMadCar)) {
            return false;
        }
        ProcesoContableMadCar other = (ProcesoContableMadCar) object;
        if ((this.procesoContableMadCarPK == null && other.procesoContableMadCarPK != null) || (this.procesoContableMadCarPK != null && !this.procesoContableMadCarPK.equals(other.procesoContableMadCarPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ProcesoContableMadCar[ procesoContableMadCarPK=" + procesoContableMadCarPK + " ]";
    }

    /**
     * @return the procesoContable
     */
    public ProcesoContable getProcesoContable() {
        return procesoContable;
    }

    /**
     * @param procesoContable the procesoContable to set
     */
    public void setProcesoContable(ProcesoContable procesoContable) {
        this.procesoContable = procesoContable;
    }

    /**
     * @return the tipoCarteraMaduracion
     */
    public TipoCarteraMaduracion getTipoCarteraMaduracion() {
        return tipoCarteraMaduracion;
    }

    /**
     * @param tipoCarteraMaduracion the tipoCarteraMaduracion to set
     */
    public void setTipoCarteraMaduracion(TipoCarteraMaduracion tipoCarteraMaduracion) {
        this.tipoCarteraMaduracion = tipoCarteraMaduracion;
    }
    
}
