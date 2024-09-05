/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "MKS_CREDITOS.TIPO_CARTERA_MADURACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCarteraMaduracion.findAll", query = "SELECT t FROM TipoCarteraMaduracion t"),
    @NamedQuery(name = "TipoCarteraMaduracion.findByCodigo", query = "SELECT t FROM TipoCarteraMaduracion t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoCarteraMaduracion.findByEliminado", query = "SELECT t FROM TipoCarteraMaduracion t WHERE t.eliminado = :eliminado")})
public class TipoCarteraMaduracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TIPO_MADURACION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoMaduracion codigoTipoMaduracion;
    @JoinColumn(name = "CODITO_TIPO_CARTERA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoCartera coditoTipoCartera;
    @JoinColumn(name = "CODIGO_RANGO_MADURACION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private RangoMaduracion codigoRangoMaduracion;

    public TipoCarteraMaduracion() {
    }

    public TipoCarteraMaduracion(Long codigo) {
        this.codigo = codigo;
    }

    public TipoCarteraMaduracion(Long codigo, char eliminado) {
        this.codigo = codigo;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public TipoMaduracion getCodigoTipoMaduracion() {
        return codigoTipoMaduracion;
    }

    public void setCodigoTipoMaduracion(TipoMaduracion codigoTipoMaduracion) {
        this.codigoTipoMaduracion = codigoTipoMaduracion;
    }

    public TipoCartera getCoditoTipoCartera() {
        return coditoTipoCartera;
    }

    public void setCoditoTipoCartera(TipoCartera coditoTipoCartera) {
        this.coditoTipoCartera = coditoTipoCartera;
    }

    public RangoMaduracion getCodigoRangoMaduracion() {
        return codigoRangoMaduracion;
    }

    public void setCodigoRangoMaduracion(RangoMaduracion codigoRangoMaduracion) {
        this.codigoRangoMaduracion = codigoRangoMaduracion;
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
        if (!(object instanceof TipoCarteraMaduracion)) {
            return false;
        }
        TipoCarteraMaduracion other = (TipoCarteraMaduracion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoCarteraMaduracion[ codigo=" + codigo + " ]";
    }
    
}
