package ec.renafipse.mks.convertir.ahorros;

import ec.renafipse.mks.modelo.ahorros.LibretaAsignadaCuenta;
import ec.renafipse.mks.negocio.ahorros.LibretaAsignadaCuentaFacade;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class LibretaAsignadaCuentaConverter implements Converter {

    @EJB
    private LibretaAsignadaCuentaFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    java.lang.String getKey(String value) {
        java.lang.String key;
        key = value;
        return key;
    }

    String getStringKey(java.lang.String value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof LibretaAsignadaCuenta) {
            LibretaAsignadaCuenta o = (LibretaAsignadaCuenta) object;
            return getStringKey(o.getNumeroLibreta());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LibretaAsignadaCuenta.class.getName()});
            return null;
        }
    }

}
