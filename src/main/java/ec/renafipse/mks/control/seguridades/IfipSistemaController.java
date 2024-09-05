package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.IfipSistema;
import ec.renafipse.mks.negocio.seguridades.IfipSistemaFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "ifipSistemaController")
@ViewScoped
public class IfipSistemaController extends AbstractController<IfipSistema> implements Serializable {

    @EJB
    private IfipSistemaFacade ejbFacade;

    private List<IfipSistema> itemsIfipSistema;
    private IfipSistema ifipSistemaSel;

    public IfipSistemaController() {
        super(IfipSistema.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.itemsIfipSistema = this.ejbFacade.findAll();
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getIfipSistemaPK().setCodigoSistema(this.getSelected().getSistema().getCodigo());
        this.getSelected().getIfipSistemaPK().setCodigoIfip(this.getSelected().getIfip().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setIfipSistemaPK(new ec.renafipse.mks.modelo.seguridades.IfipSistemaPK());
    }

    /**
     *
     * @param event
     */
    public void nuevo(ActionEvent event) {
        this.setSelected(new IfipSistema());
        this.getSelected().setFechaRegistro(new Date());
    }

    public void creaIfipSistema(ActionEvent event) {
        boolean registrado = false;        
        this.initializeEmbeddableKey();
        this.setEmbeddableKeys();
        for (IfipSistema is : this.itemsIfipSistema) {
            if (is.getIfipSistemaPK().getCodigoIfip() == this.getSelected().getIfipSistemaPK().getCodigoIfip()
                    && is.getIfipSistemaPK().getCodigoSistema() == this.getSelected().getIfipSistemaPK().getCodigoSistema()) {
                registrado = true;
                break;
            }
        }
        if (registrado == true) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SistemaExisteIfip");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.getSelected().getRegistradoPor().setCodigo(this.getSelected().getRegistradoPor().getCodigo());
            this.getSelected().setFechaRegistro(this.getSelected().getFechaRegistro());
            this.getSelected().setEliminado(this.getSelected().getEliminado());
            this.ejbFacade.create(this.getSelected());
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
            MuestraMensaje.addSatisfactorio(msg);
            this.itemsIfipSistema = this.ejbFacade.findAll();
        }
    }

    public void editaIfipSistema(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.setEmbeddableKeys();
                this.getSelected().getRegistradoPor().setCodigo(this.getSelected().getRegistradoPor().getCodigo());
                this.getSelected().setFechaRegistro(this.getSelected().getFechaRegistro());
                this.getSelected().setEliminado(this.getSelected().getEliminado());
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
                MuestraMensaje.addSatisfactorio(msg);
                this.setItemsIfipSistema(this.ejbFacade.findAll());
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"ifipSistemaController", "editaIfipSistema", CapturaError.getErrorException(ex)});
            }
        }
    }

    /**
     *
     * @param event
     */
    public void eliminaIfipSistema(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setEliminado('S');
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
                this.setItemsIfipSistema(this.ejbFacade.findAll());
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"ifipSistemaController", "eliminaIfipSistema", CapturaError.getErrorException(ex)});
            }
        }
    }

    /**
     * @return the itemsIfipSistema
     */
    public List<IfipSistema> getItemsIfipSistema() {
        return itemsIfipSistema;
    }

    /**
     * @param itemsIfipSistema the itemsIfipSistema to set
     */
    public void setItemsIfipSistema(List<IfipSistema> itemsIfipSistema) {
        this.itemsIfipSistema = itemsIfipSistema;
    }

    /**
     * @return the ifipSistemaSel
     */
    public IfipSistema getIfipSistemaSel() {
        return ifipSistemaSel;
    }

    /**
     * @param ifipSistemaSel the ifipSistemaSel to set
     */
    public void setIfipSistemaSel(IfipSistema ifipSistemaSel) {
        this.ifipSistemaSel = ifipSistemaSel;
    }

}
