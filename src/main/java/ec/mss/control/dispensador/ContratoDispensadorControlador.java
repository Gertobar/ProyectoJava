/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.control.dispensador;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import ec.mss.entidad.RespuestaServicio;
import ec.mss.modelo.cdm.ContratoCdm;
import ec.mss.modelo.dispensador.proceso.ContratoCdmRq;
import ec.mss.modelo.dispensador.proceso.ContratoMonederoRq;
import ec.mss.web.consumo.dispensador.DispensadorServicio;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.http.HttpStatus;

/**
 *
 * @author andres
 */
@ViewScoped
@ManagedBean(name = "contratoDispensadorControlador")
public class ContratoDispensadorControlador implements Serializable {

    public ContratoDispensadorControlador() {
    }

    private String foco;
    private Persona persona;

    @PostConstruct
    public void init() {
        try {
            foco = "cuentaDebito";
            habilitaGuardarContrato = false;
            contratoDispensadorNuevo = null;
        } catch (JsonSyntaxException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "init", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    private Long codigoCuenta;
    private List<ContratoCdm> listaContratoDispensador;
    private ContratoCdm contratoDispensador;
    private ContratoCdm contratoDispensadorNuevo;
    private List<Cuenta> listaCuentaVista;
    private BigDecimal montoMaximoDispensar;
    private BigDecimal montoMaximoDispensarMen;
    private boolean habilitaGuardarContrato;

    /**
     *
     */
    public void nuevo() {
        habilitaGuardarContrato = false;
        try {
            contratoDispensadorNuevo = new ContratoCdm();
            cargarCuentasSocio();
            if (listaCuentaVista != null) {
                habilitaGuardarContrato = true;
            } else {
                MuestraMensaje.addAdvertencia("No existe registro de cuentas");
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "nuevo", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     */
    public void edita() {
        try {
            contratoDispensadorNuevo = null;
            if (contratoDispensador.getMonederoList() != null) {
                if (contratoDispensador.getMonederoList().size() > 0) {
                    codigoCuenta = contratoDispensador.getMonederoList().get(0).getCodigoCuenta();
                    montoMaximoDispensar = contratoDispensador.getMonederoList().get(0).getMontoMaximoADispensar();
                    montoMaximoDispensarMen = contratoDispensador.getMonederoList().get(0).getMontoMaximoADispensarMen();
                    cargarCuentasSocio();
                    if (listaCuentaVista != null) {
                        habilitaGuardarContrato = true;
                    } else {
                        MuestraMensaje.addAdvertencia("No existe registro de cuentas");
                    }
                }
            }
        } catch (JsonSyntaxException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "edita", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    /**
     *
     */
    public void cargarCuentasSocio() {
        try {
            listaCuentaVista = ejbFacadeCuenta.getItemsCodigoPersonaCodigoIfipCodigoEstado(persona.getCodigo(), ActivacionUsuario.getCodigoIfip(), Long.valueOf("1"));
            if (listaCuentaVista != null) {
                listaCuentaVista = listaCuentaVista.stream().filter(p -> p.getCodigoTipoProducto() == 2).collect(Collectors.toList());
            } else {
                listaCuentaVista = null;
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "cargarCuentasSocio", CapturaError.getErrorException(ex)});
            listaCuentaVista = null;
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAlCargarCuentasPersona"));
        }
    }

    private String estadoVinculo;

    /**
     *
     * @param event
     */
    public void rowSelected(SelectEvent event) {
        try {
            if (contratoDispensador.getMonederoList() != null) {
                if (contratoDispensador.getMonederoList().size() > 0) {
                    estadoVinculo = contratoDispensador.getMonederoList().get(0).getIdentificadorVinculo().replace(" ", "");
                } else {
                    estadoVinculo = "";
                }
            } else {
                estadoVinculo = "";
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "rowSelected", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     * @param event
     */
    public void rowUnSelected(UnselectEvent event) {
        try {
            estadoVinculo = "";
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "rowUnSelected", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     */
    public void vincularContrato() {
        try {
            codigoMonedero = contratoDispensador.getMonederoList().get(0).getCodigo();
            token = null;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "vincularContrato", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    @EJB
    private PersonaFacade personaFacade;

    /**
     *
     */
    public void cargarPersona() {
        try {
            persona = null;
            listaContratoDispensador = null;
            String valorCampo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("componentePersonaForm:componenteBuscarPersona:listaPersonasComponenteForm:buscaPersonaComponenteCodigoPersona");
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
            persona = personaFacade.getPersonaByCodigo(codigoPersona);
            if (persona == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaNoEncontrada"));
            }
            mensaje = cargarContratos();
            if (mensaje != null) {
                MuestraMensaje.addAdvertencia(mensaje);
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "cargarPersona", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    @EJB
    private DispensadorServicio dispensadorServicio;
    private RespuestaServicio respuestaServicio;
    private JsonElement jsonElement;
    private Type type;

    /**
     *
     * @return
     */
    public String cargarContratos() {
        try {
            respuestaServicio = dispensadorServicio.obtieneContratoCdmPersona(persona.getCodigo());
            if (respuestaServicio.getCodigo() != HttpStatus.OK && respuestaServicio.getCodigo() != HttpStatus.NO_CONTENT) {
                persona = null;
                return respuestaServicio.getMensaje().toUpperCase();
            } else {
                if (respuestaServicio.getCodigo() != HttpStatus.NO_CONTENT) {
                    jsonElement = respuestaServicio.getTrama().get("contratoCdm");
                    type = new TypeToken<List<ContratoCdm>>() {
                    }.getType();
                    listaContratoDispensador = new Gson().fromJson(jsonElement, type);
                    if (listaContratoDispensador == null) {
                        return "No existen registros";
                    }
                    listaContratoDispensador.sort(Comparator.comparing(ContratoCdm::getCodigo).reversed());
                }
                return null;
            }
        } catch (JsonSyntaxException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "cargarContratos", CapturaError.getErrorException(ex)});
            persona = null;
            return "Error al cargar el contrato";
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "cargarContratos", CapturaError.getErrorException(ex)});
            persona = null;
            return "Error al cargar el contrato";
        }
    }

    private ContratoCdmRq contratoCdmRq;
    private ContratoMonederoRq contratoMonederoRq;
    private String mensaje;

    /**
     *
     */
    public void guarda() {
        try {
            if (contratoDispensadorNuevo != null) {
                if (validaNuevoContrato()) {
                    contratoCdmRq = new ContratoCdmRq();
                    contratoCdmRq.setCodigoCanalSevicio(Long.valueOf("1"));
                    contratoCdmRq.setCodigoPersona(persona.getCodigo());
                    contratoCdmRq.setCodigoIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia());
                    contratoCdmRq.setCodigoUsuario(ActivacionUsuario.getCodigoUsuario());
                    contratoCdmRq.setEstado("C");
                    contratoCdmRq.setCelular(contratoDispensadorNuevo.getCelular());
                    contratoCdmRq.setCorreoElectronico(contratoDispensadorNuevo.getCorreoElectronico());
                    contratoMonederoRq = new ContratoMonederoRq();
                    contratoMonederoRq.setCodigoCuenta(codigoCuenta);
                    contratoMonederoRq.setIdentificadorVinculo("X");
                    contratoMonederoRq.setSaldo(null);
                    contratoMonederoRq.setSaldoMinimo(null);
                    contratoMonederoRq.setMontoMaximoDispensar(montoMaximoDispensar);
                    contratoMonederoRq.setMontoMaximoDispensarMen(montoMaximoDispensarMen);
                    contratoMonederoRq.setUltimaIdPeticion(null);
                    contratoMonederoRq.setIdentificadorPropietario(null);
                    contratoCdmRq.setContratoMonederoRq(contratoMonederoRq);
                    respuestaServicio = dispensadorServicio.guardaNuevoContrato(contratoCdmRq);
                    if (respuestaServicio.getCodigo() == HttpStatus.OK) {
                        MuestraMensaje.addSatisfactorioPersistencia(1);
                        RequestContext.getCurrentInstance().execute("contratoDispensadorCreateDialog.hide()");
                        mensaje = cargarContratos();
                        if (mensaje != null) {
                            MuestraMensaje.addAdvertencia(mensaje);
                        }
                    } else {
                        MuestraMensaje.addError(respuestaServicio.getMensaje().toUpperCase());
                    }
                }
            } else {
                if (validaEditaContrato()) {
                    respuestaServicio = dispensadorServicio.guardaEdicionContrato(contratoDispensador.getCodigo(), montoMaximoDispensar, montoMaximoDispensarMen);
                    if (respuestaServicio.getCodigo() == HttpStatus.OK) {
                        MuestraMensaje.addSatisfactorioPersistencia(1);
                        RequestContext.getCurrentInstance().execute("contratoDispensadorEditDialog.hide()");
                        mensaje = cargarContratos();
                        if (mensaje != null) {
                            MuestraMensaje.addAdvertencia(mensaje);
                        }
                    } else {
                        MuestraMensaje.addError(respuestaServicio.getMensaje().toUpperCase());
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "guarda", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     * @return
     */
    public boolean validaNuevoContrato() {
        try {
            if (persona == null) {
                MuestraMensaje.addError("Debe seleccionar una persona");
                return false;
            }
            if (persona.getCodigo() == null) {
                MuestraMensaje.addError("Debe seleccionar una persona");
                return false;
            }
            if (contratoDispensadorNuevo == null) {
                MuestraMensaje.addError("Ingrese los datos del nuevo contrato");
                return false;
            }
            if (contratoDispensadorNuevo.getCelular().isEmpty()) {
                MuestraMensaje.addError("Ingrese el celular del nuevo contrato");
                return false;
            }
            if (contratoDispensadorNuevo.getCorreoElectronico().isEmpty()) {
                MuestraMensaje.addError("Ingrese el correo electrónico del nuevo contrato");
                return false;
            }
            if (codigoCuenta == null) {
                MuestraMensaje.addError("Seleccione la cuenta del nuevo contrato");
                return false;
            }
            if (codigoCuenta.compareTo(Long.valueOf("0")) == 0) {
                MuestraMensaje.addError("Seleccione la cuenta del nuevo contrato");
                return false;
            }
            if (montoMaximoDispensar == null) {
                MuestraMensaje.addError("Ingresar monto maximo dispensar del nuevo contrato");
                return false;
            }
            if (montoMaximoDispensar.compareTo(BigDecimal.ZERO) == 0) {
                MuestraMensaje.addError("Ingresar monto maximo dispensar del nuevo contrato");
                return false;
            }
            if (montoMaximoDispensarMen == null) {
                MuestraMensaje.addError("Ingresar monto maximo mensual dispensar del nuevo contrato");
                return false;
            }
            if (montoMaximoDispensarMen.compareTo(BigDecimal.ZERO) == 0) {
                MuestraMensaje.addError("Ingresar monto maximo mensual dispensar del nuevo contrato");
                return false;
            }
            if (montoMaximoDispensar.compareTo(montoMaximoDispensarMen) == 1) {
                MuestraMensaje.addError("El monto maximo mensual debe ser mayor que el monto maximo");
                return false;
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "validaNuevoContrato", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validaEditaContrato() {
        try {
            if (contratoDispensador == null) {
                MuestraMensaje.addError("Seleccione contrato");
                return false;
            }
            if (montoMaximoDispensar == null) {
                MuestraMensaje.addError("Ingresar monto maximo dispensar del nuevo contrato");
                return false;
            }
            if (montoMaximoDispensar.compareTo(BigDecimal.ZERO) == 0) {
                MuestraMensaje.addError("Ingresar monto maximo dispensar del nuevo contrato");
                return false;
            }
            if (montoMaximoDispensarMen == null) {
                MuestraMensaje.addError("Ingresar monto maximo mensual dispensar del nuevo contrato");
                return false;
            }
            if (montoMaximoDispensarMen.compareTo(BigDecimal.ZERO) == 0) {
                MuestraMensaje.addError("Ingresar monto maximo mensual dispensar del nuevo contrato");
                return false;
            }
            if (montoMaximoDispensar.compareTo(montoMaximoDispensarMen) == 1) {
                MuestraMensaje.addError("El monto maximo mensual debe ser mayor que el monto maximo");
                return false;
            }
            if (contratoDispensador.getEstado() == null) {
                MuestraMensaje.addError("Debe seleccionar estado contrato");
                return false;
            }
            if (contratoDispensador.getEstado().isEmpty()) {
                MuestraMensaje.addError("Debe seleccionar estado contrato");
                return false;
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "validaEditaContrato", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
            return false;
        }
    }

    private Long codigoMonedero;
    private String token;

    /**
     *
     */
    public void guardaContratoVinculacion() {
        try {
            if (validaContratoVinculacion()) {
                codigoMonedero = contratoDispensador.getMonederoList().get(0).getCodigo();
                respuestaServicio = dispensadorServicio.guardaContratoVinculacion(codigoMonedero, token);
                if (respuestaServicio.getCodigo() == HttpStatus.OK) {
                    estadoVinculo = "";
                    MuestraMensaje.addSatisfactorioPersistencia(1);
                    RequestContext.getCurrentInstance().execute("contratoDispensadorViewDialog.hide()");
                    mensaje = cargarContratos();
                    if (mensaje != null) {
                        MuestraMensaje.addAdvertencia(mensaje);
                    }
                } else {
                    MuestraMensaje.addError(respuestaServicio.getMensaje().toUpperCase());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "guardaContratoVinculacion", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     * @return
     */
    public boolean validaContratoVinculacion() {
        try {
            if (token == null) {
                MuestraMensaje.addError("Ingrese el campo token");
                return false;
            }
            if (token.isEmpty()) {
                MuestraMensaje.addError("Ingrese el campo token");
                return false;
            }
            if (contratoDispensador == null) {
                MuestraMensaje.addError("Seleccione un contrato");
                return false;
            }
            if (contratoDispensador.getMonederoList() == null) {
                MuestraMensaje.addError("Contrato no tiene monedero");
                return false;
            }
            if (contratoDispensador.getMonederoList().size() <= 0) {
                MuestraMensaje.addError("Contrato no tiene monedero");
                return false;
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "validaContratoVinculacion", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
            return false;
        }
    }

    private SelectItem[] estadosContrato;
    private String estadoSeleccionada;
    private String motivoCambioEstado;

    /**
     *
     */
    public void cambiaEstadoContrato() {
        try {
            estadosContrato = null;
            estadoSeleccionada = null;
            motivoCambioEstado = null;
            if (contratoDispensador.getMonederoList() != null) {
                if (contratoDispensador.getMonederoList().size() > 0) {
                    codigoCuenta = contratoDispensador.getMonederoList().get(0).getCodigoCuenta();
                    montoMaximoDispensar = contratoDispensador.getMonederoList().get(0).getMontoMaximoADispensar();
                    montoMaximoDispensarMen = contratoDispensador.getMonederoList().get(0).getMontoMaximoADispensarMen();
                    cargarCuentasSocio();
                    if (listaCuentaVista != null) {
                        if (contratoDispensador.getEstado().equals("C")) {
                            estadosContrato = new SelectItem[]{new SelectItem("", "SELECCIONE"),
                                new SelectItem("B", "BLOQUEADO"),
                                new SelectItem("F", "FINALIZADO")};
                        }
                        if (contratoDispensador.getEstado().equals("B")) {
                            estadosContrato = new SelectItem[]{new SelectItem("", "SELECCIONE"),
                                new SelectItem("C", "CONTRATADO"),
                                new SelectItem("F", "FINALIZADO")};
                        }
                    } else {
                        MuestraMensaje.addAdvertencia("No existe registro de cuentas");
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "cambiaEstadoContrato", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     */
    public void guardaCambiaEstadoContrato() {
        try {
            if (validaCambiaEstadoContrato()) {
                respuestaServicio = dispensadorServicio.actualizaEstadoContratoCdm(ActivacionUsuario.getCodigoUsuario(),contratoDispensador.getCodigo(),estadoSeleccionada,motivoCambioEstado);
                if (respuestaServicio.getCodigo() == HttpStatus.OK) {
                    MuestraMensaje.addSatisfactorioPersistencia(1);
                    contratoDispensador.setEstado(estadoSeleccionada);
                    RequestContext.getCurrentInstance().execute("contratoDispensadorChangeDialog.hide()");
                    mensaje = cargarContratos();
                    if (mensaje != null) {
                        MuestraMensaje.addAdvertencia(mensaje);
                    }
                } else {
                    MuestraMensaje.addError(respuestaServicio.getMensaje().toUpperCase());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "guardaCambiaEstadoContrato", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     *
     * @return
     */
    public boolean validaCambiaEstadoContrato() {
        try {
            if (contratoDispensador == null) {
                MuestraMensaje.addError("Seleccione contrato");
                return false;
            }
            if (estadoSeleccionada == null) {
                MuestraMensaje.addError("Debe seleccionar estado contrato");
                return false;
            }
            if (estadoSeleccionada.isEmpty()) {
                MuestraMensaje.addError("Debe seleccionar estado contrato");
                return false;
            }
            if (motivoCambioEstado == null) {
                MuestraMensaje.addError("Debe ingresar motivo para el cambio de estado contrato");
                return false;
            }
            if (motivoCambioEstado.isEmpty()) {
                MuestraMensaje.addError("Debe ingresar motivo para el cambio de estado contrato");
                return false;
            }
            if (motivoCambioEstado.replace(" ", "").isEmpty()) {
                MuestraMensaje.addError("Debe ingresar motivo para el cambio de estado contrato");
                return false;
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoDispensadorControlador", "validaCambiaEstadoContrato", CapturaError.getErrorException(ex)});
            MuestraMensaje.addErrorCapaControl();
            return false;
        }
    }

    public String getFoco() {
        return foco;
    }

    public void setFoco(String foco) {
        this.foco = foco;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ContratoCdm getContratoDispensador() {
        return contratoDispensador;
    }

    public void setContratoDispensador(ContratoCdm contratoDispensador) {
        this.contratoDispensador = contratoDispensador;
    }

    public List<Cuenta> getListaCuentaVista() {
        return listaCuentaVista;
    }

    public void setListaCuentaVista(List<Cuenta> listaCuentaVista) {
        this.listaCuentaVista = listaCuentaVista;
    }

    public List<ContratoCdm> getListaContratoDispensador() {
        return listaContratoDispensador;
    }

    public void setListaContratoDispensador(List<ContratoCdm> listaContratoDispensador) {
        this.listaContratoDispensador = listaContratoDispensador;
    }

    public Long getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(Long codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public BigDecimal getMontoMaximoDispensar() {
        return montoMaximoDispensar;
    }

    public void setMontoMaximoDispensar(BigDecimal montoMaximoDispensar) {
        this.montoMaximoDispensar = montoMaximoDispensar;
    }

    public BigDecimal getMontoMaximoDispensarMen() {
        return montoMaximoDispensarMen;
    }

    public void setMontoMaximoDispensarMen(BigDecimal montoMaximoDispensarMen) {
        this.montoMaximoDispensarMen = montoMaximoDispensarMen;
    }

    public ContratoCdm getContratoDispensadorNuevo() {
        return contratoDispensadorNuevo;
    }

    public void setContratoDispensadorNuevo(ContratoCdm contratoDispensadorNuevo) {
        this.contratoDispensadorNuevo = contratoDispensadorNuevo;
    }

    public boolean isHabilitaGuardarContrato() {
        return habilitaGuardarContrato;
    }

    public void setHabilitaGuardarContrato(boolean habilitaGuardarContrato) {
        this.habilitaGuardarContrato = habilitaGuardarContrato;
    }

    public Long getCodigoMonedero() {
        return codigoMonedero;
    }

    public void setCodigoMonedero(Long codigoMonedero) {
        this.codigoMonedero = codigoMonedero;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEstadoVinculo() {
        return estadoVinculo;
    }

    public void setEstadoVinculo(String estadoVinculo) {
        this.estadoVinculo = estadoVinculo;
    }

    public SelectItem[] getEstadosContrato() {
        return estadosContrato;
    }

    public void setEstadosContrato(SelectItem[] estadosContrato) {
        this.estadosContrato = estadosContrato;
    }

    public String getEstadoSeleccionada() {
        return estadoSeleccionada;
    }

    public void setEstadoSeleccionada(String estadoSeleccionada) {
        this.estadoSeleccionada = estadoSeleccionada;
    }

    public String getMotivoCambioEstado() {
        return motivoCambioEstado;
    }

    public void setMotivoCambioEstado(String motivoCambioEstado) {
        this.motivoCambioEstado = motivoCambioEstado;
    }

}
