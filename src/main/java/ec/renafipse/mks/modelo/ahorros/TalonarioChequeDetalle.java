/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.TALONARIO_CHEQUE_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TalonarioChequeDetalle.findAll", query = "SELECT t FROM TalonarioChequeDetalle t"),
    @NamedQuery(name = "TalonarioChequeDetalle.findByNumeroCheque", query = "SELECT t FROM TalonarioChequeDetalle t WHERE t.talonarioChequeDetallePK.numeroCheque = :numeroCheque"),
    @NamedQuery(name = "TalonarioChequeDetalle.findByEstado", query = "SELECT t FROM TalonarioChequeDetalle t WHERE t.estado = :estado"),
    @NamedQuery(name = "TalonarioChequeDetalle.findByCodigoCuentaEntFin", query = "SELECT t FROM TalonarioChequeDetalle t WHERE t.talonarioChequeDetallePK.codigoCuentaEntFin = :codigoCuentaEntFin"),
    @NamedQuery(name = "TalonarioChequeDetalle.findByNumChequEntFin", query = "SELECT t FROM TalonarioChequeDetalle t WHERE t.talonarioChequeDetallePK.numeroCheque = :numeroCheque AND t.talonarioCheque.ifipCuentaEntidadFinanciera.codigoEntidadFinanciera = :codigoCuentaEntFin"),
    @NamedQuery(name = "TalonarioChequeDetalle.findByNumChequEntFinaIfipAgencia", query = "SELECT t FROM TalonarioChequeDetalle t JOIN t.talonarioCheque tche WHERE t.talonarioChequeDetallePK.numeroCheque = :numeroCheque AND tche.ifipCuentaEntidadFinanciera.codigoEntidadFinanciera = :codigoCuentaEntFin AND tche.codigoIfipAgencia =:codigoAgencia ")
})
public class TalonarioChequeDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    /// listar todos los los detalles del pago de compra segun CODIGO_ENTIDAD_FINANCIERA, NUMERO_CHEQUE, NUMERO_CUENTA
    public static final String findByNumChequEntFin = "TalonarioChequeDetalle.findByNumChequEntFin";
    public static final String findByNumChequEntFinaIfipAgencia = "TalonarioChequeDetalle.findByNumChequEntFinaIfipAgencia";
    @EmbeddedId
    protected TalonarioChequeDetallePK talonarioChequeDetallePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TALONARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_CUENTA_ENT_FIN", referencedColumnName = "CODIGO_CUENTA_ENT_FIN", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TalonarioCheque talonarioCheque;
 
    public TalonarioChequeDetalle() {
    }

    public TalonarioChequeDetalle(TalonarioChequeDetallePK talonarioChequeDetallePK) {
        this.talonarioChequeDetallePK = talonarioChequeDetallePK;
    }

    public TalonarioChequeDetalle(TalonarioChequeDetallePK talonarioChequeDetallePK, char estado) {
        this.talonarioChequeDetallePK = talonarioChequeDetallePK;
        this.estado = estado;
    }

    public TalonarioChequeDetalle(long numeroCheque, long codigoCuentaEntFin) {
        this.talonarioChequeDetallePK = new TalonarioChequeDetallePK(numeroCheque, codigoCuentaEntFin);
    }

    public TalonarioChequeDetallePK getTalonarioChequeDetallePK() {
        return talonarioChequeDetallePK;
    }

    public void setTalonarioChequeDetallePK(TalonarioChequeDetallePK talonarioChequeDetallePK) {
        this.talonarioChequeDetallePK = talonarioChequeDetallePK;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public TalonarioCheque getTalonarioCheque() {
        return talonarioCheque;
    }

    public void setTalonarioCheque(TalonarioCheque talonarioCheque) {
        this.talonarioCheque = talonarioCheque;
    }

 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (talonarioChequeDetallePK != null ? talonarioChequeDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TalonarioChequeDetalle)) {
            return false;
        }
        TalonarioChequeDetalle other = (TalonarioChequeDetalle) object;
        if ((this.talonarioChequeDetallePK == null && other.talonarioChequeDetallePK != null) || (this.talonarioChequeDetallePK != null && !this.talonarioChequeDetallePK.equals(other.talonarioChequeDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.ahorros.TalonarioChequeDetalle[ talonarioChequeDetallePK=" + talonarioChequeDetallePK + " ]";
    }
    
}
