/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_DPFS.TALONARIO_DOCUMENTO_DPF_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TalonarioDocumentoDpfDet.findAll", query = "SELECT t FROM TalonarioDocumentoDpfDet t"),
    @NamedQuery(name = "TalonarioDocumentoDpfDet.findByCodigo", query = "SELECT t FROM TalonarioDocumentoDpfDet t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TalonarioDocumentoDpfDet.findBySerie", query = "SELECT t FROM TalonarioDocumentoDpfDet t WHERE t.serie = :serie"),
    @NamedQuery(name = "TalonarioDocumentoDpfDet.findByNumero", query = "SELECT t FROM TalonarioDocumentoDpfDet t WHERE t.numero = :numero"),
    @NamedQuery(name = "TalonarioDocumentoDpfDet.findByEstado", query = "SELECT t FROM TalonarioDocumentoDpfDet t WHERE t.estado = :estado"),
    //PERSONALIZADOS
    @NamedQuery(name = "TalonarioDocumentoDpfDet.findBySerieContratoDpf", query = "SELECT MIN(t.serie) FROM TalonarioDocumentoDpfDet t JOIN t.talonario c WHERE c.codigoMoneda = :codigoMoneda AND  c.codigoIfip = :codigoIfip AND c.codigoComputador = :codigoComputador AND c.codigoIfipAgencia =  :codigoIfipAgencia AND t.estado = :estado ORDER BY t.serie"),
    @NamedQuery(name = "TalonarioDocumentoDpfDet.findByCodigoIfipSerie", query = "SELECT t FROM TalonarioDocumentoDpfDet  t WHERE t.codigoIfip = :codigoIfip AND t.serie = :serie"),
    @NamedQuery(name = "TalonarioDocumentoDpfDet.findByAnulaDocumento", query = "SELECT t FROM TalonarioDocumentoDpfDet t JOIN t.talonario c WHERE c.codigoMoneda = :codigoMoneda AND  c.codigoIfip = :codigoIfip AND c.codigoComputador = :codigoComputador AND c.codigoIfipAgencia =  :codigoIfipAgencia AND t.serie BETWEEN :inicioSerie AND :finSerie AND t.estado <> :estado ORDER BY t.serie")
})
public class TalonarioDocumentoDpfDet implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findBySerieContratoDpf = "TalonarioDocumentoDpfDet.findBySerieContratoDpf";
    public static final String findByCodigoIfipSerie = "TalonarioDocumentoDpfDet.findByCodigoIfipSerie";
    public static final String findByAnulaDocumento = "TalonarioDocumentoDpfDet.findByAnulaDocumento";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TALONARIO_DOC_DPF_DET")
    @SequenceGenerator(name = "GSQ_TALONARIO_DOC_DPF_DET", schema = "MKS_DPFS",  allocationSize = 0, sequenceName = "SEQ_TALONARIO_DOC_DPF_DET")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERIE")
    private Long serie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private Long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TALONARIO")
    private Long codigoTalonario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "talonarioDocumentoDpfDet", fetch = FetchType.LAZY)
    private Collection<DocumentoContratoDpf> documentoContratoDpfCollection;
    @JoinColumn(name = "CODIGO_TALONARIO", referencedColumnName = "CODIGO", insertable = false, updatable =  false)
    @ManyToOne(optional = false)
    private TalonarioDocumentoDpf talonario;
    

    public TalonarioDocumentoDpfDet() {
    }

    public TalonarioDocumentoDpfDet(Long codigo) {
        this.codigo = codigo;
    }

    public TalonarioDocumentoDpfDet(Long codigo, String numero, char estado) {
        this.codigo = codigo;
        this.numero = numero;
        this.estado = estado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getSerie() {
        return serie;
    }

    public void setSerie(Long serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<DocumentoContratoDpf> getDocumentoContratoDpfCollection() {
        return documentoContratoDpfCollection;
    }

    public void setDocumentoContratoDpfCollection(Collection<DocumentoContratoDpf> documentoContratoDpfCollection) {
        this.documentoContratoDpfCollection = documentoContratoDpfCollection;
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
        if (!(object instanceof TalonarioDocumentoDpfDet)) {
            return false;
        }
        TalonarioDocumentoDpfDet other = (TalonarioDocumentoDpfDet) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.TalonarioDocumentoDpfDet[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoIfip
     */
    public Long getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(Long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    /**
     * @return the codigoTalonario
     */
    public Long getCodigoTalonario() {
        return codigoTalonario;
    }

    /**
     * @param codigoTalonario the codigoTalonario to set
     */
    public void setCodigoTalonario(Long codigoTalonario) {
        this.codigoTalonario = codigoTalonario;
    }

    /**
     * @return the talonario
     */
    public TalonarioDocumentoDpf getTalonario() {
        return talonario;
    }

    /**
     * @param talonario the talonario to set
     */
    public void setTalonario(TalonarioDocumentoDpf talonario) {
        this.talonario = talonario;
    }
    
}
