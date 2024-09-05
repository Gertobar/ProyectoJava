/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.componentes;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Utilidades;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistema;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioSistemaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 *
 * @author andres
 */
@ManagedBean(name = "olvideClaveUsuarioComponenteController")
@ViewScoped
@FacesComponent("olvideClaveUsuarioComponente")
public class OlvideClaveUsuarioComponenteController extends UINamingContainer implements Serializable {

    private String nombreUsuario;
    private String identificacion;
    private String foco;
    private String correoElectronico;
    private String clave;
    private String claveMd5;
    private final String correoSistemas = "incidentes@mascoop.fin.ec";
    private boolean habilitaGenerarClave;
    private boolean habilitaFechaNacimiento;
    private Usuario usuario;
    private Date fechaNacimiento;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private LlamaSP llamaSP;
    @EJB
    private UsuarioSistemaFacade usuarioSistemaFacade;

    @PostConstruct
    public void init() {
        try {
            inicializaPropiedades();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"OlvideClaveUsuarioComponenteController", "init", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void inicializaPropiedades() {
        try {
            nombreUsuario = "";
            identificacion = "";
            habilitaGenerarClave = false;
            habilitaFechaNacimiento = false;
            usuario = null;
            fechaNacimiento = null;
            foco = "nombreUsuario";
            correoElectronico = "";
            clave = "";
            claveMd5 = "";
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"OlvideClaveUsuarioComponenteController", "inicializaPropiedades", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void enceraPropiedades() {
        try {
            nombreUsuario = "";
            identificacion = "";
            habilitaGenerarClave = false;
            habilitaFechaNacimiento = false;
            usuario = null;
            fechaNacimiento = null;
            foco = "nombreUsuario";
            correoElectronico = "";
            clave = "";
            claveMd5 = "";
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"OlvideClaveUsuarioComponenteController", "enceraPropiedades", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void buscarUsuario() {
        try {
            identificacion = "";
            fechaNacimiento = null;
            correoElectronico = "";
            habilitaFechaNacimiento = false;
            habilitaGenerarClave = false;
            usuario = usuarioFacade.getUsuarioPorUsername(nombreUsuario);
            if (usuario == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoExiste"));
                enceraPropiedades();
                return;
            }
            UsuarioSistema usuarioSistema = usuarioSistemaFacade.getUsuarioSistemaPorCodigoUsuarioCodigoSistema(usuario.getCodigo(), Long.valueOf("2"));
            if (usuarioSistema.getEstado() != 'V') {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoEstaVigente"));
                enceraPropiedades();
            }else {
                foco = "identificacion";
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"OlvideClaveUsuarioComponenteController", "buscarUsuario", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaIdentificacionUsuario() {
        try {
            habilitaGenerarClave = false;
            fechaNacimiento = null;
            correoElectronico = "";
            foco = "identificacion";
            habilitaFechaNacimiento = false;
            if (validarIdentificacionUsuario()) {
                foco = "fechaNacimiento";
                habilitaFechaNacimiento = true;
            } else {
                identificacion = "";
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"OlvideClaveUsuarioComponenteController", "cambiaIdentificacionUsuario", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaFechaNacimientoUsuario() {
        try {
            habilitaGenerarClave = false;
            foco = "fechaNacimiento";
            correoElectronico = "";
            if (validarFechaNacimientoUsuario()) {
                if (seleccionarCorreoElectronico()) {
                    habilitaGenerarClave = true;
                    foco = "correoElectronico";
                }
            } else {
                fechaNacimiento = null;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"OlvideClaveUsuarioComponenteController", "cambiaFechaNacimientoUsuario", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean validarIdentificacionUsuario() {
        try {
            if (usuario == null) {
                foco = "nombreUsuario";
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresarNombreUsuario"));
                return false;
            }
            if (identificacion == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseIdentificacionUsuario"));
                return false;
            } else {
                if (identificacion.length() <= 0) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseIdentificacionUsuario"));
                    return false;
                } else {
                    if (usuario.getCodigoPersona().getIdentificacion().compareTo(identificacion) == 0) {
                        return true;
                    } else {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionUsuarioIncorrecta"));
                        return false;
                    }
                }
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"OlvideClaveUsuarioComponenteController", "validarIdentificacionUsuario", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean validarFechaNacimientoUsuario() {
        try {
            if (usuario == null) {
                foco = "nombreUsuario";
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresarNombreUsuario"));
                return false;
            }
            if (fechaNacimiento == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseFechaNacimientoUsuario"));
                return false;
            } else {
                if (!(fechaNacimiento.compareTo(usuario.getCodigoPersona().getPersonaNatural().getFechaNacimiento()) == 0)) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FechaNacimientoUsuarioIncorrecta"));
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"OlvideClaveUsuarioComponenteController", "validarFechaNacimientoUsuario", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void abrirDialogo() {
        try {
            inicializaPropiedades();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"OlvideClaveUsuarioComponenteController", "abrirDialogo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void generaClaveUsuario() {
        try {
            if (habilitaGenerarClave) {
                if (validarIdentificacionUsuario()) {
                    if (validarFechaNacimientoUsuario()) {
                        if (seleccionarCorreoElectronico()) {
                            ArrayList<Integer> numeros = Utilidades.generaNumerosTemporales(4);
                            clave = "";
                            claveMd5 = "";
                            for (Integer item : numeros) {
                                clave += item;
                            }
                            claveMd5 = Utilidades.getMd5DesdeCadena(clave);
                            llamaSP.cargaConexion();
                            llamaSP.setCerrarConexion(false);
                            llamaSP.autoCommit(false);
                            llamaSP.setNombreSP("mks_seguridades.pkm_usuario_clave_temporal.p_inserta_clave_tem_usu");
                            llamaSP.setNumeroParametros(7);
                            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", 1});
                            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", usuario.getCodigo()});
                            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_username", usuario.getUsername()});
                            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_username_genero", "MKS"});
                            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_correo_ele_gen", correoElectronico});
                            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_contrasena_genero", clave});
                            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_contrasena_gen_md5", claveMd5});
                            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                            llamaSP.invocaSP();
                            if (llamaSP.isEjecucionCorrecta()) {
                                llamaSP.commit();
                                MuestraMensaje.addSatisfactorio(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ClaveTemporalEnviadaCorrectamente"));
                                habilitaGenerarClave = false;
                            } else {
                                if (llamaSP.getConexionBD() != null) {
                                    llamaSP.rollback();
                                }
                            }
                        }else{
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAlSeleccionarCorreoEnvioClave"));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.rollback();
            }
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"OlvideClaveUsuarioComponenteController", "generaClaveUsuario", CapturaError.getErrorException(ex)});
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
            inicializaPropiedades();
        }
    }

    /**
     *
     * @return
     */
    public boolean seleccionarCorreoElectronico() {
        try {
            String correo = usuario.getCorreoElectronico();
            if (correo == null) {
                correoElectronico = correoSistemas;
            } else {
                if (correo.length() <= 0) {
                    correoElectronico = correoSistemas;
                } else {
                    String[] vector = correo.split("@");
                    if (vector[1].compareTo("mascoop.fin.ec") == 0) {
                        correoElectronico = correo;
                    } else {
                        correoElectronico = correoSistemas;
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"OlvideClaveUsuarioComponenteController", "seleccionarCorreoElectronico", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the habilitaGenerarClave
     */
    public boolean isHabilitaGenerarClave() {
        return habilitaGenerarClave;
    }

    /**
     * @param habilitaGenerarClave the habilitaGenerarClave to set
     */
    public void setHabilitaGenerarClave(boolean habilitaGenerarClave) {
        this.habilitaGenerarClave = habilitaGenerarClave;
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
     * @return the habilitaFechaNacimiento
     */
    public boolean isHabilitaFechaNacimiento() {
        return habilitaFechaNacimiento;
    }

    /**
     * @param habilitaFechaNacimiento the habilitaFechaNacimiento to set
     */
    public void setHabilitaFechaNacimiento(boolean habilitaFechaNacimiento) {
        this.habilitaFechaNacimiento = habilitaFechaNacimiento;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

}
