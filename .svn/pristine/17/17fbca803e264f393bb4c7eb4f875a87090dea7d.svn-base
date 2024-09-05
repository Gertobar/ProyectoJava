/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.REFERENCIA_PERSONAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReferenciaPersonal.findAll", query = "SELECT r FROM ReferenciaPersonal r"),
    @NamedQuery(name = "ReferenciaPersonal.findByCodigo", query = "SELECT r FROM ReferenciaPersonal r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "ReferenciaPersonal.findByNombres", query = "SELECT r FROM ReferenciaPersonal r WHERE r.nombres = :nombres"),
    @NamedQuery(name = "ReferenciaPersonal.findByDireccion", query = "SELECT r FROM ReferenciaPersonal r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "ReferenciaPersonal.findByTelefono", query = "SELECT r FROM ReferenciaPersonal r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "ReferenciaPersonal.findByEliminado", query = "SELECT r FROM ReferenciaPersonal r WHERE r.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "ReferenciaPersonal.findByPersona", query = "SELECT r FROM ReferenciaPersonal r WHERE r.codigoPersona = :codigoPersona")
    
})
public class ReferenciaPersonal implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByPersona = "ReferenciaPersonal.findByPersona";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_REFERENCIA_PERSONAL")
    @SequenceGenerator(name = "GSQ_REFERENCIA_PERSONAL", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_REFERENCIA_PERSONAL")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 7, max = 18)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Persona codigoPersona;

    public ReferenciaPersonal() {
    }

    public ReferenciaPersonal(Long codigo) {
        this.codigo = codigo;
    }

    public ReferenciaPersonal(Long codigo, String nombres, String direccion, String telefono, char eliminado) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.direccion = direccion;
        this.telefono = telefono;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Persona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Persona codigoPersona) {
        this.codigoPersona = codigoPersona;
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
        if (!(object instanceof ReferenciaPersonal)) {
            return false;
        }
        ReferenciaPersonal other = (ReferenciaPersonal) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.ReferenciaPersonal[ codigo=" + codigo + " ]";
    }
    
}
