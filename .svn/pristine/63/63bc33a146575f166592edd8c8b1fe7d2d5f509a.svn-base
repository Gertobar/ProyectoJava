/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.servicio.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author andres
 */
@Embeddable
public class ServicioAplRecCatHeaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SERVICIO_APLICACION_REC")
    private long codigoServicioAplicacionRec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CATALOGO_HEADER")
    private long codigoCatalogoHeader;

    public ServicioAplRecCatHeaPK() {
    }

    public ServicioAplRecCatHeaPK(long codigoServicioAplicacionRec, long codigoCatalogoHeader) {
        this.codigoServicioAplicacionRec = codigoServicioAplicacionRec;
        this.codigoCatalogoHeader = codigoCatalogoHeader;
    }

    public long getCodigoServicioAplicacionRec() {
        return codigoServicioAplicacionRec;
    }

    public void setCodigoServicioAplicacionRec(long codigoServicioAplicacionRec) {
        this.codigoServicioAplicacionRec = codigoServicioAplicacionRec;
    }

    public long getCodigoCatalogoHeader() {
        return codigoCatalogoHeader;
    }

    public void setCodigoCatalogoHeader(long codigoCatalogoHeader) {
        this.codigoCatalogoHeader = codigoCatalogoHeader;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoServicioAplicacionRec;
        hash += (int) codigoCatalogoHeader;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioAplRecCatHeaPK)) {
            return false;
        }
        ServicioAplRecCatHeaPK other = (ServicioAplRecCatHeaPK) object;
        if (this.codigoServicioAplicacionRec != other.codigoServicioAplicacionRec) {
            return false;
        }
        if (this.codigoCatalogoHeader != other.codigoCatalogoHeader) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.mss.web.servicio.entidad.ServicioAplRecCatHeaPK[ codigoServicioAplicacionRec=" + codigoServicioAplicacionRec + ", codigoCatalogoHeader=" + codigoCatalogoHeader + " ]";
    }
    
}
