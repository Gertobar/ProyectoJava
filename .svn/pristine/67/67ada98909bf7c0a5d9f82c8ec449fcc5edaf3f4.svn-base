/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.comunes;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_COMUNES.FORMATO_ENTRADA_ARCHIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormatoEntradaArchivo.findByNombre", query = "SELECT f FROM FormatoEntradaArchivo f WHERE f.nombre = :nombre")})
public class FormatoEntradaArchivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "FORMATO_CARGA")
    private String formatoCarga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_COLUMNAS_ARCHIVOS")
    private int numeroColumnasArchivos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_FILA_CABECERA")
    private long numeroFilaCabecera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_FILA_INICIAL")
    private long numeroFilaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_MAXIMO_FILAS")
    private long numeroMaximoFilas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERIFICA_CABECERA_ARCHIVO")
    private char verificaCabeceraArchivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERMITE_LINEAS_CON_ERROR")
    private char permiteLineasConError;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "SEPARADOR_COLUMNAS")
    private String separadorColumnas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SEPARADOR_DECIMALES")
    private String separadorDecimales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_MODIFICACION")
    private long codigoUsuarioModificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ULTIMA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoFormatoEntArc")
    private Collection<FormatoEntradaArchivoDet> formatoEntradaArchivoDetCollection;

    public FormatoEntradaArchivo() {
    }

    public FormatoEntradaArchivo(Long codigo) {
        this.codigo = codigo;
    }

    public FormatoEntradaArchivo(Long codigo, String nombre, String descripcion, String formatoCarga, int numeroColumnasArchivos, long numeroFilaCabecera,long numeroFilaInicial, long numeroMaximoFilas, char verificaCabeceraArchivo, char permiteLineasConError, String separadorColumnas, String separadorDecimales, long codigoUsuarioModificacion, Date fechaUltimaModificacion, String contrasena, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.formatoCarga = formatoCarga;
        this.numeroColumnasArchivos = numeroColumnasArchivos;
        this.numeroFilaCabecera = numeroFilaCabecera;
        this.numeroFilaInicial = numeroFilaInicial;
        this.numeroMaximoFilas = numeroMaximoFilas;
        this.verificaCabeceraArchivo = verificaCabeceraArchivo;
        this.permiteLineasConError = permiteLineasConError;
        this.separadorColumnas = separadorColumnas;
        this.separadorDecimales = separadorDecimales;
        this.codigoUsuarioModificacion = codigoUsuarioModificacion;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.contrasena = contrasena;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFormatoCarga() {
        return formatoCarga;
    }

    public void setFormatoCarga(String formatoCarga) {
        this.formatoCarga = formatoCarga;
    }

    public int getNumeroColumnasArchivos() {
        return numeroColumnasArchivos;
    }

    public void setNumeroColumnasArchivos(int numeroColumnasArchivos) {
        this.numeroColumnasArchivos = numeroColumnasArchivos;
    }
    
    
    public long getNumeroFilaCabecera() {
        return numeroFilaCabecera;
    }

    public void setNumeroFilaCabecera(long numeroFilaCabecera) {
        this.numeroFilaCabecera = numeroFilaCabecera;
    }

    public long getNumeroFilaInicial() {
        return numeroFilaInicial;
    }

    public void setNumeroFilaInicial(long numeroFilaInicial) {
        this.numeroFilaInicial = numeroFilaInicial;
    }

    public long getNumeroMaximoFilas() {
        return numeroMaximoFilas;
    }

    public void setNumeroMaximoFilas(long numeroMaximoFilas) {
        this.numeroMaximoFilas = numeroMaximoFilas;
    }

    public char getVerificaCabeceraArchivo() {
        return verificaCabeceraArchivo;
    }

    public void setVerificaCabeceraArchivo(char verificaCabeceraArchivo) {
        this.verificaCabeceraArchivo = verificaCabeceraArchivo;
    }

    public char getPermiteLineasConError() {
        return permiteLineasConError;
    }

    public void setPermiteLineasConError(char permiteLineasConError) {
        this.permiteLineasConError = permiteLineasConError;
    }

    public String getSeparadorColumnas() {
        return separadorColumnas;
    }

    public void setSeparadorColumnas(String separadorColumnas) {
        this.separadorColumnas = separadorColumnas;
    }

    public String getSeparadorDecimales() {
        return separadorDecimales;
    }

    public void setSeparadorDecimales(String separadorDecimales) {
        this.separadorDecimales = separadorDecimales;
    }

    public long getCodigoUsuarioModificacion() {
        return codigoUsuarioModificacion;
    }

    public void setCodigoUsuarioModificacion(long codigoUsuarioModificacion) {
        this.codigoUsuarioModificacion = codigoUsuarioModificacion;
    }

    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }
    
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<FormatoEntradaArchivoDet> getFormatoEntradaArchivoDetCollection() {
        return formatoEntradaArchivoDetCollection;
    }

    public void setFormatoEntradaArchivoDetCollection(Collection<FormatoEntradaArchivoDet> formatoEntradaArchivoDetCollection) {
        this.formatoEntradaArchivoDetCollection = formatoEntradaArchivoDetCollection;
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
        if (!(object instanceof FormatoEntradaArchivo)) {
            return false;
        }
        FormatoEntradaArchivo other = (FormatoEntradaArchivo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivo[ codigo=" + codigo + " ]";
    }
    
}
