package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.TipoOperacion;
import ec.renafipse.mks.negocio.seguridades.TipoOperacionFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "tipoOperacionController")
@ViewScoped
public class TipoOperacionController extends AbstractController<TipoOperacion> implements Serializable {

    @EJB
    private TipoOperacionFacade ejbFacade;


    public TipoOperacionController() {
        super(TipoOperacion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);

        if (ActivacionUsuario.getControlador() != null) {
            if (!ActivacionUsuario.getControlador().equals("tipoOperacionController")) {
                String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlMenuMKS");
                try {
                    Sesion.redireccionaPagina(url);
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"tipoOperacionController", "init", CapturaError.getErrorException(ex)});
                }

            }
        } else {
            String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlMenuMKS");
            try {
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"tipoOperacionController", "init", CapturaError.getErrorException(ex)});
            }
        }
    }

}
