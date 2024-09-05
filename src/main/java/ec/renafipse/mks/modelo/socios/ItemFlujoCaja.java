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
import javax.persistence.FetchType;
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
@Table(name = "MKS_SOCIOS.ITEM_FLUJO_CAJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemFlujoCaja.findAll", query = "SELECT i FROM ItemFlujoCaja i"),
    @NamedQuery(name = "ItemFlujoCaja.findByCodigo", query = "SELECT i FROM ItemFlujoCaja i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "ItemFlujoCaja.findByNombre", query = "SELECT i FROM ItemFlujoCaja i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "ItemFlujoCaja.findByIndice", query = "SELECT i FROM ItemFlujoCaja i WHERE i.indice = :indice"),
    @NamedQuery(name = "ItemFlujoCaja.findByTipo", query = "SELECT i FROM ItemFlujoCaja i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "ItemFlujoCaja.findByEliminado", query = "SELECT i FROM ItemFlujoCaja i WHERE i.eliminado = :eliminado"),
    // Personalizados
    @NamedQuery(name = "ItemFlujoCaja.findByTipoEliminado", query = "SELECT i FROM ItemFlujoCaja i WHERE i.tipo = :tipo AND i.eliminado = :eliminado ORDER BY i.nombre")
})
public class ItemFlujoCaja implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByTipoEliminado = "ItemFlujoCaja.findByTipoEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_ITEM_FLUJO_CAJA")
    @SequenceGenerator(name = "GSQ_ITEM_FLUJO_CAJA", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_ITEM_FLUJO_CAJA")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "INDICE")
    private String indice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemFlujoCaja", fetch = FetchType.LAZY)
    private Collection<SocioFlujoCajaIngreso> socioFlujoCajaIngresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemFlujoCaja", fetch = FetchType.LAZY)
    private Collection<SocioFlujoCajaEgreso> socioFlujoCajaEgresoCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "itemFlujoCaja")
    private ItemFlujoCajaMonto itemFlujoCajaMonto;

    public ItemFlujoCaja() {
    }

    public ItemFlujoCaja(Long codigo) {
        this.codigo = codigo;
    }

    public ItemFlujoCaja(Long codigo, String nombre, String indice, char tipo, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.indice = indice;
        this.tipo = tipo;
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

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
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
    public Collection<SocioFlujoCajaIngreso> getSocioFlujoCajaIngresoCollection() {
        return socioFlujoCajaIngresoCollection;
    }

    public void setSocioFlujoCajaIngresoCollection(Collection<SocioFlujoCajaIngreso> socioFlujoCajaIngresoCollection) {
        this.socioFlujoCajaIngresoCollection = socioFlujoCajaIngresoCollection;
    }

    @XmlTransient
    public Collection<SocioFlujoCajaEgreso> getSocioFlujoCajaEgresoCollection() {
        return socioFlujoCajaEgresoCollection;
    }

    public void setSocioFlujoCajaEgresoCollection(Collection<SocioFlujoCajaEgreso> socioFlujoCajaEgresoCollection) {
        this.socioFlujoCajaEgresoCollection = socioFlujoCajaEgresoCollection;
    }
    
    public ItemFlujoCajaMonto getItemFlujoCajaMonto() {
        return itemFlujoCajaMonto;
    }

    public void setItemFlujoCajaMonto(ItemFlujoCajaMonto itemFlujoCajaMonto) {
        this.itemFlujoCajaMonto = itemFlujoCajaMonto;
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
        if (!(object instanceof ItemFlujoCaja)) {
            return false;
        }
        ItemFlujoCaja other = (ItemFlujoCaja) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.ItemFlujoCaja[ codigo=" + codigo + " ]";
    }
    
}
