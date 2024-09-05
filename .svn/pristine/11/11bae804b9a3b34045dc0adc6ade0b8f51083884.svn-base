/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.CLIENTE_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteIfip.findAll", query = "SELECT c FROM ClienteIfip c"),
    @NamedQuery(name = "ClienteIfip.findByCodigoCliente", query = "SELECT c FROM ClienteIfip c WHERE c.clienteIfipPK.codigoCliente = :codigoCliente"),
    @NamedQuery(name = "ClienteIfip.findByCodigoIfip", query = "SELECT c FROM ClienteIfip c WHERE c.clienteIfipPK.codigoIfip = :codigoIfip ORDER BY c.cliente.persona.nombreCompleto"),
    @NamedQuery(name = "ClienteIfip.findByFechaRegistro", query = "SELECT c FROM ClienteIfip c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ClienteIfip.findByRegistradoPor", query = "SELECT c FROM ClienteIfip c WHERE c.registradoPor = :registradoPor"),
    @NamedQuery(name = "ClienteIfip.findByEliminado", query = "SELECT c FROM ClienteIfip c WHERE c.eliminado = :eliminado"),
    //PERSONALIZADOS
    @NamedQuery(name = "ClienteIfip.findByIdentificacionPersonaCodigoIfip", query = "SELECT c FROM ClienteIfip c WHERE c.clienteIfipPK.codigoIfip = :codigoIfip AND c.cliente.persona.identificacion = :identificacion"),
    @NamedQuery(name = "ClienteIfip.findByNombrePersonaCodigoIfip", query = "SELECT c FROM ClienteIfip c WHERE c.clienteIfipPK.codigoIfip = :codigoIfip AND c.cliente.persona.nombreCompleto LIKE :nombreCompleto")
})
public class ClienteIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoIfip ="ClienteIfip.findByCodigoIfip";
    public static final String findByIdentificacionPersonaCodigoIfip ="ClienteIfip.findByIdentificacionPersonaCodigoIfip";
    public static final String findByNombrePersonaCodigoIfip ="ClienteIfip.findByNombrePersonaCodigoIfip";
    @EmbeddedId
    protected ClienteIfipPK clienteIfipPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIfip")
    private Collection<Venta> ventaCollection;
    @JoinColumn(name = "CODIGO_CLIENTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public ClienteIfip() {
    }

    public ClienteIfip(ClienteIfipPK clienteIfipPK) {
        this.clienteIfipPK = clienteIfipPK;
    }

    public ClienteIfip(ClienteIfipPK clienteIfipPK, Date fechaRegistro, long registradoPor, char eliminado) {
        this.clienteIfipPK = clienteIfipPK;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
        this.eliminado = eliminado;
    }

    public ClienteIfip(long codigoCliente, long codigoIfip) {
        this.clienteIfipPK = new ClienteIfipPK(codigoCliente, codigoIfip);
    }

    public ClienteIfipPK getClienteIfipPK() {
        return clienteIfipPK;
    }

    public void setClienteIfipPK(ClienteIfipPK clienteIfipPK) {
        this.clienteIfipPK = clienteIfipPK;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Venta> getVentaCollection() {
        return ventaCollection;
    }

    public void setVentaCollection(Collection<Venta> ventaCollection) {
        this.ventaCollection = ventaCollection;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteIfipPK != null ? clienteIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteIfip)) {
            return false;
        }
        ClienteIfip other = (ClienteIfip) object;
        if ((this.clienteIfipPK == null && other.clienteIfipPK != null) || (this.clienteIfipPK != null && !this.clienteIfipPK.equals(other.clienteIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adqusiciones.ClienteIfip[ clienteIfipPK=" + clienteIfipPK + " ]";
    }
    
}
