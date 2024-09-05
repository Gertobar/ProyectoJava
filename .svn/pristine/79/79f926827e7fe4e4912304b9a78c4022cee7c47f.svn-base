/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.componentes;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import java.io.Serializable;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import org.primefaces.context.RequestContext;

/**
 *
 * @author andres
 */
@ManagedBean(name = "boletoComponenteController")
@ViewScoped
@FacesComponent("boletoComponente")
public class BoletoComponenteController extends UINamingContainer implements Serializable {

    private String styleComponente;
    private String mensaje;

    @EJB
    private LlamaSP llamaSP;

    /**
     *
     */
    @PostConstruct
    public void init() {
        try {
            styleComponente = "display: none !important;";
            mensaje = null;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BoletoComponenteController", "init", CapturaError.getErrorException(e)});
        }
    }

    /**
     *
     * @param codigoServicioBoleto
     * @param codigoServicio
     * @param widgetVar
     */
    public void abrirDialogo(java.lang.Long codigoServicioBoleto, java.lang.Long codigoServicio, java.lang.String widgetVar) {
        try {
            //System.out.println("codigoServicioBoleto: " + codigoServicioBoleto + "  codigoServicio: " + codigoServicio);
            mensaje = cargarMensaje(codigoServicioBoleto, codigoServicio);
            if  (mensaje != null) {
                styleComponente = "display: inline !important";
            }else{
                styleComponente = "display: none !important";
                cerrarDialogo(widgetVar);
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BoletoComponenteController", "abrirDialogo", CapturaError.getErrorException(e)});
            styleComponente = "display: none !important;";
            cerrarDialogo(widgetVar);
        }
    }

    /**
     *
     * @param widgetVar
     */
    public void cerrarDialogo(java.lang.String widgetVar) {
        try {
            widgetVar = "PF('" + widgetVar + "').hide();";
            RequestContext.getCurrentInstance().execute(widgetVar);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BoletoComponenteController", "cerrarDialogo", CapturaError.getErrorException(e)});
        }
    }

    /**
     *
     * @param codigoServicioBoleto
     * @param codigoServicio
     * @return
     */
    public String cargarMensaje(java.lang.Long codigoServicioBoleto, java.lang.Long codigoServicio) {
        try {
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_sorteos.pkm_sorteo.p_obtiene_boleto");
            llamaSP.setNumeroParametros(5);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_servicio_boleto", codigoServicioBoleto});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_servicio", codigoServicio});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia() });
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario() });
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje", Types.VARCHAR});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                llamaSP.commit();
                Object valorObject = llamaSP.getListResultado().get(0);
                if (valorObject == null){
                    return null;
                }else{
                    String valor = llamaSP.getListResultado().get(0).toString();
                    if (valor != null) {
                        if (valor.length() > 0) {
                            return valor;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                }
            } else {
                llamaSP.rollback();
                return null;
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BoletoComponenteController", "cargarMensaje", CapturaError.getErrorException(e)});
            if (llamaSP.getConexionBD() != null) {
                llamaSP.rollback();
            }
            return null;
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     * @return the styleComponente
     */
    public String getStyleComponente() {
        return styleComponente;
    }

    /**
     * @param styleComponente the styleComponente to set
     */
    public void setStyleComponente(String styleComponente) {
        this.styleComponente = styleComponente;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
