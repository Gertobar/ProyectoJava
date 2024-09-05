/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.servicio.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "SERVICIO_APL_REC_CAT_AUT" , schema = "MSS_SERVICIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioAplRecCatAut.findAll", query = "SELECT s FROM ServicioAplRecCatAut s")
    , @NamedQuery(name = "ServicioAplRecCatAut.findByCodigoServicioAplicacionRec", query = "SELECT s FROM ServicioAplRecCatAut s WHERE s.servicioAplRecCatAutPK.codigoServicioAplicacionRec = :codigoServicioAplicacionRec")
    , @NamedQuery(name = "ServicioAplRecCatAut.findByCodigoCatalogoAutorizacion", query = "SELECT s FROM ServicioAplRecCatAut s WHERE s.servicioAplRecCatAutPK.codigoCatalogoAutorizacion = :codigoCatalogoAutorizacion")
    , @NamedQuery(name = "ServicioAplRecCatAut.findByEstado", query = "SELECT s FROM ServicioAplRecCatAut s WHERE s.estado = :estado")})
public class ServicioAplRecCatAut implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ServicioAplRecCatAutPK servicioAplRecCatAutPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private short estado;
    @JoinColumn(name = "CODIGO_CATALOGO_AUTORIZACION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CatalogoAutorizacion catalogoAutorizacion;
    @JoinColumn(name = "CODIGO_SERVICIO_APLICACION_REC", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ServicioAplicacionRecurso servicioAplicacionRecurso;

    public ServicioAplRecCatAut() {
    }

    public ServicioAplRecCatAut(ServicioAplRecCatAutPK servicioAplRecCatAutPK) {
        this.servicioAplRecCatAutPK = servicioAplRecCatAutPK;
    }

    public ServicioAplRecCatAut(ServicioAplRecCatAutPK servicioAplRecCatAutPK, short estado) {
        this.servicioAplRecCatAutPK = servicioAplRecCatAutPK;
        this.estado = estado;
    }

    public ServicioAplRecCatAut(long codigoServicioAplicacionRec, long codigoCatalogoAutorizacion) {
        this.servicioAplRecCatAutPK = new ServicioAplRecCatAutPK(codigoServicioAplicacionRec, codigoCatalogoAutorizacion);
    }

    public ServicioAplRecCatAutPK getServicioAplRecCatAutPK() {
        return servicioAplRecCatAutPK;
    }

    public void setServicioAplRecCatAutPK(ServicioAplRecCatAutPK servicioAplRecCatAutPK) {
        this.servicioAplRecCatAutPK = servicioAplRecCatAutPK;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public CatalogoAutorizacion getCatalogoAutorizacion() {
        return catalogoAutorizacion;
    }

    public void setCatalogoAutorizacion(CatalogoAutorizacion catalogoAutorizacion) {
        this.catalogoAutorizacion = catalogoAutorizacion;
    }

    public ServicioAplicacionRecurso getServicioAplicacionRecurso() {
        return servicioAplicacionRecurso;
    }

    public void setServicioAplicacionRecurso(ServicioAplicacionRecurso servicioAplicacionRecurso) {
        this.servicioAplicacionRecurso = servicioAplicacionRecurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicioAplRecCatAutPK != null ? servicioAplRecCatAutPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioAplRecCatAut)) {
            return false;
        }
        ServicioAplRecCatAut other = (ServicioAplRecCatAut) object;
        if ((this.servicioAplRecCatAutPK == null && other.servicioAplRecCatAutPK != null) || (this.servicioAplRecCatAutPK != null && !this.servicioAplRecCatAutPK.equals(other.servicioAplRecCatAutPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.mss.web.servicio.entidad.ServicioAplRecCatAut[ servicioAplRecCatAutPK=" + servicioAplRecCatAutPK + " ]";
    }
    
}
