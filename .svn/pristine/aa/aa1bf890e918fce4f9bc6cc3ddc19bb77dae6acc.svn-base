/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioEstadoCredito;
import java.io.Serializable;
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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.SEGUIMIENTO_ESTADO_SOLICITUD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeguimientoEstadoSolicitud.findAll", query = "SELECT s FROM SeguimientoEstadoSolicitud s"),
    @NamedQuery(name = "SeguimientoEstadoSolicitud.findByCodigo", query = "SELECT s FROM SeguimientoEstadoSolicitud s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "SeguimientoEstadoSolicitud.findByCodigoEstado", query = "SELECT s FROM SeguimientoEstadoSolicitud s WHERE s.codigoEstado = :codigoEstado"),
    @NamedQuery(name = "SeguimientoEstadoSolicitud.findByRegistradoPor", query = "SELECT s FROM SeguimientoEstadoSolicitud s WHERE s.registradoPor = :registradoPor"),
    @NamedQuery(name = "SeguimientoEstadoSolicitud.findByFechaEstado", query = "SELECT s FROM SeguimientoEstadoSolicitud s WHERE s.fechaEstado = :fechaEstado"),
//Personalizadas
    @NamedQuery(name = "SeguimientoEstadoSolicitud.findBySolicitudIfipEstado", query = "SELECT s FROM SeguimientoEstadoSolicitud s JOIN s.solicitud so JOIN s.usuarioEstadoCredito u WHERE so.solicitudPK.numero = :numeroSolicitud AND so.solicitudPK.codigoIfip = :codigoIfip AND u.eliminado = :eliminado")

})
public class SeguimientoEstadoSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findBySolicitudIfipEstado = "SeguimientoEstadoSolicitud.findBySolicitudIfipEstado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_SEGUIMIENTO_ESTADO_SOL")
    @SequenceGenerator(name = "GSQ_SEGUIMIENTO_ESTADO_SOL", schema = "MKS_CREDITOS", allocationSize = 0, sequenceName = "SEQ_SEGUIMIENTO_ESTADO_SOL")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESTADO")
    private long codigoEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Solicitud solicitud;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO_ESTADO", insertable = false, updatable = false),
        @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO_USUARIO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private UsuarioEstadoCredito usuarioEstadoCredito;

    public SeguimientoEstadoSolicitud() {
    }

    public SeguimientoEstadoSolicitud(Long codigo) {
        this.codigo = codigo;
    }

    public SeguimientoEstadoSolicitud(Long codigo, Long codigoEstado, Long registradoPor, Date fechaEstado) {
        this.codigo = codigo;
        this.codigoEstado = codigoEstado;
        this.registradoPor = registradoPor;
        this.fechaEstado = fechaEstado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(long codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
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
        if (!(object instanceof SeguimientoEstadoSolicitud)) {
            return false;
        }
        SeguimientoEstadoSolicitud other = (SeguimientoEstadoSolicitud) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.SeguimientoEstadoSolicitud[ codigo=" + codigo + " ]";
    }

    /**
     * @return the usuarioEstadoCredito
     */
    public UsuarioEstadoCredito getUsuarioEstadoCredito() {
        return usuarioEstadoCredito;
    }

    /**
     * @param usuarioEstadoCredito the usuarioEstadoCredito to set
     */
    public void setUsuarioEstadoCredito(UsuarioEstadoCredito usuarioEstadoCredito) {
        this.usuarioEstadoCredito = usuarioEstadoCredito;
    }

}
