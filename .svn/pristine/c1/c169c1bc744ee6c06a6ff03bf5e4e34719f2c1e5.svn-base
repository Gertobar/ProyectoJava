/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.RUBRO_CONCEPTO_TRANSACCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RubroConceptoTransaccion.findAll", query = "SELECT r FROM RubroConceptoTransaccion r"),
    @NamedQuery(name = "RubroConceptoTransaccion.findByCodigoRubro", query = "SELECT r FROM RubroConceptoTransaccion r WHERE r.codigoRubro = :codigoRubro"),
    @NamedQuery(name = "RubroConceptoTransaccion.findByCodigoTipoProducto", query = "SELECT r FROM RubroConceptoTransaccion r WHERE r.codigoTipoProducto = :codigoTipoProducto"),
    @NamedQuery(name = "RubroConceptoTransaccion.findByCodigoMoneda", query = "SELECT r FROM RubroConceptoTransaccion r WHERE r.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "RubroConceptoTransaccion.findByCodigoConcepto", query = "SELECT r FROM RubroConceptoTransaccion r WHERE r.codigoConcepto = :codigoConcepto"),
    @NamedQuery(name = "RubroConceptoTransaccion.findByRegistradoPor", query = "SELECT r FROM RubroConceptoTransaccion r WHERE r.registradoPor = :registradoPor"),
    @NamedQuery(name = "RubroConceptoTransaccion.findByFechaRegistro", query = "SELECT r FROM RubroConceptoTransaccion r WHERE r.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "RubroConceptoTransaccion.findByEliminado", query = "SELECT r FROM RubroConceptoTransaccion r WHERE r.eliminado = :eliminado")})
public class RubroConceptoTransaccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_RUBRO")
    private Long codigoRubro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_PRODUCTO")
    private long codigoTipoProducto;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CONCEPTO")
    private long codigoConcepto;
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
    /*@JoinColumn(name = "CODIGO_RUBRO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TipoRubroProCreMonIfi tipoRubroProCreMonIfi;*/

    public RubroConceptoTransaccion() {
    }

    public RubroConceptoTransaccion(Long codigoRubro) {
        this.codigoRubro = codigoRubro;
    }

    public RubroConceptoTransaccion(Long codigoRubro, long codigoTipoProducto, long codigoMoneda, long codigoConcepto, long registradoPor, Date fechaRegistro, char eliminado) {
        this.codigoRubro = codigoRubro;
        this.codigoTipoProducto = codigoTipoProducto;
        this.codigoMoneda = codigoMoneda;
        this.codigoConcepto = codigoConcepto;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public Long getCodigoRubro() {
        return codigoRubro;
    }

    public void setCodigoRubro(Long codigoRubro) {
        this.codigoRubro = codigoRubro;
    }

    public long getCodigoTipoProducto() {
        return codigoTipoProducto;
    }

    public void setCodigoTipoProducto(long codigoTipoProducto) {
        this.codigoTipoProducto = codigoTipoProducto;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoConcepto() {
        return codigoConcepto;
    }

    public void setCodigoConcepto(long codigoConcepto) {
        this.codigoConcepto = codigoConcepto;
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

  /*  public TipoRubroProCreMonIfi getTipoRubroProCreMonIfi() {
        return tipoRubroProCreMonIfi;
    }

    public void setTipoRubroProCreMonIfi(TipoRubroProCreMonIfi tipoRubroProCreMonIfi) {
        this.tipoRubroProCreMonIfi = tipoRubroProCreMonIfi;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoRubro != null ? codigoRubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RubroConceptoTransaccion)) {
            return false;
        }
        RubroConceptoTransaccion other = (RubroConceptoTransaccion) object;
        if ((this.codigoRubro == null && other.codigoRubro != null) || (this.codigoRubro != null && !this.codigoRubro.equals(other.codigoRubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.RubroConceptoTransaccion[ codigoRubro=" + codigoRubro + " ]";
    }
    
}
