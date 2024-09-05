package ec.renafipse.mks.control.creditos.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.control.ahorros.bean.LicitudFondosBean;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.LicitudFonCptoTran;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosFormulario;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosModulo;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.creditos.EstadoCredito;
import ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfi;
import ec.renafipse.mks.modelo.creditos.RubroTablaAmortizacion;
import ec.renafipse.mks.modelo.creditos.RubroTablaAmortizacionPK;
import ec.renafipse.mks.modelo.creditos.SeguimientoEstadoSolicitud;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.modelo.creditos.SolicitudDetallePK;
import ec.renafipse.mks.modelo.creditos.SolicitudPagare;
import ec.renafipse.mks.modelo.creditos.SolicitudPagarePK;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacion;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacionPK;
import ec.renafipse.mks.modelo.creditos.TasaInteresProCreMonIfi;
import ec.renafipse.mks.modelo.creditos.TipoGarantiaCredito;
import ec.renafipse.mks.modelo.creditos.TipoGarantiaProCreMonIfi;
import ec.renafipse.mks.modelo.creditos.TipoRubroCredito;
import ec.renafipse.mks.modelo.creditos.TipoRubroProCreMonIfi;
import ec.renafipse.mks.modelo.ifips.ParametroServidorIfip;
import ec.renafipse.mks.modelo.ifips.ParametroServidorIfipPK;
import ec.renafipse.mks.modelo.seguridades.UsuarioEstadoCredito;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFonCptoTranFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosFormularioFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosModuloFacade;
import ec.renafipse.mks.negocio.creditos.EstadoCreditoFacade;
import ec.renafipse.mks.negocio.creditos.FabricaUsuarioPerfilFacade;
import ec.renafipse.mks.negocio.creditos.PeriodicidadProMonIfiFacade;
import ec.renafipse.mks.negocio.creditos.RubroConceptoTransaccionFacade;
import ec.renafipse.mks.negocio.creditos.SeguimientoEstadoSolicitudFacade;
import ec.renafipse.mks.negocio.creditos.SolcitudPagareFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudDetalleFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudFacade;
import ec.renafipse.mks.negocio.creditos.TablaAmortizacionFacade;
import ec.renafipse.mks.negocio.creditos.TasaInteresProCreMonIfiFacade;
import ec.renafipse.mks.negocio.creditos.TipoGarantiaCreditoFacade;
import ec.renafipse.mks.negocio.creditos.TipoGarantiaProCreMonIfiFacade;
import ec.renafipse.mks.negocio.creditos.TipoRubroCreditoFacade;
import ec.renafipse.mks.negocio.creditos.TipoRubroProCreMonIfiFacade;
import ec.renafipse.mks.negocio.ifips.ParametroServidorIfipFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

@ManagedBean(name = "concedeBean")
@ViewScoped
public class ConcederBean extends AbstractController<Solicitud> implements Serializable {

    @EJB
    private SolicitudFacade ejbFacade;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private EstadoCreditoFacade ejbFacadeEstadoCredito;

    @EJB
    private UsuarioFacade ejbFacadeUsuarioEstadoCredito;

    @EJB
    private SeguimientoEstadoSolicitudFacade ejbFacadeSeguimientoEstadoSolicitud;

    @EJB
    private SolicitudDetalleFacade ejbFacadeSolicitudDetalle;

    @EJB
    private TablaAmortizacionFacade ejbFacadeTablaAmortizacion;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private SolcitudPagareFacade ejbFacadeSolicitudPagare;

    @EJB
    private TasaInteresProCreMonIfiFacade ejbFacadeTasaInteresProCreMonIfi;

    @EJB
    private TipoRubroCreditoFacade ejbFacadeTipoRubroCredito;

    @EJB
    private PeriodicidadProMonIfiFacade ejbFacadePeriodicidadProMonIfi;
    @EJB

    private TipoRubroProCreMonIfiFacade ejbFacadeTipoRubroProCreMonIfi;

    @EJB
    private RubroConceptoTransaccionFacade ejbFacadeRubroConceptoTransaccion;

    @EJB
    private ParametroServidorIfipFacade ejbFacadeParametroServidorIfip;

    @EJB
    private TipoGarantiaProCreMonIfiFacade ejbFacadeTipoGarantiaProCreMonIfi;

    @EJB
    private TipoGarantiaCreditoFacade ejbFacadeTipoGarantiaCredito;
    
    @EJB
    private LicitudFondosModuloFacade ejbFacadeLicitudFondosModulo;
    
    @EJB
    private LicitudFonCptoTranFacade ejbFacadeLicitudFonCptoTran;
    
    @EJB
    private LicitudFondosFormularioFacade ejbFacadeLicitudFondosFormulario;
    
    @EJB
    private FabricaUsuarioPerfilFacade ejbFacadeFabricaUsuarioPerfil;
    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    
    @ManagedProperty("#{licitudFondosBean}")
    private LicitudFondosBean licitudFondosBean;

    private LlamaSP llamaSP;

    private String msg;
    private String es_pagare;

    private Timestamp fecha;

    private boolean deshabilitaEnvioBot;

    private Long numeroCredito;
    private Long numeroPagare;
    private long generaFormulario;
    private long codigoFormulario;

    private Socio socioSel;
    private LicitudFondosModulo licitudFondosModulo;
    private EstadoCredito estadoCreditoBusqueda;
    private UsuarioEstadoCredito usuarioEstadoCredito;
    private Solicitud solicitud;
    private Solicitud solicitudSel;
    private SolicitudDetalle solicitudDetalle;
    private GeneraReporte generaReporte;
    private BigDecimal totalCapital;
    private BigDecimal totalInteres;
    private BigDecimal totalRubros;
    private BigDecimal totalCredito;
    private String totalCapitalCadena;
    private String totalInteresCadena;
    private String totalRubrosCadena;
    private String totalCreditoCadena;
    private BigDecimal montoActualCredito;
    private BigDecimal tasaActualCredito;
    private BigDecimal totalRubrosCredito;
    private BigDecimal totalRubrosCreditoConcecion;
    private SeguimientoEstadoSolicitud seguimientoEstadoSolicitud;

    private char tipoCuentaSel;
    private Cuenta cuentaCombo;
    private Long diasPago;
    private SolicitudPagare solicitudPagare;
    private RequestContext context;
    private String simboloMoneda;
    private boolean tieneRubroSegContigo;
    private Long codigoSeguroContigo;

    private List<Socio> itemsSocio;
    private List<Solicitud> itemsSolicitud;
    private List<EstadoCredito> itemsEstadoCreditoLista;
    private List<Solicitud> itemsSolicitudSel;
    private List<UsuarioEstadoCredito> listaUsuarioEstadoCredito;
    private List<SolicitudDetalle> itemsSolicitudDetalle;
    private List<Cuenta> itemsCuentas;
    private List<TablaAmortizacion> itemsTablaAmortizacion;
    private List<TipoRubroCredito> itemsTipoRubroCredito;
    private List<RubroTablaAmortizacion> itemsRubroTablaAmortizacion;
    private List<TipoRubroProCreMonIfi> listaRubros;
    
    private String mensajeAporteCertificados;

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);

        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        
        try {

            if (ActivacionUsuario.getCodigoComputador() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
            
            // Colocando el modulo de liciytud de fondos
            licitudFondosModulo  = this.ejbFacadeLicitudFondosModulo.find(2L);
            
            // Verificando que exista el modulo de licitud de fondos
            if (licitudFondosModulo == null)
            {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ModuloLicitudFondosNoExiste"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
            //Verificar el acceso a la opcion por perfil en Fabrica de Credito
            if(ejbFacadeFabricaUsuarioPerfil.validaAccesoProcesoCredito(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), Long.parseLong("6"))){            
                this.seguimientoEstadoSolicitud = new SeguimientoEstadoSolicitud();
                this.deshabilitaEnvioBot = true;
                this.es_pagare = null;
                this.obtieneSolicitudes();
                this.solicitud = null;
            } else {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                Sesion.redireccionaPagina(url);
            }
        } catch (IOException e) {

            //ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"concedeBean", "init", CapturaError.getErrorException(ex)});
            }
        }
    }

    /**
     * Flujo del WIZARD
     *
     * @param event
     * @return
     */
    public String sigueProcesoSocio(FlowEvent event) {

        if (event.getOldStep().equals("negociacionTab") && event.getNewStep().equals("confirmacionTab")) {
            if (!this.validaMonto()) {
                this.validaNumeroCuotas();
                if (this.msg != null) {
                    return "negociacionTab";
                }

            }
        }

        return event.getNewStep();
    }

    /**
     * Obtiene las solicitudes Aprobadas.
     */
    public void obtieneSolicitudes() {
        this.itemsSolicitud = this.ejbFacade.getItemsSolicitudCodigoIfipFabrica(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), Long.parseLong("4"), ActivacionUsuario.getCodigoUsuario());
    }

    /**
     * Prepara la concecion del credito
     *
     * @param actionEvent
     */
    public void preparaConcederCredito(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();

        if (this.solicitud.getCodigoEstado() == 6) {
            context.execute("procesandoDlg.hide()");
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoConcedito");
            MuestraMensaje.addError(msg);
        }
        // Obteniendo el Pagare del Credito
        this.solicitudPagare = this.ejbFacadeSolicitudPagare.find(new SolicitudPagarePK(this.solicitud.getSolicitudPK().getNumero(), this.solicitud.getSolicitudPK().getCodigoIfip()));

        ///Valida garatntias obligatorias
        List<TipoGarantiaProCreMonIfi> garantiasObligatoreias = ejbFacadeTipoGarantiaProCreMonIfi.getTipoGarantiaProCreMonIfiTasaObliElim(solicitud.getCodigoTasa().getCodigo(), 'S', 'N');
        List<TipoGarantiaCredito> garantiasCredito = ejbFacadeTipoGarantiaCredito.getTipoGarantiaCreditoElim(solicitud.getSolicitudPK().getNumero(), solicitud.getSolicitudPK().getCodigoIfip(), 'N');
        boolean garantiasOK, continuar = true;
        for (TipoGarantiaProCreMonIfi tg : garantiasObligatoreias) {
            garantiasOK = false;
            for (TipoGarantiaCredito gc : garantiasCredito) {
                if (tg.getCodigoTipoGarantia().getCodigo().longValue() == gc.getTipoGarantiaCreditoPK().getCodigoTipoGarantia()) {
                    garantiasOK = true;
                }
            }
            if (!garantiasOK) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoSinGarantiaObligatoria") + tg.getCodigoTipoGarantia().getNombre();
                MuestraMensaje.addError(msg);
                continuar = false;
            }
        }
        if (!continuar) {
            context.execute("procesandoDlg.hide()");
            return;
        }
        if (this.solicitudPagare == null) {
            context.execute("procesandoDlg.hide()");
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("EmitaPagare");
            MuestraMensaje.addError(msg);
        } else {
            context.execute("procesandoDlg.hide()");
            this.montoActualCredito = this.solicitud.getMontoCredito();
            tasaActualCredito = this.solicitud.getTasa();
            this.cuentaCombo = null;
            this.setDiasPago(null);

            // Generando la Tabla
            this.itemsTipoRubroCredito = this.ejbFacadeTipoRubroCredito.getItemsNumeroCreditoCodigoIfip(this.solicitud.getSolicitudPK().getNumero(), this.solicitud.getSolicitudPK().getCodigoIfip());
            this.generarTablaAmort();

            this.itemsSolicitudDetalle = new ArrayList<SolicitudDetalle>();
            this.solicitudDetalle = this.ejbFacadeSolicitudDetalle.find(new SolicitudDetallePK(this.solicitud.getSolicitudPK().getNumero(), this.solicitud.getSolicitudPK().getCodigoIfip()));
            this.itemsSolicitudDetalle.add(solicitudDetalle);
            this.itemsCuentas = this.ejbFacadeCuenta.getItemsCuentasSocioIfipTipoProductoEstado(solicitud.getCodigoSocio(), this.solicitud.getSolicitudPK().getCodigoIfip(), solicitud.getCodigoMoneda(), Long.parseLong("2"), Long.parseLong("1"));
            this.setSimboloMoneda(solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getSimbolo());
            this.numeroPagare = solicitudPagare.getNumeroPagare();
            this.setMensajeAporteCertificados(this.ejbFacade.validaValorCertificado(solicitud.getSolicitudPK().getNumero(), solicitud.getSolicitudPK().getCodigoIfip()));
            context.execute("ConcecionCreditoDialog.show()");
        }

    }

    /**
     * Genera la Tabla de Amortizacion
     */
    public void generarTablaAmort() {

        context = RequestContext.getCurrentInstance();
        //context.execute("procesandoDlg.show()");

        try {
            if (getDiasPago() == null) {
                setDiasPago((Long) Long.parseLong("0"));
            }

            if (diasPago.compareTo(Long.parseLong("30")) > 0) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DiasPagoMayor");
                MuestraMensaje.addAdvertencia(msg);
                this.diasPago = (long) 30;
                context.execute("procesandoDlg.hide()");
                return;
            }
            if (diasPago.compareTo(Long.parseLong("-30")) < 0) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DiasPagoMenor");
                MuestraMensaje.addAdvertencia(msg);
                this.diasPago = (long) -30;
                context.execute("procesandoDlg.hide()");
                return;
            }

            this.cargarRubros();
            DecimalFormat formatoValores = new DecimalFormat("########0.00");;

            setTotalRubrosCredito(BigDecimal.ZERO);
            setTotalRubrosCreditoConcecion(BigDecimal.ZERO);

            // Calcula los Rubros que se deben cobrar al conceder el credito
            // mediante una nota de debito
            calculaRubrosConcecion();

            itemsTablaAmortizacion = new ArrayList<TablaAmortizacion>();
            this.itemsRubroTablaAmortizacion = new ArrayList<RubroTablaAmortizacion>();

            double monto = Double.parseDouble(formatoValores.format(solicitud.getMontoCredito().doubleValue()));
            double saldoCapital = monto;
            double tasa = solicitud.getTasa().doubleValue();
            double interes = 0;
            double pago = 0;
            double capital = 0;
            double interesPorcentaje = 0;
            double totCapital = 0;
            double totInteres = 0;
            double totRubros = 0;
            double totCredito = 0;
            double rubros;
            Date fechaInicio = new Date();/////////---------------------------
            Date fechaIni = new Date();
            int numeroCuota = 0;
            int numCuotas = solicitud.getNumeroCuotas();
            boolean porMeses;
            int diasInteres;
            this.totalCapital = new BigDecimal("0.00");
            this.totalInteres = new BigDecimal("0.00");
            this.totalRubros = new BigDecimal("0.00");
            this.totalCredito = new BigDecimal("0.00");

            formatoValores = new DecimalFormat("###,###,##0.00");
            setTotalCapitalCadena(formatoValores.format(totalCapital.doubleValue()));
            setTotalInteresCadena(formatoValores.format(totalInteres.doubleValue()));
            setTotalRubrosCadena(formatoValores.format(totalRubros.doubleValue()));
            setTotalCreditoCadena(formatoValores.format(totalCredito.doubleValue()));

            formatoValores = new DecimalFormat("########0.00");
            Periodicidad period = solicitud.getPeriodicidadProMonIfi().getPeriodicidad();

            TablaAmortizacion item;

            ////System.out.println("Genera Tabla");
            if (monto > 0 && numCuotas > 0) {

                //this.cargarRubros();
                if (period.getEquivalenciaMeses() == 0) {
                    //interesPorcentaje = period.getEquivalenciaDias() * tasa / 36000.0;//en dias
                    porMeses = false;
                } else {
                    //interesPorcentaje = period.getEquivalenciaMeses() * tasa / 1200.0;//en meses
                    porMeses = true;
                }

                ////System.out.println("period "+period);
                // Obteniendo tasa 
                interesPorcentaje = tasa / 100 / 360;//en dias 
                Date fechaInicialTabla = this.agregaDias(new Date(), this.getDiasPago().intValue());

                if (solicitud.getTipoTabla() == 'C') {
                    capital = Double.parseDouble(formatoValores.format(monto / numCuotas));
                    ///Calculo de cuotas
                    for (int i = 0; i < numCuotas; i++) {
                        if (i == numCuotas - 1) {
                            capital = saldoCapital;
                        }

                        item = new TablaAmortizacion();
                        numeroCuota++;

                        // ta = this.ejbFacadeTablaAmortizacion.find(new TablaAmortizacionPK(this.solicitud.getSolicitudPK().getNumero(), this.solicitud.getSolicitudPK().getCodigoIfip(), Long.parseLong(String.valueOf(numeroCuota))));
                        item.setTablaAmortizacionPK(new TablaAmortizacionPK());
                        item.getTablaAmortizacionPK().setCuota(numeroCuota);

                        if (porMeses) {

                            if (i == 0) {
                                item.setFechaInicio(fechaInicio);
                                fechaInicio = this.agregaDias(fechaInicio, this.getDiasPago().intValue());
                                item.setFechaPago((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicialTabla, period.getEquivalenciaMeses() * (i + 1)) : agregaDias(fechaInicio, period.getEquivalenciaDias()));
                            } else {
                                item.setFechaInicio(fechaInicio);
                                item.setFechaPago((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicialTabla, period.getEquivalenciaMeses() * (i + 1)) : agregaDias(fechaInicio, period.getEquivalenciaDias()));

                            }

                            diasInteres = this.diferenciaEnDias2(item.getFechaInicio(), item.getFechaPago());// (this.getSolicitud().getDiaFijo() == 'S') ? this.diferenciaEnDias2(item.getFechaInicio(), item.getFechaPago()) : Integer.parseInt(String.valueOf(period.getEquivalenciaDias() + ((numeroCuota == 1) ? getDiasPago() : 0)));
                        } else {
                            item.setFechaInicio(fechaInicio);
                            item.setFechaPago(agregaDias(fechaInicio, period.getEquivalenciaDias() + ((numeroCuota == 1) ? getDiasPago() : 0)));
                            diasInteres = (int) (Integer.parseInt(String.valueOf(period.getEquivalenciaDias())) + ((numeroCuota == 1) ? getDiasPago() : 0));
                            //item.setFechaPago(agregaDias(item.getFechaPago(), (numeroCuota == 1) ?  getDiasPago()  : 0));
                        }

                        // Colocando el Sando Capital
                        item.setSaldoCapital(formatoValor(new BigDecimal(saldoCapital)));

                        // Colocando el Capital
                        item.setCapital(formatoValor(new BigDecimal(capital)));

                        // Calculando el Interes
                        //////System.out.println("diasInteres "+diasInteres);
                        interes = Double.parseDouble(formatoValores.format(saldoCapital * interesPorcentaje * diasInteres));//(diasInteres + ((numeroCuota == 1) ? getDiasPago() : 0));
                        item.setInteres(new BigDecimal(interes));

                        //Colocando los Rubros ya generados en al tabla   
                        //TablaAmortizacion ta = this.ejbFacadeTablaAmortizacion.find(new TablaAmortizacionPK(this.solicitud.getSolicitudPK().getNumero(), this.solicitud.getSolicitudPK().getCodigoIfip(), Long.parseLong(String.valueOf(numeroCuota))));
                        rubros = calculaRubrosCuota(monto, saldoCapital, numeroCuota, (item.getFechaPago().getTime() - item.getFechaInicio().getTime()) / (24 * 60 * 60 * 1000));
                        item.setRubros(new BigDecimal(rubros));

                        // Calculando el Total
                        pago = capital + interes;
                        item.setTotal(new BigDecimal(pago).add(item.getRubros()));
                    //Registra la cuota maxima a pagar

                        // Agrendo el Item en la Lista
                        this.itemsTablaAmortizacion.add(item);

                        // Colocando Totales
                        totCapital += item.getCapital().doubleValue();
                        totInteres += item.getInteres().doubleValue();
                        totRubros += item.getRubros().doubleValue();
                        totCredito += item.getTotal().doubleValue();

                        // Asignando el nuevo capital
                        saldoCapital -= capital;

                        // Asignando la proxima fecha de inicio
                        fechaInicio = item.getFechaPago();
                    }
                } else if (solicitud.getTipoTabla() == 'P') {
                    ////System.out.println("Genera Tabla Pago Fijo");
                    pago = Double.parseDouble(formatoValores.format(this.getPagoCuotaFija()));
                    ////System.out.println("pago " + pago);
                    for (int i = 0; i < numCuotas; i++) {

                        item = new TablaAmortizacion();

                        numeroCuota++;

                        item.setTablaAmortizacionPK(new TablaAmortizacionPK());
                        item.getTablaAmortizacionPK().setCuota(numeroCuota);

                        if (porMeses) {

                            if (i == 0) {
                                item.setFechaInicio(fechaInicio);
                                fechaInicio = this.agregaDias(fechaInicio, this.getDiasPago().intValue());
                                item.setFechaPago((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicialTabla, period.getEquivalenciaMeses() * (i + 1)) : agregaDias(fechaInicio, period.getEquivalenciaDias()));
                            } else {
                                item.setFechaInicio(fechaInicio);
                                item.setFechaPago((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicialTabla, period.getEquivalenciaMeses() * (i + 1)) : agregaDias(fechaInicio, period.getEquivalenciaDias()));
                            }
                            diasInteres = this.diferenciaEnDias2(item.getFechaInicio(), item.getFechaPago());
                        } else {
                            item.setFechaInicio(fechaInicio);
                            item.setFechaPago(agregaDias(fechaInicio, period.getEquivalenciaDias() + ((numeroCuota == 1) ? getDiasPago() : 0)));
                            diasInteres = (int) (Integer.parseInt(String.valueOf(period.getEquivalenciaDias())) + ((numeroCuota == 1) ? getDiasPago() : 0));
                        }

                        // Colocando el Sando Capital
                        item.setSaldoCapital(new BigDecimal(saldoCapital));

                        // Calculando el Interes
                        interes = Double.parseDouble(formatoValores.format(saldoCapital * interesPorcentaje * diasInteres));//(diasInteres + ((numeroCuota == 1) ? getDiasPago() : 0));
                        item.setInteres(new BigDecimal(interes));

                        // Obteniendo el Capital
                        if (numCuotas == numeroCuota) {
                            capital = saldoCapital;
                        } else {
                            capital = pago - Double.parseDouble(formatoValores.format(interes));
                        }

                        item.setCapital(new BigDecimal((numCuotas == numeroCuota) ? saldoCapital : capital));

                        //Colocando los Rubros ya generados en al tabla                    
                        // TablaAmortizacion ta = this.ejbFacadeTablaAmortizacion.find(new TablaAmortizacionPK(this.solicitud.getSolicitudPK().getNumero(), this.solicitud.getSolicitudPK().getCodigoIfip(), Long.parseLong(String.valueOf(numeroCuota))));
                        rubros = calculaRubrosCuota(monto, saldoCapital, numeroCuota, (item.getFechaPago().getTime() - item.getFechaInicio().getTime()) / (24 * 60 * 60 * 1000));
                        item.setRubros(new BigDecimal(rubros));

                        //Sumando el total                    
                        item.setTotal(item.getCapital().add(item.getInteres()).add(item.getRubros()));

                        // Agregando a la Lista
                        this.itemsTablaAmortizacion.add(item);

                        // Colocando Totales
                        totCapital += item.getCapital().doubleValue();
                        totInteres += item.getInteres().doubleValue();
                        totRubros += item.getRubros().doubleValue();
                        totCredito += item.getTotal().doubleValue();

                        //Obteniendo el nuevo saldo capital
                        saldoCapital -= capital;

                        // Colocando la Fecha proxima de inicio de pago
                        fechaInicio = item.getFechaPago();
                    }
                }

                totalCapital = new BigDecimal(formatoValores.format(totCapital));
                totalInteres = new BigDecimal(formatoValores.format(totInteres));
                totalRubros = new BigDecimal(formatoValores.format(totRubros));
                totalCredito = totalCapital.add(totalInteres).add(totalRubros);

                // Formateando valores para mostrar en la ventana los totales
                formatoValores = new DecimalFormat("###,###,##0.00");
                setTotalCapitalCadena(formatoValores.format(totalCapital.doubleValue()));
                setTotalInteresCadena(formatoValores.format(totalInteres.doubleValue()));
                setTotalRubrosCadena(formatoValores.format(totalRubros.doubleValue()));
                setTotalCreditoCadena(formatoValores.format(totalCredito.doubleValue()));

                context.execute("procesandoDlg.hide()");

            }
        } catch (Exception ex) {
            context.execute("procesandoDlg.show()");
            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"concedeBean", "generarTablaAmort", CapturaError.getErrorException(ex)});
        }
    }

    private void cargarRubros() {
        this.setListaRubros(this.ejbFacadeTipoRubroProCreMonIfi.getItemsProCreMonIfi(
                solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(),
                solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(),
                ActivacionUsuario.getCodigoIfip(),
                'N'));

        /**
         * Colocando los tipos de rubros del credito
         */
        this.itemsTipoRubroCredito = new ArrayList<TipoRubroCredito>();
        TipoRubroCredito tipoRubroCredito;
        for (TipoRubroProCreMonIfi ifi : getListaRubros()) {
            tipoRubroCredito = new TipoRubroCredito();
            tipoRubroCredito.setCalculadoSobre(ifi.getCalculaSobre());
            tipoRubroCredito.setCobradoEn(ifi.getCobradoEn());
            tipoRubroCredito.setCodigoMovimiento(null);
            tipoRubroCredito.setSolicitud(solicitud);
            tipoRubroCredito.setTipo(ifi.getTipo());
            tipoRubroCredito.setTipoRubro(ifi.getCodigoTipoRubro());
            tipoRubroCredito.setTotalRubroGrabado(new BigDecimal("0.00"));
            tipoRubroCredito.setValor(ifi.getValor());
            itemsTipoRubroCredito.add(tipoRubroCredito);
        }

    }

    /**
     * Calcula los Rubros por cada Cuota
     *
     * @param monto MOnto del Credito
     * @param saldo Saldo del Capital
     * @param cuota NUmero de Cuota
     * @return Valor de rubro cargado a la cuota
     */
    private double calculaRubrosCuota(double monto, double saldo, long cuota, long dias) {
        double totalRubro = 0, itemRubro;
        RubroTablaAmortizacion rta;

        //DecimalFormat formatoValores = new DecimalFormat("########0.00");
        ////System.out.println("listaRubros " + this.listaRubros);
        for (TipoRubroCredito trc : this.getItemsTipoRubroCredito()) {
            rta = new RubroTablaAmortizacion();
            itemRubro = 0;
            if (trc.getCobradoEn() == 'D') {
                if (trc.getTipo() == 'V') {
                    //////Seguro en periodicidades mensuales y quincenales
                    if (solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo().longValue() == 4 || solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo().longValue() == 5) {
                        if (trc.getCobradoEn() == 'D') {
                            itemRubro = trc.getValor().doubleValue() / 30 * solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getEquivalenciaDias();
                        } else {
                            itemRubro = trc.getValor().doubleValue();
                        }
                    }
                } else if (trc.getTipo() == 'P') {
                    double porcentaje = trc.getValor().doubleValue() / 100;
                    if (trc.getCalculadoSobre() == 'M') {
                        itemRubro = monto * porcentaje / 360 * dias;
                    } else {
                        itemRubro = saldo * porcentaje / 360 * dias;
                    }
                }
                itemRubro = this.formatoValor(new BigDecimal(itemRubro)).doubleValue();
                totalRubro = totalRubro + itemRubro;

                //-- Colocando el detalle de la tabla de amortizacion
                rta.setRubroTablaAmortizacionPK(new RubroTablaAmortizacionPK(solicitud.getSolicitudPK().getNumero(), solicitud.getSolicitudPK().getCodigoIfip(), cuota, trc.getTipoRubro().getCodio()));
                for (TipoRubroProCreMonIfi trpcmi : listaRubros) {
                    if (trpcmi.getCodigoTipoRubro() == trc.getTipoRubro()) {
                        rta.setTipoRubroProCreMonIfi(trpcmi);
                    }
                    if (rta.getTipoRubroProCreMonIfi() != null) {
                        break;
                    }
                }
                rta.setValor(new BigDecimal(itemRubro));
                rta.setAbono(BigDecimal.ZERO);
                rta.setSaldo(rta.getValor());
                this.getItemsRubroTablaAmortizacion().add(rta);

                //--- Colocando en totales del tipo del rubro del credito
                trc.setTotalRubroGrabado(trc.getTotalRubroGrabado().add(new BigDecimal(itemRubro)));

            }

        }

        this.setTotalRubrosCredito(getTotalRubrosCredito().add(new BigDecimal(totalRubro)));
        setTotalRubrosCredito(this.formatoValor(getTotalRubrosCredito()));
        return (totalRubro);
    }

    /**
     * Caluclo los trubos que tenga al credito al conceder al credito
     */
    private void calculaRubrosConcecion() {
        double rubro = 0;

        for (TipoRubroCredito trc : this.itemsTipoRubroCredito) {
            //System.out.println("Detalle " + trc.getCobradoEn() + trc.getTipoRubro().getNombre());
            //System.out.println("Total rubro Inicio: " + trc.getTotalRubroGrabado());
            if (trc.getCobradoEn() != 'D') {
                if (trc.getTipo() == 'V') {
                    //////Seguro en periodicidades mensuales y quincenales
                    if (solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo().longValue() == 4 || solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo().longValue() == 5) {
                        if (trc.getCobradoEn() == 'D') {
                            rubro = trc.getValor().doubleValue() / 30 * solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getEquivalenciaDias();
                        } else {
                            rubro = trc.getValor().doubleValue();
                        }
                    }
                } else if (trc.getTipo() == 'P') {
                    double porcentaje = trc.getValor().doubleValue() / 100;
                    // Si es mnor a 12 meses se anuliza el rubro
                    if (this.solicitud.getNumeroCuotas() * solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getEquivalenciaMeses() < 12L) {

                        //rubro = solicitud.getMontoCredito().doubleValue() * porcentaje / 360;
                        //rubro = ((solicitud.getMontoCredito().doubleValue() * porcentaje ) / 12) * this.solicitud.getNumeroCuotas();
                        rubro = solicitud.getMontoCredito().doubleValue() * porcentaje / 360 * this.solicitud.getNumeroCuotas() * solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getEquivalenciaDias();

                    } else {
                        rubro = solicitud.getMontoCredito().doubleValue() * porcentaje;
                    }
                }

                //--- Colocando en totales del tipo del rubro del credito
                trc.setTotalRubroGrabado(trc.getTotalRubroGrabado().add(new BigDecimal(rubro)));

                this.setTotalRubrosCredito(getTotalRubrosCredito().add(new BigDecimal(rubro)));
                setTotalRubrosCreditoConcecion(getTotalRubrosCreditoConcecion().add(new BigDecimal(rubro)));
            }
        }

        setTotalRubrosCredito(this.formatoValor(getTotalRubrosCredito()));
        setTotalRubrosCreditoConcecion(this.formatoValor(getTotalRubrosCreditoConcecion()));
    }

    /**
     * Valida los numero de cuotas
     */
    public void validaNumeroCuotas() {
        this.msg = null;

        Long cuotasMaximas = 0L;
        if (solicitud.getPeriodicidadProMonIfi().getPeriodicidad() != null) {
            PeriodicidadProMonIfi per = this.ejbFacadePeriodicidadProMonIfi.getPeriodicidadProMonIfiCuoMax(solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N', solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo());
            cuotasMaximas = per.getCuotasMaximas();
        }

        try {
            if (!(this.solicitud.getNumeroCuotas() >= 1 && this.solicitud.getNumeroCuotas() <= cuotasMaximas)) {
                this.solicitud.setNumeroCuotas(0);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroCuotasInvalido") + cuotasMaximas;
                MuestraMensaje.addAdvertencia(msg);
            }
        } catch (NumberFormatException e) {
            this.solicitud.setNumeroCuotas(0);
        }

        this.generarTablaAmort();

    }

    /**
     * Formato Valor
     *
     * @param valor
     * @return
     */
    public BigDecimal formatoValor(BigDecimal valor) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", simbolos);
        try {
            return new BigDecimal(df.format(valor.doubleValue()));
        } catch (NumberFormatException e) {
            simbolos.setDecimalSeparator(',');
            df = new DecimalFormat("#.00", simbolos);
            return new BigDecimal(df.format(valor.doubleValue()));
        }

    }

    /**
     * Agrega Meses a una fecha
     *
     * @param fecha Fecha
     * @param meses Numero de Meses a Agregar
     * @return Fecha agregada meses
     */
    private Date agregaMes(Date fecha, long meses) {
        ////System.out.println("Meses "+meses);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MONTH, (int) meses);
        return calendar.getTime();
    }

    /**
     * Agrega días a una fecha
     *
     * @param fecha FEcha
     * @param dias Numero de dias a agregar
     * @return FEcha agregada días
     */
    private Date agregaDias(Date fecha, long dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, (int) dias);
        return calendar.getTime();
    }

    /**
     * Obtiene los dias de entre dos fechas
     *
     * @param fechaMayor Fecha Mayor
     * @param fechaMenor Fecha Menor (a restar)
     * @return Numero de Dias entre las dos fechas
     */
    public int diferenciaEnDias2(Date fechaMayor, Date fechaMenor) {
        Long dias  = (fechaMayor.getTime()/ (1000 * 60 * 60 * 24) - fechaMenor.getTime()/ (1000 * 60 * 60 * 24));
        return (dias.intValue() < 0) ? -1 * dias.intValue() : dias.intValue();
    }

    /**
     * Agrega la cuenta la detalle.
     */
    public void agregaCuentaDetalle() {
        if (this.getCuentaCombo() != null) {
            if (itemsSolicitudDetalle.isEmpty()) {
                itemsSolicitudDetalle.add(new SolicitudDetalle());
            }
            if (getTipoCuentaSel() == 'D') {
                itemsSolicitudDetalle.get(0).setCuentaDebito(getCuentaCombo());
            }
            if (getTipoCuentaSel() == 'C') {
                itemsSolicitudDetalle.get(0).setCuentaAcreditada(getCuentaCombo());
            }
        }
    }

    public void verificaSeleccionCuenta() {
        if (getCuentaCombo() != null) {
            if (this.getCuentaCombo().getCodigoEstado().getCodigo() != 1) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaNoActiva"));
                setCuentaCombo(null);
            }
        }
    }

    public void cambiaMonto() {
        if (this.validaMonto()) {
            this.generarTablaAmort();
        }
    }

    public boolean validaMonto() {
        boolean valido = false;
        if (this.solicitud.getMontoCredito().compareTo(montoActualCredito) > 0) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MontoNoPuedeSerMayorSolicitado"));
            this.solicitud.setMontoCredito(this.montoActualCredito);
            return valido;
        }

        List<TasaInteresProCreMonIfi> listaTasaCredito;
        listaTasaCredito = this.ejbFacadeTasaInteresProCreMonIfi.getListaTasaInteresProCreMonIfi(solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N');

        if (listaTasaCredito != null) {
            for (int i = 0; i < listaTasaCredito.size(); i++) {
                if (solicitud.getMontoCredito().doubleValue() >= listaTasaCredito.get(i).getMontoInicial().doubleValue() && solicitud.getMontoCredito().doubleValue() <= listaTasaCredito.get(i).getMontoFinal().doubleValue()) {
                    solicitud.setCodigoTasa(listaTasaCredito.get(i));
                    solicitud.setTasa(formatoValor(listaTasaCredito.get(i).getCodigoTasa().getValor()));

                    ////System.out.println("formatoValor(listaTasaCredito.get(i).getCodigoTasa().getValor() " + listaTasaCredito.get(i).getCodigoTasa().getValor() + " solicitud.getTasa() " + solicitud.getTasa() + "  tasa actual " + this.tasaActualCredito);
                    if (this.solicitud.getTasa().doubleValue() != this.tasaActualCredito.doubleValue()) {
                        valido = false;
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TasaNoPuedeSerDiferente") + "TASA NUEVA: " + this.solicitud.getTasa().toString());
                        this.solicitud.setTasa(this.tasaActualCredito);
                        return valido;
                    }

                    valido = true;
                    break;
                }
            }
        }

        if (valido) {
            solicitud.setTasa(this.formatoValor(solicitud.getTasa()));

            // Validando que el Rubro tenga una transaccion Definida
            for (TipoRubroProCreMonIfi trpcmi : listaRubros) {
                if (trpcmi.getCobradoEn() != 'D') {
                    if (ejbFacadeRubroConceptoTransaccion.find(trpcmi.getCodigoTipoRubro().getCodio()) == null) {
                        valido = false;
                    }
                    if (!valido) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RubroNoTieneTransaccionDefinida") + trpcmi.getCodigoTipoRubro().getNombre());
                        break;
                    }
                }
            }

        } else {
            solicitud.setTasa(this.formatoValor(new BigDecimal("0")));
            solicitud.setMontoCredito(BigDecimal.ZERO);
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MontoCreditoFueraDeRango"));
        }

        return valido;

    }

    /**
     * Obtiene el pago de la cuota fija, esto sirve para calculos de tabla de
     * Amortizacion de Pago Fijo.
     *
     * @return Pago
     */
    public double getPagoCuotaFija() {

        context = RequestContext.getCurrentInstance();

        double pagoCuota = 0;
        try {

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            llamaSP.setNombreSP("mks_creditos.pkm_solicitud.p_obtiene_pago_cuota_fija");

            llamaSP.setNumeroParametros(5);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pn_tasa", this.getSolicitud().getTasa()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pn_dias_plazo", solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getEquivalenciaDias()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pn_cuotas", solicitud.getNumeroCuotas()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pn_monto_credito", solicitud.getMontoCredito()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_exponente", Types.DOUBLE});

            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                pagoCuota = Double.parseDouble(llamaSP.getListResultado().get(0).toString());

            } else {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"concedeBean", "getPagoCuotaFija", CapturaError.getErrorException(ex)});
        }

        return pagoCuota;
    }
    //---------------------------------------------------------------------------
    // PROCESOS DE CONCECION DE CREDITO
    /**
     * Guarda la Concecion del Credito
     *
     *
     * @param actionEvent
     */
    public void guardaConcedeCredito(ActionEvent actionEvent) throws IOException {
        context = RequestContext.getCurrentInstance();
        try {

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Elimina detalles de la solictud para ingresar valores ultimos
            if (this.eliminaDatosSolicitud()) {
                // Guarda la Tabla de Amortizacion
                if (this.guardaTablaAmortizacion()) {
                    // Guarda los rubros de la tabla
                    if (this.guardaRubrosTablaAmort()) {
                        //Guarda los Tipos de Rubro del Credito
                        if (guardaTipoRubroCredito()) {
                            //Guarda Seguro CONTIGO
                            if (this.guardaContratoSeguro()) {
                                // Concecion y Acreditacion del Crédito.
                                if (this.concedeCredito()) {

                                    // Inserta la tabla de calculo cuota a pagar
                                    insertaCalculoCuotaPagar();
                                }
                            }
                        }
                    }
                }
            }

            // Si fue una ejecucion correcta de procesos.
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoConcedioCorrectamente");
                MuestraMensaje.addSatisfactorio(msg);
                context.execute("procesandoDlg.hide()");
                context.execute("ConcecionCreditoDialog.hide()");

                // lamando al formulario de licitud de fondos
                this.impresionFormularioLicitudFondos();

                //context.execute("ImprimeComprobanteConcecionDialogo.show()");
                this.obtieneSolicitudes();

            } else {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                context.execute("procesandoDlg.hide()");
            }

        } catch (UnknownHostException ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"concedeBean", "guardaConcedeCredito", CapturaError.getErrorException(ex)});
        }

    }
    
    private boolean guardaContratoSeguro() {
        if (isTieneRubroSegContigo()) {
            llamaSP.setNombreSP("mks_seguros.pkm_contrato_seguro.p_registra_seguro");
            llamaSP.setNumeroParametros(17);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", solicitud.getCodigoSocio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_valor", 1});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_contrato", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_vencimiento", new java.sql.Timestamp(this.itemsTablaAmortizacion.get(itemsTablaAmortizacion.size() - 1).getFechaPago().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_feche_actualizacion", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", "C"});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", solicitud.getSolicitudPK().getNumero()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_crea", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_actualiza", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_eliminado", "N"});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "A"});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_renovacion_automatica", "N"});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_contrato", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                //System.out.println("Codigo Contrato " + (Long.parseLong(llamaSP.getListResultado().get(0).toString())));
                this.codigoSeguroContigo = Long.parseLong(llamaSP.getListResultado().get(0).toString());
            }

            return llamaSP.isEjecucionCorrecta();
        } else {
            return true;
        }

    }

    /**
     * Reliza la conecion del credito y acredita en la cuenta del socio.
     *
     *
     * @return true correcto false incorrecto
     * @throws UnknownHostException
     */
    public boolean concedeCredito() throws UnknownHostException {
        llamaSP.setNombreSP("mks_creditos.pkm_solicitud.p_concede_credito");        
        this.fecha = new java.sql.Timestamp(new Date().getTime());
        llamaSP.setNumeroParametros(24);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", solicitud.getSolicitudPK().getNumero()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", solicitud.getSolicitudPK().getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_cartera", solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigoTipoCartera().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_estado", Long.parseLong("6")});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", fecha});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tuvo_rubros", (this.totalRubrosCredito.compareTo(new BigDecimal("0.00")) == 0 ? "N" : "S")});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo_capital", this.solicitud.getMontoCredito()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuota_pendiente", Long.parseLong("1")});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_monto_acreditado", this.solicitud.getMontoCredito()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.getSolicitudDetalle().getCuentaAcreditada().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", this.getSolicitudDetalle().getCuentaAcreditada().getProductoIfip().getProductoIfipPK().getCodigoTipoProducto()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.getSolicitudDetalle().getCuentaAcreditada().getProductoIfip().getProductoIfipPK().getCodigoMoneda()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_dia_fijo", String.valueOf(this.solicitud.getDiaFijo())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo_tabla", String.valueOf(this.solicitud.getTipoTabla())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cuotas", solicitud.getNumeroCuotas()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", this.solicitud.getSocio().getCodigoPersona().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo", licitudFondosModulo.getCodigo()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario", Types.VARCHAR});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {

            this.generaFormulario = -1L;
            // Encerando el codigo de formulario de licitud de fondos.
            ActivacionUsuario.setCodigoFormularioLicitudFondos(null);

            // Obtencion de parametros de salida
            this.msg = (llamaSP.getListResultado().get(0) != null) ? llamaSP.getListResultado().get(0).toString() : null;
            this.codigoFormulario = (llamaSP.getListResultado().get(1) != null) ? Long.parseLong(llamaSP.getListResultado().get(1).toString()) : null;
            this.generaFormulario = Long.parseLong(llamaSP.getListResultado().get(2).toString());

            // Asignando el codigo de formulario
            ActivacionUsuario.setCodigoFormularioLicitudFondos(codigoFormulario);
        }

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Actualiza la tabla de amortizacion generada en la concecion del credito
     *
     * @return true correcto y false incorrecto
     * @throws UnknownHostException
     */
    public boolean actualizaTablaAmortizacion() throws UnknownHostException {
        llamaSP.setNombreSP("mks_creditos.pkg_tabla_amortizacion.p_actualiza_tabla_concecion");
        llamaSP.setNumeroParametros(10);

        for (TablaAmortizacion tablaAmortizacion : itemsTablaAmortizacion) {
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", solicitud.getSolicitudPK().getNumero()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", solicitud.getSolicitudPK().getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuota", tablaAmortizacion.getTablaAmortizacionPK().getCuota()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_inicio", new java.sql.Date(tablaAmortizacion.getFechaInicio().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_pago", new java.sql.Date(tablaAmortizacion.getFechaPago().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_capital", tablaAmortizacion.getCapital()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo_capital", tablaAmortizacion.getSaldoCapital()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_interes", tablaAmortizacion.getInteres()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_rubros", tablaAmortizacion.getRubros()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", tablaAmortizacion.getTotal()});

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

    /**
     * Elimina los detalles de la solicitud que sean necesario para ingresar con
     * los nuevos valores
     *
     * @return
     */
    private boolean eliminaDatosSolicitud() {

        llamaSP.setNombreSP("mks_creditos.pkm_solicitud.p_elimina_datos_solicitud_con");
        llamaSP.setNumeroParametros(2);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero", this.solicitud.getSolicitudPK().getNumero()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Guarda la Tabla de Amotizacion
     *
     * @return
     */
    private boolean guardaTablaAmortizacion() {

        llamaSP.setNombreSP("mks_creditos.pkm_tabla_amortizacion.p_inserta_item_tabla");
        llamaSP.setNumeroParametros(10);
        for (TablaAmortizacion item : this.getItemsTablaAmortizacion()) {
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitud.getSolicitudPK().getNumero()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuota", item.getTablaAmortizacionPK().getCuota()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_inicio", new java.sql.Timestamp(item.getFechaInicio().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_pago", new java.sql.Timestamp(item.getFechaPago().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo_capital", item.getSaldoCapital()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_capital", item.getCapital()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_interes", item.getInteres()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_rubros", item.getRubros()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", item.getTotal()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el registro fue correcto
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
        }

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Guarda los Rubrols de la Tabla de Amotizacion.
     *
     * @return
     */
    private boolean guardaRubrosTablaAmort() {

        llamaSP.setNombreSP("mks_creditos.pkm_rubro_tabla_amort.p_inserta_rubro_tab_amor");
        llamaSP.setNumeroParametros(5);
        for (RubroTablaAmortizacion rta : itemsRubroTablaAmortizacion) {
            ///Guarda el rubro Seguro Contigo Solo para mensuales y bimensuales
            if (!((rta.getTipoRubroProCreMonIfi().getCodigoTipoRubro().getCodio() == 6 || rta.getTipoRubroProCreMonIfi().getCodigoTipoRubro().getCodio() == 7)
                    && !(solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo() == 4 || solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo() == 5))) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitud.getSolicitudPK().getNumero()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuota", rta.getRubroTablaAmortizacionPK().getCuota()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_rubro", rta.getTipoRubroProCreMonIfi().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", rta.getValor()});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el registro fue correcto
                if (!llamaSP.isEjecucionCorrecta()) {
                    return llamaSP.isEjecucionCorrecta();
                }
            }
        }

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Gaurda el Tipo de Rubro Grabado en el Credito
     *
     * @return
     */
    private boolean guardaTipoRubroCredito() {
        llamaSP.setNombreSP("mks_creditos.pkm_tipo_rubro_credito.p_inserta_rubro");
        llamaSP.setNumeroParametros(8);
        for (TipoRubroCredito trc : this.itemsTipoRubroCredito) {
            if (!((trc.getTipoRubro().getCodio() == 6 || trc.getTipoRubro().getCodio() == 7)
                    && !(solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo() == 4 || solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo() == 5))) {
                if (trc.getTipoRubro().getCodio().longValue() == 6) {
                    setTieneRubroSegContigo(true);
                }
                //System.out.println("Tipo Rubro " + trc.getTipoRubro().getCodio().longValue() + (trc.getTipoRubro().getCodio().longValue() == 6));
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_rubro", trc.getTipoRubro().getCodio()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitud.getSolicitudPK().getNumero()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.solicitud.getSolicitudPK().getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", String.valueOf(trc.getTipo())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_calculado_sobre", String.valueOf(trc.getCalculadoSobre())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_cobrado_en", String.valueOf(trc.getCobradoEn())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", trc.getValor()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_rubro_grabado", trc.getTotalRubroGrabado()});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el registro fue correcto
                if (!llamaSP.isEjecucionCorrecta()) {
                    return llamaSP.isEjecucionCorrecta();
                }
            }
        }

        return llamaSP.isEjecucionCorrecta();
    }

    // -- FIN DE GUARADAR LA CONCECION
    //-------------------------------------------------------------------------------
    /**
     * Inserta en las cuotas en la tabla de calculo cuota a pagar que serviran
     * para realizar los calculos diarios.
     *
     * @return true correcto y false incorrecto
     * @throws UnknownHostException
     */
    public boolean insertaCalculoCuotaPagar() throws UnknownHostException {
        llamaSP.setNombreSP("mks_creditos.pkg_calculo_cuota_pagar.p_inserta_cuotas_concecion");

        this.fecha = new java.sql.Timestamp(new Date().getTime());
        llamaSP.setNumeroParametros(2);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", solicitud.getSolicitudPK().getNumero()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", solicitud.getSolicitudPK().getCodigoIfip()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }
    // Fin DE PROCESO DE CONCECION DE CREDITO
    //-------------------------------------------------------------------------------

    /**
     * Genera pagare del Credito
     *
     * @param actionEvent
     */
    public void generaPagareCredito(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");

            this.solicitudPagare = this.ejbFacadeSolicitudPagare.find(new SolicitudPagarePK(this.solicitud.getSolicitudPK().getNumero(), this.solicitud.getSolicitudPK().getCodigoIfip()));

            if (solicitudPagare == null) {
                // Cargando la conexion de BD
                llamaSP.cargaConexion();

                // Indicando que no cierre la conexion de la base de datos
                llamaSP.setCerrarConexion(false);

                // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                llamaSP.autoCommit(false);

                llamaSP.setNombreSP("mks_creditos.pkm_solicitud.p_genera_pagare");

                this.fecha = new java.sql.Timestamp(new Date().getTime());

                llamaSP.setNumeroParametros(5);

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", solicitud.getSolicitudPK().getNumero()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", solicitud.getSolicitudPK().getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", fecha});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                llamaSP.getListParametrosSalida().add(new Object[]{"p_numero_pagare", Types.NUMERIC});

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando que la ejecucion del proceso ha sido correcta
                if (llamaSP.isEjecucionCorrecta()) {
                    // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                    llamaSP.commit();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PagareEmitidoCorrectamente");
                    MuestraMensaje.addSatisfactorio(msg);

                    this.numeroPagare = Long.parseLong(llamaSP.getListResultado().get(0).toString());
                    context.execute("procesandoDlg.hide()");
                    context.execute("ImprimeComprobantePagareDialogo.show()");
                    //this.init();
                } else {
                    context.execute("procesandoDlg.hide()");
                    llamaSP.rollback();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                }
            } else {
                this.numeroPagare = solicitudPagare.getNumeroPagare();
                context.execute("procesandoDlg.hide()");
                context.execute("ImprimeComprobantePagareDialogo.show()");
            }

        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"concedeBean", "concedeCredito", CapturaError.getErrorException(ex)});
        }
    }

    //-----------------------------------------------------------------
    // IMPRESION DE DOCUMENTOS DE CONCECION
    /**
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimirPagare(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        //this.solicitudPagare = this.ejbFacadeSolicitudPagare.find(new SolicitudPagarePK(this.solicitud.getSolicitudPK().getNumero(), this.solicitud.getSolicitudPK().getCodigoIfip()));
        ////System.out.println("numeroSolicitud: " + this.solicitud.getSolicitudPK().getNumero());
        String nombreReporte;
        String directoriReporte = "/financiero/creditos/ConcederCredito/reporte/";
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("numeroCredito", this.solicitud.getSolicitudPK().getNumero());
        getGeneraReporte().getParametros().put("codigoIfip", this.solicitud.getSolicitudPK().getCodigoIfip());
        getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));
        //getGeneraReporte().getParametros().put("SUBREPORT_DIR", directoriReporte);
        getGeneraReporte().getParametros().put("subReporteGarantes", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "garantes"));

        nombreReporte = "pagare";

        getGeneraReporte().exporta(directoriReporte, nombreReporte,
                nombreReporte + this.numeroPagare.toString() + "Solicutd" + this.solicitud.getSolicitudPK().getNumero() + ".pdf",
                "PDF", externalContext, facesContext);
        // Guardar el servicio financiero
        this.guardarServicioFinanciero(this.solicitud.getSolicitudPK().getNumero(), "imprimirPagare");
    }
    
    public void imprimirAceptacionSeguro(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        //System.out.println("IMPRIME ACEPTACION");
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/seguros/contratoSeguro/reporte/";
        ////System.out.println("Imprime el Contrato");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoContratoSeg", codigoSeguroContigo);
        //System.out.println("Codigo Contrato " + codigoSeguroContigo);
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());

        nombreReporte = "aceptacionSeguro";

        getGeneraReporte().exporta("/financiero/seguros/contratoSeguro/reporte/", nombreReporte,
                nombreReporte + this.codigoSeguroContigo + ".pdf",
                "PDF", externalContext, facesContext);
    }

    public void imprimirCobertura(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        //System.out.println("IMPRIME ACEPTACION");
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/seguros/contratoSeguro/reporte/";
        ////System.out.println("Imprime el Contrato");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoContratoSeg", codigoSeguroContigo);
        //System.out.println("Codigo Contrato " + codigoSeguroContigo);
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());

        nombreReporte = "certificadoCobertura";

        getGeneraReporte().exporta("/financiero/seguros/contratoSeguro/reporte/", nombreReporte,
                nombreReporte + this.codigoSeguroContigo + ".pdf",
                "PDF", externalContext, facesContext);
    }
    
    public void imprimirContratoSeguro(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/seguros/contratoSeguro/reporte/";
        ////System.out.println("Imprime el Contrato");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoContratoSeg", codigoSeguroContigo);
        //System.out.println("Codigo Contrato " + codigoSeguroContigo);
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());

        nombreReporte = "contratoSeguroCredito";

        getGeneraReporte().exporta("/financiero/seguros/contratoSeguro/reporte/", nombreReporte,
                nombreReporte + codigoSeguroContigo + ".pdf",
                "PDF", externalContext, facesContext);
    }

    /**
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimirContrato(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/creditos/ConcederCredito/reporte/";
        ////System.out.println("Imprime el Contrato");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("numeroCredito", this.solicitud.getSolicitudPK().getNumero());
        getGeneraReporte().getParametros().put("codigoIfip", this.solicitud.getSolicitudPK().getCodigoIfip());
        getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));
        getGeneraReporte().getParametros().put("subReporteListadoGarantes", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "garantesListado"));
        getGeneraReporte().getParametros().put("subReporteGarantes", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "garantes"));
        getGeneraReporte().getParametros().put("subReporteAceptacionRubro", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "aceptacionRubro"));

        nombreReporte = "contrato";

        getGeneraReporte().exporta("/financiero/creditos/ConcederCredito/reporte/", nombreReporte,
                nombreReporte + this.solicitud.getSolicitudPK().getNumero() + ".pdf",
                "PDF", externalContext, facesContext);
        // Guardar el servicio financiero
        this.guardarServicioFinanciero(this.solicitud.getSolicitudPK().getNumero(), "imprimirContrato");
    }

    /**
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimirConcecion(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/creditos/ConcederCredito/reporte/";
        ////System.out.println("Imprime Concecion");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("numeroCredito", this.solicitud.getSolicitudPK().getNumero());
        getGeneraReporte().getParametros().put("codigoIfip", this.solicitud.getSolicitudPK().getCodigoIfip());
        String path = obtienePathReporte("tablaAmortizacion");
        if (path == null) {
            getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));
        } else {
            getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, path, "tablaAmortizacion"));
        }
        path = obtienePathReporte("aceptacionRubro");
        if (path == null) {
            getGeneraReporte().getParametros().put("subReporteAceptacionRubro", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "aceptacionRubro"));
        } else {
            getGeneraReporte().getParametros().put("subReporteAceptacionRubro", getGeneraReporte().getSubReporte(externalContext, path, "aceptacionRubro"));
        }
        path = obtienePathReporte("garantes");
        if (path == null) {
            getGeneraReporte().getParametros().put("subReporteGarantes", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "garantes"));
        } else {
            getGeneraReporte().getParametros().put("subReporteGarantes", getGeneraReporte().getSubReporte(externalContext, path, "garantes"));
        }
        nombreReporte = "concecion";
        String pathReporte = obtienePathReporte(nombreReporte);
        if (pathReporte == null) {
            pathReporte = "/financiero/creditos/ConcederCredito/reporte/";
        }
        getGeneraReporte().exporta(pathReporte, nombreReporte,
                nombreReporte + this.solicitud.getSolicitudPK().getNumero() + ".pdf",
                "PDF", externalContext, facesContext);
        // Guardar el servicio financiero
        this.guardarServicioFinanciero(this.solicitud.getSolicitudPK().getNumero(), "imprimirConcecion");
    }

    private String obtienePathReporte(String nombreReporte) {
        ParametroServidorIfip parametro = ejbFacadeParametroServidorIfip.find(new ParametroServidorIfipPK(9, ActivacionUsuario.getCodigoIfip()));
        if (parametro == null) {
            System.out.println("No existe directorio de reportes externos");
            return null;
        } else {
            String ruta = parametro.getValor();
            File archivo = new File(ruta + nombreReporte + ".jasper");
            if (archivo.exists()) {
                return ruta;
            } else {
                System.out.println("No existe el reporte " + nombreReporte + ".jasper" + " en el directorio configurado");
                return null;
            }
        }
    }
    
    private void guardarServicioFinanciero( long numeroCredito, String proceso ){
        // Registrar el servicio financiero para la F01 
        try {
            // Cargando la conexion de BD
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_ifips.pkm_servicio_financiero.p_registra_servicio_financiero");
            llamaSP.setNumeroParametros(6);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_servicio_fin_tip_can", 8}); // EMISION DE TABLAS DE AMORTIZACION
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha",new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_identificador", numeroCredito});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_cantidad", 1});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_observacion","Impresion desde "+proceso});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
            }else{
                System.out.println("Error la registrar el servicio financiero desde Consulta de Cuentas.");
                System.out.println(llamaSP.getError());
                System.out.println(llamaSP.getErroSql());
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SolicitudController", "guardarServicioFinanciero", CapturaError.getErrorException(ex)});
        }
        finally{
             if( llamaSP.getConexionBD()!= null ) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
             }
        }
    }

  /**
     * Llama al diaglo de la impresion del formulario en caso de que exista
     *
     * @throws IOException
     */
    public void impresionFormularioLicitudFondos() throws IOException {
        context = RequestContext.getCurrentInstance();
        context.execute("ImprimeComprobanteConcecionDialogo.hide()");
        context = RequestContext.getCurrentInstance();
        if (generaFormulario == 1L && ActivacionUsuario.getCodigoFormularioLicitudFondos() != null) {

            context = RequestContext.getCurrentInstance();

            LicitudFondosFormulario lff = ejbFacadeLicitudFondosFormulario.find(codigoFormulario);
            List<LicitudFonCptoTran> listaLicitudFonCptoTran = ejbFacadeLicitudFonCptoTran.getByTipo(lff.getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoConcepto(),
                                                                                                     lff.getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoMoneda(),
                                                                                                     lff.getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoTipoProducto(),
                                                                                                     lff.getCodigoIfip(),
                                                                                                     'N');
            
            if (listaLicitudFonCptoTran.size() == 1) {
                //context.execute("FormularioLicitudFondosDialogo.show()");

                List<String> listaComponentesActualizar = new ArrayList<String>();
                listaComponentesActualizar.add("FormularioLicitudFondosForm");
                context.update(listaComponentesActualizar);

                listaComponentesActualizar = new ArrayList<String>();
                listaComponentesActualizar.add("ConcedeCreditoForm");
                context.update(listaComponentesActualizar);

                //Indicando que dialogo mostrar
                //ActivacionUsuario.setDialagoAMostrar("ImprimeComprobanteConcecionDialogo");
                // Iniciand formulario de licitud de fondos
                getLicitudFondosBean().setCodigoFormulario(this.codigoFormulario);
                getLicitudFondosBean().preparaLicitudFondos();
                getLicitudFondosBean().setLlamadoOtrasVentanas(true);
                getLicitudFondosBean().cargarListaDestinos('N');
                getLicitudFondosBean().cargarListaOrigenes('S');

                switch (listaLicitudFonCptoTran.get(0).getTipo()) {
                    case 'A':
                        getLicitudFondosBean().setDeshabilitaComboDestino(false);
                        getLicitudFondosBean().setDeshabilitaComboOrigen(false);
                        break;
                    case 'O':
                        getLicitudFondosBean().setDeshabilitaComboOrigen(false);
                        break;
                    default:
                        getLicitudFondosBean().setDeshabilitaComboDestino(false);
                        break;
                }

                getLicitudFondosBean().setListaComponentesActualizar(listaComponentesActualizar);
                getLicitudFondosBean().setDialogoMostrar("ImprimeComprobanteConcecionDialogo");

                context.execute("FormularioLicitudFondosDialogo.show()");
            }else
            {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteTransaccionLicitudFondos"));
                context.execute("ImprimeComprobanteConcecionDialogo.show()");
            }
        } else {
            context.execute("ImprimeComprobanteConcecionDialogo.show()");
        }
    }
    // ---------------------------------------------------------------------------
    /**
     * @return the itemsSolicitud
     */
    public List<Solicitud> getItemsSolicitud() {
        return itemsSolicitud;
    }

    /**
     * @param itemsSolicitud the itemsSolicitud to set
     */
    public void setItemsSolicitud(List<Solicitud> itemsSolicitud) {
        this.itemsSolicitud = itemsSolicitud;
    }

    /**
     * @return the itemsSocio
     */
    public List<Socio> getItemsSocio() {
        return itemsSocio;
    }

    /**
     * @param itemsSocio the itemsSocio to set
     */
    public void setItemsSocio(List<Socio> itemsSocio) {
        this.itemsSocio = itemsSocio;
    }

    /**
     * @return the socioSel
     */
    public Socio getSocioSel() {
        return socioSel;
    }

    /**
     * @param socioSel the socioSel to set
     */
    public void setSocioSel(Socio socioSel) {
        this.socioSel = socioSel;
    }

    /**
     * @return the estadoCreditoBusqueda
     */
    public EstadoCredito getEstadoCreditoBusqueda() {
        return estadoCreditoBusqueda;
    }

    /**
     * @param estadoCreditoBusqueda the estadoCreditoBusqueda to set
     */
    public void setEstadoCreditoBusqueda(EstadoCredito estadoCreditoBusqueda) {
        this.estadoCreditoBusqueda = estadoCreditoBusqueda;
    }

    /**
     * @return the itemsEstadoCreditoLista
     */
    public List<EstadoCredito> getItemsEstadoCreditoLista() {
        this.itemsEstadoCreditoLista = this.ejbFacadeEstadoCredito.findAll();
        return itemsEstadoCreditoLista;
    }

    /**
     * @param itemsEstadoCreditoLista the itemsEstadoCreditoLista to set
     */
    public void setItemsEstadoCreditoLista(List<EstadoCredito> itemsEstadoCreditoLista) {
        this.itemsEstadoCreditoLista = itemsEstadoCreditoLista;
    }

    /**
     * @return the solicitud
     */
    public Solicitud getSolicitud() {
        return solicitud;
    }

    /**
     * @param solicitud the solicitud to set
     */
    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the itemsSolicitudSel
     */
    public List<Solicitud> getItemsSolicitudSel() {
        return itemsSolicitudSel;
    }

    /**
     * @param itemsSolicitudSel the itemsSolicitudSel to set
     */
    public void setItemsSolicitudSel(List<Solicitud> itemsSolicitudSel) {
        this.itemsSolicitudSel = itemsSolicitudSel;
    }

    /**
     * @return the deshabilitaEnvioBot
     */
    public boolean isDeshabilitaEnvioBot() {
        return deshabilitaEnvioBot;
    }

    /**
     * @param deshabilitaEnvioBot the deshabilitaEnvioBot to set
     */
    public void setDeshabilitaEnvioBot(boolean deshabilitaEnvioBot) {
        this.deshabilitaEnvioBot = deshabilitaEnvioBot;
    }

    /**
     * @return the usuarioEstadoCredito
     */
    public UsuarioEstadoCredito getUsuarioEstadoCredito() {
        return usuarioEstadoCredito;
    }

    /**
     * @param usuarioEstadoCredito the usuarioEstadoCredito to set
     */
    public void setUsuarioEstadoCredito(UsuarioEstadoCredito usuarioEstadoCredito) {
        this.usuarioEstadoCredito = usuarioEstadoCredito;
    }

    /**
     * @return the fecha
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
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
     * @return the es_pagare
     */
    public String getEs_pagare() {
        return es_pagare;
    }

    /**
     * @param es_pagare the es_pagare to set
     */
    public void setEs_pagare(String es_pagare) {
        this.es_pagare = es_pagare;
    }

    /**
     * @return the numeroCredito
     */
    public Long getNumeroCredito() {
        return numeroCredito;
    }

    /**
     * @param numeroCredito the numeroCredito to set
     */
    public void setNumeroCredito(Long numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    /**
     * @return the numeroPagare
     */
    public Long getNumeroPagare() {
        return numeroPagare;
    }

    /**
     * @param numeroPagare the numeroPagare to set
     */
    public void setNumeroPagare(Long numeroPagare) {
        this.numeroPagare = numeroPagare;
    }

    /**
     * @return the solicitudSel
     */
    public Solicitud getSolicitudSel() {
        return solicitudSel;
    }

    /**
     * @param solicitudSel the solicitudSel to set
     */
    public void setSolicitudSel(Solicitud solicitudSel) {
        this.solicitudSel = solicitudSel;
    }

    /**
     * @return the solicitudDetalle
     */
    public SolicitudDetalle getSolicitudDetalle() {
        return solicitudDetalle;
    }

    /**
     * @param solicitudDetalle the solicitudDetalle to set
     */
    public void setSolicitudDetalle(SolicitudDetalle solicitudDetalle) {
        this.solicitudDetalle = solicitudDetalle;
    }

    /**
     * @return the itemsSolicitudDetalle
     */
    public List<SolicitudDetalle> getItemsSolicitudDetalle() {
        return itemsSolicitudDetalle;
    }

    /**
     * @param itemsSolicitudDetalle the itemsSolicitudDetalle to set
     */
    public void setItemsSolicitudDetalle(List<SolicitudDetalle> itemsSolicitudDetalle) {
        this.itemsSolicitudDetalle = itemsSolicitudDetalle;
    }

    /**
     * @return the itemsCuentas
     */
    public List<Cuenta> getItemsCuentas() {
        return itemsCuentas;
    }

    /**
     * @param itemsCuentas the itemsCuentas to set
     */
    public void setItemsCuentas(List<Cuenta> itemsCuentas) {
        this.itemsCuentas = itemsCuentas;
    }

    /**
     * @return the tipoCuentaSel
     */
    public char getTipoCuentaSel() {
        return tipoCuentaSel;
    }

    /**
     * @param tipoCuentaSel the tipoCuentaSel to set
     */
    public void setTipoCuentaSel(char tipoCuentaSel) {
        this.tipoCuentaSel = tipoCuentaSel;
    }

    /**
     * @return the cuentaCombo
     */
    public Cuenta getCuentaCombo() {
        return cuentaCombo;
    }

    /**
     * @param cuentaCombo the cuentaCombo to set
     */
    public void setCuentaCombo(Cuenta cuentaCombo) {
        this.cuentaCombo = cuentaCombo;
    }

    /**
     * @return the itemsTablaAmortizacion
     */
    public List<TablaAmortizacion> getItemsTablaAmortizacion() {
        return itemsTablaAmortizacion;
    }

    /**
     * @param itemsTablaAmortizacion the itemsTablaAmortizacion to set
     */
    public void setItemsTablaAmortizacion(List<TablaAmortizacion> itemsTablaAmortizacion) {
        this.itemsTablaAmortizacion = itemsTablaAmortizacion;
    }

    /**
     * @return the diasPago
     */
    public Long getDiasPago() {
        return diasPago;
    }

    /**
     * @param diasPago the diasPago to set
     */
    public void setDiasPago(Long diasPago) {
        this.diasPago = diasPago;
    }

    /**
     * @return the solicitudPagare
     */
    public SolicitudPagare getSolicitudPagare() {
        return solicitudPagare;
    }

    /**
     * @param solicitudPagare the solicitudPagare to set
     */
    public void setSolicitudPagare(SolicitudPagare solicitudPagare) {
        this.solicitudPagare = solicitudPagare;
    }

    /**
     * @return the totalCapital
     */
    public BigDecimal getTotalCapital() {
        return totalCapital;
    }

    /**
     * @param totalCapital the totalCapital to set
     */
    public void setTotalCapital(BigDecimal totalCapital) {
        this.totalCapital = totalCapital;
    }

    /**
     * @return the totalInteres
     */
    public BigDecimal getTotalInteres() {
        return totalInteres;
    }

    /**
     * @param totalInteres the totalInteres to set
     */
    public void setTotalInteres(BigDecimal totalInteres) {
        this.totalInteres = totalInteres;
    }

    /**
     * @return the totalRubros
     */
    public BigDecimal getTotalRubros() {
        return totalRubros;
    }

    /**
     * @param totalRubros the totalRubros to set
     */
    public void setTotalRubros(BigDecimal totalRubros) {
        this.totalRubros = totalRubros;
    }

    /**
     * @return the totalCredito
     */
    public BigDecimal getTotalCredito() {
        return totalCredito;
    }

    /**
     * @param totalCredito the totalCredito to set
     */
    public void setTotalCredito(BigDecimal totalCredito) {
        this.totalCredito = totalCredito;
    }

    /**
     * @return the simboloMoneda
     */
    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    /**
     * @param simboloMoneda the simboloMoneda to set
     */
    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    /**
     * @return the totalCapitalCadena
     */
    public String getTotalCapitalCadena() {
        return totalCapitalCadena;
    }

    /**
     * @param totalCapitalCadena the totalCapitalCadena to set
     */
    public void setTotalCapitalCadena(String totalCapitalCadena) {
        this.totalCapitalCadena = totalCapitalCadena;
    }

    /**
     * @return the totalInteresCadena
     */
    public String getTotalInteresCadena() {
        return totalInteresCadena;
    }

    /**
     * @param totalInteresCadena the totalInteresCadena to set
     */
    public void setTotalInteresCadena(String totalInteresCadena) {
        this.totalInteresCadena = totalInteresCadena;
    }

    /**
     * @return the totalRubrosCadena
     */
    public String getTotalRubrosCadena() {
        return totalRubrosCadena;
    }

    /**
     * @param totalRubrosCadena the totalRubrosCadena to set
     */
    public void setTotalRubrosCadena(String totalRubrosCadena) {
        this.totalRubrosCadena = totalRubrosCadena;
    }

    /**
     * @return the totalCreditoCadena
     */
    public String getTotalCreditoCadena() {
        return totalCreditoCadena;
    }

    /**
     * @param totalCreditoCadena the totalCreditoCadena to set
     */
    public void setTotalCreditoCadena(String totalCreditoCadena) {
        this.totalCreditoCadena = totalCreditoCadena;
    }

    /**
     * @return the itemsTipoRubroCredito
     */
    public List<TipoRubroCredito> getItemsTipoRubroCredito() {
        return itemsTipoRubroCredito;
    }

    /**
     * @param itemsTipoRubroCredito the itemsTipoRubroCredito to set
     */
    public void setItemsTipoRubroCredito(List<TipoRubroCredito> itemsTipoRubroCredito) {
        this.itemsTipoRubroCredito = itemsTipoRubroCredito;
    }

    /**
     * @return the itemsRubroTablaAmortizacion
     */
    public List<RubroTablaAmortizacion> getItemsRubroTablaAmortizacion() {
        return itemsRubroTablaAmortizacion;
    }

    /**
     * @param itemsRubroTablaAmortizacion the itemsRubroTablaAmortizacion to set
     */
    public void setItemsRubroTablaAmortizacion(List<RubroTablaAmortizacion> itemsRubroTablaAmortizacion) {
        this.itemsRubroTablaAmortizacion = itemsRubroTablaAmortizacion;
    }

    /**
     * @return the totalRubrosCredito
     */
    public BigDecimal getTotalRubrosCredito() {
        return totalRubrosCredito;
    }

    /**
     * @param totalRubrosCredito the totalRubrosCredito to set
     */
    public void setTotalRubrosCredito(BigDecimal totalRubrosCredito) {
        this.totalRubrosCredito = totalRubrosCredito;
    }

    /**
     * @return the totalRubrosCreditoConcecion
     */
    public BigDecimal getTotalRubrosCreditoConcecion() {
        return totalRubrosCreditoConcecion;
    }

    /**
     * @param totalRubrosCreditoConcecion the totalRubrosCreditoConcecion to set
     */
    public void setTotalRubrosCreditoConcecion(BigDecimal totalRubrosCreditoConcecion) {
        this.totalRubrosCreditoConcecion = totalRubrosCreditoConcecion;
    }

    /**
     * @return the listaRubros
     */
    public List<TipoRubroProCreMonIfi> getListaRubros() {
        return listaRubros;
    }

    /**
     * @param listaRubros the listaRubros to set
     */
    public void setListaRubros(List<TipoRubroProCreMonIfi> listaRubros) {
        this.listaRubros = listaRubros;
    }

    /**
     * @return the licitudFondosBean
     */
    public LicitudFondosBean getLicitudFondosBean() {
        return licitudFondosBean;
    }

    /**
     * @param licitudFondosBean the licitudFondosBean to set
     */
    public void setLicitudFondosBean(LicitudFondosBean licitudFondosBean) {
        this.licitudFondosBean = licitudFondosBean;
    }
    
    /**
     * @return the tieneRubroSegContigo
     */
    public boolean isTieneRubroSegContigo() {
        return tieneRubroSegContigo;
    }

    /**
     * @param tieneRubroSegContigo the tieneRubroSegContigo to set
     */
    public void setTieneRubroSegContigo(boolean tieneRubroSegContigo) {
        this.tieneRubroSegContigo = tieneRubroSegContigo;
    }

    /**
     * @return the codigoSeguroContigo
     */
    public Long getCodigoSeguroContigo() {
        return codigoSeguroContigo;
    }

    /**
     * @param codigoSeguroContigo the codigoSeguroContigo to set
     */
    public void setCodigoSeguroContigo(Long codigoSeguroContigo) {
        this.codigoSeguroContigo = codigoSeguroContigo;
    }

    /**
     * @return the mensajeAporteCertificados
     */
    public String getMensajeAporteCertificados() {
        return mensajeAporteCertificados;
    }

    /**
     * @param mensajeAporteCertificados the mensajeAporteCertificados to set
     */
    public void setMensajeAporteCertificados(String mensajeAporteCertificados) {
        this.mensajeAporteCertificados = mensajeAporteCertificados;
    }
}
