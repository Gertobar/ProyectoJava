/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cobranzas;

import ec.renafipse.mks.modelo.ifips.Ifip;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
 * @author Andres
 */
@Entity
@Table(name = "MKS_CREDITOS.COBRANZA_EXTRAJUDICIAL")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "CobranzaExtrajudicial.findByCodigoIfipYNombre", query = "SELECT c FROM CobranzaExtrajudicial c WHERE c.codigoIfip.codigo = :codigoIfip and trim(upper(c.nombre)) = :nombre"),
               @NamedQuery(name = "CobranzaExtrajudicial.findByCodigoIfip", query = "SELECT c FROM CobranzaExtrajudicial c WHERE c.codigoIfip.codigo = :codigoIfip order by c.codigo")})
public class CobranzaExtrajudicial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_COBRANZA_EXTRAJUDICIAL")
    @SequenceGenerator(name = "GSQ_COBRANZA_EXTRAJUDICIAL", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_COBRANZA_EXTRAJUDICIAL")
    @Basic(optional = false)
    @NotNull
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_CAPITAL_INICIAL")
    private BigDecimal valorCapitalInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_CAPITAL_FINAL")
    private BigDecimal valorCapitalFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCobranzaExtrajudicial", fetch=FetchType.EAGER)
    private List<CobranzaExtrajudicialDet> cobranzaExtrajudicialDetList;

    public CobranzaExtrajudicial() {
    }

    public CobranzaExtrajudicial(Long codigo) {
        this.codigo = codigo;
    }

    public CobranzaExtrajudicial(Long codigo, Ifip codigoIfip, String nombre, BigDecimal valorCapitalInicial, BigDecimal valorCapitalFinal, Long codigoUsuario, Date fechaModificacion, char eliminado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.nombre = nombre;
        this.valorCapitalInicial = valorCapitalInicial;
        this.valorCapitalFinal = valorCapitalFinal;
        this.codigoUsuario = codigoUsuario;
        this.fechaModificacion = fechaModificacion;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getValorCapitalInicial() {
        return valorCapitalInicial;
    }

    public void setValorCapitalInicial(BigDecimal valorCapitalInicial) {
        this.valorCapitalInicial = valorCapitalInicial;
    }

    public BigDecimal getValorCapitalFinal() {
        return valorCapitalFinal;
    }

    public void setValorCapitalFinal(BigDecimal valorCapitalFinal) {
        this.valorCapitalFinal = valorCapitalFinal;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    
    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<CobranzaExtrajudicialDet> getCobranzaExtrajudicialDetList() {
        return cobranzaExtrajudicialDetList;
    }

    public void setCobranzaExtrajudicialDetList(List<CobranzaExtrajudicialDet> cobranzaExtrajudicialDetList) {
        this.cobranzaExtrajudicialDetList = cobranzaExtrajudicialDetList;
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
        if (!(object instanceof CobranzaExtrajudicial)) {
            return false;
        }
        CobranzaExtrajudicial other = (CobranzaExtrajudicial) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cobranzas.CobranzaExtrajudicial[ codigo=" + codigo + " ]";
    }
    
}