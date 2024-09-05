/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos.asignacionCartera;

import ec.renafipse.mks.modelo.socios.Persona;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author willan
 */
@Entity
@Table(name = "MKS_CREDITOS.RECUPERADOR")
@NamedQueries({
    @NamedQuery(name = "Recuperador.findAll", query = "SELECT r FROM Recuperador r")
    , @NamedQuery(name = "Recuperador.findByCodigoPersona", query = "SELECT r FROM Recuperador r WHERE r.codigoPersona = :codigoPersona")
    , @NamedQuery(name = "Recuperador.findByCodigoIfip", query = "SELECT r FROM Recuperador r WHERE r.codigoIfip = :codigoIfip")
    , @NamedQuery(name = "Recuperador.findByCodigoEmpresa", query = "SELECT r FROM Recuperador r WHERE r.codigoEmpresa = :codigoEmpresa")
    , @NamedQuery(name = "Recuperador.findByCodigoUsuario", query = "SELECT r FROM Recuperador r WHERE r.codigoUsuario = :codigoUsuario")
    , @NamedQuery(name = "Recuperador.findByFechaRegistro", query = "SELECT r FROM Recuperador r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Recuperador.findByVigente", query = "SELECT r FROM Recuperador r WHERE r.vigente = :vigente")
    , @NamedQuery(name = "Recuperador.findByEliminado", query = "SELECT r FROM Recuperador r WHERE r.eliminado = :eliminado")})
public class Recuperador implements Serializable  {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private Long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Column(name = "CODIGO_EMPRESA")
    private Long codigoEmpresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoRecuperador")
    private List<CarteraAsignada> carteraAsignadaList;

    public Recuperador() {
    }

    public Recuperador(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public Recuperador(Long codigoPersona, long codigoIfip, long codigoUsuario, Date fechaRegistro, String vigente, String eliminado) {
        this.codigoPersona = codigoPersona;
        this.codigoIfip = codigoIfip;
        this.codigoUsuario = codigoUsuario;
        this.fechaRegistro = fechaRegistro;
        this.vigente = vigente;
        this.eliminado = eliminado;
    }

    public Long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public Long getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Long codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<CarteraAsignada> getCarteraAsignadaList() {
        return carteraAsignadaList;
    }

    public void setCarteraAsignadaList(List<CarteraAsignada> carteraAsignadaList) {
        this.carteraAsignadaList = carteraAsignadaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPersona != null ? codigoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recuperador)) {
            return false;
        }
        Recuperador other = (Recuperador) object;
        if ((this.codigoPersona == null && other.codigoPersona != null) || (this.codigoPersona != null && !this.codigoPersona.equals(other.codigoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.asignacionCartera.Recuperador[ codigoPersona=" + codigoPersona + " ]";
    }
    
}
