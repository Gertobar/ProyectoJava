/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.CONCEPTO_TRANSACCION_LOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptoTransaccionLote.findAll", query = "SELECT c FROM ConceptoTransaccionLote c"),
    @NamedQuery(name = "ConceptoTransaccionLote.findByCodigo", query = "SELECT c FROM ConceptoTransaccionLote c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "ConceptoTransaccionLote.findByCodigoIfip", query = "SELECT c FROM ConceptoTransaccionLote c WHERE c.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ConceptoTransaccionLote.findByRegistradoPor", query = "SELECT c FROM ConceptoTransaccionLote c WHERE c.registradoPor = :registradoPor"),
    @NamedQuery(name = "ConceptoTransaccionLote.findByFechaRegistro", query = "SELECT c FROM ConceptoTransaccionLote c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ConceptoTransaccionLote.findByEliminado", query = "SELECT c FROM ConceptoTransaccionLote c WHERE c.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "ConceptoTransaccionLote.findByConceptoTransaccionEjecucion", query = "SELECT c FROM ConceptoTransaccionLote l JOIN l.conceptoTransaccionPro p JOIN p.conceptoTransaccion c  JOIN c.codigoTransaccion t  WHERE p.conceptoTransaccionProPK.codigoTipoProducto = :codigoTipoProducto AND p.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda AND  t.codigo = :codigoTransaccion AND  l.codigoIfip = :codigoIfip AND l.eliminado = :eliminado AND p.eliminado = :eliminado AND c.eliminado = :eliminado AND t.eliminado = :eliminado"),
    @NamedQuery(name = "ConceptoTransaccionLote.findByTransaccionEjecucion", query = "SELECT DISTINCT t FROM ConceptoTransaccionLote l JOIN l.conceptoTransaccionPro p JOIN p.conceptoTransaccion c  JOIN c.codigoTransaccion t  WHERE p.conceptoTransaccionProPK.codigoTipoProducto = :codigoTipoProducto AND p.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda  AND  l.codigoIfip = :codigoIfip  AND l.eliminado = :eliminado AND p.eliminado = :eliminado AND c.eliminado = :eliminado AND t.eliminado = :eliminado")
})
public class ConceptoTransaccionLote implements Serializable {
    public static final String findByConceptoTransaccionEjecucion = "ConceptoTransaccionLote.findByConceptoTransaccionEjecucion";
    public static final String findByTransaccionEjecucion = "ConceptoTransaccionLote.findByTransaccionEjecucion";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
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
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable =  false, updatable =  false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable =  false, updatable =  false),
        @JoinColumn(name = "CODIGO_CONCEPTO", referencedColumnName = "CODIGO_CONCEPTO", insertable =  false, updatable =  false)})
    @ManyToOne(optional = false)
    private ConceptoTransaccionPro conceptoTransaccionPro;

    public ConceptoTransaccionLote() {
    }

    public ConceptoTransaccionLote(Long codigo) {
        this.codigo = codigo;
    }

    public ConceptoTransaccionLote(Long codigo, long codigoIfip, long registradoPor, Date fechaRegistro, char eliminado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
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

    public ConceptoTransaccionPro getConceptoTransaccionPro() {
        return conceptoTransaccionPro;
    }

    public void setConceptoTransaccionPro(ConceptoTransaccionPro conceptoTransaccionPro) {
        this.conceptoTransaccionPro = conceptoTransaccionPro;
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
        if (!(object instanceof ConceptoTransaccionLote)) {
            return false;
        }
        ConceptoTransaccionLote other = (ConceptoTransaccionLote) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionLote[ codigo=" + codigo + " ]";
    }

   
}
