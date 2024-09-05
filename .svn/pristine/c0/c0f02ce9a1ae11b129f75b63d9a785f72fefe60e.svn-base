/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@Entity
@Table(name = "MKS_CREDITOS.GARANTE_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GaranteCredito.findAll", query = "SELECT g FROM GaranteCredito g"),
    @NamedQuery(name = "GaranteCredito.findByCodigoPersona", query = "SELECT g FROM GaranteCredito g WHERE g.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "GaranteCredito.findByCoberturaDada", query = "SELECT g FROM GaranteCredito g WHERE g.coberturaDada = :coberturaDada"),
    @NamedQuery(name = "GaranteCredito.findByRegistradoPor", query = "SELECT g FROM GaranteCredito g WHERE g.registradoPor = :registradoPor"),
    @NamedQuery(name = "GaranteCredito.findByFechaRegistro", query = "SELECT g FROM GaranteCredito g WHERE g.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "GaranteCredito.findByVigente", query = "SELECT g FROM GaranteCredito g WHERE g.vigente = :vigente"),
    @NamedQuery(name = "GaranteCredito.findByEliminado", query = "SELECT g FROM GaranteCredito g WHERE g.eliminado = :eliminado"),
    @NamedQuery(name = "GaranteCredito.findByCodigo", query = "SELECT g FROM GaranteCredito g WHERE g.codigo = :codigo"),
//Personalizadas
    @NamedQuery(name = "GaranteCredito.findByGaranteCreditoIfipVigenteEliminado", query = "SELECT g FROM GaranteCredito g JOIN g.tipoGarantiaCredito t WHERE t.tipoGarantiaCreditoPK.numeroCredito = :numeroCredito AND t.tipoGarantiaCreditoPK.codigoIfip = :codigoIfip AND g.vigente = :vigente AND g.eliminado = :eliminado AND t.eliminado = :eliminado"),
    @NamedQuery(name = "GaranteCredito.findByCodigoPersonaIfipVigente", query = "SELECT g.solicitud  FROM GaranteCredito g WHERE g.codigoPersona = :codigoPersona AND g.solicitud.solicitudPK.codigoIfip = :codigoIfip AND g.vigente = :vigente"),
    @NamedQuery(name = "GaranteCredito.findByCodigoPersonaVigenteEstadoCred", query = "SELECT g.codigoPersona FROM GaranteCredito g WHERE g.codigoPersona = :codigoPersona AND g.vigente = :vigente AND g.solicitud.codigoEstado = :codigoEstado")
})
public class GaranteCredito implements Serializable {
 
    private static final long serialVersionUID = 1L;
    public static final String findByGaranteCreditoIfipVigenteEliminado = "GaranteCredito.findByGaranteCreditoIfipVigenteEliminado";
    public static final String findByCodigoPersonaVigenteEstadoCred = "GaranteCredito.findByCodigoPersonaVigenteEstadoCred";
    public static final String findByCodigoPersonaIfipVigente = "GaranteCredito.findByCodigoPersonaIfipVigente";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_GARANTE_CREDITO")
    @SequenceGenerator(name = "GSQ_GARANTE_CREDITO", schema = "MKS_CREDITOS", allocationSize = 0, sequenceName = "SEQ_GARANTE_CREDITO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "COBERTURA_DADA")
    private BigDecimal coberturaDada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENTE")
    private char vigente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO_CREDITO"),
        @JoinColumn(name = "CODIGO_TIPO_GARANTIA", referencedColumnName = "CODIGO_TIPO_GARANTIA"),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP")})
    @ManyToOne(optional = false)
    private TipoGarantiaCredito tipoGarantiaCredito;
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario registradoPorUsu;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO_PERSONA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PersonaNatural garante;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Solicitud solicitud;

    public GaranteCredito() {
    }

    public GaranteCredito(Long codigo) {
        this.codigo = codigo;
    }

    public GaranteCredito(Long codigo, long codigoPersona, BigDecimal coberturaDada, long registradoPor, Date fechaRegistro, char vigente, char eliminado) {
        this.codigo = codigo;
        this.codigoPersona = codigoPersona;
        this.coberturaDada = coberturaDada;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.vigente = vigente;
        this.eliminado = eliminado;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public BigDecimal getCoberturaDada() {
        return coberturaDada;
    }

    public void setCoberturaDada(BigDecimal coberturaDada) {
        this.coberturaDada = coberturaDada;
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

    public char getVigente() {
        return vigente;
    }

    public void setVigente(char vigente) {
        this.vigente = vigente;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public TipoGarantiaCredito getTipoGarantiaCredito() {
        return tipoGarantiaCredito;
    }

    public void setTipoGarantiaCredito(TipoGarantiaCredito tipoGarantiaCredito) {
        this.tipoGarantiaCredito = tipoGarantiaCredito;
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
        if (!(object instanceof GaranteCredito)) {
            return false;
        }
        GaranteCredito other = (GaranteCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject4.GaranteCredito[ codigo=" + codigo + " ]";
    }

    /**
     * @return the registradoPorUsu
     */
    public Usuario getRegistradoPorUsu() {
        return registradoPorUsu;
    }

    /**
     * @param registradoPorUsu the registradoPorUsu to set
     */
    public void setRegistradoPorUsu(Usuario registradoPorUsu) {
        this.registradoPorUsu = registradoPorUsu;
    }

    /**
     * @return the garante
     */
    public PersonaNatural getGarante() {
        return garante;
    }

    /**
     * @param garante the garante to set
     */
    public void setGarante(PersonaNatural garante) {
        this.garante = garante;
    }

    /**
     * @return the solicitud
     */
    public Solicitud getSolicitud() {
        return solicitud;
    }

    /**
     * @param solicitud the solicitud to set
     */
    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

}
