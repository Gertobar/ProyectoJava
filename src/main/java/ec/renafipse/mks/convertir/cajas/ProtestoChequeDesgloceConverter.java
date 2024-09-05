package ec.renafipse.mks.convertir.cajas;

import ec.renafipse.mks.modelo.cajas.ProtestoChequeDesgloce;
import ec.renafipse.mks.negocio.cajas.ProtestoChequeDesgloceFacade;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class ProtestoChequeDesgloceConverter implements Converter {

    @EJB
    private ProtestoChequeDesgloceFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    java.lang.Long getKey(String value) {
        java.lang.Long key;
        key = Long.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Long value) {
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
        if (object instanceof ProtestoChequeDesgloce) {
            ProtestoChequeDesgloce o = (ProtestoChequeDesgloce) object;
            return getStringKey(o.getCodigoCheque());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProtestoChequeDesgloce.class.getName()});
            return null;
        }
    }

}
