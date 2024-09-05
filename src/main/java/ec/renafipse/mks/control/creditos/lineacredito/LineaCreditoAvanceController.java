/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.creditos.lineacredito;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.impresiones.ImpresionComprobantes;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.clases.creditos.lineacredito.PagoLineaCreditoAvance;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.ParametroGeneralCreditoIfip;
import ec.renafipse.mks.modelo.creditos.lineacredito.AvanceLineaCredito;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCredito;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.creditos.ParametroGeneralCreditoIfipFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.AvanceLineaCreditoFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoSolicitudFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import oracle.jdbc.OracleTypes;
import org.primefaces.context.RequestContext;

/**
 *
 * @author andres
 */
@ManagedBean(name = "lineaCreditoAvanceController")
@ViewScoped
public class LineaCreditoAvanceController extends AbstractController<AvanceLineaCredito> implements Serializable {

    /**
     * @return the cupoConsumido
     */
    public BigDecimal getCupoConsumido() {
        return cupoConsumido;
    }

    /**
     * @param cupoConsumido the cupoConsumido to set
     */
    public void setCupoConsumido(BigDecimal cupoConsumido) {
        this.cupoConsumido = cupoConsumido;
    }
    
    private boolean permiteGuardar;
    private boolean desactivarImprime;
    private String nombreSocio;
    private LineaCreditoSolicitud lineaCreditoSolicitud;
    private List<LineaCreditoSolicitud> listaLineaCreditoSolicitud;
    private List<PagoLineaCreditoAvance> listaPagoLineaCreditoAvance;
    private BigDecimal montoSolicitado;
    private BigDecimal capitalAPagar;
    private short plazoEnDias;
    private BigDecimal interesAPagar;
    private BigDecimal rubrosAPagar;
    private Date fechaVencimiento;
    private String valoresLong;
    private Socio socio;
    private int cuotas;
    private boolean habilitaIngresoCuotas;
    private BigDecimal saldoLineaCreditoSoliictud;
    private BigDecimal montoLineaCreditoSoliictud;
    private BigDecimal montoSolicitadoAvance;
    private BigDecimal cupoConsumido;
    private boolean imprimeEnArchivo;
    private RequestContext context;
    private AvanceLineaCredito avanceLineaCredito;

    @EJB
    private LineaCreditoSolicitudFacade ejbFacadeLineaCreditoSolicitud;
    @EJB
    private SocioFacade ejbFacadeSocio;
    @EJB
    private LlamaSP llamaSP;
    @EJB
    private ParametroGeneralCreditoIfipFacade ejbFacadeParametroGeneralCreditoIfip;
    @EJB
    private AvanceLineaCreditoFacade ejbFacadeAvanceLineaCredito;
    @EJB
    private LineaCreditoFacade ejbFacadeLineaCredito;
    @EJB
    private IfipAgenciaFacade ejbFacadeAgencia;

    @PostConstruct
    public void init() {
        try {
            inicializaPropiedades();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "init", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void guardar() {
        try {
             if (socio == null){
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            if (lineaCreditoSolicitud == null){                
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneLineaCreditoSolicitud"));
                return;
            }
            if (montoSolicitado == null){                
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MontoSolicitado"));
                return;
            }else{
                if (montoSolicitado.compareTo(BigDecimal.ZERO) == 0){
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MontoSolicitado"));
                    return;
                }
            }
            if (cuotas == 0){
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroCuotasAvance"));
                return;
            }
            if (permiteGuardar = true) {
                llamaSP.cargaConexion();
                llamaSP.setCerrarConexion(false);
                llamaSP.autoCommit(false);
                llamaSP.setNombreSP("mks_creditos.pkm_linea_credito.p_crea_avance_linea_credito");
                llamaSP.setNumeroParametros(13);
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_linea_credito_sol", lineaCreditoSolicitud.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_monto", montoSolicitado});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_numero_cuotas_usuario", cuotas});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_computador ", ActivacionUsuario.getCodigoComputador()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});
                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario", Types.VARCHAR});
                llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo_formulario", Types.NUMERIC});
                llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario", Types.NUMERIC});
                llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo_movimiento", Types.NUMERIC});
                llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo", Types.NUMERIC});
                llamaSP.invocaSP();
                if (llamaSP.isEjecucionCorrecta()) {
                    llamaSP.commit();
                    MuestraMensaje.addSatisfactorioPersistencia(1);
                    permiteGuardar = false;
                    desactivarImprime = false;
                    //Obtener el avance de linea de credito
                    avanceLineaCredito = ejbFacadeAvanceLineaCredito.getAvanceByCodigo(Long.parseLong(llamaSP.getListResultado().get(4).toString()));
                } else {
                    llamaSP.rollback();
                }
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "guardar", CapturaError.getErrorException(ex)});
            if (llamaSP.getConexionBD() != null){
               llamaSP.rollback();
            }
        } finally{
           if (llamaSP.getConexionBD() != null){
               llamaSP.cerrarConexion();
               llamaSP.setConexionBD(null);
           }
        }
    }
    /**
     * 
     */
    public void calculaAvanceTotalYPagos(){
        try {
            listaPagoLineaCreditoAvance = null;
            capitalAPagar = BigDecimal.ZERO;
            plazoEnDias = 0;
            interesAPagar = BigDecimal.ZERO;
            rubrosAPagar = BigDecimal.ZERO;
            fechaVencimiento = null;
            if (socio == null){
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            if (lineaCreditoSolicitud == null){                
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneLineaCreditoSolicitud"));
                return;
            }
            if (montoSolicitado == null){                
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MontoSolicitado"));
                return;
            }else{
                if (montoSolicitado.compareTo(BigDecimal.ZERO) == 0){
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MontoSolicitado"));
                    return;
                }else{
                    //Obtener el valor minimo para realizar el avance
                    ParametroGeneralCreditoIfip parametroGeneralCreditoIifip = ejbFacadeParametroGeneralCreditoIfip.getItemCodigoParametroIfipEliminado(7L,ActivacionUsuario.getCodigoIfip() ,'N');
                    if( parametroGeneralCreditoIifip.getValorNumerico().compareTo(montoSolicitado)>0){
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValorInferiorMontoMinimoAvance")+" "+parametroGeneralCreditoIifip.getValorNumerico());
                        return;
                    }
                }
            }
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_creditos.pkm_linea_credito.p_genera_tab_pro_ava_lin_cre");
            llamaSP.setNumeroParametros(11);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_lin_cre_sol", lineaCreditoSolicitud.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_monto_lin_cre_sol", montoSolicitado});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_plazo", null});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_numero_cuotas", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_capital_a_pagar", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_plazo_en_dias", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_interes_a_pagar", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pd_fecha_vencimiento", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_rubros_a_pagar", Types.NUMERIC});        
            llamaSP.getListParametrosSalida().add(new Object[]{"pc_tabla_pagos_ava", OracleTypes.CURSOR});
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo_tmp_solicitud", Types.NUMERIC});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                cuotas = new Integer(llamaSP.getListResultado().get(0).toString());
                capitalAPagar = new BigDecimal(llamaSP.getListResultado().get(1).toString());
                plazoEnDias = Short.valueOf(llamaSP.getListResultado().get(2).toString());
                interesAPagar = new BigDecimal(llamaSP.getListResultado().get(3).toString());
                fechaVencimiento = new SimpleDateFormat("dd-MM-yyyy").parse(llamaSP.getListResultado().get(4).toString());
                rubrosAPagar = new BigDecimal(llamaSP.getListResultado().get(5).toString());
                ResultSet resultSet = (ResultSet)llamaSP.getListResultado().get(6);
                if (resultSet != null) {
                    List<PagoLineaCreditoAvance> lista = new ArrayList<PagoLineaCreditoAvance>();
                    while(resultSet.next()) { 
                        lista.add(new PagoLineaCreditoAvance(
                                                             new Integer(resultSet.getString("cuota")),
                                                             new SimpleDateFormat("dd-MM-yyyy").parse(resultSet.getString("fecha_pago")),
                                                             new BigDecimal(resultSet.getString("saldo_capital")),
                                                             new BigDecimal(resultSet.getString("capital")),
                                                             new BigDecimal(resultSet.getString("total_a_pagar")),
                                                             new BigDecimal(resultSet.getString("minimo_a_pagar"))));
                    }
                    listaPagoLineaCreditoAvance = lista;
                }else{
                    listaPagoLineaCreditoAvance = null;
                }
                if (!"S".equals(lineaCreditoSolicitud.getLineaCredito().getPlazoAutomatico())) {
                    setHabilitaIngresoCuotas(true);
                    setCuotas(0);
                } else {
                    setHabilitaIngresoCuotas(false);
                }
                permiteGuardar = true;
            }else{
                setPermiteGuardar(false);
                setCuotas(0);
                listaPagoLineaCreditoAvance = null;
                capitalAPagar = BigDecimal.ZERO;
                plazoEnDias = 0;
                interesAPagar = BigDecimal.ZERO;
                rubrosAPagar = BigDecimal.ZERO;
                fechaVencimiento = null;
            }
        } catch (Exception ex) {
            setPermiteGuardar(false);
            setCuotas(0);
            listaPagoLineaCreditoAvance = null;
            capitalAPagar = BigDecimal.ZERO;
            plazoEnDias = 0;
            interesAPagar = BigDecimal.ZERO;
            rubrosAPagar = BigDecimal.ZERO;
            fechaVencimiento = null;
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "calculaAvanceTotalYPagos", CapturaError.getErrorException(ex)});
        }
        finally{
           if (llamaSP.getConexionBD() != null){
               llamaSP.cerrarConexion();
               llamaSP.setConexionBD(null);
           }
        }
    }
    /***
     * Metodo para calcular el monto de avance final
     */
    public void calcularMontoAvance(){
        try{
            //Obtener el valor final del avance
            setMontoSolicitado(ejbFacadeLineaCreditoSolicitud.getMontoFinalAvance(lineaCreditoSolicitud.getCodigo(),montoSolicitadoAvance));
            calculaAvanceTotalYPagos();
        }catch(Exception ex){
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "calcularMontoAvance", CapturaError.getErrorException(ex)});
        }            
    }
    /**
     *
     */
    public void imprimirComprobante() {
        try {
            imprimeEnArchivo = true;
            context = RequestContext.getCurrentInstance();
            context.execute("ImprimirComprobanteDialogo.hide()");
            context.execute("procesandoDlg.show()");
            ImpresionComprobantes impresionComprobantes = new ImpresionComprobantes(ActivacionUsuario.getRutaImpresora());
            //impresionComprobantes.setMensajeAdicional(cargarMensaje(1l,codigoMovimiento));
            //Obtener la linea de credito
            LineaCredito lineaCredito = ejbFacadeLineaCredito.getLineaCreditoByCodigo(lineaCreditoSolicitud.getCodigoLineaCredito());
            IfipAgencia agencia = ejbFacadeAgencia.getAgenciaPorCodigo(ActivacionUsuario.getCodigoIfipAgencia());
            BigDecimal cupoLinea = getLineaCreditoSolicitud().getMontoLineaCredito();
            BigDecimal valorAvance = getMontoSolicitado();
            BigDecimal cupoDisponible = getLineaCreditoSolicitud().getMontoLineaCredito().subtract(ejbFacadeLineaCreditoSolicitud.getSaldoOcupadoLineaCreditoSolicitud(lineaCreditoSolicitud.getCodigo()));
            impresionComprobantes.avanceLineaCredito(avanceLineaCredito, lineaCreditoSolicitud, lineaCredito, socio, agencia, cupoLinea, valorAvance, cupoDisponible);
            // Incia Datos
            context.execute("procesandoDlg.hide()");
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "imprimirComprobante", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void imprimirTablaPagos() {
        try {
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "imprimirTablaPagos", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void obtieneSolicitudesDeLineaDesdeComponente() {
        try {
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
            listaLineaCreditoSolicitud = ejbFacadeLineaCreditoSolicitud.getListaLineaCreditoSolicitudPorCodigoSocioYCodigoEstado(socio.getSocioPK().getCodigo(), Long.valueOf("8"));
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
                    new Object[]{"LineaCreditoAvanceController", "obtieneSolicitudesDeLineaDesdeComponente", CapturaError.getErrorException(ex)});
        }
    }
    
    public void inicializaPropiedades(){
        try{
             nombreSocio = "";
             lineaCreditoSolicitud = null;
             listaLineaCreditoSolicitud = null;
             listaPagoLineaCreditoAvance = null;
             montoSolicitado = BigDecimal.ZERO;
             montoSolicitadoAvance = BigDecimal.ZERO;
             capitalAPagar = BigDecimal.ZERO;
             plazoEnDias = 0;
             interesAPagar = BigDecimal.ZERO;
             rubrosAPagar = BigDecimal.ZERO;
             fechaVencimiento = null;
             socio = null;
             desactivarImprime = true;
             permiteGuardar = false;
             habilitaIngresoCuotas = false;
        }catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "inicializaPropiedades", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaLineaCreditoSolicitud() {
        try {
            listaPagoLineaCreditoAvance = null;
            montoSolicitado = BigDecimal.ZERO;
            capitalAPagar = BigDecimal.ZERO;
            plazoEnDias = 0;
            interesAPagar = BigDecimal.ZERO;
            rubrosAPagar = BigDecimal.ZERO;
            fechaVencimiento = null;
            desactivarImprime = true;
            habilitaIngresoCuotas = false;
            saldoLineaCreditoSoliictud = getSaldoDisponibleLineaCreditoSolicitud();
            setMontoLineaCreditoSoliictud(lineaCreditoSolicitud.getMontoLineaCredito());
            setCupoConsumido(lineaCreditoSolicitud.getMontoLineaCredito().subtract(saldoLineaCreditoSoliictud));            
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "cambiaLineaCreditoSolicitud", CapturaError.getErrorException(ex)});
        }
    }
    /***
     * Metodo para obtener el saldo disponible de la linea de credito
     * @return 
     */
    private BigDecimal getSaldoDisponibleLineaCreditoSolicitud(){
        try{
            BigDecimal saldoOcupadoLineaCredito = ejbFacadeLineaCreditoSolicitud.getSaldoOcupadoLineaCreditoSolicitud(lineaCreditoSolicitud.getCodigo());
            return lineaCreditoSolicitud.getMontoLineaCredito().subtract(saldoOcupadoLineaCredito);
        }catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "getsaldoDisponibleLineaCreditoSolicitud", CapturaError.getErrorException(ex)});
            return new BigDecimal("-1");
        }
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
     * @return the listaPagoLineaCreditoAvance
     */
    public List<PagoLineaCreditoAvance> getListaPagoLineaCreditoAvance() {
        return listaPagoLineaCreditoAvance;
    }

    /**
     * @param listaPagoLineaCreditoAvance the listaPagoLineaCreditoAvance to set
     */
    public void setListaPagoLineaCreditoAvance(List<PagoLineaCreditoAvance> listaPagoLineaCreditoAvance) {
        this.listaPagoLineaCreditoAvance = listaPagoLineaCreditoAvance;
    }

    /**
     * @return the montoSolicitado
     */
    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    /**
     * @param montoSolicitado the montoSolicitado to set
     */
    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    /**
     * @return the capitalAPagar
     */
    public BigDecimal getCapitalAPagar() {
        return capitalAPagar;
    }

    /**
     * @param capitalAPagar the capitalAPagar to set
     */
    public void setCapitalAPagar(BigDecimal capitalAPagar) {
        this.capitalAPagar = capitalAPagar;
    }

    /**
     * @return the plazoEnDias
     */
    public short getPlazoEnDias() {
        return plazoEnDias;
    }

    /**
     * @param plazoEnDias the plazoEnDias to set
     */
    public void setPlazoEnDias(short plazoEnDias) {
        this.plazoEnDias = plazoEnDias;
    }

    /**
     * @return the interesAPagar
     */
    public BigDecimal getInteresAPagar() {
        return interesAPagar;
    }

    /**
     * @param interesAPagar the interesAPagar to set
     */
    public void setInteresAPagar(BigDecimal interesAPagar) {
        this.interesAPagar = interesAPagar;
    }

    /**
     * @return the rubrosAPagar
     */
    public BigDecimal getRubrosAPagar() {
        return rubrosAPagar;
    }

    /**
     * @param rubrosAPagar the rubrosAPagar to set
     */
    public void setRubrosAPagar(BigDecimal rubrosAPagar) {
        this.rubrosAPagar = rubrosAPagar;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the imprime
     */
    public boolean isDesactivarImprime() {
        return desactivarImprime;
    }

    /**
     * @param desactivarImprime the imprime to set
     */
    public void setDesactivarImprime(boolean desactivarImprime) {
        this.desactivarImprime = desactivarImprime;
    }

    /**
     * @return the permiteGuardar
     */
    public boolean isPermiteGuardar() {
        return permiteGuardar;
    }

    /**
     * @param permiteGuardar the permiteGuardar to set
     */
    public void setPermiteGuardar(boolean permiteGuardar) {
        this.permiteGuardar = permiteGuardar;
    }

    /**
     * @return the cuotas
     */
    public int getCuotas() {
        return cuotas;
    }

    /**
     * @param cuotas the cuotas to set
     */
    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    /**
     * @return the habilitaIngresoCuotas
     */
    public boolean isHabilitaIngresoCuotas() {
        return habilitaIngresoCuotas;
    }

    /**
     * @param habilitaIngresoCuotas the habilitaIngresoCuotas to set
     */
    public void setHabilitaIngresoCuotas(boolean habilitaIngresoCuotas) {
        this.habilitaIngresoCuotas = habilitaIngresoCuotas;
    }

    /**
     * @return the saldoLineaCreditoSoliictud
     */
    public BigDecimal getSaldoLineaCreditoSoliictud() {
        return saldoLineaCreditoSoliictud;
    }

    /**
     * @param saldoLineaCreditoSoliictud the saldoLineaCreditoSoliictud to set
     */
    public void setSaldoLineaCreditoSoliictud(BigDecimal saldoLineaCreditoSoliictud) {
        this.saldoLineaCreditoSoliictud = saldoLineaCreditoSoliictud;
    }

    /**
     * @return the montoLineaCreditoSoliictud
     */
    public BigDecimal getMontoLineaCreditoSoliictud() {
        return montoLineaCreditoSoliictud;
    }

    /**
     * @param montoLineaCreditoSoliictud the montoLineaCreditoSoliictud to set
     */
    public void setMontoLineaCreditoSoliictud(BigDecimal montoLineaCreditoSoliictud) {
        this.montoLineaCreditoSoliictud = montoLineaCreditoSoliictud;
    }
    
    /**
     * @return the montoSolicitadoAvance
     */
    public BigDecimal getMontoSolicitadoAvance() {
        return montoSolicitadoAvance;
    }

    /**
     * @param montoSolicitadoAvance the montoSolicitadoAvance to set
     */
    public void setMontoSolicitadoAvance(BigDecimal montoSolicitadoAvance) {
        this.montoSolicitadoAvance = montoSolicitadoAvance;
    }
}
