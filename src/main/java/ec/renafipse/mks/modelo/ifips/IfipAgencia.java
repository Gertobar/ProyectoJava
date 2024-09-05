/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ifips;

import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
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
@Table(name = "MKS_IFIPS.IFIP_AGENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfipAgencia.findAll", query = "SELECT i FROM IfipAgencia i"),
    @NamedQuery(name = "IfipAgencia.findByCodigo", query = "SELECT i FROM IfipAgencia i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "IfipAgencia.findByCodigoUbiGeoPais", query = "SELECT i FROM IfipAgencia i WHERE i.codigoUbiGeoPais = :codigoUbiGeoPais"),
    @NamedQuery(name = "IfipAgencia.findByCodigoUbiGeoProvincia", query = "SELECT i FROM IfipAgencia i WHERE i.codigoUbiGeoProvincia = :codigoUbiGeoProvincia"),
    @NamedQuery(name = "IfipAgencia.findByCodigoUbiGeoCiudad", query = "SELECT i FROM IfipAgencia i WHERE i.codigoUbiGeoCiudad = :codigoUbiGeoCiudad"),
    @NamedQuery(name = "IfipAgencia.findByCodigoUbiGeoParroquia", query = "SELECT i FROM IfipAgencia i WHERE i.codigoUbiGeoParroquia = :codigoUbiGeoParroquia"),
    @NamedQuery(name = "IfipAgencia.findByNombre", query = "SELECT i FROM IfipAgencia i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IfipAgencia.findByAutorizacionSri", query = "SELECT i FROM IfipAgencia i WHERE i.autorizacionSri = :autorizacionSri"),
    @NamedQuery(name = "IfipAgencia.findByDireccion", query = "SELECT i FROM IfipAgencia i WHERE i.direccion = :direccion"),
    @NamedQuery(name = "IfipAgencia.findByEmail", query = "SELECT i FROM IfipAgencia i WHERE i.email = :email"),
    @NamedQuery(name = "IfipAgencia.findByEsVentanilla", query = "SELECT i FROM IfipAgencia i WHERE i.esVentanilla = :esVentanilla"),
    @NamedQuery(name = "IfipAgencia.findByMatriz", query = "SELECT i FROM IfipAgencia i WHERE i.matriz = :matriz"),
    @NamedQuery(name = "IfipAgencia.findByEliminado", query = "SELECT i FROM IfipAgencia i WHERE i.eliminado = :eliminado"),
//Personalizadas
    @NamedQuery(name = "IfipAgencia.findByIfipAgenPorIfipEliminado", query = "SELECT i FROM IfipAgencia i WHERE i.codigoIfip.codigo = :codigoIfip AND i.eliminado = :eliminado"),
    @NamedQuery(name = "IfipAgencia.findByIfipAgenPorIfip", query = "SELECT i FROM IfipAgencia i WHERE i.codigoIfip.codigo = :codigoIfip")})
   

public class IfipAgencia implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByIfipAgenPorIfipEliminado = "IfipAgencia.findByIfipAgenPorIfipEliminado";
    public static final String findByIfipAgenPorIfip = "IfipAgencia.findByIfipAgenPorIfip";
    public static final String findByCodigo = "IfipAgencia.findByCodigo";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_IFIP_AGENCIA")
    @SequenceGenerator(name = "GSQ_IFIP_AGENCIA", schema = "MKS_IFIPS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_IFIP_AGENCIA")
    @Column(name = "CODIGO")
    private Long codigo;
    /*@Basic(optional = false)
     @NotNull
     @Column(name = "CODIGO_UBI_GEO_PAIS")
     private long codigoUbiGeoPais;*/
    @JoinColumn(name = "CODIGO_UBI_GEO_PAIS", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false/*, fetch = FetchType.EAGER*/)
    private UbicacionGeografica codigoUbiGeoPais;
    
    /*@Basic(optional = false)
     @NotNull
     @Column(name = "CODIGO_UBI_GEO_PROVINCIA")
     private long codigoUbiGeoProvincia;*/
    @JoinColumn(name = "CODIGO_UBI_GEO_PROVINCIA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false/*, fetch = FetchType.EAGER*/)
    private UbicacionGeografica codigoUbiGeoProvincia;
    /*@Basic(optional = false)
     @NotNull
     @Column(name = "CODIGO_UBI_GEO_CIUDAD")
     private long codigoUbiGeoCiudad;*/
    @JoinColumn(name = "CODIGO_UBI_GEO_CIUDAD", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false/*, fetch = FetchType.EAGER*/)
    private UbicacionGeografica codigoUbiGeoCiudad;
    /*@Basic(optional = false)
     @NotNull
     @Column(name = "CODIGO_UBI_GEO_PARROQUIA")
     private long codigoUbiGeoParroquia;*/
    @JoinColumn(name = "CODIGO_UBI_GEO_PARROQUIA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false/*, fetch = FetchType.EAGER*/)
    private UbicacionGeografica codigoUbiGeoParroquia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "AUTORIZACION_SRI")
    private String autorizacionSri;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_VENTANILLA")
    private char esVentanilla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MATRIZ")
    private char matriz;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ifipAgencia")
    private Collection<IfipAgenciaSecuencia> ifipAgenciaSecuenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoIfipAgenciaPertenece")
    private Collection<Computador> computadorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ifipAgencia")
    private Collection<IfipAgenciaJornadaLaboral> ifipAgenciaJornadaLaboralCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoIfipAgencia")
    private Collection<IfipAgenciaTelefono> ifipAgenciaTelefonoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ifipAgencia")
    private Collection<EntidadFinancieraIfiAge> entidadFinancieraIfiAgeCollection;
    @OneToMany(mappedBy = "codigoIfipAgenciaPadre")
    private Collection<IfipAgencia> ifipAgenciaCollection;

    @JoinColumn(name = "CODIGO_IFIP_AGENCIA_PADRE", referencedColumnName = "CODIGO")
    @ManyToOne
    private IfipAgencia codigoIfipAgenciaPadre;
//    
//    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO",insertable = false,nullable = false)
//    @ManyToOne(optional = false)
//    private Ifip codigoIfip;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", nullable = false)
    @ManyToOne(optional = false)
    private Ifip codigoIfip;

    public IfipAgencia() {
    }

    public IfipAgencia(Long codigo) {
        this.codigo = codigo;
    }

    public IfipAgencia(Long codigo, UbicacionGeografica codigoUbiGeoPais, UbicacionGeografica codigoUbiGeoProvincia, UbicacionGeografica codigoUbiGeoCiudad, UbicacionGeografica codigoUbiGeoParroquia, String nombre, String autorizacionSri, String direccion, String email, char esVentanilla, char matriz, char eliminado) {
        this.codigo = codigo;
        this.codigoUbiGeoPais = codigoUbiGeoPais;
        this.codigoUbiGeoProvincia = codigoUbiGeoProvincia;
        this.codigoUbiGeoCiudad = codigoUbiGeoCiudad;
        this.codigoUbiGeoParroquia = codigoUbiGeoParroquia;
        this.nombre = nombre;
        this.autorizacionSri = autorizacionSri;
        this.direccion = direccion;
        this.email = email;
        this.esVentanilla = esVentanilla;
        this.matriz = matriz;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public UbicacionGeografica getCodigoUbiGeoPais() {
        return codigoUbiGeoPais;
    }

    public void setCodigoUbiGeoPais(UbicacionGeografica codigoUbiGeoPais) {
        this.codigoUbiGeoPais = codigoUbiGeoPais;
    }

    public UbicacionGeografica getCodigoUbiGeoProvincia() {
        return codigoUbiGeoProvincia;
    }

    public void setCodigoUbiGeoProvincia(UbicacionGeografica codigoUbiGeoProvincia) {
        this.codigoUbiGeoProvincia = codigoUbiGeoProvincia;
    }

    public UbicacionGeografica getCodigoUbiGeoCiudad() {
        return codigoUbiGeoCiudad;
    }

    public void setCodigoUbiGeoCiudad(UbicacionGeografica codigoUbiGeoCiudad) {
        this.codigoUbiGeoCiudad = codigoUbiGeoCiudad;
    }

    public UbicacionGeografica getCodigoUbiGeoParroquia() {
        return codigoUbiGeoParroquia;
    }

    public void setCodigoUbiGeoParroquia(UbicacionGeografica codigoUbiGeoParroquia) {
        this.codigoUbiGeoParroquia = codigoUbiGeoParroquia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutorizacionSri() {
        return autorizacionSri;
    }

    public void setAutorizacionSri(String autorizacionSri) {
        this.autorizacionSri = autorizacionSri;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getEsVentanilla() {
        return esVentanilla;
    }

    public void setEsVentanilla(char esVentanilla) {
        this.esVentanilla = esVentanilla;
    }

    public char getMatriz() {
        return matriz;
    }

    public void setMatriz(char matriz) {
        this.matriz = matriz;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<IfipAgenciaSecuencia> getIfipAgenciaSecuenciaCollection() {
        return ifipAgenciaSecuenciaCollection;
    }

    public void setIfipAgenciaSecuenciaCollection(Collection<IfipAgenciaSecuencia> ifipAgenciaSecuenciaCollection) {
        this.ifipAgenciaSecuenciaCollection = ifipAgenciaSecuenciaCollection;
    }

    @XmlTransient
    public Collection<Computador> getComputadorCollection() {
        return computadorCollection;
    }

    public void setComputadorCollection(Collection<Computador> computadorCollection) {
        this.computadorCollection = computadorCollection;
    }

    @XmlTransient
    public Collection<IfipAgenciaJornadaLaboral> getIfipAgenciaJornadaLaboralCollection() {
        return ifipAgenciaJornadaLaboralCollection;
    }

    public void setIfipAgenciaJornadaLaboralCollection(Collection<IfipAgenciaJornadaLaboral> ifipAgenciaJornadaLaboralCollection) {
        this.ifipAgenciaJornadaLaboralCollection = ifipAgenciaJornadaLaboralCollection;
    }

    @XmlTransient
    public Collection<IfipAgenciaTelefono> getIfipAgenciaTelefonoCollection() {
        return ifipAgenciaTelefonoCollection;
    }

    public void setIfipAgenciaTelefonoCollection(Collection<IfipAgenciaTelefono> ifipAgenciaTelefonoCollection) {
        this.ifipAgenciaTelefonoCollection = ifipAgenciaTelefonoCollection;
    }

    @XmlTransient
    public Collection<EntidadFinancieraIfiAge> getEntidadFinancieraIfiAgeCollection() {
        return entidadFinancieraIfiAgeCollection;
    }

    public void setEntidadFinancieraIfiAgeCollection(Collection<EntidadFinancieraIfiAge> entidadFinancieraIfiAgeCollection) {
        this.entidadFinancieraIfiAgeCollection = entidadFinancieraIfiAgeCollection;
    }

    @XmlTransient
    public Collection<IfipAgencia> getIfipAgenciaCollection() {
        return ifipAgenciaCollection;
    }

    public void setIfipAgenciaCollection(Collection<IfipAgencia> ifipAgenciaCollection) {
        this.ifipAgenciaCollection = ifipAgenciaCollection;
    }

    public IfipAgencia getCodigoIfipAgenciaPadre() {
        return codigoIfipAgenciaPadre;
    }

    public void setCodigoIfipAgenciaPadre(IfipAgencia codigoIfipAgenciaPadre) {
        this.codigoIfipAgenciaPadre = codigoIfipAgenciaPadre;
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
        if (!(object instanceof IfipAgencia)) {
            return false;
        }
        IfipAgencia other = (IfipAgencia) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.IfipAgencia[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoIfip
     */
    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    /**
     * @return the ifip
     */
}
