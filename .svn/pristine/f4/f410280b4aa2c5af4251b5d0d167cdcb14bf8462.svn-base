/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.COMPROBANTE_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComprobanteDetalle.findAll", query = "SELECT c FROM ComprobanteDetalle c"),
    @NamedQuery(name = "ComprobanteDetalle.findByCodigoComprobante", query = "SELECT c FROM ComprobanteDetalle c WHERE c.comprobanteDetallePK.codigoComprobante = :codigoComprobante"),
    @NamedQuery(name = "ComprobanteDetalle.findByCuentaContable", query = "SELECT c FROM ComprobanteDetalle c WHERE c.comprobanteDetallePK.cuentaContable = :cuentaContable"),
    @NamedQuery(name = "ComprobanteDetalle.findByCodigoTipoPlan", query = "SELECT c FROM ComprobanteDetalle c WHERE c.comprobanteDetallePK.codigoTipoPlan = :codigoTipoPlan"),
    @NamedQuery(name = "ComprobanteDetalle.findByTipo", query = "SELECT c FROM ComprobanteDetalle c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "ComprobanteDetalle.findByValor", query = "SELECT c FROM ComprobanteDetalle c WHERE c.valor = :valor"),
    @NamedQuery(name = "ComprobanteDetalle.findByLinea", query = "SELECT c FROM ComprobanteDetalle c WHERE c.linea = :linea"),
//Personalizadas
    @NamedQuery(name = "ComprobanteDetalle.findByIfipEntreFechaCuenta", query = "SELECT c FROM ComprobanteDetalle c WHERE c.comprobanteDetallePK.cuentaContable = :cuentaContable AND c.comprobanteDetallePK.codigoTipoPlan = :codigoTipoPlan AND c.planDeCuentaIfip.planDeCuentaIfipPK.codigoIfip = :codigoIfip AND c.comprobante.fecha >= :fechaInicio AND c.comprobante.fecha <= :fechaFin  AND c.comprobante.codigoEstado.codigo  IN (1, 3) ORDER BY c.comprobanteDetallePK.codigoComprobante, c.linea")
})
public class ComprobanteDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoComprobante="ComprobanteDetalle.findByCodigoComprobante";
    public static final String findByIfipEntreFechaCuenta="ComprobanteDetalle.findByIfipEntreFechaCuenta";
    @EmbeddedId
    protected ComprobanteDetallePK comprobanteDetallePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINEA")
    private short linea;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN", insertable = false, updatable = false),
        @JoinColumn(name = "CUENTA_CONTABLE", referencedColumnName = "CUENTA_CONTABLE", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PlanDeCuentaIfip planDeCuentaIfip;
    @JoinColumn(name = "CODIGO_COMPROBANTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comprobante comprobante;

    public ComprobanteDetalle() {
    }

    public ComprobanteDetalle(ComprobanteDetallePK comprobanteDetallePK) {
        this.comprobanteDetallePK = comprobanteDetallePK;
    }

    public ComprobanteDetalle(ComprobanteDetallePK comprobanteDetallePK, char tipo, BigDecimal valor, short linea) {
        this.comprobanteDetallePK = comprobanteDetallePK;
        this.tipo = tipo;
        this.valor = valor;
        this.linea = linea;
    }

    public ComprobanteDetalle(long codigoComprobante, String cuentaContable, long codigoTipoPlan) {
        this.comprobanteDetallePK = new ComprobanteDetallePK(codigoComprobante, cuentaContable, codigoTipoPlan);
    }

    public ComprobanteDetallePK getComprobanteDetallePK() {
        return comprobanteDetallePK;
    }

    public void setComprobanteDetallePK(ComprobanteDetallePK comprobanteDetallePK) {
        this.comprobanteDetallePK = comprobanteDetallePK;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public short getLinea() {
        return linea;
    }

    public void setLinea(short linea) {
        this.linea = linea;
    }

    public PlanDeCuentaIfip getPlanDeCuentaIfip() {
        return planDeCuentaIfip;
    }

    public void setPlanDeCuentaIfip(PlanDeCuentaIfip planDeCuentaIfip) {
        this.planDeCuentaIfip = planDeCuentaIfip;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comprobanteDetallePK != null ? comprobanteDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComprobanteDetalle)) {
            return false;
        }
        ComprobanteDetalle other = (ComprobanteDetalle) object;
        if ((this.comprobanteDetallePK == null && other.comprobanteDetallePK != null) || (this.comprobanteDetallePK != null && !this.comprobanteDetallePK.equals(other.comprobanteDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ComprobanteDetalle[ comprobanteDetallePK=" + comprobanteDetallePK + " ]";
    }

}
