/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.creditos.lineacredito;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCredito;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoPlazo;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoPlazoMaximo;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoFacade;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoMonedaIfipFacade;
import ec.renafipse.mks.modelo.creditos.ProductoCredito;
import ec.renafipse.mks.negocio.creditos.PeriodicidadProMonIfiFacade;
import ec.renafipse.mks.modelo.creditos.OrigenRecursos;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoEstadoSol;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.auditorias.LineaCreditoAuditoriaFacade;
import ec.renafipse.mks.negocio.auditorias.LineaCreditoPlazoAuditoriaFacade;
import ec.renafipse.mks.negocio.auditorias.LineaCreditoPlazoMaximoAudFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoPlazoFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoPlazoMaximoFacade;
import ec.renafipse.mks.negocio.creditos.OrigenRecursosFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoEstadoSolFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoSolicitudFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.Serializable;
import java.math.BigDecimal;
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
import org.primefaces.component.wizard.Wizard;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author andres
 */
@ManagedBean(name = "lineaCreditoController")
@ViewScoped
public class LineaCreditoController extends AbstractController<LineaCredito> implements Serializable {

    @EJB
    private LineaCreditoFacade ejbFacade;
    @EJB
    private IfipFacade ejbFacadeIfip;
    @EJB
    private ProductoCreditoMonedaIfipFacade ejbFacadeProductoCreditoMonedaIfip;
    @EJB
    private PeriodicidadProMonIfiFacade ejbFacadePeriodicidadProMonIfiFacade;
    @EJB
    private OrigenRecursosFacade ejbFacadeOrigenRecursos;
    @EJB
    private LineaCreditoPlazoFacade ejbFacadeLineaCreditoPlazo;
    @EJB
    private LineaCreditoPlazoMaximoFacade ejbFacadeLineaCreditoPlazoMaximo;
    @EJB
    private LineaCreditoAuditoriaFacade ejbFacadeLineaCreditoAuditoria;
    @EJB
    private LineaCreditoPlazoAuditoriaFacade ejbFacadeLineaCreditoPlazoAuditoria;
    @EJB
    private LineaCreditoPlazoMaximoAudFacade ejbFacadeLineaCreditoPlazoMaximoAud;
    @EJB
    private LineaCreditoSolicitudFacade ejbFacadeLineaCreditoSolicitud;
    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;
    @EJB
    private SocioFacade ejbFacadeSocio;
    @EJB
    private LineaCreditoEstadoSolFacade ejbFacadeLineaCreditoEstadoSol;
    @EJB
    private LlamaSP llamaSP;
            
    
    private LineaCredito linea;
    private Moneda moneda;
    private ProductoCredito productoCredito;
    private Periodicidad periodicidad;
    private OrigenRecursos origenRecursos;
    private List<LineaCredito> listaLineaCredito;
    private List<Moneda> listaMoneda;
    private List<ProductoCredito> listaProductoCredito;
    private List<Periodicidad> listaPeriodicidad;
    private List<OrigenRecursos> listaOrigenRecursos;
    private List<LineaCreditoPlazo> listaLineaCreditoPlazo;
    private ArrayList<LineaCreditoPlazo> listaLineaCreditoPlazoArray;
    private List<LineaCreditoPlazoMaximo> listaLineaCreditoPlazoMaximo;
    private ArrayList<LineaCreditoPlazoMaximo> listaLineaCreditoPlazoMaximoArray;
    private Ifip ifip;
    private Usuario usuario;
    private boolean edicion;
    private boolean inactivaControlesLineaCreditoPlazo;
    private boolean inactivaControlesLineaCreditoPlazoMaximo;
    private boolean celdaValida;
    private String mensajeValidaCelda;
    private String nombreCeldaValidada;
    private Wizard wizard;
    private List<LineaCreditoSolicitud> lineaCreditoSolicitudParaActivarLista;
    private LineaCreditoSolicitud lineaCreditoSolicitudParaActivarSeleccion;
    private GeneraReporte generaReporte;

    public LineaCreditoController() {
        super(LineaCredito.class);
    }

    @PostConstruct
    public void init() {
        try {
            usuario = ActivacionUsuario.getUsuario();
            ifip = ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
            listaLineaCredito = ejbFacade.getListaLineaCreditoPorIfipVigente(ifip.getCodigo(), "S");
            listaMoneda = ejbFacadeProductoCreditoMonedaIfip.getItemsIfipEliminado(ifip.getCodigo(), 'N');
            listaOrigenRecursos = ejbFacadeOrigenRecursos.getItemsEliminado('N');
            inactivaControlesLineaCreditoPlazo = false;
            inactivaControlesLineaCreditoPlazoMaximo = false;
            
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "init", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Crea una nueva linea de credito
     *
     * @param event
     */
    public void nuevo(ActionEvent event) {
        try {
            edicion = false;
            moneda = new Moneda(Long.valueOf("0"));
            productoCredito = new ProductoCredito(Long.valueOf("0"));
            periodicidad = new Periodicidad(Long.valueOf("0"));
            origenRecursos = new OrigenRecursos(Long.valueOf("0"));
            listaLineaCreditoPlazo = new ArrayList<>();
            listaLineaCreditoPlazoMaximo = new ArrayList<>();
            linea = new LineaCredito();
            linea.setCodigoUsuario(usuario.getCodigo());
            linea.setCodigoIfip(ifip);
            linea.setFechaRegistro(new Date());
            setSelected(linea);
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "nuevo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Edita una nueva linea de credito
     *
     * @param event
     */
    public void edita(ActionEvent event) {
        try {
            edicion = true;
            linea = getSelected();
            moneda = linea.getCodigoMoneda();
            productoCredito = linea.getCodigoProducto();
            periodicidad = linea.getCodigoPeriodicidad();
            origenRecursos = linea.getCodigoOrigenRecursos();
            listaProductoCredito = ejbFacadeProductoCreditoMonedaIfip.getItemsProductoCreditobyCodigoIfipMoneda(moneda.getCodigo(), ifip.getCodigo(), 'N');
            listaPeriodicidad = ejbFacadePeriodicidadProMonIfiFacade.getPeriodicidadProMonIfiElim(productoCredito.getCodigo(), moneda.getCodigo(), ifip.getCodigo(), 'N', 'S');
            listaLineaCreditoPlazo = ejbFacadeLineaCreditoPlazo.getListaLineaCreditoPlazoPorLinea(getSelected().getCodigo());
            listaLineaCreditoPlazoMaximo = ejbFacadeLineaCreditoPlazoMaximo.getListaLineaCreditoPlazoMaximoPorLinea(getSelected().getCodigo());
            if (listaLineaCreditoPlazoMaximo == null) {
                listaLineaCreditoPlazoMaximo = new ArrayList<>();
            }
            if (listaLineaCreditoPlazo == null) {
                listaLineaCreditoPlazo = new ArrayList<>();
            }
            wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot().findComponent("lineaCreditoEditForm:wiz");
            wizard.setStep("lineaCredito");
            RequestContext.getCurrentInstance().update("lineaCreditoEditForm:wiz");
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "edita", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * Muestra una nueva linea de credito
     *
     * @param event
     */
    public void muestra(ActionEvent event) {
        try {
            linea = getSelected();
            moneda = linea.getCodigoMoneda();
            productoCredito = linea.getCodigoProducto();
            periodicidad = linea.getCodigoPeriodicidad();
            origenRecursos = linea.getCodigoOrigenRecursos();
            listaProductoCredito = ejbFacadeProductoCreditoMonedaIfip.getItemsProductoCreditobyCodigoIfipMoneda(moneda.getCodigo(), ifip.getCodigo(), 'N');
            listaPeriodicidad = ejbFacadePeriodicidadProMonIfiFacade.getPeriodicidadProMonIfiElim(productoCredito.getCodigo(), moneda.getCodigo(), ifip.getCodigo(), 'N', 'S');
            listaLineaCreditoPlazo = ejbFacadeLineaCreditoPlazo.getListaLineaCreditoPlazoPorLinea(getSelected().getCodigo());
            listaLineaCreditoPlazoMaximo = ejbFacadeLineaCreditoPlazoMaximo.getListaLineaCreditoPlazoMaximoPorLinea(getSelected().getCodigo());
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "muestra", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Guardar linea de credito
     *
     * @param event
     */
    public void guarda(ActionEvent event) {
        try {
            if (FacesContext.getCurrentInstance().isValidationFailed()) {
                return;
            }
            if (!validaCreaLineaCredito()) {
                return;
            }
            if (linea.getPlazoAutomatico().equals("N")) {
                if (!validaListaLineaCreditoPlazo()) {
                    return;
                }
                linea.setLineaCreditoPlazoCollection(listaLineaCreditoPlazo);
            } else {
                if (!validaListaLineaCreditoPlazoMaximo()) {
                    return;
                }
                linea.setLineaCreditoPlazoMaximoCollection(listaLineaCreditoPlazoMaximo);
            }
            linea.setCodigoProducto(productoCredito);
            linea.setCodigoPeriodicidad(periodicidad);
            linea.setCodigoOrigenRecursos(origenRecursos);
            linea.setCodigoMoneda(moneda);
            linea.setCodigoIfip(ifip);
            if (!edicion) {
                ejbFacade.create(linea);
            } else {
                ejbFacade.edit(linea);
            }
            MuestraMensaje.addSatisfactorioPersistencia(1);
            listaLineaCredito = ejbFacade.getListaLineaCreditoPorIfipVigente(ifip.getCodigo(), "S");
            guardaAuditoria(edicion ? 'U' : 'I');
        } catch (Exception ex) {
            MuestraMensaje.addErrorPersistencia();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "guarda", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * 
     * @param accion
     */
    public void guardaAuditoria(char accion){
        try{
            ejbFacadeLineaCreditoAuditoria.guardaLineaCreditoAuditoria(linea, accion);
            ejbFacadeLineaCreditoPlazoAuditoria.guardaLineaCreditoPlazoAuditoria(listaLineaCreditoPlazo, accion);   
            ejbFacadeLineaCreditoPlazoMaximoAud.guardaLineaCreditoPlazoMaximoAuditoria(listaLineaCreditoPlazoMaximo, accion);
        } catch (Exception ex) {
            MuestraMensaje.addAdvertencia("Error al guardar auditorias");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "guardaAuditoria", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Ejecuta un cambio de moneda del combo y actualzia el producto de credito
     *
     */
    public void cambiaMoneda() {
        try {
            if (moneda != null) {
                listaProductoCredito = ejbFacadeProductoCreditoMonedaIfip.getItemsProductoCreditobyCodigoIfipMoneda(moneda.getCodigo(), ifip.getCodigo(), 'N');
            } else {
                listaProductoCredito = null;
                productoCredito = new ProductoCredito(Long.valueOf("0"));
            }
            listaPeriodicidad = null;
            periodicidad = new Periodicidad(Long.valueOf("0"));
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "cambiaProductoCredito", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Ejecuta un cambio de producto credito del combo y actualiza la
     * periodicidad
     *
     */
    public void cambiaProductoCredito() {
        try {
            if (productoCredito != null) {
                listaPeriodicidad = ejbFacadePeriodicidadProMonIfiFacade.getPeriodicidadProMonIfiElim(productoCredito.getCodigo(), moneda.getCodigo(), ifip.getCodigo(), 'N', 'S');
            } else {
                listaPeriodicidad = null;
                periodicidad = new Periodicidad(Long.valueOf("0"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "cambiaPeriodicidad", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Ejecuta un cambio de plazo automatico y solicita la informacion de plazos
     * para la linea de credito
     *
     */
    public void habilitaTipoDePlazo() {
        try {
            if (getSelected().getPlazoAutomatico().equals("N")) {
                inactivaControlesLineaCreditoPlazo = false;
                inactivaControlesLineaCreditoPlazoMaximo = true;
            } else {
                inactivaControlesLineaCreditoPlazoMaximo = false;
                inactivaControlesLineaCreditoPlazo = true;
            }
            if (!edicion) {
                listaLineaCreditoPlazo = new ArrayList<>();
                listaLineaCreditoPlazoMaximo = new ArrayList<>();
            } else {
                listaLineaCreditoPlazo = ejbFacadeLineaCreditoPlazo.getListaLineaCreditoPlazoPorLinea(getSelected().getCodigo());
                listaLineaCreditoPlazoMaximo = ejbFacadeLineaCreditoPlazoMaximo.getListaLineaCreditoPlazoMaximoPorLinea(getSelected().getCodigo());
                if (listaLineaCreditoPlazoMaximo == null) {
                    listaLineaCreditoPlazoMaximo = new ArrayList<>();
                }
                if (listaLineaCreditoPlazo == null) {
                    listaLineaCreditoPlazo = new ArrayList<>();
                }
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "cargaPlazoAutomatico", CapturaError.getErrorException(ex)});
        }

    }

    /**
     *
     * @return
     */
    public boolean validaCreaLineaCredito() {
        try {
            boolean valido = true;
            if (moneda == null) {
                MuestraMensaje.addError("Seleccionar campo moneda");
                valido = false;
            } else {
                if (moneda.getCodigo().compareTo(Long.valueOf("0")) == 0) {
                    MuestraMensaje.addError("Seleccionar campo moneda");
                    valido = false;
                }
            }
            if (productoCredito == null) {
                MuestraMensaje.addError("Seleccionar campo producto crédito");
                valido = false;
            } else {
                if (productoCredito.getCodigo().compareTo(Long.valueOf("0")) == 0) {
                    MuestraMensaje.addError("Seleccionar campo producto crédito");
                    valido = false;
                }
            }
            if (periodicidad == null) {
                MuestraMensaje.addError("Seleccionar campo periodicidad");
                valido = false;
            } else {
                if (periodicidad.getCodigo().compareTo(Long.valueOf("0")) == 0) {
                    MuestraMensaje.addError("Seleccionar campo periodicidad");
                    valido = false;
                }
            }
            if (origenRecursos == null) {
                MuestraMensaje.addError("Seleccionar campo origen recursos");
                valido = false;
            } else {
                if (origenRecursos.getCodigo().compareTo(Long.valueOf("0")) == 0) {
                    MuestraMensaje.addError("Seleccionar campo origen recursos");
                    valido = false;
                }
            }
            try {
                BigDecimal montoMinimo = getSelected().getMontoMinimo();
                if ((montoMinimo.compareTo(BigDecimal.ZERO) == 0) || (montoMinimo.compareTo(BigDecimal.ZERO) == -1)) {
                    MuestraMensaje.addError("El campo monto minimo debe ser mayor que 0");
                    valido = false;
                }
            } catch (Exception ex) {
                MuestraMensaje.addError("El campo monto minimo debe ser mayor que 0");
                valido = false;
            }
            try {
                BigDecimal montoMaximo = getSelected().getMontoMaximo();
                if ((montoMaximo.compareTo(BigDecimal.ZERO) == 0) || (montoMaximo.compareTo(BigDecimal.ZERO) == -1)) {
                    MuestraMensaje.addError("El campo monto maximo debe ser mayor que 0");
                    valido = false;
                } else {
                    if (montoMaximo.compareTo(getSelected().getMontoMinimo()) == -1) {
                        MuestraMensaje.addError("El campo monto maximo debe ser mayor que monto minimo");
                        valido = false;
                    }
                }
            } catch (Exception ex) {
                MuestraMensaje.addError("El campo monto maximo debe ser mayor que 0");
                valido = false;
            }
            try {
                int diaGeneraCredito = (int) getSelected().getDiaGeneraCredito();
                if (!(diaGeneraCredito >= 1 && diaGeneraCredito <= 28)) {
                    MuestraMensaje.addError("El campo dia generar credito debe estar entre 1 y 28");
                    valido = false;
                }
            } catch (Exception ex) {
                MuestraMensaje.addError("El campo dia generar credito debe estar entre 1 y 28");
                valido = false;
            }
            try {
                BigDecimal porcentajePagoMinimo = getSelected().getPorcentajePagoMinimo();
                if ((porcentajePagoMinimo.compareTo(BigDecimal.ZERO) == 0) || (porcentajePagoMinimo.compareTo(BigDecimal.ZERO) == -1)) {
                    MuestraMensaje.addError("El campo porcentaje pago minimo debe ser mayor que 0");
                    valido = false;
                }
            } catch (Exception ex) {
                MuestraMensaje.addError("El campo porcentaje pago minimo debe ser mayor que 0");
                valido = false;
            }
            try {
                int diaParaBloqueo = (int) getSelected().getDiaParaBloqueo();
                if (diaParaBloqueo <= 0) {
                    MuestraMensaje.addError("El campo día bloqueo debe ser mayor que 0");
                    valido = false;
                }
            } catch (Exception ex) {
                MuestraMensaje.addError("El campo día bloqueo debe ser mayor que 0");
                valido = false;
            }
            return valido;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "validaCreaLineaCredito", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     * Permite el paso del proceso del asistente
     *
     * @param event
     * @return
     */
    public String flujoProceso(FlowEvent event) {
        try {
            String tab;
            if (event.getOldStep().equals("lineaCredito")) {
                if (validaCreaLineaCredito()) {
                    if (getSelected().getPlazoAutomatico().equals("N")) {
                        tab = "lineaCreditoPlazo";
                    } else {
                        tab = "lineaCreditoPlazoMaximo";
                    }
                } else {
                    tab = event.getOldStep();
                }
            } else {
                if ((event.getOldStep().equals("lineaCreditoPlazo")) && (event.getNewStep().equals("lineaCredito"))) {
                    tab = "lineaCredito";
                } else {
                    if ((event.getOldStep().equals("lineaCreditoPlazo")) && (event.getNewStep().equals("lineaCreditoPlazoMaximo"))) {
                        tab = "lineaCreditoPlazo";
                    } else {
                        if ((event.getOldStep().equals("lineaCreditoPlazoMaximo")) && (event.getNewStep().equals("lineaCredito"))) {
                            tab = "lineaCredito";
                        } else {
                            if ((event.getOldStep().equals("lineaCreditoPlazoMaximo")) && (event.getNewStep().equals("lineaCreditoPlazo"))) {
                                tab = "lineaCredito";
                            } else {
                                tab = "lineaCreditoPlazoMaximo";
                            }
                        }
                    }
                }
            }
            return tab;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "flujoProceso", CapturaError.getErrorException(ex)});
            return event.getOldStep();
        }
    }

    /**
     *
     * @param event
     */
    public void adicionarFilaLineaCreditoPlazo(ActionEvent event) {
        try {
            if (listaLineaCreditoPlazo != null) {
                listaLineaCreditoPlazo.add(new LineaCreditoPlazo(null, null, null, Long.valueOf("1"), "N", linea));
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAdicionarFila"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "adicionarFilaLineaCreditoPlazo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void adicionarFilaLineaCreditoPlazoMaximo(ActionEvent event) {
        try {
            if (listaLineaCreditoPlazoMaximo != null) {
                listaLineaCreditoPlazoMaximo.add(new LineaCreditoPlazoMaximo(null, null, null, Long.valueOf("1"), Long.valueOf("1"), "N", linea));
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAdicionarFila"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "adicionarFilaLineaCreditoPlazoMaximo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param codigo
     * @param indice
     */
    public void eliminarFilaLineaCreditoPlazo(Long codigo, int indice) {
        try {
            if (codigo == null) {
                listaLineaCreditoPlazoArray = ((ArrayList) listaLineaCreditoPlazo);
                listaLineaCreditoPlazoArray.remove(indice);
                listaLineaCreditoPlazo = listaLineaCreditoPlazoArray;
            } else if (codigo.longValue() == 0L) {
                listaLineaCreditoPlazoArray = ((ArrayList) listaLineaCreditoPlazo);
                listaLineaCreditoPlazoArray.remove(indice);
                listaLineaCreditoPlazo = listaLineaCreditoPlazoArray;
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorEliminarFila") + ", no se puede eliminar un registro grabado");
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "eliminarFilaLineaCreditoPlazo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param codigo
     * @param indice
     */
    public void eliminarFilaLineaCreditoPlazoMaximo(Long codigo, int indice) {
        try {
            if (codigo == null) {
                listaLineaCreditoPlazoMaximoArray = ((ArrayList) listaLineaCreditoPlazoMaximo);
                listaLineaCreditoPlazoMaximoArray.remove(indice);
                setListaLineaCreditoPlazoMaximo(listaLineaCreditoPlazoMaximoArray);
            } else if (codigo.longValue() == 0L) {
                listaLineaCreditoPlazoMaximoArray = ((ArrayList) listaLineaCreditoPlazoMaximo);
                listaLineaCreditoPlazoMaximoArray.remove(indice);
                setListaLineaCreditoPlazoMaximo(listaLineaCreditoPlazoMaximoArray);
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorEliminarFila") + ", no se puede eliminar un registro grabado");
            }

        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "eliminarFilaLineaCreditoPlazoMaximo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void editaCeldaLineaCreditoPlazo(CellEditEvent event) {
        try {
            celdaValida = true;
            mensajeValidaCelda = "";
            Object valor = event.getNewValue();
            nombreCeldaValidada = event.getColumn().getHeaderText().toUpperCase().replace(" ", "");
            listaLineaCreditoPlazoArray = ((ArrayList) listaLineaCreditoPlazo);
            LineaCreditoPlazo lineaCreditoPlazo;
            BigDecimal valorMonto;
            for (int i = 0; i < listaLineaCreditoPlazoArray.size(); i++) {
                lineaCreditoPlazo = (LineaCreditoPlazo) listaLineaCreditoPlazoArray.get(i);
                if (nombreCeldaValidada.equals("MONTOINICIOPLAZO") && (i != event.getRowIndex())) {
                    valorMonto = new BigDecimal(valor.toString());
                    if (((valorMonto.compareTo(lineaCreditoPlazo.getMontoInicioPlazo()) == 1) || (valorMonto.compareTo(lineaCreditoPlazo.getMontoInicioPlazo()) == 0))
                            && ((valorMonto.compareTo(lineaCreditoPlazo.getMontoFinPlazo()) == -1) || (valorMonto.compareTo(lineaCreditoPlazo.getMontoFinPlazo()) == 0))) {
                        celdaValida = false;
                        mensajeValidaCelda = ("El valor asignado ya se encuentra dentro del rango en la fila: " + (i + 2));
                        break;
                    }
                }
            }
            lineaCreditoPlazo = (LineaCreditoPlazo) listaLineaCreditoPlazoArray.get(event.getRowIndex());
            if (nombreCeldaValidada.equals("MONTOFINPLAZO")) {
                valorMonto = new BigDecimal(valor.toString());
                try {
                    if (lineaCreditoPlazo.getMontoInicioPlazo().compareTo(valorMonto) == 1) {
                        celdaValida = false;
                        mensajeValidaCelda = ("El valor del monto final debe ser mayor al monto inicial en la fila: " + (event.getRowIndex()));
                    }
                } catch (Exception e) {
                    celdaValida = false;
                    mensajeValidaCelda = ("El valor del monto inicial no debe ser nulo en la fila: " + (event.getRowIndex()));
                }
            }
            if (!celdaValida) {
                MuestraMensaje.addError(mensajeValidaCelda);
                FacesContext.getCurrentInstance().validationFailed();
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "editaCeldaLineaCreditoPlazo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void editaCeldaLineaCreditoPlazoMaximo(CellEditEvent event) {
        try {
            celdaValida = true;
            mensajeValidaCelda = "";
            Object valor = event.getNewValue();
            nombreCeldaValidada = event.getColumn().getHeaderText().toUpperCase().replace(" ", "");
            listaLineaCreditoPlazoMaximoArray = ((ArrayList) listaLineaCreditoPlazoMaximo);
            LineaCreditoPlazoMaximo lineaCreditoPlazoMaximo;
            BigDecimal valorMonto;
            Long valorCuota;
            for (int i = 0; i < listaLineaCreditoPlazoMaximoArray.size(); i++) {
                lineaCreditoPlazoMaximo = (LineaCreditoPlazoMaximo) listaLineaCreditoPlazoMaximoArray.get(i);
                if (nombreCeldaValidada.equals("MONTOINICIOPLAZO") && (i != event.getRowIndex())) {
                    valorMonto = new BigDecimal(valor.toString());
                    if (((valorMonto.compareTo(lineaCreditoPlazoMaximo.getMontoInicioPlazo()) == 1) || (valorMonto.compareTo(lineaCreditoPlazoMaximo.getMontoInicioPlazo()) == 0))
                            && ((valorMonto.compareTo(lineaCreditoPlazoMaximo.getMontoFinPlazo()) == -1) || (valorMonto.compareTo(lineaCreditoPlazoMaximo.getMontoFinPlazo()) == 0))) {
                        celdaValida = false;
                        mensajeValidaCelda = ("El valor del monto asignado ya se encuentra dentro del rango en la fila: " + (i + 2));
                        break;
                    }
                }
                if (nombreCeldaValidada.equals("NUMEROINICIOCUOTAS") && (i != event.getRowIndex())) {
                    valorCuota = new Long(valor.toString());
                    if (((valorCuota >= lineaCreditoPlazoMaximo.getNumeroCuotasInicio()) && (valorCuota <= lineaCreditoPlazoMaximo.getNumeroCuotasFin()))) {
                        celdaValida = false;
                        mensajeValidaCelda = ("El valor del plazo asignado ya se encuentra dentro del rango en la fila: " + (i + 2));
                        break;
                    }
                }
            }
            lineaCreditoPlazoMaximo = (LineaCreditoPlazoMaximo) listaLineaCreditoPlazoMaximoArray.get(event.getRowIndex());
            if (nombreCeldaValidada.equals("MONTOFINPLAZO")) {
                valorMonto = new BigDecimal(valor.toString());
                try {
                    if (lineaCreditoPlazoMaximo.getMontoInicioPlazo().compareTo(valorMonto) == 1) {
                        celdaValida = false;
                        mensajeValidaCelda = ("El valor del monto final debe ser mayor al monto inicial en la fila: " + (event.getRowIndex()));
                    }
                } catch (Exception e) {
                    celdaValida = false;
                    mensajeValidaCelda = ("El valor del monto inicial no debe ser nulo en la fila: " + (event.getRowIndex()));
                }
            }
            if (nombreCeldaValidada.equals("NUMEROFINCUOTAS")) {
                valorCuota = new Long(valor.toString());
                try {
                    if (valorCuota <= lineaCreditoPlazoMaximo.getNumeroCuotasInicio()) {
                        celdaValida = false;
                        mensajeValidaCelda = ("El valor del numero cuotas final debe ser mayor al numero cuotas inicial en la fila: " + (event.getRowIndex()));
                    }
                } catch (Exception e) {
                    celdaValida = false;
                    mensajeValidaCelda = ("El valor del numero cuotas inicial no debe ser nulo en la fila: " + (event.getRowIndex()));
                }
            }
            if (!celdaValida) {
                MuestraMensaje.addError(mensajeValidaCelda);
                FacesContext.getCurrentInstance().validationFailed();
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "editaCeldaLineaCreditoPlazoMaximo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean validaListaLineaCreditoPlazo() {
        try {
            listaLineaCreditoPlazoArray = ((ArrayList) listaLineaCreditoPlazo);
            LineaCreditoPlazo lineaCreditoPlazo;
            if (listaLineaCreditoPlazoArray == null) {
                MuestraMensaje.addError("Debe ingresar los parametros linea credito plazo");
                return false;
            }
            if (listaLineaCreditoPlazoArray.isEmpty()) {
                MuestraMensaje.addError("Debe ingresar los parametros linea credito plazo");
                return false;
            }
            for (int i = 0; i < listaLineaCreditoPlazoArray.size(); i++) {
                lineaCreditoPlazo = listaLineaCreditoPlazoArray.get(i);
                if (lineaCreditoPlazo.getMontoInicioPlazo() == null) {
                    MuestraMensaje.addError("EL valor del monto inicio plazo en la fila " + (i + 1) + " no debe ser nulo");
                    return false;
                }
                if (lineaCreditoPlazo.getMontoFinPlazo() == null) {
                    MuestraMensaje.addError("EL valor del monto fin plazo en la fila " + (i + 1) + " no debe ser nulo");
                    return false;
                }
                if (lineaCreditoPlazo.getNumeroCuotas() == null) {
                    MuestraMensaje.addError("EL valor del numero de cuotas en la fila " + (i + 1) + " debe ser mayor o igual a 1");
                    return false;
                }
                if (lineaCreditoPlazo.getMontoInicioPlazo() == BigDecimal.ZERO) {
                    MuestraMensaje.addError("EL valor del monto inicio plazo en la fila " + (i + 1) + " no debe ser nulo");
                    return false;
                }
                if (lineaCreditoPlazo.getMontoFinPlazo() == BigDecimal.ZERO) {
                    MuestraMensaje.addError("EL valor del monto fin plazo en la fila " + (i + 1) + " no debe ser nulo");
                    return false;
                }
                if (lineaCreditoPlazo.getNumeroCuotas() <= 0) {
                    MuestraMensaje.addError("EL valor del numero de cuotas en la fila " + (i + 1) + " debe ser mayor o igual a 1");
                    return false;
                }
                if (lineaCreditoPlazo.getMontoFinPlazo().compareTo(lineaCreditoPlazo.getMontoInicioPlazo()) == -1) {
                    MuestraMensaje.addError("El valor del monto final debe ser mayor al monto inicial en la fila " + (i + 1));
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "validaListaLineaCreditoPlazo", CapturaError.getErrorException(e)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaListaLineaCreditoPlazoMaximo() {
        try {
            listaLineaCreditoPlazoMaximoArray = ((ArrayList) listaLineaCreditoPlazoMaximo);
            LineaCreditoPlazoMaximo lineaCreditoPlazoMaximo;
            if (listaLineaCreditoPlazoMaximoArray == null) {
                MuestraMensaje.addError("Debe ingresar los parametros linea credito plazo maximo");
                return false;
            }
            if (listaLineaCreditoPlazoMaximoArray.isEmpty()) {
                MuestraMensaje.addError("Debe ingresar los parametros linea credito plazo maximo");
                return false;
            }
            for (int i = 0; i < listaLineaCreditoPlazoMaximoArray.size(); i++) {
                lineaCreditoPlazoMaximo = listaLineaCreditoPlazoMaximoArray.get(i);
                if (lineaCreditoPlazoMaximo.getMontoInicioPlazo() == null) {
                    MuestraMensaje.addError("EL valor del monto inicio plazo en la fila " + (i + 1) + " no debe ser nulo");
                    return false;
                }
                if (lineaCreditoPlazoMaximo.getMontoFinPlazo() == null) {
                    MuestraMensaje.addError("EL valor del monto fin plazo en la fila " + (i + 1) + " no debe ser nulo");
                    return false;
                }
                if (lineaCreditoPlazoMaximo.getMontoInicioPlazo() == BigDecimal.ZERO) {
                    MuestraMensaje.addError("EL valor del monto inicio plazo en la fila " + (i + 1) + " no debe ser nulo");
                    return false;
                }
                if (lineaCreditoPlazoMaximo.getMontoFinPlazo() == BigDecimal.ZERO) {
                    MuestraMensaje.addError("EL valor del monto fin plazo en la fila " + (i + 1) + " no debe ser nulo");
                    return false;
                }
                if (lineaCreditoPlazoMaximo.getMontoFinPlazo().compareTo(lineaCreditoPlazoMaximo.getMontoInicioPlazo()) == -1) {
                    MuestraMensaje.addError("El valor del monto final debe ser mayor al monto inicial en la fila " + (i + 1));
                    return false;
                }
                if (lineaCreditoPlazoMaximo.getNumeroCuotasInicio() == null) {
                    MuestraMensaje.addError("EL valor del numero de cuotas inicial en la fila " + (i + 1) + " debe ser mayor o igual a 1");
                    return false;
                }
                if (lineaCreditoPlazoMaximo.getNumeroCuotasFin() == null) {
                    MuestraMensaje.addError("EL valor del numero de cuotas final en la fila " + (i + 1) + " debe ser mayor o igual a 1");
                    return false;
                }
                if (lineaCreditoPlazoMaximo.getNumeroCuotasInicio() <= 0) {
                    MuestraMensaje.addError("EL valor del numero de cuotas inicial en la fila " + (i + 1) + " debe ser mayor o igual a 1");
                    return false;
                }
                if (lineaCreditoPlazoMaximo.getNumeroCuotasFin() <= 0) {
                    MuestraMensaje.addError("EL valor del numero de cuotas final en la fila " + (i + 1) + " debe ser mayor o igual a 1");
                    return false;
                }
                if (lineaCreditoPlazoMaximo.getNumeroCuotasInicio() < lineaCreditoPlazoMaximo.getNumeroCuotasFin()) {
                    MuestraMensaje.addError("El valor del numero de cuotas final debe ser mayor al numero de cuotas inicial en la fila " + (i + 1));
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "validaListaLineaCreditoPlazoMaximo", CapturaError.getErrorException(e)});
            return false;
        }
    }
    
    /***
     * Metodo para obtener la solicitudes de linea de credito para su activacion
     */
    public void obtieneSolicitudLineaActivacion(){
        try{
            //Lista de estados
            List<Long> estado = new ArrayList<>();
            estado.add(4L);
            List<LineaCreditoSolicitud> listaCreditoSolictiud = ejbFacadeLineaCreditoSolicitud.getListaLineaCreditoSolicitudPorCodigoAgenciaListaCodigoEstado(ActivacionUsuario.getCodigoIfipAgencia(),estado);
            if(listaCreditoSolictiud!=null){
                for(int a=0;a<listaCreditoSolictiud.size();a++){
                    //Obtener el nombre de la agencia
                    IfipAgencia agencia = ejbFacadeIfipAgencia.getAgenciaPorCodigo(listaCreditoSolictiud.get(a).getCodigoIfipAgencia());
                    //Obtener el nombre del socio
                    Socio socio = ejbFacadeSocio.getSocioPorCodigo(listaCreditoSolictiud.get(a).getCodigoSocio());
                    listaCreditoSolictiud.get(a).setAgencia(agencia.getNombre());
                    listaCreditoSolictiud.get(a).setNombreSocio(socio.getCodigoPersona().getNombreCompleto());
                }                
            }
            setLineaCreditoSolicitudParaActivarLista(listaCreditoSolictiud);
        }catch (Exception e){
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "obtieneSolicitudLineaActivacion", CapturaError.getErrorException(e)});
        }
    }
    /***
     * Metodo para activar una linea de credito
     */
    public void activaLineaCreditoSolicitud(){
        try{
            if (lineaCreditoSolicitudParaActivarSeleccion!=null) {
                llamaSP.cargaConexion();
                llamaSP.setCerrarConexion(false);
                llamaSP.autoCommit(false);
                llamaSP.setNombreSP("mks_creditos.pkm_linea_credito_solicitud.p_actualiza_estado_con_aud");
                llamaSP.setListParametrosSalida(new ArrayList<>());
                llamaSP.setListParametrosEntrada(new ArrayList<>());
                llamaSP.setNumeroParametros(4);
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_cod_lin_cre_sol", lineaCreditoSolicitudParaActivarSeleccion.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_estado", 8L});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_accion", "U"});
                llamaSP.invocaSP();
                if (!llamaSP.isEjecucionCorrecta()) {
                    llamaSP.rollback();
                        MuestraMensaje.addErrorCapaControl();
                        return;
                }
                MuestraMensaje.addSatisfactorioPersistencia(1);
                llamaSP.commit();
                setLineaCreditoSolicitudParaActivarSeleccion(null);
                obtieneSolicitudLineaActivacion();
            }
        }catch (Exception ex) {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.rollback();
            }
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAprobarNegarController", "activaLineaCreditoSolicitud", CapturaError.getErrorException(ex)});
        }finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }
    
    /**
     * *
     * Metodo para imprimir el contrato
     */
    public void imprimirSolicitudEnPDF() {
        try {
            String nombreReporte;
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String directoriReporte = "/financiero/lineaCreditos/lineaCreditoSolicitud/reportelinea/";
            setGeneraReporte(new GeneraReporte());
            getGeneraReporte().setParametros(new HashMap<>());
            getGeneraReporte().getParametros().put("codigoLineaCredito", lineaCreditoSolicitudParaActivarSeleccion.getCodigo());
            String reporteAbsoluto = externalContext.getRealPath(directoriReporte + "contratoCrediAmigo.jasper");
            String nombreCortado = reporteAbsoluto.substring(0, reporteAbsoluto.indexOf("contratoCrediAmigo.jasper"));
            getGeneraReporte().getParametros().put("contratoCrediAmigo1", nombreCortado);
            nombreReporte = "contratoCrediAmigo";
            getGeneraReporte().exporta(directoriReporte, nombreReporte, nombreReporte + new java.util.Date().getTime() + ".pdf", "PDF", externalContext, facesContext);
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "imprimirEnPDF", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * *
     * Método para imprimir el pagaré
     */
    public void imprimirPagareEnPDF(){
        try{
            String nombreReporte;
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String directoriReporte = "/financiero/lineaCreditos/lineaCreditoSolicitud/reportelinea/";
            setGeneraReporte(new GeneraReporte());
            getGeneraReporte().setParametros(new HashMap<>());
            getGeneraReporte().getParametros().put("codigoLineaCredito", lineaCreditoSolicitudParaActivarSeleccion.getCodigo());
            String reporteAbsoluto = externalContext.getRealPath(directoriReporte + "pagareLineaCredito.jasper");
            String nombreCortado = reporteAbsoluto.substring(0, reporteAbsoluto.indexOf("pagareLineaCredito.jasper"));
            getGeneraReporte().getParametros().put("contratoCrediAmigo1", nombreCortado);
            nombreReporte = "pagareLineaCredito";
            getGeneraReporte().exporta(directoriReporte, nombreReporte, nombreReporte + new java.util.Date().getTime() + ".pdf", "PDF", externalContext, facesContext);
        }catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoController", "imprimirPagareEnPDF", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * @return the listaLineaCredito
     */
    public List<LineaCredito> getListaLineaCredito() {
        return listaLineaCredito;
    }

    /**
     * @param listaLineaCredito the listaLineaCredito to set
     */
    public void setListaLineaCredito(List<LineaCredito> listaLineaCredito) {
        this.listaLineaCredito = listaLineaCredito;
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

    /**
     * @return the edicion
     */
    public boolean isEdicion() {
        return edicion;
    }

    /**
     * @param edicion the edicion to set
     */
    public void setEdicion(boolean edicion) {
        this.edicion = edicion;
    }

    /**
     * @return the listaMoneda
     */
    public List<Moneda> getListaMoneda() {
        return listaMoneda;
    }

    /**
     * @param listaMoneda the listaMoneda to set
     */
    public void setListaMoneda(List<Moneda> listaMoneda) {
        this.listaMoneda = listaMoneda;
    }

    /**
     * @return the listaProductoCredito
     */
    public List<ProductoCredito> getListaProductoCredito() {
        return listaProductoCredito;
    }

    /**
     * @param listaProductoCredito the listaProductoCredito to set
     */
    public void setListaProductoCredito(List<ProductoCredito> listaProductoCredito) {
        this.listaProductoCredito = listaProductoCredito;
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the productoCredito
     */
    public ProductoCredito getProductoCredito() {
        return productoCredito;
    }

    /**
     * @param productoCredito the productoCredito to set
     */
    public void setProductoCredito(ProductoCredito productoCredito) {
        this.productoCredito = productoCredito;
    }

    /**
     * @return the periodicidad
     */
    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    /**
     * @param periodicidad the periodicidad to set
     */
    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    /**
     * @return the listaPeriodicidad
     */
    public List<Periodicidad> getListaPeriodicidad() {
        return listaPeriodicidad;
    }

    /**
     * @param listaPeriodicidad the listaPeriodicidad to set
     */
    public void setListaPeriodicidad(List<Periodicidad> listaPeriodicidad) {
        this.listaPeriodicidad = listaPeriodicidad;
    }

    /**
     * @return the origenRecursos
     */
    public OrigenRecursos getOrigenRecursos() {
        return origenRecursos;
    }

    /**
     * @param origenRecursos the origenRecursos to set
     */
    public void setOrigenRecursos(OrigenRecursos origenRecursos) {
        this.origenRecursos = origenRecursos;
    }

    /**
     * @return the listaOrigenRecursos
     */
    public List<OrigenRecursos> getListaOrigenRecursos() {
        return listaOrigenRecursos;
    }

    /**
     * @param listaOrigenRecursos the listaOrigenRecursos to set
     */
    public void setListaOrigenRecursos(List<OrigenRecursos> listaOrigenRecursos) {
        this.listaOrigenRecursos = listaOrigenRecursos;
    }

    /**
     * @return the listaLineaCreditoPlazo
     */
    public List<LineaCreditoPlazo> getListaLineaCreditoPlazo() {
        return listaLineaCreditoPlazo;
    }

    /**
     * @param listaLineaCreditoPlazo the listaLineaCreditoPlazo to set
     */
    public void setListaLineaCreditoPlazo(List<LineaCreditoPlazo> listaLineaCreditoPlazo) {
        this.listaLineaCreditoPlazo = listaLineaCreditoPlazo;
    }

    /**
     * @return the inactivaControlesLineaCreditoPlazo
     */
    public boolean isInactivaControlesLineaCreditoPlazo() {
        return inactivaControlesLineaCreditoPlazo;
    }

    /**
     * @param inactivaControlesLineaCreditoPlazo the
     * inactivaControlesLineaCreditoPlazo to set
     */
    public void setInactivaControlesLineaCreditoPlazo(boolean inactivaControlesLineaCreditoPlazo) {
        this.inactivaControlesLineaCreditoPlazo = inactivaControlesLineaCreditoPlazo;
    }

    /**
     * @return the inactivaControlesLineaCreditoPlazoMaximo
     */
    public boolean isInactivaControlesLineaCreditoPlazoMaximo() {
        return inactivaControlesLineaCreditoPlazoMaximo;
    }

    /**
     * @param inactivaControlesLineaCreditoPlazoMaximo the
     * inactivaControlesLineaCreditoPlazoMaximo to set
     */
    public void setInactivaControlesLineaCreditoPlazoMaximo(boolean inactivaControlesLineaCreditoPlazoMaximo) {
        this.inactivaControlesLineaCreditoPlazoMaximo = inactivaControlesLineaCreditoPlazoMaximo;
    }

    /**
     * @return the listaLineaCreditoPlazoMaximo
     */
    public List<LineaCreditoPlazoMaximo> getListaLineaCreditoPlazoMaximo() {
        return listaLineaCreditoPlazoMaximo;
    }

    /**
     * @param listaLineaCreditoPlazoMaximo the listaLineaCreditoPlazoMaximo to
     * set
     */
    public void setListaLineaCreditoPlazoMaximo(List<LineaCreditoPlazoMaximo> listaLineaCreditoPlazoMaximo) {
        this.listaLineaCreditoPlazoMaximo = listaLineaCreditoPlazoMaximo;
    }

    /**
     * @return the linea
     */
    public LineaCredito getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(LineaCredito linea) {
        this.linea = linea;
    }

    /**
     * @return the lineaCreditoSolicitudParaActivarLista
     */
    public List<LineaCreditoSolicitud> getLineaCreditoSolicitudParaActivarLista() {
        return lineaCreditoSolicitudParaActivarLista;
    }

    /**
     * @param lineaCreditoSolicitudParaActivarLista the lineaCreditoSolicitudParaActivarLista to set
     */
    public void setLineaCreditoSolicitudParaActivarLista(List<LineaCreditoSolicitud> lineaCreditoSolicitudParaActivarLista) {
        this.lineaCreditoSolicitudParaActivarLista = lineaCreditoSolicitudParaActivarLista;
    }

    /**
     * @return the lineaCreditoSolicitudParaActivarSeleccion
     */
    public LineaCreditoSolicitud getLineaCreditoSolicitudParaActivarSeleccion() {
        return lineaCreditoSolicitudParaActivarSeleccion;
    }

    /**
     * @param lineaCreditoSolicitudParaActivarSeleccion the lineaCreditoSolicitudParaActivarSeleccion to set
     */
    public void setLineaCreditoSolicitudParaActivarSeleccion(LineaCreditoSolicitud lineaCreditoSolicitudParaActivarSeleccion) {
        this.lineaCreditoSolicitudParaActivarSeleccion = lineaCreditoSolicitudParaActivarSeleccion;
    }
    
    /**
     * 
     * @return 
     */
    public GeneraReporte getGeneraReporte() {
        return generaReporte;
    }

    /**
     * 
     * @param generaReporte 
     */
    public void setGeneraReporte(GeneraReporte generaReporte) {
        this.generaReporte = generaReporte;
    }

}