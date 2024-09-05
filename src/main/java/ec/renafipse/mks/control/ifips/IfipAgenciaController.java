package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;

import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.negocio.comunes.UbicacionGeograficaFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "ifipAgenciaController")
@ViewScoped
public class IfipAgenciaController extends AbstractController<IfipAgencia> implements Serializable {

    @EJB
    private IfipAgenciaFacade ejbFacade;
    @EJB
    private UbicacionGeograficaFacade ejbFacadeUbiGeo;
    @EJB
    private UbicacionGeograficaFacade ejbFacadeUbiGeoPai;
    @EJB
    private UbicacionGeograficaFacade ejbFacadeUbiGeoPro;
    @EJB
    private UbicacionGeograficaFacade ejbFacadeUbiGeoCiu;
    @EJB
    private UbicacionGeograficaFacade ejbFacadeUbiGeoPar;
    @EJB
    private IfipFacade EjbIfipFacade;

    private UbicacionGeografica ubiGeoPai;
    private UbicacionGeografica ubiGeoPro;
    private UbicacionGeografica ubiGeoCiu;
    private UbicacionGeografica ubiGeoPar;
    private IfipAgencia ifipAgencia;
    private Ifip ifip;
    private UbicacionGeografica ubicacionGeografica;

    private List<UbicacionGeografica> itemsUbiGeoPai;
    private List<UbicacionGeografica> itemsUbiGeoPro;
    private List<UbicacionGeografica> itemsUbiGeoCiu;
    private List<UbicacionGeografica> itemsUbiGeoPar;
    private List<IfipAgencia> itemsIfipAgencia;

    public IfipAgenciaController() {
        super(IfipAgencia.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.preparaUbicacion();
        this.ifip = new Ifip();
        this.setItemsIfipAgencia(this.ejbFacade.getItemsIfipAgenciaPorIfip(ActivacionUsuario.getCodigoIfip()));

    }

    public void preparaUbicacion() {
        this.setItemsUbiGeoPai(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1")));
        this.ubiGeoPai=null;
       
     

    }

    public void cargarUibicacion() {
        this.setIfipAgencia(this.getSelected());

  
                    if (this.getIfipAgencia().getCodigoUbiGeoPais().getCodigoJerarquia().getSiglas().equals("PA")) 
                    {
                    this.ubiGeoPai = this.getIfipAgencia().getCodigoUbiGeoPais();
                   
                    this.cambiaUbiGeoPai();
                    this.cambiaUbiGeoPro();
                    this.cambiaUbiGeoCiu();
                    }

                if (this.getIfipAgencia().getCodigoUbiGeoProvincia().getCodigoJerarquia().getSiglas().equals("PR")) {
                    this.ubiGeoPai = this.getIfipAgencia().getCodigoUbiGeoPais();
                    this.cambiaUbiGeoPai();
                    this.ubiGeoPro = this.getIfipAgencia().getCodigoUbiGeoProvincia();   

                    this.cambiaUbiGeoPai();
                    this.cambiaUbiGeoPro();

                }

                if (this.getIfipAgencia().getCodigoUbiGeoCiudad().getCodigoJerarquia().getSiglas().equals("CI")) {
                   this.ubiGeoPai = this.getIfipAgencia().getCodigoUbiGeoPais();
                    this.cambiaUbiGeoPai();
                    this.ubiGeoPro = this.getIfipAgencia().getCodigoUbiGeoProvincia();   
                    this.cambiaUbiGeoPro();
                    this.ubiGeoCiu = this.getIfipAgencia().getCodigoUbiGeoCiudad();

                    this.cambiaUbiGeoCiu();
                    
                }

                if (this.getIfipAgencia().getCodigoUbiGeoParroquia().getCodigoJerarquia().getSiglas().equals("PQ")) {
                    this.ubiGeoPai = this.getIfipAgencia().getCodigoUbiGeoPais();
                    this.cambiaUbiGeoPai();
                    this.ubiGeoPro = this.getIfipAgencia().getCodigoUbiGeoProvincia();
                    this.cambiaUbiGeoPro();
                    this.ubiGeoCiu = this.getIfipAgencia().getCodigoUbiGeoCiudad();
                    this.cambiaUbiGeoCiu();
                    this.ubiGeoPar = this.getIfipAgencia().getCodigoUbiGeoParroquia();

                }
        
        
    }

    public void cambiaUbiGeoPai() {
        this.setItemsUbiGeoPro(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoPai()));
        this.setUbiGeoPro(null);
        this.setItemsUbiGeoCiu(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoPro()));
        this.setUbiGeoCiu(null);
        this.setItemsUbiGeoPar(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiu()));
        this.setUbiGeoPar(null);
    }

    public void cambiaUbiGeoPro() {
        this.setItemsUbiGeoCiu(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoPro()));
        this.setUbiGeoCiu(null);
        this.setItemsUbiGeoPar(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiu()));
        this.setUbiGeoPar(null);
    }

    public void cambiaUbiGeoCiu() {
        this.setItemsUbiGeoPar(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiu()));
        this.setUbiGeoPar(null);
    }

    public void nuevo(ActionEvent event) {
        this.setSelected(new IfipAgencia());
    }

    public void eliminar(ActionEvent event) {
        this.delete(event);
        this.itemsIfipAgencia = this.ejbFacade.getItemsIfipAgenciaPorIfip(ActivacionUsuario.getCodigoIfip());
    }

    public void guardar(ActionEvent event) {

        this.ifip = this.EjbIfipFacade.find(ActivacionUsuario.getCodigoIfip());

        this.getSelected().setCodigoUbiGeoPais(this.getUbiGeoPai());
        this.getSelected().setCodigoUbiGeoProvincia(this.getUbiGeoPro());
        this.getSelected().setCodigoUbiGeoCiudad(this.getUbiGeoCiu());
        this.getSelected().setCodigoUbiGeoParroquia(this.getUbiGeoPar());
        this.getSelected().setNombre(this.getSelected().getNombre().toString());
        this.getSelected().setAutorizacionSri(this.getSelected().getAutorizacionSri().toString());
        this.getSelected().setDireccion(this.getSelected().getDireccion().toString());
        this.getSelected().setEmail(this.getSelected().getEmail().toString());
        this.getSelected().setEsVentanilla(this.getSelected().getEsVentanilla());
        this.getSelected().setMatriz(this.getSelected().getMatriz());
        this.getSelected().setCodigoIfipAgenciaPadre(this.getSelected().getCodigoIfipAgenciaPadre());
        this.getSelected().setCodigoIfip(ifip);
        this.getSelected().setEliminado(this.getSelected().getEliminado());
        this.ejbFacade.create(this.getSelected());
        this.itemsIfipAgencia = this.ejbFacade.getItemsIfipAgenciaPorIfip(ActivacionUsuario.getCodigoIfip());
        String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
        MuestraMensaje.addSatisfactorio(msg);

    }
    
    public void editarUbicacion(ActionEvent event)
    {
        try{
        this.preparaUbicacion();
        this.cargarUibicacion();
        
      
                
            
        
        }catch(Exception ex)
        {
        //System.out.println(ex);
        }
        
    }

    /**
     * @return the itemsUbiGeoPai
     */
    public List<UbicacionGeografica> getItemsUbiGeoPai() {
        return itemsUbiGeoPai;
    }

    /**
     * @param itemsUbiGeoPai the itemsUbiGeoPai to set
     */
    public void setItemsUbiGeoPai(List<UbicacionGeografica> itemsUbiGeoPai) {
        this.itemsUbiGeoPai = itemsUbiGeoPai;
    }

    /**
     * @return the itemsUbiGeoPro
     */
    public List<UbicacionGeografica> getItemsUbiGeoPro() {
        return itemsUbiGeoPro;
    }

    /**
     * @param itemsUbiGeoPro the itemsUbiGeoPro to set
     */
    public void setItemsUbiGeoPro(List<UbicacionGeografica> itemsUbiGeoPro) {
        this.itemsUbiGeoPro = itemsUbiGeoPro;
    }

    /**
     * @return the itemsUbiGeoCiu
     */
    public List<UbicacionGeografica> getItemsUbiGeoCiu() {
        return itemsUbiGeoCiu;
    }

    /**
     * @param itemsUbiGeoCiu the itemsUbiGeoCiu to set
     */
    public void setItemsUbiGeoCiu(List<UbicacionGeografica> itemsUbiGeoCiu) {
        this.itemsUbiGeoCiu = itemsUbiGeoCiu;
    }

    /**
     * @return the itemsUbiGeoPar
     */
    public List<UbicacionGeografica> getItemsUbiGeoPar() {
        return itemsUbiGeoPar;
    }

    /**
     * @param itemsUbiGeoPar the itemsUbiGeoPar to set
     */
    public void setItemsUbiGeoPar(List<UbicacionGeografica> itemsUbiGeoPar) {
        this.itemsUbiGeoPar = itemsUbiGeoPar;
    }

    /**
     * @return the ejbFacadeUbiGeo
     */
    public UbicacionGeograficaFacade getEjbFacadeUbiGeo() {
        return ejbFacadeUbiGeo;
    }

    /**
     * @param ejbFacadeUbiGeo the ejbFacadeUbiGeo to set
     */
    public void setEjbFacadeUbiGeo(UbicacionGeograficaFacade ejbFacadeUbiGeo) {
        this.ejbFacadeUbiGeo = ejbFacadeUbiGeo;
    }

    /**
     * @return the ejbFacadeUbiGeoPai
     */
    public UbicacionGeograficaFacade getEjbFacadeUbiGeoPai() {
        return ejbFacadeUbiGeoPai;
    }

    /**
     * @param ejbFacadeUbiGeoPai the ejbFacadeUbiGeoPai to set
     */
    public void setEjbFacadeUbiGeoPai(UbicacionGeograficaFacade ejbFacadeUbiGeoPai) {
        this.ejbFacadeUbiGeoPai = ejbFacadeUbiGeoPai;
    }

    /**
     * @return the ejbFacadeUbiGeoPro
     */
    public UbicacionGeograficaFacade getEjbFacadeUbiGeoPro() {
        return ejbFacadeUbiGeoPro;
    }

    /**
     * @param ejbFacadeUbiGeoPro the ejbFacadeUbiGeoPro to set
     */
    public void setEjbFacadeUbiGeoPro(UbicacionGeograficaFacade ejbFacadeUbiGeoPro) {
        this.ejbFacadeUbiGeoPro = ejbFacadeUbiGeoPro;
    }

    /**
     * @return the ejbFacadeUbiGeoCiu
     */
    public UbicacionGeograficaFacade getEjbFacadeUbiGeoCiu() {
        return ejbFacadeUbiGeoCiu;
    }

    /**
     * @param ejbFacadeUbiGeoCiu the ejbFacadeUbiGeoCiu to set
     */
    public void setEjbFacadeUbiGeoCiu(UbicacionGeograficaFacade ejbFacadeUbiGeoCiu) {
        this.ejbFacadeUbiGeoCiu = ejbFacadeUbiGeoCiu;
    }

    /**
     * @return the ejbFacadeUbiGeoPar
     */
    public UbicacionGeograficaFacade getEjbFacadeUbiGeoPar() {
        return ejbFacadeUbiGeoPar;
    }

    /**
     * @param ejbFacadeUbiGeoPar the ejbFacadeUbiGeoPar to set
     */
    public void setEjbFacadeUbiGeoPar(UbicacionGeograficaFacade ejbFacadeUbiGeoPar) {
        this.ejbFacadeUbiGeoPar = ejbFacadeUbiGeoPar;
    }

    /**
     * @return the ubiGeoPai
     */
    public UbicacionGeografica getUbiGeoPai() {
        return ubiGeoPai;
    }

    /**
     * @param ubiGeoPai the ubiGeoPai to set
     */
    public void setUbiGeoPai(UbicacionGeografica ubiGeoPai) {
        this.ubiGeoPai = ubiGeoPai;
    }

    /**
     * @return the ubiGeoPro
     */
    public UbicacionGeografica getUbiGeoPro() {
        return ubiGeoPro;
    }

    /**
     * @param ubiGeoPro the ubiGeoPro to set
     */
    public void setUbiGeoPro(UbicacionGeografica ubiGeoPro) {
        this.ubiGeoPro = ubiGeoPro;
    }

    /**
     * @return the ubiGeoCiu
     */
    public UbicacionGeografica getUbiGeoCiu() {
        return ubiGeoCiu;
    }

    /**
     * @param ubiGeoCiu the ubiGeoCiu to set
     */
    public void setUbiGeoCiu(UbicacionGeografica ubiGeoCiu) {
        this.ubiGeoCiu = ubiGeoCiu;
    }

    /**
     * @return the ubiGeoPar
     */
    public UbicacionGeografica getUbiGeoPar() {
        return ubiGeoPar;
    }

    /**
     * @param ubiGeoPar the ubiGeoPar to set
     */
    public void setUbiGeoPar(UbicacionGeografica ubiGeoPar) {
        this.ubiGeoPar = ubiGeoPar;
    }

    /**
     * @return the ubicacionGeografica
     */
    public UbicacionGeografica getUbicacionGeografica() {
        return ubicacionGeografica;
    }

    /**
     * @param ubicacionGeografica the ubicacionGeografica to set
     */
    public void setUbicacionGeografica(UbicacionGeografica ubicacionGeografica) {
        this.ubicacionGeografica = ubicacionGeografica;
    }

    /**
     * @return the ifipAgencia
     */
    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    /**
     * @param ifipAgencia the ifipAgencia to set
     */
    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
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
