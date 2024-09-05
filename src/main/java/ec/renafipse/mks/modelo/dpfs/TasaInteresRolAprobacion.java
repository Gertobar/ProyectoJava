/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.dpfs;

import ec.renafipse.mks.modelo.seguridades.Rol;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Willan Jara
 */
@Entity
@Table(name = "MKS_DPFS.TASA_INTERES_ROL_APR")
@XmlRootElement
public class TasaInteresRolAprobacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TASA_INTERES_ROL_APR")
    @SequenceGenerator(name = "GSQ_TASA_INTERES_ROL_APR", schema = "MKS_DPFS", allocationSize = 0, sequenceName = "SEQ_TASA_INTERES_ROL_APR ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "TASA_MAXIMA")
    private BigDecimal tasaMaxima;
                
    @JoinColumn(name = "ROL_APRUEBA",referencedColumnName = "CODIGO", insertable = false, updatable = false)            
    @ManyToOne(optional = false)
    private Rol rolAprueba;
    
    
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    
    @Column(name = "PLAZO_DIAS_MINIMO")
    private long plazoDiasMinimo;
    
    @Column(name = "MONTO_MINIMO")
    private BigDecimal montoMinimo;
    
    /**
     *
     */
    public TasaInteresRolAprobacion(){
        
    }

    /**
     *
     * @param codigo
     * @param tasaMaxima
     * @param rolAprueba
     * @param codigoUsuario
     */
    public TasaInteresRolAprobacion(Long codigo, BigDecimal tasaMaxima, Rol rolAprueba, Long codigoUsuario) {
        this.codigo = codigo;
        this.tasaMaxima = tasaMaxima;
        this.rolAprueba = rolAprueba;
        this.codigoUsuario = codigoUsuario;
    }
           
    /**
     *
     * @return
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
     * @return
     */
    public BigDecimal getTasaMaxima() {
        return tasaMaxima;
    }

    /**
     *
     * @param tasaMaxima
     */
    public void setTasaMaxima(BigDecimal tasaMaxima) {
        this.tasaMaxima = tasaMaxima;
    }

    /**
     *
     * @return
     */
    public Rol getRolAprueba() {
        return rolAprueba;
    }

    /**
     *
     * @param rolAprueba
     */
    public void setRolAprueba(Rol rolAprueba) {
        this.rolAprueba = rolAprueba;
    }                 

    /**
     *
     * @return
     */
    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    /**
     *
     * @param codigoUsuario
     */
    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
            
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }
          
    @Override
    public boolean equals(Object object) {        
        if (!(object instanceof TasaInteresRolAprobacion)) {
            return false;
        }
        TasaInteresRolAprobacion other = (TasaInteresRolAprobacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return devuelve el plazo minimo de dias
     */
    public long getPlazoDiasMinimo() {
        return plazoDiasMinimo;
    }

    /**
     *
     * @param plazoDiasMinimo
     */
    public void setPlazoDiasMinimo(long plazoDiasMinimo) {
        this.plazoDiasMinimo = plazoDiasMinimo;
    }

    /**
     *
     * @return devuelve el monto minimo
     */
    public BigDecimal getMontoMinimo() {
        return montoMinimo;
    }

    /**
     *
     * @param montoMinimo
     */
    public void setMontoMinimo(BigDecimal montoMinimo) {
        this.montoMinimo = montoMinimo;
    }
                
    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs[ codigo=" + codigo + " ]";
    }
}
