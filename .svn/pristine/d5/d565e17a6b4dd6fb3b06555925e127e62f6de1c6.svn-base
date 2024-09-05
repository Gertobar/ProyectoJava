/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.TIPO_PER_TIPO_IDE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPerTipoIde.findAll", query = "SELECT t FROM TipoPerTipoIde t"),
    @NamedQuery(name = "TipoPerTipoIde.findByCodigoTipoPersona", query = "SELECT t FROM TipoPerTipoIde t WHERE t.tipoPerTipoIdePK.codigoTipoPersona = :codigoTipoPersona"),
    @NamedQuery(name = "TipoPerTipoIde.findByCodigoTipoIdentificacion", query = "SELECT t FROM TipoPerTipoIde t WHERE t.tipoPerTipoIdePK.codigoTipoIdentificacion = :codigoTipoIdentificacion"),
    @NamedQuery(name = "TipoPerTipoIde.findByEliminado", query = "SELECT t FROM TipoPerTipoIde t WHERE t.eliminado = :eliminado"),
    // ---------------------------------------------------------------------------------
    // PERSONALIZADOS
    //
    @NamedQuery(name = "TipoPerTipoIde.findByTipoPersona", query = "SELECT i FROM TipoPerTipoIde t  JOIN t.tipoIdentificacion i WHERE  t.tipoPerTipoIdePK.codigoTipoPersona = :codigoTipoPersona  AND t.eliminado = :eliminado AND i.eliminado = :eliminadoTipoIde AND t.tipoPersona.eliminado = :eliminadoTipoPer ORDER BY i.nombre")
    
})
public class TipoPerTipoIde implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByTipoPersona = "TipoPerTipoIde.findByTipoPersona";
    @EmbeddedId
    protected TipoPerTipoIdePK tipoPerTipoIdePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TIPO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoPersona tipoPersona;
    @JoinColumn(name = "CODIGO_TIPO_IDENTIFICACION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoIdentificacion tipoIdentificacion;

    public TipoPerTipoIde() {
    }

    public TipoPerTipoIde(TipoPerTipoIdePK tipoPerTipoIdePK) {
        this.tipoPerTipoIdePK = tipoPerTipoIdePK;
    }

    public TipoPerTipoIde(TipoPerTipoIdePK tipoPerTipoIdePK, char eliminado) {
        this.tipoPerTipoIdePK = tipoPerTipoIdePK;
        this.eliminado = eliminado;
    }

    public TipoPerTipoIde(long codigoTipoPersona, long codigoTipoIdentificacion) {
        this.tipoPerTipoIdePK = new TipoPerTipoIdePK(codigoTipoPersona, codigoTipoIdentificacion);
    }

    public TipoPerTipoIdePK getTipoPerTipoIdePK() {
        return tipoPerTipoIdePK;
    }

    public void setTipoPerTipoIdePK(TipoPerTipoIdePK tipoPerTipoIdePK) {
        this.tipoPerTipoIdePK = tipoPerTipoIdePK;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoPerTipoIdePK != null ? tipoPerTipoIdePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPerTipoIde)) {
            return false;
        }
        TipoPerTipoIde other = (TipoPerTipoIde) object;
        if ((this.tipoPerTipoIdePK == null && other.tipoPerTipoIdePK != null) || (this.tipoPerTipoIdePK != null && !this.tipoPerTipoIdePK.equals(other.tipoPerTipoIdePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.TipoPerTipoIde[ tipoPerTipoIdePK=" + tipoPerTipoIdePK + " ]";
    }
    
}
