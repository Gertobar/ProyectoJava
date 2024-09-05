/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.seguridades.Usuario;
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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.CONCEPTO_TRANSACCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptoTransaccion.findAll", query = "SELECT c FROM ConceptoTransaccion c"),
    @NamedQuery(name = "ConceptoTransaccion.findByCodigo", query = "SELECT c FROM ConceptoTransaccion c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "ConceptoTransaccion.findByNombre", query = "SELECT c FROM ConceptoTransaccion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ConceptoTransaccion.findByDescripcion", query = "SELECT c FROM ConceptoTransaccion c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "ConceptoTransaccion.findBySiglas", query = "SELECT c FROM ConceptoTransaccion c WHERE c.siglas = :siglas"),
    @NamedQuery(name = "ConceptoTransaccion.findByAfectaCaja", query = "SELECT c FROM ConceptoTransaccion c WHERE c.afectaCaja = :afectaCaja"),
    @NamedQuery(name = "ConceptoTransaccion.findByContabiliza", query = "SELECT c FROM ConceptoTransaccion c WHERE c.contabiliza = :contabiliza"),
    @NamedQuery(name = "ConceptoTransaccion.findByRegistradoPor", query = "SELECT c FROM ConceptoTransaccion c WHERE c.registradoPor = :registradoPor"),
    @NamedQuery(name = "ConceptoTransaccion.findByFechaRegistro", query = "SELECT c FROM ConceptoTransaccion c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ConceptoTransaccion.findByEliminado", query = "SELECT c FROM ConceptoTransaccion c WHERE c.eliminado = :eliminado")})
public class ConceptoTransaccion implements Serializable {
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_CONCEPTO_TRANSACCION")
    @SequenceGenerator(name = "GSQ_CONCEPTO_TRANSACCION", schema = "MKS_AHORROS", allocationSize = 0, sequenceName = "SEQ_CONCEPTO_TRANSACCION")
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
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AFECTA_CAJA")
    private char afectaCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONTABILIZA")
    private char contabiliza;
    /*@Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")*/
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario registradoPor;
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
    @Column(name = "ACREDITA_INTERES")
    private char acreditaInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPRIME_EN_LIBRETA")
    private char imprimeEnLibreta;
    @JoinColumn(name = "CODIGO_TRANSACCION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Transaccion codigoTransaccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptoTransaccion", fetch = FetchType.LAZY)
    private Collection<ConceptoTransaccionPro> conceptoTransaccionProCollection;

    public ConceptoTransaccion() {
    }
    public ConceptoTransaccion(Long codigo) {
        this.codigo = codigo;
    }

    public ConceptoTransaccion(Long codigo, String nombre, String descripcion, String siglas, char afectaCaja, char contabiliza, Usuario registradoPor, Date fechaRegistro, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.siglas = siglas;
        this.afectaCaja = afectaCaja;
        this.contabiliza = contabiliza;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
         //System.out.println("-----------ssssssssssssssssssssssssssssss    "+getCodigo());
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public char getAfectaCaja() {
        return afectaCaja;
    }

    public void setAfectaCaja(char afectaCaja) {
        this.afectaCaja = afectaCaja;
    }

    public char getContabiliza() {
        return contabiliza;
    }

    public void setContabiliza(char contabiliza) {
        this.contabiliza = contabiliza;
    }

    public Usuario getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(Usuario registradoPor) {
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

    public Transaccion getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(Transaccion codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    @XmlTransient
    public Collection<ConceptoTransaccionPro> getConceptoTransaccionProCollection() {
        return conceptoTransaccionProCollection;
    }

    public void setConceptoTransaccionProCollection(Collection<ConceptoTransaccionPro> conceptoTransaccionProCollection) {
        this.conceptoTransaccionProCollection = conceptoTransaccionProCollection;
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
        if (!(object instanceof ConceptoTransaccion)) {
            return false;
        }
        ConceptoTransaccion other = (ConceptoTransaccion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.ConceptoTransaccion[ codigo=" + codigo + " ]";
    }

    /**
     * @return the acreditaInteres
     */
    public char getAcreditaInteres() {
        return acreditaInteres;
    }

    /**
     * @param acreditaInteres the acreditaInteres to set
     */
    public void setAcreditaInteres(char acreditaInteres) {
        this.acreditaInteres = acreditaInteres;
    }

    /**
     * @return the imprimeEnLibreta
     */
    public char getImprimeEnLibreta() {
        return imprimeEnLibreta;
    }

    /**
     * @param imprimeEnLibreta the imprimeEnLibreta to set
     */
    public void setImprimeEnLibreta(char imprimeEnLibreta) {
        this.imprimeEnLibreta = imprimeEnLibreta;
    }
    
}
