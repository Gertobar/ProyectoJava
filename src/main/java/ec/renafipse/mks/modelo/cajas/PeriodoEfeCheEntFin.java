/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.PERIODO_EFE_CHE_ENT_FIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoEfeCheEntFin.findAll", query = "SELECT p FROM PeriodoEfeCheEntFin p"),
    @NamedQuery(name = "PeriodoEfeCheEntFin.findByCodigoEntidadFinanciera", query = "SELECT p FROM PeriodoEfeCheEntFin p WHERE p.codigoEntidadFinanciera = :codigoEntidadFinanciera"),
    @NamedQuery(name = "PeriodoEfeCheEntFin.findByRegistradoPor", query = "SELECT p FROM PeriodoEfeCheEntFin p WHERE p.registradoPor = :registradoPor"),
    @NamedQuery(name = "PeriodoEfeCheEntFin.findByFechaRegistro", query = "SELECT p FROM PeriodoEfeCheEntFin p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "PeriodoEfeCheEntFin.findByEliminado", query = "SELECT p FROM PeriodoEfeCheEntFin p WHERE p.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "PeriodoEfeCheEntFin.findByEntidadFinancieraIfipEliminado", query = "SELECT e  FROM PeriodoEfeCheEntFin p JOIN p.entidadFinanciera e WHERE p.codigoIfip = :codigoIfip AND  p.eliminado = :eliminado AND e.eliminado = :eliminado"),
    @NamedQuery(name = "PeriodoEfeCheEntFin.findByPeriodicidadEntFinIfipEliminado", query = "SELECT per FROM PeriodoEfeCheEntFin p JOIN p.entidadFinanciera e JOIN p.codigoPeriodo pe JOIN pe.periodicidad per WHERE e.codigo = :codigoEntidadFinanciera AND p.codigoIfip = :codigoIfip AND p.eliminado = :eliminado AND e.eliminado = :eliminado AND pe.eliminado = :eliminado AND per.eliminado = :eliminado")
})
public class PeriodoEfeCheEntFin implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEntidadFinancieraIfipEliminado = "PeriodoEfeCheEntFin.findByEntidadFinancieraIfipEliminado";
    public static final String findByPeriodicidadEntFinIfipEliminado = "PeriodoEfeCheEntFin.findByPeriodicidadEntFinIfipEliminado";
    @Id    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENTIDAD_FINANCIERA")
    private Long codigoEntidadFinanciera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @JoinColumn(name = "CODIGO_PERIODO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private PeriodoEfectivizacionCheque codigoPeriodo;
    @JoinColumn(name = "CODIGO_ENTIDAD_FINANCIERA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EntidadFinanciera entidadFinanciera;
    public PeriodoEfeCheEntFin() {
    }

    public PeriodoEfeCheEntFin(Long codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
    }

    public PeriodoEfeCheEntFin(Long codigoEntidadFinanciera, long registradoPor, Date fechaRegistro, char eliminado) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public Long getCodigoEntidadFinanciera() {
        return codigoEntidadFinanciera;
    }

    public void setCodigoEntidadFinanciera(Long codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public PeriodoEfectivizacionCheque getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(PeriodoEfectivizacionCheque codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEntidadFinanciera != null ? codigoEntidadFinanciera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoEfeCheEntFin)) {
            return false;
        }
        PeriodoEfeCheEntFin other = (PeriodoEfeCheEntFin) object;
        if ((this.codigoEntidadFinanciera == null && other.codigoEntidadFinanciera != null) || (this.codigoEntidadFinanciera != null && !this.codigoEntidadFinanciera.equals(other.codigoEntidadFinanciera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.PeriodoEfeCheEntFin[ codigoEntidadFinanciera=" + codigoEntidadFinanciera + " ]";
    }

    /**
     * @return the entidadFinanciera
     */
    public EntidadFinanciera getEntidadFinanciera() {
        return entidadFinanciera;
    }

    /**
     * @param entidadFinanciera the entidadFinanciera to set
     */
    public void setEntidadFinanciera(EntidadFinanciera entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    /**
     * @return the codigoIfip
     */
    public long getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }
    
}
