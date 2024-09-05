/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

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
 * @author Mascoop
 */
@Entity
@Table(name = "FABRICA_PERFIL_ESTADO", catalog = "", schema = "MKS_CREDITOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FabricaPerfilEstado.findAll", query = "SELECT f FROM FabricaPerfilEstado f")
    , @NamedQuery(name = "FabricaPerfilEstado.findByCodigo", query = "SELECT f FROM FabricaPerfilEstado f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "FabricaPerfilEstado.findByVigente", query = "SELECT f FROM FabricaPerfilEstado f WHERE f.vigente = :vigente")})
public class FabricaPerfilEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_ESTADO_CREDITO")
    private String tipoEstadoCredito;
    @JoinColumn(name = "CODIGO_FABRICA_PERFIL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private FabricaPerfil codigoFabricaPerfil;
    @JoinColumn(name = "CODIGO_ESTADO_CREDITO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private EstadoCredito estadoCredito;
    

    public FabricaPerfilEstado() {
    }

    public FabricaPerfilEstado(Long codigo) {
        this.codigo = codigo;
    }

    public FabricaPerfilEstado(Long codigo, String vigente) {
        this.codigo = codigo;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public FabricaPerfil getCodigoFabricaPerfil() {
        return codigoFabricaPerfil;
    }

    public void setCodigoFabricaPerfil(FabricaPerfil codigoFabricaPerfil) {
        this.codigoFabricaPerfil = codigoFabricaPerfil;
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
        if (!(object instanceof FabricaPerfilEstado)) {
            return false;
        }
        FabricaPerfilEstado other = (FabricaPerfilEstado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.FabricaPerfilEstado[ codigo=" + codigo + " ]";
    }

    /**
     * @return the estadoCredito
     */
    public EstadoCredito getEstadoCredito() {
        return estadoCredito;
    }

    /**
     * @param estadoCredito the estadoCredito to set
     */
    public void setEstadoCredito(EstadoCredito estadoCredito) {
        this.estadoCredito = estadoCredito;
    }

    /**
     * @return the tipoEstadoCredito
     */
    public String getTipoEstadoCredito() {
        return tipoEstadoCredito;
    }

    /**
     * @param tipoEstadoCredito the tipoEstadoCredito to set
     */
    public void setTipoEstadoCredito(String tipoEstadoCredito) {
        this.tipoEstadoCredito = tipoEstadoCredito;
    }
    
}
