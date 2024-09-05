/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ifips;

import ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalleCheque;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FliaAstudillo
 */
@Entity
@Table(name = "MKS_IFIPS.IFIP_CUENTA_ENTIDAD_FINANCIERA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfipCuentaEntidadFinanciera.findAll", query = "SELECT i FROM IfipCuentaEntidadFinanciera i"),
    @NamedQuery(name = "IfipCuentaEntidadFinanciera.findByCodigo", query = "SELECT i FROM IfipCuentaEntidadFinanciera i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "IfipCuentaEntidadFinanciera.findByCodigoEntidadFinanciera", query = "SELECT i FROM IfipCuentaEntidadFinanciera i WHERE i.codigoEntidadFinanciera = :codigoEntidadFinanciera"),
    @NamedQuery(name = "IfipCuentaEntidadFinanciera.findByCodigoTipoCuenta", query = "SELECT i FROM IfipCuentaEntidadFinanciera i WHERE i.codigoTipoCuenta = :codigoTipoCuenta"),
    @NamedQuery(name = "IfipCuentaEntidadFinanciera.findByNumeroCuenta", query = "SELECT i FROM IfipCuentaEntidadFinanciera i WHERE i.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "IfipCuentaEntidadFinanciera.findByObservaciones", query = "SELECT i FROM IfipCuentaEntidadFinanciera i WHERE i.observaciones = :observaciones"),
    @NamedQuery(name = "IfipCuentaEntidadFinanciera.findByEliminado", query = "SELECT i FROM IfipCuentaEntidadFinanciera i WHERE i.eliminado = :eliminado"),
    @NamedQuery(name = "IfipCuentaEntidadFinanciera.findByCuentaFinancieraIfipAgencia", 
                query = "SELECT i FROM IfipCuentaEntidadFinanciera i JOIN i.entidadFinanciera  e "
                      + "WHERE e.eliminado = :eliminado AND i.eliminado = :eliminado "
                      + "AND e.codigo IN (SELECT a.entidadFinancieraIfiAgePK.codigoEntidadFinanciera  "
                                        + "FROM EntidadFinancieraIfiAge a "
                                        + "WHERE a.ifipAgencia.codigo = :codigoIfipAgencia "
                                        + "AND   a.ifipAgencia.codigoIfip.codigo = :codigoIfip "
                                        + "AND   a.eliminado = :eliminado) "),
    @NamedQuery(name = "IfipCuentaEntidadFinanciera.findEntidadByIfipAgenciaIifpEliminado", 
                query = "SELECT e FROM IfipCuentaEntidadFinanciera i JOIN i.entidadFinanciera  e "
                      + "WHERE e.eliminado = :eliminado AND i.eliminado = :eliminado "
                      + "AND e.codigo IN (SELECT a.entidadFinancieraIfiAgePK.codigoEntidadFinanciera  "
                                        + "FROM EntidadFinancieraIfiAge a "
                                        + "WHERE a.ifipAgencia.codigo = :codigoIfipAgencia "
                                        + "AND   a.ifipAgencia.codigoIfip.codigo = :codigoIfip "
                                        + "AND   a.eliminado = :eliminado) "),
    @NamedQuery(name = "IfipCuentaEntidadFinanciera.findCodCueByIfipAgenciaIifpEliminadoEntFin", 
                query = "SELECT i FROM IfipCuentaEntidadFinanciera i JOIN i.entidadFinanciera  e "
                      + "WHERE e.eliminado = :eliminado AND i.eliminado = :eliminado AND i.codigoEntidadFinanciera = :codigoEntidadFinanciera "
                      + "AND e.codigo IN (SELECT a.entidadFinancieraIfiAgePK.codigoEntidadFinanciera  "
                                        + "FROM EntidadFinancieraIfiAge a "
                                        + "WHERE a.ifipAgencia.codigo = :codigoIfipAgencia "
                                        + "AND   a.ifipAgencia.codigoIfip.codigo = :codigoIfip "
                                        + "AND   a.eliminado = :eliminado) ")
})
public class IfipCuentaEntidadFinanciera implements Serializable {

    private static final long serialVersionUID = 1L;
    public static String findByCuentaFinancieraIfipAgencia = "IfipCuentaEntidadFinanciera.findByCuentaFinancieraIfipAgencia";
    public static String findEntidadByIfipAgenciaIifpEliminado = "IfipCuentaEntidadFinanciera.findEntidadByIfipAgenciaIifpEliminado";
    public static String findCodCueByIfipAgenciaIifpEliminadoEntFin = "IfipCuentaEntidadFinanciera.findCodCueByIfipAgenciaIifpEliminadoEntFin";
    //
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_IFIP_CUENTA_ENTIDAD_FIN")
    @SequenceGenerator(name = "GSQ_IFIP_CUENTA_ENTIDAD_FIN", schema = "MKS_IFIPS", allocationSize = 0, sequenceName = "SEQ_IFIP_CUENTA_ENTIDAD_FIN")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENTIDAD_FINANCIERA")
    private long codigoEntidadFinanciera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_CUENTA")
    private long codigoTipoCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
    @JoinColumn(name = "CODIGO_ENTIDAD_FINANCIERA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EntidadFinanciera entidadFinanciera;
    @JoinColumn(name = "CODIGO_TIPO_CUENTA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoCuentaEntidadFinanciera tipoCuentaEntidadFinanciera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ifipCuentaEntidadFinanciera", fetch = FetchType.LAZY)
    private Collection<PagoCompraDetalleCheque> PagoCompraDetalleChequeCollection; 

    public IfipCuentaEntidadFinanciera() {
    }

    public IfipCuentaEntidadFinanciera(Long codigo) {
        this.codigo = codigo;
    }

    public IfipCuentaEntidadFinanciera(Long codigo, long codigoEntidadFinanciera, long codigoTipoCuenta, String numeroCuenta, String observaciones, char eliminado) {
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

    public long getCodigoEntidadFinanciera() {
        return codigoEntidadFinanciera;
    }

    public void setCodigoEntidadFinanciera(long codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
    }

    public long getCodigoTipoCuenta() {
        return codigoTipoCuenta;
    }

    public void setCodigoTipoCuenta(long codigoTipoCuenta) {
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

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
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
        if (!(object instanceof IfipCuentaEntidadFinanciera)) {
            return false;
        }
        IfipCuentaEntidadFinanciera other = (IfipCuentaEntidadFinanciera) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.ifips.IfipCuentaEntidadFinanciera[ codigo=" + codigo + " ]";
    }

 

    /**
     * @return the tipoCuentaEntidadFinanciera
     */
    public TipoCuentaEntidadFinanciera getTipoCuentaEntidadFinanciera() {
        return tipoCuentaEntidadFinanciera;
    }

    /**
     * @param tipoCuentaEntidadFinanciera the tipoCuentaEntidadFinanciera to set
     */
    public void setTipoCuentaEntidadFinanciera(TipoCuentaEntidadFinanciera tipoCuentaEntidadFinanciera) {
        this.tipoCuentaEntidadFinanciera = tipoCuentaEntidadFinanciera;
    }
 

    /**
     * @return the entidadFinanciera
     */
    public EntidadFinanciera getEntidadFinanciera() {
        return entidadFinanciera;
    }

    /**
     * @param entidadFinanciera the entidadFinanciera to set
     */
    public void setEntidadFinanciera(EntidadFinanciera entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    /**
     * @return the PagoCompraDetalleChequeCollection
     */
    public Collection<PagoCompraDetalleCheque> getPagoCompraDetalleChequeCollection() {
        return PagoCompraDetalleChequeCollection;
    }

    /**
     * @param PagoCompraDetalleChequeCollection the PagoCompraDetalleChequeCollection to set
     */
    public void setPagoCompraDetalleChequeCollection(Collection<PagoCompraDetalleCheque> PagoCompraDetalleChequeCollection) {
        this.PagoCompraDetalleChequeCollection = PagoCompraDetalleChequeCollection;
    }

}
