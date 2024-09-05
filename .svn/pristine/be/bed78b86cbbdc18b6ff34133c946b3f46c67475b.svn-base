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
import ec.renafipse.mks.modelo.socios.ItemSituacionPatrimonial;
import ec.renafipse.mks.modelo.socios.ItemSituacionPatrimonialMon;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatAct;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatActPK;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatPas;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatPasPK;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonial;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonialPK;
import ec.renafipse.mks.negocio.socios.ItemSituacionPatrimonialFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatActFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatPasFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatrimonialFacade;
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
@ManagedBean(name = "situacionPatrimonialComponenteController")
@ViewScoped
@FacesComponent("situacionPatrimonialComponente")
public class SituacionPatrimonialComponenteController extends UINamingContainer implements Serializable {

    @EJB
    private SocioFacade ejbFacadeSocio;
    @EJB
    private ItemSituacionPatrimonialFacade ejbFacadeItemSituacionPatrimonial;
    @EJB
    private SocioSituacionPatrimonialFacade ejbFacadeSituacionPatrimonial;
    @EJB
    private SocioSituacionPatActFacade ejbFacadeSituacionPatrimonialActivo;
    @EJB
    private SocioSituacionPatPasFacade ejbFacadeSituacionPatrimonialPasivo;

    private List<ItemSituacionPatrimonial> listaItemSituacionPatrimonialActivo;
    private List<SocioSituacionPatAct> listaNuevaSituacionPatrimonialActivo;
    private List<SocioSituacionPatAct> listaEditaSituacionPatrimonialActivo;
    private List<SocioSituacionPatAct> listaSituacionPatrimonialActivo;

    private List<ItemSituacionPatrimonial> listaItemSituacionPatrimonialPasivo;
    private List<SocioSituacionPatPas> listaNuevaSituacionPatrimonialPasivo;
    private List<SocioSituacionPatPas> listaEditaSituacionPatrimonialPasivo;
    private List<SocioSituacionPatPas> listaSituacionPatrimonialPasivo;

    private SocioSituacionPatrimonial situacionPatrimonial;
    private boolean situacionPatrimonialGuardado;
    private boolean nuevaSituacionPatrimonial;
    private Socio socio;
    private int indice;
    private java.lang.Boolean editable;
    private java.lang.Boolean mensajeErrorGuardar;
    private String mensaje;

    @PostConstruct
    public void init() {
        try {
            listaItemSituacionPatrimonialActivo = null;
            listaNuevaSituacionPatrimonialActivo = null;
            listaEditaSituacionPatrimonialActivo = null;
            listaSituacionPatrimonialActivo = null;
            listaItemSituacionPatrimonialPasivo = null;
            listaNuevaSituacionPatrimonialPasivo = null;
            listaEditaSituacionPatrimonialPasivo = null;
            listaSituacionPatrimonialPasivo = null;
            socio = null;
            situacionPatrimonial = null;
            situacionPatrimonialGuardado = false;
            nuevaSituacionPatrimonial = false;
        } catch (Exception ex) {
            socio = null;
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SituacionPatrimonialComponenteController", "init", CapturaError.getErrorException(ex)});
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
            situacionPatrimonialGuardado = false;
            if (listaItemSituacionPatrimonialActivo == null && listaItemSituacionPatrimonialPasivo == null) {
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
                listaItemSituacionPatrimonialActivo = this.ejbFacadeItemSituacionPatrimonial.getItemsSituacionPatrimonial('A', 'N');
                listaItemSituacionPatrimonialPasivo = this.ejbFacadeItemSituacionPatrimonial.getItemsSituacionPatrimonial('P', 'N');
                socio = ejbFacadeSocio.getSocioPorCodigo(codigoSocio);
                if (cargaSituacioPatrimonial()) {
                    if (cargaSituacioPatrimonialActivo()) {
                        cargaSituacioPatrimonialPasivo();
                    }
                }
            }
        } catch (Exception ex) {
            socio = null;
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SituacionPatrimonialComponenteController", "cargarComponente", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean cargaSituacioPatrimonial() {
        try {
            if (socio != null) {
                situacionPatrimonial = ejbFacadeSituacionPatrimonial.getSocioSocioSituacionPatrimonialCodigoSocioIfip(socio.getSocioPK().getCodigo(), socio.getSocioPK().getCodigoIfip());
                if (situacionPatrimonial == null) {
                    situacionPatrimonial = new SocioSituacionPatrimonial(Long.valueOf("0"));
                    situacionPatrimonial.setTotalActivos(new BigDecimal("0.00"));
                    situacionPatrimonial.setTotalPasivos(new BigDecimal("0.00"));
                    situacionPatrimonial.setTotalPatrimonio(new BigDecimal("0.00"));
                    situacionPatrimonial.setCodigoUsuarioIngreso(ActivacionUsuario.getCodigoUsuario());
                    situacionPatrimonial.setFechaIngreso(new Date());
                    situacionPatrimonial.setFechaActualizacion(situacionPatrimonial.getFechaIngreso());
                    situacionPatrimonial.setCodigoUsuarioActualizo(ActivacionUsuario.getCodigoUsuario());
                    nuevaSituacionPatrimonial = true;
                } else {
                    nuevaSituacionPatrimonial = false;
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
                    new Object[]{"SituacionPatrimonialComponenteController", "cargaSituacioPatrimonial", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean cargaSituacioPatrimonialActivo() {
        try {
            boolean nuevo = false;
            listaNuevaSituacionPatrimonialActivo = new ArrayList<SocioSituacionPatAct>();
            listaEditaSituacionPatrimonialActivo = new ArrayList<SocioSituacionPatAct>();
            indice = 0;
            if (socio != null) {
                listaNuevaSituacionPatrimonialActivo = ejbFacadeSituacionPatrimonialActivo.getItemsSocioSituacionPatAct(socio.getSocioPK().getCodigo(), socio.getSocioPK().getCodigoIfip());
                if (listaNuevaSituacionPatrimonialActivo == null) {
                    nuevo = true;
                } else {
                    if (listaNuevaSituacionPatrimonialActivo.isEmpty()) {
                        nuevo = true;
                    }
                }
                if (nuevo) {
                    listaNuevaSituacionPatrimonialActivo = new ArrayList<SocioSituacionPatAct>();
                    for (ItemSituacionPatrimonial item : listaItemSituacionPatrimonialActivo) {
                        /*listaNuevaSituacionPatrimonialActivo.add(new SocioSituacionPatAct(new SocioSituacionPatActPK(socio.getSocioPK().getCodigo(),
                                socio.getSocioPK().getCodigoIfip(),
                                item.getCodigo()),
                                BigDecimal.ZERO,
                                item));*/
                        SocioSituacionPatAct socioSituacionPatAct = new SocioSituacionPatAct();

                        SocioSituacionPatActPK sspapk = new SocioSituacionPatActPK(item.getCodigo(),
                                socio.getCodigoPersona().getCodigo());

                        socioSituacionPatAct.setSocioSituacionPatActPK(sspapk);
                        socioSituacionPatAct.setSocioSituacionPatrimonial(new SocioSituacionPatrimonial(
                                socio.getCodigoPersona().getCodigo(),
                                socio.getSocioPK().getCodigo(),
                                socio.getSocioPK().getCodigoIfip()
                        )
                        );
                        socioSituacionPatAct.setItemSituacionPatrimonial(item);
                        socioSituacionPatAct.setTotal(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                        listaNuevaSituacionPatrimonialActivo.add(socioSituacionPatAct);
                    }
                    listaSituacionPatrimonialActivo = listaNuevaSituacionPatrimonialActivo;
                } else {
                    for (ItemSituacionPatrimonial item : listaItemSituacionPatrimonialActivo) {
                        indice = 0;
                        nuevo = true;
                        for (SocioSituacionPatAct itemSocio : listaNuevaSituacionPatrimonialActivo) {
                            if (item.getCodigo().compareTo(itemSocio.getItemSituacionPatrimonial().getCodigo()) == 0) {
                                nuevo = false;
                                break;
                            }
                            indice++;
                        }
                        if (nuevo) {
                            /*listaEditaSituacionPatrimonialActivo.add(new SocioSituacionPatAct(new SocioSituacionPatActPK(socio.getSocioPK().getCodigo(),
                                    socio.getSocioPK().getCodigoIfip(),
                                    item.getCodigo()),
                                    BigDecimal.ZERO,
                                    item)
                            );*/
                            SocioSituacionPatAct socioSituacionPatAct = new SocioSituacionPatAct();

                            SocioSituacionPatActPK sspapk = new SocioSituacionPatActPK(item.getCodigo(),
                                    socio.getCodigoPersona().getCodigo());

                            socioSituacionPatAct.setSocioSituacionPatActPK(sspapk);
                            socioSituacionPatAct.setSocioSituacionPatrimonial(new SocioSituacionPatrimonial(
                                    socio.getCodigoPersona().getCodigo(),
                                    socio.getSocioPK().getCodigo(),
                                    socio.getSocioPK().getCodigoIfip()
                            )
                            );
                            socioSituacionPatAct.setItemSituacionPatrimonial(item);
                            socioSituacionPatAct.setTotal(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                            listaEditaSituacionPatrimonialActivo.add(socioSituacionPatAct);
                        } else {
                            listaEditaSituacionPatrimonialActivo.add(listaNuevaSituacionPatrimonialActivo.get(indice));
                        }
                    }
                    listaSituacionPatrimonialActivo = listaEditaSituacionPatrimonialActivo;
                }
                Collections.sort(listaSituacionPatrimonialActivo, new Comparator<SocioSituacionPatAct>() {
                    @Override
                    public int compare(SocioSituacionPatAct o1, SocioSituacionPatAct o2) {
                        return o1.getItemSituacionPatrimonial().getIndice().compareTo(o2.getItemSituacionPatrimonial().getIndice());
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
                    new Object[]{"SituacionPatrimonialComponenteController", "cargaSituacioPatrimonialActivo", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean cargaSituacioPatrimonialPasivo() {
        try {
            boolean nuevo = false;
            listaNuevaSituacionPatrimonialPasivo = new ArrayList<SocioSituacionPatPas>();
            listaEditaSituacionPatrimonialPasivo = new ArrayList<SocioSituacionPatPas>();
            indice = 0;
            if (socio != null) {
                listaNuevaSituacionPatrimonialPasivo = ejbFacadeSituacionPatrimonialPasivo.getItemsSocioSituacionPatPas(socio.getSocioPK().getCodigo(), socio.getSocioPK().getCodigoIfip());
                if (listaNuevaSituacionPatrimonialPasivo == null) {
                    nuevo = true;
                } else {
                    if (listaNuevaSituacionPatrimonialPasivo.isEmpty()) {
                        nuevo = true;
                    }
                }
                if (nuevo) {
                    listaNuevaSituacionPatrimonialPasivo = new ArrayList<SocioSituacionPatPas>();
                    for (ItemSituacionPatrimonial item : listaItemSituacionPatrimonialPasivo) {
                        /*listaNuevaSituacionPatrimonialPasivo.add(new SocioSituacionPatPas(new SocioSituacionPatPasPK(socio.getSocioPK().getCodigo(),
                                socio.getSocioPK().getCodigoIfip(),
                                item.getCodigo()),
                                BigDecimal.ZERO,
                                item));*/

                        SocioSituacionPatPas socioSituacionPatPas = new SocioSituacionPatPas();
                        SocioSituacionPatPasPK sspppk = new SocioSituacionPatPasPK(item.getCodigo(),
                                socio.getCodigoPersona().getCodigo());

                        socioSituacionPatPas.setSocioSituacionPatPasPK(sspppk);
                        socioSituacionPatPas.setSocioSituacionPatrimonial(new SocioSituacionPatrimonial(
                                socio.getCodigoPersona().getCodigo(),
                                socio.getSocioPK().getCodigo(),
                                socio.getSocioPK().getCodigoIfip()
                        ));
                        socioSituacionPatPas.setItemSituacionPatrimonial(item);
                        socioSituacionPatPas.setTotal(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                        listaNuevaSituacionPatrimonialPasivo.add(socioSituacionPatPas);
                    }
                    listaSituacionPatrimonialPasivo = listaNuevaSituacionPatrimonialPasivo;
                } else {
                    for (ItemSituacionPatrimonial item : listaItemSituacionPatrimonialPasivo) {
                        indice = 0;
                        nuevo = true;
                        for (SocioSituacionPatPas itemSocio : listaNuevaSituacionPatrimonialPasivo) {
                            if (item.getCodigo().compareTo(itemSocio.getItemSituacionPatrimonial().getCodigo()) == 0) {
                                nuevo = false;
                                break;
                            }
                            indice++;
                        }
                        if (nuevo) {
                            /*listaEditaSituacionPatrimonialPasivo.add(new SocioSituacionPatPas(new SocioSituacionPatPasPK(socio.getSocioPK().getCodigo(),
                                    socio.getSocioPK().getCodigoIfip(),
                                    item.getCodigo()),
                                    BigDecimal.ZERO,
                                    item)
                            );*/
                            SocioSituacionPatPas socioSituacionPatPas = new SocioSituacionPatPas();
                            SocioSituacionPatPasPK sspppk = new SocioSituacionPatPasPK(item.getCodigo(),
                                    socio.getCodigoPersona().getCodigo());

                            socioSituacionPatPas.setSocioSituacionPatPasPK(sspppk);
                            socioSituacionPatPas.setSocioSituacionPatrimonial(new SocioSituacionPatrimonial(
                                    socio.getCodigoPersona().getCodigo(),
                                    socio.getSocioPK().getCodigo(),
                                    socio.getSocioPK().getCodigoIfip()
                            ));
                            socioSituacionPatPas.setItemSituacionPatrimonial(item);
                            socioSituacionPatPas.setTotal(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                            listaEditaSituacionPatrimonialPasivo.add(socioSituacionPatPas);
                        } else {
                            listaEditaSituacionPatrimonialPasivo.add(listaNuevaSituacionPatrimonialPasivo.get(indice));
                        }
                    }
                    listaSituacionPatrimonialPasivo = listaEditaSituacionPatrimonialPasivo;
                }
                Collections.sort(listaSituacionPatrimonialPasivo, new Comparator<SocioSituacionPatPas>() {
                    @Override
                    public int compare(SocioSituacionPatPas o1, SocioSituacionPatPas o2) {
                        return o1.getItemSituacionPatrimonial().getIndice().compareTo(o2.getItemSituacionPatrimonial().getIndice());
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
                    new Object[]{"SituacionPatrimonialComponenteController", "cargaSituacioPatrimonialPasivo", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void guardar() {
        try {
            if (editable) {
                if (FacesContext.getCurrentInstance().isValidationFailed()) {
                    situacionPatrimonialGuardado = false;
                    RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "situacionPatrimonialComponenteResultadoGuardar"));
                    if (mensajeErrorGuardar) {
                        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Situacion Patrimonial", mensaje));
                    }
                    return;
                }
                if (!validaActivosYPasivos()) {
                    situacionPatrimonialGuardado = false;
                    RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "situacionPatrimonialComponenteResultadoGuardar"));
                    if (mensajeErrorGuardar) {
                        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Situacion Patrimonial", mensaje));
                    }
                    return;
                }
                if (nuevaSituacionPatrimonial) {
                    ejbFacadeSituacionPatrimonial.create(situacionPatrimonial);
                    // Situacion Patrimonial - Activos
                    for (SocioSituacionPatAct item : listaSituacionPatrimonialActivo) {
                        ejbFacadeSituacionPatrimonialActivo.create(item);
                    }
                    // Situacion Patrimonial - Pasivos
                    for (SocioSituacionPatPas item : listaSituacionPatrimonialPasivo) {
                        ejbFacadeSituacionPatrimonialPasivo.create(item);
                    }
                } else {
                    situacionPatrimonial.setFechaActualizacion(new Date());
                    situacionPatrimonial.setCodigoUsuarioActualizo(ActivacionUsuario.getCodigoUsuario());
                    ejbFacadeSituacionPatrimonial.actualiza(situacionPatrimonial);
                    // Flujo de Caja - Ingresos
                    for (SocioSituacionPatAct item : listaSituacionPatrimonialActivo) {
                        if (ejbFacadeSituacionPatrimonialActivo.find(item.getSocioSituacionPatActPK()) == null) {
                            ejbFacadeSituacionPatrimonialActivo.create(item);
                        } else {
                            ejbFacadeSituacionPatrimonialActivo.actualiza(item);
                        }
                    }
                    // Flujo de Caja - Egresos        
                    for (SocioSituacionPatPas item : listaSituacionPatrimonialPasivo) {
                        if (ejbFacadeSituacionPatrimonialPasivo.find(item.getSocioSituacionPatPasPK()) == null) {
                            ejbFacadeSituacionPatrimonialPasivo.create(item);
                        } else {
                            ejbFacadeSituacionPatrimonialPasivo.actualiza(item);
                        }
                    }
                }
                situacionPatrimonialGuardado = true;
                nuevaSituacionPatrimonial = false;
                RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "situacionPatrimonialComponenteResultadoGuardar"));
            } else {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Situacion Patrimonial", "Componente cargado como no editable"));
            }
        } catch (Exception ex) {
            situacionPatrimonialGuardado = false;
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "situacionPatrimonialComponenteResultadoGuardar"));
            if (mensajeErrorGuardar) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Situacion Patrimonial", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCapaControl")));
            }
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SituacionPatrimonialComponenteController", "guardar", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void edicionCeldaActivos(CellEditEvent event) {
        try {
            int fila = event.getRowIndex();
            ItemSituacionPatrimonial itemSituacionPatrimonial = listaSituacionPatrimonialActivo.get(fila).getItemSituacionPatrimonial();
            if (listaItemSituacionPatrimonialActivo.contains(itemSituacionPatrimonial)) {
                itemSituacionPatrimonial = listaItemSituacionPatrimonialActivo.get(listaItemSituacionPatrimonialActivo.indexOf(itemSituacionPatrimonial));
                ItemSituacionPatrimonialMon item = itemSituacionPatrimonial.getItemSituacionPatrimonialMon();
                if (item != null) {
                    if (item.getEliminado() == 'N') {
                        BigDecimal valor = new BigDecimal(event.getNewValue().toString());
                        if (((valor.compareTo(item.getMontoMinimo()) == 1)
                                || (valor.compareTo(item.getMontoMinimo()) == 0))
                                && ((valor.compareTo(item.getMontoMaximo()) == -1)
                                || (valor.compareTo(item.getMontoMaximo()) == 0))) {

                        } else {
                            MuestraMensaje.addError("El valor ingresado debe estar entre: " + itemSituacionPatrimonial.getItemSituacionPatrimonialMon().getMontoMinimo() + " y " + itemSituacionPatrimonial.getItemSituacionPatrimonialMon().getMontoMaximo());
                            FacesContext.getCurrentInstance().validationFailed();
                            situacionPatrimonialGuardado = false;
                            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "situacionPatrimonialComponenteResultadoGuardar"));
                            return;
                        }
                    }
                }
            }
            BigDecimal totalActivosSocio = new BigDecimal("0.00");
            for (SocioSituacionPatAct item : listaSituacionPatrimonialActivo) {
                totalActivosSocio = totalActivosSocio.add(item.getTotal());
            }
            situacionPatrimonial.setTotalActivos(totalActivosSocio);
            situacionPatrimonial.setTotalPatrimonio(situacionPatrimonial.getTotalActivos().subtract(situacionPatrimonial.getTotalPasivos()));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalActivosLabel"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalPasivosLabel"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalPatrimonioLabel"));
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SituacionPatrimonialComponenteController", "edicionCeldaActivos", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void edicionCeldaPasivos(CellEditEvent event) {
        try {
            int fila = event.getRowIndex();
            ItemSituacionPatrimonial itemSituacionPatrimonial = listaSituacionPatrimonialPasivo.get(fila).getItemSituacionPatrimonial();
            if (listaItemSituacionPatrimonialPasivo.contains(itemSituacionPatrimonial)) {
                itemSituacionPatrimonial = listaItemSituacionPatrimonialPasivo.get(listaItemSituacionPatrimonialPasivo.indexOf(itemSituacionPatrimonial));
                ItemSituacionPatrimonialMon item = itemSituacionPatrimonial.getItemSituacionPatrimonialMon();
                if (item != null) {
                    if (item.getEliminado() == 'N') {
                        BigDecimal valor = new BigDecimal(event.getNewValue().toString());
                        if (((valor.compareTo(item.getMontoMinimo()) == 1)
                                || (valor.compareTo(item.getMontoMinimo()) == 0))
                                && ((valor.compareTo(item.getMontoMaximo()) == -1)
                                || (valor.compareTo(item.getMontoMaximo()) == 0))) {

                        } else {
                            MuestraMensaje.addError("El valor ingresado debe estar entre: " + itemSituacionPatrimonial.getItemSituacionPatrimonialMon().getMontoMinimo() + " y " + itemSituacionPatrimonial.getItemSituacionPatrimonialMon().getMontoMaximo());
                            FacesContext.getCurrentInstance().validationFailed();
                            situacionPatrimonialGuardado = false;
                            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "situacionPatrimonialComponenteResultadoGuardar"));
                            return;
                        }
                    }
                }
            }
            BigDecimal totalPasivosSocio = new BigDecimal("0.00");
            for (SocioSituacionPatPas sspp : listaSituacionPatrimonialPasivo) {
                totalPasivosSocio = totalPasivosSocio.add(sspp.getTotal());
            }
            situacionPatrimonial.setTotalPasivos(totalPasivosSocio);
            situacionPatrimonial.setTotalPatrimonio(situacionPatrimonial.getTotalActivos().subtract(situacionPatrimonial.getTotalPasivos()));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalActivosLabel"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalPasivosLabel"));
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "totalPatrimonioLabel"));
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SituacionPatrimonialComponenteController", "edicionCeldaPasivos", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param mensaje
     */
    public void vaciarComponente() {
        try {
            listaItemSituacionPatrimonialActivo = null;
            listaItemSituacionPatrimonialPasivo = null;
            listaNuevaSituacionPatrimonialActivo = null;
            listaEditaSituacionPatrimonialActivo = null;
            listaSituacionPatrimonialActivo = null;
            listaSituacionPatrimonialPasivo = null;
            listaNuevaSituacionPatrimonialPasivo = null;
            listaEditaSituacionPatrimonialPasivo = null;
            socio = null;
            situacionPatrimonial = null;
            situacionPatrimonialGuardado = false;
            nuevaSituacionPatrimonial = false;
            RequestContext.getCurrentInstance().update(JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "situacionPatrimonialForm"));
        } catch (Exception ex) {
            socio = null;
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SituacionPatrimonialComponenteController", "vaciarComponente", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean validaActivosYPasivos() {
        BigDecimal totalActivos = BigDecimal.ZERO;
        BigDecimal totalPasivos = BigDecimal.ZERO;
        int fila = 0;
        ItemSituacionPatrimonial itemSituacionPatrimonial;
        ItemSituacionPatrimonialMon itemSituacionPatrimonialMonto;
        try {
            if (listaSituacionPatrimonialActivo == null) {
                mensaje = "Debe completar todos los Item-Situacion-Patrimonial-Activo";
                FacesContext.getCurrentInstance().validationFailed();
                return false;
            } else {
                if (listaSituacionPatrimonialActivo.isEmpty()) {
                    mensaje = "Debe completar todos los Item-Situacion-Patrimonial-Activo";
                    FacesContext.getCurrentInstance().validationFailed();
                    return false;
                }
            }
            if (listaSituacionPatrimonialPasivo == null) {
                mensaje = "Debe completar todos los Item-Situacion-Patrimonial-Pasivo";
                FacesContext.getCurrentInstance().validationFailed();
                return false;
            } else {
                if (listaSituacionPatrimonialPasivo.isEmpty()) {
                    mensaje = "Debe completar todos los Item-Situacion-Patrimonial-Pasivo";
                    FacesContext.getCurrentInstance().validationFailed();
                    return false;
                }
            }
            //Recorre Activo
            for (SocioSituacionPatAct item : listaSituacionPatrimonialActivo) {
                itemSituacionPatrimonial = listaSituacionPatrimonialActivo.get(fila).getItemSituacionPatrimonial();
                if (listaItemSituacionPatrimonialActivo.contains(itemSituacionPatrimonial)) {
                    itemSituacionPatrimonial = listaItemSituacionPatrimonialActivo.get(listaItemSituacionPatrimonialActivo.indexOf(itemSituacionPatrimonial));
                    itemSituacionPatrimonialMonto = itemSituacionPatrimonial.getItemSituacionPatrimonialMon();
                    if (itemSituacionPatrimonialMonto != null) {
                        if (itemSituacionPatrimonialMonto.getEliminado() == 'N') {
                            if (((item.getTotal().compareTo(itemSituacionPatrimonialMonto.getMontoMinimo()) == 1)
                                    || (item.getTotal().compareTo(itemSituacionPatrimonialMonto.getMontoMinimo()) == 0))
                                    && ((item.getTotal().compareTo(itemSituacionPatrimonialMonto.getMontoMaximo()) == -1)
                                    || (item.getTotal().compareTo(itemSituacionPatrimonialMonto.getMontoMaximo()) == 0))) {
                            } else {
                                mensaje = "Activos:Item " + itemSituacionPatrimonial.getNombre() + " el valor ingresado en la fila " + (fila + 1) + " debe estar entre: " + itemSituacionPatrimonial.getItemSituacionPatrimonialMon().getMontoMinimo() + " y " + itemSituacionPatrimonial.getItemSituacionPatrimonialMon().getMontoMaximo();
                                FacesContext.getCurrentInstance().validationFailed();
                                return false;
                            }
                        }
                    }
                }
                totalActivos = totalActivos.add(item.getTotal());
                fila++;
            }
            if (totalActivos.compareTo(new BigDecimal("0")) == 0) {
                mensaje = "Total de activos debe ser mayor que 0.00";
                return false;
            }
            //Recorre pasivos
            fila = 0;
            for (SocioSituacionPatPas item : listaSituacionPatrimonialPasivo) {
                itemSituacionPatrimonial = listaSituacionPatrimonialPasivo.get(fila).getItemSituacionPatrimonial();
                if (listaItemSituacionPatrimonialPasivo.contains(itemSituacionPatrimonial)) {
                    itemSituacionPatrimonial = listaItemSituacionPatrimonialPasivo.get(listaItemSituacionPatrimonialPasivo.indexOf(itemSituacionPatrimonial));
                    itemSituacionPatrimonialMonto = itemSituacionPatrimonial.getItemSituacionPatrimonialMon();
                    if (itemSituacionPatrimonialMonto != null) {
                        if (itemSituacionPatrimonialMonto.getEliminado() == 'N') {
                            if (((item.getTotal().compareTo(itemSituacionPatrimonialMonto.getMontoMinimo()) == 1)
                                    || (item.getTotal().compareTo(itemSituacionPatrimonialMonto.getMontoMinimo()) == 0))
                                    && ((item.getTotal().compareTo(itemSituacionPatrimonialMonto.getMontoMaximo()) == -1)
                                    || (item.getTotal().compareTo(itemSituacionPatrimonialMonto.getMontoMaximo()) == 0))) {
                            } else {
                                mensaje = "Pasivos:Item " + itemSituacionPatrimonial.getNombre() + " el valor ingresado en la fila " + (fila + 1) + " debe estar entre: " + itemSituacionPatrimonial.getItemSituacionPatrimonialMon().getMontoMinimo() + " y " + itemSituacionPatrimonial.getItemSituacionPatrimonialMon().getMontoMaximo();
                                FacesContext.getCurrentInstance().validationFailed();
                                return false;
                            }
                        }
                    }
                }
                totalPasivos = totalPasivos.add(item.getTotal());
                fila++;
            }
            if (totalPasivos.compareTo(new BigDecimal("0")) == 0) {
                mensaje = "Total de pasivos debe ser mayor que 0.00";
                return false;
            }
            mensaje = "";
            return true;
        } catch (Exception e) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SituacionPatrimonialComponenteController", "validaActivosYPasivos", CapturaError.getErrorException(e)});
            return false;
        }
    }

    /**
     * @return the listaSituacionPatrimonialActivo
     */
    public List<SocioSituacionPatAct> getListaSituacionPatrimonialActivo() {
        return listaSituacionPatrimonialActivo;
    }

    /**
     * @param listaSituacionPatrimonialActivo the
     * listaSituacionPatrimonialActivo to set
     */
    public void setListaSituacionPatrimonialActivo(List<SocioSituacionPatAct> listaSituacionPatrimonialActivo) {
        this.listaSituacionPatrimonialActivo = listaSituacionPatrimonialActivo;
    }

    /**
     * @return the situacionPatrimonial
     */
    public SocioSituacionPatrimonial getSituacionPatrimonial() {
        return situacionPatrimonial;
    }

    /**
     * @param situacionPatrimonial the situacionPatrimonial to set
     */
    public void setSituacionPatrimonial(SocioSituacionPatrimonial situacionPatrimonial) {
        this.situacionPatrimonial = situacionPatrimonial;
    }

    /**
     * @return the listaSituacionPatrimonialPasivo
     */
    public List<SocioSituacionPatPas> getListaSituacionPatrimonialPasivo() {
        return listaSituacionPatrimonialPasivo;
    }

    /**
     * @param listaSituacionPatrimonialPasivo the
     * listaSituacionPatrimonialPasivo to set
     */
    public void setListaSituacionPatrimonialPasivo(List<SocioSituacionPatPas> listaSituacionPatrimonialPasivo) {
        this.listaSituacionPatrimonialPasivo = listaSituacionPatrimonialPasivo;
    }

    /**
     * @return the situacionPatrimonialGuardado
     */
    public boolean isSituacionPatrimonialGuardado() {
        return situacionPatrimonialGuardado;
    }

    /**
     * @param situacionPatrimonialGuardado the situacionPatrimonialGuardado to
     * set
     */
    public void setSituacionPatrimonialGuardado(boolean situacionPatrimonialGuardado) {
        this.situacionPatrimonialGuardado = situacionPatrimonialGuardado;
    }

    /**
     * @return the socio
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }
}
