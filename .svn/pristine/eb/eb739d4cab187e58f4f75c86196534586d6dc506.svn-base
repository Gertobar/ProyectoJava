package ec.renafipse.mks.convertir.creditos;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.creditos.TipoNotificacionIfip;
import ec.renafipse.mks.negocio.creditos.TipoNotificacionIfipFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class TipoNotificacionIfipConverter implements Converter {

    @EJB
    private TipoNotificacionIfipFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.creditos.TipoNotificacionIfipPK getKey(String value) {
        ec.renafipse.mks.modelo.creditos.TipoNotificacionIfipPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.creditos.TipoNotificacionIfipPK();
        key.setCodigoTipoNotificacion(Long.parseLong(values[0]));
        key.setCodigoIfip(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.creditos.TipoNotificacionIfipPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoTipoNotificacion());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoIfip());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof TipoNotificacionIfip) {
            TipoNotificacionIfip o = (TipoNotificacionIfip) object;
            return getStringKey(o.getTipoNotificacionIfipPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoNotificacionIfip.class.getName()});
            return null;
        }
    }

}
