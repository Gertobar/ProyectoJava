/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.control.ahorro.programado;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import ec.mss.entidad.RespuestaServicio;
import ec.mss.entidad.ahorro.programado.ContratoAhorroProgramado;
import ec.mss.web.consumo.ahorro.ContratoAhorroProgramadoServicio;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.springframework.http.HttpStatus;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import ec.mss.entidad.Catalogo;
import ec.mss.entidad.ahorro.programado.PenalidadContratoAhorroResumen;
import ec.mss.entidad.ahorro.programado.TablaAhorroProgramado;
import ec.mss.entidad.ahorro.programado.TablaAhorroProgramadoResumen;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.PersonaNaturalFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author andres
 */
@ViewScoped
@ManagedBean(name = "contratoAhorroProgramadoController")
public class ContratoAhorroProgramadoController implements Serializable {

    @EJB
    private ContratoAhorroProgramadoServicio contratoAhorroProgramadoServicio;
    @EJB
    private PersonaNaturalFacade personaNaturalFacade;
    @EJB
    private CuentaFacade ejbFacadeCuenta;
    @EJB
    private IfipFacade ejbFacadeIfip;
    @EJB
    private PersonaFacade personaFacade;
    private ContratoAhorroProgramado contratoAhorroProgramado;
    private List<ContratoAhorroProgramado> listaContratoAhorroProgramado;
    private RespuestaServicio respuestaServico;
    private JsonObject jsonObject;
    private JsonElement jsonElement;
    private Type type;
    private String nombrePersona;
    private PersonaNatural personaNatural;
    private List<Cuenta> listaCuentaVista;
    private List<Cuenta> listaCuentaProgramado;
    private String foco;
    private Ifip ifip;
    private Persona personaBeneficiario;
    private List<Catalogo> listaAhorroProgramado;
    private List<Catalogo> listaMotivoAhorroProgramado;
    private List<TablaAhorroProgramado> listaTablaAhorroProgramado;
    private List<TablaAhorroProgramadoResumen> listaTablaAhorroProgramadoResumen;
    private List<Cuenta> listaCuenta;
    private GeneraReporte generaReporte;
    private PenalidadContratoAhorroResumen penalidadContratoAhorroResumen;   
    private Long codigoMotivoPrecancelacionAhorroProgramado;
    private List<Catalogo> listaMotivoPrecancelacionAhorroProgramado;
    

    public ContratoAhorroProgramadoController() {
    }

    @PostConstruct
    public void init() {
        try {
            ifip = (Ifip) ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
            foco = "beneficiario";
        } catch (JsonSyntaxException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "init", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     */
    public void buscarContratoPersona() {
        try {
            nombrePersona = "";
            listaContratoAhorroProgramado = null;
            personaNatural = null;
            String valorCampo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("componentePersonaNaturalForm:componenteBuscarPersonaNatural:listaPersonasComponenteForm:buscaPersonaComponenteCodigoPersona");
            if (valorCampo.isEmpty()) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersona"));
                return;
            }
            Long codigoPersona = Long.valueOf(valorCampo);
            if (codigoPersona == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersona"));
                return;
            } else {
                if (codigoPersona.compareTo(Long.valueOf("0")) == 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersona"));
                    return;
                }
            }
            personaNatural = personaNaturalFacade.getPorCodigoPersona(codigoPersona);
            if (personaNatural == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaNoEncontrada"));
                return;
            }
            if (cargarContratoAhorroProgramado(codigoPersona)) {
                nombrePersona = personaNatural.getPersona().getNombreCompleto();
                cargarCuentasSocio();
            }
        } catch (JsonSyntaxException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "buscarContratoPersona", CapturaError.getErrorException(ex)});
            personaNatural = null;
            nombrePersona = "";
            listaContratoAhorroProgramado = null;
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     * @param codigoPersona
     * @return
     */
    public boolean cargarContratoAhorroProgramado(Long codigoPersona) {
        try {
            respuestaServico = contratoAhorroProgramadoServicio.getListaContratoAhorroProgramadoPorPersona(codigoPersona);
            if (respuestaServico.getCodigo() != HttpStatus.OK && respuestaServico.getCodigo() != HttpStatus.NO_CONTENT) {
                MuestraMensaje.addError(respuestaServico.getMensaje().toUpperCase());
                return false;
            } else {
                if (respuestaServico.getCodigo() != HttpStatus.NO_CONTENT) {
                    jsonElement = respuestaServico.getTrama().get("trama");
                    type = new TypeToken<List<ContratoAhorroProgramado>>() {
                    }.getType();
                    listaContratoAhorroProgramado = new Gson().fromJson(jsonElement, type);
                    listaContratoAhorroProgramado.sort(Comparator.comparing(ContratoAhorroProgramado::getCodigo).reversed());
                }
                return true;
            }
        } catch (JsonSyntaxException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "buscarContratoPersona", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void cargarBeneficiario() {
        try {
            personaBeneficiario = null;
            String valorCampo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("componentePersonaForm:componenteBuscarPersona:listaPersonasComponenteForm:buscaPersonaComponenteCodigoPersona");
            if (valorCampo.isEmpty()) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersona"));
                foco = "beneficiario";
                return;
            }
            Long codigoPersona = Long.valueOf(valorCampo);
            if (codigoPersona == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersona"));
                foco = "beneficiario";
                return;
            } else {
                if (codigoPersona.compareTo(Long.valueOf("0")) == 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersona"));
                    foco = "beneficiario";
                    return;
                }
            }
            personaBeneficiario = personaFacade.getPersonaByCodigo(codigoPersona);
            if (personaBeneficiario == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaNoEncontrada"));
                foco = "beneficiario";
                return;
            }
            foco = "cuentaDebito";
        } catch (JsonSyntaxException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "cargarBeneficiario", CapturaError.getErrorException(ex)});
            personaBeneficiario = null;
            foco = "beneficiario";
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     */
    public void cargarCuentasSocio() {
        try {
            listaCuenta = ejbFacadeCuenta.getItemsCodigoPersonaCodigoIfipCodigoEstado(personaNatural.getCodigoPersona(), ActivacionUsuario.getCodigoIfip(), Long.valueOf("1"));
            if (listaCuenta != null) {
                listaCuentaVista = listaCuenta;
                listaCuentaVista = listaCuentaVista.stream().filter(p -> p.getCodigoTipoProducto() == 2).collect(Collectors.toList());
            } else {
                listaCuentaVista = null;
                listaCuentaProgramado = null;
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "cargarCuentasSocio", CapturaError.getErrorException(ex)});
            personaNatural = null;
            nombrePersona = "";
            listaContratoAhorroProgramado = null;
            listaCuentaVista = null;
            listaCuentaProgramado = null;
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAlCargarCuentasPersona"));
        }
    }

    /**
     *
     */
    public void cargaCuentasAhorroProgramado() {
        try {
            listaCuentaProgramado = null;
            if ((listaCuenta != null) && (contratoAhorroProgramado.getCodigoAhorroProgramado() != null)) {                
                List<Catalogo> l = listaAhorroProgramado.stream().filter(p -> Objects.equals(p.getCodigo(), contratoAhorroProgramado.getCodigoAhorroProgramado())).collect(Collectors.toList());
                listaCuentaProgramado = listaCuenta.stream().filter(p -> p.getCodigoTipoProducto() == Long.valueOf(l.get(0).getTipoProducto())).collect(Collectors.toList());
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "cargarCuentasSocio", CapturaError.getErrorException(ex)});
            listaCuentaProgramado = null;
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("cargaCuentasAhorroProgramado"));
        }
    }

    /**
     *
     */
    public void cargaTablaContratoAhorroProgramado() {
        try {
            listaTablaAhorroProgramadoResumen = null;
            contratoAhorroProgramado.setCodigoPeriodicidad(Long.valueOf("4"));
            if (contratoAhorroProgramado.getCodigoAhorroProgramado() == null) {
                return;
            }
            if (contratoAhorroProgramado.getMonto() == null) {
                return;
            }
            if (contratoAhorroProgramado.getMonto().compareTo(BigDecimal.ZERO) < 0) {
                return;
            }
            if (contratoAhorroProgramado.getCodigoPeriodicidad() == null) {
                return;
            }
            if (contratoAhorroProgramado.getCodigoPeriodicidad() <= 0) {
                return;
            }
            if (contratoAhorroProgramado.getNumeroCuotas() <= 0) {
                return;
            }
            if (contratoAhorroProgramado.getDiaPago() <= 0) {
                return;
            }
            respuestaServico = contratoAhorroProgramadoServicio.calculaTablaNuevoContratoAhorro(contratoAhorroProgramado.getCodigoAhorroProgramado(),
                    contratoAhorroProgramado.getMonto(), contratoAhorroProgramado.getCodigoPeriodicidad(), contratoAhorroProgramado.getNumeroCuotas(), contratoAhorroProgramado.getDiaPago());
            if (respuestaServico.getCodigo() != HttpStatus.OK && respuestaServico.getCodigo() != HttpStatus.NO_CONTENT) {
                MuestraMensaje.addError(respuestaServico.getMensaje().toUpperCase());
            } else {
                if (respuestaServico.getCodigo() != HttpStatus.NO_CONTENT) {
                    jsonElement = respuestaServico.getTrama().get("trama");
                    type = new TypeToken<List<TablaAhorroProgramadoResumen>>() {
                    }.getType();
                    listaTablaAhorroProgramadoResumen = new Gson().fromJson(jsonElement, type);
                }
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "cargaTablaContratoAhorroProgramado", CapturaError.getErrorException(ex)});
            listaTablaAhorroProgramadoResumen = null;
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAlCargarCuentasPersona"));
        }
    }

    /**
     *
     * @param actionEvent
     */
    public void nuevo(ActionEvent actionEvent) {
        try {
            contratoAhorroProgramado = new ContratoAhorroProgramado();
            contratoAhorroProgramado.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
            contratoAhorroProgramado.setCodigoIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia());
            contratoAhorroProgramado.setCodigoPersona(personaNatural.getCodigoPersona());
            contratoAhorroProgramado.setCodigoUsuario(ActivacionUsuario.getCodigoUsuario());
            contratoAhorroProgramado.setEstado("V");
            contratoAhorroProgramado.setRenovacionAutomatica("S");
            listaAhorroProgramado = ActivacionUsuario.getCatalogo().stream().filter(p -> p.getGrupo().equals("ahorro-programado")).collect(Collectors.toList());
            listaMotivoAhorroProgramado = listaAhorroProgramado.stream().filter(p -> p.getCatalogo().equals("motivo")).collect(Collectors.toList());
            listaAhorroProgramado = listaAhorroProgramado.stream().filter(p -> p.getCatalogo().equals("tipo")).collect(Collectors.toList());
            listaTablaAhorroProgramadoResumen = null;
            personaBeneficiario = null;
            foco = "beneficiario";
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "nuevo", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     */
    public void guarda() {
        try {
            if (validaCampos()) {
                contratoAhorroProgramado.setCodigoPersonaBeneficiario(personaBeneficiario.getCodigo());
                respuestaServico = contratoAhorroProgramadoServicio.addContratoAhorroProgramado(contratoAhorroProgramado);
                if (respuestaServico.getCodigo() == HttpStatus.OK) {
                    MuestraMensaje.addSatisfactorioPersistencia(1);
                    RequestContext.getCurrentInstance().execute("contratoAhorroProgramadoCreateDialog.hide()");
                    cargarContratoAhorroProgramado(personaNatural.getCodigoPersona());
                } else {
                    MuestraMensaje.addError(respuestaServico.getMensaje().toUpperCase());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "guarda", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     * @return
     */
    public boolean validaCampos() {
        try {
            if (personaBeneficiario == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionarPersonaBeneficiario"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoCuentaDebita() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuentaDebito"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoCuentaDebita().equals(Long.valueOf("0"))) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuentaDebito"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoCuentaAcredita() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuentaCredito"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoCuentaAcredita().equals(Long.valueOf("0"))) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuentaCredito"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoAhorroProgramado() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneAhorroProgramado"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoAhorroProgramado().equals(Long.valueOf("0"))) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneAhorroProgramado"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoMotivoAhorroPro() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneMotivoAhorroProgramado"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoMotivoAhorroPro().equals(Long.valueOf("0"))) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneMotivoAhorroProgramado"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoCuentaAhorroPro() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuentaAhorroProgramado"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoCuentaAhorroPro().equals(Long.valueOf("0"))) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuentaAhorroProgramado"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoAhorroProgramado() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionarAhorroProgramado"));
                return false;
            }
            if (contratoAhorroProgramado.getMonto() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MontoSolicitado"));
                return false;
            }
            if (contratoAhorroProgramado.getMonto().compareTo(BigDecimal.ZERO) < 0) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MontoSolicitado"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoPeriodicidad() == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorPeriodicidad"));
                return false;
            }
            if (contratoAhorroProgramado.getCodigoPeriodicidad() <= 0) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorPeriodicidad"));
                return false;
            }
            if (contratoAhorroProgramado.getNumeroCuotas() <= 0) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroCuotasInvalido"));
                return false;
            }
            if (contratoAhorroProgramado.getDiaPago() <= 0) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DiaPagoInvalido"));
                return false;
            }
            if (listaTablaAhorroProgramadoResumen == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GenerarTablaPagoAhorroProgramado"));
                return false;
            }
            if (personaNatural == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersona"));
                return false;
            }
            if (personaBeneficiario == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionePersonaBeneficiario"));
                return false;
            }
            return true;
        } catch (NumberFormatException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "guarda", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
            return false;
        }
    }

    /**
     *
     */
    public void generaContrato() {
        try {
            String nombreReporte;
            String directoriReporte = "/financiero/ahorros/contratoAhorroProgramado/reporte/";
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            generaReporte = new GeneraReporte();
            generaReporte.setParametros(new HashMap<>());
            generaReporte.getParametros().put("nombre", personaNatural.getPersona().getNombreCompleto());
            generaReporte.getParametros().put("identificacion", personaNatural.getPersona().getIdentificacion());
            generaReporte.getParametros().put("meses", contratoAhorroProgramado.getNumeroCuotas());
            generaReporte.getParametros().put("valor", contratoAhorroProgramado.getMonto());
            generaReporte.getParametros().put("contrato", contratoAhorroProgramado.getCodigo());
            generaReporte.getParametros().put("anio", Long.valueOf("2018"));
            generaReporte.getParametros().put("codigoIfipAgencia", ActivacionUsuario.getCodigoIfipAgencia());
            nombreReporte = "contrato";
            generaReporte.exporta(directoriReporte, nombreReporte,
                     "ContratoAhorroProgramado" + this.contratoAhorroProgramado.getCodigo() + ".pdf",
                    "PDF", externalContext, facesContext);
        } catch (Exception ex) {
            RequestContext.getCurrentInstance().execute("procesandoDlg.hide()");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "generaReporte", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }
    
    /**
     * 
     */
    public void calculaPenalizacion(){
        try {
            codigoMotivoPrecancelacionAhorroProgramado = null;
            listaTablaAhorroProgramado = new ArrayList<>(contratoAhorroProgramado.getTablaAhorroProgramadoSet());
            listaTablaAhorroProgramado.sort((p1, p2) -> p1.getCuota().compareTo(p2.getCuota()));
            listaAhorroProgramado = ActivacionUsuario.getCatalogo().stream().filter(p -> p.getGrupo().equals("ahorro-programado")).collect(Collectors.toList());
            listaMotivoAhorroProgramado = listaAhorroProgramado.stream().filter(p -> p.getCatalogo().equals("motivo")).collect(Collectors.toList());
            listaAhorroProgramado = listaAhorroProgramado.stream().filter(p -> p.getCatalogo().equals("tipo")).collect(Collectors.toList());            
            listaMotivoPrecancelacionAhorroProgramado = ActivacionUsuario.getCatalogo().stream().filter(p -> p.getGrupo().equals("ahorro-programado")).collect(Collectors.toList());
            listaMotivoPrecancelacionAhorroProgramado = listaMotivoPrecancelacionAhorroProgramado.stream().filter(p -> p.getCatalogo().equals("motivo-precancelacion")).collect(Collectors.toList());            
            List<Catalogo> l = listaAhorroProgramado.stream().filter(p -> Objects.equals(p.getCodigo(), contratoAhorroProgramado.getCodigoAhorroProgramado())).collect(Collectors.toList());
            listaCuentaProgramado = listaCuenta.stream().filter(p -> p.getCodigoTipoProducto() == Long.valueOf(l.get(0).getTipoProducto())).collect(Collectors.toList());
            personaBeneficiario = personaFacade.getPersonaByCodigo(contratoAhorroProgramado.getCodigoPersonaBeneficiario());            
            respuestaServico = contratoAhorroProgramadoServicio.calculaPenalizacionContratoAhorro(contratoAhorroProgramado.getCodigo());
            if (respuestaServico.getCodigo() != HttpStatus.OK) {
                MuestraMensaje.addError(respuestaServico.getMensaje().toUpperCase());
            } else {
                jsonObject = respuestaServico.getTrama();                
                ObjectMapper objectMapper = new ObjectMapper();
                penalidadContratoAhorroResumen = objectMapper.readValue(jsonObject.toString(), PenalidadContratoAhorroResumen.class);
            }        
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "calculaPrecancelacion", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }
    
    /**
     * 
     * @return 
     */
    public boolean validaPrecancelacion(){
        try{
            if (penalidadContratoAhorroResumen == null){
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorPenalidadAhorroProgramado"));
                return false;
            }            
            if (codigoMotivoPrecancelacionAhorroProgramado == null){
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorMotivoPrecancelacionAhorroProgramado"));
                return false;
            }
            if (codigoMotivoPrecancelacionAhorroProgramado.compareTo(Long.valueOf("0")) == 0) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorMotivoPrecancelacionAhorroProgramado"));
                return false;
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "validaPrecancelacion", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
            return false;
        }
    }
    
    
    /**
     * 
     */
    public void precancelaContrato(){
        try {
             if (validaPrecancelacion()) {                
                respuestaServico = contratoAhorroProgramadoServicio.precancelacionContratoAhorro( contratoAhorroProgramado.getCodigo() ,
                                                                                                  Short.valueOf(codigoMotivoPrecancelacionAhorroProgramado.toString()),
                                                                                                  ActivacionUsuario.getCodigoUsuario(),
                                                                                                  ActivacionUsuario.getCodigoComputador() ,
                                                                                                  ObtieneInformacionCliente.obtenerDireccionIP());
                if (respuestaServico.getCodigo() == HttpStatus.OK) {
                    MuestraMensaje.addSatisfactorioPersistencia(1);
                    RequestContext.getCurrentInstance().execute("contratoAhorroProgramadoPreCancelaDialog.hide()");
                    penalidadContratoAhorroResumen = null;
                    cargarContratoAhorroProgramado(personaNatural.getCodigoPersona());
                } else {
                    MuestraMensaje.addError(respuestaServico.getMensaje().toUpperCase());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "precancelaContrato", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     * @param actionEvent
     */
    public void muestra(ActionEvent actionEvent) {
        try {
            listaTablaAhorroProgramado = new ArrayList<>(contratoAhorroProgramado.getTablaAhorroProgramadoSet());
            listaTablaAhorroProgramado.sort((p1, p2) -> p1.getCuota().compareTo(p2.getCuota()));
            listaAhorroProgramado = ActivacionUsuario.getCatalogo().stream().filter(p -> p.getGrupo().equals("ahorro-programado")).collect(Collectors.toList());
            listaMotivoAhorroProgramado = listaAhorroProgramado.stream().filter(p -> p.getCatalogo().equals("motivo")).collect(Collectors.toList());
            listaAhorroProgramado = listaAhorroProgramado.stream().filter(p -> p.getCatalogo().equals("tipo")).collect(Collectors.toList());            
            List<Catalogo> l = listaAhorroProgramado.stream().filter(p -> Objects.equals(p.getCodigo(), contratoAhorroProgramado.getCodigoAhorroProgramado())).collect(Collectors.toList());
            listaCuentaProgramado = listaCuenta.stream().filter(p -> p.getCodigoTipoProducto() == Long.valueOf(l.get(0).getTipoProducto())).collect(Collectors.toList());
            personaBeneficiario = personaFacade.getPersonaByCodigo(contratoAhorroProgramado.getCodigoPersonaBeneficiario());
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoController", "muestra", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     * @return the contratoAhorroProgramado
     */
    public ContratoAhorroProgramado getContratoAhorroProgramado() {
        return contratoAhorroProgramado;
    }

    /**
     * @param contratoAhorroProgramado the contratoAhorroProgramado to set
     */
    public void setContratoAhorroProgramado(ContratoAhorroProgramado contratoAhorroProgramado) {
        this.contratoAhorroProgramado = contratoAhorroProgramado;
    }

    /**
     * @return the listaContratoAhorroProgramado
     */
    public List<ContratoAhorroProgramado> getListaContratoAhorroProgramado() {
        return listaContratoAhorroProgramado;
    }

    /**
     * @param listaContratoAhorroProgramado the listaContratoAhorroProgramado to
     * set
     */
    public void setListaContratoAhorroProgramado(List<ContratoAhorroProgramado> listaContratoAhorroProgramado) {
        this.listaContratoAhorroProgramado = listaContratoAhorroProgramado;
    }

    /**
     * @return the nombrePersona
     */
    public String getNombrePersona() {
        return nombrePersona;
    }

    /**
     * @param nombrePersona the nombrePersona to set
     */
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    /**
     * @return the personaNatural
     */
    public PersonaNatural getPersonaNatural() {
        return personaNatural;
    }

    /**
     * @param personaNatural the personaNatural to set
     */
    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }

    /**
     * @return the listaCuentaVista
     */
    public List<Cuenta> getListaCuentaVista() {
        return listaCuentaVista;
    }

    /**
     * @param listaCuentaVista the listaCuentaVista to set
     */
    public void setListaCuentaVista(List<Cuenta> listaCuentaVista) {
        this.listaCuentaVista = listaCuentaVista;
    }

    /**
     * @return the listaCuentaProgramado
     */
    public List<Cuenta> getListaCuentaProgramado() {
        return listaCuentaProgramado;
    }

    /**
     * @param listaCuentaProgramado the listaCuentaProgramado to set
     */
    public void setListaCuentaProgramado(List<Cuenta> listaCuentaProgramado) {
        this.listaCuentaProgramado = listaCuentaProgramado;
    }

    /**
     * @return the foco
     */
    public String getFoco() {
        return foco;
    }

    /**
     * @param foco the foco to set
     */
    public void setFoco(String foco) {
        this.foco = foco;
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
     * @return the listaAhorroProgramado
     */
    public List<Catalogo> getListaAhorroProgramado() {
        return listaAhorroProgramado;
    }

    /**
     * @param listaAhorroProgramado the listaAhorroProgramado to set
     */
    public void setListaAhorroProgramado(List<Catalogo> listaAhorroProgramado) {
        this.listaAhorroProgramado = listaAhorroProgramado;
    }

    /**
     * @return the listaMotivoAhorroProgramado
     */
    public List<Catalogo> getListaMotivoAhorroProgramado() {
        return listaMotivoAhorroProgramado;
    }

    /**
     * @param listaMotivoAhorroProgramado the listaMotivoAhorroProgramado to set
     */
    public void setListaMotivoAhorroProgramado(List<Catalogo> listaMotivoAhorroProgramado) {
        this.listaMotivoAhorroProgramado = listaMotivoAhorroProgramado;
    }

    /**
     * @return the listaTablaAhorroProgramado
     */
    public List<TablaAhorroProgramado> getListaTablaAhorroProgramado() {
        return listaTablaAhorroProgramado;
    }

    /**
     * @param listaTablaAhorroProgramado the listaTablaAhorroProgramado to set
     */
    public void setListaTablaAhorroProgramado(List<TablaAhorroProgramado> listaTablaAhorroProgramado) {
        this.listaTablaAhorroProgramado = listaTablaAhorroProgramado;
    }

    /**
     * @return the listaTablaAhorroProgramadoResumen
     */
    public List<TablaAhorroProgramadoResumen> getListaTablaAhorroProgramadoResumen() {
        return listaTablaAhorroProgramadoResumen;
    }

    /**
     * @param listaTablaAhorroProgramadoResumen the
     * listaTablaAhorroProgramadoResumen to set
     */
    public void setListaTablaAhorroProgramadoResumen(List<TablaAhorroProgramadoResumen> listaTablaAhorroProgramadoResumen) {
        this.listaTablaAhorroProgramadoResumen = listaTablaAhorroProgramadoResumen;
    }
    
    /**
     * 
     * @return penalidadContratoAhorroResumen
     */
    public PenalidadContratoAhorroResumen getPenalidadContratoAhorroResumen() {
        return penalidadContratoAhorroResumen;
    }
    
    /**
     * 
     * @param penalidadContratoAhorroResumen 
     */
    public void setPenalidadContratoAhorroResumen(PenalidadContratoAhorroResumen penalidadContratoAhorroResumen) {
        this.penalidadContratoAhorroResumen = penalidadContratoAhorroResumen;
    }

    /**
     * @return the codigoMotivoPrecancelacionAhorroProgramado
     */
    public Long getCodigoMotivoPrecancelacionAhorroProgramado() {
        return codigoMotivoPrecancelacionAhorroProgramado;
    }

    /**
     * @param codigoMotivoPrecancelacionAhorroProgramado the codigoMotivoPrecancelacionAhorroProgramado to set
     */
    public void setCodigoMotivoPrecancelacionAhorroProgramado(Long codigoMotivoPrecancelacionAhorroProgramado) {
        this.codigoMotivoPrecancelacionAhorroProgramado = codigoMotivoPrecancelacionAhorroProgramado;
    }

    /**
     * @return the listaMotivoPrecancelacionAhorroProgramado
     */
    public List<Catalogo> getListaMotivoPrecancelacionAhorroProgramado() {
        return listaMotivoPrecancelacionAhorroProgramado;
    }

    /**
     * @param listaMotivoPrecancelacionAhorroProgramado the listaMotivoPrecancelacionAhorroProgramado to set
     */
    public void setListaMotivoPrecancelacionAhorroProgramado(List<Catalogo> listaMotivoPrecancelacionAhorroProgramado) {
        this.listaMotivoPrecancelacionAhorroProgramado = listaMotivoPrecancelacionAhorroProgramado;
    }

}
