/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos.lineacredito;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_CREDITOS.LINEA_CREDITO_SOL_TIP_GAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaCreditoSolTipGar.findAll", query = "SELECT l FROM LineaCreditoSolTipGar l")
    , @NamedQuery(name = "LineaCreditoSolTipGar.findByCodigo", query = "SELECT l FROM LineaCreditoSolTipGar l WHERE l.codigo = :codigo")
    , @NamedQuery(name = "LineaCreditoSolTipGar.findByCodigoUsuarioReg", query = "SELECT l FROM LineaCreditoSolTipGar l WHERE l.codigoUsuarioReg = :codigoUsuarioReg")
    , @NamedQuery(name = "LineaCreditoSolTipGar.findByFechaRegistro", query = "SELECT l FROM LineaCreditoSolTipGar l WHERE l.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "LineaCreditoSolTipGar.findByEliminado", query = "SELECT l FROM LineaCreditoSolTipGar l WHERE l.eliminado = :eliminado")
    , @NamedQuery(name = "LineaCreditoSolTipGar.findByCodigoLineaCreSol", query = "SELECT l FROM LineaCreditoSolTipGar l WHERE l.lineaCreditoSolicitud.codigo = :codigoLineaCreSol")
    , @NamedQuery(name = "LineaCreditoSolTipGar.findByCodigoTipoGarantia", query = "SELECT l FROM LineaCreditoSolTipGar l WHERE l.codigoTipoGarantia = :codigoTipoGarantia")
    , @NamedQuery(name = "LineaCreditoSolTipGar.findByCodigoLineaCreditoSolicitudCodigoTipoGarantia", query = "SELECT l FROM LineaCreditoSolTipGar l WHERE l.lineaCreditoSolicitud.codigo = :codigoLineaCreditoSolicitud AND l.codigoTipoGarantia = :codigoTipoGarantia")
})
public class LineaCreditoSolTipGar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_LINEA_CREDITO_SOL_TIP_GAR")
    @SequenceGenerator(name = "GSQ_LINEA_CREDITO_SOL_TIP_GAR", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_LINEA_CREDITO_SOL_TIP_GAR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
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
    @Column(name = "ELIMINADO")
    private Character eliminado;
    @JoinColumn(name = "CODIGO_LINEA_CRE_SOL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private LineaCreditoSolicitud lineaCreditoSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_GARANTIA")
    private long codigoTipoGarantia;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lineaCreditoSolicitudTipoGarantia")
    private List<LineaCreditoSolicitudGar> listaLineaCreditoSolicitudGarantia;

    public LineaCreditoSolTipGar() {
    }

    public LineaCreditoSolTipGar(Long codigo) {
        this.codigo = codigo;
    }

    public LineaCreditoSolTipGar(Long codigo, long codigoUsuarioReg, Date fechaRegistro, Character eliminado, LineaCreditoSolicitud lineaCreditoSolicitud, long codigoTipoGarantia) {
        this.codigo = codigo;
        this.codigoUsuarioReg = codigoUsuarioReg;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
        this.lineaCreditoSolicitud = lineaCreditoSolicitud;
        this.codigoTipoGarantia = codigoTipoGarantia;
    }
    
    public LineaCreditoSolTipGar(long codigoUsuarioReg, Date fechaRegistro, Character eliminado, LineaCreditoSolicitud lineaCreditoSolicitud, long codigoTipoGarantia) {
        this.codigoUsuarioReg = codigoUsuarioReg;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
        this.lineaCreditoSolicitud = lineaCreditoSolicitud;
        this.codigoTipoGarantia = codigoTipoGarantia;
    }
    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public Character getEliminado() {
        return eliminado;
    }

    public void setEliminado(Character eliminado) {
        this.eliminado = eliminado;
    }

    public LineaCreditoSolicitud getLineaCreditoSolicitud() {
        return lineaCreditoSolicitud;
    }

    public void setLineaCreditoSolicitud(LineaCreditoSolicitud lineaCreditoSolicitud) {
        this.lineaCreditoSolicitud = lineaCreditoSolicitud;
    }

    public long getCodigoTipoGarantia() {
        return codigoTipoGarantia;
    }

    public void setCodigoTipoGarantia(long codigoTipoGarantia) {
        this.codigoTipoGarantia = codigoTipoGarantia;
    }
    
    public List<LineaCreditoSolicitudGar> getListaLineaCreditoSolicitudGarantia() {
        return listaLineaCreditoSolicitudGarantia;
    }
    
    public void setListaLineaCreditoSolicitudGarantia(List<LineaCreditoSolicitudGar> listaLineaCreditoSolicitudGarantia) {
        this.listaLineaCreditoSolicitudGarantia = listaLineaCreditoSolicitudGarantia;
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
        if (!(object instanceof LineaCreditoSolTipGar)) {
            return false;
        }
        LineaCreditoSolTipGar other = (LineaCreditoSolTipGar) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolTipGar[ codigo=" + codigo + " ]";
    }
    
}
