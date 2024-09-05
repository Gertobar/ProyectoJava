package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.CaracterNoAcpetadoContrasena;
import ec.renafipse.mks.modelo.seguridades.ContrasenaUsuario;
import ec.renafipse.mks.modelo.seguridades.ExpiracionContrasena;
import ec.renafipse.mks.modelo.seguridades.MotivoCambioContrasena;
import ec.renafipse.mks.modelo.seguridades.PalabraNoAceptadaContrasena;
import ec.renafipse.mks.modelo.seguridades.ParametroGeneralSegIfi;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistema;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistemaPK;
import ec.renafipse.mks.negocio.seguridades.CaracterNoAcpetadoContrasenaFacade;
import ec.renafipse.mks.negocio.seguridades.ContrasenaUsuarioFacade;
import ec.renafipse.mks.negocio.seguridades.ExpiracionContrasenaFacade;
import ec.renafipse.mks.negocio.seguridades.MotivoCambioContrasenaFacade;
import ec.renafipse.mks.negocio.seguridades.PalabraNoAceptadaContrasenaFacade;
import ec.renafipse.mks.negocio.seguridades.ParametroGeneralSegIfiFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioSistemaFacade;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;

@ManagedBean(name = "contrasenaUsuarioController")
@ViewScoped
public class ContrasenaUsuarioController extends AbstractController<ContrasenaUsuario> implements Serializable {

    @EJB
    private ContrasenaUsuarioFacade ejbFacade;

    @EJB
    private CaracterNoAcpetadoContrasenaFacade ejbFacadeCarNoAceCon;

    @EJB
    private PalabraNoAceptadaContrasenaFacade ejbFacadePalNoAceCon;

    @EJB
    private UsuarioSistemaFacade ejbFacadeUsuSis;

    @EJB
    private ParametroGeneralSegIfiFacade ejbFacadeParGenSegIfi;

    @EJB
    private ExpiracionContrasenaFacade ejbFacadeExpiraContrasena;

    @EJB
    private MotivoCambioContrasenaFacade ejbFacadeMotCamContrasena;

    private UsuarioSistema usuarioSistema;
    private ParametroGeneralSegIfi paramGenSegIfi;
    private ContrasenaUsuario contrasenaUsuario;
    private UsuarioSistemaPK usuSisPK;
    private ExpiracionContrasena expiracionContrasena;
    private MotivoCambioContrasena motivoCambioContrasena;

    private String contrasenaActual;
    private String contrasenaNueva;
    private String confirmacionContrasena;
    private String mensajeComplejidad;
    private String msg;
    private short diasGracia;
    private short mesesVigencia;
    private boolean repiteContrasena;
    private boolean contrasenaAnio;

    private List<ContrasenaUsuario> itemsConstrasenaUsuario;
    private List<ParametroGeneralSegIfi> itemsParGenIfi;
    private List<UsuarioSistema> itemsUsuarioSistema;
    private List<ExpiracionContrasena> listExpiracionContrasena;
    private List<MotivoCambioContrasena> itemsMotCamContrasena;

    public ContrasenaUsuarioController() {
        super(ContrasenaUsuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(getEjbFacade());
        this.usuSisPK = new UsuarioSistemaPK();
        this.usuarioSistema = new UsuarioSistema();
        this.paramGenSegIfi = new ParametroGeneralSegIfi();
        this.contrasenaUsuario = new ContrasenaUsuario();
        this.expiracionContrasena = new ExpiracionContrasena();
        this.motivoCambioContrasena = new MotivoCambioContrasena();
        this.contrasenaActual = null;
        this.confirmacionContrasena = null;
        this.contrasenaNueva = null;

        this.mesesVigencia = -1;
        this.diasGracia = -1;
        this.repiteContrasena = false;

        this.setItemsParGenIfi(this.ejbFacadeParGenSegIfi.getItemsParGenSegIfi(ActivacionUsuario.getCodigoIfip()));
        for (ParametroGeneralSegIfi pGSI : this.getItemsParGenIfi()) {
            if (String.valueOf(pGSI.getParametroGeneralSegIfiPK().getCodigoParametro()).equals("1")) {
                this.setMesesVigencia(pGSI.getValorNumerico().shortValueExact());
            }
            if (String.valueOf(pGSI.getParametroGeneralSegIfiPK().getCodigoParametro()).equals("2")) {
                this.setDiasGracia(pGSI.getValorNumerico().shortValueExact());
            }

            if (String.valueOf(pGSI.getParametroGeneralSegIfiPK().getCodigoParametro()).equals("3")) {
                this.setRepiteContrasena((pGSI.getValorNumerico().intValue() == 1));
            }

        }

    }

    /**
     * Metodo que permite cambiar la contrasena del usuario
     *
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public void cambiaContrasenaUsuario() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String estadoContrasena = null;
        msg = null;
        expiracionContrasena = null;
        if (this.getMesesVigencia() > -1 && this.getDiasGracia() > -1) {
            setUsuSisPK(new UsuarioSistemaPK());
            getUsuSisPK().setCodigoSistema(Long.parseLong("2"));
            getUsuSisPK().setCodigoUsuario(ActivacionUsuario.getCodigoUsuario());
            setUsuarioSistema(this.getEjbFacadeUsuSis().find(getUsuSisPK()));
            estadoContrasena = String.valueOf(this.getUsuarioSistema().getEstado());
            try {
                if (!Sesion.MD5(this.getContrasenaActual()).equals(usuarioSistema.getContrasena())) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaActual");

                }
            } catch (Exception ex) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValidarContrasena");
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"contrasenaUsuarioController", "cambiaContrasenaUsuario", CapturaError.getErrorException(ex)});
            }
            if (this.getContrasenaNueva().equals(this.getContrasenaActual())) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NuevaContrasena");
            }

            if (!this.getContrasenaNueva().equals(this.getConfirmacionContrasena())) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ConfirmaContrasena");
            }

            if (this.getContrasenaNueva().toUpperCase().lastIndexOf(this.getUsuarioSistema().getUsuario().getUsername()) != -1) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaNoPuedeContenerUsername");
            }

            List<CaracterNoAcpetadoContrasena> listaCaracterNoAcpetadoContrasena
                    = ejbFacadeCarNoAceCon.getItemsEliminado('N');
            for (CaracterNoAcpetadoContrasena cnac : listaCaracterNoAcpetadoContrasena) {
                if (this.getContrasenaNueva().lastIndexOf(cnac.getCaracter()) != -1) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaContieneCaracteresNoAceptados");
                    break;
                }
            }

            if (msg == null) {
                List<PalabraNoAceptadaContrasena> listaPalabraNoAceptadaContrasena
                        = this.ejbFacadePalNoAceCon.getItemsEliminado('N');

                for (PalabraNoAceptadaContrasena pnac : listaPalabraNoAceptadaContrasena) {
                    if (this.getContrasenaNueva().toUpperCase().lastIndexOf(pnac.getPalabra().toUpperCase()) != -1) {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaContienePalabrasNoAceptados");
                        break;
                    }
                }
            }

            /*if (msg == null) {
                boolean caracterAnteriorLetra = false;
                int vecesNumeroLetras = 0;
                for (int c = 0; c < this.getContrasenaNueva().length(); c++) {

                    /**
                     * A 65 B 66 C 67 Z 90 0 48 9 57
                     
                    ////System.out.println
                    if (c > 0) {
                        caracterAnteriorLetra = (this.getContrasenaNueva().toUpperCase().codePointAt(c - 1) >= 65)
                                && (this.getContrasenaNueva().toUpperCase().codePointAt(c-1) <= 90);
                    }
                    
                    
                    if ((this.getContrasenaNueva().toUpperCase().codePointAt(c) >= 65)
                            && (this.getContrasenaNueva().toUpperCase().codePointAt(c) <= 90)) {
                        if (caracterAnteriorLetra) {
                            vecesNumeroLetras++;
                        }
                    }
                    
                    if (vecesNumeroLetras == 2)
                    {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaLetrasNumerosAlternadas");
                    }
                }
            }*/
            if (msg == null) {
                if (validaComplejidadContrasena()) {
                    if (!validaContrasenaUsuarioAnio()) {

                        Long codigoMotivoCambio = Long.parseLong(estadoContrasena.equals("X") ? "1" : ((estadoContrasena.equals("V")) ? "2" : "3"));
                        motivoCambioContrasena = this.ejbFacadeMotCamContrasena.find(codigoMotivoCambio);
                        if (motivoCambioContrasena == null) {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoCambContrasena");
                        }

                        if (estadoContrasena.equals("X")) {
                            // Obtengo el objeto de expiracion de la contraseña
                            listExpiracionContrasena = this.ejbFacadeExpiraContrasena.getIemsExpiracionConUsuSisVig(usuarioSistema, 'S');
                            if (listExpiracionContrasena == null) {
                                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteExpContrasena");
                            } else {
                                if (listExpiracionContrasena.size() > 1) {
                                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValidacionConExpiradas");
                                }
                            }
                        }

                    }
                }
            }
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoParGenSeg");
        }

        try {
            if (msg == null) {

                //Actualiza la tabla de Usuario sistema con la nueva contrasenia
                this.usuarioSistema.setContrasena(Sesion.MD5(this.getContrasenaNueva()));
                this.usuarioSistema.setFechaCaducaContrasena(obtieneFechaCaducidad());
                this.usuarioSistema.setMesesVigencia(this.getMesesVigencia());
                this.usuarioSistema.setDiasGracia(this.getDiasGracia());
                this.usuarioSistema.setEstado('V');
                this.usuarioSistema.setFechaCambioContrasena(new Date());
                this.ejbFacadeUsuSis.edit(usuarioSistema);

                if (estadoContrasena.equals("X")) {
                    //Actualizo la tabla de Expiracion contrasenia con la vigencia de la contrasenia
                    expiracionContrasena = listExpiracionContrasena.get(0);
                    expiracionContrasena.setVigente('N');
                    this.ejbFacadeExpiraContrasena.edit(expiracionContrasena);
                }

                //Crea la Contrasenia Usuario
                setContrasenaUsuario(new ContrasenaUsuario());
                this.getContrasenaUsuario().setCodigoUsuario(this.usuarioSistema.getUsuarioSistemaPK().getCodigoUsuario());
                this.getContrasenaUsuario().setCodigoSistema(this.usuarioSistema.getUsuarioSistemaPK().getCodigoSistema());
                this.getContrasenaUsuario().setMotivoCambioUsuario(motivoCambioContrasena);
                this.getContrasenaUsuario().setContrasena(Sesion.MD5(this.getContrasenaNueva()));
                this.getContrasenaUsuario().setMesesVigencia(this.usuarioSistema.getMesesVigencia());
                this.getContrasenaUsuario().setDiasGracia(this.usuarioSistema.getDiasGracia());
                this.getContrasenaUsuario().setFecha(new Date());
                this.ejbFacade.create(this.getContrasenaUsuario());

                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaCorrecta");
                MuestraMensaje.addSatisfactorio(msg);
                Sesion.invalidaSesion();
                Sesion.redireccionaPagina("contrasenaCambiada.jsf");

            } else {
                MuestraMensaje.addAdvertencia(msg);
            }
        } catch (Exception er) {
            er.printStackTrace();

        }

    }

    /**
     * Metodo que valida la complejidad de la contrasena del usuario
     *
     * @return
     */
    public boolean validaComplejidadContrasena() {
        boolean complejidadContrasena = false;
        mensajeComplejidad = null;
        if (Validaciones.contrasenaComplejidadBaja(this.contrasenaNueva)) {
            complejidadContrasena = false;
            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadBaja");
        } else if (Validaciones.contrasenaComplejidadMedia(this.contrasenaNueva)) {
            complejidadContrasena = true;
            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadMedia");;
        } else if (Validaciones.contrasenaComplejidadAlta(this.contrasenaNueva)) {
            complejidadContrasena = true;
            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadAlta");;
        } else if (this.mensajeComplejidad == null) {
            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaInsegura");;
        }

        msg = (mensajeComplejidad != null && !complejidadContrasena) ? "Contraseña no Cumple con Complejidad Aceptable. " + this.mensajeComplejidad : null;

        return complejidadContrasena;
    }

    /**
     *
     * @return @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public boolean validaContrasenaUsuarioAnio() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        contrasenaAnio = false;
        msg = null;
        if (this.isRepiteContrasena()) {
            Date fechaInicial = obtieneFechaInicial(obtieneAnio());
            Date fechaFinal = obtieneFechaFinal(obtieneAnio());
            itemsConstrasenaUsuario = this.getEjbFacade().getItemsContrasenaUsuario(Long.parseLong("2"), Sesion.MD5(this.getContrasenaNueva()), fechaInicial, fechaFinal);

            if (itemsConstrasenaUsuario.size() > 0) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaRepetida");
                contrasenaAnio = true;
            } else {
                contrasenaAnio = false;
            }
        }

        return contrasenaAnio;
    }

    /**
     *
     * @return
     */
    public int obtieneAnio() {
        GregorianCalendar calendario = new GregorianCalendar();
        int anio = calendario.get(Calendar.YEAR);
        return anio;
    }

    /**
     *
     * @param anio
     * @return
     */
    public Date obtieneFechaInicial(int anio) {
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaIni = "01/01/" + anio;
        Date fechaInicial = null;
        try {
            fechaInicial = formato.parse(fechaIni);

        } catch (ParseException ex) {
            Logger.getLogger(ContrasenaUsuario.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return fechaInicial;
    }

    /**
     *
     * @param anio
     * @return
     */
    public Date obtieneFechaFinal(int anio) {
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFin = "31/12/" + anio;
        Date fechaFinal = null;
        try {
            fechaFinal = formato.parse(fechaFin);

        } catch (ParseException ex) {
            Logger.getLogger(ContrasenaUsuario.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return fechaFinal;
    }

    public Date obtieneFechaCaducidad() {

        GregorianCalendar calendario = new GregorianCalendar();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        calendario.add(Calendar.MONTH, this.usuarioSistema.getMesesVigencia());
        calendario.add(Calendar.DATE, this.usuarioSistema.getDiasGracia());
        String fecha = formato.format(calendario.getTime());
        Date envioFechaExpiracion = null;
        try {
            envioFechaExpiracion = formato.parse(fecha);

        } catch (ParseException ex) {
            Logger.getLogger(ContrasenaUsuario.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return envioFechaExpiracion;

    }

    /**
     * @param usuarioSistema the usuarioSistema to set
     */
    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    /**
     * @param contrasenaActual the contrasenaActual to set
     */
    public void setContrasenaActual(String contrasenaActual) {
        this.contrasenaActual = contrasenaActual;
    }

    /**
     * @param contrasenaNueva the contrasenaNueva to set
     */
    public void setContrasenaNueva(String contrasenaNueva) {
        this.contrasenaNueva = contrasenaNueva;
    }

    /**
     * @param mensajeComplejidad the mensajeComplejidad to set
     */
    public void setMensajeComplejidad(String mensajeComplejidad) {
        this.mensajeComplejidad = mensajeComplejidad;
    }

    /**
     * @param confirmacionContrasena the confirmacionContrasena to set
     */
    public void setConfirmacionContrasena(String confirmacionContrasena) {
        this.confirmacionContrasena = confirmacionContrasena;
    }

    /**
     * @return the mensajeComplejidad
     */
    public String getMensajeComplejidad() {
        return mensajeComplejidad;
    }

    /**
     * @return the usuarioSistema
     */
    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    /**
     * @return the contrasenaActual
     */
    public String getContrasenaActual() {
        return contrasenaActual;
    }

    /**
     * @return the confirmacionContrasena
     */
    public String getConfirmacionContrasena() {
        return confirmacionContrasena;
    }

    /**
     * @return the contrasenaNueva
     */
    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    /**
     * @return the paramGenSegIfi
     */
    public ParametroGeneralSegIfi getParamGenSegIfi() {
        return paramGenSegIfi;
    }

    /**
     * @param paramGenSegIfi the paramGenSegIfi to set
     */
    public void setParamGenSegIfi(ParametroGeneralSegIfi paramGenSegIfi) {
        this.paramGenSegIfi = paramGenSegIfi;
    }

    /**
     * @return the itemsConstrasenaUsuario
     */
    public List<ContrasenaUsuario> getItemsConstrasenaUsuario() {
        return itemsConstrasenaUsuario;
    }

    /**
     * @param itemsConstrasenaUsuario the itemsConstrasenaUsuario to set
     */
    public void setItemsConstrasenaUsuario(List<ContrasenaUsuario> itemsConstrasenaUsuario) {
        this.itemsConstrasenaUsuario = itemsConstrasenaUsuario;
    }

    /**
     * @return the itemsParGenIfi
     */
    public List<ParametroGeneralSegIfi> getItemsParGenIfi() {
        return itemsParGenIfi;
    }

    /**
     * @param itemsParGenIfi the itemsParGenIfi to set
     */
    public void setItemsParGenIfi(List<ParametroGeneralSegIfi> itemsParGenIfi) {
        this.itemsParGenIfi = itemsParGenIfi;
    }

    /**
     * @return the ejbFacade
     */
    public ContrasenaUsuarioFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ContrasenaUsuarioFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the ejbFacadeUsuSis
     */
    public UsuarioSistemaFacade getEjbFacadeUsuSis() {
        return ejbFacadeUsuSis;
    }

    /**
     * @param ejbFacadeUsuSis the ejbFacadeUsuSis to set
     */
    public void setEjbFacadeUsuSis(UsuarioSistemaFacade ejbFacadeUsuSis) {
        this.ejbFacadeUsuSis = ejbFacadeUsuSis;
    }

    /**
     * @return the ejbFacadeParGenSegIfi
     */
    public ParametroGeneralSegIfiFacade getEjbFacadeParGenSegIfi() {
        return ejbFacadeParGenSegIfi;
    }

    /**
     * @param ejbFacadeParGenSegIfi the ejbFacadeParGenSegIfi to set
     */
    public void setEjbFacadeParGenSegIfi(ParametroGeneralSegIfiFacade ejbFacadeParGenSegIfi) {
        this.ejbFacadeParGenSegIfi = ejbFacadeParGenSegIfi;
    }

    /**
     * @return the contrasenaUsuario
     */
    public ContrasenaUsuario getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    /**
     * @param contrasenaUsuario the contrasenaUsuario to set
     */
    public void setContrasenaUsuario(ContrasenaUsuario contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    /**
     * @return the itemsUsuarioSistema
     */
    public List<UsuarioSistema> getItemsUsuarioSistema() {
        return itemsUsuarioSistema;
    }

    /**
     * @param itemsUsuarioSistema the itemsUsuarioSistema to set
     */
    public void setItemsUsuarioSistema(List<UsuarioSistema> itemsUsuarioSistema) {
        this.itemsUsuarioSistema = itemsUsuarioSistema;
    }

    /**
     * @return the ejbFacadeExpiraContrasena
     */
    public ExpiracionContrasenaFacade getEjbFacadeExpiraContrasena() {
        return ejbFacadeExpiraContrasena;
    }

    /**
     * @param ejbFacadeExpiraContrasena the ejbFacadeExpiraContrasena to set
     */
    public void setEjbFacadeExpiraContrasena(ExpiracionContrasenaFacade ejbFacadeExpiraContrasena) {
        this.ejbFacadeExpiraContrasena = ejbFacadeExpiraContrasena;
    }

    /**
     * @return the expiracionContrasena
     */
    public ExpiracionContrasena getExpiracionContrasena() {
        return expiracionContrasena;
    }

    /**
     * @param expiracionContrasena the expiracionContrasena to set
     */
    public void setExpiracionContrasena(ExpiracionContrasena expiracionContrasena) {
        this.expiracionContrasena = expiracionContrasena;
    }

    /**
     * @return the usuSisPK
     */
    public UsuarioSistemaPK getUsuSisPK() {
        return usuSisPK;
    }

    /**
     * @param usuSisPK the usuSisPK to set
     */
    public void setUsuSisPK(UsuarioSistemaPK usuSisPK) {
        this.usuSisPK = usuSisPK;
    }

    /**
     * @return the ejbFacadeMotCamContrasena
     */
    public MotivoCambioContrasenaFacade getEjbFacadeMotCamContrasena() {
        return ejbFacadeMotCamContrasena;
    }

    /**
     * @param ejbFacadeMotCamContrasena the ejbFacadeMotCamContrasena to set
     */
    public void setEjbFacadeMotCamContrasena(MotivoCambioContrasenaFacade ejbFacadeMotCamContrasena) {
        this.ejbFacadeMotCamContrasena = ejbFacadeMotCamContrasena;
    }

    /**
     * @return the motivoCambioContrasena
     */
    public MotivoCambioContrasena getMotivoCambioContrasena() {
        return motivoCambioContrasena;
    }

    /**
     * @param motivoCambioContrasena the motivoCambioContrasena to set
     */
    public void setMotivoCambioContrasena(MotivoCambioContrasena motivoCambioContrasena) {
        this.motivoCambioContrasena = motivoCambioContrasena;
    }

    /**
     * @return the itemsMotCamContrasena
     */
    public List<MotivoCambioContrasena> getItemsMotCamContrasena() {
        return itemsMotCamContrasena;
    }

    /**
     * @param itemsMotCamContrasena the itemsMotCamContrasena to set
     */
    public void setItemsMotCamContrasena(List<MotivoCambioContrasena> itemsMotCamContrasena) {
        this.itemsMotCamContrasena = itemsMotCamContrasena;
    }

    /**
     * @return the listExpiracionContrasena
     */
    public List<ExpiracionContrasena> getListExpiracionContrasena() {
        return listExpiracionContrasena;
    }

    /**
     * @param listExpiracionContrasena the listExpiracionContrasena to set
     */
    public void setListExpiracionContrasena(List<ExpiracionContrasena> listExpiracionContrasena) {
        this.listExpiracionContrasena = listExpiracionContrasena;
    }

    /**
     * @return the diasGracia
     */
    public short getDiasGracia() {
        return diasGracia;
    }

    /**
     * @param diasGracia the diasGracia to set
     */
    public void setDiasGracia(short diasGracia) {
        this.diasGracia = diasGracia;
    }

    /**
     * @return the mesesVigencia
     */
    public short getMesesVigencia() {
        return mesesVigencia;
    }

    /**
     * @param mesesVigencia the mesesVigencia to set
     */
    public void setMesesVigencia(short mesesVigencia) {
        this.mesesVigencia = mesesVigencia;
    }

    /**
     * @return the repiteContrasena
     */
    public boolean isRepiteContrasena() {
        return repiteContrasena;
    }

    /**
     * @param repiteContrasena the repiteContrasena to set
     */
    public void setRepiteContrasena(boolean repiteContrasena) {
        this.repiteContrasena = repiteContrasena;
    }

}
