/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_IFIPS.IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ifip.findAll", query = "SELECT i FROM Ifip i"),
    @NamedQuery(name = "Ifip.findByCodigo", query = "SELECT i FROM Ifip i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "Ifip.findByNombre", query = "SELECT i FROM Ifip i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Ifip.findByRuc", query = "SELECT i FROM Ifip i WHERE i.ruc = :ruc"),
    @NamedQuery(name = "Ifip.findByRazonSocial", query = "SELECT i FROM Ifip i WHERE i.razonSocial = :razonSocial"),
    @NamedQuery(name = "Ifip.findByObjetoSocial", query = "SELECT i FROM Ifip i WHERE i.objetoSocial = :objetoSocial"),
    @NamedQuery(name = "Ifip.findByContabilidadPorAgencia", query = "SELECT i FROM Ifip i WHERE i.contabilidadPorAgencia = :contabilidadPorAgencia"),
    @NamedQuery(name = "Ifip.findByPathImagen", query = "SELECT i FROM Ifip i WHERE i.pathImagen = :pathImagen"),
    @NamedQuery(name = "Ifip.findByEliminado", query = "SELECT i FROM Ifip i WHERE i.eliminado = :eliminado"),
    //Personazalizado SC
    @NamedQuery(name = "Ifip.findByIfipEliminado", query = "SELECT i FROM Ifip i WHERE i.codigo = :codigoIfip AND i.eliminado = :eliminado")})
public class Ifip implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado="Ifip.findByEliminado";
     public static final String findByIfipEliminado="Ifip.findByIfipEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_IFIP")
    @SequenceGenerator(name = "GSQ_IFIP", schema = "MKS_IFIPS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_IFIP")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RUC")
    private String ruc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBJETO_SOCIAL")
    private String objetoSocial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONTABILIDAD_POR_AGENCIA")
    private char contabilidadPorAgencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PATH_IMAGEN")
    private String pathImagen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LOGO_CENTRAL")
    private String logoCentral;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TIPO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoIfip codigoTipoIfip;
    @JoinColumn(name = "CODIGO_SEGMENTO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private SegmentoIfip codigoSegmentoIfip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoIfip")
    private Collection<Computador> computadorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoIfip")
    private Collection<IfipAgencia> ifipAgenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ifip")
    private Collection<IfipMoneda> ifipMonedaCollection;

    
    //Agregado por Willan Jara
    //Este campo tiene por Objetivo establecer una igamen de fondo en el reporte
    //de DPF
    @Basic(optional = true)    
    @Size(min = 1, max = 500)
    @Column(name = "PATH_IMAGEN1")
    private String pathImagen1;
    
    public Ifip() {
    }

    public Ifip(Long codigo) {
        this.codigo = codigo;
    }

    public Ifip(Long codigo, String nombre, String ruc, String razonSocial, String objetoSocial, char contabilidadPorAgencia, String pathImagen, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.objetoSocial = objetoSocial;
        this.contabilidadPorAgencia = contabilidadPorAgencia;
        this.pathImagen = pathImagen;
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getObjetoSocial() {
        return objetoSocial;
    }

    public void setObjetoSocial(String objetoSocial) {
        this.objetoSocial = objetoSocial;
    }

    public char getContabilidadPorAgencia() {
        return contabilidadPorAgencia;
    }

    public void setContabilidadPorAgencia(char contabilidadPorAgencia) {
        this.contabilidadPorAgencia = contabilidadPorAgencia;
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public TipoIfip getCodigoTipoIfip() {
        return codigoTipoIfip;
    }

    public void setCodigoTipoIfip(TipoIfip codigoTipoIfip) {
        this.codigoTipoIfip = codigoTipoIfip;
    }

    public SegmentoIfip getCodigoSegmentoIfip() {
        return codigoSegmentoIfip;
    }

    public void setCodigoSegmentoIfip(SegmentoIfip codigoSegmentoIfip) {
        this.codigoSegmentoIfip = codigoSegmentoIfip;
    }

    @XmlTransient
    public Collection<Computador> getComputadorCollection() {
        return computadorCollection;
    }

    public void setComputadorCollection(Collection<Computador> computadorCollection) {
        this.computadorCollection = computadorCollection;
    }

    @XmlTransient
    public Collection<IfipAgencia> getIfipAgenciaCollection() {
        return ifipAgenciaCollection;
    }

    public void setIfipAgenciaCollection(Collection<IfipAgencia> ifipAgenciaCollection) {
        this.ifipAgenciaCollection = ifipAgenciaCollection;
    }

    @XmlTransient
    public Collection<IfipMoneda> getIfipMonedaCollection() {
        return ifipMonedaCollection;
    }

    public void setIfipMonedaCollection(Collection<IfipMoneda> ifipMonedaCollection) {
        this.ifipMonedaCollection = ifipMonedaCollection;
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
        if (!(object instanceof Ifip)) {
            return false;
        }
        Ifip other = (Ifip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.Ifip[ codigo=" + codigo + " ]";
    }

    /**
     * @return the logoCentral
     */
    public String getLogoCentral() {
        return logoCentral;
    }

    /**
     * @param logoCentral the logoCentral to set
     */
    public void setLogoCentral(String logoCentral) {
        this.logoCentral = logoCentral;
    }

    /**
     *
     * @return devuelve el path de la imagen
     */
    public String getPathImagen1() {
        return pathImagen1;
    }

    /**
     * Setea el path de la imagen
     * @param pathImagen1
     */
    public void setPathImagen1(String pathImagen1) {
        this.pathImagen1 = pathImagen1;
    }
    
}
