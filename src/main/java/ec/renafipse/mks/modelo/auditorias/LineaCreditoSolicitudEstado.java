/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.auditorias;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "MKS_AUDITORIAS.LINEA_CREDITO_SOLICITUD_ESTADO")
@XmlRootElement
public class LineaCreditoSolicitudEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_LINEA_CREDITO_SOL_EST")
    @SequenceGenerator(name = "GSQ_LINEA_CREDITO_SOL_EST", schema = "MKS_AUDITORIAS",  allocationSize = 0, sequenceName = "SEQ_LINEA_CREDITO_SOL_EST")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_LINEA_CREDITO_SOL")
    private Long codigoLineaCreditoSol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_LINEA_CRE_EST_SOL")
    private Long codigoLineaCreEstSol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCION")
    private char accion;

    public LineaCreditoSolicitudEstado() {
    }

    public LineaCreditoSolicitudEstado(Long codigo) {
        this.codigo = codigo;
    }

    public LineaCreditoSolicitudEstado(Long codigo, Long codigoLineaCreditoSol, Long codigoLineaCreEstSol, Long codigoUsuario, Date fecha, char accion) {
        this.codigo = codigo;
        this.codigoLineaCreditoSol = codigoLineaCreditoSol;
        this.codigoLineaCreEstSol = codigoLineaCreEstSol;
        this.codigoUsuario = codigoUsuario;
        this.fecha = fecha;
        this.accion = accion;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoLineaCreditoSol() {
        return codigoLineaCreditoSol;
    }

    public void setCodigoLineaCreditoSol(Long codigoLineaCreditoSol) {
        this.codigoLineaCreditoSol = codigoLineaCreditoSol;
    }

    public Long getCodigoLineaCreEstSol() {
        return codigoLineaCreEstSol;
    }

    public void setCodigoLineaCreEstSol(Long codigoLineaCreEstSol) {
        this.codigoLineaCreEstSol = codigoLineaCreEstSol;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public char getAccion() {
        return accion;
    }

    public void setAccion(char accion) {
        this.accion = accion;
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
        if (!(object instanceof LineaCreditoSolicitudEstado)) {
            return false;
        }
        LineaCreditoSolicitudEstado other = (LineaCreditoSolicitudEstado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.auditorias.LineaCreditoSolicitudEstado[ codigo=" + codigo + " ]";
    }

}
