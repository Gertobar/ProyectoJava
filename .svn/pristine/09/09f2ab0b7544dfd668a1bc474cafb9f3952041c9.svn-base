/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.judiciales;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_JUDICIALES.PAGO_COSTO_JUDICIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoCostoJudicial.findAll", query = "SELECT p FROM PagoCostoJudicial p"),
    @NamedQuery(name = "PagoCostoJudicial.findByCodigoPagoCredito", query = "SELECT p FROM PagoCostoJudicial p WHERE p.codigoPagoCredito = :codigoPagoCredito"),
    @NamedQuery(name = "PagoCostoJudicial.findByEliminado", query = "SELECT p FROM PagoCostoJudicial p WHERE p.eliminado = :eliminado")})
public class PagoCostoJudicial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PAGO_CREDITO")
    private Long codigoPagoCredito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @JoinColumn(name = "CODIGO_CABECERA_COSTO_JUDICIAL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private CabeceraCostoJudicial codigoCabeceraCostoJudicial;

    public PagoCostoJudicial() {
    }

    public PagoCostoJudicial(Long codigoPagoCredito) {
        this.codigoPagoCredito = codigoPagoCredito;
    }

    public PagoCostoJudicial(Long codigoPagoCredito, String eliminado) {
        this.codigoPagoCredito = codigoPagoCredito;
        this.eliminado = eliminado;
    }

    public Long getCodigoPagoCredito() {
        return codigoPagoCredito;
    }

    public void setCodigoPagoCredito(Long codigoPagoCredito) {
        this.codigoPagoCredito = codigoPagoCredito;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public CabeceraCostoJudicial getCodigoCabeceraCostoJudicial() {
        return codigoCabeceraCostoJudicial;
    }

    public void setCodigoCabeceraCostoJudicial(CabeceraCostoJudicial codigoCabeceraCostoJudicial) {
        this.codigoCabeceraCostoJudicial = codigoCabeceraCostoJudicial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPagoCredito != null ? codigoPagoCredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoCostoJudicial)) {
            return false;
        }
        PagoCostoJudicial other = (PagoCostoJudicial) object;
        if ((this.codigoPagoCredito == null && other.codigoPagoCredito != null) || (this.codigoPagoCredito != null && !this.codigoPagoCredito.equals(other.codigoPagoCredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.judiciales.PagoCostoJudicial[ codigoPagoCredito=" + codigoPagoCredito + " ]";
    }
    
}
