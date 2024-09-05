package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.Transaccion;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.seguridades.Rol;
import ec.renafipse.mks.modelo.seguridades.RolConceptoTransaccionIfip;
import ec.renafipse.mks.modelo.seguridades.RolConceptoTransaccionIfipPK;
import ec.renafipse.mks.negocio.ahorros.ConceptoTransaccionProFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import ec.renafipse.mks.negocio.seguridades.RolConceptoTransaccionIfipFacade;
import ec.renafipse.mks.negocio.seguridades.RolFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "rolConceptoTransaccionIfipController")
@ViewScoped
public class RolConceptoTransaccionIfipController extends AbstractController<RolConceptoTransaccionIfip> implements Serializable {

    @EJB
    private RolConceptoTransaccionIfipFacade ejbFacade;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private IfipFacade ejbFacadeIfip;

    @EJB
    private RolFacade ejbFacadeRol;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private ProductoIfipFacade ejbFacadeProuctoIfip;

    @EJB
    private ConceptoTransaccionProFacade ejbFacadeConceptoTransaccionPro;

    @EJB
    private RolConceptoTransaccionIfipFacade ejbFacadeRolConceptoTransaccionIfip;

    private RolConceptoTransaccionIfip rolConceptoTransaccionIfip;

    private String msg;
    private Rol ubicaRol;
    private Moneda ubicaMoneda;
    private ProductoIfip ubicaProductoIfip;
    private Transaccion ubicaTransaccion;
    private RolConceptoTransaccionIfipPK rolConceptoTransaccionIfipPK;

    private List<Rol> itemsRol;
    private List<Moneda> itemsMoneda;
    private List<ProductoIfip> itemsProductoIfip;
    private List<Transaccion> itemsTransaccion;

    private List<ConceptoTransaccionPro> conceptoTransaccionProIfiExistentesList;
    private List<ConceptoTransaccionPro> conceptoTransaccionProIfiExistentesRolList;
    private List<ConceptoTransaccionPro> conceptoTransaccionProIfiFaltantesList;

    private DualListModel<ConceptoTransaccionPro> itemsConceptoTransaccionPro;

    public RolConceptoTransaccionIfipController() {
        super(RolConceptoTransaccionIfip.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.msg = null;
        this.ubicaRol = null;
        this.ubicaMoneda = null;
        this.ubicaProductoIfip = null;
        this.ubicaTransaccion = null;

        this.rolConceptoTransaccionIfipPK = new RolConceptoTransaccionIfipPK();
        this.rolConceptoTransaccionIfip = new RolConceptoTransaccionIfip();

        this.ubicaMoneda = null;
        this.ubicaProductoIfip = null;
        this.ubicaTransaccion = null;
        this.ubicaRol = null;
        this.setItemsMoneda(new ArrayList<Moneda>());
        this.setItemsProductoIfip(new ArrayList<ProductoIfip>());
        this.setItemsTransaccion(new ArrayList<Transaccion>());
        this.setItemsRol(new ArrayList<Rol>());

        this.itemsRol = this.ejbFacadeRol.getItemsRol('N');
        this.setConceptoTransaccionProIfiExistentesList(new ArrayList<ConceptoTransaccionPro>());
        this.setConceptoTransaccionProIfiExistentesRolList(new ArrayList<ConceptoTransaccionPro>());
        this.setConceptoTransaccionProIfiFaltantesList(new ArrayList<ConceptoTransaccionPro>());
        this.itemsConceptoTransaccionPro = new DualListModel<ConceptoTransaccionPro>();
    }

    //LOGICA
    /**
     * SE OBTIENE LAS MONEDAS
     *
     * @return
     */
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

    /**
     * CUANDO CAMBIA LA MONEDA O SELECCIONA.
     */
    public void cambiaMoneda() {
        this.msg = null;
        this.getConceptoTransaccionProIfiExistentesRolList().clear();
        this.getConceptoTransaccionProIfiFaltantesList().clear();
        if (this.ubicaMoneda == null) {
            this.itemsProductoIfip.clear();
            this.itemsTransaccion.clear();
            this.itemsRol.clear();
            this.ubicaProductoIfip = null;
            this.ubicaTransaccion = null;
            this.ubicaRol = null;
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Moneda")
                    + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else {
            this.setItemsProductoIfip(this.ejbFacadeProuctoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.ubicaMoneda.getCodigo(), 'N'));
            this.ubicaProductoIfip = null;
            this.ubicaTransaccion = null;
            this.ubicaRol = null;
        }

        if (msg != null) {
            MuestraMensaje.addError(msg);
        }
        this.setItemsConceptoTransaccionPro(new DualListModel<ConceptoTransaccionPro>(this.getConceptoTransaccionProIfiFaltantesList(), this.getConceptoTransaccionProIfiExistentesRolList()));
    }

    /**
     * CUANDO CAMBIA EL PRODUCTO.
     */
    public void cambiaProductoIfip() {
        this.msg = null;
        this.getConceptoTransaccionProIfiExistentesRolList().clear();
        this.getConceptoTransaccionProIfiFaltantesList().clear();
        if (this.ubicaProductoIfip == null) {
            this.itemsTransaccion.clear();
            this.itemsRol.clear();
            this.ubicaTransaccion = null;
            this.ubicaRol = null;
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Producto")
                    + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else {
            this.setItemsTransaccion(this.ejbFacadeConceptoTransaccionPro.getItemsTransaccionesPermisosRol(this.ubicaProductoIfip.getProductoIfipPK().getCodigoTipoProducto(), this.ubicaMoneda.getCodigo(), 'N'));
            this.ubicaTransaccion = null;
            this.ubicaRol = null;
        }

        if (msg != null) {
            MuestraMensaje.addError(msg);
        }
        this.itemsConceptoTransaccionPro = new DualListModel<ConceptoTransaccionPro>(this.getConceptoTransaccionProIfiFaltantesList(), this.getConceptoTransaccionProIfiExistentesRolList());

    }

    /**
     * CUANDO CAMBIA EL ROL.
     */
    public void cambiaRol() {
        boolean existe = false;
        this.getConceptoTransaccionProIfiExistentesRolList().clear();
        this.getConceptoTransaccionProIfiFaltantesList().clear();
        this.getConceptoTransaccionProIfiExistentesList().clear();
        List<RolConceptoTransaccionIfip> listaRolConceptoTransaccionIfip;

        if ((this.ubicaRol == null && this.ubicaTransaccion == null) || (this.ubicaTransaccion == null && this.ubicaRol != null) || (this.ubicaTransaccion != null && this.ubicaRol == null)) {
            this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneDatosFaltantes");
            //MuestraMensaje.addAdvertencia(msg);
            this.itemsConceptoTransaccionPro = new DualListModel<ConceptoTransaccionPro>(this.getConceptoTransaccionProIfiFaltantesList(), this.getConceptoTransaccionProIfiExistentesRolList());
        } else if (this.ubicaRol != null && this.ubicaTransaccion != null) {

            this.setConceptoTransaccionProIfiExistentesList(this.ejbFacadeConceptoTransaccionPro.getItemsPermisosExistentesConceptoTransaccionProIfi(this.ubicaProductoIfip.getProductoIfipPK().getCodigoTipoProducto(), this.ubicaMoneda.getCodigo(), this.ubicaTransaccion.getCodigo(), 'N'));
            this.setConceptoTransaccionProIfiExistentesRolList(this.ejbFacadeRolConceptoTransaccionIfip.getItemsPermisosExistentesRolConTranIfip(this.ubicaProductoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                    this.ubicaMoneda.getCodigo(),
                    ActivacionUsuario.getCodigoIfip(),
                    this.ubicaRol.getCodigo(),
                    this.ubicaTransaccion.getCodigo(), 'N', 'S'));
            //System.out.println("conceptoTransaccionProIfiExistentesRolList  " +this.conceptoTransaccionProIfiExistentesRolList);
            for (ConceptoTransaccionPro ctpEx : this.getConceptoTransaccionProIfiExistentesList()) {
                for (ConceptoTransaccionPro ctpExRol : this.getConceptoTransaccionProIfiExistentesRolList()) {
                    if (ctpEx.getConceptoTransaccion().getCodigo() == ctpExRol.getConceptoTransaccion().getCodigo()) {
                        existe = true;
                    } else {
                        existe = false;
                    }
                    if (existe) {
                        break;
                    }
                }
                if (!existe) {
                    this.getConceptoTransaccionProIfiFaltantesList().add(ctpEx);
                } else {
                    listaRolConceptoTransaccionIfip = this.ejbFacade.getItemsRolConceptoTransaccionIfip(this.ubicaRol.getCodigo(), ctpEx.getConceptoTransaccion().getCodigoTransaccion().getCodigo(), ActivacionUsuario.getCodigoIfip(), this.ubicaProductoIfip.getProductoIfipPK().getCodigoTipoProducto(), this.ubicaMoneda.getCodigo());
                    if (listaRolConceptoTransaccionIfip.size() == 1) {
                        this.rolConceptoTransaccionIfip = listaRolConceptoTransaccionIfip.get(0);
                        if (String.valueOf(this.rolConceptoTransaccionIfip.getEliminado()).equals("S")) {
                            this.getConceptoTransaccionProIfiFaltantesList().add(ctpEx);
                            this.getConceptoTransaccionProIfiExistentesRolList().remove(ctpEx);
                        }
                    }
                }

            }
            this.itemsConceptoTransaccionPro = new DualListModel<ConceptoTransaccionPro>(this.getConceptoTransaccionProIfiFaltantesList(), this.getConceptoTransaccionProIfiExistentesRolList());
        }
        this.ubicaRol = null;
    }

    public void guardaRolConceptoTransaccionIfip() {
        msg = null;
        List<RolConceptoTransaccionIfip> listaRolConceptoTransaccionIfip;

        for (ConceptoTransaccionPro ctp : this.getItemsConceptoTransaccionPro().getSource()) {
            listaRolConceptoTransaccionIfip = this.ejbFacade.getItemsRolConceptoTransaccionIfip(this.ubicaRol.getCodigo(), ctp.getConceptoTransaccion().getCodigoTransaccion().getCodigo(), ActivacionUsuario.getCodigoIfip(), this.ubicaProductoIfip.getProductoIfipPK().getCodigoTipoProducto(), this.ubicaMoneda.getCodigo());
            if (listaRolConceptoTransaccionIfip.size() == 1) {
                RolConceptoTransaccionIfip rolConceptoTransaccionIfipSource = listaRolConceptoTransaccionIfip.get(0);
                if (String.valueOf(rolConceptoTransaccionIfipSource.getEliminado()).equals("N")) {
                    rolConceptoTransaccionIfipSource.setEliminado('S');
                    this.ejbFacade.edit(rolConceptoTransaccionIfipSource);
                }
            }
            if (listaRolConceptoTransaccionIfip.size() > 1) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnRolConceptoTransaccionIfip");
                break;
            }
            
            //System.out.println("ctp source" + ctp);
        }

        if (msg == null) {
            for (ConceptoTransaccionPro ctp : this.itemsConceptoTransaccionPro.getTarget()) {
                listaRolConceptoTransaccionIfip = this.ejbFacade.getItemsRolConceptoTransaccionIfip(this.ubicaRol.getCodigo(), ctp.getConceptoTransaccion().getCodigoTransaccion().getCodigo(), ActivacionUsuario.getCodigoIfip(), this.ubicaProductoIfip.getProductoIfipPK().getCodigoTipoProducto(), this.ubicaMoneda.getCodigo());
                //System.out.println("listaRolConceptoTransaccionIfip "+listaRolConceptoTransaccionIfip);
                if (listaRolConceptoTransaccionIfip.size() == 1) {
                    RolConceptoTransaccionIfip rolConceptoTransaccionIfipTarget = listaRolConceptoTransaccionIfip.get(0);
                    if (String.valueOf(rolConceptoTransaccionIfipTarget.getEliminado()).equals("S")) {
                        rolConceptoTransaccionIfipTarget.setEliminado('N');
                        this.ejbFacade.edit(rolConceptoTransaccionIfipTarget);
                    }
                } else if (listaRolConceptoTransaccionIfip.size() > 1) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnRolConceptoTransaccionIfip");
                } else {

                    this.rolConceptoTransaccionIfip = new RolConceptoTransaccionIfip();
                    this.rolConceptoTransaccionIfipPK.setCodigoTipoProducto(this.ubicaProductoIfip.getProductoIfipPK().getCodigoTipoProducto());
                    this.rolConceptoTransaccionIfipPK.setCodigoMoneda(this.ubicaMoneda.getCodigo());
                    this.rolConceptoTransaccionIfipPK.setCodigoConcepto(ctp.getConceptoTransaccion().getCodigo());
                    this.rolConceptoTransaccionIfipPK.setCodigoRol(this.ubicaRol.getCodigo());
                    this.rolConceptoTransaccionIfipPK.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
                    this.rolConceptoTransaccionIfip.setRolConceptoTransaccionIfipPK(rolConceptoTransaccionIfipPK);
                    this.rolConceptoTransaccionIfip.setIngresadoPor(ActivacionUsuario.getUsuario());
                    this.rolConceptoTransaccionIfip.setFechaIngreso(new Date());
                    this.rolConceptoTransaccionIfip.setEliminado('N');
                    this.ejbFacade.create(rolConceptoTransaccionIfip);
                }
                
                //System.out.println("ctp target"+ctp);

            }

            if (msg == null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RolConceptoTransaccionIfipGuardada");
                MuestraMensaje.addSatisfactorio(msg);
                init();
            } else {
                MuestraMensaje.addError(msg);
            }
        }

    }

    //FIN LOGICA
    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getRolConceptoTransaccionIfipPK().setCodigoRol(this.getSelected().getRol().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setRolConceptoTransaccionIfipPK(new ec.renafipse.mks.modelo.seguridades.RolConceptoTransaccionIfipPK());
    }

    /**
     * @return the ubicaRol
     */
    public Rol getUbicaRol() {
        return ubicaRol;
    }

    /**
     * @param ubicaRol the ubicaRol to set
     */
    public void setUbicaRol(Rol ubicaRol) {
        this.ubicaRol = ubicaRol;
    }

    /**
     * @return the ubicaMoneda
     */
    public Moneda getUbicaMoneda() {
        return ubicaMoneda;
    }

    /**
     * @param ubicaMoneda the ubicaMoneda to set
     */
    public void setUbicaMoneda(Moneda ubicaMoneda) {
        this.ubicaMoneda = ubicaMoneda;
    }

    /**
     * @return the ubicaProductoIfip
     */
    public ProductoIfip getUbicaProductoIfip() {
        return ubicaProductoIfip;
    }

    /**
     * @param ubicaProductoIfip the ubicaProductoIfip to set
     */
    public void setUbicaProductoIfip(ProductoIfip ubicaProductoIfip) {
        this.ubicaProductoIfip = ubicaProductoIfip;
    }

    /**
     * @return the ubicaTransaccion
     */
    public Transaccion getUbicaTransaccion() {
        return ubicaTransaccion;
    }

    /**
     * @param ubicaTransaccion the ubicaTransaccion to set
     */
    public void setUbicaTransaccion(Transaccion ubicaTransaccion) {
        this.ubicaTransaccion = ubicaTransaccion;
    }

    /**
     * @return the itemsRol
     */
    public List<Rol> getItemsRol() {
        return itemsRol;
    }

    /**
     * @param itemsRol the itemsRol to set
     */
    public void setItemsRol(List<Rol> itemsRol) {
        this.itemsRol = itemsRol;
    }

    /**
     * @return the itemsProductoIfip
     */
    public List<ProductoIfip> getItemsProductoIfip() {
        return itemsProductoIfip;
    }

    /**
     * @param itemsProductoIfip the itemsProductoIfip to set
     */
    public void setItemsProductoIfip(List<ProductoIfip> itemsProductoIfip) {
        this.itemsProductoIfip = itemsProductoIfip;
    }

    /**
     * @return the itemsTransaccion
     */
    public List<Transaccion> getItemsTransaccion() {
        return itemsTransaccion;
    }

    /**
     * @param itemsTransaccion the itemsTransaccion to set
     */
    public void setItemsTransaccion(List<Transaccion> itemsTransaccion) {
        this.itemsTransaccion = itemsTransaccion;
    }

    /**
     * @param itemsMoneda the itemsMoneda to set
     */
    public void setItemsMoneda(List<Moneda> itemsMoneda) {
        this.itemsMoneda = itemsMoneda;
    }

    /**
     * @return the conceptoTransaccionProIfiExistentesList
     */
    public List<ConceptoTransaccionPro> getConceptoTransaccionProIfiExistentesList() {
        return conceptoTransaccionProIfiExistentesList;
    }

    /**
     * @param conceptoTransaccionProIfiExistentesList the
     * conceptoTransaccionProIfiExistentesList to set
     */
    public void setConceptoTransaccionProIfiExistentesList(List<ConceptoTransaccionPro> conceptoTransaccionProIfiExistentesList) {
        this.conceptoTransaccionProIfiExistentesList = conceptoTransaccionProIfiExistentesList;
    }

    /**
     * @return the conceptoTransaccionProIfiExistentesRolList
     */
    public List<ConceptoTransaccionPro> getConceptoTransaccionProIfiExistentesRolList() {
        return conceptoTransaccionProIfiExistentesRolList;
    }

    /**
     * @param conceptoTransaccionProIfiExistentesRolList the
     * conceptoTransaccionProIfiExistentesRolList to set
     */
    public void setConceptoTransaccionProIfiExistentesRolList(List<ConceptoTransaccionPro> conceptoTransaccionProIfiExistentesRolList) {
        this.conceptoTransaccionProIfiExistentesRolList = conceptoTransaccionProIfiExistentesRolList;
    }

    /**
     * @return the conceptoTransaccionProIfiFaltantesList
     */
    public List<ConceptoTransaccionPro> getConceptoTransaccionProIfiFaltantesList() {
        return conceptoTransaccionProIfiFaltantesList;
    }

    /**
     * @param conceptoTransaccionProIfiFaltantesList the
     * conceptoTransaccionProIfiFaltantesList to set
     */
    public void setConceptoTransaccionProIfiFaltantesList(List<ConceptoTransaccionPro> conceptoTransaccionProIfiFaltantesList) {
        this.conceptoTransaccionProIfiFaltantesList = conceptoTransaccionProIfiFaltantesList;
    }

    /**
     * @return the itemsConceptoTransaccionPro
     */
    public DualListModel<ConceptoTransaccionPro> getItemsConceptoTransaccionPro() {
        return itemsConceptoTransaccionPro;
    }

    /**
     * @param itemsConceptoTransaccionPro the itemsConceptoTransaccionPro to set
     */
    public void setItemsConceptoTransaccionPro(DualListModel<ConceptoTransaccionPro> itemsConceptoTransaccionPro) {
        this.itemsConceptoTransaccionPro = itemsConceptoTransaccionPro;
    }

}
