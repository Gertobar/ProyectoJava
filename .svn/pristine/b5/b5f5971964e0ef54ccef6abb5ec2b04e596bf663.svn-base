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
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.FirmanteCuenta;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author v.astudillo
 */
public class ImpresionLibreta extends ImpresionImpresora implements Serializable {

    private LlamaSP llamaSP;

    private boolean muestraDialogoReverso;
    private boolean muestraDialogoNuevaCartola;
    private boolean muestraDialogoComprobante;

    private String dialogoReverso;
    private String dialogoNuevaCartola;
    private String dialogoComprobante;

    private String transaccion;
    private String valor;
    private String saldo;
    private String signo;

    boolean empezoImpresion;
    boolean esReverso;
    private int lineaLibreta;
    private int lineaLibretaimpresa;

    private String msg;

    private List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalImpresas;
    private List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalNoImpresas;
    private List<FirmanteCuenta> itemsFirmanteCuenta;
    private int numeroLineasAnverso;
    private int numeroLineasReverso;
    private int formatoLibreta;
    private Cuenta cuenta;
    private long codigoMovimientoUltimoImpreso;
    private long codigoMovimientoUltimoReimpreso;
    private long codigoMovimientoPrimeroReimpreso;
    private String numeroLibretaNueva;
    private String numeroLibretaAnterior;
    private boolean esReimpresion;

    public ImpresionLibreta(String impresora) {
        super(impresora);
        llamaSP = new LlamaSP();
        this.dialogoComprobante = null;
        this.dialogoNuevaCartola = null;
        this.dialogoReverso = null;
        this.muestraDialogoComprobante = false;
        this.muestraDialogoNuevaCartola = false;
        this.muestraDialogoReverso = false;
        this.esReimpresion = false;
    }

    public void imprimirAnverso() throws IOException {
        this.setLineasImpresas(0);
        int lineasEncabezado = 0;
        this.setImpresionCorrecta(true);

        try {

            empezoImpresion = false;
            esReverso = false;

            //numero de posicion en la cartola en la que se va a imprimir
            lineaLibretaimpresa = getItemsMovimientoCuentaAdicionalImpresas().size() + 1;

            // Estableciendo Formatos de Impresion
            this.colcarFormatoFecha();

            //  MostramosMensaje de Asignar Nueva Libreta
            if (itemsMovimientoCuentaAdicionalImpresas.size() >= this.numeroLineasAnverso + this.numeroLineasReverso) {
                this.setImpresionCorrecta(true);
                this.muestraDialogoNuevaCartola = true;
                return;
            }

            //Verificando que tenga lineas que impirmi
            if (getItemsMovimientoCuentaAdicionalNoImpresas().isEmpty()) {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenMovimientos"));
                MuestraMensaje.addAdvertencia(getMsg());
                return;
            }

            // Verificando si tiene que irse a la Impresion reversa
            if (getLineaLibreta() > this.getNumeroLineasAnverso()) {
                this.muestraDialogoReverso = true;
                this.setImpresionCorrecta(true);
                return;
            }

            //Si es la primera linea de la libreta
            if (getLineaLibreta() == 1) {
                int numeroFirmante = 0;
                if (this.getFormatoLibreta() == 4) {
                    //FORMATO JOYOCOTO
                    imprimeLinea(null);
                    imprimeLinea("    Codigo   : " + this.getCuenta().getSocio().getSocioPK().getCodigo());
                    imprimeLinea("    Socio    : " + this.getCuenta().getSocio().getCodigoPersona().getNombreCompleto());
                    imprimeLinea("    # Cuenta : " + this.getCuenta().getNumero());
                    imprimeLinea("    Firmantes: " + this.getCuenta().getCodigoTipoFirma().getNombre());

                    // Si tiene Firmantes se imprime en la cabecera
                    numeroFirmante = 0;

                    if (!this.itemsFirmanteCuenta.isEmpty()) {
                        for (FirmanteCuenta fc : getItemsFirmanteCuenta()) {
                            numeroFirmante++;
                            imprimeLinea("      Firmante " + numeroFirmante + ": " + fc.getPersona().getNombreCompleto());
                        }

                    }

                    //Si se existieron firmantes
                    if (numeroFirmante > 0) {
                        for (int i = numeroFirmante; i < 3; i++) {
                            imprimeLinea(null);
                        }
                    } else {
                        imprimeLinea(null);
                        imprimeLinea(null);
                    }
                    imprimeLinea(null);

                } else if (this.getFormatoLibreta() == 2) {
                    //FORMATO SANTA ANA - SINDICATO
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);

                    imprimeLinea(RPad("", 17) + RPad(this.getCuenta().getNumero(), 28) + RPad("", 11) + RPad(this.getCuenta().getProductoIfip().getProducto().getTipoProducto().getNombre(), 20));
                    imprimeLinea(RPad("", 17) + RPad(this.getCuenta().getSocio().getCodigoPersona().getNombreCompleto(), 28) + RPad("", 13) + RPad(String.valueOf(this.getCuenta().getSocio().getSocioPK().getCodigo()), 20));

                    imprimeLinea(null);

                } // FORMATO MASSCOOP - CHOLA - URBA10
                else if (this.getFormatoLibreta() == 3) {
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(RPad("", 18) + RPad(this.getCuenta().getSocio().getCodigoPersona().getNombreCompleto(), 30) + RPad("", 5) + RPad(String.valueOf(this.getCuenta().getSocio().getSocioPK().getCodigo()), 20));
                    // Si tiene Firmantes se imprime en la cabecera                    
                    numeroFirmante = 0;
                    if (this.getCuenta().getCodigoTipoFirma().getTieneFirmas() == 'S') {
                        if (!this.itemsFirmanteCuenta.isEmpty()) {
                            for (FirmanteCuenta fc : getItemsFirmanteCuenta()) {
                                numeroFirmante++;
                                imprimeLinea(RPad("", 18) + "Firmante " + numeroFirmante + ": " + fc.getPersona().getNombreCompleto());
                            }
                        }
                    } else {
                        if (this.getCuenta().getCodigoPersonaBeneficiario() != null) {
                            imprimeLinea(RPad("", 18) + "Beneficiario: " + this.getCuenta().getPersonaBeneficiario().getNombreCompleto());
                            numeroFirmante++;
                        }
                    }
                    if (numeroFirmante <= 0) {
                        imprimeLinea(null);
                    }
                    imprimeLinea(RPad("", 18) + RPad(this.getCuenta().getNumero(), 30) + RPad("", 5) + RPad(this.getCuenta().getProductoIfip().getProducto().getTipoProducto().getNombre(), 20));
                }else if (this.getFormatoLibreta() == 5) {
                    //FORMATO ETAPA - ACHIK
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);

                    imprimeLinea(RPad("", 25) + RPad(this.getCuenta().getSocio().getCodigoPersona().getNombreCompleto(), 30) + RPad("", 5) + RPad(String.valueOf(this.getCuenta().getSocio().getSocioPK().getCodigo()), 20));

                    // Si tiene Firmantes se imprime en la cabecera
                    numeroFirmante = 0;

                    if (!this.itemsFirmanteCuenta.isEmpty()) {
                        for (FirmanteCuenta fc : getItemsFirmanteCuenta()) {
                            numeroFirmante++;
                            imprimeLinea("      Firmante " + numeroFirmante + ": " + fc.getPersona().getNombreCompleto());
                        }

                    }

                    //Si se existieron firmantes
                    if (numeroFirmante > 0) {
                        for (int i = numeroFirmante; i < 2; i++) {
                            imprimeLinea(null);
                        }
                    } else {
                        imprimeLinea(null);
                        //imprimeLinea(null);

                    }

                    imprimeLinea(RPad("", 25) + RPad(this.getCuenta().getNumero(), 30) + RPad("", 5) + RPad(this.getCuenta().getProductoIfip().getProducto().getTipoProducto().getNombre(), 20));
                    imprimeLinea(null);//si es correcto
                    imprimeLinea(null);
                    //imprimeLinea(null);

                } else { // FORMATO FOCAP - JOYOCOTO
                    imprimeLinea("Codigo   : " + this.getCuenta().getSocio().getSocioPK().getCodigo());
                    imprimeLinea("Socio    : " + this.getCuenta().getSocio().getCodigoPersona().getNombreCompleto());
                    imprimeLinea("# Cuenta : " + this.getCuenta().getNumero());
                    imprimeLinea("Firmantes: " + this.getCuenta().getCodigoTipoFirma().getNombre());

                    // Si tiene Firmantes se imprime en la cabecera
                    numeroFirmante = 0;

                    if (!this.itemsFirmanteCuenta.isEmpty()) {
                        for (FirmanteCuenta fc : getItemsFirmanteCuenta()) {
                            numeroFirmante++;
                            imprimeLinea("      Firmante " + numeroFirmante + ": " + fc.getPersona().getNombreCompleto());
                        }

                    }

                    //Si se existieron firmantes
                    if (numeroFirmante > 0) {
                        for (int i = numeroFirmante; i < 4; i++) {
                            imprimeLinea(null);
                        }
                    } else {
                        imprimeLinea(null);
                        imprimeLinea(null);
                        imprimeLinea(null);
                    }

                    imprimeLinea(null);
                }

                this.empezoImpresion = true;
            }
            //CALCULAR CADA FORMATO DEPENDIENDO DE LA COOPERATIVA
            int numeroDeLineasParaCabecera = 0;
            switch (this.getFormatoLibreta()) {
                case 3:
                    lineasEncabezado = 10;
                    numeroDeLineasParaCabecera = 3;
                    break;
                case 6:
                    lineasEncabezado = 8;
                    break;
                case 5:
                    lineasEncabezado = 8;
                    break;
                case 2:
                    lineasEncabezado = 7;
                    break;
                case 4:
                    lineasEncabezado = 7;
                    break;
                default:
                    lineasEncabezado = 7;
                    break;
            }
            int lineasNull = 0;
            if (getLineaLibreta() <= 1) {
                lineasNull = numeroDeLineasParaCabecera;
            } else {
                lineasNull = (getLineaLibreta() + lineasEncabezado);
            }
            // Impresion de Líneas de Libreta
            for (MovimientoCuentaAdicional mca : getItemsMovimientoCuentaAdicionalNoImpresas()) {
                // Verificando si es la primera linea para imprimir la cabecera
                if (getLineaLibreta() <= this.getNumeroLineasAnverso()) {
                    // Recorremos el carro hasta que llegue a la linea correcta
                    for (int c = 0; c < lineasNull; c++) {
                        imprimeLinea(null);
                    }
                    //}
                } else {
                    esReverso = true;
                    //this.setItemsMovimientoCuentaAdicionalNoImpresas(this.ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalNoImpreso(this.getCuenta().getCodigo(), 'N'));

                }
                // Si NO toca el reverso
                if (!esReverso) {
                    transaccion = "  " + mca.getMovimientoCuenta().getConceptoTransaccionPro().getConceptoTransaccion().getSiglas();
                    signo = (String.valueOf(mca.getMovimientoCuenta().getTipo()).equals("D")) ? "-" : " ";
                    valor = this.getFormatoValores().format(mca.getMovimientoCuenta().getTotalMovimiento().doubleValue());
                    saldo = this.getFormatoValores().format(mca.getMovimientoCuenta().getSaldoCuenta().doubleValue());

                    if (this.getFormatoLibreta() == 2) {
                        //FORMATO SANTA ANA - SINDICATO
                        // Imprimiendo la Libreta
                        imprimeLinea(LPad(String.valueOf(getLineaLibreta()), 3)
                                + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 12)
                                + LPad("", 2)
                                + RPad(transaccion, 22)
                                + (signo.trim().equals("") ? LPad(signo + valor.toString(), 10) : LPad("", 10))
                                + (signo.trim().equals("-") ? LPad(signo + valor.toString(), 11) : LPad("", 11))
                                + LPad(saldo.toString(), 12));
                    } else if (this.getFormatoLibreta() == 3) {
                        // FORMATO MASCOOP - CHOLA - URBA10
                        imprimeLinea(LPad(String.valueOf(lineaLibretaimpresa), 3)
                                + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 15)
                                + LPad("", 2)
                                + (signo.trim().equals("") ? LPad(signo + valor.toString(), 16) : LPad("", 16))
                                + (signo.trim().equals("-") ? LPad(signo + valor.toString(), 15) : LPad("", 15))
                                + LPad(saldo.toString(), 16)
                                + LPad("", 4) + transaccion);

                    } else if (this.getFormatoLibreta() == 6) {
                        
                        imprimeLinea(LPad(String.valueOf(lineaLibretaimpresa), 3)
                                + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 15)
                                + LPad("", 2)
                                + (signo.trim().equals("") ? LPad(signo + valor.toString(), 16) : LPad("", 16))
                                + (signo.trim().equals("-") ? LPad(signo + valor.toString(), 15) : LPad("", 15))
                                + LPad(saldo.toString(), 16)
                                + LPad("", 4) + transaccion);

                    } else if (this.getFormatoLibreta() == 5) {
                        // FORMATO ETAPA - ACHIK
                        imprimeLinea(LPad(String.valueOf(lineaLibretaimpresa), 3)
                                + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 15)
                                + LPad("", 2)
                                + (signo.trim().equals("") ? LPad(signo + valor.toString(), 16) : LPad("", 16))
                                + (signo.trim().equals("-") ? LPad(signo + valor.toString(), 15) : LPad("", 15))
                                + LPad(saldo.toString(), 16)
                                + LPad("", 4) + transaccion);

                    } else if (this.getFormatoLibreta() == 4) {
                         //FORMATO JOYOCOTO 
                        // Imprimiendo la Libreta
                        imprimeLinea(LPad(String.valueOf(lineaLibreta), 2)
                                + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 12)
                                + LPad("", 3)
                                + RPad(transaccion, 15)
                                + (signo.trim().equals("") ? LPad(signo + valor.toString(), 11) : LPad("", 11))
                                + (signo.trim().equals("-") ? LPad(signo + valor.toString(), 13) : LPad("", 13))
                                + LPad(saldo.toString(), 22));

                    } else { //FORMATO FOCAP - JOYOCOTO
                        // Imprimiendo la Libreta
                        imprimeLinea(LPad(String.valueOf(getLineaLibreta()), 3)
                                + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 12)
                                + LPad("", 2) + RPad(transaccion, 22)
                                + (signo.trim().equals("") ? LPad(signo + valor.toString(), 10) : LPad("", 10))
                                + (signo.trim().equals("-") ? LPad(signo + valor.toString(), 11) : LPad("", 11))
                                + LPad(saldo.toString(), 12));
                    }

                    // Verificando si ya empezo a imprimir
                    if (!empezoImpresion) {
                        empezoImpresion = true;
                    }

                    this.setCodigoMovimientoUltimoImpreso(mca.getCodigoMovimiento());
                    lineaLibretaimpresa++;
                    this.setLineaLibreta(this.getLineaLibreta() + 1);
                } else {
                    break;
                }
                lineasNull = 0;
            }

            if (this.enviaImpresora()) {

                // Registrando en la base de datos la impresion de la libreta
                this.guardarImpresionLibretaCuenta();

                // Si la guardada de la impresion es correcta
                if (this.isImpresionCorrecta()) {
                    // Si hay que imprimir el reverso
                    if (esReverso) {
                        //  MostramosMensaje de dar vuelta a la libreta
                        this.muestraDialogoReverso = true;
                        //System.err.println("Muestra Reverso 2");
                    } else {
                        // Guardando la reimpresion de la libreta
                        if (esReimpresion) {
                            this.guardaReimpresionLibreta();
                        }
                    }
                }

            } else {
                this.setImpresionCorrecta(false);
                return;
            }

            Thread.sleep(this.getLineasImpresas() * 200);

        } catch (InterruptedException e) {
            this.setImpresionCorrecta(false);
            // Error en capa de control
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ImpresionLibreta", "imprimirAnverso", CapturaError.getErrorException(e)});
        }
    }

    /**
     * Imprime el rerverso
     *
     */
    public void imprimirReverso() {
        //  opcion = 1;
        this.setImpresion("");
        this.setImprimeEnArchivo(false);
        boolean terminoLibreta = false;
        this.setLineasImpresas(0);
        this.setImpresionCorrecta(true);
        int numeroFirmante = 0;
        this.colcarFormatoFecha();

        try {

            //  MostramosMensaje de Asignar Nueva Libreta
            if (itemsMovimientoCuentaAdicionalImpresas.size() >= this.numeroLineasAnverso + this.numeroLineasReverso) {
                this.setImpresionCorrecta(true);
                this.muestraDialogoNuevaCartola = true;
                return;
            }

            if (itemsMovimientoCuentaAdicionalNoImpresas.size() > 0) {
                //IMPRESION FIRMANTES Y BENEFICIARIOS MASCOOP
                if (this.formatoLibreta == 3) {
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);
                    if (this.itemsMovimientoCuentaAdicionalImpresas.size() == numeroLineasAnverso) {
                        imprimeLinea(RPad("", 18) + RPad(this.getCuenta().getSocio().getCodigoPersona().getNombreCompleto(), 30) + RPad("", 5) + RPad(String.valueOf(this.getCuenta().getSocio().getSocioPK().getCodigo()), 20));
                        // Si tiene Firmantes se imprime en la cabecera                    
                        numeroFirmante = 0;
                        if (this.getCuenta().getCodigoTipoFirma().getTieneFirmas() == 'S') {
                            if (!this.itemsFirmanteCuenta.isEmpty()) {
                                for (FirmanteCuenta fc : getItemsFirmanteCuenta()) {
                                    numeroFirmante++;
                                    imprimeLinea(RPad("", 18) + "Firmante " + numeroFirmante + ": " + fc.getPersona().getNombreCompleto());
                                }
                            }
                        } else {
                            if (this.getCuenta().getCodigoPersonaBeneficiario() != null) {
                                imprimeLinea(RPad("", 18) + "Beneficiario: " + this.getCuenta().getPersonaBeneficiario().getNombreCompleto());
                                numeroFirmante++;
                            }
                        }
                        //Si se existieron firmantes
                        if (numeroFirmante <= 0) {
                            imprimeLinea(null);
                        }
                        imprimeLinea(RPad("", 18) + RPad(this.getCuenta().getNumero(), 30) + RPad("", 5) + RPad(this.getCuenta().getProductoIfip().getProducto().getTipoProducto().getNombre(), 20));
                    } else {
                        imprimeLinea(null);
                        imprimeLinea(null);
                        imprimeLinea(null);
                        imprimeLinea(null);
                    }
                } 
                //CALCULAR LINEAS NULL DE LA CABECERA DEPENDIENDO DE LA COOPERATIVA
                int numeroDeLineasParaCabecera = 0;
                switch (this.getFormatoLibreta()) {
                    case 3:
                        numeroDeLineasParaCabecera = 3;
                        break;
                    case 6:
                        numeroDeLineasParaCabecera = 2;
                        break;
                    case 5:
                        numeroDeLineasParaCabecera = 11;
                        break;
                    case 2:
                        numeroDeLineasParaCabecera = 7;
                        break;
                    case 4:
                        numeroDeLineasParaCabecera = 4;
                        break;
                    default:
                        numeroDeLineasParaCabecera = 3;
                        break;
                }
                // Verificando si se esta imprimiendo ´primera vez el Reverso
                int lineasNull = 0;
                if (this.itemsMovimientoCuentaAdicionalImpresas.size() == numeroLineasAnverso) {
                    lineasNull = numeroDeLineasParaCabecera;
                } else {//RECORRE A LA POSICION SEGUN EL NUMERO DE LINEAS DE LA CABECERA                 
                    lineasNull = ((this.itemsMovimientoCuentaAdicionalImpresas.size() - this.numeroLineasAnverso) + (this.getFormatoLibreta() == 3 ? (numeroDeLineasParaCabecera - 1) : numeroDeLineasParaCabecera));
                }
                for (int c = 0; c < lineasNull; c++) {
                    imprimeLinea(null);
                }
                lineaLibreta = itemsMovimientoCuentaAdicionalImpresas.size() + 1;
                //setLineaLibreta(itemsMovimientoCuentaAdicionalImpresas.size());
                lineaLibretaimpresa = itemsMovimientoCuentaAdicionalImpresas.size() + 1;

                for (MovimientoCuentaAdicional mca : itemsMovimientoCuentaAdicionalNoImpresas) {

                    if (getLineaLibreta() <= this.numeroLineasAnverso + this.numeroLineasReverso) {
                        transaccion = "  " + mca.getMovimientoCuenta().getConceptoTransaccionPro().getConceptoTransaccion().getSiglas();
                        signo = (String.valueOf(mca.getMovimientoCuenta().getTipo()).equals("D")) ? "-" : " ";
                        valor = this.getFormatoValores().format(mca.getMovimientoCuenta().getTotalMovimiento().doubleValue());
                        saldo = this.getFormatoValores().format(mca.getMovimientoCuenta().getSaldoCuenta().doubleValue());

                        // Imprimiendo la Libreta
                        //pw.println(LPad(String.valueOf(lineaLibreta), 5) + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 15) + LPad(transaccion, 12) + RPad(signo + valor.toString(), 12) + RPad(saldo.toString(), 12));
                        //imprimeLinea(LPad(String.valueOf(lineaLibreta), 5) + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 15) + LPad(transaccion, 12) + RPad(signo + valor.toString(), 12) + RPad(saldo.toString(), 12));
                        if (this.formatoLibreta == 2) {
                            // Imprimiendo la Libreta
                            imprimeLinea(LPad(String.valueOf(getLineaLibreta()), 3) + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 12) + LPad("", 2) + RPad(transaccion, 22) + (signo.trim().equals("") ? LPad(signo + valor.toString(), 10) : LPad("", 10)) + (signo.trim().equals("-") ? LPad(signo + valor.toString(), 11) : LPad("", 11)) + LPad(saldo.toString(), 12));
                        } else if (this.getFormatoLibreta() == 4) {
                            // Imprimiendo la Libreta
                            imprimeLinea(LPad(String.valueOf(lineaLibreta), 2)
                                + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 12)
                                + LPad("", 3)
                                + RPad(transaccion, 15)
                                + (signo.trim().equals("") ? LPad(signo + valor.toString(), 11) : LPad("", 11))
                                + (signo.trim().equals("-") ? LPad(signo + valor.toString(), 13) : LPad("", 13))
                                + LPad(saldo.toString(), 22));

                        } // FORMATO MASCOOP
                        else if (this.getFormatoLibreta() == 3) {
                            imprimeLinea(LPad(String.valueOf(lineaLibretaimpresa), 3) + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 15) + LPad("", 2) + (signo.trim().equals("") ? LPad(signo + valor.toString(), 16) : LPad("", 16)) + (signo.trim().equals("-") ? LPad(signo + valor.toString(), 15) : LPad("", 15)) + LPad(saldo.toString(), 16) + LPad("", 4) + transaccion);
                        } else if (this.getFormatoLibreta() == 5) {
                            imprimeLinea(LPad(String.valueOf(lineaLibretaimpresa), 3) + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 15) + LPad("", 2) + (signo.trim().equals("") ? LPad(signo + valor.toString(), 16) : LPad("", 16)) + (signo.trim().equals("-") ? LPad(signo + valor.toString(), 15) : LPad("", 15)) + LPad(saldo.toString(), 16) + LPad("", 4) + transaccion);
                        } else {
                            // Imprimiendo la Libreta                            
                            imprimeLinea(LPad(String.valueOf(getLineaLibreta()), 3)
                                    + LPad(this.getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 12)
                                    + LPad("", 2) + RPad(transaccion, 22)
                                    + (signo.trim().equals("") ? LPad(signo + valor.toString(), 10) : LPad("", 10))
                                    + (signo.trim().equals("-") ? LPad(signo + valor.toString(), 11) : LPad("", 11))
                                    + LPad(saldo.toString(), 12));
                        }

                        // Verificando si ya empezo a imprimir
                        if (!empezoImpresion) {
                            empezoImpresion = true;
                        }
                        this.codigoMovimientoUltimoImpreso = mca.getCodigoMovimiento();
                        this.setLineaLibreta(this.getLineaLibreta() + 1);
                        lineaLibretaimpresa++;

                    } else {

                        terminoLibreta = true;
                        break;
                    }

                }

                // Enviand a la impresora
                if (this.enviaImpresora()) {
                    // Registrando en la base de datos la impresion de la libreta
                    this.guardarImpresionLibretaCuenta();
                    if (this.isImpresionCorrecta()) {
                        //  MostramosMensaje de Asignar Nueva Libreta
                        if (terminoLibreta) {
                            this.muestraDialogoNuevaCartola = true;
                        }
                        // Guardando la reimpresion de la libreta
                        if (esReimpresion) {
                            this.guardaReimpresionLibreta();
                        }
                    }

                } else {
                    this.setImpresionCorrecta(false);
                }

                Thread.sleep(this.getLineasImpresas() * 200);

            } else {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenMovimientos"));
                MuestraMensaje.addAdvertencia(getMsg());
            }

        } catch (InterruptedException e) {
            this.setImpresionCorrecta(false);
            // Error en capa de control
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ImpresionLibreta", "imprimirReverso", CapturaError.getErrorException(e)});
        }
    }

    /**
     * Guarda la Impresion de la Libreta
     */
    private void guardarImpresionLibretaCuenta() {

        //System.out.println("this.codigoMovimientoUltimoImpreso " + this.codigoMovimientoUltimoImpreso);
        try {
            //System.out.println("Guarda Impresion Libretas " + codigoMovimientoUltimoImpreso);

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando la impresion de la libreta
            llamaSP.setNombreSP("mks_ahorros.pkm_impresion_libreta_cuenta.p_guarda");
            llamaSP.setNumeroParametros(3);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.cuenta.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_libreta", this.cuenta.getNumeroLibreta()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_movmiento", this.codigoMovimientoUltimoImpreso});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                //System.out.println("Impresion Guardada");
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                //msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovimientoGrabado");
                //MuestraMensaje.addInformacion(msg);

            } else {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            this.setImpresionCorrecta(false);
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ImpresionLibreta", "guardarImpresionLibretaCuenta", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Prepara la Reimpresion de la Libreta
     */
    public void preparaReimpresionLibreta() {
        this.setImpresionCorrecta(true);
        //System.out.println("this.codigoMovimientoUltimoImpreso " + this.codigoMovimientoUltimoImpreso);
        try {

            // Cargando la conexion de BD
            llamaSP = new LlamaSP();
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_ahorros.pkm_impresion_libreta_cuenta.p_prepara_reimpresion");
            llamaSP.setNumeroParametros(7);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.cuenta.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_movimiento_inicial", this.getCodigoMovimientoPrimeroReimpreso()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_movimiento_final", this.getCodigoMovimientoUltimoReimpreso()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_libreta_anterior", this.cuenta.getNumeroLibreta()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_libreta_nueva", this.numeroLibretaNueva});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_asignado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_asignacion", new java.sql.Timestamp(new Date().getTime())});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                //System.out.println("Prepara ReImpresion Guardada");
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                //msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovimientoGrabado");
                //MuestraMensaje.addInformacion(msg);

            } else {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            this.setImpresionCorrecta(false);
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ImpresionLibreta", "preparaReimpresionLibreta", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Prepara la Reimpresion de la Libreta
     */
    public void guardaReimpresionLibreta() {
        this.setImpresionCorrecta(true);
        //System.out.println("this.codigoMovimientoUltimoImpreso " + this.codigoMovimientoUltimoImpreso);
        try {

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_ahorros.pkm_impresion_libreta_cuenta.p_guarda_reimpresion");
            llamaSP.setNumeroParametros(3);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.cuenta.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_libreta_anterior", this.getNumeroLibretaAnterior()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_libreta_nueva", this.getNumeroLibretaNueva()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                //System.out.println("Impresion Guardada");
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                //msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovimientoGrabado");
                //MuestraMensaje.addInformacion(msg);

            } else {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            this.setImpresionCorrecta(false);
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ImpresionLibreta", "guardaReimpresionLibreta", CapturaError.getErrorException(ex)});
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
     * @return the muestraDialogoReverso
     */
    public boolean isMuestraDialogoReverso() {
        return muestraDialogoReverso;
    }

    /**
     * @param muestraDialogoReverso the muestraDialogoReverso to set
     */
    public void setMuestraDialogoReverso(boolean muestraDialogoReverso) {
        this.muestraDialogoReverso = muestraDialogoReverso;
    }

    /**
     * @return the muestraDialogoNuevaCartola
     */
    public boolean isMuestraDialogoNuevaCartola() {
        return muestraDialogoNuevaCartola;
    }

    /**
     * @param muestraDialogoNuevaCartola the muestraDialogoNuevaCartola to set
     */
    public void setMuestraDialogoNuevaCartola(boolean muestraDialogoNuevaCartola) {
        this.muestraDialogoNuevaCartola = muestraDialogoNuevaCartola;
    }

    /**
     * @return the muestraDialogoComprobante
     */
    public boolean isMuestraDialogoComprobante() {
        return muestraDialogoComprobante;
    }

    /**
     * @param muestraDialogoComprobante the muestraDialogoComprobante to set
     */
    public void setMuestraDialogoComprobante(boolean muestraDialogoComprobante) {
        this.muestraDialogoComprobante = muestraDialogoComprobante;
    }

    /**
     * @return the dialogoReverso
     */
    public String getDialogoReverso() {
        return dialogoReverso;
    }

    /**
     * @param dialogoReverso the dialogoReverso to set
     */
    public void setDialogoReverso(String dialogoReverso) {
        this.dialogoReverso = dialogoReverso;
    }

    /**
     * @return the dialogoNuevaCartola
     */
    public String getDialogoNuevaCartola() {
        return dialogoNuevaCartola;
    }

    /**
     * @param dialogoNuevaCartola the dialogoNuevaCartola to set
     */
    public void setDialogoNuevaCartola(String dialogoNuevaCartola) {
        this.dialogoNuevaCartola = dialogoNuevaCartola;
    }

    /**
     * @return the dialogoComprobante
     */
    public String getDialogoComprobante() {
        return dialogoComprobante;
    }

    /**
     * @param dialogoComprobante the dialogoComprobante to set
     */
    public void setDialogoComprobante(String dialogoComprobante) {
        this.dialogoComprobante = dialogoComprobante;
    }

    /**
     * @return the itemsMovimientoCuentaAdicionalImpresas
     */
    public List<MovimientoCuentaAdicional> getItemsMovimientoCuentaAdicionalImpresas() {
        return itemsMovimientoCuentaAdicionalImpresas;
    }

    /**
     * @param itemsMovimientoCuentaAdicionalImpresas the
     * itemsMovimientoCuentaAdicionalImpresas to set
     */
    public void setItemsMovimientoCuentaAdicionalImpresas(List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalImpresas) {
        this.itemsMovimientoCuentaAdicionalImpresas = itemsMovimientoCuentaAdicionalImpresas;
    }

    /**
     * @return the itemsMovimientoCuentaAdicionalNoImpresas
     */
    public List<MovimientoCuentaAdicional> getItemsMovimientoCuentaAdicionalNoImpresas() {
        return itemsMovimientoCuentaAdicionalNoImpresas;
    }

    /**
     * @param itemsMovimientoCuentaAdicionalNoImpresas the
     * itemsMovimientoCuentaAdicionalNoImpresas to set
     */
    public void setItemsMovimientoCuentaAdicionalNoImpresas(List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalNoImpresas) {
        this.itemsMovimientoCuentaAdicionalNoImpresas = itemsMovimientoCuentaAdicionalNoImpresas;
    }

    /**
     * @return the itemsFirmanteCuenta
     */
    public List<FirmanteCuenta> getItemsFirmanteCuenta() {
        return itemsFirmanteCuenta;
    }

    /**
     * @param itemsFirmanteCuenta the itemsFirmanteCuenta to set
     */
    public void setItemsFirmanteCuenta(List<FirmanteCuenta> itemsFirmanteCuenta) {
        this.itemsFirmanteCuenta = itemsFirmanteCuenta;
    }

    /**
     * @return the numeroLineasAnverso
     */
    public int getNumeroLineasAnverso() {
        return numeroLineasAnverso;
    }

    /**
     * @param numeroLineasAnverso the numeroLineasAnverso to set
     */
    public void setNumeroLineasAnverso(int numeroLineasAnverso) {
        this.numeroLineasAnverso = numeroLineasAnverso;
    }

    /**
     * @return the formatoLibreta
     */
    public int getFormatoLibreta() {
        return formatoLibreta;
    }

    /**
     * @param formatoLibreta the formatoLibreta to set
     */
    public void setFormatoLibreta(int formatoLibreta) {
        this.formatoLibreta = formatoLibreta;
    }

    /**
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the codigoMovimientoUltimoImpreso
     */
    public long getCodigoMovimientoUltimoImpreso() {
        return codigoMovimientoUltimoImpreso;
    }

    /**
     * @param codigoMovimientoUltimoImpreso the codigoMovimientoUltimoImpreso to
     * set
     */
    public void setCodigoMovimientoUltimoImpreso(long codigoMovimientoUltimoImpreso) {
        this.codigoMovimientoUltimoImpreso = codigoMovimientoUltimoImpreso;
    }

    /**
     * @return the numeroLineasReverso
     */
    public int getNumeroLineasReverso() {
        return numeroLineasReverso;
    }

    /**
     * @param numeroLineasReverso the numeroLineasReverso to set
     */
    public void setNumeroLineasReverso(int numeroLineasReverso) {
        this.numeroLineasReverso = numeroLineasReverso;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the esReimpresion
     */
    public boolean isEsReimpresion() {
        return esReimpresion;
    }

    /**
     * @param esReimpresion the esReimpresion to set
     */
    public void setEsReimpresion(boolean esReimpresion) {
        this.esReimpresion = esReimpresion;
    }

    /**
     * @return the codigoMovimientoUltimoReimpreso
     */
    public long getCodigoMovimientoUltimoReimpreso() {
        return codigoMovimientoUltimoReimpreso;
    }

    /**
     * @param codigoMovimientoUltimoReimpreso the
     * codigoMovimientoUltimoReimpreso to set
     */
    public void setCodigoMovimientoUltimoReimpreso(long codigoMovimientoUltimoReimpreso) {
        this.codigoMovimientoUltimoReimpreso = codigoMovimientoUltimoReimpreso;
    }

    /**
     * @return the codigoMovimientoPrimeroReimpreso
     */
    public long getCodigoMovimientoPrimeroReimpreso() {
        return codigoMovimientoPrimeroReimpreso;
    }

    /**
     * @param codigoMovimientoPrimeroReimpreso the
     * codigoMovimientoPrimeroReimpreso to set
     */
    public void setCodigoMovimientoPrimeroReimpreso(long codigoMovimientoPrimeroReimpreso) {
        this.codigoMovimientoPrimeroReimpreso = codigoMovimientoPrimeroReimpreso;
    }

    /**
     * @return the numeroLibretaNueva
     */
    public String getNumeroLibretaNueva() {
        return numeroLibretaNueva;
    }

    /**
     * @param numeroLibretaNueva the numeroLibretaNueva to set
     */
    public void setNumeroLibretaNueva(String numeroLibretaNueva) {
        this.numeroLibretaNueva = numeroLibretaNueva;
    }

    /**
     * @return the numeroLibretaAnterior
     */
    public String getNumeroLibretaAnterior() {
        return numeroLibretaAnterior;
    }

    /**
     * @param numeroLibretaAnterior the numeroLibretaAnterior to set
     */
    public void setNumeroLibretaAnterior(String numeroLibretaAnterior) {
        this.numeroLibretaAnterior = numeroLibretaAnterior;
    }

    /**
     * @return the lineaLibreta
     */
    public int getLineaLibreta() {
        return lineaLibreta;
    }

    /**
     * @param lineaLibreta the lineaLibreta to set
     */
    public void setLineaLibreta(int lineaLibreta) {
        this.lineaLibreta = lineaLibreta;
    }

}
