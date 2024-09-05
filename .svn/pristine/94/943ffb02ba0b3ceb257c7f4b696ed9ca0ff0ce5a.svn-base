/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.adquisicones;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.adquisiciones.Compra;
import ec.renafipse.mks.negocio.adquisiciones.CompraFacade;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author vastudillo
 */
@Named(value = "compraController")
@ViewScoped
public class CompraController extends AbstractController<Compra> implements Serializable {

    @EJB
    private CompraFacade ejbFacade;

    // VARIABLES PERSONALIZADAS
    private char buscarPor;
    private String criterio;
    private Date fechaInicio;
    private Date fechaFin;
    private Compra compraSel;

    private boolean deshabilitaBotonBuscar;
    private boolean deshabilitaCodigoProveedor;

    private List<Compra> itemsCompra;

    /**
     * Initialize the concrete Proveedor controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setBuscarPor(' ');
        this.criterio = null;
        this.fechaFin = null;
        this.fechaInicio = null;
        this.compraSel = new Compra();
        //setListaItemsProveedor(ejbFacade.getItemsProveedor());
    }

    public CompraController() {
        // Inform the Abstract parent controller of the concrete Proveedor?cap_first Entity
        super(Compra.class);
    }

    public void obtieneCompras() {
        compraSel=this.ejbFacade.find(Long.parseLong("1"));
        //System.out.println("compra: "+ compraSel.getObservaciones());
        //System.out.println("ENTRA: "+ buscarPor);
        if (String.valueOf(this.getBuscarPor()).equals("P")) {
            //System.out.println("esta buscando por Proveedor");
        } else {
            //System.out.println("------------------------------------  " + getFechaInicio() + "  " + getFechaFin() + " " + ActivacionUsuario.getCodigoIfip());
            this.itemsCompra = this.ejbFacade.getItemsCompraFecha(ActivacionUsuario.getCodigoIfip(), this.getFechaInicio(), this.getFechaFin());

        }
    }

    /**
     * @return the criterio
     */
    public String getCriterio() {
        return criterio;
    }

    /**
     * @param criterio the criterio to set
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;

    }

    /**
     * @return the itemsCompra
     */
    public List<Compra> getItemsCompra() {
        return itemsCompra;
    }

    /**
     * @param itemsCompra the itemsCompra to set
     */
    public void setItemsCompra(List<Compra> itemsCompra) {
        this.itemsCompra = itemsCompra;
    }

    /**
     * @return the compraSel
     */
    public Compra getCompraSel() {
        return compraSel;
    }

    /**
     * @param compraSel the compraSel to set
     */
    public void setCompraSel(Compra compraSel) {
        this.compraSel = compraSel;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @return the deshabilitaBotonBuscar
     */
    public boolean isDeshabilitaBotonBuscar() {
        return deshabilitaBotonBuscar;
    }

    /**
     * @param deshabilitaBotonBuscar the deshabilitaBotonBuscar to set
     */
    public void setDeshabilitaBotonBuscar(boolean deshabilitaBotonBuscar) {
        this.deshabilitaBotonBuscar = deshabilitaBotonBuscar;
    }

    /**
     * @return the deshabilitaCodigoProveedor
     */
    public boolean isDeshabilitaCodigoProveedor() {
        return deshabilitaCodigoProveedor;
    }

    /**
     * @param deshabilitaCodigoProveedor the deshabilitaCodigoProveedor to set
     */
    public void setDeshabilitaCodigoProveedor(boolean deshabilitaCodigoProveedor) {
        this.deshabilitaCodigoProveedor = deshabilitaCodigoProveedor;
    }

    /**
     * @return the buscarPor
     */
    public char getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor the buscarPor to set
     */
    public void setBuscarPor(char buscarPor) {
        this.buscarPor = buscarPor;
    }

}
