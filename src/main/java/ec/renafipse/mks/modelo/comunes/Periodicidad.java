/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.comunes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "MKS_COMUNES.PERIODICIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodicidad.findAll", query = "SELECT p FROM Periodicidad p"),
    @NamedQuery(name = "Periodicidad.findByCodigo", query = "SELECT p FROM Periodicidad p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Periodicidad.findByNombre", query = "SELECT p FROM Periodicidad p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Periodicidad.findBySiglas", query = "SELECT p FROM Periodicidad p WHERE p.siglas = :siglas"),
    @NamedQuery(name = "Periodicidad.findByEquivalenciaDias", query = "SELECT p FROM Periodicidad p WHERE p.equivalenciaDias = :equivalenciaDias"),
    @NamedQuery(name = "Periodicidad.findByEquivalenciaMeses", query = "SELECT p FROM Periodicidad p WHERE p.equivalenciaMeses = :equivalenciaMeses"),
    @NamedQuery(name = "Periodicidad.findByEliminado", query = "SELECT p FROM Periodicidad p WHERE p.eliminado = :eliminado"),
    //PERSONALIZADOS
    @NamedQuery(name = "Periodicidad.findByEsParaDpfEliminado", query = "SELECT p FROM Periodicidad p WHERE p.esParaDpf = :esParaDpf AND p.eliminado = :eliminado ORDER BY p.nombre"),
    @NamedQuery(name = "Periodicidad.findByEsParaSolicitudSocioEliminado", query = "SELECT p FROM Periodicidad p WHERE p.esParaSolicitudSocio = :esParaSolicitudSocio AND p.eliminado = :eliminado ORDER BY p.nombre")
            })
public class Periodicidad implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByEliminado = "Periodicidad.findByEliminado";
    public static String findByEsParaDpfEliminado = "Periodicidad.findByEsParaDpfEliminado";
    public static String findByEsParaSolicitudSocioEliminado = "Periodicidad.findByEsParaSolicitudSocioEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PERIODICIDAD")
    @SequenceGenerator(name = "GSQ_PERIODICIDAD", schema = "MKS_COMUNES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_PERIODICIDAD")    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EQUIVALENCIA_DIAS")
    private long equivalenciaDias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EQUIVALENCIA_MESES")
    private long equivalenciaMeses;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PARA_DPF")
    private char esParaDpf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PARA_SOLICITUD_SOCIO")
    private char esParaSolicitudSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PARA_CREDITO")
    private char esParaCredito;

    public Periodicidad() {
    }

    public Periodicidad(Long codigo) {
        this.codigo = codigo;
    }

    public Periodicidad(Long codigo, String nombre, String siglas, long equivalenciaDias, long equivalenciaMeses, char eliminado, char esParaDpf, char esParaSolicitudSocio, char esParaCredito) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
        this.equivalenciaDias = equivalenciaDias;
        this.equivalenciaMeses = equivalenciaMeses;
        this.eliminado = eliminado;
        this.esParaDpf = esParaDpf;
        this.esParaSolicitudSocio = esParaSolicitudSocio;
        this.esParaCredito = esParaCredito;
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

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public long getEquivalenciaDias() {
        return equivalenciaDias;
    }

    public void setEquivalenciaDias(long equivalenciaDias) {
        this.equivalenciaDias = equivalenciaDias;
    }

    public long getEquivalenciaMeses() {
        return equivalenciaMeses;
    }

    public void setEquivalenciaMeses(long equivalenciaMeses) {
        this.equivalenciaMeses = equivalenciaMeses;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public char getEsParaDpf() {
        return esParaDpf;
    }

    public void setEsParaDpf(char esParaDpf) {
        this.esParaDpf = esParaDpf;
    }

    public char getEsParaSolicitudSocio() {
        return esParaSolicitudSocio;
    }

    public void setEsParaSolicitudSocio(char esParaSolicitudSocio) {
        this.esParaSolicitudSocio = esParaSolicitudSocio;
    }

    public char getEsParaCredito() {
        return esParaCredito;
    }

    public void setEsParaCredito(char esParaCredito) {
        this.esParaCredito = esParaCredito;
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
        if (!(object instanceof Periodicidad)) {
            return false;
        }
        Periodicidad other = (Periodicidad) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.comunes.Periodicidad[ codigo=" + codigo + " ]";
    }
}