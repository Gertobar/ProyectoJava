/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mascoop
 */
@Entity
@Table(name = "SOLICITUD_MOTIVO_DEVOLUCION", catalog = "", schema = "MKS_CREDITOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudMotivoDevolucion.findAll", query = "SELECT s FROM SolicitudMotivoDevolucion s")
    , @NamedQuery(name = "SolicitudMotivoDevolucion.findByVigente", query = "SELECT s FROM SolicitudMotivoDevolucion s WHERE s.codigoIfip = :codigoIfip AND s.vigente = :vigente ORDER BY s.motivo")
    , @NamedQuery(name = "SolicitudMotivoDevolucion.findByCodigo", query = "SELECT s FROM SolicitudMotivoDevolucion s WHERE s.codigo = :codigo")
    })
public class SolicitudMotivoDevolucion implements Serializable {
    public static String findByVigente = "SolicitudMotivoDevolucion.findByVigente";
    public static String findByCodigo = "SolicitudMotivoDevolucion.findByCodigo";
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "MOTIVO")
    private String motivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;

    public SolicitudMotivoDevolucion() {
    }

    public SolicitudMotivoDevolucion(Long codigo) {
        this.codigo = codigo;
    }

    public SolicitudMotivoDevolucion(Long codigo, long codigoIfip, String motivo, String vigente) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.motivo = motivo;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
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
        if (!(object instanceof SolicitudMotivoDevolucion)) {
            return false;
        }
        SolicitudMotivoDevolucion other = (SolicitudMotivoDevolucion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.SolicitudMotivoDevolucion[ codigo=" + codigo + " ]";
    }
    
}
