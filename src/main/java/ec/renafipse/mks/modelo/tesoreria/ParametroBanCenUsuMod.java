/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.tesoreria;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
 * @author andres
 */
@Entity
@Table(name = "MKS_TESORERIA.PARAMETRO_BAN_CEN_USU_MOD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroBanCenUsuMod.findByCodigoParametro", query = "SELECT p FROM ParametroBanCenUsuMod p WHERE p.codigoParametroBanCen.codigo = :codigoParametro")})
public class ParametroBanCenUsuMod implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PARAMETRO_BAN_CEN_USU_MOD")
    @SequenceGenerator(name = "GSQ_PARAMETRO_BAN_CEN_USU_MOD", schema = "MKS_TESORERIA",  allocationSize = 0, sequenceName = "SEQ_PARAMETRO_BAN_CEN_USU_MOD")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ACCION")
    private String accion;
    @JoinColumn(name = "CODIGO_PARAMETRO_BAN_CEN", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ParametroBancoCentral codigoParametroBanCen;

    public ParametroBanCenUsuMod() {
    }

    public ParametroBanCenUsuMod(Long codigo) {
        this.codigo = codigo;
    }

    public ParametroBanCenUsuMod(Long codigo, Long codigoUsuario, Date fecha, String accion, ParametroBancoCentral codigoParametroBanCen) {
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.fecha = fecha;
        this.accion = accion;
        this.codigoParametroBanCen = codigoParametroBanCen;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public ParametroBancoCentral getCodigoParametroBanCen() {
        return codigoParametroBanCen;
    }

    public void setCodigoParametroBanCen(ParametroBancoCentral codigoParametroBanCen) {
        this.codigoParametroBanCen = codigoParametroBanCen;
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
        if (!(object instanceof ParametroBanCenUsuMod)) {
            return false;
        }
        ParametroBanCenUsuMod other = (ParametroBanCenUsuMod) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.tesoreria.ParametroBanCenUsuMod[ codigo=" + codigo + " ]";
    }
    
}
