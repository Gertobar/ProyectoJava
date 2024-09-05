/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.ifips.Ifip;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "MKS_CREDITOS.CALIFICACION_TIPO_CARTERA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalificacionTipoCartera.findAll", query = "SELECT c FROM CalificacionTipoCartera c"),
    @NamedQuery(name = "CalificacionTipoCartera.findAllOrderByTipoCartera", query = "SELECT c FROM CalificacionTipoCartera c order by c.codigoTipoCartera.nombre, c.calificacion ASC"),
    @NamedQuery(name = "CalificacionTipoCartera.findByCodigo", query = "SELECT c FROM CalificacionTipoCartera c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CalificacionTipoCartera.findByCodigoIfip", query = "SELECT c FROM CalificacionTipoCartera c WHERE c.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "CalificacionTipoCartera.findByCalificacion", query = "SELECT c FROM CalificacionTipoCartera c WHERE c.calificacion = :calificacion"),
    @NamedQuery(name = "CalificacionTipoCartera.findByDiasMoraInicial", query = "SELECT c FROM CalificacionTipoCartera c WHERE c.diasMoraInicial = :diasMoraInicial"),
    @NamedQuery(name = "CalificacionTipoCartera.findByDiasMoraFinal", query = "SELECT c FROM CalificacionTipoCartera c WHERE c.diasMoraFinal = :diasMoraFinal"),
    @NamedQuery(name = "CalificacionTipoCartera.findByProvisionDesde", query = "SELECT c FROM CalificacionTipoCartera c WHERE c.provisionDesde = :provisionDesde"),
    @NamedQuery(name = "CalificacionTipoCartera.findByProvisionHasta", query = "SELECT c FROM CalificacionTipoCartera c WHERE c.provisionHasta = :provisionHasta"),
    @NamedQuery(name = "CalificacionTipoCartera.findByValorActualProvision", query = "SELECT c FROM CalificacionTipoCartera c WHERE c.valorActualProvision = :valorActualProvision"),
    @NamedQuery(name = "CalificacionTipoCartera.findByFechaUltimaModificacion", query = "SELECT c FROM CalificacionTipoCartera c WHERE c.fechaUltimaModificacion = :fechaUltimaModificacion"),
    @NamedQuery(name = "CalificacionTipoCartera.findByIfipCarteraCalificacion", query = "SELECT c FROM CalificacionTipoCartera c WHERE c.codigoIfip.codigo = :codigoIfip AND c.codigoTipoCartera.codigo = :codigoTipoCartera AND c.calificacion = :calificacion"),
    @NamedQuery(name = "CalificacionTipoCartera.findByEliminado", query = "SELECT c FROM CalificacionTipoCartera c WHERE c.eliminado = :eliminado")})
public class CalificacionTipoCartera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_CALIFICACION_TIP_CAR")
    @SequenceGenerator(name = "GSQ_CALIFICACION_TIP_CAR", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_CALIFICACION_TIPO_CARTERA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CALIFICACION")
    private String calificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_MORA_INICIAL")
    private long diasMoraInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_MORA_FINAL")
    private long diasMoraFinal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROVISION_DESDE")
    private BigDecimal provisionDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROVISION_HASTA")
    private BigDecimal provisionHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_ACTUAL_PROVISION")
    private BigDecimal valorActualProvision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ULTIMA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TIPO_CARTERA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoCartera codigoTipoCartera;

    public CalificacionTipoCartera() {
    }

    public CalificacionTipoCartera(Long codigo) {
        this.codigo = codigo;
    }

    public CalificacionTipoCartera(Long codigo, Ifip codigoIfip, String calificacion, long diasMoraInicial, long diasMoraFinal, BigDecimal provisionDesde, BigDecimal provisionHasta, BigDecimal valorActualProvision, Date fechaUltimaModificacion, char eliminado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.calificacion = calificacion;
        this.diasMoraInicial = diasMoraInicial;
        this.diasMoraFinal = diasMoraFinal;
        this.provisionDesde = provisionDesde;
        this.provisionHasta = provisionHasta;
        this.valorActualProvision = valorActualProvision;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public long getDiasMoraInicial() {
        return diasMoraInicial;
    }

    public void setDiasMoraInicial(long diasMoraInicial) {
        this.diasMoraInicial = diasMoraInicial;
    }

    public long getDiasMoraFinal() {
        return diasMoraFinal;
    }

    public void setDiasMoraFinal(long diasMoraFinal) {
        this.diasMoraFinal = diasMoraFinal;
    }

    public BigDecimal getProvisionDesde() {
        return provisionDesde;
    }

    public void setProvisionDesde(BigDecimal provisionDesde) {
        this.provisionDesde = provisionDesde;
    }

    public BigDecimal getProvisionHasta() {
        return provisionHasta;
    }

    public void setProvisionHasta(BigDecimal provisionHasta) {
        this.provisionHasta = provisionHasta;
    }

    public BigDecimal getValorActualProvision() {
        return valorActualProvision;
    }

    public void setValorActualProvision(BigDecimal valorActualProvision) {
        this.valorActualProvision = valorActualProvision;
    }

    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public TipoCartera getCodigoTipoCartera() {
        return codigoTipoCartera;
    }

    public void setCodigoTipoCartera(TipoCartera codigoTipoCartera) {
        this.codigoTipoCartera = codigoTipoCartera;
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
        if (!(object instanceof CalificacionTipoCartera)) {
            return false;
        }
        CalificacionTipoCartera other = (CalificacionTipoCartera) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.CalificacionTipoCartera[ codigo=" + codigo + " ]";
    }
    
}
