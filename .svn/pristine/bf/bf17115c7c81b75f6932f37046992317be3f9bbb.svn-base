/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.creditos.lineacredito;

import ec.renafipse.mks.capas.impresiones.ImpresionComprobantes;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.creditos.lineacredito.AvanceLineaCredito;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud;
import ec.renafipse.mks.modelo.creditos.lineacredito.PagoAvanceLineaCredito;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.AvanceLineaCreditoFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoSolicitudFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.PagoAvanceLineaCreditoFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author andres
 */
@ManagedBean(name = "lineaCreditoPagoAvanceController")
@ViewScoped
public class LineaCreditoPagoAvanceController implements Serializable {

    @EJB
    private LineaCreditoSolicitudFacade lineaCreditoSolicitudFacade;
    @EJB
    private SocioFacade socioFacade;
    @EJB
    private AperturaFacade ejbFacadeApertura;
    @EJB
    private PagoAvanceLineaCreditoFacade pagoAvanceLineaCreditoFacade;
    @EJB
    private AvanceLineaCreditoFacade avanceLineaCreditoFacade;
    @EJB
    private IfipAgenciaFacade ejbIfipAgenciaFacade;
    private String nombreCompletoSocio;
    private List<LineaCreditoSolicitud> listaLineaCreditoSolicitud;
    private List<AvanceLineaCredito> listaAvanceLineaCredito;
    private AvanceLineaCredito avanceLineaCredito;
    private LineaCreditoSolicitud lineaCreditoSolicitud;
    private BigDecimal valorPago;
    private String formaPago;
    private PagoAvanceLineaCredito pagoAvanceLineaCredito;
    private boolean habilitaBotonPagoAvanceParcial;
    private boolean habilitaBotonPagoAvanceTotal;
    private Socio socio;
    private final String urlSinPermiso = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
    private boolean esPagoParcial;
    private ImpresionComprobantes impresionComprobantes;
    private IfipAgencia ifipAgencia;

    @PostConstruct
    public void init() {
        try {
            esPagoParcial = false;
            ifipAgencia = ejbIfipAgenciaFacade.getAgenciaPorCodigo(ActivacionUsuario.getCodigoIfipAgencia());
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "init", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaAvanceLineaCredito() {
        try {
            vaciarCamposCambiaAvanceLineaCredito();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "vaciarCamposCambiaAvanceLineaCredito", CapturaError.getErrorException(ex)});
        }

    }

    /**
     *
     */
    public void vaciarCamposCambiaAvanceLineaCredito() {
        try {
            formaPago = null;
            if (avanceLineaCredito != null) {
                pagoAvanceLineaCredito = new PagoAvanceLineaCredito();
                pagoAvanceLineaCredito.setCapital(avanceLineaCredito.getSaldoCapital());
                pagoAvanceLineaCredito.setInteres(avanceLineaCredito.getInteresPendiente());
                pagoAvanceLineaCredito.setTotal(pagoAvanceLineaCredito.getCapital().add(pagoAvanceLineaCredito.getInteres()));
            } else {
                pagoAvanceLineaCredito = null;
            }
            valorPago = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "vaciarCamposCambiaTipoPagoParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void nuevoPagoAvanceParcialLineaCredito() {
        try {
            valorPago = null;
            formaPago = null;
            pagoAvanceLineaCredito = null;
            listaAvanceLineaCredito = null;
            avanceLineaCredito = null;
            listaAvanceLineaCredito = avanceLineaCreditoFacade.getListaLineaCreditoPorCodigoSolicitudPendientePago(lineaCreditoSolicitud.getCodigo());
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "nuevoPagoAvanceParcialLineaCredito", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void nuevoPagoAvanceTotalLineaCredito() {
        try {
            valorPago = null;
            formaPago = null;
            pagoAvanceLineaCredito = null;
            listaAvanceLineaCredito = null;
            avanceLineaCredito = null;
            listaAvanceLineaCredito = avanceLineaCreditoFacade.getListaLineaCreditoPorCodigoSolicitudPendientePago(lineaCreditoSolicitud.getCodigo());
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "nuevoPagoAvanceTotalLineaCredito", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void obtieneLineasPagoDesdeComponente() {
        try {
            nombreCompletoSocio = "";
            listaLineaCreditoSolicitud = null;
            socio = null;
            String valorCampo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("componenteSocioForm:componenteBusca:listaSociosComponenteForm:buscaSocioComponenteCodigoSocio");
            if (valorCampo == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersona"));
                return;
            }
            if (valorCampo.isEmpty()) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersona"));
                return;
            }
            Long codigoSocio = Long.valueOf(valorCampo);
            if (codigoSocio == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersona"));
                return;
            } else {
                if (codigoSocio.compareTo(Long.valueOf("0")) == 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersona"));
                    return;
                }
            }
            socio = socioFacade.getSocioPorCodigo(codigoSocio);
            if (socio == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste"));
                return;
            }
            listaLineaCreditoSolicitud = lineaCreditoSolicitudFacade.getListaLineaCreditoAvancePendientePagoPorCodigoSocio(codigoSocio);
            if (listaLineaCreditoSolicitud != null) {
                if (listaLineaCreditoSolicitud.size() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                } else {
                    socio = socioFacade.getSocioPorCodigo(getListaLineaCreditoSolicitud().get(0).getCodigoSocio());
                    nombreCompletoSocio = socio.getCodigoPersona().getNombreCompleto();
                }
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "obtieneLineasPagoDesdeComponente", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void onRowSelect(SelectEvent event) {
        try {
            lineaCreditoSolicitud = (LineaCreditoSolicitud) event.getObject();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "onRowSelect", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void onRowUnselect(UnselectEvent event) {
        try {
            lineaCreditoSolicitud = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "onRowUnselect", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaValorTipoPagoAvanceParcial() {
        try {
            vaciarCamposCambiaValorTipoPagoAvanceParcial();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "cambiaValorTipoPagoAvanceParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void vaciarCamposCambiaValorTipoPagoAvanceParcial() {
        try {
            formaPago = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "vaciarCamposCambiaValorTipoPagoParcial", CapturaError.getErrorException(ex)});
        }
    }
    
     /**
     *
     */
    public void cambiaValorTipoPagoAvanceTotal() {
        try {
            vaciarCamposCambiaValorTipoPagoAvanceTotal();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "cambiaValorTipoPagoAvanceTotal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void vaciarCamposCambiaValorTipoPagoAvanceTotal() {
        try {
            formaPago = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "vaciarCamposCambiaValorTipoPagoAvanceTotal", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     *
     */
    public void cambiaFormaPagoAvanceParcial() {
        try {
            vaciarCamposCambiaFormaPagoAvanceParcial();
            calculaPagoLineaCreditoAvanceParcial();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "cambiaFormaPagoAvanceParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void vaciarCamposCambiaFormaPagoAvanceParcial() {
        try {
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "vaciarCamposCambiaFormaPagoParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void calculaPagoLineaCreditoAvanceParcial() {
        try {
            if (validaValorPagoAvanceParcial()) {
                if (validaFormaPagoAvanceParcial()) {
                    pagoAvanceLineaCredito = new PagoAvanceLineaCredito();
                    pagoAvanceLineaCredito.setCapital(avanceLineaCredito.getSaldoCapital());
                    pagoAvanceLineaCredito.setInteres(avanceLineaCredito.getInteresPendiente());
                    pagoAvanceLineaCredito.setMora(BigDecimal.ZERO);
                    pagoAvanceLineaCredito.setNotificacion(BigDecimal.ZERO);
                    pagoAvanceLineaCredito.setRubro(BigDecimal.ZERO);
                    pagoAvanceLineaCredito.setCostoJudicial(BigDecimal.ZERO);
                    pagoAvanceLineaCredito.setTotal(pagoAvanceLineaCredito.getCapital().add(pagoAvanceLineaCredito.getInteres()));
                    habilitaBotonPagoAvanceParcial = true;
                } else {
                    habilitaBotonPagoAvanceParcial = false;
                }
            } else {
                habilitaBotonPagoAvanceParcial = false;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "calculaPagoLineaCreditoAvanceParcial", CapturaError.getErrorException(ex)});
            pagoAvanceLineaCredito = null;
            habilitaBotonPagoAvanceParcial = false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaValorPagoAvanceParcial() {
        try {
            if (pagoAvanceLineaCredito == null) {
                MuestraMensaje.addError("Debe seleccionar un avance de la linea de crédito");
                return false;
            }
            BigDecimal bigDecimal = valorPago;
            if (bigDecimal.compareTo(BigDecimal.ZERO) > 0) {
                if (bigDecimal.compareTo(pagoAvanceLineaCredito.getTotal()) < 0) {
                    return true;
                } else {
                    if(bigDecimal.compareTo(pagoAvanceLineaCredito.getTotal()) == 0){
                        MuestraMensaje.addError("Si desea realizar el pago total de un avance, ingrese a Pago Total por favor.");
                    }
                    MuestraMensaje.addError("Ingrese un valor menor que " + pagoAvanceLineaCredito.getTotal());
                    return false;
                }
            } else {                
                MuestraMensaje.addError("Ingrese un valor mayor que 0.00");
                return false;
            }
        } catch (NumberFormatException e) {
            MuestraMensaje.addError("Ingrese un valor valido #0.00#");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "validaValorPorTipoPago", CapturaError.getErrorException(e)});
            return false;
        } catch (Exception ex) {
            MuestraMensaje.addError("Ingrese un valor valido #0.00#");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "validaValorPorTipoPago", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaFormaPagoAvanceParcial() {
        try {
            switch (formaPago) {
                //VALIDA PAGO INGRESO CAJA
                case "I":
                    return validaPermisosYAperturaCaja();
                //VALIDA PAGO DEBITO CUENTA
                case "D":
                    return validaValorDebitoCuentaParcial();
                default:
                    return false;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "validaFormaPago", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaPermisosYAperturaCaja() {
        try {
            List<Apertura> listaApertura = ejbFacadeApertura.getItemsAperturaUsuarioIfipAgencia(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), 'A');
            if (!listaApertura.isEmpty()) {
                if (listaApertura.size() == 1) {
                    if (!Validaciones.validaFechaIgualHoy(listaApertura.get(0).getFechaApertura())) {
                        ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AperturaNoCorrespondeDiaActual") + " " + DateFormat.getDateInstance(DateFormat.MEDIUM).format(listaApertura.get(0).getFechaApertura()));
                        Sesion.redireccionaPagina(urlSinPermiso);
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioMasUnaApertura"));
                    Sesion.redireccionaPagina(urlSinPermiso);
                    return false;
                }
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTieneAperturaCaja"));
                listaApertura = ejbFacadeApertura.getItemsUsuarioEstado(ActivacionUsuario.getCodigoUsuario(), 'A');
                if (!listaApertura.isEmpty()) {
                    String nombreAgencia = null;
                    if (listaApertura.size() == 1) {
                        nombreAgencia = listaApertura.get(0).getIfipAgencia().getNombre();
                    }
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioAperturoCajaOtraAgencia") + " " + nombreAgencia);
                    Sesion.redireccionaPagina(urlSinPermiso);
                }
                return false;
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "validaPermisosYAperturaCaja", CapturaError.getErrorException(e)});
            MuestraMensaje.addErrorCapaControl();
            try {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                Sesion.redireccionaPagina(urlSinPermiso);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"LineaCreditoPagoAvanceController", "validaPermisosYAperturaCaja", CapturaError.getErrorException(ex)});
                MuestraMensaje.addErrorCapaControl();
            }
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaValorDebitoCuentaParcial() {
        try {
            if (lineaCreditoSolicitud.getCuentaDebito().getSaldoTotal().compareTo(valorPago) < 0) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible"));
                return false;
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "validaValorDebitoCuenta", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void realizaPagoAvanceLineaCreditoParcial() {
        try {
            if (validaFormaPagoAvanceParcial()) {
                if (pagoAvanceLineaCredito != null) {
                    PagoAvanceLineaCredito pago = pagoAvanceLineaCreditoFacade.pagoParcialAvanceLineaCredito(avanceLineaCredito.getCodigo(),
                            valorPago,
                            formaPago,
                            ActivacionUsuario.getCodigoUsuario(),
                            ActivacionUsuario.getCodigoIfipAgencia(),
                            "PAGO PARCIAL AVANCE LINEA CREDITO");
                    if (pago.getError() == null) {
                        pagoAvanceLineaCredito.setCodigo(pago.getCodigo());
                        avanceLineaCredito.setCodigoLineaCreditoSolicitud(lineaCreditoSolicitud);
                        pagoAvanceLineaCredito.setAvanceLineaCredito(avanceLineaCredito);
                        esPagoParcial = true;
                        MuestraMensaje.addSatisfactorioPersistencia(1);
                        RequestContext.getCurrentInstance().execute("ImprimeComprobanteDialogo.show();");
                    } else {
                        MuestraMensaje.addError(pago.getError());
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                                new Object[]{"LineaCreditoPagoAvanceController", "realizaPagoAvanceLineaCreditoParcial", pago.getError()});
                    }
                } else {
                    MuestraMensaje.addError("Debe calcular el valor del pago");
                }
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "realizaPagoAvanceLineaCreditoParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaTipoPagoAvanceTotal() {
        try {
            vaciarCamposCambiaTipoPagoAvanceTotal();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "cambiaTipoPagoAvanceTotal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void vaciarCamposCambiaTipoPagoAvanceTotal() {
        try {
            formaPago = null;
            pagoAvanceLineaCredito = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "vaciarCamposCambiaTipoPagoAvanceTotal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaFormaPagoAvanceTotal() {
        try {
            vaciarCamposCambiaFormaPagoAvanceTotal();
            calculaPagoLineaCreditoAvanceTotal();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "cambiaFormaPagoAvanceTotal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void vaciarCamposCambiaFormaPagoAvanceTotal() {
        try {
            pagoAvanceLineaCredito = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "vaciarCamposCambiaFormaPagoAvanceTotal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void calculaPagoLineaCreditoAvanceTotal() {
        try {
            if (validaFormaPagoAvanceTotal()) {
                pagoAvanceLineaCredito = new PagoAvanceLineaCredito();
                pagoAvanceLineaCredito.setCapital(avanceLineaCredito.getSaldoCapital());
                pagoAvanceLineaCredito.setInteres(avanceLineaCredito.getInteresPendiente());
                pagoAvanceLineaCredito.setMora(BigDecimal.ZERO);
                pagoAvanceLineaCredito.setNotificacion(BigDecimal.ZERO);
                pagoAvanceLineaCredito.setRubro(BigDecimal.ZERO);
                pagoAvanceLineaCredito.setCostoJudicial(BigDecimal.ZERO);
                pagoAvanceLineaCredito.setTotal(pagoAvanceLineaCredito.getCapital().add(pagoAvanceLineaCredito.getInteres()));
                habilitaBotonPagoAvanceTotal = true;
            } else {
                habilitaBotonPagoAvanceTotal = false;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "calculaPagoLineaCreditoAvanceTotal", CapturaError.getErrorException(ex)});
            pagoAvanceLineaCredito = null;
            habilitaBotonPagoAvanceTotal = false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaFormaPagoAvanceTotal() {
        try {
            switch (formaPago) {
                //VALIDA PAGO INGRESO CAJA
                case "I":
                    return validaPermisosYAperturaCaja();
                case "D":
                    return true;
                default:
                    return false;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "validaFormaPagoAvanceTotal", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void realizaPagoAvanceLineaCreditoTotal() {
        try {
            if (pagoAvanceLineaCredito != null && validaValorDebitoCuentaAvanceTotal()) {
                PagoAvanceLineaCredito pago = pagoAvanceLineaCreditoFacade.pagoTotalAvanceLineaCredito(avanceLineaCredito.getCodigo(),
                        valorPago,
                        formaPago,
                        ActivacionUsuario.getCodigoUsuario(),
                        ActivacionUsuario.getCodigoIfipAgencia(),
                        "PAGO TOTAL AVANCE LINEA CREDITO");
                if (pago.getError() == null) {
                    pagoAvanceLineaCredito.setCodigo(pago.getCodigo());
                    avanceLineaCredito.setCodigoLineaCreditoSolicitud(lineaCreditoSolicitud);
                    pagoAvanceLineaCredito.setAvanceLineaCredito(avanceLineaCredito);
                    esPagoParcial = false;
                    MuestraMensaje.addSatisfactorioPersistencia(1);
                    RequestContext.getCurrentInstance().execute("ImprimeComprobanteDialogo.show();");
                } else {
                    MuestraMensaje.addError(pago.getError());
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                            new Object[]{"LineaCreditoPagoAvanceController", "realizaPagoAvanceLineaCreditoTotal", pago.getError()});
                }
            } else {
                MuestraMensaje.addError("Debe calcular el valor del pago");
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "realizaPagoAvanceLineaCreditoTotal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean validaValorDebitoCuentaAvanceTotal() {
        try {
            if (lineaCreditoSolicitud.getCuentaDebito().getSaldoTotal().compareTo(pagoAvanceLineaCredito.getTotal()) < 0 && formaPago.equals("D")) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible"));
                return false;
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "validaValorDebitoCuentaAvanceTotal", CapturaError.getErrorException(ex)});
            return false;
        }
    }
    
    /**
     *
     */
    public void imprimirComprobante() {
        try {
            impresionComprobantes = new ImpresionComprobantes(ActivacionUsuario.getRutaImpresora(), null);
            boolean impresion = 
                    impresionComprobantes.pagoAvanceLineaCredito(ifipAgencia, pagoAvanceLineaCredito, ActivacionUsuario.getUsuario());
            if (impresion) {
                listaLineaCreditoSolicitud = lineaCreditoSolicitudFacade.getListaLineaCreditoSolicitudPendientePagoPorCodigoSocio(socio.getSocioPK().getCodigo());
                if (listaLineaCreditoSolicitud != null) {
                    if (listaLineaCreditoSolicitud.size() <= 0) {
                        lineaCreditoSolicitud = null;
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                    } else {
                        socio = socioFacade.getSocioPorCodigo(getListaLineaCreditoSolicitud().get(0).getCodigoSocio());
                        nombreCompletoSocio = socio.getCodigoPersona().getNombreCompleto();
                    }
                } else {
                    lineaCreditoSolicitud = null;
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                }
                if (esPagoParcial) {
                    RequestContext.getCurrentInstance().execute("PagoAvanceLineaCreditoParcialDialog.hide();");
                }else{
                    RequestContext.getCurrentInstance().execute("PagoAvanceLineaCreditoTotalDialog.hide();");
                }
                RequestContext.getCurrentInstance().execute("ImprimeComprobanteDialogo.hide();");
            }else{
                MuestraMensaje.addError("Error al imprimir comprobante");
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoAvanceController", "imprimirComprobante", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * @return the nombreCompletoSocio
     */
    public String getNombreCompletoSocio() {
        return nombreCompletoSocio;
    }

    /**
     * @param nombreCompletoSocio the nombreCompletoSocio to set
     */
    public void setNombreCompletoSocio(String nombreCompletoSocio) {
        this.nombreCompletoSocio = nombreCompletoSocio;
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
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @return the pagoAvanceLineaCredito
     */
    public PagoAvanceLineaCredito getPagoAvanceLineaCredito() {
        return pagoAvanceLineaCredito;
    }

    /**
     * @param pagoAvanceLineaCredito the pagoAvanceLineaCredito to set
     */
    public void setPagoAvanceLineaCredito(PagoAvanceLineaCredito pagoAvanceLineaCredito) {
        this.pagoAvanceLineaCredito = pagoAvanceLineaCredito;
    }

    /**
     * @return the habilitaBotonPagoAvanceParcial
     */
    public boolean isHabilitaBotonPagoAvanceParcial() {
        return habilitaBotonPagoAvanceParcial;
    }

    /**
     * @param habilitaBotonPagoAvanceParcial the habilitaBotonPagoAvanceParcial
     * to set
     */
    public void setHabilitaBotonPagoAvanceParcial(boolean habilitaBotonPagoAvanceParcial) {
        this.habilitaBotonPagoAvanceParcial = habilitaBotonPagoAvanceParcial;
    }

    /**
     * @return the habilitaBotonPagoAvanceTotal
     */
    public boolean isHabilitaBotonPagoAvanceTotal() {
        return habilitaBotonPagoAvanceTotal;
    }

    /**
     * @param habilitaBotonPagoAvanceTotal the habilitaBotonPagoAvanceTotal to
     * set
     */
    public void setHabilitaBotonPagoAvanceTotal(boolean habilitaBotonPagoAvanceTotal) {
        this.habilitaBotonPagoAvanceTotal = habilitaBotonPagoAvanceTotal;
    }

    /**
     * @return the listaAvanceLineaCredito
     */
    public List<AvanceLineaCredito> getListaAvanceLineaCredito() {
        return listaAvanceLineaCredito;
    }

    /**
     * @param listaAvanceLineaCredito the listaAvanceLineaCredito to set
     */
    public void setListaAvanceLineaCredito(List<AvanceLineaCredito> listaAvanceLineaCredito) {
        this.listaAvanceLineaCredito = listaAvanceLineaCredito;
    }

    /**
     * @return the avanceLineaCredito
     */
    public AvanceLineaCredito getAvanceLineaCredito() {
        return avanceLineaCredito;
    }

    /**
     * @param avanceLineaCredito the avanceLineaCredito to set
     */
    public void setAvanceLineaCredito(AvanceLineaCredito avanceLineaCredito) {
        this.avanceLineaCredito = avanceLineaCredito;
    }

    /**
     * @return the valorPago
     */
    public BigDecimal getValorPago() {
        return valorPago;
    }

    /**
     * @param valorPago the valorPago to set
     */
    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }
}
