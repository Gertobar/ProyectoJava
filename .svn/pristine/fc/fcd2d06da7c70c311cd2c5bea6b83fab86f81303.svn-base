/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.DOCUMENTOS_IFIP_AGENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentosIfipAgencia.findAll", query = "SELECT d FROM DocumentosIfipAgencia d"),
    @NamedQuery(name = "DocumentosIfipAgencia.findByCodigo", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "DocumentosIfipAgencia.findByCodigoIfipAgencia", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "DocumentosIfipAgencia.findByFechaEmision", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "DocumentosIfipAgencia.findByFechaCaduca", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.fechaCaduca = :fechaCaduca"),
    @NamedQuery(name = "DocumentosIfipAgencia.findByDigitosComprobante", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.digitosComprobante = :digitosComprobante"),
    @NamedQuery(name = "DocumentosIfipAgencia.findByFechaIngreso", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "DocumentosIfipAgencia.findByRegistradoPor", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.registradoPor = :registradoPor"),
    @NamedQuery(name = "DocumentosIfipAgencia.findByInicioSerie", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.inicioSerie = :inicioSerie"),
    @NamedQuery(name = "DocumentosIfipAgencia.findByFinSerie", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.finSerie = :finSerie"),
    @NamedQuery(name = "DocumentosIfipAgencia.findBySerie", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.serie = :serie"),
    @NamedQuery(name = "DocumentosIfipAgencia.findByEsFacturaElectronica", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.esFacturaElectronica = :esFacturaElectronica"),
    @NamedQuery(name = "DocumentosIfipAgencia.findByEliminado", query = "SELECT d FROM DocumentosIfipAgencia d WHERE d.eliminado = :eliminado")})
public class DocumentosIfipAgencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EMISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CADUCA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaduca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIGITOS_COMPROBANTE")
    private short digitosComprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INICIO_SERIE")
    private long inicioSerie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FIN_SERIE")
    private long finSerie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "SERIE")
    private String serie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_FACTURA_ELECTRONICA")
    private char esFacturaElectronica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoDocumentoIfip")
    private Collection<DocumentosIfipAgenciaDet> documentosIfipAgenciaDetCollection;
    @JoinColumn(name = "CODIGO_TIPO_COMPROBANTE", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoComprobanteVenta codigoTipoComprobante;

    public DocumentosIfipAgencia() {
    }

    public DocumentosIfipAgencia(Long codigo) {
        this.codigo = codigo;
    }

    public DocumentosIfipAgencia(Long codigo, long codigoIfipAgencia, Date fechaEmision, Date fechaCaduca, short digitosComprobante, Date fechaIngreso, long registradoPor, long inicioSerie, long finSerie, String serie, char esFacturaElectronica, char eliminado) {
        this.codigo = codigo;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.fechaEmision = fechaEmision;
        this.fechaCaduca = fechaCaduca;
        this.digitosComprobante = digitosComprobante;
        this.fechaIngreso = fechaIngreso;
        this.registradoPor = registradoPor;
        this.inicioSerie = inicioSerie;
        this.finSerie = finSerie;
        this.serie = serie;
        this.esFacturaElectronica = esFacturaElectronica;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaCaduca() {
        return fechaCaduca;
    }

    public void setFechaCaduca(Date fechaCaduca) {
        this.fechaCaduca = fechaCaduca;
    }

    public short getDigitosComprobante() {
        return digitosComprobante;
    }

    public void setDigitosComprobante(short digitosComprobante) {
        this.digitosComprobante = digitosComprobante;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public long getInicioSerie() {
        return inicioSerie;
    }

    public void setInicioSerie(long inicioSerie) {
        this.inicioSerie = inicioSerie;
    }

    public long getFinSerie() {
        return finSerie;
    }

    public void setFinSerie(long finSerie) {
        this.finSerie = finSerie;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public char getEsFacturaElectronica() {
        return esFacturaElectronica;
    }

    public void setEsFacturaElectronica(char esFacturaElectronica) {
        this.esFacturaElectronica = esFacturaElectronica;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<DocumentosIfipAgenciaDet> getDocumentosIfipAgenciaDetCollection() {
        return documentosIfipAgenciaDetCollection;
    }

    public void setDocumentosIfipAgenciaDetCollection(Collection<DocumentosIfipAgenciaDet> documentosIfipAgenciaDetCollection) {
        this.documentosIfipAgenciaDetCollection = documentosIfipAgenciaDetCollection;
    }

    public TipoComprobanteVenta getCodigoTipoComprobante() {
        return codigoTipoComprobante;
    }

    public void setCodigoTipoComprobante(TipoComprobanteVenta codigoTipoComprobante) {
        this.codigoTipoComprobante = codigoTipoComprobante;
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
        if (!(object instanceof DocumentosIfipAgencia)) {
            return false;
        }
        DocumentosIfipAgencia other = (DocumentosIfipAgencia) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.DocumentosIfipAgencia[ codigo=" + codigo + " ]";
    }
    
}
