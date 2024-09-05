/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.comunes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_COMUNES.MONEDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moneda.findAll", query = "SELECT m FROM Moneda m"),
    @NamedQuery(name = "Moneda.findByCodigo", query = "SELECT m FROM Moneda m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Moneda.findByNombre", query = "SELECT m FROM Moneda m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Moneda.findBySiglas", query = "SELECT m FROM Moneda m WHERE m.siglas = :siglas"),
    @NamedQuery(name = "Moneda.findByEliminado", query = "SELECT m FROM Moneda m WHERE m.eliminado = :eliminado"),
    //PERSONALIZADAS
    @NamedQuery(name = "Moneda.findByTipoProducto", query = "SELECT DISTINCT m FROM ConceptoTransaccionPro c JOIN c.producto p JOIN p.moneda m WHERE p.tipoProducto.codigo= :codigo"),
    @NamedQuery(name = "Moneda.findByProductoCreditoMoneda", query = "SELECT c FROM ProductoCreditoMonedaIfip i JOIN i.productoCreditoMoneda m JOIN m.moneda c WHERE i.productoCreditoMonedaIfipPK.codigoProducto = :codigoProducto AND i.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND i.eliminado = :eliminado"),
    @NamedQuery(name = "Moneda.findByIfipMoneda", query = "SELECT m FROM IfipMoneda i JOIN i.moneda m WHERE i.ifipMonedaPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Moneda.findByProductoCreditoM", query = "SELECT c FROM ProductoCreditoMonedaIfip i JOIN i.productoCreditoMoneda m JOIN m.moneda c WHERE i.productoCreditoMonedaIfipPK.codigoProducto = :codigoProducto AND i.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Moneda.findByMonedaIfip", query = "SELECT m FROM ProductoIfip f JOIN f.producto p JOIN p.moneda m WHERE f.productoIfipPK.codigoIfip = :codigoIfip AND f.productoIfipPK.codigoTipoProducto= :codigoTipoProducto"),
    @NamedQuery(name = "Moneda.findByTasaIntProCreMonIfi", query = "SELECT DISTINCT m FROM TasaInteresProCreMonIfi i JOIN i.productoCreditoMonedaIfip t JOIN t.productoCreditoMoneda c JOIN c.moneda m WHERE t.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND i.eliminado = :eliminado AND t.productoCreditoMonedaIfipPK.codigoProducto = :codigoProducto")

})
public class Moneda implements Serializable {

    @Basic(optional = false)
    @NotNull
    //@Lob
    @Column(name = "SIMBOLO")
    private String simbolo;
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "Moneda.findByEliminado";
    public static final String findByCodigo = "Moneda.findByCodigo";
    public static final String findByTipoProducto = "Moneda.findByTipoProducto";
    public static final String findByProductoCreditoMoneda = "Moneda.findByProductoCreditoMoneda";
    public static final String findByIfipMoneda = "Moneda.findByIfipMoneda";
    public static final String findByProductoCreditoM = "Moneda.findByProductoCreditoM";
    public static final String findByMonedaIfip = "Moneda.findByMonedaIfip";
    public static final String findByTasaIntProCreMonIfi = "Moneda.findByTasaIntProCreMonIfi";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    public Moneda() {
    }

    public Moneda(Long codigo) {
        this.codigo = codigo;
    }

    public Moneda(Long codigo, String nombre, String siglas, String simbolo, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
        this.simbolo = simbolo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moneda)) {
            return false;
        }
        Moneda other = (Moneda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.comunes.Moneda[ codigo=" + codigo + " ]";
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

}
