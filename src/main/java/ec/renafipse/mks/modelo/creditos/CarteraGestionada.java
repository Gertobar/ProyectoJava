/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.clase.creditos.GestionCartera;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mascoop
 */
@Entity
@Table(name = "CARTERA_GESTIONADA", schema = "MKS_CREDITOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarteraGestionada.findAll", query = "SELECT c FROM CarteraGestionada c")
    , @NamedQuery(name = "CarteraGestionada.findByCodigoCarteraAsignadaVigente", query = "SELECT c FROM CarteraGestionada c WHERE c.codigoCarteraAsignada = :codigoCarteraAsignada AND c.vigente = 'S'")
    , @NamedQuery(name = "CarteraGestionada.findByCodigo", query = "SELECT c FROM CarteraGestionada c WHERE c.codigo = :codigo")
    })
@NamedNativeQueries({
    @NamedNativeQuery(name="CarteraGestionada.findByNumeroCredito", 
            query= "SELECT *\n" +
"FROM   mks_creditos.cartera_gestionada cg\n" +
"WHERE  cg.codigo_cartera_asignada IN ( SELECT ca.codigo\n" +
"                                       FROM   mks_creditos.cartera_asignada ca\n" +
"                                       WHERE  ca.numero_credito = :numeroCredito\n" +
"                                       AND    ca.codigo_ifip = :codigoIfip )\n" +
"AND    cg.vigente = 'N'\n" +
"ORDER BY cg.fecha DESC" , resultClass=CarteraGestionada.class)
})
public class CarteraGestionada implements Serializable {
    public static String findByCodigoCarteraAsignadaVigente = "CarteraGestionada.findByCodigoCarteraAsignadaVigente";
    public static String findByCodigo = "CarteraGestionada.findByCodigo";
    public static String findByNumeroCredito = "CarteraGestionada.findByNumeroCredito";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_CARTERA_GESTIONADA")
    @SequenceGenerator(name = "GSQ_CARTERA_GESTIONADA", schema = "MKS_CREDITOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_CARTERA_GESTIONADA")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_GESTIONA", nullable = false)
    private long codigoUsuarioGestiona;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(nullable = false, length = 500)
    private String gestion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "GESTION_SATISFACTORIA", nullable = false, length = 1)
    private String gestionSatisfactoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(nullable = false, length = 1)
    private String vigente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CARTERA_ASIGNADA", nullable = false)
    private long codigoCarteraAsignada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_RECUPERADOR", nullable = false)
    private long codigoRecuperador;

    public CarteraGestionada() {
    }

    public CarteraGestionada(Long codigo) {
        this.codigo = codigo;
    }

    public CarteraGestionada(long codigoUsuarioGestiona, Date fecha, String gestion, String gestionSatisfactoria, String vigente, long codigoCarteraAsignada, long codigoRecuperador) {
        this.codigoUsuarioGestiona = codigoUsuarioGestiona;
        this.fecha = fecha;
        this.gestion = gestion;
        this.gestionSatisfactoria = gestionSatisfactoria;
        this.vigente = vigente;
        this.codigoCarteraAsignada = codigoCarteraAsignada;
        this.codigoRecuperador = codigoRecuperador;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoUsuarioGestiona() {
        return codigoUsuarioGestiona;
    }

    public void setCodigoUsuarioGestiona(long codigoUsuarioGestiona) {
        this.codigoUsuarioGestiona = codigoUsuarioGestiona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public String getGestionSatisfactoria() {
        return gestionSatisfactoria;
    }

    public void setGestionSatisfactoria(String gestionSatisfactoria) {
        this.gestionSatisfactoria = gestionSatisfactoria;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public long getCodigoCarteraAsignada() {
        return codigoCarteraAsignada;
    }

    public void setCodigoCarteraAsignada(long codigoCarteraAsignada) {
        this.codigoCarteraAsignada = codigoCarteraAsignada;
    }

    public long getCodigoRecuperador() {
        return codigoRecuperador;
    }

    public void setCodigoRecuperador(long codigoRecuperador) {
        this.codigoRecuperador = codigoRecuperador;
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
        if (!(object instanceof CarteraGestionada)) {
            return false;
        }
        CarteraGestionada other = (CarteraGestionada) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.CarteraGestionada[ codigo=" + codigo + " ]";
    }
    
}
