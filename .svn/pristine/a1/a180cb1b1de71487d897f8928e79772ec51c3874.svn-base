/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_IFIPS.IFIP_AGENCIA_JORNADA_LABORAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfipAgenciaJornadaLaboral.findAll", query = "SELECT i FROM IfipAgenciaJornadaLaboral i"),
    @NamedQuery(name = "IfipAgenciaJornadaLaboral.findByDia", query = "SELECT i FROM IfipAgenciaJornadaLaboral i WHERE i.ifipAgenciaJornadaLaboralPK.dia = :dia"),
    @NamedQuery(name = "IfipAgenciaJornadaLaboral.findByMeridiano", query = "SELECT i FROM IfipAgenciaJornadaLaboral i WHERE i.ifipAgenciaJornadaLaboralPK.meridiano = :meridiano"),
    @NamedQuery(name = "IfipAgenciaJornadaLaboral.findByCodigoIfipAgencia", query = "SELECT i FROM IfipAgenciaJornadaLaboral i WHERE i.ifipAgenciaJornadaLaboralPK.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "IfipAgenciaJornadaLaboral.findByHoraInicial", query = "SELECT i FROM IfipAgenciaJornadaLaboral i WHERE i.horaInicial = :horaInicial"),
    @NamedQuery(name = "IfipAgenciaJornadaLaboral.findByHoraFinal", query = "SELECT i FROM IfipAgenciaJornadaLaboral i WHERE i.horaFinal = :horaFinal"),
    @NamedQuery(name = "IfipAgenciaJornadaLaboral.findByEliminado", query = "SELECT i FROM IfipAgenciaJornadaLaboral i WHERE i.eliminado = :eliminado")})
public class IfipAgenciaJornadaLaboral implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IfipAgenciaJornadaLaboralPK ifipAgenciaJornadaLaboralPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_INICIAL")
    private short horaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_FINAL")
    private short horaFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipAgencia ifipAgencia;

    public IfipAgenciaJornadaLaboral() {
    }

    public IfipAgenciaJornadaLaboral(IfipAgenciaJornadaLaboralPK ifipAgenciaJornadaLaboralPK) {
        this.ifipAgenciaJornadaLaboralPK = ifipAgenciaJornadaLaboralPK;
    }

    public IfipAgenciaJornadaLaboral(IfipAgenciaJornadaLaboralPK ifipAgenciaJornadaLaboralPK, short horaInicial, short horaFinal, char eliminado) {
        this.ifipAgenciaJornadaLaboralPK = ifipAgenciaJornadaLaboralPK;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.eliminado = eliminado;
    }

    public IfipAgenciaJornadaLaboral(short dia, char meridiano, long codigoIfipAgencia) {
        this.ifipAgenciaJornadaLaboralPK = new IfipAgenciaJornadaLaboralPK(dia, meridiano, codigoIfipAgencia);
    }

    public IfipAgenciaJornadaLaboralPK getIfipAgenciaJornadaLaboralPK() {
        return ifipAgenciaJornadaLaboralPK;
    }

    public void setIfipAgenciaJornadaLaboralPK(IfipAgenciaJornadaLaboralPK ifipAgenciaJornadaLaboralPK) {
        this.ifipAgenciaJornadaLaboralPK = ifipAgenciaJornadaLaboralPK;
    }

    public short getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(short horaInicial) {
        this.horaInicial = horaInicial;
    }

    public short getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(short horaFinal) {
        this.horaFinal = horaFinal;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ifipAgenciaJornadaLaboralPK != null ? ifipAgenciaJornadaLaboralPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfipAgenciaJornadaLaboral)) {
            return false;
        }
        IfipAgenciaJornadaLaboral other = (IfipAgenciaJornadaLaboral) object;
        if ((this.ifipAgenciaJornadaLaboralPK == null && other.ifipAgenciaJornadaLaboralPK != null) || (this.ifipAgenciaJornadaLaboralPK != null && !this.ifipAgenciaJornadaLaboralPK.equals(other.ifipAgenciaJornadaLaboralPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.IfipAgenciaJornadaLaboral[ ifipAgenciaJornadaLaboralPK=" + ifipAgenciaJornadaLaboralPK + " ]";
    }
    
}
