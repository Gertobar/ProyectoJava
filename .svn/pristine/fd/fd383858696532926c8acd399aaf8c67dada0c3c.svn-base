/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguros;

import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.TipoRelacion;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author scordero
 */
@Entity
@Table(name = "MKS_SEGUROS.CONTRATO_SEGURO_DEPENDIENTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoSeguroDependientes.findAll", query = "SELECT c FROM ContratoSeguroDependientes c"),
    @NamedQuery(name = "ContratoSeguroDependientes.findByCodigoSeguro", query = "SELECT c FROM ContratoSeguroDependientes c WHERE c.contratoSeguro.codigo = :codigoSeguro"),
    @NamedQuery(name = "ContratoSeguroDependientes.findByCodigoPersona", query = "SELECT c FROM ContratoSeguroDependientes c WHERE c.dependiente.codigo = :codigoPersona"),
    @NamedQuery(name = "ContratoSeguroDependientes.findByCodigoTipoRelacion", query = "SELECT c FROM ContratoSeguroDependientes c WHERE c.codigoTipoRelacion = :codigoTipoRelacion"),
    @NamedQuery(name = "ContratoSeguroDependientes.findByEliminado", query = "SELECT c FROM ContratoSeguroDependientes c WHERE c.eliminado = :eliminado"),
//PERSONALIZADO
    @NamedQuery(name = "ContratoSeguroDependientes.findByCodigoSeguroEliminado", query = "SELECT c FROM ContratoSeguroDependientes c WHERE c.contratoSeguro.codigo = :codigoSeguro AND c.eliminado = :eliminado")
})
public class ContratoSeguroDependientes implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoSeguroEliminado = "ContratoSeguroDependientes.findByCodigoSeguroEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_RELACION")
    private long codigoTipoRelacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_SEGURO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = true)
    private ContratoSeguro contratoSeguro;    
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Persona dependiente;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_RELACION", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TipoRelacion tipoRelacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 150)
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    public ContratoSeguroDependientes() {
    }

    public ContratoSeguroDependientes(Long codigo) {
        this.codigo=codigo;
    }


    public long getCodigoTipoRelacion() {
        return codigoTipoRelacion;
    }

    public void setCodigoTipoRelacion(long codigoTipoRelacion) {
        this.codigoTipoRelacion = codigoTipoRelacion;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public ContratoSeguro getContratoSeguro() {
        return contratoSeguro;
    }

    public void setContratoSeguro(ContratoSeguro contratoSeguro) {
        this.contratoSeguro = contratoSeguro;
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
        if (!(object instanceof ContratoSeguro)) {
            return false;
        }
        ContratoSeguroDependientes other = (ContratoSeguroDependientes) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguros.ContratoSeguroDependientes[codigo=" + codigo + " ]";
    }

    /**
     * @return the dependiente
     */
    public Persona getDependiente() {
        return dependiente;
    }

    /**
     * @param dependiente the dependiente to set
     */
    public void setDependiente(Persona dependiente) {
        this.dependiente = dependiente;
    }

    /**
     * @return the tipoRelacion
     */
    public TipoRelacion getTipoRelacion() {
        return tipoRelacion;
    }

    /**
     * @param tipoRelacion the tipoRelacion to set
     */
    public void setTipoRelacion(TipoRelacion tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    /**
     * @return the fechaActualizacion
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
}
