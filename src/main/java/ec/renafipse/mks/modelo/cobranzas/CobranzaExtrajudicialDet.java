/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cobranzas;

import ec.renafipse.mks.modelo.ifips.ServicioFinancieroTipoCanal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * @author Andres
 */
@Entity
@Table(name = "MKS_CREDITOS.COBRANZA_EXTRAJUDICIAL_DET")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "CobranzaExtrajudicialDet.findByCodigoCobranza", query = "SELECT c FROM CobranzaExtrajudicialDet c WHERE c.codigoCobranzaExtrajudicial.codigo = :codigoCobranza")})
public class CobranzaExtrajudicialDet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_COBRANZA_EXTRAJUDICIAL_DET")
    @SequenceGenerator(name = "GSQ_COBRANZA_EXTRAJUDICIAL_DET", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_COBRANZA_EXTRAJUDICIAL_DET")
    @Basic(optional = false)
    @NotNull
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_DIA_INICIAL")
    private BigInteger numeroDiaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_DIA_FINAL")
    private BigInteger numeroDiaFinal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_MAXIMO")
    private BigDecimal valorMaximo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CALCULA_POR_DIFERENCIA")
    private char calculaPorDiferencia;
    @JoinColumn(name = "CODIGO_COBRANZA_EXTRAJUDICIAL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private CobranzaExtrajudicial codigoCobranzaExtrajudicial;
    @JoinColumn(name = "CODIGO_SER_FIN_TIP_CAN", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ServicioFinancieroTipoCanal codigoServicioFinancieroTipoCanal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    
    public CobranzaExtrajudicialDet() {
    }

    public CobranzaExtrajudicialDet(Long codigo, String nombre, BigInteger numeroDiaInicial, BigInteger numeroDiaFinal, BigDecimal valorMaximo, char eliminado, char calculaPorDiferencia, CobranzaExtrajudicial codigoCobranzaExtrajudicial) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.numeroDiaInicial = numeroDiaInicial;
        this.numeroDiaFinal = numeroDiaFinal;
        this.valorMaximo = valorMaximo;
        this.calculaPorDiferencia = calculaPorDiferencia;
        this.eliminado = eliminado;
    }
    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getNumeroDiaInicial() {
        return numeroDiaInicial;
    }

    public void setNumeroDiaInicial(BigInteger numeroDiaInicial) {
        this.numeroDiaInicial = numeroDiaInicial;
    }

    public BigInteger getNumeroDiaFinal() {
        return numeroDiaFinal;
    }

    public void setNumeroDiaFinal(BigInteger numeroDiaFinal) {
        this.numeroDiaFinal = numeroDiaFinal;
    }

    public BigDecimal getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(BigDecimal valorMaximo) {
        this.valorMaximo = valorMaximo;
    }
   
    public char getCalculaPorDiferencia() {
        return calculaPorDiferencia;
    }

    public void setCalculaPorDiferencia(char calculaPorDiferencia) {
        this.calculaPorDiferencia = calculaPorDiferencia;
    }
   
    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public CobranzaExtrajudicial getCodigoCobranzaExtrajudicial() {
        return codigoCobranzaExtrajudicial;
    }

    public void setCodigoCobranzaExtrajudicial(CobranzaExtrajudicial codigoCobranzaExtrajudicial) {
        this.codigoCobranzaExtrajudicial = codigoCobranzaExtrajudicial;
    }
    
    public ServicioFinancieroTipoCanal getCodigoServicioFinancieroTipoCanal() {
        return codigoServicioFinancieroTipoCanal;
    }

    public void setCodigoServicioFinancieroTipoCanal(ServicioFinancieroTipoCanal codigoServicioFinancieroTipoCanal) {
        this.codigoServicioFinancieroTipoCanal = codigoServicioFinancieroTipoCanal;
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
        if (!(object instanceof CobranzaExtrajudicialDet)) {
            return false;
        }
        CobranzaExtrajudicialDet other = (CobranzaExtrajudicialDet) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cobranzas.CobranzaExtrajudicialDet[ codigo=" + codigo + " ]";
    }
}