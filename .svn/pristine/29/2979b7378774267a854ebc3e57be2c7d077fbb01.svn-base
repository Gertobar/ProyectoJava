package ec.renafipse.mks.convertir.ifips;

import ec.renafipse.mks.modelo.ifips.IfipAgenciaJornadaLaboral;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaJornadaLaboralFacade;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class IfipAgenciaJornadaLaboralConverter implements Converter {

    @EJB
    private IfipAgenciaJornadaLaboralFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.ifips.IfipAgenciaJornadaLaboralPK getKey(String value) {
        ec.renafipse.mks.modelo.ifips.IfipAgenciaJornadaLaboralPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.ifips.IfipAgenciaJornadaLaboralPK();
        key.setDia(Short.parseShort(values[0]));
        key.setMeridiano(values[1].charAt(0));
        key.setCodigoIfipAgencia(Long.parseLong(values[2]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.ifips.IfipAgenciaJornadaLaboralPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getDia());
        sb.append(SEPARATOR);
        sb.append(value.getMeridiano());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoIfipAgencia());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof IfipAgenciaJornadaLaboral) {
            IfipAgenciaJornadaLaboral o = (IfipAgenciaJornadaLaboral) object;
            return getStringKey(o.getIfipAgenciaJornadaLaboralPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), IfipAgenciaJornadaLaboral.class.getName()});
            return null;
        }
    }

}
