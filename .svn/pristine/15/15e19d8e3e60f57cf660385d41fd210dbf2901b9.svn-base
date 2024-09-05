/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author renafipse1
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.ATS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ats.findAll", query = "SELECT a FROM Ats a"),
    @NamedQuery(name = "Ats.findByCodigo", query = "SELECT a FROM Ats a WHERE a.codigo = :codigo"),    
    @NamedQuery(name = "Ats.findByLinea", query = "SELECT a FROM Ats a WHERE a.linea = :linea"),
    @NamedQuery(name = "Ats.findByFechaCorte", query = "SELECT a FROM Ats a WHERE a.fechaCorte = :fechaCorte AND a.tipo = :tipo")
     })
public class Ats implements Serializable {
    private static final long serialVersionUID = 1L;    
    public static final String findByFechaCorte = "Ats.findByFechaCorte";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Size(max = 1000)
    @Column(name = "LINEA")
    private String linea;
    @Column(name = "FECHA_CORTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCorte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    

    public Ats() {
    }

    public Ats(Long codigo) {
        this.codigo = codigo;
    }

 

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
 

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
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
        if (!(object instanceof Ats)) {
            return false;
        }
        Ats other = (Ats) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.negocio.adquisiciones.Ats[ codigo=" + codigo + " ]";
    }

    /**
     * @return the linea
     */
    public String getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }

    /**
     * @return the tipo
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    
}
