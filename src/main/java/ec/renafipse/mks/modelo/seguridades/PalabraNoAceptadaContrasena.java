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
@Table(name = "MKS_SEGURIDADES.PALABRA_NO_ACEPTADA_CONTRASENA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PalabraNoAceptadaContrasena.findAll", query = "SELECT p FROM PalabraNoAceptadaContrasena p"),
    @NamedQuery(name = "PalabraNoAceptadaContrasena.findByPalabra", query = "SELECT p FROM PalabraNoAceptadaContrasena p WHERE p.palabra = :palabra"),
    @NamedQuery(name = "PalabraNoAceptadaContrasena.findByEliminado", query = "SELECT p FROM PalabraNoAceptadaContrasena p WHERE p.eliminado = :eliminado")})
public class PalabraNoAceptadaContrasena implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "PalabraNoAceptadaContrasena.findByEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PALABRA")
    private String palabra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    public PalabraNoAceptadaContrasena() {
    }

    public PalabraNoAceptadaContrasena(String palabra) {
        this.palabra = palabra;
    }

    public PalabraNoAceptadaContrasena(String palabra, char eliminado) {
        this.palabra = palabra;
        this.eliminado = eliminado;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
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
        hash += (palabra != null ? palabra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PalabraNoAceptadaContrasena)) {
            return false;
        }
        PalabraNoAceptadaContrasena other = (PalabraNoAceptadaContrasena) object;
        if ((this.palabra == null && other.palabra != null) || (this.palabra != null && !this.palabra.equals(other.palabra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.PalabraNoAceptadaContrasena[ palabra=" + palabra + " ]";
    }
    
}
