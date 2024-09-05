/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.impresiones;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
import ec.renafipse.mks.modelo.ahorros.TransferenciaEntreCuentas;
import ec.renafipse.mks.modelo.cajas.IngresoEgresoCaja;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.creditos.PagoCredito;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacion;
import ec.renafipse.mks.modelo.creditos.lineacredito.AvanceLineaCredito;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCredito;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud;
import ec.renafipse.mks.modelo.creditos.lineacredito.PagoAvanceLineaCredito;
import ec.renafipse.mks.modelo.creditos.lineacredito.PagoLineaCreditoSolicitud;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.Socio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author v.astudillo
 */
public class ImpresionComprobantes extends ImpresionImpresora implements Serializable{

    
    private LlamaSP llamaSP;
    private String depositante = null;
    private String mensajeAdicional="";

    public ImpresionComprobantes(String impresora) {
        super(impresora);

    }
    
    public ImpresionComprobantes(String impresora, String depositante) {
        super(impresora);
        this.depositante = depositante;
    }

    /**
     * Imprime los movimientos de las cuentas del socio
     *
     * @param mca
     */
    public void movimientoCuenta(MovimientoCuentaAdicional mca) {
        //Instancion el proceso de llama Store Procedure
        try {
            setLlamaSP(new LlamaSP());
            
            // Estableciendo Formatos de Impresion
            this.colcarFormatoFechaHora();
            
            ////System.out.println("Imprimir comprobante");
            imprimeLinea(this.LPad(mca.getIfipAgencia().getCodigoIfip().getCodigoTipoIfip().getSiglas() + " " + mca.getIfipAgencia().getCodigoIfip().getNombre(), 5));
            imprimeLinea(null);
            imprimeLinea(this.LPad("MOVIMIENTO DE CUENTA", 15));
            imprimeLinea(null);
            imprimeLinea("AGENCIA           :" + mca.getIfipAgencia().getNombre());
            imprimeLinea("CODIGO MOVIMIENTO :" + mca.getCodigoMovimiento().toString());
            imprimeLinea("FECHA MOVIMIENTO  :" + this.getFormatoFechas().format(mca.getFechaSistema()));
            imprimeLinea("FECHA IMPRESION   :" + this.getFormatoFechas().format(new java.sql.Timestamp(new Date().getTime())));
            imprimeLinea("SOCIO             :" + mca.getMovimientoCuenta().getCodigoCuenta().getSocio().getCodigoPersona().getNombreCompleto());
            imprimeLinea("MONEDA            :" + mca.getMovimientoCuenta().getCodigoCuenta().getProductoIfip().getProducto().getMoneda().getNombre());
            imprimeLinea("PRODUCTO          :" + mca.getMovimientoCuenta().getCodigoCuenta().getProductoIfip().getProducto().getTipoProducto().getNombre());
            imprimeLinea("CUENTA            :" + mca.getMovimientoCuenta().getCodigoCuenta().getNumero());
            imprimeLinea("TRANSACCION       :" + mca.getMovimientoCuenta().getConceptoTransaccionPro().getConceptoTransaccion().getNombre());
            imprimeLinea("EFECTIVO          :" + this.getFormatoValores().format(mca.getMovimientoCuenta().getTotalEfectivo().doubleValue()));
            imprimeLinea("CHEQUES           :" + this.getFormatoValores().format(mca.getMovimientoCuenta().getTotalCheques().doubleValue()));
            imprimeLinea("TOTAL             :" + this.getFormatoValores().format(mca.getMovimientoCuenta().getTotalMovimiento().doubleValue()));
            if (depositante != null)
                if (depositante.length() > 0)
                    imprimeLinea("DEPOSITANTE       :" + depositante);
            imprimeLinea("OBSERVACIONES     :" + (mca.getObservaciones().length() > 34 ? mca.getObservaciones().substring(0, 33) : mca.getObservaciones()));
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea("...........................................");
            imprimeLinea(mca.getMovimientoCuenta().getCodigoCuenta().getSocio().getCodigoPersona().getNombreCompleto());
            imprimeLinea("SOCIO");

            imprimeLinea(null);
            imprimeLinea(mensajeAdicional);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea("...........................................");
            imprimeLinea(ActivacionUsuario.getUsuario().getCodigoPersona().getNombreCompleto());
            imprimeLinea(ActivacionUsuario.getCodigoRol());
            
            
            this.enviaImpresora();

        } catch (InterruptedException er) {
        }

    }

    /**
     * Imprsion de Comprobante de Ingreso y Egerso de Caja
     *
     * @param iec
     * @param usuario
     * @param moneda
     */
    public void ingresoEgresoCaja(IngresoEgresoCaja iec, Usuario usuario, Moneda moneda) {
        //Instancion el proceso de llama Store Procedure
        try {
            setLlamaSP(new LlamaSP());

            //IngresoEgresoCaja iec = this.ejbFacadeIngresoEgresoCaja.find(codigoIngresoEgreso);

            // Estableciendo Formatos de Impresion
            this.colcarFormatoFechaHora();

            //Usuario usuario = this.ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario());
            //Moneda moneda = this.ejbFacadeMoneda.find(iec.getIngresoEgresoMoneda().getIngresoEgresoMonedaPK().getCodigoMoneda());

            ////System.out.println("Imprimir comprobante");
            imprimeLinea(this.LPad(iec.getCodigoApertura().getIfipAgencia().getCodigoIfip().getCodigoTipoIfip().getSiglas() + " " + iec.getCodigoApertura().getIfipAgencia().getCodigoIfip().getNombre(), 5));
            imprimeLinea(null);
            imprimeLinea(this.LPad(((iec.getTipo() == 'I') ? "INGRESO" : "EGRESO") + " DE CAJA", 15));
            imprimeLinea(null);
            imprimeLinea(this.RPad("AGENCIA", 18) + ": " + iec.getCodigoApertura().getIfipAgencia().getNombre());
            imprimeLinea(this.RPad("CODIGO " + ((iec.getTipo() == 'I') ? "INGRESO" : "EGRESO"), 18) + ": " + iec.getCodigo().toString());
            imprimeLinea(this.RPad("FECHA " + ((iec.getTipo() == 'I') ? "INGRESO" : "EGRESO"), 18) + ": " + this.getFormatoFechas().format(iec.getFechaIngresoEgreso()));
            imprimeLinea(this.RPad("FECHA IMPRESION ", 18) + ": " + this.getFormatoFechas().format(new java.sql.Timestamp(new Date().getTime())));
            imprimeLinea(this.RPad("MONEDA", 18) + ": " + moneda.getNombre());
            imprimeLinea(this.RPad("TRANSACCION", 18) + ": " + iec.getIngresoEgresoMoneda().getIngresoEgreso().getNombre());
            imprimeLinea(this.RPad("EFECTIVO", 18) + ": " + this.getFormatoValores().format(iec.getTotalEfectivo().doubleValue()));
            imprimeLinea(this.RPad("CHEQUES", 18) + ": " + this.getFormatoValores().format(iec.getTotalCheques().doubleValue()));
            imprimeLinea(this.RPad("TOTAL", 18) + ": " + this.getFormatoValores().format(iec.getTotalIngresoEgreso().doubleValue()));
            // //System.out.println("Imprimir comprobante 2");
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea("...........................................");
            imprimeLinea(usuario.getCodigoPersona().getNombreCompleto());
            imprimeLinea(ActivacionUsuario.getCodigoRol());

            this.enviaImpresora();

        } catch (InterruptedException er) {
        }

    }

    /**
     * Imprsion de Comprobante del Pago de Credito
     *
     * @param pagoCredito
     * @param tablaAmortizacion
     * @param nombreUsuario
     */
    public void pagoCredito(PagoCredito pagoCredito, TablaAmortizacion tablaAmortizacion, String nombreUsuario) {
        //Instancion el proceso de llama Store Procedure
        try {
            setLlamaSP(new LlamaSP());

            //PagoCredito pagoCredito = this.ejbFacadePagoCredito.find(codigoCobro);

            if (pagoCredito == null) {
                return;
            }

            //Usuario usuario = this.ejbFacadeUsuario.find(pagoCredito.getCobradoPor().getCodigo());
            /*TablaAmortizacion ta = null;
            if (pagoCredito.getSolicitud().getCodigoEstado() == 6) {
                ta = this.ejbFacadeTablaAmortizacion.find(new TablaAmortizacionPK(pagoCredito.getSolicitud().getSolicitudPK().getNumero(), pagoCredito.getSolicitud().getSolicitudPK().getCodigoIfip(), pagoCredito.getCuotaAPagar()));
            }*/
            // Estableciendo Formatos de Impresion
            this.colcarFormatoFechaHora();

            ////System.out.println("Imprimir comprobante");
            imprimeLinea(this.LPad(pagoCredito.getIfipAgencia().getCodigoIfip().getCodigoTipoIfip().getSiglas() + " " + pagoCredito.getIfipAgencia().getCodigoIfip().getNombre(), 5));
            imprimeLinea(this.LPad("PAGO DE CREDITO", 30));
            imprimeLinea(null);
            imprimeLinea(this.RPad("AGENCIA", 22) + ": " + pagoCredito.getIfipAgencia().getNombre());
            imprimeLinea(this.RPad("SOCIO", 22) + ": " + pagoCredito.getSolicitud().getSocio().getCodigoPersona().getNombreCompleto());
            imprimeLinea(this.RPad("# CREDITO", 22) + ": " + pagoCredito.getSolicitud().getSolicitudPK().getNumero());
            imprimeLinea(this.RPad("CODIGO PAGO", 22) + ": " + pagoCredito.getCodigo());
            imprimeLinea(this.RPad("FORMA DE PAGO", 22) + ": " + ((pagoCredito.getFormaPago() == 'D') ? "DEBITO DE CUENTA " : "INGRESO DE CAJA"));
            imprimeLinea(this.RPad("FECHA PAGO", 22) + ": " + this.getFormatoFechas().format(pagoCredito.getFechaSistema()));
            imprimeLinea(this.RPad("FECHA IMPRESION", 22) + ": " + this.getFormatoFechas().format(new java.sql.Timestamp(new Date().getTime())));

            imprimeLinea("-- DETALLE DE PAGO");
            imprimeLinea(this.RPad("CAPITAL", 22) + ": " + this.getFormatoValores().format(pagoCredito.getCapital().doubleValue()));
            imprimeLinea(this.RPad("INTERES", 22) + ": " + this.getFormatoValores().format(pagoCredito.getInteres().doubleValue()));
            imprimeLinea(this.RPad("DIAS INTERES", 22) + ": " + pagoCredito.getDiasInteres());
            imprimeLinea(this.RPad("MORA", 22) + ": " + this.getFormatoValores().format(pagoCredito.getMora().doubleValue()));
            imprimeLinea(this.RPad("DIAS MORA", 22) + ": " + pagoCredito.getDiasMora());
            imprimeLinea(this.RPad("NOTIFICACIONES", 22) + ": " + this.getFormatoValores().format(pagoCredito.getNotificaciones().doubleValue()));
            imprimeLinea(this.RPad("# NOTIFICACIONES", 22) + ": " + pagoCredito.getNumeroNotificaciones());
            imprimeLinea(this.RPad("RUBROS", 22) + ": " + this.getFormatoValores().format(pagoCredito.getRubros().doubleValue()));
            imprimeLinea(this.RPad("COSTOS JUDICALES", 22) + ": " + this.getFormatoValores().format(pagoCredito.getCostosJudiciales().doubleValue()));
            imprimeLinea(this.RPad("TOTAL PAGO", 22) + ": " + this.getFormatoValores().format(pagoCredito.getTotal().doubleValue()));

            imprimeLinea("-- DATOS PROXIMO PAGO");
            if (tablaAmortizacion != null) {
                this.colcarFormatoFecha();
                imprimeLinea(this.RPad("CUOTA A PAGAR", 22) + ": " + pagoCredito.getCuotaAPagar().toString());
                imprimeLinea(this.RPad("VALOR PROXIMA CUOTA", 22) + ": " + this.getFormatoValores().format(tablaAmortizacion.getTotal().subtract(tablaAmortizacion.getAbono()).doubleValue()));
                imprimeLinea(this.RPad("FECHA PROXIMA DE PAGO", 22) + ": " + this.getFormatoFechas().format(pagoCredito.getFechaProximaPago()));
            } else {
                imprimeLinea("CREDITO HA SIDO CANCELADO. NO TIENE CUOTAS PENDIENTES DE PAGO");
            }

            // //System.out.println("Imprimir comprobante 2");
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea("...........................................");
            imprimeLinea(nombreUsuario);
            imprimeLinea(null);
            imprimeLinea(ActivacionUsuario.getCodigoRol());

            this.enviaImpresora();

        } catch (InterruptedException er) {
        }

    }
    
    /**
     * Imprime los movimientos de las cuentas del socio
     *
     * @param tec     
     * @param nombreUsuario     
     */
    public void transferenciaEntreCuenta(TransferenciaEntreCuentas tec, String nombreUsuario) {
        //Instancion el proceso de llama Store Procedure
        try {
            setLlamaSP(new LlamaSP());
            
            // Estableciendo Formatos de Impresion
            this.colcarFormatoFechaHora();
            
            ////System.out.println("Imprimir comprobante");
            imprimeLinea(this.LPad(tec.getCodigoCuentaOrigen().getSocio().getCodigoIfipAgencia().getCodigoIfip().getCodigoTipoIfip().getSiglas() + " " + tec.getCodigoCuentaOrigen().getSocio().getCodigoIfipAgencia().getCodigoIfip().getNombre(), 5));
            imprimeLinea(null);
            imprimeLinea(this.LPad("TRANSFERENCIA ENTRE CUENTAS", 15));
            imprimeLinea(null);                        
            imprimeLinea("FECHA TRANSFERENCIA   :" + this.getFormatoFechas().format(tec.getFechaTransferencia()));
            imprimeLinea("FECHA IMPRESION       :" + this.getFormatoFechas().format(new java.sql.Timestamp(new Date().getTime())));            
            imprimeLinea("SOCIO                 :" + tec.getCodigoCuentaOrigen().getSocio().getCodigoPersona().getNombreCompleto());
            imprimeLinea("MONEDA                :" + tec.getCodigoCuentaOrigen().getMoneda().getNombre());
            imprimeLinea("PRODUCTO              :" + tec.getCodigoCuentaOrigen().getTipoProducto().getNombre());
            imprimeLinea("CUENTA                :" + tec.getCodigoCuentaOrigen().getNumero());
            imprimeLinea("TRANSACCION           :" + tec.getCodigoConceptoTransferencia().getDescripcion());
            imprimeLinea("VALOR TRANSFERENCIA   :" + this.getFormatoValores().format(tec.getValorTransferido().doubleValue()));
            imprimeLinea("-- DATOS CUENTA DESTINO ");
            imprimeLinea("SOCIO                 :" + tec.getCodigoCuentaDestino().getSocio().getCodigoPersona().getNombreCompleto());            
            imprimeLinea("PRODUCTO              :" + tec.getCodigoCuentaDestino().getTipoProducto().getNombre());
            imprimeLinea("CUENTA                :" + tec.getCodigoCuentaDestino().getNumero());
            
            // //System.out.println("Imprimir comprobante 2");
            imprimeLinea(null);
            imprimeLinea(null);            
            imprimeLinea("...........................................");
            imprimeLinea(nombreUsuario);
            imprimeLinea(ActivacionUsuario.getCodigoRol());

            imprimeLinea(null);
            imprimeLinea(null);            
            imprimeLinea("...........................................");
            imprimeLinea(tec.getCodigoCuentaOrigen().getSocio().getCodigoPersona().getNombreCompleto());
            imprimeLinea("SOCIO");
            
            this.enviaImpresora();

        } catch (InterruptedException er) {
        }

    }
    
    /**
     * 
     * @param socio
     * @param ifipAgencia
     * @param pagoLineaCreditoSolicitud
     * @param usuario
     * @param tipoPago
     * @return 
     */
    public boolean pagoLineaCredito(Socio socio, IfipAgencia ifipAgencia, PagoLineaCreditoSolicitud pagoLineaCreditoSolicitud, Usuario usuario, String tipoPago) {
        try {
            if ((socio == null) || (ifipAgencia == null) || (pagoLineaCreditoSolicitud == null) || (usuario == null) || (tipoPago == null)) {
                return false;
            }
            this.colcarFormatoFechaHora();
            imprimeLinea(this.LPad(ifipAgencia.getCodigoIfip().getCodigoTipoIfip().getSiglas() + " " + ifipAgencia.getCodigoIfip().getNombre(), 5));
            imprimeLinea(this.LPad("PAGO LINEA DE CREDITO", 30));
            imprimeLinea(null);
            imprimeLinea(this.RPad("AGENCIA", 22) + ": " + ifipAgencia.getNombre());
            imprimeLinea(this.RPad("SOCIO", 22) + ": " + socio.getCodigoPersona().getNombreCompleto());
            imprimeLinea(this.RPad("# LINEA CREDITO", 22) + ": " + pagoLineaCreditoSolicitud.getCodigoLineaCreditoSolicitud());
            imprimeLinea(this.RPad("CODIGO PAGO", 22) + ": " + pagoLineaCreditoSolicitud.getCodigo());
            imprimeLinea(this.RPad("FORMA DE PAGO", 22) + ": " + (("D".equals(tipoPago)) ? "DEBITO DE CUENTA " : "INGRESO DE CAJA"));
            imprimeLinea(this.RPad("FECHA PAGO", 22) + ": " + this.getFormatoFechas().format(new java.sql.Timestamp(new Date().getTime())));
            imprimeLinea(this.RPad("FECHA IMPRESION", 22) + ": " + this.getFormatoFechas().format(new java.sql.Timestamp(new Date().getTime())));
            imprimeLinea("-- DETALLE DE PAGO");
            imprimeLinea(this.RPad("CAPITAL", 22) + ": " + getFormatoValores().format(pagoLineaCreditoSolicitud.getCapital().doubleValue()));
            imprimeLinea(this.RPad("INTERES", 22) + ": " + getFormatoValores().format(pagoLineaCreditoSolicitud.getInteres().doubleValue()));
            imprimeLinea(this.RPad("MORA", 22) + ": " + getFormatoValores().format(pagoLineaCreditoSolicitud.getMora().doubleValue()));
            imprimeLinea(this.RPad("NOTIFICACIONES", 22) + ": " + getFormatoValores().format(pagoLineaCreditoSolicitud.getNotificaciones().doubleValue()));
            imprimeLinea(this.RPad("RUBROS", 22) + ": " + getFormatoValores().format(pagoLineaCreditoSolicitud.getRubros().doubleValue()));
            imprimeLinea(this.RPad("COSTOS JUDICALES", 22) + ": " + getFormatoValores().format(pagoLineaCreditoSolicitud.getCostoJudicial().doubleValue()));
            imprimeLinea(this.RPad("TOTAL PAGO", 22) + ": " + getFormatoValores().format(pagoLineaCreditoSolicitud.getTotal().doubleValue()));
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea("...........................................");
            imprimeLinea(usuario.getCodigoPersona().getNombreCompleto());
            imprimeLinea(null);
            imprimeLinea(ActivacionUsuario.getCodigoRol());
            this.enviaImpresora();
            return true;
        } catch (InterruptedException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl, 
                    new Object[]{"ImpresionComprobantes", "pagoLineaCredito", CapturaError.getErrorException(e)});
            return false;
        }catch (Exception e) {
             Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"ImpresionComprobantes", "pagoLineaCredito", CapturaError.getErrorException(e)});
            return false;
        }
    }
    
    /**
     * 
     * @param ifipAgencia
     * @param pagoAvanceLineaCredito
     * @param usuario 
     * @return  
     */
    public boolean pagoAvanceLineaCredito(IfipAgencia ifipAgencia, PagoAvanceLineaCredito pagoAvanceLineaCredito, Usuario usuario) {
        try {
            if ((ifipAgencia == null) || (pagoAvanceLineaCredito == null) || (usuario == null)) {
                return false;
            }
            this.colcarFormatoFechaHora();
            imprimeLinea(this.LPad(ifipAgencia.getCodigoIfip().getCodigoTipoIfip().getSiglas() + " " + ifipAgencia.getCodigoIfip().getNombre(), 5));
            imprimeLinea(this.LPad("PAGO AVANCE LINEA DE CREDITO", 30));
            imprimeLinea(null);
            imprimeLinea(this.RPad("AGENCIA", 22) + ": " + ifipAgencia.getNombre());
            imprimeLinea(this.RPad("SOCIO", 22) + ": " + pagoAvanceLineaCredito.getAvanceLineaCredito().getCodigoLineaCreditoSolicitud().getSocio().getCodigoPersona().getNombreCompleto());
            imprimeLinea(this.RPad("# LINEA CREDITO", 22) + ": " + pagoAvanceLineaCredito.getAvanceLineaCredito().getCodigoLineaCreditoSolicitud().getCodigo());
            imprimeLinea(this.RPad("CODIGO PAGO", 22) + ": " + pagoAvanceLineaCredito.getCodigo());
            imprimeLinea(this.RPad("FORMA DE PAGO", 22) + ": " + (("D".equals(pagoAvanceLineaCredito.getFormaPago())) ? "DEBITO DE CUENTA " : "INGRESO DE CAJA"));
            imprimeLinea(this.RPad("FECHA PAGO", 22) + ": " + this.getFormatoFechas().format(new java.sql.Timestamp(new Date().getTime())));
            imprimeLinea(this.RPad("FECHA IMPRESION", 22) + ": " + this.getFormatoFechas().format(new java.sql.Timestamp(new Date().getTime())));
            imprimeLinea("-- DETALLE DE PAGO");
            imprimeLinea(this.RPad("CAPITAL", 22) + ": " + getFormatoValores().format(pagoAvanceLineaCredito.getCapital().doubleValue()));
            imprimeLinea(this.RPad("INTERES", 22) + ": " + getFormatoValores().format(pagoAvanceLineaCredito.getInteres().doubleValue()));
            imprimeLinea(this.RPad("MORA", 22) + ": " + getFormatoValores().format(pagoAvanceLineaCredito.getMora().doubleValue()));
            imprimeLinea(this.RPad("NOTIFICACIONES", 22) + ": " + getFormatoValores().format(pagoAvanceLineaCredito.getNotificacion().doubleValue()));
            imprimeLinea(this.RPad("RUBROS", 22) + ": " + getFormatoValores().format(pagoAvanceLineaCredito.getRubro().doubleValue()));
            imprimeLinea(this.RPad("COSTOS JUDICALES", 22) + ": " + getFormatoValores().format(pagoAvanceLineaCredito.getCostoJudicial().doubleValue()));
            imprimeLinea(this.RPad("TOTAL PAGO", 22) + ": " + getFormatoValores().format(pagoAvanceLineaCredito.getTotal().doubleValue()));
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea("...........................................");
            imprimeLinea(usuario.getCodigoPersona().getNombreCompleto());
            imprimeLinea(null);
            imprimeLinea(ActivacionUsuario.getCodigoRol());
            this.enviaImpresora();
            return true;
        } catch (InterruptedException e) {
             Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"ImpresionComprobantes", "pagoAvanceLineaCredito", CapturaError.getErrorException(e)});
            return false;
        }catch (Exception e) {
             Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"ImpresionComprobantes", "pagoAvanceLineaCredito", CapturaError.getErrorException(e)});
            return false;
        }
    }
    /**
     * Metodo para la impresion del comprobante del avance de linea de credito
     * @param avanceLineaCredito
     * @param lineaCreditoSolicitud
     * @param lineaCredito
     * @param socio
     * @param agencia
     * @param cupoLinea
     * @param valorAvance
     * @param cupoDisponible 
     */
    public void avanceLineaCredito(AvanceLineaCredito avanceLineaCredito, LineaCreditoSolicitud lineaCreditoSolicitud, LineaCredito lineaCredito, Socio socio, IfipAgencia agencia, BigDecimal cupoLinea, BigDecimal valorAvance, BigDecimal cupoDisponible) {
        //Instancion el proceso de llama Store Procedure
        try {
            setLlamaSP(new LlamaSP());
            // Estableciendo Formatos de Impresion
            colcarFormatoFechaHora();
            imprimeLinea(this.LPad("COOPERATIVA DE AHORRO Y CREDITO MAS AHORRO SOLIDARIO MASCOOP", 5));
            imprimeLinea(this.LPad("                   Controlada por la",5));
            imprimeLinea(this.LPad("       \"Superintendencia de Economia Popular y Solidaria\"", 5));
            imprimeLinea(null);
            imprimeLinea(this.LPad("AVANCE LINEA DE CREDITO \""+lineaCredito.getNombre().toUpperCase()+"\"", 5));
            imprimeLinea(null);
            imprimeLinea("NUMERO LINEA      :" + lineaCreditoSolicitud.getCodigo());
            imprimeLinea("NUMERO AVANCE     :" + avanceLineaCredito.getCodigo());
            imprimeLinea("CODIGO SOCIO      :" + socio.getSocioPK().getCodigo());
            imprimeLinea("SOCIO             :" + socio.getCodigoPersona().getNombreCompleto().toUpperCase());
            imprimeLinea("AGENCIA           :" + agencia.getNombre().toUpperCase());
            imprimeLinea("FECHA             :" + getFormatoFechas().format(avanceLineaCredito.getFechaRegistro()));
            imprimeLinea(null);
            imprimeLinea("CUPO DE LINEA     :" + getFormatoValores().format(cupoLinea.doubleValue()));
            imprimeLinea("VALOR DE AVANCE   :" + getFormatoValores().format(valorAvance.doubleValue()));
            imprimeLinea("CUPO DISPONIBLE   :" + getFormatoValores().format(cupoDisponible.doubleValue()));
            imprimeLinea(null);
            imprimeLinea("Este credito se autoriza  y  concede en el marco general del");
            imprimeLinea("Contrato de Credito  suscrito de  fecha "+getFormatoFechas().format(lineaCreditoSolicitud.getFechaSolicitud()));
            imprimeLinea("cuyo documento se constituye como parte integrante de  dicho");
            imprimeLinea("contrato. Debo y pagare a la Cooperativa de Ahorro y Credito");
            imprimeLinea("Mascoop  incondicionalmente  y sin protesto el total de este");
            imprimeLinea("documento mas los intereses y cargos por servicio.");
            imprimeLinea(null);
            imprimeLinea("Lugar y Fecha: "+agencia.getCodigoUbiGeoCiudad().getNombre()+" "+getFormatoFechas().format(new java.sql.Timestamp(new Date().getTime())));
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea("...........................................");
            imprimeLinea(socio.getCodigoPersona().getNombreCompleto());
            imprimeLinea("SOCIO");            
            enviaImpresora();
        } catch (Exception er) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorEnviarImpresoraComprobanteAvance"));
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, er.getMessage(),
                    new Object[]{"ImpresionComprobante", "avanceLineaCredito", er.getMessage()});
        }
    }
    
    /**
     * @return the llamaSP
     */
    public LlamaSP getLlamaSP() {
        return llamaSP;
    }

    /**
     * @param llamaSP the llamaSP to set
     */
    public void setLlamaSP(LlamaSP llamaSP) {
        this.llamaSP = llamaSP;
    }

    /**
     *
     * @return devuelve un mensaje adicional
     */
    public String getMensajeAdicional() {
        return mensajeAdicional;
    }

    /**
     *
     * @param mensajeAdicional
     */
    public void setMensajeAdicional(String mensajeAdicional) {
        this.mensajeAdicional = mensajeAdicional;
    }

}
