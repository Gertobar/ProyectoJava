/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "MKS_AHORROS.LICITUD_FON_CPTO_TRAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicitudFonCptoTran.findAll", query = "SELECT l FROM LicitudFonCptoTran l"),    
    @NamedQuery(name = "LicitudFonCptoTran.findByCodigoIfip", query = "SELECT l FROM LicitudFonCptoTran l WHERE l.codigoIfip = :codigoIfip"),        
    @NamedQuery(name = "LicitudFonCptoTran.findByTipo", query = "SELECT l FROM LicitudFonCptoTran l WHERE l.conceptoTransaccionPro.conceptoTransaccionProPK.codigoConcepto = :codigoConcepto  AND l.conceptoTransaccionPro.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda AND l.conceptoTransaccionPro.conceptoTransaccionProPK.codigoTipoProducto = :codigoTipoProducto AND l.codigoIfip = :codigoIfip AND l.eliminado = :eliminado"
    )})
public class LicitudFonCptoTran implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByTipo = "LicitudFonCptoTran.findByTipo";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACUMULADO")
    private char acumulado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMPENSACION")
    private char compensacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_INDIVIDUAL")
    private char esIndividual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO_TIPO_PRODUCTO"),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA"),
        @JoinColumn(name = "CODIGO_CONCEPTO", referencedColumnName = "CODIGO_CONCEPTO")})
    @ManyToOne(optional = false)
    private ConceptoTransaccionPro conceptoTransaccionPro;

    public LicitudFonCptoTran() {
    }

    public LicitudFonCptoTran(Long codigo) {
        this.codigo = codigo;
    }

    public LicitudFonCptoTran(Long codigo, long codigoIfip, char acumulado, char compensacion, char esIndividual, char eliminado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.acumulado = acumulado;
        this.compensacion = compensacion;
        this.esIndividual = esIndividual;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public char getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(char acumulado) {
        this.acumulado = acumulado;
    }

    public char getCompensacion() {
        return compensacion;
    }

    public void setCompensacion(char compensacion) {
        this.compensacion = compensacion;
    }

    public char getEsIndividual() {
        return esIndividual;
    }

    public void setEsIndividual(char esIndividual) {
        this.esIndividual = esIndividual;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public ConceptoTransaccionPro getConceptoTransaccionPro() {
        return conceptoTransaccionPro;
    }

    public void setConceptoTransaccionPro(ConceptoTransaccionPro conceptoTransaccionPro) {
        this.conceptoTransaccionPro = conceptoTransaccionPro;
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
        if (!(object instanceof LicitudFonCptoTran)) {
            return false;
        }
        LicitudFonCptoTran other = (LicitudFonCptoTran) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorrosl.LicitudFonCptoTran[ codigo=" + codigo + " ]";
    }

    /**
     * @return the tipo
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    
}
