/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.TIPO_COMPROBANTE_VENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoComprobanteVenta.findAll", query = "SELECT t FROM TipoComprobanteVenta t"),
    @NamedQuery(name = "TipoComprobanteVenta.findByCodigo", query = "SELECT t FROM TipoComprobanteVenta t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoComprobanteVenta.findByNombre", query = "SELECT t FROM TipoComprobanteVenta t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoComprobanteVenta.findBySiglas", query = "SELECT t FROM TipoComprobanteVenta t WHERE t.siglas = :siglas"),
    @NamedQuery(name = "TipoComprobanteVenta.findByEliminado", query = "SELECT t FROM TipoComprobanteVenta t WHERE t.eliminado = :eliminado"),
    @NamedQuery(name = "TipoComprobanteVenta.findByEsLiquidacion", query = "SELECT t FROM TipoComprobanteVenta t WHERE t.esLiquidacion = :esLiquidacion")})
public class TipoComprobanteVenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_LIQUIDACION")
    private char esLiquidacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoComprobante")
    private Collection<DocumentosIfipAgencia> documentosIfipAgenciaCollection;

    public TipoComprobanteVenta() {
    }

    public TipoComprobanteVenta(Long codigo) {
        this.codigo = codigo;
    }

    public TipoComprobanteVenta(Long codigo, String nombre, String siglas, char eliminado, char esLiquidacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
        this.eliminado = eliminado;
        this.esLiquidacion = esLiquidacion;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public char getEsLiquidacion() {
        return esLiquidacion;
    }

    public void setEsLiquidacion(char esLiquidacion) {
        this.esLiquidacion = esLiquidacion;
    }

    @XmlTransient
    public Collection<DocumentosIfipAgencia> getDocumentosIfipAgenciaCollection() {
        return documentosIfipAgenciaCollection;
    }

    public void setDocumentosIfipAgenciaCollection(Collection<DocumentosIfipAgencia> documentosIfipAgenciaCollection) {
        this.documentosIfipAgenciaCollection = documentosIfipAgenciaCollection;
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
        if (!(object instanceof TipoComprobanteVenta)) {
            return false;
        }
        TipoComprobanteVenta other = (TipoComprobanteVenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.TipoComprobanteVenta[ codigo=" + codigo + " ]";
    }
    
}
