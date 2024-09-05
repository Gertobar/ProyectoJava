package ec.renafipse.mks.control.dpfs;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.dpfs.TalonarioDocumentoDpf;
import ec.renafipse.mks.modelo.dpfs.TalonarioDocumentoDpfDet;
import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.negocio.dpfs.TalonarioDocumentoDpfDetFacade;
import ec.renafipse.mks.negocio.dpfs.TalonarioDocumentoDpfFacade;
import ec.renafipse.mks.negocio.ifips.ComputadorFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "talonarioDocumentoDpfController")
@ViewScoped
public class TalonarioDocumentoDpfController extends AbstractController<TalonarioDocumentoDpf> {

    @EJB
    private TalonarioDocumentoDpfFacade ejbFacade;

    @EJB
    private TalonarioDocumentoDpfDetFacade ejbFacadeTalDocDpfDet;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private ComputadorFacade ejbFacadeComputador;

    private LlamaSP llamaSP;

    private RequestContext context;
    private String msg;
    private Long inicioSerie;
    private Long finSerie;
    private String observaciones;
    private String urlSinPermisos;

    private TalonarioDocumentoDpfDet talonarioDocumentoDpfDetSel;    
    private Moneda moneda;

    private List<TalonarioDocumentoDpf> itemsTalonarioDocumentoDpf;
    private List<TalonarioDocumentoDpfDet> itemsTalonarioDocumentoDpfDet;
    private List<Computador> itemsComputador;

    /**
     * Initialize the concrete TalonarioDocumentoDpf controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     * @throws java.io.IOException
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();
        
        urlSinPermisos = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        if (ActivacionUsuario.getCodigoComputador() == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(urlSinPermisos);
            } catch (IOException ex) {
                 Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"talonarioDocumentoDpfController", "init", CapturaError.getErrorException(ex)});
            }
        }else
        {
           List<Computador> listaComputador = this.ejbFacadeComputador.getItemsCodigoComputadorIfiAgenPerEli(ActivacionUsuario.getCodigoComputador(), ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N');
           if (listaComputador.isEmpty())
           {
               ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoPerteneAgencia"));
               try {
                   Sesion.redireccionaPagina(urlSinPermisos);
               } catch (IOException ex) {
                   Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"talonarioDocumentoDpfController", "init", CapturaError.getErrorException(ex)});
               }
           }
        }
        this.obtieneTalonarioDpf();
        this.setItemsComputador(this.ejbFacadeComputador.getItemsIfipAgenciaEliminado(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N'));
    }

    public void nuevo(ActionEvent event) {
        this.setSelected(new TalonarioDocumentoDpf());

    }

    /**
     * Obtiene los Dpfs asignados a la Agencia
     */
    private void obtieneTalonarioDpf() {
        this.setItemsTalonarioDocumentoDpf(this.ejbFacade.getItemsCodigoIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia()));
    }

    public void moneda() {
        this.validaSerie();
    }

    /**
     * VALIDA LA SERIE
     */
    public void validaSerie() {
        this.msg = null;

        if (this.getSelected().getFinSerie() > 0 && this.getSelected().getInicioSerie() > 0) {
            if (this.getSelected().getFinSerie() <= this.getSelected().getInicioSerie()) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SerieFinTalonarioMenorSerieInicio");
                MuestraMensaje.addAdvertencia(msg);
                return;
            }

            List<TalonarioDocumentoDpf> listaTalonarioDocumentoDpf;
            listaTalonarioDocumentoDpf = this.ejbFacade.getItemsUnique(ActivacionUsuario.getCodigoIfip(), this.getSelected().getMoneda().getCodigo(), this.getSelected().getInicioSerie());
            if (!listaTalonarioDocumentoDpf.isEmpty()) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SerieIncioTalonarioExistente");
                MuestraMensaje.addAdvertencia(msg);
                return;
            }

            listaTalonarioDocumentoDpf = this.ejbFacade.getItemsRangoSerie(ActivacionUsuario.getCodigoIfip(), this.getSelected().getMoneda().getCodigo(), this.getSelected().getInicioSerie());
            if (!listaTalonarioDocumentoDpf.isEmpty()) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SerieIncioTalonarioExistente");
                MuestraMensaje.addAdvertencia(msg);

            }
        }
    }

    public void guardar(ActionEvent actionEvent) {
        try {
            this.validaSerie();
            if (this.msg == null) {
                this.getSelected().setCodigoMoneda(this.getSelected().getMoneda().getCodigo());
                this.getSelected().setCodigoIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia());
                this.getSelected().setCodigoIfip(ActivacionUsuario.getCodigoIfip());
                this.getSelected().setCodigoComputador(this.getSelected().getComputador().getCodigo());
                this.getSelected().setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
                this.getSelected().setFechaRegistro(new Date());
                this.ejbFacade.create(this.getSelected());
                
                
             
                
            // Insertando el Detalle del Talonario de los DPFS           
            String numerpoDPf;
            TalonarioDocumentoDpfDet dpfDet;
             int digitosDpf=(int)this.getSelected().getDigitos();
            for (int c=(int)this.getSelected().getInicioSerie(); c<=(int)this.getSelected().getFinSerie(); c++)
            {
                //System.out.println("getSelected "+getSelected().getCodigo());
                numerpoDPf = String.format("%0"+digitosDpf+"d",c);
                dpfDet = new TalonarioDocumentoDpfDet();
                dpfDet.setCodigoTalonario(this.getSelected().getCodigo());
                dpfDet.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
                dpfDet.setEstado('P');
                dpfDet.setNumero(numerpoDPf);
                dpfDet.setSerie(Long.parseLong(String.valueOf(c)));
                
                this.ejbFacadeTalDocDpfDet.create(dpfDet);
                
            }
            
            this.obtieneTalonarioDpf();
            //Mensaje de Satisfactorio
            MuestraMensaje.addSatisfactorioPersistencia(1);
            
            }
        } catch (Exception ex) {
            context = RequestContext.getCurrentInstance();
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"talonarioDocumentoDpfController", "guardar", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Edita los cambios realizados
     *
     * @param actionEvent
     */
    public void editar(ActionEvent actionEvent) {
        try {
            this.getSelected().setCodigoComputador(this.getSelected().getComputador().getCodigo());
            this.getSelected().setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            this.getSelected().setFechaRegistro(new Date());
            this.ejbFacade.edit(this.getSelected());
            MuestraMensaje.addSatisfactorioPersistencia(1);
            this.obtieneTalonarioDpf();

        } catch (Exception ex) {
            context = RequestContext.getCurrentInstance();
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"talonarioDocumentoDpfController", "actualizar", CapturaError.getErrorException(ex)});
        }
    }

    //-------------------------------------------------------------------------
    //-- ANUALCION DE DOCUMENTOS DE DPFS
    public void obtieneDocucumentos() {
        this.setItemsTalonarioDocumentoDpfDet(this.ejbFacadeTalDocDpfDet.getItemsAnulaDocumento(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoComputador(), this.getMoneda().getCodigo(), 'N', ActivacionUsuario.getCodigoIfipAgencia(), inicioSerie, finSerie));
    }

    public void guardaAnulacion() {
        context = RequestContext.getCurrentInstance();
        try {
                ////System.out.println("Guarda Movimiento");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            Timestamp fechaAnulacion = new java.sql.Timestamp(new Date().getTime());
            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_dpfs.pkm_anulacion_documento_dpf.p_anula");
            llamaSP.setNumeroParametros(6);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_documento", this.getTalonarioDocumentoDpfDetSel().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_anulado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_anulacion", fechaAnulacion});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", fechaAnulacion});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.observaciones});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DocumentoAnulado");
                MuestraMensaje.addInformacion(msg);
                this.obtieneDocucumentos();
                this.talonarioDocumentoDpfDetSel = null;

                //context.execute("MovimientoBovedaFor.update()");
            } else {
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
                    new Object[]{"contratoDpfController", "guardar", CapturaError.getErrorException(ex)});
        }
    }

    @Override
    protected void setEmbeddableKeys() {

    }

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

   
    // -- FIN DE COMBOS
    //------------------------------------------------------------------------------

    /**
     * @return the itemsTalonarioDocumentoDpf
     */
    public List<TalonarioDocumentoDpf> getItemsTalonarioDocumentoDpf() {
        return itemsTalonarioDocumentoDpf;
    }

    /**
     * @param itemsTalonarioDocumentoDpf the itemsTalonarioDocumentoDpf to set
     */
    public void setItemsTalonarioDocumentoDpf(List<TalonarioDocumentoDpf> itemsTalonarioDocumentoDpf) {
        this.itemsTalonarioDocumentoDpf = itemsTalonarioDocumentoDpf;
    }

    /**
     * @return the inicioSerie
     */
    public Long getInicioSerie() {
        return inicioSerie;
    }

    /**
     * @param inicioSerie the inicioSerie to set
     */
    public void setInicioSerie(Long inicioSerie) {
        this.inicioSerie = inicioSerie;
    }

    /**
     * @return the finSerie
     */
    public Long getFinSerie() {
        return finSerie;
    }

    /**
     * @param finSerie the finSerie to set
     */
    public void setFinSerie(Long finSerie) {
        this.finSerie = finSerie;
    }

    /**
     * @return the itemsTalonarioDocumentoDpfDet
     */
    public List<TalonarioDocumentoDpfDet> getItemsTalonarioDocumentoDpfDet() {
        return itemsTalonarioDocumentoDpfDet;
    }

    /**
     * @param itemsTalonarioDocumentoDpfDet the itemsTalonarioDocumentoDpfDet to
     * set
     */
    public void setItemsTalonarioDocumentoDpfDet(List<TalonarioDocumentoDpfDet> itemsTalonarioDocumentoDpfDet) {
        this.itemsTalonarioDocumentoDpfDet = itemsTalonarioDocumentoDpfDet;
    }

    /**
     * @return the talonarioDocumentoDpfDetSel
     */
    public TalonarioDocumentoDpfDet getTalonarioDocumentoDpfDetSel() {
        return talonarioDocumentoDpfDetSel;
    }

    /**
     * @param talonarioDocumentoDpfDetSel the talonarioDocumentoDpfDetSel to set
     */
    public void setTalonarioDocumentoDpfDetSel(TalonarioDocumentoDpfDet talonarioDocumentoDpfDetSel) {
        this.talonarioDocumentoDpfDetSel = talonarioDocumentoDpfDetSel;
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the itemsComputador
     */
    public List<Computador> getItemsComputador() {
        return itemsComputador;
    }

    /**
     * @param itemsComputador the itemsComputador to set
     */
    public void setItemsComputador(List<Computador> itemsComputador) {
        this.itemsComputador = itemsComputador;
    }

}
