package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.TipoUsuario;
import ec.renafipse.mks.negocio.seguridades.TipoUsuarioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoUsuarioController")
@ViewScoped
public class TipoUsuarioController extends AbstractController<TipoUsuario> implements Serializable {

    @EJB
    private TipoUsuarioFacade ejbFacade;

    public TipoUsuarioController() {
        super(TipoUsuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        if (ActivacionUsuario.getControlador() != null) {
            if (!ActivacionUsuario.getControlador().equals("tipoUsuarioController")) {
                String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlMenuMKS");
                try {
                    Sesion.redireccionaPagina(url);
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"tipoUsuarioController", "init", CapturaError.getErrorException(ex)});
                }

            }
        } else {
            String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlMenuMKS");
            try {
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"tipoUsuarioController", "init", CapturaError.getErrorException(ex)});
            }
        }
    }

}
