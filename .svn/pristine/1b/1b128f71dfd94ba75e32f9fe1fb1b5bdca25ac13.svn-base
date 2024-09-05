/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.comunes;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_COMUNES.UBICACION_GEOGRAFICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UbicacionGeografica.findAll", query = "SELECT u FROM UbicacionGeografica u"),
    @NamedQuery(name = "UbicacionGeografica.findByCodigo", query = "SELECT u FROM UbicacionGeografica u WHERE u.codigo = :codigo"),
    @NamedQuery(name = "UbicacionGeografica.findByNombre", query = "SELECT u FROM UbicacionGeografica u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "UbicacionGeografica.findBySiglas", query = "SELECT u FROM UbicacionGeografica u WHERE u.siglas = :siglas"),
    @NamedQuery(name = "UbicacionGeografica.findByEliminado", query = "SELECT u FROM UbicacionGeografica u WHERE u.eliminado = :eliminado"),
    // --------------------------------------------------------------------------
    // PERSONALIZADOS
    @NamedQuery(name = "UbicacionGeografica.findByJerarquiaEliminado", query = "SELECT u FROM UbicacionGeografica u WHERE u.codigoJerarquia.codigo = :codigoJerarquia AND u.eliminado = :eliminado ORDER BY u.nombre"),
    @NamedQuery(name = "UbicacionGeografica.findByPadreEliminado", query = "SELECT u FROM UbicacionGeografica u WHERE u.codigoUbiGeoPadre = :codigoUbiGeoPadre AND u.eliminado = :eliminado ORDER BY u.nombre")


})
public class UbicacionGeografica implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByJerarquiaEliminado = "UbicacionGeografica.findByJerarquiaEliminado";
    public static final String findByPadreEliminado = "UbicacionGeografica.findByPadreEliminado";
    public static final String findByCodigo = "UbicacionGeografica.findByCodigo";
   

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_UBICACION_GEOGRAFICA")
    @SequenceGenerator(name = "GSQ_UBICACION_GEOGRAFICA", schema = "MKS_COMUNES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_UBICACION_GEOGRAFICA")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SIGLAS")
    private String siglas;
    @Column(name = "CODIGO_OC")
    private String codigoOc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(mappedBy = "codigoUbiGeoPadre")
    private Collection<UbicacionGeografica> ubicacionGeograficaCollection;
    @JoinColumn(name = "CODIGO_UBI_GEO_PADRE", referencedColumnName = "CODIGO")
    @ManyToOne
    private UbicacionGeografica codigoUbiGeoPadre;
    @JoinColumn(name = "CODIGO_JERARQUIA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private JerarquiaUbicacionGeografica codigoJerarquia;

    public UbicacionGeografica() {
    }

    public UbicacionGeografica(Long codigo) {
        this.codigo = codigo;
    }

    public UbicacionGeografica(Long codigo, String nombre, String siglas, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<UbicacionGeografica> getUbicacionGeograficaCollection() {
        return ubicacionGeograficaCollection;
    }

    public void setUbicacionGeograficaCollection(Collection<UbicacionGeografica> ubicacionGeograficaCollection) {
        this.ubicacionGeograficaCollection = ubicacionGeograficaCollection;
    }

    public UbicacionGeografica getCodigoUbiGeoPadre() {
        return codigoUbiGeoPadre;
    }

    public void setCodigoUbiGeoPadre(UbicacionGeografica codigoUbiGeoPadre) {
        this.codigoUbiGeoPadre = codigoUbiGeoPadre;
    }

    public JerarquiaUbicacionGeografica getCodigoJerarquia() {
        return codigoJerarquia;
    }

    public void setCodigoJerarquia(JerarquiaUbicacionGeografica codigoJerarquia) {
        this.codigoJerarquia = codigoJerarquia;
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
        if (!(object instanceof UbicacionGeografica)) {
            return false;
        }
        UbicacionGeografica other = (UbicacionGeografica) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.comunes.UbicacionGeografica[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoOc
     */
    public String getCodigoOc() {
        return codigoOc;
    }

    /**
     * @param codigoOc the codigoOc to set
     */
    public void setCodigoOc(String codigoOc) {
        this.codigoOc = codigoOc;
    }

}
