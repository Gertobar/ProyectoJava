/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.cajas;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.EFECTIVIZACION_CHEQUE_DESGLOCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EfectivizacionChequeDesgloce.findAll", query = "SELECT e FROM EfectivizacionChequeDesgloce e"),
    @NamedQuery(name = "EfectivizacionChequeDesgloce.findByCodigoCheque", query = "SELECT e FROM EfectivizacionChequeDesgloce e WHERE e.codigoCheque = :codigoCheque")})
public class EfectivizacionChequeDesgloce implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CHEQUE")
    private Long codigoCheque;
    @JoinColumn(name = "CODIGO_CHEQUE", referencedColumnName = "CODIGO_CHEQUE", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private GuiaDepositoChequeDes guiaDepositoChequeDes;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_EFE_CHE_DET", referencedColumnName = "CODIGO_EFE_CHE", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private EfectivizacionChequeDetalle efectivizacionChequeDetalle;


    public EfectivizacionChequeDesgloce() {
    }

    public EfectivizacionChequeDesgloce(Long codigoCheque) {
        this.codigoCheque = codigoCheque;
    }

    public Long getCodigoCheque() {
        return codigoCheque;
    }

    public void setCodigoCheque(Long codigoCheque) {
        this.codigoCheque = codigoCheque;
    }

    public GuiaDepositoChequeDes getGuiaDepositoChequeDes() {
        return guiaDepositoChequeDes;
    }

    public void setGuiaDepositoChequeDes(GuiaDepositoChequeDes guiaDepositoChequeDes) {
        this.guiaDepositoChequeDes = guiaDepositoChequeDes;
    }

    public EfectivizacionChequeDetalle getEfectivizacionChequeDetalle() {
        return efectivizacionChequeDetalle;
    }

    public void setEfectivizacionChequeDetalle(EfectivizacionChequeDetalle efectivizacionChequeDetalle) {
        this.efectivizacionChequeDetalle = efectivizacionChequeDetalle;
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
        if (!(object instanceof EfectivizacionChequeDesgloce)) {
            return false;
        }
        EfectivizacionChequeDesgloce other = (EfectivizacionChequeDesgloce) object;
        if ((this.codigoCheque == null && other.codigoCheque != null) || (this.codigoCheque != null && !this.codigoCheque.equals(other.codigoCheque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.EfectivizacionChequeDesgloce[ codigoCheque=" + codigoCheque + " ]";
    }

}
