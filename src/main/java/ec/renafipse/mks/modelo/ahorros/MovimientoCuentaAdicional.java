/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.MOVIMIENTO_CUENTA_ADICIONAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimientoCuentaAdicional.findAll", query = "SELECT m FROM MovimientoCuentaAdicional m"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByCodigoMovimiento", query = "SELECT m FROM MovimientoCuentaAdicional m WHERE m.codigoMovimiento = :codigoMovimiento"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByCodigoUsuario", query = "SELECT m FROM MovimientoCuentaAdicional m WHERE m.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByCodigoIfip", query = "SELECT m FROM MovimientoCuentaAdicional m WHERE m.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByCodigoIfipAgencia", query = "SELECT m FROM MovimientoCuentaAdicional m WHERE m.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByCodigoComputador", query = "SELECT m FROM MovimientoCuentaAdicional m WHERE m.codigoComputador = :codigoComputador"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByFechaSistema", query = "SELECT m FROM MovimientoCuentaAdicional m WHERE m.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByNumeroComprobante", query = "SELECT m FROM MovimientoCuentaAdicional m WHERE m.numeroComprobante = :numeroComprobante"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByImpreso", query = "SELECT m FROM MovimientoCuentaAdicional m WHERE m.impreso = :impreso"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByDireccionIp", query = "SELECT m FROM MovimientoCuentaAdicional m WHERE m.direccionIp = :direccionIp"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByObservaciones", query = "SELECT m FROM MovimientoCuentaAdicional m WHERE m.observaciones = :observaciones"),
    //Personalizado
    @NamedQuery(name = "MovimientoCuentaAdicional.findByNumeroComprobanteIfip", query = "SELECT m FROM MovimientoCuentaAdicional m WHERE m.codigoIfip = :codigoIfip AND  m.numeroComprobante = :numeroComprobante"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByMovimientoAdicionalNoImpreso", query = "SELECT ma FROM MovimientoCuenta m JOIN m.movimientoCuentaAdicional ma WHERE m.codigoCuenta.codigo = :codigoCuenta AND ma.impreso = :impreso ORDER BY m.codigo"),
    @NamedQuery(name = "MovimientoCuentaAdicional.findByMovimientoAdicionalImpreso", query = "SELECT a FROM ImpresionLibretaCuenta i JOIN i.movimientoCuentaAdicional a JOIN a.movimientoCuenta m  WHERE m.codigoCuenta.codigo = :codigoCuenta AND i.impresionLibretaCuentaPK.numeroLibreta = :numeroLibreta AND a.impreso = :impreso   ORDER BY m.codigo")
})
public class MovimientoCuentaAdicional implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String  findByNumeroComprobanteIfip="MovimientoCuentaAdicional.findByNumeroComprobanteIfip";
    public static final String findByMovimientoAdicionalNoImpreso = "MovimientoCuentaAdicional.findByMovimientoAdicionalNoImpreso";
    public static final String findByMovimientoAdicionalImpreso= "MovimientoCuentaAdicional.findByMovimientoAdicionalImpreso";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MOVIMIENTO")
    private Long codigoMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPUTADOR")
    private long codigoComputador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Column(name = "NUMERO_COMPROBANTE")
    private String numeroComprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPRESO")
    private char impreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DIRECCION_IP")
    private String direccionIp;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "CODIGO_MOVIMIENTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MovimientoCuenta movimientoCuenta;
    @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private IfipAgencia ifipAgencia;
    @JoinColumn(name = "CODIGO_COMPUTADOR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Computador computador;
    

    public MovimientoCuentaAdicional() {
    }

    public MovimientoCuentaAdicional(Long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public MovimientoCuentaAdicional(Long codigoMovimiento, long codigoUsuario, long codigoIfip, long codigoIfipAgencia, long codigoComputador, Date fechaSistema, String numeroComprobante, char impreso, String direccionIp, String observaciones) {
        this.codigoMovimiento = codigoMovimiento;
        this.codigoUsuario = codigoUsuario;
        this.codigoIfip = codigoIfip;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoComputador = codigoComputador;
        this.fechaSistema = fechaSistema;
        this.numeroComprobante = numeroComprobante;
        this.impreso = impreso;
        this.direccionIp = direccionIp;
        this.observaciones = observaciones;
    }

    public Long getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(Long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoComputador() {
        return codigoComputador;
    }

    public void setCodigoComputador(long codigoComputador) {
        this.codigoComputador = codigoComputador;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public char getImpreso() {
        return impreso;
    }

    public void setImpreso(char impreso) {
        this.impreso = impreso;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public MovimientoCuenta getMovimientoCuenta() {
        return movimientoCuenta;
    }

    public void setMovimientoCuenta(MovimientoCuenta movimientoCuenta) {
        this.movimientoCuenta = movimientoCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMovimiento != null ? codigoMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoCuentaAdicional)) {
            return false;
        }
        MovimientoCuentaAdicional other = (MovimientoCuentaAdicional) object;
        if ((this.codigoMovimiento == null && other.codigoMovimiento != null) || (this.codigoMovimiento != null && !this.codigoMovimiento.equals(other.codigoMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional[ codigoMovimiento=" + codigoMovimiento + " ]";
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the ifipAgencia
     */
    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    /**
     * @param ifipAgencia the ifipAgencia to set
     */
    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }

    /**
     * @return the computador
     */
    public Computador getComputador() {
        return computador;
    }

    /**
     * @param computador the computador to set
     */
    public void setComputador(Computador computador) {
        this.computador = computador;
    }
    
}
