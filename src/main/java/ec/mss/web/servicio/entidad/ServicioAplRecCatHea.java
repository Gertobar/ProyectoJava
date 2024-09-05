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
@Table(name = "SERVICIO_APL_REC_CAT_HEA", schema = "MSS_SERVICIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioAplRecCatHea.findAll", query = "SELECT s FROM ServicioAplRecCatHea s")
    , @NamedQuery(name = "ServicioAplRecCatHea.findByCodigoServicioAplicacionRec", query = "SELECT s FROM ServicioAplRecCatHea s WHERE s.servicioAplRecCatHeaPK.codigoServicioAplicacionRec = :codigoServicioAplicacionRec")
    , @NamedQuery(name = "ServicioAplRecCatHea.findByCodigoCatalogoHeader", query = "SELECT s FROM ServicioAplRecCatHea s WHERE s.servicioAplRecCatHeaPK.codigoCatalogoHeader = :codigoCatalogoHeader")
    , @NamedQuery(name = "ServicioAplRecCatHea.findByEstado", query = "SELECT s FROM ServicioAplRecCatHea s WHERE s.estado = :estado")})
public class ServicioAplRecCatHea implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ServicioAplRecCatHeaPK servicioAplRecCatHeaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private short estado;
    @JoinColumn(name = "CODIGO_CATALOGO_HEADER", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CatalogoHeader catalogoHeader;
    @JoinColumn(name = "CODIGO_SERVICIO_APLICACION_REC", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ServicioAplicacionRecurso servicioAplicacionRecurso;

    public ServicioAplRecCatHea() {
    }

    public ServicioAplRecCatHea(ServicioAplRecCatHeaPK servicioAplRecCatHeaPK) {
        this.servicioAplRecCatHeaPK = servicioAplRecCatHeaPK;
    }

    public ServicioAplRecCatHea(ServicioAplRecCatHeaPK servicioAplRecCatHeaPK, short estado) {
        this.servicioAplRecCatHeaPK = servicioAplRecCatHeaPK;
        this.estado = estado;
    }

    public ServicioAplRecCatHea(long codigoServicioAplicacionRec, long codigoCatalogoHeader) {
        this.servicioAplRecCatHeaPK = new ServicioAplRecCatHeaPK(codigoServicioAplicacionRec, codigoCatalogoHeader);
    }

    public ServicioAplRecCatHeaPK getServicioAplRecCatHeaPK() {
        return servicioAplRecCatHeaPK;
    }

    public void setServicioAplRecCatHeaPK(ServicioAplRecCatHeaPK servicioAplRecCatHeaPK) {
        this.servicioAplRecCatHeaPK = servicioAplRecCatHeaPK;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public CatalogoHeader getCatalogoHeader() {
        return catalogoHeader;
    }

    public void setCatalogoHeader(CatalogoHeader catalogoHeader) {
        this.catalogoHeader = catalogoHeader;
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
        hash += (servicioAplRecCatHeaPK != null ? servicioAplRecCatHeaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioAplRecCatHea)) {
            return false;
        }
        ServicioAplRecCatHea other = (ServicioAplRecCatHea) object;
        if ((this.servicioAplRecCatHeaPK == null && other.servicioAplRecCatHeaPK != null) || (this.servicioAplRecCatHeaPK != null && !this.servicioAplRecCatHeaPK.equals(other.servicioAplRecCatHeaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.mss.web.servicio.entidad.ServicioAplRecCatHea[ servicioAplRecCatHeaPK=" + servicioAplRecCatHeaPK + " ]";
    }
    
}
