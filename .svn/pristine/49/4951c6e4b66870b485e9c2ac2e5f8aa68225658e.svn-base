/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.dpfs;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.dpfs.ContratoDpf;
import ec.renafipse.mks.modelo.dpfs.CuentaContratoDpf;
import ec.renafipse.mks.modelo.dpfs.CuentaContratoDpfPK;
import ec.renafipse.mks.negocio.dpfs.ContratoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.CuentaContratoDpfFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "anularContratoDpfController")
@ViewScoped
public class AnularContratoDpf extends AbstractController<ContratoDpf> {

    @EJB
    private ContratoDpfFacade ejbFacade;

    @EJB
    private CuentaContratoDpfFacade ejbFacadeCuentaContratoDpf;

    private LlamaSP llamaSP;

    //--------------------------------------------------------
    // PERSONALIZADOS
    private String msg;

    private ContratoDpf contratoDpfSel;

    private List<ContratoDpf> itemsContratoDpf;

    /**
     * Initialize the concrete ContratoDpf controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     *
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.obtieneDpfs();
        this.contratoDpfSel = null;
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();

    }

    public AnularContratoDpf() {
        // Inform the Abstract parent controller of the concrete PagoCredito?cap_first Entity
        super(ContratoDpf.class);
    }

    /**
     * Obtiene los DPFS de las Personas
     */
    public void obtieneDpfs() {
        this.setItemsContratoDpf(this.ejbFacade.getItemsAnularContrato(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'V', new Date()));

    }

    /**
     * Anula DPF
     *
     * @param actionEvent
     */
    public void anular(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            CuentaContratoDpf cuentaContratoDpf = this.ejbFacadeCuentaContratoDpf.find(new CuentaContratoDpfPK(this.contratoDpfSel.getContratoDpfPK().getCodigo(), this.contratoDpfSel.getContratoDpfPK().getCodigoIfip()));
                ////System.out.println("Guarda Movimiento");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_dpfs.pkm_contrato_dpf.p_anula");
            llamaSP.setNumeroParametros(11);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_contrato", this.contratoDpfSel.getContratoDpfPK().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.contratoDpfSel.getContratoDpfPK().getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.contratoDpfSel.getCodigoMoneda()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_capital", this.contratoDpfSel.getCapital()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tip_pro_deb", cuentaContratoDpf.getCuentaDebito().getProductoIfip().getProductoIfipPK().getCodigoTipoProducto()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_debito", cuentaContratoDpf.getCodigoCuentaDebito()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_plazo_dias", this.contratoDpfSel.getPlazoDias()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_anulado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AnulacionContratoDpfGrabado");
                MuestraMensaje.addInformacion(msg);
                context.execute("procesandoDlg.hide()");
                this.init();

                //context.execute("MovimientoBovedaFor.update()");
            } else {
                context.execute("procesandoDlg.hide()");

                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"anularContratoDpfController", "anular", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * @return the contratoDpfSel
     */
    public ContratoDpf getContratoDpfSel() {
        return contratoDpfSel;
    }

    /**
     * @param contratoDpfSel the contratoDpfSel to set
     */
    public void setContratoDpfSel(ContratoDpf contratoDpfSel) {
        this.contratoDpfSel = contratoDpfSel;
    }

    /**
     * @return the itemsContratoDpf
     */
    public List<ContratoDpf> getItemsContratoDpf() {
        return itemsContratoDpf;
    }

    /**
     * @param itemsContratoDpf the itemsContratoDpf to set
     */
    public void setItemsContratoDpf(List<ContratoDpf> itemsContratoDpf) {
        this.itemsContratoDpf = itemsContratoDpf;
    }
}
