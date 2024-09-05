package ec.renafipse.mks.control.adquisicones;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.adquisiciones.Proveedor;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.negocio.adquisiciones.ProveedorFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

@Named(value = "proveedorController")
@SessionScoped
public class ProveedorController extends AbstractController<Proveedor> implements Serializable {

    @Inject
    private ProveedorFacade ejbFacade;

    @EJB
    private PersonaFacade ejbFacadePersona;
    
    private List<Proveedor> listaItemsProveedor;
    private Proveedor proveedor;
    private String identificacion;
    private Persona persona;

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
        // setListaItemsProveedor(ejbFacade.getItemsProveedor());
    }

    public ProveedorController() {
        // Inform the Abstract parent controller of the concrete Proveedor?cap_first Entity
        super(Proveedor.class);
    }

    /**
     * @return the listaItemsProveedor
     */
    public List<Proveedor> getListaItemsProveedor() {
        //System.out.println("En getListaItemsProveedor");
        return listaItemsProveedor;
    }

    /**
     * @param listaItemsProveedor the listaItemsProveedor to set
     */
    public void setListaItemsProveedor(List<Proveedor> listaItemsProveedor) {
        this.listaItemsProveedor = listaItemsProveedor;
    }

    /**
     * @return the proveedor
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void buscarPorIdentificacion() {
        this.proveedor = new Proveedor();
//        try {
//            setPersona(ejbFacadePersona.getPersona(getIdentificacion()));
//            if (proveedor != null) {
//                setProveedor(ejbFacade.getProveedor(Long.parseLong(getPersona().getCodigo().toString())));
////                setTipoPersona(ejbFacadeTipoPersona.getTipoPersona(Long.parseLong(getPersona().getCodigoTipoPersona().toString())));
//            } else {
//                //System.out.println("No existe registro");
//            }
//        } catch (Exception e) {
//            //System.out.println("----E");
//        }
//        persona = new Persona();
//        iniciaParamatros();
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
