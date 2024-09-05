/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.capas.utilitario;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.sigma.factura.FacturaServicioConsumo;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author Andres
 */
@Stateless(name = "GeneraFacturaVenta")
@TransactionManagement(value=TransactionManagementType.BEAN)
public class GeneraFacturaVenta {
    private final LlamaSP llamaSP;
    private Long numeroVenta;
    
    public GeneraFacturaVenta(){
        llamaSP = new LlamaSP();
    }
    /**
     * 
     * @param codigoIfip Codigo de la ifip que genera la venta
     * @param codigoIfipAgencia Codigo de la agencia que genera la venta
     * @param codigoPersona Codigo de la persona a quien esta dirigida la venta
     * @param codigoUsuario Codigo del usuario que genera la factura
     * @param codigoTipoComprobante Codigo del tipo de comprobante de venta ejemplo 1=Factura, 2=Liquidacion, etc
     * @param gravaIva Si la venta carga iva
     * @param porcentajeIva Porcentaje del iva cargado ejemplo 12, 14, 14.01 etc.
     * @param valorIva Valor del iva cargado a la venta
     * @param valorDescuento Valor del descuento total en la venta
     * @param total Total de la venta
     * @param codigoEstadoVenta Codigo del estado con el cual se crea la venta ejemplo, 1 = Ingresada 2 = Contabilizada, etc
     * @param codigoItemVenta Codigo de lo que se esta vendiendo por ejemplo 1 = Notificación de crédito, etc
     * @param codigoSustento Codigo del sustento tributario para el SRI
     * @param observaciones Observaciones generales de la venta
     * @param detalleItemVenta Detalle del item que la venta
     * @param servicioProducto Nombre de servicio o producto que origino la venta ejemplo NotificacionCredito
     * @param procesoEjecucion Nombre del proceso que ejecuto o creo la venta ejemplo NotificacionCreditoController.GenraFactura 
     * @return Codigo de la venta creada
     */
    public Long generaFacturaVentaUnItemSinRetencion(Long codigoIfip, 
                                                     Long codigoIfipAgencia, 
                                                     Long codigoPersona, 
                                                     Long codigoUsuario, 
                                                     Long codigoTipoComprobante, 
                                                     boolean gravaIva, 
                                                     BigDecimal porcentajeIva, 
                                                     BigDecimal valorIva, 
                                                     BigDecimal valorDescuento, 
                                                     BigDecimal total, 
                                                     Long codigoEstadoVenta, 
                                                     Long codigoItemVenta, 
                                                     Long codigoSustento,
                                                     String observaciones, 
                                                     String detalleItemVenta, 
                                                     String servicioProducto, 
                                                     String procesoEjecucion){
        numeroVenta = Long.valueOf("0");
        try{
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            // Formando parametros para el sp
            llamaSP.setNombreSP("mks_adquisiciones.pkm_facturas.p_gen_fac_ven_un_item_sin_ret");
            llamaSP.setNumeroParametros(23);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", codigoIfip});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip_age", codigoIfipAgencia});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", codigoPersona});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", codigoUsuario});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_com", codigoTipoComprobante});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_subtotal", (total.subtract(valorDescuento).subtract(valorIva))});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_subtotal_con_iva", (total.subtract(valorDescuento))});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_subtotal_iva_cero", (total.subtract(valorIva).subtract(valorDescuento).add(BigDecimal.ZERO))});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_iva", valorIva});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_porcentaje_iva", porcentajeIva});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_grava_iva", gravaIva ? "S" : "N"});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_descuento", valorDescuento});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_total", total});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_estado", codigoEstadoVenta});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_retencion_iva", BigDecimal.ZERO});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_retencion_renta", BigDecimal.ZERO});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_item_venta", codigoItemVenta});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_sustento", codigoSustento});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_observaciones", observaciones});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_detalle_venta", detalleItemVenta});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_servicio_producto", servicioProducto});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_ejecutado_por", procesoEjecucion});
            
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo_venta", Types.VARCHAR});
            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                numeroVenta = Long.valueOf(llamaSP.getListResultado().get(0).toString());
            }
        }
        catch (Exception ex) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"GeneraFacturaVenta", "generaFacturaVentaSinRetencion", CapturaError.getErrorException(ex)});
        }finally{
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
        FacturaServicioConsumo factura = new FacturaServicioConsumo();
        factura.setItemId(codigoItemVenta.toString());
        factura.setValor(total.doubleValue());
        factura.setPersonaId(codigoPersona.toString());
        factura.setUsuarioCod(codigoUsuario.toString());
        factura.setTrxOrigen(numeroVenta.toString());
        factura.setServicio(codigoPersona.toString());
        factura.setDescripcionProducto(servicioProducto);

        factura.procesaServicio();
        if (factura.getErrorProcesaServicio() != null) {
            System.err.println("\n\nError al Procesar la Factura: " + factura.getErrorProcesaServicio());
        } else {
            System.out.println("\n\nNumero Factura: " + factura.getNumeroFactura());
        }
        System.out.println("\n\nPaso Genera Factura");
        return numeroVenta;
    }
}
