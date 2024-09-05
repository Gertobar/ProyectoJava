/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import ec.renafipse.mks.modelo.comunes.OperadoraTelefono;
import ec.renafipse.mks.modelo.comunes.TipoTelefono;
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
@Table(name = "MKS_SOCIOS.PERSONA_TELEFONO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaTelefono.findAll", query = "SELECT p FROM PersonaTelefono p"),
    @NamedQuery(name = "PersonaTelefono.findByCodigo", query = "SELECT p FROM PersonaTelefono p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "PersonaTelefono.findByCodigoTipoTelefono", query = "SELECT p FROM PersonaTelefono p WHERE p.codigoTipoTelefono = :codigoTipoTelefono"),
    @NamedQuery(name = "PersonaTelefono.findByCodigoOperadoraTelefono", query = "SELECT p FROM PersonaTelefono p WHERE p.codigoOperadoraTelefono = :codigoOperadoraTelefono"),
    @NamedQuery(name = "PersonaTelefono.findByNumero", query = "SELECT p FROM PersonaTelefono p WHERE p.numero = :numero"),
    @NamedQuery(name = "PersonaTelefono.findByEliminado", query = "SELECT p FROM PersonaTelefono p WHERE p.eliminado = :eliminado"),
    // Personalizados
    @NamedQuery(name = "PersonaTelefono.findByPersona", query = "SELECT p FROM PersonaTelefono p WHERE p.codigoPersona = :codigoPersona")})
public class PersonaTelefono implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByPersona ="PersonaTelefono.findByPersona";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PERSONA_TELEFONO")
    @SequenceGenerator(name = "GSQ_PERSONA_TELEFONO", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_PERSONA_TELEFONO")
    @Column(name = "CODIGO")
    private Long codigo;        
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "NUMERO")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = true)    
    @Column(name = "RECIBE_NOTIFICACION")
    private String recibeNotificacion;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Persona codigoPersona;
    @JoinColumn(name = "CODIGO_TIPO_TELEFONO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoTelefono codigoTipoTelefono;
    @JoinColumn(name = "CODIGO_OPERADORA_TELEFONO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private OperadoraTelefono codigoOperadoraTelefono;    

    public PersonaTelefono() {
    }

    public PersonaTelefono(Long codigo) {
        this.codigo = codigo;
    }

    public PersonaTelefono(Long codigo, TipoTelefono codigoTipoTelefono, OperadoraTelefono codigoOperadoraTelefono, String numero, char eliminado, String recibeNotificacion) {
        this.codigo = codigo;
        this.codigoTipoTelefono = codigoTipoTelefono;
        this.codigoOperadoraTelefono = codigoOperadoraTelefono;
        this.numero = numero;
        this.eliminado = eliminado;
        this.recibeNotificacion = recibeNotificacion;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public TipoTelefono getCodigoTipoTelefono() {
        return codigoTipoTelefono;
    }

    public void setCodigoTipoTelefono(TipoTelefono codigoTipoTelefono) {
        this.codigoTipoTelefono = codigoTipoTelefono;
    }

    public OperadoraTelefono getCodigoOperadoraTelefono() {
        return codigoOperadoraTelefono;
    }

    public void setCodigoOperadoraTelefono(OperadoraTelefono codigoOperadoraTelefono) {
        this.codigoOperadoraTelefono = codigoOperadoraTelefono;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Persona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Persona codigoPersona) {
        this.codigoPersona = codigoPersona;
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
        if (!(object instanceof PersonaTelefono)) {
            return false;
        }
        PersonaTelefono other = (PersonaTelefono) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaTelefono[ codigo=" + codigo + " ]";
    }

    /**
     * @return the recibeNotificacion
     */
    public String getRecibeNotificacion() {
        return recibeNotificacion;
    }

    /**
     * @param recibeNotificacion the recibeNotificacion to set
     */
    public void setRecibeNotificacion(String recibeNotificacion) {
        this.recibeNotificacion = recibeNotificacion;
    }
    
}