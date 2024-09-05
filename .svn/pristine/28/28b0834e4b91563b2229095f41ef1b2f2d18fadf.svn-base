/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Mascoop
 */
@Entity
@Table(name = "FABRICA_PERFIL", catalog = "", schema = "MKS_CREDITOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FabricaPerfil.findAll", query = "SELECT f FROM FabricaPerfil f")
    , @NamedQuery(name = "FabricaPerfil.findByCodigo", query = "SELECT f FROM FabricaPerfil f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "FabricaPerfil.findByCodigoIfip", query = "SELECT f FROM FabricaPerfil f WHERE f.codigoIfip = :codigoIfip")
    , @NamedQuery(name = "FabricaPerfil.findByNombre", query = "SELECT f FROM FabricaPerfil f WHERE f.nombre = :nombre")
    , @NamedQuery(name = "FabricaPerfil.findByDescripcion", query = "SELECT f FROM FabricaPerfil f WHERE f.descripcion = :descripcion")})
public class FabricaPerfil implements Serializable {

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
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPerfil")
    private List<FabricaUsuarioPerfil> fabricaUsuarioPerfilList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoFabricaPerfil")
    private List<FabricaPerfilEstado> fabricaPerfilEstadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoFabricaPerfil")
    private List<FabricaPerfilMonto> fabricaPerfilMontoList;

    public FabricaPerfil() {
    }

    public FabricaPerfil(Long codigo) {
        this.codigo = codigo;
    }

    public FabricaPerfil(Long codigo, long codigoIfip, String nombre, String descripcion) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<FabricaUsuarioPerfil> getFabricaUsuarioPerfilList() {
        return fabricaUsuarioPerfilList;
    }

    public void setFabricaUsuarioPerfilList(List<FabricaUsuarioPerfil> fabricaUsuarioPerfilList) {
        this.fabricaUsuarioPerfilList = fabricaUsuarioPerfilList;
    }

    @XmlTransient
    public List<FabricaPerfilEstado> getFabricaPerfilEstadoList() {
        return fabricaPerfilEstadoList;
    }

    public void setFabricaPerfilEstadoList(List<FabricaPerfilEstado> fabricaPerfilEstadoList) {
        this.fabricaPerfilEstadoList = fabricaPerfilEstadoList;
    }

    @XmlTransient
    public List<FabricaPerfilMonto> getFabricaPerfilMontoList() {
        return fabricaPerfilMontoList;
    }

    public void setFabricaPerfilMontoList(List<FabricaPerfilMonto> fabricaPerfilMontoList) {
        this.fabricaPerfilMontoList = fabricaPerfilMontoList;
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
        if (!(object instanceof FabricaPerfil)) {
            return false;
        }
        FabricaPerfil other = (FabricaPerfil) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.FabricaPerfil[ codigo=" + codigo + " ]";
    }
    
}
