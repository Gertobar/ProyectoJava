/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "MKS_SEGURIDADES.CARACTER_NOACPETADO_CONTRASENA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaracterAcpetadoContrasena.findAll", query = "SELECT c FROM CaracterNoAcpetadoContrasena c"),
    @NamedQuery(name = "CaracterAcpetadoContrasena.findByCaracter", query = "SELECT c FROM CaracterNoAcpetadoContrasena c WHERE c.caracter = :caracter"),
    @NamedQuery(name = "CaracterAcpetadoContrasena.findByEliminado", query = "SELECT c FROM CaracterNoAcpetadoContrasena c WHERE c.eliminado = :eliminado")})
public class CaracterNoAcpetadoContrasena implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "CaracterAcpetadoContrasena.findByEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CARACTER")
    private String caracter;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    public CaracterNoAcpetadoContrasena() {
    }

    public CaracterNoAcpetadoContrasena(String caracter) {
        this.caracter = caracter;
    }

    public CaracterNoAcpetadoContrasena(String caracter, char eliminado) {
        this.caracter = caracter;
        this.eliminado = eliminado;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
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
        hash += (caracter != null ? caracter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaracterNoAcpetadoContrasena)) {
            return false;
        }
        CaracterNoAcpetadoContrasena other = (CaracterNoAcpetadoContrasena) object;
        if ((this.caracter == null && other.caracter != null) || (this.caracter != null && !this.caracter.equals(other.caracter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.CaracterAcpetadoContrasena[ caracter=" + caracter + " ]";
    }
    
}
