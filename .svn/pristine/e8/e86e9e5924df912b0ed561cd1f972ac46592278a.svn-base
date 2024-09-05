/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.servicio.entidad;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "SERVICIO_APLICACION_RECURSO", schema = "MSS_SERVICIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioAplicacionRecurso.findByEstadoYServicioEstado", query = "SELECT s FROM ServicioAplicacionRecurso s WHERE s.estado = :estado AND s.codigoServicioAplicacion.estado = :estadoServicioAplicacion")
    ,@NamedQuery(name = "ServicioAplicacionRecurso.findByCodigo", query = "SELECT s FROM ServicioAplicacionRecurso s WHERE s.codigo = :codigo")})
public class ServicioAplicacionRecurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RECURSO")
    private String recurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "RECURSO_URI")
    private String recursoUri;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "RECURSO_PROTOCOLO")
    private String recursoProtocolo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "RECURSO_HTTP")
    private String recursoHttp;
    @Size(max = 25)
    @Column(name = "RECURSO_BODY")
    private String recursoBody;
    @Lob
    @Column(name = "RECURSO_BODY_TRAMA")
    private String recursoBodyTrama;
    @Size(max = 8)
    @Column(name = "RECURSO_TIPO_TOKEN")
    private String recursoTipoToken;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RECURSO_DESCRIPCION")
    private String recursoDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private short estado;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "servicioAplicacionRecurso")
    private Set<ServicioAplRecCatHea> servicioAplRecCatHeaSet =  new HashSet<ServicioAplRecCatHea>();
    @JoinColumn(name = "CODIGO_SERVICIO_APLICACION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ServicioAplicacion codigoServicioAplicacion;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "servicioAplicacionRecurso")
    private Set<ServicioAplRecCatAut> servicioAplRecCatAutSet =  new HashSet<ServicioAplRecCatAut>();

    public ServicioAplicacionRecurso() {
    }

    public ServicioAplicacionRecurso(Long codigo) {
        this.codigo = codigo;
    }

    public ServicioAplicacionRecurso(Long codigo, String recurso, String recursoUri, String recursoProtocolo, String recursoHttp, String recursoDescripcion, short estado) {
        this.codigo = codigo;
        this.recurso = recurso;
        this.recursoUri = recursoUri;
        this.recursoProtocolo = recursoProtocolo;
        this.recursoHttp = recursoHttp;
        this.recursoDescripcion = recursoDescripcion;
        this.estado = estado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getRecursoUri() {
        return recursoUri;
    }

    public void setRecursoUri(String recursoUri) {
        this.recursoUri = recursoUri;
    }

    public String getRecursoProtocolo() {
        return recursoProtocolo;
    }

    public void setRecursoProtocolo(String recursoProtocolo) {
        this.recursoProtocolo = recursoProtocolo;
    }

    public String getRecursoHttp() {
        return recursoHttp;
    }

    public void setRecursoHttp(String recursoHttp) {
        this.recursoHttp = recursoHttp;
    }

    public String getRecursoBody() {
        return recursoBody;
    }

    public void setRecursoBody(String recursoBody) {
        this.recursoBody = recursoBody;
    }

    public String getRecursoBodyTrama() {
        return recursoBodyTrama;
    }

    public void setRecursoBodyTrama(String recursoBodyTrama) {
        this.recursoBodyTrama = recursoBodyTrama;
    }

    public String getRecursoTipoToken() {
        return recursoTipoToken;
    }

    public void setRecursoTipoToken(String recursoTipoToken) {
        this.recursoTipoToken = recursoTipoToken;
    }

    public String getRecursoDescripcion() {
        return recursoDescripcion;
    }

    public void setRecursoDescripcion(String recursoDescripcion) {
        this.recursoDescripcion = recursoDescripcion;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Set<ServicioAplRecCatHea> getServicioAplRecCatHeaSet() {
        return servicioAplRecCatHeaSet;
    }

    public void setServicioAplRecCatHeaSet(Set<ServicioAplRecCatHea> servicioAplRecCatHeaSet) {
        this.servicioAplRecCatHeaSet = servicioAplRecCatHeaSet;
    }

    public ServicioAplicacion getCodigoServicioAplicacion() {
        return codigoServicioAplicacion;
    }

    public void setCodigoServicioAplicacion(ServicioAplicacion codigoServicioAplicacion) {
        this.codigoServicioAplicacion = codigoServicioAplicacion;
    }

    @XmlTransient
    public Set<ServicioAplRecCatAut> getServicioAplRecCatAutSet() {
        return servicioAplRecCatAutSet;
    }

    public void setServicioAplRecCatAutSet(Set<ServicioAplRecCatAut> servicioAplRecCatAutSet) {
        this.servicioAplRecCatAutSet = servicioAplRecCatAutSet;
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
        if (!(object instanceof ServicioAplicacionRecurso)) {
            return false;
        }
        ServicioAplicacionRecurso other = (ServicioAplicacionRecurso) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.mss.web.servicio.entidad.ServicioAplicacionRecurso[ codigo=" + codigo + " ]";
    }
    
}
