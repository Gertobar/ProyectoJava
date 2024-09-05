/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.FRACCION_MONEDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FraccionMoneda.findAll", query = "SELECT f FROM FraccionMoneda f"),
    @NamedQuery(name = "FraccionMoneda.findByCodigoTipoFraccion", query = "SELECT f FROM FraccionMoneda f WHERE f.fraccionMonedaPK.codigoTipoFraccion = :codigoTipoFraccion"),
    @NamedQuery(name = "FraccionMoneda.findByCodigoMoneda", query = "SELECT f FROM FraccionMoneda f WHERE f.fraccionMonedaPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "FraccionMoneda.findByOrden", query = "SELECT f FROM FraccionMoneda f WHERE f.orden = :orden"),
    @NamedQuery(name = "FraccionMoneda.findByRegistradoPor", query = "SELECT f FROM FraccionMoneda f WHERE f.registradoPor = :registradoPor"),
    @NamedQuery(name = "FraccionMoneda.findByFechaRegistro", query = "SELECT f FROM FraccionMoneda f WHERE f.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "FraccionMoneda.findByEliminado", query = "SELECT f FROM FraccionMoneda f WHERE f.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "FraccionMoneda.findByEliminadoTipoFranccionMoneda", query = "SELECT f FROM FraccionMoneda f JOIN f.tipoFraccionMoneda t WHERE f.fraccionMonedaPK.codigoMoneda = :codigoMoneda AND  f.eliminado = :eliminado AND t.eliminado = :eliminado ORDER BY f.orden")
})
public class FraccionMoneda implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminadoTipoFranccionMoneda = "FraccionMoneda.findByEliminadoTipoFranccionMoneda";
    @EmbeddedId
    protected FraccionMonedaPK fraccionMonedaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN")
    private int orden;
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
    @JoinColumn(name = "CODIGO_TIPO_FRACCION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoFraccionMoneda tipoFraccionMoneda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fraccionMoneda", fetch = FetchType.LAZY)
    private Collection<AperturaDesgloceEfectivo> aperturaDesgloceEfectivoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fraccionMoneda", fetch =  FetchType.LAZY)
    private Collection<FondeoCajaDesgloceEfectivo> fondeoCajaDesgloceEfectivoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fraccionMoneda", fetch = FetchType.LAZY)
    private Collection<CierreDesgloceEfectivo> cierreDesgloceEfectivoCollection;

    public FraccionMoneda() {
    }

    public FraccionMoneda(FraccionMonedaPK fraccionMonedaPK) {
        this.fraccionMonedaPK = fraccionMonedaPK;
    }

    public FraccionMoneda(FraccionMonedaPK fraccionMonedaPK, int orden, long registradoPor, Date fechaRegistro, char eliminado) {
        this.fraccionMonedaPK = fraccionMonedaPK;
        this.orden = orden;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public FraccionMoneda(long codigoTipoFraccion, long codigoMoneda) {
        this.fraccionMonedaPK = new FraccionMonedaPK(codigoTipoFraccion, codigoMoneda);
    }

    public FraccionMonedaPK getFraccionMonedaPK() {
        return fraccionMonedaPK;
    }

    public void setFraccionMonedaPK(FraccionMonedaPK fraccionMonedaPK) {
        this.fraccionMonedaPK = fraccionMonedaPK;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
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

    public TipoFraccionMoneda getTipoFraccionMoneda() {
        return tipoFraccionMoneda;
    }

    public void setTipoFraccionMoneda(TipoFraccionMoneda tipoFraccionMoneda) {
        this.tipoFraccionMoneda = tipoFraccionMoneda;
    }

    @XmlTransient
    public Collection<AperturaDesgloceEfectivo> getAperturaDesgloceEfectivoCollection() {
        return aperturaDesgloceEfectivoCollection;
    }

    public void setAperturaDesgloceEfectivoCollection(Collection<AperturaDesgloceEfectivo> aperturaDesgloceEfectivoCollection) {
        this.aperturaDesgloceEfectivoCollection = aperturaDesgloceEfectivoCollection;
    }

    @XmlTransient
    public Collection<FondeoCajaDesgloceEfectivo> getFondeoCajaDesgloceEfectivoCollection() {
        return fondeoCajaDesgloceEfectivoCollection;
    }

    public void setFondeoCajaDesgloceEfectivoCollection(Collection<FondeoCajaDesgloceEfectivo> fondeoCajaDesgloceEfectivoCollection) {
        this.fondeoCajaDesgloceEfectivoCollection = fondeoCajaDesgloceEfectivoCollection;
    }

    @XmlTransient
    public Collection<CierreDesgloceEfectivo> getCierreDesgloceEfectivoCollection() {
        return cierreDesgloceEfectivoCollection;
    }

    public void setCierreDesgloceEfectivoCollection(Collection<CierreDesgloceEfectivo> cierreDesgloceEfectivoCollection) {
        this.cierreDesgloceEfectivoCollection = cierreDesgloceEfectivoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fraccionMonedaPK != null ? fraccionMonedaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FraccionMoneda)) {
            return false;
        }
        FraccionMoneda other = (FraccionMoneda) object;
        if ((this.fraccionMonedaPK == null && other.fraccionMonedaPK != null) || (this.fraccionMonedaPK != null && !this.fraccionMonedaPK.equals(other.fraccionMonedaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.FraccionMoneda[ fraccionMonedaPK=" + fraccionMonedaPK + " ]";
    }
    
}
