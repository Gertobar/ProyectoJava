/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos.lineacredito;

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
 * @author andres
 */
@Entity
@Table(name = "MKS_CREDITOS.LINEA_CREDITO_SOLICITUD_GAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaCreditoSolicitudGar.findAll", query = "SELECT l FROM LineaCreditoSolicitudGar l")
    , @NamedQuery(name = "LineaCreditoSolicitudGar.findByCodigo", query = "SELECT l FROM LineaCreditoSolicitudGar l WHERE l.codigo = :codigo")
    , @NamedQuery(name = "LineaCreditoSolicitudGar.findByCodigoPersona", query = "SELECT l FROM LineaCreditoSolicitudGar l WHERE l.codigoPersona = :codigoPersona")
    , @NamedQuery(name = "LineaCreditoSolicitudGar.findByCoberturaDada", query = "SELECT l FROM LineaCreditoSolicitudGar l WHERE l.coberturaDada = :coberturaDada")
    , @NamedQuery(name = "LineaCreditoSolicitudGar.findByCodigoUsuarioReg", query = "SELECT l FROM LineaCreditoSolicitudGar l WHERE l.codigoUsuarioReg = :codigoUsuarioReg")
    , @NamedQuery(name = "LineaCreditoSolicitudGar.findByFechaRegistro", query = "SELECT l FROM LineaCreditoSolicitudGar l WHERE l.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "LineaCreditoSolicitudGar.findByVigente", query = "SELECT l FROM LineaCreditoSolicitudGar l WHERE l.vigente = :vigente")
    , @NamedQuery(name = "LineaCreditoSolicitudGar.findByEliminado", query = "SELECT l FROM LineaCreditoSolicitudGar l WHERE l.eliminado = :eliminado")})
public class LineaCreditoSolicitudGar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_LINEA_CREDITO_SOL_GAR")
    @SequenceGenerator(name = "GSQ_LINEA_CREDITO_SOL_GAR", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_LINEA_CREDITO_SOL_GAR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO_PERSONA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PersonaNatural garante;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "COBERTURA_DADA")
    private BigDecimal coberturaDada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_REG")
    private long codigoUsuarioReg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENTE")
    private Character vigente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private Character eliminado;
    @JoinColumn(name = "CODIGO_LIN_CRE_SOL_TIP_GAR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private LineaCreditoSolTipGar lineaCreditoSolicitudTipoGarantia;

    public LineaCreditoSolicitudGar() {
    }

    public LineaCreditoSolicitudGar(Long codigo) {
        this.codigo = codigo;
    }

    public LineaCreditoSolicitudGar(Long codigo, long codigoPersona, BigDecimal coberturaDada, long codigoUsuarioReg, Date fechaRegistro, Character vigente, Character eliminado) {
        this.codigo = codigo;
        this.codigoPersona = codigoPersona;
        this.coberturaDada = coberturaDada;
        this.codigoUsuarioReg = codigoUsuarioReg;
        this.fechaRegistro = fechaRegistro;
        this.vigente = vigente;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public long getCodigoUsuarioReg() {
        return codigoUsuarioReg;
    }

    public void setCodigoUsuarioReg(long codigoUsuarioReg) {
        this.codigoUsuarioReg = codigoUsuarioReg;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Character getVigente() {
        return vigente;
    }

    public void setVigente(Character vigente) {
        this.vigente = vigente;
    }

    public Character getEliminado() {
        return eliminado;
    }

    public void setEliminado(Character eliminado) {
        this.eliminado = eliminado;
    }

    public LineaCreditoSolTipGar getLineaCreditoSolicitudTipoGarantia() {
        return lineaCreditoSolicitudTipoGarantia;
    }

    public void setLineaCreditoSolicitudTipoGarantia(LineaCreditoSolTipGar lineaCreditoSolicitudTipoGarantia) {
        this.lineaCreditoSolicitudTipoGarantia = lineaCreditoSolicitudTipoGarantia;
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
        if (!(object instanceof LineaCreditoSolicitudGar)) {
            return false;
        }
        LineaCreditoSolicitudGar other = (LineaCreditoSolicitudGar) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitudGar[ codigo=" + codigo + " ]";
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
    
}
