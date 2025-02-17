package ec.renafipse.mks.control;

import ec.renafipse.mks.negocio.AbstractFacade;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;

import javax.ejb.EJBException;

/**
 * Represents an abstract shell of to be used as JSF Controller to be used in
 * AJAX-enabled applications. No outcomes will be generated from its methods
 * since handling is designed to be done inside one page.
 */
public abstract class AbstractController<T> {

    private AbstractFacade<T> ejbFacade;
    private Class<T> itemClass;
    private T selected;
    private List<T> items;

    
    private enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }

    public AbstractController() {
    }

    public AbstractController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    protected AbstractFacade<T> getFacade() {
        return ejbFacade;
    }

    protected void setFacade(AbstractFacade<T> ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    ;

    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    /**
     * Returns all items in a List object
     *
     * @return
     */
    public List<T> getItems() {
        if (items == null) {
            items = this.ejbFacade.findAll();
        }
        return items;
    }

    public void save(ActionEvent event) {
        String msg = "";// = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Updated");
        persist(PersistAction.UPDATE, msg);    
    }

    public void saveNew(ActionEvent event) {
        String msg = "";
        persist(PersistAction.CREATE, msg);        
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

   
    
    public void delete(ActionEvent event) {
        //String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Deleted");
        String msg = "";
        persist(PersistAction.DELETE, msg);        
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            this.setEmbeddableKeys();
            try {
                String msg = "";
                if (persistAction == PersistAction.UPDATE) {
                    this.ejbFacade.edit(selected);
                    MuestraMensaje.addSatisfactorioPersistencia(1);                   
                } else if (persistAction == PersistAction.CREATE){
                    this.ejbFacade.create(selected);
                    MuestraMensaje.addSatisfactorioPersistencia(1);
                }
                else {
                    this.ejbFacade.remove(selected);
                    MuestraMensaje.addSatisfactorioPersistencia(2);                   
                }

            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                //System.out.print("Error en PERSISTENCIA " + ex.toString());
                msg = CapturaError.getErrorPersistencia(ex);
                if (msg == null) {
                    MuestraMensaje.addErrorPersistencia();
                } else {
                    MuestraMensaje.addErrorPersistencia(msg);
                }

                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, CapturaError.getErrorException(ex));

                /*if (msg.length() > 0) {
                 JsfUtil.addErrorMessage(msg);
                 } else {
                 JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/bdhBL").getString("PersistenceErrorOccured"));
                 }*/
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, CapturaError.getErrorException(ex));
                MuestraMensaje.addErrorPersistencia();
                //JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/bdhBL").getString("PersistenceErrorOccured"));
            }
        }
    }
    
    

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @param event
     * @return
     */
    public T prepareCreate(ActionEvent event) {
        T newItem;
        try {
            newItem = itemClass.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();
            return newItem;
        } catch (InstantiationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

}
