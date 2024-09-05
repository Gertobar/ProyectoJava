/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.componentes;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.modelo.socios.ItemFlujoCaja;
import ec.renafipse.mks.modelo.socios.ItemFlujoCajaMonto;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioFlujoCaja;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaEgreso;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaEgresoPK;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaIngreso;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaIngresoPK;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaPK;
import ec.renafipse.mks.negocio.socios.ItemFlujoCajaFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaEgresoFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaIngresoFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author andres
 */
@ManagedBean(name = "flujoCajaComponenteController")
@ViewScoped
@FacesComponent("flujoCajaComponente")
public class FlujoCajaComponenteController extends UINamingContainer implements Serializable {

    @EJB
    private ItemFlujoCajaFacade ejbFacadeItemFlujoCaja;
    @EJB
    private SocioFlujoCajaIngresoFacade ejbFacadeFlujoCajaIngreso;
    @EJB
    private SocioFlujoCajaEgresoFacade ejbFacadeFlujoCajaEgreso;
    @EJB
    private SocioFacade ejbFacadeSocio;
    @EJB
    private SocioFlujoCajaFacade ejbFacadeFlujoCaja;

    private List<ItemFlujoCaja> listaItemFlujoCajaIngreso;
    private List<SocioFlujoCajaIngreso> listaNuevaFlujoCajaIngreso;
    private List<SocioFlujoCajaIngreso> listaEditaFlujoCajaIngreso;
    private List<SocioFlujoCajaIngreso> listaFlujoCajaIngreso;

    private List<ItemFlujoCaja> listaItemFlujoCajaEgreso;
    private List<SocioFlujoCajaEgreso> listaNuevaFlujoCajaEgreso;
    private List<SocioFlujoCajaEgreso> listaEditaFlujoCajaEgreso;
    private List<SocioFlujoCajaEgreso> listaFlujoCajaEgreso;

    private Socio socio;
    private SocioFlujoCaja flujoCaja;
    private int indice;
    private boolean nuevoFlujoCaja;
    private boolean flujoGuardado;
    private String mensaje;
    private java.lang.Boolean editable;
    private java.lang.Boolean mensajeErrorGuardar;

    @PostConstruct
    public void init() {
        try {
            listaItemFlujoCajaIngreso = null;
            listaNuevaFlujoCajaIngreso = null;
            listaEditaFlujoCajaIngreso = null;
            listaFlujoCajaIngreso = null;
            listaItemFlujoCajaEgreso = null;
            listaNuevaFlujoCajaEgreso = null;
            listaEditaFlujoCajaEgreso = null;
            listaFlujoCajaEgreso = null;
            socio = null;
            flujoCaja = null;
            flujoGuardado = false;
            nuevoFlujoCaja = false;
        } catch (Exception ex) {
            socio = null;
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FlujoCajaSocioComponenteController", "init", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param codigoSocio
     * @param editable
     * @param mensajeErrorGuardar
     */
    public void cargarComponente(java.lang.Long codigoSocio, java.lang.Boolean editable, java.lang.Boolean mensajeErrorGuardar) {
        try {
            this.editable = editable;
            this.mensajeErrorGuardar = mensajeErrorGuardar;
            flujoGuardado = false;
            if (listaItemFlujoCajaIngreso == null && listaItemFlujoCajaEgreso == null) {
                socio = null;
                if (codigoSocio == null) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteCodigoSocio"));
                    return;
                } else {
                    if (codigoSocio.compareTo(Long.valueOf("0")) == 0) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteCodigoSocio"));
                        return;
                    }
                }
                listaItemFlujoCajaIngreso = this.ejbFacadeItemFlujoCaja.getItemsFlujoCaja('I', 'N');
                listaItemFlujoCajaEgreso = this.ejbFacadeItemFlujoCaja.getItemsFlujoCaja('E', 'N');
                socio = ejbFacadeSocio.getSocioPorCodigo(codigoSocio);
                if (cargaFlujoCaja()) {
                    if (cargaFlujoCajaIngreso()) {
                        cargaFlujoCajaEgreso();
                    }
                }
            }
        } catch (Exception ex) {
            socio = null;
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FlujoCajaSocioComponenteController", "cargarComponente", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean cargaFlujoCaja() {
        try {
            if (socio != null) {
                flujoCaja = ejbFacadeFlujoCaja.getSocioFlujoCajaCodigoSocioIfip(socio.getSocioPK().getCodigo(), socio.getSocioPK().getCodigoIfip());
                if (flujoCaja == null) {
                    flujoCaja = new SocioFlujoCaja(Long.valueOf("0"));
                    flujoCaja.setTotalGastosSocio(new BigDecimal("0.00"));
                    flujoCaja.setTotalIngresosConyuge(new BigDecimal("0.00"));
                    flujoCaja.setTotalIngresosSocio(new BigDecimal("0.00"));
                    flujoCaja.setSaldo(new BigDecimal("0.00"));
                    flujoCaja.setCobertura(new BigDecimal("0.00"));
                    flujoCaja.setCodigoUsuarioIngreso(ActivacionUsuario.getCodigoUsuario());
                    flujoCaja.setFechaIngreso(new Date());
                    flujoCaja.setFechaActualizacion(flujoCaja.getFechaIngreso());
                    flujoCaja.setCodigoUsuarioActualizo(ActivacionUsuario.getCodigoUsuario());
                    nuevoFlujoCaja = true;
                } else {
                    nuevoFlujoCaja = false;
                }
                return true;
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return false;
            }
        } catch (Exception ex) {
            socio = null;
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FlujoCajaSocioComponenteController", "cargaFlujoCaja", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean cargaFlujoCajaIngreso() {
        try {
            boolean nuevo = false;
            listaNuevaFlujoCajaIngreso = new ArrayList<SocioFlujoCajaIngreso>();
            listaEditaFlujoCajaIngreso = new ArrayList<SocioFlujoCajaIngreso>();
            indice = 0;
            if (socio != null) {
                listaNuevaFlujoCajaIngreso = ejbFacadeFlujoCajaIngreso.getItemsFlujoCajaIngresoSocio(socio.getSocioPK().getCodigo(), socio.getSocioPK().getCodigoIfip());
                if (listaNuevaFlujoCajaIngreso == null) {
                    nuevo = true;
                } else {
                    if (listaNuevaFlujoCajaIngreso.isEmpty()) {
                        nuevo = true;
                    }
                }
                if (nuevo) {
                    listaNuevaFlujoCajaIngreso = new ArrayList<SocioFlujoCajaIngreso>();
                    for (ItemFlujoCaja item : listaItemFlujoCajaIngreso) {
                        /*listaNuevaFlujoCajaIngreso.add(new SocioFlujoCajaIngreso(
                                 item.getCodigo(),
                                socio.getCodigoPersona().getCodigo()),
                                BigDecimal.ZERO,
                                BigDecimal.ZERO,
                                item)
                        );*/
                        SocioFlujoCajaIngreso socioFlujoCajaIngreso = new SocioFlujoCajaIngreso();
                        SocioFlujoCajaIngresoPK sfcipk = new SocioFlujoCajaIngresoPK(
                                item.getCodigo(),
                                socio.getCodigoPersona().getCodigo()
                        );

                        socioFlujoCajaIngreso.setSocioFlujoCajaIngresoPK(sfcipk);
                        socioFlujoCajaIngreso.setSocioFlujoCaja(new SocioFlujoCaja(socio.getCodigoPersona().getCodigo(),
                                socio.getSocioPK().getCodigo(),
                                socio.getSocioPK().getCodigoIfip()
                        )
                        );

                        socioFlujoCajaIngreso.setSocioFlujoCajaIngresoPK(sfcipk);
                        socioFlujoCajaIngreso.setItemFlujoCaja(item);
                        socioFlujoCajaIngreso.setTotalSocio(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                        socioFlujoCajaIngreso.setTotalConyuge(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                        listaNuevaFlujoCajaIngreso.add(socioFlujoCajaIngreso);
                    }
                    listaFlujoCajaIngreso = listaNuevaFlujoCajaIngreso;
                } else {
                    for (ItemFlujoCaja item : listaItemFlujoCajaIngreso) {
                        indice = 0;
                        nuevo = true;
                        for (SocioFlujoCajaIngreso itemSocio : listaNuevaFlujoCajaIngreso) {
                            if (item.getCodigo().compareTo(itemSocio.getItemFlujoCaja().getCodigo()) == 0) {
                                nuevo = false;
                                break;
                            }
                            indice++;
                        }
                        if (nuevo) {
                            /*listaEditaFlujoCajaIngreso.add(new SocioFlujoCajaIngreso(
                                    new SocioFlujoCajaIngresoPK(socio.getSocioPK().getCodigo(),
                                            socio.getSocioPK().getCodigoIfip(),
                                            item.getCodigo()),
                                    BigDecimal.ZERO,
                                    BigDecimal.ZERO,
                                    item)
                            );*/
                            SocioFlujoCajaIngreso socioFlujoCajaIngreso = new SocioFlujoCajaIngreso();
                            SocioFlujoCajaIngresoPK sfcipk = new SocioFlujoCajaIngresoPK(
                                    item.getCodigo(),
                                    socio.getCodigoPersona().getCodigo()
                            );

                            socioFlujoCajaIngreso.setSocioFlujoCajaIngresoPK(sfcipk);
                            socioFlujoCajaIngreso.setSocioFlujoCaja(new SocioFlujoCaja(socio.getCodigoPersona().getCodigo(),
                                    socio.getSocioPK().getCodigo(),
                                    socio.getSocioPK().getCodigoIfip()
                            )
                            );

                            socioFlujoCajaIngreso.setSocioFlujoCajaIngresoPK(sfcipk);
                            socioFlujoCajaIngreso.setItemFlujoCaja(item);
                            socioFlujoCajaIngreso.setTotalSocio(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                            socioFlujoCajaIngreso.setTotalConyuge(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                            listaEditaFlujoCajaIngreso.add(socioFlujoCajaIngreso);

                        } else {
                            listaEditaFlujoCajaIngreso.add(listaNuevaFlujoCajaIngreso.get(indice));
                        }
                    }
                    listaFlujoCajaIngreso = listaEditaFlujoCajaIngreso;
                }
                Collections.sort(listaFlujoCajaIngreso, new Comparator<SocioFlujoCajaIngreso>() {
                    @Override
                    public int compare(SocioFlujoCajaIngreso o1, SocioFlujoCajaIngreso o2) {
                        return o1.getItemFlujoCaja().getIndice().compareTo(o2.getItemFlujoCaja().getIndice());
                    }

                });
                return true;
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return false;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FlujoCajaSocioComponenteController", "cargaFlujoCajaIngreso", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean cargaFlujoCajaEgreso() {
        try {
            boolean nuevo = false;
            listaNuevaFlujoCajaEgreso = new ArrayList<SocioFlujoCajaEgreso>();
            listaEditaFlujoCajaEgreso = new ArrayList<SocioFlujoCajaEgreso>();
            indice = 0;
            if (socio != null) {
                listaNuevaFlujoCajaEgreso = ejbFacadeFlujoCajaEgreso.getItemsFlujoCajaEgresoSocio(socio.getSocioPK().getCodigo(), socio.getSocioPK().getCodigoIfip());
                if (listaNuevaFlujoCajaEgreso == null) {
                    nuevo = true;
                } else {
                    if (listaNuevaFlujoCajaEgreso.isEmpty()) {
                        nuevo = true;
                    }
                }
                if (nuevo) {
                    listaNuevaFlujoCajaEgreso = new ArrayList<SocioFlujoCajaEgreso>();
                    for (ItemFlujoCaja item : listaItemFlujoCajaEgreso) {
                        /*listaNuevaFlujoCajaEgreso.add(new SocioFlujoCajaEgreso(
                                new SocioFlujoCajaEgresoPK(socio.getSocioPK().getCodigo(),
                                        socio.getSocioPK().getCodigoIfip(),
                                        item.getCodigo()),
                                BigDecimal.ZERO,
                                item)
                        );*/
                        SocioFlujoCajaEgreso socioFlujoCajaEgreso = new SocioFlujoCajaEgreso();

                        SocioFlujoCajaEgresoPK sfcepk = new SocioFlujoCajaEgresoPK(
                                item.getCodigo(),
                                socio.getCodigoPersona().getCodigo()
                        );

                        socioFlujoCajaEgreso.setSocioFlujoCajaEgresoPK(sfcepk);
                        socioFlujoCajaEgreso.setSocioFlujoCaja(new SocioFlujoCaja(socio.getCodigoPersona().getCodigo(),
                                socio.getSocioPK().getCodigo(),
                                socio.getSocioPK().getCodigoIfip()
                        )
                        );
                        socioFlujoCajaEgreso.setItemFlujoCaja(item);
                        socioFlujoCajaEgreso.setTotalSocio(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                        listaNuevaFlujoCajaEgreso.add(socioFlujoCajaEgreso);
                    }
                    listaFlujoCajaEgreso = listaNuevaFlujoCajaEgreso;
                } else {
                    for (ItemFlujoCaja item : listaItemFlujoCajaEgreso) {
                        indice = 0;
                        nuevo = true;
                        for (SocioFlujoCajaEgreso itemSocio : listaNuevaFlujoCajaEgreso) {
                            if (item.getCodigo().compareTo(itemSocio.getItemFlujoCaja().getCodigo()) == 0) {
                                nuevo = false;
                                break;
                            }
                            indice++;
                        }
                        if (nuevo) {
                            /*listaEditaFlujoCajaEgreso.add(new SocioFlujoCajaEgreso(
                                    new SocioFlujoCajaEgresoPK(socio.getSocioPK().getCodigo(),
                                            socio.getSocioPK().getCodigoIfip(),
                                            item.getCodigo()),
                                    BigDecimal.ZERO,
                                    item)
                            );*/
                            SocioFlujoCajaEgreso socioFlujoCajaEgreso = new SocioFlujoCajaEgreso();

                            SocioFlujoCajaEgresoPK sfcepk = new SocioFlujoCajaEgresoPK(
                                    item.getCodigo(),
                                    socio.getCodigoPersona().getCodigo()
                            );

                            socioFlujoCajaEgreso.setSocioFlujoCajaEgresoPK(sfcepk);
                            socioFlujoCajaEgreso.setSocioFlujoCaja(new SocioFlujoCaja(socio.getCodigoPersona().getCodigo(),
                                    socio.getSocioPK().getCodigo(),
                                    socio.getSocioPK().getCodigoIfip()
                            )
                            );
                            socioFlujoCajaEgreso.setItemFlujoCaja(item);
                            socioFlujoCajaEgreso.setTotalSocio(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                            listaEditaFlujoCajaEgreso.add(socioFlujoCajaEgreso);
                        } else {
                            listaEditaFlujoCajaEgreso.add(listaNuevaFlujoCajaEgreso.get(indice));
                        }
                    }
                    listaFlujoCajaEgreso = listaEditaFlujoCajaEgreso;
                }
                Collections.sort(listaFlujoCajaEgreso, new Comparator<SocioFlujoCajaEgreso>() {
                    @Override
                    public int compare(SocioFlujoCajaEgreso o1, SocioFlujoCajaEgreso o2) {
                        return o1.getItemFlujoCaja().getIndice().compareTo(o2.getItemFlujoCaja().getIndice());
                    }

                });
                return true;
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return false;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FlujoCajaSocioComponenteController", "cargaFlujoCajaEgreso", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void vaciarComponente() {
        try {
            listaItemFlujoCajaIngreso = null;
            listaNuevaFlujoCajaIngreso = null;
            listaEditaFlujoCajaIngreso = null;
            listaFlujoCajaIngreso = null;
            listaItemFlujoCajaEgreso = null;
            listaNuevaFlujoCajaEgreso = null;
            listaEditaFlujoCajaEgreso = null;
            listaFlujoCajaEgreso = null;
            socio = null;
            flujoCaja = null;
            flujoGuardado = false;
            nuevoFlujoCaja = false;
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "flujoCajaForm"));
        } catch (Exception ex) {
            socio = null;
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FlujoCajaSocioComponenteController", "vaciarComponente", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void guardar() {
        try {
            if (editable) {
                if (FacesContext.getCurrentInstance().isValidationFailed()) {
                    flujoGuardado = false;
                    RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "flujoCajaComponenteResultadoGuardar"));
                    if (mensajeErrorGuardar) {
                        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Flujo de caja", mensaje));
                    }
                    return;
                }
                if (!validaTotalIngresosContraEgresos()) {
                    flujoGuardado = false;
                    RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "flujoCajaComponenteResultadoGuardar"));
                    if (mensajeErrorGuardar) {
                        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Flujo de caja", mensaje));
                    }
                    return;
                }
                if (nuevoFlujoCaja) {
                    ejbFacadeFlujoCaja.create(flujoCaja);
                    // Flujo de Caja - Ingresos
                    for (SocioFlujoCajaIngreso item : listaFlujoCajaIngreso) {
                        ejbFacadeFlujoCajaIngreso.create(item);
                    }
                    // Flujo de Caja - Egresos        
                    for (SocioFlujoCajaEgreso item : listaFlujoCajaEgreso) {
                        ejbFacadeFlujoCajaEgreso.create(item);
                    }
                } else {
                    flujoCaja.setFechaActualizacion(new Date());
                    flujoCaja.setCodigoUsuarioActualizo(ActivacionUsuario.getCodigoUsuario());
                    ejbFacadeFlujoCaja.actualiza(flujoCaja);
                    // Flujo de Caja - Ingresos
                    for (SocioFlujoCajaIngreso item : listaFlujoCajaIngreso) {
                        if (ejbFacadeFlujoCajaIngreso.find(item.getSocioFlujoCajaIngresoPK()) == null) {
                            ejbFacadeFlujoCajaIngreso.create(item);
                        } else {
                            ejbFacadeFlujoCajaIngreso.actualiza(item);
                        }
                    }
                    // Flujo de Caja - Egresos        
                    for (SocioFlujoCajaEgreso item : listaFlujoCajaEgreso) {
                        if (ejbFacadeFlujoCajaEgreso.find(item.getSocioFlujoCajaEgresoPK()) == null) {
                            ejbFacadeFlujoCajaEgreso.create(item);
                        } else {
                            ejbFacadeFlujoCajaEgreso.actualiza(item);
                        }
                    }
                }
                flujoGuardado = true;
                nuevoFlujoCaja = false;
                RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "flujoCajaComponenteResultadoGuardar"));
            } else {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Flujo de caja", "Componente cargado como no editable"));
            }
        } catch (Exception ex) {
            flujoGuardado = false;
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "flujoCajaComponenteResultadoGuardar"));
            if (mensajeErrorGuardar) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Flujo de caja", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCapaControl")));
            }
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FlujoCajaSocioComponenteController", "guardar", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean validaTotalIngresosContraEgresos() {
        BigDecimal totalIngresos = BigDecimal.ZERO;
        BigDecimal totalIngresosConyuge = BigDecimal.ZERO;
        BigDecimal totalEgresos = BigDecimal.ZERO;
        BigDecimal valorTotal = BigDecimal.ZERO;
        int fila = 0;
        ItemFlujoCaja itemFlujoCaja;
        ItemFlujoCajaMonto itemFlujoCajaMonto;
        try {
            if (listaFlujoCajaIngreso == null) {
                mensaje = "Debe completar todos los Item-Ingresos";
                FacesContext.getCurrentInstance().validationFailed();
                return false;
            } else {
                if (listaFlujoCajaIngreso.isEmpty()) {
                    mensaje = "Debe completar todos los Item-Ingresos";
                    FacesContext.getCurrentInstance().validationFailed();
                    return false;
                }
            }
            if (listaFlujoCajaEgreso == null) {
                mensaje = "Debe completar todos los Item-Egresos";
                FacesContext.getCurrentInstance().validationFailed();
                return false;
            } else {
                if (listaFlujoCajaEgreso.isEmpty()) {
                    mensaje = "Debe completar todos los Item-Egresos";
                    FacesContext.getCurrentInstance().validationFailed();
                    return false;
                }
            }
            //Recorre ingresos
            for (SocioFlujoCajaIngreso item : listaFlujoCajaIngreso) {
                itemFlujoCaja = listaFlujoCajaIngreso.get(fila).getItemFlujoCaja();
                if (listaItemFlujoCajaIngreso.contains(itemFlujoCaja)) {
                    itemFlujoCaja = listaItemFlujoCajaIngreso.get(listaItemFlujoCajaIngreso.indexOf(itemFlujoCaja));
                    itemFlujoCajaMonto = itemFlujoCaja.getItemFlujoCajaMonto();
                    if (itemFlujoCajaMonto != null) {
                        if (itemFlujoCajaMonto.getEliminado() == 'N') {
                            if (((item.getTotalSocio().compareTo(itemFlujoCajaMonto.getMontoMinimo()) == 1)
                                    || (item.getTotalSocio().compareTo(itemFlujoCajaMonto.getMontoMinimo()) == 0))
                                    && ((item.getTotalSocio().compareTo(itemFlujoCajaMonto.getMontoMaximo()) == -1)
                                    || (item.getTotalSocio().compareTo(itemFlujoCajaMonto.getMontoMaximo()) == 0))) {
                                if (((item.getTotalConyuge().compareTo(itemFlujoCajaMonto.getMontoMinimo()) == 1)
                                        || (item.getTotalConyuge().compareTo(itemFlujoCajaMonto.getMontoMinimo()) == 0))
                                        && ((item.getTotalConyuge().compareTo(itemFlujoCajaMonto.getMontoMaximo()) == -1)
                                        || (item.getTotalConyuge().compareTo(itemFlujoCajaMonto.getMontoMaximo()) == 0))) {
                                } else {
                                    mensaje = "Ingresos:Item " + itemFlujoCaja.getNombre() + " (socio/conyuge) el valor del conyuge ingresado" + " en la fila " + (fila + 1) + " debe estar entre: " + itemFlujoCaja.getItemFlujoCajaMonto().getMontoMinimo() + " y " + itemFlujoCaja.getItemFlujoCajaMonto().getMontoMaximo();
                                    FacesContext.getCurrentInstance().validationFailed();
                                    return false;
                                }
                            } else {
                                mensaje = "Ingresos:Item " + itemFlujoCaja.getNombre() + " (socio/conyuge) el valor ingresado en la fila " + (fila + 1) + " debe estar entre: " + itemFlujoCaja.getItemFlujoCajaMonto().getMontoMinimo() + " y " + itemFlujoCaja.getItemFlujoCajaMonto().getMontoMaximo();
                                FacesContext.getCurrentInstance().validationFailed();
                                return false;
                            }
                        }
                    }
                }
                totalIngresos = totalIngresos.add(item.getTotalSocio());
                totalIngresosConyuge = totalIngresosConyuge.add(item.getTotalConyuge());
                fila++;
            }
            if (totalIngresos.compareTo(new BigDecimal("0")) == 0) {
                mensaje = "Total de ingresos debe ser mayor que 0.00";
                return false;
            }
            if (totalIngresosConyuge.compareTo(new BigDecimal("0")) == -1) {
                mensaje = "Total de ingresos de conyuge debe ser mayor o igual que 0.00";
                return false;
            }
            //Recorre egresos
            fila = 0;
            for (SocioFlujoCajaEgreso item : listaFlujoCajaEgreso) {
                itemFlujoCaja = listaFlujoCajaEgreso.get(fila).getItemFlujoCaja();
                if (listaItemFlujoCajaEgreso.contains(itemFlujoCaja)) {
                    itemFlujoCaja = listaItemFlujoCajaEgreso.get(listaItemFlujoCajaEgreso.indexOf(itemFlujoCaja));
                    itemFlujoCajaMonto = itemFlujoCaja.getItemFlujoCajaMonto();
                    if (itemFlujoCajaMonto != null) {
                        if (itemFlujoCajaMonto.getEliminado() == 'N') {
                            if (((item.getTotalSocio().compareTo(itemFlujoCajaMonto.getMontoMinimo()) == 1)
                                    || (item.getTotalSocio().compareTo(itemFlujoCajaMonto.getMontoMinimo()) == 0))
                                    && ((item.getTotalSocio().compareTo(itemFlujoCajaMonto.getMontoMaximo()) == -1)
                                    || (item.getTotalSocio().compareTo(itemFlujoCajaMonto.getMontoMaximo()) == 0))) {
                            } else {
                                mensaje = "Egresos:Item " + itemFlujoCaja.getNombre() + " el valor egreso" + " en la fila " + (fila + 1) + " debe estar entre: " + itemFlujoCaja.getItemFlujoCajaMonto().getMontoMinimo() + " y " + itemFlujoCaja.getItemFlujoCajaMonto().getMontoMaximo();
                                FacesContext.getCurrentInstance().validationFailed();
                                return false;
                            }
                        }
                    }
                }
                totalEgresos = totalEgresos.add(item.getTotalSocio());
                fila++;
            }
            if (totalEgresos.compareTo(new BigDecimal("0")) == 0) {
                mensaje = "Total de egresos debe ser mayor que 0.00";
                return false;
            }
            valorTotal = valorTotal.add(totalIngresos);
            valorTotal = valorTotal.add(totalIngresosConyuge);
            valorTotal = valorTotal.subtract(totalEgresos);
            if (valorTotal.compareTo(new BigDecimal("0")) == 0) {
                mensaje = "Total de (ingresos - egresos) debe ser mayor o igual que 0.00";
                return false;
            }
            mensaje = "";
            return true;
        } catch (Exception e) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FlujoCajaSocioComponenteController", "validaTotalIngresosContraEgresos", CapturaError.getErrorException(e)});
            return false;
        }
    }

    /**
     *
     * @param event
     */
    public void edicionCeldaIngresos(CellEditEvent event) {
        try {
            int fila = event.getRowIndex();
            ItemFlujoCaja itemFlujoCaja = listaFlujoCajaIngreso.get(fila).getItemFlujoCaja();
            if (listaItemFlujoCajaIngreso.contains(itemFlujoCaja)) {
                itemFlujoCaja = listaItemFlujoCajaIngreso.get(listaItemFlujoCajaIngreso.indexOf(itemFlujoCaja));
                ItemFlujoCajaMonto item = itemFlujoCaja.getItemFlujoCajaMonto();
                if (item != null) {
                    if (item.getEliminado() == 'N') {
                        BigDecimal valor = new BigDecimal(event.getNewValue().toString());
                        if (((valor.compareTo(item.getMontoMinimo()) == 1)
                                || (valor.compareTo(item.getMontoMinimo()) == 0))
                                && ((valor.compareTo(item.getMontoMaximo()) == -1)
                                || (valor.compareTo(item.getMontoMaximo()) == 0))) {

                        } else {
                            MuestraMensaje.addError("El valor ingresado debe estar entre: " + itemFlujoCaja.getItemFlujoCajaMonto().getMontoMinimo() + " y " + itemFlujoCaja.getItemFlujoCajaMonto().getMontoMaximo());
                            FacesContext.getCurrentInstance().validationFailed();
                            flujoGuardado = false;
                            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "flujoCajaComponenteResultadoGuardar"));
                            return;
                        }
                    }
                }
            }
            BigDecimal totalIngreso = new BigDecimal("0.00");
            BigDecimal totalConyugeIngreso = new BigDecimal("0.00");
            for (SocioFlujoCajaIngreso item : listaFlujoCajaIngreso) {
                totalIngreso = totalIngreso.add(item.getTotalSocio());
                totalConyugeIngreso = totalConyugeIngreso.add(item.getTotalConyuge());
            }
            flujoCaja.setTotalIngresosSocio(totalIngreso);
            flujoCaja.setTotalIngresosConyuge(totalConyugeIngreso);
            flujoCaja.setSaldo((flujoCaja.getTotalIngresosSocio().add(flujoCaja.getTotalIngresosConyuge())).subtract(flujoCaja.getTotalGastosSocio()));
            flujoCaja.setCobertura(flujoCaja.getSaldo());
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalIngresosSocio"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalIngresosConyuge"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalGastosSocio"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "saldo"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "cobertura"));
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FlujoCajaSocioComponenteController", "edicionCeldaIngresos", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void edicionCeldaEgresos(CellEditEvent event) {
        try {
            int fila = event.getRowIndex();
            ItemFlujoCaja itemFlujoCaja = listaFlujoCajaEgreso.get(fila).getItemFlujoCaja();
            if (listaItemFlujoCajaEgreso.contains(itemFlujoCaja)) {
                itemFlujoCaja = listaItemFlujoCajaEgreso.get(listaItemFlujoCajaEgreso.indexOf(itemFlujoCaja));
                ItemFlujoCajaMonto item = itemFlujoCaja.getItemFlujoCajaMonto();
                if (item != null) {
                    if (item.getEliminado() == 'N') {
                        BigDecimal valor = new BigDecimal(event.getNewValue().toString());
                        if (((valor.compareTo(item.getMontoMinimo()) == 1)
                                || (valor.compareTo(item.getMontoMinimo()) == 0))
                                && ((valor.compareTo(item.getMontoMaximo()) == -1)
                                || (valor.compareTo(item.getMontoMaximo()) == 0))) {
                        } else {
                            MuestraMensaje.addError("El valor ingresado debe estar entre: " + itemFlujoCaja.getItemFlujoCajaMonto().getMontoMinimo() + " y " + itemFlujoCaja.getItemFlujoCajaMonto().getMontoMaximo());
                            FacesContext.getCurrentInstance().validationFailed();
                            flujoGuardado = false;
                            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "flujoCajaComponenteResultadoGuardar"));
                            return;
                        }
                    }
                }
            }
            BigDecimal totalGastos = new BigDecimal("0.00");
            for (SocioFlujoCajaEgreso item : listaFlujoCajaEgreso) {
                totalGastos = totalGastos.add(item.getTotalSocio());
            }
            flujoCaja.setTotalGastosSocio(totalGastos);
            flujoCaja.setSaldo((flujoCaja.getTotalIngresosSocio().add(flujoCaja.getTotalIngresosConyuge())).subtract(flujoCaja.getTotalGastosSocio()));
            flujoCaja.setCobertura(flujoCaja.getSaldo());
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalIngresosSocio"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalIngresosConyuge"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalGastosSocio"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "saldo"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "cobertura"));
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FlujoCajaSocioComponenteController", "edicionCeldaEgresos", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * @return the listaSocioFlujoCajaIngreso
     */
    public List<SocioFlujoCajaIngreso> getListaFlujoCajaIngreso() {
        return listaFlujoCajaIngreso;
    }

    /**
     * @param listaFlujoCajaIngreso the listaFlujoCajaIngreso to set
     */
    public void setListaFlujoCajaIngreso(List<SocioFlujoCajaIngreso> listaFlujoCajaIngreso) {
        this.listaFlujoCajaIngreso = listaFlujoCajaIngreso;
    }

    /**
     * @return the flujoCaja
     */
    public SocioFlujoCaja getFlujoCaja() {
        return flujoCaja;
    }

    /**
     * @param flujoCaja the flujoCaja to set
     */
    public void setFlujoCaja(SocioFlujoCaja flujoCaja) {
        this.flujoCaja = flujoCaja;
    }

    /**
     * @return the listaFlujoCajaEgreso
     */
    public List<SocioFlujoCajaEgreso> getListaFlujoCajaEgreso() {
        return listaFlujoCajaEgreso;
    }

    /**
     * @param listaFlujoCajaEgreso the listaFlujoCajaEgreso to set
     */
    public void setListaFlujoCajaEgreso(List<SocioFlujoCajaEgreso> listaFlujoCajaEgreso) {
        this.listaFlujoCajaEgreso = listaFlujoCajaEgreso;
    }

    /**
     * @return the listaItemFlujoCajaIngreso
     */
    public List<ItemFlujoCaja> getListaItemFlujoCajaIngreso() {
        return listaItemFlujoCajaIngreso;
    }

    /**
     * @param listaItemFlujoCajaIngreso the listaItemFlujoCajaIngreso to set
     */
    public void setListaItemFlujoCajaIngreso(List<ItemFlujoCaja> listaItemFlujoCajaIngreso) {
        this.listaItemFlujoCajaIngreso = listaItemFlujoCajaIngreso;
    }

    /**
     * @return the listaItemFlujoCajaEgreso
     */
    public List<ItemFlujoCaja> getListaItemFlujoCajaEgreso() {
        return listaItemFlujoCajaEgreso;
    }

    /**
     * @param listaItemFlujoCajaEgreso the listaItemFlujoCajaEgreso to set
     */
    public void setListaItemFlujoCajaEgreso(List<ItemFlujoCaja> listaItemFlujoCajaEgreso) {
        this.listaItemFlujoCajaEgreso = listaItemFlujoCajaEgreso;
    }

    /**
     * @return the flujoGuardado
     */
    public boolean isFlujoGuardado() {
        return flujoGuardado;
    }

    /**
     * @param flujoGuardado the flujoGuardado to set
     */
    public void setFlujoGuardado(boolean flujoGuardado) {
        this.flujoGuardado = flujoGuardado;
    }
}
