/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "FABRICA_PERFIL_MONTO", catalog = "", schema = "MKS_CREDITOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FabricaPerfilMonto.findAll", query = "SELECT f FROM FabricaPerfilMonto f")
    , @NamedQuery(name = "FabricaPerfilMonto.findByCodigo", query = "SELECT f FROM FabricaPerfilMonto f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "FabricaPerfilMonto.findByMontoInicial", query = "SELECT f FROM FabricaPerfilMonto f WHERE f.montoInicial = :montoInicial")
    , @NamedQuery(name = "FabricaPerfilMonto.findByMontoFinal", query = "SELECT f FROM FabricaPerfilMonto f WHERE f.montoFinal = :montoFinal")
    , @NamedQuery(name = "FabricaPerfilMonto.findByVigente", query = "SELECT f FROM FabricaPerfilMonto f WHERE f.vigente = :vigente")})
public class FabricaPerfilMonto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_INICIAL")
    private BigDecimal montoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_FINAL")
    private BigDecimal montoFinal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;
    @JoinColumn(name = "CODIGO_FABRICA_PERFIL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private FabricaPerfil codigoFabricaPerfil;

    public FabricaPerfilMonto() {
    }

    public FabricaPerfilMonto(Long codigo) {
        this.codigo = codigo;
    }

    public FabricaPerfilMonto(Long codigo, BigDecimal montoInicial, BigDecimal montoFinal, String vigente) {
        this.codigo = codigo;
        this.montoInicial = montoInicial;
        this.montoFinal = montoFinal;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }

    public BigDecimal getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(BigDecimal montoFinal) {
        this.montoFinal = montoFinal;
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
        if (!(object instanceof FabricaPerfilMonto)) {
            return false;
        }
        FabricaPerfilMonto other = (FabricaPerfilMonto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.FabricaPerfilMonto[ codigo=" + codigo + " ]";
    }
    
}
