/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos.lineacredito;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author andres
 */
@Entity
@Table(name = "MKS_CREDITOS.LINEA_CREDITO_PLAZO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaCreditoPlazo.findAll", query = "SELECT l FROM LineaCreditoPlazo l"),
    @NamedQuery(name = "LineaCreditoPlazo.findByCodigo", query = "SELECT l FROM LineaCreditoPlazo l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LineaCreditoPlazo.findByCodigoLineaCredito", query = "SELECT l FROM LineaCreditoPlazo l WHERE l.codigoLineaCredito.codigo = :codigoLineaCredito")})
public class LineaCreditoPlazo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_LINEA_CREDITO_PLAZO")
    @SequenceGenerator(name = "GSQ_LINEA_CREDITO_PLAZO", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_LINEA_CREDITO_PLAZO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_INICIO_PLAZO")
    private BigDecimal montoInicioPlazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_FIN_PLAZO")
    private BigDecimal montoFinPlazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CUOTAS")
    private Long numeroCuotas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @JoinColumn(name = "CODIGO_LINEA_CREDITO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private LineaCredito codigoLineaCredito;

    public LineaCreditoPlazo() {
    }

    public LineaCreditoPlazo(Long codigo) {
        this.codigo = codigo;
    }

    public LineaCreditoPlazo(Long codigo, BigDecimal montoInicioPlazo, BigDecimal montoFinPlazo, Long numeroCuotas, String eliminado, LineaCredito codigoLineaCredito) {
        this.codigo = codigo;
        this.montoInicioPlazo = montoInicioPlazo;
        this.montoFinPlazo = montoFinPlazo;
        this.numeroCuotas = numeroCuotas;
        this.eliminado = eliminado;
        this.codigoLineaCredito = codigoLineaCredito;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getMontoInicioPlazo() {
        return montoInicioPlazo;
    }

    public void setMontoInicioPlazo(BigDecimal montoInicioPlazo) {
        this.montoInicioPlazo = montoInicioPlazo;
    }

    public BigDecimal getMontoFinPlazo() {
        return montoFinPlazo;
    }

    public void setMontoFinPlazo(BigDecimal montoFinPlazo) {
        this.montoFinPlazo = montoFinPlazo;
    }

    public Long getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(Long numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public LineaCredito getCodigoLineaCredito() {
        return codigoLineaCredito;
    }

    public void setCodigoLineaCredito(LineaCredito codigoLineaCredito) {
        this.codigoLineaCredito = codigoLineaCredito;
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
        if (!(object instanceof LineaCreditoPlazo)) {
            return false;
        }
        LineaCreditoPlazo other = (LineaCreditoPlazo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.LineaCreditoPlazo[ codigo=" + codigo + " ]";
    }
    
}
