/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "MKS_SOCIOS.ITEM_SITUACION_PATRIMONIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemSituacionPatrimonial.findAll", query = "SELECT i FROM ItemSituacionPatrimonial i"),
    @NamedQuery(name = "ItemSituacionPatrimonial.findByCodigo", query = "SELECT i FROM ItemSituacionPatrimonial i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "ItemSituacionPatrimonial.findByIndice", query = "SELECT i FROM ItemSituacionPatrimonial i WHERE i.indice = :indice"),
    @NamedQuery(name = "ItemSituacionPatrimonial.findByNombre", query = "SELECT i FROM ItemSituacionPatrimonial i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "ItemSituacionPatrimonial.findByTipo", query = "SELECT i FROM ItemSituacionPatrimonial i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "ItemSituacionPatrimonial.findByEliminado", query = "SELECT i FROM ItemSituacionPatrimonial i WHERE i.eliminado = :eliminado"),
    // Personalizados
    @NamedQuery(name = "ItemSituacionPatrimonial.findByTipoEliminado", query = "SELECT i FROM ItemSituacionPatrimonial i WHERE i.tipo = :tipo AND i.eliminado = :eliminado ORDER BY i.nombre"  )
})
public class ItemSituacionPatrimonial implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByTipoEliminado = "ItemSituacionPatrimonial.findByTipoEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_ITEM_SITUACION_PATRIMONIAL")
    @SequenceGenerator(name = "GSQ_ITEM_SITUACION_PATRIMONIAL", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_ITEM_SITUACION_PATRIMONIAL")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "INDICE")
    private String indice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemSituacionPatrimonial")
    private Collection<SocioSituacionPatAct> socioSituacionPatActCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemSituacionPatrimonial")
    private Collection<SocioSituacionPatPas> socioSituacionPatPasCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "itemSituacionPatrimonial")
    private ItemSituacionPatrimonialMon itemSituacionPatrimonialMon;

    public ItemSituacionPatrimonial() {
    }

    public ItemSituacionPatrimonial(Long codigo) {
        this.codigo = codigo;
    }

    public ItemSituacionPatrimonial(Long codigo, String indice, String nombre, char tipo, char eliminado) {
        this.codigo = codigo;
        this.indice = indice;
        this.nombre = nombre;
        this.tipo = tipo;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<SocioSituacionPatAct> getSocioSituacionPatActCollection() {
        return socioSituacionPatActCollection;
    }

    public void setSocioSituacionPatActCollection(Collection<SocioSituacionPatAct> socioSituacionPatActCollection) {
        this.socioSituacionPatActCollection = socioSituacionPatActCollection;
    }

    @XmlTransient
    public Collection<SocioSituacionPatPas> getSocioSituacionPatPasCollection() {
        return socioSituacionPatPasCollection;
    }

    public void setSocioSituacionPatPasCollection(Collection<SocioSituacionPatPas> socioSituacionPatPasCollection) {
        this.socioSituacionPatPasCollection = socioSituacionPatPasCollection;
    }

    public ItemSituacionPatrimonialMon getItemSituacionPatrimonialMon() {
        return itemSituacionPatrimonialMon;
    }

    public void setItemSituacionPatrimonialMon(ItemSituacionPatrimonialMon itemSituacionPatrimonialMon) {
        this.itemSituacionPatrimonialMon = itemSituacionPatrimonialMon;
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
        if (!(object instanceof ItemSituacionPatrimonial)) {
            return false;
        }
        ItemSituacionPatrimonial other = (ItemSituacionPatrimonial) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.ItemSituacionPatrimonial[ codigo=" + codigo + " ]";
    }
    
}
