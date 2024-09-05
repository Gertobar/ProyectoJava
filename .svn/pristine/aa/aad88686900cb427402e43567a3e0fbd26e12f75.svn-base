/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.judiciales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_JUDICIALES.DETALLE_COSTO_JUDICIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCostoJudicial.findAll", query = "SELECT d FROM DetalleCostoJudicial d"),
    @NamedQuery(name = "DetalleCostoJudicial.findByCodigo", query = "SELECT d FROM DetalleCostoJudicial d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "DetalleCostoJudicial.findByFechaIngreso", query = "SELECT d FROM DetalleCostoJudicial d WHERE d.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "DetalleCostoJudicial.findByValor", query = "SELECT d FROM DetalleCostoJudicial d WHERE d.valor = :valor"),
    @NamedQuery(name = "DetalleCostoJudicial.findByEliminado", query = "SELECT d FROM DetalleCostoJudicial d WHERE d.eliminado = :eliminado")})
public class DetalleCostoJudicial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @JoinColumn(name = "CODIGO_PROCESO_JUDICIAL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ProcesoJudicial codigoProcesoJudicial;
    @JoinColumn(name = "CODIGO_CABECERA_COSTO_JUDICIAL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private CabeceraCostoJudicial codigoCabeceraCostoJudicial;

    public DetalleCostoJudicial() {
    }

    public DetalleCostoJudicial(Long codigo) {
        this.codigo = codigo;
    }

    public DetalleCostoJudicial(Long codigo, Date fechaIngreso, BigDecimal valor, String eliminado) {
        this.codigo = codigo;
        this.fechaIngreso = fechaIngreso;
        this.valor = valor;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public ProcesoJudicial getCodigoProcesoJudicial() {
        return codigoProcesoJudicial;
    }

    public void setCodigoProcesoJudicial(ProcesoJudicial codigoProcesoJudicial) {
        this.codigoProcesoJudicial = codigoProcesoJudicial;
    }

    public CabeceraCostoJudicial getCodigoCabeceraCostoJudicial() {
        return codigoCabeceraCostoJudicial;
    }

    public void setCodigoCabeceraCostoJudicial(CabeceraCostoJudicial codigoCabeceraCostoJudicial) {
        this.codigoCabeceraCostoJudicial = codigoCabeceraCostoJudicial;
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
        if (!(object instanceof DetalleCostoJudicial)) {
            return false;
        }
        DetalleCostoJudicial other = (DetalleCostoJudicial) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.judiciales.DetalleCostoJudicial[ codigo=" + codigo + " ]";
    }
    
}
