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
public class ServicioAplRecCatAutPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SERVICIO_APLICACION_REC")
    private long codigoServicioAplicacionRec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CATALOGO_AUTORIZACION")
    private long codigoCatalogoAutorizacion;

    public ServicioAplRecCatAutPK() {
    }

    public ServicioAplRecCatAutPK(long codigoServicioAplicacionRec, long codigoCatalogoAutorizacion) {
        this.codigoServicioAplicacionRec = codigoServicioAplicacionRec;
        this.codigoCatalogoAutorizacion = codigoCatalogoAutorizacion;
    }

    public long getCodigoServicioAplicacionRec() {
        return codigoServicioAplicacionRec;
    }

    public void setCodigoServicioAplicacionRec(long codigoServicioAplicacionRec) {
        this.codigoServicioAplicacionRec = codigoServicioAplicacionRec;
    }

    public long getCodigoCatalogoAutorizacion() {
        return codigoCatalogoAutorizacion;
    }

    public void setCodigoCatalogoAutorizacion(long codigoCatalogoAutorizacion) {
        this.codigoCatalogoAutorizacion = codigoCatalogoAutorizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoServicioAplicacionRec;
        hash += (int) codigoCatalogoAutorizacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioAplRecCatAutPK)) {
            return false;
        }
        ServicioAplRecCatAutPK other = (ServicioAplRecCatAutPK) object;
        if (this.codigoServicioAplicacionRec != other.codigoServicioAplicacionRec) {
            return false;
        }
        if (this.codigoCatalogoAutorizacion != other.codigoCatalogoAutorizacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.mss.web.servicio.entidad.ServicioAplRecCatAutPK[ codigoServicioAplicacionRec=" + codigoServicioAplicacionRec + ", codigoCatalogoAutorizacion=" + codigoCatalogoAutorizacion + " ]";
    }
    
}
