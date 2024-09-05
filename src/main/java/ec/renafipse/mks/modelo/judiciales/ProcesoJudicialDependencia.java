/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.judiciales;

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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_JUDICIALES.PROCESO_JUDICIAL_DEPENDENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoJudicialDependencia.findAll", query = "SELECT p FROM ProcesoJudicialDependencia p"),
    @NamedQuery(name = "ProcesoJudicialDependencia.findByCodigo", query = "SELECT p FROM ProcesoJudicialDependencia p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ProcesoJudicialDependencia.findByEliminado", query = "SELECT p FROM ProcesoJudicialDependencia p WHERE p.eliminado = :eliminado")})
public class ProcesoJudicialDependencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @JoinColumn(name = "CODIGO_PROCESO_JUDICIAL_DEP", referencedColumnName = "CODIGO")
    @ManyToOne
    private ProcesoJudicial codigoProcesoJudicialDep;
    @JoinColumn(name = "CODIGO_PROCESO_JUDICIAL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ProcesoJudicial codigoProcesoJudicial;

    public ProcesoJudicialDependencia() {
    }

    public ProcesoJudicialDependencia(Long codigo) {
        this.codigo = codigo;
    }

    public ProcesoJudicialDependencia(Long codigo, String eliminado) {
        this.codigo = codigo;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public ProcesoJudicial getCodigoProcesoJudicialDep() {
        return codigoProcesoJudicialDep;
    }

    public void setCodigoProcesoJudicialDep(ProcesoJudicial codigoProcesoJudicialDep) {
        this.codigoProcesoJudicialDep = codigoProcesoJudicialDep;
    }

    public ProcesoJudicial getCodigoProcesoJudicial() {
        return codigoProcesoJudicial;
    }

    public void setCodigoProcesoJudicial(ProcesoJudicial codigoProcesoJudicial) {
        this.codigoProcesoJudicial = codigoProcesoJudicial;
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
        if (!(object instanceof ProcesoJudicialDependencia)) {
            return false;
        }
        ProcesoJudicialDependencia other = (ProcesoJudicialDependencia) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.judiciales.ProcesoJudicialDependencia[ codigo=" + codigo + " ]";
    }
    
}
