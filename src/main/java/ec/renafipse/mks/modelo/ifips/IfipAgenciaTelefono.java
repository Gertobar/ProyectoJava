/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_IFIPS.IFIP_AGENCIA_TELEFONO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfipAgenciaTelefono.findAll", query = "SELECT i FROM IfipAgenciaTelefono i"),
    @NamedQuery(name = "IfipAgenciaTelefono.findByCodigo", query = "SELECT i FROM IfipAgenciaTelefono i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "IfipAgenciaTelefono.findByCodigoTipoTelefono", query = "SELECT i FROM IfipAgenciaTelefono i WHERE i.codigoTipoTelefono = :codigoTipoTelefono"),
    @NamedQuery(name = "IfipAgenciaTelefono.findByCodigoOperadoraTelefono", query = "SELECT i FROM IfipAgenciaTelefono i WHERE i.codigoOperadoraTelefono = :codigoOperadoraTelefono"),
    @NamedQuery(name = "IfipAgenciaTelefono.findByNumero", query = "SELECT i FROM IfipAgenciaTelefono i WHERE i.numero = :numero"),
    @NamedQuery(name = "IfipAgenciaTelefono.findByPrincipal", query = "SELECT i FROM IfipAgenciaTelefono i WHERE i.principal = :principal"),
    @NamedQuery(name = "IfipAgenciaTelefono.findByMostrarImpresion", query = "SELECT i FROM IfipAgenciaTelefono i WHERE i.mostrarImpresion = :mostrarImpresion"),
    @NamedQuery(name = "IfipAgenciaTelefono.findByEliminado", query = "SELECT i FROM IfipAgenciaTelefono i WHERE i.eliminado = :eliminado")})
public class IfipAgenciaTelefono implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_IFIP_AGENCIA_TELEFONO")
    @SequenceGenerator(name = "GSQ_IFIP_AGENCIA_TELEFONO", schema = "MKS_IFIPS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_IFIP_AGENCIA_TELEFONO")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_TELEFONO")
    private long codigoTipoTelefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_OPERADORA_TELEFONO")
    private long codigoOperadoraTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NUMERO")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRINCIPAL")
    private char principal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOSTRAR_IMPRESION")
    private char mostrarImpresion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private IfipAgencia codigoIfipAgencia;

    public IfipAgenciaTelefono() {
    }

    public IfipAgenciaTelefono(Long codigo) {
        this.codigo = codigo;
    }

    public IfipAgenciaTelefono(Long codigo, long codigoTipoTelefono, long codigoOperadoraTelefono, String numero, char principal, char mostrarImpresion, char eliminado) {
        this.codigo = codigo;
        this.codigoTipoTelefono = codigoTipoTelefono;
        this.codigoOperadoraTelefono = codigoOperadoraTelefono;
        this.numero = numero;
        this.principal = principal;
        this.mostrarImpresion = mostrarImpresion;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoTipoTelefono() {
        return codigoTipoTelefono;
    }

    public void setCodigoTipoTelefono(long codigoTipoTelefono) {
        this.codigoTipoTelefono = codigoTipoTelefono;
    }

    public long getCodigoOperadoraTelefono() {
        return codigoOperadoraTelefono;
    }

    public void setCodigoOperadoraTelefono(long codigoOperadoraTelefono) {
        this.codigoOperadoraTelefono = codigoOperadoraTelefono;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public char getPrincipal() {
        return principal;
    }

    public void setPrincipal(char principal) {
        this.principal = principal;
    }

    public char getMostrarImpresion() {
        return mostrarImpresion;
    }

    public void setMostrarImpresion(char mostrarImpresion) {
        this.mostrarImpresion = mostrarImpresion;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public IfipAgencia getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(IfipAgencia codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
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
        if (!(object instanceof IfipAgenciaTelefono)) {
            return false;
        }
        IfipAgenciaTelefono other = (IfipAgenciaTelefono) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.IfipAgenciaTelefono[ codigo=" + codigo + " ]";
    }
    
}
