package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.clase.creditos.GestionCartera;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.CarteraGestionada;
import ec.renafipse.mks.negocio.creditos.CarteraGestionadaFacade;
import java.io.IOException;
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

@ManagedBean(name = "carteraGestionadaController")
@ViewScoped
public class CarteraGestionadaController extends AbstractController<CarteraGestionada> implements Serializable {

    @EJB
    private CarteraGestionadaFacade ejbCarteraGestionadaFacade;
    
    private List<GestionCartera> listaGestionCartera;
    private GestionCartera gestionCartera;
    private String gestionRealizada;
    private String gestionSatisfactoria;
    private boolean desactivaGestion;

    @PostConstruct
    public void init() {
        super.setFacade(ejbCarteraGestionadaFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        setDesactivaGestion(true);
        try {
            
        } catch (Exception e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"CarteraGestionadaController", "CarteraGestionadaController", CapturaError.getErrorException(ex)});
            }
        }
    }

    public CarteraGestionadaController() {
        super(CarteraGestionada.class);
    }
    
    public void ejecutarConsulta(){
        setListaGestionCartera(ejbCarteraGestionadaFacade.getGestionCartera(ActivacionUsuario.getCodigoUsuario(),ActivacionUsuario.getCodigoIfipAgencia()));
    }
    
    public void inicializarRegistroGestion(){
        setGestionRealizada(null);
        setGestionRealizada(null);
    }
    /***
     * Metodo para registrar la gestion realizada
     */
    public void registrarGestion(){
        try{
            if(getGestionRealizada() == null){
                MuestraMensaje.addError("Por favor ingrese la gestion realizada");
                return;
            }
            if(getGestionSatisfactoria() == null){
                MuestraMensaje.addError("Por favor seleccione el resultado de su gestión");
                return;
            }
            //Colocar en vigente NO las gestiones anteriores a la que se va a guardar
            ejbCarteraGestionadaFacade.actualizaVigenciaCarteraGestionada(getGestionCartera().getCodigoCarteraAsignada());
            //Crear la nueva gestion
            CarteraGestionada gestion = new CarteraGestionada( ActivacionUsuario.getCodigoUsuario(),
                                                               new Date(),
                                                               getGestionRealizada().toUpperCase(),
                                                               getGestionSatisfactoria(),
                                                               "S",
                                                               getGestionCartera().getCodigoCarteraAsignada(),
                                                               getGestionCartera().getCodigoRecuperador());
            ejbCarteraGestionadaFacade.create(gestion);
            MuestraMensaje.addSatisfactorio(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GestionRegistradaSatisfactoriamente"));
            ejecutarConsulta();
        }catch(Exception ex){
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"CarteraGestionadaController", "registrarGestion", CapturaError.getErrorException(ex)});
        }
    }
    /***
     * Metodo para activar el boton de gestion de cartera
     */
    public void activarBotonGestion(){
        setDesactivaGestion(false);
    }
    /***
     * Metodo para des-activar el boton de gestion de cartera
     */
    public void desActivarBotonGestion(){
        setDesactivaGestion(true);
    }

    /**
     * @return the gestionCartera
     */
    public GestionCartera getGestionCartera() {
        return gestionCartera;
    }

    /**
     * @param gestionCartera the gestionCartera to set
     */
    public void setGestionCartera(GestionCartera gestionCartera) {
        this.gestionCartera = gestionCartera;
    }

    /**
     * @return the listaGestionCartera
     */
    public List<GestionCartera> getListaGestionCartera() {
        return listaGestionCartera;
    }

    /**
     * @param listaGestionCartera the listaGestionCartera to set
     */
    public void setListaGestionCartera(List<GestionCartera> listaGestionCartera) {
        this.listaGestionCartera = listaGestionCartera;
    }

    /**
     * @return the gestionRealizada
     */
    public String getGestionRealizada() {
        return gestionRealizada;
    }

    /**
     * @param gestionRealizada the gestionRealizada to set
     */
    public void setGestionRealizada(String gestionRealizada) {
        this.gestionRealizada = gestionRealizada;
    }

    /**
     * @return the gestionSatisfactoria
     */
    public String getGestionSatisfactoria() {
        return gestionSatisfactoria;
    }

    /**
     * @param gestionSatisfactoria the gestionSatisfactoria to set
     */
    public void setGestionSatisfactoria(String gestionSatisfactoria) {
        this.gestionSatisfactoria = gestionSatisfactoria;
    }

    /**
     * @return the desactivaGestion
     */
    public boolean isDesactivaGestion() {
        return desactivaGestion;
    }

    /**
     * @param desactivaGestion the desactivaGestion to set
     */
    public void setDesactivaGestion(boolean desactivaGestion) {
        this.desactivaGestion = desactivaGestion;
    }

}
