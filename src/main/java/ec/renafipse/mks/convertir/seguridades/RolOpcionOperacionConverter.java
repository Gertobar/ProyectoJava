package ec.renafipse.mks.convertir.seguridades;

import ec.renafipse.mks.modelo.seguridades.RolOpcionOperacion;
import ec.renafipse.mks.negocio.seguridades.RolOpcionOperacionFacade;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class RolOpcionOperacionConverter implements Converter {

    @EJB
    private RolOpcionOperacionFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.seguridades.RolOpcionOperacionPK getKey(String value) {
        ec.renafipse.mks.modelo.seguridades.RolOpcionOperacionPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.seguridades.RolOpcionOperacionPK();
        key.setCodigoOperacion(Long.parseLong(values[0]));
        key.setCodigoIfip(Long.parseLong(values[1]));
        key.setCodigoRol(values[2]);
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.seguridades.RolOpcionOperacionPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoOperacion());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoIfip());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoRol());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof RolOpcionOperacion) {
            RolOpcionOperacion o = (RolOpcionOperacion) object;
            return getStringKey(o.getRolOpcionOperacionPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RolOpcionOperacion.class.getName()});
            return null;
        }
    }

}
