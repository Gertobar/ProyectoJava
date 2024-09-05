/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;


import ec.renafipse.mks.modelo.socios.Persona;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.PROVEEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.getCodigoProveedor", query = "SELECT MAX(p.codigo) FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findAllCodigo", query = "SELECT p FROM Proveedor p ORDER BY p.codigo DESC"),
    @NamedQuery(name = "Proveedor.findByCodigo", query = "SELECT p FROM Proveedor p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Proveedor.findByCodigoPersona", query = "SELECT p FROM Proveedor p WHERE p.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "Proveedor.findByCodigoTipoContribuyente", query = "SELECT p FROM Proveedor p where  p.codigoTipoContribuyente = :codigoTipoContribuyente"),
    @NamedQuery(name = "Proveedor.findByRetieneIva", query = "SELECT p FROM Proveedor p WHERE p.retieneIva = :retieneIva"),
    @NamedQuery(name = "Proveedor.findByTieneRise", query = "SELECT p FROM Proveedor p WHERE p.tieneRise = :tieneRise"),
    @NamedQuery(name = "Proveedor.findByRegistradoPor", query = "SELECT p FROM Proveedor p WHERE p.registradoPor = :registradoPor"),
    @NamedQuery(name = "Proveedor.findByFechaRegistro", query = "SELECT p FROM Proveedor p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Proveedor.findByEliminado", query = "SELECT p FROM Proveedor p WHERE p.eliminado = :eliminado"),
     
    @NamedQuery(name = "Proveedor.countAll", query = "SELECT max(p.codigo) FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdentificacion", query = "SELECT s FROM Proveedor s  JOIN s.persona p WHERE p.identificacion = :identificacion"),
    @NamedQuery(name = "Proveedor.findByCodigoPersonaProveedor", query = "SELECT p FROM Proveedor p WHERE p.persona.codigo = :codigoPersona"),    
    @NamedQuery(name = "Proveedor.findByNombrePersonaProveedor", query = "SELECT p FROM Proveedor p WHERE p.persona.nombreCompleto LIKE :nombreCompleto AND p.eliminado = :eliminado ")
})

public class Proveedor implements Serializable {
    public static final String findAll = "Proveedor.findAll";
    public static final String findByIdentificacion = "Proveedor.findByIdentificacion";
    public static final String findByCodigoPersona = "Proveedor.findByCodigoPersonaProveedor";
    public static final String findByNombrePersonaProveedor = "Proveedor.findByNombrePersonaProveedor";
    public static final String findByCodigo = "Proveedor.findByCodigo";
    public static final String findAllCodigo = "Proveedor.findAllCodigo";
    public static final String getCodigoProveedor = "Proveedor.getCodigoProveedor";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PROVEEDOR")
    @SequenceGenerator(name = "GSQ_PROVEEDOR", schema = "MKS_ADQUISICIONES", allocationSize = 0, sequenceName = "SEQ_PROVEEDOR")
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;   
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_CONTRIBUYENTE")
    private long codigoTipoContribuyente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RETIENE_IVA")
    private char retieneIva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIENE_RISE")
    private char tieneRise;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "LLEVA_CONTABILIDAD")
    private char llevaContabilidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGENTE_RETENCION")
    private char agenteRetencion;    
    @Size(min = 4, max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(min = 7, max = 18)
    @Column(name = "TELEFONO")
    private String telefono;
    
    @JoinColumn(name = "CODIGO_TIPO_CONTRIBUYENTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoContribuyente tipoContribuyente;
     @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;
     
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoProveedor", fetch = FetchType.LAZY)
    private Collection<Compra> compraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor", fetch = FetchType.LAZY)
    private Collection<DocumentosProveedor> documentosProveedorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor", fetch = FetchType.LAZY)
    private Collection<ProveedorIfip> proveedorIfipCollection;
  
    public Proveedor() {
    }

    public Proveedor(Long codigo) {
        this.codigo = codigo;
    }

    public Proveedor(Long codigo, Persona persona, Long tipoContribuyente, char retieneIva, char tieneRise, long registradoPor, Date fechaRegistro, char eliminado, char llevaContabilidad, char agenteRetencion) {
        this.codigo = codigo;
        this.persona = persona;
        this.codigoTipoContribuyente=tipoContribuyente;
        this.retieneIva = retieneIva;
        this.tieneRise = tieneRise;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
        this.llevaContabilidad = llevaContabilidad;
        this.agenteRetencion = agenteRetencion;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }


    

    public char getRetieneIva() {
        return retieneIva;
    }

    public void setRetieneIva(char retieneIva) {
        this.retieneIva = retieneIva;
    }

    public char getTieneRise() {
        return tieneRise;
    }

    public void setTieneRise(char tieneRise) {
        this.tieneRise = tieneRise;
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

    @XmlTransient
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    @XmlTransient
    public Collection<DocumentosProveedor> getDocumentosProveedorCollection() {
        return documentosProveedorCollection;
    }

    public void setDocumentosProveedorCollection(Collection<DocumentosProveedor> documentosProveedorCollection) {
        this.documentosProveedorCollection = documentosProveedorCollection;
    }

    @XmlTransient
    public Collection<ProveedorIfip> getProveedorIfipCollection() {
        return proveedorIfipCollection;
    }

    public void setProveedorIfipCollection(Collection<ProveedorIfip> proveedorIfipCollection) {
        this.proveedorIfipCollection = proveedorIfipCollection;
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
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.Proveedor[ codigo=" + codigo + " ]";
    }

    /**
     * @return the llevaContabilidad
     */
    public char getLlevaContabilidad() {
        return llevaContabilidad;
    }

    /**
     * @param llevaContabilidad the llevaContabilidad to set
     */
    public void setLlevaContabilidad(char llevaContabilidad) {
        this.llevaContabilidad = llevaContabilidad;
    }
    
    /**
     * @return the agenteRetencion
     */
    public char getAgenteRetencion() {
        return agenteRetencion;
    }

    /**
     * @param agenteRetencion the agenteRetencion to set
     */
    public void setAgenteRetencion(char agenteRetencion) {
        this.agenteRetencion = agenteRetencion;
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the codigoPersona
     */
    public long getCodigoPersona() {
        return codigoPersona;
    }

    /**
     * @param codigoPersona the codigoPersona to set
     */
    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    /**
     * @return the codigoTipoContribuyente
     */
    public long getCodigoTipoContribuyente() {
        return codigoTipoContribuyente;
    }

    /**
     * @param codigoTipoContribuyente the codigoTipoContribuyente to set
     */
    public void setCodigoTipoContribuyente(long codigoTipoContribuyente) {
        this.codigoTipoContribuyente = codigoTipoContribuyente;
    }

    /**
     * @return the tipoContribuyente
     */
    public TipoContribuyente getTipoContribuyente() {
        return tipoContribuyente;
    }

    /**
     * @param tipoContribuyente the tipoContribuyente to set
     */
    public void setTipoContribuyente(TipoContribuyente tipoContribuyente) {
        this.tipoContribuyente = tipoContribuyente;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

   
    
}
