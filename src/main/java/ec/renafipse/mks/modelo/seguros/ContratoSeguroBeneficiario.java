/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguros;

import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.TipoRelacion;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author scordero
 */
@Entity
@Table(name = "MKS_SEGUROS.CONTRATO_SEGURO_BENEFICIARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoSeguroBeneficiario.findAll", query = "SELECT c FROM ContratoSeguroBeneficiario c"),
    @NamedQuery(name = "ContratoSeguroBeneficiario.findByCodigoSeguro", query = "SELECT c FROM ContratoSeguroBeneficiario c WHERE c.contratoSeguroBeneficiarioPK.codigoSeguro = :codigoSeguro"),
    @NamedQuery(name = "ContratoSeguroBeneficiario.findByCodigoPersona", query = "SELECT c FROM ContratoSeguroBeneficiario c WHERE c.contratoSeguroBeneficiarioPK.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "ContratoSeguroBeneficiario.findByCodigoTipoRelacion", query = "SELECT c FROM ContratoSeguroBeneficiario c WHERE c.codigoTipoRelacion = :codigoTipoRelacion"),
    @NamedQuery(name = "ContratoSeguroBeneficiario.findByPorcentaje", query = "SELECT c FROM ContratoSeguroBeneficiario c WHERE c.porcentaje = :porcentaje"),
    @NamedQuery(name = "ContratoSeguroBeneficiario.findByEliminado", query = "SELECT c FROM ContratoSeguroBeneficiario c WHERE c.eliminado = :eliminado"),
//PERSONALIZADOS
    @NamedQuery(name = "ContratoSeguroBeneficiario.findByCodigoSeguroEliminado", query = "SELECT c FROM ContratoSeguroBeneficiario c WHERE c.contratoSeguro.codigo = :codigoSeguro AND c.eliminado = :eliminado")
})
public class ContratoSeguroBeneficiario implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoSeguroEliminado = "ContratoSeguroBeneficiario.findByCodigoSeguroEliminado";
    
    @EmbeddedId
    protected ContratoSeguroBeneficiarioPK contratoSeguroBeneficiarioPK;
    @Column(name = "CODIGO_TIPO_RELACION")
    private Long codigoTipoRelacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PORCENTAJE")
    private BigDecimal porcentaje;
    @Column(name = "ELIMINADO")
    private Character eliminado;
    @JoinColumn(name = "CODIGO_SEGURO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ContratoSeguro contratoSeguro;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Persona beneficiario;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_RELACION", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TipoRelacion tipoRelacion;

    public ContratoSeguroBeneficiario() {
    }

    public ContratoSeguroBeneficiario(ContratoSeguroBeneficiarioPK contratoSeguroBeneficiarioPK) {
        this.contratoSeguroBeneficiarioPK = contratoSeguroBeneficiarioPK;
    }

    public ContratoSeguroBeneficiario(long codigoSeguro, long codigoPersona) {
        this.contratoSeguroBeneficiarioPK = new ContratoSeguroBeneficiarioPK(codigoSeguro, codigoPersona);
    }

    public ContratoSeguroBeneficiarioPK getContratoSeguroBeneficiarioPK() {
        return contratoSeguroBeneficiarioPK;
    }

    public void setContratoSeguroBeneficiarioPK(ContratoSeguroBeneficiarioPK contratoSeguroBeneficiarioPK) {
        this.contratoSeguroBeneficiarioPK = contratoSeguroBeneficiarioPK;
    }

    public Long getCodigoTipoRelacion() {
        return codigoTipoRelacion;
    }

    public void setCodigoTipoRelacion(Long codigoTipoRelacion) {
        this.codigoTipoRelacion = codigoTipoRelacion;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Character getEliminado() {
        return eliminado;
    }

    public void setEliminado(Character eliminado) {
        this.eliminado = eliminado;
    }

    public ContratoSeguro getContratoSeguro() {
        return contratoSeguro;
    }

    public void setContratoSeguro(ContratoSeguro contratoSeguro) {
        this.contratoSeguro = contratoSeguro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratoSeguroBeneficiarioPK != null ? contratoSeguroBeneficiarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoSeguroBeneficiario)) {
            return false;
        }
        ContratoSeguroBeneficiario other = (ContratoSeguroBeneficiario) object;
        if ((this.contratoSeguroBeneficiarioPK == null && other.contratoSeguroBeneficiarioPK != null) || (this.contratoSeguroBeneficiarioPK != null && !this.contratoSeguroBeneficiarioPK.equals(other.contratoSeguroBeneficiarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguros.ContratoSeguroBeneficiario[ contratoSeguroBeneficiarioPK=" + contratoSeguroBeneficiarioPK + " ]";
    }

    /**
     * @return the beneficiario
     */
    public Persona getBeneficiario() {
        return beneficiario;
    }

    /**
     * @param beneficiario the beneficiario to set
     */
    public void setBeneficiario(Persona beneficiario) {
        this.beneficiario = beneficiario;
    }

    /**
     * @return the tipoRelacion
     */
    public TipoRelacion getTipoRelacion() {
        return tipoRelacion;
    }

    /**
     * @param tipoRelacion the tipoRelacion to set
     */
    public void setTipoRelacion(TipoRelacion tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }
    
}
