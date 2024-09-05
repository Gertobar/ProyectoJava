/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.reportes;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.FirmanteCuenta;
import ec.renafipse.mks.modelo.ahorros.ImpresionLibretaCuenta;
import ec.renafipse.mks.modelo.ahorros.ImpresionLibretaCuentaPK;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
import ec.renafipse.mks.negocio.ahorros.ImpresionLibretaCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.MovimientoCuentaAdicionalFacade;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author FliaAstudillo
 */
public class ImprimeLibreta {

    @EJB
    private ImpresionLibretaCuentaFacade ejbFacade;

    @EJB
    private MovimientoCuentaAdicionalFacade ejbFacadeMovimientoCuentaAdicional;

    // Variables de impresion
    private String msg;
    private FileOutputStream os;
    private PrintStream pw;
    private boolean empezoImpresion;
    private boolean esReverso;
    private int lineaLibreta;
    private String transaccion;
    private String valor;
    private String saldo;
    private String signo;
    private DecimalFormat formatoValores;
    private SimpleDateFormat formatoFechas;
    private int numeroLineasAnverso;
    private int numeroLineasReverso;
    private List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalImpresas;
    private List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalNoImpresas;
    private List<FirmanteCuenta> listaFirmanteCuenta;
    private RequestContext context;
    private Cuenta cuenta;
    private String rutaImpresora;

    public ImprimeLibreta(String rutaImpresora, int numeroLineasAnverso,
            int numeroLineasReverso, Cuenta cuenta,
            List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalImpresas,
            List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalNoImpresas) {
        this.empezoImpresion = false;
        this.esReverso = false;
        this.rutaImpresora = rutaImpresora;
        this.numeroLineasAnverso = numeroLineasAnverso;
        this.numeroLineasReverso = numeroLineasReverso;
        this.cuenta = cuenta;
        this.itemsMovimientoCuentaAdicionalImpresas = itemsMovimientoCuentaAdicionalImpresas;
        this.itemsMovimientoCuentaAdicionalNoImpresas = itemsMovimientoCuentaAdicionalNoImpresas;
    }

    public void imprimirAnverso(RequestContext context) {
        //  opcion = 1;

        try {
            this.context = context;
            String impresora = ActivacionUsuario.getRutaImpresora();
            //System.out.println("impresora " + impresora);

            //impresora = "\\\\192.168.1.101\\epsonlx";

            /*DecimalFormat miFormato = new DecimalFormat("###,###,##0.00");
             String cad = "";
             

             PrintStream ps = new PrintStream(os);*/
            //----------------- DESDE AQUI
            try {;
                setOs(new FileOutputStream(this.getRutaImpresora()));
                setPw(new PrintStream(getOs()));

                //System.out.println("pw.checkError() " + getPw().checkError());
            } catch (FileNotFoundException pwe) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorIniciarImpresora"));
                pwe.printStackTrace();
            }

            setEmpezoImpresion(false);
            setEsReverso(false);

            //numero de posicion en la cartola en la que se va a imprimir
            setLineaLibreta(getItemsMovimientoCuentaAdicionalImpresas().size() + 1);

            // Estableciendo Formatos de Impresion
            setFormatoValores(new DecimalFormat("###,###,##0.00"));
            setFormatoFechas(new SimpleDateFormat("dd/MM/yyyy"));

            //System.out.println("Impresion lista " + getItemsMovimientoCuentaAdicionalNoImpresas().size());

            //Verificando que tenga lineas que impirmi
            if (getItemsMovimientoCuentaAdicionalNoImpresas().isEmpty()) {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenMovimientos"));
                getPw().close();
                getPw().flush();
                MuestraMensaje.addAdvertencia(getMsg());
                getContext().execute("procesandoDlg.hide()");
                return;
            }

            //System.out.println("Pso lista vacia");

            // Verificando si tiene que irse a la Impresion reversa
            if (getLineaLibreta() > this.getNumeroLineasAnverso()) {
                getContext().execute("procesandoDlg.hide()");
                getContext().execute("ImprimeReversoDialogo.show()");
                //  MostramosMensaje de dar vuelta a la libreta
                //System.out.println("Impresion Reverso");
                return;
            }
            
            //System.out.println("Pso si es reverso");

            // Verificando si es la primera linea para imprimir la cabecera
            if (getLineaLibreta() == 1) {
                getPw().println("Codigo   : " + this.getCuenta().getSocio().getSocioPK().getCodigo());
                getPw().println("Socio    : " + this.getCuenta().getSocio().getCodigoPersona().getNombreCompleto());
                getPw().println("# Cuenta : " + this.getCuenta().getNumero());
                getPw().println("Firmantes: " + this.getCuenta().getCodigoTipoFirma().getNombre());

                // Si tiene Firmantes se imprime en la cabecera
                int numeroFirmante = 0;
                if (!this.listaFirmanteCuenta.isEmpty()) {
                    for (FirmanteCuenta fc : getListaFirmanteCuenta()) {
                        numeroFirmante++;
                        getPw().println("      Firmante " + numeroFirmante + ": " + fc.getPersona().getNombreCompleto());
                    }

                }

                if (numeroFirmante > 0) {
                    for (int i = numeroFirmante; i < 3; i++) {
                        getPw().println();
                    }
                } else {
                    getPw().println();
                    getPw().println();
                    getPw().println();
                }
                
                //System.out.println("Imprimio Cabecera");
                getPw().println();

                // Consultando si esta en el anverso de la libreta
            }
            
            //System.out.println("Pao impreson cabcera");
            

            for (MovimientoCuentaAdicional mca : getItemsMovimientoCuentaAdicionalNoImpresas()) {

                if (getLineaLibreta() <= this.getNumeroLineasAnverso()) {
                    if (!isEmpezoImpresion()) {
                        // Recorremos el carro hasta que llegue a la linea correcta
                        for (int c = 0; c <= getLineaLibreta() + 8; c++) {
                            getPw().println();
                        }
                    }
                } else {
                    setEsReverso(true);
                    this.setItemsMovimientoCuentaAdicionalNoImpresas(this.ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalNoImpreso(this.getCuenta().getCodigo(), 'N'));

                }
                
                // Si toca el reverso
                if (!isEsReverso()) {
                    setTransaccion(mca.getMovimientoCuenta().getConceptoTransaccionPro().getConceptoTransaccion().getSiglas());
                    setSigno((String.valueOf(mca.getMovimientoCuenta().getTipo()).equals("D")) ? "-" : " ");
                    setValor(getFormatoValores().format(mca.getMovimientoCuenta().getTotalMovimiento().doubleValue()));
                    setSaldo(getFormatoValores().format(mca.getMovimientoCuenta().getSaldoCuenta().doubleValue()));

                    // Imprimiendo la Libreta
                    getPw().println(LPad(String.valueOf(getLineaLibreta()), 5) + LPad(getFormatoFechas().format(mca.getMovimientoCuenta().getFechaMovimiento()), 15) + LPad(getTransaccion(), 12) + RPad(getSigno() + getValor().toString(), 12) + RPad(getSaldo().toString(), 12));
                    
                    //System.out.println("Imprimio linea "+lineaLibreta);
                    // Guardando la Impresion de la Libreta
                    if (!this.guardarImpresionLibretaCuenta(mca, (short) lineaLibreta, mca.getCodigoMovimiento(), this.cuenta.getNumeroLibreta())) {
                        getPw().close();
                        getPw().flush();
                        return;
                    }
                    
                    //System.out.println("Guardo Imprimio linea "+lineaLibreta);

                    // Verificando si ya empezo a imprimir
                    if (!isEmpezoImpresion()) {
                        setEmpezoImpresion(true);
                    }

                    this.lineaLibreta ++;
                    
                    //System.out.println("Sumo linea ");
                } else {
                    getPw().close();
                    getPw().flush();
                    Thread.sleep(2000);
                    getContext().execute("procesandoDlg.hide()");
                    getContext().execute("ImprimeReversoDialogo.show()");
                    //  MostramosMensaje de dar vuelta a la libreta
                    return;
                }

            }
            // Cerramos la Impresion            
            getPw().close();
            getPw().flush();
            Thread.sleep(2000);
            getContext().execute("procesandoDlg.hide()");

        } catch (Exception e) {
        }
    }

    /**
     * Guarda la Impresion de la Libreta
     *
     * @param movimientoCuentaAdicional Movimiento de la Cuenta
     * @param numeroLinea Numero de Linea de la LIbreta
     * @param codigoMovimiento Cofigo de Movimiento
     * @param numeroLibreta NUmero de Libreta
     * @return true = correcto ; false = error al guardar
     */
    private boolean guardarImpresionLibretaCuenta(MovimientoCuentaAdicional movimientoCuentaAdicional, short numeroLinea, Long codigoMovimiento, String numeroLibreta) {
        try {
            // Insertando la impresión de la libreta
            ImpresionLibretaCuenta impresionLibreta = new ImpresionLibretaCuenta(new ImpresionLibretaCuentaPK());
            impresionLibreta.getImpresionLibretaCuentaPK().setCodigoMovimiento(codigoMovimiento);
            impresionLibreta.getImpresionLibretaCuentaPK().setNumeroLibreta(numeroLibreta);
            impresionLibreta.setNumeroLinea(numeroLinea);
            this.ejbFacade.create(impresionLibreta);

            // Actualizando el estado de la libreta
            movimientoCuentaAdicional.setImpreso('S');
            this.ejbFacadeMovimientoCuentaAdicional.edit(movimientoCuentaAdicional);
        } catch (Exception e) {
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorGuardaImpresionLibreta"));
            MuestraMensaje.addError(getMsg());
            return false;
        }

        return true;

    }

    private String LPad(String str, Integer length) {
        return str + String.format("%" + (length - str.length()) + "s", "");
    }

    private String RPad(String str, Integer length) {
        return String.format("%" + (length - str.length()) + "s", "") + str;
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
     * @return the os
     */
    public FileOutputStream getOs() {
        return os;
    }

    /**
     * @param os the os to set
     */
    public void setOs(FileOutputStream os) {
        this.os = os;
    }

    /**
     * @return the pw
     */
    public PrintStream getPw() {
        return pw;
    }

    /**
     * @param pw the pw to set
     */
    public void setPw(PrintStream pw) {
        this.pw = pw;
    }

    /**
     * @return the empezoImpresion
     */
    public boolean isEmpezoImpresion() {
        return empezoImpresion;
    }

    /**
     * @param empezoImpresion the empezoImpresion to set
     */
    public void setEmpezoImpresion(boolean empezoImpresion) {
        this.empezoImpresion = empezoImpresion;
    }

    /**
     * @return the esReverso
     */
    public boolean isEsReverso() {
        return esReverso;
    }

    /**
     * @param esReverso the esReverso to set
     */
    public void setEsReverso(boolean esReverso) {
        this.esReverso = esReverso;
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

    /**
     * @return the transaccion
     */
    public String getTransaccion() {
        return transaccion;
    }

    /**
     * @param transaccion the transaccion to set
     */
    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the saldo
     */
    public String getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the signo
     */
    public String getSigno() {
        return signo;
    }

    /**
     * @param signo the signo to set
     */
    public void setSigno(String signo) {
        this.signo = signo;
    }

    /**
     * @return the formatoValores
     */
    public DecimalFormat getFormatoValores() {
        return formatoValores;
    }

    /**
     * @param formatoValores the formatoValores to set
     */
    public void setFormatoValores(DecimalFormat formatoValores) {
        this.formatoValores = formatoValores;
    }

    /**
     * @return the formatoFechas
     */
    public SimpleDateFormat getFormatoFechas() {
        return formatoFechas;
    }

    /**
     * @param formatoFechas the formatoFechas to set
     */
    public void setFormatoFechas(SimpleDateFormat formatoFechas) {
        this.formatoFechas = formatoFechas;
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
     * @return the listaFirmanteCuenta
     */
    public List<FirmanteCuenta> getListaFirmanteCuenta() {
        return listaFirmanteCuenta;
    }

    /**
     * @param listaFirmanteCuenta the listaFirmanteCuenta to set
     */
    public void setListaFirmanteCuenta(List<FirmanteCuenta> listaFirmanteCuenta) {
        this.listaFirmanteCuenta = listaFirmanteCuenta;
    }

    /**
     * @return the context
     */
    public RequestContext getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(RequestContext context) {
        this.context = context;
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
     * @return the rutaImpresora
     */
    public String getRutaImpresora() {
        return rutaImpresora;
    }

    /**
     * @param rutaImpresora the rutaImpresora to set
     */
    public void setRutaImpresora(String rutaImpresora) {
        this.rutaImpresora = rutaImpresora;
    }

}
