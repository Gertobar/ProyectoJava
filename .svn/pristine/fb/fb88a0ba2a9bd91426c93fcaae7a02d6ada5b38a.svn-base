/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.TIPO_COMPROBANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoComprobante.findAll", query = "SELECT t FROM TipoComprobante t"),
    @NamedQuery(name = "TipoComprobante.findByCodigo", query = "SELECT t FROM TipoComprobante t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoComprobante.findByNombre", query = "SELECT t FROM TipoComprobante t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoComprobante.findBySiglas", query = "SELECT t FROM TipoComprobante t WHERE t.siglas = :siglas"),
    @NamedQuery(name = "TipoComprobante.findByDigitos", query = "SELECT t FROM TipoComprobante t WHERE t.digitos = :digitos"),
    @NamedQuery(name = "TipoComprobante.findByEliminado", query = "SELECT t FROM TipoComprobante t WHERE t.eliminado = :eliminado"),
//Personalizadas
    @NamedQuery(name = "TipoComprobante.findByCodigoEliminado", query = "SELECT t FROM TipoComprobante t WHERE t.codigo = :codigo AND t.eliminado = :eliminado "),
    @NamedQuery(name = "TipoComprobante.findByEliminadoParaComprobante", query = "SELECT t FROM TipoComprobante t WHERE t.eliminado = :eliminado and t.esParaComprobante = :esParaComprobante"),
})
public class TipoComprobante implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminadoParaComprobante="TipoComprobante.findByEliminadoParaComprobante";
    public static final String findByCodigoEstadoEliminado="TipoComprobante.findByCodigoEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_COMPROBANTE")
    @SequenceGenerator(name = "GSQ_TIPO_COMPROBANTE", schema = "MKS_CONTABLES",  allocationSize = 0, sequenceName = "SEQ_TIPO_COMPROBANTE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIGITOS")
    private long digitos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PARA_COMPROBANTE")
    private char esParaComprobante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoComprobante", fetch = FetchType.LAZY)
    private Collection<Comprobante> comprobanteCollection;

    public TipoComprobante() {
    }

    public TipoComprobante(Long codigo) {
        this.codigo = codigo;
    }

    public TipoComprobante(Long codigo, String nombre, String siglas, long digitos, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
        this.digitos = digitos;
        this.eliminado = eliminado;
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

    public long getDigitos() {
        return digitos;
    }

    public void setDigitos(long digitos) {
        this.digitos = digitos;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

   

    @XmlTransient
    public Collection<Comprobante> getComprobanteCollection() {
        return comprobanteCollection;
    }

    public void setComprobanteCollection(Collection<Comprobante> comprobanteCollection) {
        this.comprobanteCollection = comprobanteCollection;
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
        if (!(object instanceof TipoComprobante)) {
            return false;
        }
        TipoComprobante other = (TipoComprobante) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.TipoComprobante[ codigo=" + codigo + " ]";
    }

    /**
     * @return the esParaComprobante
     */
    public char getEsParaComprobante() {
        return esParaComprobante;
    }

    /**
     * @param esParaComprobante the esParaComprobante to set
     */
    public void setEsParaComprobante(char esParaComprobante) {
        this.esParaComprobante = esParaComprobante;
    }
    
}
