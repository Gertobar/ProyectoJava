package ec.renafipse.mks.convertir.creditos;

import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class CalculoCuotaPagarConverter implements Converter {

    @EJB
    private CalculoCuotaPagarFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.renafipse.mks.modelo.creditos.CalculoCuotaPagarPK getKey(String value) {
        ec.renafipse.mks.modelo.creditos.CalculoCuotaPagarPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.renafipse.mks.modelo.creditos.CalculoCuotaPagarPK();
        key.setNumeroCredito(Long.parseLong(values[0]));
        key.setCodigoIfip(Long.parseLong(values[1]));
        key.setCuota(Long.parseLong(values[2]));
        return key;
    }

    String getStringKey(ec.renafipse.mks.modelo.creditos.CalculoCuotaPagarPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getNumeroCredito());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoIfip());
        sb.append(SEPARATOR);
        sb.append(value.getCuota());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof CalculoCuotaPagar) {
            CalculoCuotaPagar o = (CalculoCuotaPagar) object;
            return getStringKey(o.getCalculoCuotaPagarPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CalculoCuotaPagar.class.getName()});
            return null;
        }
    }

}
