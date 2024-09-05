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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_COMUNES.FORMATO_ENTRADA_ARC_DET_COM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormatoEntradaArcDetCom.findByCodigoFormato", query = "SELECT f FROM FormatoEntradaArcDetCom f WHERE f.codigoFormatoEntArc.codigo = :codigoFormato"),
    @NamedQuery(name = "FormatoEntradaArcDetCom.findByNombreFormato", query = "SELECT f FROM FormatoEntradaArcDetCom f WHERE f.codigoFormatoEntArc.nombre = :nombreFormato")
})
public class FormatoEntradaArcDetCom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @JoinColumn(name = "CODIGO_FORMATO_ENT_ARC", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private FormatoEntradaArchivo codigoFormatoEntArc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN_ARCHIVO")
    private int ordenArchivo;
    @JoinColumn(name = "NOMBRE_FORMATO_ENT_ARC", referencedColumnName = "NOMBRE")
    @ManyToOne(optional = false)
    private FormatoEntradaArchivo nombreFormatoEntArc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_MD5")
    private char esMd5;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_RETORNO")
    private char esRetorno;

    public FormatoEntradaArcDetCom() {
    }

    public FormatoEntradaArcDetCom(Long codigo) {
        this.codigo = codigo;
    }

    public FormatoEntradaArcDetCom(Long codigo, FormatoEntradaArchivo codigoFormatoEntArc, int ordenArchivo, FormatoEntradaArchivo nombreFormatoEntArc, char esMd5, char esRetorno) {
        this.codigo = codigo;
        this.codigoFormatoEntArc = codigoFormatoEntArc;
        this.ordenArchivo = ordenArchivo;
        this.nombreFormatoEntArc = nombreFormatoEntArc;
        this.esMd5 = esMd5;
        this.esRetorno = esRetorno;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public FormatoEntradaArchivo getCodigoFormatoEntArc() {
        return codigoFormatoEntArc;
    }

    public void setCodigoFormatoEntArc(FormatoEntradaArchivo codigoFormatoEntArc) {
        this.codigoFormatoEntArc = codigoFormatoEntArc;
    }

    public int getOrdenArchivo() {
        return ordenArchivo;
    }

    public void setOrdenArchivo(int ordenArchivo) {
        this.ordenArchivo = ordenArchivo;
    }

    public FormatoEntradaArchivo getNombreFormatoEntArc() {
        return nombreFormatoEntArc;
    }

    public void setNombreFormatoEntArc(FormatoEntradaArchivo nombreFormatoEntArc) {
        this.nombreFormatoEntArc = nombreFormatoEntArc;
    }

    public char getEsMd5() {
        return esMd5;
    }

    public void setEsMd5(char esMd5) {
        this.esMd5 = esMd5;
    }

    public char getEsRetorno() {
        return esRetorno;
    }

    public void setEsRetorno(char esRetorno) {
        this.esRetorno = esRetorno;
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
        if (!(object instanceof FormatoEntradaArcDetCom)) {
            return false;
        }
        FormatoEntradaArcDetCom other = (FormatoEntradaArcDetCom) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.comunes.FormatoEntradaArcDetCom[ codigo=" + codigo + " ]";
    }
    
}
