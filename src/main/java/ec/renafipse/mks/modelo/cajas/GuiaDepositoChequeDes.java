/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.GUIA_DEPOSITO_CHEQUE_DES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GuiaDepositoChequeDes.findAll", query = "SELECT g FROM GuiaDepositoChequeDes g"),
    @NamedQuery(name = "GuiaDepositoChequeDes.findByCodigoCheque", query = "SELECT g FROM GuiaDepositoChequeDes g WHERE g.codigoCheque = :codigoCheque"),
    @NamedQuery(name = "GuiaDepositoChequeDes.findByFechaEfectivizacion", query = "SELECT g FROM GuiaDepositoChequeDes g WHERE g.fechaEfectivizacion = :fechaEfectivizacion"),
//Personalizados    
    @NamedQuery(name = "GuiaDepositoChequeDes.findByChequesEmitidosGuia", query = "SELECT c FROM GuiaDepositoChequeDes g JOIN g.guiaDepositoChequeDet d JOIN d.guiaDepositoCheque dc JOIN g.transferenciaChequeDesgloce t JOIN t.chequeDepositado c WHERE d.guiaDepositoChequeDetPK.codigoMoneda = :codigoMoneda AND c.codigoEstado.codigo = :codigoEstado AND g.fechaEfectivizacion <= :fechaEfectivizacion AND dc.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "GuiaDepositoChequeDes.findByChequesGuiaProtesto", query = "SELECT c FROM GuiaDepositoChequeDes g JOIN g.guiaDepositoChequeDet d JOIN d.guiaDepositoCheque dc JOIN g.transferenciaChequeDesgloce t JOIN t.chequeDepositado c JOIN c.entidadFinanciera e JOIN c.movimientoCuenta mo JOIN mo.movimientoCuentaAdicional mca WHERE mca.codigoIfipAgencia = :codigoIfipAgencia AND e.codigo = :codigoEntFin AND d.guiaDepositoChequeDetPK.codigoMoneda = :codigoMoneda AND c.codigoEstado.codigo = :codigoEstado AND dc.fecha <= :fechaGuia")

})
public class GuiaDepositoChequeDes implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByChequesEmitidosGuia = "GuiaDepositoChequeDes.findByChequesEmitidosGuia";
    public static final String findByChequesGuiaProtesto = "GuiaDepositoChequeDes.findByChequesGuiaProtesto";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CHEQUE")
    private Long codigoCheque;
    @Column(name = "FECHA_EFECTIVIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEfectivizacion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "guiaDepositoChequeDes", fetch = FetchType.LAZY)
    private EfectivizacionChequeDesgloce efectivizacionChequeDesgloce;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "guiaDepositoChequeDes", fetch = FetchType.LAZY)
    private ProtestoChequeDesgloce protestoChequeDesgloce;
    @JoinColumn(name = "CODIGO_CHEQUE", referencedColumnName = "CODIGO_CHEQUE", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TransferenciaChequeDesgloce transferenciaChequeDesgloce;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_GUIA_DEPOSITO", referencedColumnName = "CODIGO_GUIA_DEPOSITO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private GuiaDepositoChequeDet guiaDepositoChequeDet;

    public GuiaDepositoChequeDes() {
    }

    public GuiaDepositoChequeDes(Long codigoCheque) {
        this.codigoCheque = codigoCheque;
    }

    public Long getCodigoCheque() {
        return codigoCheque;
    }

    public void setCodigoCheque(Long codigoCheque) {
        this.codigoCheque = codigoCheque;
    }

    public Date getFechaEfectivizacion() {
        return fechaEfectivizacion;
    }

    public void setFechaEfectivizacion(Date fechaEfectivizacion) {
        this.fechaEfectivizacion = fechaEfectivizacion;
    }

    public EfectivizacionChequeDesgloce getEfectivizacionChequeDesgloce() {
        return efectivizacionChequeDesgloce;
    }

    public void setEfectivizacionChequeDesgloce(EfectivizacionChequeDesgloce efectivizacionChequeDesgloce) {
        this.efectivizacionChequeDesgloce = efectivizacionChequeDesgloce;
    }

    public ProtestoChequeDesgloce getProtestoChequeDesgloce() {
        return protestoChequeDesgloce;
    }

    public void setProtestoChequeDesgloce(ProtestoChequeDesgloce protestoChequeDesgloce) {
        this.protestoChequeDesgloce = protestoChequeDesgloce;
    }

    public TransferenciaChequeDesgloce getTransferenciaChequeDesgloce() {
        return transferenciaChequeDesgloce;
    }

    public void setTransferenciaChequeDesgloce(TransferenciaChequeDesgloce transferenciaChequeDesgloce) {
        this.transferenciaChequeDesgloce = transferenciaChequeDesgloce;
    }

    public GuiaDepositoChequeDet getGuiaDepositoChequeDet() {
        return guiaDepositoChequeDet;
    }

    public void setGuiaDepositoChequeDet(GuiaDepositoChequeDet guiaDepositoChequeDet) {
        this.guiaDepositoChequeDet = guiaDepositoChequeDet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCheque != null ? codigoCheque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GuiaDepositoChequeDes)) {
            return false;
        }
        GuiaDepositoChequeDes other = (GuiaDepositoChequeDes) object;
        if ((this.codigoCheque == null && other.codigoCheque != null) || (this.codigoCheque != null && !this.codigoCheque.equals(other.codigoCheque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.GuiaDepositoChequeDes[ codigoCheque=" + codigoCheque + " ]";
    }

}
