package ec.renafipse.mks.convertir.seguridades;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.seguridades.RolIngresoEgresoIfip;
import ec.renafipse.mks.negocio.seguridades.RolIngresoEgresoIfipFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class RolIngresoEgresoIfipConverter implements Converter {

    @EJB
    private RolIngresoEgresoIfipFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.seguridades.RolIngresoEgresoIfipPK getKey(String value) {
        ec.renafipse.mks.modelo.seguridades.RolIngresoEgresoIfipPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.seguridades.RolIngresoEgresoIfipPK();
        key.setCodigoIngresoEgreso(Long.parseLong(values[0]));
        key.setCodigoMoneda(Long.parseLong(values[1]));
        key.setCodigoRol(values[2]);
        key.setCodigoIfip(Long.parseLong(values[3]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.seguridades.RolIngresoEgresoIfipPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoIngresoEgreso());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoMoneda());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoRol());
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
        if (object instanceof RolIngresoEgresoIfip) {
            RolIngresoEgresoIfip o = (RolIngresoEgresoIfip) object;
            return getStringKey(o.getRolIngresoEgresoIfipPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RolIngresoEgresoIfip.class.getName()});
            return null;
        }
    }

}
