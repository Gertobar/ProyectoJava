package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccion;
import ec.renafipse.mks.modelo.seguridades.IfipSistema;
import ec.renafipse.mks.negocio.ahorros.ConceptoTransaccionFacade;
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

@ManagedBean(name = "conceptoTransaccionController")
@ViewScoped
public class ConceptoTransaccionController extends AbstractController<ConceptoTransaccion> implements Serializable {

    @EJB
    private ConceptoTransaccionFacade ejbFacade;

    private List<ConceptoTransaccion> listaConceptoTransaccion;

    public ConceptoTransaccionController() {
        super(ConceptoTransaccion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.listaConceptoTransaccion = this.ejbFacade.findAll();
    }
    @Override
    protected void setEmbeddableKeys() {
        getSelected().setFechaRegistro(new Date());
    }
    public void nuevo(ActionEvent event) {
        this.setSelected(new ConceptoTransaccion());
        this.getSelected().setFechaRegistro(new Date());
    }

    public void eliminaConceptoTransaccion(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setEliminado('S');
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
                this.listaConceptoTransaccion = this.ejbFacade.findAll();
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"conceptoTransaccionController", "eliminaConceptoTransaccion", CapturaError.getErrorException(ex)});
            }
        }

    }

    /**
     * @return the listaConceptoTransaccion
     */
    public List<ConceptoTransaccion> getListaConceptoTransaccion() {
        return listaConceptoTransaccion;
    }

    /**
     * @param listaConceptoTransaccion the listaConceptoTransaccion to set
     */
    public void setListaConceptoTransaccion(List<ConceptoTransaccion> listaConceptoTransaccion) {
        this.listaConceptoTransaccion = listaConceptoTransaccion;
    }
}
