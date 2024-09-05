 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

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
 * @author SISTEMAS
 */
@Entity
@Table(name = "MKS_CREDITOS.SOLICITUD_APROBADA_NEGADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudAprobadaNegada.findAll", query = "SELECT s FROM SolicitudAprobadaNegada s"),
    @NamedQuery(name = "SolicitudAprobadaNegada.findByCodigo", query = "SELECT s FROM SolicitudAprobadaNegada s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "SolicitudAprobadaNegada.findByFechaRegistro", query = "SELECT s FROM SolicitudAprobadaNegada s WHERE s.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "SolicitudAprobadaNegada.findByRegistradoPor", query = "SELECT s FROM SolicitudAprobadaNegada s WHERE s.registradoPor = :registradoPor"),
    @NamedQuery(name = "SolicitudAprobadaNegada.findByAprobado", query = "SELECT s FROM SolicitudAprobadaNegada s WHERE s.aprobado = :aprobado"),
    @NamedQuery(name = "SolicitudAprobadaNegada.findByObservaciones", query = "SELECT s FROM SolicitudAprobadaNegada s WHERE s.observaciones = :observaciones")})
public class SolicitudAprobadaNegada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_SOLICITUD_APROBADA_NEGADA")
    @SequenceGenerator(name = "GSQ_SOLICITUD_APROBADA_NEGADA", schema = "MKS_CREDITOS", allocationSize = 0, sequenceName = "SEQ_SOLICITUD_APROBADA_NEGADA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @Column(name = "APROBADO")
    private char aprobado;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO",insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Solicitud solicitud;

    public SolicitudAprobadaNegada() {
    }

    public SolicitudAprobadaNegada(Long codigo) {
        this.codigo = codigo;
    }

    public SolicitudAprobadaNegada(Long codigo, Date fechaRegistro, long registradoPor, char aprobado) {
        this.codigo = codigo;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
        this.aprobado = aprobado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public char getAprobado() {
        return aprobado;
    }

    public void setAprobado(char aprobado) {
        this.aprobado = aprobado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        if (!(object instanceof SolicitudAprobadaNegada)) {
            return false;
        }
        SolicitudAprobadaNegada other = (SolicitudAprobadaNegada) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject2.SolicitudAprobadaNegada[ codigo=" + codigo + " ]";
    }

}
