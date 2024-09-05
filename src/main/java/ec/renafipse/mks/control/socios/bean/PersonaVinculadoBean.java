/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.socios.bean;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.socios.Cargo;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaRelacionIfip;
import ec.renafipse.mks.modelo.socios.PersonaVinculado;
import ec.renafipse.mks.modelo.socios.PersonaVinculadoPK;
import ec.renafipse.mks.modelo.socios.TipoVinculado;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.socios.CargoFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.PersonaRelacionIfipFacade;
import ec.renafipse.mks.negocio.socios.PersonaVinculadoFacade;
import ec.renafipse.mks.negocio.socios.TipoVinculadoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Miguel Saca
 */
@ManagedBean(name = "personaVinculadoBean")
@ViewScoped
public class PersonaVinculadoBean extends AbstractController<PersonaVinculado> implements Serializable {

    @EJB
    private PersonaVinculadoFacade ejbFacade;
    @EJB
    private PersonaFacade ejbFacadePersona;
    @EJB
    private TipoVinculadoFacade ejbTipoVinculadoFacade;
    @EJB
    private CargoFacade ejbCargoFacade;
    @EJB
    private PersonaRelacionIfipFacade ejbPersonaRelacionIfipFacade;
    @EJB
    private IfipAgenciaFacade ejbIfipAgenciaFacade;
    // -- PARAMETROS PERSONALIZADOS
    private String buscarPor;
    private String nombrePersona;
    private String nombrePersonaBusqueda;
    private String mensajeCriterio;
    private boolean deshabilitaBuscarPersonaLista;
    private boolean nuevoCargo;
    private Persona personaSel;
    private Persona personaVincularSel;
    private TipoVinculado tipoVinculado;
    private PersonaRelacionIfip personaRelacionIfip;
    private List<Persona> itemsPersona;
    private List<PersonaVinculado> itemsPersonaVinculado;
    private List<TipoVinculado> itemsTipoVinculados;
    private List<Cargo> itemsCargo;
    private List<IfipAgencia> itemsIfipAgencia;

    public PersonaVinculadoBean() {
        super(PersonaVinculado.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        itemsTipoVinculados = ejbTipoVinculadoFacade.findAll();
        itemsCargo = ejbCargoFacade.findAll();
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPersonaVinculadoPK(new PersonaVinculadoPK());
    }

    public void obtienePersonasNaturales() {

        try {
            if (Validaciones.cumpleRequerimientoCampo(nombrePersonaBusqueda, 8)) {
                this.setItemsPersona(this.ejbFacadePersona.getItemsPersona(nombrePersonaBusqueda, (getBuscarPor() == null) ? "N" : getBuscarPor(), 'N'));
            } else {
                this.mensajeCriterio = Validaciones.msg;
                this.setItemsPersona(null);
                this.setPersonaSel(null);

            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaGeneralBean", "obtienePersonasNaturales", CapturaError.getErrorException(ex)});
        }
    }

    public void creaPersonaVinculado(ActionEvent actionEvent) {

    }

    public void editaPersonaVinculado(ActionEvent actionEvent) {
    }

    public void preparaEdicionCargo(ActionEvent actionEvent) {
        personaRelacionIfip = ejbPersonaRelacionIfipFacade.getItemByCodigoPersona(personaSel.getCodigo());
        nuevoCargo = (personaRelacionIfip == null);
        if (nuevoCargo) {
            personaRelacionIfip = new PersonaRelacionIfip();
        }
        itemsIfipAgencia = ejbIfipAgenciaFacade.getItemsIfipAgenciaPorIfip(ActivacionUsuario.getCodigoIfip());
    }

    public void preparaBusquedaSocioLista(ActionEvent actionEvent) throws IOException {
        itemsPersona = null;
        personaSel = null;
        itemsPersonaVinculado = null;
        nombrePersona = null;
        setSelected(null);
    }

    public void seleccionaPersona(ActionEvent actionEvent) {
        itemsPersonaVinculado = ejbFacade.getItemsfindByVinculadoYpersona(personaSel.getCodigo());
        nombrePersona = personaSel.getNombreCompleto();
        itemsPersona = null;
        mensajeCriterio = null;
        nombrePersonaBusqueda = null;
        buscarPor = "N";
    }

    public void editaViculado(ActionEvent actionEvent) {
        if (personaSel != null && getSelected() != null) {
            getSelected().setFechaRegistro(new Date());
            getSelected().setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            ejbFacade.actualiza(getSelected().getPersonaVinculadoPK().getCodigoPersona(),
                    getSelected().getPersonaVinculadoPK().getCodigoPersonaVinculado(),
                    getSelected().getCodigoTipoVinculado().getCodigo(),
                    getSelected().getEliminado(),
                    getSelected().getFechaRegistro(),
                    getSelected().getRegistradoPor());
            itemsPersonaVinculado = ejbFacade.getItemsfindByVinculadoYpersona(personaSel.getCodigo());
            MuestraMensaje.addSatisfactorioPersistencia(1);
        } else {
            MuestraMensaje.addError("Selecccionar Primero el registro a editar");
        }
    }

    public void crearEditarCargo(ActionEvent actionEvent) {
        personaRelacionIfip.setCodigoPersona(personaSel);
        personaRelacionIfip.setFechaRegistro(new Date());
        if (nuevoCargo) {
            ejbPersonaRelacionIfipFacade.create(personaRelacionIfip);
        } else {
            ejbPersonaRelacionIfipFacade.actualiza(personaRelacionIfip.getCodigo(),
                    personaRelacionIfip.getCodigoCargo().getCodigo(),
                    personaRelacionIfip.getCodigoIfipAgencia().getCodigo(),
                    personaRelacionIfip.getFechaRegistro(),
                    personaRelacionIfip.getEliminado());
        }
        MuestraMensaje.addSatisfactorioPersistencia(1);
    }

    public void seleccionaPersonaVinculada(ActionEvent actionEvent) {
        if (personaSel != null && personaVincularSel != null) {
            if (itemsPersonaVinculado == null) {
                itemsPersonaVinculado = new ArrayList<PersonaVinculado>();
            }
            for (PersonaVinculado pv : itemsPersonaVinculado) {
                if (pv.getPersona().getIdentificacion().equals(personaVincularSel.getIdentificacion())
                        || pv.getPersona1().getIdentificacion().equals(personaVincularSel.getIdentificacion())) {
                    itemsPersona = null;
                    MuestraMensaje.addError("Vinculo  Ya registrado");
                    return;
                }
            }
            if (tipoVinculado == null && itemsTipoVinculados != null && !itemsTipoVinculados.isEmpty()) {
                tipoVinculado = itemsTipoVinculados.get(0);
            }
            PersonaVinculado personaVinculado = new PersonaVinculado(personaSel.getCodigo(), personaVincularSel.getCodigo());
            personaVinculado.setEliminado('N');
            personaVinculado.setFechaRegistro(new Date());
            personaVinculado.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            personaVinculado.setCodigoTipoVinculado(tipoVinculado);
            ejbFacade.create(personaVinculado);
            itemsPersonaVinculado = ejbFacade.getItemsfindByVinculadoYpersona(personaSel.getCodigo());
            MuestraMensaje.addSatisfactorioPersistencia(1);
        } else {
            MuestraMensaje.addError("No hay una persona seleccionada");
        }
        itemsPersona = null;
    }

    public void cambiaCriterio() {
        deshabilitaBuscarPersonaLista = buscarPor.equals("N");
    }

    //Visualizacion de datos de personas vinculadas
    public String valorIdentificacion(String identificacion, String identificacion1) {
        if (personaSel != null) {
            return (identificacion.equals(personaSel.getIdentificacion())) ? identificacion1 : identificacion;
        }
        return "";
    }

    public String valorNombre(String nombre, String nombre1) {
        if (personaSel != null) {
            return (nombre.equals(personaSel.getNombreCompleto())) ? nombre1 : nombre;
        }
        return "";
    }

    public String valorRol(long codigoPersona, TipoVinculado tipoVinculado) {
        if (personaSel != null) {
            if (personaSel.getCodigo() == codigoPersona) {
                return tipoVinculado.getRol();
            } else {
                return ejbFacade.getRolReciprocoByVinculadoYpersona(codigoPersona, tipoVinculado.getCodigoMasculino(), tipoVinculado.getCodigoFemenino());
            }
        }
        return "";
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
     * @return the itemsPersona
     */
    public List<Persona> getItemsPersona() {
        return itemsPersona;
    }

    /**
     * @param itemsPersona the itemsPersona to set
     */
    public void setItemsPersona(List<Persona> itemsPersona) {
        this.itemsPersona = itemsPersona;
    }

    /**
     * @return the personaSel
     */
    public Persona getPersonaSel() {
        return personaSel;
    }

    /**
     * @param personaSel the personaSel to set
     */
    public void setPersonaSel(Persona personaSel) {
        this.personaSel = personaSel;
    }

    /**
     * @return the nombrePersona
     */
    public String getNombrePersona() {
        return nombrePersona;
    }

    /**
     * @param nombrePersona the nombrePersona to set
     */
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    /**
     * @return the deshabilitaBuscarPersonaLista
     */
    public boolean isDeshabilitaBuscarPersonaLista() {
        return deshabilitaBuscarPersonaLista;
    }

    /**
     * @param deshabilitaBuscarPersonaLista the deshabilitaBuscarPersonaLista to
     * set
     */
    public void setDeshabilitaBuscarPersonaLista(boolean deshabilitaBuscarPersonaLista) {
        this.deshabilitaBuscarPersonaLista = deshabilitaBuscarPersonaLista;
    }

    /**
     * @return the nombrePersonaBusqueda
     */
    public String getNombrePersonaBusqueda() {
        return nombrePersonaBusqueda;
    }

    /**
     * @param nombrePersonaBusqueda the nombrePersonaBusqueda to set
     */
    public void setNombrePersonaBusqueda(String nombrePersonaBusqueda) {
        this.nombrePersonaBusqueda = nombrePersonaBusqueda;
    }

    /**
     * @return the mensajeCriterio
     */
    public String getMensajeCriterio() {
        return mensajeCriterio;
    }

    /**
     * @param mensajeCriterio the mensajeCriterio to set
     */
    public void setMensajeCriterio(String mensajeCriterio) {
        this.mensajeCriterio = mensajeCriterio;
    }

    /**
     * @return the itemsPersonaVinculado
     */
    public List<PersonaVinculado> getItemsPersonaVinculado() {
        return itemsPersonaVinculado;
    }

    /**
     * @param itemsPersonaVinculado the itemsPersonaVinculado to set
     */
    public void setItemsPersonaVinculado(List<PersonaVinculado> itemsPersonaVinculado) {
        this.itemsPersonaVinculado = itemsPersonaVinculado;
    }

    /**
     * @return the personaVincularSel
     */
    public Persona getPersonaVincularSel() {
        return personaVincularSel;
    }

    /**
     * @param personaVincularSel the personaVincularSel to set
     */
    public void setPersonaVincularSel(Persona personaVincularSel) {
        this.personaVincularSel = personaVincularSel;
    }

    /**
     * @return the tipoVinculado
     */
    public TipoVinculado getTipoVinculado() {
        return tipoVinculado;
    }

    /**
     * @param tipoVinculado the tipoVinculado to set
     */
    public void setTipoVinculado(TipoVinculado tipoVinculado) {
        this.tipoVinculado = tipoVinculado;
    }

    /**
     * @return the itemsTipoVinculados
     */
    public List<TipoVinculado> getItemsTipoVinculados() {
        return itemsTipoVinculados;
    }

    /**
     * @param itemsTipoVinculados the itemsTipoVinculados to set
     */
    public void setItemsTipoVinculados(List<TipoVinculado> itemsTipoVinculados) {
        this.itemsTipoVinculados = itemsTipoVinculados;
    }

    /**
     * @return the itemsCargo
     */
    public List<Cargo> getItemsCargo() {
        return itemsCargo;
    }

    /**
     * @param itemsCargo the itemsCargo to set
     */
    public void setItemsCargo(List<Cargo> itemsCargo) {
        this.itemsCargo = itemsCargo;
    }

    /**
     * @return the personaRelacionIfip
     */
    public PersonaRelacionIfip getPersonaRelacionIfip() {
        return personaRelacionIfip;
    }

    /**
     * @param personaRelacionIfip the personaRelacionIfip to set
     */
    public void setPersonaRelacionIfip(PersonaRelacionIfip personaRelacionIfip) {
        this.personaRelacionIfip = personaRelacionIfip;
    }

    /**
     * @return the itemsIfipAgencia
     */
    public List<IfipAgencia> getItemsIfipAgencia() {
        return itemsIfipAgencia;
    }

    /**
     * @param itemsIfipAgencia the itemsIfipAgencia to set
     */
    public void setItemsIfipAgencia(List<IfipAgencia> itemsIfipAgencia) {
        this.itemsIfipAgencia = itemsIfipAgencia;
    }
}
