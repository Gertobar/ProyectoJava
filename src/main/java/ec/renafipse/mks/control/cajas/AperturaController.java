package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.ConvierteDato;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.cajas.AperturaDetalle;
import ec.renafipse.mks.modelo.cajas.AperturaDetallePK;
import ec.renafipse.mks.modelo.cajas.FondeoCaja;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDetalle;
import ec.renafipse.mks.negocio.cajas.AperturaCajaFondeoFacade;
import ec.renafipse.mks.negocio.cajas.AperturaDetalleFacade;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.cajas.FondeoCajaDetalleFacade;
import ec.renafipse.mks.negocio.cajas.FondeoCajaFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.ComputadorFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.seguridades.RolOpcionOperacionFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "aperturaController")
@ViewScoped
public class AperturaController extends AbstractController<Apertura> implements Serializable {

    @EJB
    private AperturaFacade ejbFacade;

    @EJB
    private AperturaDetalleFacade ejbFacadeAperturaDetalle;

    @EJB
    private RolOpcionOperacionFacade ejbFacadeRolOpcionOperacion;

    @EJB
    private FondeoCajaFacade ejbFacadeFondeoCaja;

    @EJB
    private FondeoCajaDetalleFacade ejbFacadeFondeoCajaDetalle;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private ComputadorFacade ejbFacadeComputador;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;

    @EJB
    private AperturaCajaFondeoFacade ejbFacadeAperturaCajaFondeo;

    
    private LlamaSP llamaSP;

    // ---------------------------------------------------------------------------  
    // -- PARAMETROS PERSONALIZADOS
    private String msg;
    private boolean deshabilitaBotonGuardar;
    private GeneraReporte generaReporte;
    private Long codigoApertura;
    private String mensajeCabecera;

    private Apertura apertura;
    private AperturaDetalle aperturaDetalle;
    private FondeoCaja fondeoCaja;
    private List<FondeoCajaDetalle> itemsFondeoCajaDetalle;
    private List<AperturaDetalle> itemsAperturaDetalle;

    public AperturaController() {
        super(Apertura.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);

        this.deshabilitaBotonGuardar = true;
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {
            //////System.out.println("Codigo OPcion " + ActivacionUsuario.getCodigoOpcion());
            /*if (this.ejbFacadeRolOpcionOperacion.getItemsOperacionPermisosMenu(ActivacionUsuario.getCodigoRol(), ActivacionUsuario.getCodigoOpcion(), ActivacionUsuario.getCodigoIfip(), 'N').isEmpty()) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }*/
            System.err.println("Datos Apertura: "+new Date()+ " Computador  " + ActivacionUsuario.getCodigoComputador()+"  Usuario "+ ActivacionUsuario.getCodigoUsuario() +" estado "+ 'E'+" Agencia"+ ActivacionUsuario.getCodigoIfipAgencia());

            if (ActivacionUsuario.getCodigoComputador() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }

            List<Apertura> listaApertura = this.ejbFacade.getItemsAperturaUsuarioIfipAgencia(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), 'A');
            // Verifico que si tiene aperturado la caja
            if (!listaApertura.isEmpty()) {
                if (listaApertura.size() == 1) {
                    this.mensajeCabecera = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioAperturoCaja");
                    this.apertura = listaApertura.get(0);
                    this.itemsAperturaDetalle = this.ejbFacadeAperturaDetalle.getItemsCodigoApertura(this.apertura.getCodigo());
                    this.fondeoCaja = ejbFacadeAperturaCajaFondeo.find(this.apertura.getCodigo()).getCodigoFondeo();
                    this.codigoApertura = this.apertura.getCodigo();
                } else {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioMasUnaApertura"));
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                }

            } else {
                listaApertura = this.ejbFacade.getItemsUsuarioEstado(ActivacionUsuario.getCodigoUsuario(), 'A');
                if (!listaApertura.isEmpty()) {
                    String nombreAgencia = null;
                    if (listaApertura.size() == 1) {
                        nombreAgencia = listaApertura.get(0).getIfipAgencia().getNombre();
                    }
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioAperturoCajaOtraAgencia")+" "+nombreAgencia);
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                }
                System.err.println("Datos Fondeo: "+new Date()+ " Computador  " + ActivacionUsuario.getCodigoComputador()+"  Usuario "+ ActivacionUsuario.getCodigoUsuario() +" estado "+ 'E'+" Agencia"+ ActivacionUsuario.getCodigoIfipAgencia());
                // Obteniendo el fondeo de la CAJA 
                List<FondeoCaja> listaFondeoCaja = this.ejbFacadeFondeoCaja.getItemsFondeoAperturaCaja(new Date(), ActivacionUsuario.getCodigoComputador(), ActivacionUsuario.getCodigoUsuario(), 'E', ActivacionUsuario.getCodigoIfipAgencia());
                if (listaFondeoCaja.isEmpty()) {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioComputadorSinFondeoFecha"));
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);

                    // Si el fondeo de caja es unico para la fecha
                } else if (listaFondeoCaja.size() == 1) {

                    this.mensajeCabecera = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AperturaCajaMedianteFondeo");

                    //Instancion el proceso de llama Store Procedure
                    llamaSP = new LlamaSP();
            
                    // Colocando los datos para la apertura
                    this.fondeoCaja = listaFondeoCaja.get(0);
                    this.itemsFondeoCajaDetalle = this.ejbFacadeFondeoCajaDetalle.getItemsCodigoFondeo(this.fondeoCaja.getCodigo());
                    
                    this.apertura = new Apertura();
                    this.apertura.setCodigoComputador(ActivacionUsuario.getCodigoComputador());
                    this.apertura.setCodigoIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia());
                    this.apertura.setCodigoUsuario(ActivacionUsuario.getCodigoUsuario());
                    this.apertura.setFechaSistema(new Date());
                    this.apertura.setFechaApertura(ConvierteDato.getFechaMedium(this.apertura.getFechaSistema()));
                    this.apertura.setUsuario(this.ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario()));
                    this.apertura.setComputador(this.ejbFacadeComputador.find(ActivacionUsuario.getCodigoComputador()));
                    this.apertura.setIfipAgencia(this.ejbFacadeIfipAgencia.find(ActivacionUsuario.getCodigoIfipAgencia()));

                    this.itemsAperturaDetalle = new ArrayList<AperturaDetalle>();
                    for (FondeoCajaDetalle fcd : this.itemsFondeoCajaDetalle) {
                        aperturaDetalle = new AperturaDetalle();

                        aperturaDetalle.setAperturaDetallePK(new AperturaDetallePK());
                        aperturaDetalle.getAperturaDetallePK().setCodigoMoneda(fcd.getFondeoCajaDetallePK().getCodigoMoneda());

                        aperturaDetalle.setNumeroCheques(Long.parseLong("0"));
                        aperturaDetalle.setValorEfectivo(fcd.getValorEfectivo());
                        aperturaDetalle.setValorCheques(new BigDecimal("0.00"));
                        aperturaDetalle.setTotalApertura(fcd.getTotalFondeo());

                        aperturaDetalle.setApertura(apertura);
                        aperturaDetalle.setMoneda(this.ejbFacadeMoneda.find(fcd.getFondeoCajaDetallePK().getCodigoMoneda()));

                        this.itemsAperturaDetalle.add(aperturaDetalle);
                    }

                    this.deshabilitaBotonGuardar = false;
                } else {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioComputadorMasUnFondeoFecha"));
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                }
            }

        } catch (IOException e) {
            try {
                System.out.println("Ocurrio Error Apetura Caja "+e.toString());
                e.printStackTrace();
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(AperturaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException e) {
            try {
                System.out.println("Ocurrio Error Apetura Caja "+e.toString());
                e.printStackTrace();
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(AperturaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * GUARDA LA APERTURA DE LA CAJA
     *
     * @param actionEvent
     */
    public void guardarAperturaCaja(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {

            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (this.guardaApertura()) {
                this.codigoApertura = ((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                if (this.guardaAperturaDetalle()) {
                    if (this.guardaFondeoCajaApertura()) {
                        this.actualizaEstadoFondeo();
                    }
                }
            }

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                //msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovimientoGrabado");
                MuestraMensaje.addSatisfactorioPersistencia(1);
                context.execute("procesandoDlg.hide()");
                context.execute("imprimeDialog.show()");
                this.deshabilitaBotonGuardar = true;
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
                    new Object[]{"aperturaController", "guardarAperturaCaja", CapturaError.getErrorException(ex)});
        }
    }

    public boolean guardaApertura() {
        // Guardando  la Apertura de la Caja - Cabecera
        llamaSP.setNombreSP("mks_cajas.pkg_apertura.p_inserta_apertura");
        llamaSP.setNumeroParametros(6);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", this.apertura.getUsuario().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", "F"});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "A"});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }

    public boolean guardaAperturaDetalle() {
        // Guardando el detalled e la apertura
        llamaSP.setNombreSP("mks_cajas.pkg_apertura_detalle.p_inserta_apertura_detalle");
        llamaSP.setNumeroParametros(6);
        for (AperturaDetalle ad : this.itemsAperturaDetalle) {
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", this.codigoApertura});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", ad.getAperturaDetallePK().getCodigoMoneda()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_efectivo", ad.getValorEfectivo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_cheques", ad.getValorCheques()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheques", ad.getNumeroCheques()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_apertura", ad.getTotalApertura()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el desgloce no dio errores
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public boolean guardaFondeoCajaApertura() {
        // Guardando el Fondeo de Caja para la Apertura
        llamaSP.setNombreSP("mks_cajas.pkg_apertura_caja_fondeo.p_inserta_apertura_caja_fondeo");
        llamaSP.setNumeroParametros(2);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", this.codigoApertura});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_fondeo", this.fondeoCaja.getCodigo()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }

    public boolean actualizaEstadoFondeo() {
        // Guardando el Fondeo de Caja para la Apertura
        llamaSP.setNombreSP("mks_cajas.pkg_fondeo_caja.p_actualiza_estado");
        llamaSP.setNumeroParametros(2);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", this.fondeoCaja.getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "A"});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * IMPRIME LA APERTURA DE LA CAJA
     *
     * @param actionEvent
     */
    public void imprimeApertura(ActionEvent actionEvent) {
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoApertura", this.codigoApertura);

        nombreReporte = "aperturaCaja";
        getGeneraReporte().exporta("/financiero/cajas/apertura/reporte/", nombreReporte,
                "aperturaCaja" + this.codigoApertura + ".pdf",
                "PDF", externalContext, facesContext);
    }

    /**
     * IMPRIME EL FONDEO DE LA CAJA SELECCIONADA
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimeFondeo(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoFondeo", this.fondeoCaja.getCodigo());

        nombreReporte = "fondeoCaja";
        getGeneraReporte().exporta("/financiero/boveda/fondeoCaja/reporte/", nombreReporte,
                "fondeoCaja" + String.valueOf(this.fondeoCaja.getCodigo()) + ".pdf",
                "PDF", externalContext, facesContext);

    }

    /**
     * @return the fondeoCaja
     */
    public FondeoCaja getFondeoCaja() {
        return fondeoCaja;
    }

    /**
     * @param fondeoCaja the fondeoCaja to set
     */
    public void setFondeoCaja(FondeoCaja fondeoCaja) {
        this.fondeoCaja = fondeoCaja;
    }

    /**
     * @return the itemsFondeoCajaDetalle
     */
    public List<FondeoCajaDetalle> getItemsFondeoCajaDetalle() {
        return itemsFondeoCajaDetalle;
    }

    /**
     * @param itemsFondeoCajaDetalle the itemsFondeoCajaDetalle to set
     */
    public void setItemsFondeoCajaDetalle(List<FondeoCajaDetalle> itemsFondeoCajaDetalle) {
        this.itemsFondeoCajaDetalle = itemsFondeoCajaDetalle;
    }

    /**
     * @return the apertura
     */
    public Apertura getApertura() {
        return apertura;
    }

    /**
     * @param apertura the apertura to set
     */
    public void setApertura(Apertura apertura) {
        this.apertura = apertura;
    }

    /**
     * @return the itemsAperturaDetalle
     */
    public List<AperturaDetalle> getItemsAperturaDetalle() {
        return itemsAperturaDetalle;
    }

    /**
     * @param itemsAperturaDetalle the itemsAperturaDetalle to set
     */
    public void setItemsAperturaDetalle(List<AperturaDetalle> itemsAperturaDetalle) {
        this.itemsAperturaDetalle = itemsAperturaDetalle;
    }

    /**
     * @return the aperturaDetalle
     */
    public AperturaDetalle getAperturaDetalle() {
        return aperturaDetalle;
    }

    /**
     * @param aperturaDetalle the aperturaDetalle to set
     */
    public void setAperturaDetalle(AperturaDetalle aperturaDetalle) {
        this.aperturaDetalle = aperturaDetalle;
    }

    /**
     * @return the deshabilitaBotonGuardar
     */
    public boolean isDeshabilitaBotonGuardar() {
        return deshabilitaBotonGuardar;
    }

    /**
     * @param deshabilitaBotonGuardar the deshabilitaBotonGuardar to set
     */
    public void setDeshabilitaBotonGuardar(boolean deshabilitaBotonGuardar) {
        this.deshabilitaBotonGuardar = deshabilitaBotonGuardar;
    }

    /**
     * @return the generaReporte
     */
    public GeneraReporte getGeneraReporte() {
        return generaReporte;
    }

    /**
     * @param generaReporte the generaReporte to set
     */
    public void setGeneraReporte(GeneraReporte generaReporte) {
        this.generaReporte = generaReporte;
    }

    /**
     * @return the mensajeCabecera
     */
    public String getMensajeCabecera() {
        return mensajeCabecera;
    }

    /**
     * @param mensajeCabecera the mensajeCabecera to set
     */
    public void setMensajeCabecera(String mensajeCabecera) {
        this.mensajeCabecera = mensajeCabecera;
    }

}
