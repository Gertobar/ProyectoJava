/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.PAGO_COMPRA_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoCompraDetalle.findAll", query = "SELECT p FROM PagoCompraDetalle p"),
    @NamedQuery(name = "PagoCompraDetalle.findByCodigoPago", query = "SELECT p FROM PagoCompraDetalle p WHERE p.pagoCompra.codigo = :codigoPago"),
    @NamedQuery(name = "PagoCompraDetalle.findByCodigoFormaPago", query = "SELECT p FROM PagoCompraDetalle p WHERE p.formaPago = :codigoFormaPago"),
    @NamedQuery(name = "PagoCompraDetalle.findByValor", query = "SELECT p FROM PagoCompraDetalle p WHERE p.valor = :valor"),
    @NamedQuery(name = "PagoCompraDetalle.findMax", query = "SELECT NVL(max(p.codigo),0) FROM PagoCompraDetalle p"),
    @NamedQuery(name = "PagoCompraDetalle.findByCodigo", query = "SELECT p FROM PagoCompraDetalle p WHERE p.codigo = :codigo")
})
public class PagoCompraDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPago = "PagoCompraDetalle.findByCodigoPago";
    public static final String findMax = "PagoCompraDetalle.findMax";
    public static final String findByCodigo = "PagoCompraDetalle.findByCodigo";
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PAGO_COMPRA_DETALLE")
    @SequenceGenerator(name = "GSQ_PAGO_COMPRA_DETALLE", schema = "MKS_ADQUISICIONES", allocationSize = 0, sequenceName = "SEQ_PAGO_COMPRA_DETALLE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Min(value=1)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @JoinColumn(name = "CODIGO_PAGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PagoCompra pagoCompra;
    @JoinColumn(name = "CODIGO_FORMA_PAGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FormaPago formaPago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagoCompraDetalle", fetch = FetchType.LAZY)
    private Collection<PagoCompraDetalleCheque> pagoCompraDetalleChequeCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagoCompraDetalleCueA", fetch = FetchType.LAZY)
    private Collection<PagoCompraDetalleCueAh> pagoCompraDetalleCueAhCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagoCompraDetalleTra", fetch = FetchType.LAZY)
    private Collection<PagoCompraDetalleTransf> pagoCompraDetalleTranfsCollection;  
    
    public PagoCompraDetalle() {
    }

     
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public PagoCompra getPagoCompra() {
        return pagoCompra;
    }

    public void setPagoCompra(PagoCompra pagoCompra) {
        this.pagoCompra = pagoCompra;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    @XmlTransient
    public Collection<PagoCompraDetalleCheque> getPagoCompraDetalleChequeCollection() {
        return pagoCompraDetalleChequeCollection;
    }

    public void setPagoCompraDetalleChequeCollection(Collection<PagoCompraDetalleCheque> pagoCompraDetalleChequeCollection) {
        this.pagoCompraDetalleChequeCollection = pagoCompraDetalleChequeCollection;
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
        if (!(object instanceof PagoCompraDetalle)) {
            return false;
        }
        PagoCompraDetalle other = (PagoCompraDetalle) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.PagoCompraDetalle[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the pagoCompraDetalleCueAhCollection
     */
    public Collection<PagoCompraDetalleCueAh> getPagoCompraDetalleCueAhCollection() {
        return pagoCompraDetalleCueAhCollection;
    }

    /**
     * @param pagoCompraDetalleCueAhCollection the pagoCompraDetalleCueAhCollection to set
     */
    public void setPagoCompraDetalleCueAhCollection(Collection<PagoCompraDetalleCueAh> pagoCompraDetalleCueAhCollection) {
        this.pagoCompraDetalleCueAhCollection = pagoCompraDetalleCueAhCollection;
    }

    /**
     * @return the pagoCompraDetalleTranfsCollection
     */
    public Collection<PagoCompraDetalleTransf> getPagoCompraDetalleTranfsCollection() {
        return pagoCompraDetalleTranfsCollection;
    }

    /**
     * @param pagoCompraDetalleTranfsCollection the pagoCompraDetalleTranfsCollection to set
     */
    public void setPagoCompraDetalleTranfsCollection(Collection<PagoCompraDetalleTransf> pagoCompraDetalleTranfsCollection) {
        this.pagoCompraDetalleTranfsCollection = pagoCompraDetalleTranfsCollection;
    }
    
}
