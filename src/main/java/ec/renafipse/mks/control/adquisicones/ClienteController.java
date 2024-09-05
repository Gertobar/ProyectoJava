package ec.renafipse.mks.control.adquisicones;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.adquisiciones.Cliente;
import ec.renafipse.mks.modelo.adquisiciones.ClienteIfip;
import ec.renafipse.mks.modelo.adquisiciones.ClienteIfipPK;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.TipoIdentificacion;
import ec.renafipse.mks.modelo.socios.TipoPersona;
import ec.renafipse.mks.negocio.adquisiciones.ClienteFacade;
import ec.renafipse.mks.negocio.adquisiciones.ClienteIfipFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.TipoPerTipoIdeFacade;
import ec.renafipse.mks.negocio.socios.TipoPersonaFacade;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "clienteController")
@ViewScoped
public class ClienteController extends AbstractController<ClienteIfip> {

    @EJB
    private ClienteIfipFacade ejbFacade;

    @EJB
    private ClienteFacade ejbFacadeCliente;

    @EJB
    private PersonaFacade ejbFacadePesrsona;

    @EJB
    private TipoPersonaFacade ejbFacadeTipPer;

    @EJB
    private TipoPerTipoIdeFacade ejbFacadeTipPerTipoIde;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private String buscarPor;
    private String siglasIdentificacion;
    private String msg;
    private String criterio;
    private String identificacion;
    private String etiquetaDialogo;

    private boolean esEdicion;
    private boolean esCliente;

    private List<ClienteIfip> itemsClienteIfip;
    private List<TipoIdentificacion> itemsTipoIdentificacion;

    private ClienteIfip clienteIfip;
    private Cliente cliente;
    private Persona persona;

    /**
     * Initialize the concrete ItemVenta controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsClienteIfip(null);
        this.setClienteIfip(null);
        this.setPersona(null);
        this.setCliente(null);

    }

    public ClienteController() {
        // Inform the Abstract parent controller of the concrete ItemVenta?cap_first Entity
        super(ClienteIfip.class);
    }

    // -------------------------------------------------------------------------------------------------
    // -- METODOS PARA GUARDAR Y EDITAR EL CLIENTE
    public void guardar() {
        try {
            if (this.getMsg() != null) {
                MuestraMensaje.addError(msg);
                return;
            }

            if (persona != null) {
                this.ejbFacadePesrsona.edit(persona);
            } else {
                if (!this.esEdicion) {
                    this.persona = new Persona();
                    this.persona = this.getClienteIfip().getCliente().getPersona();
                    this.persona.setFechaActualizacion(new Date());
                    this.persona.setFechaIngreso(new Date());
                    this.ejbFacadePesrsona.create(persona);
                }
            }

            if (cliente != null) {
                this.ejbFacadeCliente.edit(cliente);
            } else {
                if (!this.esEdicion) {
                    this.cliente = new Cliente();
                    this.cliente.setCodigoPersona(this.persona.getCodigo());
                    this.cliente.setDireccion(this.getClienteIfip().getCliente().getDireccion());
                    this.cliente.setFechaRegistro(new Date());
                    this.cliente.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
                    this.cliente.setTelefono(this.getClienteIfip().getCliente().getTelefono());
                    this.ejbFacadeCliente.create(cliente);

                }
            }

            if (!this.esEdicion) {
                this.clienteIfip.setClienteIfipPK(new ClienteIfipPK(this.cliente.getCodigo(), ActivacionUsuario.getCodigoIfip()));
                this.clienteIfip.setEliminado('N');
                this.clienteIfip.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
                this.clienteIfip.setFechaRegistro(new Date());
                this.clienteIfip.setCliente(cliente);
                this.ejbFacade.create(clienteIfip);
            } else {
                this.ejbFacade.edit(clienteIfip);
            }

            this.clienteIfip = null;
            this.obtieneClientesIfip();

            // Mostrando mensaje de Satisfactorio
            MuestraMensaje.addSatisfactorioPersistencia(1);

        } catch (Exception ex) {
            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"clienteController", "guardar", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Obtiene los Clientes de la IFIP
     */
    public void obtieneClientesIfip() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(criterio, 6)) {
                if (this.buscarPor.equals("N")) {
                    this.setItemsClienteIfip(this.ejbFacade.getItemsNombrePersonaCodigoIfip(ActivacionUsuario.getCodigoIfip(), criterio.trim().toUpperCase()));
                } else {
                    this.setItemsClienteIfip(this.ejbFacade.getItemsIdentificacionPersonaCodigoIfip(ActivacionUsuario.getCodigoIfip(), criterio.trim().toUpperCase()));
                }
                this.clienteIfip = null;
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsClienteIfip(null);
                this.setClienteIfip(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"clienteController", "obtieneClientesIfip", CapturaError.getErrorException(ex)});
        }
    }

    // -------------------------------------------------------------------------------------------------
    // -- METODOS PARA PREPARAR LA CREACION Y EDICION DEL CLIENTE
    /**
     * Prepara la Creación del Cliente.
     *
     * @param actionEvent
     */
    public void prepraCliente(ActionEvent actionEvent) {
        this.esEdicion = false;
        this.setClienteIfip(new ClienteIfip());
        this.getClienteIfip().setCliente(new Cliente());
        this.getClienteIfip().getCliente().setPersona(new Persona());
        this.clienteIfip.getCliente().getPersona().setCodigoTipoIdentificacion(null);
        this.itemsTipoIdentificacion = null;
        this.setPersona(null);
        this.setCliente(null);
        this.setMsg(null);
        this.identificacion = null;
        etiquetaDialogo = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Crea");

    }

    /**
     * Prepara la Edicion del Cliente
     *
     * @param actionEvent
     */
    public void preparaEdicion(ActionEvent actionEvent) {
        this.esEdicion = true;
        this.identificacion = this.getClienteIfip().getCliente().getPersona().getIdentificacion();
        this.persona = this.getClienteIfip().getCliente().getPersona();
        this.cliente = this.getClienteIfip().getCliente();
        this.setMsg(null);
        etiquetaDialogo = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Editar");
    }

    /**
     * Valida si el Cliente ingresado existe en la ifip, caso contrario busca si
     * ya esta ingresado como un cliente y/o persona en el sistema.
     */
    public void validaClienteIngresado() {

        this.setMsg(null);
        // VErificando si es edicion y no ha cambiado la cedula para no validar si es socio ingresado.
        if (this.esEdicion && this.identificacion.equals(this.getClienteIfip().getCliente().getPersona().getIdentificacion())) {
            return;
        }

        this.esCliente = false;
        List<ClienteIfip> listaClienteIfip = this.ejbFacade.getItemsIdentificacionPersonaCodigoIfip(ActivacionUsuario.getCodigoIfip(), this.getClienteIfip().getCliente().getPersona().getIdentificacion());
        if (!listaClienteIfip.isEmpty()) {
            this.esCliente = true;
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ClienteYaExistente"));
            MuestraMensaje.addError(msg);

        } else {
            List<Cliente> listaClientes = this.ejbFacadeCliente.getItemsIdentificacionPersona(this.getClienteIfip().getCliente().getPersona().getIdentificacion());
            if (!listaClientes.isEmpty()) {
                if (listaClientes.size() == 1) {
                    this.setCliente(listaClientes.get(0));
                    this.getClienteIfip().setCliente(cliente);
                    this.persona = this.getCliente().getPersona();
                } else {
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ExisteMasUnClienteIdentificacion"));
                    MuestraMensaje.addError(msg);
                }
            } else {
                this.persona = this.ejbFacadePesrsona.getPersona(this.getClienteIfip().getCliente().getPersona().getIdentificacion());
                if (persona != null) {
                    this.getClienteIfip().getCliente().setPersona(persona);
                }
            }
        }
    }
    // -------------------------------------------------------------------------------------
    // -- REFRESCAMIENTO DE COMBOS/LISTAS 
    /**
     * Al cambiar el tipo de persona
     */
    public void cambiaTipoPersona() {

        this.setItemsTipoIdentificacion(this.ejbFacadeTipPerTipoIde.getItemsTipoIdentificacionVigente(this.clienteIfip.getCliente().getPersona().getCodigoTipoPersona().getCodigo()));
        this.clienteIfip.getCliente().getPersona().setIdentificacion(null);
        this.clienteIfip.getCliente().getPersona().setCodigoTipoIdentificacion(null);
    }

    /**
     * Al cambiar el tipo de Identificacion
     */
    public void cambiaTipoIdentificacion() {
        this.clienteIfip.getCliente().getPersona().setIdentificacion(null);
        this.setSiglasIdentificacion(String.valueOf(this.clienteIfip.getCliente().getPersona().getCodigoTipoIdentificacion().getSiglas()));
        if (this.getSiglasIdentificacion().equals("S")) {
            this.getClienteIfip().getCliente().getPersona().setCodigoTipoIdentificacion(null);
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TipoIdenficacionNoAdmitido"));
            MuestraMensaje.addError(msg);
        }

    }

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<TipoPersona> getItemsTipoPersona() {
        return this.ejbFacadeTipPer.getItemsTipoPersonaVigentes();
    }

    /**
     * @return the itemsClienteIfip
     */
    public List<ClienteIfip> getItemsClienteIfip() {
        return itemsClienteIfip;
    }

    /**
     * @param itemsClienteIfip the itemsClienteIfip to set
     */
    public void setItemsClienteIfip(List<ClienteIfip> itemsClienteIfip) {
        this.itemsClienteIfip = itemsClienteIfip;
    }

    /**
     * @return the clienteIfip
     */
    public ClienteIfip getClienteIfip() {
        return clienteIfip;
    }

    /**
     * @param clienteIfip the clienteIfip to set
     */
    public void setClienteIfip(ClienteIfip clienteIfip) {
        this.clienteIfip = clienteIfip;
    }

    /**
     * @return the itemsTipoIdentificacion
     */
    public List<TipoIdentificacion> getItemsTipoIdentificacion() {
        return itemsTipoIdentificacion;
    }

    /**
     * @param itemsTipoIdentificacion the itemsTipoIdentificacion to set
     */
    public void setItemsTipoIdentificacion(List<TipoIdentificacion> itemsTipoIdentificacion) {
        this.itemsTipoIdentificacion = itemsTipoIdentificacion;
    }

    /**
     * @return the siglasIdentificacion
     */
    public String getSiglasIdentificacion() {
        return siglasIdentificacion;
    }

    /**
     * @param siglasIdentificacion the siglasIdentificacion to set
     */
    public void setSiglasIdentificacion(String siglasIdentificacion) {
        this.siglasIdentificacion = siglasIdentificacion;
    }

    /**
     * @return the buscarPor
     */
    public String getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor the buscarPor to set
     */
    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
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
     * @return the criterio
     */
    public String getCriterio() {
        return criterio;
    }

    /**
     * @param criterio the criterio to set
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the esEdicion
     */
    public boolean isEsEdicion() {
        return esEdicion;
    }

    /**
     * @param esEdicion the esEdicion to set
     */
    public void setEsEdicion(boolean esEdicion) {
        this.esEdicion = esEdicion;
    }

    /**
     * @return the esCliente
     */
    public boolean isEsCliente() {
        return esCliente;
    }

    /**
     * @param esCliente the esCliente to set
     */
    public void setEsCliente(boolean esCliente) {
        this.esCliente = esCliente;
    }

    /**
     * @return the etiquetaDialogo
     */
    public String getEtiquetaDialogo() {
        return etiquetaDialogo;
    }

    /**
     * @param etiquetaDialogo the etiquetaDialogo to set
     */
    public void setEtiquetaDialogo(String etiquetaDialogo) {
        this.etiquetaDialogo = etiquetaDialogo;
    }

}
