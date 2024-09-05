/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.TRANSFERENCIA_CHEQUE_DEP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransferenciaChequeDep.findAll", query = "SELECT t FROM TransferenciaChequeDep t"),
    @NamedQuery(name = "TransferenciaChequeDep.findByCodigo", query = "SELECT t FROM TransferenciaChequeDep t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TransferenciaChequeDep.findByCodigoUsuario", query = "SELECT t FROM TransferenciaChequeDep t WHERE t.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "TransferenciaChequeDep.findByCodigoUsuarioDestino", query = "SELECT t FROM TransferenciaChequeDep t WHERE t.codigoUsuarioDestino = :codigoUsuarioDestino"),
    @NamedQuery(name = "TransferenciaChequeDep.findByCodigoComputador", query = "SELECT t FROM TransferenciaChequeDep t WHERE t.codigoComputador = :codigoComputador"),
    @NamedQuery(name = "TransferenciaChequeDep.findByCodigoIfipAgencia", query = "SELECT t FROM TransferenciaChequeDep t WHERE t.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "TransferenciaChequeDep.findByFecha", query = "SELECT t FROM TransferenciaChequeDep t WHERE t.fecha = :fecha"),
//Personalizados
    @NamedQuery(name = "TransferenciaChequeDep.findByUsuarioIfipAgencia", query = "SELECT t FROM TransferenciaChequeDep t WHERE t.codigoUsuario = :codigoUsuario AND t.codigoIfipAgencia = :codigoIfipAgencia") 
})
public class TransferenciaChequeDep implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByUsuarioIfipAgencia="TransferenciaChequeDep.findByUsuarioIfipAgencia";
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TRANSFERENCIA_CHEQUE_DEP")
    @SequenceGenerator(name = "GSQ_TRANSFERENCIA_CHEQUE_DEP", schema = "MKS_CAJAS", allocationSize = 0, sequenceName = "SEQ_TRANSFERENCIA_CHEQUE_DEP")
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
    @Column(name = "CODIGO_USUARIO_DESTINO")
    private long codigoUsuarioDestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPUTADOR")
    private long codigoComputador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transferenciaChequeDep", fetch = FetchType.LAZY)
    private Collection<TransferenciaChequeDepDet> transferenciaChequeDepDetCollection;

    public TransferenciaChequeDep() {
    }

    public TransferenciaChequeDep(Long codigo) {
        this.codigo = codigo;
    }

    public TransferenciaChequeDep(Long codigo, long codigoUsuario, long codigoUsuarioDestino, long codigoComputador, long codigoIfipAgencia, Date fecha){
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.codigoUsuarioDestino = codigoUsuarioDestino;
        this.codigoComputador = codigoComputador;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.fecha = fecha;
       
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

    public long getCodigoUsuarioDestino() {
        return codigoUsuarioDestino;
    }

    public void setCodigoUsuarioDestino(long codigoUsuarioDestino) {
        this.codigoUsuarioDestino = codigoUsuarioDestino;
    }

    public long getCodigoComputador() {
        return codigoComputador;
    }

    public void setCodigoComputador(long codigoComputador) {
        this.codigoComputador = codigoComputador;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

  
    @XmlTransient
    public Collection<TransferenciaChequeDepDet> getTransferenciaChequeDepDetCollection() {
        return transferenciaChequeDepDetCollection;
    }

    public void setTransferenciaChequeDepDetCollection(Collection<TransferenciaChequeDepDet> transferenciaChequeDepDetCollection) {
        this.transferenciaChequeDepDetCollection = transferenciaChequeDepDetCollection;
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
        if (!(object instanceof TransferenciaChequeDep)) {
            return false;
        }
        TransferenciaChequeDep other = (TransferenciaChequeDep) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.TransferenciaChequeDep[ codigo=" + codigo + " ]";
    }

    /**
     * @return the fechaSistema
     */
    public Date getFechaSistema() {
        return fechaSistema;
    }

    /**
     * @param fechaSistema the fechaSistema to set
     */
    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }
    
}
