/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.reportes;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_REPORTES.REPORTE_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteDetalle.findAll", query = "SELECT r FROM ReporteDetalle r"),
    @NamedQuery(name = "ReporteDetalle.findByCodigo", query = "SELECT r FROM ReporteDetalle r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "ReporteDetalle.findByNombre", query = "SELECT r FROM ReporteDetalle r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "ReporteDetalle.findByFormatoFechaHora", query = "SELECT r FROM ReporteDetalle r WHERE r.formatoFechaHora = :formatoFechaHora"),
    @NamedQuery(name = "ReporteDetalle.findByFormatoDecimales", query = "SELECT r FROM ReporteDetalle r WHERE r.formatoDecimales = :formatoDecimales"),
    @NamedQuery(name = "ReporteDetalle.findByEliminado", query = "SELECT r FROM ReporteDetalle r WHERE r.eliminado = :eliminado")})
public class ReporteDetalle implements Serializable {
    private static final Long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "CONSULTA")
    private String consulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMATO_FECHA_HORA")
    private char formatoFechaHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMATO_DECIMALES")
    private char formatoDecimales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXCEL")
    private char excel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PDF")
    private char pdf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_EXCEL")
    private String nombreExcel;
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_METODO_LLAMAR")
    private String nombreMetodoLlamar;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoReporte", fetch = FetchType.LAZY)
    private Collection<ParametroEntradaReporte> parametroEntradaReporteCollection;
    @JoinColumn(name = "CODIGO_GRUPO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ReporteGrupo codigoGrupo;

    public ReporteDetalle() {
    }

    public ReporteDetalle(Long codigo) {
        this.codigo = codigo;
    }

    public ReporteDetalle(Long codigo, String nombre, String consulta, char formatoFechaHora, char formatoDecimales, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.consulta = consulta;
        this.formatoFechaHora = formatoFechaHora;
        this.formatoDecimales = formatoDecimales;
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

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public char getFormatoFechaHora() {
        return formatoFechaHora;
    }

    public void setFormatoFechaHora(char formatoFechaHora) {
        this.formatoFechaHora = formatoFechaHora;
    }

    public char getFormatoDecimales() {
        return formatoDecimales;
    }

    public void setFormatoDecimales(char formatoDecimales) {
        this.formatoDecimales = formatoDecimales;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<ParametroEntradaReporte> getParametroEntradaReporteCollection() {
        return parametroEntradaReporteCollection;
    }

    public void setParametroEntradaReporteCollection(Collection<ParametroEntradaReporte> parametroEntradaReporteCollection) {
        this.parametroEntradaReporteCollection = parametroEntradaReporteCollection;
    }

    public ReporteGrupo getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(ReporteGrupo codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
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
        if (!(object instanceof ReporteDetalle)) {
            return false;
        }
        ReporteDetalle other = (ReporteDetalle) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.reportes.ReporteDetalle[ codigo=" + codigo + " ]";
    }

    /**
     * @return the excel
     */
    public char getExcel() {
        return excel;
    }

    /**
     * @param excel the excel to set
     */
    public void setExcel(char excel) {
        this.excel = excel;
    }

    /**
     * @return the pdf
     */
    public char getPdf() {
        return pdf;
    }

    /**
     * @param pdf the pdf to set
     */
    public void setPdf(char pdf) {
        this.pdf = pdf;
    }

    /**
     * @return the nombreExcel
     */
    public String getNombreExcel() {
        return nombreExcel;
    }

    /**
     * @param nombreExcel the nombreExcel to set
     */
    public void setNombreExcel(String nombreExcel) {
        this.nombreExcel = nombreExcel;
    }

    /**
     * @return the nombreMetodoLlamar
     */
    public String getNombreMetodoLlamar() {
        return nombreMetodoLlamar;
    }

    /**
     * @param nombreMetodoLlamar the nombreMetodoLlamar to set
     */
    public void setNombreMetodoLlamar(String nombreMetodoLlamar) {
        this.nombreMetodoLlamar = nombreMetodoLlamar;
    }
    
}
