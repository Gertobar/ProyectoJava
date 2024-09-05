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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mascoop
 */
@Entity
@Table(name = "FABRICA_USUARIO_PERFIL", catalog = "", schema = "MKS_CREDITOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FabricaUsuarioPerfil.findAll", query = "SELECT f FROM FabricaUsuarioPerfil f")
    // Personalizados
    , @NamedQuery(name = "FabricaUsuarioPerfil.findPerfilByUsuarioAgencia", query = "SELECT f FROM FabricaUsuarioPerfil f WHERE f.codigoUsuario = :codigoUsuario AND f.codigoIfipAgencia = :codigIfipAgencia AND f.vigente = 'S'")})
public class FabricaUsuarioPerfil implements Serializable {

    public static String findPerfilByUsuarioAgencia = "FabricaUsuarioPerfil.findPerfilByUsuarioAgencia";
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENTE")
    private String vigente;
    @JoinColumn(name = "CODIGO_PERFIL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private FabricaPerfil codigoPerfil;

    public FabricaUsuarioPerfil() {
    }

    public FabricaUsuarioPerfil(Long codigo) {
        this.codigo = codigo;
    }

    public FabricaUsuarioPerfil(Long codigo, long codigoUsuario, long codigoIfipAgencia, String vigente) {
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public FabricaPerfil getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setCodigoPerfil(FabricaPerfil codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
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
        if (!(object instanceof FabricaUsuarioPerfil)) {
            return false;
        }
        FabricaUsuarioPerfil other = (FabricaUsuarioPerfil) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.FabricaUsuarioPerfil[ codigo=" + codigo + " ]";
    }
    
}
