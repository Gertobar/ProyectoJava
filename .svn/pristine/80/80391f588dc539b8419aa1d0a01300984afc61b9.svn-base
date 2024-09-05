/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.BLOQUEO_CUENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BloqueoCuenta.findAll", query = "SELECT b FROM BloqueoCuenta b"),
    @NamedQuery(name = "BloqueoCuenta.findByCodigoCuenta", query = "SELECT b FROM BloqueoCuenta b WHERE b.bloqueoCuentaPK.codigoCuenta = :codigoCuenta"),
    @NamedQuery(name = "BloqueoCuenta.findByCodigoTipoBloqueo", query = "SELECT b FROM BloqueoCuenta b WHERE b.bloqueoCuentaPK.codigoTipoBloqueo = :codigoTipoBloqueo"),
    @NamedQuery(name = "BloqueoCuenta.findByValor", query = "SELECT b FROM BloqueoCuenta b WHERE b.valor = :valor"),
    @NamedQuery(name = "BloqueoCuenta.findByFechaInicio", query = "SELECT b FROM BloqueoCuenta b WHERE b.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "BloqueoCuenta.findByFechaFin", query = "SELECT b FROM BloqueoCuenta b WHERE b.fechaFin = :fechaFin"),
    @NamedQuery(name = "BloqueoCuenta.findByCuentaVigente", query = "SELECT b FROM BloqueoCuenta b WHERE b.cuenta = :cuenta AND b.vigente = :vigente ORDER BY b.valor"),
    @NamedQuery(name = "BloqueoCuenta.findByVigente", query = "SELECT b FROM BloqueoCuenta b WHERE b.vigente = :vigente"),
    @NamedQuery(name = "BloqueoCuenta.findByCuentaVigenteMayorAUnValor", query = "SELECT b FROM BloqueoCuenta b WHERE b.cuenta = :cuenta AND b.vigente = :vigente AND b.valor > :valor ORDER BY b.valor"),
})
public class BloqueoCuenta implements Serializable {
    
    private static final long serialVersionUID = 1L;
    public static final String findByCuentaVigente = "BloqueoCuenta.findByCuentaVigente";
    @EmbeddedId
    protected BloqueoCuentaPK bloqueoCuentaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENTE")
    private char vigente;
    @JoinColumn(name = "CODIGO_TIPO_BLOQUEO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoBloqueoCuenta tipoBloqueoCuenta;
    @JoinColumn(name = "CODIGO_CUENTA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta;

    public BloqueoCuenta() {
    }

    public BloqueoCuenta(BloqueoCuentaPK bloqueoCuentaPK) {
        this.bloqueoCuentaPK = bloqueoCuentaPK;
    }

    public BloqueoCuenta(BloqueoCuentaPK bloqueoCuentaPK, BigDecimal valor, Date fechaInicio, Date fechaFin, char vigente) {
        this.bloqueoCuentaPK = bloqueoCuentaPK;
        this.valor = valor;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vigente = vigente;
    }

    public BloqueoCuenta(long codigoCuenta, long codigoTipoBloqueo) {
        this.bloqueoCuentaPK = new BloqueoCuentaPK(codigoCuenta, codigoTipoBloqueo);
    }

    public BloqueoCuentaPK getBloqueoCuentaPK() {
        return bloqueoCuentaPK;
    }

    public void setBloqueoCuentaPK(BloqueoCuentaPK bloqueoCuentaPK) {
        this.bloqueoCuentaPK = bloqueoCuentaPK;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public char getVigente() {
        return vigente;
    }

    public void setVigente(char vigente) {
        this.vigente = vigente;
    }

    public TipoBloqueoCuenta getTipoBloqueoCuenta() {
        return tipoBloqueoCuenta;
    }

    public void setTipoBloqueoCuenta(TipoBloqueoCuenta tipoBloqueoCuenta) {
        this.tipoBloqueoCuenta = tipoBloqueoCuenta;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bloqueoCuentaPK != null ? bloqueoCuentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BloqueoCuenta)) {
            return false;
        }
        BloqueoCuenta other = (BloqueoCuenta) object;
        if ((this.bloqueoCuentaPK == null && other.bloqueoCuentaPK != null) || (this.bloqueoCuentaPK != null && !this.bloqueoCuentaPK.equals(other.bloqueoCuentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.BloqueoCuenta[ bloqueoCuentaPK=" + bloqueoCuentaPK + " ]";
    }
    
}
