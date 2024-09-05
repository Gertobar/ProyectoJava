/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.creditos.lineacredito;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.modelo.auditorias.LineaCreditoSolicitudEstado;
import ec.renafipse.mks.modelo.creditos.lineacredito.AvanceLineaCredito;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoEstadoSol;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.auditorias.LineaCreditoSolicitudEstadoFacade;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.AvanceLineaCreditoFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoEstadoSolFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoSolicitudFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * @author andres
 */
@ManagedBean(name = "lineaCreditoSolicitudCambiaEstadoController")
@ViewScoped
public class LineaCreditoSolicitudCambiaEstadoController implements Serializable {
    
    @EJB
    private LineaCreditoEstadoSolFacade ejbFacadeLineaCreditoEstadoSol; 
    @EJB
    private LineaCreditoSolicitudFacade ejbFacadeLineaCreditoSolicitud; 
    @EJB
    private IfipFacade ejbFacadeIfip;
    @EJB
    private LlamaSP llamaSP;
    @EJB
    private SocioFacade ejbFacadeSocio;
    @EJB
    private LineaCreditoSolicitudEstadoFacade ejbFacadeLineaCreditoSolicitudEstado;
    @EJB
    private LineaCreditoFacade ejbFacadeLineaCredito;
    @EJB
    private AvanceLineaCreditoFacade ejbFacadeAvanceLineaCredito;
    
    private Usuario usuario;
    private Ifip ifip;
    private boolean permiteGuardar;
    private String nombreSocio;
    private LineaCreditoSolicitud lineaCreditoSolicitud;
    private List<LineaCreditoSolicitud> listaLineaCreditoSolicitud;
    private LineaCreditoEstadoSol lineaCreditoEstadoSolicitud;
    private List<LineaCreditoEstadoSol> listaLineaCreditoEstadoSolicitud;
    private String observacion;
    private Date fechaVencimiento;
    private BigDecimal capitalAPagar;
    private BigDecimal interesAPagar;
    private BigDecimal moraAPagar;
    private BigDecimal rubrosAPagar;
    private short diasMora;
    private short diasRetraso;
    private short cuotasRetraso;
    
    private String valoresLong;
    private Socio socio;
    
            
    @PostConstruct
    public void init() {
        try {
            setUsuario(ActivacionUsuario.getUsuario());
            setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
            listaLineaCreditoEstadoSolicitud = ejbFacadeLineaCreditoEstadoSol.getItemsPorCodigoYEliminado(Long.valueOf("7") ,'N');
            inicializaPropiedades();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "init", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * Guarda el cambio de estado de una solicitud de linea de credíto a cancelado
     */
    public void guardar(){
        //no tenga avances vigente y la solicitud no este en estado boqueada
        try{
            if (socio == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            if (lineaCreditoSolicitud == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneLineaCreditoSolicitud"));
                return;
            }
            /*if (lineaCreditoSolicitud.getCodigoLineaCreditoEstadoSol().compareTo(Long.valueOf("7")) == 0 ){
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("LineaCreditoSolicitudeEstadoBloqueada"));
                return;
            }*/
            if (lineaCreditoEstadoSolicitud == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneEstadoLineaCreditoSolicitud"));
                return;
            }
            if (observacion == null){
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ObservacionesRequerido"));
                return;
            }else{
                if (observacion.length() <= 0){
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ObservacionesRequerido"));
                    return;
                }
            }
            if( lineaCreditoEstadoSolicitud.getCodigo() == lineaCreditoSolicitud.getLineaCreditoEstadoSol().getCodigo() ){
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CambioAMismoEstado"));
                return;
            }
            if( lineaCreditoEstadoSolicitud.getCodigo()==8 && getDiasMora() > 0 ){
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoActivaPorDiasMora"));
                return;
            }
            if( lineaCreditoEstadoSolicitud.getCodigo()==9 ){
                //Verifica si es posible finalizar un contrato
                boolean puedeFinalizar = true;
                List<AvanceLineaCredito> listaAvanceLineaCredito = ejbFacadeAvanceLineaCredito.getListaLineaCreditoPorCodigoSolicitudPendientePago(lineaCreditoSolicitud.getCodigo());
                if( listaAvanceLineaCredito != null ){
                    if( listaAvanceLineaCredito.size()>0 ){
                        for( int a=0; a<listaAvanceLineaCredito.size();a++ ){
                            if( !listaAvanceLineaCredito.get(a).getEstado().equals("C") ){
                                puedeFinalizar = false;
                                break;
                            }
                        }
                    }
                }
                if( !puedeFinalizar ){
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoPuedeFinalizarLineaCredito"));
                    return;
                }
            }
            if (permiteGuardar) {
                if(lineaCreditoEstadoSolicitud.getCodigo()==9){
                    //Generar el contable de la finalizacion del contrato
                    if(generaContableFinalizacion())
                        return;
                }
                lineaCreditoSolicitud.setCodigoLineaCreditoEstadoSol(lineaCreditoEstadoSolicitud.getCodigo());
                lineaCreditoSolicitud.setObservaciones(observacion);
                ejbFacadeLineaCreditoSolicitud.edit(lineaCreditoSolicitud);
                LineaCreditoSolicitudEstado lineaCreditoSolicitudEstado = new LineaCreditoSolicitudEstado();
                lineaCreditoSolicitudEstado.setCodigoLineaCreditoSol(lineaCreditoSolicitud.getCodigo());
                lineaCreditoSolicitudEstado.setCodigoLineaCreEstSol(lineaCreditoEstadoSolicitud.getCodigo());
                lineaCreditoSolicitudEstado.setCodigoUsuario(usuario.getCodigo());
                lineaCreditoSolicitudEstado.setFecha(new java.sql.Timestamp(new Date().getTime()));
                lineaCreditoSolicitudEstado.setAccion('U');
                ejbFacadeLineaCreditoSolicitudEstado.create(lineaCreditoSolicitudEstado);
                permiteGuardar = false;
                MuestraMensaje.addSatisfactorioPersistencia(1);
            }
        }catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "guardar", CapturaError.getErrorException(ex)});
        }
    }
    /***
     * Metodo para genera el contable x finanlizacion
     * @return 
     */
    private boolean generaContableFinalizacion(){
        try{
            boolean bandera = false;
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_contables.pkm_linea_credito_contable.p_contabiliza_finalizacion_lin");
            llamaSP.setNumeroParametros(1);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_linea_credito_sol", lineaCreditoSolicitud.getCodigo()});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                llamaSP.commit();
                bandera = true;
            } else {
                llamaSP.rollback();
                bandera = false;
            }
            return bandera;
        }catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudCambiaEstadoController", "generaContableFinalizacion", CapturaError.getErrorException(ex)});
            return false;
        }
    }
    
    /**
     * 
     */
    public void obtieneSolicitudesDeLineaDesdeComponente(){
        try {
            inicializaPropiedades();
            valoresLong = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("criterioForm:componenteBusca:listaSociosComponenteForm:buscaSocioComponenteCodigoPersona");
            if (valoresLong == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            } else {
                if (valoresLong.length() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                    return;
                }
            }
            if ((Long.valueOf(valoresLong).compareTo(Long.valueOf("0")) == 0)) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            valoresLong = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("criterioForm:componenteBusca:listaSociosComponenteForm:buscaSocioComponenteCodigoSocio");
            if (valoresLong == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            } else {
                if (valoresLong.length() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                    return;
                }
            }
            if ((Long.valueOf(valoresLong).compareTo(Long.valueOf("0")) == 0)) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            socio = ejbFacadeSocio.getSocioPorCodigo(Long.valueOf(valoresLong));
            if (socio == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            nombreSocio = socio.getCodigoPersona().getNombreCompleto();
            //listaLineaCreditoSolicitud = ejbFacadeLineaCreditoSolicitud.getListaAprobadasSinAvanceVigente(socio.getSocioPK().getCodigo());
            listaLineaCreditoSolicitud = ejbFacadeLineaCreditoSolicitud.getListaLineaCreditoSolicitudParaCambioEstado(socio.getSocioPK().getCodigo());
            if (listaLineaCreditoSolicitud != null) {
                if (listaLineaCreditoSolicitud.size() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                }else{
                    //Colocar el nombre de la linea de credito
                    for(int a=0;a<listaLineaCreditoSolicitud.size();a++){
                        //Obtener el nombre de la linea de credito
                        listaLineaCreditoSolicitud.get(a).setNombreLineaCredito(ejbFacadeLineaCredito.getLineaCreditoByCodigo(listaLineaCreditoSolicitud.get(a).getCodigoLineaCredito()).getNombre());
                    }
                }
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoSolicitudController", "obtieneSolicitudesDeLineaDesdeComponente", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * Inicializa las propiedades de pantalla
     */
    public void inicializaPropiedades(){
        try{
            socio = null;
            nombreSocio = null;
            listaLineaCreditoSolicitud = null;
            lineaCreditoSolicitud = null;
            observacion = null;
            fechaVencimiento = null;
            capitalAPagar = null;
            interesAPagar = null;
            moraAPagar = null;
            rubrosAPagar = null;
            diasMora = 0;
            diasRetraso = 0;
            cuotasRetraso = 0;
            permiteGuardar = true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "inicializaPropiedades", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * 
     */
    public void cambiaLineaCreditoSolicitud(){
        try {
            fechaVencimiento = null;
            capitalAPagar = null;
            interesAPagar = null;
            moraAPagar = null;
            rubrosAPagar = null;
            diasMora = 0;
            diasRetraso = 0;
            cuotasRetraso = 0;
            permiteGuardar = true;
            if (socio == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocio"));
                return;
            }
            if (lineaCreditoSolicitud == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneLineaCreditoSolicitud"));
                return;
            }
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_creditos.pkm_avance_linea_credito.p_obtiene_total_deuda");
            llamaSP.setNumeroParametros(10);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_lin_cre_sol", lineaCreditoSolicitud.getCodigo()});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_monto", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pc_fecha_vencimiento", Types.CHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_capital_por_pagar", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_interes_pendiente", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_mora_pendiente", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_rubro_pendiente", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_dias_mora", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_dias_atraso", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_cuotas_atrasadas", Types.NUMERIC});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                if(llamaSP.getListResultado().get(1) != null)
                    fechaVencimiento = new SimpleDateFormat("dd-MM-yyyy").parse(llamaSP.getListResultado().get(1).toString());
                if(llamaSP.getListResultado().get(2) != null)
                    capitalAPagar = new BigDecimal(llamaSP.getListResultado().get(2).toString());
                if(llamaSP.getListResultado().get(3) != null)
                    interesAPagar = new BigDecimal(llamaSP.getListResultado().get(3).toString());
                if(llamaSP.getListResultado().get(4) != null)
                    moraAPagar = new BigDecimal(llamaSP.getListResultado().get(4).toString());
                if(llamaSP.getListResultado().get(5) != null)
                    rubrosAPagar = new BigDecimal(llamaSP.getListResultado().get(5).toString());
                if(llamaSP.getListResultado().get(6) != null)
                    diasMora = Short.valueOf(llamaSP.getListResultado().get(6).toString());
                if(llamaSP.getListResultado().get(7) != null)
                    diasRetraso = Short.valueOf(llamaSP.getListResultado().get(7).toString());
                if(llamaSP.getListResultado().get(8) != null)
                    cuotasRetraso = Short.valueOf(llamaSP.getListResultado().get(8).toString());
            }
        }catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAvanceController", "cambiaLineaCreditoSolicitud", CapturaError.getErrorException(ex)});
        }
        finally{
           if (llamaSP.getConexionBD() != null){
               llamaSP.cerrarConexion();
               llamaSP.setConexionBD(null);
           }
        }
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
     * @return the permiteGuardar
     */
    public boolean isPermiteGuardar() {
        return permiteGuardar;
    }

    /**
     * @param permiteGuardar the permiteGuardar to set
     */
    public void setPermiteGuardar(boolean permiteGuardar) {
        this.permiteGuardar = permiteGuardar;
    }

    /**
     * @return the nombreSocio
     */
    public String getNombreSocio() {
        return nombreSocio;
    }

    /**
     * @param nombreSocio the nombreSocio to set
     */
    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    /**
     * @return the lineaCreditoSolicitud
     */
    public LineaCreditoSolicitud getLineaCreditoSolicitud() {
        return lineaCreditoSolicitud;
    }

    /**
     * @param lineaCreditoSolicitud the lineaCreditoSolicitud to set
     */
    public void setLineaCreditoSolicitud(LineaCreditoSolicitud lineaCreditoSolicitud) {
        this.lineaCreditoSolicitud = lineaCreditoSolicitud;
    }

    /**
     * @return the listaLineaCreditoSolicitud
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitud() {
        return listaLineaCreditoSolicitud;
    }

    /**
     * @param listaLineaCreditoSolicitud the listaLineaCreditoSolicitud to set
     */
    public void setListaLineaCreditoSolicitud(List<LineaCreditoSolicitud> listaLineaCreditoSolicitud) {
        this.listaLineaCreditoSolicitud = listaLineaCreditoSolicitud;
    }

    /**
     * @return the lineaCreditoEstadoSolicitud
     */
    public LineaCreditoEstadoSol getLineaCreditoEstadoSolicitud() {
        return lineaCreditoEstadoSolicitud;
    }

    /**
     * @param lineaCreditoEstadoSolicitud the lineaCreditoEstadoSolicitud to set
     */
    public void setLineaCreditoEstadoSolicitud(LineaCreditoEstadoSol lineaCreditoEstadoSolicitud) {
        this.lineaCreditoEstadoSolicitud = lineaCreditoEstadoSolicitud;
    }

    /**
     * @return the listaLineaCreditoEstadoSolicitud
     */
    public List<LineaCreditoEstadoSol> getListaLineaCreditoEstadoSolicitud() {
        return listaLineaCreditoEstadoSolicitud;
    }

    /**
     * @param listaLineaCreditoEstadoSolicitud the listaLineaCreditoEstadoSolicitud to set
     */
    public void setListaLineaCreditoEstadoSolicitud(List<LineaCreditoEstadoSol> listaLineaCreditoEstadoSolicitud) {
        this.listaLineaCreditoEstadoSolicitud = listaLineaCreditoEstadoSolicitud;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the capitalAPagar
     */
    public BigDecimal getCapitalAPagar() {
        return capitalAPagar;
    }

    /**
     * @param capitalAPagar the capitalAPagar to set
     */
    public void setCapitalAPagar(BigDecimal capitalAPagar) {
        this.capitalAPagar = capitalAPagar;
    }

    /**
     * @return the interesAPagar
     */
    public BigDecimal getInteresAPagar() {
        return interesAPagar;
    }

    /**
     * @param interesAPagar the interesAPagar to set
     */
    public void setInteresAPagar(BigDecimal interesAPagar) {
        this.interesAPagar = interesAPagar;
    }

    /**
     * @return the moraAPagar
     */
    public BigDecimal getMoraAPagar() {
        return moraAPagar;
    }

    /**
     * @param moraAPagar the moraAPagar to set
     */
    public void setMoraAPagar(BigDecimal moraAPagar) {
        this.moraAPagar = moraAPagar;
    }

    /**
     * @return the rubrosAPagar
     */
    public BigDecimal getRubrosAPagar() {
        return rubrosAPagar;
    }

    /**
     * @param rubrosAPagar the rubrosAPagar to set
     */
    public void setRubrosAPagar(BigDecimal rubrosAPagar) {
        this.rubrosAPagar = rubrosAPagar;
    }

    /**
     * @return the diasMora
     */
    public short getDiasMora() {
        return diasMora;
    }

    /**
     * @param diasMora the diasMora to set
     */
    public void setDiasMora(short diasMora) {
        this.diasMora = diasMora;
    }

    /**
     * @return the diasRetraso
     */
    public short getDiasRetraso() {
        return diasRetraso;
    }

    /**
     * @param diasRetraso the diasRetraso to set
     */
    public void setDiasRetraso(short diasRetraso) {
        this.diasRetraso = diasRetraso;
    }

    /**
     * @return the cuotasRetraso
     */
    public short getCuotasRetraso() {
        return cuotasRetraso;
    }

    /**
     * @param cuotasRetraso the cuotasRetraso to set
     */
    public void setCuotasRetraso(short cuotasRetraso) {
        this.cuotasRetraso = cuotasRetraso;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
