/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TIPO_RUBRO_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRubroCredito.findAll", query = "SELECT t FROM TipoRubroCredito t"),
    @NamedQuery(name = "TipoRubroCredito.findByCodigoTipoRubro", query = "SELECT t FROM TipoRubroCredito t WHERE t.tipoRubroCreditoPK.codigoTipoRubro = :codigoTipoRubro"),
    @NamedQuery(name = "TipoRubroCredito.findByNumeroCredito", query = "SELECT t FROM TipoRubroCredito t WHERE t.tipoRubroCreditoPK.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "TipoRubroCredito.findByCodigoIfip", query = "SELECT t FROM TipoRubroCredito t WHERE t.tipoRubroCreditoPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "TipoRubroCredito.findByTipo", query = "SELECT t FROM TipoRubroCredito t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TipoRubroCredito.findByCalculadoSobre", query = "SELECT t FROM TipoRubroCredito t WHERE t.calculadoSobre = :calculadoSobre"),
    @NamedQuery(name = "TipoRubroCredito.findByCobradoEn", query = "SELECT t FROM TipoRubroCredito t WHERE t.cobradoEn = :cobradoEn"),
    @NamedQuery(name = "TipoRubroCredito.findByValor", query = "SELECT t FROM TipoRubroCredito t WHERE t.valor = :valor"),
    @NamedQuery(name = "TipoRubroCredito.findByTotalRubroGrabado", query = "SELECT t FROM TipoRubroCredito t WHERE t.totalRubroGrabado = :totalRubroGrabado"),
    @NamedQuery(name = "TipoRubroCredito.findByCodigoMovimiento", query = "SELECT t FROM TipoRubroCredito t WHERE t.codigoMovimiento = :codigoMovimiento"),
    @NamedQuery(name = "TipoRubroCredito.findByNumeroCreditoCodigoIfip", query = "SELECT t FROM TipoRubroCredito t WHERE t.tipoRubroCreditoPK.numeroCredito = :numeroCredito AND t.tipoRubroCreditoPK.codigoIfip = :codigoIfip ORDER BY t.tipoRubroCreditoPK.codigoTipoRubro"),
    
})
public class TipoRubroCredito implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByNumeroCreditoCodigoIfip = "TipoRubroCredito.findByNumeroCreditoCodigoIfip";
    @EmbeddedId
    protected TipoRubroCreditoPK tipoRubroCreditoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CALCULADO_SOBRE")
    private char calculadoSobre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COBRADO_EN")
    private char cobradoEn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_RUBRO_GRABADO")
    private BigDecimal totalRubroGrabado;
    @Column(name = "CODIGO_MOVIMIENTO")
    private Long codigoMovimiento;
    @JoinColumn(name = "CODIGO_TIPO_RUBRO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoRubro tipoRubro;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Solicitud solicitud;

    public TipoRubroCredito() {
    }

    public TipoRubroCredito(TipoRubroCreditoPK tipoRubroCreditoPK) {
        this.tipoRubroCreditoPK = tipoRubroCreditoPK;
    }

    public TipoRubroCredito(TipoRubroCreditoPK tipoRubroCreditoPK, char tipo, char calculadoSobre, char cobradoEn, BigDecimal valor, BigDecimal totalRubroGrabado) {
        this.tipoRubroCreditoPK = tipoRubroCreditoPK;
        this.tipo = tipo;
        this.calculadoSobre = calculadoSobre;
        this.cobradoEn = cobradoEn;
        this.valor = valor;
        this.totalRubroGrabado = totalRubroGrabado;
    }

    public TipoRubroCredito(long codigoTipoRubro, long numeroCredito, long codigoIfip) {
        this.tipoRubroCreditoPK = new TipoRubroCreditoPK(codigoTipoRubro, numeroCredito, codigoIfip);
    }

    public TipoRubroCreditoPK getTipoRubroCreditoPK() {
        return tipoRubroCreditoPK;
    }

    public void setTipoRubroCreditoPK(TipoRubroCreditoPK tipoRubroCreditoPK) {
        this.tipoRubroCreditoPK = tipoRubroCreditoPK;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public char getCalculadoSobre() {
        return calculadoSobre;
    }

    public void setCalculadoSobre(char calculadoSobre) {
        this.calculadoSobre = calculadoSobre;
    }

    public char getCobradoEn() {
        return cobradoEn;
    }

    public void setCobradoEn(char cobradoEn) {
        this.cobradoEn = cobradoEn;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTotalRubroGrabado() {
        return totalRubroGrabado;
    }

    public void setTotalRubroGrabado(BigDecimal totalRubroGrabado) {
        this.totalRubroGrabado = totalRubroGrabado;
    }

    public Long getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(Long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public TipoRubro getTipoRubro() {
        return tipoRubro;
    }

    public void setTipoRubro(TipoRubro tipoRubro) {
        this.tipoRubro = tipoRubro;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoRubroCreditoPK != null ? tipoRubroCreditoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRubroCredito)) {
            return false;
        }
        TipoRubroCredito other = (TipoRubroCredito) object;
        if ((this.tipoRubroCreditoPK == null && other.tipoRubroCreditoPK != null) || (this.tipoRubroCreditoPK != null && !this.tipoRubroCreditoPK.equals(other.tipoRubroCreditoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoRubroCredito[ tipoRubroCreditoPK=" + tipoRubroCreditoPK + " ]";
    }
    
}
