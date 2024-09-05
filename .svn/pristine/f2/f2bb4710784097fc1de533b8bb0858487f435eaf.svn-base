/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

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
@Table(name = "MKS_SEGURIDADES.MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findByCodigo", query = "SELECT m FROM Menu m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Menu.findByNombre", query = "SELECT m FROM Menu m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Menu.findByAplicacion", query = "SELECT m FROM Menu m WHERE m.aplicacion = :aplicacion"),
    @NamedQuery(name = "Menu.findByIcono", query = "SELECT m FROM Menu m WHERE m.icono = :icono"),
    @NamedQuery(name = "Menu.findByTitulo", query = "SELECT m FROM Menu m WHERE m.titulo = :titulo"),
    @NamedQuery(name = "Menu.findByOrden", query = "SELECT m FROM Menu m WHERE m.orden = :orden"),
    @NamedQuery(name = "Menu.findByTipo", query = "SELECT m FROM Menu m WHERE m.tipo = :tipo AND m.codigoSistema.codigo = :codigoSistema"),
    @NamedQuery(name = "Menu.findByEsDeRenafipse", query = "SELECT m FROM Menu m WHERE m.esDeRenafipse = :esDeRenafipse"),
    @NamedQuery(name = "Menu.findByDaPermisoRenafipse", query = "SELECT m FROM Menu m WHERE m.daPermisoRenafipse = :daPermisoRenafipse"),
    @NamedQuery(name = "Menu.findByEliminado", query = "SELECT m FROM Menu m WHERE m.eliminado = :eliminado"),
    @NamedQuery(name = "Menu.findBySistema", query = "SELECT m FROM Menu m WHERE m.codigoSistema = :sistema ORDER BY m.orden"),
    // Personalizados
    @NamedQuery(name = "Menu.findByModulo", query = "SELECT m FROM Menu m WHERE m.tipo = :tipo AND m.eliminado = :eliminado AND m.codigoMenuPadre = :codigoMenuPadre ORDER BY m.orden"),
    @NamedQuery(name = "Menu.findByOpcionPorModulo", query = "SELECT m FROM Menu m JOIN m.codigoSistema s WHERE m.tipo = :tipo  AND m.eliminado = :eliminado AND s.codigo= :codigoSistema ORDER BY m.orden"),
    @NamedQuery(name = "Menu.findByOpcionPorMenu", query = "SELECT m FROM Menu m JOIN m.codigoSistema s WHERE m.tipo = :tipo  AND m.eliminado = :eliminado AND s.codigo= :codigoSistema AND m.codigo = :codigo ORDER BY m.orden"),
    @NamedQuery(name = "Menu.findByCodigoPadre", query = "SELECT m FROM Menu m WHERE m.codigoMenuPadre = :codigoMenuPadre AND m.eliminado = :eliminado ORDER BY m.nombre")
})
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findBySistema = "Menu.findBySistema";
    public static final String findByModulo = "Menu.findByModulo";
    public static final String findByOpcionPorModulo = "Menu.findByOpcionPorModulo";
    public static final String findByOpcionPorMenu = "Menu.findByOpcionPorMenu";
    public static final String findByCodigoPadre = "Menu.findByCodigoPadre";
    public static final String findByCodigo = "Menu.findByCodigo";
    public static final String findByTipo = "Menu.findByTipo";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_MENU")
    @SequenceGenerator(name = "GSQ_MENU", schema = "MKS_SEGURIDADES", allocationSize = 0, sequenceName = "SEQ_MENU")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 50)
    @Column(name = "APLICACION")
    private String aplicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ICONO")
    private String icono;
    @Size(max = 50)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN")
    private short orden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_DE_RENAFIPSE")
    private char esDeRenafipse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DA_PERMISO_RENAFIPSE")
    private char daPermisoRenafipse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Size(max = 50)
    @Column(name = "CONTROLADOR")
    private String controlador;

    @JoinColumn(name = "CODIGO_SISTEMA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Sistema codigoSistema;
    @OneToMany(mappedBy = "codigoMenuPadre")
    private Collection<Menu> menuCollection;
    @JoinColumn(name = "CODIGO_MENU_PADRE", referencedColumnName = "CODIGO")
    @ManyToOne
    private Menu codigoMenuPadre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMenu", fetch = FetchType.LAZY)
    private Collection<OpcionOperacion> opcionOperacionCollection;

    public Menu() {
    }

    public Menu(Long codigo) {
        this.codigo = codigo;
    }

    public Menu(Long codigo, String nombre, String icono, short orden, char tipo, char esDeRenafipse, char daPermisoRenafipse, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.icono = icono;
        this.orden = orden;
        this.tipo = tipo;
        this.esDeRenafipse = esDeRenafipse;
        this.daPermisoRenafipse = daPermisoRenafipse;
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

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public short getOrden() {
        return orden;
    }

    public void setOrden(short orden) {
        this.orden = orden;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public char getEsDeRenafipse() {
        return esDeRenafipse;
    }

    public void setEsDeRenafipse(char esDeRenafipse) {
        this.esDeRenafipse = esDeRenafipse;
    }

    public char getDaPermisoRenafipse() {
        return daPermisoRenafipse;
    }

    public void setDaPermisoRenafipse(char daPermisoRenafipse) {
        this.daPermisoRenafipse = daPermisoRenafipse;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Sistema getCodigoSistema() {
        return codigoSistema;
    }

    public void setCodigoSistema(Sistema codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    public Menu getCodigoMenuPadre() {
        return codigoMenuPadre;
    }

    public void setCodigoMenuPadre(Menu codigoMenuPadre) {
        this.codigoMenuPadre = codigoMenuPadre;
    }

    @XmlTransient
    public Collection<OpcionOperacion> getOpcionOperacionCollection() {
        return opcionOperacionCollection;
    }

    public void setOpcionOperacionCollection(Collection<OpcionOperacion> opcionOperacionCollection) {
        this.opcionOperacionCollection = opcionOperacionCollection;
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
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mksseguridades.control.Menu[ codigo=" + codigo + " ]";
    }

    /**
     * @return the Controlador
     */
    public String getControlador() {
        return controlador;
    }

    /**
     * @param Controlador the Controlador to set
     */
    public void setControlador(String Controlador) {
        this.controlador = Controlador;
    }
}
