/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ifips;

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
@Table(name = "MKS_IFIPS.COMPUTADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Computador.findAll", query = "SELECT c FROM Computador c"),
    @NamedQuery(name = "Computador.findByCodigo", query = "SELECT c FROM Computador c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Computador.findByNombre", query = "SELECT c FROM Computador c WHERE c.nombre = :nombre "),
    @NamedQuery(name = "Computador.findByRutaImpresora", query = "SELECT c FROM Computador c WHERE c.rutaImpresora = :rutaImpresora"),
    @NamedQuery(name = "Computador.findByDireccionIp", query = "SELECT c FROM Computador c WHERE c.direccionIp = :direccionIp"),
    @NamedQuery(name = "Computador.findByDireccionMac", query = "SELECT c FROM Computador c WHERE c.direccionMac = :direccionMac"),
    @NamedQuery(name = "Computador.findByEliminado", query = "SELECT c FROM Computador c WHERE c.eliminado = :eliminado and c.codigoIfip.codigo = :codigoIfip"),
    //Personalizados
    @NamedQuery(name = "Computador.findByIfipTipoCoputadorEliminado", query = "SELECT c FROM Computador c join c.codigoTipoComputador t WHERE c.codigoIfip.codigo = :codigoIfip AND c.eliminado = :eliminado"),
    @NamedQuery(name = "Computador.findByIfipAgenciaDestinoEliminado", query = "SELECT c FROM Computador c WHERE c.codigoIfip.codigo = :codigoIfip AND c.codigoIfipAgenciaPertenece.codigo = :codigoIfipAgencia AND c.codigoDestinoComputador.codigo = :codigoDestino AND c.eliminado = :eliminado"),
    @NamedQuery(name = "Computador.findByDireccionIpIfipEliminado", query = "SELECT c FROM Computador c WHERE c.codigoIfip.codigo =:codigoIfip AND c.direccionIp = :direccionIp AND c.eliminado = :eliminado"),
    @NamedQuery(name = "Computador.findByIfipAgenciaEliminado", query = "SELECT c FROM Computador c WHERE c.codigoIfip.codigo =:codigoIfip AND c.codigoIfipAgenciaPertenece.codigo = :codigoIfipAgencia AND c.eliminado = :eliminado"),
    @NamedQuery(name = "Computador.findByIfipComputador", query = "SELECT c FROM Computador c WHERE c.codigoIfip.codigo =:codigoIfip"),
    @NamedQuery(name = "Computador.findByCodigoComputadorIfiAgenPerEli", query = "SELECT c FROM Computador c WHERE c.codigo = :codigoComputador AND c.codigoIfip.codigo =:codigoIfip AND c.codigoIfipAgenciaPertenece.codigo = :codigoIfipAgenciaPertenece  AND c.eliminado = :eliminado")})

public class Computador implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByIfipAgenciaDestinoEliminado = "Computador.findByIfipAgenciaDestinoEliminado";
    public static final String findByDireccionIpIfipEliminado = "Computador.findByDireccionIpIfipEliminado";
    public static final String findByIfipAgenciaEliminado = "Computador.findByIfipAgenciaEliminado";
    public static final String findByIfipTipoComputadorEliminado = "Computador.findByIfipTipoCoputadorEliminado";
    public static final String findByIfipComputador = "Computador.findByIfipComputador";
    public static final String findByCodigoComputadorIfiAgenPerEli = "Computador.findByCodigoComputadorIfiAgenPerEli";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_COMPUTADOR")
    @SequenceGenerator(name = "GSQ_COMPUTADOR", schema = "MKS_IFIPS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_COMPUTADOR")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RUTA_IMPRESORA")
    private String rutaImpresora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DIRECCION_IP")
    private String direccionIp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DIRECCION_MAC")
    private String direccionMac;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TIPO_SISTEMA_OPERATIVO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoSistemaOperativo codigoTipoSistemaOperativo;
    @JoinColumn(name = "CODIGO_TIPO_COMPUTADOR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoComputador codigoTipoComputador;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA_PERTENECE", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private IfipAgencia codigoIfipAgenciaPertenece;
//    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO",nullable = false)
//    @ManyToOne(optional = false)
//    private Ifip codigoIfip;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", nullable = false)
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
      // cambiado por santiago

    @JoinColumn(name = "CODIGO_DESTINO_COMPUTADOR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private DestinoComputador codigoDestinoComputador;

    public Computador() {
    }

    public Computador(Long codigo) {
        this.codigo = codigo;
    }

    public Computador(Long codigo, String nombre, String rutaImpresora, String direccionIp, String direccionMac, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.rutaImpresora = rutaImpresora;
        this.direccionIp = direccionIp;
        this.direccionMac = direccionMac;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaImpresora() {
        return rutaImpresora;
    }

    public void setRutaImpresora(String rutaImpresora) {
        this.rutaImpresora = rutaImpresora;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public String getDireccionMac() {
        return direccionMac;
    }

    public void setDireccionMac(String direccionMac) {
        this.direccionMac = direccionMac;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public TipoSistemaOperativo getCodigoTipoSistemaOperativo() {
        return codigoTipoSistemaOperativo;
    }

    public void setCodigoTipoSistemaOperativo(TipoSistemaOperativo codigoTipoSistemaOperativo) {
        this.codigoTipoSistemaOperativo = codigoTipoSistemaOperativo;
    }

    public TipoComputador getCodigoTipoComputador() {
        return codigoTipoComputador;
    }

    public void setCodigoTipoComputador(TipoComputador codigoTipoComputador) {
        this.codigoTipoComputador = codigoTipoComputador;
    }

    public IfipAgencia getCodigoIfipAgenciaPertenece() {
        return codigoIfipAgenciaPertenece;
    }

    public void setCodigoIfipAgenciaPertenece(IfipAgencia codigoIfipAgenciaPertenece) {
        this.codigoIfipAgenciaPertenece = codigoIfipAgenciaPertenece;
    }

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public DestinoComputador getCodigoDestinoComputador() {
        return codigoDestinoComputador;
    }

    public void setCodigoDestinoComputador(DestinoComputador codigoDestinoComputador) {
        this.codigoDestinoComputador = codigoDestinoComputador;
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
        if (!(object instanceof Computador)) {
            return false;
        }
        Computador other = (Computador) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.Computador[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoIfip
     */
}
