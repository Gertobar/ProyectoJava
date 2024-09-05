/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

import ec.renafipse.mks.modelo.reportes.ReporteDetalle;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_SEGURIDADES.ROL_REPORTE_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolReporteDetalle.findAll", query = "SELECT r FROM RolReporteDetalle r"),
    @NamedQuery(name = "RolReporteDetalle.findByCodigoRol", query = "SELECT r FROM RolReporteDetalle r WHERE r.rolReporteDetallePK.codigoRol = :codigoRol"),
    @NamedQuery(name = "RolReporteDetalle.findByCodigoReporte", query = "SELECT r FROM RolReporteDetalle r WHERE r.rolReporteDetallePK.codigoReporte = :codigoReporte"),
    @NamedQuery(name = "RolReporteDetalle.findByEliminado", query = "SELECT r FROM RolReporteDetalle r WHERE r.eliminado = :eliminado"),
    //PERSONALIZADOS
    @NamedQuery(name = "RolReporteDetalle.findByReporteDetalleRolGrupoEliminado", query = "SELECT d FROM RolReporteDetalle r JOIN r.reporteDetalle d WHERE r.rolReporteDetallePK.codigoRol = :codigoRol AND d.codigoGrupo.codigo = :codigoGrupo AND r.eliminado = :eliminado AND d.eliminado = :eliminado ORDER BY d.codigo"),
    @NamedQuery(name = "RolReporteDetalle.findByReporteRolGrupoEliminado", query = "SELECT DISTINCT g FROM RolReporteDetalle r JOIN r.reporteDetalle d JOIN d.codigoGrupo g WHERE r.rolReporteDetallePK.codigoRol = :codigoRol  AND r.eliminado = :eliminado AND d.eliminado = :eliminado AND g.eliminado =:eliminado ORDER BY g.codigo")
})
public class RolReporteDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByReporteDetalleRolGrupoEliminado = "RolReporteDetalle.findByReporteDetalleRolGrupoEliminado";
    public static final String findByReporteRolGrupoEliminado = "RolReporteDetalle.findByReporteRolGrupoEliminado";
    @EmbeddedId
    protected RolReporteDetallePK rolReporteDetallePK;
    
    @JoinColumn(name = "CODIGO_REPORTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ReporteDetalle reporteDetalle;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    public RolReporteDetalle() {
    }

    public RolReporteDetalle(RolReporteDetallePK rolReporteDetallePK) {
        this.rolReporteDetallePK = rolReporteDetallePK;
    }

    public RolReporteDetalle(RolReporteDetallePK rolReporteDetallePK, char eliminado) {
        this.rolReporteDetallePK = rolReporteDetallePK;
        this.eliminado = eliminado;
    }

    public RolReporteDetalle(String codigoRol, long codigoReporte) {
        this.rolReporteDetallePK = new RolReporteDetallePK(codigoRol, codigoReporte);
    }

    public RolReporteDetallePK getRolReporteDetallePK() {
        return rolReporteDetallePK;
    }

    public void setRolReporteDetallePK(RolReporteDetallePK rolReporteDetallePK) {
        this.rolReporteDetallePK = rolReporteDetallePK;
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
        hash += (rolReporteDetallePK != null ? rolReporteDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolReporteDetalle)) {
            return false;
        }
        RolReporteDetalle other = (RolReporteDetalle) object;
        if ((this.rolReporteDetallePK == null && other.rolReporteDetallePK != null) || (this.rolReporteDetallePK != null && !this.rolReporteDetallePK.equals(other.rolReporteDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.RolReporteDetalle[ rolReporteDetallePK=" + rolReporteDetallePK + " ]";
    }

    /**
     * @return the reporteDetalle
     */
    public ReporteDetalle getReporteDetalle() {
        return reporteDetalle;
    }

    /**
     * @param reporteDetalle the reporteDetalle to set
     */
    public void setReporteDetalle(ReporteDetalle reporteDetalle) {
        this.reporteDetalle = reporteDetalle;
    }
    
}
