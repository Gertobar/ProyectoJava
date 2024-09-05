/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FliaAstudillo
 */
@Entity
@Table(name = "MKS_AHORROS.CONCEPTO_TRANSACCION_TRANSF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptoTransaccionTransf.findAll", query = "SELECT c FROM ConceptoTransaccionTransf c"),
    @NamedQuery(name = "ConceptoTransaccionTransf.findByCodigo", query = "SELECT c FROM ConceptoTransaccionTransf c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "ConceptoTransaccionTransf.findByDescripcion", query = "SELECT c FROM ConceptoTransaccionTransf c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "ConceptoTransaccionTransf.findByEliminado", query = "SELECT c FROM ConceptoTransaccionTransf c WHERE c.eliminado = :eliminado"),
    @NamedQuery(name = "ConceptoTransaccionTransf.findByDescripcionTransferencia", query = "SELECT c FROM ConceptoTransaccionTransf c WHERE c.codigoMoneda = :codigoMoneda AND c.codigoProductoOrigen = :codigoProductoOrigen AND c.codigoIfip = :codigoIfip AND c.eliminado = :eliminado ORDER BY c.descripcion")})
public class ConceptoTransaccionTransf implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "ConceptoTransaccionTransf.findByEliminado";
    public static final String findByDescripcionTransferencia = "ConceptoTransaccionTransf.findByDescripcionTransferencia";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PRODUCTO_ORIGEN")
    private long codigoProductoOrigen;
     @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PRODUCTO_DESTINO")
    private long codigoProductoDestino;
     @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CONCEPTO_ORIGEN")
    private long codigoConceptoOrigen;
     @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CONCEPTO_DESTINO")
    private long codigoConceptoDestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO_DESTINO", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoIfip productoIfipDestino;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO_ORIGEN", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoIfip productoIfipOrigen;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO_DESTINO", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_CONCEPTO_DESTINO", referencedColumnName = "CODIGO_CONCEPTO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ConceptoTransaccionPro conceptoTransaccionProDestino;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO_ORIGEN", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_CONCEPTO_ORIGEN", referencedColumnName = "CODIGO_CONCEPTO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ConceptoTransaccionPro conceptoTransaccionProOrigen;

    public ConceptoTransaccionTransf() {
    }

    public ConceptoTransaccionTransf(Long codigo) {
        this.codigo = codigo;
    }

    public ConceptoTransaccionTransf(Long codigo, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConceptoTransaccionTransf)) {
            return false;
        }
        ConceptoTransaccionTransf other = (ConceptoTransaccionTransf) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionTransf[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoMoneda
     */
    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    /**
     * @param codigoMoneda the codigoMoneda to set
     */
    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }   

    /**
     * @return the codigoIfip
     */
    public long getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    /**
     * @return the codigoProductoOrigen
     */
    public long getCodigoProductoOrigen() {
        return codigoProductoOrigen;
    }

    /**
     * @param codigoProductoOrigen the codigoProductoOrigen to set
     */
    public void setCodigoProductoOrigen(long codigoProductoOrigen) {
        this.codigoProductoOrigen = codigoProductoOrigen;
    }

    /**
     * @return the codigoProductoDestino
     */
    public long getCodigoProductoDestino() {
        return codigoProductoDestino;
    }

    /**
     * @param codigoProductoDestino the codigoProductoDestino to set
     */
    public void setCodigoProductoDestino(long codigoProductoDestino) {
        this.codigoProductoDestino = codigoProductoDestino;
    }

    /**
     * @return the productoIfipDestino
     */
    public ProductoIfip getProductoIfipDestino() {
        return productoIfipDestino;
    }

    /**
     * @param productoIfipDestino the productoIfipDestino to set
     */
    public void setProductoIfipDestino(ProductoIfip productoIfipDestino) {
        this.productoIfipDestino = productoIfipDestino;
    }

    /**
     * @return the productoIfipOrigen
     */
    public ProductoIfip getProductoIfipOrigen() {
        return productoIfipOrigen;
    }

    /**
     * @param productoIfipOrigen the productoIfipOrigen to set
     */
    public void setProductoIfipOrigen(ProductoIfip productoIfipOrigen) {
        this.productoIfipOrigen = productoIfipOrigen;
    }

    /**
     * @return the conceptoTransaccionProDestino
     */
    public ConceptoTransaccionPro getConceptoTransaccionProDestino() {
        return conceptoTransaccionProDestino;
    }

    /**
     * @param conceptoTransaccionProDestino the conceptoTransaccionProDestino to set
     */
    public void setConceptoTransaccionProDestino(ConceptoTransaccionPro conceptoTransaccionProDestino) {
        this.conceptoTransaccionProDestino = conceptoTransaccionProDestino;
    }

    /**
     * @return the conceptoTransaccionProOrigen
     */
    public ConceptoTransaccionPro getConceptoTransaccionProOrigen() {
        return conceptoTransaccionProOrigen;
    }

    /**
     * @param conceptoTransaccionProOrigen the conceptoTransaccionProOrigen to set
     */
    public void setConceptoTransaccionProOrigen(ConceptoTransaccionPro conceptoTransaccionProOrigen) {
        this.conceptoTransaccionProOrigen = conceptoTransaccionProOrigen;
    }

    /**
     * @return the codigoConceptoOrigen
     */
    public long getCodigoConceptoOrigen() {
        return codigoConceptoOrigen;
    }

    /**
     * @param codigoConceptoOrigen the codigoConceptoOrigen to set
     */
    public void setCodigoConceptoOrigen(long codigoConceptoOrigen) {
        this.codigoConceptoOrigen = codigoConceptoOrigen;
    }

    /**
     * @return the codigoConceptoDestino
     */
    public long getCodigoConceptoDestino() {
        return codigoConceptoDestino;
    }

    /**
     * @param codigoConceptoDestino the codigoConceptoDestino to set
     */
    public void setCodigoConceptoDestino(long codigoConceptoDestino) {
        this.codigoConceptoDestino = codigoConceptoDestino;
    }
    
}
