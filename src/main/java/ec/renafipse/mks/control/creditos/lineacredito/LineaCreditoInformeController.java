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
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.TasaInteresCreditoIfip;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitudInfTec;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.creditos.TasaInteresCreditoIfipFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoSolicitudFacade;
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
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author andres
 */
@ManagedBean(name = "lineaCreditoInformeController")
@ViewScoped
public class LineaCreditoInformeController extends AbstractController<LineaCreditoSolicitudInfTec> implements Serializable {

    @EJB
    private SocioFacade ejbFacadeSocio;
    @EJB
    private LineaCreditoSolicitudFacade ejbFacadeLineaCreditoSolicitud;
    @EJB
    private TasaInteresCreditoIfipFacade ejbFacadeTasaInteresCreditoIfip;
    @EJB
    private LlamaSP llamaSP;

    private LineaCreditoSolicitudInfTec lineaCreditoSolicitudInformeTecnico;
    private LineaCreditoSolicitud lineaCreditoSolicitud;
    private List<LineaCreditoSolicitud> listaLineaCreditoSolicitud;
    private List<TasaInteresCreditoIfip> listaTasaInteresCreditoIfip;
    private String criterioBusqueda;
    private String nombreSocio;
    private Long codigoSocio;
    private Socio socio;
    private String valoresLong;
    private boolean desactivarImprime;
    private List<Long> codigosEstado;
    private boolean permiteNuevoInforme;
    private boolean permiteImprimirInforme;
    private String mensaje;
    private BigDecimal tasa;
    private boolean inactivaFactible;
    private boolean inactivaRecomendacion;
    private BigDecimal totalIngresos;
    private BigDecimal totalIngresosConyuge;
    private BigDecimal totalGastos;
    private BigDecimal saldo;
    private BigDecimal cobertura;
    private BigDecimal totalActivos;
    private BigDecimal totalPasivos;
    private BigDecimal totalPatrimonio;
    private UIViewRoot vista;
    private UIComponent componente;
    private boolean procesoExitoso;
    private boolean procesoError;
    private GeneraReporte generaReporte;

    @PostConstruct
    public void init() {
        try {
            inicializaPropiedades();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "init", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void nuevo() {
        try {
            if (permiteNuevoInforme) {
                lineaCreditoSolicitudInformeTecnico = new LineaCreditoSolicitudInfTec();
                lineaCreditoSolicitudInformeTecnico.setCodigoLineaCreditoSol(lineaCreditoSolicitud.getCodigo());
                lineaCreditoSolicitudInformeTecnico.setTotalIngresos(BigDecimal.ZERO);
                lineaCreditoSolicitudInformeTecnico.setTotalEgresos(BigDecimal.ZERO);
                lineaCreditoSolicitudInformeTecnico.setTotalFlujoCaja(BigDecimal.ZERO);
                lineaCreditoSolicitudInformeTecnico.setTotalActivos(BigDecimal.ZERO);
                lineaCreditoSolicitudInformeTecnico.setTotalPasivos(BigDecimal.ZERO);
                lineaCreditoSolicitudInformeTecnico.setTotalPatrimonio(BigDecimal.ZERO);
                lineaCreditoSolicitudInformeTecnico.setCobertura(BigDecimal.ZERO);
                lineaCreditoSolicitudInformeTecnico.setFechaRegistro(new java.sql.Timestamp(new Date().getTime()));
                lineaCreditoSolicitudInformeTecnico.setCodigoUsuario(ActivacionUsuario.getCodigoUsuario());
                listaTasaInteresCreditoIfip = ejbFacadeTasaInteresCreditoIfip.getItemsTasaInteres(lineaCreditoSolicitud.getLineaCredito().getCodigoProducto().getCodigo(),
                        lineaCreditoSolicitud.getLineaCredito().getCodigoMoneda().getCodigo(),
                        lineaCreditoSolicitud.getLineaCredito().getCodigoIfip().getCodigo(),
                        'N');
                tasa = listaTasaInteresCreditoIfip.get(0).getValor();
                inactivaFactible = false;
                inactivaRecomendacion = false;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "nuevo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void guardar() {
        try {
            setProcesoExitoso(false);
            setProcesoError(false);
            if (permiteNuevoInforme) {
                if (validaDatosInformeTecnico()) {
                    llamaSP.cargaConexion();
                    llamaSP.setCerrarConexion(false);
                    llamaSP.autoCommit(false);
                    llamaSP.setNombreSP("mks_creditos.pkm_linea_cre_sol_inf_tec.p_inserta_informe_act_sol");
                    llamaSP.setNumeroParametros(11);
                    llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_linea_cre_sol", lineaCreditoSolicitud.getCodigo()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_factible", String.valueOf(lineaCreditoSolicitudInformeTecnico.getFactible())});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_recomendacion", lineaCreditoSolicitudInformeTecnico.getRecomendacion()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_total_ingresos", totalIngresos.add(totalIngresosConyuge)});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_total_egresos", totalGastos});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_total_flujo_caja", totalIngresos.add(totalIngresosConyuge).subtract(totalGastos)});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_total_activos", totalActivos});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_total_pasivos", totalPasivos});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_total_patrimonio", totalPatrimonio});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_cobertura", cobertura});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
                    llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                    llamaSP.invocaSP();
                    if (llamaSP.isEjecucionCorrecta()) {
                        llamaSP.commit();
                        setProcesoExitoso(true);
                        permiteNuevoInforme = false;
                        permiteImprimirInforme = true;
                        inactivaFactible = true;
                        inactivaRecomendacion = true;
                        codigosEstado = new ArrayList<Long>();
                        codigosEstado.add(Long.valueOf("1"));
                        codigosEstado.add(Long.valueOf("2"));
                        listaLineaCreditoSolicitud = ejbFacadeLineaCreditoSolicitud.getListaLineaCreditoSolicitudPorCodigoSocioListaCodigoEstado(socio.getSocioPK().getCodigo(), codigosEstado);
                        if (listaLineaCreditoSolicitud != null) {
                            if (listaLineaCreditoSolicitud.size() <= 0) {
                                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                            }
                        } else {
                            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                        }
                        RequestContext.getCurrentInstance().execute("informeTecnicoNuevoDialog.hide();");

                    } else {
                        setProcesoError(true);
                        llamaSP.rollback();
                    }
                }else{
                    MuestraMensaje.addError(this.mensaje);
                }
            }
        } catch (Exception ex) {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.rollback();
            }
            setProcesoError(true);
            MuestraMensaje.addErrorPersistencia();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "guardar", CapturaError.getErrorException(ex)});
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     *
     */
    public void cargarValoresFlujoCaja() {
        try {
            vista = FacesContext.getCurrentInstance().getViewRoot();
            componente = vista.findComponent(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalIngresosSocio"));
            totalIngresos = (BigDecimal) componente.getAttributes().get("value");
            componente = vista.findComponent(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalIngresosConyuge"));
            totalIngresosConyuge = (BigDecimal) componente.getAttributes().get("value");
            componente = vista.findComponent(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalGastosSocio"));
            totalGastos = (BigDecimal) componente.getAttributes().get("value");
            componente = vista.findComponent(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "saldo"));
            saldo = (BigDecimal) componente.getAttributes().get("value");
            componente = vista.findComponent(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "cobertura"));
            cobertura = (BigDecimal) componente.getAttributes().get("value");
        } catch (Exception ex) {
            totalIngresos = BigDecimal.ZERO;
            totalIngresosConyuge = BigDecimal.ZERO;
            totalGastos = BigDecimal.ZERO;
            saldo = BigDecimal.ZERO;
            cobertura = BigDecimal.ZERO;
            MuestraMensaje.addError("Error al obtener valores flujo caja");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "cargarValoresFlujoCaja", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cargarValoresSituacionPatrimonial() {
        try {
            vista = FacesContext.getCurrentInstance().getViewRoot();
            componente = vista.findComponent(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalActivosLabel"));
            totalActivos = (BigDecimal) componente.getAttributes().get("value");
            componente = vista.findComponent(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalPasivosLabel"));
            totalPasivos = (BigDecimal) componente.getAttributes().get("value");
            componente = vista.findComponent(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalPatrimonioLabel"));
            totalPatrimonio = (BigDecimal) componente.getAttributes().get("value");
        } catch (Exception ex) {
            totalActivos = BigDecimal.ZERO;
            totalPasivos = BigDecimal.ZERO;
            totalPatrimonio = BigDecimal.ZERO;
            MuestraMensaje.addError("Error al obtener valores flujo caja");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "cargarValoresSituacionPatrimonial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void cambiaFactible(AjaxBehaviorEvent event) {
        try {
            char factible = (java.lang.Character) ((UIOutput) event.getSource()).getValue();
            lineaCreditoSolicitudInformeTecnico.setFactible(factible);
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "cambiaFactible", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean validaDatosInformeTecnico() {
        try {
            if (lineaCreditoSolicitud == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformeTecnicoLineaSolicitud"));
                return false;
            }
            try {
                lineaCreditoSolicitudInformeTecnico.getFactible();
            } catch (Exception ex) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformeTecnicoLineaFactible"));
                return false;
            }
            if (lineaCreditoSolicitudInformeTecnico.getRecomendacion() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformeTecnicoLineaRecomendacion"));
                return false;
            }
            if (totalIngresos.compareTo(BigDecimal.ZERO) < 0) {
                MuestraMensaje.addError("Total de ingresos debe ser mayor o igual a 0.00");
                return false;
            }
            if (totalIngresosConyuge.compareTo(BigDecimal.ZERO) < 0) {
                MuestraMensaje.addError("Total de ingresos de conyuge debe ser mayor o igual que 0.00");
                return false;
            }
            if (totalGastos.compareTo(BigDecimal.ZERO) < 0) {
                MuestraMensaje.addError("Total de egresos debe ser mayor o igual a 0.00");
                return false;
            }
            BigDecimal valorTotal = BigDecimal.ZERO;
            valorTotal = valorTotal.add(totalIngresos);
            valorTotal = valorTotal.add(totalIngresosConyuge);
            valorTotal = valorTotal.subtract(totalGastos);
            /*if (valorTotal.compareTo(BigDecimal.ZERO) == 0) {
                MuestraMensaje.addError("Total de (ingresos - egresos) debe ser mayor o igual que 0.00");
                return false;
            }*/
            if (totalActivos.compareTo(BigDecimal.ZERO) < 0) {
                mensaje = "Total de activos debe ser mayor o igual a 0.00";
                return false;
            }
            if (totalPasivos.compareTo(BigDecimal.ZERO) < 0) {
                mensaje = "Total de pasivos debe ser mayor o igual a 0.00";
                return false;
            }
            /*if (totalPatrimonio.compareTo(BigDecimal.ZERO) == 0) {
                mensaje = "Total de patrimonio debe ser mayor que 0.00";
                return false;
            }*/
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "validaDatosInformeTecnico", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @param event
     */
    public void onRowSelect(SelectEvent event) {
        try {
            lineaCreditoSolicitud = (LineaCreditoSolicitud) event.getObject();
            if (lineaCreditoSolicitud.getCodigoLineaCreditoEstadoSol().compareTo(Long.valueOf("1")) == 0) {
                permiteImprimirInforme = false;
                permiteNuevoInforme = true;
                inactivaFactible = false;
                inactivaRecomendacion = false;
            }
            if (lineaCreditoSolicitud.getCodigoLineaCreditoEstadoSol().compareTo(Long.valueOf("2")) == 0) {
                permiteImprimirInforme = true;
                permiteNuevoInforme = false;
                inactivaFactible = true;
                inactivaRecomendacion = true;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "onRowSelect", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void onRowUnselect(UnselectEvent event) {
        try {
            lineaCreditoSolicitud = null;
            permiteImprimirInforme = false;
            permiteNuevoInforme = false;
            inactivaFactible = true;
            inactivaRecomendacion = true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "onRowUnselect", CapturaError.getErrorException(ex)});
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
            if (event.getNewStep().equals("confirmacion")) {
                cargarValoresFlujoCaja();
                cargarValoresSituacionPatrimonial();
            }
            return event.getNewStep();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "flujoProceso", CapturaError.getErrorException(ex)});
            return event.getOldStep();
        }
    }

    /**
     *
     */
    public void obtieneSolicitudesDeLinea() {
        try {
            inicializaPropiedades();
            if (criterioBusqueda == null) {
                MuestraMensaje.addAdvertencia("Criterio consulta " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido") + " Para Busqueda");
                return;
            } else {
                if (criterioBusqueda.length() <= 0) {
                    MuestraMensaje.addAdvertencia("Criterio consulta " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido") + " Para Busqueda");
                    return;
                }
            }
            codigosEstado = new ArrayList<Long>();
            codigosEstado.add(Long.valueOf("1"));
            codigosEstado.add(Long.valueOf("2"));
            listaLineaCreditoSolicitud = ejbFacadeLineaCreditoSolicitud.getListaLineaCreditoSolicitudPorCodigoListaCodigoEstadoCodigoAgencia(Long.valueOf(criterioBusqueda), codigosEstado, ActivacionUsuario.getCodigoIfipAgencia());
            if (listaLineaCreditoSolicitud != null) {
                if (listaLineaCreditoSolicitud.size() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                } else {
                    socio = ejbFacadeSocio.getSocioPorCodigo(listaLineaCreditoSolicitud.get(0).getCodigoSocio());
                    nombreSocio = socio.getCodigoPersona().getNombreCompleto();
                    codigoSocio = socio.getSocioPK().getCodigo();
                }
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "obtieneSolicitudesDeLinea", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void obtieneSolicitudesDeLineaDesdeComponente() {
        try {
            criterioBusqueda = "";
            inicializaPropiedades();
            valoresLong = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("barraForm:componenteBusca:listaSociosComponenteForm:buscaSocioComponenteCodigoPersona");
            if (valoresLong == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            } else {
                if (valoresLong.length() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                    return;
                }
            }
            if ((Long.valueOf(valoresLong).compareTo(Long.valueOf("0")) == 0)) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            valoresLong = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("barraForm:componenteBusca:listaSociosComponenteForm:buscaSocioComponenteCodigoSocio");
            if (valoresLong == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            } else {
                if (valoresLong.length() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                    return;
                }
            }
            if ((Long.valueOf(valoresLong).compareTo(Long.valueOf("0")) == 0)) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            socio = ejbFacadeSocio.getSocioPorCodigo(Long.valueOf(valoresLong));
            if (socio == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            nombreSocio = socio.getCodigoPersona().getNombreCompleto();
            codigoSocio = socio.getSocioPK().getCodigo();
            codigosEstado = new ArrayList<Long>();
            codigosEstado.add(Long.valueOf("1"));
            codigosEstado.add(Long.valueOf("2"));
            listaLineaCreditoSolicitud = ejbFacadeLineaCreditoSolicitud.getListaLineaCreditoSolicitudPorCodigoSocioListaCodigoEstado(socio.getSocioPK().getCodigo(), codigosEstado);
            if (listaLineaCreditoSolicitud != null) {
                if (listaLineaCreditoSolicitud.size() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                }
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "obtieneSolicitudesDeLineaDesdeComponente", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void imprimeLineaCreditoInformeTecnico() {
        try {
            String nombreReporte;
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String directoriReporte = "/financiero/lineaCreditos/lineaCreditoInforme/reporteInforme/";
            setGeneraReporte(new GeneraReporte());
            getGeneraReporte().setParametros(new HashMap<>());
            getGeneraReporte().getParametros().put("codigoLineaCredito", lineaCreditoSolicitud.getCodigo());
            getGeneraReporte().getParametros().put("codigoIfip", lineaCreditoSolicitud.getCodigoIfip());
            getGeneraReporte().getParametros().put("subReportGarante",externalContext.getRealPath(directoriReporte + "garantesLineaCreditoListado.jasper"));
            nombreReporte = "informeTecnicoLineaCredito";
            getGeneraReporte().exporta(directoriReporte, nombreReporte, nombreReporte + new java.util.Date().getTime() + ".pdf", "PDF", externalContext, facesContext);
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoInformeController", "imprimeLineaCreditoInformeTecnico", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void inicializaPropiedades() {
        try {
            lineaCreditoSolicitudInformeTecnico = null;
            lineaCreditoSolicitud = null;
            listaLineaCreditoSolicitud = null;
            listaTasaInteresCreditoIfip = null;
            criterioBusqueda = "";
            nombreSocio = "";
            codigoSocio = null;
            socio = null;
            valoresLong = "";
            desactivarImprime = true;
            codigosEstado = null;
            permiteNuevoInforme = false;
            permiteImprimirInforme = false;
            mensaje = "";
            tasa = null;
            inactivaFactible = true;
            inactivaRecomendacion = true;
            totalIngresos = BigDecimal.ZERO;
            totalIngresosConyuge = BigDecimal.ZERO;
            totalGastos = BigDecimal.ZERO;
            saldo = BigDecimal.ZERO;
            cobertura = BigDecimal.ZERO;
            totalActivos = BigDecimal.ZERO;
            totalPasivos = BigDecimal.ZERO;
            totalPatrimonio = BigDecimal.ZERO;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "inicializaPropiedades", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * 
     */
    public void muestraMensajeProceso(){
        try{
            if (isProcesoExitoso()){
                MuestraMensaje.addSatisfactorioPersistencia(1);
            }
            if(isProcesoError()){
                MuestraMensaje.addErrorCapaControl();
            }
            setProcesoError(false);
            setProcesoExitoso(false);
        }catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "muestraMensajeProceso", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * @return the lineaCreditoSolicitud
     */
    public LineaCreditoSolicitud getLineaCreditoSolicitud() {
        return lineaCreditoSolicitud;
    }

    /**
     * @param lineaCreditoSolicitud the lineaCreditoSolicitud to set
     */
    public void setLineaCreditoSolicitud(LineaCreditoSolicitud lineaCreditoSolicitud) {
        this.lineaCreditoSolicitud = lineaCreditoSolicitud;
    }

    /**
     * @return the listaLineaCreditoSolicitud
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitud() {
        return listaLineaCreditoSolicitud;
    }

    /**
     * @param listaLineaCreditoSolicitud the listaLineaCreditoSolicitud to set
     */
    public void setListaLineaCreditoSolicitud(List<LineaCreditoSolicitud> listaLineaCreditoSolicitud) {
        this.listaLineaCreditoSolicitud = listaLineaCreditoSolicitud;
    }

    /**
     * @return the criterioBusqueda
     */
    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    /**
     * @param criterioBusqueda the criterioBusqueda to set
     */
    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    /**
     * @return the nombreSocio
     */
    public String getNombreSocio() {
        return nombreSocio;
    }

    /**
     * @param nombreSocio the nombreSocio to set
     */
    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    /**
     * @return the socio
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    /**
     * @return the desactivarImprime
     */
    public boolean isDesactivarImprime() {
        return desactivarImprime;
    }

    /**
     * @param desactivarImprime the desactivarImprime to set
     */
    public void setDesactivarImprime(boolean desactivarImprime) {
        this.desactivarImprime = desactivarImprime;
    }

    /**
     * @return the permiteNuevoInforme
     */
    public boolean isPermiteNuevoInforme() {
        return permiteNuevoInforme;
    }

    /**
     * @param permiteNuevoInforme the permiteNuevoInforme to set
     */
    public void setPermiteNuevoInforme(boolean permiteNuevoInforme) {
        this.permiteNuevoInforme = permiteNuevoInforme;
    }

    /**
     * @return the permiteImprimirInforme
     */
    public boolean isPermiteImprimirInforme() {
        return permiteImprimirInforme;
    }

    /**
     * @param permiteImprimirInforme the permiteImprimirInforme to set
     */
    public void setPermiteImprimirInforme(boolean permiteImprimirInforme) {
        this.permiteImprimirInforme = permiteImprimirInforme;
    }

    /**
     * @return the lineaCreditoSolicitudInformeTecnico
     */
    public LineaCreditoSolicitudInfTec getLineaCreditoSolicitudInformeTecnico() {
        return lineaCreditoSolicitudInformeTecnico;
    }

    /**
     * @param lineaCreditoSolicitudInformeTecnico the
     * lineaCreditoSolicitudInformeTecnico to set
     */
    public void setLineaCreditoSolicitudInformeTecnico(LineaCreditoSolicitudInfTec lineaCreditoSolicitudInformeTecnico) {
        this.lineaCreditoSolicitudInformeTecnico = lineaCreditoSolicitudInformeTecnico;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the codigoSocio
     */
    public Long getCodigoSocio() {
        return codigoSocio;
    }

    /**
     * @param codigoSocio the codigoSocio to set
     */
    public void setCodigoSocio(Long codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    /**
     * @return the tasa
     */
    public BigDecimal getTasa() {
        return tasa;
    }

    /**
     * @param tasa the tasa to set
     */
    public void setTasa(BigDecimal tasa) {
        this.tasa = tasa;
    }

    /**
     * @return the inactivaFactible
     */
    public boolean isInactivaFactible() {
        return inactivaFactible;
    }

    /**
     * @param inactivaFactible the inactivaFactible to set
     */
    public void setInactivaFactible(boolean inactivaFactible) {
        this.inactivaFactible = inactivaFactible;
    }

    /**
     * @return the inactivaRecomendacion
     */
    public boolean isInactivaRecomendacion() {
        return inactivaRecomendacion;
    }

    /**
     * @param inactivaRecomendacion the inactivaRecomendacion to set
     */
    public void setInactivaRecomendacion(boolean inactivaRecomendacion) {
        this.inactivaRecomendacion = inactivaRecomendacion;
    }

    /**
     * @return the totalIngresos
     */
    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    /**
     * @param totalIngresos the totalIngresos to set
     */
    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    /**
     * @return the totalIngresosConyuge
     */
    public BigDecimal getTotalIngresosConyuge() {
        return totalIngresosConyuge;
    }

    /**
     * @param totalIngresosConyuge the totalIngresosConyuge to set
     */
    public void setTotalIngresosConyuge(BigDecimal totalIngresosConyuge) {
        this.totalIngresosConyuge = totalIngresosConyuge;
    }

    /**
     * @return the totalGastos
     */
    public BigDecimal getTotalGastos() {
        return totalGastos;
    }

    /**
     * @param totalGastos the totalGastos to set
     */
    public void setTotalGastos(BigDecimal totalGastos) {
        this.totalGastos = totalGastos;
    }

    /**
     * @return the saldo
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the cobertura
     */
    public BigDecimal getCobertura() {
        return cobertura;
    }

    /**
     * @param cobertura the cobertura to set
     */
    public void setCobertura(BigDecimal cobertura) {
        this.cobertura = cobertura;
    }

    /**
     * @return the totalActivos
     */
    public BigDecimal getTotalActivos() {
        return totalActivos;
    }

    /**
     * @param totalActivos the totalActivos to set
     */
    public void setTotalActivos(BigDecimal totalActivos) {
        this.totalActivos = totalActivos;
    }

    /**
     * @return the totalPasivos
     */
    public BigDecimal getTotalPasivos() {
        return totalPasivos;
    }

    /**
     * @param totalPasivos the totalPasivos to set
     */
    public void setTotalPasivos(BigDecimal totalPasivos) {
        this.totalPasivos = totalPasivos;
    }

    /**
     * @return the totalPatrimonio
     */
    public BigDecimal getTotalPatrimonio() {
        return totalPatrimonio;
    }

    /**
     * @param totalPatrimonio the totalPatrimonio to set
     */
    public void setTotalPatrimonio(BigDecimal totalPatrimonio) {
        this.totalPatrimonio = totalPatrimonio;
    }

    /**
     * @return the procesoExitoso
     */
    public boolean isProcesoExitoso() {
        return procesoExitoso;
    }

    /**
     * @param procesoExitoso the procesoExitoso to set
     */
    public void setProcesoExitoso(boolean procesoExitoso) {
        this.procesoExitoso = procesoExitoso;
    }

    /**
     * @return the procesoError
     */
    public boolean isProcesoError() {
        return procesoError;
    }

    /**
     * @param procesoError the procesoError to set
     */
    public void setProcesoError(boolean procesoError) {
        this.procesoError = procesoError;
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
