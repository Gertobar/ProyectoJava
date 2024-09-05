/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.seguros.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.modelo.creditos.ParametroGeneralCreditoIfip;
import ec.renafipse.mks.modelo.creditos.ParametroGeneralCreditoIfipPK;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.modelo.seguros.ContratoSeguro;
import ec.renafipse.mks.modelo.seguros.ContratoSeguroBeneficiario;
import ec.renafipse.mks.modelo.seguros.ContratoSeguroDependientes;
import ec.renafipse.mks.modelo.seguros.ValorSeguro;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaConyuge;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioPK;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonial;
import ec.renafipse.mks.modelo.socios.TipoRelacion;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import ec.renafipse.mks.negocio.creditos.GaranteCreditoFacade;
import ec.renafipse.mks.negocio.creditos.ParametroGeneralCreditoIfipFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudDetalleFacade;
import ec.renafipse.mks.negocio.seguros.ContratoSeguroBeneficiarioFacade;
import ec.renafipse.mks.negocio.seguros.ContratoSeguroDependientesFacade;
import ec.renafipse.mks.negocio.seguros.ContratoSeguroFacade;
import ec.renafipse.mks.negocio.seguros.ValorSeguroFacade;
import ec.renafipse.mks.negocio.socios.PersonaConyugeFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.PersonaNaturalFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatrimonialFacade;
import ec.renafipse.mks.negocio.socios.TipoRelacionFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author vicastoc
 */
@ManagedBean(name = "contratoSeguroBean")
@ViewScoped
public class ContratoSeguroBean implements Serializable {

    @EJB
    private ContratoSeguroFacade ejbFacade;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private ValorSeguroFacade ejbFacadeValorSeguro;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private PersonaFacade ejbFacadePersona;

    @EJB
    private PersonaNaturalFacade ejbFacadePersonaNatural;

    @EJB
    private TipoRelacionFacade ejbFacadeTipoRelacion;

    @EJB
    private SocioSituacionPatrimonialFacade ejbFacadeSocioSituacionPatrimonial;

    @EJB
    private ParametroGeneralCreditoIfipFacade ejbFacadeParametroGeneralCreditoIfip;

    @EJB
    private GaranteCreditoFacade ejbFacadeGaranteCredito;

    @EJB
    private SolicitudDetalleFacade ejbFacadeSolicitudDetalle;

    @EJB
    private CalculoCuotaPagarFacade ejbFacadeCalculoCuotaPagar;

    @EJB
    private PersonaConyugeFacade ejbFacadeConyugePersona;

    @EJB
    private ContratoSeguroBeneficiarioFacade ejbFacadeContratoSeguroBeneficiario;

    @EJB
    private ContratoSeguroDependientesFacade ejbFacadeContratoSeguroDependientes;

    private ContratoSeguro contrato;
    private Socio socioSeleccionado;
    private ValorSeguro valorSeguro;
    private Persona personaDependiente;
    private Persona personaBeneficiario;
    private ContratoSeguroDependientes dependienteSeleccionado;
    private ContratoSeguroBeneficiario beneficiarioSeleccionado;
    private PersonaNatural personaBusqueda;
    private TipoRelacion tipoRelacionDependiente;
    private TipoRelacion tipoRelacionBeneficiario;
    private SocioSituacionPatrimonial socioSituacionPatrimonial;
    private GeneraReporte generaReporte;
    private ContratoSeguro contratoSeleccionado;

    private Long codigoSocio;
    private String nombreSocioBusqueda;
    private String nombrePersonaBusqueda;
    private String msg;
    private boolean busquedaDependiente;
    private BigDecimal porcentajeBeneficio;
    private int mesesMaxUltimaActualizacion;
    private Timestamp fechaContrato;
    private Timestamp fechaVencimiento;
    private Long codigoContrato;
    private String buscarPor;
    private String criterio;
    private boolean editaContrato;
    private boolean contratoPorCredito;
    private boolean renovacionAutomatica;
    private char tipoContrto;
    private String nombreDependiente;
    private Date fechaNacimientoDependiente;
    private boolean deshabilitaNombreDependiente;

    private List<Socio> itemsSocio;
    private List<Cuenta> itemsCuentasAhorrosVista;
    private List<ContratoSeguroDependientes> itemsDependientes;
    private List<ContratoSeguroBeneficiario> itemsBeneficiarios;
    private List<TipoRelacion> itemsTipoRelacionDependiente;
    private List<TipoRelacion> itemsTipoRelacionBeneficiario;
    private List<PersonaNatural> itemsPersonaBuisqueda;
    private List<ContratoSeguro> itemsContratoSeguro;

    private RequestContext context;
    private LlamaSP llamaSP;

    public ContratoSeguroBean() {

    }

    @PostConstruct
    public void init() {
        valorSeguro = ejbFacadeValorSeguro.find(new Long(1));
        setItemsTipoRelacionDependiente(ejbFacadeTipoRelacion.getItemsEsParaSegurosEliminado('S', 'N'));
        //Quia de la lista el tipo de relacio OTROS
        TipoRelacion quitar = null;
        for (TipoRelacion t : itemsTipoRelacionDependiente) {
            if (t.getCodigo() == 11) {
                quitar = t;
            }
        }
        itemsTipoRelacionDependiente.remove(quitar);
        setItemsTipoRelacionBeneficiario(ejbFacadeTipoRelacion.getItemsEsParaSegurosEliminado('S', 'N'));
        // Obteniendo los dias que un socio puede tener desactualizada su informacion
        ParametroGeneralCreditoIfip parametroGeneralCreditoIfip = ejbFacadeParametroGeneralCreditoIfip.find(new ParametroGeneralCreditoIfipPK((long) 2, ActivacionUsuario.getCodigoIfip()));
        if (parametroGeneralCreditoIfip == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaMesesUltimaActualizacion"));
            //Accediendo a la ventana de no permisos    

        }

        if (parametroGeneralCreditoIfip.getValorNumerico() == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaMesesUltimaActualizacion"));
            //Accediendo a la ventana de no permisos 

        }

        mesesMaxUltimaActualizacion = parametroGeneralCreditoIfip.getValorNumerico().intValue();
        llamaSP = new LlamaSP();
        contrato = new ContratoSeguro();
        this.setItemsContratoSeguro(ejbFacade.getItemsEliminado('N'));
        renovacionAutomatica = true;
    }

    public void buscaSocioContrato() {
        if (codigoSocio != null) {
            if (contrato.getSocio() == null || contrato.getSocio().getSocioPK().getCodigo() != codigoSocio) {
                Socio socio = ejbFacadeSocio.find(new SocioPK(this.codigoSocio, ActivacionUsuario.getCodigoIfip()));
                if (socio == null) {
                    inicializaCampos();
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste"));
                } else {
                    inicializaCampos();
                    if (!(ejbFacade.getItemsSocioNoEstado(socio.getSocioPK().getCodigo(),
                            'F',
                            'N',
                            ActivacionUsuario.getCodigoIfip()).isEmpty())) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("socioYaContratoSeguro"));
                        return;
                    }
                    contrato.setSocio(socio);
                    datosActualizados(socio);
                    this.setItemsCuentasAhorrosVista(this.ejbFacadeCuenta.getItemsPuedeReactivarAhorros(contrato.getSocio().getSocioPK().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'S', Long.parseLong("2")));
                }
            }
        }
    }

    public void preparaBusquedaSocioSolicitud(ActionEvent actionEvent) {
        //////System.out.println("Busca Socios Solicitud");
        this.codigoSocio = null;
        this.itemsSocio = null;
        this.contrato.setSocio(null);
        this.nombreSocioBusqueda = null;
        this.itemsSocio = null;
        //////System.out.println("Busca Socios Solicitud Fin");

    }

    public void preparaBusquedaDependiente(ActionEvent actionEvent) {
        System.out.println("Busca Dependiente");
        nombrePersonaBusqueda = "";
        itemsPersonaBuisqueda = null;
        personaBusqueda = null;
        setBusquedaDependiente(true);
        System.out.println("Busqueda depandiento " + isBusquedaDependiente());

    }

    public void preparaBusquedaBeneficiario(ActionEvent actionEvent) {
        //////System.out.println("Busca Socios Solicitud");
        nombrePersonaBusqueda = "";
        itemsPersonaBuisqueda = null;
        personaBusqueda = null;
        setBusquedaDependiente(false);
        //////System.out.println("Busca Socios Solicitud Fin");

    }

    /*
     Finaliza el contrato por peticion de socio
     */
    public void cancelaContratoSeguro(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.show()");
        try {
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            llamaSP.setNombreSP("mks_seguros.pkm_contrato_seguro.p_cancela_contrato_seguro");
            llamaSP.setNumeroParametros(3);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_seguro",contratoSeleccionado.getCodigo() });
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha", new java.sql.Timestamp(new Date().getTime())});
            
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                imprimirSolicitudCancelacion(actionEvent);
                context.execute("procesandoDlg.hide()");
                this.init();
                //this.nuevo(actionEvent);

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
                    new Object[]{"ContratoSeguroBean", "cancelaContratoSeguro", CapturaError.getErrorException(ex)});
        }
    }
    
    public void datosActualizados(Socio socio) {
        this.socioSituacionPatrimonial = ejbFacadeSocioSituacionPatrimonial.getSocioSocioSituacionPatrimonialCodigoSocioIfip(socio.getSocioPK().getCodigo(), ActivacionUsuario.getCodigoIfip());
        int rangoEnDias = this.mesesMaxUltimaActualizacion * 30;
        if (this.socioSituacionPatrimonial == null) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformacionPatrimonilaSocioDesactualizada"));
        } else {
            long diferenciaEn_ms = new Date().getTime() - this.socioSituacionPatrimonial.getFechaActualizacion().getTime();
            long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
            if (dias > rangoEnDias) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformacionPatrimonilaSocioDesactualizada"));
            }
        }

        long diferenciaEn_ms = new Date().getTime() - socio.getFechaActualizacion().getTime();
        long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
        if (dias > rangoEnDias) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformacionSocioDesactualizada"));
        }
        renovacionAutomatica = true;
    }

    public void guardaContrato(ActionEvent actionEvent) {
        if (!validaSumaPorcentaje()) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("sumaPorcentajeIncorrecta"));
            return;
        }
        this.setFechaContrato(new java.sql.Timestamp(contrato.getFechaContrato().getTime()));
        this.setFechaVencimiento(new java.sql.Timestamp(contrato.getFechaVencimiento().getTime()));

        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (isEditaContrato()) {
                if (this.editaContrato()) {
                    if (this.guardaDependientes()) {
                        this.guardaBeneficiarios();
                    }

                }
            } else {
                if (this.guardaContrato()) {
                    if (this.guardaDependientes()) {
                        this.guardaBeneficiarios();
                    }

                }
            }

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContratoSeguroGrabado");
                MuestraMensaje.addInformacion(msg);
                tipoContrto = contrato.getTipo();
                context.execute("procesandoDlg.hide()");
                context.execute("ContratoSegCreateDialog.hide()");
                context.execute("ImprimeComprobanteSeguroDialogo.show()");
                this.init();
                //this.nuevo(actionEvent);

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
                    new Object[]{"ContratoSeguroBean", "guardaContrato", CapturaError.getErrorException(ex)});
        }

    }

    private boolean validaSumaPorcentaje() {
        double beneficioTotal = 0;
        for (ContratoSeguroBeneficiario b : itemsBeneficiarios) {
            beneficioTotal = beneficioTotal + b.getPorcentaje().doubleValue();
        }
        if (beneficioTotal == 100) {
            return true;
        } else {
            return false;
        }
    }

    public void muestraVentanaImprimir() {
        System.out.println(contratoSeleccionado);
        if (contratoSeleccionado != null) {
            System.out.println("Imprimir");
            codigoContrato = contratoSeleccionado.getCodigo();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("ImprimeComprobanteSeguroDialogo.show()");
        }
    }

    private boolean guardaContrato() {
        llamaSP.setNombreSP("mks_seguros.pkm_contrato_seguro.p_registra_seguro");
        llamaSP.setNumeroParametros(17);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", contrato.getSocio().getSocioPK().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_valor", contrato.getCodigoValor().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_contrato", getFechaContrato()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_vencimiento", getFechaVencimiento()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", getFechaContrato()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_feche_actualizacion", getFechaContrato()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", "V"});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", null});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", contrato.getCuenta().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_crea", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_actualiza", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_eliminado", "N"});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "A"});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_renovacion_automatica", renovacionAutomatica ? "S" : "N"});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_contrato", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.contrato.setCodigo(Long.parseLong(llamaSP.getListResultado().get(0).toString()));
            System.out.println("Codigo Contrato " + (Long.parseLong(llamaSP.getListResultado().get(0).toString())));
            codigoContrato = Long.parseLong(llamaSP.getListResultado().get(0).toString());
            tipoContrto = 'V';
        }

        return llamaSP.isEjecucionCorrecta();
    }

    private boolean editaContrato() {
        llamaSP.setNombreSP("mks_seguros.pkm_contrato_seguro.p_actualza_seguro");
        llamaSP.setNumeroParametros(5);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_vencimiento", new java.sql.Timestamp(contrato.getFechaVencimiento().getTime())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_feche_actualizacion", new java.sql.Timestamp(new Date().getTime())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", contrato.getCodigoCuenta()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_actualiza", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_contrato", contrato.getCodigo()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            codigoContrato = contrato.getCodigo();
        }

        return llamaSP.isEjecucionCorrecta();
    }

    private boolean guardaDependientes() {
        //System.out.println("Guarda punto 1");
        llamaSP.setNombreSP("mks_seguros.pkm_contrato_seguro.p_registra_dependiente");
        llamaSP.setNumeroParametros(7);
        // Insertando detalle de comprobante 
        //System.out.println("Guarda punto 2");
        //System.out.println(this.getItemsComprobanteDetalle());

        for (ContratoSeguroDependientes item : this.getItemsDependientes()) {
            //System.out.println("Guarda detalle "+item);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            //System.out.println("p_codigo_comprobante"+ this.getComprobante().getCodigo());
            //System.out.println("p_cuenta_contable"+ item.getComprobanteDetallePK().getCuentaContable());
            //System.out.println("p_codigo_ifip"+ ActivacionUsuario.getCodigoIfip());
            //System.out.println("p_tipo"+ String.valueOf(item.getTipo()));
            //System.out.println("p_valor"+ item.getValor());
            //System.out.println("p_linea"+ linea);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_seguro", contrato.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", item.getDependiente().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_relacion", item.getTipoRelacion().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_nombre_completo", item.getNombreCompleto()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_nacimiento", new java.sql.Timestamp(item.getFechaNacimiento().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_actualizacion", new java.sql.Timestamp(item.getFechaActualizacion().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_eliminado", "N"});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            //System.out.println("ANTES registrar detalle");
            llamaSP.invocaSP();
            //System.out.println("DESPUES registrar detalle");

            // Verificando si el registro fue correcto
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
            //System.out.println("Guardo detalle");
        }
        return llamaSP.isEjecucionCorrecta();
    }

    private boolean guardaBeneficiarios() {
        //System.out.println("Guarda punto 1");
        llamaSP.setNombreSP("mks_seguros.pkm_contrato_seguro.p_registra_beneficiario");
        llamaSP.setNumeroParametros(5);
        // Insertando detalle de comprobante 
        //System.out.println("Guarda punto 2");
        //System.out.println(this.getItemsComprobanteDetalle());
        System.out.println("Beneficiarios " + getItemsBeneficiarios().size());
        for (ContratoSeguroBeneficiario item : this.getItemsBeneficiarios()) {
            //System.out.println("Guarda detalle "+item);
            System.out.println("Beneficiario " + item.getBeneficiario().getNombreCompleto());
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            //System.out.println("p_codigo_comprobante"+ this.getComprobante().getCodigo());
            //System.out.println("p_cuenta_contable"+ item.getComprobanteDetallePK().getCuentaContable());
            //System.out.println("p_codigo_ifip"+ ActivacionUsuario.getCodigoIfip());
            //System.out.println("p_tipo"+ String.valueOf(item.getTipo()));
            //System.out.println("p_valor"+ item.getValor());
            //System.out.println("p_linea"+ linea);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_seguro", contrato.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", item.getBeneficiario().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_relacion", item.getTipoRelacion().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_porcentaje", item.getPorcentaje()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_eliminado", "N"});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            //System.out.println("ANTES registrar detalle");
            llamaSP.invocaSP();
            //System.out.println("DESPUES registrar detalle");

            // Verificando si el registro fue correcto
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
            //System.out.println("Guardo detalle");
        }
        return llamaSP.isEjecucionCorrecta();
    }

    public void agregarDependiente(ActionEvent actionEvent) throws IOException {
        if ( tipoRelacionDependiente == null || fechaNacimientoDependiente==null ||nombreDependiente==null ||"".equals(nombreDependiente.trim())) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseInformcaionPersona"));
            return;
        }

        if (calcularEdad(fechaNacimientoDependiente) > 21 && tipoRelacionDependiente.getCodigo() == 10) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("dependienteMayorEdad"));
            return;
        }
        if (itemsDependientes != null) {
            int numHijos = 0;
            int conyuge = 0;
            if (tipoRelacionDependiente.getCodigo() == 10) {
                numHijos++;//numero hijos aumenta 1
            }
            if (tipoRelacionDependiente.getCodigo() == 4) {
                conyuge++;//numero conyuge aumenta 1
            }
            for (ContratoSeguroDependientes d : itemsDependientes) {
                if (d.getTipoRelacion().getCodigo() == 10) {
                    numHijos++;//numero hijos aumenta 1
                }
                if (d.getTipoRelacion().getCodigo() == 4) {
                    conyuge++;//numero conyuge aumenta 1
                }
            }
            if (numHijos > 4) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("maximoNumeroHijosDependientes"));
                return;
            }
            if (conyuge > 1) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("maximoNumeroConyugeDependiente"));
                return;
            }
        }
        ContratoSeguroDependientes dep = new ContratoSeguroDependientes();
        dep.setDependiente(personaDependiente);
        dep.setContratoSeguro(contrato);
        dep.setTipoRelacion(tipoRelacionDependiente);
        dep.setFechaActualizacion(new Date());
        dep.setNombreCompleto(nombreDependiente);
        dep.setFechaNacimiento(fechaNacimientoDependiente);
//        for (ContratoSeguroDependientes d : itemsDependientes) {
//            System.out.println("Dep "+dep.getDependiente().getCodigo());
//            if (dep.getDependiente().getCodigo()!=null)
//            if (d.getDependiente().getCodigo().longValue() == dep.getDependiente().getCodigo().longValue()) {
//                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaYaAgregada"));
//                return;
//            }
//        }
        itemsDependientes.add(dep);
        personaDependiente = new Persona();
        tipoRelacionDependiente = null;
        nombreDependiente="";
        fechaNacimientoDependiente=null;
    }

    public void agregarBeneficiario(ActionEvent actionEvent) throws IOException {
        if (personaBeneficiario.getCodigo() == null || tipoRelacionBeneficiario == null || porcentajeBeneficio == null) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("dependienteMayorEdad"));
            return;
        }
        if (porcentajeBeneficio.doubleValue() < 0 || porcentajeBeneficio.doubleValue() > 100) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("prcentajeFueraRango"));
            return;
        }
        ContratoSeguroBeneficiario ben = new ContratoSeguroBeneficiario();
        ben.setBeneficiario(personaBeneficiario);
        ben.setContratoSeguro(contrato);
        ben.setTipoRelacion(tipoRelacionBeneficiario);
        ben.setPorcentaje(porcentajeBeneficio);
        double sumaBeneficio = porcentajeBeneficio.doubleValue();
        System.out.println("porcentaje a agregar " + sumaBeneficio);
        for (ContratoSeguroBeneficiario d : itemsBeneficiarios) {
            sumaBeneficio = sumaBeneficio + d.getPorcentaje().doubleValue();
            System.out.println("suma " + sumaBeneficio);
            if (d.getBeneficiario().getCodigo().longValue() == ben.getBeneficiario().getCodigo().longValue()) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaYaAgregada"));
                return;
            }
        }
        if (sumaBeneficio > 100) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("porcentajeDependientesMayor100"));
            return;
        }
        itemsBeneficiarios.add(ben);
        personaBeneficiario = new Persona();
        tipoRelacionBeneficiario = null;
        porcentajeBeneficio = null;
    }

    public void obtieneSocios() {
        if (Validaciones.cumpleRequerimientoCampo(this.getNombreSocioBusqueda(), 6)) {
            socioSeleccionado = null;
            this.setItemsSocio(this.ejbFacadeSocio.getItemsNombresIndicaActivo(this.getNombreSocioBusqueda(), ActivacionUsuario.getCodigoIfip(), 'S'));

        }
    }

    public void obtienePersonas() {
        if (Validaciones.cumpleRequerimientoCampo(this.nombrePersonaBusqueda, 6)) {
            this.setPersonaBusqueda(null);
            this.setItemsPersonaBuisqueda(this.ejbFacadePersonaNatural.getItemsLikeNombre(this.nombrePersonaBusqueda));

        } else {
            this.setItemsPersonaBuisqueda(null);
        }
    }

    public void seleccionaPersonaBusqueda() {
        System.out.println("Persona " + personaBusqueda);
        System.out.println("Dependinte " + isBusquedaDependiente());
        if (isBusquedaDependiente()) {
            setPersonaDependiente(personaBusqueda.getPersona());
        } else {
            setPersonaBeneficiario(personaBusqueda.getPersona());
        }
    }

    public void seleccionaSocio() {
        if (socioSeleccionado != null) {
            if (!(ejbFacade.getItemsSocioNoEstado(socioSeleccionado.getSocioPK().getCodigo(),
                    'F',
                    'N',
                    ActivacionUsuario.getCodigoIfip()).isEmpty())) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("socioYaContratoSeguro"));
                return;
            }
            codigoSocio = socioSeleccionado.getSocioPK().getCodigo();
            contrato.setSocio(socioSeleccionado);
            datosActualizados(socioSeleccionado);
            this.setItemsCuentasAhorrosVista(this.ejbFacadeCuenta.getItemsPuedeReactivarAhorros(contrato.getSocio().getSocioPK().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'S', Long.parseLong("2")));
        } else {
            this.setItemsCuentasAhorrosVista(null);
        }
    }

    public void preparaContratoNuevo() {
        codigoSocio = null;
        inicializaCampos();
        setEditaContrato(false);
        setContratoPorCredito(false);
    }

    public void preparaEditaContrato() {
        if (contratoSeleccionado != null) {
            setEditaContrato(true);
            codigoSocio = contratoSeleccionado.getSocio().getSocioPK().getCodigo();
            contrato = contratoSeleccionado;
            setPersonaBeneficiario(new Persona());
            setPersonaDependiente(new Persona());
            itemsBeneficiarios = ejbFacadeContratoSeguroBeneficiario.getItemsCodContrato(contratoSeleccionado.getCodigo(), 'N');
            itemsDependientes = ejbFacadeContratoSeguroDependientes.getItemsCodContrato(contratoSeleccionado.getCodigo(), 'N');
            if (contratoSeleccionado.getNumeroCredito() == null) {
                setContratoPorCredito(false);
            } else {
                setContratoPorCredito(true);
            }
            this.setItemsCuentasAhorrosVista(this.ejbFacadeCuenta.getItemsPuedeReactivarAhorros(contratoSeleccionado.getSocio().getSocioPK().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'S', Long.parseLong("2")));
        }
    }

    private void inicializaCampos() {
        contrato = new ContratoSeguro();
        contrato.setFechaContrato(new Date());
        Date fechaFin = new Date();
        fechaFin.setYear(fechaFin.getYear() + 1);
        contrato.setFechaVencimiento(fechaFin);
        contrato.setCodigoValor(valorSeguro);
        this.setItemsCuentasAhorrosVista(null);
        setPersonaBeneficiario(new Persona());
        setPersonaDependiente(new Persona());
        itemsBeneficiarios = new ArrayList<ContratoSeguroBeneficiario>();
        itemsDependientes = new ArrayList<ContratoSeguroDependientes>();        
        nombreDependiente="";
        fechaNacimientoDependiente=null;
    }

    public void buscaPersonaDependiente() {
        this.msg = null;
        if (this.getPersonaDependiente().getIdentificacion() == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseIdentificacion");
            MuestraMensaje.addError(msg);
            return;
        }
//        if (Validaciones.validaCedula(this.getPersonaDependiente().getIdentificacion())) {
            Persona persona = this.ejbFacadePersona.getPersona(this.getPersonaDependiente().getIdentificacion());
            if (persona == null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseNombre");
                setNombreDependiente("");
                setFechaNacimientoDependiente(null);
                personaDependiente = new Persona();
                MuestraMensaje.addInformacion(msg);
            } else {
                setPersonaDependiente(persona);
                setNombreDependiente(persona.getNombreCompleto());
                setFechaNacimientoDependiente(persona.getPersonaNatural().getFechaNacimiento());
            }
//        } else {
//            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CedulaIncorrecta");
//            MuestraMensaje.addError(msg);
//
//        }
    }

    public void buscaPersonaBeneficiario() {
        this.msg = null;
        if (this.getPersonaBeneficiario().getIdentificacion() == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseIdentificacion");
            MuestraMensaje.addError(msg);
            return;
        }
        if (Validaciones.validaCedula(this.getPersonaBeneficiario().getIdentificacion())) {
            Persona persona = this.ejbFacadePersona.getPersona(this.getPersonaBeneficiario().getIdentificacion());
            if (persona == null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaNoRegistrada");
                personaBeneficiario = new Persona();
                MuestraMensaje.addInformacion(msg);
            } else {
                setPersonaBeneficiario(persona);
            }
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CedulaIncorrecta");
            MuestraMensaje.addError(msg);

        }
    }

    public void quitaDependienteLista() {
        System.out.println("Quita Dependiente");
        itemsDependientes.remove(dependienteSeleccionado);
        System.out.println("Lista " + itemsDependientes.size());
    }

    public void quitaBeneficiarioLista() {
        System.out.println("Quita Beneficiario");
        itemsBeneficiarios.remove(beneficiarioSeleccionado);
        System.out.println("Lista " + itemsBeneficiarios.size());
    }

    public void imprimirAceptacion(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        System.out.println("IMPRIME ACEPTACION");
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/seguros/contratoSeguro/reporte/";
        ////System.out.println("Imprime el Contrato");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoContratoSeg", codigoContrato);
        System.out.println("Codigo Contrato " + codigoContrato);
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());

        nombreReporte = "aceptacionSeguro";

        getGeneraReporte().exporta("/financiero/seguros/contratoSeguro/reporte/", nombreReporte,
                nombreReporte + codigoContrato + ".pdf",
                "PDF", externalContext, facesContext);
    }

    public void imprimirSolicitudCancelacion(ActionEvent actionEvent) {
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/seguros/contratoSeguro/reporte/";
        ////System.out.println("Imprime el Contrato");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());
        codigoContrato = contratoSeleccionado.getCodigo();
        getGeneraReporte().getParametros().put("codigoContratoSeg", codigoContrato);
        System.out.println("Codigo Contrato " + codigoContrato);
        getGeneraReporte().getParametros().put("codigoIfip", contratoSeleccionado.getCodigoIfip());

        nombreReporte = "solicitudCancelacion";

        getGeneraReporte().exporta("/financiero/seguros/contratoSeguro/reporte/", nombreReporte,
                nombreReporte + codigoContrato + ".pdf",
                "PDF", externalContext, facesContext);
    }

    public void imprimirContrato(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/seguros/contratoSeguro/reporte/";
        ////System.out.println("Imprime el Contrato");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoContratoSeg", codigoContrato);
        System.out.println("Codigo Contrato " + codigoContrato);
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());

        if (tipoContrto == 'C') {
            nombreReporte = "contratoSeguroCredito";
        } else {
            nombreReporte = "contratoSeguroAhorros";
        }

        getGeneraReporte().exporta("/financiero/seguros/contratoSeguro/reporte/", nombreReporte,
                nombreReporte + codigoContrato + ".pdf",
                "PDF", externalContext, facesContext);
    }

    public void imprimirCobertura(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        System.out.println("IMPRIME ACEPTACION");
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/seguros/contratoSeguro/reporte/";
        ////System.out.println("Imprime el Contrato");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoContratoSeg", codigoContrato);
        System.out.println("Codigo Contrato " + codigoContrato);
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());

        nombreReporte = "certificadoCobertura";

        getGeneraReporte().exporta("/financiero/seguros/contratoSeguro/reporte/", nombreReporte,
                nombreReporte + codigoContrato + ".pdf",
                "PDF", externalContext, facesContext);
    }

    public void imprimirSolicitud(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        System.out.println("IMPRIME SOLICITUD");
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/seguros/contratoSeguro/reporte/";
        ////System.out.println("Imprime el Contrato");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoContratoSeg", codigoContrato);
        System.out.println("Codigo Contrato " + codigoContrato);
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());

        nombreReporte = "solicitudAfiliacion";

        getGeneraReporte().exporta("/financiero/seguros/contratoSeguro/reporte/", nombreReporte,
                nombreReporte + codigoContrato + ".pdf",
                "PDF", externalContext, facesContext);
    }

    public void obtieneSociosConSeguro() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(getCriterio(), 6)) {
                this.setItemsContratoSeguro(this.ejbFacade.getItemsSeguro(this.getCriterio(), this.getBuscarPor(), 'S', ActivacionUsuario.getCodigoIfip()));
                if (this.getItemsContratoSeguro().size() == 1) {
                    this.buscarMoraSocio(getItemsContratoSeguro().get(0).getSocio().getSocioPK().getCodigo());
                    this.buscarMoraConyuge(getItemsContratoSeguro().get(0).getSocio().getSocioPK().getCodigo());
                    this.buscarMoraGarante(getItemsContratoSeguro().get(0).getSocio().getCodigoPersona().getCodigo());
                }
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsContratoSeguro(null);
                this.setContratoSeleccionado(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"situacionEconomicaBean", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }

    public void buscarMoraConyuge(Long codigoSocio) {
        Socio socio = ejbFacadeSocio.find(new SocioPK(codigoSocio, ActivacionUsuario.getCodigoIfip()));
        //System.out.println("Busqueda Socio Mora: " + socio);
        if (socio == null) {
            return;
        }
        Persona personaSocio = socio.getCodigoPersona();
        Long codigoPersona = personaSocio.getCodigo();
        if (String.valueOf(personaSocio.getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
            PersonaNatural personaNatural = this.ejbFacadePersonaNatural.find(codigoPersona);
            //System.out.println("personaNatural " + personaNatural + " estado civil " + personaNatural.getCodigoEstadoCivil());
            // Verificando si el estado civil es  casado para recuperar el conyue
            if (personaNatural.getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("2")) == 0 || personaNatural.getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("6")) == 0) {

                // Buscando el conyuge actual                
                List<PersonaConyuge> listaPersonaConyuge = ejbFacadeConyugePersona.getItemsCodigoPersonaElminado(codigoPersona, 'N');

                // Si existe un conyuge definido para el socio                
                if (listaPersonaConyuge.size() == 1) {
                    List<Socio> socios = ejbFacadeSocio.getItemsSociofindByIdentificacion(listaPersonaConyuge.get(0).getPersonaNaturalConyuge().getPersona().getIdentificacion());
                    //System.out.println("Busqueda Socios Mora: " + socios);
                    if (socios.isEmpty()) {
                        return;
                    }
                    Long idSocio = socios.get(0).getSocioPK().getCodigo();
                    //System.out.println("Busqueda idSocio Mora: " + idSocio);
                    List<SolicitudDetalle> moraSocio = ejbFacadeSolicitudDetalle.getItemsDetalleCodigoSocioCodigoIfipMora(idSocio, ActivacionUsuario.getCodigoIfip(), new BigDecimal(0));
                    if (!moraSocio.isEmpty()) {
                        String aux;
                        for (SolicitudDetalle sol : moraSocio) {
                            List<CalculoCuotaPagar> pendientes = this.ejbFacadeCalculoCuotaPagar.getItemsCuotasVencidasCreditoIfip(sol.getSolicitudDetallePK().getNumeroCredito(), sol.getSolicitudDetallePK().getCodigoIfip(), new Date(), 'P');
                            double deuda = 0;
                            for (CalculoCuotaPagar c : pendientes) {
                                deuda = deuda + c.getTotalPago().doubleValue();
                            }
                            Long diasMora = this.ejbFacadeCalculoCuotaPagar.getDiasMoraNumeroIfip(sol.getSolicitudDetallePK().getNumeroCredito(), sol.getSolicitudDetallePK().getCodigoIfip(), 'P');
                            aux = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioConyugeMora") + "\n";
                            aux = aux.replace("$DIAS", String.valueOf(diasMora)).replace("$SUMA", sol.getTotalMora().toPlainString()).replace("$NUMERO", String.valueOf(sol.getSolicitudDetallePK().getNumeroCredito())).replace("$SOCIO", socios.get(0).getCodigoPersona().getNombreCompleto()).replace("$DEUDA", String.valueOf(formatoValor(new BigDecimal(deuda))));
                            MuestraMensaje.addInformacion(aux);
                        }

                    }
                }

            }
        }

    }

    public void buscarMoraSocio(Long codigoSocio) {
        ////VERIFICACIONES DE MORA
        List<SolicitudDetalle> moraSocio = ejbFacadeSolicitudDetalle.getItemsDetalleCodigoSocioCodigoIfipMora(codigoSocio, ActivacionUsuario.getCodigoIfip(), new BigDecimal(0));
        if (!moraSocio.isEmpty()) {
            String aux;
            for (SolicitudDetalle sol : moraSocio) {
                List<CalculoCuotaPagar> pendientes = this.ejbFacadeCalculoCuotaPagar.getItemsCuotasVencidasCreditoIfip(sol.getSolicitudDetallePK().getNumeroCredito(), sol.getSolicitudDetallePK().getCodigoIfip(), new Date(), 'P');
                double deuda = 0;
                for (CalculoCuotaPagar c : pendientes) {
                    deuda = deuda + c.getTotalPago().doubleValue();
                }
                Long diasMora = this.ejbFacadeCalculoCuotaPagar.getDiasMoraNumeroIfip(sol.getSolicitudDetallePK().getNumeroCredito(), sol.getSolicitudDetallePK().getCodigoIfip(), 'P');
                aux = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioTieneMora") + "\n";
                aux = aux.replace("$DIAS", String.valueOf(diasMora)).replace("$SUMA", sol.getTotalMora().toPlainString()).replace("$NUMERO", String.valueOf(sol.getSolicitudDetallePK().getNumeroCredito())).replace("$DEUDA", String.valueOf(formatoValor(new BigDecimal(deuda))));
                MuestraMensaje.addInformacion(aux);
            }

        }
    }

    public void buscarMoraGarante(Long codigoPrsona) {
        ////VERIFICACIONES DE MORA
        List<Solicitud> solicitudes = ejbFacadeGaranteCredito.getItemPersonaIfipVigente(codigoPrsona, ActivacionUsuario.getCodigoIfip(), 'S');
        for (Solicitud sol : solicitudes) {
            List<SolicitudDetalle> moraSocio = ejbFacadeSolicitudDetalle.getItemSolicitudCreditoNumeroIfipMora(sol.getSolicitudPK().getNumero(), ActivacionUsuario.getCodigoIfip(), new BigDecimal(0));
            if (!moraSocio.isEmpty()) {
                String aux;
                for (SolicitudDetalle s : moraSocio) {
                    List<CalculoCuotaPagar> pendientes = this.ejbFacadeCalculoCuotaPagar.getItemsCuotasVencidasCreditoIfip(s.getSolicitudDetallePK().getNumeroCredito(), s.getSolicitudDetallePK().getCodigoIfip(), new Date(), 'P');
                    double deuda = 0;
                    for (CalculoCuotaPagar c : pendientes) {
                        deuda = deuda + c.getTotalPago().doubleValue();
                    }
                    Long diasMora = this.ejbFacadeCalculoCuotaPagar.getDiasMoraNumeroIfip(s.getSolicitudDetallePK().getNumeroCredito(), s.getSolicitudDetallePK().getCodigoIfip(), 'P');
                    aux = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioGaranteMora") + "\n";
                    aux = aux.replace("$DIAS", String.valueOf(diasMora)).replace("$SUMA", s.getTotalMora().toPlainString()).replace("$NUMERO", String.valueOf(s.getSolicitudDetallePK().getNumeroCredito())).replace("$SOCIO", s.getSolicitud().getSocio().getCodigoPersona().getNombreCompleto()).replace("$DEUDA", String.valueOf(formatoValor(new BigDecimal(deuda))));
                    MuestraMensaje.addInformacion(aux);
                }
            }
        }
    }

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

    public void buscaMora() {
        tipoContrto = contratoSeleccionado.getTipo();
        if (this.getContratoSeleccionado() != null) {
            this.buscarMoraSocio(this.getContratoSeleccionado().getSocio().getSocioPK().getCodigo());
            this.buscarMoraConyuge(this.getContratoSeleccionado().getSocio().getSocioPK().getCodigo());
            this.buscarMoraGarante(this.getContratoSeleccionado().getSocio().getCodigoPersona().getCodigo());
        }
    }

    public static int calcularEdad(Date fechaNacimiento) {
        Calendar fechaNac = new GregorianCalendar();
        fechaNac.setTime(fechaNacimiento);
        Calendar today = Calendar.getInstance();
        int diffYear = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int diffMonth = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int diffDay = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
        // Si está en ese año pero todavía no los ha cumplido
        if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
            diffYear = diffYear - 1;
        }
        return diffYear;
    }

    /**
     * @return the ejbFacade
     */
    public ContratoSeguroFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ContratoSeguroFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
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
     * @return the ejbFacadeSocio
     */
    public SocioFacade getEjbFacadeSocio() {
        return ejbFacadeSocio;
    }

    /**
     * @param ejbFacadeSocio the ejbFacadeSocio to set
     */
    public void setEjbFacadeSocio(SocioFacade ejbFacadeSocio) {
        this.ejbFacadeSocio = ejbFacadeSocio;
    }

    /**
     * @return the contrato
     */
    public ContratoSeguro getContrato() {
        return contrato;
    }

    /**
     * @param contrato the contrato to set
     */
    public void setContrato(ContratoSeguro contrato) {
        this.contrato = contrato;
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
     * @return the socioSeleccionado
     */
    public Socio getSocioSeleccionado() {
        return socioSeleccionado;
    }

    /**
     * @param socioSeleccionado the socioSeleccionado to set
     */
    public void setSocioSeleccionado(Socio socioSeleccionado) {
        this.socioSeleccionado = socioSeleccionado;
    }

    /**
     * @return the nombreSocioBusqueda
     */
    public String getNombreSocioBusqueda() {
        return nombreSocioBusqueda;
    }

    /**
     * @param nombreSocioBusqueda the nombreSocioBusqueda to set
     */
    public void setNombreSocioBusqueda(String nombreSocioBusqueda) {
        this.nombreSocioBusqueda = nombreSocioBusqueda;
    }

    /**
     * @return the itemsCuentasAhorrosVista
     */
    public List<Cuenta> getItemsCuentasAhorrosVista() {
        return itemsCuentasAhorrosVista;
    }

    /**
     * @param itemsCuentasAhorrosVista the itemsCuentasAhorrosVista to set
     */
    public void setItemsCuentasAhorrosVista(List<Cuenta> itemsCuentasAhorrosVista) {
        this.itemsCuentasAhorrosVista = itemsCuentasAhorrosVista;
    }

    /**
     * @return the itemsBeneficiarios
     */
    public List<ContratoSeguroBeneficiario> getItemsBeneficiarios() {
        return itemsBeneficiarios;
    }

    /**
     * @param itemsBeneficiarios the itemsBeneficiarios to set
     */
    public void setItemsBeneficiarios(List<ContratoSeguroBeneficiario> itemsBeneficiarios) {
        this.itemsBeneficiarios = itemsBeneficiarios;
    }

    /**
     * @return the itemsDependientes
     */
    public List<ContratoSeguroDependientes> getItemsDependientes() {
        return itemsDependientes;
    }

    /**
     * @param itemsDependientes the itemsDependientes to set
     */
    public void setItemsDependientes(List<ContratoSeguroDependientes> itemsDependientes) {
        this.itemsDependientes = itemsDependientes;
    }

    /**
     * @return the dependienteSeleccionado
     */
    public ContratoSeguroDependientes getDependienteSeleccionado() {
        return dependienteSeleccionado;
    }

    /**
     * @param dependienteSeleccionado the dependienteSeleccionado to set
     */
    public void setDependienteSeleccionado(ContratoSeguroDependientes dependienteSeleccionado) {
        this.dependienteSeleccionado = dependienteSeleccionado;
    }

    /**
     * @return the beneficiarioSeleccionado
     */
    public ContratoSeguroBeneficiario getBeneficiarioSeleccionado() {
        return beneficiarioSeleccionado;
    }

    /**
     * @param beneficiarioSeleccionado the beneficiarioSeleccionado to set
     */
    public void setBeneficiarioSeleccionado(ContratoSeguroBeneficiario beneficiarioSeleccionado) {
        this.beneficiarioSeleccionado = beneficiarioSeleccionado;
    }

    /**
     * @return the itemsTipoRelacion
     */
    public List<TipoRelacion> getItemsTipoRelacionDependiente() {
        return itemsTipoRelacionDependiente;
    }

    /**
     * @param itemsTipoRelacion the itemsTipoRelacion to set
     */
    public void setItemsTipoRelacionDependiente(List<TipoRelacion> itemsTipoRelacionDependiente) {
        this.itemsTipoRelacionDependiente = itemsTipoRelacionDependiente;
    }

    /**
     * @return the nombrePersonaBusqueda
     */
    public String getNombrePersonaBusqueda() {
        return nombrePersonaBusqueda;
    }

    /**
     * @param nombrePersonaBusqueda the nombrePersonaBusqueda to set
     */
    public void setNombrePersonaBusqueda(String nombrePersonaBusqueda) {
        this.nombrePersonaBusqueda = nombrePersonaBusqueda;
    }

    /**
     * @return the itemsPersonaBuisqueda
     */
    public List<PersonaNatural> getItemsPersonaBuisqueda() {
        return itemsPersonaBuisqueda;
    }

    /**
     * @param itemsPersonaBuisqueda the itemsPersonaBuisqueda to set
     */
    public void setItemsPersonaBuisqueda(List<PersonaNatural> itemsPersonaBuisqueda) {
        this.itemsPersonaBuisqueda = itemsPersonaBuisqueda;
    }

    /**
     * @return the personaBusqueda
     */
    public PersonaNatural getPersonaBusqueda() {
        return personaBusqueda;
    }

    /**
     * @param personaBusqueda the personaBusqueda to set
     */
    public void setPersonaBusqueda(PersonaNatural personaBusqueda) {
        this.personaBusqueda = personaBusqueda;
    }

    /**
     * @return the busquedaDependiente
     */
    public boolean isBusquedaDependiente() {
        return busquedaDependiente;
    }

    /**
     * @param busquedaDependiente the busquedaDependiente to set
     */
    public void setBusquedaDependiente(boolean busquedaDependiente) {
        this.busquedaDependiente = busquedaDependiente;
    }

    /**
     * @return the tipoRelacionDependiente
     */
    public TipoRelacion getTipoRelacionDependiente() {
        return tipoRelacionDependiente;
    }

    /**
     * @param tipoRelacionDependiente the tipoRelacionDependiente to set
     */
    public void setTipoRelacionDependiente(TipoRelacion tipoRelacionDependiente) {
        this.tipoRelacionDependiente = tipoRelacionDependiente;
    }

    /**
     * @return the personaBeneficiario
     */
    public Persona getPersonaBeneficiario() {
        return personaBeneficiario;
    }

    /**
     * @param personaBeneficiario the personaBeneficiario to set
     */
    public void setPersonaBeneficiario(Persona personaBeneficiario) {
        this.personaBeneficiario = personaBeneficiario;
    }

    /**
     * @return the personaDependiente
     */
    public Persona getPersonaDependiente() {
        return personaDependiente;
    }

    /**
     * @param personaDependiente the personaDependiente to set
     */
    public void setPersonaDependiente(Persona personaDependiente) {
        this.personaDependiente = personaDependiente;
    }

    /**
     * @return the tipoRelacionBeneficiario
     */
    public TipoRelacion getTipoRelacionBeneficiario() {
        return tipoRelacionBeneficiario;
    }

    /**
     * @param tipoRelacionBeneficiario the tipoRelacionBeneficiario to set
     */
    public void setTipoRelacionBeneficiario(TipoRelacion tipoRelacionBeneficiario) {
        this.tipoRelacionBeneficiario = tipoRelacionBeneficiario;
    }

    /**
     * @return the porcentajeBeneficio
     */
    public BigDecimal getPorcentajeBeneficio() {
        return porcentajeBeneficio;
    }

    /**
     * @param porcentajeBeneficio the porcentajeBeneficio to set
     */
    public void setPorcentajeBeneficio(BigDecimal porcentajeBeneficio) {
        this.porcentajeBeneficio = porcentajeBeneficio;
    }

    /**
     * @return the fechaContrato
     */
    public Timestamp getFechaContrato() {
        return fechaContrato;
    }

    /**
     * @param fechaContrato the fechaContrato to set
     */
    public void setFechaContrato(Timestamp fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    /**
     * @return the fechaVencimiento
     */
    public Timestamp getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Timestamp fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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
     * @return the buscarPor
     */
    public String getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor the buscarPor to set
     */
    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    /**
     * @return the criterio
     */
    public String getCriterio() {
        return criterio;
    }

    /**
     * @param criterio the criterio to set
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    /**
     * @return the itemsContratoSeguro
     */
    public List<ContratoSeguro> getItemsContratoSeguro() {
        return itemsContratoSeguro;
    }

    /**
     * @param itemsContratoSeguro the itemsContratoSeguro to set
     */
    public void setItemsContratoSeguro(List<ContratoSeguro> itemsContratoSeguro) {
        this.itemsContratoSeguro = itemsContratoSeguro;
    }

    /**
     * @return the contratoSeleccionado
     */
    public ContratoSeguro getContratoSeleccionado() {
        return contratoSeleccionado;
    }

    /**
     * @param contratoSeleccionado the contratoSeleccionado to set
     */
    public void setContratoSeleccionado(ContratoSeguro contratoSeleccionado) {
        this.contratoSeleccionado = contratoSeleccionado;
    }

    /**
     * @return the editaContrato
     */
    public boolean isEditaContrato() {
        return editaContrato;
    }

    /**
     * @param editaContrato the editaContrato to set
     */
    public void setEditaContrato(boolean editaContrato) {
        this.editaContrato = editaContrato;
    }

    /**
     * @return the contratoPorCredito
     */
    public boolean isContratoPorCredito() {
        return contratoPorCredito;
    }

    /**
     * @param contratoPorCredito the contratoPorCredito to set
     */
    public void setContratoPorCredito(boolean contratoPorCredito) {
        this.contratoPorCredito = contratoPorCredito;
    }

    /**
     * @return the renovacionAutomatica
     */
    public boolean isRenovacionAutomatica() {
        return renovacionAutomatica;
    }

    /**
     * @param renovacionAutomatica the renovacionAutomatica to set
     */
    public void setRenovacionAutomatica(boolean renovacionAutomatica) {
        this.renovacionAutomatica = renovacionAutomatica;
    }

    /**
     * @return the tipoContrto
     */
    public char isTipoContrto() {
        return tipoContrto;
    }

    /**
     * @param tipoContrto the tipoContrto to set
     */
    public void setTipoContrto(char tipoContrto) {
        this.tipoContrto = tipoContrto;
    }

    /**
     * @return the itemsTipoRelacionBeneficiario
     */
    public List<TipoRelacion> getItemsTipoRelacionBeneficiario() {
        return itemsTipoRelacionBeneficiario;
    }

    /**
     * @param itemsTipoRelacionBeneficiario the itemsTipoRelacionBeneficiario to
     * set
     */
    public void setItemsTipoRelacionBeneficiario(List<TipoRelacion> itemsTipoRelacionBeneficiario) {
        this.itemsTipoRelacionBeneficiario = itemsTipoRelacionBeneficiario;
    }

    /**
     * @return the nombreDependiente
     */
    public String getNombreDependiente() {
        return nombreDependiente;
    }

    /**
     * @param nombreDependiente the nombreDependiente to set
     */
    public void setNombreDependiente(String nombreDependiente) {
        this.nombreDependiente = nombreDependiente;
    }

    /**
     * @return the fechaNacimientoDependiente
     */
    public Date getFechaNacimientoDependiente() {
        return fechaNacimientoDependiente;
    }

    /**
     * @param fechaNacimientoDependiente the fechaNacimientoDependiente to set
     */
    public void setFechaNacimientoDependiente(Date fechaNacimientoDependiente) {
        this.fechaNacimientoDependiente = fechaNacimientoDependiente;
    }

    /**
     * @return the deshabilitaNombreDependiente
     */
    public boolean isDeshabilitaNombreDependiente() {
        return deshabilitaNombreDependiente;
    }

    /**
     * @param deshabilitaNombreDependiente the deshabilitaNombreDependiente to set
     */
    public void setDeshabilitaNombreDependiente(boolean deshabilitaNombreDependiente) {
        this.deshabilitaNombreDependiente = deshabilitaNombreDependiente;
    }

}
