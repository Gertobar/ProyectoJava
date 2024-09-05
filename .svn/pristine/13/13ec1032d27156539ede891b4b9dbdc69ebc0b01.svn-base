package ec.renafipse.mks.control.cobranzas;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cobranzas.CobranzaExtrajudicial;
import ec.renafipse.mks.modelo.cobranzas.CobranzaExtrajudicialDet;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.ifips.ServicioFinancieroTipoCanal;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.cobranzas.CobranzaExtrajudicialDetFacade;
import ec.renafipse.mks.negocio.cobranzas.CobranzaExtrajudicialFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.ifips.ServicioFinancieroTipoCanalFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.CellEditEvent;

@ManagedBean(name="cobranzaExtrajudicialController")
@ViewScoped
public class CobranzaExtrajudicialController
  extends AbstractController<CobranzaExtrajudicial>
  implements Serializable
{
  @EJB
  private CobranzaExtrajudicialFacade ejbFacade;
  @EJB
  private CobranzaExtrajudicialDetFacade ejbDetalleFacade;
  @EJB
  private IfipFacade ejbFacadeIfip;
  @EJB
  private ServicioFinancieroTipoCanalFacade ejbFacadeServicioFinancieroTipoCanal;
  private LlamaSP llamaSP;
  private Ifip ifip;
  private Usuario usuario;
  private FacesMessage msg;
  private List<CobranzaExtrajudicial> listaCobranzaExtrajudicial;
  private List<CobranzaExtrajudicialDet> listaCobranzaExtrajudicialDetAdicionar;
  private List<ServicioFinancieroTipoCanal> listaServicioFinancieroTipoCanal;
  private ServicioFinancieroTipoCanal servicioFinancieroTipoCanal;
  private CobranzaExtrajudicialDet cobranzaExtrajudicialDetNuevo;
  private ArrayList<CobranzaExtrajudicialDet> lista;
  private boolean celdaValida;
  private String mensajeValidaCelda;
  private String nombreCeldaValidada;
  private int resultadoNombreDetalle;
  private boolean detalleValido;
  private boolean edicion;
  
  public CobranzaExtrajudicialController() {}
  
  @PostConstruct
  public void init()
  {
    super.setFacade(ejbFacade);
    setIfip((Ifip)ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
    setUsuario(ActivacionUsuario.getUsuario());
    llamaSP = new LlamaSP();
    setListaCobranzaExtrajudicial(ejbFacade.getListaCobranzaExtrajudicialPorIfip(getIfip().getCodigo()));
    setListaServicioFinancieroTipoCanal(ejbFacadeServicioFinancieroTipoCanal.getServicioFinancieroTipoCanalPorCodigoIfip(getIfip().getCodigo()));
  }
  
  public void nuevo(ActionEvent event) {
    setUsuario(getUsuario());
    setSelected(new CobranzaExtrajudicial());
    ((CobranzaExtrajudicial)getSelected()).setFechaModificacion(new Date());
    ((CobranzaExtrajudicial)getSelected()).setEliminado('N');
    edicion = false;
    setListaCobranzaExtrajudicialDetAdicionar(new ArrayList());
  }
  
  public void edita(ActionEvent event) {
    if (getSelected() != null) {
      edicion = true;
      setUsuario(getUsuario());
      setListaCobranzaExtrajudicialDetAdicionar(ejbDetalleFacade.getListaCobranzaExtrajudicialDetPorCobranza(((CobranzaExtrajudicial)getSelected()).getCodigo()));
    }
    else {
      MuestraMensaje.addError("Seleccione un registro primero");
    }
  }
  
  public void cargaListaDetalle(Long codigo) {
    try {
      setListaCobranzaExtrajudicialDetAdicionar(ejbDetalleFacade.getListaCobranzaExtrajudicialDetPorCobranza(codigo));
    }
    catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", CapturaError.getErrorException(e));
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e), new Object[] { "cobranzaExtrajudicialController", "cargaListaDetalle", CapturaError.getErrorException(e) });
      
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }
  
  public void guarda(ActionEvent event) {
    try {
      validaFormulario();
      if (FacesContext.getCurrentInstance().isValidationFailed()) {
        return;
      }
      ((CobranzaExtrajudicial)getSelected()).setCodigoIfip(getIfip());
      ((CobranzaExtrajudicial)getSelected()).setCodigoUsuario(getUsuario().getCodigo());
      ((CobranzaExtrajudicial)getSelected()).setCobranzaExtrajudicialDetList(getListaCobranzaExtrajudicialDetAdicionar());
      if (!edicion) {
        ejbFacade.create(getSelected());
      } else {
        ejbFacade.edit(getSelected());
      }
      setListaCobranzaExtrajudicial(ejbFacade.getListaCobranzaExtrajudicialPorIfip(getIfip().getCodigo()));
      MuestraMensaje.addSatisfactorioPersistencia(1);
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", CapturaError.getErrorPersistencia(e));
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorPersistencia(e), new Object[] { "cobranzaExtrajudicialController", "guarda", CapturaError.getErrorPersistencia(e) });
      
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }
  
  public void adicionarDetalle(ActionEvent event) {
    try {
      if (getListaServicioFinancieroTipoCanal() == null) {
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorServicioFinancieroTipoCanal"));
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
      else if (getListaServicioFinancieroTipoCanal().size() <= 0) {
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorServicioFinancieroTipoCanal"));
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
      

      if (getListaCobranzaExtrajudicialDetAdicionar() != null) {
        cobranzaExtrajudicialDetNuevo = new CobranzaExtrajudicialDet();
        cobranzaExtrajudicialDetNuevo.setCodigoCobranzaExtrajudicial((CobranzaExtrajudicial)getSelected());
        cobranzaExtrajudicialDetNuevo.setEliminado('N');
        cobranzaExtrajudicialDetNuevo.setCodigoServicioFinancieroTipoCanal((ServicioFinancieroTipoCanal)getListaServicioFinancieroTipoCanal().get(0));
        cobranzaExtrajudicialDetNuevo.setCalculaPorDiferencia('S');
        cobranzaExtrajudicialDetNuevo.setNombre("");
        cobranzaExtrajudicialDetNuevo.setNumeroDiaFinal(BigInteger.ZERO);
        cobranzaExtrajudicialDetNuevo.setNumeroDiaInicial(BigInteger.ZERO);
        cobranzaExtrajudicialDetNuevo.setValorMaximo(BigDecimal.ZERO);
        getListaCobranzaExtrajudicialDetAdicionar().add(cobranzaExtrajudicialDetNuevo);
      } else {
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAdicionarFila"));
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAdicionarFila"));
      Logger.getLogger(CobranzaExtrajudicialController.class.getName()).log(Level.SEVERE, null, e);
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }
  
  public void eliminarDetalle(Long codigo, int indice) {
    try {
      if (codigo == null) {
        lista = ((ArrayList)getListaCobranzaExtrajudicialDetAdicionar());
        lista.remove(indice);
        setListaCobranzaExtrajudicialDetAdicionar(lista);
      }
      else if (codigo.longValue() == 0L) {
        lista = ((ArrayList)getListaCobranzaExtrajudicialDetAdicionar());
        lista.remove(indice);
        setListaCobranzaExtrajudicialDetAdicionar(lista);
      } else {
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorEliminarFila") + ", no se puede eliminar un registro grabado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorEliminarFila") + CapturaError.getErrorException(e));
      Logger.getLogger(CobranzaExtrajudicialController.class.getName()).log(Level.SEVERE, null, e);
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }
  
  public void editaCelda(CellEditEvent event) {
    try {
      celdaValida = true;
      mensajeValidaCelda = "";
      Object valor = event.getNewValue();
      nombreCeldaValidada = event.getColumn().getHeaderText().toUpperCase().replace(" ", "");
      lista = ((ArrayList)getListaCobranzaExtrajudicialDetAdicionar());
      for (int i = 0; i < lista.size(); i++) {
        if (i != event.getRowIndex()) {
          if ((nombreCeldaValidada.equals("NOMBRE")) && (((CobranzaExtrajudicialDet)lista.get(i)).getNombre().equals(valor)))
          {
            celdaValida = false;
            
            mensajeValidaCelda = ("El nombre ya se encuentra asignado en la fila: " + (i + 1));
            break;
          }
          
          if ((nombreCeldaValidada.equals("DIAINICIAL")) && (((CobranzaExtrajudicialDet)lista.get(i)).getNumeroDiaInicial().equals(valor)))
          {
            celdaValida = false;
            
            mensajeValidaCelda = ("El día inicial ya se encuentra asignado en la fila: " + (i + 1));
            break;
          }
          
          if ((nombreCeldaValidada.equals("DIAFINAL")) && (((CobranzaExtrajudicialDet)lista.get(i)).getNumeroDiaFinal().equals(valor)))
          {
            celdaValida = false;
            
            mensajeValidaCelda = ("El día final ya se encuentra asignado en la fila: " + (i + 1));
            break;
          }
        } else {
          if ((nombreCeldaValidada.equals("NOMBRE")) && ((valor.toString().replace(" ", "").length() < 5) || (valor.toString().replace(" ", "").length() > 50))) {
            celdaValida = false;
            
            mensajeValidaCelda = ("El nombre debe estar entre 5 a 50 caracteres en la fila: " + (i + 1));
            break;
          }
          
          if ((nombreCeldaValidada.equals("DIAFINAL")) && (((CobranzaExtrajudicialDet)lista.get(i)).getNumeroDiaInicial().compareTo(((CobranzaExtrajudicialDet)lista.get(i)).getNumeroDiaFinal()) == 1))
          {
            celdaValida = false;
            
            mensajeValidaCelda = ("El día inicial debe ser mayor al dia final en la fila: " + (i + 1));
            break;
          }
        }
      }
      
      if (!celdaValida) {
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensajeValidaCelda);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().validationFailed();
      }
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorEditarCelda") + e.getLocalizedMessage());
      Logger.getLogger(CobranzaExtrajudicialController.class.getName()).log(Level.SEVERE, null, e);
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }
  
  public void validaFormulario() {
    boolean valido = validaRangosCapital();
    if (!valido) {
      FacesContext.getCurrentInstance().validationFailed();
      return;
    }
    valido = validaNombreUnicoCapital();
    if (!valido) {
      FacesContext.getCurrentInstance().validationFailed();
      return;
    }
    lista = ((ArrayList)getListaCobranzaExtrajudicialDetAdicionar());
    if (lista != null) {
      for (int i = 0; i < lista.size(); i++) {
        detalleValido = validaDetalles((CobranzaExtrajudicialDet)lista.get(i), i);
        if (!detalleValido) {
          FacesContext.getCurrentInstance().validationFailed();
          break;
        }
      }
    }
  }
  
  public boolean validaRangosCapital() {
    boolean valido = false;
    try {
      if (getSelected() != null) {
        int menor = ((CobranzaExtrajudicial)getSelected()).getValorCapitalFinal().compareTo(((CobranzaExtrajudicial)getSelected()).getValorCapitalInicial());
        if (menor == -1) {
          msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RangoCapitalCobranza"));
          FacesContext.getCurrentInstance().addMessage(null, msg);
          return valido;
        }
        valido = true;
      } else {
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionarRegistro"));
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValidaRangoCapitalCobranza") + e.getLocalizedMessage());
      Logger.getLogger(CobranzaExtrajudicialController.class.getName()).log(Level.SEVERE, null, e);
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    return valido;
  }
  
  public boolean validaNombreUnicoCapital() {
    boolean valido = false;
    try {
      if (getSelected() != null) {
        if (edicion) {
          valido = true;
        } else {
          CobranzaExtrajudicial cobranzaExtrajudicial = ejbFacade.getCobranzaExtrajudicialPorNombre(getIfip().getCodigo(), ((CobranzaExtrajudicial)getSelected()).getNombre().toUpperCase());
          if (cobranzaExtrajudicial != null) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CapitalCobranzaNombreUnico"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
          } else {
            valido = true;
          }
        }
      } else {
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionarRegistro"));
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCapitalCobranzaNombre") + e.getLocalizedMessage());
      Logger.getLogger(CobranzaExtrajudicialController.class.getName()).log(Level.SEVERE, null, e);
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    return valido;
  }
  
  public boolean validaDetalles(CobranzaExtrajudicialDet cobranzaExtrajudicialDet, int indiceFila) {
    try {
      resultadoNombreDetalle = -1;
      llamaSP.cargaConexion();
      llamaSP.setCerrarConexion(false);
      llamaSP.autoCommit(false);
      llamaSP.setNombreSP("mks_creditos.pkm_cobranza_extrajudicial_det.p_exi_cob_ext_det_por_ifip_nom");
      celdaValida = true;
      lista = ((ArrayList)getListaCobranzaExtrajudicialDetAdicionar());
      
      for (int i = 0; i < lista.size(); i++) {
        if (i != indiceFila) {
          if (((CobranzaExtrajudicialDet)lista.get(i)).getNombre().equals(cobranzaExtrajudicialDet.getNombre())) {
            celdaValida = false;
            mensajeValidaCelda = ("El nombre ya se encuentra asignado en la fila: " + (i + 1));
            break;
          }
          if ((((CobranzaExtrajudicialDet)lista.get(i)).getNombre().replace(" ", "").length() < 5) || (((CobranzaExtrajudicialDet)lista.get(i)).getNombre().replace(" ", "").length() > 50)) {
            celdaValida = false;
            mensajeValidaCelda = ("El nombre debe tener una longitud entre 5 a 50 caracteres en la fila: " + (i + 1));
            break;
          }
          
          if (((CobranzaExtrajudicialDet)lista.get(i)).getNumeroDiaInicial().equals(cobranzaExtrajudicialDet.getNumeroDiaInicial())) {
            celdaValida = false;
            mensajeValidaCelda = ("El día inicial ya se encuentra asignado en la fila: " + (i + 1));
            break;
          }
          
          if (((CobranzaExtrajudicialDet)lista.get(i)).getNumeroDiaFinal().equals(cobranzaExtrajudicialDet.getNumeroDiaFinal())) {
            celdaValida = false;
            mensajeValidaCelda = ("El día final ya se encuentra asignado en la fila: " + (i + 1));
            break;
          }
        } else {
          if ((cobranzaExtrajudicialDet.getNombre().replace(" ", "").length() < 5) || (cobranzaExtrajudicialDet.getNombre().replace(" ", "").length() > 50)) {
            celdaValida = false;
            mensajeValidaCelda = ("El nombre debe estar entre 5 a 50 caracteres en la fila: " + (i + 1));
            break;
          }
          
          if (((CobranzaExtrajudicialDet)lista.get(i)).getNumeroDiaInicial().compareTo(((CobranzaExtrajudicialDet)lista.get(i)).getNumeroDiaFinal()) == 1) {
            celdaValida = false;
            mensajeValidaCelda = ("El día inicial debe ser mayor al dia final en la fila: " + (i + 1));
            break;
          }
        }
      }
      
      if ((celdaValida) && (edicion)) {
        llamaSP.setNumeroParametros(4);
        llamaSP.setListParametrosEntrada(new ArrayList());
        llamaSP.getListParametrosEntrada().add(new Object[] { "pt_codigo_ifip", getIfip().getCodigo() });
        llamaSP.getListParametrosEntrada().add(new Object[] { "pt_nombre_cobranza_detalle", cobranzaExtrajudicialDet.getNombre() });
        llamaSP.getListParametrosEntrada().add(new Object[] { "pt_codigo_cobranza_ext", cobranzaExtrajudicialDet.getCodigo() });
        llamaSP.setListParametrosSalida(new ArrayList());
        llamaSP.getListParametrosSalida().add(new Object[] { "pn_existe", Integer.valueOf(4) });
        llamaSP.invocaSP();
        
        if (llamaSP.isEjecucionCorrecta()) {
          resultadoNombreDetalle = ((Integer)llamaSP.getListResultado().get(0)).intValue();
          if ((resultadoNombreDetalle > 0) && (cobranzaExtrajudicialDet.getCodigo().longValue() != resultadoNombreDetalle))
          {
            celdaValida = false;
            mensajeValidaCelda = ("El nombre ingresado para el detalle ya existe en la fila: " + (indiceFila + 1));
          }
          
          if (resultadoNombreDetalle < 0) {
            celdaValida = false;
            mensajeValidaCelda = ("Error al ejecutar validacion detalle nombres en la fila: " + (indiceFila + 1));
          }
        } else {
          celdaValida = false;
          mensajeValidaCelda = "Error al ejecutar validacion detalle nombres";
        }
      }
      
      if (!celdaValida) {
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensajeValidaCelda);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().validationFailed();
        return false;
      }
      return true;
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCapitalCobranzaNombre") + e.getLocalizedMessage());
      Logger.getLogger(CobranzaExtrajudicialController.class.getName()).log(Level.SEVERE, null, e);
      FacesContext.getCurrentInstance().addMessage(null, msg);
      return false;
    } finally {
      if (llamaSP.getConexionBD() != null) {
        llamaSP.cerrarConexion();
        llamaSP.setConexionBD(null);
      }
    }
  }
  
  public Ifip getIfip() {
    return ifip;
  }
  
  public void setIfip(Ifip ifip) {
    this.ifip = ifip;
  }
  
  public Usuario getUsuario() {
    return usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public List<CobranzaExtrajudicial> getListaCobranzaExtrajudicial() {
    return listaCobranzaExtrajudicial;
  }
  
  public void setListaCobranzaExtrajudicial(List<CobranzaExtrajudicial> listaCobranzaExtrajudicial) {
    this.listaCobranzaExtrajudicial = listaCobranzaExtrajudicial;
  }
  
  public List<CobranzaExtrajudicialDet> getListaCobranzaExtrajudicialDetAdicionar() {
    return listaCobranzaExtrajudicialDetAdicionar;
  }
  
  public void setListaCobranzaExtrajudicialDetAdicionar(List<CobranzaExtrajudicialDet> listaCobranzaExtrajudicialDetAdicionar) {
    this.listaCobranzaExtrajudicialDetAdicionar = listaCobranzaExtrajudicialDetAdicionar;
  }
  
  public List<ServicioFinancieroTipoCanal> getListaServicioFinancieroTipoCanal() {
    return listaServicioFinancieroTipoCanal;
  }
  
  public void setListaServicioFinancieroTipoCanal(List<ServicioFinancieroTipoCanal> listaServicioFinancieroTipoCanal) {
    this.listaServicioFinancieroTipoCanal = listaServicioFinancieroTipoCanal;
  }
  
  public ServicioFinancieroTipoCanal getServicioFinancieroTipoCanal() {
    return servicioFinancieroTipoCanal;
  }
  
  public void setServicioFinancieroTipoCanal(ServicioFinancieroTipoCanal servicioFinancieroTipoCanal) {
    this.servicioFinancieroTipoCanal = servicioFinancieroTipoCanal;
  }
}