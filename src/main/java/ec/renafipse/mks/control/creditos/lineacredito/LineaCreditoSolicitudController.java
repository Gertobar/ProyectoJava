/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.creditos.lineacredito;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Utilidades;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.auditorias.LineaCreditoSolicitudEstado;
import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import ec.renafipse.mks.modelo.creditos.DestinoEspecifico;
import ec.renafipse.mks.modelo.creditos.DestinoFinanciero;
import ec.renafipse.mks.modelo.creditos.OficialCredito;
import ec.renafipse.mks.modelo.creditos.OrigenRecursos;
import ec.renafipse.mks.modelo.creditos.ParametroGeneralCreditoIfip;
import ec.renafipse.mks.modelo.creditos.ParametroGeneralCreditoIfipPK;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.TasaInteresProCreMonIfi;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCredito;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud;
import ec.renafipse.mks.modelo.ifips.CanalServicioIfip;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.ActividadEconomica;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioFlujoCaja;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonial;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.comunes.UbicacionGeograficaFacade;
import ec.renafipse.mks.negocio.creditos.ActividadEcoProMonIifipFacade;
import ec.renafipse.mks.negocio.creditos.DestinoFinProCreMonIfipFacade;
import ec.renafipse.mks.negocio.creditos.ParametroGeneralCreditoIfipFacade;
import ec.renafipse.mks.negocio.creditos.TasaInteresProCreMonIfiFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoSolicitudFacade;
import ec.renafipse.mks.modelo.creditos.TipoGarantiaProCreMonIfi;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoEstadoSol;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoPlazoMaximo;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolTipGar;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitudGar;
import ec.renafipse.mks.modelo.ifips.EnvioCanalServicioIfip;
import ec.renafipse.mks.modelo.socios.PersonaConyuge;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.negocio.auditorias.LineaCreditoSolicitudEstadoFacade;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import ec.renafipse.mks.negocio.creditos.DestinoEspecificoFacade;
import ec.renafipse.mks.negocio.creditos.GaranteCreditoFacade;
import ec.renafipse.mks.negocio.creditos.OficialCreditoFacade;
import ec.renafipse.mks.negocio.creditos.OrigenRecursosFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudFacade;
import ec.renafipse.mks.negocio.creditos.TipoGarantiaProCreMonIfiFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoEstadoSolFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoPlazoMaximoFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoSolTipGarFacade;
import ec.renafipse.mks.negocio.ifips.CanalServicioIfipFacade;
import ec.renafipse.mks.negocio.ifips.EnvioCanalServicioIfipFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.socios.PersonaConyugeFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.PersonaNaturalFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatrimonialFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import oracle.jdbc.OracleTypes;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author andres
 */
@ManagedBean(name = "lineaCreditoSolicitudController")
@ViewScoped
public class LineaCreditoSolicitudController extends AbstractController<LineaCreditoSolicitud> implements Serializable {

    @EJB
    private LineaCreditoSolicitudFacade ejbFacade;
    @EJB
    private SocioFacade ejbFacadeSocio;
    @EJB
    private LineaCreditoFacade ejbFacadeLineaCredito;
    @EJB
    private IfipFacade ejbFacadeIfip;
    @EJB
    private CanalServicioIfipFacade ejbFacadeCanalServicioIfip;
    @EJB
    private CuentaFacade ejbFacadeCuenta;
    @EJB
    private SocioFlujoCajaFacade ejbFacadeSocioFlujoCaja;
    @EJB
    private SocioSituacionPatrimonialFacade ejbFacadeSocioSituacionPatrimonial;
    @EJB
    private LlamaSP llamaSP;
    @EJB
    private PersonaFacade ejbFacadePersona;
    @EJB
    private ParametroGeneralCreditoIfipFacade ejbFacadeParametroGeneralCreditoIfip;
    @EJB
    private ActividadEcoProMonIifipFacade ejbFacadeActividadEcoProMonIifip;
    @EJB
    private UbicacionGeograficaFacade ejbFacadeUbicacionGeografica;
    @EJB
    private DestinoFinProCreMonIfipFacade ejbFacadeDestinoFinProCreMonIfipFacade;
    @EJB
    private TasaInteresProCreMonIfiFacade ejbFacadeTasaInteresProCreMonIfi;
    @EJB
    private TipoGarantiaProCreMonIfiFacade ejbFacadeTipoGarantiaProCreMonIfi;
    @EJB
    private OrigenRecursosFacade ejbFacadeOrigenRecursos;
    @EJB
    private LineaCreditoEstadoSolFacade ejbLineaCreditoEstadoSolicitud;
    @EJB
    private EnvioCanalServicioIfipFacade ejbEnvioCanalServicioIfip;
    @EJB
    private PersonaNaturalFacade ejbPersonaNatural;
    @EJB
    private PersonaConyugeFacade ejbConyugePersona;
    @EJB
    private GaranteCreditoFacade ejbGaranteCredito;
    @EJB
    private LineaCreditoSolTipGarFacade ejbFacadeCreditoSolicitudTipoGarantia;
    @EJB
    private DestinoEspecificoFacade ejbFacadeDestinoEspecifico;
    @EJB
    private LineaCreditoSolicitudEstadoFacade ejbLineaCreditoSolicitudEstadoFacade;
    @EJB
    private OficialCreditoFacade ejbOficialCreditoFacade;
    @EJB
    private CalculoCuotaPagarFacade ejbCalculoCuotaPagarFacade;
    @EJB
    private SolicitudFacade ejbSolicitudFacade;
    @EJB
    private LineaCreditoPlazoMaximoFacade ejbLineaCreditoPlazoMaximoFacade;

    private String foco;
    private Ifip ifip;
    private Usuario usuario;
    private String criterioBusqueda;
    private String valoresLong;
    private Socio socio;
    private String nombreCompletoSocio;
    private boolean inactivaBusquedaSocioNombre;
    private List<LineaCreditoSolicitud> listaLineaCreditoSolicitud;
    private LineaCreditoSolicitud lineaCreditoSolicitud;
    private String mensaje;
    private boolean edicion;
    private boolean buscaSolicitudes;
    private LineaCredito lineaCredito;
    private List<LineaCredito> listaLineaCredito;
    private List<CanalServicioIfip> listaCanalServicio;
    private CanalServicioIfip canalServicio;
    private List<Cuenta> listaSocioCuenta;
    private SocioFlujoCaja socioFlujoCaja;
    private SocioSituacionPatrimonial socioSituacionPatrimonial;
    private Persona personaConyugeSocio;
    private boolean habilitarBotonEditarConyuge;
    private boolean habilitarBotonEliminarConyuge;
    private String tipoCuentaSolicitudSeleccionada;
    private Cuenta cuentaSolicitudSeleccionada;
    private List<Cuenta> listaSocioCuentasVista;
    private List<LineaCreditoSolicitud> listaCuentasSolicitudAgredadas;
    private int mesesMaxUltimaActualizacion;
    private ParametroGeneralCreditoIfip parametroGeneralCreditoIfip;
    private ActividadEconomica actividadEconomicaN1;
    private ActividadEconomica actividadEconomicaN2;
    private ActividadEconomica actividadEconomicaN3;
    private ActividadEconomica actividadEconomicaN4;
    private ActividadEconomica actividadEconomicaN5;
    private ActividadEconomica actividadEconomicaN6;
    private List<ActividadEconomica> listaActividadEconomicaN1;
    private List<ActividadEconomica> listaActividadEconomicaN2;
    private List<ActividadEconomica> listaActividadEconomicaN3;
    private List<ActividadEconomica> listaActividadEconomicaN4;
    private List<ActividadEconomica> listaActividadEconomicaN5;
    private List<ActividadEconomica> listaActividadEconomicaN6;
    private List<DestinoFinanciero> listaDestinoFinanciero;
    private DestinoFinanciero destinoFinanciero;
    private UbicacionGeografica ubicacionGeograficaPais;
    private UbicacionGeografica ubicacionGeograficaProvincia;
    private UbicacionGeografica ubicacionGeograficaCiudad;
    private UbicacionGeografica ubicacionGeograficaParroquia;
    private List<UbicacionGeografica> listaUbicacionGeograficaPais;
    private List<UbicacionGeografica> listaUbicacionGeograficaProvincia;
    private List<UbicacionGeografica> listaUbicacionGeograficaCiudad;
    private List<UbicacionGeografica> listaUbicacionGeograficaParroquia;
    private List<TasaInteresProCreMonIfi> listaTasaLineaCredito;
    private TasaInteresProCreMonIfi tasaLineaCredito;

    private char tieneGarantias;
    private int garantesRequeridos;
    private boolean garantiasObligatorias;
    private String identificacionGarante;
    private boolean habilitarBotonEditarConyugeGarante;
    private boolean habilitarBotonEliminarConyugeGarante;
    private PersonaNatural personaNaturalGarante;
    private Persona personaConyugeGarante;
    private BigDecimal coberturaGarante;
    private List<LineaCreditoSolicitudGar> listaGaranteLineaCredito;
    private List<LineaCreditoSolicitudGar> listaGaranteLineaCreditoEliminar;
    private int garantiasMaximasPorPersona;
    private List<LineaCreditoSolTipGar> listaLineaCreditoSolicitudTipoGarantia;
    private LineaCreditoSolTipGar lineaCreditoSolicitudTipoGarantia;

    private List<OrigenRecursos> listaOrigenRecursos;
    private boolean procesoExitoso;
    private boolean procesoError;
    private int maximoNivelActividadEconomica;
    private int maximoNivelUbicacionGeografica;
    private LineaCreditoEstadoSol lineaCreditoEstadoSolicitud;
    private EnvioCanalServicioIfip envioCanalServicioIfip;
    private boolean habilitaEdicion;
    private GeneraReporte generaReporte;
    private List<DestinoEspecifico> listaDestinoEspecifico;
            
    @PostConstruct
    public void init() {
        try {
            //Obtener el oficial de credito
            String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
            OficialCredito oficialCredito = ejbOficialCreditoFacade.getOficialCredito(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia());
            if(oficialCredito == null){
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                Sesion.redireccionaPagina(url);
            }
            inicializaPropiedades();
            cargaValoresIniciales();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "init", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cargaValoresIniciales() {
        try {
            String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
            listaUbicacionGeograficaPais = ejbFacadeUbicacionGeografica.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1"));
            listaOrigenRecursos = ejbFacadeOrigenRecursos.getItemsEliminado('N');
            lineaCreditoEstadoSolicitud = ejbLineaCreditoEstadoSolicitud.getLineaCreditoEstadoSolPorCodigo(Long.valueOf("1"));
            // Obteniendo los dias que un socio puede tener desactualizada su informacion
            ParametroGeneralCreditoIfip parametroGeneralCreditoIfip = ejbFacadeParametroGeneralCreditoIfip.find(new ParametroGeneralCreditoIfipPK((long) 2, ActivacionUsuario.getCodigoIfip()));
            if (parametroGeneralCreditoIfip == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaMesesUltimaActualizacion"));
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            }
            if (parametroGeneralCreditoIfip.getValorNumerico() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaMesesUltimaActualizacion"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
            mesesMaxUltimaActualizacion = parametroGeneralCreditoIfip.getValorNumerico().intValue();
            // Obteniendo los numeros de garantias que puede hacer una persona
            parametroGeneralCreditoIfip = ejbFacadeParametroGeneralCreditoIfip.find(new ParametroGeneralCreditoIfipPK((long) 1, ActivacionUsuario.getCodigoIfip()));
            if (parametroGeneralCreditoIfip == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaGarantiasMaximasPorPersona"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
            if (parametroGeneralCreditoIfip.getValorNumerico() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaGarantiasMaximasPorPersona"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
            this.garantiasMaximasPorPersona = parametroGeneralCreditoIfip.getValorNumerico().intValue();
            //procesoExitoso = false;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cargaValoresIniciales", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void inicializaPropiedades() {
        try {
            setFoco("contratoPanel");
            setUsuario(ActivacionUsuario.getUsuario());
            setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
            setCriterioBusqueda("");
            setLineaCredito(null);
            setListaLineaCreditoSolicitud(null);
            setListaLineaCredito(ejbFacadeLineaCredito.getListaLineaCreditoPorIfipVigente(getIfip().getCodigo(), "S"));
            setListaCanalServicio(ejbFacadeCanalServicioIfip.getListaEnvioCanalServicoIfipEliminado(getIfip().getCodigo(), 'N'));
            setListaDestinoEspecifico(ejbFacadeDestinoEspecifico.getListaEliminado("N"));
            setPersonaConyugeSocio(null);
            setHabilitarBotonEditarConyuge(false);
            setHabilitarBotonEliminarConyuge(false);
            setListaSocioCuenta(null);
            setListaSocioCuentasVista(null);
            setListaCuentasSolicitudAgredadas(null);
            setNombreCompletoSocio("");
            setSocioFlujoCaja(null);
            setSocioSituacionPatrimonial(null);
            setTipoCuentaSolicitudSeleccionada(null);
            setParametroGeneralCreditoIfip(ejbFacadeParametroGeneralCreditoIfip.find(new ParametroGeneralCreditoIfipPK((long) 2, ActivacionUsuario.getCodigoIfip())));
            setMesesMaxUltimaActualizacion(getParametroGeneralCreditoIfip().getValorNumerico().intValue());
            setListaGaranteLineaCredito(null);
            if (isProcesoExitoso()) {
                MuestraMensaje.addSatisfactorioPersistencia(1);
                setListaLineaCreditoSolicitud(ejbFacade.getListaLineaCreditoSolicitudPorCodigoSocio(getSocio().getSocioPK().getCodigo()));
                if (getListaLineaCreditoSolicitud() != null) {
                    if (getListaLineaCreditoSolicitud().size() <= 0) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                    } else {
                        setSocio(ejbFacadeSocio.getSocioPorCodigo(getListaLineaCreditoSolicitud().get(0).getCodigoSocio()));
                        setNombreCompletoSocio(getSocio().getCodigoPersona().getNombreCompleto());
                    }
                } else {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                }
            }
            if (isProcesoError()) {
                MuestraMensaje.addErrorCapaControl();
                setListaLineaCreditoSolicitud(ejbFacade.getListaLineaCreditoSolicitudPorCodigoSocio(getSocio().getSocioPK().getCodigo()));
                if (getListaLineaCreditoSolicitud() != null) {
                    if (getListaLineaCreditoSolicitud().size() <= 0) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                    } else {
                        setSocio(ejbFacadeSocio.getSocioPorCodigo(getListaLineaCreditoSolicitud().get(0).getCodigoSocio()));
                        setNombreCompletoSocio(getSocio().getCodigoPersona().getNombreCompleto());
                    }
                } else {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                }
            }
            setProcesoExitoso(false);
            setProcesoError(false);
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "inicializaPropiedades", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Busca solicitudes de linea de crédito por tipo y criterio de busqueda
     *
     */
    public void obtieneSolicitudesDeLinea() {
        try {
            setSocio(null);
            setNombreCompletoSocio("");
            setListaLineaCreditoSolicitud(null);
            setHabilitarBotonEliminarConyuge(false);
            setHabilitarBotonEditarConyuge(false);
            setPersonaConyugeSocio(null);
            setListaSocioCuenta(null);
            setListaSocioCuentasVista(null);
            setListaCuentasSolicitudAgredadas(null);
            setSocioFlujoCaja(null);
            setSocioSituacionPatrimonial(null);
            setTipoCuentaSolicitudSeleccionada(null);
            if (getCriterioBusqueda() == null) {
                MuestraMensaje.addAdvertencia("Criterio consulta " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido") + " Para Busqueda");
                return;
            } else {
                if (getCriterioBusqueda().length() <= 0) {
                    MuestraMensaje.addAdvertencia("Criterio consulta " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido") + " Para Busqueda");
                    return;
                }
            }
            setListaLineaCreditoSolicitud(ejbFacade.getListaLineaCreditoSolicitudPorCodigo(Long.valueOf(getCriterioBusqueda())));
            if (getListaLineaCreditoSolicitud() != null) {
                if (getListaLineaCreditoSolicitud().size() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                } else {
                    setSocio(ejbFacadeSocio.getSocioPorCodigo(getListaLineaCreditoSolicitud().get(0).getCodigoSocio()));
                    setNombreCompletoSocio(getSocio().getCodigoPersona().getNombreCompleto());
                }
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "obtieneSolicitudesDeLinea", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Carga el formulario para la busqueda de un socio por nombre
     *
     */
    public void obtieneSolicitudesDeLineaDesdeComponente() {
        try {
            setCriterioBusqueda("");
            setSocio(null);
            setNombreCompletoSocio("");
            setListaLineaCreditoSolicitud(null);
            setHabilitarBotonEliminarConyuge(false);
            setHabilitarBotonEditarConyuge(false);
            setPersonaConyugeSocio(null);
            setListaSocioCuenta(null);
            setListaSocioCuentasVista(null);
            setListaCuentasSolicitudAgredadas(null);
            setSocioFlujoCaja(null);
            setSocioSituacionPatrimonial(null);
            setTipoCuentaSolicitudSeleccionada(null);
            setValoresLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("componenteSocioForm:componenteBusca:listaSociosComponenteForm:buscaSocioComponenteCodigoPersona"));

            if (getValoresLong() == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            } else {
                if (getValoresLong().length() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                    return;
                }
            }
            if ((Long.valueOf(getValoresLong()).compareTo(Long.valueOf("0")) == 0)) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            setValoresLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("componenteSocioForm:componenteBusca:listaSociosComponenteForm:buscaSocioComponenteCodigoSocio"));
            if (getValoresLong() == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            } else {
                if (getValoresLong().length() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                    return;
                }
            }
            if ((Long.valueOf(getValoresLong()).compareTo(Long.valueOf("0")) == 0)) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            setSocio(ejbFacadeSocio.getSocioPorCodigo(Long.valueOf(getValoresLong())));
            if (getSocio() == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            } else {
                setNombreCompletoSocio(getSocio().getCodigoPersona().getNombreCompleto());
            }

            if (isBuscaSolicitudes()) {
                setListaLineaCreditoSolicitud(ejbFacade.getListaLineaCreditoSolicitudPorCodigoSocio(Long.valueOf(getValoresLong())));
                if (getListaLineaCreditoSolicitud() != null) {
                    if (getListaLineaCreditoSolicitud().size() <= 0) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                    } else {
                        setSocio(ejbFacadeSocio.getSocioPorCodigo(getListaLineaCreditoSolicitud().get(0).getCodigoSocio()));
                        setNombreCompletoSocio(getSocio().getCodigoPersona().getNombreCompleto());
                    }
                } else {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                }
            } else {
                cargarCuentasSocio();
                cargarCuentasVistaSocio();

                cargarFlujoSocio();
                cargarSituacionPatrimonialSocio();

                cargarPersonaConyuge();
                setFoco("linea");
                lineaCreditoSolicitud.setSocio(getSocio());
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "obtieneSolicitudesDeLineaDesdeComponente", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Cambia valor de la variable para que busque lineas despues de seleccionar
     * el socio
     */
    public void buscaSolicitudesDesdeComponente() {
        try {
            setBuscaSolicitudes(true);
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "buscaSolicitudesDesdeComponente", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Cambia valor de la variable para que no busque lineas despues de
     * seleccionar el socio desde la pantalla de crear solicitud
     */
    public void noBuscaSolicitudesDesdeComponente() {
        try {
            setBuscaSolicitudes(false);
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "buscaSolicitudesDesdeComponente", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * crea una nueva solicitud de linea de crédito
     */
    public void nuevo() {
        try {
            setNombreCompletoSocio("");
            setSocio(null);
            setLineaCreditoSolicitud(new LineaCreditoSolicitud());
            lineaCreditoSolicitudTipoGarantia = new LineaCreditoSolTipGar(ActivacionUsuario.getCodigoUsuario(), new java.sql.Timestamp(new Date().getTime()), 'N', lineaCreditoSolicitud, Long.valueOf("1"));
            edicion = false;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "nueva", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void edicion() {
        try {
            if (habilitaEdicion) {
                edicion = true;
                setLineaCreditoSolicitud(getLineaCreditoSolicitud());
                setSocio(ejbFacadeSocio.getSocioPorCodigo(lineaCreditoSolicitud.getCodigoSocio()));
                setNombreCompletoSocio(getSocio().getCodigoPersona().getNombreCompleto());
                setLineaCredito(getLineaCreditoSolicitud().getLineaCredito());
                setCanalServicio(lineaCreditoSolicitud.getEnvioCanalServicioIfip().getCodigoCanalServicioIfip());
                setEnvioCanalServicioIfip(lineaCreditoSolicitud.getEnvioCanalServicioIfip());
                listaTasaLineaCredito = ejbFacadeTasaInteresProCreMonIfi.getListaTasaInteresProCreMonIfi(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N');
                setTasaLineaCredito(listaTasaLineaCredito.get(0));
                listaDestinoFinanciero = ejbFacadeDestinoFinProCreMonIfipFacade.getItemsDestinoFinancieroPorProducto(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip());
                lineaCreditoSolicitudTipoGarantia = ejbFacadeCreditoSolicitudTipoGarantia.getLineaCreditoSolTipGarPorLineaCreditoSolicitudCodigoTipoGarantia(getLineaCreditoSolicitud().getCodigo(), Long.valueOf("1"));
                listaGaranteLineaCredito = getLineaCreditoSolicitud().getListaLineaCreditoSolicitudTipoGarantia().get(0).getListaLineaCreditoSolicitudGarantia();
                setDestinoFinanciero(lineaCreditoSolicitud.getDestinoFinanciero());
                cargarCuentasSocio();
                cargarCuentasVistaSocio();
                setTipoCuentaSolicitudSeleccionada("D");
                setCuentaSolicitudSeleccionada(lineaCreditoSolicitud.getCuentaDebito());
                agregaCuentaSolicitud();
                setTipoCuentaSolicitudSeleccionada("C");
                setCuentaSolicitudSeleccionada(lineaCreditoSolicitud.getCuentaAcreditada());
                agregaCuentaSolicitud();
                cargarFlujoSocio();
                cargarSituacionPatrimonialSocio();
                cargarPersonaConyuge();
                listaActividadEconomicaN1 = ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelEliminado(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 1, 'C', 'N');
                cargarActividadEconomicaLineaSolicitud();
                listaUbicacionGeograficaPais = ejbFacadeUbicacionGeografica.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1"));
                cargarUbicacionGeograficaLineaSolicitud();
                setFoco("nombreSocioSolicitud");
                seleccionaTasaPorMonto();
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSePuedeEditarSolicitudDiferenteSolicitada"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "edicion", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cargarCuentasSocio() {
        try {
            setListaSocioCuenta(ejbFacadeCuenta.getItemsCodigoSocioCodigoIfipCodigoEstado(getSocio().getSocioPK().getCodigo(), getSocio().getSocioPK().getCodigoIfip(), Long.valueOf("1")));
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cargarCuentasSocio", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cargarCuentasVistaSocio() {
        try {
            setListaSocioCuentasVista(ejbFacadeCuenta.getItemsPuedeReactivarAhorros(getSocio().getSocioPK().getCodigo(), getSocio().getSocioPK().getCodigoIfip(), 'S', Long.parseLong("2")));
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cargarCuentasVistaSocio", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cargarFlujoSocio() {
        try {
            setSocioFlujoCaja(ejbFacadeSocioFlujoCaja.find(socio.getCodigoPersona().getCodigo()));
            if (getSocioFlujoCaja() != null) {
                getSocioFlujoCaja().setCobertura(Utilidades.formatoValorPorSeparador(getSocioFlujoCaja().getCobertura()));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cargarFlujoSocio", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cargarSituacionPatrimonialSocio() {
        try {
            setSocioSituacionPatrimonial(ejbFacadeSocioSituacionPatrimonial.find(socio.getCodigoPersona().getCodigo()));
            if (getSocioSituacionPatrimonial() != null) {
                getSocioSituacionPatrimonial().setTotalPatrimonio(Utilidades.formatoValorPorSeparador(getSocioSituacionPatrimonial().getTotalPatrimonio()));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cargarSituacionPatrimonialSocio", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cargarPersonaConyuge() {
        try {
            setHabilitarBotonEliminarConyuge(false);
            setHabilitarBotonEditarConyuge(false);
            setPersonaConyugeSocio(null);
            setTipoCuentaSolicitudSeleccionada(null);
            if (getSocio() != null) {
                llamaSP.cargaConexion();
                llamaSP.setCerrarConexion(false);
                llamaSP.autoCommit(false);
                llamaSP.setNombreSP("mks_socios.pkm_persona_conyuge.p_obtiene_conyuge_persona");
                llamaSP.setNumeroParametros(2);
                llamaSP.setListParametrosEntrada(new ArrayList<>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_identificacion", getSocio().getCodigoPersona().getIdentificacion()});
                llamaSP.setListParametrosSalida(new ArrayList<>());
                llamaSP.getListParametrosSalida().add(new Object[]{"pt_persona_conyuge", OracleTypes.CURSOR});
                llamaSP.invocaSP();
                if (llamaSP.isEjecucionCorrecta()) {
                    ResultSet resultSet = (ResultSet) llamaSP.getListResultado().get(0);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            setPersonaConyugeSocio(new Persona(resultSet.getLong("codigo"),
                                    resultSet.getString("identificacion"),
                                    resultSet.getString("nombre_completo"),
                                    resultSet.getDate("fecha_ingreso"),
                                    resultSet.getDate("fecha_actualizacion")));
                            setHabilitarBotonEliminarConyuge(true);
                            setHabilitarBotonEditarConyuge(false);
                            break;
                        }
                    } else {
                        setPersonaConyugeSocio(null);
                        setHabilitarBotonEditarConyuge(false);
                    }
                } else {
                    setHabilitarBotonEditarConyuge(false);
                    setHabilitarBotonEliminarConyuge(false);
                }
            } else {
                setHabilitarBotonEditarConyuge(false);
                setHabilitarBotonEliminarConyuge(false);
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cargarPersonaConyuge", CapturaError.getErrorException(ex)});
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
    public void guardarPersonaConyuge() {
        try {
            if (getSocio() != null) {
                if (getPersonaConyugeSocio() != null) {
                    llamaSP.cargaConexion();
                    llamaSP.setCerrarConexion(false);
                    llamaSP.autoCommit(false);
                    llamaSP.setNombreSP("mks_socios.pkm_persona_conyuge.p_guarda_conyuge");
                    llamaSP.setNumeroParametros(3);
                    llamaSP.setListParametrosEntrada(new ArrayList<>());
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", getSocio().getCodigoPersona().getCodigo()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona_conyuge", getPersonaConyugeSocio().getCodigo()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
                    llamaSP.setListParametrosSalida(new ArrayList<>());
                    llamaSP.invocaSP();
                    if (llamaSP.isEjecucionCorrecta()) {
                        setHabilitarBotonEditarConyuge(false);
                        setHabilitarBotonEliminarConyuge(true);
                        llamaSP.commit();
                        MuestraMensaje.addSatisfactorioPersistencia(1);
                    } else {
                        setHabilitarBotonEditarConyuge(false);
                        setHabilitarBotonEliminarConyuge(false);
                        setPersonaConyugeSocio(null);
                        llamaSP.rollback();
                        MuestraMensaje.addErrorCapaControl();
                    }
                } else {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneConyuge"));
                }
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            setHabilitarBotonEditarConyuge(false);
            setHabilitarBotonEliminarConyuge(false);
            setPersonaConyugeSocio(null);
            if (llamaSP.getConexionBD() != null) {
                llamaSP.rollback();
            }
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "guardarPersonaConyuge", CapturaError.getErrorException(ex)});
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
    public void eliminarPersonaConyuge() {
        try {
            if (getSocio() != null) {
                llamaSP.cargaConexion();
                llamaSP.setCerrarConexion(false);
                llamaSP.autoCommit(false);
                llamaSP.setNombreSP("mks_socios.pkm_persona_conyuge.p_guarda_conyuge");
                llamaSP.setNumeroParametros(3);
                llamaSP.setListParametrosEntrada(new ArrayList<>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", getSocio().getCodigoPersona().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona_conyuge", Long.valueOf("0")});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.setListParametrosSalida(new ArrayList<>());
                llamaSP.invocaSP();
                if (llamaSP.isEjecucionCorrecta()) {
                    setHabilitarBotonEditarConyuge(false);
                    setHabilitarBotonEliminarConyuge(false);
                    setPersonaConyugeSocio(null);
                    llamaSP.commit();
                    MuestraMensaje.addSatisfactorioPersistencia(1);
                } else {
                    setHabilitarBotonEditarConyuge(true);
                    setHabilitarBotonEliminarConyuge(false);
                    setPersonaConyugeSocio(null);
                    llamaSP.rollback();
                    MuestraMensaje.addErrorCapaControl();
                }
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
            }
        } catch (Exception ex) {
            setHabilitarBotonEditarConyuge(true);
            setHabilitarBotonEliminarConyuge(false);
            setPersonaConyugeSocio(null);
            MuestraMensaje.addErrorCapaControl();
            if (llamaSP.getConexionBD() != null) {
                llamaSP.rollback();
            }
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "eliminarPersonaConyuge", CapturaError.getErrorException(ex)});
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
    public void cargaPersonaConyugeSocioComponente() {
        try {
            setPersonaConyugeSocio(null);
            setValoresLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("componentePersonaForm:componenteBuscarPersona:listaPersonasComponenteForm:buscaPersonaComponenteCodigoPersona"));
            if (getValoresLong() == null) {
                setHabilitarBotonEditarConyuge(false);
                return;
            } else {
                if (getValoresLong().length() <= 0) {
                    setHabilitarBotonEditarConyuge(false);
                    return;
                }
            }
            if ((Long.valueOf(getValoresLong()).compareTo(Long.valueOf("0")) == 0)) {
                setHabilitarBotonEditarConyuge(false);
                return;
            }
            setPersonaConyugeSocio(ejbFacadePersona.getPersonaByCodigo(Long.valueOf(getValoresLong())));
            if (getPersonaConyugeSocio() == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaNoEncontrada"));
                setHabilitarBotonEditarConyuge(false);
            } else {
                setFoco("nombreConyuge");
                setHabilitarBotonEditarConyuge(true);
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cargaPersonaConyugeSocioComponente", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaLineaCredito() {
        try {
            if (lineaCredito != null) {
                if (lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito() != null) {
                    listaActividadEconomicaN1 = ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelEliminado(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 1, 'C', 'N');
                    listaDestinoFinanciero = ejbFacadeDestinoFinProCreMonIfipFacade.getItemsDestinoFinancieroPorProducto(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip());
                    listaTasaLineaCredito = ejbFacadeTasaInteresProCreMonIfi.getListaTasaInteresProCreMonIfi(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N');
                } else {
                    listaActividadEconomicaN1 = null;
                    listaActividadEconomicaN2 = null;
                    listaActividadEconomicaN3 = null;
                    listaActividadEconomicaN4 = null;
                    listaActividadEconomicaN5 = null;
                    listaActividadEconomicaN6 = null;
                    actividadEconomicaN1 = null;
                    actividadEconomicaN2 = null;
                    actividadEconomicaN3 = null;
                    actividadEconomicaN4 = null;
                    actividadEconomicaN5 = null;
                    actividadEconomicaN6 = null;
                    listaDestinoFinanciero = null;
                    listaTasaLineaCredito = null;
                    tasaLineaCredito = null;
                }
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cambiaLineaCredito", CapturaError.getErrorException(ex)});
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
            //Valida la primera etapa que es el paso de la contratacion a la negociacion
            if ((event.getOldStep().equals("contrato")) && (event.getNewStep().equals("negociacion"))) {
                if (!validaContrato()) {
                    return "contrato";
                }
            }
            if ((event.getOldStep().equals("negociacion")) && (event.getNewStep().equals("garantes"))) {
                if (!validaNegociacion()) {
                    return "negociacion";
                }
            }
            if ((event.getOldStep().equals("garantes")) && (event.getNewStep().equals("confirmacion"))) {
                if (!validaGarantes()) {
                    return "garantes";
                }
            }
            return event.getNewStep();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "flujoProceso", CapturaError.getErrorException(ex)});
            return event.getOldStep();
        }
    }

    /**
     *
     */
    public void agregaCuentaSolicitud() {
        try {
            if (getTipoCuentaSolicitudSeleccionada() != null) {
                if (getTipoCuentaSolicitudSeleccionada().length() <= 0) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneTipoCuenta"));
                } else {
                    if ((getTipoCuentaSolicitudSeleccionada().equals("D")) || (getTipoCuentaSolicitudSeleccionada().equals("C"))) {
                        if (getCuentaSolicitudSeleccionada() == null) {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuenta"));
                            return;
                        }
                        if (getListaCuentasSolicitudAgredadas() == null) {
                            setListaCuentasSolicitudAgredadas(new ArrayList<>());
                            getListaCuentasSolicitudAgredadas().add(new LineaCreditoSolicitud());
                        }
                        if (getTipoCuentaSolicitudSeleccionada().equals("D")) {
                            getListaCuentasSolicitudAgredadas().get(0).setCuentaDebito(getCuentaSolicitudSeleccionada());
                        }
                        if (getTipoCuentaSolicitudSeleccionada().equals("C")) {
                            getListaCuentasSolicitudAgredadas().get(0).setCuentaAcreditada(getCuentaSolicitudSeleccionada());
                        }
                    } else {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneTipoCuenta"));
                    }
                }
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneTipoCuenta"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "flujoProceso", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean validaActualizacionInformacionSocio() {
        try {
            if (getSocio() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return false;
            }
            int rangoEnDias = getMesesMaxUltimaActualizacion() * 30;
            if (getSocioSituacionPatrimonial() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformacionPatrimonilaSocioDesactualizada"));
                return false;
            } else {
                long diferenciaEn_ms = new Date().getTime() - getSocioSituacionPatrimonial().getFechaActualizacion().getTime();
                long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
                if (dias > rangoEnDias) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformacionPatrimonilaSocioDesactualizada"));
                    return false;
                }
            }
            long diferenciaEn_ms = new Date().getTime() - getSocio().getFechaActualizacion().getTime();
            long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
            if (dias > rangoEnDias) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformacionSocioDesactualizada"));
                return false;
            }
            if (getSocio().getCodigoPersona().getFechaCaducidadIdentificacion() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionSocioSinCaducidad"));
                return false;
            }
            if(getSocio().getCodigoPersona().getCorreoEletronico() == null ){
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CorreoElectronicoNecesario"));
                return false;
            }else{
                if( !validaCorreoElectronico( getSocio().getCodigoPersona().getCorreoEletronico()) ){
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CorreoElectronicoNecesario"));
                    return false;
                }
            }
            if (Validaciones.validaFechaMenorHoy(getSocio().getCodigoPersona().getFechaCaducidadIdentificacion())) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionSocioCaducada"));
                return false;
            }
            if (getSocio().getCodigoIfipAgencia().getCodigo().compareTo(ActivacionUsuario.getCodigoIfipAgencia()) == 0) {
                if (String.valueOf(getSocio().getCodigoEstadoSocio().getIndicaActivo()).equals("S")) {
                } else {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioInactivo") + ": {" + getSocio().getCodigoPersona().getNombreCompleto() + "," + getSocio().getCodigoEstadoSocio().getNombre() + " }");
                    return false;
                }
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AgenciaSocio") + ": " + getSocio().getCodigoIfipAgencia().getNombre());
                return false;
            }
            //Obtener las lineas de credito activas
            List<LineaCreditoSolicitud> listaLineaCreditoSolicitud = ejbFacade.getListaLineaCreditoSolicitudPorCodigoSocio(getSocio().getSocioPK().getCodigo());
            if( listaLineaCreditoSolicitud != null ){
                if( !listaLineaCreditoSolicitud.isEmpty() ){
                    //Buscar lineas de credito activas del socio
                    for( int a=0;a<listaLineaCreditoSolicitud.size();a++ ){
                        if( listaLineaCreditoSolicitud.get(a).getCodigoLineaCreditoEstadoSol()==8 ){
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioPoseeLineaCreditoActiva"));
                            return false;
                        }
                    }
                }
            }
            //Validar la mora del titular
            boolean titularMoroso = false;
            List<Solicitud> listaSolicitud = ejbSolicitudFacade.getItemsCodigoSocioCodigoIfipEstado(getSocio().getSocioPK().getCodigo(),getSocio().getSocioPK().getCodigoIfip(),6L);
            if( listaSolicitud != null ){
                if( !listaSolicitud.isEmpty() ){
                    for( int a=0;a<listaSolicitud.size();a++ ){
                        if( ejbCalculoCuotaPagarFacade.getDiasMoraNumeroIfip(listaSolicitud.get(a).getSolicitudPK().getNumero(),listaSolicitud.get(a).getSolicitudPK().getCodigoIfip(), 'P') > 0 ){
                            titularMoroso = true;
                            break;
                        }
                    }
                }
            }
            if( titularMoroso ){
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TitularMorosoLineaCredito"));
                return false;
            }
            //Validar la mora del conyuge
            boolean conyugeMoroso = false;
            if( personaConyugeSocio != null ){
                List<Socio> listaSocioConyuge = ejbFacadeSocio.getItemsSociofindByIdIfip(personaConyugeSocio.getIdentificacion(), getSocio().getSocioPK().getCodigoIfip());
                if( listaSocioConyuge != null ){
                    if( !listaSocioConyuge.isEmpty() ){
                        //Obtener el codigo de socio del conyuge
                        Socio socioConyuge = listaSocioConyuge.get(0);
                        listaSolicitud = ejbSolicitudFacade.getItemsCodigoSocioCodigoIfipEstado(socioConyuge.getSocioPK().getCodigo(),socioConyuge.getSocioPK().getCodigoIfip(),6L);
                        if( listaSolicitud != null ){
                            if( !listaSolicitud.isEmpty() ){
                                for( int a=0;a<listaSolicitud.size();a++ ){
                                    if( ejbCalculoCuotaPagarFacade.getDiasMoraNumeroIfip(listaSolicitud.get(a).getSolicitudPK().getNumero(),listaSolicitud.get(a).getSolicitudPK().getCodigoIfip(), 'P') > 0 ){
                                        conyugeMoroso = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if( conyugeMoroso ){
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ConyugeMorosoLineaCredito"));
                            return false;
                        }
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validaActualizacionInformacionSocio", CapturaError.getErrorException(ex)});
            return false;
        }
    }
    /***
     * Metodo para validar el correo electronico
     * @param correoElectronico
     * @return 
     */
    private boolean validaCorreoElectronico(String correoElectronico){
        try{
            if( correoElectronico==null )
                return false;
            // Patrón para validar el email
            Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            Matcher mather = pattern.matcher(correoElectronico);
            return mather.find();
        }catch( Exception ex ){
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validarCorreoElectronico", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaInformacionLineaCreditoSocio() {
        try {
            if (getLineaCredito() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneLineaCredito"));
                return false;
            }
            if (getCanalServicio() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CanalServicioLineaCredito"));
                return false;
            }
            if (getEnvioCanalServicioIfip() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("EnvioCanalServicioIfipLineaCredito"));
                return false;
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validaInformacionLineaCreditoSocio", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaCuentaCertificadoAportacionSocio() {        
        try {
            List<Cuenta> certificadosAport = ejbFacadeCuenta.getItemsCertAprPuedeReactivar(getSocio().getSocioPK().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'S', Long.parseLong("1"), new BigDecimal("0"));
            if (certificadosAport.isEmpty()) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoTieneCertificadoAportaciones"));
                return false;
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validaCuentaCertificadoAportacionSocio", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    private boolean validaCuentasSolicitudSocio() {
        try {
            if (getListaCuentasSolicitudAgredadas() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AgregueCuentasSolicitudCredito"));
                return false;
            }
            if (getListaCuentasSolicitudAgredadas().isEmpty()) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AgregueCuentasSolicitudCredito"));
                return false;
            }
            if (getListaCuentasSolicitudAgredadas().get(0) == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AgregueCuentasSolicitudCredito"));
                return false;
            }
            if (getListaCuentasSolicitudAgredadas().get(0).getCuentaAcreditada() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AgregueCuentasSolicitudCredito"));
                return false;
            }
            if (getListaCuentasSolicitudAgredadas().get(0).getCuentaDebito() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AgregueCuentasSolicitudCredito"));
                return false;
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validaCuentasSolicitudSocio", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaEstadoCivilSocio() {
        try {
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_socios.pkm_persona_conyuge.p_valida_conyuge");
            llamaSP.setNumeroParametros(3);
            llamaSP.setListParametrosEntrada(new ArrayList<>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", getSocio().getCodigoPersona().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona_conyuge", getPersonaConyugeSocio() == null ? Long.valueOf("0") : getPersonaConyugeSocio().getCodigo()});
            llamaSP.setListParametrosSalida(new ArrayList<>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_valido", Types.VARCHAR});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                String valor = llamaSP.getListResultado().get(0).toString();
                if (valor.equals("N")) {
                    return false;
                } else {
                    return valor.equals("S");
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validaEstadoCivilSocio", CapturaError.getErrorException(ex)});
            return false;
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     *
     * @return
     */
    public boolean validaContrato() {
        try {
            if(!validaOtroProcesoLinea()){
                return false;
            }
            if (!validaActualizacionInformacionSocio()) {
                return false;
            }
            if (!validaInformacionLineaCreditoSocio()) {
                return false;
            }
            if (!validaCuentaCertificadoAportacionSocio()) {
                return false;
            }
            if (!validaCuentasSolicitudSocio()) {
                return false;
            }
            return validaEstadoCivilSocio();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validaContrato", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaNegociacion() {
        try {
            if (getDestinoFinanciero() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneDestinoFinancieroLineaCredito"));
                return false;
            }
            return validaNivelActividadEconomicaUbicacionGeografica();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validaNegociacion", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void cambiaActividadEconomicaN1() {
        try {
            if (actividadEconomicaN1 != null) {
                listaActividadEconomicaN2 = ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelPadreEliminado(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 2, actividadEconomicaN1.getCodigo(), 'C', 'N');
            }
            listaActividadEconomicaN3 = null;
            listaActividadEconomicaN4 = null;
            listaActividadEconomicaN5 = null;
            listaActividadEconomicaN6 = null;
            actividadEconomicaN2 = null;
            actividadEconomicaN3 = null;
            actividadEconomicaN4 = null;
            actividadEconomicaN5 = null;
            actividadEconomicaN6 = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cambiaActividadEconomicaN1", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaActividadEconomicaN2() {
        try {
            if (actividadEconomicaN2 != null) {
                listaActividadEconomicaN3 = ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelPadreEliminado(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 3, actividadEconomicaN2.getCodigo(), 'C', 'N');
            }
            listaActividadEconomicaN4 = null;
            listaActividadEconomicaN5 = null;
            listaActividadEconomicaN6 = null;
            actividadEconomicaN3 = null;
            actividadEconomicaN4 = null;
            actividadEconomicaN5 = null;
            actividadEconomicaN6 = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cambiaActividadEconomicaN2", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaActividadEconomicaN3() {
        try {
            if (actividadEconomicaN3 != null) {
                listaActividadEconomicaN4 = ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelPadreEliminado(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 4, actividadEconomicaN3.getCodigo(), 'C', 'N');
            }
            listaActividadEconomicaN5 = null;
            listaActividadEconomicaN6 = null;
            actividadEconomicaN4 = null;
            actividadEconomicaN5 = null;
            actividadEconomicaN6 = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cambiaActividadEconomicaN3", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaActividadEconomicaN4() {
        try {
            if (actividadEconomicaN4 != null) {
                listaActividadEconomicaN5 = ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelPadreEliminado(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 5, actividadEconomicaN4.getCodigo(), 'C', 'N');
            }
            listaActividadEconomicaN6 = null;
            actividadEconomicaN5 = null;
            actividadEconomicaN6 = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cambiaActividadEconomicaN4", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaActividadEconomicaN5() {
        try {
            if (actividadEconomicaN5 != null) {
                listaActividadEconomicaN6 = ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelPadreEliminado(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 6, actividadEconomicaN5.getCodigo(), 'C', 'N');
            }
            actividadEconomicaN6 = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cambiaActividadEconomicaN5", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaUbicacionGeograficaPais() {
        try {
            listaUbicacionGeograficaProvincia = ejbFacadeUbicacionGeografica.getItemsUbicacionGeograficaHijosVigentes(ubicacionGeograficaPais);
            ubicacionGeograficaProvincia = null;
            listaUbicacionGeograficaCiudad = ejbFacadeUbicacionGeografica.getItemsUbicacionGeograficaHijosVigentes(ubicacionGeograficaProvincia);
            ubicacionGeograficaCiudad = null;
            listaUbicacionGeograficaParroquia = ejbFacadeUbicacionGeografica.getItemsUbicacionGeograficaHijosVigentes(ubicacionGeograficaCiudad);
            ubicacionGeograficaParroquia = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cambiaUbicacionGeograficaPais", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaUbicacionGeograficaProvincia() {
        try {
            listaUbicacionGeograficaCiudad = ejbFacadeUbicacionGeografica.getItemsUbicacionGeograficaHijosVigentes(ubicacionGeograficaProvincia);
            ubicacionGeograficaCiudad = null;
            listaUbicacionGeograficaParroquia = ejbFacadeUbicacionGeografica.getItemsUbicacionGeograficaHijosVigentes(ubicacionGeograficaCiudad);
            ubicacionGeograficaParroquia = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cambiaUbicacionGeograficaProvincia", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaUbicacionGeograficaCiudad() {
        try {
            listaUbicacionGeograficaParroquia = ejbFacadeUbicacionGeografica.getItemsUbicacionGeograficaHijosVigentes(ubicacionGeograficaCiudad);
            ubicacionGeograficaParroquia = (null);
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cambiaUbicacionGeograficaCiudad", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean validaMontoLineaCredito() {
        try {
            if (getLineaCredito() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneLineaCredito"));
                return false;
            }
            if (((lineaCreditoSolicitud.getMontoLineaCredito().compareTo(lineaCredito.getMontoMinimo()) == 1)
                    || (lineaCreditoSolicitud.getMontoLineaCredito().compareTo(lineaCredito.getMontoMinimo()) == 0))
                    && ((lineaCreditoSolicitud.getMontoLineaCredito().compareTo(lineaCredito.getMontoMaximo()) == -1)
                    || (lineaCreditoSolicitud.getMontoLineaCredito().compareTo(lineaCredito.getMontoMaximo()) == 0))) {
                return true;
            } else {
                MuestraMensaje.addError(MessageFormat.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MontoSolicitudLineaCreditoFueraDeRango"), lineaCredito.getMontoMinimo().toString(), lineaCredito.getMontoMaximo().toString()));
                return false;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validaMontoLineaCredito", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void seleccionaTasaPorMonto() {
        boolean valido = false;
        try {
            if (!validaMontoLineaCredito()) {
                tasaLineaCredito = null;
                lineaCreditoSolicitud.setMontoLineaCredito(BigDecimal.ZERO);
                return;
            }
            if (listaTasaLineaCredito != null) {
                for (int i = 0; i < listaTasaLineaCredito.size(); i++) {
                    if (lineaCreditoSolicitud.getMontoLineaCredito().doubleValue() >= listaTasaLineaCredito.get(i).getMontoInicial().doubleValue() && lineaCreditoSolicitud.getMontoLineaCredito().doubleValue() <= listaTasaLineaCredito.get(i).getMontoFinal().doubleValue()) {
                        if (!validaMontoEdadTipoPersona(socio.getCodigoPersona().getCodigoTipoPersona().getCodigo(),
                                Utilidades.fechaStringMontoSolicitud(socio.getCodigoPersona().getPersonaNatural().getFechaNacimiento(), "/"),
                                lineaCreditoSolicitud.getMontoLineaCredito().longValue())) {
                            lineaCreditoSolicitud.setMontoLineaCredito(null);
                            MuestraMensaje.addError("No es posible crear una solicitud por el monto de " + lineaCreditoSolicitud.getMontoLineaCredito() + " por la edad del socio.");
                            return;
                        }
                        tasaLineaCredito = listaTasaLineaCredito.get(i);
                        tieneGarantias = listaTasaLineaCredito.get(i).getTieneGarantias();
                        valido = true;
                        break;
                    }
                }
            }
            if (valido) {
                TipoGarantiaProCreMonIfi garantia = ejbFacadeTipoGarantiaProCreMonIfi.getTipoGarantiaProCreMonIfiElim(tasaLineaCredito.getCodigo(), Long.parseLong("1"), 'N');
                //listaGarantiaLineaCredito = ejbFacadeTipoGarantiaProCreMonIfi.getTipoGarantiaProCreMonIfiTasaElim(tasaLineaCredito.getCodigoTasa().getCodigo(), 'N');
                if (garantia == null) {
                    setGarantesRequeridos(0);
                    setGarantiasObligatorias(false);
                } else if (garantia.getObligatorio() == 'N') {
                    setGarantesRequeridos(garantia.getNumero());
                    setGarantiasObligatorias(false);
                } else {
                    setGarantesRequeridos(garantia.getNumero());
                    setGarantiasObligatorias(true);
                }
            } else {
                tasaLineaCredito = null;
                lineaCreditoSolicitud.setMontoLineaCredito(BigDecimal.ZERO);
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MontoCreditoFueraDeRango"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "seleccionaTasaPorMonto", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Valida montos por edad y tipo persona
     *
     * @param codigoTipoPersona
     * @param fechaNacimiento
     * @param monto
     * @return
     */
    public boolean validaMontoEdadTipoPersona(Long codigoTipoPersona, String fechaNacimiento, Long monto) {
        try {
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_creditos.pkg_parametro_monto_edad.p_valida");
            llamaSP.setNumeroParametros(4);
            llamaSP.setListParametrosEntrada(new ArrayList<>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_persona", codigoTipoPersona});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pv_fecha_nacimiento", fechaNacimiento});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_monto", monto});
            llamaSP.setListParametrosSalida(new ArrayList<>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_es_valido", Types.VARCHAR});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                String esValidoString = (llamaSP.getListResultado().get(0) != null) ? llamaSP.getListResultado().get(0).toString() : null;
                return esValidoString.equals("S");
            } else {
                return false;
            }
        } catch (Exception e) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validaMontoEdadTipoPersona", CapturaError.getErrorException(e)});
            return false;
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     *
     * @return
     */
    public boolean validaNivelActividadEconomicaUbicacionGeografica() {
        try {
            boolean valido = false;
            int maximoNivel = validaActividadEconomicaSolicitud(lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), lineaCredito.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip());
            maximoNivelActividadEconomica = maximoNivel;
            switch (maximoNivel) {
                case -1:
                    MuestraMensaje.addError("Error al verificar el nivel de la actividad economica.");
                    valido = false;
                    break;
                case 1:
                    if (this.actividadEconomicaN1 == null) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        valido = false;
                    } else {
                        valido = true;
                    }
                    break;
                case 2:
                    if (this.actividadEconomicaN2 == null) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        valido = false;
                    } else {
                        valido = true;
                    }
                    break;
                case 3:
                    if (this.actividadEconomicaN3 == null) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        valido = false;
                    } else {
                        valido = true;
                    }
                    break;
                case 4:
                    if (this.actividadEconomicaN4 == null) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        valido = false;
                    } else {
                        valido = true;
                    }
                    break;
                case 5:
                    if (this.actividadEconomicaN5 == null) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        valido = false;
                    } else {
                        valido = true;
                    }
                    break;
                case 6:
                    if (this.actividadEconomicaN6 == null) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        valido = false;
                    } else {
                        valido = true;
                    }
                    break;
            }
            if (valido) {
                if (ubicacionGeograficaPais == null) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaUbicacionGeografica"));
                    return false;
                }
                int maximaJerarquia = validaUbicacionGeograficaSolicitud(ubicacionGeograficaPais.getCodigo());
                maximoNivelUbicacionGeografica = maximaJerarquia;
                switch (maximaJerarquia) {
                    case -1:
                        MuestraMensaje.addError("Error al verificar el nivel de la Ubicacion Geografica.");
                        valido = false;
                        break;
                    case 1:
                        if (ubicacionGeograficaPais == null) {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaUbicacionGeografica"));
                            valido = false;
                        } else {
                            valido = true;
                        }
                        break;
                    case 2:
                        if (ubicacionGeograficaProvincia == null) {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaUbicacionGeografica"));
                            valido = false;
                        } else {
                            valido = true;
                        }
                        break;
                    case 3:
                        if (ubicacionGeograficaCiudad == null) {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaUbicacionGeografica"));
                            valido = false;
                        } else {
                            valido = true;
                        }
                        break;
                    case 4:
                        if (ubicacionGeograficaParroquia == null) {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaUbicacionGeografica"));
                            valido = false;
                        } else {
                            valido = true;
                        }
                        break;
                }
            }
            return valido;
        } catch (Exception e) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validaNivelActividadEconomicaUbicacionGeografica", CapturaError.getErrorException(e)});
            maximoNivelActividadEconomica = 0;
            maximoNivelUbicacionGeografica = 0;
            return false;
        }
    }

    /**
     * Metodo para valida que se haya ingresado hasta el último nivel de
     * Actividades Economicas
     *
     * @param codigoProducto
     * @param codigoMoneda
     * @param codigoIfip
     * @return
     */
    public int validaActividadEconomicaSolicitud(Long codigoProducto, Long codigoMoneda, Long codigoIfip) {
        try {
            int maximoNivel;
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_creditos.pkm_act_eco_pro_cre_mon_ifip.p_obtiene_maximo_nivel");
            llamaSP.setNumeroParametros(4);
            llamaSP.setListParametrosEntrada(new ArrayList<>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_producto", codigoProducto});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_moneda", codigoMoneda});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", codigoIfip});
            llamaSP.setListParametrosSalida(new ArrayList<>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_maximo_nivel", Types.NUMERIC});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                if (llamaSP.getListResultado() != null) {
                    maximoNivel = Integer.parseInt(llamaSP.getListResultado().get(0).toString());
                    return maximoNivel;
                } else {
                    return -1;
                }
            } else {
                MuestraMensaje.addErrorCapaControl();
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                        new Object[]{"LineaCreditoSolicitudController", "validaActividadEconomicaSolicitud", llamaSP.getErroSql()});
                return -1;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                    new Object[]{"LineaCreditoSolicitudController", "validaActividadEconomicaSolicitud", llamaSP.getErroSql()});
            return -1;
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     * Metodo para valida que se haya ingresado hasta el último nivel de
     * Actividades Economicas
     *
     * @param codigoPais
     * @return
     */
    public int validaUbicacionGeograficaSolicitud(Long codigoPais) {
        try {
            int maximaJerarquia;
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_comunes.pkg_ubicacion_geografica.p_obtiene_maxima_jerarquia");
            llamaSP.setNumeroParametros(2);
            llamaSP.setListParametrosEntrada(new ArrayList<>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo", codigoPais});
            llamaSP.setListParametrosSalida(new ArrayList<>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo_jerarquia", Types.NUMERIC});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                if (llamaSP.getListResultado() != null) {
                    maximaJerarquia = Integer.parseInt(llamaSP.getListResultado().get(0).toString());
                    return maximaJerarquia;
                } else {
                    return -1;
                }
            } else {
                MuestraMensaje.addErrorCapaControl();
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                        new Object[]{"LineaCreditoSolicitudController", "validaUbicacionGeograficaSolicitud", llamaSP.getErroSql()});
                return -1;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                    new Object[]{"LineaCreditoSolicitudController", "validaUbicacionGeograficaSolicitud", llamaSP.getErroSql()});
            return -1;
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
    public void guardaLineaCreditoSolicitud() {
        setProcesoExitoso(false);
        setProcesoError(false);
        try {
            if (!validaContrato()) {
                return;
            }
            if (!validaNegociacion()) {
                return;
            }
            if (!validaGarantes()) {
                return;
            }
            if (!asignaValoresLineaCreditoSolicitud()) {
                return;
            }
            if (!edicion) {
                ejbFacade.create(lineaCreditoSolicitud);
            } else {
                if (!editaGarantes()) {
                    setProcesoError(true);
                    return;
                }
                ejbFacade.actualiza(lineaCreditoSolicitud);
            }
            //Guardar los firmantes de linea de credito
            if (!ejbFacade.guardaFirmante(lineaCreditoSolicitud.getCodigo())) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"LineaCreditoSolicitudController", "guardaLineaCreditoSolicitud", "Error al guardar firmantes"});
            }
            //Guardar la auditoria
            this.guardarAuditoriaEstado( lineaCreditoSolicitud.getCodigo(),lineaCreditoSolicitud.getCodigoLineaCreditoEstadoSol(),ActivacionUsuario.getCodigoUsuario(),new Date(),'U');
            setProcesoExitoso(true);
        } catch (Exception ex) {
            setProcesoError(true);
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "guardaLineaCreditoSolicitud", CapturaError.getErrorException(ex)});
        }
    }
    /***
     * Metodo para registra la auditoria de estados de la solicitud de linea de credito
     * @param codigoLineaCreditoSol codigo de la solicitud de linea de credito
     * @param codigoLineaCreEstSol codigo del estado de la solicitud de linea de credito
     * @param codigoUsuario codigo del usuario que registra el estado
     * @param fecha fecha en la que se registra el estado
     * @param accion accion que realiza con el estado
     */
    public void guardarAuditoriaEstado(Long codigoLineaCreditoSol, Long codigoLineaCreEstSol, Long codigoUsuario, Date fecha, char accion){
        try{
            LineaCreditoSolicitudEstado estado = new LineaCreditoSolicitudEstado();
            estado.setCodigoLineaCreditoSol(codigoLineaCreditoSol);
            estado.setCodigoLineaCreEstSol(codigoLineaCreEstSol);
            estado.setCodigoUsuario(codigoUsuario);
            estado.setFecha(fecha);
            estado.setAccion(accion);
            ejbLineaCreditoSolicitudEstadoFacade.create(estado);
        }catch(Exception ex){
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "guardarAuditoriaEstado", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean asignaValoresLineaCreditoSolicitud() {
        try {
            if (!edicion) {
                lineaCreditoSolicitud.setCodigo(null);
                lineaCreditoSolicitud.setCodigoIfip(ifip.getCodigo());
                lineaCreditoSolicitud.setDiaPago(lineaCredito.getDiaGeneraCredito());
                lineaCreditoSolicitud.setDiaEstadoCuenta(lineaCredito.getDiaGeneraCredito());
                lineaCreditoSolicitud.setCodigoLineaCredito(lineaCredito.getCodigo());
                lineaCreditoSolicitud.setCodigoDestinoFinanciero(destinoFinanciero.getCodigo());
                lineaCreditoSolicitud.setCodigoEnvioCanalServicioIfip(getEnvioCanalServicioIfip().getCodigo());
                lineaCreditoSolicitud.setCodigoLineaCreditoEstadoSol(lineaCreditoEstadoSolicitud.getCodigo());
                lineaCreditoSolicitud.setCodigoSocio(socio.getSocioPK().getCodigo());
                lineaCreditoSolicitud.setFechaSolicitud(new java.sql.Timestamp(new Date().getTime()));
                lineaCreditoSolicitud.setFechaActualizacion(new java.sql.Timestamp(new Date().getTime()));
                lineaCreditoSolicitud.setCodigoCuentaDebito(listaCuentasSolicitudAgredadas.get(0).getCuentaDebito().getCodigo());
                lineaCreditoSolicitud.setCodigoCuentaAcreditada(listaCuentasSolicitudAgredadas.get(0).getCuentaAcreditada().getCodigo());
                lineaCreditoSolicitud.setPorcentajePagoMinimo(lineaCredito.getPorcentajePagoMinimo());
                lineaCreditoSolicitud.setCodigoIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia());
                if (listaGaranteLineaCredito != null) {
                    if (listaGaranteLineaCredito.size() > 0) {
                        listaLineaCreditoSolicitudTipoGarantia = new ArrayList<>();
                        listaLineaCreditoSolicitudTipoGarantia.add(lineaCreditoSolicitudTipoGarantia);
                        listaLineaCreditoSolicitudTipoGarantia.get(0).setListaLineaCreditoSolicitudGarantia(listaGaranteLineaCredito);
                        lineaCreditoSolicitud.setListaLineaCreditoSolicitudTipoGarantia(listaLineaCreditoSolicitudTipoGarantia);
                    }
                }
            } else {

            }
            switch (maximoNivelActividadEconomica) {
                case -1:
                    MuestraMensaje.addError("Error al verificar el nivel de la actividad economica.");
                    return false;
                case 1:
                    lineaCreditoSolicitud.setCodigoActEco(actividadEconomicaN1.getCodigo());
                    break;
                case 2:
                    lineaCreditoSolicitud.setCodigoActEco(actividadEconomicaN2.getCodigo());
                    break;
                case 3:
                    lineaCreditoSolicitud.setCodigoActEco(actividadEconomicaN3.getCodigo());
                    break;
                case 4:
                    lineaCreditoSolicitud.setCodigoActEco(actividadEconomicaN4.getCodigo());
                    break;
                case 5:
                    lineaCreditoSolicitud.setCodigoActEco(actividadEconomicaN5.getCodigo());
                    break;
                case 6:
                    lineaCreditoSolicitud.setCodigoActEco(actividadEconomicaN6.getCodigo());
                    break;
            }
            switch (maximoNivelUbicacionGeografica) {
                case -1:
                    MuestraMensaje.addError("Error al verificar el nivel de la Ubicacion Geografica.");
                    return false;
                case 1:
                    lineaCreditoSolicitud.setCodigoUbicacionGeografica(ubicacionGeograficaPais.getCodigo());
                    break;
                case 2:
                    lineaCreditoSolicitud.setCodigoUbicacionGeografica(ubicacionGeograficaProvincia.getCodigo());
                    break;
                case 3:
                    lineaCreditoSolicitud.setCodigoUbicacionGeografica(ubicacionGeograficaCiudad.getCodigo());
                    break;
                case 4:
                    lineaCreditoSolicitud.setCodigoUbicacionGeografica(ubicacionGeograficaParroquia.getCodigo());
                    break;
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "asignaValoresLineaCreditoSolicitud", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void cambiaCanalServicio() {
        try {
            if (canalServicio != null) {
                setEnvioCanalServicioIfip(ejbEnvioCanalServicioIfip.getEnvioCanalServicioIfipPorCodigoServicioIfipCodigoCanalServicioIfip(Long.valueOf("1"), canalServicio.getCodigo()));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cambiaCanalServicio", CapturaError.getErrorException(ex)});
            setEnvioCanalServicioIfip(null);
        }
    }

    /**
     *
     */
    public void cargarActividadEconomicaLineaSolicitud() {
        try {
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_socios.pkm_actividad_economica.p_obtiene_jerarquia_actividad");
            llamaSP.setNumeroParametros(2);
            llamaSP.setListParametrosEntrada(new ArrayList<>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo", lineaCreditoSolicitud.getCodigoActEco()});
            llamaSP.setListParametrosSalida(new ArrayList<>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_actividad_economica", OracleTypes.CURSOR});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                if (llamaSP.getListResultado() != null) {
                    ResultSet resultSet = (ResultSet) llamaSP.getListResultado().get(0);
                    if (resultSet != null) {
                        List<ActividadEconomica> lista = null;
                        while (resultSet.next()) {
                            if (lista == null) {
                                lista = new ArrayList<>();
                            }
                            lista.add(new ActividadEconomica(resultSet.getLong("codigo"),
                                    resultSet.getString("codigo_oc"),
                                    resultSet.getString("nombre"),
                                    resultSet.getString("eliminado").charAt(0)));
                        }
                        if (lista == null) {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistrosCargaActividadEconomica"));
                        } else {
                            for (int i = 0; i <= lista.size(); i++) {
                                switch (i) {
                                    case 0:
                                        actividadEconomicaN1 = lista.get(i);
                                        break;
                                    case 1:
                                        actividadEconomicaN2 = lista.get(i);
                                        listaActividadEconomicaN2 = new ArrayList<>();
                                        listaActividadEconomicaN2.add(actividadEconomicaN2);
                                        break;
                                    case 2:
                                        actividadEconomicaN3 = lista.get(i);
                                        listaActividadEconomicaN3 = new ArrayList<>();
                                        listaActividadEconomicaN3.add(actividadEconomicaN3);
                                        break;
                                    case 3:
                                        actividadEconomicaN4 = lista.get(i);
                                        listaActividadEconomicaN4 = new ArrayList<>();
                                        listaActividadEconomicaN4.add(actividadEconomicaN4);
                                        break;
                                    case 4:
                                        actividadEconomicaN5 = lista.get(i);
                                        listaActividadEconomicaN5 = new ArrayList<>();
                                        listaActividadEconomicaN5.add(actividadEconomicaN5);
                                        break;
                                    case 5:
                                        actividadEconomicaN6 = lista.get(i);
                                        listaActividadEconomicaN6 = new ArrayList<>();
                                        listaActividadEconomicaN6.add(actividadEconomicaN6);
                                        break;
                                }
                            }
                        }
                    }
                } else {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoCargaActividadEconomica"));
                }
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCargaActividadEconomica"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cargarActividadEconomicaSocio", CapturaError.getErrorException(ex)});
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
    public void cargarUbicacionGeograficaLineaSolicitud() {
        try {
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_comunes.pkm_ubicacion_geografica.p_obtiene_jerarquia_ubicacion");
            llamaSP.setNumeroParametros(2);
            llamaSP.setListParametrosEntrada(new ArrayList<>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo", lineaCreditoSolicitud.getCodigoUbicacionGeografica()});
            llamaSP.setListParametrosSalida(new ArrayList<>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_ubicacion_geografica", OracleTypes.CURSOR});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                if (llamaSP.getListResultado() != null) {
                    ResultSet resultSet = (ResultSet) llamaSP.getListResultado().get(0);
                    if (resultSet != null) {
                        List<UbicacionGeografica> lista = null;
                        while (resultSet.next()) {
                            if (lista == null) {
                                lista = new ArrayList<>();
                            }
                            lista.add(new UbicacionGeografica(resultSet.getLong("codigo"),
                                    resultSet.getString("nombre"),
                                    resultSet.getString("siglas"),
                                    resultSet.getString("eliminado").charAt(0)));
                        }
                        if (lista == null) {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistrosCargaUbicacionGeografica"));
                        } else {
                            for (int i = 0; i <= lista.size(); i++) {
                                switch (i) {
                                    case 0:
                                        ubicacionGeograficaPais = lista.get(i);
                                        break;
                                    case 1:
                                        ubicacionGeograficaProvincia = lista.get(i);
                                        listaUbicacionGeograficaProvincia = new ArrayList<>();
                                        listaUbicacionGeograficaProvincia.add(ubicacionGeograficaProvincia);
                                        break;
                                    case 2:
                                        ubicacionGeograficaCiudad = lista.get(i);
                                        listaUbicacionGeograficaCiudad = new ArrayList<>();
                                        listaUbicacionGeograficaCiudad.add(ubicacionGeograficaCiudad);
                                        break;
                                    case 3:
                                        ubicacionGeograficaParroquia = lista.get(i);
                                        listaUbicacionGeograficaParroquia = new ArrayList<>();
                                        listaUbicacionGeograficaParroquia.add(ubicacionGeograficaParroquia);
                                        break;
                                }
                            }
                        }
                    }
                } else {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoCargaUbicacionGeografica"));
                }
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCargaUbicacionGeografica"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cargarActividadEconomicaSocio", CapturaError.getErrorException(ex)});
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     *
     * @param event
     */
    public void onRowSelect(SelectEvent event) {
        try {
            lineaCreditoSolicitud = (LineaCreditoSolicitud) event.getObject();
            habilitaEdicion = lineaCreditoSolicitud.getCodigoLineaCreditoEstadoSol().compareTo(Long.valueOf("1")) == 0;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "onRowSelect", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void onRowUnselect(UnselectEvent event) {
        try {
            lineaCreditoSolicitud = null;
            habilitaEdicion = false;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "onRowUnselect", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void buscaPersonaNaturalGaranteLineaSolicitud() {
        try {
            if (identificacionGarante != null) {
                if (identificacionGarante.length() > 0) {
                    List<PersonaNatural> listaPersonaNaturalGarante = ejbPersonaNatural.getItemsIdentificacion(identificacionGarante);
                    if (listaPersonaNaturalGarante == null) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                        setPersonaNaturalGarante(null);
                        setPersonaConyugeGarante(null);
                        setCoberturaGarante(BigDecimal.ZERO);
                        return;
                    }
                    if (listaPersonaNaturalGarante.size() <= 0) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                        setPersonaNaturalGarante(null);
                        setPersonaConyugeGarante(null);
                        setCoberturaGarante(BigDecimal.ZERO);
                        return;
                    }
                    if (listaPersonaNaturalGarante.size() > 1) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionAsignadoMasDeUnaPersona"));
                        setPersonaNaturalGarante(null);
                        setPersonaConyugeGarante(null);
                        setCoberturaGarante(BigDecimal.ZERO);
                        return;
                    }
                    setPersonaNaturalGarante(listaPersonaNaturalGarante.get(0));
                    cargarPersonaNaturalConyugeGarante();
                } else {
                    setPersonaNaturalGarante(null);
                    setPersonaConyugeGarante(null);
                    setCoberturaGarante(BigDecimal.ZERO);
                }
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseCriterioConsulta"));
                setPersonaNaturalGarante(null);
                setPersonaConyugeGarante(null);
                setCoberturaGarante(BigDecimal.ZERO);
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "buscaPersonaNaturalGaranteLineaSolicitud", CapturaError.getErrorException(ex)});
            setPersonaNaturalGarante(null);
            setPersonaConyugeGarante(null);
            setCoberturaGarante(BigDecimal.ZERO);
        }
    }

    /**
     *
     */
    public void cargarPersonaNaturalConyugeGarante() {
        try {
            setHabilitarBotonEliminarConyugeGarante(false);
            setHabilitarBotonEditarConyugeGarante(false);
            setPersonaConyugeGarante(null);
            if (getPersonaNaturalGarante() != null) {
                llamaSP.cargaConexion();
                llamaSP.setCerrarConexion(false);
                llamaSP.autoCommit(false);
                llamaSP.setNombreSP("mks_socios.pkm_persona_conyuge.p_obtiene_conyuge_persona");
                llamaSP.setNumeroParametros(2);
                llamaSP.setListParametrosEntrada(new ArrayList<>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_identificacion", getPersonaNaturalGarante().getPersona().getIdentificacion()});
                llamaSP.setListParametrosSalida(new ArrayList<>());
                llamaSP.getListParametrosSalida().add(new Object[]{"pt_persona_conyuge", OracleTypes.CURSOR});
                llamaSP.invocaSP();
                if (llamaSP.isEjecucionCorrecta()) {
                    ResultSet resultSet = (ResultSet) llamaSP.getListResultado().get(0);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            setPersonaConyugeGarante(new Persona(resultSet.getLong("codigo"),
                                    resultSet.getString("identificacion"),
                                    resultSet.getString("nombre_completo"),
                                    resultSet.getDate("fecha_ingreso"),
                                    resultSet.getDate("fecha_actualizacion")));
                            setHabilitarBotonEliminarConyugeGarante(true);
                            setHabilitarBotonEditarConyugeGarante(false);
                            break;
                        }
                    } else {
                        setPersonaConyugeGarante(null);
                        setHabilitarBotonEditarConyugeGarante(false);
                    }
                } else {
                    setHabilitarBotonEditarConyugeGarante(false);
                    setHabilitarBotonEliminarConyugeGarante(false);
                }
            } else {
                setHabilitarBotonEditarConyugeGarante(false);
                setHabilitarBotonEliminarConyugeGarante(false);
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "cargarPersonaConyugeGarante", CapturaError.getErrorException(ex)});
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
    public void asignarGarante() {
        try {
            if (personaConyugeSocio != null) {
                if (personaConyugeSocio.getIdentificacion().trim().equals(personaNaturalGarante.getPersona().getIdentificacion())) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ConyugeNoPuedeSerGarante"));
                    return;
                }
            }
            if (socio != null) {
                if (socio.getCodigoPersona().getIdentificacion().trim().equals(personaNaturalGarante.getPersona().getIdentificacion())) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoPuedeSerGaranteMismoSocio"));
                    return;
                }
            } else {
                MuestraMensaje.addInformacion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste"));
                return;
            }
            if (listaGaranteLineaCredito == null) {
                listaGaranteLineaCredito = new ArrayList<>();
            }
            if (listaGaranteLineaCredito.size() >= garantesRequeridos) {
                MuestraMensaje.addInformacion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GarantesCompletos"));
                return;
            }
            if (personaNaturalGarante != null && (coberturaGarante.compareTo(BigDecimal.ZERO) == 1)) {
                //Verificar la mora del garante
                boolean garanteMoroso = false;
                //Obtener la persona garante
                Persona personaGarante = ejbFacadePersona.getItemsIdenPersona(personaNaturalGarante.getPersona().getIdentificacion()).get(0);
                List<Socio> listaSocioGarante= ejbFacadeSocio.getItemsSociofindByIdIfip(personaGarante.getIdentificacion(), getSocio().getSocioPK().getCodigoIfip());
                if( listaSocioGarante != null ){
                    if( !listaSocioGarante.isEmpty() ){
                        //Obtener el codigo de socio del garante
                        Socio socioGarante = listaSocioGarante.get(0);
                        List<Solicitud> listaSolicitud = ejbSolicitudFacade.getItemsCodigoSocioCodigoIfipEstado(socioGarante.getSocioPK().getCodigo(),socioGarante.getSocioPK().getCodigoIfip(),6L);
                        if( listaSolicitud != null ){
                            if( !listaSolicitud.isEmpty() ){
                                for( int a=0;a<listaSolicitud.size();a++ ){
                                    if( ejbCalculoCuotaPagarFacade.getDiasMoraNumeroIfip(listaSolicitud.get(a).getSolicitudPK().getNumero(),listaSolicitud.get(a).getSolicitudPK().getCodigoIfip(), 'P') > 0 ){
                                        garanteMoroso = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if( garanteMoroso ){
                            setPersonaConyugeGarante(null);
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteMorosoLineaCredito"));
                            return;
                        }
                    }
                }
                
                //Validar la cobertura del garante
                List<LineaCreditoPlazoMaximo> listaLineaCreditoPlazoMaximo = ejbLineaCreditoPlazoMaximoFacade.getListaLineaCreditoPlazoMaximoPorLinea(getLineaCredito().getCodigo());
                long plazoMaximo = 0;
                if( listaLineaCreditoPlazoMaximo != null ){
                    for( int a=0;a<listaLineaCreditoPlazoMaximo.size();a++ ){
                        if( plazoMaximo<listaLineaCreditoPlazoMaximo.get(a).getNumeroCuotasFin() )
                            plazoMaximo = listaLineaCreditoPlazoMaximo.get(a).getNumeroCuotasFin();
                    }
                    BigDecimal valorACubrir = ( getLineaCreditoSolicitud().getMontoLineaCredito().divide(BigDecimal.valueOf(plazoMaximo),RoundingMode.UP) ).setScale(2,RoundingMode.UP);
                    if( valorACubrir.compareTo(coberturaGarante) == 1 ){
                        MuestraMensaje.addInformacion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteSinCobertura"));
                        return;
                    }
                }
                if (personaNaturalGarante.getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("2")) == 0 || personaNaturalGarante.getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("6")) == 0) {
                    // Buscando el conyuge actual                
                    List<PersonaConyuge> listaPersonaConyuge = ejbConyugePersona.getItemsCodigoPersonaElminado(personaNaturalGarante.getCodigoPersona(), 'N');
                    if (listaPersonaConyuge.isEmpty()) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteNoTieneConyuge"));
                        return;
                    } else if (listaPersonaConyuge.size() > 1) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteTieneMasDeUnConyuge"));
                        return;
                    }
                }
                if (Objects.equals(personaNaturalGarante.getCodigoPersona(), lineaCreditoSolicitud.getSocio().getCodigoPersona().getCodigo())) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPuedeSerGarante"));
                    return;
                }

                if (personaConyugeSocio != null) {
                    if (Objects.equals(personaNaturalGarante.getCodigoPersona(), personaConyugeSocio.getCodigo())) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ConyugeNoPuedeSerGarante"));
                        return;
                    }
                }
                int cantidadGarantias = ejbGaranteCredito.getNumeroGarantiasPersona(personaNaturalGarante.getCodigoPersona(), Long.parseLong("6"), 'S');
                if (cantidadGarantias < getGarantiasMaximasPorPersona()) {
                    //Verifica que el garante no este en la lista
                    boolean existe = false;
                    for (int i = 0; i < listaGaranteLineaCredito.size(); i++) {
                        if (listaGaranteLineaCredito.get(i).getGarante().getCodigoPersona().longValue() == personaNaturalGarante.getCodigoPersona().longValue()) {
                            existe = true;
                            break;
                        }
                    }
                    if (existe) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteEstaEnLista"));
                    } else {
                        LineaCreditoSolicitudGar garante = new LineaCreditoSolicitudGar();
                        garante.setGarante(personaNaturalGarante);
                        garante.setCoberturaDada(coberturaGarante);
                        garante.setCodigoUsuarioReg(ActivacionUsuario.getCodigoUsuario());
                        garante.setEliminado('N');
                        garante.setFechaRegistro(new java.sql.Timestamp(new Date().getTime()));
                        garante.setGarante(personaNaturalGarante);
                        garante.setCodigoPersona(personaNaturalGarante.getCodigoPersona());
                        garante.setVigente('S');
                        garante.setLineaCreditoSolicitudTipoGarantia(lineaCreditoSolicitudTipoGarantia);
                        listaGaranteLineaCredito.add(garante);
                        if (listaGaranteLineaCredito.size() >= garantesRequeridos) {
                            MuestraMensaje.addInformacion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GarantesCompletos"));
                        }
                    }
                    personaNaturalGarante = null;
                    personaConyugeGarante = null;
                    identificacionGarante = null;
                    coberturaGarante = BigDecimal.ZERO;
                } else {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ExedeCantidadDeGarantias"));
                }
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneGaranteCobertura"));
            }
            setIdentificacionGarante("");
            setPersonaNaturalGarante(null);
            setFoco("identificacionGarante");
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "asignarGarante", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param codigo
     * @param indice
     */
    public void eliminarGarante(Long codigo, int indice) {
        try {
            listaGaranteLineaCreditoEliminar = ((ArrayList) listaGaranteLineaCredito);
            listaGaranteLineaCreditoEliminar.remove(indice);
            listaGaranteLineaCredito = listaGaranteLineaCreditoEliminar;
            setFoco("identificacionGarante");
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "eliminarGarante", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return 
     */
    public boolean validaGarantes() {
        try {
            if (tieneGarantias == 'S' && getGarantesRequeridos() == 0) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoRequiereGarantias"));
                return false;
            }
            if (tieneGarantias == 'N') {
                if (getGarantesRequeridos() > 0) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoNoNecesitaGarantes"));
                    return false;
                }
            }
            if (listaGaranteLineaCredito == null) {
                listaGaranteLineaCredito = new ArrayList<>();
            }
            boolean coberturaCorrecta = false;
            if (listaGaranteLineaCredito.size() > 0) {
                double cobertura = 0;
                for (int i = 0; i < listaGaranteLineaCredito.size(); i++) {
                    if (listaGaranteLineaCredito.get(i).getCoberturaDada().doubleValue() <= 0) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CoberturaMayorDeCero"));
                        return false;
                    }
                    cobertura += listaGaranteLineaCredito.get(i).getCoberturaDada().doubleValue();
                }
                long plazoMaximo = 0;
                List<LineaCreditoPlazoMaximo> listaLineaCreditoPlazoMaximo = ejbLineaCreditoPlazoMaximoFacade.getListaLineaCreditoPlazoMaximoPorLinea(getLineaCredito().getCodigo());
                if( listaLineaCreditoPlazoMaximo != null ){
                    for( int a=0;a<listaLineaCreditoPlazoMaximo.size();a++ ){
                        if( plazoMaximo<listaLineaCreditoPlazoMaximo.get(a).getNumeroCuotasFin() )
                            plazoMaximo = listaLineaCreditoPlazoMaximo.get(a).getNumeroCuotasFin();
                    }
                }
                System.out.println("COBERTURA LINEA CREDITO: "+lineaCreditoSolicitud.getMontoLineaCredito()+" / "+plazoMaximo+" bigdecimal = "+BigDecimal.valueOf(plazoMaximo));
                /*BigDecimal valorACubrir = ( lineaCreditoSolicitud.getMontoLineaCredito().divide(BigDecimal.valueOf(plazoMaximo),RoundingMode.UP) ).setScale(2,RoundingMode.UP);
                if( valorACubrir.compareTo(BigDecimal.valueOf(cobertura)) == 1 ){
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CoberturaNoPermiteLineaCredito"));
                    return false;
                }else {
                    coberturaCorrecta = true;
                }*/
                coberturaCorrecta = true;
            }
            if (!coberturaCorrecta) {
                if (listaGaranteLineaCredito.size() > getGarantesRequeridos()) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ExcedeGarantesLineaCredito"));
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "validaGarantiaLineaCredito", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return 
     */
    public boolean editaGarantes() {
        try {
            if (listaGaranteLineaCredito == null) {
                return true;
            }
            if (listaGaranteLineaCredito.size() <= 0) {
                return true;
            }
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_creditos.pkm_linea_credito_garante.p_guarda_linea_credito_garante");
            for (LineaCreditoSolicitudGar item : listaGaranteLineaCredito) {
                llamaSP.setNumeroParametros(7);
                llamaSP.setListParametrosEntrada(new ArrayList<>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo", item.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_cod_lin_cre_sol_tip_gar", lineaCreditoSolicitudTipoGarantia.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", item.getCodigoPersona()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_cobertura_dada", item.getCoberturaDada()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario_reg", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_vigente", String.valueOf(item.getVigente())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_eliminado", String.valueOf(item.getEliminado())});
                llamaSP.setListParametrosSalida(new ArrayList<>());
                llamaSP.invocaSP();
                if (!llamaSP.isEjecucionCorrecta()) {
                    llamaSP.rollback();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                    return false;
                }
            }
            llamaSP.commit();
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "editaGarantes", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     * *
     * Metodo para imprimir el contrato
     */
    public void imprimirContratoEnPDF() {
        try {
            String nombreReporte;
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String directoriReporte = "/financiero/lineaCreditos/lineaCreditoSolicitud/reportelinea/";
            setGeneraReporte(new GeneraReporte());
            getGeneraReporte().setParametros(new HashMap<>());
            getGeneraReporte().getParametros().put("codigoLineaCredito", lineaCreditoSolicitud.getCodigo());
            String reporteAbsoluto = externalContext.getRealPath(directoriReporte + "contratoCrediAmigo.jasper");
            String nombreCortado = reporteAbsoluto.substring(0, reporteAbsoluto.indexOf("contratoCrediAmigo.jasper"));
            getGeneraReporte().getParametros().put("contratoCrediAmigo1", nombreCortado);
            nombreReporte = "contratoCrediAmigo";
            getGeneraReporte().exporta(directoriReporte, nombreReporte, nombreReporte + new java.util.Date().getTime() + ".pdf", "PDF", externalContext, facesContext);
            
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "imprimirContratoEnPDF", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * *
     * Metodo para imprimir la solicitud
     */
    public void imprimirSolicitudEnPDF() {
        try {
            String nombreReporte;
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String directoriReporte = "/financiero/lineaCreditos/lineaCreditoSolicitud/reportelinea/";
            setGeneraReporte(new GeneraReporte());
            getGeneraReporte().setParametros(new HashMap<>());
            getGeneraReporte().getParametros().put("codigoLineaCredito", lineaCreditoSolicitud.getCodigo());
            getGeneraReporte().getParametros().put("codigoIfip", lineaCreditoSolicitud.getCodigoIfip());
            String reporteAbsoluto = externalContext.getRealPath(directoriReporte + "solicitudLineaCredito.jasper");
            String nombreCortado = reporteAbsoluto.substring(0, reporteAbsoluto.indexOf("solicitudLineaCredito.jasper"));
            getGeneraReporte().getParametros().put("contratoCrediAmigo1", nombreCortado);
            nombreReporte = "solicitudLineaCredito";
            getGeneraReporte().exporta(directoriReporte, nombreReporte, nombreReporte + new java.util.Date().getTime() + ".pdf", "PDF", externalContext, facesContext);
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "imprimirSolicitudEnPDF", CapturaError.getErrorException(ex)});
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
            getGeneraReporte().getParametros().put("codigoLineaCredito", lineaCreditoSolicitud.getCodigo());
            String reporteAbsoluto = externalContext.getRealPath(directoriReporte + "pagareLineaCredito.jasper");
            String nombreCortado = reporteAbsoluto.substring(0, reporteAbsoluto.indexOf("pagareLineaCredito.jasper"));
            getGeneraReporte().getParametros().put("contratoCrediAmigo1", nombreCortado);
            nombreReporte = "pagareLineaCredito";
            getGeneraReporte().exporta(directoriReporte, nombreReporte, nombreReporte + new java.util.Date().getTime() + ".pdf", "PDF", externalContext, facesContext);
        }catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "imprimirPagareEnPDF", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * 
     */
    public void imprimirInformeEnPDF(){
        try{
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
        }catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "imprimirInformeEnPDF", CapturaError.getErrorException(ex)});
        }
    }
    
    /***
     * Metodo para validar que no existen procesos de linea en curso o lineas activas
     * @return 
     */
    public boolean validaOtroProcesoLinea() {
        List<LineaCreditoSolicitud> lista = ejbFacade.getSolicitudesEnProceso(getSocio().getSocioPK().getCodigo());
        if(lista != null){
            if (!lista.isEmpty()) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SolicitudEnProceso"));
                return false;
            }else
                return true;
        }else
            return true;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setCodigoSocio(Socio socio) {
        this.setSocio(socio);
    }

    public boolean isInactivaBusquedaSocioNombre() {
        return inactivaBusquedaSocioNombre;
    }

    public void setInactivaBusquedaSocioNombre(boolean inactivaBusquedaSocioNombre) {
        this.inactivaBusquedaSocioNombre = inactivaBusquedaSocioNombre;
    }

    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitud() {
        return listaLineaCreditoSolicitud;
    }

    public void setListaLineaCreditoSolicitud(List<LineaCreditoSolicitud> listaLineaCreditoSolicitud) {
        this.listaLineaCreditoSolicitud = listaLineaCreditoSolicitud;
    }

    public LineaCreditoSolicitud getLineaCreditoSolicitud() {
        return lineaCreditoSolicitud;
    }

    public void setLineaCreditoSolicitud(LineaCreditoSolicitud lineaCreditoSolicitud) {
        this.lineaCreditoSolicitud = lineaCreditoSolicitud;
    }

    public String getNombreCompletoSocio() {
        return nombreCompletoSocio;
    }

    public void setNombreCompletoSocio(String nombreCompletoSocio) {
        this.nombreCompletoSocio = nombreCompletoSocio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LineaCredito getLineaCredito() {
        return lineaCredito;
    }

    public void setLineaCredito(LineaCredito lineaCredito) {
        this.lineaCredito = lineaCredito;
    }

    public List<LineaCredito> getListaLineaCredito() {
        return listaLineaCredito;
    }

    public void setListaLineaCredito(List<LineaCredito> listaLineaCredito) {
        this.listaLineaCredito = listaLineaCredito;
    }

    public boolean isEdicion() {
        return edicion;
    }

    public void setEdicion(boolean edicion) {
        this.edicion = edicion;
    }

    public CanalServicioIfip getCanalServicio() {
        return canalServicio;
    }

    public void setCanalServicio(CanalServicioIfip canalServicio) {
        this.canalServicio = canalServicio;
    }

    public List<CanalServicioIfip> getListaCanalServicio() {
        return listaCanalServicio;
    }

    public void setListaCanalServicio(List<CanalServicioIfip> listaCanalServicio) {
        this.listaCanalServicio = listaCanalServicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Cuenta> getListaSocioCuenta() {
        return listaSocioCuenta;
    }

    public void setListaSocioCuenta(List<Cuenta> listaSocioCuenta) {
        this.listaSocioCuenta = listaSocioCuenta;
    }

    public SocioFlujoCaja getSocioFlujoCaja() {
        return socioFlujoCaja;
    }

    public void setSocioFlujoCaja(SocioFlujoCaja socioFlujoCaja) {
        this.socioFlujoCaja = socioFlujoCaja;
    }

    public SocioSituacionPatrimonial getSocioSituacionPatrimonial() {
        return socioSituacionPatrimonial;
    }

    public void setSocioSituacionPatrimonial(SocioSituacionPatrimonial socioSituacionPatrimonial) {
        this.socioSituacionPatrimonial = socioSituacionPatrimonial;
    }

    public String getFoco() {
        return foco;
    }

    public void setFoco(String foco) {
        this.foco = foco;
    }

    public Persona getPersonaConyugeSocio() {
        return personaConyugeSocio;
    }

    public void setPersonaConyugeSocio(Persona personaConyugeSocio) {
        this.personaConyugeSocio = personaConyugeSocio;
    }

    public boolean isHabilitarBotonEditarConyuge() {
        return habilitarBotonEditarConyuge;
    }

    public void setHabilitarBotonEditarConyuge(boolean habilitarBotonEditarConyuge) {
        this.habilitarBotonEditarConyuge = habilitarBotonEditarConyuge;
    }

    public boolean isHabilitarBotonEliminarConyuge() {
        return habilitarBotonEliminarConyuge;
    }

    public void setHabilitarBotonEliminarConyuge(boolean habilitarBotonEliminarConyuge) {
        this.habilitarBotonEliminarConyuge = habilitarBotonEliminarConyuge;
    }

    public String getTipoCuentaSolicitudSeleccionada() {
        return tipoCuentaSolicitudSeleccionada;
    }

    public void setTipoCuentaSolicitudSeleccionada(String tipoCuentaSolicitudSeleccionada) {
        this.tipoCuentaSolicitudSeleccionada = tipoCuentaSolicitudSeleccionada;
    }

    public Cuenta getCuentaSolicitudSeleccionada() {
        return cuentaSolicitudSeleccionada;
    }

    public void setCuentaSolicitudSeleccionada(Cuenta cuentaSolicitudSeleccionada) {
        this.cuentaSolicitudSeleccionada = cuentaSolicitudSeleccionada;
    }

    public List<Cuenta> getListaSocioCuentasVista() {
        return listaSocioCuentasVista;
    }

    public void setListaSocioCuentasVista(List<Cuenta> listaSocioCuentasVista) {
        this.listaSocioCuentasVista = listaSocioCuentasVista;
    }

    public List<LineaCreditoSolicitud> getListaCuentasSolicitudAgredadas() {
        return listaCuentasSolicitudAgredadas;
    }

    public void setListaCuentasSolicitudAgredadas(List<LineaCreditoSolicitud> listaCuentasSolicitudAgredadas) {
        this.listaCuentasSolicitudAgredadas = listaCuentasSolicitudAgredadas;
    }

    public ActividadEconomica getActividadEconomicaN1() {
        return actividadEconomicaN1;
    }

    public void setActividadEconomicaN1(ActividadEconomica actividadEconomicaN1) {
        this.actividadEconomicaN1 = actividadEconomicaN1;
    }

    public List<ActividadEconomica> getListaActividadEconomicaN1() {
        return listaActividadEconomicaN1;
    }

    public void setListaActividadEconomicaN1(List<ActividadEconomica> listaActividadEconomicaN1) {
        this.listaActividadEconomicaN1 = listaActividadEconomicaN1;
    }

    public Ifip getIfip() {
        return ifip;
    }

    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

    public String getValoresLong() {
        return valoresLong;
    }

    public void setValoresLong(String valoresLong) {
        this.valoresLong = valoresLong;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public boolean isBuscaSolicitudes() {
        return buscaSolicitudes;
    }

    public void setBuscaSolicitudes(boolean buscaSolicitudes) {
        this.buscaSolicitudes = buscaSolicitudes;
    }

    public int getMesesMaxUltimaActualizacion() {
        return mesesMaxUltimaActualizacion;
    }

    public void setMesesMaxUltimaActualizacion(int mesesMaxUltimaActualizacion) {
        this.mesesMaxUltimaActualizacion = mesesMaxUltimaActualizacion;
    }

    public ParametroGeneralCreditoIfip getParametroGeneralCreditoIfip() {
        return parametroGeneralCreditoIfip;
    }

    public void setParametroGeneralCreditoIfip(ParametroGeneralCreditoIfip parametroGeneralCreditoIfip) {
        this.parametroGeneralCreditoIfip = parametroGeneralCreditoIfip;
    }

    public ActividadEconomica getActividadEconomicaN2() {
        return actividadEconomicaN2;
    }

    public void setActividadEconomicaN2(ActividadEconomica actividadEconomicaN2) {
        this.actividadEconomicaN2 = actividadEconomicaN2;
    }

    public ActividadEconomica getActividadEconomicaN3() {
        return actividadEconomicaN3;
    }

    public void setActividadEconomicaN3(ActividadEconomica actividadEconomicaN3) {
        this.actividadEconomicaN3 = actividadEconomicaN3;
    }

    public ActividadEconomica getActividadEconomicaN4() {
        return actividadEconomicaN4;
    }

    public void setActividadEconomicaN4(ActividadEconomica actividadEconomicaN4) {
        this.actividadEconomicaN4 = actividadEconomicaN4;
    }

    public ActividadEconomica getActividadEconomicaN5() {
        return actividadEconomicaN5;
    }

    public void setActividadEconomicaN5(ActividadEconomica actividadEconomicaN5) {
        this.actividadEconomicaN5 = actividadEconomicaN5;
    }

    public ActividadEconomica getActividadEconomicaN6() {
        return actividadEconomicaN6;
    }

    public void setActividadEconomicaN6(ActividadEconomica actividadEconomicaN6) {
        this.actividadEconomicaN6 = actividadEconomicaN6;
    }

    public List<ActividadEconomica> getListaActividadEconomicaN2() {
        return listaActividadEconomicaN2;
    }

    public void setListaActividadEconomicaN2(List<ActividadEconomica> listaActividadEconomicaN2) {
        this.listaActividadEconomicaN2 = listaActividadEconomicaN2;
    }

    public List<ActividadEconomica> getListaActividadEconomicaN3() {
        return listaActividadEconomicaN3;
    }

    public void setListaActividadEconomicaN3(List<ActividadEconomica> listaActividadEconomicaN3) {
        this.listaActividadEconomicaN3 = listaActividadEconomicaN3;
    }

    public List<ActividadEconomica> getListaActividadEconomicaN4() {
        return listaActividadEconomicaN4;
    }

    public void setListaActividadEconomicaN4(List<ActividadEconomica> listaActividadEconomicaN4) {
        this.listaActividadEconomicaN4 = listaActividadEconomicaN4;
    }

    public List<ActividadEconomica> getListaActividadEconomicaN5() {
        return listaActividadEconomicaN5;
    }

    public void setListaActividadEconomicaN5(List<ActividadEconomica> listaActividadEconomicaN5) {
        this.listaActividadEconomicaN5 = listaActividadEconomicaN5;
    }

    public List<ActividadEconomica> getListaActividadEconomicaN6() {
        return listaActividadEconomicaN6;
    }

    public void setListaActividadEconomicaN6(List<ActividadEconomica> listaActividadEconomicaN6) {
        this.listaActividadEconomicaN6 = listaActividadEconomicaN6;
    }

    public List<DestinoFinanciero> getListaDestinoFinanciero() {
        return listaDestinoFinanciero;
    }

    public void setListaDestinoFinanciero(List<DestinoFinanciero> listaDestinoFinanciero) {
        this.listaDestinoFinanciero = listaDestinoFinanciero;
    }

    public UbicacionGeografica getUbicacionGeograficaPais() {
        return ubicacionGeograficaPais;
    }

    public void setUbicacionGeograficaPais(UbicacionGeografica ubicacionGeograficaPais) {
        this.ubicacionGeograficaPais = ubicacionGeograficaPais;
    }

    public UbicacionGeografica getUbicacionGeograficaProvincia() {
        return ubicacionGeograficaProvincia;
    }

    public void setUbicacionGeograficaProvincia(UbicacionGeografica ubicacionGeograficaProvincia) {
        this.ubicacionGeograficaProvincia = ubicacionGeograficaProvincia;
    }

    public UbicacionGeografica getUbicacionGeograficaCiudad() {
        return ubicacionGeograficaCiudad;
    }

    public void setUbicacionGeograficaCiudad(UbicacionGeografica ubicacionGeograficaCiudad) {
        this.ubicacionGeograficaCiudad = ubicacionGeograficaCiudad;
    }

    public UbicacionGeografica getUbicacionGeograficaParroquia() {
        return ubicacionGeograficaParroquia;
    }

    public void setUbicacionGeograficaParroquia(UbicacionGeografica ubicacionGeograficaParroquia) {
        this.ubicacionGeograficaParroquia = ubicacionGeograficaParroquia;
    }

    public List<UbicacionGeografica> getListaUbicacionGeograficaPais() {
        return listaUbicacionGeograficaPais;
    }

    public void setListaUbicacionGeograficaPais(List<UbicacionGeografica> listaUbicacionGeograficaPais) {
        this.listaUbicacionGeograficaPais = listaUbicacionGeograficaPais;
    }

    public List<UbicacionGeografica> getListaUbicacionGeograficaProvincia() {
        return listaUbicacionGeograficaProvincia;
    }

    public void setListaUbicacionGeograficaProvincia(List<UbicacionGeografica> listaUbicacionGeograficaProvincia) {
        this.listaUbicacionGeograficaProvincia = listaUbicacionGeograficaProvincia;
    }

    public List<UbicacionGeografica> getListaUbicacionGeograficaCiudad() {
        return listaUbicacionGeograficaCiudad;
    }

    public void setListaUbicacionGeograficaCiudad(List<UbicacionGeografica> listaUbicacionGeograficaCiudad) {
        this.listaUbicacionGeograficaCiudad = listaUbicacionGeograficaCiudad;
    }

    public List<UbicacionGeografica> getListaUbicacionGeograficaParroquia() {
        return listaUbicacionGeograficaParroquia;
    }

    public void setListaUbicacionGeograficaParroquia(List<UbicacionGeografica> listaUbicacionGeograficaParroquia) {
        this.listaUbicacionGeograficaParroquia = listaUbicacionGeograficaParroquia;
    }

    public TasaInteresProCreMonIfi getTasaLineaCredito() {
        return tasaLineaCredito;
    }

    public void setTasaLineaCredito(TasaInteresProCreMonIfi tasaLineaCredito) {
        this.tasaLineaCredito = tasaLineaCredito;
    }

    public List<OrigenRecursos> getListaOrigenRecursos() {
        return listaOrigenRecursos;
    }

    public void setListaOrigenRecursos(List<OrigenRecursos> listaOrigenRecursos) {
        this.listaOrigenRecursos = listaOrigenRecursos;
    }

    public DestinoFinanciero getDestinoFinanciero() {
        return destinoFinanciero;
    }

    public void setDestinoFinanciero(DestinoFinanciero destinoFinanciero) {
        this.destinoFinanciero = destinoFinanciero;
    }

    public EnvioCanalServicioIfip getEnvioCanalServicioIfip() {
        return envioCanalServicioIfip;
    }

    public void setEnvioCanalServicioIfip(EnvioCanalServicioIfip envioCanalServicioIfip) {
        this.envioCanalServicioIfip = envioCanalServicioIfip;
    }

    public boolean isHabilitaEdicion() {
        return habilitaEdicion;
    }

    public void setHabilitaEdicion(boolean habilitaEdicion) {
        this.habilitaEdicion = habilitaEdicion;
    }

    public int getGarantesRequeridos() {
        return garantesRequeridos;
    }

    public void setGarantesRequeridos(int garantesRequeridos) {
        this.garantesRequeridos = garantesRequeridos;
    }

    public boolean getGarantiasObligatorias() {
        return garantiasObligatorias;
    }

    public void setGarantiasObligatorias(boolean garantiasObligatorias) {
        this.garantiasObligatorias = garantiasObligatorias;
    }

    public String getIdentificacionGarante() {
        return identificacionGarante;
    }

    public void setIdentificacionGarante(String identificacionGarante) {
        this.identificacionGarante = identificacionGarante;
    }

    public boolean isHabilitarBotonEditarConyugeGarante() {
        return habilitarBotonEditarConyugeGarante;
    }

    public void setHabilitarBotonEditarConyugeGarante(boolean habilitarBotonEditarConyugeGarante) {
        this.habilitarBotonEditarConyugeGarante = habilitarBotonEditarConyugeGarante;
    }

    public boolean isHabilitarBotonEliminarConyugeGarante() {
        return habilitarBotonEliminarConyugeGarante;
    }

    public void setHabilitarBotonEliminarConyugeGarante(boolean habilitarBotonEliminarConyugeGarante) {
        this.habilitarBotonEliminarConyugeGarante = habilitarBotonEliminarConyugeGarante;
    }

    public Persona getPersonaConyugeGarante() {
        return personaConyugeGarante;
    }

    public void setPersonaConyugeGarante(Persona personaConyugeGarante) {
        this.personaConyugeGarante = personaConyugeGarante;
    }

    public PersonaNatural getPersonaNaturalGarante() {
        return personaNaturalGarante;
    }

    public void setPersonaNaturalGarante(PersonaNatural personaNaturalGarante) {
        this.personaNaturalGarante = personaNaturalGarante;
    }

    public BigDecimal getCoberturaGarante() {
        return coberturaGarante;
    }

    public void setCoberturaGarante(BigDecimal coberturaGarante) {
        this.coberturaGarante = coberturaGarante;
    }

    public List<LineaCreditoSolicitudGar> getListaGaranteLineaCredito() {
        return listaGaranteLineaCredito;
    }

    public void setListaGaranteLineaCredito(List<LineaCreditoSolicitudGar> listaGaranteLineaCredito) {
        this.listaGaranteLineaCredito = listaGaranteLineaCredito;
    }

    public int getGarantiasMaximasPorPersona() {
        return garantiasMaximasPorPersona;
    }

    public void setGarantiasMaximasPorPersona(int garantiasMaximasPorPersona) {
        this.garantiasMaximasPorPersona = garantiasMaximasPorPersona;
    }

    public List<LineaCreditoSolTipGar> getListaLineaCreditoSolicitudTipoGarantia() {
        return listaLineaCreditoSolicitudTipoGarantia;
    }

    public void setListaLineaCreditoSolicitudTipoGarantia(List<LineaCreditoSolTipGar> listaLineaCreditoSolicitudTipoGarantia) {
        this.listaLineaCreditoSolicitudTipoGarantia = listaLineaCreditoSolicitudTipoGarantia;
    }

    public boolean isProcesoExitoso() {
        return procesoExitoso;
    }

    public void setProcesoExitoso(boolean procesoExitoso) {
        this.procesoExitoso = procesoExitoso;
    }

    public boolean isProcesoError() {
        return procesoError;
    }

    public void setProcesoError(boolean procesoError) {
        this.procesoError = procesoError;
    }
    
    public GeneraReporte getGeneraReporte() {
        return generaReporte;
    }

    public void setGeneraReporte(GeneraReporte generaReporte) {
        this.generaReporte = generaReporte;
    }

    public List<DestinoEspecifico> getListaDestinoEspecifico() {
        return listaDestinoEspecifico;
    }

    public void setListaDestinoEspecifico(List<DestinoEspecifico> listaDestinoEspecifico) {
        this.listaDestinoEspecifico = listaDestinoEspecifico;
    }

}