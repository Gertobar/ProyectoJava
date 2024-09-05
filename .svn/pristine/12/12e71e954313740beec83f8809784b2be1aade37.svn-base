/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.dpfs;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.dpfs.TasaIntenresProductoDPFMontoDetalle;
import ec.renafipse.mks.modelo.dpfs.TasaInteresProDpfMon;
import ec.renafipse.mks.modelo.dpfs.TasaInteresRolAprobacion;
import ec.renafipse.mks.modelo.seguridades.Rol;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.negocio.dpfs.TasaIntenresProductoDPFMontoDetalleFacade;
import ec.renafipse.mks.negocio.dpfs.TasaInteresProDpfMonFacade;
import ec.renafipse.mks.negocio.dpfs.TasaInteresRolAprobacionFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import ec.renafipse.mks.negocio.seguridades.RolFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author willan
 */
@ManagedBean(name = "tasaIntenresProductoDPFMontoDetalleController")
@ViewScoped
public class TasaIntenresProductoDPFMontoDetalleController extends AbstractController<TasaIntenresProductoDPFMontoDetalle> implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="VARIABLES">
    @EJB
    private TasaIntenresProductoDPFMontoDetalleFacade ejbFacade;

    @EJB
    private TasaInteresRolAprobacionFacade tasaRolAproFacade;

    @EJB
    private PersonaFacade personasFacade;

    @EJB
    private RolFacade rolFacade;

    @EJB
    private TasaInteresProDpfMonFacade ejbFacadeTasaInteresProDpfMon;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    private List<TasaIntenresProductoDPFMontoDetalle> lista;

    private Long codigoPersona;
    private String nombreCompletoPersona = "";
    private Persona persona;

    private BigDecimal montoCapital;
    private TasaInteresProDpfMon tasaInteresProDpfMon = null;
    private TasaInteresRolAprobacion tasaInteresRolAprobacion = null;
    private TasaIntenresProductoDPFMontoDetalle tasaIntenresProductoDPFMontoDetalle;
    private boolean configuracionCorrecta = false;
    private boolean permitirEditar = false;
    private boolean validar = true;
    private long plazoDiasMinimo = 0l;
    private BigDecimal montoMinimoAprobacion;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="INICIALIZACION">
    /**
     * Constructor de la aplicacion
     */
    public TasaIntenresProductoDPFMontoDetalleController() {
        super(TasaIntenresProductoDPFMontoDetalle.class);
    }

    /**
     * Permite la incialización de los componentes
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setLista(ejbFacade.getListaTasaIntenresProductoDPFMontoDetalle());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="OBTENCIÓN DE DATOS">
    /**
     * @return devuelve un listado de times moneda de los cuales será utilizado
     * uno para obtener la moneda por defecto
     */
    public List<Moneda> getItemsMoneda() {
        return ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

    /**
     *
     * @return devuelve la entidad instanciada
     */
    public TasaInteresProDpfMon getTasaInteresProDpfMon() {
        return tasaInteresProDpfMon;
    }

    /**
     * Se establece la entidad TasaInteresProDpfMon encontrada y setea el valor
     * de la tasa normal
     *
     * Se pone el valor de la entidad encontrada tasaInteresProDpfMon
     *
     * @param tasaInteresProDpfMon
     */
    public void setTasaInteresProDpfMon(TasaInteresProDpfMon tasaInteresProDpfMon) {
        this.tasaInteresProDpfMon = tasaInteresProDpfMon;
        if (tasaIntenresProductoDPFMontoDetalle != null) {
            tasaIntenresProductoDPFMontoDetalle.setRasaInteresProDpfMon(tasaInteresProDpfMon);
            tasaIntenresProductoDPFMontoDetalle.setTasaNormal(tasaInteresProDpfMon.getTasa().getValor());
        }

    }

    /**
     *
     * @return devuelve la entidad en la que estan parametrizados los roles
     */
    public TasaInteresRolAprobacion getTasaInteresRolAprobacion() {
        return tasaInteresRolAprobacion;
    }

    /**
     * Permite establecer el rol del usuario que esta aprobando
     *
     * @param tasaInteresRolAprobacion
     */
    public void setTasaInteresRolAprobacion(TasaInteresRolAprobacion tasaInteresRolAprobacion) {
        this.tasaInteresRolAprobacion = tasaInteresRolAprobacion;
        if (tasaIntenresProductoDPFMontoDetalle != null) {
            tasaIntenresProductoDPFMontoDetalle.setRolAprueba(tasaInteresRolAprobacion);
        }
    }

    /**
     *
     * @return devuelve el moto capital
     */
    public BigDecimal getMontoCapital() {
        return montoCapital;
    }

    /**
     *
     * @param montoCapital
     */
    public void setMontoCapital(BigDecimal montoCapital) {
        this.montoCapital = montoCapital;
    }

    /**
     * Permite establecer la tasa maxima parametrizada por rol, que es obtenida
     * por el rol de usuario que accede
     *
     */
    public void verificaTasaMaxima() {
        List<Object> valoreObjecto = new ArrayList<Object>();
        Rol rol = rolFacade.find(ActivacionUsuario.getCodigoRol());
        //valoreObjecto.add(rol);
        valoreObjecto.add(ActivacionUsuario.getCodigoUsuario());
        TasaInteresRolAprobacion tasaInteresRolAprobacionLocal = tasaRolAproFacade.buscarTasaInteresRolAprobacion(
                Arrays.asList("codigoUsuario"), valoreObjecto);
        if (tasaInteresRolAprobacionLocal != null) {
            setTasaInteresRolAprobacion(tasaInteresRolAprobacionLocal);
            tasaIntenresProductoDPFMontoDetalle.setPorcentajeAcumulacionMaximo(tasaInteresRolAprobacion.getTasaMaxima());
            setPlazoDiasMinimo(tasaInteresRolAprobacionLocal.getPlazoDiasMinimo());
            setMontoMinimoAprobacion(tasaInteresRolAprobacionLocal.getMontoMinimo());
        }
    }

    /**
     *
     * @return devuelve la clase instanciada
     */
    public TasaIntenresProductoDPFMontoDetalle getTasaIntenresProductoDPFMontoDetalle() {
        return tasaIntenresProductoDPFMontoDetalle;
    }

    /**
     * Permite inicializar la funcionalidad y setar una clase instanciada con
     * valores por defecto
     *
     * @param tasaIntenresProductoDPFMontoDetalle
     */
    public void setTasaIntenresProductoDPFMontoDetalle(TasaIntenresProductoDPFMontoDetalle tasaIntenresProductoDPFMontoDetalle) {
        this.tasaIntenresProductoDPFMontoDetalle = tasaIntenresProductoDPFMontoDetalle;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="ACCIONES SOBRE PERSONAS">
    /**
     * @return devuelve el codigo del socio obtenido por el componente
     */
    public Long getCodigoPersona() {
        return codigoPersona;
    }

    /**
     *
     * @param codigoPersona
     */
    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    /**
     *
     * @return devuelve los nombres de la persona
     */
    public String getNombreCompletoPersona() {
        return nombreCompletoPersona;
    }

    /**
     *
     * @param nombreCompletoPersona
     */
    public void setNombreCompletoPersona(String nombreCompletoPersona) {
        this.nombreCompletoPersona = nombreCompletoPersona;
    }

    /**
     *
     * @return devuelve la persona seleccionada por el control
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Permite establecer la persona selecciona en el componente y pone los
     * datos en la funcionalidad correspondiente
     *
     * @param persona
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
        if (persona != null) {
            tasaIntenresProductoDPFMontoDetalle.setPersona(persona);
        }
    }

    /**
     * Permite actualizar los datos de la persona seleccionada con el componente
     * de busqueda
     */
    public void seleccionaPersona() {
        try {
            nombreCompletoPersona = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoForm:componenteBusca:listaSociosComponenteForm:buscaSocioComponenteNombreCompleto");
            if (nombreCompletoPersona == null || nombreCompletoPersona.isEmpty()) {
                nombreCompletoPersona = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoEditaForm:componenteBusca:listaSociosComponenteForm:buscaSocioComponenteNombreCompleto");
                codigoPersona = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoEditaForm:componenteBusca:listaSociosComponenteForm:buscaSocioComponenteCodigoPersona"));

            } else {
                codigoPersona = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoForm:componenteBusca:listaSociosComponenteForm:buscaSocioComponenteCodigoPersona"));
            }
            setPersona(personasFacade.getItemsByCodigo(codigoPersona).get(0));
        } catch (NumberFormatException e) {
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="VALIDACIÓN DE FUNCIONALIDAD">
    /**
     *
     * @return devuelve verdadero para efectuar la validación
     */
    public boolean isValidar() {
        return validar;
    }

    /**
     *
     * @param validar
     */
    public void setValidar(boolean validar) {
        this.validar = validar;
    }

    /**
     * Deshabilita las validaciones en la accion cerrar
     */
    public void validarAlCerrar() {
        validar = false;
    }

    /**
     * Metodo que permite establecer la tasa encontrada a través de atributos
     * como el capital y el numero de dias
     */
    public void buscarDatosPorCapitalPlazo() {
        if (!validar) {
            return;
        }
        if (!validaMontoMinimo()) {
            return;
        }
        if (tasaIntenresProductoDPFMontoDetalle != null) {
            if (tasaIntenresProductoDPFMontoDetalle.getMontoCapital() != null) {
                if (tasaIntenresProductoDPFMontoDetalle.getMontoCapital().doubleValue() > 0) {
                    List<TasaInteresProDpfMon> tasaInteresProDpfMonLista = ejbFacadeTasaInteresProDpfMon.getItemsContratoDpf(ActivacionUsuario.getCodigoIfip(),
                            tasaIntenresProductoDPFMontoDetalle.getPlasoDias(),
                            getItemsMoneda().get(0).getCodigo(),
                            tasaIntenresProductoDPFMontoDetalle.getMontoCapital(),
                            'N');
                    if (!tasaInteresProDpfMonLista.isEmpty()) {
                        setTasaInteresProDpfMon(tasaInteresProDpfMonLista.get(0));
                    }
                } else {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ModificacionTasaDPFValidaDias"));
                    setConfiguracionCorrecta(false);
                }
            }
        }
    }

    /**
     * Método para validar la nueva tasa, se debe tomar en cuenta que la tasa
     * debe ser superior a la utilizada en un DPF normal e inferior a la tasa
     * maxima posible
     */
    public void validarTasaAcumulada() {
        if (!validar) {
            return;
        }
        try {

            if (tasaIntenresProductoDPFMontoDetalle != null) {
                if (tasaIntenresProductoDPFMontoDetalle.getTasaNormal() != null) {
                    BigDecimal tasaTotal = tasaIntenresProductoDPFMontoDetalle.getPorcentajeAcumulacionMaximo().add(tasaIntenresProductoDPFMontoDetalle.getTasaNormal());
                    if (tasaIntenresProductoDPFMontoDetalle.getTasaAcumulada().doubleValue() < tasaIntenresProductoDPFMontoDetalle.getTasaNormal().doubleValue()) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ModificacionTasaDPFValidaTasaNormal"));
                        setConfiguracionCorrecta(false);
                    } else if (tasaIntenresProductoDPFMontoDetalle.getTasaAcumulada().doubleValue() > tasaTotal.doubleValue()) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ModificacionTasaDPFValidaTasaAcumulada"));
                        tasaIntenresProductoDPFMontoDetalle.setTasaAcumulada(tasaTotal);
                        setConfiguracionCorrecta(false);
                    }
                    if (tasaIntenresProductoDPFMontoDetalle.getPlasoDias() < getPlazoDiasMinimo()) {
                        MuestraMensaje.addError(MessageFormat.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ModificacionTasaDPFValidaPlazoDiasMinimo"), String.valueOf(getPlazoDiasMinimo())));
                        setConfiguracionCorrecta(false);
                    } else {
                        setConfiguracionCorrecta(true);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     *
     * Permite validar el monto minimo de aprobacion, este metodo es utilizado
     * para la aprobacion de tasas superiores al monto parametrizado
     */
    public boolean validaMontoMinimo() {
        boolean procesoCorrecto = true;
        try {

            if (tasaIntenresProductoDPFMontoDetalle != null) {
                if (tasaIntenresProductoDPFMontoDetalle.getTasaNormal() != null) {
                    if (tasaIntenresProductoDPFMontoDetalle.getMontoCapital().doubleValue() < getMontoMinimoAprobacion().doubleValue()) {
                        MuestraMensaje.addError(MessageFormat.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ModificacionTasaDPFValidaMontoMinimo"), String.valueOf(getMontoMinimoAprobacion().doubleValue())));
                        setConfiguracionCorrecta(false);
                        procesoCorrecto = false;
                    }
                }
            }
        } catch (Exception e) {
        }
        return procesoCorrecto;
    }

    /**
     *
     * @return devuelve verdadero si la las validaciones son correctas para
     * habilitar el boton de guardar
     */
    public boolean isConfiguracionCorrecta() {
        return configuracionCorrecta;
    }

    /**
     *
     * @param configuracionCorrecta
     */
    public void setConfiguracionCorrecta(boolean configuracionCorrecta) {
        this.configuracionCorrecta = configuracionCorrecta;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CRUD">
    /**
     * Utilizado para no modificar a la persona que ya fue ingresada
     *
     * @return devuelve verdadero si esta en modo de edicion
     *
     */
    public boolean isPermitirEditar() {
        return permitirEditar;
    }

    /**
     *
     * @param permitirEditar
     */
    public void setPermitirEditar(boolean permitirEditar) {
        this.permitirEditar = permitirEditar;
    }

    /**
     *
     * @return devuelve un listado de DPF aprobados
     */
    public List<TasaIntenresProductoDPFMontoDetalle> getLista() {
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(List<TasaIntenresProductoDPFMontoDetalle> lista) {
        this.lista = lista;
    }

    /**
     * Metodo que permite almacenar la aprobacion del DPF cuando es nuevo
     */
    public void guardar() {
        try {
            if (FacesContext.getCurrentInstance().isValidationFailed())//Valida campos requeridos
            {
                return;
            }
            if (tasaInteresProDpfMon.getMontoMinimoNuevaTasa() == null) {
                return;
            }
            if (!configuracionCorrecta) {
                return;
            }
            if (tasaIntenresProductoDPFMontoDetalle.getPersona() == null) {
                return;
            }

            tasaIntenresProductoDPFMontoDetalle.setFechaRegistro(new java.util.Date());
            tasaIntenresProductoDPFMontoDetalle.setRasaInteresProDpfMon(tasaInteresProDpfMon);
            tasaIntenresProductoDPFMontoDetalle.setMontoInicial(tasaInteresProDpfMon.getMontoMinimoNuevaTasa());
            tasaIntenresProductoDPFMontoDetalle.setMontoFinal(tasaInteresProDpfMon.getMontoMinimoNuevaTasa());
            ejbFacade.guardaOActualiza(tasaIntenresProductoDPFMontoDetalle);
            setLista(ejbFacade.getListaTasaIntenresProductoDPFMontoDetalle());
        } catch (Exception ex) {
            MuestraMensaje.addErrorPersistencia();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TasaIntenresProductoDPFMontoDetalleController", "guarda", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Permite inicializar la funcionalidad y consultar el rol para establecer
     * las tasa maxima
     */
    public void nuevo() {
        validar = true;
        nombreCompletoPersona = "";
        configuracionCorrecta = false;
        permitirEditar = true;
        persona = new Persona();
        tasaIntenresProductoDPFMontoDetalle = new TasaIntenresProductoDPFMontoDetalle(
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                0l,
                BigDecimal.ZERO,
                'S',
                new java.util.Date(),
                "",
                tasaInteresRolAprobacion,
                tasaInteresProDpfMon,
                persona,
                ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario()));
        verificaTasaMaxima();
    }

    /**
     * Edita el registro seleccionado
     *
     */
    public void edita() {
        validar = true;
        try {
            nombreCompletoPersona = "";
            configuracionCorrecta = true;
            if (getSelected() == null) {
                MuestraMensaje.addError("Seleccione un registro primero");
            } else {
                tasaIntenresProductoDPFMontoDetalle = getSelected();
                setPersona(personasFacade.getItemsByCodigo(getSelected().getPersona().getCodigo()).get(0));
                codigoPersona = getPersona().getCodigo();
                nombreCompletoPersona = getPersona().getNombreCompleto();
                buscarDatosPorCapitalPlazo();
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TasaIntenresProductoDPFMontoDetalleController", "edita", CapturaError.getErrorException(ex)});
        }
        configuracionCorrecta = true;
        permitirEditar = false;
    }

    // </editor-fold>
    /**
     *
     * @return devuelve el plazo de dias minimo
     */
    public long getPlazoDiasMinimo() {
        return plazoDiasMinimo;
    }

    /**
     *
     * @param plazoDiasMinimo
     */
    public void setPlazoDiasMinimo(long plazoDiasMinimo) {
        this.plazoDiasMinimo = plazoDiasMinimo;
    }

    /**
     *
     * @return devuelve el monto minimo de aprobacion
     */
    public BigDecimal getMontoMinimoAprobacion() {
        return montoMinimoAprobacion;
    }

    /**
     *
     * @param montoMinimoAprobacion
     */
    public void setMontoMinimoAprobacion(BigDecimal montoMinimoAprobacion) {
        this.montoMinimoAprobacion = montoMinimoAprobacion;
    }

}
