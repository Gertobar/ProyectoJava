/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera;
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
@Table(name = "MKS_SOCIOS.REFERENCIA_ENTIDAD_FINANCIERA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReferenciaEntidadFinanciera.findAll", query = "SELECT r FROM ReferenciaEntidadFinanciera r"),
    @NamedQuery(name = "ReferenciaEntidadFinanciera.findByCodigo", query = "SELECT r FROM ReferenciaEntidadFinanciera r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "ReferenciaEntidadFinanciera.findByCodigoEntidadFinanciera", query = "SELECT r FROM ReferenciaEntidadFinanciera r WHERE r.codigoEntidadFinanciera = :codigoEntidadFinanciera"),
    @NamedQuery(name = "ReferenciaEntidadFinanciera.findByCodigoTipoCuenta", query = "SELECT r FROM ReferenciaEntidadFinanciera r WHERE r.codigoTipoCuenta = :codigoTipoCuenta"),
    @NamedQuery(name = "ReferenciaEntidadFinanciera.findByNumeroCuenta", query = "SELECT r FROM ReferenciaEntidadFinanciera r WHERE r.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "ReferenciaEntidadFinanciera.findByObservaciones", query = "SELECT r FROM ReferenciaEntidadFinanciera r WHERE r.observaciones = :observaciones"),
    @NamedQuery(name = "ReferenciaEntidadFinanciera.findByEliminado", query = "SELECT r FROM ReferenciaEntidadFinanciera r WHERE r.eliminado = :eliminado"),
    // Personalizados
    @NamedQuery(name = "ReferenciaEntidadFinanciera.findByPersona", query = "SELECT r FROM ReferenciaEntidadFinanciera r WHERE r.codigoPersona = :codigoPersona")
        
})
public class ReferenciaEntidadFinanciera implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByPersona = "ReferenciaEntidadFinanciera.findByPersona";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_REFERENCIA_ENTIDAD_FIN")
    @SequenceGenerator(name = "GSQ_REFERENCIA_ENTIDAD_FIN", schema = "MKS_SOCIOS", allocationSize = 0, sequenceName = "SEQ_REFERENCIA_ENTIDAD_FIN")
    @Column(name = "CODIGO")
    private Long codigo;        
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 30)
    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Persona codigoPersona;
    @JoinColumn(name = "CODIGO_ENTIDAD_FINANCIERA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private EntidadFinanciera codigoEntidadFinanciera;    
    @JoinColumn(name = "CODIGO_TIPO_CUENTA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoCuentaEntidadFinanciera codigoTipoCuenta;

    public ReferenciaEntidadFinanciera() {
    }

    public ReferenciaEntidadFinanciera(Long codigo) {
        this.codigo = codigo;
    }

    public ReferenciaEntidadFinanciera(Long codigo, EntidadFinanciera codigoEntidadFinanciera, TipoCuentaEntidadFinanciera codigoTipoCuenta, String numeroCuenta, String observaciones, char eliminado) {
        this.codigo = codigo;
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
        this.codigoTipoCuenta = codigoTipoCuenta;
        this.numeroCuenta = numeroCuenta;
        this.observaciones = observaciones;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public EntidadFinanciera getCodigoEntidadFinanciera() {
        return codigoEntidadFinanciera;
    }

    public void setCodigoEntidadFinanciera(EntidadFinanciera codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
    }

    public TipoCuentaEntidadFinanciera getCodigoTipoCuenta() {
        return codigoTipoCuenta;
    }

    public void setCodigoTipoCuenta(TipoCuentaEntidadFinanciera codigoTipoCuenta) {
        this.codigoTipoCuenta = codigoTipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Persona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Persona codigoPersona) {
        this.codigoPersona = codigoPersona;
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
        if (!(object instanceof ReferenciaEntidadFinanciera)) {
            return false;
        }
        ReferenciaEntidadFinanciera other = (ReferenciaEntidadFinanciera) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.ReferenciaEntidadFinanciera[ codigo=" + codigo + " ]";
    }
    
}
