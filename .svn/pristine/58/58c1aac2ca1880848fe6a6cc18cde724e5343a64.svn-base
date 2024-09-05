package ec.renafipse.mks.convertir.contables;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.contables.PlanDeCuentaIfip;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaIfipFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class PlanDeCuentaIfipConverter implements Converter {

    @EJB
    private PlanDeCuentaIfipFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.contables.PlanDeCuentaIfipPK getKey(String value) {
        ec.renafipse.mks.modelo.contables.PlanDeCuentaIfipPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.contables.PlanDeCuentaIfipPK();
        key.setCuentaContable(values[0]);
        key.setCodigoTipoPlan(Long.parseLong(values[1]));
        key.setCodigoIfip(Long.parseLong(values[2]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.contables.PlanDeCuentaIfipPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCuentaContable());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoTipoPlan());
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
        if (object instanceof PlanDeCuentaIfip) {
            PlanDeCuentaIfip o = (PlanDeCuentaIfip) object;
            return getStringKey(o.getPlanDeCuentaIfipPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PlanDeCuentaIfip.class.getName()});
            return null;
        }
    }

}
