package ec.renafipse.mks.convertir.creditos;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.creditos.GaranteNotificacion;
import ec.renafipse.mks.negocio.creditos.GaranteNotificacionFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class GaranteNotificacionConverter implements Converter {

    @EJB
    private GaranteNotificacionFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.creditos.GaranteNotificacionPK getKey(String value) {
        ec.renafipse.mks.modelo.creditos.GaranteNotificacionPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.creditos.GaranteNotificacionPK();
        key.setCodigoNotificacion(Long.parseLong(values[0]));
        key.setCodigoGarante(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.creditos.GaranteNotificacionPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoNotificacion());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoGarante());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof GaranteNotificacion) {
            GaranteNotificacion o = (GaranteNotificacion) object;
            return getStringKey(o.getGaranteNotificacionPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), GaranteNotificacion.class.getName()});
            return null;
        }
    }

}
