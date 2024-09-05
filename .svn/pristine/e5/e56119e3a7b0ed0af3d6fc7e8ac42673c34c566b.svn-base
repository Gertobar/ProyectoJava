/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.contables;

import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "MKS_CONTABLES.PROCESO_CONTABLE_CON_TRA_PRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoContableConTraPro.findAll", query = "SELECT p FROM ProcesoContableConTraPro p"),
    @NamedQuery(name = "ProcesoContableConTraPro.findByCodigoProceso", query = "SELECT p FROM ProcesoContableConTraPro p WHERE p.procesoContableConTraProPK.codigoProceso = :codigoProceso"),
    @NamedQuery(name = "ProcesoContableConTraPro.findByCodigoTipoProducto", query = "SELECT p FROM ProcesoContableConTraPro p WHERE p.procesoContableConTraProPK.codigoTipoProducto = :codigoTipoProducto"),
    @NamedQuery(name = "ProcesoContableConTraPro.findByCodigoMoneda", query = "SELECT p FROM ProcesoContableConTraPro p WHERE p.procesoContableConTraProPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "ProcesoContableConTraPro.findByCodigoConcepto", query = "SELECT p FROM ProcesoContableConTraPro p WHERE p.procesoContableConTraProPK.codigoConcepto = :codigoConcepto"),
    @NamedQuery(name = "ProcesoContableConTraPro.findByRegistradoPor", query = "SELECT p FROM ProcesoContableConTraPro p WHERE p.registradoPor = :registradoPor"),
    @NamedQuery(name = "ProcesoContableConTraPro.findByFechaRegistro", query = "SELECT p FROM ProcesoContableConTraPro p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ProcesoContableConTraPro.findByEliminado", query = "SELECT p FROM ProcesoContableConTraPro p WHERE p.eliminado = :eliminado"),

    //personalizadas
    @NamedQuery(name = "ProcesoContableConTraPro.findByProcesoContableMonedaTipoProductoTransaccionConcepto",
            query = "SELECT p FROM ProcesoContableConTraPro p JOIN p.conceptoTransaccionPro ptp WHERE p.procesoContableConTraProPK.codigoMoneda = :codigoMoneda and p.procesoContableConTraProPK.codigoTipoProducto = :codigoTipoProducto and ptp.conceptoTransaccion.codigoTransaccion.codigo = :codigoTransaccion and p.procesoContableConTraProPK.codigoConcepto=:codigoConcepto and p.eliminado= :eliminado"),
    @NamedQuery(name = "ProcesoContableConTraPro.findByProcesoContableMonedaTipoProductoTransaccionConceptotodos",
            query = "SELECT p FROM ProcesoContableConTraPro p JOIN p.conceptoTransaccionPro ptp WHERE p.procesoContableConTraProPK.codigoMoneda = :codigoMoneda and p.procesoContableConTraProPK.codigoTipoProducto = :codigoTipoProducto and ptp.conceptoTransaccion.codigoTransaccion.codigo = :codigoTransaccion and p.procesoContableConTraProPK.codigoConcepto=:codigoConcepto")

})
public class ProcesoContableConTraPro implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByProcesoContableMonedaTipoProductoTransaccionConcepto = "ProcesoContableConTraPro.findByProcesoContableMonedaTipoProductoTransaccionConcepto";
    public static final String findByProcesoContableMonedaTipoProductoTransaccionConceptotodos = "ProcesoContableConTraPro.findByProcesoContableMonedaTipoProductoTransaccionConceptotodos";
    @EmbeddedId
    protected ProcesoContableConTraProPK procesoContableConTraProPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "ELIMINADO")
    private Character eliminado;
    @JoinColumn(name = "CODIGO_PROCESO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProcesoContable procesoContable;

    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_CONCEPTO", referencedColumnName = "CODIGO_CONCEPTO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ConceptoTransaccionPro conceptoTransaccionPro;

    public ProcesoContableConTraPro() {
    }

    public ProcesoContableConTraPro(ProcesoContableConTraProPK procesoContableConTraProPK) {
        this.procesoContableConTraProPK = procesoContableConTraProPK;
    }

    public ProcesoContableConTraPro(ProcesoContableConTraProPK procesoContableConTraProPK, long registradoPor) {
        this.procesoContableConTraProPK = procesoContableConTraProPK;
        this.registradoPor = registradoPor;
    }

    public ProcesoContableConTraPro(long codigoProceso, long codigoTipoProducto, long codigoMoneda, long codigoConcepto) {
        this.procesoContableConTraProPK = new ProcesoContableConTraProPK(codigoProceso, codigoTipoProducto, codigoMoneda, codigoConcepto);
    }

    public ProcesoContableConTraProPK getProcesoContableConTraProPK() {
        return procesoContableConTraProPK;
    }

    public void setProcesoContableConTraProPK(ProcesoContableConTraProPK procesoContableConTraProPK) {
        this.procesoContableConTraProPK = procesoContableConTraProPK;
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

    public Character getEliminado() {
        return eliminado;
    }

    public void setEliminado(Character eliminado) {
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
        hash += (procesoContableConTraProPK != null ? procesoContableConTraProPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoContableConTraPro)) {
            return false;
        }
        ProcesoContableConTraPro other = (ProcesoContableConTraPro) object;
        if ((this.procesoContableConTraProPK == null && other.procesoContableConTraProPK != null) || (this.procesoContableConTraProPK != null && !this.procesoContableConTraProPK.equals(other.procesoContableConTraProPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ProcesoContableConTraPro[ procesoContableConTraProPK=" + procesoContableConTraProPK + " ]";
    }

    /**
     * @return the conceptoTransaccionPro
     */
    public ConceptoTransaccionPro getConceptoTransaccionPro() {
        return conceptoTransaccionPro;
    }

    /**
     * @param conceptoTransaccionPro the conceptoTransaccionPro to set
     */
    public void setConceptoTransaccionPro(ConceptoTransaccionPro conceptoTransaccionPro) {
        this.conceptoTransaccionPro = conceptoTransaccionPro;
    }

}
