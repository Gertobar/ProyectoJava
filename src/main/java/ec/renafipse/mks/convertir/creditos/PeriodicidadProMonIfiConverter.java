package ec.renafipse.mks.convertir.creditos;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfi;
import ec.renafipse.mks.negocio.creditos.PeriodicidadProMonIfiFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class PeriodicidadProMonIfiConverter implements Converter {

    @EJB
    private PeriodicidadProMonIfiFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfiPK getKey(String value) {
        ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfiPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfiPK();
        key.setCodigoProducto(Long.parseLong(values[0]));
        key.setCodigoMoneda(Long.parseLong(values[1]));
        key.setCodigoIfip(Long.parseLong(values[2]));
        key.setCodigoPeriodicidad(Long.parseLong(values[3]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfiPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoProducto());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoMoneda());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoIfip());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoPeriodicidad());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof PeriodicidadProMonIfi) {
            PeriodicidadProMonIfi o = (PeriodicidadProMonIfi) object;
            return getStringKey(o.getPeriodicidadProMonIfiPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PeriodicidadProMonIfi.class.getName()});
            return null;
        }
    }

}
