package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.TasaInteresCreditoIfip;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.creditos.TasaInteresCreditoIfipFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "tasaInteresCreditoIfipController")
@ViewScoped
public class TasaInteresCreditoIfipController extends AbstractController<TasaInteresCreditoIfip> {

    @EJB
    private TasaInteresCreditoIfipFacade ejbFacade;
    @EJB
    private IfipFacade ejbFacadeIfip;
    /**
     * Initialize the concrete TasaInteresCreditoIfip controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    private Ifip ifip;
    private String msgValorUnico;
    private Usuario usuario;
    public TasaInteresCreditoIfipController() {
        super(TasaInteresCreditoIfip.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
         setUsuario(ActivacionUsuario.getUsuario());
    }
//
    @Override
    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
        this.getSelected().setRegistradoPor( getSelected().getUsuarioRegistradoPor().getCodigo());
        getSelected().setFechaRegistro(new Date());
    }
    public void nuevo(ActionEvent event) {
        this.setSelected(new TasaInteresCreditoIfip());
        this.getSelected().setFechaRegistro(new Date());
        this.getSelected().setCodigoIfip(getIfip().getCodigo());
        getSelected().setUsuarioRegistradoPor(getUsuario());
        this.getSelected().setCodigo(codigo());
        //System.out.println("codigo: "+ this.getSelected().getCodigo());
    }
    public Long codigo(){
        long max=1;
             for (TasaInteresCreditoIfip tasaInteresCreditoIfip : getItems()) {
            if(tasaInteresCreditoIfip.getCodigo()>max)
                max=tasaInteresCreditoIfip.getCodigo()+1;
            else if(tasaInteresCreditoIfip.getCodigo()== max)
                max++;
        }
          return max;   
    } 
    public void edita(ActionEvent event) {
        getSelected().setFechaRegistro(new Date());
        getSelected().setUsuarioRegistradoPor(getUsuario());
        setIfip(ejbFacadeIfip.find(getSelected().getCodigoIfip()));
    }

    public void eliminaConceptoTransaccion(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setEliminado('S');
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"tasaInteresCreditoIfipController", "eliminaTasaInteresCreditoIfipController", CapturaError.getErrorException(ex)});
            }
        }

    }

    public void validateValorUnico(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        if (ejbFacade.getTasaInteresCreditoIfip((BigDecimal) arg2) != null) {
            throw new ValidatorException(new FacesMessage(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ExisteTalValor")));
        }
    }

    /**
     * @return the ifip
     */
    public Ifip getIfip() {
        return ifip;
    }

    /**
     * @param ifip the ifip to set
     */
    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

    /**
     * @return the msgValorUnico
     */
    public String getMsgValorUnico() {
        return msgValorUnico;
    }

    /**
     * @param msgValorUnico the msgValorUnico to set
     */
    public void setMsgValorUnico(String msgValorUnico) {
        this.msgValorUnico = msgValorUnico;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
