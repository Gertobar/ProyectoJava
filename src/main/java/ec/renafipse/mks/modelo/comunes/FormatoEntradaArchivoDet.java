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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_COMUNES.FORMATO_ENTRADA_ARCHIVO_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormatoEntradaArchivoDet.findByCodigoFormato", query = "SELECT f FROM FormatoEntradaArchivoDet f WHERE f.codigoFormatoEntArc.codigo = :codigoFormato ORDER BY f.ordenColumna"),
    @NamedQuery(name = "FormatoEntradaArchivoDet.findByNombreFormato", query = "SELECT f FROM FormatoEntradaArchivoDet f WHERE f.codigoFormatoEntArc.nombre = :nombreFormato ORDER BY f.ordenColumna")})
public class FormatoEntradaArchivoDet implements Serializable {
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
    @Column(name = "ORDEN_COLUMNA")
    private int ordenColumna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NOMBRE_COLUMNA")
    private String nombreColumna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "TIPO_DATO_COLUMNA")
    private String tipoDatoColumna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_OBLIGATORIO")
    private char esObligatorio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NOMBRE_VALIDACION")
    private String nombreValidacion;

    public FormatoEntradaArchivoDet() {
    }

    public FormatoEntradaArchivoDet(Long codigo) {
        this.codigo = codigo;
    }

    public FormatoEntradaArchivoDet(Long codigo, FormatoEntradaArchivo codigoFormatoEntArc, int ordenColumna, String nombreColumna, String tipoDatoColumna, char esObligatorio, String nombreValidacion) {
        this.codigo = codigo;
        this.codigoFormatoEntArc = codigoFormatoEntArc;
        this.ordenColumna = ordenColumna;
        this.nombreColumna = nombreColumna;
        this.tipoDatoColumna = tipoDatoColumna;
        this.esObligatorio = esObligatorio;
        this.nombreValidacion = nombreValidacion;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public int getOrdenColumna() {
        return ordenColumna;
    }

    public void setOrdenColumna(int ordenColumna) {
        this.ordenColumna = ordenColumna;
    }

    public String getNombreColumna() {
        return nombreColumna;
    }

    public void setNombreColumna(String nombreColumna) {
        this.nombreColumna = nombreColumna;
    }

    public String getTipoDatoColumna() {
        return tipoDatoColumna;
    }

    public void setTipoDatoColumna(String tipoDatoColumna) {
        this.tipoDatoColumna = tipoDatoColumna;
    }

    public char getEsObligatorio() {
        return esObligatorio;
    }

    public void setEsObligatorio(char esObligatorio) {
        this.esObligatorio = esObligatorio;
    }

    public String getNombreValidacion() {
        return nombreValidacion;
    }

    public void setNombreValidacion(String nombreValidacion) {
        this.nombreValidacion = nombreValidacion;
    }

    public FormatoEntradaArchivo getCodigoFormatoEntArc() {
        return codigoFormatoEntArc;
    }

    public void setCodigoFormatoEntArc(FormatoEntradaArchivo codigoFormatoEntArc) {
        this.codigoFormatoEntArc = codigoFormatoEntArc;
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
        if (!(object instanceof FormatoEntradaArchivoDet)) {
            return false;
        }
        FormatoEntradaArchivoDet other = (FormatoEntradaArchivoDet) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivoDet[ codigo=" + codigo + " ]";
    }
    
}
