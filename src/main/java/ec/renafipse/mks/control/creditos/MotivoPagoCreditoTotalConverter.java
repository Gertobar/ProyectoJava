package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.creditos.MotivoPagoCreditoTotal;
import ec.renafipse.mks.modelo.creditos.MotivoPagoCreditoTotalPK;
import ec.renafipse.mks.negocio.creditos.MotivoPagoCreditoTotalFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class MotivoPagoCreditoTotalConverter implements Converter {

    @EJB
    private MotivoPagoCreditoTotalFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

        MotivoPagoCreditoTotalPK getKey(String value) {
        MotivoPagoCreditoTotalPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new MotivoPagoCreditoTotalPK();
        key.setCodigoPago(Long.parseLong(values[0]));
        key.setCodigoTipoMotivo(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(MotivoPagoCreditoTotalPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoPago());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoTipoMotivo());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof MotivoPagoCreditoTotal) {
            MotivoPagoCreditoTotal o = (MotivoPagoCreditoTotal) object;
            return getStringKey(o.getMotivoPagoCreditoTotalPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MotivoPagoCreditoTotal.class.getName()});
            return null;
        }
    }

}
