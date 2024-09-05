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
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud;
import ec.renafipse.mks.modelo.creditos.lineacredito.PagoLineaCreditoSolicitud;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoSolicitudFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.PagoLineaCreditoSolicitudFacade;
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
@ManagedBean(name = "lineaCreditoPagoController")
@ViewScoped
public class LineaCreditoPagoController implements Serializable {

    private LineaCreditoSolicitud lineaCreditoSolicitud;
    private String criterioBusqueda;
    private String nombreCompletoSocio;
    private List<LineaCreditoSolicitud> listaLineaCreditoSolicitud;
    @EJB
    private LineaCreditoSolicitudFacade lineaCreditoSolicitudFacade;
    @EJB
    private SocioFacade socioFacade;
    @EJB
    private PagoLineaCreditoSolicitudFacade pagoLineaCreditoSolicitudFacade;
    @EJB
    private AperturaFacade ejbFacadeApertura;
    @EJB
    private IfipAgenciaFacade ejbIfipAgenciaFacade;
    private Socio socio;
    private PagoLineaCreditoSolicitud pagoLineaCreditoSolicitud;
    private String tipoPago;
    private String formaPago;
    private Object valorParaTipoPago;
    private String etiquetaTipoValor;
    private boolean habilitaBotonPagoParcial;
    private boolean habilitaBotonPagoTotal;
    private final String urlSinPermiso = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
    private boolean esPagoParcial;
    private ImpresionComprobantes impresionComprobantes;
    private IfipAgencia ifipAgencia;

    @PostConstruct
    public void init() {
        try {
            etiquetaTipoValor = "VALOR";
            tipoPago = "";
            esPagoParcial = false;
            ifipAgencia = ejbIfipAgenciaFacade.getAgenciaPorCodigo(ActivacionUsuario.getCodigoIfipAgencia());
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "init", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void nuevoPagoParcialLineaCreditoSolicitud() {
        try {
            tipoPago = null;
            valorParaTipoPago = null;
            formaPago = null;
            pagoLineaCreditoSolicitud = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "nuevoPagoParcialLineaCreditoSolicitud", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void nuevoPagoTotalLineaCreditoSolicitud() {
        try {
            tipoPago = null;
            valorParaTipoPago = null;
            formaPago = null;
            pagoLineaCreditoSolicitud = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "nuevoPagoTotalLineaCreditoSolicitud", CapturaError.getErrorException(ex)});
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
            listaLineaCreditoSolicitud = lineaCreditoSolicitudFacade.getListaLineaCreditoSolicitudPendientePagoPorCodigoSocio(codigoSocio);
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
                    new Object[]{"LineaCreditoPagoController", "obtieneLineasPagoDesdeComponente", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaTipoPagoParcial() {
        try {
            etiquetaTipoValor = "VALOR";
            if (tipoPago.equals("V")) {
                etiquetaTipoValor = "VALOR *";
            }
            if (tipoPago.equals("C")) {
                etiquetaTipoValor = "NUMERO CUOTAS *";
            }
            vaciarCamposCambiaTipoPagoParcial();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "cambiaTipoPagoParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void vaciarCamposCambiaTipoPagoParcial() {
        try {
            valorParaTipoPago = null;
            formaPago = null;
            pagoLineaCreditoSolicitud = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "vaciarCamposCambiaTipoPagoParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaValorTipoPagoParcial() {
        try {
            vaciarCamposCambiaValorTipoPagoParcial();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "cambiaValorTipoPagoParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void vaciarCamposCambiaValorTipoPagoParcial() {
        try {
            formaPago = null;
            pagoLineaCreditoSolicitud = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "vaciarCamposCambiaValorTipoPagoParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaFormaPagoParcial() {
        try {
            vaciarCamposCambiaFormaPagoParcial();
            calculaPagoLineaCreditoParcial();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "cambiaFormaPagoParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void vaciarCamposCambiaFormaPagoParcial() {
        try {
            pagoLineaCreditoSolicitud = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "vaciarCamposCambiaFormaPagoParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void calculaPagoLineaCreditoParcial() {
        try {
            if (validaValorPorTipoPagoParcial()) {
                if (validaFormaPagoParcial()) {
                    switch (tipoPago) {
                        case "C":
                            Short numeroCuotas = new Short((String) valorParaTipoPago);
                            pagoLineaCreditoSolicitud = pagoLineaCreditoSolicitudFacade.calculaPagoParcialLineaCreditoSolicitud(lineaCreditoSolicitud.getCodigo(),
                                    numeroCuotas,
                                    BigDecimal.ZERO);

                            if (pagoLineaCreditoSolicitud.getError() == null) {
                                habilitaBotonPagoParcial = true;
                            } else {
                                MuestraMensaje.addError(pagoLineaCreditoSolicitud.getError());
                                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                                        new Object[]{"LineaCreditoPagoController", "calculaPagoParcialLineaCreditoSolicitud", pagoLineaCreditoSolicitud.getError()});
                                habilitaBotonPagoParcial = false;
                            }
                            break;
                        case "V":
                            BigDecimal valor = new BigDecimal((String) valorParaTipoPago);
                            pagoLineaCreditoSolicitud = pagoLineaCreditoSolicitudFacade.calculaPagoParcialLineaCreditoSolicitud(lineaCreditoSolicitud.getCodigo(),
                                    Short.valueOf("0"),
                                    valor);
                            if (pagoLineaCreditoSolicitud.getError() == null) {
                                habilitaBotonPagoParcial = true;
                            } else {
                                MuestraMensaje.addError(pagoLineaCreditoSolicitud.getError());
                                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                                        new Object[]{"LineaCreditoPagoController", "calculaPagoParcialLineaCreditoSolicitud", pagoLineaCreditoSolicitud.getError()});
                                habilitaBotonPagoParcial = false;
                            }
                            break;
                    }
                } else {
                    habilitaBotonPagoParcial = false;
                }
            } else {
                habilitaBotonPagoParcial = false;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "calculaPagoLineaCreditoParcial", CapturaError.getErrorException(ex)});
            pagoLineaCreditoSolicitud = null;
            habilitaBotonPagoParcial = false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaValorPorTipoPagoParcial() {
        try {
            String cadena = (String) valorParaTipoPago;
            if (cadena.isEmpty()) {
                MuestraMensaje.addError("Ingrese un valor en " + etiquetaTipoValor);
                return false;
            }
            switch (tipoPago) {
                //VALIDA CUOTAS
                case "C":
                    Boolean soloNumeros = true;
                    for (int i = 0; i < (cadena.length()); i++) {
                        String caracter = cadena.substring(i, i + 1);
                        try {
                            Short.valueOf(caracter);
                        } catch (NumberFormatException e) {
                            soloNumeros = false;
                            MuestraMensaje.addError("Ingrese un valor valido 1#");
                            break;
                        } catch (Exception ex) {
                            soloNumeros = false;
                            MuestraMensaje.addError("Ingrese un valor valido 1#");
                            break;
                        }
                    }
                    if (Short.valueOf(cadena) <= 0) {
                        soloNumeros = false;
                    }
                    return soloNumeros;
                //VALIDA VALOR
                case "V":
                    try {
                        BigDecimal bigDecimal = new BigDecimal(cadena);
                        if (bigDecimal.compareTo(BigDecimal.ZERO) > 0) {
                            return true;
                        } else {
                            MuestraMensaje.addError("Ingrese un valor mayor que 0.00");
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        MuestraMensaje.addError("Ingrese un valor valido #0.00#");
                        return false;
                    } catch (Exception ex) {
                        MuestraMensaje.addError("Ingrese un valor valido #0.00#");
                        return false;
                    }
                default:
                    MuestraMensaje.addError("Seleccione tipo de pago");
                    return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "validaValorPorTipoPago", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaFormaPagoParcial() {
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
                    new Object[]{"LineaCreditoPagoController", "validaFormaPago", CapturaError.getErrorException(ex)});
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
                    new Object[]{"LineaCreditoPagoController", "validaPermisosYAperturaCaja", CapturaError.getErrorException(e)});
            MuestraMensaje.addErrorCapaControl();
            try {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                Sesion.redireccionaPagina(urlSinPermiso);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"LineaCreditoPagoController", "validaPermisosYAperturaCaja", CapturaError.getErrorException(ex)});
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
            BigDecimal bigDecimal = new BigDecimal((String) valorParaTipoPago);
            if (lineaCreditoSolicitud.getCuentaDebito().getSaldoTotal().compareTo(bigDecimal) < 0) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible"));
                return false;
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "validaValorDebitoCuenta", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void realizaPagoLineaCreditoParcial() {
        try {
            if (pagoLineaCreditoSolicitud != null) {
                PagoLineaCreditoSolicitud pago = pagoLineaCreditoSolicitudFacade.pagoParcialLineaCreditoSolicitud(lineaCreditoSolicitud.getCodigo(),
                        Long.valueOf("0"),
                        ActivacionUsuario.getCodigoIfip(),
                        ActivacionUsuario.getCodigoUsuario(),
                        Long.valueOf("1"),
                        tipoPago,
                        pagoLineaCreditoSolicitud.getTotal(),
                        ActivacionUsuario.getCodigoIfipAgencia(),
                        "PAGO PARCIAL LINEA CREDITO",
                        "N");
                if (pago.getError() == null) {
                    pagoLineaCreditoSolicitud.setCodigo(pago.getCodigo());
                    pagoLineaCreditoSolicitud.setCodigoLineaCreditoSolicitud(lineaCreditoSolicitud.getCodigo());
                    esPagoParcial = true;
                    MuestraMensaje.addSatisfactorioPersistencia(1);
                    RequestContext.getCurrentInstance().execute("ImprimeComprobanteDialogo.show();");
                } else {
                    MuestraMensaje.addError(pago.getError());
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                            new Object[]{"LineaCreditoPagoController", "realizaPagoLineaCreditoParcial", pago.getError()});
                }
            } else {
                MuestraMensaje.addError("Debe calcular el valor del pago");
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "realizaPagoLineaCreditoParcial", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaTipoPagoTotal() {
        try {
            vaciarCamposCambiaTipoPagoTotal();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "cambiaTipoPagoTotal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void vaciarCamposCambiaTipoPagoTotal() {
        try {
            formaPago = null;
            pagoLineaCreditoSolicitud = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "vaciarCamposCambiaTipoPagoTotal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaFormaPagoTotal() {
        try {
            vaciarCamposCambiaFormaPagoTotal();
            calculaPagoLineaCreditoTotal();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "cambiaFormaPagoTotal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void vaciarCamposCambiaFormaPagoTotal() {
        try {
            pagoLineaCreditoSolicitud = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "vaciarCamposCambiaFormaPagoTotal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void calculaPagoLineaCreditoTotal() {
        try {
            if (validaFormaPagoTotal()) {
                pagoLineaCreditoSolicitud = pagoLineaCreditoSolicitudFacade.calculaPagoTotalLineaCreditoSolicitud(lineaCreditoSolicitud.getCodigo());
                if (pagoLineaCreditoSolicitud.getError() == null) {
                    setHabilitaBotonPagoTotal(true);
                } else {
                    MuestraMensaje.addError(pagoLineaCreditoSolicitud.getError());
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                            new Object[]{"LineaCreditoPagoController", "calculaPagoLineaCreditoTotal", pagoLineaCreditoSolicitud.getError()});
                    setHabilitaBotonPagoTotal(false);
                }
            } else {
                setHabilitaBotonPagoTotal(false);
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "calculaPagoLineaCreditoTotal", CapturaError.getErrorException(ex)});
            pagoLineaCreditoSolicitud = null;
            setHabilitaBotonPagoTotal(false);
        }
    }

    /**
     *
     * @return
     */
    public boolean validaFormaPagoTotal() {
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
                    new Object[]{"LineaCreditoPagoController", "validaFormaPagoTotal", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void realizaPagoLineaCreditoTotal() {
        try {
            if (pagoLineaCreditoSolicitud != null && validaValorDebitoCuentaTotal()) {
                PagoLineaCreditoSolicitud pago = pagoLineaCreditoSolicitudFacade.pagoTotalLineaCreditoSolicitud(lineaCreditoSolicitud.getCodigo(),
                        Long.valueOf("0"),
                        ActivacionUsuario.getCodigoIfip(),
                        ActivacionUsuario.getCodigoUsuario(),
                        Long.valueOf("1"),
                        tipoPago,
                        pagoLineaCreditoSolicitud.getTotal(),
                        ActivacionUsuario.getCodigoIfipAgencia(),
                        "PAGO TOTAL LINEA CREDITO",
                        "N");
                if (pago.getError() == null) {
                    pagoLineaCreditoSolicitud.setCodigo(pago.getCodigo());
                    pagoLineaCreditoSolicitud.setCodigoLineaCreditoSolicitud(lineaCreditoSolicitud.getCodigo());
                    esPagoParcial = false;
                    MuestraMensaje.addSatisfactorioPersistencia(1);
                    RequestContext.getCurrentInstance().execute("ImprimeComprobanteDialogo.show();");
                } else {
                    MuestraMensaje.addError(pago.getError());
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                            new Object[]{"LineaCreditoPagoController", "realizaPagoLineaCreditoTotal", pago.getError()});
                }
            } else {
                MuestraMensaje.addError("Debe calcular el valor del pago");
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "realizaPagoLineaCreditoTotal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean validaValorDebitoCuentaTotal() {
        try {
            if (lineaCreditoSolicitud.getCuentaDebito().getSaldoTotal().compareTo(pagoLineaCreditoSolicitud.getTotal()) < 0) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible"));
                return false;
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "validaValorDebitoCuentaTotal", CapturaError.getErrorException(ex)});
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
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "onRowSelect", CapturaError.getErrorException(ex)});
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
                    new Object[]{"LineaCreditoPagoController", "onRowUnselect", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void imprimirComprobante() {
        try {
            impresionComprobantes = new ImpresionComprobantes(ActivacionUsuario.getRutaImpresora(), null);
            boolean impresion = 
                    impresionComprobantes.pagoLineaCredito(socio, ifipAgencia, pagoLineaCreditoSolicitud, ActivacionUsuario.getUsuario(), tipoPago);
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
                    RequestContext.getCurrentInstance().execute("PagoLineaCreditoParcialDialog.hide();");
                }else{
                    RequestContext.getCurrentInstance().execute("PagoLineaCreditoTotalDialog.hide();");
                }
                RequestContext.getCurrentInstance().execute("ImprimeComprobanteDialogo.hide();");
            }else{
                MuestraMensaje.addError("Error al imprimir comprobante");
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoPagoController", "imprimirComprobante", CapturaError.getErrorException(ex)});
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
     * @return the pagoLineaCreditoSolicitud
     */
    public PagoLineaCreditoSolicitud getPagoLineaCreditoSolicitud() {
        return pagoLineaCreditoSolicitud;
    }

    /**
     * @param pagoLineaCreditoSolicitud the pagoLineaCreditoSolicitud to set
     */
    public void setPagoLineaCreditoSolicitud(PagoLineaCreditoSolicitud pagoLineaCreditoSolicitud) {
        this.pagoLineaCreditoSolicitud = pagoLineaCreditoSolicitud;
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
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * @return the valorParaTipoPago
     */
    public Object getValorParaTipoPago() {
        return valorParaTipoPago;
    }

    /**
     * @param valorParaTipoPago the valorParaTipoPago to set
     */
    public void setValorParaTipoPago(Object valorParaTipoPago) {
        this.valorParaTipoPago = valorParaTipoPago;
    }

    /**
     * @return the etiquetaTipoValor
     */
    public String getEtiquetaTipoValor() {
        return etiquetaTipoValor;
    }

    /**
     * @param etiquetaTipoValor the etiquetaTipoValor to set
     */
    public void setEtiquetaTipoValor(String etiquetaTipoValor) {
        this.etiquetaTipoValor = etiquetaTipoValor;
    }

    /**
     * @return the habilitaBotonPagoParcial
     */
    public boolean isHabilitaBotonPagoParcial() {
        return habilitaBotonPagoParcial;
    }

    /**
     * @param habilitaBotonPagoParcial the habilitaBotonPagoParcial to set
     */
    public void setHabilitaBotonPagoParcial(boolean habilitaBotonPagoParcial) {
        this.habilitaBotonPagoParcial = habilitaBotonPagoParcial;
    }

    /**
     * @return the habilitaBotonPagoTotal
     */
    public boolean isHabilitaBotonPagoTotal() {
        return habilitaBotonPagoTotal;
    }

    /**
     * @param habilitaBotonPagoTotal the habilitaBotonPagoTotal to set
     */
    public void setHabilitaBotonPagoTotal(boolean habilitaBotonPagoTotal) {
        this.habilitaBotonPagoTotal = habilitaBotonPagoTotal;
    }
}
