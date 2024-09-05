package ec.renafipse.mks.convertir.seguridades;

import ec.renafipse.mks.modelo.seguridades.RolOpcionOpePorDef;
import ec.renafipse.mks.negocio.seguridades.RolOpcionOpePorDefFacade;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class RolOpcionOpePorDefConverter implements Converter {

    @EJB
    private RolOpcionOpePorDefFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.seguridades.RolOpcionOpePorDefPK getKey(String value) {
        ec.renafipse.mks.modelo.seguridades.RolOpcionOpePorDefPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.seguridades.RolOpcionOpePorDefPK();
        key.setCodigoRol(values[0]);
        key.setCodigoOperacion(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.seguridades.RolOpcionOpePorDefPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoRol());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoOperacion());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof RolOpcionOpePorDef) {
            RolOpcionOpePorDef o = (RolOpcionOpePorDef) object;
            return getStringKey(o.getRolOpcionOpePorDefPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RolOpcionOpePorDef.class.getName()});
            return null;
        }
    }

}
