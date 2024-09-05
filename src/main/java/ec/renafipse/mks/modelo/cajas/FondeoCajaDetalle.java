/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.FONDEO_CAJA_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FondeoCajaDetalle.findAll", query = "SELECT f FROM FondeoCajaDetalle f"),
    @NamedQuery(name = "FondeoCajaDetalle.findByCodigoFondeo", query = "SELECT f FROM FondeoCajaDetalle f WHERE f.fondeoCajaDetallePK.codigoFondeo = :codigoFondeo"),
    @NamedQuery(name = "FondeoCajaDetalle.findByCodigoMoneda", query = "SELECT f FROM FondeoCajaDetalle f WHERE f.fondeoCajaDetallePK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "FondeoCajaDetalle.findByValorEfectivo", query = "SELECT f FROM FondeoCajaDetalle f WHERE f.valorEfectivo = :valorEfectivo"),
    @NamedQuery(name = "FondeoCajaDetalle.findByTotalFondeo", query = "SELECT f FROM FondeoCajaDetalle f WHERE f.totalFondeo = :totalFondeo")})
public class FondeoCajaDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoFondeo = "FondeoCajaDetalle.findByCodigoFondeo";
    
    @EmbeddedId
    protected FondeoCajaDetallePK fondeoCajaDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_EFECTIVO")
    private BigDecimal valorEfectivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_FONDEO")
    private BigDecimal totalFondeo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fondeoCajaDetalle", fetch = FetchType.LAZY)
    private Collection<FondeoCajaDesgloceEfectivo> fondeoCajaDesgloceEfectivoCollection;
    @JoinColumn(name = "CODIGO_FONDEO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FondeoCaja fondeoCaja;
    @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda;
    
    public FondeoCajaDetalle() {
    }

    public FondeoCajaDetalle(FondeoCajaDetallePK fondeoCajaDetallePK) {
        this.fondeoCajaDetallePK = fondeoCajaDetallePK;
    }

    public FondeoCajaDetalle(FondeoCajaDetallePK fondeoCajaDetallePK, BigDecimal valorEfectivo, BigDecimal totalFondeo) {
        this.fondeoCajaDetallePK = fondeoCajaDetallePK;
        this.valorEfectivo = valorEfectivo;
        this.totalFondeo = totalFondeo;
    }

    public FondeoCajaDetalle(long codigoFondeo, long codigoMoneda) {
        this.fondeoCajaDetallePK = new FondeoCajaDetallePK(codigoFondeo, codigoMoneda);
    }

    public FondeoCajaDetallePK getFondeoCajaDetallePK() {
        return fondeoCajaDetallePK;
    }

    public void setFondeoCajaDetallePK(FondeoCajaDetallePK fondeoCajaDetallePK) {
        this.fondeoCajaDetallePK = fondeoCajaDetallePK;
    }

    public BigDecimal getValorEfectivo() {
        return valorEfectivo;
    }

    public void setValorEfectivo(BigDecimal valorEfectivo) {
        this.valorEfectivo = valorEfectivo;
    }

    public BigDecimal getTotalFondeo() {
        return totalFondeo;
    }

    public void setTotalFondeo(BigDecimal totalFondeo) {
        this.totalFondeo = totalFondeo;
    }

    @XmlTransient
    public Collection<FondeoCajaDesgloceEfectivo> getFondeoCajaDesgloceEfectivoCollection() {
        return fondeoCajaDesgloceEfectivoCollection;
    }

    public void setFondeoCajaDesgloceEfectivoCollection(Collection<FondeoCajaDesgloceEfectivo> fondeoCajaDesgloceEfectivoCollection) {
        this.fondeoCajaDesgloceEfectivoCollection = fondeoCajaDesgloceEfectivoCollection;
    }

    public FondeoCaja getFondeoCaja() {
        return fondeoCaja;
    }

    public void setFondeoCaja(FondeoCaja fondeoCaja) {
        this.fondeoCaja = fondeoCaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fondeoCajaDetallePK != null ? fondeoCajaDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FondeoCajaDetalle)) {
            return false;
        }
        FondeoCajaDetalle other = (FondeoCajaDetalle) object;
        if ((this.fondeoCajaDetallePK == null && other.fondeoCajaDetallePK != null) || (this.fondeoCajaDetallePK != null && !this.fondeoCajaDetallePK.equals(other.fondeoCajaDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.FondeoCajaDetalle[ fondeoCajaDetallePK=" + fondeoCajaDetallePK + " ]";
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
    
}
