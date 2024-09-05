/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.dpfs;

import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Willan Jara 30 de Noviembre de 2017
 */
@Entity
@Table(name = "MKS_DPFS.REGISTRO_AGENCIA_SOC_DPF")
@XmlRootElement
public class RegistroAgenciaSocioDPF {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_REGISTRO_AGENCIA_SOC_DPF")
    @SequenceGenerator(name = "GSQ_REGISTRO_AGENCIA_SOC_DPF", schema = "MKS_DPFS", allocationSize = 0, sequenceName = "SEQ_REGISTRO_AGENCIA_SOC_DPF")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;

    @Basic(optional = true)
    @Column(name = "DESCRIPCION")
    private String descripcion;

   

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_REGISTRA")
    private Long codigoUsuarioRegistra;
    
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_AGENCIA_REGISTRO")
    private Long codigoAgenciaRegistro;      

    @Column(name = "CODIGO_PERSONA_RECOMIENDA", nullable = true)
    @Basic(optional = true)
    private Long codigoPersonaRecomienda;

    
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_AGENCIA_ORIGEN")
    private Long codigoAgenciaOrigen;
            

    @Column(name = "FECHA_REGISTRO")
    private java.util.Date fechaRegistro;

    @JoinColumns({
        @JoinColumn(name = "CODIGO_DPF", referencedColumnName = "CODIGO", insertable = false, updatable = false)
        ,
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ContratoDpf contratoDpf;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private Long codigoIFIP;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_DPF")
    private long codigoDPF;

    /**
     *
     * @return devuelve la clave primaria o codigo unico
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     *
     * @param codigo
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @return devuelve al descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

    

    /**
     *
     * @return devuelve el codigo de la persona que recomienda
     */
    public Long getCodigoPersonaRecomienda() {
        return codigoPersonaRecomienda;
    }

    /**
     *
     * @param codigoPersonaRecomienda
     */
    public void setCodigoPersonaRecomienda(Long codigoPersonaRecomienda) {
        this.codigoPersonaRecomienda = codigoPersonaRecomienda;
    }

   

    /**
     *
     * @return devuelve la fecha de registro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     *
     * @param fechaRegistro
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     *
     * @return devuelve el contrato del DPF
     */
    public ContratoDpf getContratoDpf() {
        return contratoDpf;
    }

    /**
     *
     * @param contratoDpf
     */
    public void setContratoDpf(ContratoDpf contratoDpf) {
        this.contratoDpf = contratoDpf;
    }

    /**
     *
     * @return devuelve el codigo de la institucion financiera
     */
    public Long getCodigoIFIP() {
        return codigoIFIP;
    }

    /**
     *
     * @param codigoIFIP
     */
    public void setCodigoIFIP(Long codigoIFIP) {
        this.codigoIFIP = codigoIFIP;
    }

    /**
     *
     * @return devuelve el codigo del DPF
     */
    public long getCodigoDPF() {
        return codigoDPF;
    }

    /**
     *
     * @param codigoDPF
     */
    public void setCodigoDPF(long codigoDPF) {
        this.codigoDPF = codigoDPF;
    }

    /**
     *
     * @return devuelve el codigo del usaurio que registra
     */
    public Long getCodigoUsuarioRegistra() {
        return codigoUsuarioRegistra;
    }

    /**
     *
     * @param codigoUsuarioRegistra
     */
    public void setCodigoUsuarioRegistra(Long codigoUsuarioRegistra) {
        this.codigoUsuarioRegistra = codigoUsuarioRegistra;
    }

    /**
     *
     * @return devuelve el codigo de la agencia de registro
     */
    public Long getCodigoAgenciaRegistro() {
        return codigoAgenciaRegistro;
    }

    /**
     *
     * @param codigoAgenciaRegistro
     */
    public void setCodigoAgenciaRegistro(Long codigoAgenciaRegistro) {
        this.codigoAgenciaRegistro = codigoAgenciaRegistro;
    }

    /**
     *
     * @return devuelve el codigo de la agencia de origen
     */
    public Long getCodigoAgenciaOrigen() {
        return codigoAgenciaOrigen;
    }

    /**
     *
     * @param codigoAgenciaOrigen
     */
    public void setCodigoAgenciaOrigen(Long codigoAgenciaOrigen) {
        this.codigoAgenciaOrigen = codigoAgenciaOrigen;
    }

    
    
}
