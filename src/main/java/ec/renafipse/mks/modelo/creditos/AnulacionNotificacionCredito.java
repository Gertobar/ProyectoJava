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
@Table(name = "MKS_CREDITOS.ANULACION_NOTIFICACION_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnulacionNotificacionCredito.findAll", query = "SELECT a FROM AnulacionNotificacionCredito a"),
    @NamedQuery(name = "AnulacionNotificacionCredito.findByCodigo", query = "SELECT a FROM AnulacionNotificacionCredito a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "AnulacionNotificacionCredito.findByEstado", query = "SELECT a FROM AnulacionNotificacionCredito a WHERE a.estado = :estado"),
    @NamedQuery(name = "AnulacionNotificacionCredito.findByRegistradoPor", query = "SELECT a FROM AnulacionNotificacionCredito a WHERE a.registradoPor = :registradoPor"),
    @NamedQuery(name = "AnulacionNotificacionCredito.findByFechaAnulacion", query = "SELECT a FROM AnulacionNotificacionCredito a WHERE a.fechaAnulacion = :fechaAnulacion"),
    @NamedQuery(name = "AnulacionNotificacionCredito.findByFechaSistema", query = "SELECT a FROM AnulacionNotificacionCredito a WHERE a.fechaSistema = :fechaSistema")})
public class AnulacionNotificacionCredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_ANULACION_NOTIFICACION_CRE")
    @SequenceGenerator(name = "GSQ_ANULACION_NOTIFICACION_CRE", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_ANULACION_NOTIFICACION_CRE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "REGISTRADO_POR")
    private Long registradoPor;
    @Column(name = "FECHA_ANULACION")
    @Temporal(TemporalType.DATE)
    private Date fechaAnulacion;
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.DATE)
    private Date fechaSistema;
    @JoinColumn(name = "CODIGO_NOTIFICACION", referencedColumnName = "CODIGO")
    @ManyToOne
    private NotificacionCredito codigoNotificacion;

    public AnulacionNotificacionCredito() {
    }

    public AnulacionNotificacionCredito(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(Long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public NotificacionCredito getCodigoNotificacion() {
        return codigoNotificacion;
    }

    public void setCodigoNotificacion(NotificacionCredito codigoNotificacion) {
        this.codigoNotificacion = codigoNotificacion;
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
        if (!(object instanceof AnulacionNotificacionCredito)) {
            return false;
        }
        AnulacionNotificacionCredito other = (AnulacionNotificacionCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject4.AnulacionNotificacionCredito[ codigo=" + codigo + " ]";
    }
    
}
