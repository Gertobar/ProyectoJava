/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TIPO_GARANTIA_PRO_CRE_MON_IFI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoGarantiaProCreMonIfi.findAll", query = "SELECT t FROM TipoGarantiaProCreMonIfi t"),
    @NamedQuery(name = "TipoGarantiaProCreMonIfi.findByCodigo", query = "SELECT t FROM TipoGarantiaProCreMonIfi t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoGarantiaProCreMonIfi.findByNumero", query = "SELECT t FROM TipoGarantiaProCreMonIfi t WHERE t.numero = :numero"),
    @NamedQuery(name = "TipoGarantiaProCreMonIfi.findByObligatorio", query = "SELECT t FROM TipoGarantiaProCreMonIfi t WHERE t.obligatorio = :obligatorio"),
    @NamedQuery(name = "TipoGarantiaProCreMonIfi.findByRegistradoPor", query = "SELECT t FROM TipoGarantiaProCreMonIfi t WHERE t.registradoPor = :registradoPor"),
    @NamedQuery(name = "TipoGarantiaProCreMonIfi.findByFechaRegistro", query = "SELECT t FROM TipoGarantiaProCreMonIfi t WHERE t.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "TipoGarantiaProCreMonIfi.findByEliminado", query = "SELECT t FROM TipoGarantiaProCreMonIfi t WHERE t.eliminado = :eliminado"),
    //PERSONALIZADOS
    @NamedQuery(name = "TipoGarantiaProCreMonIfi.findByTasaTipoGarantia", query = "SELECT t FROM TipoGarantiaProCreMonIfi t WHERE t.codigoTasa.codigo = :codigoTasa AND t.codigoTipoGarantia.codigo= :codigoTipoGarantia"),
    @NamedQuery(name = "TipoGarantiaProCreMonIfi.findByTasaTipoGarantiaElim", query = "SELECT t FROM TipoGarantiaProCreMonIfi t WHERE t.codigoTasa.codigo = :codigoTasa AND t.codigoTipoGarantia.codigo= :codigoTipoGarantia AND t.eliminado = :eliminado"),
    @NamedQuery(name = "TipoGarantiaProCreMonIfi.findByTasaElim", query = "SELECT t FROM TipoGarantiaProCreMonIfi t WHERE t.codigoTasa.codigo = :codigoTasa AND t.eliminado = :eliminado"),
    @NamedQuery(name = "TipoGarantiaProCreMonIfi.findByTasaObliElim", query = "SELECT t FROM TipoGarantiaProCreMonIfi t WHERE t.codigoTasa.codigo = :codigoTasa AND t.obligatorio = :obligatorio AND t.eliminado = :eliminado")
})
public class TipoGarantiaProCreMonIfi implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByTasaTipoGarantia = "TipoGarantiaProCreMonIfi.findByTasaTipoGarantia";
    public static final String findByTasaTipoGarantiaElim = "TipoGarantiaProCreMonIfi.findByTasaTipoGarantiaElim";
    public static final String findByTasaElim = "TipoGarantiaProCreMonIfi.findByTasaElim";
    public static final String findByTasaObliElim = "TipoGarantiaProCreMonIfi.findByTasaObliElim";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_GAR_PRO_CRE_MON_IFI")
    @SequenceGenerator(name = "GSQ_TIPO_GAR_PRO_CRE_MON_IFI", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_TIPO_GAR_PRO_CRE_MON_IFI")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO")
    private int numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OBLIGATORIO")
    private char obligatorio;
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
    @JoinColumn(name = "CODIGO_TIPO_GARANTIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoGarantia codigoTipoGarantia;
    @JoinColumn(name = "CODIGO_TASA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TasaInteresProCreMonIfi codigoTasa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoGarantiaProCreMonIfi", fetch = FetchType.LAZY)
    private Collection<TipoGarantiaCredito> tipoGarantiaCreditoCollection;
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioRegistradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TASA")
    private long codigoTasaCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_GARANTIA")
    private long codigoTipoGarantiaCode;
    public TipoGarantiaProCreMonIfi() {
    }

    public TipoGarantiaProCreMonIfi(Long codigo) {
        this.codigo = codigo;
    }

    public TipoGarantiaProCreMonIfi(Long codigo, int numero, char obligatorio, long registradoPor, Date fechaRegistro, char eliminado) {
        this.codigo = codigo;
        this.numero = numero;
        this.obligatorio = obligatorio;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public char getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(char obligatorio) {
        this.obligatorio = obligatorio;
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

    public TipoGarantia getCodigoTipoGarantia() {
        return codigoTipoGarantia;
    }

    public void setCodigoTipoGarantia(TipoGarantia codigoTipoGarantia) {
        this.codigoTipoGarantia = codigoTipoGarantia;
    }

    public TasaInteresProCreMonIfi getCodigoTasa() {
        return codigoTasa;
    }

    public void setCodigoTasa(TasaInteresProCreMonIfi codigoTasa) {
        this.codigoTasa = codigoTasa;
    }

    @XmlTransient
    public Collection<TipoGarantiaCredito> getTipoGarantiaCreditoCollection() {
        return tipoGarantiaCreditoCollection;
    }

    public void setTipoGarantiaCreditoCollection(Collection<TipoGarantiaCredito> tipoGarantiaCreditoCollection) {
        this.tipoGarantiaCreditoCollection = tipoGarantiaCreditoCollection;
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
        if (!(object instanceof TipoGarantiaProCreMonIfi)) {
            return false;
        }
        TipoGarantiaProCreMonIfi other = (TipoGarantiaProCreMonIfi) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoGarantiaProCreMonIfi[ codigo=" + codigo + " ]";
    }
    
    /**
     * @return the usuarioRegistradoPor
     */
    public Usuario getUsuarioRegistradoPor() {
        return usuarioRegistradoPor;
    }

    /**
     * @param usuarioRegistradoPor the usuarioRegistradoPor to set
     */
    public void setUsuarioRegistradoPor(Usuario usuarioRegistradoPor) {
        this.usuarioRegistradoPor = usuarioRegistradoPor;
    }

    /**
     * @return the codigoTasaCode
     */
    public long getCodigoTasaCode() {
        return codigoTasaCode;
    }

    /**
     * @param codigoTasaCode the codigoTasaCode to set
     */
    public void setCodigoTasaCode(long codigoTasaCode) {
        this.codigoTasaCode = codigoTasaCode;
    }

    /**
     * @return the codigoTipoGarantiaCode
     */
    public long getCodigoTipoGarantiaCode() {
        return codigoTipoGarantiaCode;
    }

    /**
     * @param codigoTipoGarantiaCode the codigoTipoGarantiaCode to set
     */
    public void setCodigoTipoGarantiaCode(long codigoTipoGarantiaCode) {
        this.codigoTipoGarantiaCode = codigoTipoGarantiaCode;
    }
    
}
