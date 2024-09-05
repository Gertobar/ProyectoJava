/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ahorros;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_AHORROS.TMP_EJECUCION_TRA_LOT_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmpEjecucionTraLotDet.findAll", query = "SELECT t FROM TmpEjecucionTraLotDet t")
    , @NamedQuery(name = "TmpEjecucionTraLotDet.findByCodigoEjecucion", query = "SELECT t FROM TmpEjecucionTraLotDet t WHERE t.ejecucionTransaccionLote.codigo = :codigoEjecucion")
    , @NamedQuery(name = "TmpEjecucionTraLotDet.findByCodigoCuenta", query = "SELECT t FROM TmpEjecucionTraLotDet t WHERE t.codigoCuenta = :codigoCuenta")
    , @NamedQuery(name = "TmpEjecucionTraLotDet.findByValor", query = "SELECT t FROM TmpEjecucionTraLotDet t WHERE t.valor = :valor")})
public class TmpEjecucionTraLotDet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TMP_EJE_TRA_LOT_DET")
    @SequenceGenerator(name = "GSQ_TMP_EJE_TRA_LOT_DET", schema = "MKS_AHORROS",  allocationSize = 0, sequenceName = "SEQ_TMP_EJE_TRA_LOT_DET")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @JoinColumn(name = "CODIGO_EJECUCION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private EjecucionTransaccionLote ejecucionTransaccionLote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA")
    private long codigoCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;

    public TmpEjecucionTraLotDet() {
    }

    public TmpEjecucionTraLotDet(Long codigo) {
        this.codigo = codigo;
    }

    public TmpEjecucionTraLotDet(EjecucionTransaccionLote ejecucionTransaccionLote, long codigoCuenta, long codigoPersona, BigDecimal valor) {
        this.ejecucionTransaccionLote = ejecucionTransaccionLote;
        this.codigoPersona = codigoPersona;
        this.codigoCuenta = codigoCuenta;
        this.valor = valor;
    }
    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public EjecucionTransaccionLote getEjecucionTransaccionLote() {
        return ejecucionTransaccionLote;
    }

    public void setEjecucionTransaccionLote(EjecucionTransaccionLote ejecucionTransaccionLote) {
        this.ejecucionTransaccionLote = ejecucionTransaccionLote;
    }

    public long getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(long codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }
        
    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
        if (!(object instanceof TmpEjecucionTraLotDet)) {
            return false;
        }
        TmpEjecucionTraLotDet other = (TmpEjecucionTraLotDet) object;
        return !((this.codigo == null && other.codigo != null) || (!this.codigo.equals(other.codigo) && this.codigo != null));
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.TmpEjecucionTraLotDet[ codigo=" + codigo + " ]";
    }
    
}
